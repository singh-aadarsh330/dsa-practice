# 🗳️ Election Tie Breaker

[![Difficulty](https://img.shields.io/badge/Difficulty-Medium-orange?style=flat-square)](.)
[![Concept](https://img.shields.io/badge/Concept-Frequency%20Count-blue?style=flat-square)](.)

> Determine the winner of an election with a custom tiebreaker rule. Master frequency counting and conditional logic with HashMap.

---

## 📋 Problem Description

### The Challenge

Given a list of votes where each vote is a candidate's name (String), determine who wins the election. 

**Election Rules:**
1. The candidate with the **most votes** wins
2. If there's a **tie**, the candidate whose name appears **first in the vote list** wins
3. All candidate names are case-sensitive

**Input:**
- An array/list of strings representing votes
- Each string is a candidate's name

**Output:**
- String representing the winner's name

---

### Examples

**Example 1: Clear Winner**
```
Input: ["Alice", "Bob", "Alice", "Charlie", "Alice"]

Vote Count:
Alice: 3
Bob: 1
Charlie: 1

Output: "Alice"
Reason: Alice has most votes (3)
```

**Example 2: Tie - First Occurrence Wins**
```
Input: ["Bob", "Alice", "Bob", "Alice", "Charlie"]

Vote Count:
Alice: 2
Bob: 2
Charlie: 1

Output: "Bob"
Reason: Both Alice and Bob have 2 votes, but Bob appears first in list
```

**Example 3: Three-Way Tie**
```
Input: ["Charlie", "Bob", "Alice", "Charlie", "Bob", "Alice"]

Vote Count:
Alice: 2
Bob: 2
Charlie: 2

Output: "Charlie"
Reason: All have 2 votes, Charlie appears first (index 0)
```

**Example 4: Single Vote**
```
Input: ["Alice"]

Output: "Alice"
Reason: Only one candidate
```

**Example 5: All Same Candidate**
```
Input: ["Bob", "Bob", "Bob"]

Output: "Bob"
Reason: Only candidate with all votes
```

---

## 🎯 Learning Objectives

This problem teaches:
- **HashMap for frequency counting** - Count occurrences efficiently
- **Finding maximum value** - Track highest vote count
- **Tiebreaker logic** - Custom comparison rules
- **Order preservation** - First occurrence matters
- **Edge case handling** - Empty list, single vote, ties

---

## 🔧 Approach 1: HashMap + Two Passes

### Algorithm

**Step 1: Count Frequencies**
- Use HashMap to count votes for each candidate
- Key: candidate name, Value: vote count

**Step 2: Find Maximum Votes**
- Iterate through map to find highest vote count

**Step 3: Find First Candidate with Max Votes**
- Iterate through original list (preserves order)
- Return first candidate with max votes

### Implementation

```java
public String findWinner(String[] votes) {
    // Edge case: empty votes
    if (votes == null || votes.length == 0) {
        return "";  // or throw exception
    }
    
    // Step 1: Count votes
    HashMap<String, Integer> voteCount = new HashMap<>();
    for (String candidate : votes) {
        voteCount.put(candidate, voteCount.getOrDefault(candidate, 0) + 1);
    }
    
    // Step 2: Find maximum votes
    int maxVotes = 0;
    for (int count : voteCount.values()) {
        maxVotes = Math.max(maxVotes, count);
    }
    
    // Step 3: Find first candidate with max votes
    for (String candidate : votes) {
        if (voteCount.get(candidate) == maxVotes) {
            return candidate;
        }
    }
    
    return "";  // Should never reach here
}
```

**Complexity:**
- **Time:** O(n) where n = number of votes
  - O(n) to count votes
  - O(k) to find max (k = unique candidates, k ≤ n)
  - O(n) to find first with max
  - Total: O(n)
- **Space:** O(k) for HashMap (k = unique candidates)

**Pros:**
- Simple and clear logic
- Handles tiebreaker naturally
- Easy to understand

**Cons:**
- Three separate loops (though still O(n))

---

## 🔧 Approach 2: HashMap + Single Pass for Winner

### Algorithm

Track both max votes and current winner while counting.

**Optimization:**
- Count votes as we go
- Track first occurrence of each candidate
- Update winner only when strictly better

### Implementation

```java
public String findWinner(String[] votes) {
    if (votes == null || votes.length == 0) {
        return "";
    }
    
    HashMap<String, Integer> voteCount = new HashMap<>();
    HashMap<String, Integer> firstIndex = new HashMap<>();
    
    // Count votes and track first occurrence
    for (int i = 0; i < votes.length; i++) {
        String candidate = votes[i];
        
        voteCount.put(candidate, voteCount.getOrDefault(candidate, 0) + 1);
        
        // Track first occurrence
        if (!firstIndex.containsKey(candidate)) {
            firstIndex.put(candidate, i);
        }
    }
    
    // Find winner
    String winner = "";
    int maxVotes = 0;
    int winnerFirstIndex = Integer.MAX_VALUE;
    
    for (String candidate : voteCount.keySet()) {
        int votes = voteCount.get(candidate);
        int firstIdx = firstIndex.get(candidate);
        
        // Update winner if:
        // 1. More votes, OR
        // 2. Same votes but appears earlier
        if (votes > maxVotes || 
            (votes == maxVotes && firstIdx < winnerFirstIndex)) {
            winner = candidate;
            maxVotes = votes;
            winnerFirstIndex = firstIdx;
        }
    }
    
    return winner;
}
```

**Complexity:**
- **Time:** O(n + k) where n = votes, k = unique candidates
- **Space:** O(k) for two HashMaps

**Pros:**
- Cleaner winner determination
- More efficient (fewer passes)
- Explicit tiebreaker handling

**Cons:**
- Slightly more complex
- Uses extra space for indices

---

## 🔧 Approach 3: Using Map.Entry

### Algorithm

Use Map.Entry for cleaner iteration through key-value pairs.

### Implementation

```java
public String findWinner(String[] votes) {
    if (votes == null || votes.length == 0) {
        return "";
    }
    
    // Count votes
    HashMap<String, Integer> voteCount = new HashMap<>();
    for (String candidate : votes) {
        voteCount.merge(candidate, 1, Integer::sum);  // Java 8+ shorthand
    }
    
    // Find max votes
    int maxVotes = voteCount.values().stream()
                            .max(Integer::compare)
                            .orElse(0);
    
    // Find first candidate with max votes
    for (String candidate : votes) {
        if (voteCount.get(candidate) == maxVotes) {
            return candidate;
        }
    }
    
    return "";
}
```

**Pros:**
- Modern Java 8+ syntax
- Concise code
- Functional approach

**Cons:**
- May be less readable for beginners
- Stream overhead

---

## 📊 Comparison of Approaches

| Approach | Time | Space | Passes | Readability | Best For |
|----------|------|-------|--------|-------------|----------|
| **Two Passes** | O(n) | O(k) | 3 | ⭐⭐⭐⭐⭐ | **Learning** |
| **With Indices** | O(n+k) | O(k) | 2 | ⭐⭐⭐⭐ | **Optimization** |
| **Map.Entry** | O(n) | O(k) | 2-3 | ⭐⭐⭐ | Modern code |

**Recommendation:** Use **Two Passes** for clarity, **With Indices** for interviews.

---

## 🎯 Step-by-Step Dry Run

### Input:
```java
String[] votes = {"Bob", "Alice", "Bob", "Alice", "Charlie"};
```

### Execution (Approach 1):

**Step 1: Count Votes**

| Index | Vote | HashMap State |
|-------|------|---------------|
| 0 | Bob | {Bob: 1} |
| 1 | Alice | {Bob: 1, Alice: 1} |
| 2 | Bob | {Bob: 2, Alice: 1} |
| 3 | Alice | {Bob: 2, Alice: 2} |
| 4 | Charlie | {Bob: 2, Alice: 2, Charlie: 1} |

**Step 2: Find Max Votes**
```
Iterate through values: [2, 2, 1]
maxVotes = 2
```

**Step 3: Find First with Max**

| Index | Candidate | Count | Is Max? | Action |
|-------|-----------|-------|---------|--------|
| 0 | Bob | 2 | Yes | Return "Bob" ✓ |

**Result:** "Bob"

**Why Bob wins:** Both Bob and Alice have 2 votes, but Bob appears first (index 0).

---

## ⚠️ Edge Cases & Validation

### Edge Cases to Consider

**1. Empty Vote List**
```java
Input: []
Output: "" (or throw exception)
Handling: Check at start
```

**2. Single Vote**
```java
Input: ["Alice"]
Output: "Alice"
HashMap: {Alice: 1}
Winner: Alice (only candidate)
```

**3. All Same Candidate**
```java
Input: ["Bob", "Bob", "Bob"]
Output: "Bob"
HashMap: {Bob: 3}
Winner: Bob (unanimous)
```

**4. All Candidates Tied**
```java
Input: ["A", "B", "C", "A", "B", "C"]
Output: "A"
All have 2 votes, A appears first
```

**5. Large Dataset**
```java
Input: 1 million votes
HashMap handles efficiently: O(n) time
```

**6. Special Characters in Names**
```java
Input: ["O'Brien", "O'Brien", "Smith"]
Output: "O'Brien"
String comparison works normally
```

### Input Validation

```java
public String findWinner(String[] votes) {
    // Validate input
    if (votes == null) {
        throw new IllegalArgumentException("Votes cannot be null");
    }
    
    if (votes.length == 0) {
        throw new IllegalArgumentException("No votes cast");
    }
    
    // Check for null votes
    for (String vote : votes) {
        if (vote == null || vote.isEmpty()) {
            throw new IllegalArgumentException("Invalid vote");
        }
    }
    
    // Main logic...
}
```

---

## 🧪 Test Cases

### Test Case 1: Clear Winner
```java
Input: ["Alice", "Bob", "Alice", "Charlie", "Alice"]
Expected: "Alice"
Reason: Alice has 3 votes (majority)
```

### Test Case 2: Two-Way Tie
```java
Input: ["Bob", "Alice", "Bob", "Alice"]
Expected: "Bob"
Reason: Both have 2, Bob appears first (index 0)
```

### Test Case 3: Three-Way Tie
```java
Input: ["C", "B", "A", "C", "B", "A"]
Expected: "C"
Reason: All have 2, C appears first (index 0)
```

### Test Case 4: Winner Appears Last
```java
Input: ["A", "B", "C", "D", "D", "D"]
Expected: "D"
Reason: D has 3 votes (most)
```

### Test Case 5: Many Candidates, One Vote Each
```java
Input: ["A", "B", "C", "D", "E"]
Expected: "A"
Reason: All have 1 vote, A appears first
```

### Test Case 6: Repeated Winner
```java
Input: ["Alice", "Alice", "Bob"]
Expected: "Alice"
Reason: Alice has 2 votes
```

---

## 💡 Key Concepts Illustrated

### 1. Frequency Counting Pattern

**Classic Pattern:**
```java
HashMap<Type, Integer> frequency = new HashMap<>();
for (Type item : collection) {
    frequency.put(item, frequency.getOrDefault(item, 0) + 1);
}
```

**Modern Java 8+:**
```java
frequency.merge(item, 1, Integer::sum);
```

### 2. Finding Maximum in HashMap

**Method 1: Iterate values**
```java
int max = 0;
for (int value : map.values()) {
    max = Math.max(max, value);
}
```

**Method 2: Stream (Java 8+)**
```java
int max = map.values().stream()
             .max(Integer::compare)
             .orElse(0);
```

### 3. Order-Preserving Lookup

**Why iterate original array:**
```
HashMap doesn't preserve order
Original array does
So iterate original to find first occurrence
```

### 4. Tiebreaker Implementation

```java
// Update winner if:
// 1. Strictly more votes, OR
// 2. Same votes but earlier occurrence
if (currentVotes > maxVotes || 
    (currentVotes == maxVotes && currentIndex < minIndex)) {
    winner = currentCandidate;
}
```

---

## 🎓 Interview Discussion Points

### When Interviewer Asks This Problem

**1. Clarify Requirements**
```
Q: "What if input is empty?"
Q: "Can candidate names be null?"
Q: "Are names case-sensitive?"
Q: "What defines 'first' - first vote or first unique candidate?"
```

**2. Explain Your Approach**
```
"I'll use a HashMap to count votes in O(n) time.
 Then find the maximum vote count.
 Finally, iterate the original list to find the first candidate
 with max votes, which naturally handles the tiebreaker."
```

**3. Discuss Trade-offs**
```
"My approach uses O(k) extra space where k is unique candidates.
 This is acceptable since k ≤ n and usually k << n in elections.
 The three-pass approach is clearer than tracking indices."
```

**4. Optimization Discussion**
```
"We could track first indices to reduce passes, but it adds
 complexity without improving asymptotic time complexity.
 The current O(n) solution is already optimal."
```

### Common Follow-up Questions

**Q: "What if we need top 3 candidates?"**
```java
A: "Sort candidates by votes (descending) and index (ascending),
    then return first 3. Or use a PriorityQueue."
```

**Q: "What if votes come in real-time?"**
```java
A: "Maintain a running HashMap. Update winner after each vote.
    Time per vote: O(1) for update, O(k) to check if winner changed."
```

**Q: "How to handle millions of votes efficiently?"**
```java
A: "HashMap handles large datasets well. Could parallelize
    counting with ConcurrentHashMap if needed."
```

---

## 🔄 Related Problems

### Similar Problems to Practice

1. **Most Frequent Element** - Find element with highest frequency
2. **Top K Frequent Elements** - Find k most frequent items
3. **First Unique Character** - Find first non-repeating character
4. **Majority Element** - Element appearing > n/2 times
5. **Group Anagrams** - Group strings that are anagrams

### Difficulty Progression

```
Election Tie Breaker (Medium) ← You are here
    ↓
Most Frequent Element (Easy)
    ↓
Top K Frequent Elements (Medium)
    ↓
Majority Element II (Medium - Hard)
```

---

## 🎯 Variations to Practice

### Variation 1: Return All Winners in Tie
```java
public List<String> findAllWinners(String[] votes) {
    // Return all candidates with max votes
    // Sorted by first occurrence
}
```

### Variation 2: Return Vote Percentages
```java
public Map<String, Double> getVotePercentages(String[] votes) {
    // Return each candidate's vote percentage
}
```

### Variation 3: Eliminate Runoff
```java
public String runoffElection(String[] votes, int minPercentage) {
    // Winner needs > minPercentage of votes
    // Otherwise return "runoff needed"
}
```

---

## 📚 Additional Learning

### Why HashMap is Perfect Here

1. **Fast Counting** - O(1) per vote
2. **Dynamic Keys** - Don't need to know candidates beforehand
3. **Memory Efficient** - Only stores unique candidates
4. **Simple Logic** - Clean counting pattern

### Alternative Approaches (Less Optimal)

**Using Array (if candidates known):**
```java
// Only if we know: candidates = {"Alice", "Bob", "Charlie"}
int[] counts = new int[3];  // O(k) space, but needs mapping
```

**Using Sorting:**
```java
// Sort votes: O(n log n)
// Count consecutive: O(n)
// Total: O(n log n) - worse than HashMap O(n)
```

---

## 🔗 Resources

- [HashMap Frequency Counter Pattern](https://www.geeksforgeeks.org/counting-frequencies-of-array-elements/)
- [LeetCode - Hash Table Problems](https://leetcode.com/tag/hash-table/)
- [Java HashMap Best Practices](https://www.baeldung.com/java-hashmap)

---

<div align="center">

**Master Frequency Counting with HashMap! 🗳️**

*A practical problem demonstrating HashMap's power for counting and tracking*

*Last Updated: December 2024*

[⬆ Back to HashMap](../README.md) | [⬆ Back to Main](../../README.md)

</div>
