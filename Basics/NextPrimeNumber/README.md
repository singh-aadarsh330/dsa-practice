# 🔢 Next Prime Number

[![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green?style=flat-square)](.)
[![Concept](https://img.shields.io/badge/Concept-Basic%20Math-blue?style=flat-square)](.)
[![Technique](https://img.shields.io/badge/Technique-Prime%20Check-purple?style=flat-square)](.)

> Find the smallest prime number strictly greater than a given number.  
> A foundational problem to practice loops, conditionals, and number theory basics.

---

## 📋 Problem Description

### The Challenge

Given an integer `n`, find and return the **next prime number** that is **strictly greater than `n`**.

A **prime number** is a number greater than 1 that has **no divisors other than 1 and itself**.

---

### Input
- An integer `n`

### Output
- The smallest prime number **greater than `n`**

---

## 🧪 Examples

### Example 1
Input: n = 5
Output: 7
Reason: 6 is not prime, 7 is prime


### Example 2


Input: n = 1
Output: 2
Reason: 2 is the smallest prime number


### Example 3


Input: n = 14
Output: 17
Reason: 15 and 16 are not prime


### Example 4


Input: n = 2
Output: 3


---

## 🎯 Learning Objectives

This problem helps you understand:
- Prime number definition
- Efficient primality testing
- Loop control with conditions
- Square root optimization
- Handling edge cases

---

## 🔧 Approach

### Algorithm

1. If `n < 2`, return `2`.
2. Start checking numbers from `n + 1`.
3. For each number:
   - Check if it is prime.
   - If prime, return it.
4. Repeat until a prime is found.

---

## 💡 Prime Check Optimization

Instead of checking divisibility up to `n`,  
we only check up to **√n**.

**Why?**
- If `n = a × b`, then at least one of `a` or `b` is ≤ √n.
- This significantly reduces time complexity.

---

## 🧩 Implementation (Java)


// User function Template for Java

/**
 * Returns the smallest prime number strictly greater than n.
 */
class Solution {

    public static int nextPrime(int n) {

        if (n < 2) {
            return 2;
        }

        int candidate = n + 1;

        while (!isPrime(candidate)) {
            candidate++;
        }

        return candidate;
    }

    private static boolean isPrime(int num) {

        if (num < 2) {
            return false;
        }

        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}

⏱️ Complexity Analysis

Time Complexity:

Prime check: O(√n)

Space Complexity:

O(1)

🧠 Dry Run
Input
n = 10

Steps
Check 11 → prime ✔

Output
11

⚠️ Edge Cases Covered
Input	Output
0	2
1	2
7	11
100	101
🎓 Interview Discussion Points

Square root optimization improves efficiency

Handles small and edge input values

Suitable for beginner-level number theory problems

🔄 Related Problems

Check if a number is prime

Count primes in a range

Sieve of Eratosthenes

Prime factorization

<div align="center">

Master the Basics of Prime Numbers 🔢

A clean and efficient introduction to number theory logic

Last Updated: December 2025

⬆ Back to Basics
 | ⬆ Back to Main

</div> ```
