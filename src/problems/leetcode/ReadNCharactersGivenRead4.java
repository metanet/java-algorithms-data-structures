package problems.leetcode;

/**
 * https://leetcode.com/problems/read-n-characters-given-read4
 */
public class ReadNCharactersGivenRead4 {

    public int read(char[] buf, int n) {
        int i = 0;
        char[] chars = new char[4];
        while (true) {
            int r = read4(chars);
            if (i + r > n) {
                r -= i + r - n;
            }
            System.arraycopy(chars, 0, buf, i, r);
            i += r;
            if (r < 4 || i == n) {
                break;
            }
        }

        return i;
    }

    int read4(char[] buf) {
        return 1;
    }

}
