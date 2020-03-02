package problems.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/decode-string/
 */
public class DecodeString {

    public static void main(String[] args) {
        //        String decoded = decodeString("3[a]4[b]");
        //        String decoded = decodeString("3[a2[b]c]4[d]");
        String decoded = decodeString("3[a2[c]]4[b]");
        //        String decoded = decodeString("2[abc]3[cd]ef");
        System.out.println("decoded: " + decoded);
    }

    /*
     * s = "3[a]2[bc]", return "aaabcbc".
     * s = "3[a2[c]]", return "accaccacc".
     * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
     *
     *
     * 3[a2[b]c]4[d]
     */
    public static String decodeString(String input) {
        if (input == null || input.length() == 0) {
            return "";
        }

        int num = 0;
        Deque<Object> stack = new ArrayDeque<>();
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } else if (c == '[') {
                stack.addLast(num);
                num = 0;
            } else if (c == ']') {
                String s = popString(stack);
                stack.addLast(s);
            } else {
                stack.addLast(String.valueOf(c));
            }
        }

        return popString(stack);
    }

    private static String popString(Deque<Object> s) {
        StringBuilder sb = new StringBuilder();
        StringBuilder result = new StringBuilder();
        int repeat = 1;

        while (!s.isEmpty() && s.peekLast() instanceof String) {
            sb.insert(0, s.removeLast());
        }

        if (s.size() > 0 && s.peekLast() instanceof Integer) {
            repeat = (Integer) s.removeLast();
        }

        for (int i = 0; i < repeat; i++) {
            result.append(sb.toString());
        }

        return result.toString();
    }

}
