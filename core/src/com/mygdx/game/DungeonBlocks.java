package com.mygdx.game;


import com.mygdx.game.objects.DungeonObject;
import com.mygdx.game.objects.GlassObstacle;
import com.mygdx.game.objects.GlassTile;

public class DungeonBlocks {

    private int widthBlocks = 10;
    private int heightBlocks = 10;

    DungeonObject[][] maps;

    public DungeonBlocks() {
        maps = new DungeonObject[widthBlocks][heightBlocks];
    }

    public void makeMaps() {
        for (int x = 0; x < widthBlocks; x++) {
            for (int y = 0; y < heightBlocks; y++) {
                int a = (int) (Math.random() * 10);
                if (x == 0 || x == widthBlocks - 1 || y == 0 || y == heightBlocks - 1) {
                    maps[x][y] = new GlassObstacle(x * 32, y * 32);

                } else {
                    maps[x][y] = new GlassTile(x * 32, y * 32);

                }
                //System.out.println(maps[x][y]);
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
