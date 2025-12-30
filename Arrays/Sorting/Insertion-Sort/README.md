# Insertion Sort 📝

**Category:** Sorting Algorithms  
**Difficulty:** Basic  
**Time Complexity:** O(n²) worst, O(n) best  
**Space Complexity:** O(1)  
**Stable:** Yes | **In-Place:** Yes | **Adaptive:** Yes

---

## 📋 Overview

Insertion Sort builds the sorted array one element at a time. It picks each element and inserts it into its correct position in the already sorted portion, similar to how you might sort playing cards in your hands.

---

## 🎯 Algorithm Explanation

### Core Concept
```
1. Start with second element (first is already "sorted")
2. Compare with elements in sorted portion
3. Shift larger elements to right
4. Insert current element in correct position
5. Repeat for all elements
```

### Visual Representation (Like Sorting Cards)
```
Cards in hand: [5, 2, 4, 6, 1, 3]

Pick 2: [5, 2, 4, 6, 1, 3]
        [2, 5] sorted, [4, 6, 1, 3] unsorted

Pick 4: [2, 5, 4, 6, 1, 3]
        [2, 4, 5] sorted, [6, 1, 3] unsorted

Pick 6: [2, 4, 5, 6, 1, 3]
        [2, 4, 5, 6] sorted, [1, 3] unsorted

Pick 1: [2, 4, 5, 6, 1, 3]
        [1, 2, 4, 5, 6] sorted, [3] unsorted

Pick 3: [1, 2, 4, 5, 6, 3]
        [1, 2, 3, 4, 5, 6] all sorted!
```

### Detailed Step-by-Step
```
Initial: [5, 2, 4, 6, 1, 3]
         ↑sorted

Pass 1 (key=2):
  [5, 2, 4, 6, 1, 3]  5 > 2, shift 5 right
  [5, 5, 4, 6, 1, 3]  insert 2
  [2, 5, 4, 6, 1, 3]
   ↑-sorted-↑

Pass 2 (key=4):
  [2, 5, 4, 6, 1, 3]  5 > 4, shift 5 right
  [2, 5, 5, 6, 1, 3]  2 < 4, insert 4
  [2, 4, 5, 6, 1, 3]
   ↑---sorted---↑

Pass 3 (key=6):
  [2, 4, 5, 6, 1, 3]  Already in place
   ↑-----sorted-----↑

Pass 4 (key=1):
  [2, 4, 5, 6, 1, 3]  Shift all: 6,5,4,2
  [2, 4, 5, 6, 6, 3]
  [2, 4, 5, 5, 6, 3]
  [2, 4, 4, 5, 6, 3]
  [2, 2, 4, 5, 6, 3]  Insert 1
  [1, 2, 4, 5, 6, 3]
   ↑-------sorted-------↑

Pass 5 (key=3):
  [1, 2, 4, 5, 6, 3]  Shift 6,5,4
  [1, 2, 3, 4, 5, 6]
   ↑---------sorted---------↑
```

---

## 💡 Implementation Patterns

### Pattern 1: Basic Insertion Sort
```java
void insertionSort(int[] arr) {
    int n = arr.length;
    
    for (int i = 1; i < n; i++) {
        int key = arr[i];
        int j = i - 1;
        
        // Shift elements greater than key
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j--;
        }
        
        // Insert key at correct position
        arr[j + 1] = key;
    }
}
```

### Pattern 2: With Swap Operations
```java
void insertionSortWithSwaps(int[] arr) {
    int n = arr.length;
    
    for (int i = 1; i < n; i++) {
        int j = i;
        
        // Swap until correct position
        while (j > 0 && arr[j] < arr[j - 1]) {
            int temp = arr[j];
            arr[j] = arr[j - 1];
            arr[j - 1] = temp;
            j--;
        }
    }
}
```

### Pattern 3: Descending Order
```java
void insertionSortDescending(int[] arr) {
    int n = arr.length;
    
    for (int i = 1; i < n; i++) {
        int key = arr[i];
        int j = i - 1;
        
        // Change condition to arr[j] < key
        while (j >= 0 && arr[j] < key) {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = key;
    }
}
```

### Pattern 4: Recursive Implementation
```java
void insertionSortRecursive(int[] arr, int n) {
    // Base case
    if (n <= 1) return;
    
    // Sort first n-1 elements
    insertionSortRecursive(arr, n - 1);
    
    // Insert nth element at correct position
    int key = arr[n - 1];
    int j = n - 2;
    
    while (j >= 0 && arr[j] > key) {
        arr[j + 1] = arr[j];
        j--;
    }
    arr[j + 1] = key;
}
```

