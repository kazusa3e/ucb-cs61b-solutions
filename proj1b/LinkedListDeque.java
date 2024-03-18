public class LinkedListDeque<T> implements Deque<T> {

    private static class LinkedListNode<T> {
        private T data;
        private LinkedListNode<T> prev;
        private LinkedListNode<T> next;
    }

    private int size;
    private LinkedListNode<T> sentinel;

    public LinkedListDeque() {
        this.size = 0;
        this.sentinel = new LinkedListNode<>();
        this.sentinel.data = null;
        this.sentinel.prev = this.sentinel;
        this.sentinel.next = this.sentinel;
    }

    @Override
    public void addFirst(T item) {
        LinkedListNode<T> node = new LinkedListNode<>();
        node.data = item;
        this.sentinel.next.prev = node;
        node.next = this.sentinel.next;
        node.prev = this.sentinel;
        this.sentinel.next = node;
        this.size += 1;
    }

    @Override
    public void addLast(T item) {
        LinkedListNode<T> node = new LinkedListNode<>();
        node.data = item;
        this.sentinel.prev.next = node;
        node.prev = this.sentinel.prev;
        node.next = this.sentinel;
        this.sentinel.prev = node;
        this.size += 1;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {
        LinkedListNode<T> iter = this.sentinel.next;
        while (iter != this.sentinel) {
            System.out.print(iter.data);
            if (iter.next != this.sentinel) {
                System.out.print(",");
            }
            iter = iter.next;
        }
    }

    @Override
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        T ret = this.sentinel.next.data;
        this.sentinel.next = this.sentinel.next.next;
        this.sentinel.next.prev = this.sentinel;
        this.size -= 1;
        return ret;
    }

    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        T ret = this.sentinel.prev.data;
        this.sentinel.prev = this.sentinel.prev.prev;
        this.sentinel.prev.next = this.sentinel;
        this.size -= 1;
        return ret;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        }
        LinkedListNode<T> iter = this.sentinel.next;
        int idx = 0;
        while (iter != this.sentinel) {
            if (idx == index) {
                return iter.data;
            }
            iter = iter.next;
            idx += 1;
        }
        return null;
    }

    private T getRecursive(int index, LinkedListNode<T> iter) {
        if (index == 0) {
            return iter.data;
        }
        return getRecursive(index - 1, iter.next);
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        }
        return getRecursive(index, this.sentinel.next);
    }

}
