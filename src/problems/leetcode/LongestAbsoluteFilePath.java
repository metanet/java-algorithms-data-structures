package problems.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/longest-absolute-file-path/
 */
public class LongestAbsoluteFilePath {

    public static void main(String[] args) {
        String all = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
        System.out.println(lengthLongestPath(all));
    }

    public static int lengthLongestPath(String input) {
        int i = 0, currentPathLen = 0, maxPathLen = 0;
        Deque<String> path = new ArrayDeque<>();
        while (true) {
            int j = input.indexOf("\n", i);
            String line = j != -1 ? input.substring(i, j) : input.substring(i);
            int indentationCount = indentationCount(line);
            String name = line.substring(indentationCount);
            boolean isFile = name.indexOf('.') != -1;

            if (indentationCount == 0) {
                if (isFile) {
                    if (name.length() > maxPathLen) {
                        maxPathLen = name.length();
                    }
                } else {
                    path.clear();
                    path.add(name);
                    currentPathLen = name.length();
                }
            } else {
                while (path.size() > indentationCount) {
                    currentPathLen -= path.removeLast().length();
                }

                if (isFile) {
                    if (maxPathLen < currentPathLen + name.length() + path.size()) {
                        maxPathLen = currentPathLen + name.length() + path.size();
                    }
                } else {
                    path.add(name);
                    currentPathLen += name.length();
                }
            }

            if (j == -1) {
                break;
            }

            i = j + 1;
        }

        return maxPathLen;
    }

    private static int indentationCount(String line) {
        int i = 0, count = 0;
        while (i < line.length()) {
            if (line.charAt(i++) == '\t') {
                count++;
            } else {
                break;
            }
        }

        return count;
    }

}
