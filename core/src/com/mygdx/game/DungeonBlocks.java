package com.mygdx.game;


public class DungeonBlocks {

    private int widthBlocks = 1000;
    private int heightBlocks = 1000;

    int[][] maps;

    public DungeonBlocks() {
        maps = new int[widthBlocks][heightBlocks];
    }

    public void makeMaps() {
        for (int x = 0; x < widthBlocks; x++) {
            for (int y = 0; y < heightBlocks; y++) {
                int a = (int) (Math.random() * 10);
                maps[x][y] = 1;
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


    public int getObjectType(int width, int height) {
        return maps[width][height];
    }
}
