/*
 * Problem: Longest Common Substring
 * Given two strings s1 and s2, find the length of the longest common substring.
 * 
 * Approach: Dynamic Programming
 * Time Complexity: O(n * m)
 * Space Complexity: O(m) (optimized)
 */

class Solution {

    public int longCommSubstr(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        // Previous and current row for space optimization
        int[] prev = new int[m + 1];
        int[] curr = new int[m + 1];

        int maxLen = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    curr[j] = prev[j - 1] + 1;
                    maxLen = Math.max(maxLen, curr[j]);
                } else {
                    curr[j] = 0;
                }
            }

            // Move current row to previous
            prev = curr.clone();
        }

        return maxLen;
    }
}
