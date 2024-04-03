package lab9;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Queue;

/**
 * A hash table-backed Map implementation. Provides amortized constant time
 * access to elements via get(), remove(), and put() in the best case.
 *
 * @author Your name here
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int DEFAULT_SIZE = 16;
    private static final double MAX_LF = 0.75;

    private ArrayMap<K, V>[] buckets;
    private int size;

    private int loadFactor() {
        return size / buckets.length;
    }

    public MyHashMap() {
        buckets = new ArrayMap[DEFAULT_SIZE];
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.buckets.length; i += 1) {
            this.buckets[i] = new ArrayMap<>();
        }
    }

    /**
     * Computes the hash function of the given key. Consists of
     * computing the hashcode, followed by modding by the number of buckets.
     * To handle negative numbers properly, uses floorMod instead of %.
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }

        int numBuckets = buckets.length;
        return Math.floorMod(key.hashCode(), numBuckets);
    }

    /*
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (key == null) {
            return null;
        }
        return this.buckets[hash(key)].get(key);
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        ArrayMap<K, V> bucket = this.buckets[hash(key)];
        if (bucket.containsKey(key)) {
            bucket.put(key, value);
            return;
        }
        bucket.put(key, value);
        this.size += 1;
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
        for (int ix = 0; ix != this.buckets.length; ++ix) {
            for (K key : this.buckets[ix].keySet()) {
                ret.add(key);
            }
        }
        return ret;
    }

    /*
     * Removes the mapping for the specified key from this map if exists.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key) {
        return this.buckets[hash(key)].remove(key);
    }

    /*
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for this lab. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value) {
        return this.buckets[hash(key)].remove(key, value);
    }

    private class MyHashMapIterator implements Iterator<K> {

        private int bucketIdx;
        private Queue<K> bucketKeysets;

        public MyHashMapIterator() {
            this.bucketIdx = 0;
            this.bucketKeysets = new Queue<>();
            for (K key : MyHashMap.this.buckets[this.bucketIdx].keySet()) {
                this.bucketKeysets.enqueue(key);
            }
        }

        @Override
        public boolean hasNext() {
            if (!this.bucketKeysets.isEmpty()) {
                return true;
            }
            while (this.bucketKeysets.isEmpty() && this.bucketIdx != MyHashMap.this.buckets.length) {
                this.bucketIdx += 1;
                for (K key : MyHashMap.this.buckets[this.bucketIdx].keySet()) {
                    this.bucketKeysets.enqueue(key);
                }
            }
            if (this.bucketIdx == MyHashMap.this.buckets.length) {
                return false;
            }
            if (!this.bucketKeysets.isEmpty()) {
                return true;
            }
            return false;
        }

        @Override
        public K next() {
            while (this.bucketKeysets.isEmpty() && this.bucketIdx != MyHashMap.this.buckets.length) {
                this.bucketIdx += 1;
                for (K key : MyHashMap.this.buckets[this.bucketIdx].keySet()) {
                    this.bucketKeysets.enqueue(key);
                }
            }
            if (!this.bucketKeysets.isEmpty()) {
                return this.bucketKeysets.dequeue();
            }
            return null;
        }

    }

    @Override
    public Iterator<K> iterator() {
        return new MyHashMapIterator();
    }
}
