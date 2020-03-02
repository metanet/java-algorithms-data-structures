package problems.leetcode;

/**
 * https://leetcode.com/problems/reverse-string/
 */
public class ReverseString {

    public static void reverseString(char[] s) {
        if (s == null || s.length < 2) {
            return;
        }

        int i = 0, j = s.length - 1;
        while (i < j) {
            char c = s[i];
            s[i] = s[j];
            s[j] = c;
            i++;
            j--;
        }
    }

}
