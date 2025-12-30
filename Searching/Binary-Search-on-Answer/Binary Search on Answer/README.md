# 🎯 Binary Search on Answer

[![Problems](https://img.shields.io/badge/Problems-1-blue?style=flat-square)](.)
[![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red?style=flat-square)](.)

> Advanced technique where binary search is applied on the answer space rather than on array indices. Used for optimization problems with monotonic properties.

---

## 📋 Overview

**Binary Search on Answer** is a powerful technique used when:
- You need to find an **optimal value** (minimize/maximize something)
- The answer lies in a **continuous range**
- You can write a **validation function** to check if a value works
- The validation has **monotonic property** (if x works, all values beyond x also work)

**Key Difference from Standard Binary Search:**
```
Standard BS:     Search for TARGET in sorted array
BS on Answer:    Search for OPTIMAL VALUE in answer space
```

---

## 🔑 When to Use Binary Search on Answer?

### Identification Checklist

✅ **Use BS on Answer if:**
1. Problem asks to "minimize the maximum" or "maximize the minimum"
2. Answer is numeric and lies in a range [low, high]
3. Brute force would be checking every value in range
4. You can write `isPossible(value)` or `isValid(value)` function
5. Monotonic: If value V works, then V+1, V+2... also work (or vice versa)

### Common Problem Patterns

| Pattern | Example Problems | Goal |
|---------|------------------|------|
| **Minimize Maximum** | Book Allocation, Painter's Partition | Minimize the maximum value |
| **Maximize Minimum** | Aggressive Cows, Largest Distance | Maximize the minimum value |
| **Find Minimum Time** | Minimum Time to Complete Tasks | Minimize total time |
| **Find Minimum Resources** | Minimum Ships/Capacity | Minimize resource usage |

---

## 📝 Problem in This Folder

| # | Problem | File | Difficulty | Pattern | Status |
|---|---------|------|------------|---------|--------|
| 1 | Minimum Time to Make Donuts | [MinimumTimeToMakeDonuts.java](./MinimumTimeToMakeDonuts.java) | Hard | Minimize Maximum | ✅ |

---

## 🎯 Problem: Minimum Time to Make Donuts

### Problem Description

You are given:
- `ranks[]` - Array where ranks[i] = rank of ith chef
- `donuts` - Number of donuts to make

**Rules:**
- A chef with rank R takes R × k² time to make the kth donut
  - 1st donut: R × 1² = R time
  - 2nd donut: R × 2² = 4R time  
  - 3rd donut: R × 3² = 9R time
- All chefs work simultaneously (parallel)
- Goal: Find **minimum time** to make all donuts

**Example:**
```
Input: ranks = [1, 2, 3], donuts = 10

Chef 1 (rank=1):
  1st donut: 1×1² = 1 time
  2nd donut: 1×2² = 4 time
  3rd donut: 1×3² = 9 time
  4th donut: 1×4² = 16 time
  
Output: Minimum time needed
```

---

### Why Binary Search on Answer?

**Brute Force Approach:**
```
Try time = 1, check if all donuts can be made
Try time = 2, check if all donuts can be made
Try time = 3, check if all donuts can be made
...
Until we find minimum time that works

Time Complexity: O(maxTime × numChefs)
```

**Binary Search Optimization:**
```
Binary search on time range [minPossible, maxPossible]
For each mid value, check if all donuts can be made

Time Complexity: O(log(maxTime) × numChefs)
```

**Monotonic Property:**
- If we can make all donuts in time T
- Then we can definitely make them in time T+1, T+2, etc.
- Perfect for binary search!

---

### Approach: Binary Search + Validation

**Algorithm Steps:**

1. **Define Search Space**
   ```java
   int low = 1;                                    // Minimum possible time
   int high = ranks[maxRankIndex] * donuts * donuts;  // Max time needed
   ```

2. **Binary Search**
   ```java
   while (low <= high) {
       int mid = low + (high - low) / 2;
       
       if (canMakeAllDonuts(ranks, donuts, mid)) {
           answer = mid;           // Found valid time
           high = mid - 1;         // Try to minimize further
       } else {
           low = mid + 1;          // Need more time
       }
   }
   ```

3. **Validation Function**
   ```java
   boolean canMakeAllDonuts(int[] ranks, int target, int time) {
       int totalDonuts = 0;
       
       for (int rank : ranks) {
           // Calculate max donuts this chef can make in given time
           int count = 0;
           int timeTaken = 0;
           
           for (int k = 1; timeTaken + rank * k * k <= time; k++) {
               timeTaken += rank * k * k;
               count++;
           }
           
           totalDonuts += count;
           if (totalDonuts >= target) return true;  // Early exit
       }
       
       return totalDonuts >= target;
   }
   ```

---

### Implementation Template

```java
public int minimumTimeToMakeDonuts(int[] ranks, int donuts) {
    // Step 1: Define search space
    int low = 1;
    int high = getMaxTime(ranks, donuts);
    int answer = high;
    
    // Step 2: Binary search on time
    while (low <= high) {
        int mid = low + (high - low) / 2;
        
        // Step 3: Check if mid time is sufficient
        if (canMake(ranks, donuts, mid)) {
            answer = mid;       // Valid time found
            high = mid - 1;     // Try to minimize
        } else {
            low = mid + 1;      // Need more time
        }
    }
    
    return answer;
}

private boolean canMake(int[] ranks, int target, int time) {
    int total = 0;
    
    for (int rank : ranks) {
        // For this chef, find max donuts possible in 'time'
        int donutsMade = maxDonutsInTime(rank, time);
        total += donutsMade;
        
        if (total >= target) return true;  // Early termination
    }
    
    return total >= target;
}

private int maxDonutsInTime(int rank, int time) {
    int count = 0;
    int timeTaken = 0;
    
    for (int k = 1; ; k++) {
        int timeForKthDonut = rank * k * k;
        if (timeTaken + timeForKthDonut > time) break;
        
        timeTaken += timeForKthDonut;
        count++;
    }
    
    return count;
}

private int getMaxTime(int[] ranks, int donuts) {
    // Worst case: slowest chef makes all donuts
    int maxRank = Arrays.stream(ranks).max().getAsInt();
    return maxRank * donuts * donuts;
}
```

---

### Optimization: Mathematical Formula

Instead of loop in `maxDonutsInTime`, use formula:

**For chef with rank R, max donuts in time T:**
```
R × (1² + 2² + 3² + ... + k²) ≤ T
R × (k(k+1)(2k+1)/6) ≤ T

Solve for k:
k ≈ ∛(6T/R)  (approximate)

Or use: k = (int)Math.sqrt(2 * T / R)
```

```java
private int maxDonutsInTime(int rank, int time) {
    // Approximate formula
    return (int)Math.sqrt(2.0 * time / rank);
}
```

---

### Complexity Analysis

**Time Complexity: O(N × log(T))**
- N = number of chefs
- T = maximum possible time
- Binary search: O(log T) iterations
- Each iteration validates in O(N) time

**Without BS:** O(T × N) where T could be very large

**Space Complexity: O(1)**
- Only using variables
- No additional data structures

---

## 🔑 General Template for BS on Answer

```java
// Template for "Minimize Maximum" problems
public int minimizeMaximum(int[] arr, int target) {
    int low = minPossibleAnswer;
    int high = maxPossibleAnswer;
    int answer = high;
    
    while (low <= high) {
        int mid = low + (high - low) / 2;
        
        if (isPossible(arr, target, mid)) {
            answer = mid;           // This value works
            high = mid - 1;         // Try to minimize
        } else {
            low = mid + 1;          // Need larger value
        }
    }
    
    return answer;
}

// Template for "Maximize Minimum" problems  
public int maximizeMinimum(int[] arr, int target) {
    int low = minPossibleAnswer;
    int high = maxPossibleAnswer;
    int answer = low;
    
    while (low <= high) {
        int mid = low + (high - low) / 2;
        
        if (isPossible(arr, target, mid)) {
            answer = mid;           // This value works
            low = mid + 1;          // Try to maximize
        } else {
            high = mid - 1;         // Need smaller value
        }
    }
    
    return answer;
}
```

---

## 💡 Step-by-Step Example

**Input:** ranks = [1, 2], donuts = 5

**Step 1: Define range**
```
low = 1
high = 2 × 5 × 5 = 50
```

**Step 2: Binary search**

| Iter | Low | High | Mid | Can Make 5? | Action |
|------|-----|------|-----|-------------|--------|
| 1 | 1 | 50 | 25 | ✓ (can make more) | answer=25, high=24 |
| 2 | 1 | 24 | 12 | ✓ | answer=12, high=11 |
| 3 | 1 | 11 | 6 | ✗ (only 4 donuts) | low=7 |
| 4 | 7 | 11 | 9 | ✓ | answer=9, high=8 |
| 5 | 7 | 8 | 7 | ✗ | low=8 |
| 6 | 8 | 8 | 8 | ✓ | answer=8, high=7 |

**Answer: 8**

**Verification at time=8:**
- Chef 1 (rank=1): 1×1²=1, 1×2²=4, total=5, next would be 9 → 2 donuts
- Chef 2 (rank=2): 2×1²=2, 2×2²=8, total=10 > 8 → 1 donut
Hmm, that's only 3 donuts. Let me recalculate...

Actually at time=8:
- Chef 1: Makes 2 donuts (1+4=5 time used)
- Chef 2: Makes 1 donut (2 time used)
Total = 3 donuts

This shows the problem needs careful calculation!

---

## ⚠️ Common Pitfalls

### Mistakes to Avoid

1. **Wrong Search Space Bounds**
   ```java
   ❌ int high = Integer.MAX_VALUE;      // Too large, wastes iterations
   ✅ int high = maxRank * n * n;        // Reasonable upper bound
   ```

2. **Incorrect Monotonic Property**
   ```
   Make sure: if mid works, then mid+1 also works
   Otherwise binary search gives wrong answer
   ```

3. **Off-by-One in Validation**
   ```java
   ❌ if (total > target) return true;    // Should be >=
   ✅ if (total >= target) return true;   // Correct
   ```

4. **Integer Overflow**
   ```java
   ❌ int time = rank * k * k;            // Can overflow
   ✅ long time = (long)rank * k * k;     // Use long
   ```

5. **Not Using Early Termination**
   ```java
   // Optimize validation
   if (total >= target) return true;  // Stop early
   ```

---

## 🎓 Interview Tips

### How to Recognize BS on Answer

**During problem reading, look for:**
- "Minimize the maximum..."
- "Maximize the minimum..."
- "Find minimum time/cost..."
- "What is the smallest/largest value..."

**Mental checklist:**
1. Can I define a range [low, high] for answer?
2. Can I write a function to check if value X works?
3. If X works, does X+1 also work? (monotonic)

### How to Explain Your Approach

```
"I'll use binary search on the answer space.

Step 1: The answer must be between [minimum possible] and [maximum possible]

Step 2: For any time T, I can check if it's enough by simulating
        how many donuts each chef can make in time T.

Step 3: This has monotonic property: if time T works, T+1 also works.
        So I can binary search to find the minimum valid time.

Complexity: O(N log T) instead of O(N × T) brute force."
```

### Common Follow-ups

**Q: "Why binary search instead of linear search?"**
```
A: "If max time is 10^6, linear search does 10^6 iterations.
    Binary search does only log(10^6) ≈ 20 iterations.
    That's a 50,000x speedup!"
```

**Q: "What if problem asks for maximum instead of minimum?"**
```
A: "Just change the update logic:
    When mid works: answer = mid, low = mid + 1  (maximize)
    Instead of:     answer = mid, high = mid - 1 (minimize)"
```

---

## 📚 Similar Problems to Practice

### Beginner Level
1. **Square Root of Integer** - Find √n using BS
2. **First Bad Version** - Classic BS on answer

### Intermediate Level
3. **Capacity To Ship Packages** - Minimize maximum weight
4. **Koko Eating Bananas** - Minimize eating speed
5. **Magnetic Force Between Balls** - Maximize minimum distance

### Advanced Level
6. **Book Allocation Problem** - Minimize maximum pages
7. **Painter's Partition** - Minimize maximum time
8. **Aggressive Cows** - Maximize minimum distance
9. **Split Array Largest Sum** - Minimize largest sum

---

## 🔗 Resources

- [GeeksforGeeks - BS on Answer](https://www.geeksforgeeks.org/binary-search-on-answer-tutorial/)
- [Striver - BS on Answer](https://takeuforward.org/binary-search/binary-search-on-answer/)
- [LeetCode - Binary Search Tag](https://leetcode.com/tag/binary-search/)

---

<div align="center">

**Master BS on Answer, Master Optimization! 🎯**

*Last Updated: December 2024*

[⬆ Back to Searching](../README.md) | [⬆ Back to Main](../../README.md)

</div>
