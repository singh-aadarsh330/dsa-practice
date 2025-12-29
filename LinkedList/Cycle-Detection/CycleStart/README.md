# 🔄 Find Cycle Start in Linked List

[![Difficulty](https://img.shields.io/badge/Difficulty-Medium-orange?style=flat-square)](.)
[![Concept](https://img.shields.io/badge/Concept-Two%20Pointers%20%7C%20Floyd's%20Algorithm-blue?style=flat-square)](.)

> Detect and locate the starting node of a cycle in a linked list. Master Floyd's Cycle Detection algorithm and pointer manipulation.

---

## 📋 Problem Description

### The Challenge

Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return `null`.

A cycle exists in a linked list if there is some node that can be reached again by continuously following the `next` pointer. The **tail** of the linked list connects back to some node in the list.

**Input:**
- Head of a linked list (may or may not contain a cycle)

**Output:**
- The node where the cycle begins
- `null` if no cycle exists

**Constraints:**
- Number of nodes: 0 to 10^4
- Node values: -10^5 to 10^5
- Cannot modify the linked list structure

---

### Examples

**Example 1: Cycle Starting at Position 1**
```
Input: head = [3,2,0,-4], pos = 1

Linked List Visualization:
    3 → 2 → 0 → -4
        ↑__________|

Output: Node with value 2 (index 1)
Explanation: The tail connects to node at position 1
```

**Example 2: Cycle Starting at Position 0**
```
Input: head = [1,2], pos = 0

Linked List Visualization:
    1 → 2
    ↑___|

Output: Node with value 1 (index 0)
Explanation: The tail connects to the first node
```

**Example 3: No Cycle**
```
Input: head = [1], pos = -1

Linked List Visualization:
    1 → null

Output: null
Explanation: There is no cycle in the linked list
```

**Example 4: Single Node Cycle**
```
Input: head = [1], pos = 0

Linked List Visualization:
    1 ↺

Output: Node with value 1
Explanation: The node points to itself
```

**Example 5: Long List with Cycle**
```
Input: head = [1,2,3,4,5,6,7,8], pos = 3

Linked List Visualization:
    1 → 2 → 3 → 4 → 5 → 6 → 7 → 8
                ↑___________________|

Output: Node with value 4 (index 3)
Explanation: Tail connects to node at position 3
```

---

## 🎯 Learning Objectives

This problem teaches:
- **Floyd's Cycle Detection** - Fast and slow pointer technique
- **Two Pointer Technique** - Different pointer speeds
- **Mathematical Proof** - Why the algorithm works
- **Cycle Detection** - Identifying cycles in data structures
- **Pointer Manipulation** - Advanced linked list operations
- **Space Optimization** - O(1) space solution

---

## 🔧 Approach 1: HashSet (Brute Force)

### Algorithm

Use a HashSet to track visited nodes. The first revisited node is the cycle start.

### Implementation

```java
public class Solution {
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> visited = new HashSet<>();
        ListNode current = head;
        
        while (current != null) {
            // If node already visited, it's the cycle start
            if (visited.contains(current)) {
                return current;
            }
            
            // Mark node as visited
            visited.add(current);
            current = current.next;
        }
        
        // No cycle found
        return null;
    }
}
```

**Complexity:**
- **Time:** O(n) - visit each node once
- **Space:** O(n) - store all nodes in HashSet

**Pros:**
- ✅ Simple and intuitive
- ✅ Easy to understand
- ✅ Straightforward implementation

**Cons:**
- ❌ O(n) extra space required
- ❌ Not optimal for memory-constrained systems
- ❌ Not the expected interview solution

---

## 🔧 Approach 2: Floyd's Cycle Detection (Optimal) ⭐

### Algorithm

**Phase 1: Detect if cycle exists**
- Use fast (2 steps) and slow (1 step) pointers
- If they meet, a cycle exists

**Phase 2: Find cycle start**
- Reset one pointer to head
- Move both pointers one step at a time
- They meet at the cycle start

### Mathematical Proof

```
Let:
- L = distance from head to cycle start
- C = cycle length
- k = distance from cycle start to meeting point

When slow and fast meet:
- Slow traveled: L + k
- Fast traveled: L + k + nC (n complete cycles)
- Fast speed = 2 × Slow speed

Therefore:
2(L + k) = L + k + nC
2L + 2k = L + k + nC
L + k = nC
L = nC - k

This proves: Distance from head to cycle start (L) 
equals distance from meeting point to cycle start (nC - k)
```

### Implementation

```java
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        
        // Phase 1: Detect if cycle exists
        ListNode slow = head;
        ListNode fast = head;
        boolean hasCycle = false;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;           // Move 1 step
            fast = fast.next.next;      // Move 2 steps
            
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }
        
        // No cycle found
        if (!hasCycle) {
            return null;
        }
        
        // Phase 2: Find cycle start
        slow = head;  // Reset slow to head
        
        while (slow != fast) {
            slow = slow.next;  // Move 1 step
            fast = fast.next;  // Move 1 step
        }
        
        // Both pointers meet at cycle start
        return slow;
    }
}
```

**Complexity:**
- **Time:** O(n) - visit each node at most twice
- **Space:** O(1) - only two pointers

**Pros:**
- ⭐ **Optimal space complexity**
- ⭐ **Standard interview solution**
- ⭐ Elegant mathematical foundation
- Fast and efficient

**Cons:**
- Requires understanding of the proof
- Less intuitive initially

---

## 🔧 Approach 3: Marking Nodes (Destructive)

### Algorithm

Temporarily modify nodes to mark them as visited. First already-marked node is the cycle start.

**Note:** This approach modifies the list structure temporarily.

### Implementation

```java
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        
        ListNode current = head;
        ListNode marker = new ListNode(Integer.MIN_VALUE);
        
        while (current != null) {
            // Check if node is already marked
            if (current.next == marker) {
                return current;
            }
            
            // Save the next node
            ListNode next = current.next;
            
            // Mark current node
            current.next = marker;
            
            // Move to next
            current = next;
        }
        
        return null;
    }
}
```

**Complexity:**
- **Time:** O(n)
- **Space:** O(1)

**Pros:**
- O(1) space
- Simple logic

**Cons:**
- ❌ Modifies the list (destructive)
- ❌ Not acceptable in interviews
- ❌ Cannot restore original structure easily

---

## 🔧 Approach 4: Two-Pass with Distance Calculation

### Algorithm

First detect cycle, then calculate cycle length and find start mathematically.

### Implementation

```java
public class Solution {
    public ListNode detectCycle(ListNode head) {
        // Phase 1: Detect cycle and find meeting point
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                // Phase 2: Calculate cycle length
                int cycleLength = getCycleLength(slow);
                
                // Phase 3: Find cycle start
                return findCycleStart(head, cycleLength);
            }
        }
        
        return null;
    }
    
    private int getCycleLength(ListNode node) {
        int length = 1;
        ListNode current = node.next;
        
        while (current != node) {
            length++;
            current = current.next;
        }
        
        return length;
    }
    
    private ListNode findCycleStart(ListNode head, int cycleLength) {
        ListNode ahead = head;
        ListNode behind = head;
        
        // Move ahead pointer cycleLength steps forward
        for (int i = 0; i < cycleLength; i++) {
            ahead = ahead.next;
        }
        
        // Move both until they meet at cycle start
        while (ahead != behind) {
            ahead = ahead.next;
            behind = behind.next;
        }
        
        return behind;
    }
}
```

**Pros:**
- Educational approach
- Shows cycle length calculation

**Cons:**
- More complex than Floyd's
- Not commonly used

---

## 📊 Comparison of Approaches

| Approach | Time | Space | Modifies List | Interview Score |
|----------|------|-------|---------------|-----------------|
| **HashSet** | O(n) | O(n) | No | ⭐⭐⭐ |
| **Floyd's Algorithm** | O(n) | O(1) | No | **⭐⭐⭐⭐⭐** |
| **Node Marking** | O(n) | O(1) | Yes | ⭐ |
| **Two-Pass** | O(n) | O(1) | No | ⭐⭐⭐⭐ |

**Recommendation:** Use **Floyd's Cycle Detection** in interviews - optimal and elegant.

---

## 🎯 Step-by-Step Dry Run

### Input: head = [3,2,0,-4], pos = 1

**Linked List:**
```
Index:  0   1   2   3
Value:  3 → 2 → 0 → -4
            ↑________|
```

**Phase 1: Detect Cycle**

```
Initial State:
slow = head (3)
fast = head (3)

Iteration 1:
slow moves to: 2 (index 1)
fast moves to: 0 (index 2)
slow ≠ fast → continue

Iteration 2:
slow moves to: 0 (index 2)
fast moves to: -4 (index 3) → then to 2 (index 1)
slow ≠ fast → continue

Iteration 3:
slow moves to: -4 (index 3)
fast moves to: 0 (index 2) → then to -4 (index 3)
slow == fast ✓ (both at index 3)

Cycle detected!
Meeting point: node with value -4
```

**Phase 2: Find Cycle Start**

```
Reset slow to head:
slow = head (3, index 0)
fast = -4 (index 3, meeting point)

Iteration 1:
slow moves to: 2 (index 1)
fast moves to: 2 (index 1)
slow == fast ✓

Cycle start found: node with value 2 (index 1)
```

**Visual Trace:**
```
Phase 1 - Detecting Cycle:
Step 0:  S,F at 3
         3 → 2 → 0 → -4
                     ↑___|

Step 1:  S at 2, F at 0
         3 → 2 → 0 → -4
             S   F   ↑___|

Step 2:  S at 0, F at 2
         3 → 2 → 0 → -4
                 S F ↑___|

Step 3:  S at -4, F at -4 (MEET!)
         3 → 2 → 0 → -4
                     S,F
                     ↑___|

Phase 2 - Finding Start:
Step 0:  S at 3, F at -4
         3 → 2 → 0 → -4
         S           F
                     ↑___|

Step 1:  S at 2, F at 2 (MEET!)
         3 → 2 → 0 → -4
             S,F     ↑___|

Result: Cycle starts at node 2
```

---

## ⚠️ Edge Cases & Validation

### Edge Cases to Consider

**1. No Cycle**
```java
Input: 1 → 2 → 3 → null
Output: null
```

**2. Empty List**
```java
Input: null
Output: null
```

**3. Single Node, No Cycle**
```java
Input: 1 → null
Output: null
```

**4. Single Node, Self-Cycle**
```java
Input: 1 ↺
Output: node 1
```

**5. Two Nodes, Cycle at First**
```java
Input: 1 → 2
       ↑___|
Output: node 1
```

**6. All Nodes in Cycle**
```java
Input: 1 → 2 → 3 → 4
       ↑____________|
Output: node 1
```

**7. Large List, Cycle at End**
```java
Input: 1 → 2 → ... → 999 → 1000
                            ↑___|
Output: node 1000
```

### Input Validation

```java
public ListNode detectCycle(ListNode head) {
    // Handle null or single node without cycle
    if (head == null || head.next == null) {
        return null;
    }
    
    // Main algorithm...
}
```

---

## 🧪 Test Cases

### Test Case 1: Standard Cycle
```java
Input: [3,2,0,-4], pos = 1
Expected: Node at index 1 (value 2)
```

### Test Case 2: No Cycle
```java
Input: [1,2,3,4,5], pos = -1
Expected: null
```

### Test Case 3: Cycle at Head
```java
Input: [1,2], pos = 0
Expected: Node at index 0 (value 1)
```

### Test Case 4: Self Loop
```java
Input: [1], pos = 0
Expected: Node at index 0 (value 1)
```

### Test Case 5: Long Cycle
```java
Input: [1,2,3,4,5,6,7,8,9,10], pos = 5
Expected: Node at index 5 (value 6)
```

### Test Case 6: Two Node Cycle
```java
Input: [1,2], pos = 0
Expected: Node at index 0 (value 1)
```

---

## 💡 Key Concepts Illustrated

### 1. Floyd's Cycle Detection Algorithm

```java
// Also known as "Tortoise and Hare" algorithm
ListNode slow = head;  // Moves 1 step
ListNode fast = head;  // Moves 2 steps

// Phase 1: Detect cycle
while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
    
    if (slow == fast) {
        // Cycle detected
        break;
    }
}

// Phase 2: Find start
slow = head;
while (slow != fast) {
    slow = slow.next;
    fast = fast.next;
}
return slow;  // Cycle start
```

### 2. Why Floyd's Algorithm Works

```
Mathematical Proof:

When pointers meet:
- Slow has traveled: L + a
- Fast has traveled: L + a + b + a = L + 2a + b

Where:
L = distance from head to cycle start
a = distance from cycle start to meeting point
b = remaining cycle distance

Fast travels twice the distance of slow:
2(L + a) = L + 2a + b
2L + 2a = L + 2a + b
L = b

Therefore: Distance from head to cycle start (L)
equals distance from meeting point back to cycle start (b)
```

### 3. Two Pointer Patterns

```java
// Pattern 1: Different speeds (Floyd's)
slow = slow.next;           // 1 step
fast = fast.next.next;      // 2 steps

// Pattern 2: Fixed gap
ahead = ahead.next;         // k steps ahead
behind = behind.next;       // follows

// Pattern 3: Opposite directions
left = left.next;
right = right.prev;
```

### 4. Linked List Cycle Characteristics

```
Types of Cycles:
1. No cycle: tail → null
2. Self-loop: node.next → node
3. Partial cycle: some nodes in cycle
4. Complete cycle: all nodes in cycle

Cycle Properties:
- Entry point: First node in cycle
- Cycle length: Number of nodes in cycle
- Distance to cycle: Nodes before cycle starts
```

### 5. Pointer Null Checks

```java
// Always check both conditions
while (fast != null && fast.next != null) {
    // fast.next.next is safe here
}

// Why both checks?
fast != null        // Odd-length list
fast.next != null   // Even-length list
```

---

## 🎓 Interview Discussion Points

### When Interviewer Asks This Problem

**1. Clarify Requirements**
```
Q: "Can I modify the list structure?"
Q: "What should I return if no cycle exists?"
Q: "Are node values unique?"
Q: "Can the list be empty?"
```

**2. Explain Your Approach**
```
"I'll use Floyd's Cycle Detection algorithm with two phases:

Phase 1 - Detect if cycle exists:
- Use slow (1 step) and fast (2 steps) pointers
- If they meet, a cycle exists
- If fast reaches null, no cycle

Phase 2 - Find cycle start:
- Reset slow to head
- Move both pointers one step at a time
- They'll meet at the cycle start

This works because the distance from head to cycle start
equals the distance from meeting point to cycle start.

Time: O(n), Space: O(1)"
```

**3. Discuss Trade-offs**
```
"HashSet approach is simpler but uses O(n) space.
Floyd's algorithm is optimal with O(1) space but requires
understanding the mathematical proof.

For production:
- Floyd's algorithm is preferred
- More memory efficient
- No risk of hash collisions

Alternative approaches exist but either:
- Use more space (HashSet)
- Modify the list (marking)
- Are more complex (distance calculation)"
```

### Common Follow-up Questions

**Q: "Can you prove why Floyd's algorithm works?"**
```java
A: "When slow and fast meet in the cycle:
   - Let L = distance from head to cycle start
   - Let C = cycle length
   - Let k = distance traveled in cycle before meeting
   
   Slow traveled: L + k
   Fast traveled: L + k + nC (n full cycles)
   
   Since fast moves twice as fast:
   2(L + k) = L + k + nC
   L = nC - k
   
   This means the distance from head to cycle start (L)
   equals the distance from meeting point back to start (nC-k).
   
   So when we reset one pointer to head and move both at
   same speed, they meet exactly at the cycle start."
```

**Q: "What if we need to find the cycle length?"**
```java
A: "After detecting the cycle (when pointers meet),
   keep one pointer fixed and move the other around
   the cycle counting steps until they meet again:
   
   ListNode temp = slow;
   int length = 1;
   slow = slow.next;
   
   while (slow != temp) {
       length++;
       slow = slow.next;
   }
   return length;"
```

**Q: "How would you remove the cycle?"**
```java
A: "After finding the cycle start node, traverse from
   the start until we find the node whose next points
   back to the start, then set its next to null:
   
   ListNode cycleStart = detectCycle(head);
   if (cycleStart == null) return;
   
   ListNode current = cycleStart;
   while (current.next != cycleStart) {
       current = current.next;
   }
   current.next = null;  // Break the cycle"
```

**Q: "What's the worst-case scenario for time complexity?"**
```java
A: "Worst case is still O(n):
   - Phase 1: Both pointers traverse at most n nodes
   - Phase 2: At most n steps to find cycle start
   
   Even if the cycle is very long or very short,
   we never exceed O(n) total operations.
   
   Space is always O(1) regardless of input."
```

---

## 🔄 Related Problems

### Similar Problems to Practice

1. **Linked List Cycle (Easy)** - Detect if cycle exists (boolean)
2. **Happy Number (Easy)** - Find cycle in number sequence
3. **Find Duplicate Number (Medium)** - Array as linked list
4. **Intersection of Two Linked Lists (Easy)** - Finding merge point
5. **Remove Cycle from Linked List (Medium)** - Breaking the cycle
6. **Palindrome Linked List (Easy)** - Using two pointers

### Difficulty Progression

```
Linked List Cycle (Easy)
    ↓
Cycle Start (Medium) ← You are here
    ↓
Remove Cycle (Medium)
    ↓
Find Duplicate Number (Medium)
```

---

## 🎯 Variations to Practice

### Variation 1: Return Cycle Length
```java
public int getCycleLength(ListNode head) {
    // Detect cycle and return its length
    // Return 0 if no cycle
}
```

### Variation 2: Find Kth Node Before Cycle Start
```java
public ListNode findKthNodeBeforeStart(ListNode head, int k) {
    // Find the kth node before cycle starts
}
```

### Variation 3: Multiple Cycles Detection
```java
public List<ListNode> detectMultipleCycles(ListNode[] heads) {
    // Check multiple lists for cycles
}
```

### Variation 4: Cycle in Array
```java
public int findDuplicateInArray(int[] nums) {
    // Treat array as linked list
    // nums[i] points to nums[nums[i]]
}
```

---

## 📚 Additional Learning

### Why This Problem is Important

1. **Classic Algorithm** - Floyd's is a fundamental CS algorithm
2. **Two Pointer Mastery** - Essential interview technique
3. **Mathematical Reasoning** - Proof-based problem solving
4. **Space Optimization** - O(n) → O(1) improvement
5. **Common in Interviews** - FAANG favorite

### Floyd's Algorithm Applications

**Beyond Linked Lists:**
- Detecting cycles in sequences
- Finding duplicates in arrays
- Analyzing periodic functions
- Pseudorandom number generators
- Pollard's rho algorithm (factorization)

### Two Pointer Technique Patterns

**Pattern 1: Same Direction, Different Speeds**
```java
slow = slow.next;
fast = fast.next.next;
```

**Pattern 2: Same Direction, Fixed Gap**
```java
for (int i = 0; i < k; i++) fast = fast.next;
while (fast != null) {
    slow = slow.next;
    fast = fast.next;
}
```

**Pattern 3: Opposite Directions**
```java
left = head;
right = tail;
while (left != right) {
    left = left.next;
    right = right.prev;
}
```

---

## 🔗 Resources

- [Floyd's Cycle Detection - Wikipedia](https://en.wikipedia.org/wiki/Cycle_detection#Floyd's_Tortoise_and_Hare)
- [LeetCode: Linked List Cycle II](https://leetcode.com/problems/linked-list-cycle-ii/)
- [Visual Algorithm](https://visualgo.net/en/list)
- [Mathematical Proof](https://cs.stackexchange.com/questions/10360/floyds-cycle-detection-algorithm-determining-the-starting-point-of-cycle)

---

<div align="center">

**Master Floyd's Algorithm, Master Cycle Detection! 🔄**

*Elegant mathematics meets practical coding*

*Last Updated: December 2024*

</div>
