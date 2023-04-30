package problems.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UnionFind {
    
    private int[] vals;
    private int count;

    public UnionFind(int n) {
        vals = new int[n];
        for (int i = 0; i < n; i++) {
            vals[i] = i;
        }
        count = n;
    }

    void union(int p, int q) {
        boolean change = false;
        while (vals[p] != vals[q]) {
            int next = vals[p];
            vals[p] = vals[q];
            p = next;
            change = true;
        }

        if (change) {
            count--;
        }
    }

    int find (int p) {
        while (vals[p] != p) {
            int n = vals[p];
            vals[p] = vals[n];
            p = n;
        }
        return p;
    }

    boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    int count () {
        return count;
    }

    @Override
    public String toString() {
        return Arrays.toString(vals);
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(10);
        System.out.println(uf);

        uf.union(0, 3);
        System.out.println(uf);

        uf.union(3, 6);
        System.out.println(uf);

        // uf.union(0, 3);
        // System.out.println(uf);

        // uf.find(0);
        // System.out.println(uf);

        // System.out.println(uf.connected(0, 6));
        // System.out.println(uf);

        System.out.println(uf.connected(1, 7));
        System.out.println(uf);

        System.out.println(uf.connected(6, 0));
        System.out.println(uf);

        System.out.println(uf.count());
        System.out.println(uf);

        uf.union(0, 7);
        System.out.println(uf);

        System.out.println(uf.count());

        for (int i = 0; i < 10; i++) {
            System.out.println(uf.find(i));
        }
        System.out.println(uf);
        System.out.println(uf.count());

        uf.union(6, 9);
        uf.union(6, 9);
        uf.union(6, 9);

        System.out.println(uf);
        System.out.println(uf.count());

        for (int i = 0; i < 10; i++) {
            System.out.println(uf.find(i));
        }
    }

}
