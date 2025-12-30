# Subarrays 📐

**Category:** Arrays - Core Concept  
**Difficulty:** Easy to Hard  
**Topics:** Contiguous Elements, Kadane's Algorithm, Sum Problems

---

## 📋 Overview

A subarray is a contiguous sequence of elements within an array. Subarray problems are among the most common in coding interviews and competitive programming.

---

## 🎯 Key Concepts

### Definitions
```
Array: [1, 2, 3, 4]

Subarrays (contiguous):
[1], [2], [3], [4]
[1,2], [2,3], [3,4]
[1,2,3], [2,3,4]
[1,2,3,4]

Total: n(n+1)/2 = 10 subarrays

Subsequence (not necessarily contiguous):
[1], [2], [1,3], [1,4], [2,4], etc.
Total: 2^n subsequences
```

### Important Formulas
```
Number of subarrays: n(n+1)/2
Length L subarrays: n - L + 1
```

---

## 💡 Common Patterns

### Pattern 1: Brute Force (O(n²) or O(n³))
```java
// Generate all subarrays
for (int i = 0; i < n; i++) {
    for (int j = i; j < n; j++) {
        // Process subarray arr[i...j]
        for (int k = i; k <= j; k++) {
            // Access arr[k]
        }
    }
}
```

### Pattern 2: Kadane's Algorithm (Maximum Subarray)
```java
int maxSubarraySum(int[] arr) {
    int maxSum = arr[0];
    int currentSum = arr[0];
    
    for (int i = 1; i < arr.length; i++) {
        currentSum = Math.max(arr[i], currentSum + arr[i]);
        maxSum = Math.max(maxSum, currentSum);
    }
    
    return maxSum;
}
```

### Pattern 3: Prefix Sum (Range Queries)
```java
int[] prefixSum = new int[n + 1];
for (int i = 0; i < n; i++) {
    prefixSum[i + 1] = prefixSum[i] + arr[i];
}

// Sum of subarray [i, j]
int sum = prefixSum[j + 1] - prefixSum[i];
```

### Pattern 4: HashMap (Count Subarrays)
```java
int countSubarraysWithSum(int[] arr, int k) {
    HashMap<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);
    
    int sum = 0, count = 0;
    for (int num : arr) {
        sum += num;
        count += map.getOrDefault(sum - k, 0);
        map.put(sum, map.getOrDefault(sum, 0) + 1);
    }
    
    return count;
}
```

---

## 📁 Folder Structure

```
Subarrays/
├── Max-Subarray/
├── Zero-Sum/
└── README.md
```

---

## 🔍 Problem Categories

