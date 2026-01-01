# Sliding Window Technique

A powerful algorithmic pattern for solving substring and subarray problems efficiently by maintaining a dynamic window of elements.

## 🎯 What is Sliding Window?

The sliding window technique uses two pointers (or indices) to create a "window" that slides through an array or string. This approach reduces time complexity from O(n²) or O(n³) to O(n) for many problems.

## 🔑 Key Concepts

### When to Use Sliding Window
- Finding subarrays/substrings with specific properties
- Optimizing contiguous sequence problems
- Problems asking for "longest", "shortest", "maximum", "minimum" subarrays
- When you need to track elements within a range

### Types of Sliding Windows

#### 1. Fixed-Size Window
Window size remains constant throughout.
```
[1, 2, 3], 4, 5  →  1, [2, 3, 4], 5  →  1, 2, [3, 4, 5]
```

#### 2. Dynamic-Size Window
Window size changes based on conditions.
```
[1, 2], 3, 4, 5  →  1, [2, 3, 4], 5  →  1, 2, [3], 4, 5
```

## 🧩 Common Patterns

### Pattern 1: Fixed Window Size
```
for (int i = 0; i < arr.length; i++) {
    // Add element to window
    if (i >= k - 1) {
        // Process window
        // Remove leftmost element
    }
}
```

### Pattern 2: Expanding/Contracting Window
```
int left = 0;
for (int right = 0; right < arr.length; right++) {
    // Expand window
    while (condition_not_met) {
        // Contract window from left
        left++;
    }
    // Update result
}
```

## 💡 Problem-Solving Strategy

1. **Identify** - Can the problem be solved with contiguous elements?
2. **Determine** - Fixed or dynamic window size?
3. **Track** - What data structure helps maintain window state? (HashMap, Set, counter)
4. **Expand** - Add elements to the right
5. **Contract** - Remove elements from the left when conditions are violated
6. **Update** - Track optimal result throughout

## 📊 Time & Space Complexity

- **Time**: O(n) - Each element visited at most twice
- **Space**: O(k) - Where k is the window size or character set size

## 🎓 Problems in This Section

1. [Longest Substring Without Repeating Characters](./Longest-Substring-Without-Repeating-Characters/) - Find the longest substring with unique characters

## 🔗 Related Techniques

- **Two Pointers** - Similar concept but doesn't always maintain contiguous elements
- **Hash Maps** - Often used to track window contents
- **Monotonic Queue** - For maintaining max/min in sliding window

## 💪 Practice Tips

- Draw out the window movement on paper
- Track what happens at each expansion and contraction
- Consider edge cases: empty input, window size = array size
- Start with brute force, then optimize to sliding window

---

**Slide into efficiency! 🚀**
