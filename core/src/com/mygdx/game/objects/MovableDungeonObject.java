package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Direction;
import com.mygdx.game.DungeonBlocks;

public abstract class MovableDungeonObject extends AnimatableDungeonObject {

    public DungeonBlocks getDungeonBlocks() {
        return dungeonBlocks;
    }

    public void setDungeonBlocks(DungeonBlocks dungeonBlocks) {
        this.dungeonBlocks = dungeonBlocks;
    }

    DungeonBlocks dungeonBlocks;
    Animations moveAnimation = new Animations();
    Vector2 target = new Vector2();

    enum State { //状態
        IDLE,
        MOVE
    }

    protected State state = State.IDLE;

    //マス
    private float moveX = 32;
    private float moveY = 32;

    @Override
    public void initPosition(float x, float y) {
        super.initPosition(x, y);
        target.x = current.x;
        target.y = current.y;
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

        int blockX = (int) (current.x / 32);
        int blockY = (int) (current.y / 32);

        DungeonObject nextTile = dungeonBlocks.getObjectType(blockX + (int) dx, blockY + (int) dy);
        if (nextTile == null) {
            return;
        }
        if (nextTile.isBlock) {
            return;
        }
        target.x = (float) Math.floor(nextTile.current.x);
        target.y = (float) Math.floor(nextTile.current.y);
        Vector2 tmp = new Vector2(blockX, blockY);
        Gdx.app.log("POSITION", tmp.toString() + ":" + target.toString());
    }

    public void move() {
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
                state = State.IDLE;
            }
        }
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

    public abstract void moveLeft();

    public abstract void moveRight();

    public abstract void moveUp();

    public abstract void moveDown();

    public abstract void moveLeftUp();

    public abstract void moveRightUp();

    public abstract void moveLeftDown();

    public abstract void moveRightDown();

    public abstract void moveReset();
}
