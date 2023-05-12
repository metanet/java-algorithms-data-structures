package problems.leetcode;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// https://leetcode.com/problems/n-queens/
public class NQueens {
    
    public static List<List<String>> solveNQueens(int n) {
        if (n == 0) {
            return Collections.emptyList();
        }

        List<List<String>> result = new ArrayList<>();
        for (int col = 0; col < n; col++) {
            // System.out.println("col=" + col);
            Occupied current = new Occupied(n);
            Pos pos = new Pos(0, col);
            current.occupy(pos);
            solve(n, result, current, pos);
        }

        return result;

        // n = 5

        // x . . . .
        // . . . x . 
        // . x . . .
        // . . . . x
        // . . x . .

        // x . . . .
        // . . x . . 
        // . . . . x
        // . x . . .
        // . . . x .

        // . x . . .
        // . . . x . 
        // x . . . .
        // . . x . .
        // . . . . x

        // . x . . .
        // . . . . x 
        // . . x . .
        // x . . . .
        // . . . x .

        // . . x . .
        // . . . . x 
        // x . . . .
        // . . . x .
        // . x . . .

        // . . x . .
        // x . . . . 
        // . . . . x
        // . x . . .
        // . . . x .

        // . . . x .
        // . x . . . 
        // . . . . x
        // . . x . .
        // x . . . .

        // . . . x .
        // x . . . . 
        // . . x . .
        // . . . . x
        // . x . . .

        // . . . . x
        // . x . . . 
        // . . . x .
        // x . . . .
        // . . x . .

        // . . . . x
        // . . x . . 
        // x . . . .
        // . . . x .
        // . x . . .

        // solve it for cols = 0 to ceil(n/2)

        // d1 = x - y
        // 
        
    }

    private static void solve(int boardSize, List<List<String>> result, Occupied current, Pos prev) {
        if (current.size() == boardSize) {
            result.add(current.toBoardString());
            return;
        }

        // System.out.println("current=" + current + ", prev=" + prev);
        // System.out.println("nexts-" + nextPositions);
        for (Pos next : prev.nextPositions(boardSize, current)) {
            current.occupy(next);
            solve(boardSize, result, current, next);
            current.deleteLast();;
        }
    }

    private static class Pos {
        int x;
        int y;
        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        List<Pos> nextPositions(int boardSize, Occupied occupied) {
            List<Pos> result = new ArrayList<>();
            for (int j = y + 2; j < boardSize; j++) {
                if (x + 1 < boardSize && !occupied.canAttack(x + 1, j)) {
                    result.add(new Pos(x + 1, j));
                }
                if (x > 0 && !occupied.canAttack(x - 1, j)) {
                    result.add(new Pos(x - 1, j));
                }
            }
            for (int j = y - 2; j >= 0; j--) {
                if (x + 1 < boardSize && !occupied.canAttack(x + 1, j)) {
                    result.add(new Pos(x + 1, j));
                }
                if (x > 0 && !occupied.canAttack(x - 1, j)) {
                    result.add(new Pos(x - 1, j));
                }
            }

            return result;
        }

        @Override
        public String toString() {
            return "{x=" + x + ", y=" + y + "}";
        }

        
    }

    private static class Occupied {
        int n;
        boolean rows[];
        boolean cols[];
        boolean diag1[];
        boolean diag2[];
        List<Pos> positions = new ArrayList<>();

        Occupied(int n) {
            this.n = n;
            this.rows = new boolean[n];
            this.cols = new boolean[n];
            this.diag1 = new boolean[n * 2 - 1];
            this.diag2 = new boolean[n * 2 - 1];
        }

        boolean canAttack(int x, int y) {
            return rows[x] || cols[y] || diag1[n - 1 - (x - y)] || diag2[x + y];
        }

        void occupy(Pos pos) {
            rows[pos.x] = true;
            cols[pos.y] = true;
            diag1[n - 1 - (pos.x - pos.y)] = true;
            diag2[pos.x + pos.y] = true;
            positions.add(pos);
        }

        void deleteLast() {
            Pos pos = positions.remove(positions.size() - 1);
            rows[pos.x] = false;
            cols[pos.y] = false;
            diag1[n - 1 - (pos.x - pos.y)] = false;
            diag2[pos.x + pos.y] = false;
        }

        int size() {
            return positions.size();
        }

        List<String> toBoardString() {
            char[][] board = new char[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    board[i][j] = '.';
                }
            }
            for (Pos pos : positions) {
                board[pos.x][pos.y] = 'Q';
            }
    
            List<String> rows = new ArrayList<>();
            for (char[] b : board) {
                rows.add(new String(b));
            }
    
            return rows;
        }
    }


    public static void main(String[] args) {
        // System.out.println(new Pos(0, 0).canAttack(new Pos(0, 0)));
        // System.out.println(new Pos(0, 0).canAttack(new Pos(2, 0)));
        // System.out.println(new Pos(0, 0).canAttack(new Pos(0, 3)));

        // System.out.println(new Pos(0, 0).canAttack(new Pos(2, 2)));
        // System.out.println(new Pos(1, 2).canAttack(new Pos(2, 3)));
        
        // System.out.println(new Pos(2, 0).canAttack(new Pos(0, 2)));


        for (List<String> l : solveNQueens(5)) {
            System.out.println("#############");
            for (String s : l) {
                System.out.println(s);
            }
        }

        // [["Q....","..Q..","....Q",".Q...","...Q."],
        //  ["Q....","...Q.",".Q...","....Q","..Q.."],
        //  [".Q...","...Q.","Q....","..Q..","....Q"],
        //  [".Q...","....Q","..Q..","Q....","...Q."],
        //  ["..Q..","Q....","...Q.",".Q...","....Q"],
        //  ["..Q..","....Q",".Q...","...Q.","Q...."],
        //  ["...Q.","Q....","..Q..","....Q",".Q..."],
        //  ["...Q.",".Q...","....Q","..Q..","Q...."],
        //  ["....Q",".Q...","...Q.","Q....","..Q.."],
        //  ["....Q","..Q..","Q....","...Q.",".Q..."]]


        // n-1-(x-y)
        // 8 0-4
        // 7 0-3, 1-4
        // 6 0-2, 1-3, 2-4
        // 5 0-1, 1-2, 2-3, 3-4
        // 4 0-0, 1-1, 2-2, 3-3, 4-4
        // 3 1-0, 2-1, 3-2, 4-3
        // 2 2-0, 3-1, 4-2
        // 1 3-0, 4-1
        // 0 4-0 

        // [".Q...",
        //  "...Q.",
        //  "Q....",
        //  "..Q..",
        //  "....Q"]
    }
}
