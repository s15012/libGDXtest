package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by s15012 on 17/10/12.
 */
public class Tester implements ApplicationListener {

//    public static final String LOG_TAG = .class.getSimpleName();

    //Display
    private int LOGICAL_WIDTH = 512;
    private int LOGICAL_HEIGHT = 288;

    //drawerBatch
    SpriteBatch batch;

    //Chara
    Texture charaImage;
    Sprite chara;
    Vector2 charaPos;
    Vector2 targetPos;

    //touchPanel
    Texture touchPanelImage;

    //カメラ
    OrthographicCamera camera;
    FitViewport viewPort;

    //MoveAnimationTexture
    private Animation animLeft;
    private Animation animRight;
    private Animation animUp;
    private Animation animDown;
    private Animation animLeftUp;
    private Animation animLeftDown;
    private Animation animRightUp;
    private Animation animRightDown;
    //IdleAnimation?Texture
    private Animation animIdleLeft;
    private Animation animIdleRight;
    private Animation animIdleDown;
    private Animation animIdleUp;
    private Animation animIdleLeftUp;
    private Animation animIdleRightUp;
    private Animation animIdleLeftDown;
    private Animation animIdleRightDown;

    private static final int IMAGE_COLS = 6;
    private static final int IMAGE_ROWS = 4;
    private float imageStateTime = 0;

    enum Dirs { //向き
        DIR_RIGHT,
        DIR_LEFT,
        DIR_UP,
        DIR_DOWN,
        DIR_LUP,
        DIR_RUP,
        DIR_LDOWN,
        DIR_RDOWN
    }

    enum State { //状態
        IDLE,
        MOVE
    }

    private State state = State.IDLE;
    private Dirs dir = Dirs.DIR_DOWN;

    //マス
    private float moveX = 32;
    private float moveY = 32;
    //    private static final float MOVE_SPEED = 64 * 3.0f / 60;
    private static final float MOVE_SPEED = 1;
    private static final float DIAGONAL = MOVE_SPEED * 0.7071f;

    //WorldBlocks
    private World world;
//    public TiledMapRenderer renderer;

//    private Sprite leftButton;
//    private Sprite leftUpButton;
//    private Sprite leftDownButton;
//    private Sprite rightButton;
//    private Sprite rightUpButton;
//    private Sprite rightDownButton;
//    private Sprite upButton;
//    private Sprite downButton;

    @Override
    public void create() {
        batch = new SpriteBatch();

        touchPanelImage = new Texture(Gdx.files.internal("android/assets/touchPanel.png"));
        TextureRegion[][] touchPanel = TextureRegion.split(touchPanelImage, touchPanelImage.getWidth() / 3, touchPanelImage.getHeight() / 3);

        charaImage = new Texture(Gdx.files.internal("android/assets/reimu.png"));
        TextureRegion[][] tmp = TextureRegion.split(charaImage, charaImage.getWidth() / 6, charaImage.getHeight() / 4);
        TextureRegion[] split = new TextureRegion[IMAGE_COLS * IMAGE_ROWS];
        int index = 0;

        for (int i = 0; i < IMAGE_ROWS; i++) {
            for (int j = 0; j < IMAGE_COLS; j++) {
                split[index++] = tmp[i][j];
            }
        }
        //State.MOVE
        animRight = new Animation(0.1f, split[12], split[13], split[14]);
        animLeft = new Animation(0.1f, split[6], split[7], split[8]);
        animUp = new Animation(0.1f, split[18], split[19], split[20]);
        animDown = new Animation(0.1f, split[0], split[1], split[2]);
        animRightUp = new Animation(0.1f, split[21], split[22], split[23]);
        animRightDown = new Animation(0.1f, split[9], split[10], split[11]);
        animLeftDown = new Animation(0.1f, split[3], split[4], split[5]);
        animLeftUp = new Animation(0.1f, split[15], split[16], split[17]);

        //State.IDLE
        animIdleRight = new Animation(0.5f, split[13]);
        animIdleLeft = new Animation(0.5f, split[7]);
        animIdleUp = new Animation(0.5f, split[19]);
        animIdleDown = new Animation(0.5f, split[1]);
        animIdleRightUp = new Animation(0.5f, split[22]);
        animIdleRightDown = new Animation(0.5f, split[10]);
        animIdleLeftDown = new Animation(0.5f, split[4]);
        animIdleLeftUp = new Animation(0.5f, split[16]);

//        leftButton = new Sprite(touchPanelImage, 0, 32, 32, 32);
//        leftButton.setPosition(0, 0);
//        leftUpButton = new Sprite(touchSplit[0]);
//        leftUpButton.setPosition(0, 64);
//        leftDownButton = new Sprite(touchSplit[6]);
//        leftDownButton.setPosition(0, 0);
//        rightButton = new Sprite(touchSplit[5]);
//        rightUpButton = new Sprite(touchSplit[2]);
//        rightDownButton = new Sprite(touchSplit[8]);
//        upButton = new Sprite(touchSplit[1]);
//        downButton = new Sprite(touchSplit[7]);

        chara = new Sprite(charaImage);
        charaPos = new Vector2();
        targetPos = new Vector2();

        camera = new OrthographicCamera(LOGICAL_WIDTH, LOGICAL_HEIGHT);
        camera.setToOrtho(false, LOGICAL_WIDTH, LOGICAL_HEIGHT);
        viewPort = new FitViewport(LOGICAL_WIDTH, LOGICAL_HEIGHT, camera);

        world = new World();
        world.createDemo();
    }

    @Override
    public void resize(int width, int height) {
        viewPort.update(width, height);
    }

