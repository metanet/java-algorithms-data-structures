package problems.leetcode;

/**
 * https://leetcode.com/problems/find-the-celebrity/
 */
public class FindTheCelebrity {

    public int findCelebrity(int n) {
        // The first pass is to pick out the candidate.
        // If candidate knows i, then switch candidate.

        // Proof of first pass:
        //  Assume the candidate = K after first pass.
        //  Then we know from 0 - K-1 are definitely not candidate.
        //	Because our candidate starts with 0,
        //	and we only switch when candidate knows someone, i.e., knows(candidate, i).
        //	That's why it came to K because all those previous people < K disqualify.
        //	Also since K knows no one after k+1 - n-1, because candidate didn't get
        //	updated in the rest of the loop, k+1 - n-1 can't be celebrity as well
        //	because there is at least one person (K) doesn't know them.
        //	This disqualify as celebrity.
        int candidate = 0;
        for (int i = 1; i < n; i++) {
            if (knows(candidate, i)) {
                candidate = i;
            }
        }

        //  The second pass is to check whether the candidate is valid.
        //  If candidate knows somebody or someone doesn't know candidate, then this candidate is invalid,
        //	and since we picked out the only possible candidate in first pass, we don't have a celebrity.
        for (int i = 0; i < n; i++) {
            if (i != candidate && (knows(candidate, i) || !knows(i, candidate))) {
                return -1;
            }
        }

        return candidate;
    }

    public static int findCelebrityBruteForce(int n) {
        int[] outDegree = new int[n], inDegree = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }

                if (knows(i, j)) {
                    outDegree[i]++;
                    inDegree[j]++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (outDegree[i] == 0 && inDegree[i] == n - 1) {
                return i;
            }
        }

        return -1;
    }

    private static boolean knows(int a, int b) {
        return false;
    }

}
