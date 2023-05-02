package problems.leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/palindrome-number/
public class PalindromeNumber {
    
    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        List<Integer> digits = new ArrayList<>(10);
        int j = 0;
        for (int i = 1, k = 0;i <= 1_000_000_000; i *= 10, k++) {
            long d = i;
            int digit = (int) ((((long) x) % (d * 10)) / d);
            digits.add(digit);
            if (digit > 0) {
                j = k;
            }
        }

        // System.out.println(digits);
        int i = 0;
        while (i < j) {
            if (digits.get(i) != digits.get(j)) {
                return false;
            }
            i++;
            j--;
        }
     
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(121));
        System.out.println(isPalindrome(4325));
        System.out.println(isPalindrome(43234));
        System.out.println(isPalindrome(1111111111));
        System.out.println(isPalindrome(1410110141));
    }

}
