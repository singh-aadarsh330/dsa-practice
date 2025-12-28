# 🔍 Searching

[![Difficulty](https://img.shields.io/badge/Difficulty-Medium%20to%20Hard-red?style=flat-square)](.)
[![Problems](https://img.shields.io/badge/Problems-6-blue?style=flat-square)](.)
[![Language](https://img.shields.io/badge/Language-Java-red?style=flat-square)](.)

> Master efficient searching algorithms including Binary Search and its variations. Search algorithms are crucial for solving optimization problems and are frequently asked in technical interviews.

---

## 📋 Overview

Searching algorithms are fundamental techniques used to find elements in data structures. This section covers various searching strategies with a primary focus on **Binary Search** and its applications.

**Why Searching Matters:**
- Reduces time complexity from O(n) to O(log n)
- Essential for optimization problems
- Frequently appears in coding interviews
- Foundation for advanced algorithms

---

## ✅ Topics Covered

This repository is organized into specialized subcategories:

### 1. **Binary Search** 
Core binary search implementations on sorted data structures.

### 2. **Binary Search on Answer**
Advanced technique where binary search is applied to find optimal solutions in a range.

### 3. **Matrix Search**
Efficient searching in 2D arrays with various sorting properties.

### 4. **Range Search**
Finding elements or counting occurrences within specified ranges.

---

## 🧩 Key Characteristics

### Binary Search Fundamentals
- **Works only on sorted data** - Requires pre-sorted input
- **Divide and conquer approach** - Reduces search space by half each iteration
- **Logarithmic time complexity** - O(log n) efficiency
- **Iterative or Recursive** - Can be implemented both ways

### Complexity Analysis
- **Time Complexity:** O(log n) - Exponentially faster than linear search
- **Space Complexity:** 
  - O(1) for iterative implementation
  - O(log n) for recursive (call stack overhead)

---

## 📂 Repository Structure

```
Searching/
│
├── Binary Search/                   # Core Binary Search Problems
│   ├── SearchInStrictlySortedMatrix.java
│   └── README.md
│
├── Binary Search on Answer/         # BS on Answer Space
│   ├── MinimumTimeToMakeDonuts.java
│   └── README.md
│
├── Matrix Search/                   # 2D Array Search Problems
│   ├── FindPeakElementInGrid.java
│   ├── RowWithMaxOnes.java
│   └── README.md
│
├── Range Search/                    # Range-based Queries
│   ├── CountElementsInRange.java
│   └── README.md
│
└── README.md                        # This file
```

---

## 📝 Problems Solved

### 1️⃣ Binary Search (1 problem)

| # | Problem | File | Difficulty | Key Concepts | Status |
|---|---------|------|------------|--------------|--------|
| 1 | Search in Strictly Sorted Matrix | [SearchInStrictlySortedMatrix.java](./Binary%20Search/SearchInStrictlySortedMatrix.java) | Medium | Binary search, Matrix flattening | ✅ |

**Approach:**  
Treat the sorted matrix as a flattened 1D array and apply standard binary search.

**Key Learning:**  
- Converting 2D indices to 1D and vice versa
- Utilizing complete sorting of matrix (row-wise and column-wise)
- Time: O(log(m*n)), Space: O(1)

**Related README:** [Binary Search README](./Binary%20Search/README.md)

---

### 2️⃣ Binary Search on Answer (1 problem)

| # | Problem | File | Difficulty | Key Concepts | Status |
|---|---------|------|------------|--------------|--------|
| 1 | Minimum Time to Make Donuts | [MinimumTimeToMakeDonuts.java](./Binary%20Search%20on%20Answer/MinimumTimeToMakeDonuts.java) | Hard | BS on answer, Optimization | ✅ |

**Approach:**  
Binary search on the answer space to find minimum time required.

**Key Learning:**  
- When to apply binary search on answer space
- Defining search range (low to high)
- Writing validation function `isPossible(mid)`
- Minimization vs maximization problems

**Pattern:**
```java
int low = minPossible, high = maxPossible;
int answer = high;

while (low <= high) {
    int mid = low + (high - low) / 2;
    
    if (isPossible(mid)) {
        answer = mid;           // Found valid solution
        high = mid - 1;         // Try to minimize further
    } else {
        low = mid + 1;          // Need more time
    }
}
```

**Related README:** [Binary Search on Answer README](./Binary%20Search%20on%20Answer/README.md)

---

### 3️⃣ Matrix Search (2 problems)

| # | Problem | File | Difficulty | Key Concepts | Status |
|---|---------|------|------------|--------------|--------|
| 1 | Find Peak Element in Grid | [FindPeakElementInGrid.java](./Matrix%20Search/FindPeakElementInGrid.java) | Medium | Binary search, Peak finding | ✅ |
| 2 | Row with Maximum Ones | [RowWithMaxOnes.java](./Matrix%20Search/RowWithMaxOnes.java) | Easy | Binary search, Optimization | ✅ |

**Common Techniques:**
- **Staircase Search** - Start from corners (top-right or bottom-left)
- **Binary Search per Row** - Apply BS on each row
- **Column Binary Search** - Search on specific columns

**Matrix Search Patterns:**

**Pattern 1: Row-wise sorted matrix**
```java
// Binary search on each row
for (int i = 0; i < rows; i++) {
    int idx = binarySearch(matrix[i], target);
    if (idx != -1) return new int[]{i, idx};
}
```

**Pattern 2: Staircase search (sorted rows & columns)**
```java
// Start from top-right
int row = 0, col = matrix[0].length - 1;
while (row < matrix.length && col >= 0) {
    if (matrix[row][col] == target) return true;
    else if (matrix[row][col] > target) col--;
    else row++;
}
```

**Related README:** [Matrix Search README](./Matrix%20Search/README.md)

---

### 4️⃣ Range Search (1 problem)

| # | Problem | File | Difficulty | Key Concepts | Status |
|---|---------|------|------------|--------------|--------|
| 1 | Count Elements in Range | [CountElementsInRange.java](./Range%20Search/CountElementsInRange.java) | Easy | Binary search, Bounds | ✅ |

**Approach:**  
Use binary search to find lower and upper bounds, then calculate count.

**Key Concepts:**
- **Lower Bound:** First element ≥ target
- **Upper Bound:** First element > target
- **Count in Range:** `upperBound(high) - lowerBound(low)`

**Template:**
```java
// Lower Bound - first element >= target
int lowerBound(int[] arr, int target) {
    int low = 0, high = arr.length;
    while (low < high) {
        int mid = low + (high - low) / 2;
        if (arr[mid] < target) low = mid + 1;
        else high = mid;
    }
    return low;
}

// Upper Bound - first element > target
int upperBound(int[] arr, int target) {
    int low = 0, high = arr.length;
    while (low < high) {
        int mid = low + (high - low) / 2;
        if (arr[mid] <= target) low = mid + 1;
        else high = mid;
    }
    return low;
}
```

**Related README:** [Range Search README](./Range%20Search/README.md)

---

## 🎯 Key Patterns Covered

### Pattern 1: Standard Binary Search
**When to use:** Searching in sorted array  
**Template:**
```java
int binarySearch(int[] arr, int target) {
    int low = 0, high = arr.length - 1;
    
    while (low <= high) {
        int mid = low + (high - low) / 2;
        
        if (arr[mid] == target) return mid;
        else if (arr[mid] < target) low = mid + 1;
        else high = mid - 1;
    }
    
    return -1; // Not found
}
```

### Pattern 2: Lower/Upper Bound
**When to use:** Finding first/last occurrence, range queries  
**Applications:** Count occurrences, range search, insertion position

### Pattern 3: Binary Search on Answer
**When to use:** Optimization problems with monotonic properties  
**Applications:** Minimize/maximize time, cost, distance problems

### Pattern 4: Binary Search in Matrix
**When to use:** Searching in 2D sorted arrays  
**Techniques:** Flatten to 1D, staircase search, row/column BS

---

## 💡 Common Mistakes & Tips

### ⚠️ Avoid These Mistakes

1. **Integer Overflow in Mid Calculation**
   ```java
   ❌ int mid = (low + high) / 2;        // Can overflow
   ✅ int mid = low + (high - low) / 2;  // Safe
   ```

2. **Infinite Loops**
   ```java
   ❌ while (low < high) { ... }          // Can cause infinite loop
   ✅ while (low <= high) { ... }         // Correct for standard BS
   ```

3. **Not Updating Low/High Correctly**
   ```java
   ❌ low = mid;                          // Can cause infinite loop
   ✅ low = mid + 1;                      // Correct update
   ```

4. **Using BS on Unsorted Data**
   - Binary search ONLY works on sorted data
   - Sort first if needed (adds O(n log n) complexity)

### ✅ Best Practices

1. **Always check for sorted data** before applying binary search
2. **Use overflow-safe mid calculation**
3. **Handle edge cases:** empty array, single element, all duplicates
4. **Choose correct loop condition:** `<=` vs `<` based on problem
5. **Update indices properly:** Always `+1` or `-1`, never just assign `mid`

---

## 🧠 Key Learnings

### When to Use Binary Search?
- ✅ Array/list is sorted
- ✅ Need O(log n) search time
- ✅ Problem has monotonic properties
- ✅ Can define clear search space

### When to Use BS on Answer?
- ✅ Optimization problem (minimize/maximize)
- ✅ Answer lies in a continuous range
- ✅ Can write a validation function
- ✅ Monotonic validation (if x works, all values > x work)

---

## 📚 Learning Resources

### Recommended Reading
- [GeeksforGeeks - Binary Search](https://www.geeksforgeeks.org/binary-search/)
- [Striver's Binary Search Playlist](https://takeuforward.org/data-structure/striver-a2z-dsa-course-sheet/)

### Video Tutorials
- [TakeUForward - Binary Search Complete](https://www.youtube.com/@takeUforward)
- [Binary Search on Answer Explanation](https://www.youtube.com/results?search_query=binary+search+on+answer)

### Practice Platforms
- [GeeksforGeeks Practice](https://practice.geeksforgeeks.org/explore?category%5B%5D=Binary%20Search)
- [LeetCode Binary Search](https://leetcode.com/tag/binary-search/)

---

## 🎓 Interview Preparation

### Must-Know Concepts
- [x] Standard binary search implementation
- [x] Lower bound and upper bound
- [x] Binary search on answer space
- [x] Search in rotated sorted array
- [x] Search in 2D matrix
- [ ] Advanced: Binary search on range minimum/maximum

### Common Interview Questions
1. **Easy:**
   - Binary search in sorted array
   - First and last occurrence
   - Search insert position

2. **Medium:**
   - Search in rotated sorted array
   - Find peak element
   - Search in 2D matrix
   - Kth smallest element

3. **Hard:**
   - Median of two sorted arrays
   - Aggressive cows problem
   - Book allocation problem
   - Minimum time to complete tasks

---

## 📊 Progress Tracker

**Total Problems:** 6  
**Subcategories:** 4

**Difficulty Distribution:**
- Easy: 1
- Medium: 4
- Hard: 1

**Concepts Mastered:**
- [x] Standard binary search
- [x] Binary search on sorted matrix
- [x] Binary search on answer space
- [x] Range search with bounds
- [x] Peak finding in matrix
- [ ] Search in rotated arrays
- [ ] Advanced optimization problems

---

## 🔄 Next Steps

**Upcoming Topics:**
1. Search in rotated sorted arrays
2. More binary search on answer problems
3. Advanced matrix search techniques
4. Binary search with duplicates
5. Multi-dimensional binary search

---

## 🤝 Contributing

Found an optimization or better approach? Suggestions are welcome!

---

## 📫 Questions or Feedback?

Connect with me:
- [LinkedIn](https://www.linkedin.com/in/singh-aadarsh330)
- [GeeksforGeeks Profile](https://www.geeksforgeeks.org/profile/singhaadarsh330)

---

<div align="center">

**Master Searching, Master Optimization! 🚀**

*Last Updated: December 2024*

[⬆ Back to Main Repository](../README.md)

</div>
