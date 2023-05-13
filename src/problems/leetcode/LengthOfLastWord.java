package problems.leetcode;

import java.util.Arrays;
import java.util.Collections;

// https://leetcode.com/problems/length-of-last-word/submissions/
public class LengthOfLastWord {
    
    // runtime: O(N)
    // space: O(N)
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }

        String[] words = s.trim().split(" ");
        Collections.reverse(Arrays.asList(words));
        for (String w : words) {
            if (!w.isEmpty()) {
                return w.length();
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        
    }

}
