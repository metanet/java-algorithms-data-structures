package problems.leetcode;

// https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/editorial/
public class FindTheIndexOfTheFirstOccurrenceInAString {
    
    // runtime: O(N*M) where N is len of haystack and M is len of needle
    // space: O(1)
    public int strStr(String haystack, String needle) {
        // haystack = "sazbutsad", needle = "sad"
        // "sazbutsad"
        //  i
        //  k
        //  j
        //   k
        //   j
        //
        //    k
        //    j
        //   i

        for (int i = 0; i < haystack.length(); i++) {
            boolean match = true;
            for (int j = 0; j < needle.length(); j++) {
                if (i + j >= haystack.length() || haystack.charAt(i + j) != needle.charAt(j)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return i;
            }
        } 

        return -1;
    }

}
