package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.Resources;

public class GlassObstacle extends DungeonObject {

    public GlassObstacle(float x, float y) {
        image = Resources.Textures.obstacle;
        initPosition(x, y);
        isBlock = true;
    }

    @Override
    public void draw(Batch batch, float stateTime) {

    }

    @Override
    public void dispose() {

    }

    @Override
    public int getIdentifier() {
        return 1;
    }
}
