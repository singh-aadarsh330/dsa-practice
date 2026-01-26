# Reverse-Integer

## 📌 Description
This folder contains the problem statement, approach, and Java implementation for the **Reverse Integer** problem. The task is to reverse the digits of a given integer while correctly handling negative values and overflow conditions.

## 📂 Contents
- Problem statement  
- Java solution  
- Approach  
- Time and space complexity analysis  

---

## 🧩 Problem Statement
Given a 32-bit signed integer `x`, return the integer obtained by reversing its digits.  
If reversing `x` causes the value to go outside the 32-bit signed integer range  
`[-2^31, 2^31 - 1]`, return `0`.

### Examples
- **Input:** `123` → **Output:** `321`  
- **Input:** `-456` → **Output:** `-654`  
- **Input:** `1534236469` → **Output:** `0` (overflow)

---

## 🛠 Approach
1. Extract digits from the input integer using modulo and division.  
2. Construct the reversed number digit by digit.  
3. Check for overflow on every iteration:  
   - If `rev` exceeds `Integer.MAX_VALUE` or goes below `Integer.MIN_VALUE`, return `0`.  
4. No separate sign handling is needed; operations naturally preserve it.

---

## 📘 Java Solution
```java
class Solution {
    public int reverse(int x) {
        long rev = 0;

        while (x != 0) {
            int digit = x % 10;
            rev = rev * 10 + digit;

            if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE) {
                return 0;
            }

            x /= 10;
        }

        return (int) rev;
    }
}
```

---

## ⏱ Complexity Analysis
| Type | Complexity |
|------|------------|
| **Time Complexity** | `O(log₁₀ n)` — proportional to number of digits |
| **Space Complexity** | `O(1)` — constant auxiliary space |

---
