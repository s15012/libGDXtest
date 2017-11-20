package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class InputController implements InputProcessor {

    private Texture touchPanelImage;
    private TextureRegion[][] touchPanel;
    private Sprite leftButton;
    private Sprite leftUpButton;
    private Sprite leftDownButton;
    private Sprite rightButton;
    private Sprite rightUpButton;
    private Sprite rightDownButton;
    private Sprite upButton;
    private Sprite downButton;

    private OrthographicCamera camera;

    InputController() {
        touchPanelImage = new Texture(Gdx.files.internal("touchPanel.png"));
        touchPanel = TextureRegion.split(touchPanelImage, touchPanelImage.getWidth() / 3, touchPanelImage.getHeight() / 3);
    }

    public void makePanel() {

        leftUpButton = new Sprite(touchPanel[0][0]);
        leftUpButton.setBounds(200, 200, 32, 32);
//        upButton = new Sprite(touchPanel[0][1]);
//        rightUpButton = new Sprite(touchPanel[0][2]);
//
//        leftButton = new Sprite(touchPanel[1][0]);
//        rightButton = new Sprite(touchPanel[1][2]);
//
//        leftDownButton = new Sprite(touchPanel[2][0]);
//        downButton = new Sprite(touchPanel[2][1]);
//        rightDownButton = new Sprite(touchPanel[2][2]);

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
        //DESK用のmoveイベント流用
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
