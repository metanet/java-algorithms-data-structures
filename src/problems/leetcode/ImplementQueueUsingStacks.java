package problems.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/implement-queue-using-stacks/
 */
public class ImplementQueueUsingStacks {

    private static class MyQueue {
        private Deque<Integer> stack1 = new ArrayDeque<>();
        private Deque<Integer> stack2 = new ArrayDeque<>();

        /**
         * Initialize your data structure here.
         */
        public MyQueue() {

        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            stack1.addLast(x);
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            if (stack2.size() == 0) {
                while (stack1.size() > 0) {
                    stack2.addLast(stack1.removeLast());
                }
            }

            return stack2.removeLast();
        }

        /**
         * Get the front element.
         */
        public int peek() {
            if (stack2.size() == 0) {
                while (stack1.size() > 0) {
                    stack2.addLast(stack1.removeLast());
                }
            }

            return stack2.peekLast();
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.push(5);
        queue.push(6);
        queue.push(7);
        System.out.println(queue.pop());
        queue.push(8);
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
    }

}
