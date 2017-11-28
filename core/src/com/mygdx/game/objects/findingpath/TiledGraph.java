package com.mygdx.game.objects.findingpath;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class TiledGraph<N extends TiledNode> implements IndexedGraph<TiledNode> {

    protected N[][] nodes;

    public boolean diagonal;
    public N startNode;

    private int width;
    private int height;

    public TiledGraph(int width, int height) {
        this.width = width;
        this.height = height;
        this.diagonal = true;
        this.startNode = null;
    }

    public void init(N[][] nodes) {
        this.nodes = nodes;
        // Each node has up to 4 neighbors, therefore no diagonal movement is possible
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                N n = nodes[x][y];
                if (x > 0) addConnection(n, -1, 0);
                if (y > 0) addConnection(n, 0, -1);
                if (x < width - 1) addConnection(n, 1, 0);
                if (y < height - 1) addConnection(n, 0, 1);
            }
        }

    }

    public N getNode(int x, int y) {
        return nodes[x][y];
    }

    public N getNode(Vector2 vector) {
        return nodes[(int) vector.x][(int) vector.y];
    }


    public N getNode(int index) {
        int x = index / width;
        int y = index % height;
        return nodes[x][y];
    }

    @Override
    public int getIndex(TiledNode node) {
        return node.getIndex();
    }

    @Override
    public int getNodeCount() {
        return width * height;
    }

    @Override
    public Array<Connection<TiledNode>> getConnections(TiledNode fromNode) {
        return fromNode.getConnections();
    }

    private void addConnection(TiledNode n, int xOffset, int yOffset) {
        TiledNode target = getNode(n.x + xOffset, n.y + yOffset);
        if (!target.isBlock) {
            n.getConnections().add(new TiledConnection(this, n, target));
        }
    }
}
