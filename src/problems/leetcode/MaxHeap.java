package problems.leetcode;

import java.util.Arrays;

public class MaxHeap<T extends Comparable<T>> {

    private T[] heap;
    private int size;
    private int maxSize;

    // Constructor to initialize an
    // empty max heap with given maximum
    // capacity
    public MaxHeap(int maxSize) {
        // This keyword refers to current instance itself
        this.maxSize = maxSize;
        this.size = 0;
        this.heap = (T[]) new Comparable[this.maxSize];
    }

    private int parent(int pos) {
        return (pos - 1) / 2;
    }

    private int leftChild(int pos) {
        return (2 * pos) + 1;
    }

    private int rightChild(int pos) {
        return (2 * pos) + 2;
    }

    private void swap(int l, int r) {
        T tmp;
        tmp = heap[l];
        heap[l] = heap[r];
        heap[r] = tmp;
    }

    private void maxHeapify(int i) {
        int l = leftChild(i);
        int r = rightChild(i);
        int largest = i;
        if (l < size && heap[l].compareTo(heap[i]) > 0) {
            largest = l;
        }
        if (r < size && heap[r].compareTo(heap[largest]) > 0) {
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

        heap[size] = element;
        int current = size;
        while (heap[current].compareTo(heap[parent(current)]) > 0) {
            swap(current, parent(current));
            current = parent(current);
        }
        size++;
    }

    public T remove() {
        if (size == 0) {
            throw new IllegalStateException("empty heap!");
        }
        T popped = heap[0];
        heap[0] = heap[--size];
        heap[size] = null;
        maxHeapify(0);
        return popped;
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
    }
}
