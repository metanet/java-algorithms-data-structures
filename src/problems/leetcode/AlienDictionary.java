package problems.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/alien-dictionary/
 */
public class AlienDictionary {

    private final Map<Character, Set<Character>> letters = new HashMap<>();
    private final StringBuilder order = new StringBuilder();
    private final boolean[] done = new boolean[256];

    public String alienOrder(String[] words) {
        try {
            for (String word : words) {
                for (int i = 0; i < word.length(); i++) {
                    letters.computeIfAbsent(word.charAt(i), k -> new HashSet<>());
                }
            }

            fillEdges(words, 0, words.length, 0);

            for (char c : letters.keySet()) {
                if (!done[c]) {
                    dfs(c, new boolean[256]);
                }
            }

            return order.reverse().toString();
        } catch (IllegalStateException e) {
            return "";
        }
    }

    private void fillEdges(String[] words, int startWordIndex, int endWordIndex, int letterIndex) {
        if (startWordIndex == endWordIndex - 1) {
            return;
        }

        int k = startWordIndex;
        String prevWord = words[startWordIndex];
        for (int wordIndex = startWordIndex + 1; wordIndex < endWordIndex; wordIndex++) {
            if (letterIndex >= prevWord.length()) {
                k = wordIndex;
                continue;
            }

            String currentWord = words[wordIndex];
            char ch1 = prevWord.charAt(letterIndex), ch2 = currentWord.charAt(letterIndex);
            if (ch2 != ch1) {
                letters.get(ch1).add(ch2);
                fillEdges(words, k, wordIndex, letterIndex + 1);
                k = wordIndex;
            }

            prevWord = currentWord;
        }

        fillEdges(words, k, endWordIndex, letterIndex + 1);
    }

    private void dfs(char ch, boolean[] path) {
        path[ch] = true;

        for (char neighbour : letters.get(ch)) {
            if (path[neighbour]) {
                throw new IllegalStateException("cycle");
            } else if (!done[neighbour]) {
                dfs(neighbour, path);
            }
        }

        done[ch] = true;
        order.append(ch);
        path[ch] = false;
    }

    public static void main(String[] args) {
        String[] words = new String[]{"wrt", "wrf", "er", "ett", "rftt"};
//        String[] words = new String[]{"z", "x"};
//        String[] words = new String[]{"bsusz","rhn","gfbrwec","kuw","qvpxbexnhx","gnp","laxutz","qzxccww"};
//        String[] words = new String[]{"z", "z"};
        String order = new AlienDictionary().alienOrder(words);
        System.out.println("ORDER: " + order);
    }
}
