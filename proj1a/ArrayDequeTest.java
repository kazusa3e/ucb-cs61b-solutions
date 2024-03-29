/** Performs some basic linked list tests. */
public class ArrayDequeTest {

    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    public static boolean checkValue(int expected, int actual) {
        if (expected != actual) {
            System.out.println(
                    "get() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /*
     * Prints a nice message based on whether a test passed.
     * The \n means newline.
     */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    /**
     * Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation.
     */
    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");
        System.out.println(
                "Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<String> lld1 = new ArrayDeque<String>();

        boolean passed = checkEmpty(true, lld1.isEmpty());
        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false
        // otherwise.
        passed = checkSize(1, lld1.size()) && passed;
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.addLast("middle");
        passed = checkSize(2, lld1.size()) && passed;

        lld1.addLast("back");
        passed = checkSize(3, lld1.size()) && passed;

        System.out.println("Printing out deque: ");
        lld1.printDeque();

        printTestStatus(passed);

    }

    /**
     * Adds an item, then removes an item, and ensures that dll is empty afterwards.
     */
    public static void addRemoveTest() {

        System.out.println("Running add/remove test.");

        System.out.println(
                "Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        // should be empty
        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.removeFirst();
        // should be empty
        passed = checkEmpty(true, lld1.isEmpty()) && passed;

        printTestStatus(passed);

    }

    public static void resizeTest() {
        System.out.println("Running resize test.");
        ArrayDeque<Integer> lst = new ArrayDeque<>();
        boolean passed = checkEmpty(true, lst.isEmpty());
        lst.addLast(1);
        lst.addLast(2);
        lst.addLast(3);
        lst.addLast(4);
        lst.addLast(5);
        lst.addLast(6);
        lst.addLast(7);
        lst.addLast(8);
        lst.addLast(9);
        lst.addLast(10);
        System.out.print("Printing out deque: ");
        lst.printDeque();
        System.out.println();
        passed = checkValue(lst.get(0), 1) && passed;
        passed = checkValue(lst.get(1), 2) && passed;
        passed = checkValue(lst.get(2), 3) && passed;
        passed = checkValue(lst.get(3), 4) && passed;
        passed = checkValue(lst.get(4), 5) && passed;
        passed = checkValue(lst.get(5), 6) && passed;
        passed = checkValue(lst.get(6), 7) && passed;
        passed = checkValue(lst.get(7), 8) && passed;
        passed = checkValue(lst.get(8), 9) && passed;
        passed = checkValue(lst.get(9), 10) && passed;
        printTestStatus(passed);
    }

    public static void getTest() {
        System.out.println("Running a get test.");
        ArrayDeque<Integer> lst = new ArrayDeque<>();
        boolean passed = checkEmpty(true, lst.isEmpty());
        lst.addLast(0);
        passed = checkValue(lst.get(0), 0) && passed;
        passed = checkValue(lst.removeFirst(), 0) && passed;
        lst.addFirst(3);
        passed = checkValue(lst.removeFirst(), 3) && passed;
        lst.addLast(5);
        lst.addLast(6);
        passed = checkValue(lst.removeFirst(), 5) && passed;
        lst.addFirst(8);
        passed = checkValue(lst.removeFirst(), 8) && passed;
        passed = checkValue(lst.removeLast(), 6) && passed;
        lst.addFirst(11);
        passed = checkValue(lst.get(0), 11) && passed;
        lst.addFirst(13);
        passed = checkValue(lst.get(0), 13) && passed;
        printTestStatus(passed);
    }

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        addIsEmptySizeTest();
        addRemoveTest();
        resizeTest();
        getTest();
    }
}
