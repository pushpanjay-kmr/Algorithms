package main.java.com.pushpanjaykumar.algo.datastructures.hashtable;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author pushpanjay.kumar
 */

class Entry<K, V> {
    int hash;
    K key;
    V value;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
        this.hash = key.hashCode();
    }

    public boolean equals(Entry<K, V> other) {
        if (this.hash != other.hash) {
            return false;
        }
        return key.equals(other.key);
    }

    @Override
    public String toString() {
        return key + " => " + value;
    }
}

public class HashTableSeparateChaining<K, V> implements Iterable<K>{
    private static final int DEFAULT_CAPACITY = 3;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private double maxLoadFactor;
    private int capacity, threshold, size=0;
    private LinkedList<Entry<K, V>> [] table;

    public HashTableSeparateChaining() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HashTableSeparateChaining(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    public HashTableSeparateChaining(int capacity, double maxLoadFactor) {
        if(capacity<0){
            throw new IllegalArgumentException("Illegal capacity");
        }
        if(maxLoadFactor<=0 || Double.isNaN(maxLoadFactor) || Double.isInfinite(maxLoadFactor)){
            throw new IllegalArgumentException("Illegal maxLoadFactor");
        }
        this.maxLoadFactor = maxLoadFactor;
        this.capacity = Math.max(capacity, DEFAULT_CAPACITY);
        threshold = (int) (this.capacity * this.maxLoadFactor);
        table = new LinkedList[capacity];
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    // Converts a hash value to an index.
    // Removes negative sign and places the hash value in the domain [0, capacity)
    private int normalizeIndex(int keyHash){
        return (keyHash & 0x7FFFFFFF) % capacity;
    }

    public void clear(){
        Arrays.fill(table, null);
        size = 0;
    }



    public boolean containsKey(K key){
        return hasKey(key);
    }

    public boolean hasKey(K key){
        int bucketIndex = normalizeIndex(key.hashCode());
        return bucketSeekEntry(bucketIndex, key)!=null;
    }

    public V put(K key, V value){
        return insert(key, value);
    }

    public V add(K key, V value){
        return insert(key, value);
    }

    public V insert(K key, V value){
        if(key == null) throw new IllegalArgumentException("Null Key");
        Entry<K, V> entry = new Entry<>(key, value);
        int bucketIndex = normalizeIndex(entry.hash);
        return bucketInsertEntry(bucketIndex, entry);
    }

    public V get(K key){
        if(key == null) throw new IllegalArgumentException("Null key");
        int bucketIndex = normalizeIndex(key.hashCode());
        Entry<K, V> entry = bucketSeekEntry(bucketIndex, key);
        if(entry!=null){
            return entry.value;
        }
        return null;
    }

    public V remove(K key){
        if(key == null) throw new IllegalArgumentException("Null key");
        int bucketIndex = normalizeIndex(key.hashCode());
        return bucketRemoveEntry(bucketIndex, key);
    }

    private V bucketRemoveEntry(int bucketIndex, K key){
        Entry<K, V> entry = bucketSeekEntry(bucketIndex, key);
        if(entry!=null){
            LinkedList<Entry<K, V>> bucket = table[bucketIndex];
            bucket.remove(entry);
            --size;
            return entry.value;
        }
        return null;
    }

    // return old value stored for key, null if key doesn't exist earlier
    private V bucketInsertEntry(int bucketIndex, Entry<K, V> entry) {
        LinkedList<Entry<K, V>> bucket = table[bucketIndex];
        if(bucket == null){
            table[bucketIndex] = bucket = new LinkedList<>();
        }

        Entry<K, V> existingEntry = bucketSeekEntry(bucketIndex, entry.key);
        if(existingEntry == null){
            bucket.add(entry);
            if(++size > threshold){
                resizeTable();
            }
            return null;
        } else{
            V oldVal = existingEntry.value;
            existingEntry.value = entry.value;
            return oldVal;
        }
    }

    private Entry<K, V> bucketSeekEntry(int bucketIndex, K key){
        if(key == null || table[bucketIndex] == null){
            return null;
        }
        LinkedList<Entry<K, V>> bucket = table[bucketIndex];
        for(Entry<K, V> entry : bucket){
            if(entry.key.equals(key)){
                return entry;
            }
        }
        return null;
    }

    private void resizeTable(){
        capacity = 2*capacity;
        threshold = (int)(capacity * maxLoadFactor);

        LinkedList<Entry<K, V>> [] newTable = new LinkedList[capacity];
        for(int i=0;i<table.length;i++){
            if(table[i]!=null){
                for(Entry<K, V> entry : table[i]){
                    int bucketIndex = normalizeIndex(entry.hash);
                    LinkedList<Entry<K, V>> bucket = newTable[bucketIndex];
                    if(bucket == null){
                        newTable[bucketIndex] = bucket = new LinkedList<>();
                    }
                    bucket.add(entry);
                }
                table[i].clear();
                table[i] = null;
            }
        }

        table = newTable;
    }


    public List<K> keys(){
        List<K> keys = new ArrayList<>(size);
        for(int i=0;i<table.length;i++){
            if(table[i]!=null){
                keys.addAll(table[i].stream().map(e->e.key).collect(Collectors.toList()));
            }
        }
        return keys;
    }

    public List<V> values(){
        List<V> values = new ArrayList<>(size);
        for(int i=0;i<table.length;i++){
            if(table[i]!=null){
                values.addAll(table[i].stream().map(e->e.value).collect(Collectors.toList()));
            }
        }
        return values;
    }

    //todo later
    @Override
    public Iterator<K> iterator() {
        final int elemtnCount = size();
        return new Iterator<K>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public K next() {
                return null;
            }
        };
    }
}
