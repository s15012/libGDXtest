package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Direction;
import com.mygdx.game.objects.characters.CharaParams;
import com.mygdx.game.objects.enemies.EnemyParams;


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




    public void checkLeft(Vector2 currentBlock) {
        dungeonBlockManager.attackRange((int)currentBlock.x - 1, (int)currentBlock.x - 1, (int)currentBlock.y, (int)currentBlock.y);
    }

    public void checkRight(Vector2 currentBlock) {
        dungeonBlockManager.attackRange((int)currentBlock.x + 1, (int)currentBlock.x + 1, (int)currentBlock.y, (int)currentBlock.y);
    }

    public void checkUp(Vector2 currentBlock) {
        dungeonBlockManager.attackRange((int)currentBlock.x, (int)currentBlock.x, (int)currentBlock.y + 1, (int)(currentBlock.y + 1));
    }

    public void checkDown(Vector2 currentBlock) {
        dungeonBlockManager.attackRange((int)currentBlock.x, (int)currentBlock.x, (int)currentBlock.y - 1, (int)currentBlock.y -1);
    }

    public void checkLeftUp(Vector2 currentBlock) {
        dungeonBlockManager.attackRange((int)currentBlock.x - 1, (int)currentBlock.x - 1, (int)currentBlock.y + 1, (int)currentBlock.y + 1);
    }

    public void checkRightUp(Vector2 currentBlock) {
        dungeonBlockManager.attackRange((int)currentBlock.x + 1, (int)currentBlock.x + 1, (int)currentBlock.y + 1, (int)currentBlock.y + 1);
    }

    public void checkLeftDown(Vector2 currentBlock) {
        dungeonBlockManager.attackRange((int)currentBlock.x - 1, (int)currentBlock.x - 1, (int)currentBlock.y - 1, (int)currentBlock.y - 1);
    }

    public void checkRightDown(Vector2 currentBlock) {
        dungeonBlockManager.attackRange((int)currentBlock.x + 1, (int)currentBlock.x + 1, (int)currentBlock.y - 1, (int)currentBlock.y - 1);
    }

    public void setCharaParams(int maxHp, int lv, int str, int def, int exp) {
        CharaParams CharaParams = new CharaParams();
        CharaParams.setMaxHp(maxHp);
        CharaParams.setHp(CharaParams.getMaxHp());
        CharaParams.setLv(lv);
        CharaParams.setStr(str);
        CharaParams.setDef(def);
        CharaParams.setExp(exp);
    }

    public void setEnemyParams(int maxHp, int str, int def, int xp) {
        EnemyParams params = new EnemyParams();
        params.setMaxHp(maxHp);
        params.setHp(params.getMaxHp());
        params.setStr(str);
        params.setDef(def);
        params.setXp(xp);
    }

    public void attack(Direction direction) {
        dungeonBlockManager.setNextDirection(direction);
    }
//
//    public int damage() {
//
//    }
}