### Maximum Subarray Problems
1. Maximum sum subarray (Kadane's)
2. Maximum product subarray
3. Longest subarray with sum K
4. Maximum length subarray with equal 0s and 1s

### Sum-Based Problems
5. Subarray with given sum
6. Count subarrays with sum K
7. Subarray sums divisible by K
8. Continuous subarray sum

### Zero Sum Problems
9. Zero sum subarray exists
10. Largest subarray with 0 sum
11. Count zero sum subarrays
12. Equal 0s and 1s subarray

### Advanced Problems
13. Minimum subarray sum
14. Maximum absolute difference
15. Count subarrays with bounded maximum
16. Subarray with elements greater than X

---

## 📊 Complexity Patterns

| Approach | Time | Space | Use Case |
|----------|------|-------|----------|
| Brute Force | O(n³) | O(1) | Small arrays |
| Optimized BF | O(n²) | O(1) | Medium arrays |
| Prefix Sum | O(n) | O(n) | Multiple queries |
| Kadane's | O(n) | O(1) | Max/min subarray |
| HashMap | O(n) | O(n) | Count/existence |

---

# Matrix (2D Arrays) 🔲

**Category:** Arrays - 2D Structures  
**Difficulty:** Easy to Hard  
**Topics:** Traversal, Search, Rotation, Transformations

---

## 📋 Overview

Matrix problems involve 2D arrays and require understanding of row-column indexing, traversal patterns, and spatial transformations.

---

## 📁 Folder Structure

```
Matrix/
├── Matrix-Traversal/
├── Search-in-2D/
├── Rotate-Matrix/
└── README.md
```

---

## 💡 Common Patterns

### Basic Access
```java
int[][] matrix = new int[m][n]; // m rows, n columns
int element = matrix[i][j]; // Access
```

### Pattern 1: Row-wise Traversal
```java
for (int i = 0; i < m; i++) {
    for (int j = 0; j < n; j++) {
        // Process matrix[i][j]
    }
}
```

### Pattern 2: Column-wise Traversal
```java
for (int j = 0; j < n; j++) {
    for (int i = 0; i < m; i++) {
        // Process matrix[i][j]
    }
}
```

### Pattern 3: Diagonal Traversal
```java
// Main diagonal
for (int i = 0; i < Math.min(m, n); i++) {
    // matrix[i][i]
}

// Anti-diagonal
for (int i = 0; i < Math.min(m, n); i++) {
    // matrix[i][n - 1 - i]
}
```

### Pattern 4: Spiral Traversal
```java
void spiralTraversal(int[][] matrix) {
    int top = 0, bottom = m - 1;
    int left = 0, right = n - 1;
    
    while (top <= bottom && left <= right) {
        // Right
        for (int j = left; j <= right; j++)
            result.add(matrix[top][j]);
        top++;
        
        // Down
        for (int i = top; i <= bottom; i++)
            result.add(matrix[i][right]);
        right--;
        
        // Left
        if (top <= bottom) {
            for (int j = right; j >= left; j--)
                result.add(matrix[bottom][j]);
            bottom--;
        }
        
        // Up
        if (left <= right) {
            for (int i = bottom; i >= top; i--)
                result.add(matrix[i][left]);
            left++;
        }
    }
}
```

---

## 🔍 Problem Categories

### Traversal Problems
1. Spiral order traversal
2. Zigzag traversal
3. Diagonal traversal
4. Wave traversal
5. Boundary traversal

### Search Problems
6. Search in row-wise sorted
7. Search in row-column sorted
8. Find peak element
9. Median in row-wise sorted

### Rotation & Transformation
10. Rotate matrix 90° clockwise
11. Rotate matrix 90° counter-clockwise
12. Transpose matrix
13. Flip matrix horizontally/vertically

### Path Problems
14. Unique paths in grid
15. Minimum path sum
16. Maximum path sum
17. Count paths with obstacles

### Submatrix Problems
18. Maximum sum rectangle
19. Submatrix sum queries (2D prefix sum)
20. Count square submatrices
21. Largest rectangle of 1s

---

## 📊 Common Techniques

### 1. In-Place Rotation (90° Clockwise)
```java
void rotate90(int[][] matrix) {
    int n = matrix.length;
    
    // Transpose
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            int temp = matrix[i][j];
            matrix[i][j] = matrix[j][i];
            matrix[j][i] = temp;
        }
    }
    
    // Reverse each row
    for (int i = 0; i < n; i++) {
        reverse(matrix[i]);
    }
}
```

### 2. Binary Search in Sorted Matrix
```java
boolean searchMatrix(int[][] matrix, int target) {
    int m = matrix.length, n = matrix[0].length;
    int left = 0, right = m * n - 1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        int val = matrix[mid / n][mid % n];
        
        if (val == target) return true;
        else if (val < target) left = mid + 1;
        else right = mid - 1;
    }
    
    return false;
}
```

### 3. Set Matrix Zeros
```java
void setZeroes(int[][] matrix) {
    boolean firstRowZero = false;
    boolean firstColZero = false;
    
    // Check first row and column
    for (int j = 0; j < n; j++)
        if (matrix[0][j] == 0) firstRowZero = true;
    
    for (int i = 0; i < m; i++)
        if (matrix[i][0] == 0) firstColZero = true;
    
    // Use first row/col as markers
    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            if (matrix[i][j] == 0) {
                matrix[i][0] = 0;
                matrix[0][j] = 0;
            }
        }
    }
    
    // Set zeros based on markers
    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                matrix[i][j] = 0;
            }
        }
    }
    
    // Handle first row and column
    if (firstRowZero)
        for (int j = 0; j < n; j++) matrix[0][j] = 0;
    
    if (firstColZero)
        for (int i = 0; i < m; i++) matrix[i][0] = 0;
}
```

---

## 🎯 Key Insights

### Index Management
```
matrix[i][j]:
- i = row index (0 to m-1)
- j = column index (0 to n-1)

Convert 1D to 2D: matrix[k / n][k % n]
Convert 2D to 1D: k = i * n + j
```

### Boundary Handling
```
Always check:
- i >= 0 && i < m
- j >= 0 && j < n
```

### Direction Arrays
```java
int[][] directions = {{-1,0}, {1,0}, {0,-1}, {0,1}}; // Up, Down, Left, Right
int[][] diag = {{-1,-1}, {-1,1}, {1,-1}, {1,1}}; // Diagonals
```

---

## 📈 Expected Problems

**Subarrays:** 30-35 problems
- Max Subarray: 8-10
- Zero Sum: 8-10
- Others: 12-15

**Matrix:** 25-30 problems
- Traversal: 8-10
- Search: 6-8
- Rotation: 5-6
- Others: 6-8

---

**Note:** Comprehensive solutions coming soon!

---

**Happy Coding! 📐🔲**
