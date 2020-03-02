package problems.leetcode;

/**
 * https://leetcode.com/problems/implement-trie-prefix-tree/
 */
public class ImplementTrie {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("ensar");
        System.out.println(trie.search("ensar"));
        System.out.println(trie.search("ensa"));
        System.out.println(trie.search("ensab"));
        System.out.println(trie.startsWith("ensar"));
        System.out.println(trie.startsWith("ens"));
        System.out.println(trie.startsWith("ensx"));
        System.out.println(trie.search(""));
        System.out.println(trie.startsWith(""));
        trie.insert("");
        System.out.println(trie.search(""));
        System.out.println();
    }

    private static class Trie {

        static final int CHAR_COUNT = 'z' - 'a' + 1;

        final Node head = new Node(false, new Node[CHAR_COUNT]);

        /**
         * Initialize your data structure here.
         */
        public Trie() {

        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            Node node = head;
            int i = 0;
            while (i < word.length()) {
                char c = word.charAt(i++);
                node = node.getOrCreateChildAt(c);
            }

            node.terminal = true;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            Node node = head;
            int i = 0;
            while (i < word.length()) {
                char c = word.charAt(i++);
                node = node.getChildAt(c);
                if (node == null) {
                    return false;
                }
            }

            return node.terminal;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            Node node = head;
            int i = 0;
            while (i < prefix.length()) {
                char c = prefix.charAt(i++);
                node = node.getChildAt(c);
                if (node == null) {
                    return false;
                }
            }

            return true;
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
