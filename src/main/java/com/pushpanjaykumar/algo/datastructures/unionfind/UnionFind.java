package main.java.com.pushpanjaykumar.algo.datastructures.unionfind;

/**
 * @author pushpanjay.kumar
 */
public class UnionFind {
    //number of elements in union find
    private int size;
    //used to track size of each component
    private int[] sz;
    //id[i] points to parent of i
    // if (id[i] == i) => i is a root node
    private int[] id;
    //used to track number of components
    private int numComponents;

    public UnionFind(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size of union find cannot be less than equal to 0");
        }
        this.size = size;
        this.numComponents = size;
        this.sz = new int[size];
        this.id = new int[size];

        for (int i = 0; i < size; i++) {
            id[i] = i; // link to self, initially self-root
            sz[i] = 1; // since self-root, so size of each component in beg is 1
        }
    }

    /**
     * Method to find node p belongs to which component
     * TC: Amortized constant time (alpha(n))
     *
     * @param p node
     * @return root node of component, to which p node belongs to
     */
    public int find(int p) {

        // find root of p node
        int root = p;
        while (root != id[root]) {
            root = id[root];
        }

        // path compression algo
        while (p != root) {
            int next = id[p];
            id[p] = root;
            p = next;
        }

        return root;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int componentSize(int p) {
        return sz[find(p)];
    }

    public int size() {
        return size;
    }

    public int components() {
        return numComponents;
    }

    /**
     * Unify the components containing nodes p and q
     *
     * @param p node/element
     * @param q node/element
     */
    public void unify(int p, int q) {
        int root1 = find(p);
        int root2 = find(q);

        //if both nodes are already in same component
        if (root1 == root2) {
            return;
        }

        if (sz[root1] < sz[root2]) {
            sz[root2] += sz[root1];
            id[root1] = root2;
        } else {
            sz[root1] += sz[root2];
            id[root2] = root1;
        }

        numComponents--;
    }
}
