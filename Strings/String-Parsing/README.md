# 🔤 String Parsing – DSA in Java

This folder contains problems that involve **extracting structured information from strings**
and converting it into usable data types (numbers, tokens, values).

String parsing problems are extremely common in **coding interviews**, as they test:
- Attention to edge cases
- Careful pointer movement
- Defensive programming
- Overflow and invalid-input handling

---

## 📌 What Are String Parsing Problems?

String parsing problems require you to:
- Traverse a string character by character
- Interpret meaning (digits, signs, spaces, delimiters)
- Stop parsing at the correct point
- Handle invalid or partial input safely

These problems **must not rely on built-in parsing shortcuts** in interviews.

---

## 🧠 Common Techniques Used

- Two-pointer / index-based traversal
- Whitespace skipping
- Optional sign handling (`+` / `-`)
- Digit-by-digit number construction
- Early stopping at non-valid characters
- Overflow detection without using exceptions

---

## 📂 Problems Included

| # | Problem Name | Key Concepts | Time |
|---|-------------|------------|------|
| 1 | String to Integer (atoi) | Parsing, Overflow Handling | O(n) |

---

## 🧪 Example Problem

### String to Integer (atoi)

Convert a string into a 32-bit signed integer by following rules similar to the C `atoi` function.

**Input**


" -42abc"


**Output**


-42



**Key Rules**
- Ignore leading whitespaces
- Handle optional sign
- Parse consecutive digits only
- Clamp values outside integer range

---

## 🎯 Why This Folder Matters for Interviews

String parsing problems:
- Frequently appear in **FAANG / product company interviews**
- Are easy to get partially correct but hard to perfect
- Separate average solutions from strong ones

Mastering these improves your **attention to detail and robustness**.

---

## 🏷 Tags
`Strings` `Parsing` `Simulation` `Overflow Handling` `Java`

---

📌 **Note:**  
All solutions in this folder avoid built-in parsing shortcuts and exception-based logic,
focusing instead on interview-accepted, optimal implementations.
