package problems.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * https://leetcode.com/problems/flatten-nested-list-iterator/
 */
public class FlattenNestedListIterator {

    /**
     * Input: [[1,[2, 3]],4,[5,6]]
     * Output: [1,1,2,1,1]
     * <p>
     * stack: [5, 6], 4, [1, [2, 3]]
     * stack.pop()
     * stack.push() [2, 3], 1
     * stack: [5, 6], 4, [2, 3], 1
     */

    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        List<NestedInteger> getList();
    }

    private static class NestedIterator implements Iterator<Integer> {

        private final Deque<NestedInteger> stack = new ArrayDeque<>();

        public NestedIterator(List<NestedInteger> nestedList) {
            push(nestedList);
        }

        private void push(List<NestedInteger> nestedList) {
            for (int i = nestedList.size() - 1; i >= 0; i--) {
                NestedInteger nestedInteger = nestedList.get(i);
                if (nestedInteger.isInteger()) {
                    stack.addLast(nestedInteger);
                } else {
                    push(nestedInteger.getList());
                }
            }
        }

        @Override
        public Integer next() {
            if (stack.isEmpty()) {
                throw new NoSuchElementException();
            }

            NestedInteger nestedInteger = stack.removeLast();
            if (nestedInteger.isInteger()) {
                return nestedInteger.getInteger();
            }

            push(nestedInteger.getList());
            return next();
        }

        @Override
        public boolean hasNext() {
            return stack.size() > 0;
        }
    }

}
