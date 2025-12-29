# 🔎 Search in Row-wise and Column-wise Sorted Matrix

[![Difficulty](https://img.shields.io/badge/Difficulty-Medium-orange?style=flat-square)](.)
[![Concept](https://img.shields.io/badge/Concept-Matrix%20Search%20%7C%20Staircase-blue?style=flat-square)](.)

> Search for a target in a matrix where both rows and columns are sorted. Master the elegant staircase search algorithm.

---

## 📋 Problem Description

### The Challenge

Write an efficient algorithm to search for a value `target` in an `m × n` integer matrix with the following properties:

**Matrix Properties:**
1. Each **row** is sorted in ascending order (left to right)
2. Each **column** is sorted in ascending order (top to bottom)
3. **No guarantee** that first element of row is > last element of previous row

This is different from a fully sorted matrix!

**Input:**
- `m × n` integer matrix (rows sorted, columns sorted)
- Integer `target` to search for

**Output:**
- `true` if target exists
- `false` otherwise

---

### Examples

**Example 1: Target Found**
```
Input:
matrix = [
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
target = 5

Output: true
Explanation: 5 exists at position [1,1]
```

**Example 2: Target Not Found**
```
Input:
matrix = [
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
target = 20

Output: false
Explanation: 20 doesn't exist in matrix
```

**Example 3: Single Element**
```
Input:
matrix = [[5]]
target = 5

Output: true
```

**Example 4: Target at Corner**
```
Input:
matrix = [[1, 4], [2, 5]]
target = 1  (top-left)

Output: true
```

**Example 5: Empty Matrix**
```
Input:
matrix = []
target = 5

Output: false
```

---

## 🎯 Learning Objectives

This problem teaches:
- **Staircase algorithm** - Start from corner and eliminate row/column
- **2D binary search alternative** - When standard binary search doesn't work
- **Sorted structure exploitation** - Use both row and column sorting
- **Search space reduction** - Eliminate portions intelligently
- **Interview favorite** - Classic problem with elegant solution

---

## 🔧 Approach 1: Brute Force

### Algorithm

Check every element until found or exhausted.

### Implementation

```java
public boolean searchMatrix(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        return false;
    }
    
    for (int row = 0; row < matrix.length; row++) {
        for (int col = 0; col < matrix[0].length; col++) {
            if (matrix[row][col] == target) {
                return true;
            }
        }
    }
    
    return false;
}
```

**Complexity:**
- **Time:** O(m × n) - check every element
- **Space:** O(1)

**Pros:**
- Simple implementation
- Works for any matrix

**Cons:**
- ❌ Doesn't use sorted property
- ❌ Very inefficient
- ❌ Never use in interview

---

## 🔧 Approach 2: Binary Search Each Row

### Algorithm

For each row, use binary search to find target.

### Implementation

```java
public boolean searchMatrix(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        return false;
    }
    
    for (int row = 0; row < matrix.length; row++) {
        if (binarySearch(matrix[row], target)) {
            return true;
        }
    }
    
    return false;
}

private boolean binarySearch(int[] row, int target) {
    int left = 0, right = row.length - 1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (row[mid] == target) {
            return true;
        } else if (row[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    
    return false;
}
```

**Complexity:**
- **Time:** O(m × log n) - binary search for each row
- **Space:** O(1)

**Pros:**
- Better than brute force
- Uses row sorting

**Cons:**
- Doesn't use column sorting
- Not optimal

---

## 🔧 Approach 3: Staircase Search (Optimal) ⭐

### Algorithm

**Start from Top-Right (or Bottom-Left) Corner:**

**From Top-Right:**
- If `current == target`: Found!
- If `current > target`: Move LEFT (eliminate column)
- If `current < target`: Move DOWN (eliminate row)

**Why This Works:**
- At top-right: smallest in its row (to the right), largest in its column (above)
- If too large: everything to right is larger → eliminate right
- If too small: everything above is smaller → eliminate up

### Implementation

```java
public boolean searchMatrix(int[][] matrix, int target) {
    // Edge cases
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        return false;
    }
    
    int rows = matrix.length;
    int cols = matrix[0].length;
    
    // Start from top-right corner
    int row = 0;
    int col = cols - 1;
    
    while (row < rows && col >= 0) {
        if (matrix[row][col] == target) {
            return true;
        } else if (matrix[row][col] > target) {
            col--;  // Move left
        } else {
            row++;  // Move down
        }
    }
    
    return false;
}
```

**Alternative: Start from Bottom-Left**
```java
public boolean searchMatrix(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        return false;
    }
    
    int rows = matrix.length;
    int cols = matrix[0].length;
    
    // Start from bottom-left corner
    int row = rows - 1;
    int col = 0;
    
    while (row >= 0 && col < cols) {
        if (matrix[row][col] == target) {
            return true;
        } else if (matrix[row][col] < target) {
            col++;  // Move right
        } else {
            row--;  // Move up
        }
    }
    
    return false;
}
```

**Complexity:**
- **Time:** O(m + n) - at most m + n steps
- **Space:** O(1)

**Pros:**
- ⭐ Optimal time complexity
- Elegant and simple
- Uses both sorted properties
- Single pass
- Interview gold standard

**Cons:**
- Requires understanding the logic
- Non-obvious at first

---

## 📊 Comparison of Approaches

| Approach | Time | Space | Difficulty | Interview Score |
|----------|------|-------|------------|-----------------|
| **Brute Force** | O(m×n) | O(1) | ⭐ | ❌ |
| **Binary Search Rows** | O(m log n) | O(1) | ⭐⭐⭐ | ⭐⭐⭐ |
| **Staircase** | O(m+n) | O(1) | ⭐⭐⭐⭐ | **⭐⭐⭐⭐⭐** |

**Recommendation:** Master the **Staircase Algorithm** - it's optimal and impressive!

---

## 🎯 Step-by-Step Dry Run

### Input:
```java
int[][] matrix = {
    {1,   4,  7, 11, 15},
    {2,   5,  8, 12, 19},
    {3,   6,  9, 16, 22},
    {10, 13, 14, 17, 24},
    {18, 21, 23, 26, 30}
};
int target = 5;
```

### Execution (Staircase from Top-Right):

**Start Position:**
```
[1,   4,  7, 11, 15̲]  ← Start here (row=0, col=4)
[2,   5,  8, 12, 19]
[3,   6,  9, 16, 22]
[10, 13, 14, 17, 24]
[18, 21, 23, 26, 30]
```

**Step 1:**
```
row = 0, col = 4
matrix[0][4] = 15
15 > 5 → Move LEFT
col = 3
```

**Step 2:**
```
row = 0, col = 3
matrix[0][3] = 11
11 > 5 → Move LEFT
col = 2
```

**Step 3:**
```
row = 0, col = 2
matrix[0][2] = 7
7 > 5 → Move LEFT
col = 1
```

**Step 4:**
```
row = 0, col = 1
matrix[0][1] = 4
4 < 5 → Move DOWN
row = 1
```

**Step 5:**
```
row = 1, col = 1
matrix[1][1] = 5
5 == 5 → FOUND!

Return: true
```

**Search Path:**
```
[1,   4̲,  7̲, 11̲, 15̲]
[2,   5̲,  8, 12, 19]
[3,   6,  9, 16, 22]
[10, 13, 14, 17, 24]
[18, 21, 23, 26, 30]

Path: 15 → 11 → 7 → 4 → 5 ✓
Total steps: 5
```

---

## ⚠️ Edge Cases & Validation

### Edge Cases to Consider

**1. Empty Matrix**
```java
Input: matrix = [], target = 5
Output: false
```

**2. Single Element - Found**
```java
Input: matrix = [[5]], target = 5
Output: true
```

**3. Single Element - Not Found**
```java
Input: matrix = [[5]], target = 3
Output: false
```

**4. Single Row**
```java
Input: matrix = [[1, 3, 5, 7]], target = 5
Output: true
```

**5. Single Column**
```java
Input: matrix = [[1], [3], [5], [7]], target = 5
Output: true
```

**6. Target at Corners**
```java
Top-left: matrix[0][0]
Top-right: matrix[0][n-1]
Bottom-left: matrix[m-1][0]
Bottom-right: matrix[m-1][n-1]
All should work correctly
```

**7. Target Smaller Than Minimum**
```java
Input: target < matrix[0][0]
Output: false
Should eliminate quickly
```

**8. Target Larger Than Maximum**
```java
Input: target > matrix[m-1][n-1]
Output: false
Should eliminate quickly
```

**9. Negative Numbers**
```java
Input: matrix with negative values
Should work normally with staircase
```

### Input Validation

```java
public boolean searchMatrix(int[][] matrix, int target) {
    // Validate matrix
    if (matrix == null) {
        throw new IllegalArgumentException("Matrix cannot be null");
    }
    
    if (matrix.length == 0 || matrix[0].length == 0) {
        return false;  // Empty matrix
    }
    
    // Validate all rows same length
    int cols = matrix[0].length;
    for (int i = 1; i < matrix.length; i++) {
        if (matrix[i].length != cols) {
            throw new IllegalArgumentException("All rows must have same length");
        }
    }
    
    // Main logic...
}
```

---

## 🧪 Test Cases

### Test Case 1: Target Found (Middle)
```java
Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
Expected: true
```

### Test Case 2: Target Not Found
```java
Input: Same matrix, target = 20
Expected: false
```

### Test Case 3: Target at Top-Left
```java
Input: matrix = [[1,4],[2,5]], target = 1
Expected: true
```

### Test Case 4: Target at Bottom-Right
```java
Input: matrix = [[1,4],[2,5]], target = 5
Expected: true
```

### Test Case 5: Single Element
```java
Input: matrix = [[5]], target = 5
Expected: true
```

### Test Case 6: Single Row
```java
Input: matrix = [[1,3,5,7,9]], target = 5
Expected: true
```

### Test Case 7: Negative Numbers
```java
Input: matrix = [[-10,-5,-1],[0,2,4]], target = -5
Expected: true
```

---

## 💡 Key Concepts Illustrated

### 1. Why Staircase Works

```
Starting from top-right:

[1,   4,  7, 11, 15] ← Start
[2,   5,  8, 12, 19]
[3,   6,  9, 16, 22]

At any position (r, c):
- Elements to RIGHT are larger (row sorted)
- Elements BELOW are larger (column sorted)
- Elements to LEFT are smaller (row sorted)
- Elements ABOVE are smaller (column sorted)

Decision:
- If current > target: Everything RIGHT is larger → Go LEFT
- If current < target: Everything ABOVE is smaller → Go DOWN
- If current == target: Found!

Each move eliminates a row or column!
```

### 2. Starting Corners Comparison

| Start Position | Move on > target | Move on < target | Works? |
|----------------|------------------|------------------|--------|
| **Top-Right** | ← Left | ↓ Down | ✅ |
| **Bottom-Left** | ↑ Up | → Right | ✅ |
| Top-Left | ↓ or → ? | Ambiguous | ❌ |
| Bottom-Right | ← or ↑ ? | Ambiguous | ❌ |

Only two corners provide unambiguous decisions!

### 3. Search Space Reduction

```
Matrix 5×5 (25 elements):

Start at top-right
Each comparison eliminates:
- One row (5 elements), OR
- One column (5 elements)

Maximum steps = 5 + 5 - 1 = 9
General: m + n - 1 steps worst case
```

### 4. Comparison to Binary Search

```
Standard Binary Search:
- Requires fully sorted 1D array
- O(log n)

Staircase Search:
- Works on 2D sorted (rows + columns)
- O(m + n)
- Can't do better than O(m + n) for this structure
```

---

## 🎓 Interview Discussion Points

### When Interviewer Asks This Problem

**1. Clarify Requirements**
```
Q: "Are rows sorted? Columns sorted?"
Q: "Can matrix be empty?"
Q: "Return boolean or position?"
Q: "Can matrix have duplicates?"
Q: "Are rows guaranteed same length?"
```

**2. Explain Your Approach**
```
"Since both rows and columns are sorted, I'll use the
staircase search starting from top-right corner.

At each step:
- If current > target: move left (eliminate column)
- If current < target: move down (eliminate row)
- If equal: found!

This gives O(m + n) time, which is optimal for this
matrix structure."
```

**3. Discuss Why Optimal**
```
"We can't do better than O(m + n) because:
- Target could be at bottom-left corner
- Starting top-right, we need m down moves and n left moves
- We must traverse at least m + n - 1 elements worst case

So O(m + n) is optimal for this problem."
```

### Common Follow-up Questions

**Q: "What if we need to find all occurrences?"**
```java
A: "Would need to continue search after finding first match.
   Still O(m + n) worst case if we continue eliminating.
   Collect positions in a list:
   
   List<int[]> positions = new ArrayList<>();
   // Use staircase, add positions when found
   // Don't return immediately"
```

**Q: "Return position instead of boolean?"**
```java
A: "Return int[] {row, col} when found:
   
   if (matrix[row][col] == target) {
       return new int[]{row, col};
   }
   
   return new int[]{-1, -1};  // Not found"
```

**Q: "What if only rows are sorted (not columns)?"**
```java
A: "Would use binary search on each row: O(m log n)
   Can't use staircase as it needs both properties."
```

**Q: "Count elements less than target?"**
```java
A: "Modified staircase from bottom-left:
   int count = 0;
   int row = m - 1, col = 0;
   while (row >= 0 && col < n) {
       if (matrix[row][col] < target) {
           count += row + 1;  // All above in column
           col++;
       } else {
           row--;
       }
   }
   return count;"
```

---

## 🔄 Related Problems

### Similar Problems to Practice

1. **Search a 2D Matrix** - Fully sorted matrix (different property)
2. **Kth Smallest in Sorted Matrix** - Find kth element
3. **Row with Maximum Ones** - Count 1s (similar staircase)
4. **Count Negative Numbers** - Counting variant with staircase
5. **Lucky Numbers in Matrix** - Min in row, max in column

### Difficulty Progression

```
Search in Sorted Matrix (Medium)
    ↓
Search in 2D Matrix II (Medium) ← You are here
    ↓
Kth Smallest in Sorted Matrix (Medium-Hard)
    ↓
Count Negative Numbers (Easy-Medium)
```

---

## 🎯 Variations to Practice

### Variation 1: Find Position
```java
public int[] searchMatrixPosition(int[][] matrix, int target) {
    // Return [row, col] if found, [-1, -1] if not
}
```

### Variation 2: Count Occurrences
```java
public int countOccurrences(int[][] matrix, int target) {
    // Count how many times target appears
}
```

### Variation 3: Find All Positions
```java
public List<int[]> findAllPositions(int[][] matrix, int target) {
    // Return list of all [row, col] where target exists
}
```

### Variation 4: Count Less Than Target
```java
public int countLessThan(int[][] matrix, int target) {
    // Count elements < target using staircase
}
```

---

## 📚 Additional Learning

### Visual Understanding

```
Matrix:
[1,   4,  7, 11]
[2,   5,  8, 12]
[3,   6,  9, 13]

Searching for 6:

Start: 11 > 6 → ←
       [1,   4,  7̲, 11]
       
Next:  7 > 6 → ←
       [1,   4̲,  7, 11]
       
Next:  4 < 6 → ↓
       [1,   4, ...
       [2,   5̲  ...
       
Next:  5 < 6 → ↓
       ...
       [3,   6̲  ...
       
Found: 6 == 6 ✓
```

### Why Not Top-Left or Bottom-Right?

```
Top-Left:
[1̲,   4,  7]
If target = 5:
- 1 < 5: Go right OR down? Ambiguous!
- Can't eliminate half the search space

Bottom-Right:
[7, 11, 15̲]
If target = 10:
- 15 > 10: Go left OR up? Ambiguous!
- Can't eliminate half the search space

Only Top-Right and Bottom-Left give clear decisions!
```

---

## 🔗 Resources

- [LeetCode 240 - Search a 2D Matrix II](https://leetcode.com/problems/search-a-2d-matrix-ii/)
- [Staircase Search Explained](https://www.geeksforgeeks.org/search-in-row-wise-and-column-wise-sorted-matrix/)
- [Matrix Search Problems](https://leetcode.com/tag/matrix/)

---

<div align="center">

**Master the Staircase Search Algorithm! 🔎**

*An elegant O(m+n) solution for 2D sorted matrices*

*Last Updated: December 2024*

</div>
