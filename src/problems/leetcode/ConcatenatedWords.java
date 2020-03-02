package problems.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/concatenated-words/
 */
public class ConcatenatedWords {

    public static void main(String[] args) {
        String[] words = {"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"};
        System.out.println(findAllConcatenatedWordsInADict(words));
    }

    public static List<String> findAllConcatenatedWordsInADict(String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        List<String> concatenated = new ArrayList<>();
        for (String word : words) {
            if (isConcatenated(trie, word, 0) > 1) {
                concatenated.add(word);
            }
        }

        return concatenated;
    }

    private static int isConcatenated(Trie trie, String word, int i) {
        if (i == word.length()) {
            return 0;
        }

        Node node = trie.head;
        while (i < word.length()) {
            node = node.getChildAt(word.charAt(i++));
            if (node == null) {
                return 0;
            } else if (node.terminal) {
                int subCount = isConcatenated(trie, word, i);
                if (subCount > 0) {
                    return subCount + 1;
                }
            }
        }

        return node.terminal ? 1 : 0;
    }

    private static class Trie {

        static final int CHAR_COUNT = 'z' - 'a' + 1;

        final Node head = new Node(false, new Node[CHAR_COUNT]);

        Trie() {
        }

        void insert(String word) {
            Node node = head;
            int i = 0;
            while (i < word.length()) {
                char c = word.charAt(i++);
                node = node.getOrCreateChildAt(c);
            }

            node.terminal = true;
        }
    }

    private static class Node {
        boolean terminal;
        Node[] children;

        Node(boolean terminal, Node[] children) {
            this.terminal = terminal;
            this.children = children;
        }

        Node getChildAt(char c) {
            return children[index(c)];
        }

        Node getOrCreateChildAt(char c) {
            int i = index(c);
            Node child = children[i];
            if (child == null) {
                child = new Node(false, new Node[Trie.CHAR_COUNT]);
                children[i] = child;
            }

            return child;
        }

        int index(char c) {
            return c - 'a';
        }
    }

}
