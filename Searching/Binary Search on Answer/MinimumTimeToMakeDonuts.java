import java.util.*;

/**
 * Problem:
 * Given multiple chefs with different ranks, find the minimum time
 * required to make at least N donuts.
 *
 * Each chef with rank r takes:
 *  r minutes for 1st donut,
 *  2r minutes for 2nd donut,
 *  3r minutes for 3rd donut, and so on.
 *
 * All chefs work simultaneously.
 *
 * Approach:
 * - Use Binary Search on Answer (time).
 * - For a given time, check if chefs can collectively make >= N donuts.
 *
 * Time Complexity: O(m * sqrt(T))
 * Space Complexity: O(1)
 */
class Solution {

    public int minTime(int[] arr, int n) {

        // Number of chefs
        int m = arr.length;

        // Sort ranks to get the slowest chef easily
        Arrays.sort(arr);

        // Minimum possible time
        int low = 0;

        // Maximum possible time:
        // Slowest chef makes all donuts alone
        int high = arr[m - 1] * n * (n + 1) / 2;

        // Binary search on time
        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Check if mid time is sufficient
            if (canMakeAtLeastNDonuts(arr, mid, n)) {
                high = mid - 1;   // try to minimize time
            } else {
                low = mid + 1;    // need more time
            }
        }

        // 'low' points to the minimum feasible time
        return low;
    }

    /**
     * Checks whether at least n donuts can be made
     * within the given time.
     */
    private boolean canMakeAtLeastNDonuts(int[] arr, int time, int n) {

        int total = 0;

        // For each chef
        for (int rank : arr) {
            int currTime = 0;
            int donuts = 0;

            // Count donuts this chef can make in 'time'
            while (true) {
                currTime += rank * (donuts + 1);
                if (currTime > time) break;
                donuts++;
            }

            total += donuts;

            // Early exit if requirement is met
            if (total >= n) return true;
        }
        return false;
    }
}
