# 📋 ArrayList

[![Problems](https://img.shields.io/badge/Problems-1-blue?style=flat-square)](.)
[![Difficulty](https://img.shields.io/badge/Difficulty-Medium-orange?style=flat-square)](.)
[![Language](https://img.shields.io/badge/Language-Java-red?style=flat-square)](.)

> Dynamic array implementation in Java. Master ArrayList operations, methods, and applications for flexible data management.

---

## 📋 Overview

**ArrayList** is a dynamic, resizable array implementation in Java that's part of the Collections framework. Unlike arrays, ArrayLists can grow and shrink dynamically as needed.

**Key Features:**
- **Dynamic Size** - Automatically resizes as elements are added/removed
- **Random Access** - O(1) access time using index
- **Type-Safe** - Uses generics for compile-time type checking
- **Built-in Methods** - Rich set of utility methods

**When to Use ArrayList:**
- ✅ When size is unknown or changes frequently
- ✅ Need frequent random access by index
- ✅ Insertions/deletions at end are common
- ❌ When frequent insertions in middle (use LinkedList)
- ❌ When fixed size is sufficient (use array)

---

## 🔑 ArrayList Basics

### Declaration and Initialization

```java
// Declaration
ArrayList<Integer> list = new ArrayList<>();

// With initial capacity
ArrayList<Integer> list = new ArrayList<>(10);

// From existing collection
ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));

// Different types
ArrayList<String> names = new ArrayList<>();
ArrayList<Double> prices = new ArrayList<>();
ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();  // 2D ArrayList
```

### Common Operations

```java
// Adding elements
list.add(5);              // Add at end: O(1) amortized
list.add(0, 10);          // Add at index: O(n)

// Accessing elements
int val = list.get(0);    // Get by index: O(1)
int size = list.size();   // Get size: O(1)

// Updating elements
list.set(0, 20);          // Update at index: O(1)

// Removing elements
list.remove(0);           // Remove by index: O(n)
list.remove(Integer.valueOf(5));  // Remove by value: O(n)

// Checking
boolean exists = list.contains(5);     // O(n)
boolean empty = list.isEmpty();        // O(1)

// Clearing
list.clear();             // Remove all: O(n)
```

### Iteration Methods

```java
// For-each loop
for (int num : list) {
    System.out.println(num);
}

// Traditional for loop
for (int i = 0; i < list.size(); i++) {
    System.out.println(list.get(i));
}

// Iterator
Iterator<Integer> it = list.iterator();
while (it.hasNext()) {
    System.out.println(it.next());
}

// Java 8+ forEach
list.forEach(num -> System.out.println(num));
```

---

## 📂 Repository Structure

```
ArrayList/
│
├── MinimumVerticalSum/      # Calculate minimum column sums
│   └── README.md
│
└── README.md                # This file
```

---

## 📝 Problems in This Folder

| # | Problem | Folder | Difficulty | Key Concepts | Status |
|---|---------|--------|------------|--------------|--------|
| 1 | Minimum Vertical Sum | [MinimumVerticalSum/](./MinimumVerticalSum/) | Medium | 2D ArrayList, Column operations | ✅ |

---

## 🎯 Problem: Minimum Vertical Sum

### Problem Overview

Given a 2D ArrayList (matrix), find the column with the **minimum sum** and return that sum.

**Example:**
```
Matrix:
[1, 2, 3]
[4, 5, 6]
[7, 8, 9]

Column sums:
Col 0: 1 + 4 + 7 = 12
Col 1: 2 + 5 + 8 = 15
Col 2: 3 + 6 + 9 = 18

Minimum: 12
```

**Key Concepts:**
- 2D ArrayList traversal
- Column-wise operations
- Sum calculation and comparison
- Working with nested ArrayLists

**See detailed solution:** [MinimumVerticalSum/README.md](./MinimumVerticalSum/README.md)

---

## 🔑 Key ArrayList Concepts

### 1. ArrayList vs Array

| Feature | Array | ArrayList |
|---------|-------|-----------|
| **Size** | Fixed | Dynamic |
| **Type** | Primitive or Object | Only Objects |
| **Performance** | Faster | Slightly slower (boxing) |
| **Methods** | Limited | Rich API |
| **Syntax** | `int[] arr` | `ArrayList<Integer> list` |

### 2. Internal Working

**How ArrayList Grows:**
```
Initial capacity: 10
When full: Creates new array of size (oldSize * 3/2 + 1)
Copies all elements to new array
```

**Time Complexities:**
```
add(E e):           O(1) amortized
add(index, E):      O(n)
get(index):         O(1)
remove(index):      O(n)
contains(E):        O(n)
```

### 3. 2D ArrayList

```java
// Creating 2D ArrayList
ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();

// Adding rows
for (int i = 0; i < rows; i++) {
    ArrayList<Integer> row = new ArrayList<>();
    for (int j = 0; j < cols; j++) {
        row.add(value);
    }
    matrix.add(row);
}

// Accessing elements
int val = matrix.get(row).get(col);

// Size
int rows = matrix.size();
int cols = matrix.get(0).size();  // Assuming non-empty
```

---

## 💡 Common ArrayList Patterns

### Pattern 1: Row-wise Traversal
```java
for (int i = 0; i < matrix.size(); i++) {
    for (int j = 0; j < matrix.get(i).size(); j++) {
        int element = matrix.get(i).get(j);
        // Process element
    }
}
```

### Pattern 2: Column-wise Traversal
```java
int rows = matrix.size();
int cols = matrix.get(0).size();

for (int j = 0; j < cols; j++) {          // For each column
    for (int i = 0; i < rows; i++) {      // For each row
        int element = matrix.get(i).get(j);
        // Process element
    }
}
```

### Pattern 3: Sum of Row
```java
int rowSum = 0;
for (int j = 0; j < matrix.get(i).size(); j++) {
    rowSum += matrix.get(i).get(j);
}
```

### Pattern 4: Sum of Column
```java
int colSum = 0;
for (int i = 0; i < matrix.size(); i++) {
    colSum += matrix.get(i).get(col);
}
```

### Pattern 5: Finding Min/Max
```java
int min = Integer.MAX_VALUE;
for (ArrayList<Integer> row : matrix) {
    for (int num : row) {
        min = Math.min(min, num);
    }
}
```

---

## ⚠️ Common Mistakes

### 1. Index Out of Bounds
```java
❌ Incorrect:
ArrayList<Integer> list = new ArrayList<>();
int val = list.get(0);        // IndexOutOfBoundsException!

✅ Correct:
if (!list.isEmpty()) {
    int val = list.get(0);
}
```

### 2. Comparing with ==
```java
❌ Incorrect:
Integer a = list.get(0);
Integer b = list.get(1);
if (a == b)                   // Compares references!

✅ Correct:
if (a.equals(b))              // Compares values
```

### 3. Modifying While Iterating
```java
❌ Incorrect:
for (int num : list) {
    if (num % 2 == 0) {
        list.remove(num);     // ConcurrentModificationException!
    }
}

✅ Correct:
Iterator<Integer> it = list.iterator();
while (it.hasNext()) {
    if (it.next() % 2 == 0) {
        it.remove();
    }
}
```

### 4. Not Checking Empty Lists
```java
❌ Incorrect:
int cols = matrix.get(0).size();  // Crashes if matrix is empty!

✅ Correct:
if (!matrix.isEmpty()) {
    int cols = matrix.get(0).size();
}
```

### 5. Using Primitives Directly
```java
❌ Incorrect:
ArrayList<int> list;              // Compilation error!

✅ Correct:
ArrayList<Integer> list;          // Use wrapper class
```

---

## 🎓 ArrayList Best Practices

### 1. Initialize with Capacity
```java
// If you know approximate size
ArrayList<Integer> list = new ArrayList<>(100);
// Reduces resizing operations
```

### 2. Use Enhanced For Loop When Possible
```java
// Cleaner and less error-prone
for (int num : list) {
    System.out.println(num);
}
```

### 3. Use Collections Utility Methods
```java
import java.util.Collections;

Collections.sort(list);                    // Sort
Collections.reverse(list);                 // Reverse
Collections.shuffle(list);                 // Shuffle
int max = Collections.max(list);           // Find max
int min = Collections.min(list);           // Find min
```

### 4. Convert to Array When Needed
```java
// To Integer[]
Integer[] arr = list.toArray(new Integer[0]);

// To int[] (requires loop)
int[] arr = list.stream().mapToInt(i -> i).toArray();
```

### 5. Use List Interface
```java
// More flexible
List<Integer> list = new ArrayList<>();
// Can swap implementation easily
```

---

## 📚 Topics to be Added

As I continue practicing, this folder will include:

### **Basic Operations**
- Add, remove, update elements
- Search and find operations
- Sorting and reversing

### **ArrayList Algorithms**
- Two pointers on ArrayList
- Sliding window
- Sublist operations

### **2D ArrayList Problems**
- Matrix operations
- Row/column sums
- Diagonal traversals
- Matrix manipulation

### **Advanced Topics**
- ArrayList with custom objects
- Sorting with comparators
- Stream operations
- Performance optimization

---

## 🎯 Learning Path

### Beginner Level
1. **Basic Operations** - Add, get, remove, size
2. **Iteration** - Different ways to loop
3. **Common Methods** - contains, indexOf, clear

### Intermediate Level
4. **2D ArrayList** - Creating and accessing
5. **ArrayList Algorithms** - Sorting, searching
6. **Problem Solving** - Apply to coding problems

### Advanced Level
7. **Custom Objects** - ArrayList of custom classes
8. **Comparators** - Custom sorting
9. **Performance** - Optimization techniques

---

## 💻 Useful Methods Reference

### Adding Elements
```java
add(E e)                    // Add at end
add(int index, E element)   // Add at specific position
addAll(Collection c)        // Add all from collection
```

### Removing Elements
```java
remove(int index)           // Remove by index
remove(Object o)            // Remove by value
removeAll(Collection c)     // Remove all matching
clear()                     // Remove all elements
```

### Accessing Elements
```java
get(int index)              // Get element
set(int index, E element)   // Update element
size()                      // Get size
isEmpty()                   // Check if empty
```

### Searching
```java
contains(Object o)          // Check if exists
indexOf(Object o)           // First occurrence
lastIndexOf(Object o)       // Last occurrence
```

### Bulk Operations
```java
subList(int from, int to)   // Get sublist
toArray()                   // Convert to array
clone()                     // Create shallow copy
```

---

## 🔄 ArrayList vs Other Collections

| Collection | Ordered | Indexed | Duplicates | Thread-Safe | Best For |
|------------|---------|---------|------------|-------------|----------|
| **ArrayList** | ✅ | ✅ | ✅ | ❌ | Random access |
| **LinkedList** | ✅ | ❌ | ✅ | ❌ | Frequent insertions |
| **HashSet** | ❌ | ❌ | ❌ | ❌ | Unique elements |
| **TreeSet** | ✅ | ❌ | ❌ | ❌ | Sorted unique |
| **Vector** | ✅ | ✅ | ✅ | ✅ | Legacy (avoid) |

---

## 📊 Progress Tracker

**Current Status:**
- Topics Covered: 1 (Minimum Vertical Sum)
- Total Problems: 1
- Difficulty: Medium
- 2D ArrayList: Started ✓

**Next Milestones:**
- [ ] Add 5 basic ArrayList problems
- [ ] Cover all common operations
- [ ] Practice 2D ArrayList problems
- [ ] Implement sorting algorithms on ArrayList

---

## 🎓 Interview Tips

### Common ArrayList Interview Questions

1. **"Explain how ArrayList works internally"**
   - Dynamic array with automatic resizing
   - Initial capacity of 10
   - Grows by 50% when full
   - Backed by Object[]

2. **"ArrayList vs Array"**
   - Mention size flexibility
   - Performance trade-offs
   - Type safety with generics

3. **"Time complexity of operations"**
   - add(): O(1) amortized
   - get(): O(1)
   - remove(): O(n)

### What Interviewers Look For

- ✅ Proper null checks
- ✅ Boundary condition handling
- ✅ Efficient iteration methods
- ✅ Understanding of time complexity
- ✅ Proper use of generics

---

## 📖 Additional Resources

### Documentation
- [Java ArrayList Documentation](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html)
- [Java Collections Framework](https://docs.oracle.com/javase/tutorial/collections/)

### Practice Platforms
- [GeeksforGeeks - ArrayList](https://www.geeksforgeeks.org/arraylist-in-java/)
- [LeetCode - Array Problems](https://leetcode.com/tag/array/)

### Video Tutorials
- [ArrayList Tutorial](https://www.youtube.com/results?search_query=java+arraylist+tutorial)
- [Collections Framework](https://www.youtube.com/results?search_query=java+collections+framework)

---

## 🤝 Contributing

As I add more ArrayList problems:
- Each significant problem gets its own folder
- Detailed README for complex algorithms
- Clear examples and test cases
- Multiple approaches when applicable

---

## 💡 Remember

> "ArrayList is not just a resizable array—it's a gateway to understanding dynamic data structures and the Java Collections Framework."

**Key Takeaways:**
- Choose ArrayList when size is dynamic
- Understand the trade-offs vs arrays
- Master both 1D and 2D operations
- Practice common patterns

---

<div align="center">

**Master ArrayList, Master Dynamic Collections! 📋**

*Last Updated: December 2024*

[⬆ Back to Main Repository](../README.md)

</div>
