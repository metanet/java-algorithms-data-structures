package problems.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/design-search-autocomplete-system/
 */
public class DesignSearchAutoCompleteSystem {

    private static class Trie {

        static final int CHAR_COUNT = 'z' - 'a' + 2;

        final Node head = new Node('$', new Node[CHAR_COUNT]);
        final Map<String, Integer> queries = new HashMap<>();

        Trie() {
        }

        void insert(String text, int increment) {
            int newCount = queries.getOrDefault(text, 0) + increment;
            queries.put(text, newCount);
            Query query = new Query(text, newCount);
            Node node = head;
            for (int i = 0; i < text.length(); i++) {
                node.update(query);
                node = node.getOrCreateChildAt(text.charAt(i));
            }

            node.update(query);
        }
    }

    private static class Node {
        char ch;
        Node[] children;
        List<Query> topQueries = new ArrayList<>();

        Node(char ch, Node[] children) {
            this.ch = ch;
            this.children = children;
        }

        Node getOrCreateChildAt(char c) {
            int i = index(c);
            Node child = children[i];
            if (child == null) {
                child = new Node(c, new Node[Trie.CHAR_COUNT]);
                children[i] = child;
            }

            return child;
        }

        int index(char c) {
            if (c == ' ') {
                return 0;
            }

            return c - 'a' + 1;
        }

        void update(Query query) {
            Iterator<Query> it = topQueries.iterator();
            while (it.hasNext()) {
                Query q = it.next();
                if (q.text.equals(query.text)) {
                    it.remove();
                    break;
                }
            }

            topQueries.add(query);
            Collections.sort(topQueries);
            if (topQueries.size() > AutocompleteSystem.MAX_RESULT_COUNT) {
                topQueries.remove(topQueries.size() - 1);
            }
        }

        @Override
        public String toString() {
            return "Node{" +
                    "ch=" + ch +
                    ", topQueries=" + topQueries +
                    '}';
        }
    }

    private static class Query implements Comparable<Query> {
        final String text;
        final int count;

        Query(String text, int count) {
            this.text = text;
            this.count = count;
        }

        @Override
        public int compareTo(Query other) {
            int c = Integer.compare(this.count, other.count);
            return c != 0 ? -c : this.text.compareTo(other.text);
        }

        @Override
        public String toString() {
            return "Query{" +
                    "text='" + text + '\'' +
                    ", count=" + count +
                    '}';
        }
    }

    private static class AutocompleteSystem {

        private static final int MAX_RESULT_COUNT = 3;

        private Trie trie = new Trie();
        private final StringBuilder queryBuilder = new StringBuilder();
        private Node node = trie.head;

        public AutocompleteSystem(String[] sentences, int[] times) {
            for (int i = 0; i < sentences.length; i++) {
                trie.insert(sentences[i], times[i]);
            }
        }

        public List<String> input(char c) {
            if (c == '#') {
                String text = queryBuilder.toString();
                trie.insert(text, 1);
                queryBuilder.delete(0, text.length());
                node = trie.head;
                return Collections.emptyList();
            }

            queryBuilder.append(c);
            node = node.getOrCreateChildAt(c);

            List<String> queries = new ArrayList<>();
            for (Query q : node.topQueries) {
                queries.add(q.text);
                if (queries.size() == MAX_RESULT_COUNT) {
                    break;
                }
            }

            return queries;
        }
    }

    public static void main(String[] args) {
        String[] sentences = {"i love you", "island", "ironman", "i love leetcode"};
        int[] times = {5, 3, 2, 2};
        AutocompleteSystem autoComplete = new AutocompleteSystem(sentences, times);

        System.out.println(autoComplete.input('i'));

        System.out.println(autoComplete.input(' '));

        System.out.println(autoComplete.input('a'));

        autoComplete.input('#');


        System.out.println(autoComplete.input('i'));

        System.out.println(autoComplete.input(' '));

        System.out.println(autoComplete.input('a'));

        autoComplete.input('#');


        System.out.println(autoComplete.input('i'));

        System.out.println(autoComplete.input(' '));

        System.out.println(autoComplete.input('a'));

        autoComplete.input('#');

        System.out.println();
    }


}
