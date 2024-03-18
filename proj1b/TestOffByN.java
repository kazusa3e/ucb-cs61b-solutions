import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByN = new OffByN(5);

    // Your tests go here.
    @Test
    public void testOffByNComparator() {
        assertTrue(offByN.equalChars('a', 'f'));
        assertTrue(offByN.equalChars('b', 'g'));
        assertFalse(offByN.equalChars('b', 'e'));
        assertFalse(offByN.equalChars('f', 'h'));
    }
    
}
