package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.*;

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
            return distance + state.estimatedDistanceToGoal() - o.distance - o.state.estimatedDistanceToGoal();
        }

//        @Override
//        public boolean equals(Object obj) {
//            if (!(obj instanceof SearchNode)) return false;
//            if (this == obj) return true;
//            SearchNode other = (SearchNode) obj;
//            return this.state.equals(other.state) && this.distance == other.distance;
//        }


//        @Override
//        public int hashCode() {
//            return this.state.estimatedDistanceToGoal();
//        }

    }

    private MinPQ<SearchNode> pq;
    private Set<SearchNode> visited;
    private int PQInsertCounts;
    private List<WorldState> solution;

    private List<WorldState> getResult(SearchNode node) {
        List<WorldState> result = new ArrayList<>();
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
                break;
            }
            for (WorldState n: curr.state.neighbors()) {
//                if (visited.contains(n)) {
//                    continue;
//                }
                if (curr.prev != null && n.equals(curr.prev.state)) {
                    continue;
                }
//                if (n.equals(curr.prev.state)) {
//                    continue;
//                }
                SearchNode node = new SearchNode(n, curr.distance + 1, curr);
                pq.insert(node);
                visited.add(node);
                PQInsertCounts += 1;
            }
        }
    }

    public Solver(WorldState initial) {
        pq = new MinPQ<>();
        SearchNode n = new SearchNode(initial, 0, null);
        pq.insert(n);
        visited = new HashSet<>();
        visited.add(n);
        PQInsertCounts = 1;
        solve();
    }

    public int moves() {
        if (solution == null) {
            return -1;
        }
        return solution.size() - 1;
    }

    private static class SolutionIterator implements Iterator<WorldState> {

        private List<WorldState> sol;

        public SolutionIterator(List<WorldState> s) {
            sol = s;
        }

        @Override
        public boolean hasNext() {
            return !sol.isEmpty();
        }

        @Override
        public WorldState next() {
            return sol.removeFirst();
        }
    }

    public Iterable<WorldState> solution() {
        return () -> new SolutionIterator(solution);
    }

    public int PQInsertCounts() {
        return PQInsertCounts;
    }

}
