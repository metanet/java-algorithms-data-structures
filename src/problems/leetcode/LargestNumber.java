package problems.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/largest-number/
 */
public class LargestNumber {


    public static void main(String[] args) {
        int[] nums = {1440, 7548, 4240, 6616, 733, 4712, 883, 8, 9576};
        String largest = largestNumber(nums);
        System.out.println(largest);

//        System.out.println(compare("883", "8"));
    }

    public static String largestNumber(int[] nums) {
        String[] numStrs = new String[nums.length];
        int strLen = 0;
        for (int i = 0; i < nums.length; i++) {
            numStrs[i] = String.valueOf(nums[i]);
            strLen += numStrs[i].length();
        }

        Arrays.sort(numStrs, (s1, s2) -> (s2 + s1).compareTo(s1 + s2));

        StringBuilder sb = new StringBuilder(strLen);
        for (String s : numStrs) {
            sb.append(s);
        }

        if (numStrs[0].equals("0")) {
            return "0";
        }

        return sb.toString();
    }

}
