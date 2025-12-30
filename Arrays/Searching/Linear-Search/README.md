# Linear Search 🔎

**Category:** Searching Algorithms  
**Difficulty:** Basic to Easy  
**Time Complexity:** O(n)  
**Space Complexity:** O(1)

---

## 📋 Overview

Linear search, also known as sequential search, is the simplest searching algorithm. It checks every element in the array sequentially until the target element is found or the end of the array is reached.

---

## 🎯 Algorithm Explanation

### Basic Concept
```
Start from the first element
Compare each element with target
If match found, return index
If end reached without match, return -1
```

### Pseudocode
```
LinearSearch(arr, n, target):
    for i = 0 to n-1:
        if arr[i] == target:
            return i
    return -1
```

---

## 💡 Implementation Patterns

### Pattern 1: Basic Linear Search
```java
int linearSearch(int[] arr, int n, int target) {
    for (int i = 0; i < n; i++) {
        if (arr[i] == target) {
            return i;
        }
    }
    return -1; // Not found
}
```

### Pattern 2: Search with Boolean Return
```java
boolean isPresent(int[] arr, int n, int target) {
    for (int i = 0; i < n; i++) {
        if (arr[i] == target) {
            return true;
        }
    }
    return false;
}
```

### Pattern 3: Find All Occurrences
```java
List<Integer> findAllOccurrences(int[] arr, int n, int target) {
    List<Integer> indices = new ArrayList<>();
    for (int i = 0; i < n; i++) {
        if (arr[i] == target) {
            indices.add(i);
        }
    }
    return indices;
}
```

### Pattern 4: Count Occurrences
```java
int countOccurrences(int[] arr, int n, int target) {
    int count = 0;
    for (int i = 0; i < n; i++) {
        if (arr[i] == target) {
            count++;
        }
    }
    return count;
}
```

### Pattern 5: Last Occurrence
```java
int lastOccurrence(int[] arr, int n, int target) {
    int lastIndex = -1;
    for (int i = 0; i < n; i++) {
        if (arr[i] == target) {
            lastIndex = i;
        }
    }
    return lastIndex;
}
```

---

## 🔍 Problem Categories

### Basic Search Problems
1. **Search Element** - Find if element exists
2. **Find Index** - Return position of element
3. **Check Existence** - Boolean result
4. **Search in Range** - Limited search space
5. **Search with Condition** - Based on criteria

### Counting Problems
6. **Count Occurrences** - How many times element appears
7. **Count Greater Than** - Elements > target
8. **Count in Range** - Elements in [low, high]
9. **Count Distinct** - Unique elements

### Multiple Occurrence Problems
10. **First Occurrence** - Index of first match
11. **Last Occurrence** - Index of last match
12. **All Occurrences** - All indices
13. **Kth Occurrence** - Find kth match

### Conditional Search
14. **Search Maximum** - Find largest element
15. **Search Minimum** - Find smallest element
16. **Search Second Largest** - Second max element
17. **Search with Property** - Even, odd, prime, etc.

### Advanced Linear Search
18. **Search in Unsorted Array**
19. **Search in Nearly Sorted Array**
20. **Sentinel Linear Search** - Optimization
21. **Recursive Linear Search**
22. **Search in String/Character Array**

---

## 📊 Complexity Analysis

### Time Complexity
- **Best Case:** O(1) - Element at first position
- **Average Case:** O(n) - Element in middle
- **Worst Case:** O(n) - Element at end or not present

### Space Complexity
- **Iterative:** O(1) - No extra space
- **Recursive:** O(n) - Call stack space

---

## 🎓 Advantages vs Disadvantages

### ✅ Advantages
1. Simple to implement
2. Works on unsorted arrays
3. No preprocessing required
4. Good for small datasets
5. Works with linked lists
6. No extra space needed

### ❌ Disadvantages
1. Inefficient for large datasets
2. O(n) time complexity
3. No optimization possible
4. Slower than binary search for sorted arrays
5. Checks every element

---

## 💻 Variations & Optimizations

### 1. Sentinel Linear Search
```java
int sentinelSearch(int[] arr, int n, int target) {
    int last = arr[n - 1];
    arr[n - 1] = target;
    
    int i = 0;
    while (arr[i] != target) {
        i++;
    }
    
    arr[n - 1] = last;
    
    if (i < n - 1 || arr[n - 1] == target) {
        return i;
    }
    return -1;
}
```

