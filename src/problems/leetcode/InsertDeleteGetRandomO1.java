package problems.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * https://leetcode.com/problems/insert-delete-getrandom-o1/
 */
public class InsertDeleteGetRandomO1 {

    private static class RandomizedSet {

        private Map<Integer, Integer> map = new HashMap<>();
        private List<Integer> list = new ArrayList<>();
        private Random random = new Random();

        public RandomizedSet() {

        }

        /**
         * Inserts a value to the set. Returns true if the set did not already contain the specified element.
         */
        public boolean insert(int val) {
            boolean exists = map.containsKey(val);
            if (!exists) {
                map.put(val, list.size());
                list.add(val);
            }

            return !exists;
        }

        /**
         * Removes a value from the set. Returns true if the set contained the specified element.
         */
        public boolean remove(int val) {
            Integer index = map.remove(val);
            if (index == null) {
                return false;
            }

            int valToMove = list.remove(list.size() - 1);
            if (valToMove != val) {
                map.put(valToMove, index);
                list.set(index, valToMove);
            }

            return true;
        }

        /**
         * Get a random element from the set.
         */
        public int getRandom() {
            return list.get(random.nextInt(list.size()));
        }
    }

}
