# Miscellaneous Array Problems 🎲

**Category:** Arrays - Mixed Topics  
**Difficulty:** Easy to Hard  
**Topics:** Various techniques that don't fit specific categories

---

## 📋 Overview

This section contains array problems that use mixed techniques or don't fit neatly into other categories. These problems often combine multiple concepts and are excellent for interview preparation.

---

## 🎯 Problem Categories

### Frequency & Counting Problems
1. **Majority Element** - Element appearing > n/2 times (Boyer-Moore)
2. **Find All Duplicates** - Elements appearing twice
3. **Find Missing Number** - In range [1...n]
4. **First Missing Positive** - Smallest positive integer missing
5. **Find the Duplicate** - Only one duplicate, multiple occurrences
6. **Most Frequent Element** - Highest frequency
7. **K Most Frequent Elements** - Top K by frequency
8. **Frequency Sort** - Sort by frequency

### Rearrangement Problems
9. **Rearrange Array Alternately** - Max, min, max, min...
10. **Rearrange Positive Negative** - Alternate or segregate
11. **Move All Zeros to End** - Without changing order
12. **Sort Array by Parity** - Even first, then odd
13. **Segregate 0s and 1s** - All 0s first
14. **Sort 0s, 1s, and 2s** - Dutch National Flag
15. **Rearrange by Index** - arr[i] = i

### Mathematical Problems
16. **Maximum Product of Three** - Consider negatives
17. **Product Array Puzzle** - Without division
18. **Trapping Rain Water** - Between bars
19. **Container with Most Water** - Two lines
20. **Stock Buy and Sell** - Single or multiple transactions
21. **Maximum Difference** - arr[j] - arr[i] where j > i
22. **Minimum Jumps** - Reach end with minimum jumps

### Rotation & Cyclic Problems
23. **Rotate Array** - By K positions (left/right)
24. **Rotation Count** - In rotated sorted array
25. **Search in Rotated Array** - Modified binary search
26. **Find Minimum in Rotated** - Sorted array
27. **Cyclic Rotation** - Rotate cyclically

### Pair & Triplet Problems
28. **Two Sum** - Find pair with given sum
29. **Three Sum** - Find triplets summing to target
30. **Four Sum** - Find quadruplets
31. **Closest Pair Sum** - Pair closest to target
32. **Count Pairs with Difference K** - arr[j] - arr[i] = K
33. **Pythagorean Triplet** - a² + b² = c²

### Leader & Peak Problems
34. **Array Leaders** - Elements greater than all right
35. **Peak Element** - Greater than neighbors
36. **Local Minimum** - Smaller than neighbors
37. **Next Greater Element** - Using stack
38. **Next Smaller Element** - Using stack

### Inversion & Order Problems
39. **Count Inversions** - Pairs where arr[i] > arr[j], i < j
40. **Reverse Pairs** - arr[i] > 2 * arr[j]
41. **Minimum Swaps to Sort** - Make array sorted
42. **Longest Increasing Subsequence** - Not contiguous
43. **Longest Consecutive Sequence** - In unsorted array

### Partition Problems
44. **Partition Equal Subset Sum** - Split into two equal sums
45. **Partition to K Equal Sum** - K equal subsets
46. **Balanced Partition** - Minimize difference
47. **3-Way Partition** - Elements < x, = x, > x

### Advanced & Mixed
48. **Median of Two Sorted Arrays** - O(log(min(m,n)))
49. **Merge Intervals** - Overlapping intervals
50. **Insert Interval** - Into sorted intervals
51. **Non-overlapping Intervals** - Remove minimum to make non-overlapping
52. **Meeting Rooms** - Can attend all meetings
53. **Maximum Gap** - In sorted form (bucket sort)
54. **Largest Rectangle in Histogram** - Using stack

---

## 💡 Common Techniques Used

### 1. Boyer-Moore Voting (Majority Element)
```java
int majorityElement(int[] arr) {
    int candidate = arr[0], count = 1;
    
    for (int i = 1; i < arr.length; i++) {
        if (arr[i] == candidate) {
            count++;
        } else {
            count--;
            if (count == 0) {
                candidate = arr[i];
                count = 1;
            }
        }
    }
    
    return candidate;
}
```

### 2. Dutch National Flag (3-Way Partition)
```java
void sortColors(int[] arr) {
    int low = 0, mid = 0, high = arr.length - 1;
    
    while (mid <= high) {
        if (arr[mid] == 0) {
            swap(arr, low++, mid++);
        } else if (arr[mid] == 1) {
            mid++;
        } else {
            swap(arr, mid, high--);
        }
    }
}
```

### 3. Cyclic Sort Pattern (Missing Number)
```java
void cyclicSort(int[] arr) {
    int i = 0;
    while (i < arr.length) {
        int correctIndex = arr[i] - 1;
        if (arr[i] > 0 && arr[i] <= arr.length && 
            arr[i] != arr[correctIndex]) {
            swap(arr, i, correctIndex);
        } else {
            i++;
        }
    }
}
```

### 4. Kadane's Algorithm Variant
```java
int maxDifference(int[] arr) {
    int minElement = arr[0];
    int maxDiff = arr[1] - arr[0];
    
    for (int i = 1; i < arr.length; i++) {
        maxDiff = Math.max(maxDiff, arr[i] - minElement);
        minElement = Math.min(minElement, arr[i]);
    }
    
    return maxDiff;
}
```

