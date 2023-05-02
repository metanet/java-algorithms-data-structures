package problems.leetcode;

import java.util.Arrays;

public class HeapSort {
    
    /**
     * runtime: O(NlgN)
     * space: O(1)
     * 
     * HEAPSORT(A)
     *   BUILD-MAX-HEAP(A)
     *   for i = A.length..2
     *     exch A[1], A[i]
     *     A.heap-size--
     *     MAX-HEAPIFY(A, 1)
     */
    public static void sort(int[] vals) {
        maxHeapify(vals, vals.length);

        for (int size = vals.length, i = size - 1; i >= 1; i--) {
            swap(vals, 0, i);
            maxHeapify(vals, --size, 0);
        }

        System.out.println(Arrays.toString(vals));
    }

    private static void maxHeapify(int[] heap, int size) {
        // heap[size/2..1] are leaves, which are heap themselves.
        for (int i = size / 2; i >= 0; i--) {
            maxHeapify(heap, size, i);
        }
    }

    private static void maxHeapify(int[] heap, int size, int i) {
        int l = leftChild(i);
        int r = rightChild(i);
        int largest = i;
        if (l < size && heap[l] > heap[i]) {
            largest = l;
        }
        if (r < size && heap[r] > heap[largest]) {
            largest = r;
        }
        if (largest != i) {
            swap(heap, i, largest);
            maxHeapify(heap, size, largest);
        }
    }

    private static int leftChild(int i) {
        return (i + 1) * 2 - 1;
    }

    private static int rightChild(int i) {
        return (i + 1) * 2;
    }

    private static void swap(int[] heap, int l, int r) {
        int tmp;
        tmp = heap[l];
        heap[l] = heap[r];
        heap[r] = tmp;
    }

    public static void main(String[] args) {
        sort(new int[] { 1, 2, 3, 4, 5 });
        sort(new int[] { 5, 4, 3, 2, 1 });
        sort(new int[] { 1, 2, 3, 4, 5, 4, 3, 2, 1 });
    }

}
