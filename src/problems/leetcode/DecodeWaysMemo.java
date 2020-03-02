package problems.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/decode-ways/
 */
public class DecodeWaysMemo {

    public int numDecodings(String s) {
        int[] memo = new int[s.length()];
        Arrays.fill(memo, -1);

        return numDecodings(s, 0, memo);
    }

    private int numDecodings(String s, int i, int[] memo) {
        /* If our decoding pointer out of bounds then we know that we have exhausted our ability to decode the string */
        if (i >= s.length()) {
            return 1;
        } else if (memo[i] > -1) {
            return memo[i];
        }

        int decodingCount = 0;

        if (isValid(s, i, 1)) { // single character decoding
            decodingCount += numDecodings(s, i + 1, memo);
        }

        if (isValid(s, i, 2)) { // 2 character decoding
            /*
            If this is a valid decoding then recurse on it since it is ONE valid way to decode
            a piece of the string off. If it is INVALID we will not factor this way of decoding
            in and the path in the "tree" of recursion is cut short
            */
            decodingCount += numDecodings(s, i + 2, memo);
        }

        memo[i] = decodingCount;

        return decodingCount;
    }

    private boolean isValid(String s, int i, int len) {
        if (i + len > s.length()) {
            return false;
        }

        s = s.substring(i, i + len);

        if (s.charAt(0) == '0') {
            return false;
        }

        int val = Integer.parseInt(s);

        return val >= 1 && val <= 26;
    }

}
