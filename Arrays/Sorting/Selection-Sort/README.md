# Selection Sort 🔍

**Category:** Sorting Algorithms  
**Difficulty:** Basic  
**Time Complexity:** O(n²)  
**Space Complexity:** O(1)  
**Stable:** No | **In-Place:** Yes

---

## 📋 Overview

Selection Sort is a simple comparison-based sorting algorithm. It divides the array into sorted and unsorted portions, repeatedly selecting the minimum element from the unsorted portion and placing it at the end of the sorted portion.

---

## 🎯 Algorithm Explanation

### Core Concept
```
For each position in the array:
1. Find the minimum element in unsorted portion
2. Swap it with the first element of unsorted portion
3. Move boundary of sorted portion one position right
4. Repeat until array is sorted
```

### Visual Representation
```
Initial: [64, 25, 12, 22, 11]
         ↑ unsorted portion

Pass 1: Find min (11), swap with 64
        [11, 25, 12, 22, 64]
         ↑sorted | unsorted→

Pass 2: Find min (12), swap with 25
        [11, 12, 25, 22, 64]
         ↑←sorted | unsorted→

Pass 3: Find min (22), swap with 25
        [11, 12, 22, 25, 64]
         ↑←sorted | unsorted→

Pass 4: Find min (25), no swap needed
        [11, 12, 22, 25, 64]
         ↑←sorted | unsorted→

Pass 5: Only one element left, sorted!
        [11, 12, 22, 25, 64]
         ↑←←←← all sorted ←←←
```

---

## 💡 Implementation Patterns

### Pattern 1: Basic Selection Sort
```java
void selectionSort(int[] arr) {
    int n = arr.length;
    
    // Move boundary of unsorted portion
    for (int i = 0; i < n - 1; i++) {
        // Find minimum element in unsorted array
        int minIndex = i;
        for (int j = i + 1; j < n; j++) {
            if (arr[j] < arr[minIndex]) {
                minIndex = j;
            }
        }
        
        // Swap minimum element with first element
        int temp = arr[minIndex];
        arr[minIndex] = arr[i];
        arr[i] = temp;
    }
}
```

### Pattern 2: Descending Order
```java
void selectionSortDescending(int[] arr) {
    int n = arr.length;
    
    for (int i = 0; i < n - 1; i++) {
        // Find maximum element
        int maxIndex = i;
        for (int j = i + 1; j < n; j++) {
            if (arr[j] > arr[maxIndex]) {
                maxIndex = j;
            }
        }
        
        // Swap
        int temp = arr[maxIndex];
        arr[maxIndex] = arr[i];
        arr[i] = temp;
    }
}
```

### Pattern 3: Optimized (Skip Unnecessary Swaps)
```java
void selectionSortOptimized(int[] arr) {
    int n = arr.length;
    
    for (int i = 0; i < n - 1; i++) {
        int minIndex = i;
        for (int j = i + 1; j < n; j++) {
            if (arr[j] < arr[minIndex]) {
                minIndex = j;
            }
        }
        
        // Only swap if needed
        if (minIndex != i) {
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }
}
```

### Pattern 4: Recursive Selection Sort
```java
void selectionSortRecursive(int[] arr, int start, int n) {
    // Base case
    if (start >= n - 1) return;
    
    // Find minimum in remaining array
    int minIndex = start;
    for (int i = start + 1; i < n; i++) {
        if (arr[i] < arr[minIndex]) {
            minIndex = i;
        }
    }
    
    // Swap
    int temp = arr[minIndex];
    arr[minIndex] = arr[start];
    arr[start] = temp;
    
    // Recurse for remaining array
    selectionSortRecursive(arr, start + 1, n);
}
```

### Pattern 5: Count Swaps
```java
int selectionSortCountSwaps(int[] arr) {
    int n = arr.length;
    int swapCount = 0;
    
    for (int i = 0; i < n - 1; i++) {
        int minIndex = i;
        for (int j = i + 1; j < n; j++) {
            if (arr[j] < arr[minIndex]) {
                minIndex = j;
            }
        }
        
        if (minIndex != i) {
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
            swapCount++;
        }
    }
    
    return swapCount;
}
```

---

## 📊 Complexity Analysis

### Time Complexity