### Pattern 5: Binary Insertion Sort (Optimization)
```java
void binaryInsertionSort(int[] arr) {
    int n = arr.length;
    
    for (int i = 1; i < n; i++) {
        int key = arr[i];
        
        // Find position using binary search
        int pos = binarySearch(arr, key, 0, i - 1);
        
        // Shift elements
        for (int j = i - 1; j >= pos; j--) {
            arr[j + 1] = arr[j];
        }
        arr[pos] = key;
    }
}

int binarySearch(int[] arr, int key, int low, int high) {
    while (low <= high) {
        int mid = low + (high - low) / 2;
        if (arr[mid] == key) return mid + 1;
        else if (arr[mid] < key) low = mid + 1;
        else high = mid - 1;
    }
    return low;
}
```

---

## 📊 Complexity Analysis

### Time Complexity

**Best Case: O(n)**
- Array already sorted
- Only one comparison per element
- No shifts needed

**Average Case: O(n²)**
- Random order
- Approximately n²/4 comparisons and shifts

**Worst Case: O(n²)**
- Array sorted in reverse
- Maximum comparisons: n(n-1)/2
- Maximum shifts: n(n-1)/2

### Space Complexity
- **O(1)** - In-place sorting
- **O(n)** - Recursive version (call stack)

### Adaptive Nature
```
Nearly Sorted Array: [1, 2, 3, 5, 4, 6, 7]
- Only 4 needs to move
- Close to O(n) time
- Best among simple sorts for nearly sorted data
```

---

## 🎓 Characteristics

### ✅ Advantages
1. **Simple to implement** - Easy to understand
2. **Efficient for small datasets** - Low overhead
3. **Adaptive** - O(n) for nearly sorted arrays
4. **Stable** - Maintains relative order
5. **In-place** - O(1) extra space
6. **Online algorithm** - Can sort as data arrives
7. **Better than bubble/selection** - For small or nearly sorted data

### ❌ Disadvantages
1. **O(n²) for large datasets** - Inefficient
2. **Many shifts** - Expensive for large elements
3. **Not suitable for large arrays** - Better alternatives exist
4. **Slow for reverse sorted** - Worst case scenario

---

## 🔍 Problem Categories

### Basic Implementation
1. Sort array ascending
2. Sort array descending
3. Implement from scratch
4. Recursive implementation
5. Count shifts/comparisons

### Optimizations
6. Binary insertion sort
7. Shell sort (gap-based insertion)
8. Early termination for sorted arrays
9. Optimize for specific data types

### Applications
10. Sort nearly sorted array
11. Online sorting (stream of data)
12. Sort linked list using insertion sort
13. Find kth smallest during sorting
14. Maintain sorted array with insertions

### Advanced
15. Count inversions
16. Minimum swaps to sort
17. Sort array by frequency
18. Insertion sort for specific ranges

---

## 💻 Complete Example

```java
public class InsertionSort {
    
    // Basic implementation
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
    
    // With verbose output
    public static void insertionSortVerbose(int[] arr) {
        int n = arr.length;
        
        System.out.println("Initial: " + java.util.Arrays.toString(arr));
        
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            
            System.out.println("
Pass " + i + ": key = " + key);
            
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
                System.out.println("  Shifting: " + java.util.Arrays.toString(arr));
            }
            arr[j + 1] = key;
            System.out.println("  After insertion: " + java.util.Arrays.toString(arr));
        }
    }
    
    // Main method
    public static void main(String[] args) {
        int[] arr = {5, 2, 4, 6, 1, 3};
        
        System.out.println("=== Insertion Sort Demo ===
");
        insertionSortVerbose(arr);
        
        System.out.println("
Final sorted: " + java.util.Arrays.toString(arr));
    }
}
```

**Sample Output:**
```
=== Insertion Sort Demo ===

Initial: [5, 2, 4, 6, 1, 3]

Pass 1: key = 2
  Shifting: [5, 5, 4, 6, 1, 3]
  After insertion: [2, 5, 4, 6, 1, 3]

Pass 2: key = 4
  Shifting: [2, 5, 5, 6, 1, 3]
  After insertion: [2, 4, 5, 6, 1, 3]

Pass 3: key = 6
  After insertion: [2, 4, 5, 6, 1, 3]

Pass 4: key = 1
  Shifting: [2, 4, 5, 6, 6, 3]
  Shifting: [2, 4, 5, 5, 6, 3]
  Shifting: [2, 4, 4, 5, 6, 3]
  Shifting: [2, 2, 4, 5, 6, 3]
  After insertion: [1, 2, 4, 5, 6, 3]

Pass 5: key = 3
  Shifting: [1, 2, 4, 5, 6, 6]
  Shifting: [1, 2, 4, 5, 5, 6]
  Shifting: [1, 2, 4, 4, 5, 6]
  After insertion: [1, 2, 3, 4, 5, 6]

Final sorted: [1, 2, 3, 4, 5, 6]
```

