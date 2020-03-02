package problems.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.com/problems/reorder-data-in-log-files/
 */
public class ReorderDataInLogFiles {

    public static String[] reorderLogFiles(String[] logs) {
        if (logs == null) {
            return logs;
        }

        Comparator<String> c = (s1, s2) -> {
            int i1 = s1.indexOf(' ');
            int i2 = s2.indexOf(' ');
            boolean s1Digit = Character.isDigit(s1.charAt(i1 + 1));
            boolean s2Digit = Character.isDigit(s2.charAt(i2 + 1));
            if (s1Digit && s2Digit) {
                return 0;
            } else if (s1Digit) {
                return 1;
            } else if (s2Digit) {
                return -1;
            }

            int c1 = s1.substring(i1 + 1).compareTo(s2.substring(i2 + 1));
            return c1 != 0 ? c1 : s1.substring(0, i1).compareTo(s2.substring(0, i2));
        };

        Arrays.sort(logs, c);
        return logs;
    }

    public static void main(String[] args) {
        String[] logs = {"dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"};
        reorderLogFiles(logs);
        for (String log : logs) {
            System.out.println(log);
        }
    }

}
