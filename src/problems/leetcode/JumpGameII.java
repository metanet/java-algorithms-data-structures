package problems.leetcode;

/**
 * https://leetcode.com/problems/jump-game-ii/
 */
public class JumpGameII {

    public static int jumpON(int[] nums) {
        if (nums == null) {
            return 0;
        }

        int jumpCount = 0, pos = 0, target = nums.length - 1;
        while (pos < target) {
            int maxJumps = nums[pos], nextPos = pos, maxPos = pos;
            if (pos + maxJumps >= target) {
                jumpCount++;
                break;
            }

            for (int i = pos + 1, j = Math.min(target, pos + maxJumps); i <= j; i++) {
                if (i + nums[i] > maxPos) {
                    nextPos = i;
                    maxPos = i + nums[i];
                }
            }

            pos = nextPos;
            jumpCount++;
        }

        return jumpCount;
    }

    public static int jump(int[] nums) {
        if (nums == null) {
            return 0;
        }

        int steps = 0;
        for (int l = 0, r = 0; r < nums.length - 1; steps++) {
            int next = r;
            for (int i = l; i <= r; i++) {
                next = Math.max(next, nums[i] + i);
            }
            l = r + 1;
            r = next;
        }

        return steps;
    }

    public static void main(String[] args) {
//        int[] jumps = {2, 3, 1, 1, 4};
        int[] jumps = {4, 1, 1, 3, 1, 1, 1};
//        int[] jumps = {1, 2, 1, 1, 1};
//        int[] jumps = {10,9,8,7,6,5,4,3,2,1,1,0};
        System.out.println(jump(jumps));
        System.out.println(jumpON(jumps));
    }

}
