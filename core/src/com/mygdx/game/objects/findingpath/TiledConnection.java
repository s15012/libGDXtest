package com.mygdx.game.objects.findingpath;

import com.badlogic.gdx.ai.pfa.DefaultConnection;

public class TiledConnection extends DefaultConnection<TiledNode> {

    static final float NON_DIAGONAL_COST = (float) Math.sqrt(2);

    TiledGraph worldMap;

    public TiledConnection(TiledGraph worldMap, TiledNode fromNode, TiledNode toNode) {
        super(fromNode, toNode);
        this.worldMap = worldMap;
    }

    @Override
    public float getCost() {
        return 1;
        //return getToNode().x != worldMap.startNode.x && getToNode().y != worldMap.startNode.y ? NON_DIAGONAL_COST : 1;
    }
}