package lab9;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Queue;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root; /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns the value mapped to by KEY in the subtree rooted in P.
     * or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (key.compareTo(p.key) < 0 && p.left != null) {
            return getHelper(key, p.left);
        }
        if (key.compareTo(p.key) > 0 && p.right != null) {
            return getHelper(key, p.right);
        }
        if (key.compareTo(p.key) == 0) {
            return p.value;
        }
        return null;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (this.root == null) {
            return null;
        }
        return this.getHelper(key, this.root);
    }

    /**
     * Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
     * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            this.size += 1;
            Node node = new Node(key, value);
            return node;
        }
        if (key.compareTo(p.key) == 0) {
            p.value = value;
            return p;
        }
        if (key.compareTo(p.key) < 0) {
            p.left = putHelper(key, value, p.left);
            return p;
        }
        if (key.compareTo(p.key) > 0) {
            p.right = putHelper(key, value, p.right);
            return p;
        }
        return null;
    }

    /**
     * Inserts the key KEY
     * If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        this.root = putHelper(key, value, this.root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return this.size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> ret = new TreeSet<>();
        if (this.root == null) {
            return ret;
        }
        Queue<Node> queue = new Queue<>();
        queue.enqueue(this.root);
        while (!queue.isEmpty()) {
            Node n = queue.dequeue();
            if (n.left != null) {
                queue.enqueue(n.left);
            }
            if (n.right != null) {
                queue.enqueue(n.right);
            }
            ret.add(n.key);
        }
        return ret;
    }

    private Node removeMax(Node p) {
        if (p == null) {
            return null;
        }
        if (p.right == null) {
            return p.left;
        }
        p.right = removeMax(p.right);
        return p;
    }

    private Node max(Node p) {
        if (p == null) return null;
        if (p.right == null) return p;
        return max(p.right);
    }

    private Node removeHelper(K key, Node p) {
        if (p == null) {
            return null;
        }
        if (key.compareTo(p.key) < 0 && p.left != null) {
            p.left = removeHelper(key, p.left);
            return p;
        }
        if (key.compareTo(p.key) > 0 && p.right != null) {
            p.right = removeHelper(key, p.right);
            return p;
        }
        if (key.compareTo(p.key) == 0) {
            if (p.left == null) {
                return p.right;
            }
            if (p.right == null) {
                return p.left;
            }
            Node n = max(p.left);
            n.left = removeMax(p.left);
            n.right = p.right;
            return n;
        }
        return null;
    }

    /**
     * Removes KEY from the tree if present
     * returns VALUE removed,
     * null on failed removal.
     */
    @Override
    public V remove(K key) {
        V ret = this.get(key);
        if (ret != null) {
            this.root = removeHelper(key, this.root);
            this.size -= 1;
        }
        return ret;
    }

    private Node removeHelper(K key, V val, Node p) {
        if (p == null) return null;
        if (key.compareTo(p.key) < 0 && p.left != null) {
            p.left = removeHelper(key, val, p.left);
        }
        if (key.compareTo(p.key) > 0 && p.right != null) {
            p.right = removeHelper(key, val, p.right);
        }
        if (key.compareTo(p.key) == 0 && val.equals(p.value)) {
            if (p.left == null) return p.right;
            if (p.right == null) return p.left;
            Node n = max(p.left);
            n.left = removeMax(p.left);
            n.right = p.right;
            return n;
        }
        return null;
    }

    /**
     * Removes the key-value entry for the specified key only if it is
     * currently mapped to the specified value. Returns the VALUE removed,
     * null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        V ret = this.get(key);
        if (ret != null && ret.equals(value)) {
            this.root = removeHelper(key, value, this.root);
            this.size -= 1;
        }
        return ret;
    }

    private class BSTMapIterator implements Iterator<K> {

        private Queue<Node> queue;

        public BSTMapIterator() {
            this.queue = new Queue<>();
            if (BSTMap.this.root != null) {
                this.queue.enqueue(BSTMap.this.root);
            }
        }

        @Override
        public boolean hasNext() {
            return !this.queue.isEmpty();
        }

        @Override
        public K next() {
            Node n = this.queue.dequeue();
            if (n.left != null) {
                this.queue.enqueue(n.left);
            }
            if (n.right != null) {
                this.queue.enqueue(n.right);
            }
            return n.key;
        }

    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }
}
