package problems.leetcode;

/**
 * https://leetcode.com/problems/single-number-ii/
 */
public class SingleNumberII {

    public static int singleNumber(int[] nums) {
        /*

        1,2,1,2,1,2,3

        we have two bit masks: seenOnce and seenTwice
        - change seenOnce if twice is not set
        - change seenTwice if once is not set

        seenOnce = ~seenTwice & (seenOnce ^ num)
        seenTwice = ~seenOnce & (seenTwice ^ num)

         */

        int seenOnce = 0, seenTwice = 0;
        for (int num : nums) {
            seenOnce = ~seenTwice & (seenOnce ^ num);
            seenTwice = ~seenOnce & (seenTwice ^ num);
//            System.out.println("NUM: " + num + " seenOnce: " + seenOnce + " seenTwice: " + seenTwice);
        }

        return seenOnce;
    }

    public static int singleNumber2(int[] nums) {
        int seen1 = 0, seen2 = 0, seen3 = 0;
        for (int num : nums) {
            seen1 = ~seen2 & ~seen3 & (seen1 ^ num);
            seen2 = ~seen1 & ~seen3 & (seen2 ^ num);
            seen3 = ~seen1 & ~seen2 & (seen3 ^ num);
//            System.out.println("NUM: " + num + " seenOnce: " + seenOnce + " seenTwice: " + seenTwice);
        }

        return seen3;
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 2, 2, 3, 3, 3};
        System.out.println(singleNumber2(nums));
    }

}
