package problems.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/perfect-squares/
 */
public class PerfectSquares {

    public static void main(String[] args) {
        System.out.println(numSquaresDP(13));
    }

    public static int numSquaresDP(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        int[] squares = new int[(int) Math.sqrt(n) + 1];
        for (int i = 1; i < squares.length; ++i) {
            squares[i] = i * i;
        }

        for (int num = 1; num <= n; ++num) {
            for (int j = 1; j < squares.length; ++j) {
                if (num < squares[j]) {
                    break;
                }

                dp[num] = Math.min(dp[num], dp[num - squares[j]] + 1);
            }
        }

        return dp[n];
    }

    public static int numSquares(int n) {
        List<Integer> squares = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            squares.add(i * i);
        }

//        System.out.println(squares);

        return numSquares(n, squares, squares.size() - 1, new HashMap<>());
    }

    private static int numSquares(int n, List<Integer> squares, int i, Map<Key, Integer> memo) {
        Key key = new Key(n, i);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        if (i > 0) {
            int sq = squares.get(i);
            int count = n / sq;
            if (count > 0) {
                count += numSquares(n - sq * count, squares, i - 1, memo);
                count = Math.min(count, numSquares(n, squares, i - 1, memo));
            } else {
                count = numSquares(n, squares, i - 1, memo);
            }

            memo.put(key, count);
            return count;
        } else if (i == 0) {
            return n / squares.get(0);
        }

        return 0;
    }

    private static class Key {
        int n, i;

        Key(int n, int i) {
            this.n = n;
            this.i = i;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Key key = (Key) o;

            if (n != key.n) return false;
            return i == key.i;
        }

        @Override
        public int hashCode() {
            int result = n;
            result = 31 * result + i;
            return result;
        }
    }

}
