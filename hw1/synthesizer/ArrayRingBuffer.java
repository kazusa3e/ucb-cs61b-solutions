package synthesizer;

import java.util.Iterator;

import javax.management.RuntimeErrorException;

// Make sure to make this class and all of its methods public
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {

    /* Index for the next dequeue or peek. */
    private int first; // index for the next dequeue or peek

    /* Index for the next enqueue. */
    private int last;

    /* Array for storing the buffer data. */
    private T[] rb;

    private int idx(int index) {
        return (index + this.capacity) % this.capacity;
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // Create new array with capacity elements.
        // first, last, and fillCount should all be set to 0.
        // this.capacity should be set appropriately. Note that the local variable
        // here shadows the field we inherit from AbstractBoundedQueue, so
        // you'll need to use this.capacity to set the capacity.
        this.rb = (T[]) new Object[capacity];
        this.first = 0;
        this.last = 0;
        this.fillCount = 0;
        this.capacity = capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        // Enqueue the item. Don't forget to increase fillCount and update last.
        if (this.isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        this.rb[this.last] = x;
        this.last = idx(this.last + 1);
        this.fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        // Dequeue the first item. Don't forget to decrease fillCount and update
        if (this.isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T ret = this.rb[this.first];
        this.first = idx(this.first + 1);
        this.fillCount -= 1;
        return ret;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // Return the first item. None of your instance variables should change.
        if (this.isEmpty()) {
            throw new RuntimeException("Ring buffer is empty");
        }
        return this.rb[this.first];
    }

    private class ArrayRingBufferIterator implements Iterator<T> {

        private int iter;

        public ArrayRingBufferIterator() {
            this.iter = ArrayRingBuffer.this.first;
        }

        @Override
        public boolean hasNext() {
            return this.iter != ArrayRingBuffer.this.last;
        }

        @Override
        public T next() {
            T ret = ArrayRingBuffer.this.rb[this.iter];
            this.iter = (this.iter + 1 + ArrayRingBuffer.this.capacity)
                    % ArrayRingBuffer.this.capacity;
            return ret;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }
}
