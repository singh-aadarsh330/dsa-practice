# 🔢 Kth Smallest Element in Sorted Matrix

[![Difficulty](https://img.shields.io/badge/Difficulty-Medium-orange?style=flat-square)](.)
[![Concept](https://img.shields.io/badge/Concept-Binary%20Search%20%7C%20Heap-blue?style=flat-square)](.)

> Find the kth smallest element in a matrix where rows and columns are sorted. Master binary search on answer and min-heap techniques.

---

## 📋 Problem Description

### The Challenge

Given an `n × n` matrix where:
- Each **row** is sorted in ascending order (left to right)
- Each **column** is sorted in ascending order (top to bottom)

Find the **kth smallest** element in the matrix.

**Note:** It is the kth smallest element in sorted order, not the kth distinct element.

**Input:**
- `n × n` integer matrix (sorted rows and columns)
- Integer `k` where 1 ≤ k ≤ n²

**Output:**
- The kth smallest element

---

### Examples

**Example 1: Basic 3×3**
```
Input:
matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
]
k = 8

Sorted order: [1, 5, 9, 10, 11, 12, 13, 13, 15]
                                    ↑ 8th element

Output: 13
```

**Example 2: Small Matrix**
```
Input:
matrix = [
   [-5, -4],
   [-3, -2]
]
k = 2

Sorted order: [-5, -4, -3, -2]
                    ↑ 2nd element

Output: -4
```

**Example 3: First Element**
```
Input:
matrix = [
   [1, 3, 5],
   [6, 7, 12],
   [11, 14, 14]
]
k = 1

Output: 1
Reason: Smallest element (top-left)
```

**Example 4: Last Element**
```
Input:
matrix = [
   [1, 2],
   [1, 3]
]
k = 4

Sorted order: [1, 1, 2, 3]
                        ↑ 4th element

Output: 3
Reason: Largest element (bottom-right)
```

**Example 5: Duplicate Values**
```
Input:
matrix = [
   [1, 2],
   [1, 3]
]
k = 2

Sorted order: [1, 1, 2, 3]
                    ↑ 2nd element

Output: 1
Reason: Second occurrence of 1
```

---

## 🎯 Learning Objectives

This problem teaches:
- **Binary search on answer** - Search value range instead of indices
- **Min-heap technique** - Efficiently get smallest elements
- **Counting elements** - How many elements ≤ a value
- **Sorted matrix properties** - Leverage structure for efficiency
- **Space-time trade-offs** - Different approaches for different constraints

---

## 🔧 Approach 1: Min-Heap (Priority Queue)

### Algorithm

Use a min-heap to always extract the smallest unprocessed element.

**Key Idea:**
1. Start with top-left element (smallest)
2. Extract minimum from heap
3. Add right and below neighbors
4. Repeat k times

**Optimization:** Only add each element once using visited tracking.

### Implementation

```java
public int kthSmallest(int[][] matrix, int k) {
    int n = matrix.length;
    
    // Min-heap: [value, row, col]
    PriorityQueue<int[]> minHeap = new PriorityQueue<>(
        (a, b) -> Integer.compare(a[0], b[0])
    );
    
    // Add first element of each row (optimized)
    for (int row = 0; row < Math.min(n, k); row++) {
        minHeap.offer(new int[]{matrix[row][0], row, 0});
    }
    
    int result = 0;
    
    // Extract k times
    for (int i = 0; i < k; i++) {
        int[] current = minHeap.poll();
        result = current[0];
        int row = current[1];
        int col = current[2];
        
        // Add next element in same row (if exists)
        if (col + 1 < n) {
            minHeap.offer(new int[]{matrix[row][col + 1], row, col + 1});
        }
    }
    
    return result;
}
```

**Complexity:**
- **Time:** O(k × log(min(n, k)))
  - Initialize heap: O(min(n, k))
  - k extractions: O(k × log(heap_size))
  - Heap size ≤ min(n, k)
- **Space:** O(min(n, k)) for heap

**Pros:**
- Works for non-square matrices
- Intuitive approach
- Good when k is small

**Cons:**
- Not optimal for large k
- Requires extra space
- Can't leverage full matrix structure

---

## 🔧 Approach 2: Binary Search on Answer ⭐

### Algorithm

Instead of searching indices, search the **value range**.

**Key Insight:**
- Values range from matrix[0][0] to matrix[n-1][n-1]
- For any value mid, we can count how many elements ≤ mid
- If count < k, answer is larger
- If count ≥ k, answer could be mid or smaller

**Steps:**
1. Binary search on value range
2. For each mid, count elements ≤ mid
3. Adjust search range based on count
4. Return when range converges

### Implementation

```java
public int kthSmallest(int[][] matrix, int k) {
    int n = matrix.length;
    int left = matrix[0][0];
    int right = matrix[n - 1][n - 1];
    
    while (left < right) {
        int mid = left + (right - left) / 2;
        int count = countLessEqual(matrix, mid);
        
        if (count < k) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    
    return left;
}

// Count elements <= target using sorted property
private int countLessEqual(int[][] matrix, int target) {
    int n = matrix.length;
    int count = 0;
    
    // Start from bottom-left corner
    int row = n - 1;
    int col = 0;
    
    while (row >= 0 && col < n) {
        if (matrix[row][col] <= target) {
            // All elements in this column up to this row are <= target
            count += row + 1;
            col++;  // Move right
        } else {
            row--;  // Move up
        }
    }
    
    return count;
}
```

**Complexity:**
- **Time:** O(n × log(max - min))
  - Binary search iterations: O(log(max - min))
  - Each count operation: O(n)
  - Total: O(n × log(range))
- **Space:** O(1)

**Pros:**
- ⭐ Optimal for large matrices
- O(1) space complexity
- Leverages sorted structure
- Works well for large k

**Cons:**
- More complex logic
- Requires understanding binary search on answer

---

## 🔧 Approach 3: Merge K Sorted Lists

### Algorithm

Treat each row as a sorted list and merge them.

### Implementation

```java
public int kthSmallest(int[][] matrix, int k) {
    int n = matrix.length;
    
    // Min-heap with row pointers
    PriorityQueue<int[]> pq = new PriorityQueue<>(
        (a, b) -> matrix[a[0]][a[1]] - matrix[b[0]][b[1]]
    );
    
    // Add first element of each row
    for (int i = 0; i < n; i++) {
        pq.offer(new int[]{i, 0});
    }
    
    int result = 0;
    for (int i = 0; i < k; i++) {
        int[] pos = pq.poll();
        int row = pos[0];
        int col = pos[1];
        result = matrix[row][col];
        
        if (col + 1 < n) {
            pq.offer(new int[]{row, col + 1});
        }
    }
    
    return result;
}
```

**Complexity:**
- **Time:** O(k × log(n))
- **Space:** O(n)

---

## 📊 Comparison of Approaches

| Approach | Time | Space | Best For | Difficulty |
|----------|------|-------|----------|------------|
| **Min-Heap** | O(k log k) | O(k) | Small k | ⭐⭐⭐ |
| **Binary Search** | O(n log(range)) | O(1) | **All cases** | ⭐⭐⭐⭐ |
| **Merge K Lists** | O(k log n) | O(n) | Moderate k | ⭐⭐⭐ |

**Recommendation:** Use **Binary Search on Answer** for interviews - optimal and impressive.

---

## 🎯 Step-by-Step Dry Run

### Input:
```java
int[][] matrix = {
    {1,  5,  9},
    {10, 11, 13},
    {12, 13, 15}
};
int k = 8;
```

### Execution (Binary Search Approach):

**Initialization:**
```
left = matrix[0][0] = 1
right = matrix[2][2] = 15
k = 8
```

**Iteration 1:**
```
mid = (1 + 15) / 2 = 8
countLessEqual(8):
  - Start at [2, 0] = 12 > 8, move up
  - At [1, 0] = 10 > 8, move up
  - At [0, 0] = 1 ≤ 8, count += 1, move right
  - At [0, 1] = 5 ≤ 8, count += 1, move right
  - At [0, 2] = 9 > 8, move up
  - Out of bounds
  - count = 2

count (2) < k (8) → left = mid + 1 = 9
```

**Iteration 2:**
```
left = 9, right = 15
mid = (9 + 15) / 2 = 12

countLessEqual(12):
  - Start at [2, 0] = 12 ≤ 12, count += 3, move right
  - At [2, 1] = 13 > 12, move up
  - At [1, 1] = 11 ≤ 12, count += 2, move right
  - At [1, 2] = 13 > 12, move up
  - At [0, 2] = 9 ≤ 12, count += 1, move right
  - Out of bounds
  - count = 6

count (6) < k (8) → left = mid + 1 = 13
```

**Iteration 3:**
```
left = 13, right = 15
mid = (13 + 15) / 2 = 14

countLessEqual(14):
  - count = 8 (includes both 13s)

count (8) ≥ k (8) → right = mid = 14
```

**Iteration 4:**
```
left = 13, right = 14
mid = (13 + 14) / 2 = 13

countLessEqual(13):
  - count = 8

count (8) ≥ k (8) → right = mid = 13
```

**Convergence:**
```
left = 13, right = 13
Loop exits

Result: 13
```

---

## ⚠️ Edge Cases & Validation

### Edge Cases to Consider

**1. k = 1 (First Element)**
```java
Input: [[1,2],[3,4]], k=1
Output: 1
Top-left corner
```

**2. k = n² (Last Element)**
```java
Input: [[1,2],[3,4]], k=4
Output: 4
Bottom-right corner
```

**3. All Same Elements**
```java
Input: [[5,5],[5,5]], k=2
Output: 5
All elements equal
```

**4. Single Element Matrix**
```java
Input: [[42]], k=1
Output: 42
Only one element
```

**5. Negative Numbers**
```java
Input: [[-5,-4],[-3,-2]], k=2
Output: -4
Handle negative values
```

**6. Large Range**
```java
Input: [[1,2],[1000000,1000001]], k=3
Binary search handles well
```

### Input Validation

```java
public int kthSmallest(int[][] matrix, int k) {
    if (matrix == null || matrix.length == 0) {
        throw new IllegalArgumentException("Matrix cannot be null or empty");
    }
    
    int n = matrix.length;
    if (matrix[0].length != n) {
        throw new IllegalArgumentException("Matrix must be square");
    }
    
    if (k < 1 || k > n * n) {
        throw new IllegalArgumentException("k must be between 1 and n²");
    }
    
    // Main logic...
}
```

---

## 🧪 Test Cases

### Test Case 1: Basic Example
```java
Input: [[1,5,9],[10,11,13],[12,13,15]], k=8
Expected: 13
```

### Test Case 2: First Element
```java
Input: [[1,2],[3,4]], k=1
Expected: 1
```

### Test Case 3: Last Element
```java
Input: [[1,2],[3,4]], k=4
Expected: 4
```

### Test Case 4: Middle Element
```java
Input: [[-5,-4],[-3,-2]], k=2
Expected: -4
```

### Test Case 5: Duplicates
```java
Input: [[1,2],[1,3]], k=2
Expected: 1
```

### Test Case 6: Large Matrix
```java
Input: 100×100 matrix, k=5000
Test performance
```

---

## 💡 Key Concepts Illustrated

### 1. Binary Search on Answer Pattern

```java
// Instead of searching indices
int left = minValue;
int right = maxValue;

while (left < right) {
    int mid = left + (right - left) / 2;
    if (condition(mid)) {
        right = mid;
    } else {
        left = mid + 1;
    }
}
```

### 2. Counting Elements in Sorted Matrix

```java
// From bottom-left or top-right
int row = n - 1, col = 0;
while (row >= 0 && col < n) {
    if (matrix[row][col] <= target) {
        count += row + 1;  // All above in column
        col++;
    } else {
        row--;
    }
}
```

### 3. Min-Heap with Coordinates

```java
PriorityQueue<int[]> pq = new PriorityQueue<>(
    (a, b) -> Integer.compare(a[0], b[0])  // Compare values
);
pq.offer(new int[]{value, row, col});
```

---

## 🎓 Interview Discussion Points

### When to Use Each Approach

**Min-Heap:**
- k is very small (k << n²)
- Need actual elements in order
- Matrix is not square

**Binary Search:**
- ⭐ Default choice for interviews
- Optimal space complexity
- k can be large
- Want to impress interviewer

### Common Follow-ups

**Q: "What if matrix is not square (m × n)?"**
```
A: Min-heap approach still works.
   Binary search needs adjustment for counting.
```

**Q: "Find kth largest instead?"**
```
A: Binary search from right to left,
   count elements ≥ mid instead.
```

**Q: "What if we need top k elements?"**
```
A: Use min-heap, extract k times.
```

---

## 🔄 Related Problems

1. **Search in 2D Matrix II** - Search specific value
2. **Kth Smallest Element in BST** - Tree version
3. **Find K Pairs with Smallest Sums** - Similar heap technique
4. **Merge K Sorted Lists** - Same merge concept

---

<div align="center">

**Master Binary Search on Answer! 🔢**

*An advanced technique for optimization problems*

*Last Updated: December 2024*

</div>
