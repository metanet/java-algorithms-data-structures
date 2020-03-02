package problems.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/most-common-word/
 */
public class MostCommonWord {

    public static void main(String[] args) {
        String paragraph = "Bob";
        System.out.println(mostCommonWord(paragraph, new String[]{"hit"}));
    }

    public static String mostCommonWord(String paragraph, String[] banned) {
        if (paragraph == null) {
            return null;
        }

        Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));
        Map<String, Integer> wordCounts = new HashMap<>();

        paragraph = paragraph.replaceAll("[,!.;'?]", " ");

        int i;
        while ((i = paragraph.indexOf(" ")) != -1) {
            String word = paragraph.substring(0, i).toLowerCase();
            if (word.length() > 0 && !bannedSet.contains(word)) {
                wordCounts.merge(word, 1, Integer::sum);
            }

            paragraph = paragraph.substring(i + 1);
        }

        String word = paragraph.toLowerCase();
        if (word.length() > 0 && !bannedSet.contains(word)) {
            wordCounts.merge(word, 1, Integer::sum);
        }

        String mostCommonWord = null;
        int wordCount = -1;
        for (Map.Entry<String, Integer> e : wordCounts.entrySet()) {
            if (e.getValue() > wordCount) {
                mostCommonWord = e.getKey();
                wordCount = e.getValue();
            }
        }

        return mostCommonWord;
    }

}
