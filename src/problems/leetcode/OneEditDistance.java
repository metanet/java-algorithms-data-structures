package problems.leetcode;

/**
 * https://leetcode.com/problems/one-edit-distance/
 */
public class OneEditDistance {

    public static void main(String[] args) {
        String s1 = "";
        String s2 = "";

        System.out.println(isOneEditDistance(s1, s2));
    }

    public static boolean isOneEditDistance(String s1, String s2) {
        if (Math.abs(s1.length() - s2.length()) > 1 || s1.equals(s2)) {
            return false;
        }

        int i = 0, j = 0;
        while (i < s1.length() && j < s2.length()) {
            if (s1.charAt(i) == s2.charAt(j)) {
                i++;
                j++;
            } else if (s1.length() > s2.length()) {
                return s1.substring(i + 1).equals(s2.substring(j));
            } else if (s1.length() < s2.length()) {
                return s1.substring(i).equals(s2.substring(j + 1));
            } else {
                return s1.substring(i + 1).equals(s2.substring(j + 1));
            }
        }

        // s1: "a", s2: "ac"
        return s1.length() != s2.length();
    }

}
