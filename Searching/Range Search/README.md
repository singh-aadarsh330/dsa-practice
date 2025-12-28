# 📏 Range Search

[![Problems](https://img.shields.io/badge/Problems-1-blue?style=flat-square)](.)
[![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green?style=flat-square)](.)

> Efficiently find elements, count occurrences, and perform queries within specified ranges using binary search techniques.

---

## 📋 Overview

**Range Search** problems involve finding elements or performing operations within a specific range [low, high]. These problems commonly use:
- Lower bound and upper bound concepts
- Binary search for efficient querying
- Prefix sums for range queries
- Counting and frequency problems

**Key Concepts:**
- **Lower Bound:** First element ≥ target
- **Upper Bound:** First element > target
- **Count in Range:** upper_bound(high) - lower_bound(low)
- **Range Queries:** Finding elements between two values

---

## 📝 Problem in This Folder

| # | Problem | File | Difficulty | Technique | Time | Space | Status |
|---|---------|------|------------|-----------|------|-------|--------|
| 1 | Count Elements in Range | [CountElementsInRange.java](./CountElementsInRange.java) | Easy | Binary Search Bounds | O(log n) | O(1) | ✅ |

---

## 🎯 Problem: Count Elements in Range

### Problem Description

Given a **sorted array** and a range [low, high], count how many elements fall within this range (inclusive).

**Input:**
- `arr[]` - Sorted array of integers
- `low` - Start of range (inclusive)
- `high` - End of range (inclusive)

**Output:** Count of elements where `low ≤ element ≤ high`

**Example:**
```
Array: [1, 3, 3, 5, 7, 9, 9, 10, 15]
Range: [3, 10]

Elements in range: 3, 3, 5, 7, 9, 9, 10
Count: 7
```

---

### Approach: Lower Bound + Upper Bound

**Key Insight:**
- Find first element ≥ low (lower bound of low)
- Find first element > high (lower bound of high + 1)
- Count = upper_index - lower_index

**Why This Works:**
```
Array:     [1, 3, 3, 5, 7, 9, 9, 10, 15]
Indices:    0  1  2  3  4  5  6   7   8

Range: [3, 10]

lower_bound(3) = index 1 (first element ≥ 3)
upper_bound(10) = index 8 (first element > 10)

Count = 8 - 1 = 7 elements ✓
```

---

### Implementation

```java
public int countInRange(int[] arr, int low, int high) {
    // Find first element >= low
    int leftIndex = lowerBound(arr, low);
    
    // Find first element > high
    int rightIndex = lowerBound(arr, high + 1);
    
    // Count of elements in range
    return rightIndex - leftIndex;
}

// Lower Bound: First element >= target
private int lowerBound(int[] arr, int target) {
    int left = 0;
    int right = arr.length;
    
    while (left < right) {
        int mid = left + (right - left) / 2;
        
        if (arr[mid] < target) {
            left = mid + 1;     // Go right
        } else {
            right = mid;        // Could be answer, look left
        }
    }
    
    return left;
}
```

---

### Alternative: Using Upper Bound

```java
public int countInRange(int[] arr, int low, int high) {
    // First element >= low
    int leftIndex = lowerBound(arr, low);
    
    // First element > high  
    int rightIndex = upperBound(arr, high);
    
    return rightIndex - leftIndex;
}

// Upper Bound: First element > target
private int upperBound(int[] arr, int target) {
    int left = 0;
    int right = arr.length;
    
    while (left < right) {
        int mid = left + (right - left) / 2;
        
        if (arr[mid] <= target) {
            left = mid + 1;     // Go right
        } else {
            right = mid;        // Could be answer, look left
        }
    }
    
    return left;
}
```

---

### Complexity Analysis

**Time Complexity: O(log n)**
- Lower bound: O(log n)
- Upper bound: O(log n)  
- Total: O(log n)

**Space Complexity: O(1)**
- Only using a few variables
- No additional data structures

**Comparison with Linear Search:**
```
For array of size n = 1,000,000:

Linear Search:   O(n) = 1,000,000 operations
Binary Search:   O(log n) = 20 operations

Speedup: 50,000x faster!
```

---

## 🔑 Understanding Lower Bound vs Upper Bound

### Visual Comparison

```
Array: [1, 3, 3, 3, 5, 7, 9]
         ↓  ←    →  ↓
         
Target: 3

lower_bound(3): Points to index 1 (first 3)
upper_bound(3): Points to index 4 (first element > 3, which is 5)

Count of 3s = upper_bound(3) - lower_bound(3)
            = 4 - 1 = 3 ✓
```

### Key Differences

| Aspect | Lower Bound | Upper Bound |
|--------|-------------|-------------|
| **Definition** | First element ≥ target | First element > target |
| **When not found** | Returns n | Returns n |
| **Use for** | Finding start position | Finding end position |
| **Condition in BS** | `arr[mid] < target` | `arr[mid] <= target` |

---

## 💡 Common Range Query Patterns

### Pattern 1: Count Elements in Range
```java
// Count elements where low <= element <= high
int count = upperBound(arr, high) - lowerBound(arr, low);
```

### Pattern 2: Count Occurrences of Element
```java
// Count how many times 'x' appears
int count = upperBound(arr, x) - lowerBound(arr, x);
```

### Pattern 3: Find First Occurrence
```java
// First position where element >= target
int first = lowerBound(arr, target);
if (first < n && arr[first] == target) {
    // Found at position 'first'
}
```

### Pattern 4: Find Last Occurrence
```java
// Last position where element <= target
int last = upperBound(arr, target) - 1;
if (last >= 0 && arr[last] == target) {
    // Found at position 'last'
}
```

### Pattern 5: Check if Element Exists
```java
// Check if target exists in array
int pos = lowerBound(arr, target);
boolean exists = (pos < n && arr[pos] == target);
```

---

## 🎯 Step-by-Step Example

**Problem:** Count elements in range [5, 9]

**Array:** `[1, 3, 3, 5, 7, 9, 9, 10, 15]`

**Step 1: Find lower_bound(5)**
```
Initial: left=0, right=9

Iteration 1: mid=4, arr[4]=7
  7 >= 5, so right=4

Iteration 2: mid=2, arr[2]=3
  3 < 5, so left=3

Iteration 3: mid=3, arr[3]=5
  5 >= 5, so right=3

left=3, right=3 → lower_bound(5) = 3
```

**Step 2: Find upper_bound(9)**
```
Initial: left=0, right=9

Iteration 1: mid=4, arr[4]=7
  7 <= 9, so left=5

Iteration 2: mid=7, arr[7]=10
  10 > 9, so right=7

Iteration 3: mid=6, arr[6]=9
  9 <= 9, so left=7

left=7, right=7 → upper_bound(9) = 7
```

**Step 3: Calculate count**
```
Count = upper_bound(9) - lower_bound(5)
      = 7 - 3
      = 4

Elements: [5, 7, 9, 9] ✓
```

---

## 🔧 Template: Lower & Upper Bound

### Lower Bound Template
```java
// Returns index of first element >= target
// If no such element, returns arr.length
int lowerBound(int[] arr, int target) {
    int left = 0, right = arr.length;
    
    while (left < right) {
        int mid = left + (right - left) / 2;
        
        if (arr[mid] < target) {
            left = mid + 1;         // Discard left half
        } else {
            right = mid;            // Keep mid, search left
        }
    }
    
    return left;  // or right (they're equal)
}
```

### Upper Bound Template
```java
// Returns index of first element > target
// If no such element, returns arr.length
int upperBound(int[] arr, int target) {
    int left = 0, right = arr.length;
    
    while (left < right) {
        int mid = left + (right - left) / 2;
        
        if (arr[mid] <= target) {   // Only difference: <= instead of <
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    
    return left;
}
```

---

## ⚠️ Common Pitfalls

### 1. Using `<=` in While Loop
```java
❌ while (left <= right)     // Can cause infinite loop
✅ while (left < right)      // Correct for bounds
```

### 2. Wrong Initial Value of `right`
```java
❌ int right = arr.length - 1;   // For standard BS
✅ int right = arr.length;       // For lower/upper bound
```

### 3. Not Checking if Element Exists
```java
❌ int index = lowerBound(arr, target);
   return arr[index];            // May be out of bounds!

✅ int index = lowerBound(arr, target);
   if (index < n && arr[index] == target) {
       return arr[index];
   }
```

### 4. Confusing Lower and Upper Bound
```java
// Remember:
lower_bound(x): First element >= x   (inclusive)
upper_bound(x): First element > x    (exclusive)
```

### 5. Integer Overflow in Mid
```java
❌ int mid = (left + right) / 2;        // Can overflow
✅ int mid = left + (right - left) / 2; // Safe
```

---

## 🎓 Interview Tips

### How to Explain Your Approach

```
"I'll use binary search to find the bounds efficiently.

Step 1: Use lower_bound to find the first element >= low
        This gives us the start of our range.

Step 2: Use upper_bound to find the first element > high
        This gives us one past the end of our range.

Step 3: Count = upper_index - lower_index

Time: O(log n) instead of O(n) with linear scan."
```

### Common Follow-up Questions

**Q: "What if array has duplicates?"**
```
A: "Lower and upper bound handle duplicates perfectly.
    For example, counting occurrences of x:
    count = upper_bound(x) - lower_bound(x)"
```

**Q: "What if range is [low, high) instead of [low, high]?"**
```
A: "For exclusive high, just use:
    count = lower_bound(high) - lower_bound(low)"
```

**Q: "Can you do this with one binary search?"**
```
A: "Not for accurate count in all cases. We need both bounds
    to handle edge cases like duplicates and missing elements."
```

**Q: "What if array is not sorted?"**
```
A: "We'd need to sort first: O(n log n)
    Then binary search: O(log n)
    Total: O(n log n)
    
    Or use linear scan: O(n) - might be better if array is small."
```

---

## 🧪 Edge Cases to Consider

Test your solution with:

1. **Empty array:** `[]`
2. **Single element:** `[5]`, range `[3, 7]`
3. **No elements in range:** `[1, 2, 10, 11]`, range `[3, 9]`
4. **All elements in range:** `[5, 6, 7, 8]`, range `[1, 10]`
5. **Duplicates:** `[3, 3, 3, 3]`, range `[3, 3]`
6. **Range at boundaries:**
   - Range before all elements: `[5, 10]`, range `[1, 3]`
   - Range after all elements: `[5, 10]`, range `[15, 20]`
7. **Negative numbers:** `[-5, -2, 0, 3, 7]`, range `[-3, 5]`

---

## 📚 Related Problems

### Similar Problems to Practice

1. **First and Last Position of Element** - Find start and end
2. **Search Insert Position** - Lower bound application
3. **Count Smaller/Greater Elements** - Bounds in queries
4. **Frequency of Element** - Using both bounds
5. **Find K Closest Elements** - Range selection

### Progression Path

```
1. Binary Search (Standard)
   ↓
2. Lower Bound / Upper Bound ← You are here
   ↓
3. Range Queries with Updates
   ↓
4. Segment Trees / Fenwick Trees
```

---

## 🔗 Additional Resources

- [GeeksforGeeks - Lower/Upper Bound](https://www.geeksforgeeks.org/upper_bound-and-lower_bound-for-vector-in-cpp-stl/)
- [C++ STL Reference](https://en.cppreference.com/w/cpp/algorithm/lower_bound)
- [LeetCode - Binary Search](https://leetcode.com/tag/binary-search/)

### Video Tutorials
- [Lower Bound and Upper Bound Explained](https://www.youtube.com/results?search_query=lower+bound+upper+bound+binary+search)
- [TakeUForward - Binary Search Bounds](https://www.youtube.com/@takeUforward)

---

## 💡 Pro Tips

1. **Memorize the Templates**
   - Lower bound and upper bound are used frequently
   - Practice writing them without reference

2. **Draw Diagrams**
   - Visualize where bounds point
   - Mark indices on paper during interviews

3. **Test with Duplicates**
   - Duplicates are common gotchas
   - Always test: `[1, 2, 2, 2, 3]`

4. **Check Return Values**
   - Bounds can return `arr.length`
   - Always validate before accessing array

5. **Know STL Equivalents (for C++)**
   ```cpp
   lower_bound(arr.begin(), arr.end(), target)
   upper_bound(arr.begin(), arr.end(), target)
   ```

---

<div align="center">

**Master Range Queries with Binary Search! 📏🔍**

*Last Updated: December 2024*

[⬆ Back to Searching](../README.md) | [⬆ Back to Main](../../README.md)

</div>
