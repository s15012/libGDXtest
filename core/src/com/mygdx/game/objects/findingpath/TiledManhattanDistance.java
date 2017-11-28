package com.mygdx.game.objects.findingpath;

import com.badlogic.gdx.ai.pfa.Heuristic;

public class TiledManhattanDistance<N extends TiledNode> implements Heuristic<N> {

    public TiledManhattanDistance() {
    }

    @Override
    public float estimate(N node, N endNode) {
        return Math.abs(endNode.x - node.x) + Math.abs(endNode.y - node.y);
    }
}