    @Override
    public void render() {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update();

        touchMove();
        move();

        Animation charaAnim = currentAnim();

        //camera キャラ追尾
        camera.update();
        camera.position.x = charaPos.x;
        camera.position.y = charaPos.y;

        //WorldBlock Renderer
        world.renderer.setView(camera);
        world.renderer.render();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        boolean loop = true;
        float width = 32;
        float height = 48;
        batch.draw((TextureRegion) charaAnim.getKeyFrame(imageStateTime, loop),
                charaPos.x, charaPos.y,
                width, height);

        batch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        charaImage.dispose();
        batch.dispose();
    }



    public void move() {
        //斜め方向
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            leftUp();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            rightUp();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            leftDown();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            rightDown();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            left();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            right();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            up();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            down();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
//            attack();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            reset();
        }

        if (charaPos.x < targetPos.x) {
            charaPos.x++;
        } else if (charaPos.x > targetPos.x) {
            charaPos.x--;
        }

        if (charaPos.y < targetPos.y) {
            charaPos.y++;
        } else if (charaPos.y > targetPos.y){
            charaPos.y--;
        }

        if (charaPos.x == targetPos.x && charaPos.y == targetPos.y) {
            state = State.IDLE;
        }
    }
    private void left() {
        positionMove(Dirs.DIR_LEFT, -MOVE_SPEED, 0);
    }
    private void right() {
        positionMove(Dirs.DIR_RIGHT, MOVE_SPEED, 0);
    }
    private void up() {
        positionMove(Dirs.DIR_UP, 0, MOVE_SPEED);
    }
    private void down() {
        positionMove(Dirs.DIR_DOWN, 0, -MOVE_SPEED);
    }
    private void leftUp() {
        positionMove(Dirs.DIR_LUP, -DIAGONAL, DIAGONAL);
    }
    private void rightUp() {
        positionMove(Dirs.DIR_RUP, DIAGONAL, DIAGONAL);
    }
    private void leftDown() {
        positionMove(Dirs.DIR_LDOWN, -DIAGONAL, -DIAGONAL);
    }
    private void rightDown() {
        positionMove(Dirs.DIR_RDOWN, DIAGONAL, -DIAGONAL);
    }
    private void reset() {
        charaPos.set(0, 0);
    }

//    private void attack() {
//        //TODO 攻撃
//    }

    private void update() {

        float deltaTime = Gdx.graphics.getDeltaTime();
        imageStateTime += deltaTime;
    }

    private Animation currentAnim() {
        Animation anim = null;

        if (state == State.MOVE) {
            switch (dir) {
                case DIR_LUP:
                    anim = animLeftUp;
                    break;
                case DIR_RUP:
                    anim = animRightUp;
                    break;
                case DIR_LDOWN:
                    anim = animLeftDown;
                    break;
                case DIR_RDOWN:
                    anim = animRightDown;
                    break;

                case DIR_LEFT:
                    anim = animLeft;
                    break;
                case DIR_RIGHT:
                    anim = animRight;
                    break;
                case DIR_UP:
                    anim = animUp;
                    break;
                case DIR_DOWN:
                    anim = animDown;
                    break;
                default:
                    break;
            }
        } else if (state == State.IDLE) {
            switch (dir) {
                case DIR_LUP:
                    anim = animIdleLeftUp;
                    break;
                case DIR_RUP:
                    anim = animIdleRightUp;
                    break;
                case DIR_LDOWN:
                    anim = animIdleLeftDown;
                    break;
                case DIR_RDOWN:
                    anim = animIdleRightDown;
                    break;

                case DIR_LEFT:
                    anim = animIdleLeft;
                    break;
                case DIR_RIGHT:
                    anim = animIdleRight;
                    break;
                case DIR_UP:
                    anim = animIdleUp;
                    break;
                case DIR_DOWN:
                    anim = animIdleDown;
                    break;
                default:
                    anim = animIdleDown;
                    break;
            }
        }

        return anim;
    }

    private void positionMove(Dirs direction, float dx, float dy) {
        state = State.MOVE;
        dir = direction;

        targetPos.x = charaPos.x + moveX * dx;
        targetPos.y = charaPos.y + moveY * dy;
    }

//    private void createDemo() {
//        int[][] maps = new int[8][8];
//
//        for(int x = 0; x < 8; x++) {
//            for (int y = 0; y < 8; y++) {
//                int a = (int) (Math.random() * 10);
//                if (a > 4) {
//                    maps[x][y] = 0;
//                } else {
//                    maps[x][y] = 1;
//                }
//                System.out.println(maps[x][y]);
//            }
//        }
//
//        TiledMap map = new TiledMap();
//        MapLayers layers = map.getLayers();
//        TiledMapTileLayer tiledMapTileLayer = new TiledMapTileLayer (32, 32,32,32);
//
//        for (int x = 0; x < 8; x++) {
//            for (int y = 0; y < 8; y++) {
//                if (maps[x][y] == 1) {
//                    System.out.println("設置OK");
//                    TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
//                    cell.setTile(new com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile(new TextureRegion(world.tile)));
//                    tiledMapTileLayer.setCell(x, y, cell);
//                }
////                Gdx.app.debug(LOG_TAG, "地面MAPを設置しました。　Xpos:" + x + " Ypos:" + y);
//            }
//        }
//
//        layers.add(tiledMapTileLayer);
//
//        renderer = new OrthogonalTiledMapRenderer(map);
//    }

    private void touchMove() {
    }

}

/**
 * (OK)マスアニメーション（現状は毎フレーム移動）
 * (画像表示はOK)MAP生成
 * キャラの攻撃モーション
 * 不思議ダンジョン生成（アルゴリズム）
 **/