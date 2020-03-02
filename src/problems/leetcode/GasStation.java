package problems.leetcode;

/**
 * https://leetcode.com/problems/gas-station/
 * https://www.youtube.com/watch?v=nTKdYm_5-ZY
 */
public class GasStation {

    public static void main(String[] args) {
        int[] gas = {5, 1, 2, 3, 4};
        int[] cost = {4, 4, 1, 5, 1};

        System.out.println(canCompleteCircuit(gas, cost));
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int totalTank = 0, currentTank = 0, start = 0;
        for (int i = 0; i < gas.length; ++i) {
            int diff = gas[i] = cost[i];
            totalTank += diff;
            currentTank += diff;

            if (currentTank < 0) {
                currentTank = 0;
                start = i + 1;
            }
        }

        return totalTank >= 0 ? start : -1;
    }

    public static int canCompleteCircuitON2(int[] gas, int[] cost) {
        for (int i = 0; i < gas.length; i++) {
            if (canTravel(gas, cost, i)) {
                return i;
            }
        }

        return -1;
    }

    private static boolean canTravel(int[] gas, int[] cost, int start) {
        int tank = gas[start], i = start;
        while (tank >= cost[i]) {
            tank -= cost[i];
            i = (i + 1) % gas.length;
            if (i == start) {
                return true;
            }

            tank += gas[i];
        }

        return false;
    }

}
