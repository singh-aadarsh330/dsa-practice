## Matrix (2D Arrays)

This folder contains Data Structures and Algorithms problems related to 2D arrays (matrices), commonly asked in coding interviews and competitive programming.

Matrix problems emphasize traversal, searching, and optimization using row-wise and column-wise properties.

---

## Topics Covered
- Matrix traversal techniques
- Searching in sorted matrices
- Index mapping between 1D and 2D arrays
- Boundary and edge case handling
- Optimized elimination-based approaches

---

## Problems Included

### Search in a Row-wise and Column-wise Sorted Matrix

**Approach:**
- Start from the top-right corner of the matrix.
- If the current element is greater than the target, move left.
- If the current element is smaller than the target, move down.
- Each step eliminates one row or one column.

**Time Complexity:** O(rows + columns)  
**Space Complexity:** O(1)

File: `SearchInSortedMatrix.java`

---

## Key Learnings
- Leveraging matrix properties reduces time complexity.
- Proper boundary checks prevent index-out-of-bounds errors.
- Choosing the correct traversal starting point simplifies logic.

---

## Tags
DSA, Matrix, 2D Array, Java, Interview Preparation
