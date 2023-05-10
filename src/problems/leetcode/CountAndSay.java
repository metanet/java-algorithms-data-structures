package problems.leetcode;

// https://leetcode.com/problems/count-and-say/
public class CountAndSay {

    // runtime: O(N)
    // space: O(N) due to recursive call stack
    public static String countAndSay(int n) {
        if (n < 2) {
            return "1";
        }

        String s = countAndSay(n - 1);
        StringBuilder sb = new StringBuilder();
        int current = s.charAt(0) - '0', count = 1;
        
        for (int i = 1; i < s.length(); i++) {
            int num = s.charAt(i) - '0';
            if (current == num) {
                count++;
            } else {
                sb.append(count).append(current);
                current = num;
                count = 1;
            }
        }

        sb.append(count).append(current);

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(countAndSay(2));
    }
    
}
