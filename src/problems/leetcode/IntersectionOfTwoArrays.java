package problems.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/intersection-of-two-arrays/
 */
public class IntersectionOfTwoArrays {

    public static int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        List<Integer> intersection = new ArrayList<>();
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                intersection.add(nums1[i]);
                i = proceed(nums1, i);
                j = proceed(nums2, j);
            } else if (nums1[i] < nums2[j]) {
                i = proceed(nums1, i);
            } else {
                j = proceed(nums2, j);
            }
        }

        return intersection.stream().mapToInt(v -> v).toArray();
    }

    private static int proceed(int[] nums, int i) {
        while (++i < nums.length) {
            if (nums[i] != nums[i - 1]) {
                break;
            }
        }

        return i;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{4, 9, 5};
        int[] nums2 = new int[]{9, 4, 9, 8, 4};
        int[] intersection = intersection(nums1, nums2);
        System.out.println(Arrays.toString(intersection));
    }

}
