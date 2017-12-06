package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.hud.Status;
import com.mygdx.game.objects.Character;
import com.mygdx.game.objects.DungeonObject;
import com.mygdx.game.objects.GlassObstacle;
import com.mygdx.game.objects.GlassTile;
import com.mygdx.game.objects.enemies.Enemy;
import com.mygdx.game.objects.findingpath.PathFinder;
import com.mygdx.game.objects.findingpath.TiledGraph;
import com.mygdx.game.objects.findingpath.TiledNode;

import java.util.Random;

public class DungeonBlockManager implements DrawComponent {

    private int widthBlocks = 50;
    private int heightBlocks = 50;
    private final int blockSize = 32;

    TiledGraph maps;

    Character mainCharacter;
    Array<Character> characters = new Array<Character>();

    Array<Enemy> enemies = new Array<Enemy>();

    Array<Vector2> targetList;

    PathFinder finder;

    public DungeonBlockManager() {
        maps = new TiledGraph(widthBlocks, heightBlocks);
    }

    public void setMainCharacter(Character character) {
        mainCharacter = character;
        addCharacter(character);
    }

    public Character getMainCharacter() {
        return mainCharacter;
    }

    public void addCharacter(Character character) {
        characters.add(character);
        character.setDungeonBlockManager(this);
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
        enemy.setDungeonBlockManager(this);
    }

    public Array<TiledNode> findMainCharacter(DungeonObject object) {
        return finder.findPath(vectorToBlockVector(mainCharacter.getTargetPosition()), vectorToBlockVector(object.getCurrentPosition()));
    }

    public void moveAllObject() {
        boolean isMoved = moveMainCharacter();
        findMoveToMainCharacterAllEnemies(isMoved);
    }

    public boolean moveMainCharacter() {
        return mainCharacter.move();
    }

    public void findMoveToMainCharacterAllEnemies(boolean isMainCharacterMoved) {
        for (Enemy enemy : enemies) {
            enemy.findMoveToMainCharacter(isMainCharacterMoved);
        }
    }

    public void makeMaps() {
        int index = 0;
        DungeonObject[][] maps = new DungeonObject[widthBlocks][heightBlocks];
        for (int x = 0; x < widthBlocks; x++) {
            for (int y = 0; y < heightBlocks; y++) {
                if (x == 0 || y == 0 || x == widthBlocks - 1 || y == heightBlocks - 1) {
                    maps[x][y] = new GlassObstacle(x * blockSize, y * blockSize, x, y, index);

                } else {
                    maps[x][y] = new GlassTile(x * blockSize, y * blockSize, x, y, index);
                }
                index += 1;
            }
        }
        //テスト用にブロックを生成
        Random random = new Random();
        for (int i = 0; i <= 10; i++) {
            int sizeX = random.nextInt(3) + 1;
            int sizeY = random.nextInt(3) + 1;
            int centerX = random.nextInt(widthBlocks);
            int centerY = random.nextInt(heightBlocks);
            for (int x = centerX - sizeX; x <= centerX + sizeY; x++) {
                for (int y = centerY - sizeX; y <= centerX + sizeY; y++) {
                    int x2 = Math.max(0, Math.min(x, widthBlocks - 1));
                    int y2 = Math.max(0, Math.min(y, heightBlocks - 1));
                    DungeonObject tmp = maps[x2][y2];
                    maps[x2][y2] = new GlassObstacle(x2 * blockSize, y2 * blockSize, x2, y2, tmp.index);
                }
            }
        }
        this.maps.init(maps);
        finder = new PathFinder(this.maps);
    }

//    public void checkNextAttack(float dx, float dy) {
//        Vector2 characterBlock = vectorToBlockVector(mainCharacter.getCurrentPosition());
//        DungeonObject nextTile = getObjectType((int) characterBlock.x + (int) dx, (int) characterBlock.y + (int) dy);
//        Vector2 targetBlock = vectorToBlockVector(nextTile.getCurrentPosition());
//        Array<Boolean> enemiesJudge = new Array<Boolean>();
//
//        for (Enemy enemy : enemies) {
//            Vector2 enemyCurrent = enemy.getCurrentPosition();
//            Vector2 enemyBlock = vectorToBlockVector(enemyCurrent);
//
//            if (targetBlock.x == enemyBlock.x && targetBlock.y == enemyBlock.y) {
//                enemiesJudge.add(true);
//
//            } else {
//                enemiesJudge.add(false);
//            }
//        }
//
//        mainCharacter.attack(enemiesJudge);
    //マスを指定するメソッド
    //攻撃の処理をするメソッドに分ける
//    }

