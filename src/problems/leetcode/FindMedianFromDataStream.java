package problems.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/find-median-from-data-stream/
 */
public class FindMedianFromDataStream {

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(41);
        System.out.println(medianFinder.findMedian()); // 41

        medianFinder.addNum(35);
        System.out.println(medianFinder.findMedian()); // 38

        medianFinder.addNum(62);
        System.out.println(medianFinder.findMedian()); // 41

        medianFinder.addNum(4);
        System.out.println(medianFinder.findMedian()); // 38

        medianFinder.addNum(97);
        System.out.println(medianFinder.findMedian()); // 41

        medianFinder.addNum(108);
        System.out.println(medianFinder.findMedian()); // 51.5
    }

    private static class MedianFinder {

        // smaller half of the numbers
        private final PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        // greater half of the numbers
        private final PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        public MedianFinder() {
        }

        public void addNum(int num) {
            // maxHeap is allowed to store one more element than minHeap

            maxHeap.add(num);
            minHeap.add(maxHeap.poll());

            if (maxHeap.size() < minHeap.size()) {
                maxHeap.add(minHeap.poll());
            }
        }

        public double findMedian() {
            return maxHeap.size() > minHeap.size() ? (double) maxHeap.peek() : (maxHeap.peek() + minHeap.peek()) * 0.5;
        }

    }

}
