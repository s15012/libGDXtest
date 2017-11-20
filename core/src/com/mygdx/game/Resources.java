package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Resources {

    public static class Textures {
        public static Texture floor = new Texture(Gdx.files.internal("blocks.png"));
        private static TextureRegion floorTextureRegion;

        public static TextureRegion getFloorTextureRegion() {
            if (floorTextureRegion == null) {
                TextureRegion[][] tmp = TextureRegion.split(floor, floor.getWidth() / 10, floor.getHeight() / 8);
                floorTextureRegion = tmp[4][0];
            }
            return floorTextureRegion;
        }
    }


}
