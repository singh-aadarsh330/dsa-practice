# 📊 Count Elements in Range

[![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green?style=flat-square)](.)
[![Concept](https://img.shields.io/badge/Concept-Counting%20%7C%20Binary%20Search-blue?style=flat-square)](.)

> Count how many elements in an array fall within a given range. Learn efficient counting techniques for sorted and unsorted arrays.

---

## 📋 Problem Description

### The Challenge

Given an array of integers and a range `[low, high]`, count how many elements fall within this range (inclusive).

**Requirements:**
- Count elements where `low ≤ element ≤ high`
- Both endpoints are inclusive
- Handle negative numbers
- Optimize for sorted arrays

**Input:**
- Integer array `arr` (can be sorted or unsorted)
- Integer `low` (lower bound, inclusive)
- Integer `high` (upper bound, inclusive)

**Output:**
- Integer count of elements in range [low, high]

---

### Examples

**Example 1: Basic Case**
```
Input: arr = [1, 3, 5, 7, 9, 11, 13], low = 5, high = 10

Elements in range [5, 10]:
1 → No (< 5)
3 → No (< 5)
5 → Yes (= 5) ✓
7 → Yes (5 < 7 < 10) ✓
9 → Yes (5 < 9 < 10) ✓
11 → No (> 10)
13 → No (> 10)

Output: 3
```

**Example 2: All Elements in Range**
```
Input: arr = [5, 6, 7, 8], low = 5, high = 10

All elements satisfy 5 ≤ element ≤ 10

Output: 4
```

**Example 3: No Elements in Range**
```
Input: arr = [1, 2, 3, 15, 16, 17], low = 5, high = 10

No elements in [5, 10]

Output: 0
```

**Example 4: Single Element Match**
```
Input: arr = [10], low = 5, high = 15

10 is in [5, 15]

Output: 1
```

**Example 5: Negative Numbers**
```
Input: arr = [-5, -2, 0, 3, 7, 10], low = -3, high = 5

Elements in range [-3, 5]:
-5 → No (< -3)
-2 → Yes (-3 ≤ -2 ≤ 5) ✓
0 → Yes ✓
3 → Yes ✓
7 → No (> 5)
10 → No (> 5)

Output: 3
```

**Example 6: Duplicates**
```
Input: arr = [2, 5, 5, 5, 8, 10], low = 5, high = 8

Elements in range [5, 8]:
2 → No
5 → Yes ✓
5 → Yes ✓
5 → Yes ✓
8 → Yes ✓
10 → No

Output: 4
```

---

## 🎯 Learning Objectives

This problem teaches:
- **Linear scanning** - Simple O(n) solution
- **Binary search optimization** - For sorted arrays
- **Boundary finding** - First and last in range
- **Comparison operators** - Range checking logic
- **Trade-offs** - Simple vs optimized approaches

---

## 🔧 Approach 1: Linear Scan (Works for Any Array)

### Algorithm

Iterate through array and count elements in range.

**Simple and Universal:**
- Works for sorted and unsorted arrays
- Single pass through data
- Easy to implement

### Implementation

```java
public int countInRange(int[] arr, int low, int high) {
    // Edge case
    if (arr == null || arr.length == 0) {
        return 0;
    }
    
    int count = 0;
    
    for (int num : arr) {
        if (num >= low && num <= high) {
            count++;
        }
    }
    
    return count;
}
```

**Complexity:**
- **Time:** O(n) - scan entire array
- **Space:** O(1) - only counter variable

**Pros:**
- ✅ Simple and clear
- ✅ Works for any array (sorted or not)
- ✅ Easy to understand and debug
- ✅ Optimal for unsorted arrays

**Cons:**
- Not optimal for sorted arrays
- Must check every element

---

## 🔧 Approach 2: Binary Search (For Sorted Arrays) ⭐

### Algorithm

For **sorted arrays**, use binary search to find:
1. First element ≥ low (left boundary)
2. Last element ≤ high (right boundary)
3. Count = right - left + 1

**Key Insight:** In sorted array, elements in range are contiguous!

### Implementation

```java
public int countInRangeSorted(int[] arr, int low, int high) {
    if (arr == null || arr.length == 0 || low > high) {
        return 0;
    }
    
    // Find first element >= low
    int leftIndex = findFirst(arr, low);
    
    // Find last element <= high
    int rightIndex = findLast(arr, high);
    
    // If no valid range found
    if (leftIndex == -1 || rightIndex == -1 || leftIndex > rightIndex) {
        return 0;
    }
    
    return rightIndex - leftIndex + 1;
}

// Find first index where arr[i] >= target
private int findFirst(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    int result = -1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (arr[mid] >= target) {
            result = mid;
            right = mid - 1;  // Look for earlier occurrence
        } else {
            left = mid + 1;
        }
    }
    
    return result;
}

// Find last index where arr[i] <= target
private int findLast(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    int result = -1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (arr[mid] <= target) {
            result = mid;
            left = mid + 1;  // Look for later occurrence
        } else {
            right = mid - 1;
        }
    }
    
    return result;
}
```

**Complexity:**
- **Time:** O(log n) - two binary searches
- **Space:** O(1)

**Pros:**
- ⭐ Optimal for sorted arrays
- Fast for large datasets
- Logarithmic time

**Cons:**
- Requires sorted array
- More complex code
- Two binary searches needed

---

## 🔧 Approach 3: Stream API (Java 8+)

### Algorithm

Use Java streams for functional approach.

### Implementation

```java
public int countInRange(int[] arr, int low, int high) {
    if (arr == null) {
        return 0;
    }
    
    return (int) Arrays.stream(arr)
                       .filter(num -> num >= low && num <= high)
                       .count();
}
```

**Complexity:**
- **Time:** O(n)
- **Space:** O(1)

**Pros:**
- Concise and readable
- Functional style
- Modern Java

**Cons:**
- Same O(n) as linear
- Stream overhead
- Not optimal for sorted

---

## 📊 Comparison of Approaches

| Approach | Time | Space | Array Type | Readability |
|----------|------|-------|------------|-------------|
| **Linear Scan** | O(n) | O(1) | Any | ⭐⭐⭐⭐⭐ |
| **Binary Search** | O(log n) | O(1) | **Sorted only** | ⭐⭐⭐⭐ |
| **Stream API** | O(n) | O(1) | Any | ⭐⭐⭐⭐ |

**Recommendation:** 
- Use **Linear Scan** for unsorted arrays or simplicity
- Use **Binary Search** for sorted arrays with large n

---

## 🎯 Step-by-Step Dry Run

### Input:
```java
int[] arr = {1, 3, 5, 7, 9, 11, 13};  // Sorted
int low = 5, high = 10;
```

### Execution (Binary Search Approach):

**Step 1: Find First Element ≥ 5**

```
Initial: left = 0, right = 6

Iteration 1:
mid = 3, arr[3] = 7
7 >= 5 → result = 3, right = 2

Iteration 2:
left = 0, right = 2
mid = 1, arr[1] = 3
3 < 5 → left = 2

Iteration 3:
left = 2, right = 2
mid = 2, arr[2] = 5
5 >= 5 → result = 2, right = 1

Iteration 4:
left = 2, right = 1
left > right → EXIT

leftIndex = 2
```

**Step 2: Find Last Element ≤ 10**

```
Initial: left = 0, right = 6

Iteration 1:
mid = 3, arr[3] = 7
7 <= 10 → result = 3, left = 4

Iteration 2:
left = 4, right = 6
mid = 5, arr[5] = 11
11 > 10 → right = 4

Iteration 3:
left = 4, right = 4
mid = 4, arr[4] = 9
9 <= 10 → result = 4, left = 5

Iteration 4:
left = 5, right = 4
left > right → EXIT

rightIndex = 4
```

**Step 3: Calculate Count**

```
count = rightIndex - leftIndex + 1
count = 4 - 2 + 1 = 3

Elements: arr[2], arr[3], arr[4] = 5, 7, 9 ✓
```

---

## ⚠️ Edge Cases & Validation

### Edge Cases to Consider

**1. Empty Array**
```java
Input: arr = [], low = 5, high = 10
Output: 0
```

**2. Single Element - In Range**
```java
Input: arr = [7], low = 5, high = 10
Output: 1
```

**3. Single Element - Out of Range**
```java
Input: arr = [15], low = 5, high = 10
Output: 0
```

**4. All Elements Below Range**
```java
Input: arr = [1, 2, 3], low = 10, high = 20
Output: 0
```

**5. All Elements Above Range**
```java
Input: arr = [20, 30, 40], low = 5, high = 10
Output: 0
```

**6. All Elements in Range**
```java
Input: arr = [5, 6, 7, 8, 9], low = 5, high = 10
Output: 5
```

**7. Range Boundaries**
```java
Input: arr = [5, 10, 15], low = 5, high = 10
Output: 2
Both boundaries included
```

**8. Negative Range**
```java
Input: arr = [-10, -5, 0, 5], low = -7, high = 2
Output: 2  (-5 and 0)
```

**9. Invalid Range (low > high)**
```java
Input: arr = [1, 2, 3], low = 10, high = 5
Output: 0
Invalid range
```

**10. Duplicate Values**
```java
Input: arr = [5, 5, 5, 5], low = 5, high = 5
Output: 4
All duplicates count
```

### Input Validation

```java
public int countInRange(int[] arr, int low, int high) {
    // Validate array
    if (arr == null) {
        throw new IllegalArgumentException("Array cannot be null");
    }
    
    if (arr.length == 0) {
        return 0;  // Empty array
    }
    
    // Validate range
    if (low > high) {
        throw new IllegalArgumentException("Invalid range: low > high");
    }
    
    // Main logic...
}
```

---

## 🧪 Test Cases

### Test Case 1: Basic Range
```java
Input: arr = [1,3,5,7,9,11,13], low = 5, high = 10
Expected: 3
Elements: 5, 7, 9
```

### Test Case 2: Boundary Values
```java
Input: arr = [5, 10, 15], low = 5, high = 10
Expected: 2
Elements: 5, 10 (both boundaries)
```

### Test Case 3: No Matches
```java
Input: arr = [1,2,3,20,21,22], low = 5, high = 15
Expected: 0
```

### Test Case 4: All Match
```java
Input: arr = [5,6,7,8,9], low = 5, high = 10
Expected: 5
```

### Test Case 5: Negative Numbers
```java
Input: arr = [-5,-2,0,3,7], low = -3, high = 5
Expected: 3
Elements: -2, 0, 3
```

### Test Case 6: Single Element
```java
Input: arr = [7], low = 5, high = 10
Expected: 1
```

### Test Case 7: Duplicates
```java
Input: arr = [5,5,5,5], low = 5, high = 5
Expected: 4
```

---

## 💡 Key Concepts Illustrated

### 1. Range Checking Pattern

```java
// Check if value is in range [low, high]
if (value >= low && value <= high) {
    // Value is in range
}
```

### 2. Binary Search - Find First

```java
// Find first element >= target
int left = 0, right = n - 1;
int result = -1;

while (left <= right) {
    int mid = left + (right - left) / 2;
    if (arr[mid] >= target) {
        result = mid;
        right = mid - 1;  // Look left for earlier
    } else {
        left = mid + 1;
    }
}
```

### 3. Binary Search - Find Last

```java
// Find last element <= target
int left = 0, right = n - 1;
int result = -1;

while (left <= right) {
    int mid = left + (right - left) / 2;
    if (arr[mid] <= target) {
        result = mid;
        left = mid + 1;  // Look right for later
    } else {
        right = mid - 1;
    }
}
```

### 4. Count Calculation

```java
// In sorted array, elements in range are contiguous
// Count = (last index in range) - (first index in range) + 1
int count = rightIndex - leftIndex + 1;
```

---

## 🎓 Interview Discussion Points

### When Interviewer Asks This Problem

**1. Clarify Requirements**
```
Q: "Is the array sorted?"
Q: "Can array have duplicates?"
Q: "Are endpoints inclusive or exclusive?"
Q: "Can low > high?" (Invalid range)
Q: "What to return for empty array?"
```

**2. Explain Your Approach**

*For Unsorted Array:*
```
"I'll use a simple linear scan, counting elements that
satisfy low ≤ element ≤ high. This is O(n) time and
O(1) space, which is optimal for unsorted arrays."
```

*For Sorted Array:*
```
"Since the array is sorted, I can use binary search to find:
1. First element >= low (left boundary)
2. Last element <= high (right boundary)
3. Count = right - left + 1

This is O(log n) instead of O(n)."
```

**3. Discuss Trade-offs**
```
"Linear scan works for any array and is simple.
Binary search is faster for sorted arrays but requires
two binary searches and more complex code.

For small arrays (n < 100), linear is fine.
For large sorted arrays, binary search is worth it."
```

### Common Follow-up Questions

**Q: "What if we need counts for multiple ranges?"**
```java
A: "For sorted array, could preprocess with prefix sums
   or use persistent data structure. For q queries:
   - Linear: O(q × n)
   - Binary search: O(q × log n)
   - With preprocessing: O(n + q × log n)"
```

**Q: "Count elements NOT in range?"**
```java
A: "count_not_in_range = arr.length - count_in_range"
```

**Q: "Find the actual elements, not just count?"**
```java
A: "Collect elements while counting:
   List<Integer> inRange = new ArrayList<>();
   for (int num : arr) {
       if (num >= low && num <= high) {
           inRange.add(num);
       }
   }
   return inRange;"
```

**Q: "Optimize for many queries on same array?"**
```java
A: "Build segment tree or fenwick tree for range queries.
   Preprocessing: O(n log n)
   Each query: O(log n)"
```

---

## 🔄 Related Problems

### Similar Problems to Practice

1. **Count of Smaller Numbers After Self** - Counting with ordering
2. **Count of Range Sum** - Range sum queries
3. **Range Sum Query** - Precompute for multiple queries
4. **Count Elements with Maximum Frequency** - Counting variant
5. **Elements in Range** - Find actual elements

### Difficulty Progression

```
Count Elements in Range (Easy) ← You are here
    ↓
Range Sum Query (Easy-Medium)
    ↓
Count of Range Sum (Hard)
    ↓
Segment Tree Range Queries (Hard)
```

---

## 🎯 Variations to Practice

### Variation 1: Return Elements
```java
public List<Integer> getElementsInRange(int[] arr, int low, int high) {
    // Return actual elements, not count
}
```

### Variation 2: Multiple Non-Overlapping Ranges
```java
public int countInMultipleRanges(int[] arr, int[][] ranges) {
    // ranges = [[low1,high1], [low2,high2], ...]
    // Count elements in any of the ranges
}
```

### Variation 3: Count Outside Range
```java
public int countOutsideRange(int[] arr, int low, int high) {
    // Count elements NOT in [low, high]
}
```

### Variation 4: Closest to Range
```java
public int countWithinDistance(int[] arr, int target, int distance) {
    // Count elements where |element - target| <= distance
    // Equivalent to range [target-distance, target+distance]
}
```

---

## 📚 Additional Learning

### When to Use Each Approach

| Array Type | Best Approach | Time |
|------------|---------------|------|
| Unsorted | Linear scan | O(n) |
| Sorted, small n | Linear scan | O(n) |
| Sorted, large n | Binary search | O(log n) |
| Multiple queries | Preprocess + Binary | O(n + q log n) |

### Optimization Opportunities

**Sorted Array with Many Queries:**
```java
class RangeCounter {
    private int[] arr;
    
    public RangeCounter(int[] arr) {
        this.arr = arr;
        Arrays.sort(arr);  // Sort once
    }
    
    public int count(int low, int high) {
        // Use binary search
        // O(log n) per query
    }
}
```

---

## 🔗 Resources

- [Binary Search Patterns](https://leetcode.com/discuss/general-discussion/786126/python-powerful-ultimate-binary-search-template-solved-many-problems)
- [Range Query Problems](https://leetcode.com/tag/range-sum-query/)
- [Array Problems](https://leetcode.com/tag/array/)

---

<div align="center">

**Master Efficient Counting Techniques! 📊**

*From linear scans to optimized binary search*

*Last Updated: December 2024*

</div>
