package byog.lab5;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hamcrest.core.Is;
import org.junit.Test;
import byog.lab5.HexWorld.Point;

import static org.junit.Assert.*;

public class HexWorldTest {

    @Test
    public void testHexagonRegion() {
        assertEquals(Set.of(
                new Point(0, 0), new Point(1, 0),
                new Point(-1, -1), new Point(0, -1), new Point(1, -1), new Point(2, -1),
                new Point(-1, -2), new Point(0, -2), new Point(1, -2), new Point(2, -2),
                new Point(0, -3), new Point(1, -3)
        ), HexWorld.hexagonRegion(new Point(0, 0), 2));

        // TODO: something wrong?
        // assertEquals(Set.of(
        //         new Point(0, 0), new Point(1, 0), new Point(2, 0),
        //         new Point(-1, -1), new Point(0, -1), new Point(1, -1), new Point(2, -1), new Point(3, -1),
        //         new Point(-2, -2), new Point(-1, -2), new Point(0, -2), new Point(1, -2), new Point(2, -2), new Point(4, -2),
        //         new Point(-2, -3), new Point(-1, -3), new Point(0, -3), new Point(1, -3), new Point(2, -3), new Point(4, -3),
        //         new Point(-1, -4), new Point(0, -4), new Point(1, -4), new Point(2, -4), new Point(3, -4),
        //         new Point(0, -5), new Point(1, -5), new Point(2, -5)
        // ), HexWorld.hexagonRegion(new Point(0, 0), 3));
    }

}
