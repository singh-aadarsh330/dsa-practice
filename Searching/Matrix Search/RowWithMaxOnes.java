import java.util.*;

/**
 * Problem:
 * Given a binary matrix where each row is sorted (0s followed by 1s),
 * find the index of the row with the maximum number of 1s.
 * If no 1 is present in any row, return -1.
 *
 * Approach:
 * - For each row, count the number of leading zeros.
 * - The row with the minimum number of leading zeros
 *   will have the maximum number of 1s.
 *
 * Time Complexity: O(rows × cols)
 * Space Complexity: O(1)
 */
class Solution {

    public int rowWithMax1s(int[][] arr) {

        int rows = arr.length;
        int cols = arr[0].length;

        int minZeros = Integer.MAX_VALUE;
        int resultRow = -1;

        for (int i = 0; i < rows; i++) {
            int zeros = 0;

            // Count leading zeros in current row
            for (int j = 0; j < cols; j++) {
                if (arr[i][j] == 1) break;
                zeros++;
            }

            // Update row with minimum leading zeros
            if (zeros < minZeros) {
                minZeros = zeros;
                resultRow = i;
            }
        }
        return resultRow;
    }
}
