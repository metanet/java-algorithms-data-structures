package problems.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/sliding-window-maximum/
 */
public class SlidingWindowMaximum {

    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        System.out.println(Arrays.toString(maxSlidingWindow(nums, k)));
        System.out.println(Arrays.toString(maxSlidingWindowDeque(nums, k)));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (k > nums.length) {
            throw new IllegalArgumentException();
        } else if (k == 0) {
            return new int[0];
        } else if (k == 1) {
            return nums;
        }

        NavigableMap<Integer, Integer> heap = new TreeMap<>(Comparator.reverseOrder());
        for (int i = 0; i < k; i++) {
            heap.merge(nums[i], 1, Integer::sum);
        }

        int n = nums.length;
        int[] windowMaxes = new int[n - k + 1];

        for (int i = k; i < nums.length; i++) {
            windowMaxes[i - k] = heap.firstKey();
            int numToRemove = nums[i - k];
            int count = heap.get(numToRemove);
            if (count == 1) {
                heap.remove(numToRemove);
            } else {
                heap.put(numToRemove, count - 1);
            }
            heap.merge(nums[i], 1, Integer::sum);
        }

        windowMaxes[windowMaxes.length - 1] = heap.firstKey();

        return windowMaxes;
    }


    private static void cleanDeque(Deque<Integer> deque, int[] nums, int i, int k) {
        // remove indexes of elements not from sliding window
        if (!deque.isEmpty() && deque.getFirst() == i - k) {
            deque.removeFirst();
        }

        // remove from deq indexes of all elements
        // which are smaller than current element nums[i]
        while (!deque.isEmpty() && nums[i] > nums[deque.getLast()]) {
            deque.removeLast();
        }
    }

    public static int[] maxSlidingWindowDeque(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) {
            return new int[0];
        } else if (k == 1) {
            return nums;
        }

        Deque<Integer> deque = new ArrayDeque<>();
        int maxIndex = 0;
        for (int i = 0; i < k; i++) {
            cleanDeque(deque, nums, i, k);
            deque.addLast(i);
            // compute max in nums[:k]
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        int[] windowMaxes = new int[n - k + 1];
        windowMaxes[0] = nums[maxIndex];

        for (int i = k; i < n; i++) {
            cleanDeque(deque, nums, i, k);
            deque.addLast(i);
            windowMaxes[i - k + 1] = nums[deque.getFirst()];
        }

        return windowMaxes;
    }

}
