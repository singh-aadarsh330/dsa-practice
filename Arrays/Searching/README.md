# Searching Algorithms 🔍

**Category:** Arrays - Core Algorithms  
**Difficulty:** Basic to Medium  
**Topics:** Linear Search, Binary Search, Search Variations, Optimization

---

## 📋 Overview

Searching is one of the most fundamental operations in computer science. This section covers various search algorithms, their implementations, variations, and optimization techniques. Master these algorithms to solve a wide range of problems efficiently.

---

## 📁 Folder Structure

```
Searching/
├── Linear-Search/
├── Binary-Search/
└── README.md
```

---

## 🎯 Algorithm Categories

### 1. Linear Search
**Time:** O(n) | **Space:** O(1)
- Sequential search through array
- Works on unsorted arrays
- Simple and straightforward
- Best for small datasets

### 2. Binary Search
**Time:** O(log n) | **Space:** O(1) iterative, O(log n) recursive
- Divide and conquer approach
- Requires sorted array
- Extremely efficient for large datasets
- Multiple variations and applications

### 3. Specialized Search
- Jump Search
- Interpolation Search
- Exponential Search
- Ternary Search

---

## 📊 Comparison Table

| Algorithm | Time (Avg) | Time (Worst) | Space | Prerequisites | Use Case |
|-----------|-----------|--------------|-------|---------------|----------|
| Linear Search | O(n) | O(n) | O(1) | None | Small/Unsorted data |
| Binary Search | O(log n) | O(log n) | O(1) | Sorted array | Large sorted data |
| Jump Search | O(√n) | O(√n) | O(1) | Sorted array | When binary search overhead high |
| Interpolation | O(log log n) | O(n) | O(1) | Uniformly distributed | Uniformly distributed data |
| Exponential | O(log n) | O(log n) | O(1) | Sorted array | Unbounded/infinite arrays |

---

## 💡 When to Use Which Algorithm

### Use Linear Search When:
- ✅ Array is unsorted
- ✅ Small dataset (n < 100)
- ✅ Single search operation
- ✅ Data in linked list
- ✅ Implementation simplicity matters

### Use Binary Search When:
- ✅ Array is sorted
- ✅ Large dataset
- ✅ Multiple search operations
- ✅ Need O(log n) time
- ✅ Random access available

### Use Advanced Search When:
- ✅ Specific data distribution (Interpolation)
- ✅ Unbounded array (Exponential)
- ✅ Sorted but need better than O(n) for linear (Jump)
- ✅ Optimization problems (Ternary)

---

## 🔍 Common Search Problem Types

### Basic Search
1. Find element in array
2. First occurrence of element
3. Last occurrence of element
4. Count occurrences
5. Check if element exists

### Binary Search Variations
6. Find position in sorted array
7. Search in rotated sorted array
8. Find peak element
9. Find minimum in rotated array
10. Search in nearly sorted array

### Range Queries
11. Find first element ≥ x (Lower bound)
12. Find first element > x (Upper bound)
13. Find floor and ceiling
14. Count elements in range
15. Find kth smallest element

### 2D Search
16. Search in row-wise sorted matrix
17. Search in row-column sorted matrix
18. Find peak in 2D array
19. Search in special matrices

### Advanced Applications
20. Find transition point
21. Square root using binary search
22. Allocate minimum pages
23. Aggressive cows problem
24. Median of two sorted arrays

---

## 🎓 Key Concepts

### 1. Search Space
- Continuous vs Discrete
- Sorted vs Unsorted
- 1D vs 2D
- Bounded vs Unbounded

### 2. Invariants
- Maintaining sorted property
- Search space reduction
- Element guarantees

### 3. Termination Conditions
- Element found
- Search space exhausted
- Convergence criteria

### 4. Edge Cases
- Empty array
- Single element
- Element not present
- Duplicate elements
- All same elements

---

## 💻 Template Patterns

