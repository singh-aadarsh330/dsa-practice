# Counting Sort 🔢

**Category:** Sorting Algorithms - Non-Comparison Based  
**Difficulty:** Medium  
**Time Complexity:** O(n + k)  
**Space Complexity:** O(k)  
**Stable:** Yes | **In-Place:** No

---

## 📋 Overview

Counting Sort is a non-comparison based sorting algorithm that works by counting the number of objects having distinct key values, then using arithmetic to determine the positions of each key in the output sequence. It's extremely efficient when the range of input data (k) is not significantly greater than the number of elements (n).

---

## 🎯 Algorithm Explanation

### Core Concept
```
1. Find range: min and max elements
2. Create count array of size (max - min + 1)
3. Count occurrences of each element
4. Calculate cumulative count (positions)
5. Build output array using counts
6. Copy back to original array
```

### Visual Representation
```
Input: [2, 5, 3, 0, 2, 3, 0, 3]
Range: 0 to 5

Step 1: Count occurrences
Index:  [0, 1, 2, 3, 4, 5]
Count:  [2, 0, 2, 3, 0, 1]
         ↑     ↑  ↑     ↑
         2 0s  2 2s  0 4s
               3 3s  1 5

Step 2: Cumulative count
Index:  [0, 1, 2, 3, 4, 5]
Count:  [2, 2, 4, 7, 7, 8]
         ↑     ↑  ↑     ↑
         0s end at 2
               2s end at 4
                  3s end at 7
                     5s end at 8

Step 3: Place elements
Output: [0, 0, 2, 2, 3, 3, 3, 5]
```

---

## 💡 Implementation Patterns

### Pattern 1: Basic Counting Sort (0 to k)
```java
void countingSort(int[] arr) {
    int n = arr.length;
    if (n == 0) return;
    
    // Find maximum element
    int max = arr[0];
    for (int num : arr) {
        if (num > max) max = num;
    }
    
    // Create count array
    int[] count = new int[max + 1];
    
    // Count occurrences
    for (int num : arr) {
        count[num]++;
    }
    
    // Reconstruct array
    int index = 0;
    for (int i = 0; i <= max; i++) {
        while (count[i] > 0) {
            arr[index++] = i;
            count[i]--;
        }
    }
}
```

### Pattern 2: Stable Counting Sort (Handles Negative)
```java
void countingSortStable(int[] arr) {
    int n = arr.length;
    if (n == 0) return;
    
    // Find min and max
    int min = arr[0], max = arr[0];
    for (int num : arr) {
        if (num < min) min = num;
        if (num > max) max = num;
    }
    
    int range = max - min + 1;
    int[] count = new int[range];
    int[] output = new int[n];
    
    // Count occurrences
    for (int num : arr) {
        count[num - min]++;
    }
    
    // Cumulative count
    for (int i = 1; i < range; i++) {
        count[i] += count[i - 1];
    }
    
    // Build output (traverse from end for stability)
    for (int i = n - 1; i >= 0; i--) {
        int num = arr[i];
        int pos = count[num - min] - 1;
        output[pos] = num;
        count[num - min]--;
    }
    
    // Copy back
    System.arraycopy(output, 0, arr, 0, n);
}
```

### Pattern 3: Counting Sort for Specific Range
```java
void countingSortRange(int[] arr, int min, int max) {
    int n = arr.length;
    int range = max - min + 1;
    int[] count = new int[range];
    
    // Count
    for (int num : arr) {
        count[num - min]++;
    }
    
    // Reconstruct
    int index = 0;
    for (int i = 0; i < range; i++) {
        while (count[i] > 0) {
            arr[index++] = i + min;
            count[i]--;
        }
    }
}
```

### Pattern 4: Counting Sort for Objects
```java
class Student {
    String name;
    int score;
    
    Student(String name, int score) {
        this.name = name;
        this.score = score;
    }
}

void countingSortObjects(Student[] students, int maxScore) {
    int n = students.length;
    int[] count = new int[maxScore + 1];
    Student[] output = new Student[n];
    
    // Count by score
    for (Student s : students) {
        count[s.score]++;
    }
    
    // Cumulative
    for (int i = 1; i <= maxScore; i++) {
        count[i] += count[i - 1];
    }
    
    // Build output (stable)
    for (int i = n - 1; i >= 0; i--) {
        int score = students[i].score;
        output[count[score] - 1] = students[i];
        count[score]--;
    }
    
    // Copy back
    System.arraycopy(output, 0, students, 0, n);
}
```

---

## 📊 Complexity Analysis

### Time Complexity
- **All Cases: O(n + k)**
  - n = number of elements
  - k = range of input (max - min + 1)
- Counting: O(n)
- Cumulative sum: O(k)
- Output building: O(n)

### Space Complexity
- **O(k)** for count array
- **O(n)** for output array (stable version)
- Total: O(n + k)

### When Efficient?
```
Efficient: k ≈ O(n) or k << n²
Example: Sort 1 million numbers in range 0-1000
         O(n + k) = O(1,000,000 + 1,000) ≈ O(n)

Inefficient: k >> n
Example: Sort 100 numbers in range 0-1,000,000
         O(n + k) = O(100 + 1,000,000) ≈ O(k)
         Wastes space for sparse data
```

