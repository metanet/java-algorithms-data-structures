package problems.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// https://leetcode.com/problems/isomorphic-strings/
public class IsomorphicStrings {

    public static void main(String[] args) {
        
    }

    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }

        Set<Character> mapped = new HashSet<>();
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if (!map.containsKey(c1)) {
                if (mapped.add(c2)) {
                    map.put(c1, c2);
                } else {
                    return false;
                }
            } else if (map.get(c1) != c2) {
                return false;
            }
        }

        return true;
    }

}
