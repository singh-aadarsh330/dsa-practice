# 🔀 Sorting

[![Problems](https://img.shields.io/badge/Problems-1-blue?style=flat-square)](.)
[![Difficulty](https://img.shields.io/badge/Difficulty-Easy%20to%20Hard-orange?style=flat-square)](.)
[![Language](https://img.shields.io/badge/Language-Java-red?style=flat-square)](.)

> Master sorting algorithms for linked lists. Learn merge sort, insertion sort, and special sorting techniques optimized for linked structures.

---

## 📋 Overview

**Sorting** linked lists requires different techniques than array sorting due to the sequential nature of linked structures. While we can't use quick random access, we can leverage efficient pointer manipulation and stable sorting algorithms that work well with linked lists.

**Key Features:**
- **Merge Sort** - O(n log n) time, O(log n) space
- **Insertion Sort** - O(n²) time, O(1) space, good for small/nearly sorted lists
- **Special Techniques** - Sorting with constraints and patterns
- **In-Place Operations** - Minimal extra space usage
- **Stable Sorting** - Maintains relative order of equal elements

**When Sorting is Needed:**
- ✅ Organizing data in linked structures
- ✅ Preparing for binary search operations
- ✅ Removing duplicates efficiently
- ✅ Finding median or kth element
- ✅ Merging multiple sorted lists
- ❌ When array sorting would be simpler
- ❌ When random access is frequently needed

---

## 🔑 Linked List Sorting Basics

### Why Sorting Linked Lists is Different

```
Arrays:
- Random access: O(1)
- Quick Sort works well
- In-place sorting easy
- Cache-friendly

Linked Lists:
- Sequential access: O(n)
- Merge Sort preferred
- In-place more complex
- Cache-unfriendly

Key Insight: Merge Sort is ideal for linked lists
because it doesn't need random access and can
be implemented efficiently with pointer manipulation.
```

### Common Sorting Algorithms for Linked Lists

```java
1. Merge Sort (Best for Linked Lists)
   Time: O(n log n)
   Space: O(log n) recursion
   Stable: Yes
   Best for: General purpose

2. Insertion Sort
   Time: O(n²)
   Space: O(1)
   Stable: Yes
   Best for: Small or nearly sorted lists

3. Quick Sort (Adapted)
   Time: O(n log n) average, O(n²) worst
   Space: O(log n)
   Stable: No
   Best for: When randomization possible

4. Bubble Sort (Rarely Used)
   Time: O(n²)
   Space: O(1)
   Stable: Yes
   Best for: Educational purposes only
```

### Merge Sort for Linked Lists

```java
public ListNode mergeSort(ListNode head) {
    // Base case
    if (head == null || head.next == null) {
        return head;
    }
    
    // Find middle
    ListNode mid = findMiddle(head);
    ListNode left = head;
    ListNode right = mid.next;
    mid.next = null;  // Split
    
    // Recursive sort
    left = mergeSort(left);
    right = mergeSort(right);
    
    // Merge
    return merge(left, right);
}

private ListNode findMiddle(ListNode head) {
    ListNode slow = head, fast = head.next;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    return slow;
}

private ListNode merge(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode tail = dummy;
    
    while (l1 != null && l2 != null) {
        if (l1.val <= l2.val) {
            tail.next = l1;
            l1 = l1.next;
        } else {
            tail.next = l2;
            l2 = l2.next;
        }
        tail = tail.next;
    }
    
    tail.next = (l1 != null) ? l1 : l2;
    return dummy.next;
}
```

---

## 📂 Repository Structure

```
Sorting/
│
├── SortAlternatingAscendingDescending/    # Sort alternating ordered list
│   └── README.md
│
└── README.md                               # This file
```

---

## 📝 Problems in This Folder

| # | Problem | Folder | Difficulty | Key Concepts | Status |
|---|---------|--------|------------|--------------|--------|
| 1 | Sort Alternating Ascending-Descending | [SortAlternatingAscendingDescending/](./SortAlternatingAscendingDescending/) | Medium | Split, Reverse, Merge | ✅ |

---

## 🎯 Problem: Sort Alternating Ascending-Descending

### Problem Overview

Given a linked list where elements alternate between ascending and descending order, sort the entire list in ascending order. This requires splitting the list into two sublists, reversing one, and merging them.

**Key Concepts:**
- List splitting by position
- In-place list reversal
- Merging two sorted lists
- Multi-step algorithm design

**See detailed solution:** [SortAlternatingAscendingDescending/README.md](./SortAlternatingAscendingDescending/README.md)

---

## 🔑 Key Sorting Concepts

### 1. Sorting Algorithm Comparison

| Algorithm | Time (Avg) | Time (Worst) | Space | Stable | Best Use Case |
|-----------|-----------|--------------|-------|--------|---------------|
| **Merge Sort** | O(n log n) | O(n log n) | O(log n) | Yes | General purpose |
| **Insertion Sort** | O(n²) | O(n²) | O(1) | Yes | Small/nearly sorted |
| **Quick Sort** | O(n log n) | O(n²) | O(log n) | No | Arrays (not ideal for LL) |
| **Bubble Sort** | O(n²) | O(n²) | O(1) | Yes | Educational only |
| **Selection Sort** | O(n²) | O(n²) | O(1) | No | Never use for LL |

**Merge Sort** is the clear winner for linked lists.

### 2. Merge Sort Deep Dive

**Why Merge Sort for Linked Lists?**
```
1. No random access needed
   - Only sequential traversal required
   - Perfect for linked list structure

2. Natural fit for divide-and-conquer
   - Split by finding middle
   - Merge by pointer manipulation

3. Stable sorting
   - Preserves relative order
   - Important for many applications

4. Predictable performance
   - Always O(n log n)
   - No worst-case O(n²) like Quick Sort
```

**Merge Sort Visualization:**
```
Original: 4 → 2 → 1 → 3

Split:
    4 → 2        1 → 3
    
Split again:
    4    2      1    3
    
Merge pairs:
    2 → 4        1 → 3
    
Merge final:
    1 → 2 → 3 → 4
```

### 3. Finding Middle of List (Key Operation)

```java
// Fast-slow pointer technique
private ListNode findMiddle(ListNode head) {
    if (head == null) return null;
    
    ListNode slow = head;
    ListNode fast = head.next;  // Start one ahead for split
    
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    
    return slow;  // This is the middle
}

// Why fast starts at head.next?
// For split: we want slow at end of first half
// Example: 1 → 2 → 3 → 4
// slow ends at 2, perfect split point
```

### 4. Stable vs Unstable Sorting

```java
// Stable: Equal elements maintain original order
Input:  (3,A) → (1,X) → (3,B) → (2,Y)
Sorted: (1,X) → (2,Y) → (3,A) → (3,B)
                              ↑      ↑
                              Order preserved

// Unstable: Equal elements may change order
Input:  (3,A) → (1,X) → (3,B) → (2,Y)
Sorted: (1,X) → (2,Y) → (3,B) → (3,A)
                              ↑      ↑
                              Order changed

// When stability matters:
// - Sorting records with multiple fields
// - Preserving insertion order
// - Multiple sort passes
```

---

## 💡 Common Sorting Patterns

### Pattern 1: Standard Merge Sort
```java
public ListNode sortList(ListNode head) {
    if (head == null || head.next == null) {
        return head;
    }
    
    ListNode mid = findMiddle(head);
    ListNode right = mid.next;
    mid.next = null;
    
    ListNode left = sortList(head);
    right = sortList(right);
    
    return merge(left, right);
}
```

### Pattern 2: Insertion Sort
```java
public ListNode insertionSort(ListNode head) {
    ListNode dummy = new ListNode(0);
    ListNode current = head;
    
    while (current != null) {
        ListNode next = current.next;
        
        // Find insertion position
        ListNode prev = dummy;
        while (prev.next != null && prev.next.val < current.val) {
            prev = prev.next;
        }
        
        // Insert current
        current.next = prev.next;
        prev.next = current;
        
        current = next;
    }
    
    return dummy.next;
}
```

### Pattern 3: Sort with Custom Comparator
```java
public ListNode sortWithComparator(ListNode head, 
                                   Comparator<ListNode> comp) {
    // Convert to array for custom sorting
    List<ListNode> nodes = new ArrayList<>();
    while (head != null) {
        nodes.add(head);
        head = head.next;
    }
    
    // Sort with comparator
    nodes.sort(comp);
    
    // Rebuild list
    for (int i = 0; i < nodes.size() - 1; i++) {
        nodes.get(i).next = nodes.get(i + 1);
    }
    if (!nodes.isEmpty()) {
        nodes.get(nodes.size() - 1).next = null;
    }
    
    return nodes.isEmpty() ? null : nodes.get(0);
}
```

### Pattern 4: Bottom-Up Merge Sort (Iterative)
```java
public ListNode bottomUpMergeSort(ListNode head) {
    if (head == null || head.next == null) {
        return head;
    }
    
    // Count nodes
    int length = 0;
    ListNode temp = head;
    while (temp != null) {
        length++;
        temp = temp.next;
    }
    
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    
    // Merge in increasing sizes
    for (int size = 1; size < length; size *= 2) {
        ListNode tail = dummy;
        ListNode curr = dummy.next;
        
        while (curr != null) {
            ListNode left = curr;
            ListNode right = split(left, size);
            curr = split(right, size);
            tail = merge(tail, left, right);
        }
    }
    
    return dummy.next;
}
```

### Pattern 5: Sort by Multiple Criteria
```java
public ListNode sortByMultipleCriteria(ListNode head) {
    // Sort by primary criteria first
    head = sortByPrimary(head);
    
    // Then sort by secondary (stable sort)
    head = sortBySecondary(head);
    
    return head;
}
```

---

## ⚠️ Common Mistakes

### 1. Not Disconnecting After Split
```java
❌ Incorrect:
ListNode mid = findMiddle(head);
ListNode right = mid.next;
// Forgot to disconnect!
ListNode left = sortList(head);  // Still connected

✅ Correct:
ListNode mid = findMiddle(head);
ListNode right = mid.next;
mid.next = null;  // Disconnect!
ListNode left = sortList(head);
```

### 2. Wrong Middle Finding for Split
```java
❌ Incorrect:
ListNode slow = head, fast = head;
// This gives wrong middle for split

✅ Correct:
ListNode slow = head, fast = head.next;
// Start fast one ahead for proper split
```

### 3. Not Handling Single/Empty List
```java
❌ Incorrect:
public ListNode sortList(ListNode head) {
    ListNode mid = findMiddle(head);  // NPE if head null!
    // ...
}

✅ Correct:
public ListNode sortList(ListNode head) {
    if (head == null || head.next == null) {
        return head;
    }
    // ...
}
```

### 4. Losing Last Node in Merge
```java
❌ Incorrect:
while (l1 != null && l2 != null) {
    // merge logic
}
// Forgot to attach remaining!

✅ Correct:
while (l1 != null && l2 != null) {
    // merge logic
}
tail.next = (l1 != null) ? l1 : l2;
```

### 5. Infinite Recursion
```java
❌ Incorrect:
if (head.next == null) return head;
ListNode mid = findMiddle(head);
// If mid is head, infinite loop!

✅ Correct:
if (head == null || head.next == null) return head;
// Proper base case prevents infinite recursion
```

---

## 🎓 Sorting Best Practices

### 1. Always Use Merge Sort for General Cases
```java
// Default choice for linked list sorting
public ListNode sortList(ListNode head) {
    return mergeSort(head);
}
```

### 2. Consider Insertion Sort for Small Lists
```java
// Use insertion sort for lists < 10 nodes
if (length < 10) {
    return insertionSort(head);
} else {
    return mergeSort(head);
}
```

### 3. Handle Edge Cases First
```java
if (head == null || head.next == null) {
    return head;
}
```

### 4. Use Dummy Nodes for Cleaner Code
```java
ListNode dummy = new ListNode(0);
dummy.next = head;
// Simplifies edge cases in merge
```

### 5. Test with Various Inputs
```java
// Test with:
- Empty list
- Single node
- Two nodes (same/different values)
- Already sorted
- Reverse sorted
- All equal values
- Random order
```

---

## 📚 Topics to be Added

As I continue practicing, this folder will include:

### **Basic Sorting**
- Sort List (standard merge sort)
- Insertion Sort List
- Sort by absolute values
- Sort 0s, 1s, and 2s

### **Advanced Sorting**
- Merge K sorted lists
- Sort with custom comparator
- Sort by frequency
- Sort colors (Dutch National Flag)

### **Special Techniques**
- Bottom-up merge sort
- Natural merge sort
- Radix sort for lists
- Bucket sort adaptation

### **Optimization Problems**
- Sort with minimum swaps
- Sort in specific order
- Partial sorting
- K closest elements

---

## 🎯 Learning Path

### Beginner Level
1. **Understand Merge Algorithm** - Merge two sorted lists
2. **Find Middle** - Fast-slow pointer technique
3. **Basic Merge Sort** - Recursive implementation

### Intermediate Level
4. **Insertion Sort** - O(n²) but O(1) space
5. **Sort with Constraints** - Alternating patterns ← Current
6. **Bottom-Up Merge Sort** - Iterative approach

### Advanced Level
7. **Merge K Lists** - Priority queue approach
8. **Custom Comparators** - Complex sorting criteria
9. **Optimization** - Hybrid approaches

---

## 💻 Essential Sorting Operations

### Find Middle
```java
ListNode findMiddle(ListNode head)
```

### Merge Two Sorted Lists
```java
ListNode merge(ListNode l1, ListNode l2)
```

### Merge Sort
```java
ListNode mergeSort(ListNode head)
```

### Insertion Sort
```java
ListNode insertionSort(ListNode head)
```

### Is Sorted
```java
boolean isSorted(ListNode head)
```

---

## 🔄 Sorting vs Other Operations

### When to Use What?

**Use Merge Sort when:**
- General purpose sorting needed
- Stability required
- Predictable O(n log n) performance
- List of any size

**Use Insertion Sort when:**
- List is small (< 10 nodes)
- List is nearly sorted
- Need O(1) space
- Building sorted list incrementally

**Use Quick Sort when:**
- Working with arrays (not linked lists)
- Average case sufficient
- In-place sorting critical

**Don't Sort when:**
- Already sorted
- Only need min/max
- Counting or bucketing sufficient

---

## 📊 Progress Tracker

**Current Status:**
- Topics Covered: 1 (Alternating Sort)
- Total Problems: 1
- Difficulty: Medium
- Concepts: Split, Reverse, Merge

**Next Milestones:**
- [ ] Add standard merge sort problem
- [ ] Add insertion sort implementation
- [ ] Add merge K sorted lists
- [ ] Add custom comparator sorting
- [ ] Add 5+ sorting problems

---

## 🎓 Interview Tips

### Common Sorting Interview Questions

1. **"Why use merge sort for linked lists?"**
   - No random access needed
   - Stable O(n log n) performance
   - Natural fit for sequential access
   - Efficient pointer manipulation

2. **"Merge sort vs quick sort for linked lists?"**
   - Merge sort: Better for linked lists
   - Quick sort: Better for arrays
   - Reason: Merge sort doesn't need random access
   - Quick sort partition is awkward for linked lists

3. **"How to optimize merge sort?"**
   - Use insertion sort for small sublists
   - Bottom-up to avoid recursion
   - Natural merge sort for partially sorted data

4. **"Space complexity of merge sort?"**
   - Recursive: O(log n) for call stack
   - Iterative: O(1) space possible
   - No extra nodes created

### What Interviewers Look For

- ✅ Understanding of merge sort
- ✅ Proper middle finding technique
- ✅ Clean merge implementation
- ✅ Handling edge cases
- ✅ Time/space complexity analysis
- ✅ Stability awareness

---

## 📖 Additional Resources

### Documentation
- [Merge Sort Algorithm](https://en.wikipedia.org/wiki/Merge_sort)
- [Linked List Sorting](https://www.geeksforgeeks.org/merge-sort-for-linked-list/)

### Visualizations
- [Sorting Visualizer](https://www.toptal.com/developers/sorting-algorithms)
- [VisuAlgo - Sorting](https://visualgo.net/en/sorting)

### Practice Platforms
- [LeetCode - Sort List](https://leetcode.com/problems/sort-list/)
- [LeetCode - Insertion Sort List](https://leetcode.com/problems/insertion-sort-list/)
- [LeetCode - Merge K Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/)

---

## 💡 Remember

> "Merge sort is to linked lists what quick sort is to arrays - the natural choice."

**Key Takeaways:**
- Merge sort is optimal for linked lists
- Finding middle efficiently is crucial
- Stability matters in many applications
- O(1) space possible with iteration
- Always handle edge cases first

---

<div align="center">

**Master Sorting, Master Linked Lists! 🔀**

*Divide, Conquer, and Merge*

*Last Updated: December 2024*

[⬆ Back to Linked List](../README.md)

</div>
