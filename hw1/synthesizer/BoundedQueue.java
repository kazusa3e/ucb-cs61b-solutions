package synthesizer;

import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T> {

    public int capacity();

    public int fillCount();

    void enqueue(T x);

    T dequeue();

    T peek();

    default boolean isEmpty() {
        return fillCount() == 0;
    }

    default boolean isFull() {
        return fillCount() == capacity();
    }

    abstract Iterator<T> iterator();

}
