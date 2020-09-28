package main.java.com.pushpanjaykumar.algo.datastructures.priorityqueue;

import java.util.PriorityQueue;

/**
 * @author pushpanjay.kumar
 */
public class JavaPriorityQueueDemo {
    public static void main(String[] args) {
        PriorityQueue<Integer> pQ = new PriorityQueue<Integer>();
        pQ.add(10);
        pQ.add(20);
        pQ.add(30);
        pQ.add(5);

        System.out.println(pQ.peek());
        System.out.println(pQ.poll());
        System.out.println(pQ.peek());
    }

}
