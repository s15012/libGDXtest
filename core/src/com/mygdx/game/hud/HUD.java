package com.mygdx.game.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.DrawComponent;

import java.util.ArrayList;

public class HUD implements DrawComponent {

    private ArrayList<HUDComponents> components = new ArrayList<HUDComponents>();
    private Stage stage = new Stage();

    public HUD() {
        Gdx.input.setInputProcessor(stage);
    }


    public void addComponent(HUDComponents component) {
        components.add(component);
        stage.addActor(component);
    }

    @Override
    public void draw(Batch batch, float stateTime) {
        stage.act(stateTime);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
