package main.java.com.pushpanjaykumar.algo.datastructures.queue;

/**
 * @author pushpanjay.kumar
 */
public interface Queue<T> {
    void offer(T elem);

    T poll();

    T peek();

    int size();

    boolean isEmpty();
}
