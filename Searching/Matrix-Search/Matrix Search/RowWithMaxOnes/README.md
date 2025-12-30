# 🎯 Row with Maximum Ones

[![Difficulty](https://img.shields.io/badge/Difficulty-Easy--Medium-orange?style=flat-square)](.)
[![Concept](https://img.shields.io/badge/Concept-Binary%20Matrix%20%7C%20Optimization-blue?style=flat-square)](.)

> Find the row containing the maximum number of 1s in a binary matrix where each row is sorted. Master efficient matrix traversal techniques.

---

## 📋 Problem Description

### The Challenge

Given an `m × n` **binary matrix** (containing only 0s and 1s) where **each row is sorted** in non-decreasing order, find the **row with the maximum number of 1s**.

**Matrix Properties:**
- Contains only 0s and 1s
- Each row is sorted: all 0s come before all 1s
- Row format: `[0, 0, 0, 1, 1, 1]` (0s first, then 1s)

**Input:**
- `m × n` binary matrix (sorted rows)

**Output:**
- Index of row with maximum 1s
- If tie, return smallest index

---

### Examples

**Example 1: Clear Winner**
```
Input:
matrix = [
  [0, 0, 0, 1],
  [0, 1, 1, 1],
  [1, 1, 1, 1],
  [0, 0, 0, 0]
]

Count of 1s per row:
Row 0: 1
Row 1: 3
Row 2: 4  ← Maximum
Row 3: 0

Output: 2
```

**Example 2: Tie - Return First**
```
Input:
matrix = [
  [0, 1, 1, 1],
  [0, 0, 1, 1],
  [1, 1, 1, 1],
  [0, 1, 1, 1]
]

Count of 1s per row:
Row 0: 3
Row 1: 2
Row 2: 4  ← Maximum
Row 3: 3

Output: 2
```

**Example 3: Multiple Rows with Same Max**
```
Input:
matrix = [
  [0, 0, 1, 1],
  [0, 0, 1, 1],
  [0, 0, 1, 1]
]

All rows have 2 ones

Output: 0  (First row in tie)
```

**Example 4: All Zeros**
```
Input:
matrix = [
  [0, 0, 0],
  [0, 0, 0]
]

Output: 0  (First row)
```

**Example 5: Single Row**
```
Input:
matrix = [[1, 1, 1, 1]]

Output: 0  (Only row)
```

---

## 🎯 Learning Objectives

This problem teaches:
- **Leveraging sorted property** - Use structure for optimization
- **Efficient counting** - Binary search for first 1
- **Staircase algorithm** - Optimal O(m + n) traversal
- **Trade-off analysis** - Simple vs optimal approaches
- **Matrix traversal patterns** - Different search strategies

---

## 🔧 Approach 1: Naive - Count Each Row

### Algorithm

For each row, count 1s and track maximum.

### Implementation

```java
public int rowWithMax1s(int[][] matrix) {
    if (matrix == null || matrix.length == 0) {
        return -1;
    }
    
    int maxRow = 0;
    int maxCount = 0;
    
    for (int row = 0; row < matrix.length; row++) {
        int count = 0;
        for (int col = 0; col < matrix[0].length; col++) {
            if (matrix[row][col] == 1) {
                count++;
            }
        }
        
        if (count > maxCount) {
            maxCount = count;
            maxRow = row;
        }
    }
    
    return maxRow;
}
```

**Complexity:**
- **Time:** O(m × n) - check every element
- **Space:** O(1)

**Pros:**
- Simple to implement
- Easy to understand

**Cons:**
- ❌ Doesn't use sorted property
- ❌ Inefficient for large matrices

---

## 🔧 Approach 2: Binary Search Per Row

### Algorithm

For each row, use binary search to find the **first occurrence of 1**, then calculate count.

**Key Insight:** In sorted row `[0,0,0,1,1,1]`, if first 1 is at index k, then count of 1s = n - k

### Implementation

```java
public int rowWithMax1s(int[][] matrix) {
    if (matrix == null || matrix.length == 0) {
        return -1;
    }
    
    int m = matrix.length;
    int n = matrix[0].length;
    int maxRow = 0;
    int maxCount = 0;
    
    for (int row = 0; row < m; row++) {
        // Find first occurrence of 1 in this row
        int firstOne = findFirstOne(matrix[row]);
        
        if (firstOne != -1) {
            int count = n - firstOne;
            
            if (count > maxCount) {
                maxCount = count;
                maxRow = row;
            }
        }
    }
    
    return maxRow;
}

// Binary search to find first occurrence of 1
private int findFirstOne(int[] row) {
    int left = 0, right = row.length - 1;
    int result = -1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (row[mid] == 1) {
            result = mid;
            right = mid - 1;  // Look for earlier occurrence
        } else {
            left = mid + 1;
        }
    }
    
    return result;
}
```

**Complexity:**
- **Time:** O(m × log n) - binary search for each row
- **Space:** O(1)

**Pros:**
- Much better than O(m × n)
- Uses sorted property
- Good for wide matrices

**Cons:**
- Still not optimal
- Can do better

---

## 🔧 Approach 3: Staircase Algorithm (Optimal) ⭐

### Algorithm

**Key Insight:** Start from **top-right corner**. Move left when seeing 1s, move down when seeing 0s.

**Why This Works:**
- If current = 1: all elements below in same column are also 1 (sorted)
  → Move left to potentially find more 1s
- If current = 0: current row can't have 1s to the left
  → Move down to next row

**Result:** Each step eliminates either a row or column → O(m + n)

### Implementation

```java
public int rowWithMax1s(int[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        return -1;
    }
    
    int m = matrix.length;
    int n = matrix[0].length;
    
    int maxRow = 0;
    int row = 0;
    int col = n - 1;  // Start from top-right
    
    while (row < m && col >= 0) {
        if (matrix[row][col] == 1) {
            // Found 1, this row could be answer
            maxRow = row;
            col--;  // Move left to find more 1s
        } else {
            // Found 0, move to next row
            row++;
        }
    }
    
    return maxRow;
}
```

**Complexity:**
- **Time:** O(m + n) - at most m + n steps
- **Space:** O(1)

**Pros:**
- ⭐ Optimal time complexity
- Elegant algorithm
- Single pass
- Interview gold

**Cons:**
- Requires understanding the logic
- Less intuitive initially

---

## 📊 Comparison of Approaches

| Approach | Time | Space | Difficulty | Interview Score |
|----------|------|-------|------------|-----------------|
| **Naive Count** | O(m×n) | O(1) | ⭐ | ⭐⭐ |
| **Binary Search** | O(m log n) | O(1) | ⭐⭐⭐ | ⭐⭐⭐⭐ |
| **Staircase** | O(m+n) | O(1) | ⭐⭐⭐⭐ | **⭐⭐⭐⭐⭐** |

**Recommendation:** Learn **Staircase Algorithm** - optimal and impressive in interviews!

---

## 🎯 Step-by-Step Dry Run

### Input:
```java
int[][] matrix = {
    {0, 0, 0, 1},
    {0, 1, 1, 1},
    {1, 1, 1, 1},
    {0, 0, 0, 0}
};
```

### Execution (Staircase Algorithm):

**Visualization:**
```
Start at top-right: ↓
[0, 0, 0, 1̲]  ← row = 0
[0, 1, 1, 1]
[1, 1, 1, 1]
[0, 0, 0, 0]
```

**Step 1:**
```
row = 0, col = 3
matrix[0][3] = 1
Found 1 → maxRow = 0
Move left: col = 2
```

**Step 2:**
```
row = 0, col = 2
matrix[0][2] = 0
Found 0 → Move down: row = 1
```

**Step 3:**
```
row = 1, col = 2
matrix[1][2] = 1
Found 1 → maxRow = 1
Move left: col = 1
```

**Step 4:**
```
row = 1, col = 1
matrix[1][1] = 1
Found 1 → maxRow = 1
Move left: col = 0
```

**Step 5:**
```
row = 1, col = 0
matrix[1][0] = 0
Found 0 → Move down: row = 2
```

**Step 6:**
```
row = 2, col = 0
matrix[2][0] = 1
Found 1 → maxRow = 2
Move left: col = -1
```

**Exit:** col < 0

**Result:** maxRow = 2 ✓

**Path Taken:**
```
[0, 0, 0, ①]
[0, ②, ③, x]
[④, x, x, x]
[0, 0, 0, 0]
```

---

## ⚠️ Edge Cases & Validation

### Edge Cases to Consider

**1. Empty Matrix**
```java
Input: matrix = []
Output: -1 (or 0 depending on spec)
```

**2. All Zeros**
```java
Input: [[0,0,0], [0,0,0]]
Output: 0
First row (all equal)
```

**3. All Ones**
```java
Input: [[1,1,1], [1,1,1]]
Output: 0
First row (all equal)
```

**4. Single Row**
```java
Input: [[0,0,1,1]]
Output: 0
Only row
```

**5. Single Column**
```java
Input: [[0], [1], [1]]
Output: 1 or 2
Row 1 and 2 both have 1 one
Return first: 1
```

**6. First Row All Ones**
```java
Input: [[1,1,1], [0,1,1], [0,0,1]]
Output: 0
First row has most
```

**7. Last Row All Ones**
```java
Input: [[0,0,1], [0,1,1], [1,1,1]]
Output: 2
Last row has most
```

**8. Diagonal Pattern**
```java
Input: [[1,1,1], [0,1,1], [0,0,1]]
Output: 0
```

### Input Validation

```java
public int rowWithMax1s(int[][] matrix) {
    // Validate matrix
    if (matrix == null) {
        throw new IllegalArgumentException("Matrix cannot be null");
    }
    
    if (matrix.length == 0 || matrix[0].length == 0) {
        return -1;  // or throw exception
    }
    
    // Validate binary matrix (optional, expensive)
    for (int[] row : matrix) {
        for (int val : row) {
            if (val != 0 && val != 1) {
                throw new IllegalArgumentException("Matrix must be binary");
            }
        }
    }
    
    // Main logic...
}
```

---

## 🧪 Test Cases

### Test Case 1: Standard Case
```java
Input: [[0,0,0,1],[0,1,1,1],[1,1,1,1],[0,0,0,0]]
Expected: 2
Row 2 has four 1s
```

### Test Case 2: Tie
```java
Input: [[0,1,1],[0,1,1],[0,1,1]]
Expected: 0
All rows equal, return first
```

### Test Case 3: All Zeros
```java
Input: [[0,0,0],[0,0,0]]
Expected: 0
```

### Test Case 4: All Ones
```java
Input: [[1,1,1],[1,1,1]]
Expected: 0
```

### Test Case 5: First Row Best
```java
Input: [[1,1,1,1],[0,1,1,1],[0,0,1,1]]
Expected: 0
```

### Test Case 6: Last Row Best
```java
Input: [[0,0,1],[0,1,1],[1,1,1]]
Expected: 2
```

### Test Case 7: Single Row
```java
Input: [[1,1,1,1]]
Expected: 0
```

---

## 💡 Key Concepts Illustrated

### 1. Staircase Algorithm Logic

```
Matrix:
[0, 0, 1]
[0, 1, 1]
[1, 1, 1]

Start top-right (row=0, col=2):
- If 1: Move LEFT (more 1s to the left)
- If 0: Move DOWN (no 1s to the left in this row)

Path: ↓
(0,2): 1 → LEFT
(0,1): 0 → DOWN
(1,1): 1 → LEFT
(1,0): 0 → DOWN
(2,0): 1 → DONE

Found most 1s in row 2
```

### 2. Why Start Top-Right?

```
Could also start BOTTOM-LEFT:
- If 1: Move UP (fewer 1s above)
- If 0: Move RIGHT (more 1s to the right)

Both work! Top-right is convention.
```

### 3. Binary Search for First 1

```java
// In sorted row: [0, 0, 0, 1, 1, 1]
// Find first 1 at index k
// Count of 1s = length - k

int left = 0, right = n - 1;
while (left <= right) {
    int mid = left + (right - left) / 2;
    if (row[mid] == 1) {
        result = mid;
        right = mid - 1;  // Look left
    } else {
        left = mid + 1;   // Look right
    }
}
```

### 4. Counting Pattern

```java
// For row: [0, 0, 0, 1, 1, 1]
//           0  1  2  3  4  5
// First 1 at index 3
// Count = 6 - 3 = 3 ones ✓
```

---

## 🎓 Interview Discussion Points

### When Interviewer Asks This Problem

**1. Clarify Requirements**
```
Q: "Are rows guaranteed to be sorted?"
Q: "What if multiple rows have same max?"
Q: "Can matrix be empty?"
Q: "Return index or count?"
Q: "0-indexed or 1-indexed?"
```

**2. Explain Your Approach**

*Start with Binary Search:*
```
"Since each row is sorted, I can use binary search to find
the first 1 in each row, then calculate count as n - index.
This gives O(m log n) time."
```

*Then Optimize to Staircase:*
```
"But we can do better! Starting from top-right corner:
- If we see 1, move left (more 1s possible)
- If we see 0, move down (this row exhausted)

This gives O(m + n) time - optimal!"
```

**3. Discuss Trade-offs**
```
"Naive O(m×n) is simple but ignores sorted property.
Binary search O(m log n) is good improvement.
Staircase O(m+n) is optimal and elegant."
```

### Common Follow-up Questions

**Q: "Return the row array, not just index?"**
```java
A: "return matrix[maxRow];"
```

**Q: "Count total 1s in the matrix?"**
```java
A: "Use staircase to find all 1s efficiently:
   int total = 0;
   int row = 0, col = n - 1;
   while (row < m && col >= 0) {
       if (matrix[row][col] == 1) {
           total += (col + 1);  // All to left are 1s
           row++;
       } else {
           col--;
       }
   }"
```

**Q: "What if rows aren't sorted?"**
```java
A: "Would need O(m×n) to count each row.
   No optimization possible without sorted property."
```

---

## 🔄 Related Problems

1. **Search in 2D Matrix** - Search sorted matrix
2. **Search in 2D Matrix II** - Rows and columns sorted
3. **Count Negative Numbers** - Similar staircase technique
4. **Lucky Numbers in Matrix** - Min in row, max in column
5. **Kth Smallest in Sorted Matrix** - Using sorted property

---

## 📚 Additional Learning

### Why Staircase Works

```
Starting from top-right:
- At any position, we've counted all 1s to the right
- If current = 1: potential for more 1s left
- If current = 0: no 1s can be left in this row

This eliminates one row or column per step!
Maximum steps = m + n - 1
```

### Alternative Starting Points

| Start | Move on 1 | Move on 0 | Result |
|-------|-----------|-----------|--------|
| Top-Right | ← Left | ↓ Down | Works ✓ |
| Bottom-Left | ↑ Up | → Right | Works ✓ |
| Top-Left | → or ↓ | Ambiguous | ❌ |
| Bottom-Right | ← or ↑ | Ambiguous | ❌ |

---

## 🔗 Resources

- [LeetCode 1337 - K Weakest Rows](https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix/)
- [Staircase Search Algorithm](https://www.geeksforgeeks.org/search-in-row-wise-and-column-wise-sorted-matrix/)
- [Binary Matrix Problems](https://leetcode.com/tag/matrix/)

---

<div align="center">

**Master the Staircase Algorithm! 🎯**

*An elegant O(m+n) solution for sorted matrices*

*Last Updated: December 2024*

</div>
