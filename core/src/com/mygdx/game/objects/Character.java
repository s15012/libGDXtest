package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Direction;

public abstract class Character extends MovableDungeonObject {

    private int lv;
    private int maxHp;
    private int hp;
    private int str;
    private int def;
    private int exp;
    private int xp;

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

//
//    public abstract Texture initAttackTexture();
//    public abstract TextureRegion[] getAttackSplit(Texture texture);
//    public abstract Sprite initActionSprite(Texture texture);
//    public abstract Actions initActions(TextureRegion[] actionTextures);
//

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

    //キャラ動作
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

    //攻撃動作()
    public void attackLeft(Vector2 currentBlock) {
        dungeonBlockManager.attackRange((int)currentBlock.x - 1, (int)currentBlock.x - 1, (int)currentBlock.y, (int)currentBlock.y);
    }

    public void attackRight(Vector2 currentBlock) {
        dungeonBlockManager.attackRange((int)currentBlock.x + 1, (int)currentBlock.x + 1, (int)currentBlock.y, (int)currentBlock.y);
    }

    public void attackUp(Vector2 currentBlock) {
        dungeonBlockManager.attackRange((int)currentBlock.x, (int)currentBlock.x, (int)currentBlock.y + 1, (int)(currentBlock.y + 1));
    }

    public void attackDown(Vector2 currentBlock) {
        dungeonBlockManager.attackRange((int)currentBlock.x, (int)currentBlock.x, (int)currentBlock.y - 1, (int)currentBlock.y -1);
    }

    public void attackLeftUp(Vector2 currentBlock) {
        dungeonBlockManager.attackRange((int)currentBlock.x - 1, (int)currentBlock.x - 1, (int)currentBlock.y + 1, (int)currentBlock.y + 1);
    }

    public void attackRightUp(Vector2 currentBlock) {
        dungeonBlockManager.attackRange((int)currentBlock.x + 1, (int)currentBlock.x + 1, (int)currentBlock.y + 1, (int)currentBlock.y + 1);
    }

    public void attackLeftDown(Vector2 currentBlock) {
        dungeonBlockManager.attackRange((int)currentBlock.x - 1, (int)currentBlock.x - 1, (int)currentBlock.y - 1, (int)currentBlock.y - 1);
    }

    public void attackRightDown(Vector2 currentBlock) {
        dungeonBlockManager.attackRange((int)currentBlock.x + 1, (int)currentBlock.x + 1, (int)currentBlock.y - 1, (int)currentBlock.y - 1);
    }

    public void attack(Direction direction) {
        dungeonBlockManager.setNextDirection(direction);
    }

    /**
     *
     * @param lv レベル
     * @param maxHp 最大HP
     * @param str　力（攻撃力）
     * @param def　守（防御力）
     * @param exp　総取得経験値
     * @param xp　経験値
     */
    public abstract void setParameter(int lv, int maxHp, int str, int def, int exp, int xp);

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getStr() {
        return str;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

}
