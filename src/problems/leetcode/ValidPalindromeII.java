package problems.leetcode;

/**
 * https://leetcode.com/problems/valid-palindrome-ii/
 */
public class ValidPalindromeII {

    public static void main(String[] args) {
        String a = "acbdca";
        System.out.println(validPalindrome(a));
    }

    public static boolean validPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return validPalindrome(s, i + 1, j) || validPalindrome(s, i, j - 1);
            }

            i++;
            j--;
        }

        return true;
    }

    private static boolean validPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }

}