---

## 🎓 Characteristics

### ✅ Advantages
1. **Linear time** - O(n + k), not O(n log n)
2. **Stable** - Maintains relative order
3. **Simple** - Easy to implement
4. **Predictable** - No best/worst case variation
5. **Fast for small ranges** - When k is small
6. **Used in radix sort** - As subroutine

### ❌ Disadvantages
1. **Space intensive** - Requires O(k) space
2. **Only for integers** - Or discrete values
3. **Range dependent** - Poor if k is large
4. **Not in-place** - Needs extra arrays
5. **Not comparison-based** - Can't use general comparators

---

## 🔍 Problem Categories

### Basic Applications
1. Sort array of integers (small range)
2. Sort array with negative numbers
3. Count frequencies
4. Find duplicates/missing numbers
5. Stable sorting required

### Advanced Applications
6. Sort students by scores
7. Sort by multiple keys (radix sort foundation)
8. Histogram generation
9. Bucket allocation
10. Range queries after sorting

### Interview Problems
11. Sort colors (0, 1, 2) - Dutch National Flag
12. Sort array by frequency
13. H-Index calculation
14. Maximum gap (bucket sort variant)
15. First missing positive

---

## 💻 Complete Example

```java
public class CountingSort {
    
    // Basic counting sort
    public static void countingSort(int[] arr) {
        if (arr.length == 0) return;
        
        int max = arr[0];
        for (int num : arr) {
            if (num > max) max = num;
        }
        
        int[] count = new int[max + 1];
        
        for (int num : arr) {
            count[num]++;
        }
        
        int index = 0;
        for (int i = 0; i <= max; i++) {
            while (count[i] > 0) {
                arr[index++] = i;
                count[i]--;
            }
        }
    }
    
    // Stable version with negative numbers
    public static void countingSortStable(int[] arr) {
        if (arr.length == 0) return;
        
        int min = arr[0], max = arr[0];
        for (int num : arr) {
            if (num < min) min = num;
            if (num > max) max = num;
        }
        
        int range = max - min + 1;
        int[] count = new int[range];
        int[] output = new int[arr.length];
        
        for (int num : arr) {
            count[num - min]++;
        }
        
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }
        
        for (int i = arr.length - 1; i >= 0; i--) {
            int num = arr[i];
            output[count[num - min] - 1] = num;
            count[num - min]--;
        }
        
        System.arraycopy(output, 0, arr, 0, arr.length);
    }
    
    public static void main(String[] args) {
        int[] arr = {4, 2, 2, 8, 3, 3, 1};
        
        System.out.println("Original: " + java.util.Arrays.toString(arr));
        countingSort(arr);
        System.out.println("Sorted: " + java.util.Arrays.toString(arr));
        
        int[] arr2 = {-5, -10, 0, -3, 8, 5, -1, 10};
        System.out.println("
With negatives: " + java.util.Arrays.toString(arr2));
        countingSortStable(arr2);
        System.out.println("Sorted: " + java.util.Arrays.toString(arr2));
    }
}
```

---

## ⚠️ Common Mistakes

1. ❌ **Not handling negatives**
   ```java
   // Must adjust index: count[num - min]
   ```

2. ❌ **Wrong range calculation**
   ```java
   // Correct: max - min + 1
   int range = max - min + 1;
   ```

3. ❌ **Backward iteration for stability**
   ```java
   // Must iterate from end for stability
   for (int i = n - 1; i >= 0; i--)
   ```

4. ❌ **Large range memory**
   ```java
   // Check if k is reasonable
   if (range > n * n) use_different_sort();
   ```

---

## 🎯 When to Use Counting Sort

### ✅ Perfect For:
- Small integer range (k ≈ n)
- Stability required
- Need O(n) time
- Discrete values
- Ages, scores, grades (limited range)

### ❌ Avoid When:
- Large range (k >> n)
- Floating-point numbers
- Arbitrary objects
- Memory limited
- Sparse data

---

## 🔗 Related Topics

- [Radix Sort](../Radix-Sort/) - Uses counting sort
- [Bucket Sort](../Bucket-Sort/) - Similar concept
- [Dutch National Flag](../../Problems/) - Special case
- [Sorting Main](../)

---

## 📊 Progress Tracker

- [ ] Basic counting sort
- [ ] Handle negative numbers
- [ ] Stable version
- [ ] Sort objects by key
- [ ] Optimize space
- [ ] Dutch flag problem
- [ ] Radix sort foundation

---

## 🏆 Key Takeaways

1. **Non-comparison sort** - Can beat O(n log n)
2. **Linear time** - When k is small
3. **Stable** - Important property
4. **Space trade-off** - Speed for memory
5. **Foundation for radix sort** - Building block
6. **Integer/discrete only** - Limited applicability

---

**Coming Soon!** 🚀

*More counting sort problems and applications.*

---

**Happy Sorting! 🔢**
