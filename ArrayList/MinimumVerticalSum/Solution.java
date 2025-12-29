// User function Template for Java

import java.util.ArrayList;

/**
 * Finds the minimum vertical (column-wise) sum in a ragged 2D ArrayList.
 * Rows may have different sizes.
 */
class Solution {

    public static int minimumVerticalSum(ArrayList<ArrayList<Integer>> arr) {

        int maxColumns = 0;

        // Determine the maximum number of columns
        for (ArrayList<Integer> row : arr) {
            maxColumns = Math.max(maxColumns, row.size());
        }

        int minimumSum = Integer.MAX_VALUE;

        // Calculate column-wise sums
        for (int col = 0; col < maxColumns; col++) {
            int columnSum = 0;

            for (ArrayList<Integer> row : arr) {
                if (col < row.size()) {
                    columnSum += row.get(col);
                }
            }

            minimumSum = Math.min(minimumSum, columnSum);
        }

        return minimumSum;
    }
}
