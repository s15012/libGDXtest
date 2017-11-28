package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.mygdx.game.Direction;


public abstract class AnimatableDungeonObject extends DungeonObject {

    Animations idleAnimation;
    protected Direction direction = Direction.DOWN;

    public AnimatableDungeonObject(int x, int y, int tiledX, int tiledY, int index) {
        super(x, y, tiledX, tiledY, index);
    }

    protected Animation currentAnim() {
        return idleAnimation.convertDirectionToAnimation(direction);
    }

}
