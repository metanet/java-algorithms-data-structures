package problems.leetcode;

/**
 * https://leetcode.com/problems/friend-circles/
 */
public class FindCircles {

    public static void main(String[] args) {
        int[][] friends = {{1, 0, 0, 1}, {0, 1, 1, 0}, {0, 1, 1, 1}, {1, 0, 1, 1}};
        int circles = findCircleNum(friends);
        System.out.println(circles);
    }

    public static int findCircleNum(int[][] M) {
        int circles = 0;

        for (int student1 = 0; student1 < M.length; student1++) {
            for (int student2 = 0; student2 < M.length; student2++) {
                if (M[student1][student2] == 1) {
                    circles++;
                    M[student1][student2] = -circles;
                    M[student2][student2] = -circles;
                    depthFirstSearch(M, student2, -circles);
                }
            }
        }

        return circles;
    }

    private static void depthFirstSearch(int[][] M, int student1, int marker) {
        for (int student2 = 0; student2 < M.length; student2++) {
            if (M[student1][student2] == 1) {
                M[student1][student2] = marker;
                M[student2][student2] = marker;
                depthFirstSearch(M, student2, marker);
            }
        }
    }

    /*

    Input:
          0 1 2
        0[1,1,0],
        1[1,1,0],
        2[0,0,1]]
    Output: 2
    Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.
    The 2nd student himself is in a friend circle. So return 2.

     0-1

      0 1 2 3
    0 1,0,0,1
    1 0,1,1,0
    2 0,1,1,1
    3 1,0,1,1


     */

}
