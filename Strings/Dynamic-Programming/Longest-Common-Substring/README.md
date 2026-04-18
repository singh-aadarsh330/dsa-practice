# Longest Common Substring

## 📌 Problem Statement
Given two strings `s1` and `s2`, find the length of the longest common substring.

> A substring is a contiguous sequence of characters.

## 🧪 Example
Input:
s1 = "abcde"
s2 = "abfce"

Output:
2

Explanation:
Longest common substring is "ab"

## 🚀 Approach: Dynamic Programming
We use a DP approach where:
dp[i][j] = length of longest common substring ending at s1[i-1] and s2[j-1]

### 🔁 Transition
If characters match:
dp[i][j] = dp[i-1][j-1] + 1

Else:
dp[i][j] = 0

We keep track of the maximum value in the DP table.

## ⚡ Optimized Approach
Instead of using full 2D DP, we optimize space:
Use two arrays:
- prev[]
- curr[]

This reduces space complexity from O(n*m) to O(m)

## ⏱️ Complexity Analysis
Time Complexity: O(n * m)
Space Complexity: O(m)

## 💻 Code (Java)
class Solution {
    public int longCommSubstr(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
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
            prev = curr.clone();
        }
        return maxLen;
    }
}

## 🎯 Key Takeaways
- This is a DP-based string problem
- Different from Longest Common Subsequence (LCS)
- Substring requires continuous matching
- Common interview question

## 🔥 Related Problems
- Longest Common Subsequence
- Edit Distance
- Longest Palindromic Substring

## 🏷 Tags
#DynamicProgramming #Strings #DP #InterviewPrep
