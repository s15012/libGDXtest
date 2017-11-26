package com.mygdx.game.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by arakaki on 2017/11/26.
 */

public class ActionController extends HUDComponents {

    private ActionInputListener actionListener;

    Texture attackImage;
    private Image attackButton;

    private int PANEL_SIZE = 128;

    public ActionController(ActionInputListener actionListener) {
        this.actionListener = actionListener;
        attackImage = new Texture(Gdx.files.internal("attack01.png"));
    }

    public void makePanel() {
        attackButton = new Image(attackImage);
        attackButton.setBounds(Gdx.graphics.getWidth() - PANEL_SIZE * 3, PANEL_SIZE * 3, PANEL_SIZE, PANEL_SIZE);
        attackButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ActionController.this.touchDown(Status.ATTACK);
                return  true;
            }
        });

        addActor(attackButton);
    }

    public void touchDown(Status status) {
        if (status != null) {
            actionListener.onTouchDown(status);
        }
    }

    public interface ActionInputListener {
        public void onTouchDown(Status status);
    }
}
