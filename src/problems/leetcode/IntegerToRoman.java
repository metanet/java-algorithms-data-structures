package problems.leetcode;

/**
 * https://leetcode.com/problems/integer-to-roman/
 */
public class IntegerToRoman {

    public static void main(String[] args) {
        int num = 58;
        System.out.println(intToRoman(num));
    }

    public static String intToRoman(int num) {
        if (num < 1 || num > 3999) {
            throw new IllegalArgumentException("invalid num: " + num + " to convert!");
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0, thousands = num / 1000; i < thousands; i++) {
            sb.append("M");
        }

        append(sb, (num % 1000) / 100, "C", "D", "M");
        append(sb, (num % 100) / 10, "X", "L", "C");
        append(sb, num % 10, "I", "V", "X");

        return sb.toString();
    }

    private static void append(StringBuilder sb, int num, String s1, String s2, String s3) {
        if (num == 9) {
            sb.append(s1).append(s3);
        } else if (num >= 5) {
            sb.append(s2);
            while (num-- > 5) {
                sb.append(s1);
            }
        } else if (num == 4) {
            sb.append(s1).append(s2);
        } else {
            while (num-- > 0) {
                sb.append(s1);
            }
        }
    }

    /*

    1900 = MCM

    3999 = MMMCMXCIX
     */

}
