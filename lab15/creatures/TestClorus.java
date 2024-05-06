package creatures;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class TestClorus {

    @Test
    public void testName() {
        Clorus c = new Clorus(2);
        assertEquals("clorus", c.name());
    }

    @Test
    public void testMoveAction() {
        Clorus c = new Clorus(2);
        assertEquals(2, c.energy(), 0.01);
        c.move();
        assertEquals(1.97, c.energy(), 0.01);
        c.move();
        assertEquals(1.94, c.energy(), 0.01);
        c.stay();
        assertEquals(1.93, c.energy(), 0.01);
        c.stay();
        assertEquals(1.92, c.energy(), 0.01);
    }

    @Test
    public void testColor() {
        Clorus c = new Clorus(2);
        assertEquals(new Color(34, 0, 231), c.color());
        c.move();
        assertEquals(new Color(34, 0, 231), c.color());
        c.stay();
        assertEquals(new Color(34, 0, 231), c.color());
    }

    @Test
    public void testAttack() {
        Clorus c = new Clorus(2);
        Plip p = new Plip(2);
        assertEquals(2, c.energy(), 0.01);
        c.attack(p);
        assertEquals(4, c.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Clorus c = new Clorus(2);
        assertEquals(2, c.energy(), 0.01);
        Clorus baby = c.replicate();
        assertNotSame(c, baby);
        assertEquals(1, c.energy(), 0.01);
        assertEquals(1, baby.energy(), 0.01);
    }

}
