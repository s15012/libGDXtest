package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.Resources;

/**
 * Created by arakaki on 2017/11/23.
 */

public class GlassTile extends DungeonObject {

    public GlassTile(float x, float y) {
        image = Resources.Textures.floor;
        initPosition(x, y);
    }

    @Override
    public void draw(Batch batch, float stateTime) {

    }

    @Override
    public void dispose() {

    }

    @Override
    public int getIdentifier() {
        return 0;
    }
}
