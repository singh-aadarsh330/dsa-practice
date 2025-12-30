# Insertion & Deletion Operations 🔧

**Category:** Arrays - Fundamental Operations  
**Difficulty:** Basic to Medium  
**Topics:** Array Manipulation, Shifting Elements, In-place Operations

---

## 📋 Overview

This section focuses on fundamental array modification operations - inserting new elements and deleting existing ones. These operations form the basis for understanding dynamic data structures and efficient array manipulation.

---

## 🎯 Topics Covered

### Insertion Operations

#### 1. Position-Based Insertion
- **Insert at Beginning** - Shift all elements right
- **Insert at End** - Append to last position
- **Insert at Position** - Shift elements from index
- **Insert at Sorted Position** - Maintain sorted order

#### 2. Condition-Based Insertion
- Insert if not exists
- Insert with constraints
- Insert maintaining properties
- Bulk insertion operations

### Deletion Operations

#### 1. Position-Based Deletion
- **Delete from Beginning** - Shift all elements left
- **Delete from End** - Remove last element
- **Delete from Position** - Shift elements after index
- **Delete at Range** - Remove multiple elements

#### 2. Value-Based Deletion
- Delete specific element
- Delete all occurrences
- Delete duplicates
- Conditional deletion

#### 3. Pattern-Based Deletion
- Delete alternate elements
- Delete by criteria (even/odd)
- Delete in range
- Delete and rearrange

---

## 💡 Common Patterns

### Pattern 1: Insert at Beginning
```java
void insertAtBeginning(int[] arr, int n, int element) {
    // Shift elements right
    for (int i = n - 1; i >= 0; i--) {
        arr[i + 1] = arr[i];
    }
    arr[0] = element;
}
```
**Time:** O(n) | **Space:** O(1)

### Pattern 2: Insert at Position
```java
void insertAtPosition(int[] arr, int n, int pos, int element) {
    // Shift elements from position
    for (int i = n - 1; i >= pos; i--) {
        arr[i + 1] = arr[i];
    }
    arr[pos] = element;
}
```
**Time:** O(n) | **Space:** O(1)

### Pattern 3: Delete Element
```java
int deleteElement(int[] arr, int n, int element) {
    int pos = findElement(arr, n, element);
    if (pos == -1) return n;
    
    // Shift elements left
    for (int i = pos; i < n - 1; i++) {
        arr[i] = arr[i + 1];
    }
    return n - 1;
}
```
**Time:** O(n) | **Space:** O(1)

### Pattern 4: Delete at Position
```java
int deleteAtPosition(int[] arr, int n, int pos) {
    // Shift elements left from position
    for (int i = pos; i < n - 1; i++) {
        arr[i] = arr[i + 1];
    }
    return n - 1;
}
```
**Time:** O(n) | **Space:** O(1)

### Pattern 5: Remove Duplicates (Sorted)
```java
int removeDuplicates(int[] arr, int n) {
    if (n == 0) return 0;
    
    int j = 0;
    for (int i = 1; i < n; i++) {
        if (arr[i] != arr[j]) {
            j++;
            arr[j] = arr[i];
        }
    }
    return j + 1;
}
```
**Time:** O(n) | **Space:** O(1)

---

## 🔍 Problem Categories

### Basic Operations
- Insert at beginning/end
- Delete from beginning/end
- Insert at given position
- Delete from given position
- Find and delete element

### Array Modification
- Remove duplicates from sorted array
- Remove duplicates from unsorted array
- Delete all occurrences of element
- Move zeros to end
- Move negative elements to one side

### Advanced Operations
- Insert in sorted array
- Delete middle element
- Rotate array (left/right)
- Rearrange positive and negative
- Segregate elements by property

### In-Place Operations
- Replace elements
- Swap adjacent elements
- Compact array (remove gaps)
- Merge operations
- Partition operations

---

## 📊 Complexity Analysis

| Operation | Time Complexity | Space Complexity |
|-----------|----------------|------------------|
| Insert at Beginning | O(n) | O(1) |
| Insert at End | O(1) | O(1) |
| Insert at Position | O(n) | O(1) |
| Delete at Beginning | O(n) | O(1) |
| Delete at End | O(1) | O(1) |
| Delete at Position | O(n) | O(1) |
| Remove Duplicates | O(n) | O(1) |
| Find and Delete | O(n) | O(1) |

---

## 🎓 Key Concepts

### 1. Array Size Management
- Fixed size arrays vs dynamic size
- Tracking current size vs capacity
- Overflow and underflow handling

### 2. Element Shifting
- Right shift for insertion
- Left shift for deletion
- Minimize shifts for efficiency

