package problems.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/min-stack/
 */
public class MinStack {

    private static class Item {
        final int val;
        final int min;

        Item(int val, int min) {
            this.val = val;
            this.min = min;
        }
    }

    private final Deque<Item> stack = new ArrayDeque<>();

    public MinStack() {
    }

    public void push(int x) {
        int min = stack.isEmpty() ? x : Math.min(x, stack.peekLast().min);
        stack.addLast(new Item(x, min));
    }

    public void pop() {
        stack.removeLast();
    }

    public int top() {
        return stack.peekLast().val;
    }

    public int getMin() {
        return stack.peekLast().min;
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin();
        System.out.println(minStack.getMin()); // Returns -3
        minStack.pop();
        System.out.println(minStack.top()); // Returns 0
        minStack.pop();
        System.out.println(minStack.getMin()); // Returns -2
    }

}
