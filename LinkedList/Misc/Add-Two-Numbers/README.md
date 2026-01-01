# Add Two Numbers

## Problem Statement

You are given two **non-empty** linked lists representing two non-negative integers. The digits are stored in **reverse order**, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

### Problem Source
- **Platform**: LeetCode
- **Problem Number**: #2
- **Difficulty**: Medium
- **Topics**: Linked List, Math, Recursion

## 📋 Examples

### Example 1:
```
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807
```

**Visual Representation:**
```
    2 → 4 → 3       (represents 342)
  + 5 → 6 → 4       (represents 465)
  ___________
    7 → 0 → 8       (represents 807)
```

### Example 2:
```
Input: l1 = [0], l2 = [0]
Output: [0]
```

### Example 3:
```
Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]
Explanation: 9999999 + 9999 = 10009998
```

## 🎯 Approach

### Algorithm Overview

The solution simulates the process of adding two numbers digit by digit, similar to how we add numbers on paper, but in reverse order (since the linked lists store digits in reverse).

### Key Concepts

1. **Carry Handling**: When the sum of two digits exceeds 9, we carry over to the next position
2. **Different Length Lists**: Handle cases where one list is longer than the other
3. **Final Carry**: If there's a carry remaining after processing all digits, add it as a new node

### Step-by-Step Process

1. **Initialize Variables**
   - Two pointers (`temp1`, `temp2`) to traverse both lists
   - A `head` pointer for the result list
   - A `prev` pointer to track the last node
   - A `carry` variable to handle digit overflow

2. **Process Both Lists Simultaneously**
   - Add corresponding digits from both lists along with carry
   - Create new node with `(sum % 10)`
   - Update carry as `(sum / 10)`

3. **Process Remaining Digits in l1** (if l1 is longer)
   - Continue adding digits with carry

4. **Process Remaining Digits in l2** (if l2 is longer)
   - Continue adding digits with carry

5. **Handle Final Carry**
   - If carry exists after all digits are processed, add it as final node

