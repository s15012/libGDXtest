//package com.mygdx.game;
//
//import com.badlogic.gdx.Input;
//import com.badlogic.gdx.InputProcessor;
//import com.badlogic.gdx.math.Vector2;
//
//public class MoveEvent implements InputProcessor {
//
//    enum Dirs {
//        DIR_LEFT,
//        DIR_RIGHT,
//        DIR_UP,
//        DIR_DOWN,
//        DIR_LUP,
//        DIR_LDOWN,
//        DIR_RUP,
//        DIR_RDOWN
//    }
//    enum State {
//        MOVE,
//        IDLE
//    }
//
//    State state;
//    Dirs dir;
//
//    private static final float MOVE_SPEED = 1;
//    private static final float DIAGONAL = MOVE_SPEED * 0.7071f;
//    private float moveX = 32;
//    private float moveY = 32;
//
//    public Vector2 targetPos;
//    public Vector2 charaPos;
//
//    private void positionMove(Dirs direction, float dx, float dy, Vector2 targetPos, Vector2 charaPos) {
//        state = State.MOVE;
//        dir = direction;
//
//        targetPos.x = charaPos.x + moveX * dx;
//        targetPos.y = charaPos.y + moveY * dy;
//
//    }
//
//
//
//    @Override
//    public boolean keyDown(int keycode) {
//        switch (keycode) {
//            case Input.Keys.LEFT:
//                if (keycode == Input.Keys.UP) {
//                    moveLeftUp();
//                } else if (keycode == Input.Keys.DOWN) {
//                    moveLeftDown();
//                } else {
//                    moveLeft();
//                }
//                break;
//            case Input.Keys.RIGHT:
//                if (keycode == Input.Keys.UP) {
//                    moveRightUp();
//                } else if (keycode == Input.Keys.DOWN) {
//                    moveRightDown();
//                } else {
//                    moveRight();
//                }
//                break;
//            case Input.Keys.UP:
//                moveUp();
//                break;
//            case Input.Keys.DOWN:
//                moveDown();
//                break;
//            default:
//                break;
//
//        }
//
//        return false;
//    }
//
//    @Override
//    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//        return false;
//    }
//
//
//
//
//
//
//    private void moveLeftUp() {
//        positionMove(Dirs.DIR_LUP, -DIAGONAL, DIAGONAL, targetPos, charaPos);
//    }
//    private void moveLeftDown() {
//        positionMove(Dirs.DIR_LDOWN, -DIAGONAL, -DIAGONAL, targetPos, charaPos);
//    }
//    private void moveRightUp() {
//        positionMove(Dirs.DIR_RUP, DIAGONAL, DIAGONAL, targetPos, charaPos);
//    }
//    private void moveRightDown() {
//        positionMove(Dirs.DIR_RDOWN, DIAGONAL, -DIAGONAL, targetPos, charaPos);
//    }
//    private void moveLeft() {
//        positionMove(Dirs.DIR_LEFT, -MOVE_SPEED, 0, targetPos, charaPos);
//    }
//    private void moveRight() {
//        positionMove(Dirs.DIR_RIGHT, MOVE_SPEED, 0, targetPos, charaPos);
//    }
//    private void moveUp() {
//        positionMove(Dirs.DIR_UP, 0, MOVE_SPEED, targetPos, charaPos);
//    }
//    private void moveDown() {
//        positionMove(Dirs.DIR_DOWN, 0, -MOVE_SPEED, targetPos, charaPos);
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
//    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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
//
//
//
//}
