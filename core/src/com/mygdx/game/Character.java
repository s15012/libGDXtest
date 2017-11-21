package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;


public class Character {

    //Chara
    Texture image;
    Sprite sprite;
    Vector2 current;
    Vector2 target;

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

    Character() {
        image = new Texture(Gdx.files.internal("reimu.png"));
        TextureRegion[][] tmp = TextureRegion.split(image, image.getWidth() / 6, image.getHeight() / 4);
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

        sprite = new Sprite(image);
        current = new Vector2();
        target = new Vector2();
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        current.x = 220 / 2;
        current.y = 220 / 2;

        target.x = current.x;
        target.y = current.y;
    }

    public Vector2 getCurrentPosition() {
        return current;
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

        if (current.x < target.x) {
            current.x++;
        } else if (current.x > target.x) {
            current.x--;
        }

        if (current.y < target.y) {
            current.y++;
        } else if (current.y > target.y) {
            current.y--;
        }

        if (current.x == target.x && current.y == target.y) {
            state = State.IDLE;
        }
    }

    public void left() {
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
        current.set(0, 0);
    }

    public void draw(Batch batch, float imageStateTime) {
        float width = 32;
        float height = 48;
        batch.draw((TextureRegion) currentAnim().getKeyFrame(imageStateTime, true),
                current.x, current.y,
                width, height);
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

        target.x = current.x + moveX * dx;
        target.y = current.y + moveY * dy;
    }
}
