package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Resources {

    public static class Textures {
        public static Texture floor = new Texture(Gdx.files.internal("blocks.png"));
        public static Texture obstacle = new Texture(Gdx.files.internal("obstacle.png"));
    }


}
