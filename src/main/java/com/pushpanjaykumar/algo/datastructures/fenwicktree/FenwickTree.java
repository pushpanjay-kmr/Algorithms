package main.java.com.pushpanjaykumar.algo.datastructures.fenwicktree;

import java.util.Arrays;

/**
 * @author pushpanjay.kumar
 */
public class FenwickTree {
    private final int N;
    private long[] tree;

    public FenwickTree(int size) {
        this.tree = new long[(N = size+1)];
    }

    // Constraint: values array should be one based
    public FenwickTree(long[] values) {
        if(values == null){
            throw new IllegalArgumentException("");
        }
        N = values.length;
        values[0] = 0L;

        this.tree = values.clone();

        for(int i=1; i<N; i++){
            int parent = i + lsb(i);
            if(parent<N) {
                tree[parent] += tree[i];
            }
        }
    }

    private int lsb(int i){
        // -i (basically is 2's complement of i)
        return i & -i;
    }

    private long prefixSum(int i){
        long sum = 0L;
        while(i>0){
            sum+= tree[i];
            i = i - lsb(i);  // Equivalent:
        }
        return sum;
    }

    public long sum(int l, int r){
        if(r < l){
            throw new IllegalArgumentException("Make sure right >= left");
        }
        return prefixSum(r) - prefixSum(l-1);
    }

    public void add(int i, long v){
        if(i<1 || i>=N){
            throw new IllegalArgumentException("Illegal Index");
        }
        while(i<N){
            tree[i] += v;
            i = i+lsb(i);
        }
    }

    public void set(int i, long v){
        add(i, v - sum(i, i));
    }

    @Override
    public String toString() {
        return Arrays.toString(tree);
    }
}
