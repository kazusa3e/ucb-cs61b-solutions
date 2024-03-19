package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.*;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private static final long SEED = new Date().getTime();
    private static final Random RANDOM = new Random(SEED);

    public static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point(" + this.x + "," + this.y + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Point)) {
                return false;
            }
            Point p = (Point) obj;
            return (this.x == p.x) && (this.y == p.y);
        }

        @Override
        public int hashCode() {
            return (31 * this.x) * 31 + this.y;
        }
    }

    public static Set<Point> hexagonRegion(Point tl, int size) {
        Set<Point> ret = new LinkedHashSet<>();
        for (int dy = 0; dy != size; ++dy) {
            int y = tl.y - dy;
            int offset = tl.x - dy;
            for (int dx = 0; dx != size + dy * 2; ++dx) {
                ret.add(new Point(offset + dx, y));
            }
        }
        for (int dy = 0; dy != size; ++dy) {
            int y = tl.y - size - dy;
            int offset = tl.x - (size - 1) + dy;
            for (int dx = 0; dx != size + (size - dy - 1) * 2; ++dx) {
                ret.add(new Point(offset + dx, y));
            }
        }
        return ret;
    }

    public static void fillWithNothing(TETile[][] tiles) {
        for (int x = 0; x != tiles.length; ++x) {
            for (int y = 0; y != tiles[0].length; ++y) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
    }
    
    public static void drawHexagonAt(Point tl, int size, TETile[][] tiles, TETile t) {
        Set<Point> region = hexagonRegion(tl, size);
        for (Point p: region) {
            if (p.x >= 0 && p.x < WIDTH && p.y >= 0 && p.y < HEIGHT) {
                tiles[p.x][p.y] = t;
            }
        }
    }

    public static TETile randomTile() {
        int tileNum = RANDOM.nextInt(6);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.GRASS;
            case 3: return Tileset.SAND;
            case 4: return Tileset.TREE;
            case 5: return Tileset.WATER;
            default: return Tileset.NOTHING;
        }
    }

    public static boolean isOverlap(TETile[][] tiles, Set<Point> region) {
        for (Point p: region) {
            if (p.x >= 0 && p.x < WIDTH && p.y >= 0 && p.y < HEIGHT) {
                if (tiles[p.x][p.y] != Tileset.NOTHING) {
                    return true;
                }
            }
        }
        return false;
    }

    public static List<Point> spaces(TETile[][] tiles) {
        List<Point> ret = new ArrayList<>();
        for (int x = 0; x != WIDTH; ++x) {
            for (int y = 0; y != HEIGHT; ++y) {
                if (tiles[x][y].equals(Tileset.NOTHING)) {
                    ret.add(new Point(x, y));
                }
            }
        }
        return ret;
    }

    public static Point randomLocation(TETile[][] tiles) {
        List<Point> space = spaces(tiles);
        int rand = RANDOM.nextInt(space.size());
        return space.get(rand);
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] tiles = new TETile[WIDTH][HEIGHT];
        fillWithNothing(tiles);

        int size = RANDOM.nextInt(2, 5);

        // TODO: find a way to fill it all.
        while (spaces(tiles).size() > 1200) {
            System.out.println(spaces(tiles).size());
            Point p = randomLocation(tiles);
            while (isOverlap(tiles, hexagonRegion(p, size))) {
                p = randomLocation(tiles);
            }
            drawHexagonAt(p, size, tiles, randomTile());
        }

        ter.renderFrame(tiles);
    }

}
