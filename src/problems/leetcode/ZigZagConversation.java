package problems.leetcode;

import java.util.ArrayList;

// https://leetcode.com/problems/zigzag-conversion/
public class ZigZagConversation {
    
    public static String convert(String s, int numRows) {
        if (numRows < 2) {
            return s;
        }
        
        ArrayList<ArrayList<Character>> rows = new ArrayList<ArrayList<Character>>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new ArrayList<>());
        }

        boolean forward = true;
        for (int i = 0, r = 0; i < s.length(); i++) {
            rows.get(r).add(s.charAt(i));
            if (forward) {
                if (r == numRows - 1) {
                    r--;
                    forward = false;
                } else {
                    r++;
                }
            } else {
                if (r == 0) {
                    r++;
                    forward = true;
                } else {
                    r--;
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (ArrayList<Character> row : rows) {
            for (char c : row) {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 3));
        System.out.println(convert("PAYPALISHIRING", 4));
        System.out.println(convert("AB", 1));
    }

}
