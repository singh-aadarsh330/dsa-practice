/*
Remove all nodes that have duplicate values from an unsorted linked list.

Example:
Input : 1 -> 2 -> 3 -> 2 -> 4 -> 1
Output: 3 -> 4

Approach:
1. Count the frequency of each value using HashMap.
2. Traverse the list again and remove nodes whose frequency > 1.
3. Use a dummy node to handle head deletions cleanly.

Time Complexity : O(n)
Space Complexity: O(n)
*/

import java.util.HashMap;

class Solution {

    public Node removeAllDuplicates(Node head) {

        if (head == null) return null;

        // Step 1: Count frequency of each node value
        HashMap<Integer, Integer> freq = new HashMap<>();
        Node temp = head;

        while (temp != null) {
            freq.put(temp.data, freq.getOrDefault(temp.data, 0) + 1);
            temp = temp.next;
        }

        // Step 2: Remove nodes with frequency > 1
        Node dummy = new Node(0);
        dummy.next = head;

        Node prev = dummy;
        temp = head;

        while (temp != null) {
            if (freq.get(temp.data) > 1) {
                prev.next = temp.next; // delete current node
            } else {
                prev = temp; // keep node
            }
            temp = temp.next;
        }

        return dummy.next;
    }
}