### Linear Search Template
```java
int linearSearch(int[] arr, int target) {
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] == target) {
            return i;
        }
    }
    return -1;
}
```

### Binary Search Template
```java
int binarySearch(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (arr[mid] == target) return mid;
        else if (arr[mid] < target) left = mid + 1;
        else right = mid - 1;
    }
    return -1;
}
```

### Lower Bound Template (First ≥ target)
```java
int lowerBound(int[] arr, int target) {
    int left = 0, right = arr.length;
    
    while (left < right) {
        int mid = left + (right - left) / 2;
        
        if (arr[mid] < target) left = mid + 1;
        else right = mid;
    }
    return left;
}
```

---

## 🚀 Optimization Techniques

### 1. Avoid Integer Overflow
```java
// Wrong: (left + right) / 2 may overflow
// Correct:
int mid = left + (right - left) / 2;
```

### 2. Choose Right Boundaries
```java
// Inclusive: while (left <= right)
// Exclusive: while (left < right)
```

### 3. Early Termination
```java
// Check bounds before binary search
if (target < arr[0] || target > arr[n-1]) return -1;
```

### 4. Cache-Friendly Search
- Use binary search for large arrays
- Consider block/jump search for cache optimization

---

## 📈 Problem Difficulty Distribution

```
Basic (Linear Search): 15-20 problems
Easy (Binary Search Basics): 20-25 problems
Medium (BS Variations): 15-20 problems
Hard (Advanced Applications): 5-8 problems
```

---

## 🎯 Learning Path

```
Linear Search Basics
    ↓
Binary Search Fundamentals
    ↓
Binary Search Variations
    ↓
Range Queries (Bounds)
    ↓
Rotated Array Problems
    ↓
2D Matrix Search
    ↓
Advanced Applications
    ↓
Optimization Problems
```

---

## 🏆 Must-Solve Problems

**Linear Search:**
1. Find element in array
2. Count occurrences
3. Find missing number
4. Search in unsorted array
5. First and last occurrence

**Binary Search:**
1. Classic binary search
2. First and last occurrence
3. Search in rotated array
4. Find peak element
5. Square root of number
6. Search in 2D matrix
7. Allocate minimum pages
8. Aggressive cows
9. Median of sorted arrays
10. Kth smallest element

---

## ⚠️ Common Pitfalls

1. ❌ **Integer overflow** in mid calculation
2. ❌ **Infinite loops** due to wrong boundary updates
3. ❌ **Off-by-one errors** in loop conditions
4. ❌ **Unsorted array** for binary search
5. ❌ **Wrong comparison** for duplicates
6. ❌ **Not handling edge cases** (empty, single element)

---

## 🔗 Related Topics

- [Array Traversal](../Traversal/)
- [Sorting Algorithms](../Sorting/)
- [Two Pointer](../Two-Pointer/)
- [Matrix Operations](../Matrix/)

---

## 📚 Additional Resources

- Time complexity analysis
- Space optimization techniques
- Interview problem patterns
- Real-world applications

---

## 📊 Progress Tracking

### Linear Search Module
- [ ] Basic search operations
- [ ] Count and find all occurrences
- [ ] Search with conditions
- [ ] Linear search optimizations

### Binary Search Module
- [ ] Classic binary search
- [ ] First/Last occurrence
- [ ] Lower/Upper bounds
- [ ] Rotated array search
- [ ] Peak finding
- [ ] 2D matrix search
- [ ] Advanced applications

---

## 🎯 Skills You'll Master

After completing this section:
- ✅ Implement search algorithms efficiently
- ✅ Choose right algorithm for problem
- ✅ Optimize search operations
- ✅ Handle edge cases correctly
- ✅ Analyze time/space complexity
- ✅ Solve interview problems confidently

---

**Note:** Navigate to specific folders for detailed implementations and problem solutions.

---

**Coming Soon!** 🚀

*Comprehensive solutions for 60+ searching problems will be added systematically.*

---

**Happy Searching! 🔍**
