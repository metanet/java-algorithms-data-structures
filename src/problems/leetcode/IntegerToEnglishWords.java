package problems.leetcode;

/**
 * https://leetcode.com/problems/integer-to-english-words/
 */
public class IntegerToEnglishWords {

    private static final int BILLION = 1_000_000_000;
    private static final int MILLION = 1_000_000;
    private static final int THOUSAND = 1_000;

    public static String numberToWords(int num) {
        StringBuilder sb = new StringBuilder();

        toWord(sb, num / BILLION, BILLION);
        toWord(sb, (num % BILLION) / MILLION, MILLION);
        toWord(sb, (num % MILLION) / THOUSAND, THOUSAND);
        toWord(sb, num % THOUSAND, 1);

        if (sb.length() == 0) {
            sb.append(toWord(0));
        }

        return sb.toString();
    }

    private static void toWord(StringBuilder sb, int num, int factor) {
        if (num == 0) {
            return;
        }

        toWord(sb, num);

        if (factor >= THOUSAND) {
            append(sb, toWord(factor));
        }
    }

    private static void toWord(StringBuilder s, int num) {
        int hundreds = num / 100;
        if (hundreds > 0) {
            append(s, toWord(hundreds));
            append(s, toWord(100));
        }

        int tens = num % 100;
        if (tens == 0) {
            return;
        }

        if (tens <= 20) {
            append(s, toWord(tens));
            return;
        }

        int digits = tens % 10;
        tens -= digits;
        append(s, toWord(tens));

        if (digits > 0) {
            append(s, toWord(digits));
        }
    }

    private static String toWord(int n) {
        switch (n) {
            case 0:
                return "Zero";
            case 1:
                return "One";
            case 2:
                return "Two";
            case 3:
                return "Three";
            case 4:
                return "Four";
            case 5:
                return "Five";
            case 6:
                return "Six";
            case 7:
                return "Seven";
            case 8:
                return "Eight";
            case 9:
                return "Nine";
            case 10:
                return "Ten";
            case 11:
                return "Eleven";
            case 12:
                return "Twelve";
            case 13:
                return "Thirteen";
            case 14:
                return "Fourteen";
            case 15:
                return "Fifteen";
            case 16:
                return "Sixteen";
            case 17:
                return "Seventeen";
            case 18:
                return "Eighteen";
            case 19:
                return "Nineteen";
            case 20:
                return "Twenty";
            case 30:
                return "Thirty";
            case 40:
                return "Forty";
            case 50:
                return "Fifty";
            case 60:
                return "Sixty";
            case 70:
                return "Seventy";
            case 80:
                return "Eighty";
            case 90:
                return "Ninety";
            case 100:
                return "Hundred";
            case THOUSAND:
                return "Thousand";
            case MILLION:
                return "Million";
            case BILLION:
                return "Billion";
            default:
                throw new IllegalArgumentException("cannot convert: " + n);
        }
    }

    private static void append(StringBuilder s, String w) {
        if (s.length() > 0) {
            s.append(" ");
        }

        s.append(w);
    }

    public static void main(String[] args) {
        int num = Integer.MAX_VALUE;
        System.out.println(numberToWords(num));
    }
}
