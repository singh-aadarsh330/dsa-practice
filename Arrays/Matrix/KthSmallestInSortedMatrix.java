/**
 * Problem: Kth Smallest Element in a Sorted Matrix
 *
 * The matrix is sorted in non-decreasing order both row-wise and column-wise.
 *
 * Approach:
 * - Perform binary search on the value range (not indices).
 * - For a given mid value, count how many elements are <= mid
 *   using a top-right corner traversal.
 *
 * Time Complexity: O(n log(max - min))
 * Space Complexity: O(1)
 */

class Solution {

    public int kthSmallest(int[][] arr, int k) {

        int rows = arr.length;
        int cols = arr[0].length;

        int start = arr[0][0];
        int end = arr[rows - 1][cols - 1];

        while (start <= end) {
            int mid = start + (end - start) / 2;

            int count = countSmallerOrEqual(arr, mid);

            if (count < k) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }

    private int countSmallerOrEqual(int[][] arr, int mid) {

        int rows = arr.length;
        int cols = arr[0].length;

        int i = 0, j = cols - 1;
        int count = 0;

        while (i < rows && j >= 0) {
            if (arr[i][j] <= mid) {
                count += (j + 1);
                i++;
            } else {
                j--;
            }
        }
        return count;
    }
}
