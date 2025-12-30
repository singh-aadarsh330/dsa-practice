# 🔁 Reorder Linked List (In-Place)

## 📌 Problem Statement

Given a **singly linked list**:

L0 → L1 → L2 → … → Ln-1 → Ln

Rearrange the list into the following order:

L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …

The reordering must be performed **in-place**, without modifying node values
and without using extra data structures.

---

## 🧠 Explanation

This problem focuses on **restructuring the linked list by changing node
connections**, not by altering the stored values.

The goal is to interleave nodes from the **start** and **end** of the list in
an alternating pattern while preserving the original relative order within
each half.

---

## 🛠️ Approach

The solution follows a **three-step optimal strategy**:

### 🔹 Step 1 — Find the Middle
Use the slow and fast pointer technique to locate the midpoint of the list.

### 🔹 Step 2 — Reverse the Second Half
Reverse the nodes after the middle to allow alternating access from the end.

### 🔹 Step 3 — Merge Alternately
Merge nodes from the first half and the reversed second half in alternating
order to achieve the required pattern.

---

## 🧪 Example

**Input**
1 → 2 → 3 → 4 → 5 → 6

**Output**
1 → 6 → 2 → 5 → 3 → 4

---

## ⏱️ Complexity Analysis

- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **In-place:** Yes

---

## 🔍 Key Observations

- The second half of the list is always equal to or smaller than the first
- Proper list termination is crucial to avoid cycles
- Temporary pointers must be stored before rewiring links

---

## 📂 Implementation Details

- **Language:** Java
- **File:** `ReorderList.java`
- **Technique:** Two pointers, reversal, and controlled pointer merging

---

## 🎯 Interview Notes

- Frequently asked linked list reordering problem
- Tests pointer discipline and in-place manipulation skills
- A clean implementation demonstrates strong fundamentals in linked list handling
