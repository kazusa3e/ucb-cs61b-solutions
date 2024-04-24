package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private Maze maze;
    private boolean[] visited;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        distTo[0] = 0;
        edgeTo[0] = 0;
        visited = new boolean[maze.V()];
    }

    @Override
    public void solve() {
        dfs(maze.xyTo1D(1, 1));
    }

    private void dfs(int v) {
        marked[v] = true;
        visited[v] = true;
        announce();
        for (int w : maze.adj(v)) {
            if (visited[w]) {
                continue;
            }
            if (marked[w]) {
                return;
            }
            edgeTo[w] = v;
            announce();
            distTo[w] = distTo[v] + 1;
            dfs(w);
        }
    }

    // Helper methods go here
}

