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

    public Enemy() {
        super();
    }

    @Override
    public int getIdentifier() {
        return 0;
    }


    public void findMoveToMainCharacter(boolean updateStateEnabled) {
        if (getState() == MovableDungeonObject.State.IDLE && updateStateEnabled) {
            Array<TiledNode> array = getDungeonBlockManager().findMainCharacter(this);
            if (array.size > 1) {
                DungeonObject target = (DungeonObject) array.get(1);
                Vector2 current = getDungeonBlockManager().vectorToBlockVector(getCurrentPosition());
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
                Gdx.app.log("find", builder.toString());*/
                if (direction != null) {
                    Gdx.app.log("enemy move", direction.toString());
                    setMoveDirection(direction);
                }
            }
        }
        move(true);
    }
}