    public void attackRange(int preX, int initX, int preY, int initY) {

        targetList = new Array<Vector2>();

        for (int x = preX; x <= initX; x++) {
            for (int y = preY; y <= initY; y++) {
                Vector2 tmp = new Vector2(x, y);
                targetList.add(tmp);
            }
        }

        checkTarget(targetList);
    }

    public void checkTarget(Array<Vector2> targetList) {
        for (Vector2 target : targetList) {
            for (Enemy enemy : enemies) {
                Vector2 enemyCurrent = enemy.getCurrentPosition();
                Vector2 enemyBlock = vectorToBlockVector(enemyCurrent);

                if (target.x == enemyBlock.x && target.y == enemyBlock.y) {
                    Gdx.app.log("TARGET IS TRUE", "目標 (" + enemyBlock.toString() + ") を攻撃しました。");
                    Gdx.app.log("ENEMY DAMAGE", "enemyに" + String.valueOf(damageCalc(mainCharacter, enemy)) + "のダメージを与えた。");
                } else {
                    Gdx.app.log("TARGET IS FALSE", "(" + target.toString() + ") に目標はいませんでした。");
                }
            }
        }
    }


    public void setAction(Status status) {
        switch (status) {
            case ATTACK:
                mainCharacter.attack(mainCharacter.getCharacterDir());
        }
    }

    public void setNextDirection(Direction direction) {
        Vector2 currentBlock = vectorToBlockVector(mainCharacter.getCurrentPosition());

        switch (direction) {
            case LEFT_UP:
                mainCharacter.attackLeftUp(currentBlock);
                break;
            case UP:
                mainCharacter.attackUp(currentBlock);
                break;
            case RIGHT_UP:
                mainCharacter.attackRightUp(currentBlock);
                break;
            case LEFT:
                mainCharacter.attackLeft(currentBlock);
                break;
            case RIGHT:
                mainCharacter.attackRight(currentBlock);
                break;
            case LEFT_DOWN:
                mainCharacter.attackLeftDown(currentBlock);
                break;
            case DOWN:
                mainCharacter.attackDown(currentBlock);
                break;
            case RIGHT_DOWN:
                mainCharacter.attackRightDown(currentBlock);
                break;
        }
    }

    public int damageCalc(Character character, Enemy enemy) {
        int charaStr = character.getStr();

        int enemyHp = enemy.getHp();
        int enemyDef = enemy.getDef();

        //計算式は今のとこてきとー
        int damage = charaStr / enemyDef ;

        enemy.setHp(enemyHp - damage);
        Gdx.app.log("postHP", "ENEMYのHPは(" + enemy.getHp() + ")になりました");

        return damage;
    }
//    public void judgeEnemy(Vector2 targetPosition, Vector2 enemyPosition) {
//        if (targetPosition.x == enemyPosition.x && targetPosition.y == enemyPosition.y) {
//            isEnemy = true;
//        } else {
//            isEnemy = false;
//        }
//        mainCharacter.attack();
//    }

    public int getWidthBlockCount() {
        return widthBlocks;
    }

    public int getHeightBlockCount() {
        return heightBlocks;
    }

    public Vector2 vectorToBlockVector(Vector2 vector) {
        int blockX = Math.round(vector.x / blockSize);
        int blockY = Math.round(vector.y / blockSize);

        return new Vector2(blockX, blockY);
    }

    public DungeonObject getObjectType(int x, int y) {
        if (x < 0 || x >= getWidthBlockCount()) {
            return null;
        }

        if (y < 0 || y >= getHeightBlockCount()) {
            return null;
        }

        return (DungeonObject) this.maps.getNode(x, y);
    }

    @Override
    public void draw(Batch batch, float stateTime) {
        for (Character character : characters) {
            character.draw(batch, stateTime);
        }

        for (Enemy enemy : enemies) {
            enemy.draw(batch, stateTime);
        }
    }

    @Override
    public void dispose() {
        for (Character character : characters) {
            character.dispose();
        }

        for (Enemy enemy : enemies) {
            enemy.dispose();
        }
    }
}
