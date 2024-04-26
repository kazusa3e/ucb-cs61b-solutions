package hw4.puzzle;

import java.util.Arrays;

public class Board {

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    private int[][] data;

    public int tileAt(int i, int j) {
        return data[i][j];
    }

    public Board(int[][] d) {
        data = new int[d.length][];
        for (int ix = 0; ix != d.length; ++ix) {
            data[ix] = Arrays.copyOf(d[ix], d[ix].length);
        }
    }

    public int size() {
        return data.length;
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
