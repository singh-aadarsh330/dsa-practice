// Problem: Kth Missing Element in Array
// Platform: GeeksforGeeks
// Difficulty: Medium

class KthMissingElement {
    public int kthMissing(int[] arr, int k) {
        int index = 0;
        int count = 0;
        int prev = 0;

        while (index < arr.length) {
            if (arr[index] == prev + 1) {
                prev++;
                index++;
            } else {
                prev++;
                count++;
                if (count == k) {
                    return prev;
                }
            }
        }
        return arr[arr.length - 1] + (k - count);
    }
}
