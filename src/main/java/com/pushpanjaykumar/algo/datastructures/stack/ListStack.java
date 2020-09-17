package main.java.com.pushpanjaykumar.algo.datastructures.stack;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author pushpanjay.kumar
 */
public class ListStack<T> implements Iterable<T> ,Stack<T>{
    //Linkedlist internally uses doubly linked list
    private LinkedList<T> list = new LinkedList<>();

    public ListStack() {
    }

    public ListStack(T firstElem) {
        push(firstElem);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    @Override
    public void push(T elem) {
        list.addLast(elem);
    }

    @Override
    public T pop() {
        if(isEmpty()){
            throw new EmptyStackException();
        }
        return list.removeLast();
    }

    @Override
    public T peek() {
        if(isEmpty()){
            throw new EmptyStackException();
        }
        return list.peekLast();
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