## 💻 Implementation

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode temp1 = l1, temp2 = l2;
        ListNode head = null, prev = null;
        int carry = 0;
        
        // Process both lists while both have nodes
        while (temp1 != null && temp2 != null) {
            int sum = carry + temp1.val + temp2.val;
            carry = sum / 10;
            ListNode node = new ListNode(sum % 10);
            
            if (head == null) {
                head = node;
                prev = node;
            } else {
                prev.next = node;
                prev = node;
            }
            
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        
        // Process remaining nodes in l1
        while (temp1 != null) {
            int sum = carry + temp1.val;
            carry = sum / 10;
            ListNode node = new ListNode(sum % 10);
            
            if (head == null) {
                head = node;
                prev = node;
            } else {
                prev.next = node;
                prev = node;
            }
            
            temp1 = temp1.next;
        }
        
        // Process remaining nodes in l2
        while (temp2 != null) {
            int sum = carry + temp2.val;
            carry = sum / 10;
            ListNode node = new ListNode(sum % 10);
            
            if (head == null) {
                head = node;
                prev = node;
            } else {
                prev.next = node;
                prev = node;
            }
            
            temp2 = temp2.next;
        }
        
        // Handle final carry
        if (carry != 0) {
            ListNode node = new ListNode(carry);
            if (head == null) {
                head = node;
            } else {
                prev.next = node;
            }
        }
        
        return head;
    }
}
```

## 📊 Complexity Analysis

### Time Complexity: **O(max(m, n))**
- Where `m` is the length of `l1` and `n` is the length of `l2`
- We traverse both lists completely once
- Each node is visited exactly once

### Space Complexity: **O(max(m, n))**
- The length of the result list is at most `max(m, n) + 1`
- We create new nodes for the result list
- No additional data structures used besides the result list

## 🎨 Dry Run Example

Let's trace through Example 1: `l1 = [2,4,3]`, `l2 = [5,6,4]`

| Iteration | temp1.val | temp2.val | carry (before) | sum | carry (after) | new node | result list |
|-----------|-----------|-----------|----------------|-----|---------------|----------|-------------|
| 1         | 2         | 5         | 0              | 7   | 0             | 7        | [7]         |
| 2         | 4         | 6         | 0              | 10  | 1             | 0        | [7,0]       |
| 3         | 3         | 4         | 1              | 8   | 0             | 8        | [7,0,8]     |

**Final Result**: `[7,0,8]` representing 807

## 🔍 Edge Cases Handled

1. **Empty Lists**: Not applicable (problem states non-empty lists)
2. **Different Length Lists**: Handled by separate while loops
3. **Final Carry**: Checked and added after all digits processed
4. **Single Digit Numbers**: Works correctly with minimal iterations
5. **Numbers with Leading Zeros**: Handled naturally by the algorithm
6. **Maximum Carry Propagation**: Example 3 demonstrates this

## ✅ Test Cases

### Test Case 1: Equal Length Lists
```java
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Status: ✓ Passed
```

### Test Case 2: Different Length Lists
```java
Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]
Status: ✓ Passed
```

### Test Case 3: Single Digits
```java
Input: l1 = [0], l2 = [0]
Output: [0]
Status: ✓ Passed
```

### Test Case 4: Carry Propagation
```java
Input: l1 = [9,9], l2 = [1]
Output: [0,0,1]
Status: ✓ Passed
```

## 🚀 Optimization Possibilities

### Current Implementation
- ✅ Simple and readable
- ✅ Handles all edge cases
- ✅ No recursion (avoids stack overflow)
- ❌ Code repetition in three while loops

### Potential Improvements

1. **Refactor Common Code**
```java
// Create a helper method to reduce code duplication
private ListNode createAndAppendNode(int value, ListNode head, ListNode prev) {
    ListNode node = new ListNode(value);
    if (head == null) {
        head = node;
        prev = node;
    } else {
        prev.next = node;
        prev = node;
    }
    return prev;
}
```

2. **Dummy Head Approach**
```java
// Use a dummy head to eliminate special case handling
ListNode dummy = new ListNode(0);
ListNode current = dummy;
// ... processing logic
return dummy.next;
```

3. **Single Loop Implementation**
```java
// Combine all loops into one with conditional checks
while (temp1 != null || temp2 != null || carry != 0) {
    int val1 = (temp1 != null) ? temp1.val : 0;
    int val2 = (temp2 != null) ? temp2.val : 0;
    // ... rest of logic
}
```

## 📚 Related Problems

- **LeetCode #445**: Add Two Numbers II (Numbers stored in normal order)
- **LeetCode #67**: Add Binary (String addition)
- **LeetCode #43**: Multiply Strings
- **LeetCode #415**: Add Strings

## 🎓 Key Takeaways

1. **Linked List Traversal**: Using multiple pointers to track different positions
2. **Carry Handling**: Essential in digit-by-digit arithmetic operations
3. **Edge Case Management**: Handling different length inputs and final carry
4. **Memory Management**: Creating new nodes dynamically
5. **Problem Pattern**: Similar to elementary school addition but in code

## 🏷️ Tags

`Linked List` `Math` `Two Pointers` `Elementary Math` `Digit Addition` `Carry Propagation`

---

## 📝 Notes

- This solution uses an iterative approach (no recursion)
- Three separate loops make the logic clear and easy to understand
- Can be optimized for cleaner code using dummy head technique
- Time and space complexity are optimal for this problem

## 👤 Author

**Aadarsh Singh**
- GitHub: [@singh-aadarsh330](https://github.com/singh-aadarsh330)
- LinkedIn: [singh-aadarsh330](https://www.linkedin.com/in/singh-aadarsh330)
- GeeksforGeeks: [singhaadarsh330](https://www.geeksforgeeks.org/user/singhaadarsh330)

---

*Part of DSA Practice Repository*  
*Problem solved on: December 2024*  
*Language: Java*
