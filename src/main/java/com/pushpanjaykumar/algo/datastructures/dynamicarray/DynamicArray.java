package main.java.com.pushpanjaykumar.algo.datastructures.dynamicarray;

import java.util.Iterator;

/**
 * @author pushpanjay.kumar
 */
public class DynamicArray<T> implements Iterable<T> {

    private T[] arr;
    private int length = 0;
    private int capacity = 0;

    public DynamicArray() {
        this(16);
    }

    public DynamicArray(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity :" + capacity);
        }
        this.capacity = capacity;
        arr = (T[]) new Object[capacity];
    }

    public int size() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    private boolean isOutOfRange(int index) {
        return index >= length || index < 0;
    }

    public T get(int index) {
        if (isOutOfRange(index)) {
            throw new IndexOutOfBoundsException();
        }
        return arr[index];
    }

    public void set(int index, T elem) {
        if (isOutOfRange(index)) {
            throw new IndexOutOfBoundsException();
        }
        arr[index] = elem;
    }

    public void clear() {
        for (int i = 0; i < length; i++) {
            arr[i] = null;
        }
        length = 0;
    }

    public void add(T elem) {
        if (length == capacity) {
            if (capacity == 0) {
                capacity = 1;
            } else {
                capacity = 2 * capacity;
            }
            T[] tempArr = (T[]) new Object[capacity];
            for (int i = 0; i < length; i++) {
                tempArr[i] = arr[i];
            }
            arr = tempArr;
        }
        arr[length++] = elem;
    }

    public T removeAt(int rmIndex){
        if(isOutOfRange(rmIndex)){
            throw new IndexOutOfBoundsException();
        }
        T res = arr[rmIndex];
        T[] tempArr = (T[])new Object[length-1];
        for(int i=0, j=0; i<length; i++, j++){
            if(i==rmIndex){
                j--;
            } else {
                tempArr[j] = arr[i];
            }
        }
        arr = tempArr;
        length--;
        capacity=length;
        return res;
    }

    public int indexOf(T obj){
        for(int i=0;i<length;i++){
            if(obj == null){
                if(arr[i] == null){
                    return i;
                }
            } else{
                if(arr[i] == obj){
                    return i;
                }
            }
        }
        return -1;
    }

    public boolean remove(T obj){
        int index = indexOf(obj);
        if(index == -1)
            return false;
        removeAt(index);
        return true;
    }

    public boolean contains(T obj){
        return indexOf(obj)!=-1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index<length;
            }

            @Override
            public T next() {
                return arr[index++];
            }
        };
    }

    @Override
    public String toString() {
        if(length == 0){
            return "[]";
        } else{
            StringBuilder sb = new StringBuilder("[");
            for(int i=0;i< length;i++){
                sb.append(arr[i] + ", ");
            }
            return sb.append("]").toString();
        }
    }
}
