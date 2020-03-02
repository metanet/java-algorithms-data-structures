package problems.leetcode;

import java.util.Arrays;

public class RemoveDuplicatesFromSortedArray {

    /*   0 1 2 3 4 5 6 7 8 9
        [0,0,1,1,1,2,2,3,3,4]
        i = 0, j = 1
        i = 0, j = 2, arr[i]=arr[j], i++, j++
        i = 1, j = 3


     */
    public static int removeDuplicates(int[] nums) {
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[j - 1]) {
                nums[++i] = nums[j];
            }
        }

        return i + 1;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
//        int[] nums = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] nums = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int len = removeDuplicates(nums);
        System.out.println(len);
        System.out.println(Arrays.toString(nums));
    }

}
