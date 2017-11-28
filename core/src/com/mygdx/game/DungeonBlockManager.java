package com.mygdx.game;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
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
