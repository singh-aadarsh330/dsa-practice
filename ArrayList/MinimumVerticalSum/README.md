# 📊 Minimum Vertical Sum

[![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green?style=flat-square)](.)
[![Concept](https://img.shields.io/badge/Concept-Matrix%20Traversal-blue?style=flat-square)](.)

> Find the column with the minimum sum in a 2D matrix. Master column-wise traversal and accumulation patterns.

---

## 📋 Problem Description

### The Challenge

Given a 2D matrix (m × n), find the **minimum sum** among all vertical columns.

**Requirements:**
1. Calculate the sum of each column
2. Return the minimum sum found
3. Handle negative numbers
4. Work with any matrix dimensions

**Input:**
- A 2D integer matrix (m × n)
- m rows, n columns

**Output:**
- Integer representing the minimum column sum

---

### Examples

**Example 1: Basic Case**
```
Input: 
[
  [1, 2, 3],
  [4, 5, 6],
  [7, 8, 9]
]

Column Sums:
Column 0: 1 + 4 + 7 = 12
Column 1: 2 + 5 + 8 = 15
Column 2: 3 + 6 + 9 = 18

Output: 12
Reason: Column 0 has minimum sum
```

**Example 2: With Negative Numbers**
```
Input:
[
  [5, -3, 7],
  [2,  1, 4],
  [8, -2, 3]
]

Column Sums:
Column 0: 5 + 2 + 8 = 15
Column 1: -3 + 1 + (-2) = -4
Column 2: 7 + 4 + 3 = 14

Output: -4
Reason: Column 1 has minimum (negative) sum
```

**Example 3: Single Row**
```
Input: [[5, 2, 8, 1]]

Column Sums:
Column 0: 5
Column 1: 2
Column 2: 8
Column 3: 1

Output: 1
Reason: Minimum value in single row
```

**Example 4: Single Column**
```
Input:
[
  [3],
  [7],
  [2]
]

Column Sum:
Column 0: 3 + 7 + 2 = 12

Output: 12
Reason: Only one column
```

**Example 5: All Negative**
```
Input:
[
  [-1, -5, -3],
  [-4, -2, -6]
]

Column Sums:
Column 0: -1 + (-4) = -5
Column 1: -5 + (-2) = -7
Column 2: -3 + (-6) = -9

Output: -9
Reason: Column 2 has minimum sum
```

---

## 🎯 Learning Objectives

This problem teaches:
- **Column-wise matrix traversal** - Iterate by columns instead of rows
- **Accumulation pattern** - Sum elements efficiently
- **Minimum tracking** - Find minimum across multiple values
- **Edge case handling** - Single row/column, negative numbers
- **2D array indexing** - Access elements by [row][col]

---

## 🔧 Approach 1: Nested Loop (Column-wise)

### Algorithm

**Step 1: Initialize minimum**
- Set min to Integer.MAX_VALUE

**Step 2: For each column**
- Calculate sum of all elements in that column
- Update minimum if current sum is smaller

**Step 3: Return minimum**

### Implementation

```java
public int minimumVerticalSum(int[][] matrix) {
    // Edge case: empty matrix
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        return 0;
    }
    
    int rows = matrix.length;
    int cols = matrix[0].length;
    int minSum = Integer.MAX_VALUE;
    
    // Iterate through each column
    for (int col = 0; col < cols; col++) {
        int columnSum = 0;
        
        // Sum all elements in current column
        for (int row = 0; row < rows; row++) {
            columnSum += matrix[row][col];
        }
        
        // Update minimum
        minSum = Math.min(minSum, columnSum);
    }
    
    return minSum;
}
```

**Complexity:**
- **Time:** O(m × n) where m = rows, n = columns
  - O(n) to iterate columns
  - O(m) to sum each column
  - Total: O(m × n)
- **Space:** O(1) - only using variables

**Pros:**
- Simple and intuitive
- Easy to understand
- Minimal space usage
- Clear column-wise traversal

**Cons:**
- Can't optimize below O(m × n) - must visit every element

---

## 🔧 Approach 2: Row-wise with Column Accumulators

### Algorithm

Use an array to accumulate column sums while traversing row by row.

**Optimization:**
- Single pass through matrix
- Track all column sums simultaneously

### Implementation

```java
public int minimumVerticalSum(int[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        return 0;
    }
    
    int rows = matrix.length;
    int cols = matrix[0].length;
    
    // Array to store sum of each column
    int[] columnSums = new int[cols];
    
    // Traverse row by row, accumulating column sums
    for (int row = 0; row < rows; row++) {
        for (int col = 0; col < cols; col++) {
            columnSums[col] += matrix[row][col];
        }
    }
    
    // Find minimum among column sums
    int minSum = columnSums[0];
    for (int col = 1; col < cols; col++) {
        minSum = Math.min(minSum, columnSums[col]);
    }
    
    return minSum;
}
```

**Complexity:**
- **Time:** O(m × n) - still visit every element
- **Space:** O(n) - array to store column sums

**Pros:**
- Better cache locality (row-wise access)
- Can find all column sums in one pass
- Useful if you need multiple column statistics

**Cons:**
- Extra space O(n)
- Not significantly faster in practice

---

## 🔧 Approach 3: Streaming with Early Updates

### Algorithm

Track minimum while calculating column sums - no need to store all sums.

### Implementation

```java
public int minimumVerticalSum(int[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        return 0;
    }
    
    int rows = matrix.length;
    int cols = matrix[0].length;
    int minSum = Integer.MAX_VALUE;
    
    // Process each column
    for (int col = 0; col < cols; col++) {
        int sum = 0;
        for (int row = 0; row < rows; row++) {
            sum += matrix[row][col];
        }
        minSum = Math.min(minSum, sum);
    }
    
    return minSum;
}
```

**Complexity:**
- **Time:** O(m × n)
- **Space:** O(1)

**Pros:**
- Optimal space usage
- Clean and readable
- Best for this specific problem

---

## 📊 Comparison of Approaches

| Approach | Time | Space | Cache Locality | Best For |
|----------|------|-------|----------------|----------|
| **Column-wise** | O(m×n) | O(1) | ⭐⭐⭐ | **This problem** |
| **Row-wise Accumulator** | O(m×n) | O(n) | ⭐⭐⭐⭐⭐ | Multiple stats |
| **Streaming** | O(m×n) | O(1) | ⭐⭐⭐ | Minimal memory |

**Recommendation:** Use **Column-wise** (Approach 1) for clarity and optimal space.

---

## 🎯 Step-by-Step Dry Run

### Input:
```java
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};
```

### Execution (Approach 1):

**Initialization:**
```
rows = 3
cols = 3
minSum = Integer.MAX_VALUE
```

**Column 0:**
```
col = 0
columnSum = 0

row = 0: columnSum = 0 + 1 = 1
row = 1: columnSum = 1 + 4 = 5
row = 2: columnSum = 5 + 7 = 12

minSum = min(MAX_VALUE, 12) = 12
```

**Column 1:**
```
col = 1
columnSum = 0

row = 0: columnSum = 0 + 2 = 2
row = 1: columnSum = 2 + 5 = 7
row = 2: columnSum = 7 + 8 = 15

minSum = min(12, 15) = 12
```

**Column 2:**
```
col = 2
columnSum = 0

row = 0: columnSum = 0 + 3 = 3
row = 1: columnSum = 3 + 6 = 9
row = 2: columnSum = 9 + 9 = 18

minSum = min(12, 18) = 12
```

**Result:** 12

---

## ⚠️ Edge Cases & Validation

### Edge Cases to Consider

**1. Empty Matrix**
```java
Input: []
Output: 0 (or throw exception)
Handling: Check at start
```

**2. Single Element**
```java
Input: [[5]]
Output: 5
Only one column with one element
```

**3. Single Row**
```java
Input: [[1, 2, 3, 4]]
Output: 1
Each column has only one element
```

**4. Single Column**
```java
Input: [[1], [2], [3]]
Output: 6 (1 + 2 + 3)
Only one column to consider
```

**5. All Same Values**
```java
Input: [[5, 5, 5], [5, 5, 5]]
Output: 10
All columns have same sum
```

**6. All Negative**
```java
Input: [[-5, -2], [-3, -8]]
Column 0: -8, Column 1: -10
Output: -10
```

**7. Mixed Positive and Negative**
```java
Input: [[10, -5], [-3, 2]]
Column 0: 7, Column 1: -3
Output: -3
```

**8. Large Values**
```java
Input: [[Integer.MAX_VALUE, 1], [1, 1]]
Handle potential overflow
Use long if needed
```

### Input Validation

```java
public int minimumVerticalSum(int[][] matrix) {
    // Validate input
    if (matrix == null) {
        throw new IllegalArgumentException("Matrix cannot be null");
    }
    
    if (matrix.length == 0) {
        throw new IllegalArgumentException("Matrix cannot be empty");
    }
    
    if (matrix[0].length == 0) {
        throw new IllegalArgumentException("Matrix rows cannot be empty");
    }
    
    // Validate all rows have same length
    int cols = matrix[0].length;
    for (int row = 1; row < matrix.length; row++) {
        if (matrix[row].length != cols) {
            throw new IllegalArgumentException("All rows must have same length");
        }
    }
    
    // Main logic...
}
```

---

## 🧪 Test Cases

### Test Case 1: Basic 3×3
```java
Input: [[1,2,3], [4,5,6], [7,8,9]]
Expected: 12
Reason: Column 0 sum (1+4+7=12) is minimum
```

### Test Case 2: Negative Numbers
```java
Input: [[5,-3,7], [2,1,4], [8,-2,3]]
Expected: -4
Reason: Column 1 sum (-3+1-2=-4) is minimum
```

### Test Case 3: Single Row
```java
Input: [[5,2,8,1,9]]
Expected: 1
Reason: Minimum element in row
```

### Test Case 4: Single Column
```java
Input: [[3], [7], [2], [5]]
Expected: 17
Reason: Only column sum (3+7+2+5=17)
```

### Test Case 5: All Negative
```java
Input: [[-1,-5,-3], [-4,-2,-6]]
Expected: -9
Reason: Column 2 sum (-3-6=-9) is minimum
```

### Test Case 6: Zero Values
```java
Input: [[0,5,3], [0,2,1]]
Expected: 0
Reason: Column 0 sum (0+0=0) is minimum
```

### Test Case 7: Large Matrix
```java
Input: 100×100 matrix with random values
Expected: Correct minimum column sum
Test performance and correctness
```

---

## 💡 Key Concepts Illustrated

### 1. Column-wise Traversal Pattern

```java
// Outer loop: columns
for (int col = 0; col < cols; col++) {
    // Inner loop: rows
    for (int row = 0; row < rows; row++) {
        // Access: matrix[row][col]
    }
}
```

### 2. Row-wise Traversal Pattern

```java
// Outer loop: rows (more cache-friendly)
for (int row = 0; row < rows; row++) {
    // Inner loop: columns
    for (int col = 0; col < cols; col++) {
        // Access: matrix[row][col]
    }
}
```

### 3. Accumulation Pattern

```java
int sum = 0;
for (int i = 0; i < n; i++) {
    sum += array[i];
}
```

### 4. Minimum Tracking

```java
int min = Integer.MAX_VALUE;  // Start with largest possible
for (int value : values) {
    min = Math.min(min, value);
}
```

### 5. 2D Array Dimensions

```java
int[][] matrix = new int[rows][cols];
int rows = matrix.length;        // Number of rows
int cols = matrix[0].length;     // Number of columns
```

---

## 🎓 Interview Discussion Points

### When Interviewer Asks This Problem

**1. Clarify Requirements**
```
Q: "Can the matrix be empty?"
Q: "Can elements be negative?"
Q: "Are all rows guaranteed to have same length?"
Q: "What should return for single element matrix?"
Q: "Can values overflow when summed?"
```

**2. Explain Your Approach**
```
"I'll iterate through each column, calculate its sum,
 and track the minimum sum found. This requires O(m×n)
 time since we must examine every element, and O(1) space
 since we only need a few variables."
```

**3. Discuss Trade-offs**
```
"We could use O(n) space to store all column sums if
 we needed them later, but for just finding minimum,
 O(1) space is sufficient. Row-wise traversal has better
 cache locality but same time complexity."
```

**4. Optimization Discussion**
```
"This problem can't be optimized below O(m×n) time
 since we must visit every element at least once.
 Any 'optimization' would just reorganize the same work."
```

### Common Follow-up Questions

**Q: "What if we need both minimum AND maximum column sums?"**
```java
A: "Track both in same pass - still O(m×n) time, O(1) space:
   int minSum = Integer.MAX_VALUE;
   int maxSum = Integer.MIN_VALUE;
   // Update both while calculating each column sum"
```

**Q: "What if matrix is extremely wide (many columns)?"**
```java
A: "Could parallelize column sum calculations since they're
    independent. Use parallel streams or fork-join."
```

**Q: "Return the column index instead of sum?"**
```java
A: "Track index along with minimum sum:
   int minSum = MAX_VALUE, minIndex = 0;
   if (currentSum < minSum) {
       minSum = currentSum;
       minIndex = col;
   }"
```

**Q: "What about row sum instead?"**
```java
A: "Same algorithm, just swap loop order - iterate rows
    in outer loop, calculate each row sum."
```

---

## 🔄 Related Problems

### Similar Problems to Practice

1. **Maximum Vertical Sum** - Find column with maximum sum
2. **Row with Minimum Sum** - Same problem but for rows
3. **Sum of Matrix Diagonals** - Sum main and anti-diagonals
4. **Lucky Numbers in Matrix** - Min in row and max in column
5. **Matrix Block Sum** - Sum of submatrices

### Difficulty Progression

```
Array Sum (Easy)
    ↓
Minimum Vertical Sum (Easy) ← You are here
    ↓
Lucky Numbers in Matrix (Easy-Medium)
    ↓
Matrix Block Sum (Medium)
```

---

## 🎯 Variations to Practice

### Variation 1: Return Column Index
```java
public int findMinimumSumColumnIndex(int[][] matrix) {
    // Return index of column with minimum sum
    // If tie, return smallest index
}
```

### Variation 2: All Column Sums
```java
public int[] getAllColumnSums(int[][] matrix) {
    // Return array of all column sums
    // Useful for statistics
}
```

### Variation 3: K Minimum Columns
```java
public List<Integer> findKMinimumColumns(int[][] matrix, int k) {
    // Return indices of k columns with smallest sums
    // Use heap or sorting
}
```

### Variation 4: Minimum Among Both Row and Column Sums
```java
public int minimumSum(int[][] matrix) {
    // Return minimum among all row sums AND column sums
}
```

---

## 📚 Additional Learning

### Why Column-wise Traversal?

1. **Natural for this problem** - We need column sums
2. **Clear separation** - One sum per outer loop iteration
3. **Easy to reason about** - Each column processed independently

### Cache Locality Considerations

**Row-wise access** (better cache performance):
```java
matrix[row][col]  // Adjacent in memory
```

**Column-wise access** (worse cache performance):
```java
matrix[row][col]  // Jumps between rows
```

For large matrices, row-wise with accumulator array may be faster despite O(n) space.

### When to Use Each Approach

- **Column-wise**: Problem naturally column-oriented, minimal space
- **Row-wise + Array**: Need multiple column statistics, cache matters
- **Streaming**: Extremely memory constrained

---

## 🔗 Resources

- [2D Array Traversal Patterns](https://www.geeksforgeeks.org/traverse-a-given-matrix-using-recursion/)
- [Cache-Friendly Programming](https://en.wikipedia.org/wiki/Locality_of_reference)
- [Matrix Problems on LeetCode](https://leetcode.com/tag/matrix/)

---

<div align="center">

**Master Matrix Column Operations! 📊**

*A fundamental problem for understanding 2D array traversal patterns*

*Last Updated: December 2024*

</div>
