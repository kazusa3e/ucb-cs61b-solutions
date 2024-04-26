package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.*;

public class Solver {

    private class Node {

        WorldState state;
        int distance;
        Node prev;

        public Node(WorldState s, int d, Node p) {
            state = s;
            distance = d;
            prev = p;
        }
    }

//    private WorldState initial;
    private MinPQ<Node> pq;
    private Node solution;

    public Solver(WorldState initial) {
        pq = new MinPQ<>(1, (a, b) -> a.distance + a.state.estimatedDistanceToGoal() - b.distance - b.state.estimatedDistanceToGoal());
        pq.insert(new Node(initial, 0, null));
        while (!pq.isEmpty()) {
            Node curr = pq.delMin();
            if (curr.state.isGoal()) {
                solution = curr;
                return;
            }
            for (WorldState n : curr.state.neighbors()) {
                if (n.estimatedDistanceToGoal() == curr.state.estimatedDistanceToGoal() + 1) {
                    continue;
                }
                pq.insert(new Node(n, curr.distance + 1, curr));
            }
        }
    }
    public int moves() {
        if (solution == null) {
            return -1;
        }
        return solution.distance;
    }

    private static class SolverIterator implements Iterator<WorldState> {

        private Queue<WorldState> queue;

        public SolverIterator(Node solution) {
            queue = new ArrayDeque<>();
            Stack<WorldState> stack = new Stack<>();
            Node iter = solution;
            while (iter != null) {
                stack.push(iter.state);
                iter = iter.prev;
            }
            while (!stack.isEmpty()) {
                queue.add(stack.pop());
            }

        }

        @Override
        public boolean hasNext() {
            return queue.size() != 0;
        }

        @Override
        public WorldState next() {
            return queue.poll();
        }
    }

    public Iterable<WorldState> solution() {
        // TODO:
        return new Iterable<WorldState>() {
            @Override
            public Iterator<WorldState> iterator() {
                return new SolverIterator(solution);
            }
        };
    }
}
