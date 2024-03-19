package synthesizer;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the ArrayRingBuffer class.
 * 
 * @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(4);
        assertTrue(arb.isEmpty());
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        assertTrue(arb.isFull());
        assertEquals((Integer) 1, arb.peek());
        assertEquals((Integer) 1, arb.dequeue());
        assertEquals((Integer) 2, arb.dequeue());
        arb.enqueue(1);
        arb.enqueue(2);
        assertEquals((Integer) 3, arb.peek());
        assertTrue(arb.isFull());
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
}
