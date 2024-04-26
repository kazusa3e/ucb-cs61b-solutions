package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class Solver {

    private static class SearchNode implements Comparable<SearchNode> {
        WorldState state;
        int distance;
        SearchNode prev;

        public SearchNode(WorldState ws, int d, SearchNode p) {
            state = ws;
            distance = d;
            prev = p;
        }

        @Override
        public int compareTo(SearchNode o) {
            return distance + state.estimatedDistanceToGoal()
                    - o.distance - o.state.estimatedDistanceToGoal();
        }

    }

    private MinPQ<SearchNode> pq;
    private Queue<WorldState> solution;
    private int totalMoves;

    private Queue<WorldState> getResult(SearchNode node) {
        Queue<WorldState> result = new ArrayDeque<>();
        Stack<SearchNode> stack = new Stack<>();
        SearchNode iter = node;
        while (iter != null) {
            stack.push(iter);
            iter = iter.prev;
        }
        while (!stack.isEmpty()) {
            result.add(stack.pop().state);
        }
        return result;
    }

    private void solve() {
        while (!pq.isEmpty()) {
            SearchNode curr = pq.delMin();
            if (curr.state.isGoal()) {
                solution = getResult(curr);
                totalMoves = curr.distance;
                break;
            }
            for (WorldState n: curr.state.neighbors()) {
                if (curr.prev != null && n.equals(curr.prev.state)) {
                    continue;
                }
                SearchNode node = new SearchNode(n, curr.distance + 1, curr);
                pq.insert(node);
            }
        }
    }

    public Solver(WorldState initial) {
        pq = new MinPQ<>();
        SearchNode n = new SearchNode(initial, 0, null);
        pq.insert(n);
        solve();
    }

    public int moves() {
        if (solution == null) {
            return -1;
        }
        return totalMoves;
    }

    public Iterable<WorldState> solution() {
        return solution;
    }

}
