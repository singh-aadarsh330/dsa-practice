# 🔢 Next Prime Number

[![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green?style=flat-square)](.)
[![Concept](https://img.shields.io/badge/Concept-Prime%20Numbers-blue?style=flat-square)](.)

> Find the smallest prime number strictly greater than a given integer. Master $6k ± 1$ optimization and trial division.

---

## 📋 Problem Description

### The Challenge

Given an integer `n`, find the first prime number that occurs after `n`.

**Rules:**
1. A **prime number** is a natural number greater than 1 that has no positive divisors other than 1 and itself.
2. The search must be **strictly greater** than `n$.
3. Handle edge cases where `n < 2$ (the first prime is 2).

**Input:**
- An integer `n` (supports `long` range).

**Output:**
- The next prime number as a `long`.

---

### Examples

**Example 1: Standard Input**
```
Input: 10
Output: 11

Explanation: 11 is the first prime after 10
```

**Example 2: Input is Prime**
```
Input: 7
Output: 11

Explanation: Even though 7 is prime, we need the NEXT prime after 7
```

**Example 3: Input < 2**
```
Input: -5
Output: 2

Explanation: First prime is 2
```

**Example 4: Edge Case - 2**
```
Input: 2
Output: 3

Explanation: 3 is the first prime after 2
```

**Example 5: Large Number**
```
Input: 100
Output: 101

Explanation: 101 is prime and immediately follows 100
```

---

## 🎯 Learning Objectives

This problem teaches:
- **Prime number checking** - Fundamental number theory
- **Trial division** - Basic primality testing
- **$6k ± 1$ optimization** - Efficient checking
- **Edge case handling** - Numbers less than 2
- **Loop optimization** - When to stop checking

---

## 🔑 Key Concepts

### 1. What is a Prime Number?

**Definition:**
- Natural number > 1
- Only divisors are 1 and itself
- Examples: 2, 3, 5, 7, 11, 13, 17, 19, 23, 29...

**Non-Primes (Composite):**
- 4 (2×2), 6 (2×3), 8 (2×4), 9 (3×3), 10 (2×5)...

**Special Cases:**
- **2** is the only even prime
- **1** is NOT prime (by definition)
- **Negative numbers** are NOT prime

### 2. Prime Checking Algorithm (Trial Division)

**Basic Approach:**
```java
boolean isPrime(int n) {
    if (n <= 1) return false;
    if (n <= 3) return true;
    if (n % 2 == 0 || n % 3 == 0) return false;
    
    // Check divisors up to √n
    for (int i = 5; i * i <= n; i += 6) {
        if (n % i == 0 || n % (i + 2) == 0) {
            return false;
        }
    }
    return true;
}
```

**Why check only up to √n?**
```
If n = a × b and a ≤ b, then a ≤ √n
So if n has a divisor, one must be ≤ √n
```

### 3. The $6k ± 1$ Optimization

**Key Insight:**
All primes > 3 are of the form `6k ± 1`

**Why?**
```
Numbers divisible by 2: 6k, 6k+2, 6k+4
Numbers divisible by 3: 6k, 6k+3
Remaining: 6k+1, 6k+5 (same as 6k-1)

So we only check: 5, 7, 11, 13, 17, 19, 23, 25, ...
                  (skip 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, ...)
```

---

## 🔧 Approach 1: Basic Trial Division

### Algorithm

1. Start from `n + 1`
2. Check if current number is prime
3. If prime, return it
4. Otherwise, increment and repeat

### Implementation

```java
public long nextPrime(long n) {
    // Start checking from next number
    long candidate = n + 1;
    
    // Handle edge case: ensure candidate >= 2
    if (candidate < 2) {
        return 2;
    }
    
    // Find next prime
    while (!isPrime(candidate)) {
        candidate++;
    }
    
    return candidate;
}

private boolean isPrime(long n) {
    if (n <= 1) return false;
    if (n <= 3) return true;
    if (n % 2 == 0 || n % 3 == 0) return false;
    
    // Check using 6k ± 1 optimization
    for (long i = 5; i * i <= n; i += 6) {
        if (n % i == 0 || n % (i + 2) == 0) {
            return false;
        }
    }
    
    return true;
}
```

**Complexity:**
- **Time:** O(k × √m) where k = numbers checked, m = size of candidate
  - Worst case: O(n × √n) if many composites
  - Average: Much better due to prime density
- **Space:** O(1) - only using variables

**Pros:**
- Simple and clear
- No preprocessing needed
- Works for any range

**Cons:**
- Checks every number sequentially
- Can be slow for large gaps between primes

---

## 🔧 Approach 2: Skip Even Numbers

### Optimization

After checking 2, skip all even numbers (they can't be prime).

### Implementation

```java
public long nextPrime(long n) {
    // Special case: if n < 2, first prime is 2
    if (n < 2) return 2;
    
    // Special case: if n is 2, next prime is 3
    if (n == 2) return 3;
    
    // Start from next odd number
    long candidate = (n % 2 == 0) ? n + 1 : n + 2;
    
    // Check only odd numbers
    while (!isPrime(candidate)) {
        candidate += 2;  // Skip even numbers
    }
    
    return candidate;
}

private boolean isPrime(long n) {
    if (n <= 1) return false;
    if (n == 2 || n == 3) return true;
    if (n % 2 == 0 || n % 3 == 0) return false;
    
    for (long i = 5; i * i <= n; i += 6) {
        if (n % i == 0 || n % (i + 2) == 0) {
            return false;
        }
    }
    
    return true;
}
```

**Complexity:**
- **Time:** ~50% faster than Approach 1
- **Space:** O(1)

**Pros:**
- Skips half the candidates
- Still simple logic

---

## 🔧 Approach 3: Using Math Properties

### Further Optimization

Combine multiple optimizations for best performance.

### Implementation

```java
public long nextPrime(long n) {
    // Handle small cases
    if (n < 2) return 2;
    if (n == 2) return 3;
    
    // Make sure we start with an odd number
    long candidate = n + 1;
    if (candidate % 2 == 0) candidate++;
    
    // Check odd numbers until we find a prime
    while (true) {
        if (isPrime(candidate)) {
            return candidate;
        }
        candidate += 2;  // Only check odd numbers
    }
}

private boolean isPrime(long n) {
    if (n <= 1) return false;
    if (n == 2) return true;
    if (n % 2 == 0) return false;
    if (n == 3) return true;
    if (n % 3 == 0) return false;
    
    // All primes > 3 are of form 6k ± 1
    long sqrt = (long) Math.sqrt(n);
    for (long i = 5; i <= sqrt; i += 6) {
        if (n % i == 0 || n % (i + 2) == 0) {
            return false;
        }
    }
    
    return true;
}
```

**Optimization Details:**
- Pre-check divisibility by 2 and 3
- Use 6k±1 pattern for checking
- Calculate √n once
- Skip even candidates

---

## 📊 Comparison of Approaches

| Approach | Candidates Checked | Prime Check | Best For |
|----------|-------------------|-------------|----------|
| **Basic** | All numbers | Trial division | **Learning** |
| **Skip Evens** | Odd numbers only | Trial division | Better performance |
| **Optimized** | Odd + 6k±1 | 6k±1 pattern | **Production** |

**Recommendation:** Use **Optimized** approach for best performance.

---

## 🎯 Step-by-Step Dry Run

### Input: n = 10

**Step 1: Initialize**
```
n = 10
candidate = 11 (next odd after 10)
```

**Step 2: Check if 11 is prime**
```
11 <= 1? No
11 == 2? No
11 % 2 == 0? No (11 is odd)
11 == 3? No
11 % 3 == 0? 11 ÷ 3 = 3 remainder 2, No

Check divisors using 6k±1:
  i = 5: 5² = 25 > 11, stop
  
No divisors found → 11 is prime!
```

**Step 3: Return**
```
Output: 11
```

---

### Input: n = 7

**Step 1: Initialize**
```
n = 7
candidate = 9 (next odd after 7)
```

**Step 2: Check if 9 is prime**
```
9 % 3 == 0? Yes!
9 is not prime
```

**Step 3: Try next odd**
```
candidate = 11
```

**Step 4: Check if 11 is prime**
```
(Same as above)
11 is prime!
```

**Step 5: Return**
```
Output: 11
```

---

## ⚠️ Edge Cases & Validation

### Edge Cases

**1. Input < 2**
```java
Input: -5, 0, 1
Output: 2
Reason: 2 is the first prime number
```

**2. Input = 2**
```java
Input: 2
Output: 3
Reason: Next prime after 2 is 3
```

**3. Input is Large Prime**
```java
Input: 97
Output: 101
Check: 98(no), 99(no), 100(no), 101(yes)
```

**4. Large Gap Between Primes**
```java
Input: 89
Output: 97
Gap: 8 numbers between consecutive primes
```

**5. Very Large Numbers**
```java
Input: 1000000
Output: 1000003
Must handle long type efficiently
```

### Input Validation

```java
public long nextPrime(long n) {
    // No need to validate - all inputs are valid
    // Just handle n < 2 case
    if (n < 2) return 2;
    
    // Main logic...
}
```

---

## 🧪 Test Cases

### Test Case 1: Basic
```java
Input: 10
Expected: 11
Reason: 11 is first prime > 10
```

### Test Case 2: Input is Prime
```java
Input: 7
Expected: 11
Reason: Next prime after 7 (not 7 itself)
```

### Test Case 3: Negative Number
```java
Input: -100
Expected: 2
Reason: First prime is 2
```

### Test Case 4: Zero
```java
Input: 0
Expected: 2
Reason: First prime is 2
```

### Test Case 5: One
```java
Input: 1
Expected: 2
Reason: First prime is 2
```

### Test Case 6: Two
```java
Input: 2
Expected: 3
Reason: Next prime after 2 is 3
```

### Test Case 7: Large Number
```java
Input: 100
Expected: 101
Reason: 101 is prime
```

### Test Case 8: Even Number
```java
Input: 50
Expected: 53
Check: 51(3×17), 52(4×13), 53(prime)
```

---

## 💡 Mathematical Insights

### 1. Prime Number Theorem

**Density of Primes:**
```
Number of primes ≤ n ≈ n / ln(n)

Average gap between primes near n ≈ ln(n)

For n = 100: average gap ≈ ln(100) ≈ 4.6
For n = 1000: average gap ≈ ln(1000) ≈ 6.9
```

### 2. Why √n is Sufficient

**Proof:**
```
Suppose n = a × b where a ≤ √n and b ≥ √n
Then a ≤ √n < b
So we only need to check divisors up to √n
If n has no divisor ≤ √n, then n is prime
```

### 3. The 6k±1 Pattern

**All primes > 3:**
```
Form: 6k - 1 or 6k + 1

Examples:
6(1) - 1 = 5   ✓ prime
6(1) + 1 = 7   ✓ prime
6(2) - 1 = 11  ✓ prime
6(2) + 1 = 13  ✓ prime
6(3) - 1 = 17  ✓ prime
6(3) + 1 = 19  ✓ prime

Note: 6(4) + 1 = 25 = 5² (not all 6k±1 are prime)
```

---

## 🎓 Interview Discussion Points

### When Asked This Problem

**1. Clarify Requirements**
```
Q: "Should I handle negative numbers?"
Q: "What's the range - int or long?"
Q: "Is n inclusive or exclusive?"
```

**2. Explain Your Approach**
```
"I'll check each candidate starting from n+1.
 For each candidate, I'll use trial division to check primality.
 I'll optimize by only checking odd numbers and using the 6k±1 pattern.
 Time complexity is O(k√m) where k is candidates checked."
```

**3. Discuss Optimizations**
```
"Basic: Check all numbers
Better: Skip even numbers (2x faster)
Best: Use 6k±1 pattern (3x faster than basic)"
```

**4. Handle Follow-ups**
```
Q: "What if we need many next primes?"
A: "Could use Sieve of Eratosthenes to precompute primes up to limit."

Q: "How to handle very large numbers?"
A: "Use probabilistic primality tests like Miller-Rabin for large numbers."
```

---

## 🔄 Related Problems

### Similar Problems

1. **Check if Number is Prime** - Core subroutine
2. **Count Primes up to N** - Sieve of Eratosthenes
3. **Nth Prime Number** - Find nth prime
4. **Prime Factorization** - Decompose into primes
5. **Twin Primes** - Find pairs (p, p+2) both prime

### Difficulty Progression

```
Next Prime Number (Easy) ← You are here
    ↓
Is Prime (Easy)
    ↓
Count Primes (Medium)
    ↓
Nth Prime Number (Medium)
    ↓
Largest Prime Factor (Hard)
```

---

## 🎯 Variations

### Variation 1: Previous Prime
```java
public long previousPrime(long n) {
    // Find largest prime < n
    if (n <= 2) return -1;  // No prime before 2
    
    long candidate = n - 1;
    if (candidate % 2 == 0) candidate--;
    
    while (candidate > 1) {
        if (isPrime(candidate)) return candidate;
        candidate -= 2;
    }
    
    return 2;
}
```

### Variation 2: Kth Prime After N
```java
public long kthPrimeAfter(long n, int k) {
    // Find the kth prime number after n
    long candidate = n + 1;
    int count = 0;
    
    while (count < k) {
        if (isPrime(candidate)) count++;
        if (count < k) candidate++;
    }
    
    return candidate;
}
```

### Variation 3: Next Twin Prime
```java
public long nextTwinPrime(long n) {
    // Find next prime p where p+2 is also prime
    long candidate = n + 1;
    
    while (true) {
        if (isPrime(candidate) && isPrime(candidate + 2)) {
            return candidate;
        }
        candidate++;
    }
}
```

---

## 📚 Additional Resources

- [Prime Numbers - Wikipedia](https://en.wikipedia.org/wiki/Prime_number)
- [Trial Division - GeeksforGeeks](https://www.geeksforgeeks.org/primality-test-set-1-introduction-and-school-method/)
- [Sieve of Eratosthenes](https://www.geeksforgeeks.org/sieve-of-eratosthenes/)
- [Prime Number Theorem](https://en.wikipedia.org/wiki/Prime_number_theorem)

---

<div align="center">

**Master Prime Numbers, Master Number Theory! 🔢**

*A fundamental problem in mathematics and computer science*

*Last Updated: December 2024*

[⬆ Back to Basics](../README.md) | [⬆ Back to Main](../../README.md)

</div>
