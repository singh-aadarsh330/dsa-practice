# Array Traversal 🔄

**Category:** Arrays - Fundamental Operations  
**Difficulty:** Basic to Easy  
**Topics:** Iteration, Array Access, Direction Control

---

## 📋 Overview

Array traversal is the fundamental operation of visiting each element in an array systematically. This section covers various traversal patterns and techniques that form the foundation for more complex array operations.

---

## 🎯 Topics Covered

### 1. Basic Traversal
- **Forward Traversal** - Left to right iteration
- **Backward Traversal** - Right to left iteration
- **Indexed Access** - Direct element access by position

### 2. Advanced Traversal Patterns
- **Two-Direction Traversal** - Simultaneous forward and backward
- **Step-Based Traversal** - Skipping elements (every 2nd, 3rd, etc.)
- **Conditional Traversal** - Based on element properties
- **Nested Traversal** - For 2D arrays/matrices

### 3. Special Techniques
- **Zigzag Traversal** - Alternating direction per row
- **Spiral Traversal** - Circular boundary-based movement
- **Diagonal Traversal** - For matrix operations
- **Reverse Pairs Traversal** - For comparison operations

---

## 💡 Common Patterns

### Pattern 1: Simple Forward Traversal
```java
for (int i = 0; i < arr.length; i++) {
    // Process arr[i]
}
```
**Use Cases:** Sum, Count, Find, Print

### Pattern 2: Backward Traversal
```java
for (int i = arr.length - 1; i >= 0; i--) {
    // Process arr[i]
}
```
**Use Cases:** Reverse operations, Right-to-left processing

### Pattern 3: Enhanced For Loop
```java
for (int element : arr) {
    // Process element
}
```
**Use Cases:** Read-only operations, Clean syntax

### Pattern 4: Two-Pointer Traversal
```java
int left = 0, right = arr.length - 1;
while (left < right) {
    // Process arr[left] and arr[right]
    left++;
    right--;
}
```
**Use Cases:** Palindrome check, Two-sum problems

### Pattern 5: Step Traversal
```java
for (int i = 0; i < arr.length; i += step) {
    // Process arr[i]
}
```
**Use Cases:** Alternate elements, Pattern-based access

---

## 🔍 Problem Categories

### Basic Problems
- Print all elements
- Find sum of array elements
- Count occurrences
- Find minimum/maximum
- Check if element exists
- Reverse array printing

### Intermediate Problems
- Find second largest/smallest
- Rotate array elements
- Segregate even/odd elements
- Move zeros to end
- Find leaders in array
- Array rotation count

### Advanced Problems
- Wave array traversal
- Spiral matrix traversal
- Zigzag pattern printing
- Diagonal sum calculations
- K-distance traversal
- Circular array traversal

---

## 📊 Complexity Analysis

| Operation | Time Complexity | Space Complexity |
|-----------|----------------|------------------|
| Forward Traversal | O(n) | O(1) |
| Backward Traversal | O(n) | O(1) |
| Two-Pointer | O(n) | O(1) |
| Nested (2D) | O(n×m) | O(1) |
| Spiral | O(n×m) | O(1) |

---

## 🎓 Key Concepts

### 1. Index Management
- Start index: Usually 0
- End index: arr.length - 1
- Boundary conditions: Avoid ArrayIndexOutOfBoundsException

### 2. Direction Control
- Forward: i++
- Backward: i--
- Custom step: i += k

### 3. Termination Conditions
- Fixed length: i < n
- Pointer meeting: left < right
- Condition-based: while (condition)

---

## 💻 Example Problems

### Problem 1: Print Array Elements
```java
void printArray(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
        System.out.print(arr[i] + " ");
    }
}
```

### Problem 2: Sum of Elements
```java
int arraySum(int[] arr) {
    int sum = 0;
    for (int num : arr) {
        sum += num;
    }
    return sum;
}
```

### Problem 3: Reverse Print
```java
void reversePrint(int[] arr) {
    for (int i = arr.length - 1; i >= 0; i--) {
        System.out.print(arr[i] + " ");
    }
}
```

### Problem 4: Alternate Elements
```java
void printAlternate(int[] arr) {
    for (int i = 0; i < arr.length; i += 2) {
        System.out.print(arr[i] + " ");
    }
}
```

---

## 🚀 Practice Tips

1. **Start Simple** - Master basic forward/backward traversal first
2. **Understand Indices** - Always be aware of array boundaries
3. **Visualize** - Draw array elements during traversal
4. **Edge Cases** - Empty array, single element, two elements
5. **Optimize** - Choose right loop type for the problem

---

## ⚠️ Common Mistakes

1. ❌ **Off-by-one errors** - Wrong loop conditions
2. ❌ **Index out of bounds** - Accessing invalid indices
3. ❌ **Infinite loops** - Incorrect termination conditions
4. ❌ **Wrong increment** - i++ vs i-- confusion
5. ❌ **Null checks** - Forgetting to check null arrays

---

## 🔗 Related Topics

- [Two Pointer Technique](../Two-Pointer/)
- [Sliding Window](../Sliding-Window/)
- [Subarrays](../Subarrays/)
- [Matrix Traversal](../Matrix/Matrix-Traversal/)

---

## 📚 Learning Path

```
Basic Traversal
    ↓
Forward/Backward
    ↓
Two-Pointer
    ↓
Step-Based
    ↓
2D Traversal
    ↓
Spiral/Zigzag
```

---

## 🎯 Expected Problems

**Total:** 15-20 problems  
**Basic:** 8-10 problems  
**Easy:** 5-7 problems  
**Medium:** 2-3 problems  

---

## 📈 Progress Tracker

- [ ] Master basic forward traversal
- [ ] Understand backward traversal
- [ ] Practice two-pointer technique
- [ ] Solve step-based problems
- [ ] Learn 2D traversal patterns
- [ ] Master spiral traversal
- [ ] Complete all practice problems

---

**Note:** This folder will contain organized solutions for all array traversal patterns. Each problem will include detailed explanations, multiple approaches, and complexity analysis.

---

**Coming Soon!** 🚀

*Solutions and detailed problem breakdowns will be added as problems are solved and documented.*

---

**Happy Learning! 📚**
