package problems.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/word-search-ii/
 */
public class WordSearchII {

    public static void main(String[] args) {
        char[][] board = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String[] words = {"oath", "pea", "eat", "rain"};

//        char[][] board = {{'a'}};
//        String[] words = {"a"};

        List<String> found = new WordSearchII().findWords(board, words);
        System.out.println(found);
    }

    private final Trie trie = new Trie();
    private char[][] board;
    private boolean[][] visited;
    private int m, n;
    private Set<String> remainingWords;

    public List<String> findWords(char[][] board, String[] words) {
        if (board == null || words == null || words.length < 1) {
            return Collections.emptyList();
        }

        for (String word : words) {
            trie.insert(word);
        }

        this.board = board;
        this.m = board.length;
        this.n = board[0].length;
        this.visited = new boolean[m][n];
        this.remainingWords = new HashSet<>(Arrays.asList(words));

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                proceed(i, j, new StringBuilder(), trie.head);
            }
        }

        Set<String> foundWords = new HashSet<>(Arrays.asList(words));
        foundWords.removeAll(remainingWords);

        return new ArrayList<>(foundWords);
    }

    private void proceed(int i, int j, StringBuilder sb, Node node) {
        if (node == null) {
            return;
        }

        if (node.terminal) {
            remainingWords.remove(sb.toString());
        }

        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j]) {
            return;
        }

        char ch = board[i][j];
        visited[i][j] = true;
        sb.append(ch);
        Node next = node.getChildAt(ch);

        proceed(i + 1, j, sb, next);
        proceed(i - 1, j, sb, next);
        proceed(i, j + 1, sb, next);
        proceed(i, j - 1, sb, next);

        sb.deleteCharAt(sb.length() - 1);
        visited[i][j] = false;
    }

    private static class Trie {
        final Node head = new Node(false);

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
        static final int CHAR_COUNT = 'z' - 'a' + 1;

        boolean terminal;
        Node[] children;

        Node(boolean terminal) {
            this.terminal = terminal;
            this.children = new Node[CHAR_COUNT];
        }

        Node getChildAt(char c) {
            return children[index(c)];
        }

        Node getOrCreateChildAt(char c) {
            int i = index(c);
            Node child = children[i];
            if (child == null) {
                child = new Node(false);
                children[i] = child;
            }

            return child;
        }

        int index(char c) {
            return c - 'a';
        }
    }

}
