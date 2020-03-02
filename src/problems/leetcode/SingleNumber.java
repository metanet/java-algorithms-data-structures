package problems.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/single-number/
 */
public class SingleNumber {

    public static int singleNumber(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (!seen.add(num)) {
                seen.remove(num);
            }
        }

        return seen.iterator().next();
    }

    public static int singleNumberXOR(int[] nums) {
        /*

        a ^ 0 = a
        a ^ a = 0
        a ^ b ^ a = (a ^ a) ^ b = b

        XOR is associative and commutative

        */

        int i = 0;
        for (int num : nums) {
            i ^= num;
        }

        return i;
    }

    public static void main(String[] args) {
        int[] nums = {4, 1, 2, 1, 2};
        System.out.println(singleNumberXOR(nums));
    }

}
