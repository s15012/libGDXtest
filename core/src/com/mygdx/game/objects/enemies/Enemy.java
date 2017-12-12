package com.mygdx.game.objects.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Direction;
import com.mygdx.game.objects.Character;
import com.mygdx.game.objects.DungeonObject;
import com.mygdx.game.objects.MovableDungeonObject;
import com.mygdx.game.objects.findingpath.TiledNode;

public abstract class Enemy extends Character {

    private final int MOVE_RANGE = 5; //キャラ探索可能範囲

    public Enemy() {
        super();
    }

    @Override
    public int getIdentifier() {
        return 0;
    }


    public void findMoveToMainCharacter(boolean updateStateEnabled) {
        if (getState() == MovableDungeonObject.State.IDLE && updateStateEnabled) {
            Vector2 mainCharaPos = getDungeonBlockManager().vectorToBlockVector(getDungeonBlockManager().getMainCharacter().getTargetPosition());
            Vector2 current = getDungeonBlockManager().vectorToBlockVector(getCurrentPosition());

            if (isPosition(mainCharaPos, current)) {
                trackingMove(current);
            } else {
                randomMove();
            }
        }
        move(true);
    }

    private boolean isPosition(Vector2 target, Vector2 current) {

        int tX = (int) (target.x - current.x);
        int tY = (int) (target.y - current.y);

        if (tX < MOVE_RANGE && tX > -MOVE_RANGE) {
            if (tY < MOVE_RANGE && tY > -MOVE_RANGE) {
                return true;
            }
        }
        return false;
    }

    private void trackingMove(Vector2 current) {
        Array<TiledNode> array = getDungeonBlockManager().findMainCharacter(this);

        if (array.size > 1) {
            DungeonObject target = (DungeonObject) array.get(1);

            Direction direction = null;
            if (target.x > current.x) {
                direction = Direction.RIGHT;
            } else if (target.x < current.x) {
                direction = Direction.LEFT;
            } else if (target.y > current.y) {
                direction = Direction.UP;
            } else if (target.y < current.y) {
                direction = Direction.DOWN;
            }

                /*
                    StringBuilder builder = new StringBuilder();
                    Gdx.app.log("find", "\n");
                    for(TiledNode node: array) {
                        builder.append("["+node.x+":"+node.y+"] ->");
                        for(Connection<TiledNode> connectedNode:node.getConnections()) {
                            builder.append("["+connectedNode.getToNode().x+":"+connectedNode.getToNode().y+"]");
                        }
                        builder.append("\n");
                    }
                    Gdx.app.log("find", builder.toString());
                */

            if (direction != null) {
                setMoveDirection(direction);
            }

        }
    }

    private void randomMove() {
        int randomMath = (int) (Math.random() * 10);
        int random = randomMath % 4;
        Direction direction = null;

        if (random == 0) {
            direction = Direction.DOWN;
        } else if (random == 1) {
            direction = Direction.UP;
        } else if (random == 2) {
            direction = Direction.LEFT;
        } else if (random == 3) {
            direction = Direction.RIGHT;
        }


        if (direction != null) {
            setMoveDirection(direction);
        }
    }
}
