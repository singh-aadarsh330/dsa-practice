# 👉👈 Two Pointer

[![Problems](https://img.shields.io/badge/Problems-0-blue?style=flat-square)](.)
[![Difficulty](https://img.shields.io/badge/Difficulty-Easy%20to%20Hard-orange?style=flat-square)](.)
[![Language](https://img.shields.io/badge/Language-Java-red?style=flat-square)](.)

> Master the two-pointer technique for linked lists. Learn fast-slow, same-speed, and gap-based pointer patterns for efficient problem-solving.

---

## 📋 Overview

**Two Pointer** technique is one of the most powerful patterns for solving linked list problems efficiently. By maintaining two pointers that move through the list at different speeds or with different gaps, we can solve complex problems with optimal time and space complexity.

**Key Features:**
- **Fast-Slow Pattern** - Pointers moving at different speeds
- **Same-Speed Pattern** - Pointers with fixed gap
- **Meet-in-Middle** - Pointers from opposite ends
- **Window Sliding** - Dynamic gap between pointers
- **Optimal Complexity** - Often O(n) time, O(1) space

**When Two Pointer is Useful:**
- ✅ Finding middle of list
- ✅ Cycle detection
- ✅ Removing nth node from end
- ✅ Checking palindromes
- ✅ Finding intersection points
- ✅ Partitioning lists
- ✅ Removing duplicates

---

## 🔑 Two Pointer Fundamentals

### Core Pointer Patterns

```java
// Pattern 1: Fast-Slow (Different Speeds)
public ListNode findMiddle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;
    
    while (fast != null && fast.next != null) {
        slow = slow.next;           // 1 step
        fast = fast.next.next;      // 2 steps
    }
    
    return slow;  // Middle node
}

// Pattern 2: Fixed Gap (Runner Technique)
public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode first = dummy;
    ListNode second = dummy;
    
    // Create gap of n+1
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

// Pattern 3: Same Starting Point, Different Conditions
public ListNode partition(ListNode head, int x) {
    ListNode lessHead = new ListNode(0);
    ListNode greaterHead = new ListNode(0);
    ListNode less = lessHead;
    ListNode greater = greaterHead;
    
    while (head != null) {
        if (head.val < x) {
            less.next = head;
            less = less.next;
        } else {
            greater.next = head;
            greater = greater.next;
        }
        head = head.next;
    }
    
    greater.next = null;
    less.next = greaterHead.next;
    return lessHead.next;
}

// Pattern 4: Parallel List Traversal
public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode current = dummy;
    
    while (l1 != null && l2 != null) {
        if (l1.val <= l2.val) {
            current.next = l1;
            l1 = l1.next;
        } else {
            current.next = l2;
            l2 = l2.next;
        }
        current = current.next;
    }
    
    current.next = (l1 != null) ? l1 : l2;
    return dummy.next;
}
```

### Speed Variations

```java
// 1x and 2x speeds (Most Common)
slow = slow.next;
fast = fast.next.next;

// 1x and 3x speeds
slow = slow.next;
fast = fast.next.next.next;

// Custom speeds
slow = slow.next;
for (int i = 0; i < k; i++) {
    if (fast != null) fast = fast.next;
}
```

---

## 📂 Repository Structure

```
TwoPointer/
│
├── (Problems to be added)
│
└── README.md                # This file
```

---

## 📝 Planned Problems

| # | Problem | Difficulty | Key Concepts | Status |
|---|---------|------------|--------------|--------|
| 1 | Middle of Linked List | Easy | Fast-slow | 📅 Planned |
| 2 | Remove Nth Node from End | Medium | Fixed gap | 📅 Planned |
| 3 | Linked List Cycle | Easy | Fast-slow detection | 📅 Planned |
| 4 | Palindrome Linked List | Easy | Middle + reversal | 📅 Planned |
| 5 | Intersection of Two Lists | Easy | Length alignment | 📅 Planned |
| 6 | Merge Two Sorted Lists | Easy | Parallel traversal | 📅 Planned |
| 7 | Partition List | Medium | Dual pointers | 📅 Planned |
| 8 | Sort List | Medium | Merge sort pointers | 📅 Planned |
| 9 | Reorder List | Medium | Multiple patterns | 📅 Planned |
| 10 | Split Linked List | Medium | Fast-slow variation | 📅 Planned |

---

## 🔑 Key Two Pointer Concepts

### 1. Pointer Speed Patterns

| Pattern | Slow Speed | Fast Speed | Use Case | Example |
|---------|-----------|-----------|----------|---------|
| **Classic Fast-Slow** | 1x | 2x | Middle, cycle | Find middle node |
| **Fixed Gap** | 1x | 1x (ahead by k) | Kth from end | Remove nth from end |
| **Different Lists** | 1x each | N/A | Merge, compare | Merge sorted lists |
| **Triple Speed** | 1x | 3x | Special detection | Rare applications |

### 2. Why Fast-Slow Works

```java
// Mathematical Proof for Finding Middle:

For list of length n:
- When fast reaches end, slow is at n/2
- Fast moves 2x speed of slow
- When fast travels n nodes, slow travels n/2 nodes

Example: 1 → 2 → 3 → 4 → 5
Step 0: S,F at 1
Step 1: S at 2, F at 3
Step 2: S at 3, F at 5 (end)
Result: S at middle (3)

For cycle detection:
- If cycle exists, fast will eventually lap slow
- They must meet inside the cycle
- If no cycle, fast reaches null
```

### 3. Gap-Based Two Pointer

```java
// Finding nth node from end using n-gap:

Steps:
1. Move first pointer n steps ahead
2. Move both pointers together
3. When first reaches end, second is at (length - n)

Example: Remove 2nd from end in 1→2→3→4→5
Step 1: first at 3, second at 1 (gap of 2)
Step 2: Move together until first reaches end
        first at null, second at 3
Step 3: second.next is the node to remove (4)

Why it works:
- Gap between pointers is maintained
- When first reaches end (at null after tail)
- Second is exactly n nodes before end
```

### 4. Common Two Pointer Applications

**Application 1: Find Middle**
```java
// Fast-slow pattern
ListNode slow = head, fast = head;
while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
}
return slow;  // Middle node
```

**Application 2: Check Palindrome**
```java
// Find middle + reverse + compare
ListNode middle = findMiddle(head);
ListNode reversed = reverse(middle);
while (reversed != null) {
    if (head.val != reversed.val) return false;
    head = head.next;
    reversed = reversed.next;
}
return true;
```

**Application 3: Detect Cycle**
```java
// Floyd's algorithm
ListNode slow = head, fast = head;
while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
    if (slow == fast) return true;  // Cycle found
}
return false;
```

**Application 4: Find Intersection**
```java
// Align by length difference
int lenA = length(headA), lenB = length(headB);
while (lenA > lenB) {
    headA = headA.next;
    lenA--;
}
while (lenB > lenA) {
    headB = headB.next;
    lenB--;
}
while (headA != headB) {
    headA = headA.next;
    headB = headB.next;
}
return headA;  // Intersection or null
```

---

## 💡 Common Two Pointer Patterns

### Pattern 1: Fast-Slow for Middle
```java
public ListNode findMiddle(ListNode head) {
    if (head == null) return null;
    
    ListNode slow = head;
    ListNode fast = head;
    
    // For even length, returns second middle
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    
    return slow;
}

// Variation: Return first middle for even length
public ListNode findFirstMiddle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head.next;  // Start fast ahead by 1
    
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    
    return slow;
}
```

### Pattern 2: N-Gap Runner
```java
public ListNode getNthFromEnd(ListNode head, int n) {
    ListNode first = head;
    ListNode second = head;
    
    // Move first n steps ahead
    for (int i = 0; i < n; i++) {
        if (first == null) return null;  // List too short
        first = first.next;
    }
    
    // Move both together
    while (first != null) {
        first = first.next;
        second = second.next;
    }
    
    return second;
}
```

### Pattern 3: Dual List Traversal
```java
public ListNode mergeSorted(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode tail = dummy;
    
    // Both pointers move through different lists
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

### Pattern 4: Split and Process
```java
public ListNode[] splitList(ListNode head) {
    if (head == null) return new ListNode[]{null, null};
    
    // Find middle
    ListNode slow = head;
    ListNode fast = head;
    ListNode prev = null;
    
    while (fast != null && fast.next != null) {
        prev = slow;
        slow = slow.next;
        fast = fast.next.next;
    }
    
    // Split
    if (prev != null) prev.next = null;
    
    return new ListNode[]{head, slow};
}
```

### Pattern 5: Window Technique
```java
public boolean hasSublistSum(ListNode head, int target) {
    ListNode left = head;
    ListNode right = head;
    int sum = 0;
    
    while (right != null) {
        sum += right.val;
        
        while (sum > target && left != right) {
            sum -= left.val;
            left = left.next;
        }
        
        if (sum == target) return true;
        right = right.next;
    }
    
    return false;
}
```

---

## ⚠️ Common Two Pointer Mistakes

### 1. Incorrect Null Checks
```java
❌ Incorrect:
while (fast != null) {
    slow = slow.next;
    fast = fast.next.next;  // NPE if fast.next is null
}

✅ Correct:
while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
}
```

### 2. Wrong Initial Positions
```java
❌ Incorrect (for removing nth from end):
ListNode first = head;
ListNode second = head;
for (int i = 0; i < n; i++) {  // Should be n+1
    first = first.next;
}

✅ Correct:
ListNode dummy = new ListNode(0);
dummy.next = head;
ListNode first = dummy;
ListNode second = dummy;
for (int i = 0; i <= n; i++) {  // n+1 steps
    first = first.next;
}
```

### 3. Not Handling Edge Cases
```java
❌ Incorrect:
public ListNode findMiddle(ListNode head) {
    ListNode slow = head, fast = head;
    // Crashes on empty list
}

✅ Correct:
public ListNode findMiddle(ListNode head) {
    if (head == null) return null;
    ListNode slow = head, fast = head;
    // ...
}
```

### 4. Off-by-One in Gap
```java
❌ Incorrect:
// To remove nth from end, need n+1 gap
for (int i = 0; i < n; i++) {
    first = first.next;
}

✅ Correct:
// Use dummy and move n+1 times
for (int i = 0; i <= n; i++) {
    first = first.next;
}
```

### 5. Comparing Wrong Nodes
```java
❌ Incorrect (cycle detection):
if (slow.val == fast.val) return true;  // Values can be equal!

✅ Correct:
if (slow == fast) return true;  // Compare references
```

---

## 🎓 Two Pointer Best Practices

### 1. Always Check Both Pointers
```java
// For fast pointer moving 2 steps
while (fast != null && fast.next != null)
```

### 2. Use Dummy Nodes for Deletions
```java
ListNode dummy = new ListNode(0);
dummy.next = head;
// Work with dummy to simplify edge cases
```

### 3. Initialize Carefully
```java
// For middle: both start at head
ListNode slow = head, fast = head;

// For gap: create gap first
ListNode first = head, second = head;
for (int i = 0; i < gap; i++) {
    first = first.next;
}
```

### 4. Handle List Length Edge Cases
```java
// Empty list
if (head == null) return null;

// Single node
if (head.next == null) return head;

// List shorter than gap
if (length < gap) return null;
```

### 5. Test with Multiple Lengths
```java
// Test with:
- Null
- Single node
- Two nodes
- Odd length (3, 5, 7)
- Even length (2, 4, 6)
```

---

## 📚 Topics to be Added

### **Fast-Slow Pattern**
- Find middle of list
- Detect cycle
- Find cycle start
- Check palindrome

### **Fixed Gap Pattern**
- Remove nth from end
- Kth node from end
- Rotate list
- Split at position

### **Parallel Traversal**
- Merge two sorted lists
- Intersection of lists
- Compare two lists
- Zip lists together

### **Advanced Patterns**
- Multiple pointer coordination
- Conditional movement
- Window sliding
- Custom gap strategies

---

## 🎯 Learning Path

### Beginner Level
1. **Find Middle** - Basic fast-slow
2. **Nth from End** - Fixed gap technique
3. **Merge Lists** - Parallel traversal

### Intermediate Level
4. **Cycle Detection** - Fast-slow meeting
5. **Palindrome Check** - Middle + reversal
6. **List Partition** - Dual pointer separation

### Advanced Level
7. **Intersection Finding** - Length alignment
8. **Complex Reordering** - Multiple patterns
9. **Optimization Techniques** - Custom patterns

---

## 💻 Essential Two Pointer Operations

### Fast-Slow Operations
```java
ListNode findMiddle(ListNode head)              // Find middle
boolean hasCycle(ListNode head)                 // Detect cycle
ListNode detectCycle(ListNode head)             // Find cycle start
```

### Gap-Based Operations
```java
ListNode removeNthFromEnd(ListNode head, int n) // Remove from end
ListNode getNthFromEnd(ListNode head, int n)   // Get kth from end
ListNode rotateRight(ListNode head, int k)      // Rotate list
```

### Parallel Operations
```java
ListNode mergeTwoLists(ListNode l1, ListNode l2)  // Merge sorted
ListNode getIntersection(ListNode l1, ListNode l2) // Find intersection
boolean areEqual(ListNode l1, ListNode l2)        // Compare lists
```

---

## 🔄 Two Pointer vs Single Pointer

| Aspect | Single Pointer | Two Pointer |
|--------|---------------|-------------|
| **Find Middle** | O(n) + O(n) two-pass | O(n) one-pass |
| **Nth from End** | O(n) + O(n) | O(n) one-pass |
| **Cycle Detection** | O(n) space (HashSet) | O(1) space |
| **Complexity** | Often requires extra space | Usually O(1) space |

**Key Advantage:** Two pointers often reduce space complexity from O(n) to O(1).

---

## 📊 Progress Tracker

**Current Status:**
- Topics Covered: 0 (Folder created)
- Total Problems: 0
- Planned Problems: 10+

**Next Milestones:**
- [ ] Add fast-slow problems (4)
- [ ] Add gap-based problems (3)
- [ ] Add parallel traversal (3)
- [ ] Add advanced patterns (2+)

---

## 🎓 Interview Tips

### Common Questions

1. **"Find middle of linked list"**
   - Fast-slow pointer
   - O(n) time, O(1) space
   - Most fundamental two-pointer problem

2. **"Detect if list has cycle"**
   - Fast-slow until they meet
   - If they meet, cycle exists
   - O(n) time, O(1) space

3. **"Remove nth node from end"**
   - Create n-gap between pointers
   - Move together until first reaches end
   - Second points to node before target

4. **"Check if palindrome"**
   - Find middle with fast-slow
   - Reverse second half
   - Compare two halves

### What Interviewers Look For

- ✅ Understanding of pointer speeds
- ✅ Proper null checking
- ✅ Edge case handling
- ✅ O(1) space optimization
- ✅ Clean implementation

### Common Follow-ups

- "What if we use 1x and 3x speeds?"
- "How to find cycle start, not just detect?"
- "What if list length is unknown?"
- "Can you do it in one pass?"

---

## 💡 Remember

> "Two pointers turn O(n²) problems into O(n) problems, and O(n) space into O(1) space."

**Key Takeaways:**
- Fast-slow for middle and cycles
- Fixed gap for nth from end
- Parallel for merging
- Always check both pointers for null
- Test with various list lengths

**Master Pattern:**
1. Identify which pattern applies
2. Initialize pointers correctly
3. Move with appropriate speeds/gaps
4. Handle null cases
5. Return correct result

---

<div align="center">

**Master Two Pointers, Solve with Elegance! 👉👈**

*One pointer is good, two pointers are better*

*Last Updated: December 2024*

[⬆ Back to Linked List](../README.md)

</div>
