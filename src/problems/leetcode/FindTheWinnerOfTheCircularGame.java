package problems.leetcode;

// https://leetcode.com/problems/find-the-winner-of-the-circular-game/
public class FindTheWinnerOfTheCircularGame {


    // 0, 1, 2, 3, 4, 5
    // 1, 2, 3, 4, 5, 6

    // n = 6, k = 3
    // 1, 2, 3, 4, 5, 6
    // 1, 2, -, 4, 5, 6
    // 1, 2, -, 4, 5, -
    // 1, 2, -, -, 5, -
    // 1, -, -, -, 5, -
    // 1, -, -, -, -, -
    //
    // n = 6, k = 4
    // 1, 2, 3, 4, 5, 6
    // 1, 2, 3, -, 5, 6
    // 1, -, 3, -, 5, 6
    // -, -, 3, -, 5, 6
    // -, -, -, -, 5, 6
    // -, -, -, -, 5, -
    //
    // n = 7, k = 2
    // 1, 2, 3, 4, 5, 6, 7
    // 1, -, 3, 4, 5, 6, 7
    // 1, -, 3, -, 5, 6, 7
    // 1, -, 3, -, 6, -, 7
    // -, -, 3, -, 6, -, 7
    // -, -, 3, -, -, -, 7
    // -, -, -, -, -, -, 7

    public static int findTheWinner(int n, int k) {
        int[] players = new int[n];
        for (int i = 0; i < n; i++) {
            players[i] = i + 1;
        }

        int current = 0;
        for (int remaining = n - 1; remaining > 0; remaining--) {
            for (int move = k; move > 0;) {
                if (players[current++] != 0) {
                    move--;
                }
                if (current == n) {
                    current = 0;
                }
            }
            players[((current - 1) + n) % n] = 0;
        }

        for (int i = 0; i < n; i++) {
            if (players[i] != 0) {
                return players[i];
            }
        } 

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(findTheWinner(5, 2));
        System.out.println(findTheWinner(6, 3));
        System.out.println(findTheWinner(6, 4));
        System.out.println(findTheWinner(7, 2));
    }

}