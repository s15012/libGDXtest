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
import com.mygdx.game.hud.HUD;
import com.mygdx.game.hud.InputController;
import com.mygdx.game.objects.Character;
import com.mygdx.game.objects.DungeonObject;


public class Dungeon extends ScreenAdapter implements InputController.InputListener {

    HUD hud = new HUD();

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
        viewPort = new FitViewport(240 * w / h, 240, camera);
        create();
    }


    private void create() {
        blocks.makeMaps();
        character.setDungeonBlocks(blocks);
        createDungeonMap();
        InputController inputController = new InputController(this);
        inputController.makePanel();
        hud.addComponent(inputController);
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

    @Override
    public void dispose() {
        super.dispose();
        hud.dispose();
        character.dispose();
    }

    private void draw() {
        renderer.setView(camera);
        renderer.render();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        character.draw(batch, imageStateTime);
        batch.end();
        hud.draw(batch, imageStateTime);

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
//        Texture tile = Resources.Textures.floor;
        for (int x = 0; x < widthCount; x++) {
            for (int y = 0; y < heightCount; y++) {
                DungeonObject object = blocks.getObjectType(x, y);
                TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                cell.setTile(new com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile(new TextureRegion(object.getImage())));
                tiledMapTileLayer.setCell(x, y, cell);
            }
        }
        layers.add(tiledMapTileLayer);
        renderer = new OrthogonalTiledMapRenderer(map);
    }

    @Override
    public void onTouchDown(Direction direction) {
        character.setMoveDirection(direction);
    }

    @Override
    public void onTouchUp(Direction direction) {
    }

    @Override
    public void onTouchMove(Direction direction) {
        character.setMoveDirection(direction);
    }
}
