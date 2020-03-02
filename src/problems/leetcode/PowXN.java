package problems.leetcode;

/**
 * https://leetcode.com/problems/powx-n/
 */
public class PowXN {

    public static double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }

        long limit = n;
        if (limit < 0) {
            x = 1 / x;
            limit = -limit;
        }

        double r1 = 1;
        while (limit > 0) {
            long i = 1;
            double r2 = x;

            while (i * 2 <= limit) {
                r2 *= r2;
                i *= 2;
            }

            limit -= i;
            r1 *= r2;
        }

        return r1;
    }

    public static double myPow2(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        double ans = 1;
        for (long i = 0; i < N; i++)
            ans = ans * x;
        return ans;
    }

    public static void main(String[] args) {
//        double x = 0.00001;
//        int n = 2147483647;
        double x = 2;
        int n = Integer.MIN_VALUE;
        long start = System.nanoTime();
        double r = myPow(x, n);
        long duration = System.nanoTime() - start;
        System.out.println(r);
        System.out.println("Duration: " + duration);
    }

}
