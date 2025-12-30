// User function Template for Java

/**
 * Returns the smallest prime number strictly greater than n.
 */
class Solution {

    public static int nextPrime(int n) {

        if (n < 2) {
            return 2;
        }

        int candidate = n + 1;

        while (!isPrime(candidate)) {
            candidate++;
        }

        return candidate;
    }

    private static boolean isPrime(int num) {

        if (num < 2) {
            return false;
        }

        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
