import edu.princeton.cs.algs4.Queue;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        Queue<Queue<Item>> ret = new Queue<>();
        while (!items.isEmpty()) {
            Queue<Item> q = new Queue<>();
            q.enqueue(items.dequeue());
            ret.enqueue(q);
        }
        return ret;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> ret = new Queue<>();
        while (!q1.isEmpty() && !q2.isEmpty()) {
            ret.enqueue(getMin(q1, q2));
        }
        while (!q1.isEmpty()) {
            ret.enqueue(q1.dequeue());
        }
        while (!q2.isEmpty()) {
            ret.enqueue(q2.dequeue());
        }
        return ret;
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        int sz = items.size();
        if (sz <= 1) {
            return items;
        }
        Queue<Item> input = new Queue<>();
        for (int ix = 0; ix != sz; ++ix) {
            Item item = items.dequeue();
            input.enqueue(item);
            items.enqueue(item);
        }
        int mid = sz >> 1;
        Queue<Item> leftPart = new Queue<>(), rightPart = new Queue<>();
        for (int ix = 0; ix != mid; ++ix) {
            leftPart.enqueue(input.dequeue());
        }
        for (int ix = mid; ix != sz; ++ix) {
            rightPart.enqueue(input.dequeue());
        }
        leftPart = mergeSort(leftPart);
        rightPart = mergeSort(rightPart);
        return mergeSortedQueues(leftPart, rightPart);
    }

    public static void main(String[] args) {
        Queue<String> origin = new Queue<>();
        origin.enqueue("Alice");
        origin.enqueue("Vanessa");
        origin.enqueue("Ethan");
        origin.enqueue("Bob");
        origin.enqueue("Flank");

        Queue<String> sorted = mergeSort(origin);
        System.out.println("origin: " + origin);
        System.out.println("sorted: " + sorted);
    }
}
