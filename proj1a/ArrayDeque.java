public class ArrayDeque<T> {

    private static final int INITIAL_CAPACITY = 8;
    private static final double RESIZE_UPPER_BOUNDARY = 0.8;
    private static final double RESIZE_UPPER_FACTOR = 2;
    private static final double RESIZE_LOWER_BOUNDARY = 0.3;
    private static final double RESIZE_LOWER_FACTOR = 0.5;

    private int head;
    private int tail;
    private T[] container;
    private int capacity;
    private int size;

    public ArrayDeque() {
        this.head = 0;
        this.tail = 1;
        this.capacity = INITIAL_CAPACITY;
        this.container = (T[]) new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    private int idx(int index) {
        return (index + this.capacity) % this.capacity;
    }

    private double loadFactor() {
        return (double) this.size / this.capacity;
    }

    private void resize(int caps) {
        T[] newContainer = (T[]) new Object[caps];
        int sz = 0;
        for (int iter = idx(this.head + 1); iter != idx(this.tail); iter = idx(iter + 1)) {
            newContainer[sz] = this.container[iter];
            sz += 1;
        }
        this.container = newContainer;
        this.capacity = caps;
        this.head = idx(-1);
        this.tail = idx(sz);
    }

    public void addFirst(T item) {
        if (this.loadFactor() > RESIZE_UPPER_BOUNDARY) {
            resize((int) Math.floor(RESIZE_UPPER_FACTOR * this.capacity));
        }
        this.container[idx(this.head)] = item;
        this.head = idx(this.head - 1);
        this.size += 1;
    }

    public void addLast(T item) {
        if (this.loadFactor() > RESIZE_UPPER_BOUNDARY) {
            resize((int) Math.floor(RESIZE_UPPER_FACTOR * this.capacity));
        }
        this.container[idx(this.tail)] = item;
        this.tail = idx(this.tail + 1);
        this.size += 1;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        int iter = idx(this.head + 1);
        while (iter != this.tail) {
            System.out.print(this.container[iter]);
            if (idx(iter + 1) != this.tail) {
                System.out.print(",");
            }
            iter = idx(iter + 1);
        }
    }

    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        T ret = this.container[idx(this.head + 1)];
        this.head = idx(this.head + 1);
        this.size -= 1;
        if (this.loadFactor() < RESIZE_LOWER_BOUNDARY) {
            resize((int) Math.floor(RESIZE_LOWER_FACTOR * this.capacity));
        }
        return ret;
    }

    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        T ret = this.container[idx(this.tail - 1)];
        this.tail = idx(this.tail - 1);
        this.size -= 1;
        if (this.loadFactor() < RESIZE_LOWER_BOUNDARY) {
            resize((int) Math.floor(RESIZE_LOWER_FACTOR * this.capacity));
        }
        return ret;
    }

    public T get(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        }
        return this.container[idx(this.head + index + 1)];
    }
}
