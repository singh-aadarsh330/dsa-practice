# 🎲 Miscellaneous

[![Problems](https://img.shields.io/badge/Problems-0-blue?style=flat-square)](.)
[![Difficulty](https://img.shields.io/badge/Difficulty-Easy%20to%20Hard-orange?style=flat-square)](.)
[![Language](https://img.shields.io/badge/Language-Java-red?style=flat-square)](.)

> Advanced and specialized linked list problems. Master complex operations, design challenges, and unique problem patterns.

---

## 📋 Overview

**Miscellaneous** contains advanced linked list problems that don't fit neatly into other categories but are crucial for mastering data structures. These problems often combine multiple techniques, require creative thinking, or involve special data structure designs.

**Key Features:**
- **Complex Operations** - Multi-step algorithms
- **Design Problems** - Implement data structures
- **Optimization Challenges** - Advanced efficiency requirements
- **Special Cases** - Unique problem constraints
- **Real-World Applications** - Practical implementations

**Problem Categories:**
- ✅ Linked list design and implementation
- ✅ Clone and copy operations
- ✅ Flatten operations
- ✅ Addition and arithmetic
- ✅ Special constraints and optimizations
- ✅ Combination problems
- ✅ Advanced manipulations

---

## 🔑 Miscellaneous Concepts

### Common Advanced Patterns

```java
// Pattern 1: Copy with Random Pointer
class Node {
    int val;
    Node next;
    Node random;  // Points to random node in list
    
    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public Node copyRandomList(Node head) {
    if (head == null) return null;
    
    // Step 1: Create copy nodes interleaved
    Node curr = head;
    while (curr != null) {
        Node copy = new Node(curr.val);
        copy.next = curr.next;
        curr.next = copy;
        curr = copy.next;
    }
    
    // Step 2: Assign random pointers
    curr = head;
    while (curr != null) {
        if (curr.random != null) {
            curr.next.random = curr.random.next;
        }
        curr = curr.next.next;
    }
    
    // Step 3: Separate lists
    curr = head;
    Node copyHead = head.next;
    Node copyCurr = copyHead;
    
    while (curr != null) {
        curr.next = curr.next.next;
        if (copyCurr.next != null) {
            copyCurr.next = copyCurr.next.next;
        }
        curr = curr.next;
        copyCurr = copyCurr.next;
    }
    
    return copyHead;
}

// Pattern 2: Flatten Multilevel List
class MultilevelNode {
    int val;
    MultilevelNode prev;
    MultilevelNode next;
    MultilevelNode child;  // Points to sublevel
}

public MultilevelNode flatten(MultilevelNode head) {
    if (head == null) return null;
    
    MultilevelNode curr = head;
    
    while (curr != null) {
        if (curr.child != null) {
            // Save next
            MultilevelNode next = curr.next;
            
            // Flatten child
            MultilevelNode child = flatten(curr.child);
            
            // Connect current to child
            curr.next = child;
            child.prev = curr;
            curr.child = null;
            
            // Find end of child list
            while (curr.next != null) {
                curr = curr.next;
            }
            
            // Connect to saved next
            if (next != null) {
                curr.next = next;
                next.prev = curr;
            }
        }
        curr = curr.next;
    }
    
    return head;
}

// Pattern 3: Add Two Numbers (with carry)
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode curr = dummy;
    int carry = 0;
    
    while (l1 != null || l2 != null || carry != 0) {
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
        curr.next = new ListNode(sum % 10);
        curr = curr.next;
    }
    
    return dummy.next;
}

// Pattern 4: LRU Cache Implementation
class LRUCache {
    class Node {
        int key, value;
        Node prev, next;
        
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private Map<Integer, Node> cache;
    private Node head, tail;
    private int capacity;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        
        // Dummy head and tail
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        
        Node node = cache.get(key);
        remove(node);
        add(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            remove(cache.get(key));
        }
        
        Node node = new Node(key, value);
        cache.put(key, node);
        add(node);
        
        if (cache.size() > capacity) {
            Node lru = head.next;
            remove(lru);
            cache.remove(lru.key);
        }
    }
    
    private void add(Node node) {
        Node prev = tail.prev;
        prev.next = node;
        node.prev = prev;
        node.next = tail;
        tail.prev = node;
    }
    
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}

// Pattern 5: Design Browser History
class BrowserHistory {
    class Page {
        String url;
        Page prev, next;
        
        Page(String url) {
            this.url = url;
        }
    }
    
    private Page current;
    
    public BrowserHistory(String homepage) {
        current = new Page(homepage);
    }
    
    public void visit(String url) {
        Page newPage = new Page(url);
        current.next = newPage;
        newPage.prev = current;
        current = newPage;
    }
    
    public String back(int steps) {
        while (steps > 0 && current.prev != null) {
            current = current.prev;
            steps--;
        }
        return current.url;
    }
    
    public String forward(int steps) {
        while (steps > 0 && current.next != null) {
            current = current.next;
            steps--;
        }
        return current.url;
    }
}
```

---

## 📂 Repository Structure

```
Misc/
│
├── (Problems to be added)
│
└── README.md                # This file
```

---

## 📝 Planned Problems

| # | Problem | Difficulty | Key Concepts | Status |
|---|---------|------------|--------------|--------|
| 1 | Copy List with Random Pointer | Medium | Deep copy, HashMap | 📅 Planned |
| 2 | Flatten Multilevel List | Medium | DFS, Recursion | 📅 Planned |
| 3 | Add Two Numbers | Medium | Arithmetic, Carry | 📅 Planned |
| 4 | Add Two Numbers II | Medium | Stack, Reverse | 📅 Planned |
| 5 | LRU Cache | Medium | HashMap + DLL | 📅 Planned |
| 6 | LFU Cache | Hard | Multiple data structures | 📅 Planned |
| 7 | Design Browser History | Medium | DLL navigation | 📅 Planned |
| 8 | Flatten Binary Tree to List | Medium | Tree + List | 📅 Planned |
| 9 | Convert BST to DLL | Medium | Inorder + DLL | 📅 Planned |
| 10 | All O(1) Data Structure | Hard | Complex design | 📅 Planned |
| 11 | Skip List Implementation | Hard | Probabilistic DS | 📅 Planned |
| 12 | Serialize/Deserialize List | Medium | Encoding/Decoding | 📅 Planned |

---

## 🔑 Key Miscellaneous Concepts

### 1. Problem Categories

| Category | Example Problems | Difficulty | Key Skills |
|----------|-----------------|------------|------------|
| **Copy Operations** | Copy with random pointer | Medium | HashMap, Deep copy |
| **Flatten Operations** | Flatten multilevel list | Medium | DFS, Recursion |
| **Arithmetic** | Add two numbers | Medium | Carry handling |
| **Design Problems** | LRU Cache | Medium-Hard | Multiple DS |
| **Conversions** | Tree to list | Medium | Tree traversal |
| **Special Structures** | Skip list | Hard | Probabilistic |

### 2. Design Pattern: Cache Implementation

```java
// LRU Cache uses Doubly Linked List + HashMap
// Why?
// - HashMap: O(1) access to nodes
// - DLL: O(1) insertion/deletion at ends
// - Recent items at tail, LRU items at head

Structure:
HashMap<Key, Node> cache
Node head (dummy)
Node tail (dummy)

Operations:
get(key):
  1. Check HashMap
  2. If exists, move to tail (most recent)
  3. Return value

put(key, value):
  1. If exists, update and move to tail
  2. If new, add to tail
  3. If over capacity, remove head.next (LRU)
```

### 3. Copy with Random Pointer Pattern

```java
// Three-pass algorithm:
// Pass 1: Create copy nodes interleaved
// Original: A → B → C
// After:    A → A' → B → B' → C → C'

// Pass 2: Set random pointers for copies
// A'.random = A.random.next (copy of A.random)

// Pass 3: Separate original and copy
// Original: A → B → C
// Copy:     A' → B' → C'

// Why this works:
// - Interleaving maintains relationships
// - A.next is always the copy of A
// - Can access copy of any node via its next
```

### 4. Arithmetic Operations Pattern

```java
// Adding two numbers represented as lists:
// Input:  (2 → 4 → 3) + (5 → 6 → 4)
// Output: 7 → 0 → 8
// Because: 342 + 465 = 807

// Key concepts:
// 1. Carry propagation
// 2. Different lengths
// 3. Final carry
// 4. Digit-by-digit addition

int carry = 0;
while (l1 != null || l2 != null || carry != 0) {
    int sum = carry;
    if (l1 != null) sum += l1.val;
    if (l2 != null) sum += l2.val;
    
    carry = sum / 10;
    digit = sum % 10;
}
```

---

## 💡 Common Miscellaneous Patterns

### Pattern 1: Deep Copy with HashMap
```java
public Node copyWithHashMap(Node head) {
    if (head == null) return null;
    
    Map<Node, Node> map = new HashMap<>();
    
    // First pass: Create all nodes
    Node curr = head;
    while (curr != null) {
        map.put(curr, new Node(curr.val));
        curr = curr.next;
    }
    
    // Second pass: Set pointers
    curr = head;
    while (curr != null) {
        map.get(curr).next = map.get(curr.next);
        map.get(curr).random = map.get(curr.random);
        curr = curr.next;
    }
    
    return map.get(head);
}
```

### Pattern 2: Iterative Flattening
```java
public Node flattenIterative(Node head) {
    if (head == null) return null;
    
    Stack<Node> stack = new Stack<>();
    Node curr = head;
    
    while (curr != null || !stack.isEmpty()) {
        if (curr.child != null) {
            if (curr.next != null) {
                stack.push(curr.next);
            }
            curr.next = curr.child;
            curr.child.prev = curr;
            curr.child = null;
        }
        
        if (curr.next == null && !stack.isEmpty()) {
            Node next = stack.pop();
            curr.next = next;
            next.prev = curr;
        }
        
        curr = curr.next;
    }
    
    return head;
}
```

### Pattern 3: Design with Multiple Structures
```java
class AllOne {
    class Node {
        int count;
        Set<String> keys;
        Node prev, next;
        
        Node(int count) {
            this.count = count;
            this.keys = new HashSet<>();
        }
    }
    
    private Map<String, Integer> keyCount;
    private Map<Integer, Node> countNode;
    private Node head, tail;
    
    public AllOne() {
        keyCount = new HashMap<>();
        countNode = new HashMap<>();
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
    }
    
    public void inc(String key) {
        // Complex logic involving multiple structures
    }
    
    public void dec(String key) {
        // Complex logic involving multiple structures
    }
    
    public String getMaxKey() {
        return tail.prev == head ? "" : 
               tail.prev.keys.iterator().next();
    }
    
    public String getMinKey() {
        return head.next == tail ? "" : 
               head.next.keys.iterator().next();
    }
}
```

### Pattern 4: Serialize/Deserialize
```java
public String serialize(ListNode head) {
    StringBuilder sb = new StringBuilder();
    while (head != null) {
        sb.append(head.val);
        if (head.next != null) {
            sb.append(",");
        }
        head = head.next;
    }
    return sb.toString();
}

public ListNode deserialize(String data) {
    if (data.isEmpty()) return null;
    
    String[] values = data.split(",");
    ListNode dummy = new ListNode(0);
    ListNode curr = dummy;
    
    for (String val : values) {
        curr.next = new ListNode(Integer.parseInt(val));
        curr = curr.next;
    }
    
    return dummy.next;
}
```

### Pattern 5: Conversion Between Structures
```java
// Convert BST to Circular Doubly Linked List
public Node treeToDoublyList(Node root) {
    if (root == null) return null;
    
    Node[] headTail = new Node[2];
    inorder(root, headTail);
    
    // Make it circular
    headTail[0].left = headTail[1];
    headTail[1].right = headTail[0];
    
    return headTail[0];
}

private void inorder(Node node, Node[] headTail) {
    if (node == null) return;
    
    inorder(node.left, headTail);
    
    if (headTail[1] != null) {
        headTail[1].right = node;
        node.left = headTail[1];
    } else {
        headTail[0] = node;  // First node
    }
    
    headTail[1] = node;
    
    inorder(node.right, headTail);
}
```

---

## ⚠️ Common Mistakes in Advanced Problems

### 1. Not Handling Cycles in Copy
```java
❌ Incorrect:
// Creates infinite loop if list has cycle
Node copy = new Node(original.val);
copy.next = copyList(original.next);

✅ Correct:
// Use HashMap to track copied nodes
if (map.containsKey(node)) {
    return map.get(node);
}
```

### 2. Memory Leaks in Design
```java
❌ Incorrect:
public void remove(String key) {
    cache.remove(key);
    // Forgot to remove from DLL!
}

✅ Correct:
public void remove(String key) {
    Node node = cache.remove(key);
    removeFromDLL(node);
}
```

### 3. Incorrect Carry Handling
```java
❌ Incorrect:
while (l1 != null || l2 != null) {
    // Forgot final carry!
}

✅ Correct:
while (l1 != null || l2 != null || carry != 0) {
    // Handles final carry
}
```

### 4. Not Maintaining Invariants
```java
❌ Incorrect (LRU):
public void put(int key, int value) {
    add(new Node(key, value));
    // Size can exceed capacity!
}

✅ Correct:
public void put(int key, int value) {
    add(new Node(key, value));
    if (size > capacity) {
        removeLRU();
    }
}
```

### 5. Incorrect Pointer Updates
```java
❌ Incorrect (Flatten):
curr.next = curr.child;
curr.child = null;
// Lost reference to original next!

✅ Correct:
Node next = curr.next;
curr.next = curr.child;
curr.child = null;
// Process child, then reconnect next
```

---

## 🎓 Best Practices for Complex Problems

### 1. Break Down into Steps
```java
// Example: Copy with random pointer
// Step 1: Create interleaved copies
// Step 2: Set random pointers
// Step 3: Separate lists
```

### 2. Use Helper Data Structures
```java
// HashMap for O(1) lookups
// Stack for DFS traversal
// Set for tracking visited
```

### 3. Draw Diagrams
```java
// Visualize:
// - Node relationships
// - Pointer changes
// - Edge cases
```

### 4. Test Edge Cases
```java
// Test:
- null input
- Single node
- Cycles
- Empty structures
- Maximum capacity
```

### 5. Maintain Invariants
```java
// Always ensure:
- Size consistency
- Pointer correctness
- Data structure properties
```

---

## 📚 Topics to be Added

### **Copy and Clone**
- Copy list with random pointer
- Deep copy with cycles
- Clone complex structures
- Serialize/deserialize

### **Flatten Operations**
- Flatten multilevel doubly linked list
- Flatten binary tree to list
- Flatten nested structures

### **Arithmetic Operations**
- Add two numbers (forward)
- Add two numbers (reverse)
- Multiply two numbers
- Subtract numbers

### **Design Problems**
- LRU Cache
- LFU Cache
- Browser history
- All O(1) data structure
- Skip list
- Trie with linked components

### **Conversions**
- BST to doubly linked list
- Array to linked list
- Tree to list
- Graph to list

### **Special Constraints**
- Read-only list modifications
- Space-constrained operations
- Time-critical operations
- Concurrent access

---

## 🎯 Learning Path

### Intermediate Level
1. **Copy Operations** - Deep copy basics
2. **Simple Design** - Basic cache implementation
3. **Arithmetic** - Add two numbers

### Advanced Level
4. **Complex Design** - LRU/LFU Cache
5. **Flatten Operations** - Multilevel structures
6. **Conversions** - Between data structures

### Expert Level
7. **Advanced Design** - All O(1) structure
8. **Skip List** - Probabilistic data structure
9. **Concurrent** - Thread-safe implementations

---

## 💻 Essential Operations

### Copy Operations
```java
Node copyRandomList(Node head)              // Copy with random
Node deepCopy(Node head)                    // General deep copy
```

### Design Operations
```java
class LRUCache {
    int get(int key)
    void put(int key, int value)
}
```

### Arithmetic Operations
```java
ListNode addTwoNumbers(ListNode l1, ListNode l2)
ListNode multiplyNumbers(ListNode l1, ListNode l2)
```

### Conversion Operations
```java
Node treeToList(TreeNode root)
ListNode arrayToList(int[] arr)
```

---

## 📊 Progress Tracker

**Current Status:**
- Topics Covered: 0 (Folder created)
- Total Problems: 0
- Planned Problems: 12+

**Next Milestones:**
- [ ] Add copy operations (2)
- [ ] Add design problems (3)
- [ ] Add flatten operations (2)
- [ ] Add arithmetic (2)
- [ ] Add conversions (3+)

---

## 🎓 Interview Tips

### Common Questions

1. **"Implement LRU Cache"**
   - HashMap + Doubly Linked List
   - O(1) for both get and put
   - Very common in interviews

2. **"Copy list with random pointer"**
   - Three-pass algorithm
   - Or HashMap approach
   - Tests understanding of deep copy

3. **"Add two numbers as lists"**
   - Carry propagation
   - Handle different lengths
   - Edge case: final carry

### What Interviewers Look For

- ✅ Problem decomposition
- ✅ Multiple data structure coordination
- ✅ Edge case handling
- ✅ Clean code organization
- ✅ Complexity analysis

---

## 💡 Remember

> "Miscellaneous problems are where creativity meets fundamentals - they test your ability to combine techniques."

**Key Takeaways:**
- Break complex problems into steps
- Use appropriate helper structures
- Maintain data structure invariants
- Test edge cases thoroughly
- Document your approach clearly

---

<div align="center">

**Master the Advanced, Become Complete! 🎲**

*Complex problems, elegant solutions*

*Last Updated: December 2024*

[⬆ Back to Linked List](../README.md)

</div>
