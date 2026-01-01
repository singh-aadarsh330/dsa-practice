/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode temp1 = l1, temp2 = l2;
        ListNode head = null, prev = null;
        int carry = 0;

        while (temp1 != null && temp2 != null) {
            int sum = carry + temp1.val + temp2.val;
            carry = sum / 10;
            ListNode node = new ListNode(sum % 10);

            if (head == null) {
                head = node;
                prev = node;
            } else {
                prev.next = node;
                prev = node;
            }

            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        while (temp1 != null) {
            int sum = carry + temp1.val;
            carry = sum / 10;
            ListNode node = new ListNode(sum % 10);

            if (head == null) {
                head = node;
                prev = node;
            } else {
                prev.next = node;
                prev = node;
            }

            temp1 = temp1.next;
        }

        while (temp2 != null) {
            int sum = carry + temp2.val;
            carry = sum / 10;
            ListNode node = new ListNode(sum % 10);

            if (head == null) {
                head = node;
                prev = node;
            } else {
                prev.next = node;
                prev = node;
            }

            temp2 = temp2.next;
        }

        if (carry != 0) {
            ListNode node = new ListNode(carry);
            if (head == null) {
                head = node;
            } else {
                prev.next = node;
            }
        }

        return head;
    }
}
