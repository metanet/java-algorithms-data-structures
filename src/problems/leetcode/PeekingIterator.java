package problems.leetcode;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * https://leetcode.com/problems/peeking-iterator/
 */
public class PeekingIterator implements Iterator<Integer> {

    private final Iterator<Integer> iterator;
    private Integer peeked;

    public PeekingIterator(Iterator<Integer> iterator) {
        this.iterator = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (peeked == null) {
            peeked = iterator.next();
        }

        return peeked;
    }

    @Override
    public boolean hasNext() {
        return peeked != null || iterator.hasNext();
    }

    @Override
    public Integer next() {
        if (peeked != null) {
            Integer p = peeked;
            peeked = null;
            return p;
        }

        return iterator.next();
    }


    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        PeekingIterator it = new PeekingIterator(list.iterator());
        System.out.println(it.peek());
        System.out.println(it.next());

    }


}
