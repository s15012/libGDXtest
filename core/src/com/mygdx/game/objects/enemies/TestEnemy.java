package com.mygdx.game.objects.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.objects.Animations;

public class TestEnemy extends Enemy {
    private static final int IMAGE_COLS = 6;
    private static final int IMAGE_ROWS = 4;

    private static final float MOVE_SPEED = 1;

    public TestEnemy() {
        super();
        initPosition(96, 96);
        setParameter(1, 10, 1, 1, 0, 1);
    }

    @Override
    public Texture initCaracterTexture() {
        return new Texture(Gdx.files.internal("reimu.png"));
    }

    @Override
    public Sprite initCharacterSprite(Texture texture) {
        return new Sprite(texture);
    }

    @Override
    public TextureRegion[] getSplitTexture(Texture image) {
        TextureRegion[][] tmp = TextureRegion.split(image, image.getWidth() / 6, image.getHeight() / 4);
        TextureRegion[] split = new TextureRegion[IMAGE_COLS * IMAGE_ROWS];
        int index = 0;

        for (int i = 0; i < IMAGE_ROWS; i++) {
            for (int j = 0; j < IMAGE_COLS; j++) {
                split[index++] = tmp[i][j];
            }
        }
        return split;
    }

    @Override
    public Animations initMoveAnimations(TextureRegion[] split) {
        Animation right = new Animation(0.1f, split[12], split[13], split[14]);
        Animation left = new Animation(0.1f, split[6], split[7], split[8]);
        Animation up = new Animation(0.1f, split[18], split[19], split[20]);
        Animation down = new Animation(0.1f, split[0], split[1], split[2]);
        Animation rightUp = new Animation(0.1f, split[21], split[22], split[23]);
        Animation rightDown = new Animation(0.1f, split[9], split[10], split[11]);
        Animation leftDown = new Animation(0.1f, split[3], split[4], split[5]);
        Animation leftUp = new Animation(0.1f, split[15], split[16], split[17]);

        return new Animations(leftUp, up, rightUp,
                left, right,
                leftDown, down, rightDown);
    }

    @Override
    public Animations initIdleAnimations(TextureRegion[] split) {
        Animation right = new Animation(0.5f, split[13]);
        Animation left = new Animation(0.5f, split[7]);
        Animation up = new Animation(0.5f, split[19]);
        Animation down = new Animation(0.5f, split[1]);
        Animation rightUp = new Animation(0.5f, split[22]);
        Animation rightDown = new Animation(0.5f, split[10]);
        Animation leftDown = new Animation(0.5f, split[4]);
        Animation leftUp = new Animation(0.5f, split[16]);
        return new Animations(leftUp, up, rightUp,
                left, right,
                leftDown, down, rightDown);
    }

    public void draw(Batch batch, float imageStateTime) {
        float width = 32; //TODO
        float height = 48;
        Vector2 current = getCurrentPosition();
        batch.draw((TextureRegion) currentAnim().getKeyFrame(imageStateTime, true),
                current.x, current.y,
                width, height);
    }

    @Override
    public float getMoveSpeedX() {
        return MOVE_SPEED;
    }

    @Override
    public float getMoveSpeedY() {
        return MOVE_SPEED;
    }

    @Override
    public void setParameter(int lv, int maxHp, int str, int def, int exp, int xp) {
        this.setLv(lv);
        this.setMaxHp(maxHp);
        this.setHp(maxHp);
        this.setStr(str);
        this.setDef(def);
        this.setXp(xp);
    }

    @Override
    public void attack() {

    }
}
