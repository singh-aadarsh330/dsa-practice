# Add Two Large Numbers

## Description
Add two non-negative integers represented as strings without using BigInteger or built-in large number libraries. Handles very large values, leading zeros, and proper digit-by-digit carry propagation.

---

## Example
```
Input:  s1 = "001234", s2 = "987"
Output: "2221"
```

---

## Approach
- Remove leading zeros from both strings.  
- Add digits from right to left while tracking carry.  
- Append digits to result and add final carry if present.  
- Return the computed sum as a string.

---

## Java Code
```java
class Solution {
    String findSum(String s1, String s2) {

        int carry = 0;
        String s3 = "";

        s1 = removeleadzero(s1);
        s2 = removeleadzero(s2);

        int i = s1.length() - 1, j = s2.length() - 1;

        while (i >= 0 && j >= 0) {
            int sum = (s1.charAt(i) - '0') + (s2.charAt(j) - '0') + carry;
            int digit = sum % 10;
            carry = sum / 10;
            s3 = digit + s3;
            i--;
            j--;
        }

        while (i >= 0) {
            int sum = (s1.charAt(i) - '0') + carry;
            int digit = sum % 10;
            carry = sum / 10;
            s3 = digit + s3;
            i--;
        }

        while (j >= 0) {
            int sum = (s2.charAt(j) - '0') + carry;
            int digit = sum % 10;
            carry = sum / 10;
            s3 = digit + s3;
            j--;
        }

        if (carry > 0)
            s3 = carry + s3;

        return s3;
    }

    String removeleadzero(String s) {
        int n = s.length();
        int i = 0;
        for (i = 0; i < n - 1; i++) {
            if (s.charAt(i) != '0')
                break;
        }
        return s.substring(i);
    }
}
```

---

## Complexity
- **Time:** O(n + m)  
- **Space:** O(max(n, m))
