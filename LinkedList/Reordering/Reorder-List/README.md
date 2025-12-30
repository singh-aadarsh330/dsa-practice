# Reorder Linked List (In-Place)

## 📌 Problem Statement
Given a singly linked list:

L0 → L1 → L2 → … → Ln-1 → Ln  

Reorder the list to:

L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …

The reordering must be done **in-place** without modifying node values.

---

## 🧠 Approach

The solution uses a **3-step optimal strategy**:

1. **Find the middle** of the linked list using slow and fast pointers.
2. **Reverse the second half** of the list.
3. **Merge both halves alternately**.

---

## 🧪 Example

**Input**
