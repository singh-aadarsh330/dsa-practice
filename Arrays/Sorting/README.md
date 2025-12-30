# Sorting Algorithms 📊

**Category:** Arrays - Core Algorithms  
**Difficulty:** Basic to Hard  
**Topics:** Comparison Sorting, Non-Comparison Sorting, Stability, In-Place Sorting

---

## 📋 Overview

Sorting is one of the most fundamental operations in computer science. This section covers various sorting algorithms, their implementations, time/space complexities, and when to use each algorithm. Mastering these algorithms is crucial for interviews and efficient problem-solving.

---

## 📁 Folder Structure

```
Sorting/
├── Bubble-Sort/
├── Selection-Sort/
├── Insertion-Sort/
├── Counting-Sort/
├── Merge-Sort/ (coming soon)
├── Quick-Sort/ (coming soon)
├── Heap-Sort/ (coming soon)
└── README.md
```

---

## 🎯 Algorithm Categories

### Simple Sorting Algorithms (O(n²))
- **Bubble Sort** - Adjacent element swapping
- **Selection Sort** - Select minimum and place
- **Insertion Sort** - Insert into sorted portion

### Efficient Sorting Algorithms (O(n log n))
- **Merge Sort** - Divide and conquer
- **Quick Sort** - Partition-based sorting
- **Heap Sort** - Binary heap-based

### Non-Comparison Sorting (Linear Time)
- **Counting Sort** - Frequency-based sorting
- **Radix Sort** - Digit-by-digit sorting
- **Bucket Sort** - Distribution-based sorting

### Specialized Sorting
- **Tim Sort** - Hybrid (Merge + Insertion)
- **Shell Sort** - Gap-based insertion sort
- **Comb Sort** - Improved bubble sort

---

## 📊 Comprehensive Comparison Table

| Algorithm | Best | Average | Worst | Space | Stable | In-Place | Notes |
|-----------|------|---------|-------|-------|--------|----------|-------|
| Bubble Sort | O(n) | O(n²) | O(n²) | O(1) | Yes | Yes | Simple, rarely used |
| Selection Sort | O(n²) | O(n²) | O(n²) | O(1) | No | Yes | Minimum comparisons |
| Insertion Sort | O(n) | O(n²) | O(n²) | O(1) | Yes | Yes | Good for small/nearly sorted |
| Merge Sort | O(n log n) | O(n log n) | O(n log n) | O(n) | Yes | No | Guaranteed O(n log n) |
| Quick Sort | O(n log n) | O(n log n) | O(n²) | O(log n) | No | Yes | Fast in practice |
| Heap Sort | O(n log n) | O(n log n) | O(n log n) | O(1) | No | Yes | No extra space |
| Counting Sort | O(n+k) | O(n+k) | O(n+k) | O(k) | Yes | No | k = range of input |
| Radix Sort | O(d(n+k)) | O(d(n+k)) | O(d(n+k)) | O(n+k) | Yes | No | d = number of digits |
| Bucket Sort | O(n+k) | O(n+k) | O(n²) | O(n) | Yes | No | Uniform distribution |

---

## 💡 Key Concepts

### 1. Stability
**Stable:** Preserves relative order of equal elements
- Bubble, Insertion, Merge, Counting, Radix
- Important for: Multiple-key sorting

**Unstable:** May change relative order
- Selection, Quick, Heap
- Can be made stable with extra effort

### 2. In-Place vs Out-of-Place
**In-Place:** O(1) extra space (or O(log n) for recursion)
- Bubble, Selection, Insertion, Quick, Heap

**Out-of-Place:** Requires O(n) extra space
- Merge, Counting, Radix, Bucket

### 3. Comparison-Based vs Non-Comparison
**Comparison-Based:** Compare elements
- Lower bound: O(n log n)
- Bubble, Selection, Insertion, Merge, Quick, Heap

**Non-Comparison:** Use properties of data
- Can achieve O(n) time
- Counting, Radix, Bucket

### 4. Adaptive vs Non-Adaptive
**Adaptive:** Takes advantage of existing order
- Insertion, Bubble (optimized)
- Faster on nearly sorted data

**Non-Adaptive:** Same performance regardless
- Selection, Merge (standard), Heap

---

## 🔍 When to Use Which Algorithm

### Bubble Sort 🫧
**Use When:**
- Teaching purposes
- Very small datasets (n < 10)
- Need simplest implementation
- Array nearly sorted

**Avoid When:**
- Any production code
- Large datasets
- Performance matters

### Selection Sort 🔍
**Use When:**
- Memory writes are costly
- Minimizing swaps important
- Small datasets
- Simple implementation needed

**Avoid When:**
- Need stability
- Large datasets
- Better alternatives available

### Insertion Sort 📝
**Use When:**
- Small datasets (n < 50)
- Array nearly sorted
- Online sorting (elements arrive one by one)
- As part of hybrid algorithms (TimSort)

**Avoid When:**
- Large unsorted datasets
- O(n²) is unacceptable

### Counting Sort 🔢
**Use When:**
- Range of elements is small (k ≈ n)
- Need O(n) time
- Stability required
- Integer sorting

**Avoid When:**
- Large range (k >> n)
- Floating-point numbers
- Memory limited

### Merge Sort 🔀
**Use When:**
- Need guaranteed O(n log n)
- Stability required
- Linked list sorting
- External sorting (large data)

**Avoid When:**
- Space is limited (O(n) extra)
- In-place required
- Quick sort sufficient

### Quick Sort ⚡
**Use When:**
- Average O(n log n) acceptable
- In-place sorting needed
- Cache performance matters
- General-purpose sorting

