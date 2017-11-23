package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.mygdx.game.Direction;

public class Animations {
    protected Animation left;
    protected Animation right;
    protected Animation up;
    protected Animation down;
    protected Animation leftUp;
    protected Animation leftDown;
    protected Animation rightUp;
    protected Animation rightDown;

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