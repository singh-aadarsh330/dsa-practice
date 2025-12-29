# 🔄 Reversal

[![Problems](https://img.shields.io/badge/Problems-0-blue?style=flat-square)](.)
[![Difficulty](https://img.shields.io/badge/Difficulty-Easy%20to%20Hard-orange?style=flat-square)](.)
[![Language](https://img.shields.io/badge/Language-Java-red?style=flat-square)](.)

> Master list reversal techniques. Learn iterative and recursive reversal, partial reversal, and reversal in groups.

---

## 📋 Overview

**Reversal** is one of the most fundamental and frequently asked linked list operations. It involves changing the direction of links between nodes, transforming the list from head-to-tail order into tail-to-head order. Mastering reversal is crucial as it's a building block for many complex linked list algorithms.

**Key Features:**
- **Complete Reversal** - Reverse entire list
- **Partial Reversal** - Reverse between positions
- **Group Reversal** - Reverse in k-node groups
- **In-Place Operation** - O(1) extra space
- **Pointer Manipulation** - Advanced technique practice

**When Reversal is Needed:**
- ✅ Palindrome checking
- ✅ Reordering list elements
- ✅ Addition of numbers (reverse order)
- ✅ Sorting optimizations
- ✅ List manipulation algorithms
- ✅ Interview problem solutions

---

## 🔑 Reversal Fundamentals

### Basic Reversal Pattern

```java
// Iterative Reversal (Most Common)
public ListNode reverse(ListNode head) {
    ListNode prev = null;
    ListNode current = head;
    
    while (current != null) {
        ListNode next = current.next;  // Save next
        current.next = prev;           // Reverse link
        prev = current;                // Move prev
        current = next;                // Move current
    }
    
    return prev;  // New head
}

// Visual:
// Before: 1 → 2 → 3 → 4 → null
// After:  null ← 1 ← 2 ← 3 ← 4
// Return: 4 (new head)
```

### Reversal Variations

```java
// 1. Recursive Reversal
public ListNode reverseRecursive(ListNode head) {
    if (head == null || head.next == null) {
        return head;
    }
    
    ListNode newHead = reverseRecursive(head.next);
    head.next.next = head;
    head.next = null;
    
    return newHead;
}

// 2. Reverse Between Positions
public ListNode reverseBetween(ListNode head, int left, int right) {
    if (head == null || left == right) return head;
    
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode prev = dummy;
    
    // Move to position before left
    for (int i = 0; i < left - 1; i++) {
        prev = prev.next;
    }
    
    // Reverse sublist
    ListNode current = prev.next;
    for (int i = 0; i < right - left; i++) {
        ListNode next = current.next;
        current.next = next.next;
        next.next = prev.next;
        prev.next = next;
    }
    
    return dummy.next;
}

// 3. Reverse in K Groups
public ListNode reverseKGroup(ListNode head, int k) {
    // Check if we have k nodes left
    ListNode curr = head;
    int count = 0;
    while (curr != null && count < k) {
        curr = curr.next;
        count++;
    }
    
    if (count == k) {
        // Reverse k nodes
        curr = reverseKGroup(curr, k);
        while (count-- > 0) {
            ListNode next = head.next;
            head.next = curr;
            curr = head;
            head = next;
        }
        head = curr;
    }
    
    return head;
}

// 4. Reverse Alternate K Nodes
public ListNode reverseAlternateK(ListNode head, int k) {
    ListNode current = head;
    ListNode prev = null;
    ListNode next = null;
    int count = 0;
    
    // Reverse first k nodes
    while (current != null && count < k) {
        next = current.next;
        current.next = prev;
        prev = current;
        current = next;
        count++;
    }
    
    // Skip next k nodes
    if (head != null) {
        head.next = current;
        count = 0;
        while (count < k - 1 && current != null) {
            current = current.next;
            count++;
        }
        
        // Recursively reverse remaining
        if (current != null) {
            current.next = reverseAlternateK(current.next, k);
        }
    }
    
    return prev;
}
```

---

## 📂 Repository Structure

```
Reversal/
│
├── (Problems to be added)
│
└── README.md                # This file
```

---

## 📝 Planned Problems

| # | Problem | Difficulty | Key Concepts | Status |
|---|---------|------------|--------------|--------|
| 1 | Reverse Linked List | Easy | Basic reversal | 📅 Planned |
| 2 | Reverse Linked List II | Medium | Partial reversal | 📅 Planned |
| 3 | Reverse Nodes in K-Group | Hard | Group reversal | 📅 Planned |
| 4 | Palindrome Linked List | Easy | Reversal + comparison | 📅 Planned |
| 5 | Reorder List | Medium | Split + reverse + merge | 📅 Planned |
| 6 | Swap Nodes in Pairs | Medium | Reverse pairs | 📅 Planned |
| 7 | Reverse Alternate K Nodes | Medium | Selective reversal | 📅 Planned |
| 8 | Reverse Between M and N | Medium | Range reversal | 📅 Planned |
| 9 | Add Two Numbers | Medium | Reverse + arithmetic | 📅 Planned |
| 10 | Reverse and Clone | Medium | Reversal + copying | 📅 Planned |

---

## 🔑 Key Reversal Concepts

### 1. Reversal Complexity Analysis

| Operation | Time | Space (Iterative) | Space (Recursive) |
|-----------|------|-------------------|-------------------|
| **Reverse Full List** | O(n) | O(1) | O(n) |
| **Reverse Between** | O(n) | O(1) | O(n) |
| **Reverse K Groups** | O(n) | O(1) | O(n/k) |
| **Reverse Pairs** | O(n) | O(1) | O(n) |

**Key Insight:** Iterative reversal is always O(1) space, recursive uses O(n) stack space.

### 2. Three-Pointer Technique

```java
// The classic pattern: prev, current, next
ListNode prev = null;
ListNode current = head;
ListNode next = null;

while (current != null) {
    next = current.next;    // 1. Save next
    current.next = prev;    // 2. Reverse link
    prev = current;         // 3. Move prev forward
    current = next;         // 4. Move current forward
}

return prev;  // New head

// Visualization at each step:
// Step 0: null ← | → 1 → 2 → 3 → null
//              prev  cur
//
// Step 1: null ← 1 | → 2 → 3 → null
//                prev  cur
//
// Step 2: null ← 1 ← 2 | → 3 → null
//                    prev  cur
//
// Step 3: null ← 1 ← 2 ← 3 |
//                        prev  cur(null)
```

### 3. Recursive Reversal Explanation

```java
public ListNode reverse(ListNode head) {
    // Base case
    if (head == null || head.next == null) {
        return head;
    }
    
    // Recursive call
    ListNode newHead = reverse(head.next);
    
    // Reverse current node's link
    head.next.next = head;
    head.next = null;
    
    return newHead;
}

// How it works:
// Input: 1 → 2 → 3 → null
//
// Call stack:
// reverse(1) calls reverse(2)
// reverse(2) calls reverse(3)
// reverse(3) returns 3 (base case)
//
// Unwinding:
// In reverse(2): 3.next = 2, 2.next = null
// In reverse(1): 2.next = 1, 1.next = null
//
// Result: 3 → 2 → 1 → null
```

### 4. Common Reversal Patterns

**Pattern 1: Full Reversal**
```java
// Reverse entire list
ListNode reversed = reverse(head);
```

**Pattern 2: Partial Reversal**
```java
// Reverse from position m to n
ListNode result = reverseBetween(head, m, n);
```

**Pattern 3: Group Reversal**
```java
// Reverse every k nodes
ListNode result = reverseKGroup(head, k);
```

**Pattern 4: Conditional Reversal**
```java
// Reverse only if condition met
if (shouldReverse(head)) {
    head = reverse(head);
}
```

---

## 💡 Common Reversal Patterns

### Pattern 1: Reverse and Compare (Palindrome)
```java
public boolean isPalindrome(ListNode head) {
    // Find middle
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    
    // Reverse second half
    ListNode secondHalf = reverse(slow);
    
    // Compare
    ListNode p1 = head, p2 = secondHalf;
    while (p2 != null) {
        if (p1.val != p2.val) return false;
        p1 = p1.next;
        p2 = p2.next;
    }
    
    return true;
}
```

### Pattern 2: Reverse in Place with Dummy
```java
public ListNode reverseWithDummy(ListNode head) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    
    // Reverse logic using dummy
    ListNode prev = dummy;
    ListNode current = head;
    
    while (current != null) {
        ListNode next = current.next;
        current.next = prev.next;
        prev.next = current;
        current = next;
    }
    
    return dummy.next;
}
```

### Pattern 3: Swap Pairs
```java
public ListNode swapPairs(ListNode head) {
    if (head == null || head.next == null) {
        return head;
    }
    
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode prev = dummy;
    
    while (prev.next != null && prev.next.next != null) {
        ListNode first = prev.next;
        ListNode second = first.next;
        
        // Swap
        first.next = second.next;
        second.next = first;
        prev.next = second;
        
        // Move prev
        prev = first;
    }
    
    return dummy.next;
}
```

### Pattern 4: Reorder List (Split, Reverse, Merge)
```java
public void reorderList(ListNode head) {
    if (head == null || head.next == null) return;
    
    // Find middle
    ListNode slow = head, fast = head;
    while (fast.next != null && fast.next.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    
    // Split and reverse second half
    ListNode secondHalf = reverse(slow.next);
    slow.next = null;
    
    // Merge alternately
    ListNode first = head, second = secondHalf;
    while (second != null) {
        ListNode tmp1 = first.next;
        ListNode tmp2 = second.next;
        
        first.next = second;
        second.next = tmp1;
        
        first = tmp1;
        second = tmp2;
    }
}
```

### Pattern 5: Reverse and Add
```java
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    // Reverse both lists
    l1 = reverse(l1);
    l2 = reverse(l2);
    
    // Add digits
    ListNode dummy = new ListNode(0);
    ListNode current = dummy;
    int carry = 0;
    
    while (l1 != null || l2 != null || carry > 0) {
        int sum = carry;
        if (l1 != null) {
            sum += l1.val;
            l1 = l1.next;
        }
        if (l2 != null) {
            sum += l2.val;
            l2 = l2.next;
        }
        
        carry = sum / 10;
        current.next = new ListNode(sum % 10);
        current = current.next;
    }
    
    // Reverse result
    return reverse(dummy.next);
}
```

---

## ⚠️ Common Reversal Mistakes

### 1. Losing Reference to Next Node
```java
❌ Incorrect:
ListNode prev = null, current = head;
while (current != null) {
    current.next = prev;  // Lost rest of list!
    prev = current;
    current = current.next;  // current.next is now prev!
}

✅ Correct:
ListNode prev = null, current = head;
while (current != null) {
    ListNode next = current.next;  // Save first!
    current.next = prev;
    prev = current;
    current = next;
}
```

### 2. Forgetting to Update Head
```java
❌ Incorrect:
public void reverse(ListNode head) {
    // Reverses but doesn't return new head
}

✅ Correct:
public ListNode reverse(ListNode head) {
    // ... reversal logic
    return prev;  // Return new head
}
```

### 3. Not Handling Edge Cases
```java
❌ Incorrect:
public ListNode reverse(ListNode head) {
    // Fails for null or single node
    ListNode prev = null, current = head;
    // ...
}

✅ Correct:
public ListNode reverse(ListNode head) {
    if (head == null || head.next == null) {
        return head;
    }
    // ... reversal logic
}
```

### 4. Incorrect Recursive Base Case
```java
❌ Incorrect:
public ListNode reverse(ListNode head) {
    if (head == null) return null;
    // Missing check for head.next
    ListNode newHead = reverse(head.next);
    head.next.next = head;  // NPE if head.next is null!
}

✅ Correct:
public ListNode reverse(ListNode head) {
    if (head == null || head.next == null) {
        return head;
    }
    // Safe to access head.next
}
```

### 5. Forgetting to Terminate List
```java
❌ Incorrect:
// In recursive reversal
ListNode newHead = reverse(head.next);
head.next.next = head;
// Forgot: head.next = null
return newHead;  // Creates cycle!

✅ Correct:
ListNode newHead = reverse(head.next);
head.next.next = head;
head.next = null;  // Terminate!
return newHead;
```

---

## 🎓 Reversal Best Practices

### 1. Always Save Next Before Reversing
```java
ListNode next = current.next;  // Critical step
current.next = prev;
```

### 2. Handle Edge Cases First
```java
if (head == null || head.next == null) {
    return head;
}
```

### 3. Use Dummy Nodes for Complex Reversals
```java
ListNode dummy = new ListNode(0);
dummy.next = head;
// Simplifies edge cases
```

### 4. Prefer Iterative for Production
```java
// Iterative: O(1) space, no stack overflow
// Recursive: O(n) space, risk of stack overflow
```

### 5. Test with Multiple Lengths
```java
// Test with:
- null
- Single node
- Two nodes
- Three nodes
- Many nodes
```

---

## 📚 Topics to be Added

### **Basic Reversal**
- Reverse entire list (iterative)
- Reverse entire list (recursive)
- Check if reverse equals original
- Reverse and print

### **Partial Reversal**
- Reverse between positions
- Reverse first k nodes
- Reverse last k nodes
- Reverse middle portion

### **Group Reversal**
- Reverse in k-groups
- Reverse alternate k nodes
- Swap pairs
- Reverse in triplets

### **Applications**
- Palindrome check
- Reorder list
- Add two numbers
- Reverse and merge

### **Advanced Reversal**
- Reverse doubly linked list
- Reverse multilevel list
- Reverse with constraints
- Optimal reversal patterns

---

## 🎯 Learning Path

### Beginner Level
1. **Iterative Full Reversal** - Three-pointer technique
2. **Recursive Full Reversal** - Understanding recursion
3. **Basic Applications** - Palindrome check

### Intermediate Level
4. **Partial Reversal** - Between positions
5. **Swap Pairs** - Reverse in groups of 2
6. **Reorder List** - Complex multi-step

### Advanced Level
7. **K-Group Reversal** - Reverse in groups of k
8. **Optimal Solutions** - Space and time optimization
9. **Complex Applications** - Multi-reversal algorithms

---

## 💻 Essential Reversal Operations

### Basic Operations
```java
ListNode reverse(ListNode head)                 // Full reversal
ListNode reverseRecursive(ListNode head)       // Recursive reversal
```

### Partial Operations
```java
ListNode reverseBetween(ListNode head, int m, int n)  // Reverse range
ListNode reverseFirstK(ListNode head, int k)          // Reverse first k
```

### Group Operations
```java
ListNode reverseKGroup(ListNode head, int k)    // Reverse in k-groups
ListNode swapPairs(ListNode head)               // Swap adjacent pairs
```

### Applications
```java
boolean isPalindrome(ListNode head)             // Check palindrome
void reorderList(ListNode head)                 // Reorder operation
```

---

## 🔄 Reversal Comparison

| Approach | Time | Space | Difficulty | Use Case |
|----------|------|-------|------------|----------|
| **Iterative** | O(n) | O(1) | Easy | Production code |
| **Recursive** | O(n) | O(n) | Medium | Small lists, learning |
| **In-place** | O(n) | O(1) | Easy | Memory constrained |
| **With Stack** | O(n) | O(n) | Easy | Doesn't modify links |

---

## 📊 Progress Tracker

**Current Status:**
- Topics Covered: 0 (Folder created)
- Total Problems: 0
- Planned Problems: 10+

**Next Milestones:**
- [ ] Add basic reversal problems (3)
- [ ] Add partial reversal (2)
- [ ] Add group reversal (3)
- [ ] Add applications (2+)

---

## 🎓 Interview Tips

### Common Questions

1. **"Reverse a linked list iteratively"**
   - Three pointers: prev, current, next
   - O(n) time, O(1) space
   - Most common interview question

2. **"Reverse a linked list recursively"**
   - Understand call stack
   - O(n) time, O(n) space
   - Good for small lists

3. **"Reverse nodes in k-groups"**
   - Check if k nodes available
   - Reverse k nodes at a time
   - Hard difficulty, FAANG favorite

4. **"Check if palindrome"**
   - Find middle, reverse second half
   - Compare two halves
   - O(n) time, O(1) space

### What Interviewers Look For

- ✅ Proper three-pointer technique
- ✅ Edge case handling
- ✅ Space complexity awareness
- ✅ Iterative vs recursive trade-offs
- ✅ Clean, bug-free implementation

---

## 💡 Remember

> "Reversal is the Swiss Army knife of linked list algorithms - master it, and many problems become trivial."

**Key Takeaways:**
- Always save next before reversing
- Three-pointer technique is fundamental
- Iterative is safer than recursive
- Handle null and single-node cases
- Test with various list lengths

---

<div align="center">

**Master Reversal, Unlock Linked List Mastery! 🔄**

*Reverse, Manipulate, Solve*

*Last Updated: December 2024*

[⬆ Back to Linked List](../README.md)

</div>