---

## 🚀 Optimization Techniques

### 1. Binary Insertion Sort
- Use binary search to find position
- Reduces comparisons to O(n log n)
- Shifts still O(n²)

### 2. Early Break
```java
// Already in the optimized version
while (j >= 0 && arr[j] > key)
// Breaks as soon as correct position found
```

### 3. Sentinel Optimization
```java
// Place smallest element at beginning
// Eliminates j >= 0 check
```

### 4. Use in Hybrid Algorithms
```java
// TimSort uses insertion sort for small subarrays
if (size < THRESHOLD) {
    insertionSort(arr, low, high);
}
```

---

## ⚠️ Common Mistakes

1. ❌ **Starting from index 0**
   ```java
   // Wrong: first element already sorted
   for (int i = 0; i < n; i++)
   // Correct: start from second element
   for (int i = 1; i < n; i++)
   ```

2. ❌ **Forgetting j >= 0 check**
   ```java
   // Must check bounds
   while (j >= 0 && arr[j] > key)
   ```

3. ❌ **Wrong insertion position**
   ```java
   // Correct position is j + 1, not j
   arr[j + 1] = key;
   ```

4. ❌ **Not preserving key value**
   ```java
   // Must save key before shifting
   int key = arr[i];
   ```

---

## 🎯 When to Use Insertion Sort

### ✅ Perfect For:
- Small datasets (n < 50)
- Nearly sorted arrays
- Online algorithms (data arrives one at a time)
- As part of hybrid algorithms (TimSort, IntroSort)
- When simplicity matters
- Linked list sorting

### ❌ Avoid When:
- Large datasets (n > 1000)
- Completely random/reverse sorted data
- Performance is critical
- Better alternatives available

---

## 📈 Comparison with Other O(n²) Sorts

| Aspect | Insertion | Bubble | Selection |
|--------|-----------|--------|-----------|
| Best Case | O(n) | O(n) | O(n²) |
| Adaptive | Yes | Yes | No |
| Stable | Yes | Yes | No |
| Online | Yes | No | No |
| Comparisons (Best) | n-1 | n-1 | n²/2 |
| Shifts (Worst) | n²/2 | n² swaps | n swaps |
| Real-world Use | Yes (hybrid) | No | Rare |

**Winner for:** Small/nearly sorted data

---

## 💡 Interview Tips

1. **Mention adaptivity** - O(n) for nearly sorted
2. **Compare with others** - Better than bubble/selection
3. **Real-world usage** - Part of TimSort (Python/Java)
4. **Card sorting analogy** - Easy to explain
5. **Online capability** - Sorts as data arrives

**Common Questions:**
- Implement insertion sort
- Why better than bubble sort?
- When to use insertion sort?
- Time complexity for nearly sorted?
- Stable or unstable?

---

## 🔗 Related Topics

- [Bubble Sort](../Bubble-Sort/)
- [Selection Sort](../Selection-Sort/)
- [Shell Sort](../Shell-Sort/)
- [TimSort](../Advanced/)
- [Sorting Main](../)

---

## 📊 Progress Tracker

- [ ] Basic implementation
- [ ] Descending order
- [ ] Recursive version
- [ ] Binary insertion sort
- [ ] Count inversions
- [ ] Online sorting
- [ ] Linked list insertion sort
- [ ] Nearly sorted optimization

---

## 🏆 Practice Problems

1. Implement basic insertion sort
2. Sort in descending order
3. Recursive implementation
4. Binary insertion sort
5. Count shifts required
6. Sort nearly sorted array
7. Online sorting simulation
8. Insertion sort on linked list
9. Shell sort (gap sequence)
10. Count inversions

---

## 🎓 Key Takeaways

1. **Adaptive algorithm** - O(n) for nearly sorted
2. **Stable and in-place** - Good properties
3. **Best simple sort** - For small/nearly sorted data
4. **Online sorting** - Process streaming data
5. **Used in practice** - Part of hybrid algorithms
6. **Easy to implement** - Intuitive like card sorting

---

## 💡 Real-World Usage

**Java's Arrays.sort():**
- Uses insertion sort for small subarrays (< 47 elements)
- Part of Dual-Pivot Quicksort

**Python's Timsort:**
- Uses insertion sort for runs < 64 elements
- Optimized for real-world data

**Why Still Relevant:**
- Low overhead for small data
- Excellent for nearly sorted
- Simple to implement
- Cache-friendly

---

**Note:** This folder will contain insertion sort implementations and applications.

---

**Coming Soon!** 🚀

*Practice problems and optimizations will be added.*

---

**Happy Sorting! 📝**
