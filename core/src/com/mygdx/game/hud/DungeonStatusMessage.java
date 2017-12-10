package com.mygdx.game.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.DungeonBlockManager;

/**
 * Created by arakaki on 2017/12/10.
 */

public class DungeonStatusMessage extends HUDComponents {
//    FreeTypeFontGenerator fontGenerator;
    BitmapFont bitmapFont = new BitmapFont();

    Label label;

    DungeonBlockManager dungeonBlockManager;

    public DungeonStatusMessage(DungeonBlockManager manager) {
        setDungeonBlockManager(manager);
        setFontText();
    }

    public void setFontText() {

        Label.LabelStyle labelStyle = new Label.LabelStyle(bitmapFont, Color.WHITE);
        int maxHp = dungeonBlockManager.getMainCharacter().getMaxHp();
        int hp = dungeonBlockManager.getMainCharacter().getHp();

        label = new Label("HP:" + hp + "/" + maxHp, labelStyle);
        label.setFontScale(4f);
        label.setBounds(Gdx.graphics.getWidth() / 2 - label.getPrefWidth() / 2, Gdx.graphics.getHeight() - 40, 0, 0);
        addActor(label);

    }

    public void setDungeonBlockManager(DungeonBlockManager dungeonBlockManager) {
        this.dungeonBlockManager = dungeonBlockManager;
    }


}
