/*
    Sort a Linked List that is in alternating ascending
    and descending order into non-decreasing order.

    Approach:
    - Traverse only the original list (up to original tail)
    - Remove nodes at odd positions (descending sequence)
    - Reverse the removed nodes on the fly
    - Append the reversed sequence after the original tail

    Time Complexity: O(n)
    Space Complexity: O(1)
*/

class Solution {

    public Node sort(Node head) {

        if (head == null || head.next == null)
            return head;

        Node tail = getTail(head);
        Node reversed = null;   // Head of reversed descending sequence
        Node temp = head;
        int index = -1;

        // Traverse only the original list
        while (temp != tail) {
            index++;
            Node current = temp;
            temp = temp.next;

            // Skip even positions (ascending part)
            if (index % 2 == 0)
                continue;

            // Remove node from current position
            head = deleteNode(head, current);

            // Reverse descending sequence using head insertion
            current.next = reversed;
            reversed = current;

            // Append reversed nodes after original tail
            tail.next = reversed;
        }

        return head;
    }

    // Returns the tail of the linked list
    private Node getTail(Node head) {
        Node temp = head;
        while (temp.next != null)
            temp = temp.next;
        return temp;
    }

    // Deletes a given node from the linked list
    private Node deleteNode(Node head, Node node) {
        Node curr = head, prev = null;

        while (curr != null) {
            if (curr == node)
                break;
            prev = curr;
            curr = curr.next;
        }

        // Node is never head as per problem constraints
        prev.next = curr.next;
        return head;
    }
}
