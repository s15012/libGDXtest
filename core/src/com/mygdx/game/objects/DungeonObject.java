package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.DrawComponent;

public abstract class DungeonObject implements DrawComponent {

    boolean isBlock = false;

    public Texture getImage() {
        return image;
    }

    Texture image;
    Sprite sprite;
    Vector2 current = new Vector2();

    /*
    *
    * オブジェクトIDを継承先で指定する
    * */
    abstract public int getIdentifier();

    public void initPosition(float x, float y) {
        current.x = x;
        current.y = y;
    }

}
