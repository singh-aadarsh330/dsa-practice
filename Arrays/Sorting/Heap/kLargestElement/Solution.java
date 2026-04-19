import java.util.*;

class Solution {
    public ArrayList<Integer> kLargest(int[] arr, int k) {
        // Min Heap (Priority Queue)
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // Maintain heap of size k
        for (int num : arr) {
            pq.add(num);
            if (pq.size() > k) {
                pq.poll(); // remove smallest
            }
        }

        // Store result
        ArrayList<Integer> result = new ArrayList<>();

        while (!pq.isEmpty()) {
            result.add(pq.poll());
        }

        // Convert to descending order
        Collections.reverse(result);

        return result;
    }
}
