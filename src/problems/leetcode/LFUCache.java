package problems.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/lfu-cache/
 */
public class LFUCache {

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);

        /*
        3 -> 2 : hc: 2, t: 2

        4 -> 3 : hc: 1, t: 3
         */


        cache.put(2, 1);
        cache.put(3, 2);

        System.out.println("Expected: 2, Returned: " + cache.get(3));
        System.out.println("Expected: 1, Returned: " + cache.get(2));

        cache.put(4, 3);

        System.out.println("Expected: 1, Returned: " + cache.get(2));
        System.out.println("Expected: -1, Returned: " + cache.get(3));
        System.out.println("Expected: 3, Returned: " + cache.get(4));
    }

    private final Map<Integer, KVContainer> map = new HashMap<>();
    private final NavigableMap<KVContainer, Integer> stats = new TreeMap<>();
    private final int capacity;
    private int time;

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        KVContainer container = map.get(key);
        if (container == null) {
            return -1;
        }

        stats.remove(container);
        container.hitCount++;
        container.time = ++this.time;
        stats.put(container, key);
        return container.val;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }

        KVContainer existingContainer = map.remove(key);
        int insertionTime, hitCount;
        if (existingContainer != null) {
            stats.remove(existingContainer);
            insertionTime = existingContainer.time;
            hitCount = existingContainer.hitCount + 1;
        } else {
            insertionTime = ++this.time;
            hitCount = 1;
        }

        if (map.size() == capacity) {
            KVContainer evictedContainer = stats.firstKey();
            stats.remove(evictedContainer);
            map.remove(evictedContainer.key);
        }

        KVContainer newContainer = new KVContainer(key, value, hitCount, insertionTime);
        map.put(key, newContainer);
        stats.put(newContainer, key);
    }

    private static class KVContainer implements Comparable<KVContainer> {
        int key, val, hitCount, time;

        KVContainer(int key, int val, int hitCount, int time) {
            this.hitCount = hitCount;
            this.time = time;
            this.key = key;
            this.val = val;
        }

        @Override
        public int compareTo(KVContainer other) {
            int c = Integer.compare(this.hitCount, other.hitCount);
            return c != 0 ? c : Integer.compare(this.time, other.time);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            KVContainer keyHolder = (KVContainer) o;

            if (hitCount != keyHolder.hitCount) return false;
            return time == keyHolder.time;
        }

        @Override
        public int hashCode() {
            int result = hitCount;
            result = 31 * result + time;
            return result;
        }

        @Override
        public String toString() {
            return "KVContainer{" +
                    "key=" + key +
                    ", val=" + val +
                    ", hitCount=" + hitCount +
                    ", time=" + time +
                    '}';
        }
    }

    /*

    ["LFUCache","put","put","get","put","get","get","put","get","get","get"]
    [[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]]

    expected:
    [null,null,null,1,null,-1,3,null,-1,3,4]

    output:
    [null,null,null,1,null,-1,3,null,1,3,-1]

    1: 1, 1
    2: 2, 2
    3: 1
    4: 3, 3 -> evicted: 2
    5: 2
    6: 3
    7: 4, 4 -> evicted: 1
    8:


     */
}
