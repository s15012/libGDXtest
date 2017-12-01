package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Direction;
import com.mygdx.game.DungeonBlockManager;
import com.mygdx.game.hud.Status;
import com.mygdx.game.objects.enemies.Enemy;
import com.mygdx.game.objects.enemies.TestEnemy;

public abstract class MovableDungeonObject extends AnimatableDungeonObject {

    public MovableDungeonObject(int x, int y, int tiledX, int tiledY, int index) {
        super(x, y, tiledX, tiledY, index);
    }

    public DungeonBlockManager getDungeonBlockManager() {
        return dungeonBlockManager;
    }

    public void setDungeonBlockManager(DungeonBlockManager dungeonBlockManager) {
        this.dungeonBlockManager = dungeonBlockManager;
    }

    DungeonBlockManager dungeonBlockManager;
    Animations moveAnimation;

    Vector2 target = new Vector2();
    TestEnemy testEnemy;

    public enum State { //状態
        IDLE,
        MOVE
    }

    protected State state = State.IDLE;

    public void setCharacterDir(Direction characterDir) {
        this.characterDir = characterDir;
    }

    public Direction getCharacterDir() {
        return characterDir;
    }

    Direction characterDir;

    //マス
    private float moveX = 32;
    private float moveY = 32;

    private Direction moveDirection;

    public void initPosition(float x, float y) {
        super.initPosition(x, y);
        target.x = current.x;
        target.y = current.y;
    }

    public Vector2 getTargetPosition() {
        return target;
    }

    public State getState() {
        return state;
    }

    protected Animation currentAnim() {
        switch (state) {
            case MOVE:
                return moveAnimation.convertDirectionToAnimation(direction);
            case IDLE:
                return idleAnimation.convertDirectionToAnimation(direction);
        }
        return null;
    }

    protected void positionMove(Direction direction, float dx, float dy) {
        state = State.MOVE;
        this.direction = direction;
        moveDirection = direction;

        setCharacterDir(direction);
        Vector2 block = dungeonBlockManager.vectorToBlockVector(current);

        DungeonObject nextTile = dungeonBlockManager.getObjectType((int) block.x + (int) dx, (int) block.y + (int) dy);

        if (nextTile == null) return;
        if (nextTile.isBlock) return;

        target.x = (float) Math.floor(nextTile.current.x);
        target.y = (float) Math.floor(nextTile.current.y);

        Gdx.app.log("positiomMOVE", target.toString());
    }


//    public void checkedNextTiled(Direction direction, float dx, float dy) {
//        this.direction = direction;
//
//        Vector2 block = dungeonBlockManager.vectorToBlockVector(current);
//        nextTile = dungeonBlockManager.getObjectType((int) block.x + (int) dx, (int) block.y + (int) dy);
//
//        target.x = (float) Math.floor(nextTile.current.x);
//        target.y = (float) Math.floor(nextTile.current.y);
//
//        Vector2 ec = testEnemy.getCurrentPosition();
//
//        Gdx.app.log("EnemyPos", ec.toString());
//    }

    public boolean move() {
        return move(false);
    }

    public boolean move(boolean isResetMoveDirection) {
        if (state == State.MOVE) {
            if (current.x < target.x) {
                current.x++;
            } else if (current.x > target.x) {
                current.x--;
            }

            if (current.y < target.y) {
                current.y++;
            } else if (current.y > target.y) {
                current.y--;
            }

            if ((long) current.x == (long) target.x && (long) current.y == (long) target.y) {
                if (isResetMoveDirection) {
                    moveDirection = null;
                }
                if (moveDirection == null) {
                    state = State.IDLE;
                } else {
                    setMoveDirection(direction);
                }
            }
            return true;
        }
        return false;
    }

    public void setMoveDirection(Direction direction) {
        switch (direction) {
            case LEFT_UP:
                moveLeftUp();
                break;
            case UP:
                moveUp();
                break;
            case RIGHT_UP:
                moveRightUp();
                break;
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
            case LEFT_DOWN:
                moveLeftDown();
                break;
            case DOWN:
                moveDown();
                break;
            case RIGHT_DOWN:
                moveRightDown();
                break;
        }
    }

//    public void setAction(Status status) {
//        switch (status) {
//            case ATTACK:
//                setNextDirection(direction);
//                judgeEnemy();
//                Gdx.app.log("POS???", nextTile.current.toString());
//
//                if (isEnemy) {
//                    Gdx.app.log("ENEMY:", "IS TRUE");
//                } else {
//                    Gdx.app.log("ENEMY:", "IS FALSE");
//                }
//
//                attack();
//                break;
//        }
//    }

//    public void setNextDirection(Direction direction) {
//        switch (direction) {
//            case LEFT_UP:
//                checkLeftUp();
//                break;
//            case UP:
//                checkUp();
//                break;
//            case RIGHT_UP:
//                checkRightUp();
//                break;
//            case LEFT:
//                checkLeft();
//                break;
//            case RIGHT:
//                checkRight();
//                break;
//            case LEFT_DOWN:
//                checkLeftDown();
//                break;
//            case DOWN:
//                checkDown();
//                break;
//            case RIGHT_DOWN:
//                checkRightDown();
//                break;
//        }
//    }
//
//    public void judgeEnemy() {
//        //TODO Enemy判定
//        Vector2 nextPosition = nextTile.getCurrentPosition();
//        if ( == nextPosition) {
//            isEnemy = true;
//        } else {
//            isEnemy = false;
//        }
//    }

    public abstract void attack();

    /**
     * TODO 攻撃→Dirチェック→DirNextBlockチェック→Enemyチェック
     * positionMoveみたいにnextTileの取得
     * 攻撃アニメーションはAnimationsに入れる？（問題はStateとかの判別してるから攻撃アニメーションのクラスとかの方がいいか・・・
     * Enemyがいる場合はダメージ判定、他は空振りで。（とりあえずアニメーションは確定動作
     */


    public abstract void moveLeft();

    public abstract void moveRight();

    public abstract void moveUp();

    public abstract void moveDown();

    public abstract void moveLeftUp();

    public abstract void moveRightUp();

    public abstract void moveLeftDown();

    public abstract void moveRightDown();

    public abstract void checkLeft();

    public abstract void checkRight();

    public abstract void checkUp();

    public abstract void checkDown();

    public abstract void checkLeftUp();

    public abstract void checkRightUp();

    public abstract void checkLeftDown();

    public abstract void checkRightDown();

    public void moveReset() {
        moveDirection = null;
    }
}
