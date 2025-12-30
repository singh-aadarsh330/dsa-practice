# 🔍 Binary Search

[![Problems](https://img.shields.io/badge/Problems-1-blue?style=flat-square)](.)
[![Difficulty](https://img.shields.io/badge/Difficulty-Medium-orange?style=flat-square)](.)

> Core binary search implementations on sorted data structures. Master the fundamental technique that reduces search complexity from O(n) to O(log n).

---

## 📋 Overview

**Binary Search** is a divide-and-conquer algorithm that efficiently searches for a target value in a sorted array by repeatedly dividing the search interval in half.

**Core Principle:**
- Compare target with middle element
- If match found, return position
- If target < middle, search left half
- If target > middle, search right half

**Why Binary Search?**
- ✅ Exponentially faster than linear search
- ✅ O(log n) time complexity
- ✅ Minimal space usage O(1) for iterative
- ✅ Foundation for many advanced algorithms

---

## ✅ Prerequisites

Before applying binary search:
1. **Data must be sorted** (ascending or descending)
2. **Random access** must be possible (array, not linked list)
3. Must be able to **compare elements** (define ordering)

---

## 📝 Problems in This Folder

| # | Problem | File | Difficulty | Matrix Type | Time | Space | Status |
|---|---------|------|------------|-------------|------|-------|--------|
| 1 | Search in Strictly Sorted Matrix | [SearchInStrictlySortedMatrix.java](./SearchInStrictlySortedMatrix.java) | Medium | Fully sorted 2D | O(log(m×n)) | O(1) | ✅ |

---

## 🎯 Problem: Search in Strictly Sorted Matrix

### Problem Description

Search for a target value in a **strictly sorted matrix** where:
- Each row is sorted in ascending order
- **First integer of each row > last integer of previous row**
- Entire matrix can be treated as one sorted sequence

**Example:**
```
Matrix:
[1,  3,  5,  7]
[10, 11, 16, 20]
[23, 30, 34, 60]

Target: 3  → Output: true (at position [0,1])
Target: 13 → Output: false
```

### Key Insight

This matrix has a special property: it's **completely sorted**. If you flatten it into a 1D array, it remains sorted:
```
[1, 3, 5, 7, 10, 11, 16, 20, 23, 30, 34, 60]
```

This means we can apply **standard binary search** by treating the 2D matrix as a 1D array!

---

### Approach: Binary Search with Index Conversion

**Algorithm:**
1. Treat matrix as flattened 1D array of size `m * n`
2. Apply binary search on range `[0, m*n-1]`
3. Convert 1D index to 2D coordinates when accessing elements
4. Standard binary search logic

**Index Conversion Formulas:**
```java
// Given 1D index in range [0, m*n-1]
int row = index / n;      // n = number of columns
int col = index % n;

// Given 2D coordinates (row, col)
int index = row * n + col;
```

**Example:**
```
Matrix (3×4):
[1,  3,  5,  7]   → indices 0,  1,  2,  3
[10, 11, 16, 20]  → indices 4,  5,  6,  7
[23, 30, 34, 60]  → indices 8,  9, 10, 11

Element at 1D index 5:
  row = 5 / 4 = 1
  col = 5 % 4 = 1
  matrix[1][1] = 11 ✓

Element at position [2][3]:
  index = 2 * 4 + 3 = 11
  Flattened array index 11 = 60 ✓
```

---

### Implementation Template

```java
public boolean searchMatrix(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0) return false;
    
    int m = matrix.length;        // rows
    int n = matrix[0].length;     // columns
    
    int low = 0;
    int high = m * n - 1;
    
    while (low <= high) {
        int mid = low + (high - low) / 2;
        
        // Convert 1D index to 2D coordinates
        int row = mid / n;
        int col = mid % n;
        int midValue = matrix[row][col];
        
        if (midValue == target) {
            return true;
        } else if (midValue < target) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }
    
    return false;
}
```

---

### Complexity Analysis

**Time Complexity: O(log(m × n))**
- Total elements = m × n
- Binary search halves search space each iteration
- Number of iterations = log₂(m × n)

**Space Complexity: O(1)**
- Only using a few variables (low, high, mid)
- No additional data structures needed
- In-place algorithm

**Comparison with Linear Search:**
```
For 1000×1000 matrix (1 million elements):
  Linear Search:   ~1,000,000 operations (worst case)
  Binary Search:   ~20 operations (worst case)
  Speedup:         50,000x faster!
```

---

## 🔑 Key Concepts Explained

### 1. Why Binary Search Works Here

Binary search requires a **sorted sequence**. This matrix is sorted because:
- ✅ Each row is sorted internally
- ✅ First element of row i+1 > last element of row i
- ✅ Therefore: matrix[i][j] < matrix[i][j+1] < matrix[i+1][0]

This creates a **total ordering** across the entire matrix.

### 2. Index Conversion Mathematics

**1D to 2D Conversion:**
```
Given flattened index 'i' in array of width 'n':
  
  row = i ÷ n   (integer division)
  col = i % n   (modulo operation)
  
Intuition:
  - i ÷ n tells us which "group of n" we're in (row number)
  - i % n tells us position within that group (column)
```

**2D to 1D Conversion:**
```
Given coordinates (row, col) in matrix of width 'n':
  
  index = row × n + col
  
Intuition:
  - Skip 'row' complete rows (row × n elements)
  - Add 'col' position within current row
```

### 3. Why Not Use Staircase Search?

For **strictly sorted** matrices, binary search O(log(m×n)) is better than staircase search O(m+n):

```
Example: 100×100 matrix

Binary Search:      log₂(10,000) ≈ 13 operations
Staircase Search:   100 + 100 = 200 operations

Binary search is 15x faster!
```

**When to use Staircase Search:** Only when rows are sorted AND columns are sorted, but NOT strictly sorted overall (i.e., matrix[i][n-1] might be > matrix[i+1][0])

---

## 💡 Step-by-Step Example

**Search for target = 16 in:**
```
[1,  3,  5,  7]
[10, 11, 16, 20]
[23, 30, 34, 60]
```

**Flattened view:** `[1, 3, 5, 7, 10, 11, 16, 20, 23, 30, 34, 60]`

**Binary Search Execution:**

| Step | Low | High | Mid | matrix[mid/4][mid%4] | Action |
|------|-----|------|-----|----------------------|--------|
| 1 | 0 | 11 | 5 | matrix[1][1] = 11 | 11 < 16, low = 6 |
| 2 | 6 | 11 | 8 | matrix[2][0] = 23 | 23 > 16, high = 7 |
| 3 | 6 | 7 | 6 | matrix[1][2] = 16 | **Found!** ✓ |

Total operations: **3** (vs. 7 with linear search)

---

## ⚠️ Common Mistakes & Edge Cases

### Mistakes to Avoid

1. **Integer Overflow in Mid Calculation**
   ```java
   ❌ int mid = (low + high) / 2;        // Can overflow
   ✅ int mid = low + (high - low) / 2;  // Overflow-safe
   ```

2. **Wrong Index Conversion**
   ```java
   ❌ int row = mid * n;                 // Multiply instead of divide
   ✅ int row = mid / n;                 // Correct
   ```

3. **Off-by-One Errors**
   ```java
   ❌ int high = m * n;                  // Should be m*n-1
   ✅ int high = m * n - 1;              // Correct
   ```

4. **Not Checking Empty Matrix**
   ```java
   ❌ int n = matrix[0].length;          // Crashes if empty
   ✅ if (matrix.length == 0) return false;
   ```

### Edge Cases to Test

- [ ] Empty matrix: `[]` or `[[]]`
- [ ] Single element: `[[5]]`
- [ ] Single row: `[[1,2,3,4]]`
- [ ] Single column: `[[1],[2],[3]]`
- [ ] Target smaller than all elements
- [ ] Target larger than all elements
- [ ] Target equals first element
- [ ] Target equals last element
- [ ] Target not present in matrix

---

## 🎓 Interview Tips

### Discussion Points

1. **Clarify Matrix Properties**
   ```
   Q: "Is the matrix strictly sorted?"
   - If yes → Binary search O(log(m×n))
   - If partially sorted → Staircase search O(m+n)
   ```

2. **Explain Trade-offs**
   ```
   "This approach is optimal for strictly sorted matrices.
   For partially sorted matrices, staircase search would be better."
   ```

3. **Walk Through Example**
   - Draw the matrix
   - Show flattened array
   - Trace through 2-3 iterations
   - Explain index conversions

4. **Mention Alternatives**
   ```
   Alternative 1: Binary search on each row [O(m log n)]
   Alternative 2: Linear scan [O(m×n)]
   Our approach: Binary search on entire matrix [O(log(m×n))] ✓
   ```

### Common Follow-up Questions

**Q: "What if matrix is not strictly sorted?"**
```
A: "If first element of a row can be less than last element of 
    previous row, we cannot treat it as a flattened array. 
    We'd use staircase search instead: start from top-right,
    move left if current > target, down if current < target.
    Time complexity becomes O(m+n)."
```

**Q: "Can you implement recursively?"**
```java
public boolean searchMatrixRecursive(int[][] matrix, int target) {
    int m = matrix.length, n = matrix[0].length;
    return binarySearch(matrix, target, 0, m * n - 1, n);
}

private boolean binarySearch(int[][] matrix, int target, 
                             int low, int high, int n) {
    if (low > high) return false;
    
    int mid = low + (high - low) / 2;
    int midValue = matrix[mid / n][mid % n];
    
    if (midValue == target) return true;
    else if (midValue < target) 
        return binarySearch(matrix, target, mid + 1, high, n);
    else 
        return binarySearch(matrix, target, low, mid - 1, n);
}
```

---

## 📚 Related Problems

### Similar Problems to Practice

1. **Find Peak Element in Matrix** - Harder variation
2. **Search in Row and Column Sorted Matrix** - Use staircase search
3. **Find Minimum in Rotated Sorted Matrix** - Modified binary search
4. **Kth Smallest in Sorted Matrix** - Binary search on value

### Progression Path

```
1. Standard Binary Search in 1D Array
   ↓
2. Binary Search in Strictly Sorted Matrix ← You are here
   ↓
3. Binary Search in Rotated Sorted Array
   ↓
4. Binary Search on Answer Space
```

---

## 🔗 Additional Resources

- [GeeksforGeeks - Binary Search](https://www.geeksforgeeks.org/binary-search/)
- [LeetCode - Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix/)
- [Visualization Tool](https://www.cs.usfca.edu/~galles/visualization/Search.html)

---

<div align="center">

**Master Binary Search, Master Efficiency! 🚀**

*Last Updated: December 2024*

[⬆ Back to Searching](../README.md) | [⬆ Back to Main](../../README.md)

</div>
