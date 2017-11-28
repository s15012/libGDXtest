package com.mygdx.game.objects.findingpath;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.utils.Array;

public abstract class TiledNode extends TiledNodeBase<TiledNode> {
    public TiledNode(int x, int y, int index) {
        super(x, y, index, new Array<Connection<TiledNode>>(8));
    }
}