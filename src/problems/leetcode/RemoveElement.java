package problems.leetcode;

import java.util.Arrays;

// https://leetcode.com/problems/remove-element/
public class RemoveElement {

    public static void main(String[] args) {
        int[] nums = new int[] {3,2,5,2,3};
        System.out.println(removeElement(nums, 2));
    }

    // runtime: O(N)
    // space: O(1)
    public static int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
        }
        
        System.out.println(Arrays.toString(nums));

        return i;
    }
    
}
