# Dynamic Programming (DP) - String Problems

## 📌 Introduction
Dynamic Programming (DP) is an optimization technique used to solve problems by breaking them into smaller overlapping subproblems and storing their results to avoid recomputation.

In string problems, DP is widely used for pattern matching, subsequences, substrings, and transformations.

---

## 🧠 When to Use DP?
Use DP when:
- Problem has **overlapping subproblems**
- Problem shows **optimal substructure**
- Recursion leads to repeated calculations

---

## ⚙️ Common DP Patterns in Strings

### 1. Longest Common Substring
- Continuous matching required
- dp[i][j] depends on dp[i-1][j-1]

### 2. Longest Common Subsequence (LCS)
- Not necessarily continuous
- dp[i][j] = max of choices

### 3. Edit Distance
- Minimum operations (insert, delete, replace)

### 4. Palindromic Substrings
- Check symmetry using DP

---

## 🔁 General DP Approach

1. Define DP state  
   Example:
   dp[i][j] → answer for first i and j characters  

2. Write transition  
   Based on match / mismatch  

3. Initialize base cases  

4. Build solution using loops  

---

## 📊 Complexity

| Problem Type        | Time Complexity | Space Complexity |
|--------------------|----------------|------------------|
| Typical DP Strings | O(n × m)       | O(n × m) or O(m) |

---

## 🚀 Optimization Techniques

- Space optimization using 1D arrays  
- Memoization (Top-down DP)  
- Tabulation (Bottom-up DP)  

---

## 🎯 Key Takeaways

- DP is crucial for **string-based interview problems**
- Focus on **state definition + transition**
- Practice pattern recognition

---

## 🔥 Problems Covered

- Longest Common Substring  
- Longest Common Subsequence  
- Edit Distance  
- Palindromic Substrings  

---

## 🏷 Tags
#DynamicProgramming #Strings #DP #InterviewPrep
