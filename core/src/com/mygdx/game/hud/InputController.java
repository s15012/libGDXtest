package com.mygdx.game.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Direction;
import com.mygdx.game.Dungeon;

import org.lwjgl.opengl.Display;

public class InputController extends HUDComponents {

    private Texture touchPanelImage;
    private TextureRegion[][] touchPanel;
    private Image leftButton;
    private Image leftUpButton;
    private Image leftDownButton;
    private Image rightButton;
    private Image rightUpButton;
    private Image rightDownButton;
    private Image downButton;
    private Image upButton;
    private Image rect;

    private InputListener listener;

    private int PANEL_SIZE = 128;


    public InputController(InputListener listener) {
        this.listener = listener;
        touchPanelImage = new Texture(Gdx.files.internal("touchPanel.png"));
        touchPanel = TextureRegion.split(touchPanelImage, touchPanelImage.getWidth() / 3, touchPanelImage.getHeight() / 3);
    }

    public void makePanel() {

        leftUpButton = new Image(touchPanel[0][0]);
        leftUpButton.setBounds(0, PANEL_SIZE * 2, PANEL_SIZE, PANEL_SIZE);
        leftUpButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                InputController.this.touchDown(Direction.LEFT_UP);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                InputController.this.touchUp(Direction.LEFT_UP);
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                InputController.this.touchDragged(Direction.LEFT_UP);
            }
        });
        addActor(leftUpButton);

        upButton = new Image(touchPanel[0][1]);
        upButton.setBounds(leftUpButton.getX() + PANEL_SIZE, leftUpButton.getY(), PANEL_SIZE, PANEL_SIZE);
        addActor(upButton);
        upButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                InputController.this.touchDown(Direction.UP);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                InputController.this.touchUp(Direction.UP);
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                InputController.this.touchDragged(Direction.UP);
            }
        });

        rightUpButton = new Image(touchPanel[0][2]);
        rightUpButton.setBounds(upButton.getX() + PANEL_SIZE, upButton.getY(), PANEL_SIZE, PANEL_SIZE);
        addActor(rightUpButton);
        rightUpButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                InputController.this.touchDown(Direction.RIGHT_UP);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                InputController.this.touchUp(Direction.RIGHT_UP);
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                InputController.this.touchDragged(Direction.RIGHT_UP);
            }
        });

        leftButton = new Image(touchPanel[1][0]);
        leftButton.setBounds(leftUpButton.getX(), leftUpButton.getY() - PANEL_SIZE, PANEL_SIZE, PANEL_SIZE);
        addActor(leftButton);
        leftButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                InputController.this.touchDown(Direction.LEFT);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                InputController.this.touchUp(Direction.LEFT);
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                InputController.this.touchDragged(Direction.LEFT);
            }
        });

        rect = new Image(touchPanel[1][1]);
        rect.setBounds(leftButton.getX() + PANEL_SIZE, leftButton.getY(), PANEL_SIZE, PANEL_SIZE);
        addActor(rect);
        rect.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //InputController.this.touchDown(Direction.);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                //InputController.this.touchUp(Direction.);
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                //InputController.this.touchDragged(Direction.);
            }
        });

        rightButton = new Image(touchPanel[1][2]);
        rightButton.setBounds(rect.getX() + PANEL_SIZE, rect.getY(), PANEL_SIZE, PANEL_SIZE);
        addActor(rightButton);
        rightButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                InputController.this.touchDown(Direction.RIGHT);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                InputController.this.touchUp(Direction.RIGHT);
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                InputController.this.touchDragged(Direction.RIGHT);
            }
        });

        leftDownButton = new Image(touchPanel[2][0]);
        leftDownButton.setBounds(leftButton.getX(), leftButton.getY() - PANEL_SIZE, PANEL_SIZE, PANEL_SIZE);
        addActor(leftDownButton);
        leftDownButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                InputController.this.touchDown(Direction.LEFT_DOWN);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                InputController.this.touchUp(Direction.LEFT_DOWN);
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                InputController.this.touchDragged(Direction.LEFT_DOWN);
            }
        });

        downButton = new Image(touchPanel[2][1]);
        downButton.setBounds(leftDownButton.getX() + PANEL_SIZE, leftDownButton.getY(), PANEL_SIZE, PANEL_SIZE);
        addActor(downButton);
        downButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                InputController.this.touchDown(Direction.DOWN);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                InputController.this.touchUp(Direction.DOWN);
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                InputController.this.touchDragged(Direction.DOWN);
            }
        });

        rightDownButton = new Image(touchPanel[2][2]);
        rightDownButton.setBounds(downButton.getX() + PANEL_SIZE, downButton.getY(), PANEL_SIZE, PANEL_SIZE);
        addActor(rightDownButton);
        rightDownButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                InputController.this.touchDown(Direction.RIGHT_DOWN);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                InputController.this.touchUp(Direction.RIGHT_DOWN);
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                InputController.this.touchDragged(Direction.RIGHT_DOWN);
            }
        });

    }

    public void touchDown(Direction direction) {
        if (direction != null) {
            listener.onTouchDown(direction);
        }
    }

    public void touchUp(Direction direction) {
        if (direction != null) {
            listener.onTouchUp(direction);
        }
    }

    public void touchDragged(Direction direction) {
        if (direction != null) {
            listener.onTouchMove(direction);
        }
    }


    public interface InputListener {
        public void onTouchDown(Direction direction);

        public void onTouchUp(Direction direction);

        public void onTouchMove(Direction direction);
    }
}
