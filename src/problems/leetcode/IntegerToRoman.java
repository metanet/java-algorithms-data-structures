package problems.leetcode;

/**
 * https://leetcode.com/problems/integer-to-roman/
 */
public class IntegerToRoman {

    public static void main(String[] args) {
        int num = 58;
        System.out.println(intToRoman(num));
    }

    private static String digitToString(int n, String s, String m, String l) {
        switch (n) {
            case 0:
                return "";
            case 1:
                return s;
            case 2:
                return s + s;
            case 3:
                return s + s + s;
            case 4:
                return s + m;
            case 5:
                return m;
            case 6:
                return m + s;
            case 7:
                return m + s + s;
            case 8:
                return m + s + s + s;
            case 9:
                return s + l;
            default:
                throw new IllegalArgumentException("Cannot convert " + n);
        }
    }

    public static String intToRoman(int num) {
        if (num < 1 || num > 3999) {
            throw new IllegalArgumentException("Cannot convert " + num + " to Roman!");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(digitToString(num / 1000, "M", "", ""));
        sb.append(digitToString((num % 1000) / 100, "C", "D", "M"));
        sb.append(digitToString(((num % 100) / 10), "X", "L", "C"));
        sb.append(digitToString(num % 10, "I", "V", "X"));
        
        return sb.toString();
    }

    public static String intToRoman2(int num) {
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
