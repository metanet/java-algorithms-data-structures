package problems.leetcode;

/**
 * https://leetcode.com/problems/verifying-an-alien-dictionary/
 */
public class VerifyAlienDirectory {

    public static void main(String[] args) {
        String[] words = {"hello", "leetcode"};
        String order = "hlabcdefgijkmnopqrstuvwxyz";

        System.out.println(isAlienSorted(words, order));
    }

    public static boolean isAlienSorted(String[] words, String order) {
        if (words == null || words.length < 2) {
            return true;
        }

        int[] orderArr = new int['z' - 'a' + 1];
        for (int i = 0; i < order.length(); i++) {
            orderArr[order.charAt(i) - 'a'] = i;
        }

        for (int i = 1; i < words.length; i++) {
            if (!isOrdered(words[i - 1], words[i], orderArr)) {
                return false;
            }
        }

        return true;
    }

    private static boolean isOrdered(String former, String latter, int[] order) {
        for (int i = 0, j = Math.min(former.length(), latter.length()); i < j; i++) {
            int f = order[former.charAt(i) - 'a'];
            int l = order[latter.charAt(i) - 'a'];
            if (f < l) {
                return true;
            } else if (f > l) {
                return false;
            }
        }

        return former.length() <= latter.length();
    }

}
