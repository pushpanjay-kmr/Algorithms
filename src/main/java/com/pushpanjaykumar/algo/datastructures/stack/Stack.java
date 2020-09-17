package java.com.pushpanjaykumar.algo.datastructures.stack;

/**
 * @author pushpanjay.kumar
 */
public interface Stack<T> {
    int size();

    boolean isEmpty();

    void push(T elem);

    T pop();

    T peek();
}
