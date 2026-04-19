# Heap (Priority Queue) 🔥

## 📌 Overview

A **Heap** is a special tree-based data structure that satisfies the heap property.
In Java, it is implemented using `PriorityQueue`.

Heaps are mainly used for:

* Efficient retrieval of **minimum/maximum element**
* Solving **Top K problems**
* Optimization problems

---

## ⚙️ Types of Heap

### 🔹 Min Heap

* Smallest element is at the top
* Default behavior of `PriorityQueue` in Java

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
```

---

### 🔹 Max Heap

* Largest element is at the top

```java
PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
```

---

## 🚀 Common Operations

| Operation     | Time Complexity |
| ------------- | --------------- |
| Insert        | O(log n)        |
| Delete (poll) | O(log n)        |
| Peek          | O(1)            |

---

## 🧠 Key Concepts

* Complete Binary Tree
* Heap Property
* Priority Queue
* Top K Elements
* Kth Largest / Smallest

---

## 🔥 Important Problems

* K Largest Elements
* Kth Largest Element
* Top K Frequent Elements
* Merge K Sorted Lists
* Median in Data Stream

---

## 💡 Example (Min Heap)

```java
import java.util.*;

public class Example {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        pq.add(10);
        pq.add(5);
        pq.add(20);

        System.out.println(pq.peek()); // 5

        while(!pq.isEmpty()) {
            System.out.print(pq.poll() + " ");
        }
    }
}
```

---

## ⚡ When to Use Heap?

* When you need **Top K elements**
* When frequent **min/max extraction** is required
* When sorting is too costly

---

## 🏆 Interview Tip

> Use a **Min Heap of size k** for k largest elements problems (O(n log k)).

---

## 📂 Folder Structure Suggestion

```
Heap/
 ├── k-largest-elements.java
 ├── kth-largest-element.java
 ├── top-k-frequent.java
```

---

## 🚀 Summary

* Heap = efficient priority-based structure
* Best for optimization problems
* Widely used in coding interviews

---
