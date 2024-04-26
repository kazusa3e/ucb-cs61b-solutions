package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;

public class Board implements WorldState {

    private static final int BLANK = 0;

    private int[][] data;
    private int N;

    public int tileAt(int i, int j) {
        if (i < 0 || i >= N || j < 0 || j >= N) {
            throw new IndexOutOfBoundsException();
        }
        return data[i][j];
    }

    public Board(int[][] tiles) {
        N = tiles.length;
        data = new int[N][];
        for (int ix = 0; ix != N; ++ix) {
            data[ix] = Arrays.copyOf(tiles[ix], N);
        }
    }

    public int size() {
        return N;
    }

    @Override
    /**
     * @Source: https://joshh.ug/neighbors.html
     */
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    public int hamming() {
        int numWrongPositions = 0;
        for (int row = 0; row != N; ++row) {
            for (int col = 0; col != N; ++col) {
                if (tileAt(row, col) == BLANK) {
                    continue;
                }
                if (tileAt(row, col) != (row * N) + col + 1) {
                    numWrongPositions += 1;
                }
            }
        }
        return numWrongPositions;
    }

    public int manhattan() {
        int ret = 0;
        for (int row = 0; row != N; ++row) {
            for (int col = 0; col != N; ++col) {
                if (tileAt(row, col) == BLANK) {
                    continue;
                }
                int expectedRow = tileAt(row, col) / N;
                int expectedCol = (tileAt(row, col) % N) - 1;
                ret += Math.abs(expectedRow - row) + Math.abs(expectedCol - col);
            }
        }
        return ret;
    }

    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    public boolean equals(Object y) {
        if (!(y instanceof Board)) {
            return false;
        }
        if (this == y) {
            return true;
        }
        Board other = (Board) y;
        if (this.size() != other.size()) {
            return false;
        }
        for (int row = 0; row != this.size(); ++row) {
            for (int col = 0; col != this.size(); ++col) {
                if (this.tileAt(row, col) != other.tileAt(row, col)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean isGoal() {
        return hamming() == 0;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
