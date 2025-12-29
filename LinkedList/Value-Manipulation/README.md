# Value Manipulation 🔢

Problems involving mathematical operations and value transformations on linked list nodes.

## 📊 Statistics

- **Total Problems:** 6
- **Difficulty:** Easy to Hard
- **Topics:** Number operations, Mathematical transformations, Prime numbers

## 📁 Problems in this Category

### 1. Add Number Linked Lists
**Difficulty:** Medium  
**Problem:** Add two numbers represented by linked lists.

**Key Concepts:**
- Carry propagation
- Reverse traversal or recursion
- Edge cases with different lengths

**Approach:**
```
1. Reverse both lists (optional)
2. Add digits with carry
3. Create result list
4. Handle remaining carry
```

**Time Complexity:** O(max(m,n))  
**Space Complexity:** O(max(m,n))

---

### 2. Add 1 to a Linked List Number
**Difficulty:** Medium  
**Problem:** Add 1 to a number represented as a linked list.

**Key Concepts:**
- Carry handling
- Reverse operations
- Edge case: All 9s

**Approaches:**
1. Reverse → Add → Reverse back
2. Recursion with carry
3. Find rightmost non-9 digit

**Time Complexity:** O(n)  
**Space Complexity:** O(1) or O(n) based on approach

---

### 3. Multiply Two Linked Lists
**Difficulty:** Easy  
**Problem:** Multiply two numbers represented by linked lists.

**Key Concepts:**
- Number formation from linked list
- Modulo operations
- Long integer handling

**Approach:**
```
1. Convert first list to number
2. Convert second list to number
3. Multiply the numbers
4. Apply modulo if needed
```

**Time Complexity:** O(m + n)  
**Space Complexity:** O(1)

---

### 4. Decimal Equivalent of Binary Linked List
**Difficulty:** Easy  
**Problem:** Convert binary number in linked list to decimal.

**Key Concepts:**
- Binary to decimal conversion
- Power of 2 calculations
- Left to right traversal

**Approach:**
```
Formula: result = result * 2 + current_bit
Traverse from left to right
```

**Time Complexity:** O(n)  
**Space Complexity:** O(1)

---

### 5. Subtraction in Linked List
**Difficulty:** Hard  
**Problem:** Subtract two numbers represented by linked lists.

**Key Concepts:**
- Borrow handling
- Magnitude comparison
- Sign management

**Approach:**
```
1. Compare magnitudes
2. Subtract smaller from larger
3. Handle borrow propagation
4. Set appropriate sign
```

**Time Complexity:** O(max(m,n))  
**Space Complexity:** O(max(m,n))

---

### 6. Prime List
**Difficulty:** Medium  
**Problem:** Convert all composite values in linked list to their nearest prime.

**Key Concepts:**
- Prime number checking
- Finding nearest prime
- In-place modification

**Approach:**
```
1. Traverse the list
2. For each composite number:
   - Find nearest prime
   - Update node value
3. Handle edge cases (0, 1)
```

**Helper Function:** Check if number is prime
```java
boolean isPrime(int n) {
    if (n <= 1) return false;
    if (n <= 3) return true;
    if (n % 2 == 0 || n % 3 == 0) return false;
    for (int i = 5; i * i <= n; i += 6) {
        if (n % i == 0 || n % (i + 2) == 0)
            return false;
    }
    return true;
}
```

**Time Complexity:** O(n × √m) where m is max value  
**Space Complexity:** O(1)

---

## 🎯 Common Patterns

### Pattern 1: Number Formation
```java
long num = 0;
while (head != null) {
    num = (num * base + head.data) % MOD;
    head = head.next;
}
```

### Pattern 2: Carry Propagation
```java
int carry = 0;
while (carry > 0 || node != null) {
    int sum = carry + (node != null ? node.data : 0);
    carry = sum / 10;
    digit = sum % 10;
}
```

### Pattern 3: Digit-by-Digit Operations
```java
// For operations like addition, subtraction
- Align numbers by reversing or using recursion
- Process from least significant digit
- Handle carry/borrow
```

## 💡 Key Insights

1. **Modulo Operations:** Often required for large numbers (MOD = 10^9 + 7)
2. **Edge Cases:** 
   - Numbers with different lengths
   - Leading zeros
   - All zeros
   - Carry overflow
3. **Optimization:** In-place operations save space
4. **Precision:** Use long or BigInteger for large values

## 🔧 Helper Functions

```java
// Reverse a linked list
Node reverse(Node head) { ... }

// Get length of linked list
int getLength(Node head) { ... }

// Check if prime
boolean isPrime(int n) { ... }

// Find nearest prime
int nearestPrime(int n) { ... }
```

## 📈 Complexity Summary

| Problem | Time | Space |
|---------|------|-------|
| Add Numbers | O(max(m,n)) | O(max(m,n)) |
| Add 1 | O(n) | O(1) |
| Multiply | O(m+n) | O(1) |
| Binary to Decimal | O(n) | O(1) |
| Subtraction | O(max(m,n)) | O(max(m,n)) |
| Prime List | O(n×√m) | O(1) |

## 🎓 Learning Objectives

After completing this section, you should be able to:
- ✅ Perform arithmetic operations on linked lists
- ✅ Handle carry and borrow propagation
- ✅ Convert between number representations
- ✅ Apply mathematical algorithms to linked lists
- ✅ Optimize space using in-place operations
- ✅ Handle edge cases in numeric operations

## 🚀 Practice Tips

1. **Start with simpler problems:** Decimal Equivalent → Multiply → Add
2. **Master carry handling:** Essential for addition/subtraction
3. **Consider edge cases:** Different lengths, zeros, overflow
4. **Optimize space:** Try in-place solutions
5. **Test thoroughly:** Multiple test cases with various inputs

## 📚 Related Topics

- Number Theory
- Mathematical Algorithms
- Recursion
- In-place Operations
- Modular Arithmetic

---

**Next Steps:** Try solving these problems in order of difficulty, then explore advanced variations!

*Happy Problem Solving! 🎯*
