package problems.leetcode;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/happy-number/
public class HappyNumber {

    public boolean isHappy(int n) {
        // n = 2
        // n = 4
        // n = 16
        // n = 37
        // n = 56
        // n = 61
        // n = 37

        Set<Integer> s = new HashSet<>();
        while (s.add(n)) {
            n = replace(n);
            if (n == 1) {
                return true;
            }
        }

        return false;
    }

    private int replace(int n) {
        int sum = 0;
        while (n > 0) {
            int d = n % 10;
            sum += (d * d);
            n /= 10;
        }

        return sum;
    }

}
