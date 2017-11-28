package com.mygdx.game.objects.findingpath;

import com.badlogic.gdx.ai.pfa.PathSmoother;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class PathFinder {
    TiledGraph worldMap;
    TiledSmoothableGraphPath<TiledNode> path;
    TiledManhattanDistance<TiledNode> heuristic;
    IndexedAStarPathFinder<TiledNode> pathFinder;
    PathSmoother<TiledNode, Vector2> pathSmoother;


    public PathFinder(TiledGraph worldMap) {
        this.worldMap = worldMap;
        init();
    }

    private void init() {
        path = new TiledSmoothableGraphPath<TiledNode>();
        heuristic = new TiledManhattanDistance<TiledNode>();
        pathFinder = new IndexedAStarPathFinder<TiledNode>(worldMap, true);
        pathSmoother = new PathSmoother<TiledNode, Vector2>(new TiledRaycastCollisionDetector<TiledNode>(worldMap));

    }

    public Array<TiledNode> findPath(Vector2 start, Vector2 target) {
        path.clear();
        TiledNode startNode = worldMap.getNode(start);
        TiledNode targetNode = worldMap.getNode(target);
        pathFinder.searchNodePath(startNode, targetNode, heuristic, path);
        if (pathFinder.metrics != null) {
            //pathFinder.metrics.visitedNodes
        }
        //pathSmoother.smoothPath(path);
        int nodeCount = path.getCount();
        Array<TiledNode> nodes = new Array<TiledNode>(nodeCount);
        for (int i = 0; i < nodeCount; i++) {
            TiledNode node = path.nodes.get(i);
            nodes.insert(0,node);
        }
        return nodes;
    }
}
