# 🔄 Cycle Detection

[![Problems](https://img.shields.io/badge/Problems-1-blue?style=flat-square)](.)
[![Difficulty](https://img.shields.io/badge/Difficulty-Easy%20to%20Medium-orange?style=flat-square)](.)
[![Language](https://img.shields.io/badge/Language-Java-red?style=flat-square)](.)

> Master cycle detection algorithms in linked lists. Learn Floyd's Cycle Detection and two-pointer techniques for efficient cycle identification.

---

## 📋 Overview

**Cycle Detection** is a fundamental problem in linked list manipulation where we need to identify if a cycle exists in the list and, if so, locate important points like the cycle's starting node. This is solved optimally using Floyd's "Tortoise and Hare" algorithm with two pointers moving at different speeds.

**Key Features:**
- **Optimal Detection** - O(1) space complexity
- **Two Pointer Technique** - Fast and slow pointers
- **Mathematical Foundation** - Proof-based algorithm
- **Multiple Applications** - Beyond linked lists
- **Interview Favorite** - Common in technical interviews

**When Cycle Detection is Needed:**
- ✅ Validating linked list integrity
- ✅ Detecting infinite loops in sequences
- ✅ Finding duplicates in arrays (array as list)
- ✅ Memory leak detection
- ✅ Graph cycle detection
- ❌ Trees (by definition, trees have no cycles)
- ❌ Arrays with random access (other methods better)

---

## 🔑 Cycle Detection Basics

### What is a Cycle?

A **cycle** in a linked list occurs when a node's `next` pointer points to a previous node in the list, creating a loop.

```
No Cycle:
1 → 2 → 3 → 4 → null

With Cycle:
1 → 2 → 3 → 4
    ↑_________|
    
Self-Loop:
1 ↺

Complete Cycle:
1 → 2 → 3
↑_______|
```

### Cycle Characteristics

```java
// Cycle properties
1. Entry Point: First node that's part of the cycle
2. Cycle Length: Number of nodes in the cycle
3. Distance to Cycle: Number of nodes before cycle starts
4. Meeting Point: Where fast and slow pointers meet

Example: 1 → 2 → 3 → 4 → 5 → 6
              ↑_______________|

- Entry Point: Node 2
- Cycle Length: 5 nodes (2,3,4,5,6)
- Distance to Cycle: 1 node (node 1)
- Meeting Point: Depends on starting positions
```

### Floyd's Cycle Detection Algorithm

```java
// Phase 1: Detect if cycle exists
ListNode slow = head, fast = head;

while (fast != null && fast.next != null) {
    slow = slow.next;           // Move 1 step
    fast = fast.next.next;      // Move 2 steps
    
    if (slow == fast) {
        return true;  // Cycle detected
    }
}
return false;  // No cycle

// Phase 2: Find cycle start (if cycle exists)
slow = head;
while (slow != fast) {
    slow = slow.next;
    fast = fast.next;
}
return slow;  // Cycle start node
```

---

## 📂 Repository Structure

```
CycleDetection/
│
├── CycleStart/              # Find starting node of cycle
│   └── README.md
│
└── README.md                # This file
```

---

## 📝 Problems in This Folder

| # | Problem | Folder | Difficulty | Key Concepts | Status |
|---|---------|--------|------------|--------------|--------|
| 1 | Find Cycle Start | [CycleStart/](./CycleStart/) | Medium | Floyd's algorithm, Two pointers, Cycle entry | ✅ |

---

## 🎯 Problem: Find Cycle Start

### Problem Overview

Given a linked list, detect if a cycle exists and return the node where the cycle begins. If no cycle exists, return null.

**Key Concepts:**
- Floyd's Cycle Detection (Tortoise and Hare)
- Two-pointer technique with different speeds
- Mathematical proof of algorithm correctness
- O(1) space complexity solution

**See detailed solution:** [CycleStart/README.md](./CycleStart/README.md)

---

## 🔑 Key Cycle Detection Concepts

### 1. Detection Methods Comparison

| Method | Time | Space | Modifies List | Accuracy |
|--------|------|-------|---------------|----------|
| **Floyd's Algorithm** | O(n) | O(1) | No | 100% |
| **HashSet** | O(n) | O(n) | No | 100% |
| **Node Marking** | O(n) | O(1) | Yes | 100% |
| **Pointer Reversal** | O(n) | O(1) | Yes (temp) | 100% |

**Floyd's Algorithm** is the preferred method in interviews and production.

### 2. Floyd's Algorithm - Deep Dive

**Phase 1: Cycle Detection**
```java
// Why does this work?
// If there's a cycle, fast will eventually lap slow
// They must meet inside the cycle

ListNode slow = head;
ListNode fast = head;

while (fast != null && fast.next != null) {
    slow = slow.next;        // 1 step per iteration
    fast = fast.next.next;   // 2 steps per iteration
    
    if (slow == fast) {
        return true;  // They met, cycle exists
    }
}
return false;  // Fast reached null, no cycle
```

**Phase 2: Finding Cycle Start**
```java
// Mathematical proof shows:
// Distance from head to cycle start == 
// Distance from meeting point to cycle start

slow = head;  // Reset slow to head
// fast stays at meeting point

while (slow != fast) {
    slow = slow.next;  // Both move 1 step
    fast = fast.next;
}

return slow;  // This is the cycle start
```

**Mathematical Proof:**
```
Let:
L = distance from head to cycle start
C = cycle length  
k = distance from cycle start to meeting point

When pointers meet:
- Slow traveled: L + k
- Fast traveled: L + k + nC (where n = number of complete cycles)

Since Fast moves 2x speed of Slow:
2(L + k) = L + k + nC
2L + 2k = L + k + nC
L + k = nC
L = nC - k

This means: distance from head to start (L) equals
distance from meeting point back to start (nC - k)

Therefore, moving both pointers at same speed will
make them meet at the cycle start.
```

### 3. Two Pointer Speed Variations

```java
// Standard Floyd's: 1x and 2x speed
slow = slow.next;
fast = fast.next.next;

// Alternative: 1x and 3x speed (less common)
slow = slow.next;
fast = fast.next.next.next;

// Why 2x is optimal:
// - Guaranteed to detect cycle
// - Minimizes iterations in cycle
// - Simplest mathematical proof
```

### 4. Cycle Properties Calculation

```java
// Get cycle length
public int getCycleLength(ListNode meetingPoint) {
    int length = 1;
    ListNode current = meetingPoint.next;
    
    while (current != meetingPoint) {
        length++;
        current = current.next;
    }
    
    return length;
}

// Get distance to cycle start
public int getDistanceToStart(ListNode head, ListNode cycleStart) {
    int distance = 0;
    ListNode current = head;
    
    while (current != cycleStart) {
        distance++;
        current = current.next;
    }
    
    return distance;
}

// Get all nodes in cycle
public List<ListNode> getCycleNodes(ListNode cycleStart) {
    List<ListNode> nodes = new ArrayList<>();
    ListNode current = cycleStart;
    
    do {
        nodes.add(current);
        current = current.next;
    } while (current != cycleStart);
    
    return nodes;
}
```

---

## 💡 Common Cycle Detection Patterns

### Pattern 1: Basic Cycle Detection (Boolean)
```java
public boolean hasCycle(ListNode head) {
    if (head == null || head.next == null) {
        return false;
    }
    
    ListNode slow = head;
    ListNode fast = head;
    
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        
        if (slow == fast) {
            return true;
        }
    }
    
    return false;
}
```

### Pattern 2: Find Cycle Start
```java
public ListNode detectCycle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;
    
    // Phase 1: Detect cycle
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        
        if (slow == fast) {
            break;
        }
    }
    
    // No cycle
    if (fast == null || fast.next == null) {
        return null;
    }
    
    // Phase 2: Find start
    slow = head;
    while (slow != fast) {
        slow = slow.next;
        fast = fast.next;
    }
    
    return slow;
}
```

### Pattern 3: Remove Cycle
```java
public void removeCycle(ListNode head) {
    ListNode cycleStart = detectCycle(head);
    
    if (cycleStart == null) {
        return;  // No cycle to remove
    }
    
    // Find the node that points back to cycle start
    ListNode current = cycleStart;
    while (current.next != cycleStart) {
        current = current.next;
    }
    
    // Break the cycle
    current.next = null;
}
```

### Pattern 4: Cycle Length and Position
```java
public int[] getCycleInfo(ListNode head) {
    // Returns [cycleExists, cycleStart, cycleLength]
    
    ListNode slow = head, fast = head;
    
    // Detect cycle
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        
        if (slow == fast) {
            // Calculate length
            int length = 1;
            ListNode temp = slow.next;
            while (temp != slow) {
                length++;
                temp = temp.next;
            }
            
            // Find start position
            slow = head;
            int startPos = 0;
            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
                startPos++;
            }
            
            return new int[]{1, startPos, length};
        }
    }
    
    return new int[]{0, -1, 0};  // No cycle
}
```

### Pattern 5: Using HashSet (Alternative)
```java
public ListNode detectCycleHashSet(ListNode head) {
    Set<ListNode> visited = new HashSet<>();
    ListNode current = head;
    
    while (current != null) {
        if (visited.contains(current)) {
            return current;  // First revisited node is cycle start
        }
        visited.add(current);
        current = current.next;
    }
    
    return null;
}
```

---

## ⚠️ Common Mistakes

### 1. Incorrect Null Checks
```java
❌ Incorrect:
while (fast != null) {
    slow = slow.next;
    fast = fast.next.next;  // NullPointerException!
}

✅ Correct:
while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;  // Safe
}
```

### 2. Forgetting to Reset Pointer
```java
❌ Incorrect:
// After detecting cycle
while (slow != fast) {
    slow = slow.next;
    fast = fast.next;  // Wrong! Need to reset slow first
}

✅ Correct:
slow = head;  // Reset to head
while (slow != fast) {
    slow = slow.next;
    fast = fast.next;
}
```

### 3. Wrong Pointer Speed in Phase 2
```java
❌ Incorrect:
slow = head;
while (slow != fast) {
    slow = slow.next;
    fast = fast.next.next;  // Wrong! Should be 1 step
}

✅ Correct:
slow = head;
while (slow != fast) {
    slow = slow.next;
    fast = fast.next;  // Both move 1 step
}
```

### 4. Not Handling Empty List
```java
❌ Incorrect:
public boolean hasCycle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;
    // Will fail for null head
}

✅ Correct:
public boolean hasCycle(ListNode head) {
    if (head == null || head.next == null) {
        return false;
    }
    // Rest of code
}
```

### 5. Using Node Values Instead of References
```java
❌ Incorrect:
if (slow.val == fast.val) {
    return true;  // Wrong! Values can be same
}

✅ Correct:
if (slow == fast) {
    return true;  // Correct! Check reference equality
}
```

---

## 🎓 Cycle Detection Best Practices

### 1. Always Use Reference Equality
```java
// Use == for node comparison, not equals()
if (slow == fast) {  // Correct
    // Nodes are same object
}
```

### 2. Handle Edge Cases First
```java
public ListNode detectCycle(ListNode head) {
    // Handle edge cases
    if (head == null || head.next == null) {
        return null;
    }
    
    // Main algorithm
}
```

### 3. Use Descriptive Variable Names
```java
// Good names
ListNode slow = head;
ListNode fast = head;

// Avoid
ListNode p1 = head;
ListNode p2 = head;
```

### 4. Comment the Algorithm Phases
```java
public ListNode detectCycle(ListNode head) {
    // Phase 1: Detect if cycle exists
    ListNode slow = head, fast = head;
    // ... detection code
    
    // Phase 2: Find cycle start
    slow = head;
    // ... finding code
}
```

### 5. Consider Space-Time Tradeoffs
```java
// Production: Use Floyd's (O(1) space)
// Debugging: HashSet can be clearer (O(n) space)
// Interview: Always mention both approaches
```

---

## 📚 Topics to be Added

As I continue practicing, this folder will include:

### **Basic Cycle Problems**
- Linked List Cycle (boolean detection)
- Happy Number (cycle in sequences)
- Remove cycle from linked list
- Detect cycle length

### **Advanced Cycle Problems**
- Intersection point of two lists
- Find duplicate number (array as list)
- Cycle in directed graph
- Detect cycle in undirected graph

### **Variations**
- Multiple lists cycle detection
- Cycle with specific properties
- Cycle in doubly linked list
- Cycle detection with constraints

### **Applications**
- Memory leak detection
- Infinite loop detection
- Deadlock detection
- Circular dependency detection

---

## 🎯 Learning Path

### Beginner Level
1. **Linked List Cycle (Boolean)** - Basic detection
2. **Understand Floyd's Algorithm** - Why it works
3. **HashSet Approach** - Alternative method

### Intermediate Level
4. **Find Cycle Start** - Two-phase algorithm ← Current
5. **Calculate Cycle Length** - Additional properties
6. **Remove Cycle** - Modify list structure

### Advanced Level
7. **Find Duplicate Number** - Array as linked list
8. **Intersection Detection** - Multiple lists
9. **Graph Cycle Detection** - Generalized approach

---

## 💻 Essential Operations Reference

### Cycle Detection
```java
boolean hasCycle(ListNode head)
```

### Find Cycle Start
```java
ListNode detectCycle(ListNode head)
```

### Get Cycle Length
```java
int getCycleLength(ListNode head)
```

### Remove Cycle
```java
void removeCycle(ListNode head)
```

### Get Cycle Nodes
```java
List<ListNode> getCycleNodes(ListNode head)
```

---

## 🔄 Cycle Detection vs Other Techniques

### When to Use What?

**Use Floyd's Algorithm when:**
- Need O(1) space
- Don't want to modify list
- Standard interview setting
- Performance is critical

**Use HashSet when:**
- Space is not a concern
- Need to track all visited nodes
- Debugging or testing
- Need additional node information

**Use Node Marking when:**
- Can modify list temporarily
- Single-threaded environment
- Educational purposes only

**Don't Use for:**
- Trees (no cycles by definition)
- Arrays (use other methods)
- Already validated data structures

---

## 📊 Progress Tracker

**Current Status:**
- Topics Covered: 1 (Cycle Start)
- Total Problems: 1
- Difficulty: Medium
- Concepts: Floyd's algorithm, Two pointers

**Next Milestones:**
- [ ] Add basic cycle detection (boolean)
- [ ] Add cycle removal problem
- [ ] Add cycle length calculation
- [ ] Add intersection of two lists
- [ ] Add find duplicate number

---

## 🎓 Interview Tips

### Common Cycle Detection Interview Questions

1. **"Explain Floyd's Cycle Detection algorithm"**
   - Two pointers: slow (1 step) and fast (2 steps)
   - If cycle exists, they will meet
   - Meeting point is inside the cycle
   - Mathematical proof shows how to find start

2. **"Why does Floyd's algorithm work?"**
   - If no cycle: fast reaches null
   - If cycle: fast eventually laps slow
   - They must meet inside cycle
   - Distance equality property for finding start

3. **"What's the time and space complexity?"**
   - Time: O(n) - each node visited at most twice
   - Space: O(1) - only two pointers
   - Optimal solution

4. **"What if we use 1x and 3x speeds?"**
   - Still works but may take longer
   - Less efficient than 2x speed
   - 2x is proven optimal for this problem

### What Interviewers Look For

- ✅ Understanding of Floyd's algorithm
- ✅ Proper null checks
- ✅ Correct two-phase implementation
- ✅ Knowledge of mathematical proof
- ✅ Space complexity optimization
- ✅ Handling edge cases

---

## 📖 Additional Resources

### Documentation
- [Floyd's Cycle Detection - Wikipedia](https://en.wikipedia.org/wiki/Cycle_detection)
- [LeetCode Discuss - Cycle Detection](https://leetcode.com/problems/linked-list-cycle-ii/discuss/)

### Visualizations
- [VisuAlgo - Linked List](https://visualgo.net/en/list)
- [Algorithm Visualizer](https://algorithm-visualizer.org/)

### Practice Platforms
- [LeetCode - Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/)
- [LeetCode - Linked List Cycle II](https://leetcode.com/problems/linked-list-cycle-ii/)
- [GeeksforGeeks - Cycle Detection](https://www.geeksforgeeks.org/detect-loop-in-a-linked-list/)

---

## 🤝 Contributing

As I add more cycle detection problems:
- Each problem demonstrates different aspects
- Multiple solution approaches
- Clear complexity analysis
- Real-world applications

---

## 💡 Remember

> "Floyd's Cycle Detection is a perfect example of how elegant mathematics leads to efficient algorithms."

**Key Takeaways:**
- Floyd's algorithm is optimal (O(1) space)
- Understand the mathematical proof
- Two distinct phases: detection and finding start
- Reference equality, not value equality
- Always check fast != null && fast.next != null

---

<div align="center">

**Master Cycle Detection, Master Linked Lists! 🔄**

*Elegant algorithm, powerful technique*

*Last Updated: December 2024*

[⬆ Back to Linked List](../README.md)

</div>
