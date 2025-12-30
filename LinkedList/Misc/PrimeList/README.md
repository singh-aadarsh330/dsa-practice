# Prime List 🔢

**Difficulty:** Medium  
**Category:** Value Manipulation  
**Topics:** Linked List, Prime Numbers, Mathematical Algorithms

---

## 📋 Problem Statement

Given a singly linked list, the task is to modify the value of all nodes which are **not prime** to the nearest prime number. If there are two primes at equal distance from a non-prime number, then the smaller prime should be chosen.

### Example 1:
```
Input:  2 → 6 → 10
Output: 2 → 5 → 11

Explanation:
- 2 is prime, keep as is
- 6 is not prime. Nearest primes: 5 (distance 1), 7 (distance 1)
  Choose smaller: 5
- 10 is not prime. Nearest primes: 11 (distance 1), 7 (distance 3)
  Choose 11 (smaller distance)
```

### Example 2:
```
Input:  1 → 15 → 20
Output: 2 → 13 → 19

Explanation:
- 1 is not prime. Nearest prime: 2
- 15 is not prime. Nearest primes: 13 (distance 2), 17 (distance 2)
  Choose smaller: 13
- 20 is not prime. Nearest prime: 19 (distance 1)
```

### Example 3:
```
Input:  4 → 9 → 16 → 25
Output: 3 → 7 → 17 → 23

Explanation:
- 4: Nearest primes are 3 (dist 1) and 5 (dist 1) → Choose 3
- 9: Nearest primes are 7 (dist 2) and 11 (dist 2) → Choose 7
- 16: Nearest primes are 13 (dist 3) and 17 (dist 1) → Choose 17
- 25: Nearest primes are 23 (dist 2) and 29 (dist 4) → Choose 23
```

---

## 🎯 Constraints

- 1 ≤ Number of nodes ≤ 10^4
- 1 ≤ Node value ≤ 10^5

---

## 💡 Approach

### Algorithm Steps:

1. **Traverse the linked list** node by node
2. **For each node:**
   - Check if the value is prime
   - If prime, move to next node
   - If not prime, find nearest prime
3. **Finding nearest prime:**
   - Search in both directions (smaller and larger)
   - Keep track of distances
   - Choose smaller prime if distances are equal
4. **Update node value** with nearest prime
5. **Return modified list**

### Key Components:

#### 1. Prime Check Function
```java
boolean isPrime(int n) {
    if (n <= 1) return false;
    if (n == 2 || n == 3) return true;
    if (n % 2 == 0 || n % 3 == 0) return false;
    
    for (int i = 5; i * i <= n; i += 6) {
        if (n % i == 0 || n % (i + 2) == 0)
            return false;
    }
    return true;
}
```

**Optimization:** Check divisibility by 2 and 3, then check numbers of form 6k±1

#### 2. Find Nearest Prime Function
```java
int nearestPrime(int n) {
    // Special case for numbers <= 2
    if (n <= 2) return 2;
    
    int lower = n - 1;
    int upper = n + 1;
    
    // Search in both directions
    while (true) {
        // Check lower first (for equal distance, choose smaller)
        if (lower >= 2 && isPrime(lower))
            return lower;
        
        if (isPrime(upper))
            return upper;
        
        lower--;
        upper++;
    }
}
```

#### 3. Main Function
```java
Node primeList(Node head) {
    Node current = head;
    
    while (current != null) {
        if (!isPrime(current.data)) {
            current.data = nearestPrime(current.data);
        }
        current = current.next;
    }
    
    return head;
}
```

---

## 🔍 Complexity Analysis

### Time Complexity: **O(n × √m)**
- **n** = number of nodes in linked list
- **m** = maximum value in the list
- For each node: O(√m) for prime checking
- Finding nearest prime: O(d × √m) where d is distance to nearest prime
- Overall: O(n × √m) in average case

### Space Complexity: **O(1)**
- In-place modification
- Only constant extra space for variables
- No additional data structures needed

---

## 📊 Edge Cases to Handle

1. **Single node list:**
   ```
   Input:  4
   Output: 3
   ```

2. **All prime numbers:**
   ```
   Input:  2 → 3 → 5 → 7
   Output: 2 → 3 → 5 → 7
   (No changes)
   ```

3. **All composite numbers:**
   ```
   Input:  4 → 6 → 8 → 9
   Output: 3 → 5 → 7 → 7
   ```

4. **Number 1 (not prime):**
   ```
   Input:  1
   Output: 2
   ```

5. **Number 0:**
   ```
   Input:  0
   Output: 2
   ```

