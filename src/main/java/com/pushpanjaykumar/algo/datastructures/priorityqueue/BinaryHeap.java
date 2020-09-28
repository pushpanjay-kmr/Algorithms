package main.java.com.pushpanjaykumar.algo.datastructures.priorityqueue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author pushpanjay.kumar
 */
public class BinaryHeap<T extends Comparable<T>> {
    private List<T> heap = null;

    public BinaryHeap() {
        this(1);
    }

    public BinaryHeap(int capacity) {
        this.heap = new ArrayList<>(capacity);
    }

    public BinaryHeap(T[] elem) {
        int heapSize = elem.length;
        this.heap = new ArrayList<>(heapSize);
        for (int i = 0; i < heapSize; i++) {
            heap.add(elem[i]);
        }

        for (int i = Math.max(0, (heapSize / 2) - 1); i >= 0; i--) {
            sink(i);
        }
    }

    public BinaryHeap(Collection<T> elems) {
        int heapSize = elems.size();
        this.heap = new ArrayList<>(heapSize);

        heap.addAll(elems);

        for (int i = Math.max(0, (heapSize / 2) - 1); i >= 0; i--) {
            sink(i);
        }
    }

    private void sink(int i) {
        int smallest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < heap.size() && less(l, smallest)) {
            smallest = l;
        }

        if (r < heap.size() && less(r, smallest)) {
            smallest = r;
        }

        if (smallest != i) {
            swap(smallest, i);
            sink(smallest);
        }
    }

    private void swim(int i){
        int p = (i-1)/2;
        while(i>0 && less(i, p)) {
            swap(i, p);
            i = p;
            p = (i-1)/2;
        }
    }

    private void swap(int i, int j) {
        T valI = heap.get(i);
        T valJ = heap.get(j);
        heap.set(i, valJ);
        heap.set(j, valI);
    }

    private boolean less(int i, int j) {
        return heap.get(i).compareTo(heap.get(j)) <= 0;
    }

    public int size() {
        return heap.size();
    }

    public boolean iSEmpty() {
        return size() == 0;
    }

    public void clear() {
        this.heap.clear();
    }

    public T peek() {
        if (iSEmpty()) return null;
        return heap.get(0);
    }

    public T poll() {
        return removeAt(0);
    }

    public boolean contains(T elem){
        for(int i=0;i<heap.size();i++){
            if(heap.get(i).equals(elem))
                return true;
        }
        return false;
    }

    public void add(T elem){
        if(elem == null) throw new IllegalArgumentException("Entered element should be comparable");
        heap.add(elem);
        swim(heap.size()-1);
    }

    public boolean remove(T elem){
        if(elem == null) return false;
        for(int i=0;i<size();i++){
            if(elem.equals(heap.get(i))){
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    private T removeAt(int i) {
        if (iSEmpty() || heap.size() <= i) {
            throw new IllegalArgumentException();
        }

        T removedData = heap.get(i);
        int lastIndex = heap.size() - 1;
        swap(i, lastIndex);

        heap.remove(lastIndex);
        if (i == lastIndex) {
            return removedData;
        }

        T elem = heap.get(i);
        sink(i);

        if (heap.get(i).equals(elem)) {
            swim(i);
        }
        return removedData;
    }

    public boolean isMeanHeap(int k){
        int heapSize = size();
        if(k>=heapSize) return true;

        int l = 2*k +1;
        int r = 2*k +2;

        if(l<heapSize && !less(k, l)) return false;
        if(r<heapSize && !less(k, r)) return false;

        return isMeanHeap(l) && isMeanHeap(r);
    }

}