### 3. In-Place Modifications
- No extra array allocation
- Constant space complexity
- Two-pointer technique

### 4. Edge Cases
- Empty array operations
- Single element array
- Operations at boundaries
- Invalid positions

---

## 💻 Example Problems

### Problem 1: Insert at End
```java
int insertEnd(int[] arr, int n, int capacity, int element) {
    if (n >= capacity) return n; // Array full
    arr[n] = element;
    return n + 1;
}
```

### Problem 2: Search and Delete
```java
int searchAndDelete(int[] arr, int n, int key) {
    // Find element
    int i;
    for (i = 0; i < n; i++) {
        if (arr[i] == key) break;
    }
    
    // Not found
    if (i == n) return n;
    
    // Delete by shifting
    for (int j = i; j < n - 1; j++) {
        arr[j] = arr[j + 1];
    }
    
    return n - 1;
}
```

### Problem 3: Move Zeros to End
```java
void moveZerosToEnd(int[] arr, int n) {
    int count = 0; // Count non-zero elements
    
    // Traverse and move non-zeros
    for (int i = 0; i < n; i++) {
        if (arr[i] != 0) {
            arr[count++] = arr[i];
        }
    }
    
    // Fill remaining with zeros
    while (count < n) {
        arr[count++] = 0;
    }
}
```

### Problem 4: Delete Duplicates (Unsorted)
```java
int deleteDuplicates(int[] arr, int n) {
    if (n == 0) return 0;
    
    int j = 0;
    HashSet<Integer> seen = new HashSet<>();
    
    for (int i = 0; i < n; i++) {
        if (!seen.contains(arr[i])) {
            seen.add(arr[i]);
            arr[j++] = arr[i];
        }
    }
    
    return j;
}
```

---

## 🚀 Practice Tips

1. **Master Basic Shifting** - Understand element movement
2. **Track Array Size** - Always maintain current size
3. **Handle Edge Cases** - Empty, full, single element
4. **In-Place Practice** - Avoid extra space when possible
5. **Visualize Operations** - Draw before coding

---

## ⚠️ Common Mistakes

1. ❌ **Not checking array bounds** - Overflow/underflow
2. ❌ **Wrong shift direction** - Left vs right confusion
3. ❌ **Forgetting to update size** - After insertion/deletion
4. ❌ **Off-by-one errors** - Loop boundaries
5. ❌ **Not handling duplicates** - In delete operations
6. ❌ **Modifying while iterating** - Index management issues

---

## 🎯 When to Use

### Use Insertion When:
- Adding new data to array
- Building array incrementally
- Maintaining sorted order
- Expanding dataset

### Use Deletion When:
- Removing unwanted data
- Cleaning duplicates
- Filtering elements
- Compacting array

### Consider Alternatives When:
- Frequent insertions/deletions needed (use ArrayList)
- Need dynamic resizing (use dynamic structures)
- Performance is critical (use appropriate data structure)

---

## 🔗 Related Topics

- [Array Traversal](../Traversal/)
- [Two Pointer](../Two-Pointer/)
- [Sorting Algorithms](../Sorting/)
- [Searching Algorithms](../Searching/)

---

## 📚 Learning Path

```
Basic Insert/Delete
    ↓
Position-Based Operations
    ↓
Value-Based Operations
    ↓
Remove Duplicates
    ↓
In-Place Modifications
    ↓
Complex Rearrangements
```

---

## 🎯 Expected Problems

**Total:** 20-25 problems  
**Basic:** 8-10 problems (Insert/Delete basics)  
**Easy:** 8-10 problems (Remove duplicates, Move elements)  
**Medium:** 4-7 problems (Complex rearrangements)  

---

## 📈 Progress Tracker

- [ ] Insert at beginning/end/position
- [ ] Delete from beginning/end/position
- [ ] Remove duplicates (sorted)
- [ ] Remove duplicates (unsorted)
- [ ] Move zeros to end
- [ ] Delete all occurrences
- [ ] Segregate elements
- [ ] In-place array modifications

---

## 🏆 Key Takeaways

1. **Shifting is Expensive** - O(n) time for each operation
2. **Size Management** - Critical for correctness
3. **In-Place is Efficient** - Saves space
4. **Two-Pointer Helps** - For many optimization problems
5. **Edge Cases Matter** - Test boundary conditions

---

**Note:** This folder will contain organized solutions for insertion and deletion operations. Each problem will include multiple approaches, complexity analysis, and edge case handling.

---

**Coming Soon!** 🚀

*Solutions will be added systematically as problems are solved.*

---

**Happy Coding! 💻**
