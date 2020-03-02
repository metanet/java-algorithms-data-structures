package problems.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/intersection-of-two-arrays-ii/
 */
public class IntersectionOfTwoArraysII {

    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> intersection = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], 1 + map.getOrDefault(nums1[i], 0));
        }

        for (int i = 0; i < nums2.length; i++) {
            int occurrence = map.getOrDefault(nums2[i], 0);
            if (occurrence != 0) {
                intersection.add(nums2[i]);
                map.put(nums2[i], occurrence - 1);
            }
        }

        int[] arr = new int[intersection.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = intersection.get(i);
        }

        return arr;
    }

}
