package problems.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/compare-version-numbers/
 */
public class CompareVersionNumbers {

    public static void main(String[] args) {
        String version1 = "2.5";
        String version2 = "2.5.0";

        System.out.println(compareVersion(version1, version2));
    }

    public static int compareVersion(String version1, String version2) {
        int[] v1Tokens = Arrays.stream(version1.split("\\.")).mapToInt(Integer::parseInt).toArray();
        int[] v2Tokens = Arrays.stream(version2.split("\\.")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0, j = Math.max(v1Tokens.length, v2Tokens.length); i < j; i++) {
            int v1 = i < v1Tokens.length ? v1Tokens[i] : 0;
            int v2 = i < v2Tokens.length ? v2Tokens[i] : 0;
            int c = Integer.compare(v1, v2);
            if (c != 0) {
                return c;
            }
        }

        return 0;
    }

}
