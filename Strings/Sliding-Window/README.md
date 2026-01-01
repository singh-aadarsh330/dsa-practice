# Sliding Window Technique

## Table of Contents
- [Introduction](#introduction)
- [Theoretical Foundation](#theoretical-foundation)
- [Problem Recognition](#problem-recognition)
- [Window Types](#window-types)
- [Algorithm Patterns](#algorithm-patterns)
- [Implementation Strategies](#implementation-strategies)
- [Complexity Analysis](#complexity-analysis)
- [Advanced Techniques](#advanced-techniques)
- [Common Variations](#common-variations)

## Introduction

The sliding window technique is a powerful algorithmic optimization pattern that reduces the time complexity of certain array and string problems from O(n²) or O(n³) to O(n). This technique maintains a subset of elements (a "window") that slides through the array or string, updating the window's state incrementally rather than recalculating from scratch.

### Historical Context

The sliding window approach emerged from the broader category of two-pointer techniques and has become a fundamental pattern in competitive programming and technical interviews. It represents a specific application of the incremental computation principle, where overlapping calculations are avoided through careful state management.

### Importance in Problem Solving

**Efficiency Gain**: Transforms quadratic or cubic time algorithms into linear time solutions through elimination of redundant computations.

**Pattern Recognition**: Once mastered, enables rapid identification of applicable problems and immediate implementation of optimal solutions.

**Interview Relevance**: Frequently tested in technical interviews at major technology companies (Google, Facebook, Amazon, Microsoft) as it demonstrates both optimization skills and coding proficiency.

## Theoretical Foundation

### Core Principle

The sliding window technique exploits the following mathematical property:

For a function f that computes a property over a range [i, j], if we can update f([i+1, j+1]) from f([i, j]) in O(1) time by:
- Removing the effect of element at index i
- Adding the effect of element at index j+1

Then we can compute f for all windows in O(n) total time instead of O(n²).

### Mathematical Formulation

Given an array A of length n:
- **Naive approach**: For each starting position i, compute property for all windows starting at i
  - Time complexity: Σ(n - i) for i = 0 to n-1 = O(n²)

- **Sliding window approach**: Maintain window state, update incrementally
  - Time complexity: Each element added once and removed once = O(2n) = O(n)

### Optimization Trade-off

**Time Reduction**: O(n²) → O(n)

**Space Consideration**: Often requires O(k) additional space where k is:
- Window size (for fixed windows)
- Character set size (for string problems)
- Number of distinct elements (for uniqueness constraints)

## Problem Recognition

### Identifying Sliding Window Problems

A problem is likely solvable with sliding window if it exhibits these characteristics:

#### 1. Input Structure
- Linear data structure (array or string)
- Problem involves contiguous elements (subarrays/substrings)

#### 2. Query Nature
Keywords in problem statement:
- "Longest" substring/subarray with property X
- "Shortest" substring/subarray with property Y
- "Maximum/Minimum" sum/product of k consecutive elements
- "All subarrays" satisfying condition Z

#### 3. Constraint Type
- Fixed size constraint: "Find maximum sum of k consecutive elements"
- Dynamic constraint: "Longest substring with at most k distinct characters"
- Condition-based: "Shortest substring containing all characters"

#### 4. Optimization Goal
- Finding optimal (maximum/minimum) length
- Counting valid windows
- Finding all valid windows

### Problem Examples

**Applicable**:
- Longest substring without repeating characters
- Maximum sum subarray of size k
- Minimum window substring
- Longest substring with at most k distinct characters
- Count of substrings with exactly k distinct characters

**Not Applicable**:
- Non-contiguous subsequence problems
- Problems requiring global state that cannot be updated incrementally
- Problems where order within window doesn't matter (may need sorting/hashing instead)

## Window Types

### Type 1: Fixed-Size Window

**Characteristics**:
- Window size k is predetermined and constant
- Both left and right pointers move in lockstep
- Simpler implementation, predictable behavior

**Example Problem**: "Find maximum sum of k consecutive elements"

**Visual Representation**:
```
Array: [1, 2, 3, 4, 5, 6, 7], k = 3

Step 1: [1, 2, 3] 4  5  6  7  → sum = 6
Step 2:  1 [2, 3, 4] 5  6  7  → sum = 9
Step 3:  1  2 [3, 4, 5] 6  7  → sum = 12
Step 4:  1  2  3 [4, 5, 6] 7  → sum = 15
Step 5:  1  2  3  4 [5, 6, 7] → sum = 18
```

**Implementation Pattern**:
```java
int maxSum = 0, windowSum = 0;

// Build initial window
for (int i = 0; i < k; i++) {
    windowSum += arr[i];
}
maxSum = windowSum;

// Slide window
for (int i = k; i < arr.length; i++) {
    windowSum += arr[i] - arr[i - k];  // Add new, remove old
    maxSum = Math.max(maxSum, windowSum);
}
```

**Complexity**:
- Time: O(n) - each element processed once
- Space: O(1) - constant extra space

### Type 2: Dynamic-Size Window (Expandable/Contractable)

**Characteristics**:
- Window size varies based on problem constraints
- Right pointer typically advances to expand window
- Left pointer advances to contract window when constraint violated
- More complex but handles broader problem class

**Example Problem**: "Longest substring with at most k distinct characters"

**Visual Representation**:
```
String: "eceba", k = 2

Step 1: [e]ceba          → distinct = 1 ✓
Step 2: [ec]eba          → distinct = 2 ✓
Step 3: [ece]ba          → distinct = 2 ✓
Step 4: [eceb]a          → distinct = 3 ✗ (contract)
Step 5: e[ceb]a          → distinct = 3 ✗ (contract)
Step 6: ec[eb]a          → distinct = 2 ✓
Step 7: ec[eba]          → distinct = 3 ✗ (contract)
Step 8: ece[ba]          → distinct = 2 ✓
```

**Implementation Pattern**:
```java
int left = 0, maxLength = 0;
Map<Character, Integer> windowState = new HashMap<>();

for (int right = 0; right < s.length(); right++) {
    // Expand: add right element
    char rightChar = s.charAt(right);
    windowState.put(rightChar, windowState.getOrDefault(rightChar, 0) + 1);
    
    // Contract: while constraint violated
    while (windowState.size() > k) {
        char leftChar = s.charAt(left);
        windowState.put(leftChar, windowState.get(leftChar) - 1);
        if (windowState.get(leftChar) == 0) {
            windowState.remove(leftChar);
        }
        left++;
    }
    
    // Update result
    maxLength = Math.max(maxLength, right - left + 1);
}
```

**Complexity**:
- Time: O(n) - each element added once, removed at most once
- Space: O(k) - where k is constraint parameter or character set size

## Algorithm Patterns

### Pattern 1: Fixed Window Template

**Use Case**: Problems with predetermined window size

**Template**:
```java
public int fixedWindowProblem(int[] arr, int k) {
    if (arr.length < k) return -1;  // Handle edge case
    
    // Initialize window state
    int windowMetric = 0;
    int result = 0;
    
    // Build initial window of size k
    for (int i = 0; i < k; i++) {
        windowMetric += arr[i];  // Or other operation
    }
    result = windowMetric;
    
    // Slide window across array
    for (int i = k; i < arr.length; i++) {
        // Remove leftmost element of previous window
        windowMetric -= arr[i - k];
        
        // Add rightmost element of current window
        windowMetric += arr[i];
        
        // Update result
        result = Math.max(result, windowMetric);  // Or other comparison
    }
    
    return result;
}
```

### Pattern 2: Dynamic Window with Frequency Map

**Use Case**: String problems with character counting

**Template**:
```java
public int dynamicWindowWithFrequency(String s, int constraint) {
    Map<Character, Integer> window = new HashMap<>();
    int left = 0;
    int result = 0;
    
    for (int right = 0; right < s.length(); right++) {
        // Expand window: add right character
        char rightChar = s.charAt(right);
        window.put(rightChar, window.getOrDefault(rightChar, 0) + 1);
        
        // Contract window: while constraint violated
        while (/* constraint check */) {
            char leftChar = s.charAt(left);
            window.put(leftChar, window.get(leftChar) - 1);
            if (window.get(leftChar) == 0) {
                window.remove(leftChar);
            }
            left++;
        }
        
        // Update result
        result = Math.max(result, right - left + 1);
    }
    
    return result;
}
```

### Pattern 3: Two-Pass Sliding Window

**Use Case**: Finding exact count of windows with property X

**Strategy**: 
- Count windows with "at most" X
- Count windows with "at most" X-1
- Subtract: exactly X = (at most X) - (at most X-1)

**Template**:
```java
public int exactlyKDistinct(String s, int k) {
    return atMostKDistinct(s, k) - atMostKDistinct(s, k - 1);
}

private int atMostKDistinct(String s, int k) {
    Map<Character, Integer> window = new HashMap<>();
    int left = 0, count = 0;
    
    for (int right = 0; right < s.length(); right++) {
        char rightChar = s.charAt(right);
        window.put(rightChar, window.getOrDefault(rightChar, 0) + 1);
        
        while (window.size() > k) {
            char leftChar = s.charAt(left);
            window.put(leftChar, window.get(leftChar) - 1);
            if (window.get(leftChar) == 0) {
                window.remove(leftChar);
            }
            left++;
        }
        
        count += right - left + 1;  // All subarrays ending at right
    }
    
    return count;
}
```

## Implementation Strategies

### State Management

**Decision Factors**:

1. **HashMap vs Array**
   - Use Array when: Character set is small and known (e.g., lowercase letters, ASCII)
   - Use HashMap when: Character set is large or unknown (Unicode, arbitrary integers)

2. **Window State Tracking**
   - Sum/Product: Single variable
   - Distinct count: Set size or HashMap size
   - Frequency: HashMap or frequency array
   - Custom property: May need multiple variables

### Boundary Handling

**Critical Considerations**:

1. **Empty Input**: Handle null and zero-length inputs
   ```java
   if (s == null || s.length() == 0) return 0;
   ```

2. **Window Size Validation**: For fixed windows, ensure k ≤ n
   ```java
   if (k > arr.length) return -1;  // Or throw exception
   ```

3. **Single Element**: Often edge case
   ```java
   if (s.length() == 1) return 1;  // Depends on problem
   ```

### Optimization Techniques

**1. Early Termination**
```java
if (right - left + 1 == targetLength) {
    return targetLength;  // Found optimal, no need to continue
}
```

**2. Pruning Impossible Cases**
```java
if (required > s.length()) {
    return "";  // Impossible to satisfy
}
```

**3. Avoiding Redundant Operations**
```java
// Instead of checking size repeatedly
if (window.get(leftChar) == 0) {
    window.remove(leftChar);  // Remove immediately
}
```

## Complexity Analysis

### Time Complexity Proof

**Claim**: Sliding window algorithms run in O(n) time for array of length n.

**Proof**:
1. The right pointer traverses from 0 to n-1: O(n) operations
2. The left pointer also moves from 0 to n-1 (never decreases): O(n) operations
3. Each element is:
   - Added to window exactly once (when right pointer reaches it)
   - Removed from window at most once (when left pointer passes it)
4. Total operations: 2n = O(n)

**Amortized Analysis**: 
While the inner while loop may execute multiple times, the total number of left pointer movements across all iterations is bounded by n, making the amortized cost O(1) per iteration of the outer loop.

### Space Complexity Analysis

**Fixed Window**: O(1) auxiliary space (only tracking window sum/product)

**Dynamic Window with Constraints**:
- String with k distinct characters: O(k)
- String with all unique characters: O(min(n, σ)) where σ is alphabet size
  - For ASCII: O(128) = O(1)
  - For Unicode: O(65536) or use HashMap: O(n) worst case

**Hash Map Space**: 
- Best case: O(k) where k is constraint
- Worst case: O(min(n, σ)) where σ is character set size
- Practical: Often O(26) for lowercase English letters

## Advanced Techniques

### Technique 1: Monotonic Deque for Window Maximum/Minimum

**Problem**: Find maximum in every window of size k

**Naive Sliding Window**: O(nk) - find max in each window

**Optimized with Deque**: O(n) - maintain decreasing deque

**Implementation**:
```java
public int[] maxSlidingWindow(int[] nums, int k) {
    Deque<Integer> deque = new ArrayDeque<>();  // Stores indices
    int[] result = new int[nums.length - k + 1];
    
    for (int i = 0; i < nums.length; i++) {
        // Remove elements outside window
        while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
            deque.pollFirst();
        }
        
        // Maintain decreasing order (remove smaller elements)
        while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
            deque.pollLast();
        }
        
        deque.offerLast(i);
        
        // Record maximum for complete windows
        if (i >= k - 1) {
            result[i - k + 1] = nums[deque.peekFirst()];
        }
    }
    
    return result;
}
```

### Technique 2: Binary Search on Window Size

**Problem**: Find minimum window size satisfying property

**Approach**: Binary search on answer + sliding window validation

**Template**:
```java
public int minWindowSize(int[] arr, int target) {
    int left = 1, right = arr.length;
    int result = -1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (windowOfSizeKWorks(arr, mid, target)) {
            result = mid;
            right = mid - 1;  // Try smaller
        } else {
            left = mid + 1;  // Need larger
        }
    }
    
    return result;
}

private boolean windowOfSizeKWorks(int[] arr, int k, int target) {
    // Sliding window check in O(n)
    // ...
}
```

**Complexity**: O(n log n) - log n binary search iterations, each with O(n) validation

### Technique 3: Multi-Window Technique

**Problem**: Track multiple windows simultaneously

**Example**: Longest substring with characters from different groups

**Strategy**: Maintain multiple windows with different constraints

## Common Variations

### Variation 1: Counting Valid Windows

**Instead of**: Finding longest/shortest window

**Goal**: Count total number of valid windows

**Modification**: Accumulate count instead of tracking max/min length

```java
int count = 0;
for (int right = 0; right < arr.length; right++) {
    // Expand window
    while (/* violation */) {
        // Contract
        left++;
    }
    // All subarrays [left...right] to [right...right] are valid
    count += right - left + 1;
}
```

### Variation 2: Finding All Valid Windows

**Goal**: Return all valid windows, not just count/length

**Modification**: Store window snapshots

```java
List<String> validWindows = new ArrayList<>();
for (int right = 0; right < s.length(); right++) {
    // Expand and contract
    if (/* window is valid */) {
        validWindows.add(s.substring(left, right + 1));
    }
}
```

### Variation 3: Two-Type Elements

**Problem**: Sliding window with two different element types

**Example**: Longest substring with equal number of 0s and 1s

**Technique**: Convert to prefix sum problem or use counter difference

## Problem Set

### Beginner Level
1. **Maximum Sum Subarray of Size K**
   - Difficulty: Easy
   - Type: Fixed window
   - Focus: Basic sliding window mechanics

2. **Average of All Subarrays of Size K**
   - Difficulty: Easy
   - Type: Fixed window
   - Focus: Window calculation

### Intermediate Level
1. **[Longest Substring Without Repeating Characters](./Longest-Substring-Without-Repeating-Characters/)**
   - Difficulty: Medium
   - Type: Dynamic window
   - Focus: Character uniqueness with HashMap

2. **Longest Substring with At Most K Distinct Characters**
   - Difficulty: Medium
   - Type: Dynamic window with constraint
   - Focus: Character counting

3. **Fruit Into Baskets**
   - Difficulty: Medium
   - Type: Dynamic window (disguised k=2 distinct)
   - Focus: Problem transformation

### Advanced Level
1. **Minimum Window Substring**
   - Difficulty: Hard
   - Type: Dynamic window with complex state
   - Focus: Multi-character matching

2. **Sliding Window Maximum**
   - Difficulty: Hard
   - Type: Fixed window with monotonic deque
   - Focus: Advanced data structure integration

3. **Subarrays with K Different Integers**
   - Difficulty: Hard
   - Type: Two-pass technique
   - Focus: Exact count using "at most" strategy

## Related Algorithmic Patterns

### Two Pointers
**Similarity**: Uses two indices traversing array

**Difference**: Two pointers may not maintain contiguous range; sliding window always does

**Use Case**: Two pointers for general element pairs; sliding window for subarrays

### Dynamic Programming
**Similarity**: Both optimize by avoiding recalculation

**Difference**: DP builds solution from subproblems; sliding window maintains running state

**Use Case**: DP for non-contiguous subsequences; sliding window for contiguous sequences

### Prefix Sum
**Similarity**: Both precompute to speed up range queries

**Difference**: Prefix sum stores cumulative values; sliding window maintains dynamic state

**Combination**: Can use prefix sum within sliding window for certain problems

## Debugging Strategies

### Common Issues

1. **Infinite Loop in While**
   - Cause: Left pointer not advancing or condition never satisfied
   - Fix: Ensure left++ inside while loop

2. **Window Not Contracting**
   - Cause: Incorrect constraint check
   - Fix: Verify condition logic, print window state

3. **Off-by-One Errors**
   - Cause: Incorrect window size calculation
   - Fix: Use right - left + 1 for inclusive range

4. **Missing Edge Cases**
   - Empty input, single element, k > n
   - Fix: Add validation at start of function

### Debugging Technique

```java
// Add debug printing
System.out.println("Window: [" + left + ", " + right + "]");
System.out.println("Current state: " + windowState);
System.out.println("Current result: " + result);
```

## Best Practices

1. **Initialize Carefully**: Set up window state before main loop
2. **Update Atomically**: Add/remove from window in complete operations
3. **Validate Early**: Check edge cases before main algorithm
4. **Document Invariants**: Comment what window represents at each step
5. **Test Systematically**: Empty, single element, all same, all different
6. **Choose Right Structure**: Array vs HashMap based on constraints
7. **Clean State Management**: Remove elements when count reaches 0

## References

**Research Papers**:
- "The Sliding Window Technique" - Classical algorithms literature
- Amortized analysis in CLRS (Cormen et al.)

**Online Resources**:
- LeetCode Sliding Window Pattern: https://leetcode.com/tag/sliding-window/
- GeeksforGeeks Sliding Window: https://www.geeksforgeeks.org/window-sliding-technique/

**Related Topics**:
- Two Pointers Technique
- Amortized Analysis
- Hash Tables
- Deque Data Structure

---

**Master the sliding window technique to unlock efficient solutions for substring and subarray problems.**

*Last Updated: January 2026*
