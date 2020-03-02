package problems.leetcode;

/**
 * https://leetcode.com/problems/valid-palindrome/
 */
public class ValidPalindrome {

    public static boolean isPalindrome(String s) {
        if (s == null) {
            return true;
        }

        int len = s.length(), i = 0, j = len - 1;
        if (len == 0) {
            return true;
        }

        while (i < j) {
            char l = s.charAt(i), r = s.charAt(j);
            if (shouldSkip(l)) {
                i++;
                continue;
            } else if (shouldSkip(r)) {
                j--;
                continue;
            }

            if (Character.toLowerCase(l) != Character.toLowerCase(r)) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }

    private static boolean shouldSkip(char c) {
        return !(Character.isLetter(c) || Character.isDigit(c));
    }

    public static void main(String[] args) {
        String s = "0P";
        System.out.println(isPalindrome(s));
    }

}
