package problems.leetcode;

/**
 * https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/
 */
public class ReadNCharactersGivenRead4_II {

    public static void main(String[] args) {
        ReadNCharactersGivenRead4_II reader = new ReadNCharactersGivenRead4_II();
        char[] buf = new char[10];
        int r1 = reader.read(buf, 1);
        int r2 = reader.read(buf, 2);
        int r3 = reader.read(buf, 1);
    }

    private char[] alreadyRead = new char[4];
    private int alreadyReadCount;

    public int read(char[] buf, int n) {
        int count = 0;

        if (alreadyReadCount > 0) {
            int copyCount = Math.min(alreadyReadCount, n);
            System.arraycopy(alreadyRead, 0, buf, 0, copyCount);
            System.arraycopy(alreadyRead, copyCount, alreadyRead, 0, alreadyReadCount - copyCount);
            alreadyReadCount -= copyCount;
            count += copyCount;
        }

        char[] tmp = new char[4];
        while (count < n) {
            int read = read4(tmp);
            if (count + read > n) {
                alreadyReadCount = count + read - n;
                read -= alreadyReadCount;
                System.arraycopy(tmp, read, alreadyRead, 0, alreadyReadCount);
            }

            System.arraycopy(tmp, 0, buf, count, read);
            count += read;

            if (read < 4) {
                break;
            }
        }

        return count;
    }

    int read4(char[] buf) {
        buf[0] = 'a';
        buf[1] = 'b';
        buf[2] = 'c';
        return 3;
    }

}
