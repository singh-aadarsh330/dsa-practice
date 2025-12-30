# 🧹 Remove All Duplicates from Unsorted Linked List

## 📌 Problem Statement

Given an **unsorted singly linked list**, remove **all nodes that have duplicate values**.  
After the operation, only nodes with **unique values** should remain in the list.

- If a value appears more than once, **all occurrences must be removed**
- The relative order of remaining nodes should be preserved

---

## 🧠 Explanation

This problem focuses on **eliminating duplicates entirely**, not just reducing them to a single occurrence.

For example:
- Values that appear **more than once** are considered invalid
- Only values with a **frequency of exactly one** are allowed in the final list

---

## 🛠️ Approach

The solution follows a **two-pass strategy** for clarity and correctness.

### 🔹 First Pass — Frequency Counting
Traverse the linked list and store the frequency of each node value using a `HashMap`.

### 🔹 Second Pass — Safe Node Deletion
Traverse the list again and remove nodes whose frequency is greater than one.

A **dummy node** is used to simplify pointer manipulation, especially when the
head node itself needs to be deleted.

---

## ⏱️ Complexity Analysis

- **Time Complexity:** O(n)
- **Space Complexity:** O(n)
- **In-place:** No (extra space used for HashMap)

---

## 🔍 Key Observations

- Index-based deletion is unsafe in linked lists due to shifting positions
- Pointer-based deletion ensures correctness after structural modifications
- Using a dummy node avoids special-case handling for head deletion

---

## 📂 Implementation Details

- **Language:** Java
- **File:** `RemoveAllDuplicates.java`
- **Technique:** HashMap + pointer manipulation

---

## 🎯 Interview Notes

- Common variation of duplicate-removal linked list problems
- Tests understanding of pointer discipline and safe deletion
- Interviewers prefer **clean pointer-based logic** over index-based approaches

A well-structured solution demonstrates strong fundamentals in linked list manipulation.
