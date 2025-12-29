# 🔗 Linked List

[![Topics](https://img.shields.io/badge/Topics-1-blue?style=flat-square)](.)
[![Problems](https://img.shields.io/badge/Problems-1-green?style=flat-square)](.)
[![Language](https://img.shields.io/badge/Language-Java-red?style=flat-square)](.)

> Master dynamic data structures with linked lists. Learn traversal, manipulation, and advanced pointer techniques essential for technical interviews.

---

## 📋 Overview

A **Linked List** is a linear data structure where elements are stored in nodes, and each node points to the next node in the sequence. Unlike arrays, linked lists don't require contiguous memory and allow for efficient insertion and deletion operations.

**What You'll Find:**
- **Fundamental Operations** - Insertion, deletion, traversal
- **Advanced Techniques** - Two pointers, Floyd's algorithm, reversal
- **Classic Problems** - Cycle detection, merging, intersection
- **Interview Patterns** - Fast-slow pointers, dummy nodes, recursion
- **Multiple Approaches** - Iterative and recursive solutions
- **Complexity Analysis** - Time and space trade-offs

**Key Characteristics:**
- **Dynamic Size** - Grows and shrinks as needed
- **No Random Access** - Must traverse from head
- **Efficient Insertion/Deletion** - O(1) at known positions
- **Extra Memory** - Pointer overhead per node
- **No Cache Locality** - Nodes scattered in memory

---

## 📂 Repository Structure

```
LinkedList/
│
├── CycleDetection/          # Cycle-related problems
│   ├── CycleStart/
│   └── README.md
│
├── (More topics to be added)
│
└── README.md                # This file
```

---

## 📚 Topics Covered

### ✅ Current Topics

| # | Topic | Folder | Problems | Difficulty | Status |
|---|-------|--------|----------|------------|--------|
| 1 | **Cycle Detection** | [CycleDetection/](./CycleDetection/) | 1 | Easy-Medium | ✅ In Progress |

---

## 🔑 Linked List Fundamentals

### Node Structure

```java
// Singly Linked List Node
public class ListNode {
    int val;
    ListNode next;
    
    ListNode() {}
    
    ListNode(int val) {
        this.val = val;
    }
    
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

// Doubly Linked List Node
public class DoublyListNode {
    int val;
    DoublyListNode prev;
    DoublyListNode next;
    
    DoublyListNode(int val) {
        this.val = val;
    }
}
```

### Basic Operations

```java
// Create a linked list
ListNode head = new ListNode(1);
head.next = new ListNode(2);
head.next.next = new ListNode(3);
// Result: 1 → 2 → 3 → null

// Traversal
ListNode current = head;
while (current != null) {
    System.out.print(current.val + " ");
    current = current.next;
}

// Insert at beginning
public ListNode insertAtHead(ListNode head, int val) {
    ListNode newNode = new ListNode(val);
    newNode.next = head;
    return newNode;
}

// Insert at end
public ListNode insertAtEnd(ListNode head, int val) {
    if (head == null) return new ListNode(val);
    
    ListNode current = head;
    while (current.next != null) {
        current = current.next;
    }
    current.next = new ListNode(val);
    return head;
}

// Delete node with value
public ListNode deleteNode(ListNode head, int val) {
    if (head == null) return null;
    
    if (head.val == val) return head.next;
    
    ListNode current = head;
    while (current.next != null && current.next.val != val) {
        current = current.next;
    }
    
    if (current.next != null) {
        current.next = current.next.next;
    }
    
    return head;
}

// Search for value
public boolean search(ListNode head, int val) {
    ListNode current = head;
    while (current != null) {
        if (current.val == val) return true;
        current = current.next;
    }
    return false;
}

// Get length
public int getLength(ListNode head) {
    int length = 0;
    ListNode current = head;
    while (current != null) {
        length++;
        current = current.next;
    }
    return length;
}
```

---

## 🎯 Topic: Cycle Detection

### Overview

Master the art of detecting and handling cycles in linked lists using Floyd's "Tortoise and Hare" algorithm. Learn to identify if a cycle exists, find where it starts, calculate its length, and remove it if needed.

**Key Concepts Covered:**
- Floyd's Cycle Detection Algorithm
- Two-pointer technique (fast and slow)
- Mathematical proof of algorithm correctness
- Cycle start identification
- Cycle length calculation
- Cycle removal

**Problems:**
1. **Find Cycle Start** - Detect and locate cycle beginning

**See detailed content:** [CycleDetection/README.md](./CycleDetection/README.md)

---

## 🗺️ Planned Topics

### 🔄 Coming Soon

**Basic Operations:**
- [ ] **Insertion & Deletion**
  - Insert at beginning/end/position
  - Delete node by value/position
  - Delete duplicates
  - Delete Nth node from end

- [ ] **Reversal**
  - Reverse entire list
  - Reverse in groups
  - Reverse between positions
  - Palindrome check using reversal

- [ ] **Two Pointer Techniques**
  - Middle of linked list
  - Nth node from end
  - Remove duplicates
  - Partition list

**Intermediate Operations:**
- [ ] **Merging & Splitting**
  - Merge two sorted lists
  - Merge K sorted lists
  - Split list in half
  - Odd-even list

- [ ] **Sorting**
  - Merge sort on linked list
  - Insertion sort
  - Quick sort adaptation
  - Sort list with constraints

- [ ] **Intersection & Union**
  - Intersection of two lists
  - Union of two lists
  - Check if lists intersect
  - Find intersection point

**Advanced Operations:**
- [ ] **Complex Manipulations**
  - Flatten multilevel list
  - Copy list with random pointer
  - LRU Cache implementation
  - Add two numbers as lists

- [ ] **Special Techniques**
  - Recursive solutions
  - Dummy node patterns
  - In-place algorithms
  - Space optimization

---

## 💡 Common Linked List Patterns

### Pattern 1: Dummy Node
```java
// Simplifies edge cases for head modifications
public ListNode solution(ListNode head) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    
    // Work with dummy.next
    ListNode current = dummy;
    // ... operations
    
    return dummy.next;  // New head
}
```

### Pattern 2: Two Pointers (Fast-Slow)
```java
// Find middle, detect cycle, etc.
public ListNode findMiddle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;
    
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    
    return slow;  // Middle node
}
```

### Pattern 3: Previous Pointer Tracking
```java
// For deletion or reversal
public ListNode reverse(ListNode head) {
    ListNode prev = null;
    ListNode current = head;
    
    while (current != null) {
        ListNode next = current.next;
        current.next = prev;
        prev = current;
        current = next;
    }
    
    return prev;  // New head
}
```

### Pattern 4: Runner Technique
```java
// Advance one pointer ahead
public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    
    ListNode first = dummy;
    ListNode second = dummy;
    
    // Advance first n+1 steps
    for (int i = 0; i <= n; i++) {
        first = first.next;
    }
    
    // Move both until first reaches end
    while (first != null) {
        first = first.next;
        second = second.next;
    }
    
    // Remove nth node
    second.next = second.next.next;
    return dummy.next;
}
```

### Pattern 5: Recursive Approach
```java
// Recursive reversal
public ListNode reverseRecursive(ListNode head) {
    if (head == null || head.next == null) {
        return head;
    }
    
    ListNode newHead = reverseRecursive(head.next);
    head.next.next = head;
    head.next = null;
    
    return newHead;
}
```

---

## 🔑 Key Linked List Concepts

### 1. Types of Linked Lists

| Type | Structure | Advantages | Disadvantages |
|------|-----------|------------|---------------|
| **Singly Linked** | node → node → null | Simple, less memory | One-way traversal only |
| **Doubly Linked** | ← node ↔ node → | Two-way traversal | More memory (prev pointer) |
| **Circular** | node → node → head | No null checks needed | Careful with infinite loops |
| **Circular Doubly** | ↔ node ↔ node ↔ | Best for navigation | Most complex |

### 2. Time Complexities

| Operation | Array | Singly Linked List | Doubly Linked List |
|-----------|-------|-------------------|-------------------|
| **Access by index** | O(1) | O(n) | O(n) |
| **Search** | O(n) | O(n) | O(n) |
| **Insert at head** | O(n) | O(1) | O(1) |
| **Insert at tail** | O(1)* | O(n) / O(1)† | O(1)† |
| **Insert at position** | O(n) | O(n) | O(n) |
| **Delete at head** | O(n) | O(1) | O(1) |
| **Delete at tail** | O(1)* | O(n) | O(1)† |
| **Delete at position** | O(n) | O(n) | O(n) |

*Amortized, †With tail pointer

### 3. Space Complexities

```java
// Space overhead per node
Singly Linked: data + 1 pointer (8 bytes)
Doubly Linked: data + 2 pointers (16 bytes)
Array: only data (0 overhead)

// Example for 1000 integers:
Array: 4KB (data only)
Singly Linked: 12KB (4KB data + 8KB pointers)
Doubly Linked: 20KB (4KB data + 16KB pointers)
```

### 4. When to Use Linked Lists

**Use Linked Lists When:**
- ✅ Frequent insertions/deletions at beginning
- ✅ Unknown or dynamic size
- ✅ Don't need random access
- ✅ Memory fragmentation is acceptable
- ✅ Implementing stack, queue, or deque

**Use Arrays When:**
- ✅ Need random access (O(1))
- ✅ Size is known and fixed
- ✅ Memory efficiency is critical
- ✅ Cache locality matters
- ✅ Frequent access by index

---

## ⚠️ Common Linked List Mistakes

### 1. Losing Head Reference
```java
❌ Incorrect:
public void traverse(ListNode head) {
    while (head != null) {
        System.out.println(head.val);
        head = head.next;  // Lost original head!
    }
}

✅ Correct:
public void traverse(ListNode head) {
    ListNode current = head;
    while (current != null) {
        System.out.println(current.val);
        current = current.next;
    }
}
```

### 2. Not Handling Null
```java
❌ Incorrect:
public int getLength(ListNode head) {
    int length = 0;
    while (head.next != null) {  // Fails if head is null!
        length++;
        head = head.next;
    }
    return length;
}

✅ Correct:
public int getLength(ListNode head) {
    if (head == null) return 0;
    
    int length = 0;
    while (head != null) {
        length++;
        head = head.next;
    }
    return length;
}
```

### 3. Incorrect Reversal
```java
❌ Incorrect:
public ListNode reverse(ListNode head) {
    ListNode prev = null;
    while (head != null) {
        head.next = prev;  // Lost the rest of the list!
        prev = head;
        head = head.next;  // Now this is null!
    }
    return prev;
}

✅ Correct:
public ListNode reverse(ListNode head) {
    ListNode prev = null;
    while (head != null) {
        ListNode next = head.next;  // Save next first!
        head.next = prev;
        prev = head;
        head = next;
    }
    return prev;
}
```

### 4. Off-by-One Errors
```java
❌ Incorrect:
public ListNode getNthNode(ListNode head, int n) {
    for (int i = 0; i < n; i++) {  // Wrong for 0-indexed
        head = head.next;
    }
    return head;
}

✅ Correct:
public ListNode getNthNode(ListNode head, int n) {
    for (int i = 0; i < n - 1; i++) {  // Or start from i=1
        if (head == null) return null;
        head = head.next;
    }
    return head;
}
```

### 5. Memory Leaks (Languages with Manual Memory Management)
```java
// In Java, garbage collector handles this
// In C/C++, you must free memory:

❌ C++ Incorrect:
ListNode* temp = head;
head = head->next;
// Memory leak! temp is lost

✅ C++ Correct:
ListNode* temp = head;
head = head->next;
delete temp;  // Free memory
```

---

## 🎓 Linked List Best Practices

### 1. Use Dummy Nodes for Head Operations
```java
ListNode dummy = new ListNode(0);
dummy.next = head;
// Work with dummy.next
return dummy.next;
```

### 2. Always Check for Null
```java
if (head == null) return null;
if (head.next == null) return head;
```

### 3. Use Descriptive Names
```java
// Good
ListNode current, prev, next;

// Avoid
ListNode p, q, r;
```

### 4. Draw Diagrams
```
Before: 1 → 2 → 3 → null
After:  1 → 3 → null (removed 2)
```

### 5. Test Edge Cases
```java
// Test with:
- null head
- Single node
- Two nodes
- Many nodes
- Cycle (if applicable)
```

---

## 🎯 Learning Path

### Beginner Level (Week 1-2)
1. **Basic Operations**
   - Traversal and printing
   - Insert at head/tail
   - Delete by value
   - Search for element

2. **Simple Algorithms**
   - Find length
   - Find middle node
   - Reverse a list
   - Detect cycle (boolean)

### Intermediate Level (Week 3-4)
3. **Two Pointer Techniques**
   - Cycle detection and start ← Current
   - Nth node from end
   - Remove duplicates
   - Palindrome check

4. **Merging and Sorting**
   - Merge two sorted lists
   - Merge sort
   - Split list in half
   - Partition list

### Advanced Level (Week 5-6)
5. **Complex Problems**
   - Merge K sorted lists
   - Copy with random pointer
   - LRU Cache
   - Flatten multilevel list

6. **Optimization Techniques**
   - In-place algorithms
   - Recursive solutions
   - Space complexity optimization
   - Advanced pointer manipulation

---

## 📊 Progress Tracking

### Overall Statistics
- **Topics**: 1 / 10+ planned
- **Total Problems**: 1
- **Difficulty Range**: Easy to Hard
- **Completion**: 10%

### Current Focus
- ✅ Cycle Detection basics
- 🔄 Adding more cycle problems
- 📅 Basic operations (next)
- 📅 Two-pointer techniques (upcoming)

### Milestones
- [x] Create repository structure
- [x] Add Cycle Detection topic
- [x] Document cycle start problem
- [ ] Complete 5 cycle detection problems
- [ ] Add Basic Operations section
- [ ] Add Reversal section
- [ ] Add 30+ total problems

---

## 💻 Essential Operations Quick Reference

### Creation and Traversal
```java
ListNode head = new ListNode(val);
while (current != null) current = current.next;
```

### Insertion
```java
// At head
newNode.next = head; head = newNode;

// At tail
while (current.next != null) current = current.next;
current.next = newNode;
```

### Deletion
```java
// Remove next node
current.next = current.next.next;
```

### Reversal
```java
ListNode prev = null;
while (current != null) {
    next = current.next;
    current.next = prev;
    prev = current;
    current = next;
}
```

### Two Pointers
```java
ListNode slow = head, fast = head;
while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
}
```

---

## 🔄 Linked List vs Other Data Structures

### Comparison Table

| Feature | Array | Linked List | ArrayList | Stack | Queue |
|---------|-------|-------------|-----------|-------|-------|
| **Random Access** | O(1) | O(n) | O(1) | O(n) | O(n) |
| **Insert/Delete at Start** | O(n) | O(1) | O(n) | O(1) | O(1)* |
| **Insert/Delete at End** | O(1) | O(n) | O(1)* | O(1) | O(1) |
| **Memory** | Contiguous | Scattered | Contiguous | Scattered | Varies |
| **Size** | Fixed | Dynamic | Dynamic | Dynamic | Dynamic |
| **Cache** | Good | Poor | Good | Poor | Varies |

*Amortized

### When to Choose What

**Choose Linked List:**
```java
// Frequent insertions/deletions at start
LinkedList<Integer> list = new LinkedList<>();
list.addFirst(1);  // O(1)

// Unknown size with many modifications
// Implementing custom data structures
```

**Choose Array/ArrayList:**
```java
// Frequent random access
int[] arr = new int[100];
int val = arr[50];  // O(1)

// Known size, mostly read operations
ArrayList<Integer> list = new ArrayList<>();
int val = list.get(50);  // O(1)
```

---

## 🎓 Interview Preparation

### Must-Know Problems

**Easy:**
- Reverse Linked List
- Merge Two Sorted Lists
- Remove Duplicates
- Middle of Linked List
- Cycle Detection (boolean)

**Medium:**
- Find Cycle Start ← Current
- Remove Nth Node from End
- Add Two Numbers
- Reorder List
- Sort List

**Hard:**
- Merge K Sorted Lists
- Reverse Nodes in K-Group
- Copy List with Random Pointer
- LRU Cache

### Common Interview Questions

1. **"Explain singly vs doubly linked lists"**
   - Singly: Only next pointer, one-way traversal
   - Doubly: prev and next pointers, two-way traversal
   - Trade-off: Memory vs flexibility

2. **"How do you detect a cycle?"**
   - Floyd's algorithm with fast and slow pointers
   - O(n) time, O(1) space
   - Optimal solution

3. **"Reverse a linked list in-place"**
   - Three pointers: prev, current, next
   - O(n) time, O(1) space
   - Can also do recursively

4. **"Find middle of linked list"**
   - Fast-slow pointer technique
   - When fast reaches end, slow is at middle
   - O(n) time, O(1) space

### Interview Tips

- ✅ Draw diagrams on whiteboard
- ✅ Handle null cases first
- ✅ Use dummy nodes when appropriate
- ✅ Explain your approach before coding
- ✅ Test with examples
- ✅ Discuss time and space complexity

---

## 📖 Additional Resources

### Documentation
- [Java LinkedList API](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedList.html)
- [GeeksforGeeks - Linked List](https://www.geeksforgeeks.org/data-structures/linked-list/)

### Visualizations
- [VisuAlgo - Linked List](https://visualgo.net/en/list)
- [Data Structure Visualizations](https://www.cs.usfca.edu/~galles/visualization/Algorithms.html)

### Practice Platforms
- [LeetCode - Linked List](https://leetcode.com/tag/linked-list/)
- [HackerRank - Linked List](https://www.hackerrank.com/domains/data-structures?filters%5Bsubdomains%5D%5B%5D=linked-lists)
- [InterviewBit - Linked List](https://www.interviewbit.com/courses/programming/topics/linked-lists/)

### Books
- "Cracking the Coding Interview" - Linked List Chapter
- "Elements of Programming Interviews" - Linked List Section

---

## 💡 Key Takeaways

> "Linked lists teach you pointer manipulation - a skill that translates to understanding memory, references, and complex data structures."

**Remember:**
- **Visualize** - Draw the operations
- **Null checks** - Always validate pointers
- **Edge cases** - Empty, single node, two nodes
- **Space vs Time** - O(1) space often possible
- **Patterns** - Master two-pointer techniques

**Core Skills:**
- Pointer manipulation
- Iterative and recursive thinking
- Space optimization
- Edge case handling
- Algorithm design

---

<div align="center">

## 🎯 Master Linked Lists, One Node at a Time! 🔗

**Current Progress: Cycle Detection**

*Building strong foundations in pointer manipulation*

---

### Quick Stats

📚 Topics: **1** | 💻 Problems: **1** | ⭐ Difficulty: **Easy-Medium**

---

*Last Updated: December 2024*

**[⬆ Back to Top](#-linked-list)**

</div>

---

## 📝 Roadmap

### Short Term (1-2 months)
- [ ] Complete Cycle Detection (5+ problems)
- [ ] Add Basic Operations section
- [ ] Add Reversal techniques
- [ ] Create 15+ total problems

### Medium Term (3-6 months)
- [ ] Add Merging and Sorting
- [ ] Add Two-Pointer patterns
- [ ] Add Intersection problems
- [ ] Create 30+ total problems

### Long Term (6-12 months)
- [ ] Cover all major patterns
- [ ] 50+ practice problems
- [ ] Advanced applications
- [ ] Real-world implementations

---

## 📞 Version History

- **v0.1.0** (December 2024) - Initial setup
  - Added Cycle Detection section
  - Created Find Cycle Start problem
  - Established documentation structure

---

<div align="center">

### Happy Coding! 🚀

Made with 🔗 and dedication to mastering data structures

[⬆ Back to Data Structures & Algorithms](../README.md)

</div>
