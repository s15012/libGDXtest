package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;

public interface DrawComponent {

    public void draw(Batch batch, float stateTime);

    public void dispose();

}
