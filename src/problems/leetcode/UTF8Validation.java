package problems.leetcode;

/**
 * https://leetcode.com/problems/utf-8-validation/
 */
public class UTF8Validation {

    public static boolean validUtf8(int[] data) {
        int s = 0;

        for (int num : data) {
            int num1s = headingNumberOf1s(num);

            if (s == 0) {
                if (num1s == 1 || num1s == -1) {
                    return false;
                } else if (num1s > 0) {
                    s = num1s - 1;
                }
            } else if (s > 0) {
                if (num1s != 1) {
                    return false;
                }
                s--;
            }
        }

        // check if the bytes of the last char is seen
        return s == 0;
    }

    private static int headingNumberOf1s(int num) {
        if (num >= 248) {
            // 11111xxx
            return -1;
        } else if (num >= 240) {
            // 11110xxx
            return 4;
        } else if (num >= 224) {
            // 1110xxxx
            return 3;
        } else if (num >= 192) {
            // 110xxxxx
            return 2;
        } else if (num >= 128) {
            // 10xxxxxx
            return 1;
        } else {
            // 0xxxxxxx
            return 0;
        }
    }

    public static void main(String[] args) {
        int i = 0b00000010;
        System.out.println(i);
        System.out.println(headingNumberOf1s(i));

        int[] data = {250, 145, 145, 145, 145};
        System.out.println(validUtf8(data));
    }

    /*

    128 - 191
    10000000
    10111111

    */

}
