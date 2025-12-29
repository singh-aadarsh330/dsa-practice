# 🔀 Sort Alternating Ascending-Descending Linked List

[![Difficulty](https://img.shields.io/badge/Difficulty-Medium-orange?style=flat-square)](.)
[![Concept](https://img.shields.io/badge/Concept-Sorting%20%7C%20List%20Manipulation%20%7C%20Merging-blue?style=flat-square)](.)

> Sort a linked list that alternates between ascending and descending order. Master list splitting, reversal, and merging techniques.

---

## 📋 Problem Description

### The Challenge

Given a linked list where elements alternate between ascending and descending order, sort the entire list in ascending order.

The list alternates as follows:
- Elements at **odd positions** (1st, 3rd, 5th, ...) are in **ascending order**
- Elements at **even positions** (2nd, 4th, 6th, ...) are in **descending order**

**Input:**
- Head of a linked list with alternating order

**Output:**
- Head of the sorted linked list in ascending order

**Constraints:**
- Number of nodes: 0 to 10^4
- Node values: -10^5 to 10^5
- Must maintain linked list structure

---

### Examples

**Example 1: Standard Case**
```
Input: 1 → 9 → 2 → 8 → 3 → 7 → 4

Analysis:
Odd positions (ascending):  1 → 2 → 3 → 4
Even positions (descending): 9 → 8 → 7

Output: 1 → 2 → 3 → 4 → 7 → 8 → 9

Explanation: Split into two lists, reverse descending list, merge both
```

**Example 2: Short List**
```
Input: 5 → 10 → 3

Analysis:
Odd positions:  5 → 3
Even positions: 10

Output: 3 → 5 → 10
```

**Example 3: Even Length**
```
Input: 1 → 8 → 3 → 7 → 5 → 6

Analysis:
Odd positions (ascending):  1 → 3 → 5
Even positions (descending): 8 → 7 → 6

After reversing even: 6 → 7 → 8

Output: 1 → 3 → 5 → 6 → 7 → 8
```

**Example 4: Single Element**
```
Input: 5

Output: 5

Explanation: Single element is already sorted
```

**Example 5: Two Elements**
```
Input: 3 → 7

Analysis:
Odd: 3
Even: 7

Output: 3 → 7
```

**Example 6: All Same Values**
```
Input: 5 → 5 → 5 → 5

Output: 5 → 5 → 5 → 5

Explanation: Already sorted (all equal)
```

---

## 🎯 Learning Objectives

This problem teaches:
- **List Splitting** - Separate alternating nodes
- **List Reversal** - Reverse descending portion
- **List Merging** - Merge two sorted lists
- **Multiple Operations** - Combine techniques
- **Space Optimization** - In-place operations
- **Pointer Manipulation** - Advanced techniques

---

## 🔧 Approach 1: Split, Reverse, Merge (Optimal)

### Algorithm

**Step 1:** Split list into odd and even positions
**Step 2:** Reverse the even-positioned list (descending → ascending)
**Step 3:** Merge both sorted lists

### Implementation

```java
public class Solution {
    
    public ListNode sortAlternating(ListNode head) {
        // Edge cases
        if (head == null || head.next == null) {
            return head;
        }
        
        // Step 1: Split into odd and even lists
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;
        
        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        
        // Disconnect odd list from even
        odd.next = null;
        
        // Step 2: Reverse even list (descending → ascending)
        evenHead = reverse(evenHead);
        
        // Step 3: Merge two sorted lists
        return mergeSortedLists(head, evenHead);
    }
    
    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        
        return prev;
    }
    
    private ListNode mergeSortedLists(ListNode l1, ListNode l2) {
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
        
        // Attach remaining nodes
        if (l1 != null) {
            current.next = l1;
        }
        if (l2 != null) {
            current.next = l2;
        }
        
        return dummy.next;
    }
}
```

**Complexity:**
- **Time:** O(n) - split O(n) + reverse O(n) + merge O(n)
- **Space:** O(1) - in-place operations only

**Pros:**
- ⭐ **Optimal time and space**
- ⭐ **Clear three-phase approach**
- ⭐ Easy to understand and debug
- Reuses standard algorithms

**Cons:**
- Requires three separate passes

---

## 🔧 Approach 2: Extract and Merge with Arrays

### Algorithm

Extract values into two arrays, reverse one, merge sorted arrays, rebuild list.

### Implementation

```java
public class Solution {
    
    public ListNode sortAlternating(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        // Step 1: Extract values
        List<Integer> oddValues = new ArrayList<>();
        List<Integer> evenValues = new ArrayList<>();
        
        ListNode current = head;
        boolean isOdd = true;
        
        while (current != null) {
            if (isOdd) {
                oddValues.add(current.val);
            } else {
                evenValues.add(current.val);
            }
            isOdd = !isOdd;
            current = current.next;
        }
        
        // Step 2: Reverse even values (descending to ascending)
        Collections.reverse(evenValues);
        
        // Step 3: Merge sorted lists
        List<Integer> merged = mergeLists(oddValues, evenValues);
        
        // Step 4: Rebuild linked list
        return buildList(merged);
    }
    
    private List<Integer> mergeLists(List<Integer> l1, List<Integer> l2) {
        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0;
        
        while (i < l1.size() && j < l2.size()) {
            if (l1.get(i) <= l2.get(j)) {
                result.add(l1.get(i++));
            } else {
                result.add(l2.get(j++));
            }
        }
        
        while (i < l1.size()) result.add(l1.get(i++));
        while (j < l2.size()) result.add(l2.get(j++));
        
        return result;
    }
    
    private ListNode buildList(List<Integer> values) {
        if (values.isEmpty()) return null;
        
        ListNode head = new ListNode(values.get(0));
        ListNode current = head;
        
        for (int i = 1; i < values.size(); i++) {
            current.next = new ListNode(values.get(i));
            current = current.next;
        }
        
        return head;
    }
}
```

**Complexity:**
- **Time:** O(n)
- **Space:** O(n) - arrays for values

**Pros:**
- ✅ Simple to implement
- ✅ Uses standard library methods
- Good for understanding the problem

**Cons:**
- ❌ O(n) extra space
- ❌ Not optimal for interviews

---

## 🔧 Approach 3: Recursive Merge

### Algorithm

Split recursively, reverse even list, merge recursively.

### Implementation

```java
public class Solution {
    
    public ListNode sortAlternating(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        // Split into odd and even
        ListNode[] split = splitAlternating(head);
        ListNode odd = split[0];
        ListNode even = split[1];
        
        // Reverse even list
        even = reverseRecursive(even);
        
        // Merge recursively
        return mergeRecursive(odd, even);
    }
    
    private ListNode[] splitAlternating(ListNode head) {
        if (head == null) {
            return new ListNode[]{null, null};
        }
        
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;
        
        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        
        if (odd != null) odd.next = null;
        
        return new ListNode[]{head, evenHead};
    }
    
    private ListNode reverseRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode newHead = reverseRecursive(head.next);
        head.next.next = head;
        head.next = null;
        
        return newHead;
    }
    
    private ListNode mergeRecursive(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        
        if (l1.val <= l2.val) {
            l1.next = mergeRecursive(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeRecursive(l1, l2.next);
            return l2;
        }
    }
}
```

**Complexity:**
- **Time:** O(n)
- **Space:** O(n) - recursion stack

**Pros:**
- Elegant recursive solution
- Good for understanding recursion

**Cons:**
- O(n) space for call stack
- Risk of stack overflow for large lists

---

## 🔧 Approach 4: Single Pass with Dummy Nodes

### Algorithm

Use dummy nodes to collect odd and even nodes in single pass, then process.

### Implementation

```java
public class Solution {
    
    public ListNode sortAlternating(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode oddDummy = new ListNode(0);
        ListNode evenDummy = new ListNode(0);
        ListNode oddTail = oddDummy;
        ListNode evenTail = evenDummy;
        
        ListNode current = head;
        boolean isOdd = true;
        
        // Single pass split
        while (current != null) {
            if (isOdd) {
                oddTail.next = current;
                oddTail = oddTail.next;
            } else {
                evenTail.next = current;
                evenTail = evenTail.next;
            }
            current = current.next;
            isOdd = !isOdd;
        }
        
        // Disconnect tails
        oddTail.next = null;
        evenTail.next = null;
        
        // Reverse even list
        ListNode evenHead = reverse(evenDummy.next);
        
        // Merge
        return mergeSortedLists(oddDummy.next, evenHead);
    }
    
    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
    
    private ListNode mergeSortedLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        
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
}
```

**Pros:**
- Clean dummy node usage
- Good separation of concerns

**Cons:**
- Similar complexity to Approach 1

---

## 📊 Comparison of Approaches

| Approach | Time | Space | Readability | Interview Score |
|----------|------|-------|-------------|-----------------|
| **Split-Reverse-Merge** | O(n) | O(1) | ⭐⭐⭐⭐⭐ | **⭐⭐⭐⭐⭐** |
| **Array Based** | O(n) | O(n) | ⭐⭐⭐⭐⭐ | ⭐⭐⭐ |
| **Recursive** | O(n) | O(n) | ⭐⭐⭐⭐ | ⭐⭐⭐⭐ |
| **Dummy Nodes** | O(n) | O(1) | ⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ |

**Recommendation:** Use **Split-Reverse-Merge** for interviews - optimal and clear.

---

## 🎯 Step-by-Step Dry Run

### Input: 1 → 9 → 2 → 8 → 3 → 7 → 4

**Step 1: Split into Odd and Even**

```
Original: 1 → 9 → 2 → 8 → 3 → 7 → 4

Positions:
1(odd) → 9(even) → 2(odd) → 8(even) → 3(odd) → 7(even) → 4(odd)

After Split:
Odd list:  1 → 2 → 3 → 4 → null
Even list: 9 → 8 → 7 → null
```

**Splitting Process:**
```
Initial:
odd = 1, even = 9

Iteration 1:
odd.next = odd.next.next → 1.next = 2
even.next = even.next.next → 9.next = 8
odd = 2, even = 8

Iteration 2:
odd.next = odd.next.next → 2.next = 3
even.next = even.next.next → 8.next = 7
odd = 3, even = 7

Iteration 3:
odd.next = odd.next.next → 3.next = 4
even.next = even.next.next → 7.next = null
odd = 4, even = null

Loop ends (even is null)

Result:
Odd:  1 → 2 → 3 → 4
Even: 9 → 8 → 7
```

**Step 2: Reverse Even List**

```
Before: 9 → 8 → 7 → null
After:  7 → 8 → 9 → null

Reversal Process:
prev = null, current = 9

Step 1: next = 8, 9.next = null, prev = 9, current = 8
        null ← 9    8 → 7

Step 2: next = 7, 8.next = 9, prev = 8, current = 7
        null ← 9 ← 8    7

Step 3: next = null, 7.next = 8, prev = 7, current = null
        null ← 9 ← 8 ← 7

Result: 7 → 8 → 9
```

**Step 3: Merge Sorted Lists**

```
List 1: 1 → 2 → 3 → 4
List 2: 7 → 8 → 9

Merge Process:
Compare 1 and 7: 1 < 7 → take 1
Result: 1

Compare 2 and 7: 2 < 7 → take 2
Result: 1 → 2

Compare 3 and 7: 3 < 7 → take 3
Result: 1 → 2 → 3

Compare 4 and 7: 4 < 7 → take 4
Result: 1 → 2 → 3 → 4

List 1 exhausted, append List 2
Result: 1 → 2 → 3 → 4 → 7 → 8 → 9
```

**Final Output:** 1 → 2 → 3 → 4 → 7 → 8 → 9 ✓

---

## ⚠️ Edge Cases & Validation

### Edge Cases to Consider

**1. Empty List**
```java
Input: null
Output: null
```

**2. Single Node**
```java
Input: 5
Output: 5
```

**3. Two Nodes**
```java
Input: 3 → 7
Odd: 3, Even: 7
Output: 3 → 7
```

**4. Three Nodes**
```java
Input: 5 → 10 → 3
Odd: 5 → 3, Even: 10
After reverse even: 10
Output: 3 → 5 → 10
```

**5. All Same Values**
```java
Input: 5 → 5 → 5 → 5
Output: 5 → 5 → 5 → 5
```

**6. Already Sorted**
```java
Input: 1 → 2 → 3 → 4 (if alternating)
Need to check actual alternating pattern
```

**7. Reverse Sorted**
```java
Input: 9 → 8 → 7 → 6 (if alternating)
```

**8. Large List**
```java
Input: 10,000 nodes
Test performance and stack limits
```

### Input Validation

```java
public ListNode sortAlternating(ListNode head) {
    // Handle null and single node
    if (head == null || head.next == null) {
        return head;
    }
    
    // Handle two nodes
    if (head.next.next == null) {
        if (head.val > head.next.val) {
            ListNode temp = head.next;
            temp.next = head;
            head.next = null;
            return temp;
        }
        return head;
    }
    
    // Main algorithm...
}
```

---

## 🧪 Test Cases

### Test Case 1: Standard Case
```java
Input: 1 → 9 → 2 → 8 → 3 → 7 → 4
Expected: 1 → 2 → 3 → 4 → 7 → 8 → 9
```

### Test Case 2: Short List
```java
Input: 5 → 10 → 3
Expected: 3 → 5 → 10
```

### Test Case 3: Two Elements
```java
Input: 3 → 7
Expected: 3 → 7

Input: 7 → 3
Expected: 3 → 7
```

### Test Case 4: All Equal
```java
Input: 5 → 5 → 5 → 5
Expected: 5 → 5 → 5 → 5
```

### Test Case 5: Negative Numbers
```java
Input: -5 → 10 → -3 → 8 → -1
Expected: -5 → -3 → -1 → 8 → 10
```

### Test Case 6: Mixed Order
```java
Input: 1 → 2 → 3 → 4 → 5 → 6
Expected: 1 → 2 → 3 → 4 → 5 → 6
```

---

## 💡 Key Concepts Illustrated

### 1. List Splitting Pattern

```java
// Split alternating nodes
ListNode odd = head;
ListNode even = head.next;
ListNode evenHead = even;

while (even != null && even.next != null) {
    odd.next = odd.next.next;    // Skip even nodes
    even.next = even.next.next;  // Skip odd nodes
    odd = odd.next;
    even = even.next;
}

odd.next = null;  // Disconnect lists
```

### 2. In-Place List Reversal

```java
ListNode prev = null;
ListNode current = head;

while (current != null) {
    ListNode next = current.next;  // Save next
    current.next = prev;           // Reverse link
    prev = current;                // Move prev
    current = next;                // Move current
}

return prev;  // New head
```

### 3. Merge Two Sorted Lists

```java
ListNode dummy = new ListNode(0);
ListNode tail = dummy;

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

// Attach remaining
tail.next = (l1 != null) ? l1 : l2;
return dummy.next;
```

### 4. Combining Multiple Operations

```java
// Pattern: Split → Process → Merge
public ListNode complexOperation(ListNode head) {
    // 1. Split into components
    ListNode[] parts = split(head);
    
    // 2. Process each part
    for (int i = 0; i < parts.length; i++) {
        parts[i] = process(parts[i]);
    }
    
    // 3. Merge results
    return merge(parts);
}
```

### 5. Position-Based Processing

```java
// Track odd/even positions
boolean isOdd = true;
while (current != null) {
    if (isOdd) {
        // Process odd position
    } else {
        // Process even position
    }
    isOdd = !isOdd;
    current = current.next;
}
```

---

## 🎓 Interview Discussion Points

### When Interviewer Asks This Problem

**1. Clarify Requirements**
```
Q: "Are we guaranteed the alternating pattern?"
Q: "Can the list have duplicate values?"
Q: "Should we sort in ascending or descending order?"
Q: "Can we use extra space?"
```

**2. Explain Your Approach**
```
"I'll solve this in three steps:

Step 1 - Split the list into two sublists:
  - Odd positions (1st, 3rd, 5th...) are already ascending
  - Even positions (2nd, 4th, 6th...) are in descending order

Step 2 - Reverse the even-positioned list:
  - Convert descending order to ascending

Step 3 - Merge the two sorted lists:
  - Standard merge of two sorted lists

Time: O(n) for each step = O(n) total
Space: O(1) - all operations are in-place"
```

**3. Discuss Trade-offs**
```
"Alternative approaches:

1. Extract to arrays, sort, rebuild:
   - Simpler but uses O(n) extra space
   - Not optimal for linked list problem

2. Recursive approach:
   - Elegant but O(n) stack space
   - Risk of stack overflow

3. Current approach (Split-Reverse-Merge):
   - O(1) space (optimal)
   - Clear three-phase logic
   - Reuses standard algorithms
   - Best for interviews"
```

### Common Follow-up Questions

**Q: "What if we need descending order output?"**
```java
A: "Two options:

1. Reverse the final merged list:
   return reverse(mergeSortedLists(odd, even));

2. Or don't reverse the even list and merge differently:
   - Keep even list descending
   - Merge odd (ascending) with even (descending)
   - Would need custom merge logic"
```

**Q: "Can you optimize the number of passes?"**
```java
A: "We could merge during the split phase:

ListNode dummy = new ListNode(0);
ListNode tail = dummy;

// Split and merge simultaneously
// But this makes code more complex
// Current 3-pass approach is clearer and still O(n)"
```

**Q: "How would you handle a doubly linked list?"**
```java
A: "For doubly linked list:

1. Split would also update prev pointers
2. Reversal would swap next and prev
3. Merge would maintain both directions

Code becomes more complex but same O(n) time."
```

**Q: "What if positions are 0-indexed instead?"**
```java
A: "Then:
- Even positions (0, 2, 4...) would be ascending
- Odd positions (1, 3, 5...) would be descending

We'd reverse the second list instead:
ListNode first = head;
ListNode second = head.next;
first = reverse(first);  // Reverse first list now
return mergeSortedLists(first, second);"
```

---

## 🔄 Related Problems

### Similar Problems to Practice

1. **Sort List** - General linked list sorting
2. **Merge Two Sorted Lists** - Building block for this problem
3. **Reverse Linked List** - Another building block
4. **Odd Even Linked List** - Similar splitting pattern
5. **Partition List** - Split based on value
6. **Merge K Sorted Lists** - Extended merging

### Difficulty Progression

```
Reverse Linked List (Easy)
    ↓
Merge Two Sorted Lists (Easy)
    ↓
Sort Alternating (Medium) ← You are here
    ↓
Sort List (Medium)
    ↓
Merge K Sorted Lists (Hard)
```

---

## 🎯 Variations to Practice

### Variation 1: Different Patterns
```java
public ListNode sortPattern(ListNode head, String pattern) {
    // pattern = "ADAD..." (A=ascending, D=descending)
    // Sort based on custom pattern
}
```

### Variation 2: K-way Split
```java
public ListNode sortKWayAlternating(ListNode head, int k) {
    // Split into k sublists with different orders
    // Sort and merge all k lists
}
```

### Variation 3: Return Both Lists
```java
public ListNode[] splitAndSort(ListNode head) {
    // Return [oddSorted, evenSorted] separately
}
```

### Variation 4: In-Place Sort Without Split
```java
public ListNode sortInPlace(ListNode head) {
    // Sort without explicitly splitting
    // More complex but possible
}
```

---

## 📚 Additional Learning

### Why This Problem is Important

1. **Combines Multiple Techniques** - Split, reverse, merge
2. **Real-World Application** - Data reorganization
3. **Tests Understanding** - Multiple linked list operations
4. **Interview Favorite** - Tests problem-solving approach
5. **Space Optimization** - O(1) solution exists

### Key Algorithmic Patterns

**Pattern 1: Divide and Conquer**
- Split problem into smaller parts
- Solve each part
- Combine solutions

**Pattern 2: List Manipulation**
- Splitting lists by position
- Reversing lists in-place
- Merging sorted lists

**Pattern 3: Multiple Passes**
- Sometimes multiple passes are clearer
- Still O(n) if each pass is O(n)
- Easier to debug and maintain

---

## 🔗 Resources

- [LeetCode: Sort List](https://leetcode.com/problems/sort-list/)
- [LeetCode: Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/)
- [LeetCode: Odd Even Linked List](https://leetcode.com/problems/odd-even-linked-list/)
- [GeeksforGeeks: Sort Linked List](https://www.geeksforgeeks.org/merge-sort-for-linked-list/)

---

<div align="center">

**Master List Manipulation, One Operation at a Time! 🔀**

*Split, Process, Merge - The Power of Decomposition*

*Last Updated: December 2024*

</div>
