package problems.leetcode;

public class BitShifts {
    public static void main(String[] args) {
        
        // signed left shift: 
        // - a << b
        // - shift bits of a by b times and put 0 to the empty space in the right bits
        // - equivalent to multiplication by 2^b
        {
            System.out.println("Signed left shift");
            int x = 12;
            x = x << 3;
            System.out.println("x=" + x + ", binary x=" + Integer.toBinaryString(x));
            x = -3;
            x = x << 2;
            System.out.println("x=" + x + ", binary x=" + Integer.toBinaryString(x));
            x = Integer.MIN_VALUE / 2; // 11000000000000000000000000000000
            System.out.println("x=" + x + ", binary x=" + Integer.toBinaryString(x));
            x = x << 1; // Integer.MIN_VALUE -2147483648 which is 10000000000000000000000000000000
            System.out.println("x=" + x + ", binary x=" + Integer.toBinaryString(x));
            x = x << 1; // 0 which is 00000000000000000000000000000000
            System.out.println("x=" + x + ", binary x=" + Integer.toBinaryString(x));
            x = Integer.MAX_VALUE; // 01111111111111111111111111111111
            System.out.println("x=" + x + ", binary x=" + Integer.toBinaryString(x));
            x = x << 1; // -2 which is 11111111111111111111111111111110
            System.out.println("x=" + x + ", binary x=" + Integer.toBinaryString(x));
            x = 1;
            x = x << 30; // 1000000000000000000000000000000
            System.out.println("x=" + x + ", binary x=" + Integer.toBinaryString(x));
            x = x << 1; // -2147483648 which is -2147483648
            System.out.println("x=" + x + ", binary x=" + Integer.toBinaryString(x));
        }

        // signed right shift:
        // - a >> b
        // - shift a's bits to right by b times. 
        // - the left bits are filled with the value of the leftmost sign bit, 
        //   which is 1 if a is negative, or filled with 0 if a is positive.
        {
            System.out.println("Signed right shift");
            int x = 12;
            x = x >> 2;
            System.out.println("x=" + x + ", binary x=" + Integer.toBinaryString(x));
            x = -12;
            x = x >> 2;
            System.out.println("x=" + x + ", binary x=" + Integer.toBinaryString(x));
            x = Integer.MIN_VALUE;
            x = x >> 2;
            System.out.println("x=" + x + ", binary x=" + Integer.toBinaryString(x));
            x = -1;
            System.out.println("x=" + x + ", binary x=" + Integer.toBinaryString(x));
            x = x >> 2; // still -1
            System.out.println("x=" + x + ", binary x=" + Integer.toBinaryString(x));
        }

        // unsigned right shift:
        // a >>> b
        // shift a's bits to right by b times, fill the left bits with 0 irrespective of the leftmost sign bit.
        {
            System.out.println("Unsigned right shift");
            int x = 12;
            x = x >>> 2; // 3
            System.out.println("x=" + x + ", binary x=" + Integer.toBinaryString(x));
            x = -1; // 11111111111111111111111111111111
            System.out.println("x=" + x + ", binary x=" + Integer.toBinaryString(x));
            x = x >>> 1; // Integer.MAX_VALUE which is 01111111111111111111111111111111
            System.out.println("x=" + x + ", binary x=" + Integer.toBinaryString(x));
            x = -4; // 11111111111111111111111111111100
            System.out.println("x=" + x + ", binary x=" + Integer.toBinaryString(x));
            x = x >>> 1; // Integer.MAX_VALUE - 1 which is 01111111111111111111111111111110
            System.out.println("x=" + x + ", binary x=" + Integer.toBinaryString(x));
        }

        {
            byte x = 5;
            x = (byte) (x << 1);  // cast is needed since (x << 1) returns an int
        }

        {
            long x = Integer.MAX_VALUE;
            System.out.println("x=" + x + ", binary x=" + Long.toBinaryString(x));
            x = x << 1;
            System.out.println("x=" + x + ", binary x=" + Long.toBinaryString(x));
        }
        
    }
}
