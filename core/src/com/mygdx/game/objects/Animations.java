package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.mygdx.game.Direction;

public class Animations {
    protected final Animation left;
    protected final Animation right;
    protected final Animation up;
    protected final Animation down;
    protected final Animation leftUp;
    protected final Animation leftDown;
    protected final Animation rightUp;
    protected final Animation rightDown;

    public Animations(Animation leftUp, Animation up, Animation rightUp,
                      Animation left, Animation right,
                      Animation leftDown, Animation down, Animation rightDown) {
        this.left = left;
        this.right = right;
        this.up = up;
        this.down = down;
        this.leftUp = leftUp;
        this.leftDown = leftDown;
        this.rightUp = rightUp;
        this.rightDown = rightDown;
    }

    public Animation convertDirectionToAnimation(Direction direction) {
        switch (direction) {
            case LEFT_UP:
                return leftUp;
            case UP:
                return up;
            case RIGHT_UP:
                return rightUp;
            case LEFT:
                return left;
            case RIGHT:
                return right;
            case LEFT_DOWN:
                return leftDown;
            case DOWN:
                return down;
            case RIGHT_DOWN:
                return rightDown;
        }
        return null;
    }
}