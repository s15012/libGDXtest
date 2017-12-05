package com.mygdx.game.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ActionController extends HUDComponents {

    private ActionInputListener actionListener;

    private final String attackPanel = "attack01.png";
    private final String itemPanel = "attack01.png";

    Texture attackImage;
    Texture itemImage;
    private Image attackButton;
    private Image itemButton;

    private int PANEL_SIZE = 128;

    public ActionController(ActionInputListener actionListener) {
        this.actionListener = actionListener;
        attackImage = new Texture(Gdx.files.internal(attackPanel));
        itemImage = new Texture(Gdx.files.internal(itemPanel));
    }

    public void makePanel() {
        attackButton = new Image(attackImage);
        attackButton.setBounds(Gdx.graphics.getWidth() - PANEL_SIZE * 3, PANEL_SIZE * 3, PANEL_SIZE, PANEL_SIZE);
        attackButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ActionController.this.touchDown(Status.ATTACK);
                return true;
            }
        });

        addActor(attackButton);

        itemButton = new Image(itemImage);
        itemButton.setBounds(attackButton.getX() + PANEL_SIZE, attackButton.getY(), PANEL_SIZE, PANEL_SIZE);
        itemButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ActionController.this.touchDown(Status.ITEM);
                return true;
            }
        });
        addActor(itemButton);
    }

    public void touchDown(Status status) {
        if (status != null) {
            actionListener.onTouchDown(status);
        }
    }

    public interface ActionInputListener {
        void onTouchDown(Status status);
    }
}
