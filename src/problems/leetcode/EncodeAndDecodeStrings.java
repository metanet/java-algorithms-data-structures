package problems.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/encode-and-decode-strings/
 */
public class EncodeAndDecodeStrings {
    // Encodes a list of strings to a single string.
    public static String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        sb.append(strs.size()).append(",");
        for (String s : strs) {
            sb.append(s.length()).append(",").append(s);
        }

        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public static List<String> decode(String encoded) {
        int i = encoded.indexOf(',');
        int count = Integer.parseInt(encoded.substring(0, i));
        List<String> strs = new ArrayList<>(count);
        i++;
        while (i < encoded.length()) {
            int j = encoded.indexOf(',', i);
            int len = Integer.parseInt(encoded.substring(i, j));
            j++;
            strs.add(encoded.substring(j, j + len));
            i = j + len;
        }

        return strs;
    }

    public static void main(String[] args) {
        List<String> strs = Arrays.asList("ensar", "basri", "kahveci");
        String encoded = encode(strs);
        System.out.println(encoded);
        System.out.println(decode(encoded));
    }
}
