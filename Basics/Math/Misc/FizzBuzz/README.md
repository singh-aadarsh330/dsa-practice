# 🎯 FizzBuzz

[![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green?style=flat-square)](.)
[![Concept](https://img.shields.io/badge/Concept-Modulo%20%7C%20Conditionals-blue?style=flat-square)](.)

> The classic programming interview problem. Master conditional logic and the modulo operator.

---

## 📋 Problem Description

### The Challenge

Write a program that prints numbers from 1 to n, but:
- For **multiples of 3**, print "**Fizz**" instead of the number
- For **multiples of 5**, print "**Buzz**" instead of the number  
- For numbers which are **multiples of both 3 and 5**, print "**FizzBuzz**"

**Input:**
- Integer `n` (the upper limit)

**Output:**
- List of strings representing the FizzBuzz sequence from 1 to n

---

### Examples

**Example 1: n = 15**
```
Input: n = 15

Output: 
["1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", 
 "Buzz", "11", "Fizz", "13", "14", "FizzBuzz"]

Explanation:
1  → 1       (not divisible by 3 or 5)
2  → 2       (not divisible by 3 or 5)
3  → Fizz    (divisible by 3)
4  → 4       (not divisible by 3 or 5)
5  → Buzz    (divisible by 5)
6  → Fizz    (divisible by 3)
7  → 7       (not divisible by 3 or 5)
8  → 8       (not divisible by 3 or 5)
9  → Fizz    (divisible by 3)
10 → Buzz    (divisible by 5)
11 → 11      (not divisible by 3 or 5)
12 → Fizz    (divisible by 3)
13 → 13      (not divisible by 3 or 5)
14 → 14      (not divisible by 3 or 5)
15 → FizzBuzz (divisible by both 3 and 5)
```

**Example 2: n = 5**
```
Input: n = 5
Output: ["1", "2", "Fizz", "4", "Buzz"]
```

**Example 3: n = 3**
```
Input: n = 3
Output: ["1", "2", "Fizz"]
```

**Example 4: n = 1**
```
Input: n = 1
Output: ["1"]
```

---

## 🎯 Learning Objectives

This problem teaches:
- **Modulo operator (%)** - Check divisibility
- **Conditional logic** - Order of conditions matters
- **String vs Integer output** - Type conversion
- **Edge cases** - Small inputs
- **Code cleanliness** - Multiple ways to solve

---

## 🔧 Approach 1: If-Else Chain (Classic)

### Algorithm

Check conditions in order: both, 3, 5, else number.

**Critical:** Check divisibility by both 3 AND 5 first!

### Implementation

```java
public List<String> fizzBuzz(int n) {
    List<String> result = new ArrayList<>();
    
    for (int i = 1; i <= n; i++) {
        if (i % 3 == 0 && i % 5 == 0) {
            result.add("FizzBuzz");
        } else if (i % 3 == 0) {
            result.add("Fizz");
        } else if (i % 5 == 0) {
            result.add("Buzz");
        } else {
            result.add(String.valueOf(i));
        }
    }
    
    return result;
}
```

**Complexity:**
- **Time:** O(n) - one pass through 1 to n
- **Space:** O(1) auxiliary (O(n) for output)

**Pros:**
- ✅ Clear and readable
- ✅ Easy to understand
- ✅ Standard interview solution

**Cons:**
- Checks divisibility by 15 redundantly

---

## 🔧 Approach 2: Modulo 15 (Optimized)

### Algorithm

Check divisibility by 15 (LCM of 3 and 5) first.

### Implementation

```java
public List<String> fizzBuzz(int n) {
    List<String> result = new ArrayList<>();
    
    for (int i = 1; i <= n; i++) {
        if (i % 15 == 0) {
            result.add("FizzBuzz");
        } else if (i % 3 == 0) {
            result.add("Fizz");
        } else if (i % 5 == 0) {
            result.add("Buzz");
        } else {
            result.add(String.valueOf(i));
        }
    }
    
    return result;
}
```

**Pros:**
- Slightly more efficient
- Single divisibility check for FizzBuzz

**Cons:**
- Less obvious why 15
- Not significantly better

---

## 🔧 Approach 3: String Concatenation (Extensible)

### Algorithm

Build string by concatenating "Fizz" and "Buzz" as needed.

**Advantage:** Easy to extend with more rules!

### Implementation

```java
public List<String> fizzBuzz(int n) {
    List<String> result = new ArrayList<>();
    
    for (int i = 1; i <= n; i++) {
        String str = "";
        
        if (i % 3 == 0) {
            str += "Fizz";
        }
        if (i % 5 == 0) {
            str += "Buzz";
        }
        
        if (str.isEmpty()) {
            str = String.valueOf(i);
        }
        
        result.add(str);
    }
    
    return result;
}
```

**Pros:**
- ⭐ **Most extensible**
- Easy to add more rules (e.g., "Jazz" for 7)
- Clean logic

**Cons:**
- String concatenation overhead (minor)

---

## 🔧 Approach 4: Ternary Operator (Concise)

### Algorithm

Use nested ternary operators for compact code.

### Implementation

```java
public List<String> fizzBuzz(int n) {
    List<String> result = new ArrayList<>();
    
    for (int i = 1; i <= n; i++) {
        result.add(
            i % 15 == 0 ? "FizzBuzz" :
            i % 3 == 0 ? "Fizz" :
            i % 5 == 0 ? "Buzz" :
            String.valueOf(i)
        );
    }
    
    return result;
}
```

**Pros:**
- Very concise
- One-liner per iteration

**Cons:**
- ❌ Less readable
- ❌ Harder to debug
- ❌ Not recommended for interviews

---

## 📊 Comparison of Approaches

| Approach | Readability | Extensibility | Performance | Interview Score |
|----------|-------------|---------------|-------------|-----------------|
| **If-Else Chain** | ⭐⭐⭐⭐⭐ | ⭐⭐⭐ | Good | **⭐⭐⭐⭐⭐** |
| **Modulo 15** | ⭐⭐⭐⭐ | ⭐⭐⭐ | Slightly better | ⭐⭐⭐⭐ |
| **String Concat** | ⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ | Good | **⭐⭐⭐⭐⭐** |
| **Ternary** | ⭐⭐ | ⭐⭐ | Good | ⭐⭐⭐ |

**Recommendation:** Use **If-Else Chain** or **String Concatenation** in interviews.

---

## 🎯 Step-by-Step Dry Run

### Input: n = 15

**Using Approach 1 (If-Else):**

| i | i % 3 | i % 5 | i % 15 | Condition | Output |
|---|-------|-------|--------|-----------|---------|
| 1 | 1 | 1 | 1 | else | "1" |
| 2 | 2 | 2 | 2 | else | "2" |
| 3 | 0 | 3 | 3 | i % 3 == 0 | "Fizz" |
| 4 | 1 | 4 | 4 | else | "4" |
| 5 | 2 | 0 | 5 | i % 5 == 0 | "Buzz" |
| 6 | 0 | 1 | 6 | i % 3 == 0 | "Fizz" |
| 7 | 1 | 2 | 7 | else | "7" |
| 8 | 2 | 3 | 8 | else | "8" |
| 9 | 0 | 4 | 9 | i % 3 == 0 | "Fizz" |
| 10 | 1 | 0 | 10 | i % 5 == 0 | "Buzz" |
| 11 | 2 | 1 | 11 | else | "11" |
| 12 | 0 | 2 | 12 | i % 3 == 0 | "Fizz" |
| 13 | 1 | 3 | 13 | else | "13" |
| 14 | 2 | 4 | 14 | else | "14" |
| 15 | 0 | 0 | 0 | both | "FizzBuzz" |

**Result:**
```
["1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz"]
```

---

## ⚠️ Edge Cases & Validation

### Edge Cases to Consider

**1. n = 1**
```java
Input: n = 1
Output: ["1"]
No multiples of 3 or 5
```

**2. n = 3**
```java
Input: n = 3
Output: ["1", "2", "Fizz"]
First Fizz appears
```

**3. n = 5**
```java
Input: n = 5
Output: ["1", "2", "Fizz", "4", "Buzz"]
First Buzz appears
```

**4. n = 15**
```java
Input: n = 15
Output: [..., "FizzBuzz"]
First FizzBuzz appears
```

**5. n = 0**
```java
Input: n = 0
Output: []
Empty result (no positive numbers)
```

**6. Large n**
```java
Input: n = 1,000,000
Output: 1 million strings
Test memory and performance
```

### Input Validation

```java
public List<String> fizzBuzz(int n) {
    // Validate input
    if (n < 0) {
        throw new IllegalArgumentException("n must be non-negative");
    }
    
    if (n == 0) {
        return new ArrayList<>();  // Empty list
    }
    
    // Main logic...
}
```

---

## 🧪 Test Cases

### Test Case 1: Standard Case
```java
Input: n = 15
Expected: ["1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz"]
```

### Test Case 2: Small n
```java
Input: n = 3
Expected: ["1", "2", "Fizz"]
```

### Test Case 3: Up to First Buzz
```java
Input: n = 5
Expected: ["1", "2", "Fizz", "4", "Buzz"]
```

### Test Case 4: Multiple FizzBuzz
```java
Input: n = 30
Expected: Should have "FizzBuzz" at positions 15 and 30
```

### Test Case 5: Edge Case
```java
Input: n = 1
Expected: ["1"]
```

### Test Case 6: All Fizz
```java
Input: n = 9
Expected: Contains "Fizz" at positions 3, 6, 9
```

---

## 💡 Key Concepts Illustrated

### 1. Modulo Operator

```java
// Check if x is divisible by y
if (x % y == 0) {
    // x is multiple of y
}

// Examples:
6 % 3 == 0  // true (6 is divisible by 3)
7 % 3 == 1  // false (7 mod 3 leaves remainder 1)
15 % 5 == 0 // true (15 is divisible by 5)
```

### 2. Order of Conditions Matters!

```java
// ❌ WRONG - FizzBuzz never reached!
if (i % 3 == 0) return "Fizz";
if (i % 5 == 0) return "Buzz";
if (i % 15 == 0) return "FizzBuzz";  // Never executes!

// ✅ CORRECT - Check most specific first
if (i % 15 == 0) return "FizzBuzz";
if (i % 3 == 0) return "Fizz";
if (i % 5 == 0) return "Buzz";
```

### 3. String Building Pattern

```java
StringBuilder sb = new StringBuilder();
if (condition1) sb.append("Part1");
if (condition2) sb.append("Part2");
return sb.isEmpty() ? defaultValue : sb.toString();
```

### 4. LCM (Least Common Multiple)

```java
// For FizzBuzz: LCM(3, 5) = 15
// Because 3 and 5 are coprime (GCD = 1)
// LCM(a, b) = (a × b) / GCD(a, b)
```

---

## 🎓 Interview Discussion Points

### When Interviewer Asks FizzBuzz

**1. Clarify Requirements**
```
Q: "Should output be printed or returned?"
Q: "Are numbers 1-indexed (1 to n)?"
Q: "Should handle n = 0?"
Q: "Return array or list?"
```

**2. Explain Your Approach**
```
"I'll iterate from 1 to n. For each number, I'll check:
 1. Is it divisible by both 3 and 5? → FizzBuzz
 2. Is it divisible by 3? → Fizz
 3. Is it divisible by 5? → Buzz
 4. Otherwise → the number itself
 
 The order is crucial - we must check 'both' first."
```

**3. Discuss Trade-offs**
```
"The if-else approach is clearest for this problem.
 The string concatenation approach is more extensible
 if we add more rules later (like 'Jazz' for multiples of 7).
 Performance difference is negligible for reasonable n."
```

### Common Follow-up Questions

**Q: "Extend to include 'Jazz' for multiples of 7"**
```java
A: "String concatenation approach works best:
   if (i % 3 == 0) str += 'Fizz';
   if (i % 5 == 0) str += 'Buzz';
   if (i % 7 == 0) str += 'Jazz';
   // Now i=21 gives 'FizzJazz', i=35 gives 'BuzzJazz', etc."
```

**Q: "Optimize for performance"**
```java
A: "Already O(n) which is optimal - must visit each number.
   Could reduce modulo operations by counting:
   
   int fizz = 0, buzz = 0;
   for (int i = 1; i <= n; i++) {
       fizz++; buzz++;
       if (fizz == 3 && buzz == 5) { FizzBuzz; fizz=0; buzz=0; }
       else if (fizz == 3) { Fizz; fizz=0; }
       else if (buzz == 5) { Buzz; buzz=0; }
       else { i; }
   }"
```

**Q: "What if n is very large (1 billion)?"**
```java
A: "Still O(n) time. Main concern is memory for output list.
    Could use iterator/stream for lazy evaluation if caller
    processes results one at a time."
```

---

## 🔄 Related Problems

### Similar Problems to Practice

1. **FizzBuzz Multithreaded** - Synchronize threads for output
2. **Count Fizz, Buzz, FizzBuzz** - Count occurrences instead of print
3. **Generalized FizzBuzz** - Arbitrary divisors and words
4. **Reverse FizzBuzz** - Given output, find pattern
5. **FizzBuzz Tree** - Print FizzBuzz on tree traversal

### Difficulty Progression

```
FizzBuzz (Easy) ← You are here
    ↓
FizzBuzz Variations (Easy-Medium)
    ↓
FizzBuzz Multithreaded (Medium)
    ↓
Generalized Rules Engine (Hard)
```

---

## 🎯 Variations to Practice

### Variation 1: Return Counts
```java
public Map<String, Integer> fizzBuzzCounts(int n) {
    // Return: {"Fizz": x, "Buzz": y, "FizzBuzz": z, "Number": w}
}
```

### Variation 2: Custom Rules
```java
public List<String> customFizzBuzz(int n, int divisor1, String word1, 
                                     int divisor2, String word2) {
    // Generalized version
}
```

### Variation 3: Lazy Evaluation
```java
public Iterator<String> fizzBuzzIterator(int n) {
    // Return iterator for memory efficiency
}
```

### Variation 4: Reverse Problem
```java
public int[] findPattern(String[] output) {
    // Given FizzBuzz output, find the divisors used
    // e.g., ["Fizz", "Buzz", "FizzBuzz"] → [3, 5]
}
```

---

## 📚 Additional Learning

### Why FizzBuzz is Important

1. **Screening tool** - Tests basic programming
2. **Simple yet insightful** - Shows code style
3. **Multiple solutions** - Tests design thinking
4. **Common interview starter** - Warm-up problem

### Beyond the Basics

**Object-Oriented FizzBuzz:**
```java
interface Rule {
    boolean matches(int n);
    String apply();
}

class FizzBuzzGame {
    private List<Rule> rules;
    
    public List<String> play(int n) {
        // Apply rules in order
    }
}
```

**Functional FizzBuzz (Java 8+):**
```java
IntStream.rangeClosed(1, n)
    .mapToObj(i -> i % 15 == 0 ? "FizzBuzz" :
                   i % 3 == 0 ? "Fizz" :
                   i % 5 == 0 ? "Buzz" :
                   String.valueOf(i))
    .collect(Collectors.toList());
```

---

## 🔗 Resources

- [Why FizzBuzz is Hard](https://blog.codinghorror.com/why-cant-programmers-program/)
- [FizzBuzz Variations](https://www.tomdalling.com/blog/software-design/fizzbuzz-in-too-much-detail/)
- [LeetCode FizzBuzz](https://leetcode.com/problems/fizz-buzz/)

---

<div align="center">

**The Classic Interview Problem! 🎯**

*Simple to state, reveals coding fundamentals*

*Last Updated: December 2024*

</div>
