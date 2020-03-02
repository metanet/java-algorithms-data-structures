package problems.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/first-unique-character-in-a-string/
 */
public class FirstUniqueCharacterInString {

    public static void main(String[] args) {
        String s = "leetcode";
        System.out.println(firstUniqChar(s));
    }

    public static int firstUniqChar(String s) {
        int[] indices = new int['z' - 'a' + 1];
        Arrays.fill(indices, -1);
        for (int i = 0; i < s.length(); i++) {
            int l = s.charAt(i) - 'a';
            int j = indices[l];
            if (j == -1) {
                indices[l] = i;
            } else if (j >= 0) {
                indices[l] = -2;
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < indices.length; i++) {
            int j = indices[i];
            if (j >= 0 && j < min) {
                min = j;
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public static int firstUniqChar2(String s) {
        int[] indices = new int['z' - 'a' + 1];
        for (int i = 0; i < s.length(); i++) {
            int l = s.charAt(i) - 'a';
            int j = indices[l];
            if (j == 0) {
                indices[l] = i + 1;
            } else if (j > 0) {
                indices[l] = -2;
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < indices.length; i++) {
            int j = indices[i];
            if (j > 0 && j < min) {
                min = j;
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min - 1;
    }

}
