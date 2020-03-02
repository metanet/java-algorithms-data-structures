package problems.leetcode;

import java.util.LinkedList;

/**
 * https://leetcode.com/problems/simplify-path/
 */
public class SimplifyPath {

    public static void main(String[] args) {
        String path = "/home/../home2";
        String simplified = simplifyPath(path);
        System.out.println(simplified);
    }

    public static String simplifyPath(String path) {
        if (path == null || path.length() == 0) {
            return "/";
        }

        LinkedList<String> stack = new LinkedList<>();

        for (String token : path.split("/")) {
            if (token.isEmpty() || token.equals(".")) {
                continue;
            }

            if (token.equals("..")) {
                if (stack.size() > 0) {
                    stack.removeLast();
                }
            } else {
                stack.add(token);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String token : stack) {
            sb.append("/").append(token);
        }

        if (sb.length() == 0) {
            sb.append("/");
        }

        return sb.toString();
    }

}
