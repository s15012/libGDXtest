package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Direction;


public class Character extends MovableDungeonObject {

    private static final int IMAGE_COLS = 6;
    private static final int IMAGE_ROWS = 4;

    @Override
    public int getIdentifier() {
        return 0;
    }

    //    private static final float MOVE_SPEED = 64 * 3.0f / 60;
    private static final float MOVE_SPEED = 1;
    private static final float DIAGONAL = MOVE_SPEED * 0.7071f;

    public Character() {
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
        this.moveAnimation.right = new Animation(0.1f, split[12], split[13], split[14]);
        this.moveAnimation.left = new Animation(0.1f, split[6], split[7], split[8]);
        this.moveAnimation.up = new Animation(0.1f, split[18], split[19], split[20]);
        this.moveAnimation.down = new Animation(0.1f, split[0], split[1], split[2]);
        this.moveAnimation.rightUp = new Animation(0.1f, split[21], split[22], split[23]);
        this.moveAnimation.rightDown = new Animation(0.1f, split[9], split[10], split[11]);
        this.moveAnimation.leftDown = new Animation(0.1f, split[3], split[4], split[5]);
        this.moveAnimation.leftUp = new Animation(0.1f, split[15], split[16], split[17]);

        //State.IDLE
        this.idleAnimation.right = new Animation(0.5f, split[13]);
        this.idleAnimation.left = new Animation(0.5f, split[7]);
        this.idleAnimation.up = new Animation(0.5f, split[19]);
        this.idleAnimation.down = new Animation(0.5f, split[1]);
        this.idleAnimation.rightUp = new Animation(0.5f, split[22]);
        this.idleAnimation.rightDown = new Animation(0.5f, split[10]);
        this.idleAnimation.leftDown = new Animation(0.5f, split[4]);
        this.idleAnimation.leftUp = new Animation(0.5f, split[16]);

        sprite = new Sprite(image);

        initPosition(220 / 2, 220 / 2);
    }

    @Override
    public void dispose() {

    }

    public Vector2 getCurrentPosition() {
        return current;
    }

    public void moveLeft() {
        positionMove(Direction.LEFT, -MOVE_SPEED, 0);
    }

    public void moveRight() {
        positionMove(Direction.RIGHT, MOVE_SPEED, 0);
    }

    public void moveUp() {
        positionMove(Direction.UP, 0, MOVE_SPEED);
    }

    public void moveDown() {
        positionMove(Direction.DOWN, 0, -MOVE_SPEED);
    }

    public void moveLeftUp() {
        positionMove(Direction.LEFT_UP, -DIAGONAL, DIAGONAL);
    }

    public void moveRightUp() {
        positionMove(Direction.RIGHT_UP, DIAGONAL, DIAGONAL);
    }

    public void moveLeftDown() {
        positionMove(Direction.LEFT_DOWN, -DIAGONAL, -DIAGONAL);
    }

    public void moveRightDown() {
        positionMove(Direction.RIGHT_DOWN, DIAGONAL, -DIAGONAL);
    }

    public void moveReset() {
        current.set(0, 0);
    }

    public void draw(Batch batch, float imageStateTime) {
        float width = 32;
        float height = 48;
        batch.draw((TextureRegion) currentAnim().getKeyFrame(imageStateTime, true),
                current.x, current.y,
                width, height);
    }

}
