package problems.leetcode;

/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 * https://www.youtube.com/watch?v=LPFhl65R7ww
 */
public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = {2};
        int[] nums2 = {};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        // nums1.length < nums2.length

        int nums1Len = nums1.length, nums2Len = nums2.length, low = 0, high = nums1Len;
        while (low <= high) {
            // left part of nums1 is [0, partition1)
            int partition1 = (low + high) / 2;
            // left part of nums2 is [0, partition2)
            int partition2 = (nums1Len + nums2Len + 1) / 2 - partition1;

            //if partition1 is 0 it means nothing is there on left side. Use -INF for maxLeft1
            //if partition1 is length of input then there is nothing on right side. Use +INF for minRight1
            int maxLeft1 = (partition1 == 0) ? Integer.MIN_VALUE : nums1[partition1 - 1];
            int minRight1 = (partition1 == nums1Len) ? Integer.MAX_VALUE : nums1[partition1];
            int maxLeft2 = (partition2 == 0) ? Integer.MIN_VALUE : nums2[partition2 - 1];
            int minRight2 = (partition2 == nums2Len) ? Integer.MAX_VALUE : nums2[partition2];

            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                // We have partitioned array at correct place
                // Now get max of left elements and min of right elements to get the median in case of
                // even length combined array size, or get max of left for odd length combined array size.
                if ((nums1Len + nums2Len) % 2 == 0) {
                    return ((double) Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2;
                } else {
                    return Math.max(maxLeft1, maxLeft2);
                }
            } else if (maxLeft1 > minRight2) {
                // We are too far on right side for partition1. Go on left side.
                high = partition1 - 1;
            } else {
                // maxLeft2 > minRight1
                // We are too far on left side for partition1. Go on right side.
                low = partition1 + 1;
            }
        }

        // We can come here only if input arrays are not sorted.
        throw new IllegalArgumentException();
    }

}