6. **Large numbers:**
   ```
   Input:  100
   Output: 101 (nearest prime)
   ```

7. **Equal distance scenario:**
   ```
   Input:  6
   Output: 5 (not 7, choose smaller)
   ```

---

## 🎓 Key Concepts

### 1. Prime Number Properties
- Numbers divisible only by 1 and themselves
- 2 is the only even prime
- All primes > 3 are of form 6k±1

### 2. Optimization Techniques
- Check only up to √n for divisibility
- Skip even numbers after checking 2
- Use 6k±1 pattern for efficiency

### 3. Search Strategy
- Bidirectional search for nearest prime
- Check lower values first (for equal distance)
- Increment/decrement until prime found

---

## 🔧 Complete Java Implementation

```java
class Node {
    int data;
    Node next;
    
    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class Solution {
    // Check if a number is prime
    boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n == 2 || n == 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0)
                return false;
        }
        return true;
    }
    
    // Find nearest prime to n
    int nearestPrime(int n) {
        // Edge case: n <= 2
        if (n <= 2) return 2;
        
        int lower = n - 1;
        int upper = n + 1;
        
        // Search in both directions
        while (true) {
            // Check lower first (smaller prime preferred)
            if (lower >= 2 && isPrime(lower))
                return lower;
            
            if (isPrime(upper))
                return upper;
            
            lower--;
            upper++;
        }
    }
    
    // Main function to convert list to prime list
    Node primeList(Node head) {
        Node current = head;
        
        while (current != null) {
            if (!isPrime(current.data)) {
                current.data = nearestPrime(current.data);
            }
            current = current.next;
        }
        
        return head;
    }
}
```

---

## 🧪 Test Cases

```java
// Test Case 1: Mixed values
Input:  2 → 6 → 10
Output: 2 → 5 → 11

// Test Case 2: Starting with 1
Input:  1 → 15 → 20
Output: 2 → 13 → 19

// Test Case 3: All composites
Input:  4 → 9 → 16 → 25
Output: 3 → 7 → 17 → 23

// Test Case 4: All primes
Input:  2 → 3 → 5 → 7 → 11
Output: 2 → 3 → 5 → 7 → 11

// Test Case 5: Single node
Input:  10
Output: 11

// Test Case 6: Large numbers
Input:  100 → 200 → 300
Output: 101 → 199 → 293
```

---

## 💻 How to Run

```java
public class Main {
    public static void main(String[] args) {
        // Create linked list: 2 → 6 → 10
        Node head = new Node(2);
        head.next = new Node(6);
        head.next.next = new Node(10);
        
        Solution sol = new Solution();
        head = sol.primeList(head);
        
        // Print result
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        // Output: 2 5 11
    }
}
```

---

## 🎯 Common Mistakes to Avoid

1. ❌ **Not handling n ≤ 1 properly**
   - Remember: 1 is not prime, 2 is the smallest prime

2. ❌ **Not choosing smaller prime for equal distances**
   - Always check lower values first

3. ❌ **Inefficient prime checking**
   - Use √n optimization, not checking all numbers

4. ❌ **Modifying list structure instead of values**
   - Only change node.data, not node.next

5. ❌ **Not handling edge case: 0**
   - Should convert to 2 (nearest prime)

---

## 📈 Optimization Tips

1. **Sieve of Eratosthenes:** Pre-compute primes up to max value
   - Time: O(m log log m) preprocessing
   - Space: O(m)
   - Useful when list is large

2. **Caching:** Store computed nearest primes
   - Avoid recalculation for duplicate values
   - Use HashMap<Integer, Integer>

3. **Early termination:** In prime check
   - Return false as soon as divisor found

---

## 🔗 Related Problems

1. **Count Primes** - Count prime numbers less than n
2. **Prime Factors** - Find all prime factors of a number
3. **Goldbach's Conjecture** - Express even number as sum of two primes
4. **Twin Primes** - Find pairs of primes with difference 2
5. **Happy Numbers** - Cycle detection with prime-like operations

---

## 📚 Further Reading

- [Prime Number Algorithms](https://cp-algorithms.com/algebra/primality_tests.html)
- [Sieve of Eratosthenes](https://www.geeksforgeeks.org/sieve-of-eratosthenes/)
- [Linked List Modifications](https://www.geeksforgeeks.org/data-structures/linked-list/)

---

## 🏆 Problem Link

[Solve on GeeksforGeeks](https://www.geeksforgeeks.org/problems/prime-list/)

---

**Happy Coding! 🚀**

*Master prime number algorithms and linked list manipulation with this problem!*
