package problems.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/text-justification/
 */
public class TextJustification {

    public static void main(String[] args) {
        String[] words = {"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"};
        int maxWidth = 20;

        List<String> justified = fullJustify(words, maxWidth);
        for (String s : justified) {
            System.out.println("|" + s + "|");
        }
    }

    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> justified = new ArrayList<>();
        List<List<String>> lines = parseLines(words, maxWidth);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int letterCount = line.stream().mapToInt(String::length).sum();
            int lineWordCount = line.size(), reqSpaceCount = maxWidth - letterCount, spaceCount = lineWordCount - 1;
            StringBuilder sb = new StringBuilder();
            if (lineWordCount == 1) {
                sb.append(line.get(0));
                appendSpaces(sb, reqSpaceCount);
            } else if (i == lines.size() - 1) {
                sb.append(line.get(0));
                for (int j = 1; j < lineWordCount; j++) {
                    appendSpaces(sb, 1);
                    sb.append(line.get(j));
                }
                appendSpaces(sb, reqSpaceCount - spaceCount);
            } else {
                sb.append(line.get(0));
                int[] spaces = populateSpaces(lineWordCount, reqSpaceCount, spaceCount);
                for (int j = 1; j < lineWordCount; j++) {
                    appendSpaces(sb, spaces[j - 1]);
                    sb.append(line.get(j));
                }
            }

            justified.add(sb.toString());
        }

        return justified;
    }

    private static List<List<String>> parseLines(String[] words, int maxWidth) {
        List<List<String>> lines = new ArrayList<>();
        List<String> line = new ArrayList<>();
        int lineLen = 0;

        for (String word : words) {
            int space = lineLen == 0 ? 0 : 1;
            if (lineLen + word.length() + space <= maxWidth) {
                line.add(word);
                lineLen += word.length() + space;
            } else {
                lines.add(line);
                line = new ArrayList<>();
                line.add(word);
                lineLen = word.length();
            }
        }

        if (lineLen > 0) {
            lines.add(line);
        }

        return lines;
    }

    private static void appendSpaces(StringBuilder sb, int spaceCount) {
        for (int i = 0; i < spaceCount; i++) {
            sb.append(' ');
        }
    }

    private static int[] populateSpaces(int lineWordCount, int reqSpaceCount, int spaceCount) {
        int n = lineWordCount - 1;
        int[] spaces = new int[n];
        int avgSpace = reqSpaceCount / n;
        Arrays.fill(spaces, avgSpace);
        int remaining = reqSpaceCount - avgSpace * n;
        int i = 0, j = 0;
        while (j++ < remaining) {
            spaces[i++]++;
        }
        return spaces;
    }

}
