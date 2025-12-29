// User function Template for Java
/*
class Node {
    Node next;
    int val;

    public Node(int data) {
        val = data;
        next = null;
    }
}
*/

class Solution {

    Node primeList(Node head) {

        Node temp = head;
        while (temp != null) {
            if (!isPrime(temp.val)) {
                temp.val = nearestPrime(temp.val);
            }
            temp = temp.next;
        }
        return head;
    }

    // Optimized prime check: O(sqrt(n))
    boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;

        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0)
                return false;
        }
        return true;
    }

    // Finds the nearest prime to n
    int nearestPrime(int n) {
        int diff = 1;
        while (true) {
            if (n - diff >= 2 && isPrime(n - diff))
                return n - diff;
            if (isPrime(n + diff))
                return n + diff;
            diff++;
        }
    }
}
