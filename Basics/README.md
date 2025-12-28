# 🌱 Basics

[![Problems](https://img.shields.io/badge/Problems-1-blue?style=flat-square)](.)
[![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green?style=flat-square)](.)
[![Language](https://img.shields.io/badge/Language-Java-red?style=flat-square)](.)

> Foundation problems to build programming fundamentals. Master the basics before diving into complex data structures and algorithms.

---

## 📋 Overview

This folder contains fundamental programming problems that help build:
- **Basic syntax and logic** - Core language constructs
- **Problem-solving mindset** - Breaking down problems into steps
- **Code organization** - Writing clean, readable solutions
- **Pattern recognition** - Common programming patterns

**Why Start with Basics?**
- Builds confidence before tackling harder problems
- Reinforces language syntax and semantics
- Establishes good coding practices early
- Forms foundation for advanced topics

---

## 📂 Repository Structure

```
Basics/
│
├── FizzBuzz/                # Classic programming problem
│   └── README.md
│
└── README.md                # This file
```

---

## 📝 Problems in This Folder

| # | Topic | Folder | Problems | Difficulty | Status |
|---|-------|--------|----------|------------|--------|
| 1 | FizzBuzz | [FizzBuzz/](./FizzBuzz/) | 1+ | Easy | ✅ |

---

## 🎯 Topics to be Added

As I continue practicing, this folder will include:

### **Number Operations**
- Prime number checking
- Factorial calculation
- GCD and LCM
- Palindrome numbers
- Armstrong numbers

### **Pattern Printing**
- Number patterns
- Star patterns
- Pyramid patterns
- Diamond patterns

### **String Basics**
- String reversal
- Character frequency
- Anagram checking
- String palindrome

### **Basic Math**
- Power calculation
- Digit manipulation
- Number conversions
- Basic arithmetic problems

### **Loops & Conditionals**
- Nested loops
- Switch-case problems
- If-else logic
- Loop optimization

---

## 🔑 Core Concepts Covered

### 1. **Control Flow**
```java
// Conditionals
if (condition) {
    // code
} else {
    // code
}

// Loops
for (int i = 0; i < n; i++) {
    // code
}

while (condition) {
    // code
}
```

### 2. **Basic Operators**
```java
// Arithmetic: +, -, *, /, %
// Logical: &&, ||, !
// Comparison: ==, !=, <, >, <=, >=
// Increment/Decrement: ++, --
```

### 3. **Basic Data Types**
```java
int num = 5;
double decimal = 3.14;
char letter = 'A';
boolean flag = true;
String text = "Hello";
```

### 4. **Input/Output**
```java
// Output
System.out.println("Hello");

// Input (Scanner)
Scanner sc = new Scanner(System.in);
int n = sc.nextInt();
```

---

## 💡 Learning Approach

### For Beginners

1. **Understand the Problem**
   - Read carefully, identify input/output
   - Work through examples manually
   - Think about edge cases

2. **Plan Your Solution**
   - Break problem into smaller steps
   - Write pseudocode if needed
   - Consider different approaches

3. **Code Step by Step**
   - Start with simple logic
   - Test after each step
   - Don't rush to complete

4. **Test Thoroughly**
   - Test with example cases
   - Try edge cases
   - Verify output format

5. **Refine Your Code**
   - Remove unnecessary code
   - Improve variable names
   - Add comments for clarity

---

## 🎓 Problem-Solving Tips

### Common Patterns in Basics

**Pattern 1: Iteration**
```java
// Process each number from 1 to n
for (int i = 1; i <= n; i++) {
    // Process i
}
```

**Pattern 2: Divisibility Check**
```java
// Check if divisible
if (num % divisor == 0) {
    // Divisible
}
```

**Pattern 3: Range Validation**
```java
// Check if in range
if (num >= min && num <= max) {
    // In range
}
```

**Pattern 4: Multiple Conditions**
```java
// Handle multiple cases
if (condition1) {
    // case 1
} else if (condition2) {
    // case 2
} else {
    // default
}
```

---

## ⚠️ Common Beginner Mistakes

### 1. Off-by-One Errors
```java
❌ for (int i = 0; i <= n; i++)    // One extra iteration
✅ for (int i = 0; i < n; i++)     // Correct for n items
```

### 2. Integer Division
```java
❌ int avg = (a + b) / 2;          // Integer division
✅ double avg = (a + b) / 2.0;     // Floating-point division
```

### 3. Comparison vs Assignment
```java
❌ if (x = 5)                      // Assignment, not comparison
✅ if (x == 5)                     // Correct comparison
```

### 4. Uninitialized Variables
```java
❌ int sum;                        // Not initialized
   sum = sum + 5;                  // Error!
✅ int sum = 0;                    // Initialize first
   sum = sum + 5;                  // Correct
```

### 5. Infinite Loops
```java
❌ while (x > 0) {                 // x never changes
       System.out.println(x);
   }
✅ while (x > 0) {                 // x decrements
       System.out.println(x);
       x--;
   }
```

---

## 📚 Learning Resources

### Recommended for Beginners

**Java Basics:**
- [Oracle Java Tutorials](https://docs.oracle.com/javase/tutorial/)
- [W3Schools Java](https://www.w3schools.com/java/)
- [GeeksforGeeks Java](https://www.geeksforgeeks.org/java/)

**Problem Solving:**
- [GeeksforGeeks Basic Problems](https://practice.geeksforgeeks.org/explore?page=1&difficulty%5B%5D=0)
- [HackerRank - Problem Solving](https://www.hackerrank.com/domains/algorithms)
- [LeetCode Easy Problems](https://leetcode.com/problemset/all/?difficulty=EASY)

**Video Tutorials:**
- [Java Programming Basics](https://www.youtube.com/results?search_query=java+programming+basics)
- [TakeUForward - Java Tutorial](https://www.youtube.com/@takeUforward)

---

## 🎯 Practice Strategy

### Week 1: Master the Fundamentals
- [ ] Variables and data types
- [ ] Input/output operations
- [ ] Basic arithmetic operations
- [ ] Conditional statements (if-else)

### Week 2: Loops and Logic
- [ ] For loops
- [ ] While loops
- [ ] Nested loops
- [ ] Break and continue

### Week 3: Problem Solving
- [ ] 10 easy problems with if-else
- [ ] 10 easy problems with loops
- [ ] Pattern printing problems
- [ ] Number manipulation problems

### Week 4: Consolidation
- [ ] Solve mixed basic problems
- [ ] Optimize existing solutions
- [ ] Learn from mistakes
- [ ] Move to intermediate topics

---

## 💪 Challenge Yourself

Once comfortable with basics:

1. **Solve Without Looking** - Try to solve without referring to solutions
2. **Multiple Approaches** - Find 2-3 different ways to solve same problem
3. **Optimize** - Can you reduce time/space complexity?
4. **Explain** - Can you explain your solution to someone else?
5. **Time Yourself** - How fast can you solve a basic problem?

---

## 🔄 Progress Tracking

**Current Status:**
- Topics Covered: 1 (FizzBuzz)
- Total Problems: 1+
- Difficulty: Easy
- Completion: Just started! 🚀

**Next Milestones:**
- [ ] Complete 10 basic problems
- [ ] Add 3 more topic folders
- [ ] Master all control flow patterns
- [ ] Move to intermediate topics

---

## 📊 Difficulty Progression

```
Basics (Current) → Arrays → Strings → Searching → Sorting → Recursion → DP
   ↓
Start here to build strong foundation!
```

---

## 🎓 Why Basics Matter

**Strong Foundation:**
- Complex algorithms use basic building blocks
- Understanding fundamentals prevents confusion later
- Clean basic code translates to clean complex code

**Interview Preparation:**
- Basic problems appear in screening rounds
- Interviewers check coding fundamentals
- Speed in basics = more time for hard problems

**Confidence Building:**
- Success in basics builds momentum
- Creates problem-solving mindset
- Makes harder topics less intimidating

---

## 🤝 Contributing to This Section

As I add more basic problems:
1. Each topic will get its own folder
2. Detailed READMEs for each category
3. Multiple difficulty levels within basics
4. Clear progression path

---

## 📫 Learning Together

Questions or suggestions about basic problems?
- Check [FizzBuzz README](./FizzBuzz/README.md) for detailed example
- Connect on [LinkedIn](https://www.linkedin.com/in/singh-aadarsh330)
- Practice on [GeeksforGeeks](https://www.geeksforgeeks.org/profile/singhaadarsh330)

---

## 💡 Remember

> "The expert in anything was once a beginner."
> 
> Every complex algorithm starts with understanding basics.
> Take your time, practice consistently, and don't skip fundamentals!

---

<div align="center">

**Master the Basics, Master Everything! 🌱**

*Last Updated: December 2024*

[⬆ Back to Main Repository](../README.md)

</div>
