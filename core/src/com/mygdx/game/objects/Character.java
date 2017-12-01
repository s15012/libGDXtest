package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Direction;


public abstract class Character extends MovableDungeonObject {


    @Override
    public int getIdentifier() {
        return 0;
    }

    public Character() {
        super(0, 0, 0, 0, 0);
        image = initCaracterTexture();
        TextureRegion[] splitedTextures = getSplitTexture(image);
        this.moveAnimation = initMoveAnimations(splitedTextures);
        this.idleAnimation = initIdleAnimations(splitedTextures);
        sprite = initCharacterSprite(image);
    }

    public abstract Texture initCaracterTexture();

    public abstract Sprite initCharacterSprite(Texture texture);

    public abstract TextureRegion[] getSplitTexture(Texture texture);

    public abstract Animations initMoveAnimations(TextureRegion[] splitedTextures);

    public abstract Animations initIdleAnimations(TextureRegion[] splitedTextures);

    @Override
    public void dispose() {

    }

    public abstract float getMoveSpeedX();

    public abstract float getMoveSpeedY();

    public void moveLeft() {
        positionMove(Direction.LEFT, -getMoveSpeedX(), 0);
    }

    public void moveRight() {
        positionMove(Direction.RIGHT, getMoveSpeedX(), 0);
    }

    public void moveUp() {
        positionMove(Direction.UP, 0, getMoveSpeedY());
    }

    public void moveDown() {
        positionMove(Direction.DOWN, 0, -getMoveSpeedY());
    }

    public void moveLeftUp() {
        positionMove(Direction.LEFT_UP, -getMoveSpeedX(), getMoveSpeedY());
    }

    public void moveRightUp() {
        positionMove(Direction.RIGHT_UP, getMoveSpeedX(), getMoveSpeedY());
    }

    public void moveLeftDown() {
        positionMove(Direction.LEFT_DOWN, -getMoveSpeedX(), -getMoveSpeedY());
    }

    public void moveRightDown() {
        positionMove(Direction.RIGHT_DOWN, getMoveSpeedX(), -getMoveSpeedY());
    }


    public void checkLeft() {
        dungeonBlockManager.checkedNextTiled(Direction.LEFT, -getMoveSpeedX(), 0);
    }

    public void checkRight() {
        dungeonBlockManager.checkedNextTiled(Direction.RIGHT, getMoveSpeedX(), 0);
    }

    public void checkUp() {
        dungeonBlockManager.checkedNextTiled(Direction.UP, 0, getMoveSpeedY());
    }

    public void checkDown() {
        dungeonBlockManager.checkedNextTiled(Direction.DOWN, 0, -getMoveSpeedY());
    }

    public void checkLeftUp() {
        dungeonBlockManager.checkedNextTiled(Direction.LEFT_UP, -getMoveSpeedX(), getMoveSpeedY());
    }

    public void checkRightUp() {
        dungeonBlockManager.checkedNextTiled(Direction.RIGHT_UP, getMoveSpeedX(), getMoveSpeedY());
    }

    public void checkLeftDown() {
        dungeonBlockManager.checkedNextTiled(Direction.LEFT_DOWN, -getMoveSpeedX(), -getMoveSpeedY());
    }

    public void checkRightDown() {
        dungeonBlockManager.checkedNextTiled(Direction.RIGHT_DOWN, getMoveSpeedX(), -getMoveSpeedY());
    }

    public void attack() {
        Gdx.app.log("ATTACK", "攻撃できてるよ！！");
    }
    //TODO 攻撃時のアニメーション(Directionも必要), 攻撃時のダメージ判定(攻撃方向にEnemyがいる場合はEnemyにダメージ)

}
