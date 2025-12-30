# 🎯 Kth Missing Positive Number

[![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green?style=flat-square)](.)
[![Concept](https://img.shields.io/badge/Concept-Binary%20Search%20%7C%20Array-blue?style=flat-square)](.)

> Find the kth missing positive integer in a sorted array. Master binary search optimization techniques.

---

## 📋 Problem Description

### The Challenge

Given an array `arr` of positive integers sorted in a **strictly increasing order**, and an integer `k`, return the `kth` positive integer that is missing from this array.

**Input:**
- Array `arr` of positive integers in strictly increasing order
- Integer `k` (which missing positive number to find)

**Output:**
- The kth missing positive integer

---

### Examples

**Example 1: arr = [2,3,4,7,11], k = 5**
```
Input: arr = [2,3,4,7,11], k = 5

Output: 9

Explanation:
The missing positive integers are:
1, 5, 6, 8, 9, 10, 12, 13, ...

Position: 1st, 2nd, 3rd, 4th, 5th, 6th, 7th, 8th, ...

The 5th missing positive integer is 9.
```

**Example 2: arr = [1,2,3,4], k = 2**
```
Input: arr = [1,2,3,4], k = 2
Output: 6

Explanation:
The missing positive integers are: 5, 6, 7, 8, ...
The 2nd missing positive integer is 6.
```

**Example 3: arr = [5,6,7,8], k = 3**
```
Input: arr = [5,6,7,8], k = 3
Output: 3

Explanation:
The missing positive integers are: 1, 2, 3, 4, 9, 10, ...
The 3rd missing positive integer is 3.
```

**Example 4: arr = [1], k = 1**
```
Input: arr = [1], k = 1
Output: 2

Explanation:
The only missing positive integer before 1 is none.
After 1, the missing integers are: 2, 3, 4, ...
The 1st missing positive integer is 2.
```

---

## 🎯 Learning Objectives

This problem teaches:
- **Linear Search** - Brute force approach
- **Binary Search** - Optimization technique
- **Mathematical reasoning** - Count missing numbers
- **Index manipulation** - Array index vs value relationship
- **Edge cases** - All missing before/after array

---

## 🔧 Approach 1: Linear Search (Brute Force)

### Algorithm

Iterate through the array and count missing numbers until we reach k.

**Key Insight:** At index `i`, the number of missing integers before `arr[i]` is `arr[i] - (i + 1)`.

### Implementation

```java
public int findKthPositive(int[] arr, int k) {
    // Check if answer is before the array starts
    for (int i = 0; i < arr.length; i++) {
        // Count of missing numbers before arr[i]
        int missing = arr[i] - (i + 1);
        
        // If we've found k or more missing numbers
        if (missing >= k) {
            // The kth missing number is before arr[i]
            return i + k;
        }
    }
    
    // If we reach here, answer is after the array
    // Total missing in array + remaining k
    int missing = arr[arr.length - 1] - arr.length;
    return arr[arr.length - 1] + (k - missing);
}
```

**Complexity:**
- **Time:** O(n) - iterate through entire array
- **Space:** O(1) - constant extra space

**Pros:**
- ✅ Simple and intuitive
- ✅ Easy to understand
- ✅ No complex edge cases

**Cons:**
- ❌ Doesn't utilize sorted property
- ❌ O(n) when O(log n) is possible

---

## 🔧 Approach 2: Binary Search (Optimized)

### Algorithm

Use binary search to find the position where the kth missing number should be.

**Key Formula:** 
- Missing count at index `i` = `arr[i] - (i + 1)`
- If `missing < k`, answer is to the right
- If `missing >= k`, answer is to the left or at current position

### Implementation

```java
public int findKthPositive(int[] arr, int k) {
    int left = 0, right = arr.length - 1;
    
    // Binary search for the rightmost position where missing < k
    while (left <= right) {
        int mid = left + (right - left) / 2;
        int missing = arr[mid] - (mid + 1);
        
        if (missing < k) {
            // Need more missing numbers, search right
            left = mid + 1;
        } else {
            // Too many or exact missing numbers, search left
            right = mid - 1;
        }
    }
    
    // After loop: left is the insertion point
    // Answer is left + k
    return left + k;
}
```

**Complexity:**
- **Time:** O(log n) - binary search
- **Space:** O(1) - constant extra space

**Pros:**
- ⭐ **Optimal time complexity**
- ⭐ Uses sorted property
- Clean and elegant

**Cons:**
- Slightly harder to understand initially

---

## 🔧 Approach 3: Early Return Optimization

### Algorithm

Check if k is smaller than all missing numbers in the array before binary search.

### Implementation

```java
public int findKthPositive(int[] arr, int k) {
    // If k is less than the first element, answer is simply k
    if (k < arr[0]) {
        return k;
    }
    
    // If k is larger than all missing in array
    int totalMissing = arr[arr.length - 1] - arr.length;
    if (k > totalMissing) {
        return arr[arr.length - 1] + (k - totalMissing);
    }
    
    // Binary search
    int left = 0, right = arr.length - 1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        int missing = arr[mid] - (mid + 1);
        
        if (missing < k) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    
    return left + k;
}
```

**Pros:**
- Fast for edge cases
- Handles boundary conditions explicitly

**Cons:**
- More code
- Not significantly better in practice

---

## 📊 Comparison of Approaches

| Approach | Time | Space | Readability | Interview Score |
|----------|------|-------|-------------|-----------------|
| **Linear Search** | O(n) | O(1) | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐ |
| **Binary Search** | O(log n) | O(1) | ⭐⭐⭐⭐ | **⭐⭐⭐⭐⭐** |
| **Early Return** | O(log n) | O(1) | ⭐⭐⭐ | ⭐⭐⭐⭐ |

**Recommendation:** Use **Binary Search** in interviews for optimal solution.

---

## 🎯 Step-by-Step Dry Run

### Input: arr = [2,3,4,7,11], k = 5

**Understanding Missing Numbers:**

| Index i | arr[i] | Expected | Missing Before | Cumulative Missing |
|---------|--------|----------|----------------|-------------------|
| 0 | 2 | 1 | 1 (number 1) | 1 |
| 1 | 3 | 2 | 1 (number 1) | 1 |
| 2 | 4 | 3 | 1 (number 1) | 1 |
| 3 | 7 | 4 | 3 (1, 5, 6) | 3 |
| 4 | 11 | 5 | 6 (1, 5, 6, 8, 9, 10) | 6 |

**Binary Search Execution:**

```
Initial: left = 0, right = 4, k = 5

Iteration 1:
  mid = 2
  arr[2] = 4
  missing = 4 - (2 + 1) = 1
  missing (1) < k (5) → left = 3

Iteration 2:
  mid = 3
  arr[3] = 7
  missing = 7 - (3 + 1) = 3
  missing (3) < k (5) → left = 4

Iteration 3:
  mid = 4
  arr[4] = 11
  missing = 11 - (4 + 1) = 6
  missing (6) >= k (5) → right = 3

Loop ends: left = 4, right = 3

Answer = left + k = 4 + 5 = 9
```

**Verification:**
Missing numbers: 1, 5, 6, 8, **9**, 10, ...
The 5th missing number is indeed 9 ✓

---

## ⚠️ Edge Cases & Validation

### Edge Cases to Consider

**1. k smaller than first element**
```java
Input: arr = [5,6,7], k = 3
Output: 3
Missing: 1, 2, 3, 4, ...
```

**2. k larger than all missing in array**
```java
Input: arr = [1,2,3,4], k = 2
Output: 6
Missing after array: 5, 6, 7, ...
```

**3. Single element array**
```java
Input: arr = [10], k = 5
Output: 5
Missing: 1, 2, 3, 4, 5, 6, ...
```

**4. Array starting at 1**
```java
Input: arr = [1,3,5], k = 2
Output: 4
Missing: 2, 4, 6, ...
```

**5. Large gaps**
```java
Input: arr = [1,100], k = 50
Output: 50
Many missing numbers between elements
```

**6. k = 1**
```java
Input: arr = [2,3,4], k = 1
Output: 1
First missing number
```

### Input Validation

```java
public int findKthPositive(int[] arr, int k) {
    // Validate inputs
    if (arr == null || arr.length == 0) {
        return k;  // All positive integers are missing
    }
    
    if (k <= 0) {
        throw new IllegalArgumentException("k must be positive");
    }
    
    // Main logic...
}
```

---

## 🧪 Test Cases

### Test Case 1: Standard Case
```java
Input: arr = [2,3,4,7,11], k = 5
Expected: 9
```

### Test Case 2: Missing Before Array
```java
Input: arr = [5,6,7,8], k = 3
Expected: 3
```

### Test Case 3: Missing After Array
```java
Input: arr = [1,2,3,4], k = 2
Expected: 6
```

### Test Case 4: Large Gap
```java
Input: arr = [1,100], k = 50
Expected: 50
```

### Test Case 5: First Element
```java
Input: arr = [2], k = 1
Expected: 1
```

### Test Case 6: Continuous Array
```java
Input: arr = [1,2,3,4,5], k = 1
Expected: 6
```

---

## 💡 Key Concepts Illustrated

### 1. Missing Count Formula

```java
// At index i, expected value is (i + 1)
// Actual value is arr[i]
// Missing count = arr[i] - (i + 1)

Example: arr = [2, 3, 4, 7]
Index 3: arr[3] = 7, expected = 4
Missing = 7 - 4 = 3
Missing numbers: 1, 5, 6
```

### 2. Binary Search Pattern

```java
// Search for rightmost position where condition is false
while (left <= right) {
    mid = left + (right - left) / 2;
    if (condition(mid)) {
        left = mid + 1;  // Move right
    } else {
        right = mid - 1;  // Move left
    }
}
// After loop: left is the answer position
```

### 3. Index vs Value Relationship

```java
// If array has no missing numbers:
// arr[0] = 1, arr[1] = 2, arr[2] = 3, ...
// arr[i] = i + 1

// When numbers are missing:
// arr[i] > i + 1
// Difference tells us how many are missing
```

### 4. Answer Calculation

```java
// After binary search, left points to insertion position
// kth missing number = left + k

// Example: left = 3, k = 5
// Answer is at position 3 with offset k
// Result = 3 + 5 = 8
```

---

## 🎓 Interview Discussion Points

### When Interviewer Asks This Problem

**1. Clarify Requirements**
```
Q: "Can the array be empty?"
Q: "Is k always valid (k >= 1)?"
Q: "Are all array elements positive?"
Q: "Is the array strictly increasing?"
```

**2. Explain Your Approach**
```
"I'll use binary search to find where the kth missing number falls.
 
 Key insight: At index i, we can calculate how many numbers are 
 missing before arr[i] using: arr[i] - (i + 1).
 
 We binary search to find the position where we've seen fewer 
 than k missing numbers, then calculate the result as left + k."
```

**3. Discuss Trade-offs**
```
"Linear search is O(n) but simpler to implement.
 Binary search is O(log n) and leverages the sorted property.
 
 For large arrays, binary search is significantly better.
 For small arrays (n < 20), the difference is negligible."
```

### Common Follow-up Questions

**Q: "What if array is not sorted?"**
```java
A: "Would need to sort first: O(n log n) + O(log n) = O(n log n).
   Or use a different approach like counting sort if range is small."
```

**Q: "Find all k missing numbers, not just kth"**
```java
A: "Can modify to collect all missing numbers as we go:
   
   List<Integer> result = new ArrayList<>();
   int current = 1, i = 0;
   
   while (result.size() < k) {
       if (i < arr.length && arr[i] == current) {
           i++;
       } else {
           result.add(current);
       }
       current++;
   }
   return result;"
```

**Q: "What if we want the kth missing from a specific starting point?"**
```java
A: "Add offset to the formula:
   missing = arr[i] - (start + i)
   
   For start = 10, arr = [12, 15]:
   missing at i=0: 12 - (10 + 0) = 2 (missing 10, 11)"
```

---

## 🔄 Related Problems

### Similar Problems to Practice

1. **Missing Number** - Find single missing number (Easy)
2. **Find Missing Positive** - First missing positive integer (Hard)
3. **Missing Element in Sorted Array** - Similar concept (Medium)
4. **Missing Ranges** - Find all missing ranges (Medium)
5. **Kth Smallest in Sorted Matrix** - Similar binary search (Medium)

### Difficulty Progression

```
Missing Number (Easy)
    ↓
Kth Missing Positive (Easy) ← You are here
    ↓
Missing Ranges (Medium)
    ↓
First Missing Positive (Hard)
```

---

## 🎯 Variations to Practice

### Variation 1: Return All Missing Up to K
```java
public List<Integer> findKMissing(int[] arr, int k) {
    // Return first k missing numbers as a list
}
```

### Variation 2: Count Total Missing
```java
public int countMissing(int[] arr, int target) {
    // Count missing numbers up to target value
}
```

### Variation 3: Find Missing in Range
```java
public List<Integer> missingInRange(int[] arr, int start, int end) {
    // Find all missing numbers in [start, end]
}
```

### Variation 4: Kth Missing with Duplicates
```java
public int findKthMissingWithDuplicates(int[] arr, int k) {
    // Handle array with duplicate values
}
```

---

## 📚 Additional Learning

### Why This Problem is Important

1. **Binary search mastery** - Non-obvious binary search application
2. **Mathematical reasoning** - Formula derivation
3. **Index manipulation** - Understanding array positions
4. **Optimization thinking** - O(n) → O(log n)

### Mathematical Foundation

**Core Formula:**
```
Missing count at index i = arr[i] - (i + 1)

Proof:
- If no numbers missing: arr[i] = i + 1
- Each missing number increases arr[i] by 1
- Difference = number of missing values
```

**Binary Search Invariant:**
```
After loop termination:
- right: largest index where missing < k
- left: smallest index where missing >= k
- Answer: left + k (position + offset)
```

---

## 🔗 Resources

- [LeetCode: Kth Missing Positive Number](https://leetcode.com/problems/kth-missing-positive-number/)
- [Binary Search Pattern Guide](https://leetcode.com/discuss/general-discussion/786126/python-powerful-ultimate-binary-search-template-solved-many-problems)
- [Array Missing Numbers Problems](https://leetcode.com/tag/array/)

---

<div align="center">

**Master Binary Search Optimization! 🎯**

*Simple problem, elegant solution*

*Last Updated: December 2024*

</div>
