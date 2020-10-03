package problems.leetcode;

/**
 * https://leetcode.com/problems/reverse-string/
 */
public class ReverseString {

    public static void reverseString(String s) {
        s=s.trim();
        if (s == null || s.length() < 2) {
            return;
        }

        int i = 0, j = s.length() - 1;
        while (i < j) {
            s=s+s.charAt(j)
            i++;
            j--;
        }
    }

}
