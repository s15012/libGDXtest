//package com.mygdx.game;
//
//import com.badlogic.gdx.ApplicationListener;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Input;
//import com.badlogic.gdx.InputProcessor;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.Batch;
//import com.badlogic.gdx.graphics.g2d.Sprite;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.utils.viewport.FitViewport;
//
//public class Exam implements ApplicationListener {
//
//    Batch batch;
//    Texture charaImage;
//    Sprite chara;
//
//    OrthographicCamera camera;
//    FitViewport viewport;
//
//    public static Vector2 charaPos;
//    public static Vector2 targetPos;
//
//    public static State state;
//    public static Dirs dir;
//
//    Move move;
//
//    enum Dirs {
//        LEFT,
//        RIGHT,
//        UP,
//        DOWN,
//        LEFTUP,
//        LEFTDOWN,
//        RIGHTUP,
//        RIGHTDOWN
//    }
//
//    enum State {
//        IDLE,
//        MOVE
//    }
//
//
//    @Override
//    public void create() {
//        move = new Move();
//        batch = new SpriteBatch();
//        charaImage = new Texture(Gdx.files.internal("android/assets/bg.png"));
//        chara = new Sprite(charaImage);
//        charaPos = new Vector2();
//        targetPos = new Vector2();
//
//        camera = new OrthographicCamera(240, 240);
//        camera.setToOrtho(false, 240, 240);
//        viewport = new FitViewport(600, 480, camera);
//    }
//
//    @Override
//    public void resize(int width, int height) {
//        viewport.update(width, height);
//
//    }
//
//    @Override
//    public void render() {
//
//        Gdx.gl.glClearColor(1, 1, 1, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//        camera.update();
//        camera.position.x = charaPos.x;
//        camera.position.y = charaPos.y;
//
//
//        batch.setProjectionMatrix(camera.combined);
//        batch.begin();
//            chara.draw(batch);
//        batch.end();
//
//    }
//
//    @Override
//    public void pause() {
//
//    }
//
//    @Override
//    public void resume() {
//
//    }
//
//    @Override
//    public void dispose() {
//
//    }
//}
//
//class Move implements InputProcessor {
//
//
//    private float moveX = 32;
//    private float moveY = 32;
//
//
//    private static final float MOVE_SPEED = 1;
//    private static final float DIAGONAL = MOVE_SPEED * 0.7071f;
//
//    @Override
//    public boolean keyDown(int keycode) {
//        switch (keycode) {
//            case Input.Keys.LEFT:
//                left();
//                break;
//            default:
//                break;
//        }
//        return false;
//    }
//
//    @Override
//    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//        return false;
//    }
//
//    private void positionMove(Exam.Dirs direction, float dx, float dy, Vector2 targetPos, Vector2 charaPos) {
//        Exam.state = Exam.State.MOVE;
//        Exam.dir = direction;
//
//        targetPos.x = charaPos.x + moveX * dx;
//        targetPos.y = charaPos.y + moveY * dy;
//
//    }
//
//    public void left() {
//        positionMove(Exam.Dirs.LEFT, -MOVE_SPEED, 0, Exam.targetPos, Exam.charaPos);
//    }
//
//
//
//
//
//
//    @Override
//    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
//        return false;
//    }
//
//    @Override
//    public boolean keyUp(int keycode) {
//        return false;
//    }
//
//    @Override
//    public boolean keyTyped(char character) {
//        return false;
//    }
//
//    @Override
//    public boolean touchDragged(int screenX, int screenY, int pointer) {
//        return false;
//    }
//
//    @Override
//    public boolean mouseMoved(int screenX, int screenY) {
//        return false;
//    }
//
//    @Override
//    public boolean scrolled(int amount) {
//        return false;
//    }
//}