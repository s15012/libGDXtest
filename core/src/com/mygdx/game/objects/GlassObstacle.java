package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.Resources;

public class GlassObstacle extends DungeonObject {

    public GlassObstacle(int x, int y, int tiledX, int tiledY, int index) {
        super(x, y, tiledX, tiledY, index);
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
