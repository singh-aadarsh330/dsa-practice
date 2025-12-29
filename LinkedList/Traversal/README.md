# 🚶 Traversal

[![Problems](https://img.shields.io/badge/Problems-0-blue?style=flat-square)](.)
[![Difficulty](https://img.shields.io/badge/Difficulty-Easy%20to%20Medium-orange?style=flat-square)](.)
[![Language](https://img.shields.io/badge/Language-Java-red?style=flat-square)](.)

> Master traversal techniques for linked lists. Learn iterative and recursive approaches, printing patterns, and node access methods.

---

## 📋 Overview

**Traversal** is the fundamental operation of visiting each node in a linked list systematically. While traversal seems simple, mastering different traversal patterns is essential for solving complex linked list problems efficiently.

**Key Features:**
- **Linear Traversal** - Visit nodes sequentially from head to tail
- **Recursive Traversal** - Stack-based depth-first approach
- **Reverse Traversal** - Access nodes from tail to head
- **Selective Traversal** - Visit nodes based on conditions
- **Multi-Pass Traversal** - Multiple iterations for complex operations

**When Traversal is Needed:**
- ✅ Printing or displaying list contents
- ✅ Searching for specific values
- ✅ Counting nodes or calculating properties
- ✅ Validating list structure
- ✅ Collecting node information
- ✅ Building foundation for complex algorithms

---

## 🔑 Traversal Fundamentals

### Basic Traversal Patterns

```java
// 1. Standard Forward Traversal
public void traverse(ListNode head) {
    ListNode current = head;
    while (current != null) {
        System.out.print(current.val + " ");
        current = current.next;
    }
}

// 2. Traversal with Index
public void traverseWithIndex(ListNode head) {
    ListNode current = head;
    int index = 0;
    while (current != null) {
        System.out.println("Index " + index + ": " + current.val);
        current = current.next;
        index++;
    }
}

// 3. Recursive Traversal
public void traverseRecursive(ListNode head) {
    if (head == null) return;
    System.out.print(head.val + " ");
    traverseRecursive(head.next);
}

// 4. Reverse Recursive Traversal
public void traverseReverse(ListNode head) {
    if (head == null) return;
    traverseReverse(head.next);
    System.out.print(head.val + " ");
}

// 5. Conditional Traversal
public void traverseEven(ListNode head) {
    ListNode current = head;
    while (current != null) {
        if (current.val % 2 == 0) {
            System.out.print(current.val + " ");
        }
        current = current.next;
    }
}
```

### Common Traversal Operations

```java
// Count nodes
public int countNodes(ListNode head) {
    int count = 0;
    while (head != null) {
        count++;
        head = head.next;
    }
    return count;
}

// Find maximum value
public int findMax(ListNode head) {
    if (head == null) return Integer.MIN_VALUE;
    int max = head.val;
    while (head != null) {
        max = Math.max(max, head.val);
        head = head.next;
    }
    return max;
}

// Search for value
public boolean search(ListNode head, int target) {
    while (head != null) {
        if (head.val == target) return true;
        head = head.next;
    }
    return false;
}

// Get nth node
public ListNode getNth(ListNode head, int n) {
    for (int i = 0; i < n && head != null; i++) {
        head = head.next;
    }
    return head;
}

// Collect all values
public List<Integer> toList(ListNode head) {
    List<Integer> result = new ArrayList<>();
    while (head != null) {
        result.add(head.val);
        head = head.next;
    }
    return result;
}
```

---

## 📂 Repository Structure

```
Traversal/
│
├── (Problems to be added)
│
└── README.md                # This file
```

---

## 📝 Planned Problems

| # | Problem | Difficulty | Key Concepts | Status |
|---|---------|------------|--------------|--------|
| 1 | Print Linked List | Easy | Basic traversal | 📅 Planned |
| 2 | Print Reverse | Easy | Recursive traversal | 📅 Planned |
| 3 | Get Nth Node | Easy | Indexed access | 📅 Planned |
| 4 | Find Length | Easy | Counting | 📅 Planned |
| 5 | Search Element | Easy | Linear search | 📅 Planned |
| 6 | Print Alternate Nodes | Easy | Selective traversal | 📅 Planned |
| 7 | Sum of All Nodes | Easy | Accumulation | 📅 Planned |
| 8 | Find Min/Max | Easy | Comparison traversal | 📅 Planned |
| 9 | Zigzag Traversal | Medium | Pattern traversal | 📅 Planned |
| 10 | Spiral Traversal | Medium | Complex pattern | 📅 Planned |

---

## 🔑 Key Traversal Concepts

### 1. Traversal Time Complexities

| Operation | Time | Space | Notes |
|-----------|------|-------|-------|
| **Forward Traversal** | O(n) | O(1) | Standard iteration |
| **Recursive Traversal** | O(n) | O(n) | Stack space |
| **Get Nth Node** | O(n) | O(1) | No shortcuts |
| **Search** | O(n) | O(1) | Linear search |
| **Count Nodes** | O(n) | O(1) | Single pass |
| **Print Reverse** | O(n) | O(n) | Recursion or stack |

**Key Insight:** Unlike arrays, linked lists require O(n) time for random access.

### 2. Iterative vs Recursive Traversal

```java
// Iterative: Better for large lists
public void iterative(ListNode head) {
    while (head != null) {
        process(head);
        head = head.next;
    }
}
// Space: O(1)
// No stack overflow risk

// Recursive: Cleaner for some operations
public void recursive(ListNode head) {
    if (head == null) return;
    process(head);
    recursive(head.next);
}
// Space: O(n) for call stack
// Risk of stack overflow for long lists
```

### 3. Common Traversal Patterns

**Pattern 1: Accumulator**
```java
public int sumNodes(ListNode head) {
    int sum = 0;
    while (head != null) {
        sum += head.val;
        head = head.next;
    }
    return sum;
}
```

**Pattern 2: Early Termination**
```java
public boolean contains(ListNode head, int val) {
    while (head != null) {
        if (head.val == val) return true;  // Early exit
        head = head.next;
    }
    return false;
}
```

**Pattern 3: Multi-Pass**
```java
public double findAverage(ListNode head) {
    // Pass 1: Count nodes
    int count = countNodes(head);
    if (count == 0) return 0;
    
    // Pass 2: Sum values
    int sum = sumNodes(head);
    
    return (double) sum / count;
}
```

**Pattern 4: Position-Based**
```java
public void printAlternate(ListNode head) {
    int index = 0;
    while (head != null) {
        if (index % 2 == 0) {
            System.out.print(head.val + " ");
        }
        head = head.next;
        index++;
    }
}
```

### 4. Reverse Traversal Techniques

```java
// Method 1: Using Recursion
public void printReverse(ListNode head) {
    if (head == null) return;
    printReverse(head.next);
    System.out.print(head.val + " ");
}

// Method 2: Using Stack
public void printReverseIterative(ListNode head) {
    Stack<Integer> stack = new Stack<>();
    while (head != null) {
        stack.push(head.val);
        head = head.next;
    }
    while (!stack.isEmpty()) {
        System.out.print(stack.pop() + " ");
    }
}

// Method 3: Reverse then Traverse
public void printReverseByReversing(ListNode head) {
    head = reverse(head);
    traverse(head);
    head = reverse(head);  // Restore if needed
}
```

---

## 💡 Common Traversal Patterns

### Pattern 1: Single Pass Information Gathering
```java
public class ListInfo {
    int length;
    int min;
    int max;
    int sum;
    
    public ListInfo analyze(ListNode head) {
        if (head == null) return null;
        
        ListInfo info = new ListInfo();
        info.length = 0;
        info.min = Integer.MAX_VALUE;
        info.max = Integer.MIN_VALUE;
        info.sum = 0;
        
        while (head != null) {
            info.length++;
            info.min = Math.min(info.min, head.val);
            info.max = Math.max(info.max, head.val);
            info.sum += head.val;
            head = head.next;
        }
        
        return info;
    }
}
```

### Pattern 2: K-Step Traversal
```java
public void printEveryKth(ListNode head, int k) {
    int index = 0;
    while (head != null) {
        if (index % k == 0) {
            System.out.print(head.val + " ");
        }
        head = head.next;
        index++;
    }
}
```

### Pattern 3: Range Traversal
```java
public void traverseRange(ListNode head, int start, int end) {
    int index = 0;
    while (head != null && index < end) {
        if (index >= start) {
            System.out.print(head.val + " ");
        }
        head = head.next;
        index++;
    }
}
```

### Pattern 4: Conditional Collection
```java
public List<Integer> collectPositives(ListNode head) {
    List<Integer> result = new ArrayList<>();
    while (head != null) {
        if (head.val > 0) {
            result.add(head.val);
        }
        head = head.next;
    }
    return result;
}
```

### Pattern 5: Parallel Traversal
```java
public boolean areEqual(ListNode l1, ListNode l2) {
    while (l1 != null && l2 != null) {
        if (l1.val != l2.val) return false;
        l1 = l1.next;
        l2 = l2.next;
    }
    return l1 == null && l2 == null;
}
```

---

## ⚠️ Common Traversal Mistakes

### 1. Modifying Head Pointer
```java
❌ Incorrect:
public void traverse(ListNode head) {
    while (head != null) {
        System.out.print(head.val);
        head = head.next;  // Lost original head!
    }
    // Can't access list anymore
}

✅ Correct:
public void traverse(ListNode head) {
    ListNode current = head;
    while (current != null) {
        System.out.print(current.val);
        current = current.next;
    }
    // head still points to original
}
```

### 2. Missing Null Checks
```java
❌ Incorrect:
public int getFirst(ListNode head) {
    return head.val;  // NPE if head is null!
}

✅ Correct:
public int getFirst(ListNode head) {
    if (head == null) {
        throw new IllegalArgumentException("Empty list");
    }
    return head.val;
}
```

### 3. Infinite Loops in Cyclic Lists
```java
❌ Incorrect:
public void traverse(ListNode head) {
    while (head != null) {  // Infinite if cycle!
        process(head);
        head = head.next;
    }
}

✅ Correct:
public void traverse(ListNode head) {
    Set<ListNode> visited = new HashSet<>();
    while (head != null && !visited.contains(head)) {
        visited.add(head);
        process(head);
        head = head.next;
    }
}
```

### 4. Off-by-One Errors
```java
❌ Incorrect:
public ListNode getNth(ListNode head, int n) {
    for (int i = 0; i < n; i++) {  // Wrong for 0-indexed
        head = head.next;
    }
    return head;
}

✅ Correct:
public ListNode getNth(ListNode head, int n) {
    // If 0-indexed: loop n times
    // If 1-indexed: loop n-1 times
    for (int i = 0; i < n && head != null; i++) {
        head = head.next;
    }
    return head;
}
```

### 5. Stack Overflow in Deep Recursion
```java
❌ Risky:
public void recursive(ListNode head) {
    if (head == null) return;
    process(head);
    recursive(head.next);  // Stack overflow for long lists
}

✅ Better:
public void iterative(ListNode head) {
    while (head != null) {
        process(head);
        head = head.next;  // No stack issues
    }
}
```

---

## 🎓 Traversal Best Practices

### 1. Use Current Pointer
```java
ListNode current = head;
while (current != null) {
    // Work with current
    current = current.next;
}
```

### 2. Check for Null
```java
if (head == null) return defaultValue;
```

### 3. Prefer Iteration Over Recursion
```java
// Unless recursion makes solution clearer
// Iterative is safer for long lists
```

### 4. Single Pass When Possible
```java
// Gather all info in one traversal
int count = 0, sum = 0, max = Integer.MIN_VALUE;
while (head != null) {
    count++;
    sum += head.val;
    max = Math.max(max, head.val);
    head = head.next;
}
```

### 5. Early Termination for Search
```java
while (head != null) {
    if (found) return result;  // Don't continue unnecessarily
    head = head.next;
}
```

---

## 📚 Topics to be Added

### **Basic Traversal**
- Print linked list
- Print in reverse
- Count nodes
- Find length

### **Search Operations**
- Linear search
- Find nth node
- Find nth from end
- Find middle node

### **Information Gathering**
- Sum of all nodes
- Find min/max
- Average value
- Frequency count

### **Pattern Traversal**
- Print alternate nodes
- Print every kth node
- Zigzag pattern
- Level-order style

### **Advanced Traversal**
- Parallel list traversal
- Conditional traversal
- Range-based traversal
- Custom iteration patterns

---

## 🎯 Learning Path

### Beginner Level
1. **Basic Forward Traversal** - Print all nodes
2. **Count and Search** - Length and find operations
3. **Simple Properties** - Min, max, sum

### Intermediate Level
4. **Recursive Traversal** - Understanding recursion
5. **Reverse Traversal** - Print backwards
6. **Position-Based** - Nth node, alternates

### Advanced Level
7. **Pattern Traversal** - Complex patterns
8. **Multi-List Traversal** - Parallel processing
9. **Optimized Traversal** - Single-pass algorithms

---

## 💻 Essential Traversal Operations

### Basic Operations
```java
void traverse(ListNode head)                    // Print all
int countNodes(ListNode head)                   // Get length
boolean search(ListNode head, int val)          // Find value
ListNode getNth(ListNode head, int n)          // Get nth node
```

### Information Gathering
```java
int findMin(ListNode head)                      // Minimum value
int findMax(ListNode head)                      // Maximum value
int sum(ListNode head)                          // Sum of values
double average(ListNode head)                   // Average value
```

### Pattern Operations
```java
void printReverse(ListNode head)                // Print backwards
void printAlternate(ListNode head)              // Every 2nd node
void printEveryKth(ListNode head, int k)       // Every kth node
```

---

## 🔄 Traversal Comparison

| Aspect | Array | Linked List |
|--------|-------|-------------|
| **Access nth element** | O(1) | O(n) |
| **Forward traverse** | O(n) | O(n) |
| **Reverse traverse** | O(n) | O(n) with stack/recursion |
| **Random access** | Easy | Impossible |
| **Cache performance** | Good | Poor |

**Key Difference:** Arrays have O(1) random access, linked lists must traverse.

---

## 📊 Progress Tracker

**Current Status:**
- Topics Covered: 0 (Folder created)
- Total Problems: 0
- Planned Problems: 10+

**Next Milestones:**
- [ ] Add basic traversal problems (5)
- [ ] Add search operations (3)
- [ ] Add pattern traversal (3)
- [ ] Add advanced traversal (2+)

---

## 🎓 Interview Tips

### Common Questions

1. **"Print a linked list in reverse"**
   - Recursion: O(n) space
   - Stack: O(n) space
   - Reverse list: O(1) space, modifies list

2. **"Find the nth node from end"**
   - Two passes: Count, then access
   - Two pointers: n-gap technique
   - Both O(n) time

3. **"Traverse two lists simultaneously"**
   - Compare corresponding nodes
   - Check if lists are equal
   - Merge operations

### What Interviewers Look For

- ✅ Proper null handling
- ✅ Not modifying head pointer unnecessarily
- ✅ Choosing iteration vs recursion appropriately
- ✅ Single-pass optimization when possible
- ✅ Understanding O(n) access time

---

## 💡 Remember

> "Traversal is the foundation - master it first, and complex algorithms become easier."

**Key Takeaways:**
- Always use a current pointer
- Check for null before access
- Prefer iteration for safety
- Single pass when possible
- Early termination for efficiency

---

<div align="center">

**Master Traversal, Master Linked Lists! 🚶**

*Every algorithm starts with traversal*

*Last Updated: December 2024*

[⬆ Back to Linked List](../README.md)

</div>
