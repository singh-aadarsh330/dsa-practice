## Binary Search on Answer

This folder contains problems that are solved using **Binary Search on the Answer**
technique.

In this pattern, binary search is applied on the **range of possible answers**
instead of directly on array indices.

---

## When to Use This Pattern

Binary Search on Answer is applicable when:
- The problem asks for a **minimum or maximum value**
- The answer lies within a numeric range
- A **feasibility check** can be performed for a guessed value
- The feasibility condition is **monotonic** (once true, remains true)

---

## General Strategy

1. Define the search space for the answer.
2. Apply binary search on this range.
3. For each mid value, check if it satisfies the condition.
4. If the condition is satisfied, try to optimize further.
5. Continue until the optimal answer is found.

---

## Key Rule

For problems asking for the **minimum valid answer**:

