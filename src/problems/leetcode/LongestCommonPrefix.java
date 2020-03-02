package problems.leetcode;

/**
 * https://leetcode.com/problems/longest-common-prefix/
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        String[] strs = {"c", "c"};
        System.out.println(longestCommonPrefix(strs));
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        int i = 0;
        while (true) {
            if (i == strs[0].length()) {
                break;
            }

            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                String s = strs[j];
                if (i == s.length() || c != s.charAt(i)) {
                    i = -1;
                    break;
                }
            }

            if (i != -1) {
                sb.append(c);
                i++;
            } else {
                break;
            }
        }

        return sb.toString();
    }

}
