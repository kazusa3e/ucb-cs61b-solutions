package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = { "You can do this!", "I believe in you!",
            "You got this!", "You're a star!", "Go Bears!",
            "Too easy for you!", "Wow, so impressive!" };

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, long seed) {
        /*
         * Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as
         * its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is
         * (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        // Initialize random number generator
        this.rand = new Random(seed);
    }

    public String generateRandomString(int n) {
        // Generate random string of letters of length n
        StringBuilder sb = new StringBuilder();
        for (int ix = 0; ix != n; ++ix) {
            sb.append(CHARACTERS[rand.nextInt(CHARACTERS.length)]);
        }
        return sb.toString();
    }

    public void drawFrame(String s) {
        // Take the string and display it in the center of the screen
        StdDraw.clear();
        StdDraw.setFont(new Font("Monaco", Font.BOLD, 30));
        int midWidth = width / 2;
        int midHeight = height / 2;
        StdDraw.text(midWidth, midHeight, s);
        // If game is not over, display relevant game information at the top of
        // the screen
        if (this.gameOver) {
            StdDraw.text(midWidth, midHeight - 2, "Your score: " + (this.round - 1));
        }
        StdDraw.show();
    }

    public void flashSequence(String letters) {
        // Display each character in letters, making sure to blank the screen
        // between letters
        for (int ix = 0; ix != letters.length(); ++ix) {
            drawFrame(letters.substring(ix, ix + 1));
            StdDraw.pause(1000);
            drawFrame("");
            StdDraw.pause(500);
        }

    }

    public String solicitNCharsInput(int n) {
        // Read n letters of player input
        StringBuilder sb = new StringBuilder();
        int sz = 0;
        while (sz != n) {
            if (StdDraw.hasNextKeyTyped()) {
                sb.append(StdDraw.nextKeyTyped());
                sz += 1;
            }
            StdDraw.pause(500);
        }
        return sb.toString();
    }

    public void startGame() {
        // Set any relevant variables before the game starts
        this.round = 1;
        this.gameOver = false;
        this.playerTurn = false;

        // Establish Game loop
        while (!this.gameOver) {
            drawFrame("Round: " + this.round);
            String s = generateRandomString(this.round);
            flashSequence(s);
            String input = solicitNCharsInput(this.round);
            if (!input.equals(s)) {
                this.gameOver = true;
                drawFrame("Game Over!");
            }
            this.round += 1;
        }
    }

}
