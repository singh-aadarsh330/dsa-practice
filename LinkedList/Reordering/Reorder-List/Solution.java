/*
Reorder a singly linked list in-place.

Given:
L0 → L1 → L2 → … → Ln-1 → Ln

Reordered:
L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …

Time Complexity : O(n)
Space Complexity: O(1)
*/

class Solution {

    public Node inPlace(Node head) {
        if (head == null || head.next == null) return head;

        // Step 1: Find the middle of the linked list
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse the second half
        Node second = reverse(slow.next);
        slow.next = null; // terminate first half

        // Step 3: Merge the two halves alternately
        Node first = head;
        while (second != null) {
            Node next1 = first.next;
            Node next2 = second.next;

            first.next = second;
            second.next = next1;

            first = next1;
            second = next2;
        }

        return head;
    }

    // Helper function to reverse a linked list
    private Node reverse(Node head) {
        Node prev = null;
        while (head != null) {
            Node next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}
