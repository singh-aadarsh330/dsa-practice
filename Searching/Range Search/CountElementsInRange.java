import java.util.*;

/**
 * Problem:
 * Given an array and multiple range queries [L, R],
 * return the count of elements lying within each range.
 *
 * Approach:
 * - Sort the array.
 * - For each query, iterate through the array and count
 *   elements between L and R.
 *
 * Time Complexity:
 * - Sorting: O(n log n)
 * - Each query: O(n)
 *
 * Space Complexity: O(1) (excluding output list)
 */
class Solution {

    public ArrayList<Integer> cntInRange(int[] arr, int[][] queries) {

        ArrayList<Integer> counts = new ArrayList<>();

        // Sort the array to enable early stopping
        Arrays.sort(arr);

        // Process each query
        for (int i = 0; i < queries.length; i++) {
            int count = 0;

            for (int val : arr) {
                if (val < queries[i][0]) {
                    continue;
                } else if (val > queries[i][1]) {
                    break;
                } else {
                    count++;
                }
            }
            counts.add(count);
        }
        return counts;
    }
}
