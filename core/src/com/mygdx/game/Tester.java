package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by s15012 on 17/10/12.
 */
public class Tester implements ApplicationListener {

    SpriteBatch batch;

    //キャラ構成
    Texture charaImage;
    Sprite chara;
    Vector2 charaPos;

    //カメラ
    OrthographicCamera camera;
    FitViewport viewPort;


    //アニメーション画像
    private Animation animLeft;
    private Animation animRight;
    private Animation animUp;
    private Animation animDown;
    private Animation animLeftUp;
    private Animation animLeftDown;
    private Animation animRightUp;
    private Animation animRightDown;

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

    // 移動状態
    private static final int STATE_IDLE = 0;
    private static final int STATE_MOVE = 1;

    // 向き状態
    private final int DIR_RIGHT = 1;
    private final int DIR_LEFT = 2;
    private final int DIR_UP = 4;
    private final int DIR_DOWN = 8;
    private final int DIR_RUP = 5;
    private final int DIR_LUP = 6;
    private final int DIR_RDOWN = 9;
    private final int DIR_LDOWN = 10;

    private int state;
    private int dir;
    private float moveX;
    private float moveY;

//    private static final float MOVE_SPEED = 64 * 3.0f / 60;
    private static final float MOVE_SPEED = 5;
    private static final float DIAGONAL = MOVE_SPEED * 0.7071f;

    private boolean KEY_FLAG = false;
    private boolean ATTACK_FLAG = false;
    private boolean MOVE_FLAG = false;


    @Override
    public void create() {
        batch = new SpriteBatch();

        charaImage = new Texture(Gdx.files.internal("reimu.png"));

        TextureRegion[][] tmp = TextureRegion.split(charaImage, charaImage.getWidth() / 6, charaImage.getHeight() / 4);
        TextureRegion[] split = new TextureRegion[IMAGE_COLS * IMAGE_ROWS];
        int index = 0;

        for (int i = 0; i < IMAGE_ROWS; i++) {
            for (int j = 0; j < IMAGE_COLS; j++) {
                split[index++] = tmp[i][j];
            }
        }

        animRight = new Animation(0.1f, split[12], split[13], split[14]);
        animLeft = new Animation(0.1f, split[6], split[7], split[8]);
        animUp = new Animation(0.1f, split[18], split[19], split[20]);
        animDown = new Animation(0.1f, split[0], split[1], split[2]);

        animRightUp = new Animation(0.1f, split[21], split[22], split[23]);
        animRightDown = new Animation(0.1f, split[9], split[10], split[11]);
        animLeftDown = new Animation(0.1f, split[3], split[4], split[5]);
        animLeftUp = new Animation(0.1f, split[15], split[16], split[17]);


        animIdleRight = new Animation(0.5f, split[13]);
        animIdleLeft = new Animation(0.5f, split[7]);
        animIdleUp = new Animation(0.5f, split[19]);
        animIdleDown = new Animation(0.5f, split[1]);

        animIdleRightUp = new Animation(0.5f, split[22]);
        animIdleRightDown = new Animation(0.5f, split[10]);
        animIdleLeftDown = new Animation(0.5f, split[4]);
        animIdleLeftUp = new Animation(0.5f, split[16]);

        chara = new Sprite(charaImage);
        charaPos = new Vector2();

        camera = new OrthographicCamera(600, 480);
        camera.setToOrtho(false, 600, 480);
        viewPort = new FitViewport(600, 480, camera);


    }

    @Override
    public void resize(int width, int height) {
        viewPort.update(width, height);

    }

    @Override
    public void render() {

        update();
        move();
        Animation charaAnim = currentAnim();

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        boolean loop = true;
        float width = 32;
        float height = 48;

        batch.draw((TextureRegion) charaAnim.getKeyFrame(imageStateTime, loop),
                charaPos.x, charaPos.y,
                width, height);

//        chara.draw(batch);
//        batch.draw(charaImage, 0, 0);
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
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.UP)) {
            leftUp();
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && Gdx.input.isKeyPressed(Input.Keys.UP)) {
            rightUp();
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            leftDown();
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            rightDown();
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            left();
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            right();
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            up();
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            down();
        } else if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
            attack();
        } else if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            reset();
        }
    }

    private void left() {
        positionMove(DIR_LEFT, -MOVE_SPEED, 0);
    }
    private void right() {
        positionMove(DIR_RIGHT, MOVE_SPEED, 0);
    }
    private void up() {
        positionMove(DIR_UP, 0, MOVE_SPEED);
    }
    private void down() {
        positionMove(DIR_DOWN, 0, -MOVE_SPEED);
    }
    private void leftUp() {
        positionMove(DIR_LUP, -DIAGONAL, DIAGONAL);
    }
    private void rightUp() {
        positionMove(DIR_RUP, DIAGONAL, DIAGONAL);
    }
    private void leftDown() {
        positionMove(DIR_LDOWN, -DIAGONAL, -DIAGONAL);
    }
    private void rightDown() {
        positionMove(DIR_RDOWN, DIAGONAL, -DIAGONAL);
    }
    private void reset() {
        charaPos.set(0, 0);
    }

    private void attack() {
        //TODO 攻撃
    }

    private void update() {
        state = STATE_IDLE;

        float deltaTime = Gdx.graphics.getDeltaTime();
        imageStateTime += deltaTime;
    }
    //移動アニメーション

    private Animation currentAnim() {
        Animation anim = null;

        if (state == STATE_MOVE) {
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
        } else if (state == STATE_IDLE) {
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

    private void positionMove(int direction, float dx, float dy) {
//        MOVE_FLAG = true;
        state = STATE_MOVE;
        dir = direction;

        charaPos.x += dx;
        charaPos.y += dy;

    }
}



/**
 * マスアニメーション（現状は毎フレーム移動）
 * キャラの攻撃モーション
 * 不思議ダンジョン生成（アルゴリズム）
 **/