### 2. Recursive Linear Search
```java
int recursiveSearch(int[] arr, int index, int n, int target) {
    if (index >= n) return -1;
    if (arr[index] == target) return index;
    return recursiveSearch(arr, index + 1, n, target);
}
```

### 3. Search from Both Ends
```java
int bidirectionalSearch(int[] arr, int n, int target) {
    int left = 0, right = n - 1;
    
    while (left <= right) {
        if (arr[left] == target) return left;
        if (arr[right] == target) return right;
        left++;
        right--;
    }
    return -1;
}
```

---

## 🚀 Example Problems

### Problem 1: Find Element Position
```java
// Input: arr[] = {10, 20, 30, 40}, target = 30
// Output: 2
int findPosition(int[] arr, int target) {
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] == target) return i;
    }
    return -1;
}
```

### Problem 2: Check Element Exists
```java
// Input: arr[] = {5, 8, 3, 9, 2}, target = 9
// Output: true
boolean exists(int[] arr, int target) {
    for (int num : arr) {
        if (num == target) return true;
    }
    return false;
}
```

### Problem 3: Find Maximum
```java
// Input: arr[] = {3, 7, 2, 9, 4}
// Output: 9
int findMax(int[] arr) {
    int max = arr[0];
    for (int i = 1; i < arr.length; i++) {
        if (arr[i] > max) max = arr[i];
    }
    return max;
}
```

---

## ⚠️ Common Mistakes

1. ❌ **Not checking array bounds**
   ```java
   // Wrong
   for (int i = 0; i <= arr.length; i++)
   // Correct
   for (int i = 0; i < arr.length; i++)
   ```

2. ❌ **Forgetting to return -1**
   ```java
   // Always handle "not found" case
   return -1;
   ```

3. ❌ **Not handling empty array**
   ```java
   if (arr == null || arr.length == 0) return -1;
   ```

4. ❌ **Modifying array during search**
   ```java
   // Don't change array while searching
   ```

---

## 🎯 When to Use Linear Search

### Perfect For:
- ✅ Small datasets (n < 100)
- ✅ Unsorted arrays
- ✅ Single search operation
- ✅ Linked lists (can't use binary search)
- ✅ Simple implementation needed

### Avoid When:
- ❌ Large datasets
- ❌ Multiple searches on same data
- ❌ Sorted array available (use binary search)
- ❌ Performance critical

---

## 🔗 Related Concepts

- [Binary Search](../Binary-Search/) - O(log n) for sorted arrays
- [Jump Search](../../Advanced-Searching/) - O(√n) for sorted arrays
- [Hash Tables](../../Hashing/) - O(1) average case
- [Array Traversal](../../Traversal/)

---

## 📈 Practice Roadmap

```
Basic Search (Find element)
    ↓
Boolean Search (Exists/Not)
    ↓
Count Occurrences
    ↓
First/Last Occurrence
    ↓
Multiple Occurrences
    ↓
Conditional Search
    ↓
Search with Modifications
```

---

## 🎯 Expected Problems

**Total:** 15-20 problems  
**Basic:** 8-10 problems (Simple search, count)  
**Easy:** 7-10 problems (Multiple occurrences, conditional)  

---

## 📊 Progress Tracker

- [ ] Basic element search
- [ ] Boolean existence check
- [ ] Count occurrences
- [ ] Find first/last occurrence
- [ ] Find all occurrences
- [ ] Search maximum/minimum
- [ ] Conditional search
- [ ] Recursive implementation
- [ ] Sentinel search optimization

---

## 🏆 Key Takeaways

1. **Simplicity** - Easiest search algorithm
2. **Flexibility** - Works on any array
3. **O(n) Time** - Linear time complexity
4. **No Preprocessing** - Direct search
5. **Foundation** - Understanding for advanced algorithms

---

## 💡 Pro Tips

1. Use enhanced for loop for readability when index not needed
2. Return early when element found
3. Consider sentinel search for slight optimization
4. Use binary search if array is sorted
5. Test with empty array, single element, element at boundaries

---

**Note:** This folder will contain 15-20 linear search problems with detailed solutions, multiple approaches, and complexity analysis.

---

**Coming Soon!** 🚀

*Solutions will be added as problems are documented.*

---

**Happy Searching! 🔎**
