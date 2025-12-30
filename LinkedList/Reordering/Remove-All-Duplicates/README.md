🧹 Remove All Duplicates from Unsorted Linked List

📌 Problem Statement
Given an unsorted singly linked list, remove all nodes that contain duplicate
values. After processing, only nodes with unique values should remain in the list.

🧠 Explanation
If a particular value appears more than once in the linked list, all occurrences
of that value must be removed entirely. The final list should contain only
elements that appeared exactly once.

🛠️ Approach
The solution follows a two-pass strategy to ensure correctness and clarity.

🔹 First Pass – Frequency Counting
Traverse the linked list and record the frequency of each node value using a
HashMap.

🔹 Second Pass – Pointer-Based Deletion
Traverse the linked list again and remove nodes whose frequency is greater than
one. A dummy node is used to simplify deletion logic, especially when the head
node itself needs to be removed.

⏱️ Complexity Analysis
- Time Complexity: O(n)
- Space Complexity: O(n)
- In-place: No (additional space used for HashMap)

🔍 Key Observations
- Index-based deletion is unsafe in linked lists due to shifting positions
- Pointer-based deletion ensures correctness after structural changes
- The dummy node pattern avoids special-case handling for head deletion

📂 Implementation Details
- Language: Java
- File: RemoveAllDuplicates.java
- Technique: HashMap combined with pointer manipulation

🎯 Interview Notes
This is a common linked list problem that tests understanding of pointer
discipline and safe node deletion. Interviewers typically prefer a clean,
pointer-based approach over index-based logic, even if both pass test cases.
