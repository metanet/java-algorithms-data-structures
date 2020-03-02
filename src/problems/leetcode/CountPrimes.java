package problems.leetcode;

/**
 * https://leetcode.com/problems/count-primes/
 * https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
 */
public class CountPrimes {

    public static void main(String[] args) {
        int n = 20;
        System.out.println(countPrimes(n));
    }

    public static int countPrimes(int n) {
        boolean[] prime = new boolean[n];
        for (int i = 2; i < n; i++) {
            prime[i] = true;
        }

        for (int i = 2; i * i < n; i++) {
            if (!prime[i]) {
                continue;
            }

            for (int j = i * i; j < n; j += i) {
                prime[j] = false;
            }
        }

        int count = 0;
        for (int i = 2; i < n; i++) {
            if (prime[i]) {
                count++;
            }
        }

        return count;
    }

    private static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }

        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

}
