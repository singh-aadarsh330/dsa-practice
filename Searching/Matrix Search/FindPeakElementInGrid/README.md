# ⛰️ Find Peak Element

[![Difficulty](https://img.shields.io/badge/Difficulty-Medium-orange?style=flat-square)](.)
[![Concept](https://img.shields.io/badge/Concept-Binary%20Search-blue?style=flat-square)](.)

> Find a peak element in an array where a peak is greater than or equal to its neighbors. Master binary search on unsorted data.

---

## 📋 Problem Description

### The Challenge

A **peak element** is an element that is **strictly greater** than its neighbors.

Given an integer array `nums`, find a peak element and return its **index**. If there are **multiple peaks**, return the index of **any one** of them.

**Array Properties:**
- You may imagine that `nums[-1] = nums[n] = -∞`
- Elements on boundaries can be peaks if greater than their single neighbor

**Input:**
- Integer array `nums`

**Output:**
- Index of any peak element
- Must run in O(log n) time

---

### Examples

**Example 1: Single Peak**
```
Input: nums = [1, 2, 3, 1]

     3 ← Peak
    / \
   2   1
  /
 1

Output: 2
Explanation: 3 is a peak (3 > 2 and 3 > 1)
```

**Example 2: Multiple Peaks**
```
Input: nums = [1, 2, 1, 3, 5, 6, 4]

       6 ← Peak
      / \
     5   4
    /
   3
  / \
 1   1
    /
   2
  /
 1

Peaks at indices: 1 (value=2) and 5 (value=6)
Output: 1 or 5 (either is valid)
```

**Example 3: Increasing Array**
```
Input: nums = [1, 2, 3, 4, 5]

Output: 4
Explanation: Last element is peak (5 > 4, and 5 > -∞)
```

**Example 4: Decreasing Array**
```
Input: nums = [5, 4, 3, 2, 1]

Output: 0
Explanation: First element is peak (-∞ < 5 and 5 > 4)
```

**Example 5: Single Element**
```
Input: nums = [1]

Output: 0
Explanation: Only element is a peak
```

**Example 6: Two Elements**
```
Input: nums = [1, 2]

Output: 1
Explanation: 2 is peak (2 > 1 and 2 > -∞)
```

---

## 🎯 Learning Objectives

This problem teaches:
- **Binary search on unsorted array** - Not all binary search needs sorting!
- **Decision-making logic** - How to pick search direction
- **Peak finding** - Classic divide and conquer problem
- **Guaranteed solution** - Proof that peak always exists
- **Boundary handling** - Edge cases at array ends

---

## 🔧 Approach 1: Linear Scan (Naive)

### Algorithm

Check every element to see if it's a peak.

### Implementation

```java
public int findPeakElement(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
        boolean isPeak = true;
        
        // Check left neighbor
        if (i > 0 && nums[i] <= nums[i - 1]) {
            isPeak = false;
        }
        
        // Check right neighbor
        if (i < nums.length - 1 && nums[i] <= nums[i + 1]) {
            isPeak = false;
        }
        
        if (isPeak) {
            return i;
        }
    }
    
    return -1;  // Should never reach (peak always exists)
}
```

**Complexity:**
- **Time:** O(n) - check every element
- **Space:** O(1)

**Pros:**
- Simple and straightforward
- Easy to understand

**Cons:**
- ❌ Not O(log n) as required
- ❌ Doesn't leverage problem structure

---

## 🔧 Approach 2: Binary Search (Optimal) ⭐

### Algorithm

**Key Insight:** If `nums[mid] < nums[mid + 1]`, there MUST be a peak in the right half (including mid+1).

**Why?**
- Array goes up from mid → peak exists to the right
- If it keeps going up → last element is peak
- If it goes down → we'll find a peak before going down

**Strategy:**
1. Compare mid with mid+1
2. If nums[mid] < nums[mid+1], search right
3. Else, search left (including mid)
4. Converge to peak

### Implementation

```java
public int findPeakElement(int[] nums) {
    int left = 0;
    int right = nums.length - 1;
    
    while (left < right) {
        int mid = left + (right - left) / 2;
        
        if (nums[mid] < nums[mid + 1]) {
            // Peak is in right half
            left = mid + 1;
        } else {
            // Peak is in left half (including mid)
            right = mid;
        }
    }
    
    // left == right, both point to peak
    return left;
}
```

**Complexity:**
- **Time:** O(log n) - binary search
- **Space:** O(1)

**Pros:**
- ⭐ Optimal O(log n) time
- Elegant solution
- Always finds a peak
- Works on unsorted array!

**Cons:**
- Requires understanding the logic
- Not intuitive at first

---

## 🔧 Approach 3: Recursive Binary Search

### Algorithm

Same logic as iterative but using recursion.

### Implementation

```java
public int findPeakElement(int[] nums) {
    return findPeakHelper(nums, 0, nums.length - 1);
}

private int findPeakHelper(int[] nums, int left, int right) {
    if (left == right) {
        return left;
    }
    
    int mid = left + (right - left) / 2;
    
    if (nums[mid] < nums[mid + 1]) {
        return findPeakHelper(nums, mid + 1, right);
    } else {
        return findPeakHelper(nums, left, mid);
    }
}
```

**Complexity:**
- **Time:** O(log n)
- **Space:** O(log n) - recursion stack

**Pros:**
- Clean recursive structure
- Same time complexity

**Cons:**
- Extra space for stack
- Iterative is preferred

---

## 📊 Comparison of Approaches

| Approach | Time | Space | Difficulty | Interview Score |
|----------|------|-------|------------|-----------------|
| **Linear Scan** | O(n) | O(1) | ⭐ | ⭐⭐ |
| **Binary Search (Iterative)** | O(log n) | O(1) | ⭐⭐⭐⭐ | **⭐⭐⭐⭐⭐** |
| **Binary Search (Recursive)** | O(log n) | O(log n) | ⭐⭐⭐ | ⭐⭐⭐⭐ |

**Recommendation:** Use **Iterative Binary Search** - optimal time and space!

---

## 🎯 Step-by-Step Dry Run

### Input:
```java
int[] nums = {1, 2, 1, 3, 5, 6, 4};
```

### Execution (Binary Search):

**Iteration 1:**
```
left = 0, right = 6
mid = 3

nums[3] = 3, nums[4] = 5
3 < 5 → Peak is in right half
left = 4
```

**Iteration 2:**
```
left = 4, right = 6
mid = 5

nums[5] = 6, nums[6] = 4
6 > 4 → Peak is in left half (including 5)
right = 5
```

**Iteration 3:**
```
left = 4, right = 5
mid = 4

nums[4] = 5, nums[5] = 6
5 < 6 → Peak is in right half
left = 5
```

**Exit:**
```
left = 5, right = 5
left == right → Found peak!

Return: 5
```

**Verification:**
```
nums[5] = 6
nums[4] = 5 < 6 ✓
nums[6] = 4 < 6 ✓

6 is indeed a peak!
```

---

## ⚠️ Edge Cases & Validation

### Edge Cases to Consider

**1. Single Element**
```java
Input: nums = [1]
Output: 0
Only element is peak
```

**2. Two Elements - Ascending**
```java
Input: nums = [1, 2]
Output: 1
Last element is peak
```

**3. Two Elements - Descending**
```java
Input: nums = [2, 1]
Output: 0
First element is peak
```

**4. All Same Elements**
```java
Input: nums = [5, 5, 5, 5]
Note: Problem states STRICTLY greater
This shouldn't occur per problem definition
```

**5. Strictly Increasing**
```java
Input: nums = [1, 2, 3, 4, 5]
Output: 4
Last element is peak
```

**6. Strictly Decreasing**
```java
Input: nums = [5, 4, 3, 2, 1]
Output: 0
First element is peak
```

**7. Valley Then Peak**
```java
Input: nums = [3, 1, 2]
Output: 0 or 2
Both 3 and 2 are peaks
```

**8. Multiple Peaks**
```java
Input: nums = [1, 3, 2, 4, 1]
Output: 1 or 3
Both valid
```

### Input Validation

```java
public int findPeakElement(int[] nums) {
    // Validate input
    if (nums == null || nums.length == 0) {
        throw new IllegalArgumentException("Array cannot be null or empty");
    }
    
    // Single element is always a peak
    if (nums.length == 1) {
        return 0;
    }
    
    // Main logic...
}
```

---

## 🧪 Test Cases

### Test Case 1: Single Peak
```java
Input: nums = [1,2,3,1]
Expected: 2
```

### Test Case 2: Multiple Peaks
```java
Input: nums = [1,2,1,3,5,6,4]
Expected: 1 or 5 (any peak)
```

### Test Case 3: Increasing
```java
Input: nums = [1,2,3,4,5]
Expected: 4
```

### Test Case 4: Decreasing
```java
Input: nums = [5,4,3,2,1]
Expected: 0
```

### Test Case 5: Single Element
```java
Input: nums = [1]
Expected: 0
```

### Test Case 6: Two Elements
```java
Input: nums = [1,2]
Expected: 1
```

### Test Case 7: Valley
```java
Input: nums = [3,1,2]
Expected: 0 or 2
```

---

## 💡 Key Concepts Illustrated

### 1. Why Binary Search Works Here

```
Key insight: If going upward, peak MUST exist ahead!

[1, 2, 3, 4, 5]  → Keeps going up → last is peak
[1, 2, 3, 2, 1]  → Goes down → found peak at 3

Either way, peak guaranteed in chosen direction!
```

### 2. Decision Logic

```java
if (nums[mid] < nums[mid + 1]) {
    // Going upward → peak on right
    left = mid + 1;
} else {
    // Going downward or flat → peak on left (including mid)
    right = mid;
}
```

### 3. Why Peak Always Exists

```
Boundaries are -∞:
nums[-1] = -∞
nums[n] = -∞

So any element has advantage at boundaries!

If array goes up: last element is peak
If array goes down: first element is peak  
If array has valley: elements on sides are peaks
```

### 4. Search Space Reduction

```
Each iteration eliminates half:
n → n/2 → n/4 → ... → 1

Total iterations = log₂(n)
```

---

## 🎓 Interview Discussion Points

### When Interviewer Asks This Problem

**1. Clarify Requirements**
```
Q: "Can I return any peak or specific one?"
Q: "Are there always unique peaks?"
Q: "Can array have duplicates?"
Q: "What's the time complexity requirement?"
Q: "How to handle empty array?"
```

**2. Explain Your Approach**
```
"I'll use binary search even though array isn't sorted.
The key insight is: if mid element is smaller than next,
there MUST be a peak to the right because:
- Either array keeps going up (last is peak)
- Or it goes down somewhere (that's a peak)

This guarantees O(log n) time."
```

**3. Discuss Why It Works**
```
"The algorithm works because:
1. Boundaries are -∞ (implicit)
2. If going upward, peak exists ahead
3. We always move toward a peak
4. Converge to a peak guaranteed"
```

### Common Follow-up Questions

**Q: "Find ALL peaks, not just one?"**
```java
A: "Would need O(n) to check all elements:
   List<Integer> peaks = new ArrayList<>();
   for (int i = 0; i < nums.length; i++) {
       boolean isPeak = true;
       if (i > 0 && nums[i] <= nums[i-1]) isPeak = false;
       if (i < n-1 && nums[i] <= nums[i+1]) isPeak = false;
       if (isPeak) peaks.add(i);
   }
   return peaks;"
```

**Q: "What if we need the MAXIMUM peak?"**
```java
A: "Would need O(n) to find global maximum.
   Binary search only finds A peak, not necessarily highest."
```

**Q: "2D version - find peak in matrix?"**
```java
A: "More complex. Can use binary search on columns,
   then find max in that column. Time: O(n log m) or O(m log n)"
```

**Q: "What if array is rotated sorted array?"**
```java
A: "Different problem - finding rotation point or target.
   Similar binary search technique but different logic."
```

---

## 🔄 Related Problems

### Similar Problems to Practice

1. **Find Minimum in Rotated Sorted Array** - Binary search variant
2. **Peak Index in Mountain Array** - Simpler version (guaranteed single peak)
3. **Find Peak Element II** - 2D version
4. **Search in Rotated Sorted Array** - Similar decision logic
5. **Find First and Last Position** - Binary search boundary finding

### Difficulty Progression

```
Peak Index in Mountain Array (Easy)
    ↓
Find Peak Element (Medium) ← You are here
    ↓
Find Peak Element II (Medium-Hard)
    ↓
Find Minimum in Rotated Sorted Array II (Hard)
```

---

## 🎯 Variations to Practice

### Variation 1: Count All Peaks
```java
public int countPeaks(int[] nums) {
    // Count total number of peaks
}
```

### Variation 2: Find Peak Value
```java
public int findPeakValue(int[] nums) {
    // Return the value, not index
    int peakIndex = findPeakElement(nums);
    return nums[peakIndex];
}
```

### Variation 3: Mountain Array
```java
public int peakIndexInMountainArray(int[] arr) {
    // Array guaranteed to be mountain (single peak)
    // Slightly simpler than general case
}
```

### Variation 4: Find All Local Maxima
```java
public List<Integer> findAllLocalMaxima(int[] nums) {
    // Find all elements greater than both neighbors
}
```

---

## 📚 Additional Learning

### Proof: Peak Always Exists

```
Case 1: Increasing to end
[1, 2, 3, 4, 5]
→ Last element is peak (5 > 4, 5 > -∞)

Case 2: Decreasing from start
[5, 4, 3, 2, 1]
→ First element is peak (-∞ < 5, 5 > 4)

Case 3: Has valley
[3, 1, 2]
→ Both sides have peaks (3 and 2)

Conclusion: Peak ALWAYS exists!
```

### Why Binary Search on Unsorted Array

```
Binary search doesn't need full sorting!
Just needs a way to eliminate half the search space.

Here: Decision based on slope direction
- Upward slope → peak on right
- Downward slope → peak on left

This gives us the elimination property!
```

---

## 🔗 Resources

- [LeetCode 162 - Find Peak Element](https://leetcode.com/problems/find-peak-element/)
- [Binary Search Patterns](https://leetcode.com/discuss/general-discussion/786126/python-powerful-ultimate-binary-search-template-solved-many-problems)
- [Peak Finding Algorithm](https://ocw.mit.edu/courses/6-006-introduction-to-algorithms-fall-2011/resources/lecture-1-algorithmic-thinking-peak-finding/)

---

<div align="center">

**Master Binary Search on Unsorted Data! ⛰️**

*Not all binary search requires sorting!*

*Last Updated: December 2024*

</div>
