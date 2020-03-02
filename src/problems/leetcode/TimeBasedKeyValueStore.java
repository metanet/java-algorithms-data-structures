package problems.leetcode;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/time-based-key-value-store/
 */
public class TimeBasedKeyValueStore {

    private static class TimeMap {

        private Map<String, NavigableMap<Integer, String>> map = new HashMap<>();

        public TimeMap() {

        }

        public void set(String key, String value, int timestamp) {
            map.computeIfAbsent(key, k -> new TreeMap<>()).put(timestamp, value);
        }

        public String get(String key, int timestamp) {
            NavigableMap<Integer, String> valuesMap = map.get(key);
            if (valuesMap == null) {
                return "";
            }

            Map.Entry<Integer, String> entry = valuesMap.floorEntry(timestamp);
            return entry != null ? entry.getValue() : "";
        }
    }

    public static void main(String[] args) {
        TimeMap kv = new TimeMap();
        kv.set("love", "high", 10);
        kv.set("love", "low", 20);
        System.out.println(kv.get("love", 5));
        System.out.println(kv.get("love", 10));
        System.out.println(kv.get("love", 15));
        System.out.println(kv.get("love", 20));
        System.out.println(kv.get("love", 25));
    }

    private static class TimeMap2 {

        private Map<String, List<Map.Entry<Integer, String>>> map;

        /**
         * Initialize your data structure here.
         */
        public TimeMap2() {
            map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(new AbstractMap.SimpleEntry<>(timestamp, value));
        }

        public String get(String key, int timestamp) {
            List<Map.Entry<Integer, String>> values = map.get(key);
            if (values == null) {
                return "";
            }

            int pos = search(values, timestamp);
            return pos != -1 ? values.get(pos).getValue() : "";
        }

        private int search(List<Map.Entry<Integer, String>> values, int timestamp) {
            int lo = 0;
            int hi = values.size();
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (values.get(mid).getKey() <= timestamp) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }

            return lo - 1;
        }
    }

}
