package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.mygdx.game.objects.DungeonObject;
import com.mygdx.game.objects.Enemy;
import com.mygdx.game.objects.GlassObstacle;
import com.mygdx.game.objects.GlassTile;

public class DungeonBlocks {

    private int widthBlocks = 100;
    private int heightBlocks = 30;
    private int BLOCK_SIZE = 32;

    DungeonObject[][] maps;

    Enemy enemy = new Enemy();

    public DungeonBlocks() {
        maps = new DungeonObject[widthBlocks][heightBlocks];
    }

    public void makeMaps() {
        for (int x = 0; x < widthBlocks; x++) {
            for (int y = 0; y < heightBlocks; y++) {
                int a = (int) (Math.random() * 10);
                if (x == 0 || y == 0 || x == widthBlocks-1 || y == heightBlocks-1) {
                    maps[x][y] = new GlassObstacle(x * BLOCK_SIZE, y * BLOCK_SIZE);

                } else {
                    maps[x][y] = new GlassTile(x * BLOCK_SIZE, y * BLOCK_SIZE);

                }
                Gdx.app.log("POSSS", x + ":" + y);
            }
        }
    }

    public int getWidthBlockCount() {
        return widthBlocks;
    }

    public int getHeightBlockCount() {
        return heightBlocks;
    }


    public DungeonObject getObjectType(int x, int y) {
        if (x < 0 || x >= getWidthBlockCount()) {
            return null;
        }

        if (y < 0 || y >= getHeightBlockCount()) {
            return null;
        }
        return maps[x][y];
    }
}
