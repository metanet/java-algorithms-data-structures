package problems.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.com/problems/implement-stack-using-queues/submissions/
public class ImplementStackUsingQueues {

    private static class MyStack {

        // push 1
        // push 2
        // push 3
        // peek -> 3
        // pop 3
        // push 4
        // peek -> 4
    
        // 1 -> 2 -> 3 -> 4
    
        private Deque<Integer> queue1 = new ArrayDeque<>();
        private Deque<Integer> queue2 = new ArrayDeque<>();
    
        public MyStack() {
            
        }
        
        public void push(int x) {
            queue1.addLast(x);
        }
        
        public int pop() {
            while (queue1.size() > 1) {
                queue2.addLast(queue1.removeFirst());
            }
    
            int i = queue1.removeFirst();
            Deque<Integer> temp = queue1;
            queue1 = queue2;
            queue2 = temp;
            return i;
        }
        
        public int top() {
            int i = pop();
            push(i);
            return i;
        }
        
        public boolean empty() {
            return queue1.isEmpty();
        }
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        System.out.println(stack.pop());
        System.out.println(stack.empty());
    }
    
}
