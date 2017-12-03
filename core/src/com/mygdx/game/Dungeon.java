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
import com.mygdx.game.hud.ActionController;
import com.mygdx.game.hud.HUD;
import com.mygdx.game.hud.InputController;
import com.mygdx.game.hud.Status;
import com.mygdx.game.objects.DungeonObject;
import com.mygdx.game.objects.characters.Reimu;
import com.mygdx.game.objects.enemies.TestEnemy;

public class Dungeon extends ScreenAdapter implements InputController.InputListener, ActionController.ActionInputListener {

    HUD hud = new HUD();

    SpriteBatch batch;
    OrthographicCamera camera;
    FitViewport viewPort;
    TiledMapRenderer renderer;

    DungeonBlockManager dungeonBlockManager = new DungeonBlockManager();

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
        dungeonBlockManager.makeMaps();
        dungeonBlockManager.setMainCharacter(new Reimu());
        dungeonBlockManager.addEnemy(new TestEnemy());
        createDungeonMap();
        InputController inputController = new InputController(this);
        ActionController actionController = new ActionController(this);
        inputController.makePanel();
        actionController.makePanel();
        hud.addComponent(inputController);
        hud.addComponent(actionController);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        updateTimeState();
        updateCamera();
        dungeonBlockManager.moveAllObject();
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
        dungeonBlockManager.dispose();
    }

    private void draw() {
        renderer.setView(camera);
        renderer.render();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        dungeonBlockManager.draw(batch, imageStateTime);
        batch.end();
        hud.draw(batch, imageStateTime);

    }

    private void updateCamera() {
        camera.update();
        Vector2 pos = dungeonBlockManager.getMainCharacter().getCurrentPosition();
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
        int widthCount = dungeonBlockManager.getWidthBlockCount();
        int heightCount = dungeonBlockManager.getHeightBlockCount();
        TiledMapTileLayer tiledMapTileLayer = new TiledMapTileLayer(widthCount, heightCount, 32, 32);
        for (int x = 0; x < widthCount; x++) {
            for (int y = 0; y < heightCount; y++) {
                DungeonObject object = dungeonBlockManager.getObjectType(x, y);
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
        dungeonBlockManager.getMainCharacter().setMoveDirection(direction);
    }

    @Override
    public void onTouchUp(Direction direction) {
        dungeonBlockManager.getMainCharacter().moveReset();
    }

    @Override
    public void onTouchMove(Direction direction) {
        dungeonBlockManager.getMainCharacter().setMoveDirection(direction);
    }

    @Override
    public void onTouchDown(Status status) {
        dungeonBlockManager.setAction(status);
    }
}
