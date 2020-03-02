package problems.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 */
public class LetterCombinationsOfPhoneNumber {

    private static final String[] LETTERS = new String[]{null, null, "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    private final List<String> combinations = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits.length() > 0) {
            letterCombinations(digits, 0, new char[digits.length()]);
        }
        return combinations;
    }

    private void letterCombinations(String digits, int i, char[] chars) {
        if (i == digits.length()) {
            combinations.add(new String(chars));
            return;
        }

        int digit = digits.charAt(i) - '0';
        assert digit > 1;
        String letters = LETTERS[digit];
        for (int j = 0; j < letters.length(); j++) {
            chars[i] = letters.charAt(j);
            letterCombinations(digits, i + 1, chars);
        }
    }

    public static void main(String[] args) {
        LetterCombinationsOfPhoneNumber sol = new LetterCombinationsOfPhoneNumber();
        List<String> combinations = sol.letterCombinations("234");
        for (String s : combinations) {
            System.out.println(s);
        }
    }

}
