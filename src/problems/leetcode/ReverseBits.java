package problems.leetcode;
public class ReverseBits {
    /* https://leetcode.com/problems/reverse-bits/ */
    public int reverseBits(int n) {
        int result = 0;
        for(int i=0;i<32;i++){
            result<<=1;
            result+=n%2;
            n>>>=1;
        }
        return result;
    }
}
