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

    public abstract void attack();

    public abstract void moveLeft();

    public abstract void moveRight();

    public abstract void moveUp();

    public abstract void moveDown();

    public abstract void moveLeftUp();

    public abstract void moveRightUp();

    public abstract void moveLeftDown();

    public abstract void moveRightDown();
//
//    public abstract void attackLeft();
//
//    public abstract void attackRight();
//
//    public abstract void attackUp();
//
//    public abstract void attackDown();
//
//    public abstract void attackLeftUp();
//
//    public abstract void attackRightUp();
//
//    public abstract void attackLeftDown();
//
//    public abstract void attackRightDown();

    public void moveReset() {
        moveDirection = null;
    }
}
