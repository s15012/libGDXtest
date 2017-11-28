package com.mygdx.game.objects.findingpath;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.utils.Array;


public abstract class TiledNodeBase<N extends TiledNodeBase<N>> {

    public boolean isBlock = false;

    /**
     * The x coordinate of this tile
     */
    public final int x;

    /**
     * The y coordinate of this tile
     */
    public final int y;

    public final int index;


    protected Array<Connection<N>> connections;

    public TiledNodeBase(int x, int y, int index, Array<Connection<N>> connections) {
        this.x = x;
        this.y = y;
        this.index = index;
        this.connections = connections;
    }

    public int getIndex() {
        return index;
    }

    public Array<Connection<N>> getConnections() {
        return this.connections;
    }

}
