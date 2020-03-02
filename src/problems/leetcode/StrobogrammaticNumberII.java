package problems.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/strobogrammatic-number-ii/
 */
public class StrobogrammaticNumberII {

    private List<String> result = new ArrayList<>();

    public List<String> findStrobogrammatic(int n) {
        if (n > 0) {
            process(new char[n], 0, n - 1);
        }

        return result;
    }

    private void process(char[] chars, int i, int j) {
        if (i == j) {
            chars[i] = '0';
            addResult(chars);
            chars[i] = '1';
            addResult(chars);
            chars[i] = '8';
            addResult(chars);
            return;
        }

        if (i == j + 1) {
            addResult(chars);
            return;
        }

        process(chars, i, j, '0', '0');
        process(chars, i, j, '1', '1');
        process(chars, i, j, '8', '8');
        process(chars, i, j, '6', '9');
        process(chars, i, j, '9', '6');
    }

    private void process(char[] chars, int i, int j, char c1, char c2) {
        chars[i] = c1;
        chars[j] = c2;
        process(chars, i + 1, j - 1);
    }

    private void addResult(char[] chars) {
        if (chars.length == 1 || chars[0] > '0') {
            result.add(new String(chars));
        }
    }

    public static void main(String[] args) {
        int n = 1;
        List<String> result = new StrobogrammaticNumberII().findStrobogrammatic(n);
        for (String s : result) {
            System.out.println(s);
        }
    }

}
