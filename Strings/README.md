# String Algorithms and Data Structures

## Table of Contents
- [Introduction](#introduction)
- [Fundamental Concepts](#fundamental-concepts)
- [Problem Categories](#problem-categories)
- [Algorithmic Techniques](#algorithmic-techniques)
- [Complexity Analysis](#complexity-analysis)
- [Implementation Patterns](#implementation-patterns)
- [Common Pitfalls](#common-pitfalls)
- [Advanced Topics](#advanced-topics)

## Introduction

String manipulation represents a fundamental domain in computer science, encompassing problems that range from simple character operations to complex pattern matching and text processing algorithms. Strings are ubiquitous in software systems—from web applications processing user input to compilers parsing source code—making proficiency in string algorithms essential for any software engineer.

This section contains implementations and analyses of string-related problems, organized by algorithmic technique and problem-solving pattern. Each subsection includes detailed explanations, complexity analysis, and practical applications.

### Why String Algorithms Matter

**Technical Interviews**: String problems constitute approximately 15-20% of technical interview questions at major technology companies. They test fundamental programming skills, algorithmic thinking, and optimization abilities.

**Real-World Applications**: 
- Text editors and word processors
- Search engines and information retrieval systems
- Bioinformatics (DNA sequence analysis)
- Natural language processing
- Compilers and parsers
- Data validation and sanitization
- Network protocols and data serialization

## Fundamental Concepts

### String Representation

**Immutability in Java**: 
Strings in Java are immutable objects. Any modification creates a new string object, leading to O(n) time complexity for operations that appear constant-time in mutable languages.

```java
String s = "hello";
s = s + "world";  // Creates new String object, O(n) operation
```

**Character Arrays**:
For frequent modifications, character arrays provide O(1) modification time:

```java
char[] chars = s.toCharArray();  // O(n) conversion
chars[0] = 'H';  // O(1) modification
String result = new String(chars);  // O(n) conversion back
```

**StringBuilder**:
Optimal for building strings in loops, provides amortized O(1) append:

```java
StringBuilder sb = new StringBuilder();
for (String word : words) {
    sb.append(word);  // Amortized O(1)
}
String result = sb.toString();  // O(n)
```

### Character Set Considerations

**ASCII (American Standard Code for Information Interchange)**:
- 128 characters (0-127)
- Includes control characters, printable characters, digits, uppercase, lowercase
- Common in competitive programming assumptions

**Extended ASCII**:
- 256 characters (0-255)
- Adds international characters and symbols

**Unicode**:
- Universal character set supporting all world languages
- UTF-8, UTF-16, UTF-32 encoding schemes
- Java char is 16-bit, represents UTF-16 code unit

**Practical Implications**:
```java
// ASCII assumption - fixed array for character frequency
int[] freq = new int[128];

// Unicode support - use HashMap for arbitrary characters
Map<Character, Integer> freq = new HashMap<>();
```

## Problem Categories

### 1. Substring Problems
**Definition**: Finding, extracting, or validating contiguous sequences of characters.

**Common Variations**:
- Longest substring with specific properties
- Substring matching and occurrence counting
- Palindromic substrings
- Anagram substring search

**Key Characteristics**:
- Often solvable with sliding window technique
- May require hash maps for character tracking
- Time complexity typically O(n) to O(n²)

### 2. Pattern Matching
**Definition**: Searching for occurrences of a pattern within a text.

**Algorithms**:
- Naive pattern matching: O(nm)
- Knuth-Morris-Pratt (KMP): O(n + m)
- Rabin-Karp: O(n + m) average, O(nm) worst
- Boyer-Moore: O(n/m) to O(nm)

**Applications**:
- Text search functionality
- DNA sequence matching
- Plagiarism detection

### 3. String Transformation
**Definition**: Converting strings from one form to another.

**Examples**:
- Case conversion (lowercase/uppercase)
- Character substitution and encoding
- String compression and decompression
- Anagram generation

### 4. String Comparison and Sorting
**Definition**: Determining ordering relationships between strings.

**Concepts**:
- Lexicographic ordering
- Edit distance (Levenshtein distance)
- Longest common subsequence
- String similarity metrics

## Algorithmic Techniques

### Sliding Window Technique
A powerful optimization pattern for substring problems that reduces time complexity from O(n²) or O(n³) to O(n).

**[Detailed Sliding Window Documentation →](./Sliding-Window/)**

**Applicable When**:
- Problem involves contiguous sequences
- Need to track elements within a range
- Questions ask for "longest", "shortest", "maximum", or "minimum" subarrays

**Time Complexity**: O(n) - each element processed at most twice

**Space Complexity**: O(k) - where k is window size or character set size

### Two Pointers Technique
Uses two indices to traverse the string, often from opposite ends or at different speeds.

**Common Applications**:
- Palindrome verification
- Removing duplicates
- Reversing strings
- Finding pairs with specific properties

**Pattern**:
```java
int left = 0, right = s.length() - 1;
while (left < right) {
    // Process characters at both pointers
    // Move pointers based on condition
}
```

### Hash Map / Hash Set Pattern
Efficient character frequency tracking and lookup operations.

**Use Cases**:
- Anagram detection
- Character uniqueness checking
- First non-repeating character
- Group anagrams

**Trade-off**: O(1) lookup time at cost of O(n) or O(k) space

### Dynamic Programming
For problems with overlapping subproblems and optimal substructure.

**Classic String DP Problems**:
- Longest Common Subsequence (LCS)
- Edit Distance
- Longest Palindromic Substring
- Regular Expression Matching
- Word Break

**Characteristics**:
- 2D DP table for two-string problems
- Bottom-up or top-down (memoization) approach
- Time complexity typically O(n²) or O(nm)

## Complexity Analysis

### Time Complexity Patterns

| Operation | Complexity | Notes |
|-----------|------------|-------|
| Character access | O(1) | Direct indexing |
| Substring extraction | O(m) | Creates new string of length m |
| String concatenation | O(n) | Creates new string |
| String comparison | O(min(n,m)) | Early termination possible |
| charAt() | O(1) | Direct array access |
| String.contains() | O(nm) | Naive pattern search |
| String.indexOf() | O(nm) | Implementation dependent |

### Space Complexity Considerations

**Auxiliary Space vs. Input Space**:
- Input space: O(n) for the string itself
- Auxiliary space: Additional space used by algorithm

**Common Space Requirements**:
- Hash maps for character frequency: O(k) where k ≤ 128 for ASCII
- Recursive call stack: O(d) where d is recursion depth
- Dynamic programming tables: O(n²) for two-string problems

### String Concatenation Analysis

**Inefficient Pattern** (O(n²)):
```java
String result = "";
for (int i = 0; i < n; i++) {
    result += str[i];  // Creates new string each iteration
}
// Total: n operations × O(n) copy = O(n²)
```

**Efficient Pattern** (O(n)):
```java
StringBuilder sb = new StringBuilder();
for (int i = 0; i < n; i++) {
    sb.append(str[i]);  // Amortized O(1)
}
String result = sb.toString();  // O(n)
// Total: O(n)
```

## Implementation Patterns

### Pattern 1: Frequency Counter

```java
/**
 * Build frequency map of characters in string
 * Time: O(n), Space: O(k) where k is character set size
 */
Map<Character, Integer> buildFrequencyMap(String s) {
    Map<Character, Integer> freq = new HashMap<>();
    for (char c : s.toCharArray()) {
        freq.put(c, freq.getOrDefault(c, 0) + 1);
    }
    return freq;
}

// Alternative: Array for ASCII characters
int[] freq = new int[128];
for (char c : s.toCharArray()) {
    freq[c]++;
}
```

### Pattern 2: Two-Pointer Palindrome Check

```java
/**
 * Check if string is palindrome
 * Time: O(n), Space: O(1)
 */
boolean isPalindrome(String s) {
    int left = 0, right = s.length() - 1;
    while (left < right) {
        if (s.charAt(left) != s.charAt(right)) {
            return false;
        }
        left++;
        right--;
    }
    return true;
}
```

### Pattern 3: Sliding Window Template

```java
/**
 * General sliding window template
 * Time: O(n), Space: O(k)
 */
int slidingWindow(String s) {
    Map<Character, Integer> window = new HashMap<>();
    int left = 0, result = 0;
    
    for (int right = 0; right < s.length(); right++) {
        // Expand window: add right character
        char rightChar = s.charAt(right);
        window.put(rightChar, window.getOrDefault(rightChar, 0) + 1);
        
        // Contract window: while condition violated
        while (/* window condition violated */) {
            char leftChar = s.charAt(left);
            window.put(leftChar, window.get(leftChar) - 1);
            if (window.get(leftChar) == 0) {
                window.remove(leftChar);
            }
            left++;
        }
        
        // Update result
        result = Math.max(result, right - left + 1);
    }
    return result;
}
```

## Common Pitfalls

### 1. String Immutability
**Problem**: Forgetting that string concatenation in loops is O(n²)

**Solution**: Use StringBuilder for building strings iteratively

### 2. Character Set Assumptions
**Problem**: Assuming ASCII when input may contain Unicode

**Solution**: Clarify constraints; use HashMap for arbitrary characters

### 3. Index Out of Bounds
**Problem**: Off-by-one errors in substring operations

**Solution**: 
- Remember: `substring(start, end)` excludes end index
- Carefully handle empty strings and single characters

### 4. Case Sensitivity
**Problem**: Not considering case differences in comparisons

**Solution**: Normalize case when appropriate: `s.toLowerCase()`

### 5. Null and Empty Strings
**Problem**: Not handling edge cases

**Solution**: Always validate input
```java
if (s == null || s.isEmpty()) {
    return defaultValue;
}
```

### 6. Mutable vs Immutable
**Problem**: Modifying strings inefficiently

**Solution**: 
- Use char[] for frequent modifications
- Use StringBuilder for building strings
- Original String for immutable operations

## Advanced Topics

### String Hashing
Efficient string comparison and pattern matching using hash functions.

**Rabin-Karp Algorithm**: Rolling hash for O(1) hash updates

**Applications**: Substring search, duplicate detection

### Trie (Prefix Tree)
Tree-based data structure for efficient string storage and retrieval.

**Operations**: Insert, search, prefix matching - O(m) where m is string length

**Use Cases**: Autocomplete, spell checking, IP routing

### Suffix Arrays and Trees
Advanced structures for substring queries and pattern matching.

**Suffix Array**: Sorted array of all suffixes - O(n² log n) construction

**Suffix Tree**: Tree of all suffixes - O(n) construction with Ukkonen's algorithm

### Aho-Corasick Algorithm
Multi-pattern matching algorithm for finding multiple patterns simultaneously.

**Complexity**: O(n + m + z) where n is text length, m is total pattern length, z is matches

**Applications**: Dictionary matching, virus signature detection

### Manacher's Algorithm
Linear-time algorithm for finding longest palindromic substring.

**Complexity**: O(n) time, O(n) space

**Advantage**: Optimal for palindrome-related problems

## Learning Path Recommendation

### Beginner Level
1. Basic string traversal and manipulation
2. Character frequency counting
3. Simple pattern matching
4. Palindrome problems

### Intermediate Level
1. Two-pointer technique mastery
2. Sliding window pattern recognition
3. Hash map applications
4. Basic dynamic programming (LCS, Edit Distance)

### Advanced Level
1. KMP and Rabin-Karp algorithms
2. Trie implementation and applications
3. Advanced DP (Regular Expression, Word Break II)
4. Suffix arrays and Z-algorithm

## Problems by Difficulty

### Easy
- Reverse String
- Valid Palindrome
- Valid Anagram
- First Unique Character

### Medium
- Longest Substring Without Repeating Characters
- Longest Palindromic Substring
- Group Anagrams
- Minimum Window Substring

### Hard
- Regular Expression Matching
- Wildcard Matching
- Longest Valid Parentheses
- Distinct Subsequences

## References and Further Reading

**Textbooks**:
- "Algorithms" by Robert Sedgewick and Kevin Wayne
- "String Processing Algorithms" by Dan Gusfield
- "Introduction to Algorithms" (CLRS) - String matching chapter

**Online Resources**:
- LeetCode String Problems: https://leetcode.com/tag/string/
- CP-Algorithms String Algorithms: https://cp-algorithms.com/string/
- GeeksforGeeks String Algorithms: https://www.geeksforgeeks.org/string-data-structure/

---

**Master string algorithms to build a strong foundation in algorithmic problem-solving and text processing.**

*Last Updated: January 2026*
