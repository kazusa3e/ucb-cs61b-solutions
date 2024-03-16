public class ArrayDeque<T> {

    private static final int INITIAL_CAPACITY = 8;

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

    private int _idx(int index) {
        return (index + this.capacity) % this.capacity;
    }

    public void addFirst(T item) {
        this.container[_idx(this.head)] = item;
        this.head = _idx(this.head - 1);
        this.size += 1;
    }

    public void addLast(T item) {
        this.container[_idx(this.tail)] = item;
        this.tail = _idx(this.tail + 1);
        this.size += 1;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        int iter = _idx(this.head + 1);
        while (iter != this.tail) {
            System.out.print(this.container[iter]);
            if (_idx(iter + 1) != this.tail) {
                System.out.print(",");
            }
            iter = _idx(iter + 1);
        }
    }

    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        T ret = this.container[_idx(this.head + 1)];
        this.head = _idx(this.head + 1);
        this.size -= 1;
        return ret;
    }

    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        T ret = this.container[_idx(this.tail - 1)];
        this.tail = _idx(this.tail - 1);
        this.size -= 1;
        return ret;
    }

    public T get(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        }
        return this.container[_idx(this.head + index + 1)];
    }
}
