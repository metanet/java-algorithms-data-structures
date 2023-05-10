package problems.leetcode;

/**
 * https://leetcode.com/problems/jump-game-ii/
 */
public class JumpGameII {

    // runtime: O(N^2)
    // space: O(N)
    public static int jumpON2(int[] nums) {
        int n = nums.length;
        int[] counts = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            int jumps = Integer.MAX_VALUE;
            for (int j = 1; j <= nums[i] && i + j < n; j++) {
                int c = counts[i + j];
                if (c != Integer.MAX_VALUE) {
                    jumps = Math.min(jumps, 1 + c);
                }

            }
            counts[i] = jumps;
        }

        return counts[0];
    }

    // runtime: O(N)
    // space: O(1)
    // https://leetcode.com/problems/jump-game-ii/editorial/
    public static int jumpGreedy(int[] nums) {
        // The starting range of the first jump is [0, 0]
        int answer = 0, n = nums.length;
        int curEnd = 0, curFar = 0;

        for (int i = 0; i < n - 1; ++i) {
            // Update the farthest reachable index of this jump.
            curFar = Math.max(curFar, i + nums[i]);

            // If we finish the starting range of this jump,
            // Move on to the starting range of the next jump.
            if (i == curEnd) {
                answer++;
                curEnd = curFar;
            }
        }

        return answer;
    }

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

    // https://leetcode.com/problems/jump-game-ii/editorial/comments/1776566
    public static int jump(int[] nums) {
        if (nums == null) {
            return 0;
        }

        int steps = 0, low = 0, high = 0, n = nums.length;
        while (high < n - 1) {
            int maxJump = 0;
            for (int i = low; i <= high; i++) {
                maxJump = Math.max(maxJump, i + nums[i]);
            }
            low = high + 1;
            high = maxJump;
            steps++;
        }

        return steps;
    }

    public static void main(String[] args) {
        // int[] jumps = {2, 3, 1, 1, 4};
        int[] jumps = { 4, 1, 1, 3, 1, 1, 1 };
        // int[] jumps = {1, 2, 1, 1, 1};
        // int[] jumps = {10,9,8,7,6,5,4,3,2,1,1,0};
        System.out.println(jumpGreedy(jumps));
        // System.out.println(jumpON(jumps));
    }

}
