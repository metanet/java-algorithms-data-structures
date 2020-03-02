package problems.leetcode;

/**
 * https://leetcode.com/problems/shortest-palindrome/
 */
public class ShortestPalindrome {

    public static void main(String[] args) {
        String s = "aacecaaa";
//        String s = "abcd";
        System.out.println(shortestPalindrome(s));
        System.out.println(shortestPalindromeON(s));
    }

    public static String shortestPalindromeON(String s) {
        if (s == null) {
            return null;
        } else if (s.length() < 2) {
            return s;
        }

        int n = s.length(), i = 0;
        for (int j = n - 1; j >= 0; j--) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
            }
        }

        if (i == n) {
            return s;
        }

        String suffix = s.substring(i);
        return reverse(suffix) + shortestPalindrome(s.substring(0, i)) + suffix;
    }

    public static String shortestPalindrome(String s) {
        if (s == null) {
            return null;
        } else if (s.length() < 2) {
            return s;
        }

        int n = s.length();
        for (int i = n - 1; i >= 0; i--) {
            if (isPalindrome(s, 0, i)) {
                String suffix = s.substring(i + 1);
                return reverse(suffix) + s.substring(0, i + 1) + suffix;
            }
        }

        return null;
    }

    private static boolean isPalindrome(String s, int i, int j) {
        while (i <= j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }

    private static String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }

}
