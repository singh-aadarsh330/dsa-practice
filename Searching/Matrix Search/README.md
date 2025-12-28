# 🔲 Matrix Search

[![Problems](https://img.shields.io/badge/Problems-2-blue?style=flat-square)](.)
[![Difficulty](https://img.shields.io/badge/Difficulty-Easy%20to%20Medium-orange?style=flat-square)](.)

> Efficient searching techniques for 2D arrays with various properties. Master different matrix traversal and search patterns for interview success.

---

## 📋 Overview

Matrix search problems involve finding elements, peaks, or patterns in 2D arrays. These problems require understanding:
- Matrix properties (sorted, partially sorted, unsorted)
- Efficient traversal patterns
- Optimization techniques
- Trade-offs between approaches

**Key Skills:**
- Recognizing matrix properties to choose optimal approach
- Converting between 1D and 2D indices
- Handling boundary conditions
- Optimizing time and space complexity

---

## 📝 Problems in This Folder

| # | Problem | File | Difficulty | Approach | Time | Space | Status |
|---|---------|------|------------|----------|------|-------|--------|
| 1 | Find Peak Element in Grid | [FindPeakElementInGrid.java](./FindPeakElementInGrid.java) | Medium | Binary Search | O(m log n) | O(1) | ✅ |
| 2 | Row with Maximum Ones | [RowWithMaxOnes.java](./RowWithMaxOnes.java) | Easy | Binary Search / Linear | O(m log n) | O(1) | ✅ |

---

## 🎯 Problem 1: Find Peak Element in Grid

### Problem Description

A **peak element** in a 2D grid is an element that is strictly greater than all its adjacent neighbors (up, down, left, right).

**Given:** An m × n matrix  
**Find:** Any peak element and return its position

**Example:**
```
Matrix:
[10, 20, 15]
[21, 30, 14]
[7,  16, 32]

Peak elements: 30 (at [1,1]) or 32 (at [2,2])
30 > 20, 30 > 21, 30 > 14, 30 > 16 ✓
```

**Properties:**
- Guaranteed to have at least one peak
- Can return ANY peak (not necessarily global maximum)
- Compare only with direct neighbors (not diagonals)

---

### Approach: Binary Search on Columns

**Key Insight:**
- We can't use standard binary search on entire matrix
- But we can binary search on **columns** and find max in each column!

**Algorithm:**
1. Binary search on columns (left to right)
2. For middle column, find the maximum element
3. Check if this max element is a peak:
   - If left neighbor > max: peak must be in left half
   - If right neighbor > max: peak must be in right half
   - If max is greater than both neighbors: found peak!

**Why This Works:**
- Maximum element in a column has no greater elements above/below
- Only need to check left and right neighbors
- If not a peak, we know which half contains a peak (monotonic property)

---

### Implementation

```java
public int[] findPeakGrid(int[][] mat) {
    int m = mat.length;
    int n = mat[0].length;
    
    int left = 0, right = n - 1;
    
    while (left <= right) {
        int midCol = left + (right - left) / 2;
        
        // Find max element in middle column
        int maxRow = 0;
        for (int row = 0; row < m; row++) {
            if (mat[row][midCol] > mat[maxRow][midCol]) {
                maxRow = row;
            }
        }
        
        // Check left neighbor
        boolean leftIsBigger = midCol > 0 && 
                               mat[maxRow][midCol - 1] > mat[maxRow][midCol];
        
        // Check right neighbor
        boolean rightIsBigger = midCol < n - 1 && 
                                mat[maxRow][midCol + 1] > mat[maxRow][midCol];
        
        if (!leftIsBigger && !rightIsBigger) {
            // Found peak!
            return new int[]{maxRow, midCol};
        } else if (leftIsBigger) {
            // Peak is in left half
            right = midCol - 1;
        } else {
            // Peak is in right half
            left = midCol + 1;
        }
    }
    
    return new int[]{-1, -1}; // Should never reach here
}
```

---

### Complexity Analysis

**Time Complexity: O(m log n)**
- Binary search on columns: O(log n)
- Finding max in each column: O(m)
- Total: O(m × log n)

**Space Complexity: O(1)**
- Only using a few variables
- No extra data structures

**Comparison with Alternatives:**

| Approach | Time | Space | Notes |
|----------|------|-------|-------|
| Check every element | O(m × n) | O(1) | Brute force |
| **Binary Search** | O(m log n) | O(1) | **Optimal ✓** |
| Greedy (hill climbing) | O(m × n) | O(1) | Not guaranteed |

---

### Visualization

```
Step 1: Start with full matrix
[10, 20, 15]
[21, 30, 14]
[7,  16, 32]
     ↑
  midCol=1

Step 2: Find max in column 1
Max = 30 at row 1

Step 3: Check neighbors
Left neighbor (col 0): 21 < 30 ✓
Right neighbor (col 2): 14 < 30 ✓

Step 4: Found peak at [1, 1]!
```

---

## 🎯 Problem 2: Row with Maximum Ones

### Problem Description

Given a **boolean 2D matrix** where each row is sorted:
- All 0s appear before all 1s in each row
- Find the row with **maximum number of 1s**
- If multiple rows have same count, return the one with smallest index

**Example:**
```
Matrix:
[0, 0, 0, 1]  → 1 one
[0, 1, 1, 1]  → 3 ones  ← Maximum
[1, 1, 1, 1]  → 4 ones  ← Maximum (return this, smallest index)
[0, 0, 1, 1]  → 2 ones

Output: Row 2 (0-indexed)
```

---

### Approach 1: Binary Search on Each Row (Optimal)

**Key Insight:**
- Each row is sorted: [0, 0, ..., 0, 1, 1, ..., 1]
- Use binary search to find **first occurrence of 1**
- Count of 1s = n - (index of first 1)

**Algorithm:**
```java
public int rowWithMax1s(int[][] arr) {
    int m = arr.length;    // rows
    int n = arr[0].length; // columns
    
    int maxCount = 0;
    int maxRow = -1;
    
    for (int i = 0; i < m; i++) {
        // Binary search for first 1 in row i
        int firstOne = findFirstOne(arr[i]);
        
        if (firstOne != -1) {
            int count = n - firstOne;
            
            if (count > maxCount) {
                maxCount = count;
                maxRow = i;
            }
        }
    }
    
    return maxRow;
}

private int findFirstOne(int[] row) {
    int left = 0, right = row.length - 1;
    int result = -1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (row[mid] == 1) {
            result = mid;       // Potential first 1
            right = mid - 1;    // Look for earlier 1
        } else {
            left = mid + 1;     // Look right
        }
    }
    
    return result;
}
```

**Complexity:**
- Time: O(m × log n) - Binary search on each row
- Space: O(1)

---

### Approach 2: Staircase Search (Alternative)

**Concept:**
Start from top-right corner, move left when see 1, move down when see 0.

```java
public int rowWithMax1s(int[][] arr) {
    int m = arr.length;
    int n = arr[0].length;
    
    int row = 0, col = n - 1;
    int maxRow = -1;
    
    // Start from top-right
    while (row < m && col >= 0) {
        if (arr[row][col] == 1) {
            maxRow = row;     // This row has 1s
            col--;            // Move left to find more 1s
        } else {
            row++;            // Move down to find 1s
        }
    }
    
    return maxRow;
}
```

**Complexity:**
- Time: O(m + n) - Visit at most m+n cells
- Space: O(1)

**When to use:**
- Staircase: Better when n is large compared to m
- Binary Search: Better when m is large or n is small

---

### Comparison of Approaches

| Approach | Time | Best When | Code Simplicity |
|----------|------|-----------|-----------------|
| **Binary Search** | O(m log n) | m > log n | Medium |
| **Staircase** | O(m + n) | n >> m | Simple |
| **Linear** | O(m × n) | Never use | Very simple |

**Example:** For 100×1000 matrix:
- Binary Search: 100 × log(1000) ≈ 1,000 operations
- Staircase: 100 + 1000 = 1,100 operations
- Binary Search is slightly better!

---

## 🔑 Key Patterns in Matrix Search

### Pattern 1: Binary Search on Rows/Columns

**When to use:** Each row/column is independently sorted

```java
for (int i = 0; i < rows; i++) {
    int index = binarySearchInRow(matrix[i], target);
    // Process result
}
```

### Pattern 2: Binary Search on One Dimension, Linear on Other

**When to use:** Partially sorted matrices

```java
// Binary search on columns
int left = 0, right = cols - 1;
while (left <= right) {
    int midCol = left + (right - left) / 2;
    // Linear search in column to find max/min
    int maxInCol = findMax(matrix, midCol);
    // Make decision
}
```

### Pattern 3: Staircase Search

**When to use:** Sorted row-wise AND column-wise

```java
int row = 0, col = matrix[0].length - 1;
while (row < rows && col >= 0) {
    if (matrix[row][col] == target) return true;
    else if (matrix[row][col] > target) col--;
    else row++;
}
```

### Pattern 4: Binary Search on Entire Matrix

**When to use:** Matrix is strictly sorted (row by row)

```java
int left = 0, right = rows * cols - 1;
while (left <= right) {
    int mid = left + (right - left) / 2;
    int value = matrix[mid / cols][mid % cols];
    // Standard binary search logic
}
```

---

## 💡 Problem-Solving Strategy

### Step 1: Identify Matrix Properties

Ask yourself:
- [ ] Is each row sorted?
- [ ] Is each column sorted?
- [ ] Is the entire matrix sorted?
- [ ] Are there duplicates?
- [ ] What are we searching for? (element, peak, pattern)

### Step 2: Choose Optimal Approach

```
Strictly sorted (row by row)
    → Binary search on flattened matrix: O(log(m×n))

Sorted rows AND columns
    → Staircase search: O(m+n)

Only rows sorted
    → Binary search per row: O(m × log n)

Unsorted
    → Linear scan: O(m×n)

Finding peak/special element
    → Binary search on one dimension: O(m × log n) or O(n × log m)
```

### Step 3: Handle Edge Cases

- [ ] Empty matrix
- [ ] Single row/column
- [ ] All elements same
- [ ] Target not present
- [ ] Multiple valid answers

---

## ⚠️ Common Mistakes

1. **Not Checking Matrix Properties**
   ```java
   // Always ask: Is matrix sorted? How?
   if (matrix == null || matrix.length == 0) return -1;
   ```

2. **Wrong Starting Position for Staircase**
   ```java
   ❌ Start from top-left    // Doesn't work!
   ✅ Start from top-right or bottom-left
   ```

3. **Confusing Row vs Column Indexing**
   ```java
   ❌ matrix[col][row]       // Swapped!
   ✅ matrix[row][col]       // Correct
   ```

4. **Not Handling Boundary Conditions**
   ```java
   // Always check bounds
   if (row >= 0 && row < m && col >= 0 && col < n)
   ```

---

## 🎓 Interview Tips

### Discussion Points

1. **Clarify Matrix Properties**
   ```
   "Let me confirm: is each row sorted? Are columns sorted too?
    This will help me choose the optimal approach."
   ```

2. **Explain Trade-offs**
   ```
   "I can use binary search O(m log n) or staircase search O(m+n).
    Binary search is better when n is large..."
   ```

3. **Walk Through Example**
   - Draw small matrix (3×3)
   - Trace algorithm step by step
   - Show how complexity is calculated

4. **Optimize Progressively**
   ```
   Start: "Brute force would be O(m×n)..."
   Then:  "But since rows are sorted, we can..."
   Final: "This gives us O(m log n) which is optimal."
   ```

---

## 📚 Practice Progression

### After Mastering These Problems

**Next Level:**
1. Search in Rotated 2D Matrix
2. Find Median in Row-wise Sorted Matrix
3. Kth Smallest in Sorted Matrix
4. Count Negative Numbers in Sorted Matrix
5. Search in Young Tableau

**Advanced:**
1. Maximum Sum Rectangle
2. Spiral Matrix Traversal
3. Set Matrix Zeroes
4. Rotate Matrix 90 Degrees

---

## 🔗 Resources

- [GeeksforGeeks - Matrix Searching](https://www.geeksforgeeks.org/search-in-row-wise-and-column-wise-sorted-matrix/)
- [LeetCode - Matrix Problems](https://leetcode.com/tag/matrix/)
- [Striver's Matrix Sheet](https://takeuforward.org/interviews/strivers-sde-sheet-top-coding-interview-problems/)

---

<div align="center">

**Master Matrix Search Patterns! 🔲🔍**

*Last Updated: December 2024*

[⬆ Back to Searching](../README.md) | [⬆ Back to Main](../../README.md)

</div>
