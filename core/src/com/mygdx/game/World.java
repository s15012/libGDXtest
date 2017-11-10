package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

public class World {

    Texture blockImage = new Texture(Gdx.files.internal("android/assets/blocks.png"));
    TextureRegion tile;
    TiledMapRenderer renderer;

    public World() {
        TextureRegion[][] tmp = TextureRegion.split(blockImage, blockImage.getWidth() / 10, blockImage.getHeight() / 8);
        tile = tmp[4][0];
    }

    public void createDemo() {
        int[][] maps = new int[8][8];

        for(int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                int a = (int) (Math.random() * 10);
                if (a > 4) {
                    maps[x][y] = 0;
                } else {
                    maps[x][y] = 1;
                }
                System.out.println(maps[x][y]);
            }
        }

        TiledMap map = new TiledMap();
        MapLayers layers = map.getLayers();
        TiledMapTileLayer tiledMapTileLayer = new TiledMapTileLayer (32, 32,32,32);

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (maps[x][y] == 1) {
                    System.out.println("設置OK");
                    TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                    cell.setTile(new com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile(new TextureRegion(tile)));
                    tiledMapTileLayer.setCell(x, y, cell);
                }
//                Gdx.app.debug(LOG_TAG, "地面MAPを設置しました。　Xpos:" + x + " Ypos:" + y);
            }
        }

        layers.add(tiledMapTileLayer);

        renderer = new OrthogonalTiledMapRenderer(map);
    }




}
