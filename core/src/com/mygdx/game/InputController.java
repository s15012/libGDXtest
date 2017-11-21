package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.sun.prism.paint.Color;

public class InputController implements InputProcessor {

    private Texture touchPanelImage;
    private TextureRegion[][] touchPanel;
    private Sprite leftButton;
    private Sprite leftUpButton;
    private Sprite leftDownButton;
    private Sprite rightButton;
    private Sprite rightUpButton;
    private Sprite rightDownButton;
    private Sprite downButton;
    private Sprite upButton;
    private Sprite rect;

    private int PANEL_SIZE = 32;


    InputController() {
        touchPanelImage = new Texture(Gdx.files.internal("touchPanel.png"));
        touchPanel = TextureRegion.split(touchPanelImage, touchPanelImage.getWidth() / 3, touchPanelImage.getHeight() / 3);
    }

    public void makePanel() {

        leftUpButton = new Sprite(touchPanel[0][0]);
        leftUpButton.setBounds(0,64,PANEL_SIZE,PANEL_SIZE);

        upButton = new Sprite(touchPanel[0][1]);
        upButton.setBounds(leftUpButton.getX() + PANEL_SIZE, leftUpButton.getY(), PANEL_SIZE, PANEL_SIZE);

        rightUpButton = new Sprite(touchPanel[0][2]);
        rightUpButton.setBounds(upButton.getX() + PANEL_SIZE, upButton.getY(), PANEL_SIZE, PANEL_SIZE);

        leftButton = new Sprite(touchPanel[1][0]);
        leftButton.setBounds(leftUpButton.getX(), leftUpButton.getY() - PANEL_SIZE, PANEL_SIZE, PANEL_SIZE);

        rect = new Sprite(touchPanel[1][1]);
        rect.setBounds(leftButton.getX() + PANEL_SIZE, leftButton.getY(), PANEL_SIZE, PANEL_SIZE);

        rightButton = new Sprite(touchPanel[1][2]);
        rightButton.setBounds(rect.getX() + PANEL_SIZE, rect.getY(), PANEL_SIZE, PANEL_SIZE);

        leftDownButton = new Sprite(touchPanel[2][0]);
        leftDownButton.setBounds(leftButton.getX(), leftButton.getY() - PANEL_SIZE, PANEL_SIZE, PANEL_SIZE);

        downButton = new Sprite(touchPanel[2][1]);
        downButton.setBounds(leftDownButton.getX() + PANEL_SIZE, leftDownButton.getY(), PANEL_SIZE, PANEL_SIZE);

        rightDownButton = new Sprite(touchPanel[2][2]);
        rightDownButton.setBounds(downButton.getX() + PANEL_SIZE, downButton.getY(), PANEL_SIZE, PANEL_SIZE);

    }

    public void draw(Batch batch) {
        leftUpButton.draw(batch);
        upButton.draw(batch);
        rightUpButton.draw(batch);
        leftButton.draw(batch);
        rect.draw(batch);
        rightButton.draw(batch);
        leftDownButton.draw(batch);
        downButton.draw(batch);
        rightDownButton.draw(batch);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (Gdx.input.isTouched()) {
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
