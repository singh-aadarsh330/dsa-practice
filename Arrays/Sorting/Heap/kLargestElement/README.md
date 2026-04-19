# K Largest Elements

## 🔍 Problem Statement

Given an array `arr[]` of size `n` and an integer `k`, find the **k largest elements** in the array.

---

## 💡 Approach (Min Heap - Optimal)

We use a **Min Heap (Priority Queue)** of size `k`:

1. Traverse the array
2. Insert each element into the heap
3. If heap size exceeds `k`, remove the smallest element
4. At the end, heap contains the **k largest elements**

---

## ⚡ Time Complexity

* **O(n log k)**

## 🧠 Space Complexity

* **O(k)**

---

## 🚀 Why this approach?

* More efficient than sorting (**O(n log n)**)
* Maintains only `k` elements instead of entire array
* Ideal for large datasets

---

## 🧾 Java Code

```java
import java.util.*;

class Solution {
    public ArrayList<Integer> kLargest(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int num : arr) {
            pq.add(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        ArrayList<Integer> result = new ArrayList<>();

        while (!pq.isEmpty()) {
            result.add(pq.poll());
        }

        Collections.reverse(result); // descending order
        return result;
    }
}
```

---

## 📌 Example

**Input:**

```
arr = [3, 2, 1, 5, 6, 4], k = 2
```

**Output:**

```
[6, 5]
```

---

## 🧠 Key Concepts

* Heap (Priority Queue)
* Top K Elements Pattern
* Optimization over sorting

---

## 🔥 Interview Tip

> Maintain a min heap of size `k` to efficiently track the k largest elements.

---
