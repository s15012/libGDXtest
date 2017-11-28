package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.DrawComponent;
import com.mygdx.game.objects.findingpath.TiledNode;

public abstract class DungeonObject extends TiledNode implements DrawComponent {

    private DungeonObject(int x, int y, int index) {
        super(x, y, index);
    }

    public DungeonObject(int x, int y, int tiledX, int tiledY, int index) {
        super(tiledX, tiledY, index);
    }

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


    public Vector2 getCurrentPosition() {
        return current;
    }

}
