package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.mygdx.game.Direction;


public abstract class AnimatableDungeonObject extends DungeonObject {

    Animations idleAnimation = new Animations();
    protected Direction direction = Direction.DOWN;

    protected Animation currentAnim() {
        return idleAnimation.convertDirectionToAnimation(direction);
    }

}
