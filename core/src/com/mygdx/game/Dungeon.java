package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;


public class Dungeon extends ScreenAdapter {

    InputController inputController = new InputController();

    SpriteBatch batch;
    OrthographicCamera camera;
    FitViewport viewPort;
    TiledMapRenderer renderer;

    DungeonBlocks blocks = new DungeonBlocks();

    Character character = new Character();

    private float imageStateTime = 0;

    Dungeon(SpriteBatch batch) {
        this.batch = batch;
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w / h, 1);
        viewPort = new FitViewport(220 * w / h, 220, camera);
        create();
        inputController.makePanel();
    }


    private void create() {
        blocks.makeMaps();
        createDungeonMap();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        updateTimeState();
        updateCamera();
        character.move();
        draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewPort.update(width, height);
    }

    private void draw() {
        renderer.setView(camera);
        renderer.render();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        character.draw(batch, imageStateTime);
        inputController.draw(batch);

        batch.end();
    }

    private void updateCamera() {
        camera.update();
        Vector2 pos = character.getCurrentPosition();
        camera.position.x = pos.x;
        camera.position.y = pos.y;

        if (camera.position.x < viewPort.getWorldWidth() / 2) {
            camera.position.x = viewPort.getWorldWidth() / 2;
        }

        if (camera.position.y < viewPort.getWorldHeight() / 2) {
            camera.position.y = viewPort.getWorldHeight() / 2;
        }
    }

    private void updateTimeState() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        imageStateTime += deltaTime;
    }

    public void createDungeonMap() {
        TiledMap map = new TiledMap();
        MapLayers layers = map.getLayers();
        TiledMapTileLayer tiledMapTileLayer = new TiledMapTileLayer(32, 32, 32, 32);
        int widthCount = blocks.getWidthBlockCount();
        int heightCount = blocks.getHeightBlockCount();
        TextureRegion tile = Resources.Textures.getFloorTextureRegion();
        for (int x = 0; x < widthCount; x++) {
            for (int y = 0; y < heightCount; y++) {
                if (blocks.getObjectType(x, y) == 1) {
                    TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                    cell.setTile(new com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile(new TextureRegion(tile)));
                    tiledMapTileLayer.setCell(x, y, cell);
                }
            }
        }
        layers.add(tiledMapTileLayer);
        renderer = new OrthogonalTiledMapRenderer(map);
    }

}