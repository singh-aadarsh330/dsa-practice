# Longest Substring Without Repeating Characters

## 🎯 Problem Statement

Given a string `s`, find the length of the longest substring without repeating characters.

**LeetCode**: [Problem #3](https://leetcode.com/problems/longest-substring-without-repeating-characters/)

## 📝 Examples

### Example 1
```
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with length 3.
```

### Example 2
```
Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with length 1.
```

### Example 3
```
Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with length 3.
Notice that "pwke" is a subsequence, not a substring.
```

### Example 4
```
Input: s = ""
Output: 0
```

## 💡 Approach

### Sliding Window with HashMap

We use a sliding window approach with a HashMap to track the most recent position of each character.

**Key Insights:**
1. Maintain a window `[left, right]` representing the current substring
2. Use a HashMap to store the last seen index of each character
3. When a duplicate is found, move `left` pointer past the previous occurrence
4. Track the maximum window size seen

**Algorithm Steps:**
1. Initialize `left = 0`, `maxLength = 0`, and an empty HashMap
2. Iterate through the string with `right` pointer
3. If current character exists in HashMap and its index >= left:
   - Update `left` to `map.get(char) + 1`
4. Update the character's position in HashMap
5. Update `maxLength = max(maxLength, right - left + 1)`
6. Return `maxLength`

## 🔍 Visualization

```
s = "abcabcbb"

Step 1: "a" → window = "a", maxLen = 1
Step 2: "ab" → window = "ab", maxLen = 2
Step 3: "abc" → window = "abc", maxLen = 3
Step 4: "abca" → duplicate 'a'! Move left to index 1
        window = "bca", maxLen = 3
Step 5: "bcab" → duplicate 'b'! Move left to index 2
        window = "cab", maxLen = 3
Step 6: "cabc" → duplicate 'c'! Move left to index 3
        window = "abc", maxLen = 3
...
```

## 📊 Complexity Analysis

- **Time Complexity**: O(n)
  - We visit each character at most twice (once by right pointer, once by left pointer)
  
- **Space Complexity**: O(min(n, m))
  - Where n is string length and m is character set size
  - HashMap stores at most min(n, m) characters
  - For ASCII: O(128), for Unicode: O(256)

## 🎓 Key Learnings

1. **Sliding Window Pattern**: Perfect for substring problems with constraints
2. **HashMap Usage**: Efficiently track character positions for O(1) lookups
3. **Pointer Management**: Understanding when to move left pointer is crucial
4. **Edge Cases**: Empty strings, single character, all unique, all duplicates

## 🔗 Related Problems

- Longest Substring with At Most K Distinct Characters
- Longest Repeating Character Replacement
- Minimum Window Substring
- Substring with Concatenation of All Words

## 💻 Implementation

See [Solution.java](./Solution.java) for the complete implementation.

## 🧪 Test Cases

```java
Input: "abcabcbb" → Output: 3
Input: "bbbbb" → Output: 1
Input: "pwwkew" → Output: 3
Input: "" → Output: 0
Input: " " → Output: 1
Input: "au" → Output: 2
Input: "dvdf" → Output: 3
```

## 💡 Alternative Approaches

1. **Brute Force**: O(n³) - Check all substrings
2. **Optimized Brute Force**: O(n²) - Use HashSet for each starting position
3. **Sliding Window (Optimal)**: O(n) - Current approach

---

**Tags**: `Sliding Window` `Hash Map` `String` `Two Pointers`

**Difficulty**: Medium