**Best Case: O(n²)**
- Even if array is sorted, all comparisons are made
- No optimization possible for comparisons

**Average Case: O(n²)**
- Random order array
- (n-1) + (n-2) + ... + 1 comparisons

**Worst Case: O(n²)**
- Same as best and average
- Always makes n(n-1)/2 comparisons

### Space Complexity
- **O(1)** - Only temporary variable for swapping
- In-place sorting
- No recursion stack (iterative version)

### Number of Operations
```
Comparisons: n(n-1)/2 ≈ O(n²) - Always, regardless of input
Swaps: O(n) - At most n-1 swaps (minimum among comparison sorts)
Passes: n-1
```

---

## 🎓 Characteristics

### ✅ Advantages
1. **Simple to implement** - Easy to understand
2. **In-place sorting** - O(1) extra space
3. **Minimum swaps** - At most n-1 swaps (best among O(n²) sorts)
4. **Good when swaps are expensive** - Minimizes write operations
5. **Predictable** - Always O(n²), no best/worst case variation
6. **Works well with small lists**

### ❌ Disadvantages
1. **Always O(n²)** - No optimization for sorted arrays
2. **Not stable** - May change relative order of equal elements
3. **Not adaptive** - Doesn't benefit from partially sorted data
4. **Many comparisons** - Even for sorted arrays
5. **Poor performance** - Slower than advanced algorithms
6. **Not suitable for large datasets**

---

## 🔍 Stability Issue

Selection sort is **NOT stable** by default.

**Example:**
```
Input:  [4a, 3, 4b, 2]
Output: [2, 3, 4b, 4a]  ← Order of 4a and 4b changed!
```

**Why?**
When we find minimum and swap, we may skip over equal elements.

**Making it Stable:**
```java
void stableSelectionSort(int[] arr) {
    int n = arr.length;
    
    for (int i = 0; i < n - 1; i++) {
        int minIndex = i;
        for (int j = i + 1; j < n; j++) {
            if (arr[j] < arr[minIndex]) {
                minIndex = j;
            }
        }
        
        // Instead of swapping, shift elements
        int key = arr[minIndex];
        while (minIndex > i) {
            arr[minIndex] = arr[minIndex - 1];
            minIndex--;
        }
        arr[i] = key;
    }
}
```
**Cost:** More operations, but maintains stability

---

## 🔍 Problem Categories

### Basic Implementation
1. Sort array ascending
2. Sort array descending
3. Implement from scratch
4. Recursive implementation
5. Count swaps needed

### Variations
6. Selection sort for specific range
7. Find kth smallest using selection sort
8. Stable selection sort
9. Selection sort with custom comparator
10. Bidirectional selection sort

### Applications
11. Minimum swaps to sort
12. Selection sort on linked list
13. Sort array by frequency
14. Find smallest k elements
15. Optimize for expensive swaps

---

## 💻 Complete Example

```java
public class SelectionSort {
    
    // Basic implementation
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            
            // Find minimum element
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            
            // Swap minimum with first unsorted element
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }
    
    // With step-by-step output
    public static void selectionSortVerbose(int[] arr) {
        int n = arr.length;
        
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            
            // Swap
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
            
            System.out.println("After pass " + (i + 1) + ":");
            printArray(arr);
        }
    }
    
    // Print array
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    // Main method
    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        
        System.out.println("Original array:");
        printArray(arr);
        System.out.println();
        
        selectionSortVerbose(arr);
        
        System.out.println("
Final sorted array:");
        printArray(arr);
    }
}
```

**Output:**
```
Original array:
64 25 12 22 11 

After pass 1:
11 25 12 22 64 
After pass 2:
11 12 25 22 64 
After pass 3:
11 12 22 25 64 
After pass 4:
11 12 22 25 64 

Final sorted array:
11 12 22 25 64
```

---

## 🚀 Optimization Techniques

### 1. Skip Unnecessary Swaps
```java
if (minIndex != i) {
    swap(arr, i, minIndex);
}
```

