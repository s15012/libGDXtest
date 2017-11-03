package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class World {

    Texture blockImage = new Texture(Gdx.files.internal("android/assets/blocks.png"));
    TextureRegion tile;

    Vector2 position = new Vector2();

    public World() {
        TextureRegion[][] tmp = TextureRegion.split(blockImage, blockImage.getWidth() / 10, blockImage.getHeight() / 8);
        tile = tmp[4][0];
    }

}
