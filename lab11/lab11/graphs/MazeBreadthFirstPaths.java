package lab11.graphs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private Maze maze;
    private Queue<Integer> queue;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        queue = new ArrayDeque<>();
        queue.add(s);
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs(int v) {
        if (v == t) {
            return;
        }
        while (!queue.isEmpty()) {
            int w = queue.poll();
            marked[w] = true;
            for (int n : maze.adj(w)) {
                if (!marked[n]) {
                    edgeTo[n] = w;
                    distTo[n] = distTo[w] + 1;
                    announce();
                    queue.add(n);
                    marked[n] = true;
                    if (n == t) {
                        return;
                    }
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs(s);
    }
}

