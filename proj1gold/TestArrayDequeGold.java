import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    
    private static final int TEST_COUNT = 100;
    private static final int TEST_VALUE_LIMIT = 100;

    @Test
    public void randomTest() {
        StringBuffer sb = new StringBuffer();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();

        for (int ix = 0; ix != TEST_COUNT; ++ix) {
            int oper = StdRandom.uniform(4);
            switch (oper) {
                case 0: {
                    Integer val = StdRandom.uniform(TEST_VALUE_LIMIT);
                    ads.addFirst(val);
                    sad.addFirst(val);
                    sb.append("addFirst(").append(val).append(")\n");
                    break;
                }
                case 1: {
                    Integer val = StdRandom.uniform(TEST_VALUE_LIMIT);
                    ads.addLast(val);
                    sad.addLast(val);
                    sb.append("addLast(").append(val).append(")\n");
                    break;
                }
                case 2: {
                    if (ads.size() == 0) {
                        break;
                    }
                    Integer va = ads.removeFirst();
                    Integer vb = ads.removeFirst();
                    sb.append("removeFirst()");
                    assertEquals(sb.toString(), va, vb);
                    sb.append("\n");
                    break;
                }
                case 3: {
                    if (ads.size() == 0) {
                        break;
                    }
                    Integer va = ads.removeLast();
                    Integer vb = ads.removeLast();
                    sb.append("removeLast()");
                    assertEquals(sb.toString(), va, vb);
                    sb.append("\n");
                    break;
                }
                default:
                    break;
            }
        }
    }

}
