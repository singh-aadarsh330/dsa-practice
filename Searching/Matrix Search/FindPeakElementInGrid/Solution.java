import java.util.*;

/**
 * Problem:
 * Find a peak element in a 2D grid.
 * An element is a peak if it is greater than or equal to its
 * four neighbors (top, bottom, left, right).
 * Missing neighbors are treated as negative infinity.
 *
 * Approach:
 * - Apply Binary Search on columns.
 * - For each mid column, find the row index of the maximum element.
 * - Compare it with its left and right neighbors.
 * - Move in the direction of a larger neighbor.
 *
 * Time Complexity: O(rows * log(cols))
 * Space Complexity: O(1)
 */
class Solution {

    public ArrayList<Integer> findPeakGrid(int[][] arr) {

        int rows = arr.length;
        int cols = arr[0].length;

        int low = 0, high = cols - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Find the row of maximum element in mid column
            int maxRow = 0;
            for (int i = 0; i < rows; i++) {
                if (arr[i][mid] > arr[maxRow][mid]) {
                    maxRow = i;
                }
            }

            int curr = arr[maxRow][mid];
            int left  = mid > 0        ? arr[maxRow][mid - 1] : Integer.MIN_VALUE;
            int right = mid < cols - 1 ? arr[maxRow][mid + 1] : Integer.MIN_VALUE;

            // Check peak condition
            if (curr >= left && curr >= right) {
                ArrayList<Integer> peak = new ArrayList<>();
                peak.add(maxRow);
                peak.add(mid);
                return peak;
            }

            // Move search space
            if (left > curr) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        // Peak is guaranteed to exist, this is just for safety
        return new ArrayList<>();
    }
}
