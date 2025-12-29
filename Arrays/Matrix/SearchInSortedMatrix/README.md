# 🔍 Search in a 2D Sorted Matrix

[![Difficulty](https://img.shields.io/badge/Difficulty-Medium-orange?style=flat-square)](.)
[![Concept](https://img.shields.io/badge/Concept-Binary%20Search-blue?style=flat-square)](.)

> Search for a target value in a fully sorted matrix efficiently using binary search. Master treating 2D arrays as 1D sorted lists.

---

## 📋 Problem Description

### The Challenge

Write an efficient algorithm to search for a value `target` in an `m × n` integer matrix with the following properties:

**Matrix Properties:**
1. Integers in each row are sorted from **left to right**
2. The **first integer** of each row is **greater than** the last integer of the previous row

This means the entire matrix can be viewed as a single sorted list!

**Input:**
- `m × n` integer matrix
- Integer `target` to search for

**Output:**
- `true` if target exists in matrix
- `false` otherwise

---

### Examples

**Example 1: Target Found**
```
Input:
matrix = [
  [1,  3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 60]
]
target = 3

If flattened: [1, 3, 5, 7, 10, 11, 16, 20, 23, 30, 34, 60]
                  ↑ found at position 1

Output: true
```

**Example 2: Target Not Found**
```
Input:
matrix = [
  [1,  3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 60]
]
target = 13

If flattened: [1, 3, 5, 7, 10, 11, 16, 20, 23, 30, 34, 60]
13 would be between 11 and 16, but doesn't exist

Output: false
```

**Example 3: Single Element**
```
Input:
matrix = [[5]]
target = 5

Output: true
```

**Example 4: Target at Edges**
```
Input:
matrix = [[1, 3], [5, 7]]
target = 1  (top-left)

Output: true

target = 7  (bottom-right)
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
- **Binary search on 2D array** - Treat matrix as 1D array
- **Index mapping** - Convert 1D index to 2D coordinates
- **Logarithmic search** - O(log(m×n)) efficiency
- **Sorted structure exploitation** - Use properties to optimize
- **Mathematical transformations** - Map between dimensions

---

## 🔧 Approach 1: Binary Search (Treat as 1D Array) ⭐

### Algorithm

**Key Insight:** Since the matrix is fully sorted, we can treat it as a single sorted array and apply binary search.

**Index Mapping:**
- 1D index `mid` → 2D coordinates: `[mid / cols][mid % cols]`
- Total elements: `m × n`

**Steps:**
1. Set `left = 0`, `right = m × n - 1`
2. While `left <= right`:
   - Calculate `mid`
   - Convert `mid` to row and column
   - Compare `matrix[row][col]` with target
   - Adjust search range

### Implementation

```java
public boolean searchMatrix(int[][] matrix, int target) {
    // Edge cases
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        return false;
    }
    
    int m = matrix.length;        // rows
    int n = matrix[0].length;     // columns
    int left = 0;
    int right = m * n - 1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        // Convert 1D index to 2D coordinates
        int row = mid / n;
        int col = mid % n;
        int midValue = matrix[row][col];
        
        if (midValue == target) {
            return true;
        } else if (midValue < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    
    return false;
}
```

**Complexity:**
- **Time:** O(log(m × n)) - binary search on m×n elements
- **Space:** O(1) - only using variables

**Pros:**
- ⭐ Optimal time complexity
- Simple and elegant
- Leverages full sorted property
- Single binary search

**Cons:**
- Requires specific matrix property
- Index conversion slightly tricky

---

## 🔧 Approach 2: Two Binary Searches

### Algorithm

1. **First binary search:** Find the row containing target
2. **Second binary search:** Search within that row

### Implementation

```java
public boolean searchMatrix(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        return false;
    }
    
    int m = matrix.length;
    int n = matrix[0].length;
    
    // Step 1: Binary search to find correct row
    int targetRow = -1;
    int top = 0, bottom = m - 1;
    
    while (top <= bottom) {
        int midRow = top + (bottom - top) / 2;
        
        if (matrix[midRow][0] <= target && target <= matrix[midRow][n - 1]) {
            targetRow = midRow;
            break;
        } else if (target < matrix[midRow][0]) {
            bottom = midRow - 1;
        } else {
            top = midRow + 1;
        }
    }
    
    // If no valid row found
    if (targetRow == -1) {
        return false;
    }
    
    // Step 2: Binary search within the row
    int left = 0, right = n - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (matrix[targetRow][mid] == target) {
            return true;
        } else if (matrix[targetRow][mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    
    return false;
}
```

**Complexity:**
- **Time:** O(log m + log n) = O(log(m × n))
- **Space:** O(1)

**Pros:**
- More intuitive for some
- Clear two-step process

**Cons:**
- More code
- Two separate binary searches
- Not simpler than Approach 1

---

## 🔧 Approach 3: Linear Search (Naive)

### Algorithm

Search every element until found or exhausted.

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
- **Time:** O(m × n) - worst case check all elements
- **Space:** O(1)

**Pros:**
- Simple to implement
- Works on any matrix

**Cons:**
- ❌ Doesn't use sorted property
- ❌ Very inefficient
- ❌ Never use in interview

---

## 📊 Comparison of Approaches

| Approach | Time | Space | Difficulty | Interview Score |
|----------|------|-------|------------|-----------------|
| **Binary Search (1D)** | O(log(m×n)) | O(1) | ⭐⭐⭐ | **⭐⭐⭐⭐⭐** |
| **Two Binary Searches** | O(log(m×n)) | O(1) | ⭐⭐⭐⭐ | ⭐⭐⭐⭐ |
| **Linear Search** | O(m×n) | O(1) | ⭐ | ❌ |

**Recommendation:** Use **Binary Search (Approach 1)** - optimal and elegant.

---

## 🎯 Step-by-Step Dry Run

### Input:
```java
int[][] matrix = {
    {1,  3,  5,  7},
    {10, 11, 16, 20},
    {23, 30, 34, 60}
};
int target = 11;
```

### Execution (Binary Search as 1D):

**Initialization:**
```
m = 3, n = 4
Total elements = 3 × 4 = 12
left = 0, right = 11

Logical view: [1, 3, 5, 7, 10, 11, 16, 20, 23, 30, 34, 60]
               0  1  2  3   4   5   6   7   8   9  10  11
```

**Iteration 1:**
```
mid = (0 + 11) / 2 = 5
row = 5 / 4 = 1
col = 5 % 4 = 1
matrix[1][1] = 11

11 == 11 → FOUND!
Return true
```

### Another Example - Target Not Found:

**Input:** target = 13

**Iteration 1:**
```
mid = 5, matrix[1][1] = 11
11 < 13 → left = 6
```

**Iteration 2:**
```
left = 6, right = 11
mid = (6 + 11) / 2 = 8
row = 8 / 4 = 2, col = 8 % 4 = 0
matrix[2][0] = 23
23 > 13 → right = 7
```

**Iteration 3:**
```
left = 6, right = 7
mid = (6 + 7) / 2 = 6
row = 6 / 4 = 1, col = 6 % 4 = 2
matrix[1][2] = 16
16 > 13 → right = 5
```

**Iteration 4:**
```
left = 6, right = 5
left > right → EXIT
Return false
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
Standard binary search on row
```

**5. Single Column**
```java
Input: matrix = [[1], [3], [5], [7]], target = 5
Output: true
```

**6. Target Less Than Minimum**
```java
Input: matrix = [[1, 3], [5, 7]], target = 0
Output: false
```

**7. Target Greater Than Maximum**
```java
Input: matrix = [[1, 3], [5, 7]], target = 10
Output: false
```

**8. Negative Numbers**
```java
Input: matrix = [[-10, -5], [-3, 0], [5, 10]], target = -3
Output: true
```

### Input Validation

```java
public boolean searchMatrix(int[][] matrix, int target) {
    // Validate input
    if (matrix == null) {
        throw new IllegalArgumentException("Matrix cannot be null");
    }
    
    if (matrix.length == 0 || matrix[0].length == 0) {
        return false;  // Empty matrix
    }
    
    // Validate sorted property (optional, costly)
    // Could add validation that each row is sorted
    // and first of next row > last of previous row
    
    // Main logic...
}
```

---

## 🧪 Test Cases

### Test Case 1: Target Found (Middle)
```java
Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 11
Expected: true
```

### Test Case 2: Target Not Found
```java
Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
Expected: false
```

### Test Case 3: Target at Start
```java
Input: matrix = [[1,3,5,7],[10,11,16,20]], target = 1
Expected: true
```

### Test Case 4: Target at End
```java
Input: matrix = [[1,3,5,7],[10,11,16,20]], target = 20
Expected: true
```

### Test Case 5: Single Element
```java
Input: matrix = [[5]], target = 5
Expected: true
```

### Test Case 6: Single Row
```java
Input: matrix = [[1,3,5,7,9]], target = 7
Expected: true
```

### Test Case 7: Negative Numbers
```java
Input: matrix = [[-10,-5,-3],[0,5,10]], target = -3
Expected: true
```

---

## 💡 Key Concepts Illustrated

### 1. 1D to 2D Index Conversion

```java
// Given 1D index and number of columns
int row = index / cols;
int col = index % cols;

// Example: index = 5, cols = 4
// row = 5 / 4 = 1
// col = 5 % 4 = 1
// Position: [1][1]
```

### 2. 2D to 1D Index Conversion

```java
// Given row, col, and number of columns
int index = row * cols + col;

// Example: row = 1, col = 1, cols = 4
// index = 1 * 4 + 1 = 5
```

### 3. Binary Search Template

```java
int left = 0, right = n - 1;

while (left <= right) {
    int mid = left + (right - left) / 2;  // Avoid overflow
    
    if (arr[mid] == target) {
        return mid;
    } else if (arr[mid] < target) {
        left = mid + 1;
    } else {
        right = mid - 1;
    }
}

return -1;  // Not found
```

### 4. Why This Works

```
Matrix:
[1,  3,  5,  7]    Indices 0-3
[10, 11, 16, 20]   Indices 4-7
[23, 30, 34, 60]   Indices 8-11

Fully sorted when viewed as:
[1, 3, 5, 7, 10, 11, 16, 20, 23, 30, 34, 60]

We can do binary search on this logical view
without actually creating the 1D array!
```

---

## 🎓 Interview Discussion Points

### When Interviewer Asks This Problem

**1. Clarify Requirements**
```
Q: "Can the matrix be empty?"
Q: "Are all elements distinct?"
Q: "Can matrix have negative numbers?"
Q: "Is matrix always rectangular (all rows same length)?"
Q: "What to return if target appears multiple times?"
```

**2. Explain Your Approach**
```
"Since the matrix is fully sorted - each row sorted AND
first element of each row > last of previous row - I can
treat it as a single sorted array of m×n elements.

I'll use binary search with O(log(m×n)) time complexity.
The key is converting 1D index to 2D coordinates:
- row = mid / columns
- col = mid % columns"
```

**3. Discuss Trade-offs**
```
"Binary search is optimal at O(log(m×n)) time, O(1) space.
We could do two binary searches (find row, then search row)
which has same time complexity but more code.
Linear search O(m×n) doesn't leverage the sorted property."
```

**4. Edge Cases**
```
"Need to handle:
- Empty matrix
- Single element
- Target at boundaries
- Target outside range
- Negative numbers"
```

### Common Follow-up Questions

**Q: "What if rows are sorted but the row-to-row property doesn't hold?"**
```java
A: "That's 'Search in 2D Matrix II' - different problem!
   Would need different approach:
   - Start from top-right or bottom-left
   - If target > current, move down
   - If target < current, move left
   - Time: O(m + n)"
```

**Q: "Return the position instead of boolean?"**
```java
A: "Return int[] {row, col} instead of boolean.
   Same algorithm, just return coordinates when found:
   
   if (midValue == target) {
       return new int[]{row, col};
   }
   
   return new int[]{-1, -1};  // Not found"
```

**Q: "What if we need to search for multiple targets?"**
```java
A: "Call searchMatrix() for each target.
   Or if many searches, could build auxiliary structure
   like HashMap, but that defeats memory efficiency."
```

**Q: "Can you optimize for very tall/narrow matrices?"**
```java
A: "Binary search is already optimal for any shape.
   Time is always O(log(m×n)) regardless of dimensions."
```

---

## 🔄 Related Problems

### Similar Problems to Practice

1. **Search in 2D Matrix II** - Rows and columns sorted, no row-to-row property
2. **Find Peak Element 2D** - Find local maximum in matrix
3. **Kth Smallest in Sorted Matrix** - Find kth element
4. **Search in Rotated Sorted Array** - 1D version with rotation
5. **Count Negative Numbers in Sorted Matrix** - Counting variant

### Difficulty Progression

```
Binary Search (Easy)
    ↓
Search in Sorted Matrix (Medium) ← You are here
    ↓
Search in 2D Matrix II (Medium)
    ↓
Find Median in 2D Matrix (Hard)
```

---

## 🎯 Variations to Practice

### Variation 1: Return Position
```java
public int[] searchMatrixPosition(int[][] matrix, int target) {
    // Return [row, col] if found, [-1, -1] if not
}
```

### Variation 2: Count Occurrences
```java
public int countOccurrences(int[][] matrix, int target) {
    // Count how many times target appears
    // (Note: in fully sorted matrix, at most 1)
}
```

### Variation 3: Find Closest Element
```java
public int findClosest(int[][] matrix, int target) {
    // If target not found, return closest element
}
```

### Variation 4: Range Query
```java
public int countInRange(int[][] matrix, int low, int high) {
    // Count elements in range [low, high]
}
```

---

## 📚 Additional Learning

### Why This Matrix Property Matters

**Fully Sorted Matrix:**
```
[1,  3,  5]    Last of row 0 (5) < First of row 1 (10)
[10, 11, 16]   Last of row 1 (16) < First of row 2 (23)
[23, 30, 34]
```

This allows treating as: `[1, 3, 5, 10, 11, 16, 23, 30, 34]`

**Vs. Partially Sorted Matrix (Different Problem!):**
```
[1,  4,  7]    Last of row 0 (7) NOT < First of row 1 (2)
[2,  5,  8]
[3,  6,  9]
```

Cannot use single binary search here!

### Index Arithmetic Explained

```
For 3×4 matrix (3 rows, 4 cols):

Index  Row  Col  Calculation
  0     0    0   0/4=0, 0%4=0
  1     0    1   1/4=0, 1%4=1
  2     0    2   2/4=0, 2%4=2
  3     0    3   3/4=0, 3%4=3
  4     1    0   4/4=1, 4%4=0
  5     1    1   5/4=1, 5%4=1
  ...
  11    2    3   11/4=2, 11%4=3
```

---

## 🔗 Resources

- [Binary Search on LeetCode](https://leetcode.com/tag/binary-search/)
- [2D Matrix Problems](https://leetcode.com/tag/matrix/)
- [LeetCode 74 - Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix/)

---

<div align="center">

**Master Binary Search on 2D Arrays! 🔍**

*Learn to exploit sorted structure for logarithmic efficiency*

*Last Updated: December 2024*

</div>
