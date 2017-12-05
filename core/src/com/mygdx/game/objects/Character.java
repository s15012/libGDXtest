package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Direction;
import com.mygdx.game.objects.characters.CharaParams;
import com.mygdx.game.objects.enemies.EnemyParams;


public abstract class Character extends MovableDungeonObject {

    CharaParams charaParams;
    EnemyParams enemyParams;

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

    //
//    public void setCharaParams(int lv, int maxHp, int str, int def, int exp) {
//        charaParams = new CharaParams();
//        charaParams.setLv(lv);
//        charaParams.setMaxHp(maxHp);
//        charaParams.setHp(maxHp);
//        charaParams.setStr(str);
//        charaParams.setDef(def);
//        charaParams.setExp(exp);
//    }
//
//    public Array<Integer> getCharaParams() {
//        Array<Integer> charaParamList = new Array<Integer>();
//        charaParamList.add(charaParams.getLv());
//        charaParamList.add(charaParams.getMaxHp());
//        charaParamList.add(charaParams.getHp());
//        charaParamList.add(charaParams.getStr());
//        charaParamList.add(charaParams.getDef());
//        charaParamList.add(charaParams.getExp());
//
//        return charaParamList;
//    }
//
//    public void setEnemyParams(int maxHp, int str, int def, int xp) {
//        enemyParams = new EnemyParams();
//        enemyParams.setMaxHp(maxHp);
//        enemyParams.setHp(enemyParams.getMaxHp());
//        enemyParams.setStr(str);
//        enemyParams.setDef(def);
//        enemyParams.setXp(xp);
//    }
//
//    public Array<Integer> getEnemyParams() {
//        Array<Integer> enemyParamList = new Array<Integer>();
//        enemyParamList.add(enemyParams.getMaxHp());
//        enemyParamList.add(enemyParams.getHp());
//        enemyParamList.add(enemyParams.getStr());
//        enemyParamList.add(enemyParams.getDef());
//        enemyParamList.add(enemyParams.getXp());
//
//        return enemyParamList;
//    }
    //







    public void attack(Direction direction) {
        dungeonBlockManager.setNextDirection(direction);
    }
//
//    public int damage() {
//
//    }
}
