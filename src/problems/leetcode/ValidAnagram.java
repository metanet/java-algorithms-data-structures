package problems.leetcode;

/**
 * https://leetcode.com/problems/valid-anagram/
 */
public class ValidAnagram {

    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";

        System.out.println(isAnagram(s, t));
    }

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] counts1 = new int['z' - 'a' + 1], counts2 = new int['z' - 'a' + 1];
        for (int i = 0; i < s.length(); i++) {
            counts1[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < t.length(); i++) {
            int j = t.charAt(i) - 'a';
            if (++counts2[j] > counts1[j]) {
                return false;
            }
        }

        return true;
    }

}
