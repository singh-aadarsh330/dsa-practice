# 🔲 Matrix (2D Arrays)

[![Problems](https://img.shields.io/badge/Problems-2-blue?style=flat-square)](.)
[![Difficulty](https://img.shields.io/badge/Difficulty-Medium-orange?style=flat-square)](.)

> Matrix problems involve 2D array manipulation and searching. These problems are common in interviews and require understanding of both array fundamentals and additional dimensional complexity.

---

## 📋 Overview

A **matrix** (or 2D array) is an array of arrays, representing data in rows and columns. Matrix problems often involve:
- Traversing in different patterns (row-wise, column-wise, diagonal, spiral)
- Searching efficiently using sorted properties
- In-place modifications
- Space optimization techniques

**Key Properties:**
- Access: O(1) using `matrix[row][col]`
- Common dimensions: m × n (m rows, n columns)
- Total elements: m * n

---

## 📝 Problems in This Folder

| # | Problem | File | Difficulty | Approach | Time | Space | Status |
|---|---------|------|------------|----------|------|-------|--------|
| 1 | Kth Smallest in Sorted Matrix | [KthSmallestInSortedMatrix.java](./KthSmallestInSortedMatrix.java) | Medium | Binary Search / Min Heap | O(n log n) | O(k) | ✅ |
| 2 | Search in Sorted Matrix | [SearchInSortedMatrix.java](./SearchInSortedMatrix.java) | Medium | Staircase Search / Binary Search | O(m+n) | O(1) | ✅ |

---

## 🎯 Problem 1: Kth Smallest Element in Sorted Matrix

### Problem Description
Given an `n x n` matrix where each row and column is sorted in ascending order, find the kth smallest element in the matrix.

**Matrix Properties:**
- Each row is sorted left to right
- Each column is sorted top to bottom
- NOT necessarily sorted when reading row by row

**Example:**
```
Matrix:
[1,  5,  9]
[10, 11, 13]
[12, 13, 15]

k = 8
Output: 13
Explanation: Elements in sorted order: [1,5,9,10,11,12,13,13,15]
             8th element is 13
```

### Approaches

**Approach 1: Min Heap (Priority Queue)**
```java
// Time: O(k log n), Space: O(n)
- Add first element of each row to min heap
- Extract min k times
- When extracting element at (i,j), add (i, j+1) if exists
```

**Approach 2: Binary Search on Value Range**
```java
// Time: O(n log(max-min)), Space: O(1)
- Binary search on value range [matrix[0][0], matrix[n-1][n-1]]
- For each mid, count elements <= mid
- Adjust range based on count vs k
```

### Key Learnings
- Using heap for maintaining smallest elements efficiently
- Binary search on value range (not indices)
- Counting elements <= target in sorted matrix
- Trade-off between time and space complexity

### Interview Tips
- Start with brute force (flatten and sort) then optimize
- Explain both approaches and their trade-offs
- Discuss when to use heap vs binary search
- Handle edge cases: k=1, k=n*n, duplicate values

---

## 🎯 Problem 2: Search in Sorted Matrix

### Problem Description
Write an efficient algorithm to search for a value in an m x n matrix.

**Matrix Properties:**
- Integers in each row are sorted left to right
- Integers in each column are sorted top to bottom

**Example:**
```
Matrix:
[1,  4,  7,  11]
[2,  5,  8,  12]
[3,  6,  9,  16]
[10, 13, 14, 17]

Target: 5
Output: true

Target: 20
Output: false
```

### Approaches

**Approach 1: Staircase Search (Optimal)**
```java
// Time: O(m + n), Space: O(1)
// Start from top-right corner
int row = 0, col = matrix[0].length - 1;

while (row < matrix.length && col >= 0) {
    if (matrix[row][col] == target) 
        return true;
    else if (matrix[row][col] > target) 
        col--;          // Move left
    else 
        row++;          // Move down
}
return false;
```

**Why Top-Right?**
- If current > target: all elements in current column below are > target → move left
- If current < target: all elements in current row to left are < target → move down

**Approach 2: Binary Search (If strictly sorted row-wise)**
```java
// Time: O(log(m*n)), Space: O(1)
// Treat 2D matrix as flattened 1D sorted array
int low = 0, high = m * n - 1;

while (low <= high) {
    int mid = low + (high - low) / 2;
    int midValue = matrix[mid / n][mid % n];  // Convert 1D to 2D
    
    if (midValue == target) return true;
    else if (midValue < target) low = mid + 1;
    else high = mid - 1;
}
return false;
```

### Key Learnings
- Staircase search from corners (top-right or bottom-left)
- Converting 2D indices to 1D: `index = row * cols + col`
- Converting 1D index to 2D: `row = index / cols`, `col = index % cols`
- Choosing starting position based on sorted properties

### Interview Tips
- Draw the matrix and trace your algorithm
- Explain why you start from top-right/bottom-left
- Discuss alternative approaches
- Mention time-space trade-offs

---

## 🔑 Key Patterns for Matrix Problems

### Pattern 1: Staircase Search
**Used when:** Matrix is sorted row-wise AND column-wise

**Template:**
```java
// Start from top-right (or bottom-left)
int row = 0, col = matrix[0].length - 1;

while (row < rows && col >= 0) {
    if (matrix[row][col] == target) {
        // Found
    } else if (matrix[row][col] > target) {
        col--;  // Go left
    } else {
        row++;  // Go down
    }
}
```

### Pattern 2: Binary Search on Flattened Matrix
**Used when:** Matrix rows are sorted, and first element of each row > last of previous row

**Index Conversion:**
```java
// 1D to 2D
int row = index / cols;
int col = index % cols;

// 2D to 1D  
int index = row * cols + col;
```

### Pattern 3: Binary Search on Value Range
**Used when:** Finding kth element, counting elements in range

**Template:**
```java
int low = matrix[0][0];
int high = matrix[rows-1][cols-1];

while (low < high) {
    int mid = low + (high - low) / 2;
    int count = countLessOrEqual(matrix, mid);
    
    if (count < k) low = mid + 1;
    else high = mid;
}
```

### Pattern 4: Row-wise Binary Search
**Used when:** Each row independently sorted

```java
for (int i = 0; i < rows; i++) {
    int index = binarySearch(matrix[i], target);
    if (index != -1) return new int[]{i, index};
}
```

---

## 💡 Common Techniques

### 1. Matrix Traversal Patterns
```java
// Row-wise
for (int i = 0; i < rows; i++)
    for (int j = 0; j < cols; j++)

// Column-wise
for (int j = 0; j < cols; j++)
    for (int i = 0; i < rows; i++)

// Diagonal
for (int i = 0; i < Math.min(rows, cols); i++)
    element = matrix[i][i]

// Anti-diagonal
for (int i = 0; i < rows; i++)
    element = matrix[i][cols - 1 - i]

// Spiral (complex, needs careful boundary management)
```

### 2. Boundary Checks
```java
// Always validate indices
if (row >= 0 && row < rows && col >= 0 && col < cols) {
    // Safe to access matrix[row][col]
}
```

### 3. In-place Modifications
```java
// Use matrix cells to store additional info
// Example: marking visited cells with negative values
if (matrix[i][j] != 0) {
    matrix[i][j] = -matrix[i][j];  // Mark as visited
}
```

---

## ⚠️ Common Pitfalls

1. **Index Out of Bounds**
   - Always check `row < rows && col < cols`
   - Be careful with `row+1`, `col+1`, `row-1`, `col-1`

2. **Wrong Starting Position**
   - Staircase search: start from top-right or bottom-left, NOT top-left

3. **Incorrect Index Conversion**
   ```java
   ❌ row = index * cols;
   ✅ row = index / cols;
   ```

4. **Not Utilizing Sorted Property**
   - If matrix is sorted, use binary search (O(log n)) not linear search (O(n))

5. **Assuming Complete Sorting**
   - Matrix may be sorted row-wise and column-wise but not globally

---

## 🎓 Interview Checklist

Before solving matrix problems, ask:
- [ ] Is the matrix sorted? Row-wise? Column-wise? Both?
- [ ] Can I modify the matrix in-place?
- [ ] What are the dimensions? Square (n×n) or rectangular (m×n)?
- [ ] Are there duplicate elements?
- [ ] What should I return? (boolean, indices, value, count)

During solution:
- [ ] Draw the matrix on whiteboard/paper
- [ ] Trace algorithm with example
- [ ] Check boundary conditions
- [ ] Explain time and space complexity
- [ ] Discuss alternative approaches

---

## 📚 Related Concepts

- **Arrays** - Matrix is 2D array
- **Binary Search** - Used for searching in sorted matrices
- **Heaps** - Used for finding kth smallest/largest
- **Two Pointers** - Staircase search is a form of two pointers

---

## 🚀 Practice Problems (Recommended Next)

After mastering these problems, try:
1. Spiral Matrix Traversal
2. Rotate Matrix 90 degrees
3. Set Matrix Zeroes
4. Search in Row and Column Sorted Matrix
5. Median in Row-wise Sorted Matrix
6. Count Negative Numbers in Sorted Matrix

---

## 📖 Additional Resources

- [GeeksforGeeks - Matrix Problems](https://www.geeksforgeeks.org/matrix/)
- [LeetCode - Matrix Tag](https://leetcode.com/tag/matrix/)
- [Striver's Matrix Problems](https://takeuforward.org/interviews/strivers-sde-sheet-top-coding-interview-problems/)

---

<div align="center">

**Master Matrix Patterns! 🔲**

*Last Updated: December 2024*

[⬆ Back to Arrays](../README.md) | [⬆ Back to Main](../../README.md)

</div>
