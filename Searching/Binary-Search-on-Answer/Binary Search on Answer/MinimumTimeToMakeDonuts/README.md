# 🍩 Minimum Time to Make Donuts

[![Difficulty](https://img.shields.io/badge/Difficulty-Medium--Hard-red?style=flat-square)](.)
[![Concept](https://img.shields.io/badge/Concept-Binary%20Search%20on%20Answer-blue?style=flat-square)](.)

> Find the minimum time needed to make a certain number of donuts using multiple machines with different production rates. Master binary search on answer with feasibility checking.

---

## 📋 Problem Description

### The Challenge

You have `n` donut-making machines. Each machine `i` takes `time[i]` minutes to make one donut.

Find the **minimum time** needed to make **at least** `k` donuts using all available machines.

**Key Points:**
- Machines work **in parallel** (simultaneously)
- Each machine makes one donut at a time
- A machine can make multiple donuts (one after another)
- Find the minimum time to produce **at least** k donuts

**Input:**
- Array `time[]` where `time[i]` = time for machine i to make one donut
- Integer `k` = number of donuts needed

**Output:**
- Minimum time (in minutes) to make at least k donuts

---

### Examples

**Example 1: Two Machines**
```
Input: time = [5, 10], k = 5

Machine 0: Makes 1 donut every 5 minutes
Machine 1: Makes 1 donut every 10 minutes

At time = 15 minutes:
- Machine 0: 15/5 = 3 donuts
- Machine 1: 15/10 = 1 donut
- Total: 3 + 1 = 4 donuts (not enough)

At time = 20 minutes:
- Machine 0: 20/5 = 4 donuts
- Machine 1: 20/10 = 2 donuts
- Total: 4 + 2 = 6 donuts ✓ (enough!)

Output: 20
```

**Example 2: Three Machines**
```
Input: time = [1, 2, 3], k = 10

At time = 7 minutes:
- Machine 0: 7/1 = 7 donuts
- Machine 1: 7/2 = 3 donuts
- Machine 2: 7/3 = 2 donuts
- Total: 7 + 3 + 2 = 12 donuts ✓

Output: 7
```

**Example 3: Single Machine**
```
Input: time = [5], k = 3

Need 3 donuts, machine makes 1 every 5 minutes
Time needed: 3 × 5 = 15 minutes

Output: 15
```

**Example 4: Fast Machines**
```
Input: time = [1, 1, 1], k = 5

Three machines, each makes 1/minute
At time = 2:
- Each machine: 2 donuts
- Total: 6 donuts ✓

Output: 2
```

---

## 🎯 Learning Objectives

This problem teaches:
- **Binary search on answer** - Search time range, not indices
- **Feasibility function** - Check if time T is sufficient
- **Parallel processing** - Multiple workers simultaneously
- **Mathematical optimization** - Finding minimum satisfying condition
- **Rate × Time problems** - Production rate calculations

---

## 🔧 Approach 1: Binary Search on Answer (Optimal) ⭐

### Algorithm

**Key Insight:** If we can make k donuts in T minutes, we can also make k donuts in T+1, T+2, etc.

This gives us a **monotonic property** → perfect for binary search!

**Strategy:**
1. Binary search on time range [0, max_possible_time]
2. For each mid time, check if we can make ≥ k donuts
3. If yes, try smaller time (right = mid)
4. If no, need more time (left = mid + 1)

**Feasibility Check:**
For time T, total donuts = Σ(T / time[i]) for all machines

### Implementation

```java
public int minTimeToMakeDonuts(int[] time, int k) {
    // Edge cases
    if (time == null || time.length == 0 || k <= 0) {
        return 0;
    }
    
    // Binary search on time
    long left = 1;
    long right = (long) k * findMax(time);  // Worst case: slowest machine makes all
    long result = right;
    
    while (left <= right) {
        long mid = left + (right - left) / 2;
        
        if (canMakeDonuts(time, k, mid)) {
            result = mid;  // Found valid time, try smaller
            right = mid - 1;
        } else {
            left = mid + 1;  // Need more time
        }
    }
    
    return (int) result;
}

// Check if we can make k donuts in given time
private boolean canMakeDonuts(int[] time, int k, long maxTime) {
    long totalDonuts = 0;
    
    for (int machineTime : time) {
        totalDonuts += maxTime / machineTime;
        
        // Early exit if we already have enough
        if (totalDonuts >= k) {
            return true;
        }
    }
    
    return totalDonuts >= k;
}

private int findMax(int[] arr) {
    int max = arr[0];
    for (int num : arr) {
        max = Math.max(max, num);
    }
    return max;
}
```

**Complexity:**
- **Time:** O(n × log(k × max_time))
  - Binary search: O(log(k × max_time))
  - Each feasibility check: O(n)
- **Space:** O(1)

**Pros:**
- ⭐ Optimal time complexity
- Elegant solution
- Handles large k efficiently
- Interview gold standard

**Cons:**
- Requires understanding binary search on answer
- Need to be careful with overflow (use long)

---

## 🔧 Approach 2: Optimized Search Range

### Algorithm

Improve the initial search range for faster convergence.

**Better Bounds:**
- Min time: `k / n` (if all machines were fastest)
- Max time: `k × slowest_machine` (if only slowest works)

### Implementation

```java
public int minTimeToMakeDonuts(int[] time, int k) {
    if (time == null || time.length == 0 || k <= 0) {
        return 0;
    }
    
    int minTime = findMin(time);
    int maxTime = findMax(time);
    
    // Better bounds
    long left = (long) k * minTime / time.length;  // Lower bound
    long right = (long) k * maxTime;               // Upper bound
    long result = right;
    
    while (left <= right) {
        long mid = left + (right - left) / 2;
        
        if (canMakeDonuts(time, k, mid)) {
            result = mid;
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }
    
    return (int) result;
}

private boolean canMakeDonuts(int[] time, int k, long maxTime) {
    long totalDonuts = 0;
    
    for (int machineTime : time) {
        totalDonuts += maxTime / machineTime;
        if (totalDonuts >= k) {
            return true;
        }
    }
    
    return totalDonuts >= k;
}

private int findMin(int[] arr) {
    int min = arr[0];
    for (int num : arr) {
        min = Math.min(min, num);
    }
    return min;
}

private int findMax(int[] arr) {
    int max = arr[0];
    for (int num : arr) {
        max = Math.max(max, num);
    }
    return max;
}
```

**Complexity:**
- **Time:** O(n × log(k × max_time)) - same but fewer iterations
- **Space:** O(1)

**Pros:**
- Tighter search range
- Faster in practice
- Still optimal complexity

---

## 🔧 Approach 3: Greedy (Doesn't Work!)

### Why Greedy Fails

```
Intuition: Use fastest machines first?

Problem: All machines work in parallel!
Not a sequential scheduling problem.

Example:
time = [5, 10], k = 3

Greedy thinking: Use machine 0 (faster)
- Machine 0 makes 3 donuts: 15 minutes

Correct answer: Use both machines
- At time = 10:
  - Machine 0: 2 donuts
  - Machine 1: 1 donut
  - Total: 3 donuts ✓

Greedy gives 15, optimal is 10!
```

**Conclusion:** Binary search on answer is the way!

---

## 📊 Comparison of Approaches

| Approach | Time | Space | Correctness | Interview Score |
|----------|------|-------|-------------|-----------------|
| **Binary Search** | O(n log(k·max)) | O(1) | ✅ | **⭐⭐⭐⭐⭐** |
| **Optimized Range** | O(n log(k·max)) | O(1) | ✅ | **⭐⭐⭐⭐⭐** |
| **Greedy** | - | - | ❌ | ❌ |

**Recommendation:** Use **Binary Search on Answer** - it's optimal!

---

## 🎯 Step-by-Step Dry Run

### Input:
```java
int[] time = {5, 10};
int k = 5;
```

### Execution:

**Initialization:**
```
left = 1
right = 5 × 10 = 50  (worst case: slowest machine makes all)
```

**Iteration 1:**
```
mid = (1 + 50) / 2 = 25

Can we make 5 donuts in 25 minutes?
- Machine 0: 25/5 = 5 donuts
- Machine 1: 25/10 = 2 donuts
- Total: 7 donuts ≥ 5 ✓

Yes! Try smaller time.
result = 25
right = 24
```

**Iteration 2:**
```
left = 1, right = 24
mid = (1 + 24) / 2 = 12

Can we make 5 donuts in 12 minutes?
- Machine 0: 12/5 = 2 donuts
- Machine 1: 12/10 = 1 donut
- Total: 3 donuts < 5 ✗

No! Need more time.
left = 13
```

**Iteration 3:**
```
left = 13, right = 24
mid = (13 + 24) / 2 = 18

Can we make 5 donuts in 18 minutes?
- Machine 0: 18/5 = 3 donuts
- Machine 1: 18/10 = 1 donut
- Total: 4 donuts < 5 ✗

No! Need more time.
left = 19
```

**Iteration 4:**
```
left = 19, right = 24
mid = (19 + 24) / 2 = 21

Can we make 5 donuts in 21 minutes?
- Machine 0: 21/5 = 4 donuts
- Machine 1: 21/10 = 2 donuts
- Total: 6 donuts ≥ 5 ✓

Yes! Try smaller time.
result = 21
right = 20
```

**Iteration 5:**
```
left = 19, right = 20
mid = (19 + 20) / 2 = 19

Can we make 5 donuts in 19 minutes?
- Machine 0: 19/5 = 3 donuts
- Machine 1: 19/10 = 1 donut
- Total: 4 donuts < 5 ✗

No! Need more time.
left = 20
```

**Iteration 6:**
```
left = 20, right = 20
mid = 20

Can we make 5 donuts in 20 minutes?
- Machine 0: 20/5 = 4 donuts
- Machine 1: 20/10 = 2 donuts
- Total: 6 donuts ≥ 5 ✓

Yes!
result = 20
right = 19
```

**Exit:**
```
left = 20, right = 19
left > right → EXIT

Result: 20
```

---

## ⚠️ Edge Cases & Validation

### Edge Cases to Consider

**1. Single Machine**
```java
Input: time = [5], k = 10
Output: 50
Single machine needs 10 × 5 = 50 minutes
```

**2. k = 1**
```java
Input: time = [5, 10, 15], k = 1
Output: 5
Fastest machine makes 1 donut
```

**3. All Machines Same Speed**
```java
Input: time = [5, 5, 5], k = 9
Output: 15
Each makes 3 donuts in 15 minutes
```

**4. Very Fast Machines**
```java
Input: time = [1, 1, 1], k = 100
Output: 34
Each makes 34 donuts
```

**5. Large k**
```java
Input: time = [5], k = 1000000
Need to use long to avoid overflow!
Output: 5000000
```

**6. k = 0**
```java
Input: time = [5, 10], k = 0
Output: 0
No donuts needed
```

### Input Validation

```java
public int minTimeToMakeDonuts(int[] time, int k) {
    // Validate array
    if (time == null || time.length == 0) {
        throw new IllegalArgumentException("Machines array cannot be null or empty");
    }
    
    // Validate k
    if (k < 0) {
        throw new IllegalArgumentException("k cannot be negative");
    }
    
    if (k == 0) {
        return 0;  // No donuts needed
    }
    
    // Validate machine times
    for (int t : time) {
        if (t <= 0) {
            throw new IllegalArgumentException("Machine time must be positive");
        }
    }
    
    // Main logic...
}
```

---

## 🧪 Test Cases

### Test Case 1: Basic Example
```java
Input: time = [5, 10], k = 5
Expected: 20
```

### Test Case 2: Three Machines
```java
Input: time = [1, 2, 3], k = 10
Expected: 7
```

### Test Case 3: Single Machine
```java
Input: time = [5], k = 3
Expected: 15
```

### Test Case 4: k = 1
```java
Input: time = [5, 10, 15], k = 1
Expected: 5
```

### Test Case 5: Same Speed
```java
Input: time = [5, 5, 5], k = 9
Expected: 15
```

### Test Case 6: Large k
```java
Input: time = [1], k = 1000000
Expected: 1000000
```

---

## 💡 Key Concepts Illustrated

### 1. Binary Search on Answer Pattern

```java
// Search for minimum time that satisfies condition
int left = min_possible;
int right = max_possible;

while (left < right) {
    int mid = left + (right - left) / 2;
    
    if (isFeasible(mid)) {
        right = mid;  // Try smaller
    } else {
        left = mid + 1;  // Need larger
    }
}

return left;
```

### 2. Feasibility Check

```java
// Can we make k donuts in time T?
boolean canMake(int[] time, int k, long T) {
    long total = 0;
    for (int machineTime : time) {
        total += T / machineTime;  // How many this machine makes
        if (total >= k) return true;  // Early exit
    }
    return total >= k;
}
```

### 3. Parallel Processing

```
Time = 20, Machines = [5, 10]

Machine 0: |===|===|===|===|  (4 donuts)
Machine 1: |=====|=====|     (2 donuts)
           0    5   10  15  20

Total: 4 + 2 = 6 donuts (in parallel!)
```

### 4. Overflow Prevention

```java
// Use long for large values
long right = (long) k * maxTime;
long totalDonuts = 0;

// Cast back to int only at return
return (int) result;
```

---

## 🎓 Interview Discussion Points

### When Interviewer Asks This Problem

**1. Clarify Requirements**
```
Q: "Do machines work in parallel or sequentially?"
Q: "Can a machine make multiple donuts?"
Q: "What if k = 0?"
Q: "What's the range of k and machine times?"
Q: "Can machine times be 0 or negative?"
```

**2. Explain Your Approach**
```
"I'll use binary search on the answer - the time range.

For any time T, I can calculate how many donuts each
machine produces: T / time[i]. If the total is ≥ k,
then T is feasible.

The minimum T that's feasible is our answer. Since
feasibility is monotonic (if T works, T+1 also works),
binary search is perfect here."
```

**3. Discuss Complexity**
```
"Binary search on time range: O(log(k × max_time))
Each feasibility check: O(n) for n machines
Total: O(n × log(k × max_time))

This is much better than simulating minute-by-minute!"
```

### Common Follow-ups

**Q: "What if machines have different costs?"**
```java
A: "Changes problem significantly. Would need dynamic
   programming or greedy with cost-effectiveness ratio.
   Binary search on time wouldn't directly work."
```

**Q: "Return which machines to use, not just time?"**
```java
A: "Use greedy after finding minimum time T:
   - Calculate how many each machine makes in time T
   - Prioritize faster machines to exactly reach k"
```

**Q: "Machines can break after X uses?"**
```java
A: "Would need to track uses per machine in feasibility
   check. More complex but same binary search structure."
```

---

## 🔄 Related Problems

1. **Koko Eating Bananas** - Similar binary search on answer
2. **Capacity To Ship Packages** - Binary search on capacity
3. **Minimize Max Distance to Gas Station** - Binary search on answer
4. **Split Array Largest Sum** - Binary search optimization
5. **Magnetic Force Between Balls** - Binary search on distance

---

## 📚 Additional Learning

### When to Use Binary Search on Answer

```
✅ Use when:
1. Looking for minimum/maximum value
2. Feasibility is monotonic (if x works, x+1 works)
3. Can check feasibility efficiently
4. Search space is bounded

This problem has all 4 properties!
```

### Alternative Problem Variants

**Variant 1: Minimize cost**
```
Machines have different costs
Minimize total cost to make k donuts
→ Different problem, needs DP or greedy
```

**Variant 2: Maximize donuts**
```
Given fixed time T, maximize donuts
→ Simpler: just sum T / time[i]
```

---

## 🔗 Resources

- [Binary Search on Answer Technique](https://leetcode.com/discuss/general-discussion/786126/python-powerful-ultimate-binary-search-template-solved-many-problems)
- [Similar Problems on LeetCode](https://leetcode.com/tag/binary-search/)
- [LeetCode 1482 - Minimum Days to Make Bouquets](https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/)

---

<div align="center">

**Master Binary Search on Answer! 🍩**

*When the answer itself is what you search for*

*Last Updated: December 2024*

</div>
