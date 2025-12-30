# Binary Search 🎯

**Category:** Searching Algorithms  
**Difficulty:** Easy to Hard  
**Time Complexity:** O(log n)  
**Space Complexity:** O(1) iterative, O(log n) recursive

---

## 📋 Overview

Binary Search is an efficient algorithm for finding an element in a **sorted array**. It repeatedly divides the search space in half, making it significantly faster than linear search for large datasets.

---

## 🎯 Algorithm Explanation

### Core Concept
```
1. Start with left = 0, right = n-1
2. While left <= right:
   a. Find middle: mid = left + (right - left) / 2
   b. If arr[mid] == target: return mid
   c. If arr[mid] < target: search right half (left = mid + 1)
   d. If arr[mid] > target: search left half (right = mid - 1)
3. If not found: return -1
```

### Visual Representation
```
Array: [1, 3, 5, 7, 9, 11, 13, 15, 17]
Target: 11

Step 1: left=0, right=8, mid=4
        [1, 3, 5, 7, 9, 11, 13, 15, 17]
                    ^
        arr[4]=9 < 11, search right

Step 2: left=5, right=8, mid=6
        [1, 3, 5, 7, 9, 11, 13, 15, 17]
                           ^
        arr[6]=13 > 11, search left

Step 3: left=5, right=5, mid=5
        [1, 3, 5, 7, 9, 11, 13, 15, 17]
                       ^
        arr[5]=11 == 11, FOUND!
```

---

## 💡 Implementation Patterns

### Pattern 1: Classic Binary Search (Iterative)
```java
int binarySearch(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return -1;
}
```

### Pattern 2: Recursive Binary Search
```java
int binarySearchRecursive(int[] arr, int left, int right, int target) {
    if (left > right) return -1;
    
    int mid = left + (right - left) / 2;
    
    if (arr[mid] == target) return mid;
    if (arr[mid] < target) 
        return binarySearchRecursive(arr, mid + 1, right, target);
    return binarySearchRecursive(arr, left, mid - 1, target);
}
```

### Pattern 3: First Occurrence (Lower Bound)
```java
int firstOccurrence(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    int result = -1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (arr[mid] == target) {
            result = mid;
            right = mid - 1; // Continue searching left
        } else if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return result;
}
```

### Pattern 4: Last Occurrence (Upper Bound)
```java
int lastOccurrence(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    int result = -1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (arr[mid] == target) {
            result = mid;
            left = mid + 1; // Continue searching right
        } else if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return result;
}
```

### Pattern 5: Count Occurrences
```java
int countOccurrences(int[] arr, int target) {
    int first = firstOccurrence(arr, target);
    if (first == -1) return 0;
    
    int last = lastOccurrence(arr, target);
    return last - first + 1;
}
```

---

## 🔍 Problem Categories

### Basic Binary Search
1. **Classic Binary Search** - Find element
2. **First Occurrence** - Leftmost position
3. **Last Occurrence** - Rightmost position
4. **Count Occurrences** - Using first and last
5. **Search Insert Position** - Where to insert element

### Bound Problems
6. **Lower Bound** - First element ≥ target
7. **Upper Bound** - First element > target
8. **Floor and Ceiling** - Closest smaller and larger
9. **Find Range** - Start and end positions

### Rotated Array
10. **Search in Rotated Sorted Array**
11. **Find Minimum in Rotated Array**
12. **Find Rotation Count**
13. **Search in Rotated with Duplicates**

### Peak Finding
14. **Find Peak Element** - arr[i] > arr[i-1] and arr[i] > arr[i+1]
15. **Find Bitonic Peak**
16. **Peak in 2D Matrix**

### Nearly Sorted Array
17. **Search in Almost Sorted** - Elements at i, i-1, or i+1
18. **Find Missing Element**
19. **Find Extra Element**

### Mathematical Applications
20. **Square Root** - Integer square root
21. **Nth Root** - Find nth root of number
22. **Perfect Square** - Check if perfect square
23. **Find Kth Root**

### Optimization Problems
24. **Allocate Minimum Pages** - Book allocation
25. **Aggressive Cows** - Maximum minimum distance
26. **Painter's Partition** - Minimize maximum time
27. **Split Array Largest Sum**
28. **Minimize Maximum Distance**

### Matrix Search
29. **Search in Row-Wise Sorted Matrix**
30. **Search in Row-Column Sorted Matrix**
31. **Median in Row-Wise Sorted Matrix**
32. **Kth Smallest in Sorted Matrix**

### Advanced Applications
33. **Find Transition Point** - 0s to 1s
34. **Find Fixed Point** - arr[i] = i
35. **Maximum in Bitonic Array**
36. **Median of Two Sorted Arrays**

---

## 📊 Complexity Analysis

### Time Complexity
- **Best Case:** O(1) - Element at middle
- **Average Case:** O(log n)
- **Worst Case:** O(log n)

