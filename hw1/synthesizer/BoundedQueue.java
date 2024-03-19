package synthesizer;

public interface BoundedQueue<T> {

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

}
