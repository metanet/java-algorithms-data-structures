package problems.leetcode;

/**
 * https://leetcode.com/problems/jump-game/
 */
public class JumpGame {

    public static void main(String[] args) {
        // int[] nums = {2, 3, 1, 1, 4};
        int[] nums = { 3, 2, 1, 0, 4 };
        System.out.println(canJumpON(nums));
    }

    // runtime: O(N)
    // space: O(1)
    public static boolean canJumpON(int[] nums) {
        for (int i = 0, boundary = 0; i <= boundary; i++) {
            boundary = Math.max(boundary, i + nums[i]);
            if (boundary >= nums.length - 1) {
                return true;
            }
        }

        return false;
    }

    // runtime: O(N^2)
    // space: O(N)
    public static boolean canJumpDP(int[] nums) {
        if (nums == null || nums.length < 1) {
            return false;
        }

        boolean[] success = new boolean[nums.length];
        int i = nums.length - 1;
        success[i--] = true;
        for (; i >= 0; i--) {
            for (int j = 1; j <= nums[i] && i + j < nums.length; j++) {
                if (success[i + j]) {
                    success[i] = true;
                    break;
                }
            }
        }

        return success[0];
    }

}
