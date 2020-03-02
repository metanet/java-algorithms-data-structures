package problems.leetcode;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * https://leetcode.com/problems/zigzag-iterator/
 */
public class ZigzagIterator {

    private Iterator<Integer> it1, it2, it;

    public ZigzagIterator(List<Integer> l1, List<Integer> l2) {
        it1 = l1.iterator();
        it2 = l2.iterator();
        it = it1;
    }

    public int next() {
        if (!it.hasNext()) {
            zigzag();
        }

        int v = it.next();
        zigzag();
        return v;
    }

    public boolean hasNext() {
        if (it.hasNext()) {
            return true;
        }

        zigzag();
        return it.hasNext();
    }

    private void zigzag() {
        it = it == it1 ? it2 : it1;
    }

    public static void main(String[] args) {
        List<Integer> l1 = Arrays.asList(1, 2);
        List<Integer> l2 = Arrays.asList(4, 5, 6);

        ZigzagIterator it = new ZigzagIterator(l1, l2);
        System.out.println(it.hasNext());
        System.out.println(it.next());
        System.out.println(it.hasNext());
        System.out.println(it.next());
        System.out.println(it.hasNext());
        System.out.println(it.next());
        System.out.println(it.hasNext());
        System.out.println(it.next());
        System.out.println(it.hasNext());
        System.out.println(it.next());
        System.out.println(it.hasNext());
//        System.out.println(it.next());
//        System.out.println(it.hasNext());
    }

}
