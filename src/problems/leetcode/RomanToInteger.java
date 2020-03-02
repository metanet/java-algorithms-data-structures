package problems.leetcode;

/**
 * https://leetcode.com/problems/roman-to-integer/
 */
public class RomanToInteger {

    public static void main(String[] args) {
        String s = "MCMXCIV";
        System.out.println(romanToInt(s));
    }

    public static int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int count = -1, factor = 0, num = 0;
        for (int i = 0; i < s.length(); i++) {
            int n = toArabicNumber(s.charAt(i));
            if (count == -1) {
                factor = n;
                count = 1;
            } else if (n == factor) {
                count++;
            } else if (n < factor) {
                num += factor * count;
                factor = n;
                count = 1;
            } else { // n > factor
                num += (n - factor * count);
                count = -1;
                factor = 0;
            }
        }

        num += factor * count;

        return num;
    }

    private static int toArabicNumber(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                throw new IllegalArgumentException("invalid char: " + c);
        }
    }

    /*
     * I    1
     * V    5
     * X    10
     * L    50
     * C    100
     * D    500
     * M    1000
     *
     * LII: 52
     * XXXIII: 33
     * XLII: 42
     *
     * CMXLIII 943
     */

}
