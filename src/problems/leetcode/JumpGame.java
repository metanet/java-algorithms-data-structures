package problems.leetcode;

/**
 * https://leetcode.com/problems/jump-game/
 */
public class JumpGame {

    public static void main(String[] args) {
//        int[] nums = {2, 3, 1, 1, 4};
        int[] nums = {3, 2, 1, 0, 4};
        System.out.println(canJump(nums));
    }

    public static boolean canJump(int[] nums) {
        if (nums == null) {
            return false;
        }

        int n = nums.length;
        boolean[] jumps = new boolean[n];
        jumps[n - 1] = true;
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 1, maxJump = nums[i]; j <= maxJump; j++) {
                jumps[i] |= jumps[i + j];
            }
        }

        return jumps[0];
    }

}