### Space Complexity
- **Iterative:** O(1)
- **Recursive:** O(log n) - Call stack

### Comparison with Linear Search
```
n = 1,000,000
Linear Search: 1,000,000 comparisons (worst)
Binary Search: 20 comparisons (worst)
```

---

## 🎓 Key Concepts

### 1. Prerequisites
- **Must be sorted** (ascending or descending)
- Random access (arrays, not linked lists)
- Modification not needed during search

### 2. Mid Calculation
```java
// Avoid overflow
// Wrong: mid = (left + right) / 2
// Correct: mid = left + (right - left) / 2
```

### 3. Boundary Conditions
```java
// Inclusive boundaries
while (left <= right)

// Exclusive boundaries
while (left < right)
```

### 4. Search Space Reduction
- Each iteration eliminates half the array
- O(log n) iterations maximum
- Logarithmic time complexity

---

## 🚀 Advanced Templates

### Template: Lower Bound (First ≥ target)
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

### Template: Upper Bound (First > target)
```java
int upperBound(int[] arr, int target) {
    int left = 0, right = arr.length;
    
    while (left < right) {
        int mid = left + (right - left) / 2;
        if (arr[mid] <= target) left = mid + 1;
        else right = mid;
    }
    return left;
}
```

### Template: Search in Rotated Array
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
        }
        // Right half sorted
        else {
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

## ⚠️ Common Mistakes

1. ❌ **Integer Overflow**
   ```java
   // Wrong: may overflow
   int mid = (left + right) / 2;
   // Correct
   int mid = left + (right - left) / 2;
   ```

2. ❌ **Infinite Loop**
   ```java
   // Can cause infinite loop with left < right
   left = mid; // Wrong
   left = mid + 1; // Correct
   ```

3. ❌ **Wrong Boundary**
   ```java
   // For finding first occurrence
   right = mid; // Correct
   right = mid - 1; // Wrong (might miss answer)
   ```

4. ❌ **Unsorted Array**
   ```java
   // Always verify array is sorted
   ```

5. ❌ **Not Handling Duplicates**
   ```java
   // Special care needed for duplicate elements
   ```

---

## 🎯 When to Use Binary Search

### Perfect For:
- ✅ Sorted array
- ✅ Large datasets (> 100 elements)
- ✅ Multiple searches
- ✅ Need O(log n) performance
- ✅ Search space can be halved

### Consider Alternatives When:
- ❌ Unsorted array (use linear search or sort first)
- ❌ Linked list (can't random access)
- ❌ Very small array (linear might be faster)
- ❌ Frequent insertions/deletions

---

## 💡 Pro Tips

1. **Always check if sorted** before applying
2. **Use iterative** for better space complexity
3. **Handle edge cases:** empty array, single element
4. **Test boundaries:** first, last, middle elements
5. **Overflow prevention:** Use left + (right - left) / 2
6. **Understand invariants:** What's guaranteed after each iteration

---

## 📈 Learning Path

```
Classic Binary Search
    ↓
First/Last Occurrence
    ↓
Lower/Upper Bounds
    ↓
Count Occurrences
    ↓
Rotated Array Problems
    ↓
Peak Finding
    ↓
Mathematical Applications
    ↓
Optimization Problems
    ↓
2D Matrix Search
    ↓
Advanced Applications
```

---

## 🎯 Expected Problems

**Total:** 40-50 problems  
**Easy:** 15-20 problems (Basic BS, First/Last)  
**Medium:** 20-25 problems (Rotated, Peak, Bounds)  
**Hard:** 5-8 problems (Optimization, Advanced)  

---

## 📊 Progress Tracker

- [ ] Classic binary search
- [ ] First and last occurrence
- [ ] Count occurrences
- [ ] Lower and upper bounds
- [ ] Search insert position
- [ ] Search in rotated array
- [ ] Find peak element
- [ ] Square root using BS
- [ ] Book allocation problem
- [ ] Search in 2D matrix
- [ ] Median of sorted arrays

---

## 🏆 Must-Solve Problems

1. Binary Search (Classic)
2. First and Last Position
3. Search Insert Position
4. Find Peak Element
5. Search in Rotated Sorted Array
6. Find Minimum in Rotated Array
7. Square Root
8. Allocate Minimum Pages
9. Aggressive Cows
10. Median of Two Sorted Arrays

---

## 🔗 Related Topics

- [Linear Search](../Linear-Search/)
- [Sorting Algorithms](../../Sorting/)
- [Two Pointer](../../Two-Pointer/)
- [Divide and Conquer](../../Advanced/)

---

**Note:** This folder will contain 40-50 binary search problems covering all variations and applications.

---

**Coming Soon!** 🚀

*Comprehensive solutions with multiple approaches and detailed explanations.*

---

**Happy Binary Searching! 🎯**
