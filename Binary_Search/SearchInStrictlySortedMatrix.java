/**
 * Search in a Strictly Sorted 2D Matrix
 *
 * A matrix is strictly sorted if:
 * 1. Each row is sorted in strictly increasing order.
 * 2. The first element of each row is greater than the last element of the previous row.
 *
 * Approach:
 * - Treat the matrix as a virtual 1D sorted array.
 * - Apply binary search.
 * - Convert 1D index to 2D indices:
 *      row = mid / number_of_columns
 *      col = mid % number_of_columns
 *
 * Time Complexity: O(log(n * m))
 * Space Complexity: O(1)
 */
class Solution {

    public boolean searchMatrix(int[][] arr, int x) {

        int n = arr.length;
        int m = arr[0].length;

        int start = 0;
        int end = n * m - 1;

        while (start <= end) {

            // Prevent integer overflow
            int mid = start + (end - start) / 2;

            int row = mid / m;
            int col = mid % m;

            if (arr[row][col] == x) {
                return true;
            } 
            else if (arr[row][col] < x) {
                start = mid + 1;
            } 
            else {
                end = mid - 1;
            }
        }

        return false;
    }
}