### 5. Stack for Next Greater Element
```java
int[] nextGreaterElement(int[] arr) {
    int n = arr.length;
    int[] result = new int[n];
    Stack<Integer> stack = new Stack<>();
    
    for (int i = n - 1; i >= 0; i--) {
        while (!stack.isEmpty() && stack.peek() <= arr[i]) {
            stack.pop();
        }
        result[i] = stack.isEmpty() ? -1 : stack.peek();
        stack.push(arr[i]);
    }
    
    return result;
}
```

### 6. Modified Binary Search
```java
int searchRotated(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (arr[mid] == target) return mid;
        
        // Left half sorted
        if (arr[left] <= arr[mid]) {
            if (target >= arr[left] && target < arr[mid])
                right = mid - 1;
            else
                left = mid + 1;
        } else {
            if (target > arr[mid] && target <= arr[right])
                left = mid + 1;
            else
                right = mid - 1;
        }
    }
    
    return -1;
}
```

---

## 🎓 Algorithm Patterns

### Pattern Recognition Guide

**Use HashMap when:**
- Counting frequencies
- Finding pairs/complements
- Tracking seen elements

**Use Sorting when:**
- Need ordered processing
- Find pairs/triplets
- Merge intervals

**Use Stack when:**
- Next greater/smaller element
- Histogram problems
- Balanced operations

**Use Two Pointers when:**
- Sorted array
- Find pairs
- Partition problems

**Use Greedy when:**
- Optimization problems
- Stock buy/sell
- Jump problems
- Meeting rooms

**Use DP when:**
- Optimal substructure
- Overlapping subproblems
- Longest increasing subsequence

---

## 📊 Complexity Insights

### Time Complexity Patterns
```
O(n) Solutions:
- Single pass algorithms
- HashMap-based solutions
- Stack-based solutions
- Kadane's variants

O(n log n) Solutions:
- Sorting-based
- Modified binary search
- Merge sort applications

O(n²) Solutions:
- Nested loops (optimizable)
- Some triplet problems
- Brute force approaches
```

### Space Optimization
```
O(1) Space:
- In-place modifications
- Two pointers
- Cyclic sort
- Dutch flag

O(n) Space:
- HashMap/HashSet
- Stack for NGE
- Auxiliary arrays
```

---

## 🎯 Interview Strategy

### Problem Identification
1. **Read carefully** - Understand constraints
2. **Identify pattern** - Which category?
3. **Consider edge cases** - Empty, single element, duplicates
4. **Think optimization** - Can we do better than brute force?
5. **Discuss trade-offs** - Time vs space

### Common Mistakes to Avoid
1. ❌ Not handling edge cases
2. ❌ Integer overflow (use long)
3. ❌ Off-by-one errors
4. ❌ Not checking array bounds
5. ❌ Modifying array when not allowed
6. ❌ Forgetting to sort when needed

---

## 🏆 Must-Solve Problems

**Essential (Do First):**
1. Two Sum
2. Maximum Subarray (Kadane's)
3. Move Zeros
4. Rotate Array
5. Contains Duplicate
6. Product Except Self
7. Maximum Product Subarray
8. Find Minimum in Rotated Sorted Array
9. Search in Rotated Sorted Array
10. Merge Intervals

**Important (Do Next):**
11. Three Sum
12. Container With Most Water
13. Trapping Rain Water
14. Next Permutation
15. Jump Game
16. Sort Colors
17. Majority Element
18. Missing Number
19. Find All Duplicates
20. First Missing Positive

**Advanced (Challenge Yourself):**
21. Median of Two Sorted Arrays
22. Longest Consecutive Sequence
23. Largest Rectangle in Histogram
24. Maximum Gap
25. Count of Range Sum

---

## 📈 Progress Tracker

### Frequency Problems
- [ ] Majority element
- [ ] Find duplicates
- [ ] Missing number
- [ ] Frequency sort

### Rearrangement
- [ ] Move zeros
- [ ] Sort by parity
- [ ] Dutch flag
- [ ] Positive negative alternate

### Mathematical
- [ ] Product of three
- [ ] Product except self
- [ ] Trapping rain water
- [ ] Stock buy sell

### Pairs & Triplets
- [ ] Two sum
- [ ] Three sum
- [ ] Four sum
- [ ] Pair differences

### Leaders & Peaks
- [ ] Array leaders
- [ ] Peak element
- [ ] Next greater element

### Advanced
- [ ] Median of two sorted
- [ ] Merge intervals
- [ ] Maximum gap
- [ ] Histogram rectangle

---

## 🔗 Related Topics

- [Searching](../Searching/)
- [Sorting](../Sorting/)
- [Two Pointer](../Two-Pointer/)
- [Sliding Window](../Sliding-Window/)
- [Prefix Sum](../Prefix-Sum/)
- [Matrix](../Matrix/)

---

## 💡 Pro Tips

1. **Master the basics first** - Two sum, move zeros, rotate array
2. **Identify patterns** - Many problems use similar techniques
3. **Draw examples** - Visualize the problem
4. **Consider sorting** - Often simplifies the problem
5. **Think in-place** - Can you solve with O(1) space?
6. **Use hashmaps** - For O(n) time solutions
7. **Practice variations** - Each problem has multiple approaches

---

## 🎯 Expected Problems

**Total:** 50-60 problems
- **Easy:** 20-25 problems
- **Medium:** 25-30 problems
- **Hard:** 5-10 problems

---

**Note:** This folder contains diverse array problems that combine multiple concepts. Each problem helps build problem-solving intuition and pattern recognition.

---

**Coming Soon!** 🚀

*Comprehensive solutions for all miscellaneous array problems with multiple approaches and detailed explanations.*

---

**Happy Problem Solving! 🎲**
