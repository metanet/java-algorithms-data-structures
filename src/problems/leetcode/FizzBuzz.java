package problems.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/fizz-buzz/
 */
public class FizzBuzz {

    public static List<String> fizzBuzz(int n) {
        List<String> strs = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            if (i % 3 == 0) {
                sb.append("Fizz");
            }
            if (i % 5 == 0) {
                sb.append("Buzz");
            }
            if (sb.length() == 0) {
                sb.append(i);
            }

            strs.add(sb.toString());
        }

        return strs;
    }

    public static void main(String[] args) {
        int n = 20;
        for (String s : fizzBuzz(n)) {
            System.out.println(s);
        }
    }

}