**Avoid When:**
- Worst case O(n²) unacceptable
- Stability required
- Guaranteed O(n log n) needed

### Heap Sort 🏔️
**Use When:**
- O(n log n) + O(1) space needed
- Worst case matters
- Priority queue available

**Avoid When:**
- Cache performance critical
- Stability required
- Quick/Merge available

---

## 🎓 Problem Categories

### Basic Sorting
1. Sort array ascending/descending
2. Sort with custom comparator
3. Sort specific data types
4. Implement sorting algorithm

### Sorting Applications
5. Kth largest/smallest element
6. Sort by frequency
7. Sort by custom criteria
8. Meeting room problems
9. Merge intervals

### Advanced Problems
10. Count inversions
11. Minimum swaps to sort
12. Sort nearly sorted array
13. Sort array by absolute difference
14. Pancake sorting

### Optimization Problems
15. Maximum product of three numbers
16. Minimum difference pairs
17. Closest pair sum
18. Triplet problems with sorting

### Special Sorting
19. Sort 0s, 1s, and 2s (Dutch flag)
20. Wave array
21. Rearrange array alternately
22. Sort by parity

---

## 💻 Key Takeaways

### Time Complexity Rules
```
Simple Sorts: O(n²) - Avoid for large data
Efficient Sorts: O(n log n) - General purpose
Linear Sorts: O(n) - Special conditions
```

### Space Complexity Rules
```
In-Place: O(1) - Selection, Bubble, Insertion, Heap
Recursive: O(log n) - Quick Sort
Out-of-Place: O(n) - Merge, Counting, Radix
```

### Practical Guidelines
1. **Small arrays (n < 50):** Insertion Sort
2. **Nearly sorted:** Insertion Sort
3. **General purpose:** Quick Sort or built-in sort
4. **Stability needed:** Merge Sort or Counting Sort
5. **Limited space:** Heap Sort
6. **Integer range known:** Counting Sort
7. **Linked lists:** Merge Sort

---

## 🚀 Optimization Techniques

### 1. Hybrid Approaches
```java
// Use insertion sort for small subarrays
if (right - left < 10) {
    insertionSort(arr, left, right);
    return;
}
quickSort(arr, left, right);
```

### 2. Early Termination
```java
// Bubble sort optimization
boolean swapped = false;
// If no swaps, array is sorted
if (!swapped) break;
```

### 3. Randomization
```java
// Quick sort pivot selection
// Use random pivot to avoid worst case
int pivotIndex = left + random.nextInt(right - left + 1);
```

### 4. Three-Way Partitioning
```java
// Quick sort with many duplicates
// Partition into <, =, > pivot
```

---

## 📈 Learning Path

```
Understanding Complexity
    ↓
Bubble Sort (Basics)
    ↓
Selection Sort (Min/Max finding)
    ↓
Insertion Sort (Incremental)
    ↓
Merge Sort (Divide & Conquer)
    ↓
Quick Sort (Partitioning)
    ↓
Heap Sort (Heap structure)
    ↓
Counting Sort (Non-comparison)
    ↓
Radix & Bucket Sort
    ↓
Sorting Applications
    ↓
Advanced Problems
```

---

## 🎯 Expected Problems

**Total:** 50-60 problems  
**Basic:** 15-20 problems (Implement algorithms)  
**Easy:** 15-20 problems (Simple applications)  
**Medium:** 15-20 problems (Sorting + logic)  
**Hard:** 5-8 problems (Advanced applications)  

---

## ⚠️ Common Mistakes

1. ❌ Using O(n²) for large arrays
2. ❌ Not considering stability
3. ❌ Ignoring space constraints
4. ❌ Forgetting edge cases (empty, single element)
5. ❌ Not handling duplicates properly
6. ❌ Wrong pivot selection in Quick Sort
7. ❌ Not optimizing for nearly sorted data

---

## 🔗 Related Topics

- [Searching Algorithms](../Searching/)
- [Two Pointer Technique](../Two-Pointer/)
- [Divide and Conquer](../Advanced/)
- [Priority Queues/Heaps](../Heaps/)

---

## 📊 Progress Tracking

### Basic Algorithms
- [ ] Bubble Sort implementation
- [ ] Selection Sort implementation
- [ ] Insertion Sort implementation
- [ ] Counting Sort implementation

### Advanced Algorithms
- [ ] Merge Sort
- [ ] Quick Sort
- [ ] Heap Sort
- [ ] Radix Sort

### Applications
- [ ] Kth largest/smallest
- [ ] Sort by frequency
- [ ] Merge intervals
- [ ] Count inversions

---

## 🏆 Interview Favorites

1. **Implement Quick Sort** - Understanding partitioning
2. **Merge K Sorted Arrays** - Using merge sort concept
3. **Sort Colors (0,1,2)** - Dutch National Flag
4. **Kth Largest Element** - QuickSelect or Heap
5. **Meeting Rooms** - Interval sorting
6. **Merge Intervals** - Sorting + merging
7. **Count Inversions** - Modified merge sort
8. **Maximum Gap** - Bucket sort application

---

## 💡 Pro Tips

1. **Use built-in sort** for interviews unless asked to implement
2. **Know complexity** of each algorithm
3. **Consider stability** if order matters
4. **Test edge cases:** Empty, single element, duplicates, sorted
5. **Optimize for specific inputs:** Nearly sorted → Insertion
6. **Practice implementation:** At least Quick and Merge Sort
7. **Understand applications:** When to use sorting

---

**Note:** Each subfolder contains detailed implementations, variations, and applications of specific sorting algorithms.

---

**Coming Soon!** 🚀

*Comprehensive solutions for all sorting algorithms and related problems.*

---

**Happy Sorting! 📊**
