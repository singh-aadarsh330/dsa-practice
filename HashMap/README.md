# 🗺️ HashMap

[![Problems](https://img.shields.io/badge/Problems-1-blue?style=flat-square)](.)
[![Difficulty](https://img.shields.io/badge/Difficulty-Easy%20to%20Medium-orange?style=flat-square)](.)
[![Language](https://img.shields.io/badge/Language-Java-red?style=flat-square)](.)

> Key-value pair data structure for efficient lookups, counting, and mapping operations. Master HashMap for O(1) average-time operations.

---

## 📋 Overview

**HashMap** is a hash table-based implementation of the Map interface in Java. It stores data in key-value pairs and provides fast access, insertion, and deletion operations.

**Key Features:**
- **Fast Operations** - O(1) average time for get/put/remove
- **Key-Value Pairs** - Each key maps to exactly one value
- **No Duplicate Keys** - Keys are unique, values can duplicate
- **Allows Null** - One null key and multiple null values
- **Unordered** - No guaranteed order of elements

**When to Use HashMap:**
- ✅ Need fast lookups by key
- ✅ Counting frequency of elements
- ✅ Caching/memoization
- ✅ Building indices or mappings
- ❌ Need sorted order (use TreeMap)
- ❌ Need insertion order (use LinkedHashMap)

---

## 🔑 HashMap Basics

### Declaration and Initialization

```java
// Basic declaration
HashMap<String, Integer> map = new HashMap<>();

// With initial capacity
HashMap<String, Integer> map = new HashMap<>(16);

// With initial capacity and load factor
HashMap<String, Integer> map = new HashMap<>(16, 0.75f);

// From existing map
HashMap<String, Integer> map = new HashMap<>(existingMap);

// Different key-value types
HashMap<Integer, String> idToName = new HashMap<>();
HashMap<String, ArrayList<Integer>> groupMap = new HashMap<>();
HashMap<Character, Integer> charCount = new HashMap<>();
```

### Common Operations

```java
// Adding/Updating
map.put("key", 100);              // Add or update: O(1)
map.putIfAbsent("key", 100);      // Add only if key doesn't exist

// Accessing
int value = map.get("key");       // Get value: O(1)
int value = map.getOrDefault("key", 0);  // Get with default

// Checking
boolean exists = map.containsKey("key");     // O(1)
boolean hasValue = map.containsValue(100);   // O(n)
boolean empty = map.isEmpty();               // O(1)
int size = map.size();                       // O(1)

// Removing
map.remove("key");                // Remove by key: O(1)
map.clear();                      // Remove all: O(n)

// Updating value
map.put("key", map.get("key") + 1);           // Increment
map.merge("key", 1, Integer::sum);            // Merge/increment
map.compute("key", (k, v) -> v == null ? 1 : v + 1);  // Compute
```

### Iteration Methods

```java
// Iterate over keys
for (String key : map.keySet()) {
    System.out.println(key + " -> " + map.get(key));
}

// Iterate over values
for (Integer value : map.values()) {
    System.out.println(value);
}

// Iterate over entries (most efficient)
for (Map.Entry<String, Integer> entry : map.entrySet()) {
    System.out.println(entry.getKey() + " -> " + entry.getValue());
}

// Java 8+ forEach
map.forEach((key, value) -> System.out.println(key + " -> " + value));
```

---

## 📂 Repository Structure

```
HashMap/
│
├── ElectionTieBreaker/      # Determine election winner with tiebreaker
│   └── README.md
│
└── README.md                # This file
```

---

## 📝 Problems in This Folder

| # | Problem | Folder | Difficulty | Key Concepts | Status |
|---|---------|--------|------------|--------------|--------|
| 1 | Election Tie Breaker | [ElectionTieBreaker/](./ElectionTieBreaker/) | Medium | Vote counting, Frequency map, Tiebreaker logic | ✅ |

---

## 🎯 Problem: Election Tie Breaker

### Problem Overview

Given a list of votes (candidate names), determine the winner. If there's a tie, the candidate whose name appears **first** in the list wins.

**Key Concepts:**
- Frequency counting using HashMap
- Finding maximum frequency
- Handling ties with custom logic
- Order-based tiebreaker

**See detailed solution:** [ElectionTieBreaker/README.md](./ElectionTieBreaker/README.md)

---

## 🔑 Key HashMap Concepts

### 1. HashMap vs Other Maps

| Feature | HashMap | TreeMap | LinkedHashMap | Hashtable |
|---------|---------|---------|---------------|-----------|
| **Order** | No order | Sorted by key | Insertion order | No order |
| **Null Keys** | 1 allowed | Not allowed | 1 allowed | Not allowed |
| **Null Values** | Allowed | Allowed | Allowed | Not allowed |
| **Thread-Safe** | No | No | No | Yes (legacy) |
| **Performance** | O(1) avg | O(log n) | O(1) avg | O(1) avg |
| **Best For** | Fast lookups | Sorted data | Cache (LRU) | Legacy code |

### 2. Internal Working

**Hash Function:**
```
hashCode() → internal hash → bucket index
```

**Collision Handling:**
- Uses **chaining** (linked list at each bucket)
- Java 8+: Converts to **tree** when chain too long
- Load factor: 0.75 (resizes when 75% full)

**Time Complexities:**
```
get(key):       O(1) average, O(n) worst
put(key, val):  O(1) average, O(n) worst
remove(key):    O(1) average, O(n) worst
containsKey():  O(1) average
```

### 3. Important Methods

**Basic Operations:**
```java
put(K key, V value)           // Add/update
get(Object key)               // Retrieve
remove(Object key)            // Delete
containsKey(Object key)       // Check key
containsValue(Object value)   // Check value
```

**Default Value Handling:**
```java
getOrDefault(key, defaultValue)    // Get with fallback
putIfAbsent(key, value)            // Add if absent
```

**Bulk Operations:**
```java
putAll(Map m)                 // Add all from another map
clear()                       // Remove all entries
```

**View Operations:**
```java
keySet()                      // Set of all keys
values()                      // Collection of all values
entrySet()                    // Set of key-value pairs
```

---

## 💡 Common HashMap Patterns

### Pattern 1: Frequency Counter
```java
// Count occurrences of elements
HashMap<String, Integer> freq = new HashMap<>();
for (String item : items) {
    freq.put(item, freq.getOrDefault(item, 0) + 1);
}
```

### Pattern 2: Group By Key
```java
// Group items by category
HashMap<String, ArrayList<String>> groups = new HashMap<>();
for (String item : items) {
    String category = getCategory(item);
    groups.putIfAbsent(category, new ArrayList<>());
    groups.get(category).add(item);
}
```

### Pattern 3: Two Sum Pattern
```java
// Find pairs that sum to target
HashMap<Integer, Integer> seen = new HashMap<>();
for (int i = 0; i < arr.length; i++) {
    int complement = target - arr[i];
    if (seen.containsKey(complement)) {
        return new int[]{seen.get(complement), i};
    }
    seen.put(arr[i], i);
}
```

### Pattern 4: Character/Element Index
```java
// Map elements to their indices
HashMap<Character, Integer> lastIndex = new HashMap<>();
for (int i = 0; i < s.length(); i++) {
    lastIndex.put(s.charAt(i), i);
}
```

### Pattern 5: Cache/Memoization
```java
// Store computed results
HashMap<Integer, Integer> memo = new HashMap<>();
int result = compute(n);

int compute(int n) {
    if (memo.containsKey(n)) return memo.get(n);
    int result = expensiveComputation(n);
    memo.put(n, result);
    return result;
}
```

---

## ⚠️ Common Mistakes

### 1. Modifying Keys After Insertion
```java
❌ Incorrect:
StringBuilder key = new StringBuilder("test");
map.put(key, 100);
key.append("123");        // Changes hashCode!
map.get(key);             // May not find it!

✅ Correct:
String key = "test";      // Use immutable keys
map.put(key, 100);
```

### 2. Using Mutable Objects as Keys
```java
❌ Avoid:
ArrayList<Integer> key = new ArrayList<>();
map.put(key, value);      // ArrayList is mutable!

✅ Better:
String key = list.toString();
map.put(key, value);
```

### 3. NullPointerException on get()
```java
❌ Incorrect:
Integer count = map.get(key);
count++;                  // NPE if key doesn't exist!

✅ Correct:
int count = map.getOrDefault(key, 0);
count++;
map.put(key, count);
```

### 4. Comparing with ==
```java
❌ Incorrect:
String key = "test";
if (map.get(key) == 5)    // Wrong for Integer!

✅ Correct:
if (map.get(key).equals(5))  // Use equals()
```

### 5. Concurrent Modification
```java
❌ Incorrect:
for (String key : map.keySet()) {
    if (condition) {
        map.remove(key);  // ConcurrentModificationException!
    }
}

✅ Correct:
Iterator<String> it = map.keySet().iterator();
while (it.hasNext()) {
    String key = it.next();
    if (condition) {
        it.remove();
    }
}
```

---

## 🎓 HashMap Best Practices

### 1. Use getOrDefault()
```java
// Cleaner than checking containsKey
int count = map.getOrDefault(key, 0) + 1;
map.put(key, count);
```

### 2. Use compute() for Complex Updates
```java
// Atomic update
map.compute(key, (k, v) -> v == null ? 1 : v + 1);
```

### 3. Use merge() for Combining Values
```java
// Merge values with function
map.merge(key, 1, Integer::sum);  // Increment count
```

### 4. Initialize with Capacity
```java
// Avoid resizing if size is known
HashMap<String, Integer> map = new HashMap<>(expectedSize);
```

### 5. Use Map Interface for Flexibility
```java
// More flexible
Map<String, Integer> map = new HashMap<>();
// Can easily swap to TreeMap or LinkedHashMap
```

---

## 📚 Topics to be Added

As I continue practicing, this folder will include:

### **Frequency Problems**
- Character frequency count
- Most frequent element
- Top K frequent elements
- First unique character

### **Two Pointer with HashMap**
- Two sum problem
- Subarray sum equals K
- Longest substring without repeating

### **Grouping Problems**
- Group anagrams
- Group by property
- Partition by condition

### **Advanced HashMap**
- Design HashMap from scratch
- LRU Cache implementation
- Custom hash functions

---

## 🎯 Learning Path

### Beginner Level
1. **Basic Operations** - put, get, remove
2. **Frequency Counting** - Count occurrences
3. **Existence Checks** - containsKey usage

### Intermediate Level
4. **Two Sum Problems** - Complement finding
5. **Grouping** - Group elements by criteria
6. **Iteration Techniques** - entrySet, keySet

### Advanced Level
7. **Custom Keys** - Using objects as keys
8. **Performance** - Load factor, capacity
9. **Design Problems** - Implement cache, etc.

---

## 💻 Essential Methods Reference

### Adding/Updating
```java
V put(K key, V value)              // Add or update
void putAll(Map<? extends K, ? extends V> m)
V putIfAbsent(K key, V value)      // Add if not present
```

### Retrieving
```java
V get(Object key)                  // Get value
V getOrDefault(Object key, V defaultValue)
```

### Removing
```java
V remove(Object key)               // Remove and return value
boolean remove(Object key, Object value)  // Remove if matches
void clear()                       // Remove all
```

### Checking
```java
boolean containsKey(Object key)    // Check if key exists
boolean containsValue(Object value) // Check if value exists
boolean isEmpty()                  // Check if empty
int size()                         // Number of entries
```

### Advanced Operations
```java
V compute(K key, BiFunction<? super K, ? super V, ? extends V> f)
V computeIfAbsent(K key, Function<? super K, ? extends V> f)
V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> f)
V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> f)
```

---

## 🔄 HashMap vs Other Data Structures

### When to Use What?

**Use HashMap when:**
- Need O(1) lookups by key
- Keys are hashable
- Order doesn't matter
- Counting frequencies

**Use TreeMap when:**
- Need sorted order
- Range queries needed
- Finding floor/ceiling values

**Use LinkedHashMap when:**
- Need insertion order
- Implementing LRU cache
- Predictable iteration

**Use Array when:**
- Keys are small integers
- Fixed size known
- Ultimate speed needed

**Use HashSet when:**
- Only need keys (no values)
- Checking membership
- Removing duplicates

---

## 📊 Progress Tracker

**Current Status:**
- Topics Covered: 1 (Election Tie Breaker)
- Total Problems: 1
- Difficulty: Medium
- Concepts: Frequency counting, Tiebreaker logic

**Next Milestones:**
- [ ] Add 5 frequency counting problems
- [ ] Two sum variations
- [ ] Grouping problems
- [ ] Advanced HashMap applications

---

## 🎓 Interview Tips

### Common HashMap Interview Questions

1. **"Explain how HashMap works internally"**
   - Hash function converts key to bucket index
   - Collisions handled by chaining/trees
   - Load factor triggers resizing (0.75 default)
   - O(1) average, O(n) worst case

2. **"HashMap vs HashTable vs ConcurrentHashMap"**
   - HashMap: Not thread-safe, allows null
   - HashTable: Thread-safe (legacy), no null
   - ConcurrentHashMap: Thread-safe (modern), no null

3. **"Why are immutable objects good as keys?"**
   - hashCode() must remain constant
   - Mutable objects can change hash
   - String, Integer are commonly used

### What Interviewers Look For

- ✅ Understanding of O(1) operations
- ✅ Proper null handling
- ✅ Using appropriate methods (getOrDefault, etc.)
- ✅ Knowing when to use HashMap vs alternatives
- ✅ Handling edge cases

---

## 📖 Additional Resources

### Documentation
- [Java HashMap Documentation](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html)
- [Java Map Interface](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html)

### Practice Platforms
- [GeeksforGeeks - HashMap](https://www.geeksforgeeks.org/java-util-hashmap-in-java/)
- [LeetCode - Hash Table](https://leetcode.com/tag/hash-table/)

### Video Tutorials
- [HashMap Internal Working](https://www.youtube.com/results?search_query=hashmap+internal+working+java)
- [HashMap vs TreeMap](https://www.youtube.com/results?search_query=hashmap+vs+treemap)

---

## 🤝 Contributing

As I add more HashMap problems:
- Each category will get dedicated coverage
- Multiple approaches for complex problems
- Clear time-space complexity analysis
- Interview-focused explanations

---

## 💡 Remember

> "HashMap is not just a data structure—it's a problem-solving tool that transforms O(n²) solutions into O(n) solutions."

**Key Takeaways:**
- Master frequency counting patterns
- Understand hash collisions
- Practice two-sum variations
- Know when to use HashMap vs alternatives

---

<div align="center">

**Master HashMap, Master Fast Lookups! 🗺️**

*Last Updated: December 2024*

[⬆ Back to Main Repository](../README.md)

</div>
