package problems.leetcode;

/**
 * https://leetcode.com/problems/validate-ip-address/
 */
public class ValidateIPAddress {

    public static void main(String[] args) {
        String ip = "2001:0db8:85a3:0:0:8A2E:0370:7334:";
        System.out.println(validIPAddress(ip));
    }

    public static String validIPAddress(String IP) {
        if (isIPV4(IP)) {
            return "IPv4";
        } else if (isIPV6(IP)) {
            return "IPv6";
        } else {
            return "Neither";
        }
    }

    private static boolean isIPV4(String ip) {
        if (ip == null || ip.length() == 0 || ip.charAt(ip.length() - 1) == '.') {
            return false;
        }

        String[] tokens = ip.split("\\.");
        if (tokens.length != 4) {
            return false;
        }

        for (String token : tokens) {
            try {
                int num = Integer.parseInt(token);
                if (num >= 256 || (num > 0 && token.charAt(0) == '0') || (num == 0 && token.length() > 1) || token.charAt(0) == '-') {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return true;
    }

    private static boolean isIPV6(String ip) {
        if (ip == null || ip.length() == 0 || ip.charAt(ip.length() - 1) == ':') {
            return false;
        }

        String[] tokens = ip.split(":");
        if (tokens.length != 8) {
            return false;
        }

        for (String token : tokens) {
            try {
                int num = Integer.parseInt(token, 16);
                if (num >= 2 << 15 || (token.length() > 4 && token.charAt(0) == '0') || token.charAt(0) == '-') {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return true;
    }

}