### 2. Bidirectional Selection Sort
```java
void bidirectionalSelectionSort(int[] arr) {
    int left = 0, right = arr.length - 1;
    
    while (left < right) {
        int minIndex = left, maxIndex = left;
        
        // Find min and max in one pass
        for (int i = left; i <= right; i++) {
            if (arr[i] < arr[minIndex]) minIndex = i;
            if (arr[i] > arr[maxIndex]) maxIndex = i;
        }
        
        // Swap min to left
        swap(arr, left, minIndex);
        
        // If max was at left, it's now at minIndex
        if (maxIndex == left) maxIndex = minIndex;
        
        // Swap max to right
        swap(arr, right, maxIndex);
        
        left++;
        right--;
    }
}
```

---

## ⚠️ Common Mistakes

1. ❌ **Wrong loop bounds**
   ```java
   // Wrong: will cause out of bounds
   for (int i = 0; i < n; i++)
   // Correct
   for (int i = 0; i < n - 1; i++)
   ```

2. ❌ **Not updating minIndex correctly**
   ```java
   // Must start from i, not 0
   int minIndex = i;
   ```

3. ❌ **Swapping in inner loop**
   ```java
   // Wrong: swap in inner loop
   // Correct: swap after finding minimum
   ```

4. ❌ **Assuming stability**
   ```java
   // Selection sort is NOT stable
   // Need modifications for stability
   ```

---

## 🎯 When to Use Selection Sort

### ✅ Good For:
- Small datasets (n < 20)
- Memory writes are expensive
- Need minimum number of swaps
- Auxiliary memory not available
- Simple implementation needed

### ❌ Avoid When:
- Large datasets
- Stability required
- Sorted/nearly sorted data (no advantage)
- Performance critical applications

---

## 📈 Comparison with Other Sorts

| Aspect | Selection | Bubble | Insertion |
|--------|-----------|--------|-----------|
| Time (All) | O(n²) | O(n²) | O(n²) |
| Best Case | O(n²) | O(n) | O(n) |
| Swaps | O(n) | O(n²) | O(n²) |
| Stable | No | Yes | Yes |
| Adaptive | No | Yes | Yes |
| Comparisons | Always n²/2 | Up to n²/2 | Up to n²/2 |

**Key Difference:** Selection sort makes **minimum swaps** (O(n))

---

## 💡 Interview Tips

1. **Know the complexity** - Always O(n²)
2. **Mention minimum swaps** - Only n-1 swaps needed
3. **Discuss stability** - Not stable by default
4. **Compare with bubble/insertion** - Good when swaps expensive
5. **Real-world usage** - Rarely used in production

**Common Questions:**
- Why is it called selection sort?
- What's the advantage over bubble sort?
- Is selection sort stable?
- When to use selection sort?
- Minimum swaps to sort array

---

## 🎓 Key Insights

### Why Minimum Swaps?
- Each pass places exactly one element in final position
- Total swaps = number of elements out of place ≤ n-1

### Why Not Stable?
- Long-distance swaps can skip over equal elements
- Example: Swapping minimum from end to beginning

### Why Always O(n²)?
- Must scan entire unsorted portion each time
- No early termination possible

---

## 🔗 Related Topics

- [Bubble Sort](../Bubble-Sort/)
- [Insertion Sort](../Insertion-Sort/)
- [Heap Sort](../Heap-Sort/) (improved selection)
- [Sorting Main](../)

---

## 📊 Progress Tracker

- [ ] Basic implementation
- [ ] Descending order
- [ ] Recursive version
- [ ] Count swaps
- [ ] Stable selection sort
- [ ] Bidirectional optimization
- [ ] Custom comparator
- [ ] Find kth smallest

---

## 🏆 Practice Problems

1. Implement basic selection sort
2. Sort in descending order
3. Count minimum swaps needed
4. Make selection sort stable
5. Recursive implementation
6. Find kth smallest element
7. Bidirectional selection sort
8. Selection sort on linked list

---

## 🎯 Key Takeaways

1. **Simple but inefficient** - O(n²) always
2. **Minimum swaps** - At most n-1 (best property)
3. **Not stable** - Can change order of equals
4. **Not adaptive** - No benefit from sorted data
5. **In-place** - O(1) extra space
6. **Use when swaps expensive** - Main advantage

---

**Note:** This folder will contain selection sort implementations and related problems.

---

**Coming Soon!** 🚀

*Practice problems and detailed solutions will be added.*

---

**Happy Sorting! 🔍**
