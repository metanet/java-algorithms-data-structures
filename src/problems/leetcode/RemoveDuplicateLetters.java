package problems.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/remove-duplicate-letters/
 */
public class RemoveDuplicateLetters {

    public static String removeDuplicateLettersStack(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        // this lets us keep track of what's in our solution in O(1) time
        Set<Character> seen = new HashSet<>();

        // this will let us know if there are any more instances of s[i] left in s
        Map<Character, Integer> lastOccurrence = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            lastOccurrence.put(s.charAt(i), i);
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // we can only try to add c if it's not already in our solution
            // this is to maintain only one of each character
            if (!seen.contains(c)) {
                // if the last letter in our solution:
                //     1. exists
                //     2. is greater than c so removing it will make the string smaller
                //     3. it's not the last occurrence
                // we remove it from the solution to keep the solution optimal
                while (!stack.isEmpty() && c < stack.peekLast() && lastOccurrence.get(stack.peekLast()) > i) {
                    seen.remove(stack.removeLast());
                }
                seen.add(c);
                stack.addLast(c);
            }
        }

        StringBuilder sb = new StringBuilder(stack.size());
        for (char c : stack) {
            sb.append(c);
        }

        return sb.toString();
    }

    public static String removeDuplicateLettersGreedy(String s) {
        // find pos - the index of the leftmost letter in our solution
        // we create a counter and end the iteration once the suffix doesn't have each unique character
        // pos will be the index of the smallest character we encounter before the iteration ends
        int[] counts = new int['z' - 'a' + 1];
        int pos = 0;
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < s.charAt(pos)) {
                pos = i;
            }

            if (--counts[s.charAt(i) - 'a'] == 0) {
                break;
            }
        }

        // our answer is the leftmost letter plus the recursive call on the remainder of the string
        // note that we have to get rid of further occurrences of s[pos] to ensure that there are no duplicates
        if (s.length() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(pos));

        String remaining = s.substring(pos + 1).replaceAll(String.valueOf(s.charAt(pos)), "");
        sb.append(removeDuplicateLettersGreedy(remaining));

        return sb.toString();
    }

    public static String removeDuplicateLetters(String s) {
        Map<Character, LinkedList<Integer>> occurrences = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            occurrences.computeIfAbsent(ch, c -> new LinkedList<>()).addLast(i);
        }

        List<Character> chars = new ArrayList<>(occurrences.keySet());
        Collections.sort(chars);

        StringBuilder sb = new StringBuilder();

        while (chars.size() > 0) {
            Iterator<Character> it = chars.iterator();

            while (it.hasNext()) {
                char ch = it.next();
                int chIndex = occurrences.get(ch).getFirst();
                boolean valid = true;
                for (Map.Entry<Character, LinkedList<Integer>> e : occurrences.entrySet()) {
                    if (e.getKey() == ch) {
                        continue;
                    }

                    boolean exists = false;
                    for (Integer index : e.getValue()) {
                        if (index > chIndex) {
                            exists = true;
                            break;
                        }
                    }

                    if (!exists) {
                        valid = false;
                        break;
                    }
                }

                if (valid) {
                    sb.append(ch);
                    it.remove();
                    occurrences.remove(ch);
                    for (LinkedList<Integer> indices : occurrences.values()) {
                        Iterator<Integer> it2 = indices.iterator();
                        while (it2.hasNext()) {
                            int index = it2.next();
                            if (index < chIndex) {
                                it2.remove();
                            } else {
                                break;
                            }
                        }
                    }
                    break;
                }
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String input = "cbacdcbc";
        System.out.println(removeDuplicateLettersStack(input));
        System.out.println(removeDuplicateLetters(input));
        System.out.println(removeDuplicateLettersGreedy(input));
    }

    /*
    Input: "bcabc"
    Output: "abc"

    Input: "cbacdcbc"
    Output: "acdb"

    c 0 3 5 7
    b 1 6
    a 2
    d 4






     */

}
