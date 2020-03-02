package problems.leetcode;

/**
 * https://leetcode.com/problems/decode-ways/
 */
public class DecodeWaysDP {

    public static int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }

        int n0 = 1, n1 = 1;
        for (int i = 1; i < s.length(); i++) {
            int n2 = s.charAt(i) != '0' ? n1 : 0;

            int cur = Integer.parseInt(s.substring(i - 1, i + 1));
            if (10 <= cur && cur <= 26) {
                n2 += n0;
            }

            n0 = n1;
            n1 = n2;
        }

        return n1;
    }

    public static void main(String[] args) {
        System.out.println(numDecodings("107"));
    }
}
