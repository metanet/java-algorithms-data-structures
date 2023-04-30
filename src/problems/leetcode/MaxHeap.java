package problems.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MaxHeap<T extends Comparable<T>> {

    private T[] heap;
    private int size;
    private int maxSize;

    public MaxHeap(int maxSize) {
        this(maxSize, Collections.emptyList());
    }

    public MaxHeap(int maxSize, T... vals) {
        this(maxSize, Arrays.asList(vals));
    }

    // heapify runtime: O(N)
    public MaxHeap(int maxSize, List<T> vals) {
        this.size = vals.size();
        this.maxSize = Math.max(maxSize, this.size);
        this.heap = (T[]) new Comparable[this.maxSize + 1];
        // max heapify
        for (int i = 0; i < vals.size(); i++) {
            this.heap[i + 1] = vals.get(i);
        }
        // heap[size/2..1] are leaves, which are heap themselves.
        for (int i = size / 2; i > 0; i--) {
            maxHeapify(i);
        }
    }

    private int parent(int i) {
        return i / 2;
    }

    private int leftChild(int i) {
        return 2 * i;
    }

    private int rightChild(int i) {
        return 2 * i + 1;
    }

    private void swap(int l, int r) {
        T tmp;
        tmp = heap[l];
        heap[l] = heap[r];
        heap[r] = tmp;
    }

    private boolean isGreater(int l, int r) {
        return heap[l].compareTo(heap[r]) > 0;
    }

    // runtime: O(logN)
    private void maxHeapify(int i) {
        int l = leftChild(i);
        int r = rightChild(i);
        int largest = i;
        if (l <= size && isGreater(l, i)) {
            largest = l;
        }
        if (r <= size && isGreater(r, largest)) {
            largest = r;
        }
        if (largest != i) {
            swap(i, largest);
            maxHeapify(largest);
        }
    }

    public void add(T element) {
        if (size == maxSize) {
            throw new IllegalStateException("heap full!");
        }
        heap[++size] = element;
        heapUp(size);
    }

    private void heapUp(int i) {
        while (i > 1 && isGreater(i, parent(i))) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    public T remove() {
        if (size == 0) {
            throw new IllegalStateException("empty heap!");
        }
        T popped = heap[1];
        heap[1] = heap[size];
        heap[size--] = null;
        maxHeapify(1);
        return popped;
    }

    public void increase(int i, T val) {
        if (i < 1 || i > size) {
            throw new IndexOutOfBoundsException(i);
        }

        heap[i] = val;
        heapUp(i);
    }

    @Override
    public String toString() {
        return Arrays.toString(heap);
    }

    public static void main(String[] args) {
        MaxHeap<Integer> heap = new MaxHeap<>(10);

        heap.add(5);
        heap.add(10);
        heap.add(20);
        heap.add(15);

        System.out.println(heap);

        System.out.println(heap.remove());
        System.out.println(heap);

        System.out.println(heap.remove());
        System.out.println(heap);

        System.out.println(heap.remove());
        System.out.println(heap);

        System.out.println(heap.remove());
        System.out.println(heap);

        heap = new MaxHeap<>(10, List.of(15, 5, 10, 20));
        System.out.println(heap);

        heap.increase(3, 25);
        System.out.println(heap);
        heap.increase(4, 10);
        System.out.println(heap);
    }

}
