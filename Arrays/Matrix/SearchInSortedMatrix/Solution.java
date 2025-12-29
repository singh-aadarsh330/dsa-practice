/**
 * Problem: Search in a row-wise and column-wise sorted matrix
 *
 * Approach:
 * - Start from the top-right corner of the matrix.
 * - If the current element is equal to the target, return true.
 * - If the current element is greater than the target, move left.
 * - If the current element is smaller than the target, move down.
 *
 * Time Complexity: O(rows + columns)
 * Space Complexity: O(1)
 */

class Solution {

    public static boolean matSearch(int[][] arr, int x) {

        // Edge case: empty matrix
        if (arr == null || arr.length == 0 || arr[0].length == 0) {
            return false;
        }

        int rows = arr.length;
        int cols = arr[0].length;

        // Start from top-right corner
        int i = 0;
        int j = cols - 1;

        while (i < rows && j >= 0) {

            if (arr[i][j] == x) {
                return true;
            } 
            else if (arr[i][j] > x) {
                j--;        // move left
            } 
            else {
                i++;        // move down
            }
        }

        return false;
    }
}
