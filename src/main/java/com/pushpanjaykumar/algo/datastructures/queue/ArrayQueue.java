package main.java.com.pushpanjaykumar.algo.datastructures.queue;

/**
 * @author pushpanjay.kumar
 */
//todo : pick later
public class ArrayQueue<T> implements Queue<T> {
    private T[] arr;
    private int front;
    private int rear;

    public ArrayQueue(int capacity) {
        // ArrayQueue maximum size is data.length - 1.
        arr = (T[]) new Object[capacity+1];
        int front=0;
        int rear=0;
    }

    //todo: check this
    public boolean isFull(){
       return (arr.length + front - rear)%arr.length == 1;
    }

    private int adjustIndex(int index, int size) {
        return index >= size ? index - size : index;
    }

    @Override
    public void offer(T elem) {
        if (isFull()) {
            throw new RuntimeException("Queue is full");
        }
        arr[rear++] = elem;
        rear = adjustIndex(rear, arr.length);
    }

    @Override
    public T poll() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
