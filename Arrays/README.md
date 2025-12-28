# 📊 Arrays

[![Difficulty](https://img.shields.io/badge/Difficulty-Easy%20to%20Medium-orange?style=flat-square)](.)
[![Problems](https://img.shields.io/badge/Problems-3-blue?style=flat-square)](.)
[![Language](https://img.shields.io/badge/Language-Java-red?style=flat-square)](.)

> Master array manipulation techniques and efficient searching algorithms. Arrays form the foundation of most data structure problems.

---

## 📋 Overview

Arrays are one of the most fundamental data structures used to store elements in a contiguous memory location. They allow fast access using indices and are widely used to solve a variety of algorithmic problems.

This section focuses on:
- Array traversal and manipulation techniques
- Searching and sorting within arrays
- Matrix-specific problems (2D arrays)
- Prefix sum techniques
- Subarrays and sliding window approaches
- Basic optimizations and edge case handling

---

## ✅ Topics Covered

- **Array traversal** - Efficient iteration techniques
- **Searching and sorting** - Finding elements and ordering data
- **Prefix sum techniques** - Cumulative sum for optimization
- **Subarrays and sliding window** - Continuous sequence problems
- **Basic optimizations and edge cases** - Handling corner cases efficiently

---

## 🧩 Complexity

### Time Complexity
- **Access:** O(1) - Direct index-based access
- **Search:** O(n) (linear), O(log n) (binary search on sorted arrays)
- **Insertion/Deletion:** O(n) - May require shifting elements
- **Traversal:** O(n) - Visit each element once

### Space Complexity
- **Static Array:** O(n) - Fixed size allocation
- **In-place Operations:** O(1) - No extra space needed

---

## 📂 Repository Structure

```
Arrays/
│
├── Matrix/                          # 2D Array Problems
│   ├── KthSmallestInSortedMatrix.java
│   └── SearchInSortedMatrix.java
│
├── KthMissingElement.java           # Missing element in sequence
│
└── README.md                        # This file
```

---

## 📝 Problems Solved

### Core Array Problems

| # | Problem | File | Difficulty | Key Concepts | Status |
|---|---------|------|------------|--------------|--------|
| 1 | Kth Missing Element | [KthMissingElement.java](./KthMissingElement.java) | Medium | Array traversal, Missing numbers | ✅ |

**Problem Description:**  
Find the kth missing positive number in a sequence.

**Key Learnings:**
- Identifying patterns in missing sequences
- Efficient iteration with minimal space
- Handling edge cases (empty array, k larger than missing count)

---

### Matrix Problems (2D Arrays)

| # | Problem | File | Difficulty | Key Concepts | Status |
|---|---------|------|------------|--------------|--------|
| 1 | Kth Smallest in Sorted Matrix | [Matrix/KthSmallestInSortedMatrix.java](./Matrix/KthSmallestInSortedMatrix.java) | Medium | Binary search, Min heap, Sorted matrix | ✅ |
| 2 | Search in Sorted Matrix | [Matrix/SearchInSortedMatrix.java](./Matrix/SearchInSortedMatrix.java) | Medium | Binary search, Matrix traversal | ✅ |

**Matrix Properties:**
- Row-wise sorted
- Column-wise sorted (in some problems)
- Efficient search strategies using sorted properties

**Key Approaches:**
- Binary search on matrix (treat as 1D array)
- Staircase search (start from top-right or bottom-left)
- Divide and conquer for optimization

---

## 🎯 Key Patterns & Techniques

### 1. **Array Traversal**
Efficient iteration through array elements.
```java
// Single pass traversal - O(n)
for (int i = 0; i < arr.length; i++) {
    // Process arr[i]
}
```

### 2. **Two Pointer Technique**
Using two pointers to optimize search and manipulation.
```java
// Used for sorted arrays
int left = 0, right = arr.length - 1;
while (left < right) {
    // Process and move pointers
}
```

### 3. **Prefix Sum**
Precompute cumulative sums for range queries.
```java
// Build prefix sum array
int[] prefix = new int[n];
prefix[0] = arr[0];
for (int i = 1; i < n; i++) {
    prefix[i] = prefix[i-1] + arr[i];
}
```

### 4. **Sliding Window**
Maintain a window of elements for subarray problems.
```java
// Fixed size window
int windowSum = 0;
for (int i = 0; i < k; i++) {
    windowSum += arr[i];
}
// Slide window
for (int i = k; i < n; i++) {
    windowSum += arr[i] - arr[i-k];
}
```

### 5. **Matrix Binary Search**
Search in row-wise and column-wise sorted matrices.
```java
// Staircase search from top-right
int row = 0, col = matrix[0].length - 1;
while (row < matrix.length && col >= 0) {
    if (matrix[row][col] == target) return true;
    else if (matrix[row][col] > target) col--;
    else row++;
}
```

---

## 💡 Common Pitfalls & Tips

### ⚠️ Common Mistakes
1. **Array Index Out of Bounds**
   - Always check `i < arr.length` before accessing `arr[i]`
   - Be careful with `i+1`, `i-1` indices

2. **Integer Overflow**
   - When calculating sum or product, check for overflow
   - Use `long` for large sums

3. **Not Handling Empty Arrays**
   - Always check `if (arr == null || arr.length == 0)`

4. **Off-by-One Errors**
   - Double-check loop boundaries
   - Verify inclusive vs exclusive ranges

### ✅ Best Practices
1. **In-place Modifications** - Save space when possible
2. **Early Termination** - Return/break as soon as answer is found
3. **Edge Case Testing** - Test with empty, single element, duplicate arrays
4. **Clear Variable Names** - Use descriptive names like `maxElement` instead of `x`

---

## 🚀 Optimization Strategies

### Space Optimization
- **In-place algorithms** - Modify array without extra space
- **Reuse variables** - Minimize auxiliary space usage

### Time Optimization
- **Binary search** - O(log n) instead of O(n) on sorted arrays
- **Hash maps** - O(1) lookup for frequency/presence checks
- **Prefix sums** - O(1) range sum queries after O(n) preprocessing

---

## 📚 Learning Resources

### Recommended Reading
- [GeeksforGeeks - Arrays](https://www.geeksforgeeks.org/array-data-structure/)
- [Striver's A2Z DSA Sheet - Arrays](https://takeuforward.org/data-structure/striver-a2z-dsa-course-sheet/)

### Practice Platforms
- [GeeksforGeeks Practice](https://practice.geeksforgeeks.org/explore?category%5B%5D=Arrays)
- [LeetCode Arrays](https://leetcode.com/tag/array/)

### Video Tutorials
- [TakeUForward - Arrays Playlist](https://www.youtube.com/@takeUforward)

---

## 🎓 Interview Preparation

### Must-Know Concepts
- ✅ Array traversal techniques
- ✅ Two pointer approach
- ✅ Sliding window
- ✅ Prefix sum
- ✅ Matrix traversal patterns
- ✅ Time-space complexity tradeoffs

### Common Interview Questions
1. Find missing/duplicate elements
2. Maximum subarray sum (Kadane's)
3. Search in sorted/rotated arrays
4. Merge intervals
5. Matrix problems (spiral, rotation, search)

---

## 📊 Progress Tracker

**Total Problems:** 3  
**Difficulty Distribution:**
- Easy: 0
- Medium: 3
- Hard: 0

**Topics Mastered:**
- [x] Array traversal and manipulation
- [x] Matrix searching techniques
- [x] Finding missing elements
- [ ] Subarray problems
- [ ] Sorting algorithms
- [ ] Advanced two-pointer techniques

---

## 🔄 Next Steps

**Upcoming Topics:**
1. Subarray sum problems
2. Array rotation techniques
3. Sorting-based problems
4. Advanced two-pointer problems
5. Hard-level matrix challenges

---

## 🤝 Contributing

Found a better approach or optimization? Feel free to suggest improvements!

---

## 📫 Questions or Feedback?

If you have questions about any solution or want to discuss approaches:
- Open an issue
- Connect on [LinkedIn](https://www.linkedin.com/in/singh-aadarsh330)
- Check my [GeeksforGeeks Profile](https://www.geeksforgeeks.org/profile/singhaadarsh330)

---

<div align="center">

**Happy Coding! 🚀**

*Last Updated: December 2024*

[⬆ Back to Main Repository](../README.md)

</div>
