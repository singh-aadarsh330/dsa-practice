# Prefix Sum ➕

**Category:** Arrays - Optimization Technique  
**Difficulty:** Easy to Medium  
**Time Complexity:** O(n) preprocessing, O(1) query  
**Space Complexity:** O(n)

---

## 📋 Overview

Prefix Sum (also called Cumulative Sum) is a powerful preprocessing technique that allows us to calculate the sum of any subarray in constant time O(1) after O(n) preprocessing. It's one of the most useful optimization techniques for array problems.

---

## 🎯 Core Concept

### Definition
```
Prefix sum at index i = Sum of all elements from index 0 to i

prefix[i] = arr[0] + arr[1] + ... + arr[i]
```

### Key Formula
```
Sum of subarray[i...j] = prefix[j] - prefix[i-1]

Special case: i = 0
Sum of subarray[0...j] = prefix[j]
```

### Visual Example
```
Array:     [3, 1, 4, 2, 5]
Index:      0  1  2  3  4

Prefix:    [3, 4, 8, 10, 15]
            ↑  ↑  ↑  ↑   ↑
            3  3+1 4+4 8+2 10+5

Query: Sum(1, 3) = subarray [1, 4, 2]
Answer: prefix[3] - prefix[0] = 10 - 3 = 7 ✓
```

---

## 💡 Implementation Patterns

### Pattern 1: Basic Prefix Sum
```java
int[] buildPrefixSum(int[] arr) {
    int n = arr.length;
    int[] prefix = new int[n];
    
    prefix[0] = arr[0];
    for (int i = 1; i < n; i++) {
        prefix[i] = prefix[i - 1] + arr[i];
    }
    
    return prefix;
}

int rangeSum(int[] prefix, int left, int right) {
    if (left == 0) return prefix[right];
    return prefix[right] - prefix[left - 1];
}
```

### Pattern 2: Prefix Sum with Extra Space (1-indexed)
```java
int[] buildPrefixSumPadded(int[] arr) {
    int n = arr.length;
    int[] prefix = new int[n + 1]; // Extra space at index 0
    
    for (int i = 0; i < n; i++) {
        prefix[i + 1] = prefix[i] + arr[i];
    }
    
    return prefix;
}

int rangeSum(int[] prefix, int left, int right) {
    // No need for special case
    return prefix[right + 1] - prefix[left];
}
```

### Pattern 3: In-Place Prefix Sum
```java
void prefixSumInPlace(int[] arr) {
    for (int i = 1; i < arr.length; i++) {
        arr[i] += arr[i - 1];
    }
}
```

### Pattern 4: 2D Prefix Sum (Matrix)
```java
int[][] build2DPrefixSum(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;
    int[][] prefix = new int[m + 1][n + 1];
    
    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            prefix[i][j] = matrix[i-1][j-1]
                         + prefix[i-1][j]
                         + prefix[i][j-1]
                         - prefix[i-1][j-1];
        }
    }
    
    return prefix;
}

int query2DSum(int[][] prefix, int r1, int c1, int r2, int c2) {
    // Sum of rectangle from (r1,c1) to (r2,c2)
    return prefix[r2+1][c2+1]
         - prefix[r1][c2+1]
         - prefix[r2+1][c1]
         + prefix[r1][c1];
}
```

### Pattern 5: Prefix Sum with HashMap (for counts)
```java
int subarraySum(int[] arr, int k) {
    HashMap<Integer, Integer> prefixCount = new HashMap<>();
    prefixCount.put(0, 1); // Empty prefix
    
    int sum = 0, count = 0;
    
    for (int num : arr) {
        sum += num;
        
        // If (sum - k) exists, we found subarray
        count += prefixCount.getOrDefault(sum - k, 0);
        
        // Store current prefix sum
        prefixCount.put(sum, prefixCount.getOrDefault(sum, 0) + 1);
    }
    
    return count;
}
```

---

## 🔍 Problem Categories

### Basic Prefix Sum
1. **Build prefix sum array**
2. **Range sum queries** - Sum(L, R)
3. **Multiple range queries** - Q queries
4. **Equilibrium index** - Left sum = Right sum
5. **Find pivot index**

### Subarray Problems
6. **Subarray with given sum** - Sum = K
7. **Count subarrays with sum K**
8. **Maximum sum subarray** (Kadane's variant)
9. **Longest subarray with sum K**
10. **Subarray divisible by K**

### Advanced Applications
11. **Product array puzzle** - Without division
12. **Minimum operations to make equal**
13. **Maximum subarray after K operations**
14. **Corporate flight bookings**
15. **Range addition**

### 2D Prefix Sum
16. **Matrix range sum queries**
17. **Count square submatrices**
18. **Max sum rectangle**
19. **Submatrix sum equals target**

### With HashMap
20. **Subarray sum equals K**
21. **Continuous subarray sum** (divisible by K)
22. **Maximum size subarray sum K**
23. **Count subarrays with XOR K**

---

## 📊 Complexity Analysis

### Time Complexity
```
Preprocessing: O(n) - Build prefix array
Query: O(1) - Constant time lookup
Q queries: O(n + Q) - Much better than O(n × Q)

Example:
Without prefix: 1000 queries on array of size 1000
                = 1000 × 1000 = 1,000,000 operations

With prefix: 1000 + 1000 = 2,000 operations
             500x faster!
```

### Space Complexity
```
O(n) - Store prefix array
O(1) - In-place variant (destroys original)
O(m × n) - 2D prefix sum
```

---

## 💻 Complete Examples

### Example 1: Range Sum Queries
```java
class NumArray {
    private int[] prefix;
    
    public NumArray(int[] nums) {
        int n = nums.length;
        prefix = new int[n + 1];
        
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
    }
    
    public int sumRange(int left, int right) {
        return prefix[right + 1] - prefix[left];
    }
}

// Usage
int[] arr = {1, 2, 3, 4, 5};
NumArray obj = new NumArray(arr);
System.out.println(obj.sumRange(1, 3)); // 2+3+4 = 9
System.out.println(obj.sumRange(0, 4)); // 1+2+3+4+5 = 15
```

### Example 2: Equilibrium Index
```java
int equilibriumIndex(int[] arr) {
    int n = arr.length;
    int[] prefix = new int[n];
    
    // Build prefix
    prefix[0] = arr[0];
    for (int i = 1; i < n; i++) {
        prefix[i] = prefix[i - 1] + arr[i];
    }
    
    int totalSum = prefix[n - 1];
    
    for (int i = 0; i < n; i++) {
        int leftSum = (i == 0) ? 0 : prefix[i - 1];
        int rightSum = totalSum - prefix[i];
        
        if (leftSum == rightSum) {
            return i;
        }
    }
    
    return -1;
}
```

### Example 3: Subarray Sum Equals K
```java
int subarraySum(int[] nums, int k) {
    HashMap<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);
    
    int sum = 0, count = 0;
    
    for (int num : nums) {
        sum += num;
        count += map.getOrDefault(sum - k, 0);
        map.put(sum, map.getOrDefault(sum, 0) + 1);
    }
    
    return count;
}
```

---

## 🎓 Key Insights

### Why Prefix Sum Works
```
Sum(i, j) = Sum(0, j) - Sum(0, i-1)
          = prefix[j] - prefix[i-1]

This is because:
prefix[j] contains all elements from 0 to j
prefix[i-1] contains all elements from 0 to i-1
Subtracting removes elements before i
```

### Common Trick: Prefix + HashMap
```
Problem: Count subarrays with sum K

Naive: Check all subarrays O(n²)
Optimized: Use prefix sum + HashMap O(n)

Key Insight:
If prefix[j] - prefix[i] = K
Then prefix[i] = prefix[j] - K

So for each j, check if (current_sum - K) exists in map!
```

---

## ⚠️ Common Mistakes

1. ❌ **Off-by-one in range queries**
   ```java
   // Wrong: prefix[right] - prefix[left]
   // Correct: prefix[right] - prefix[left-1]
   ```

2. ❌ **Not handling left = 0**
   ```java
   // Use 1-indexed or special case
   if (left == 0) return prefix[right];
   ```

3. ❌ **Integer overflow**
   ```java
   // Use long for large sums
   long[] prefix = new long[n];
   ```

4. ❌ **2D formula errors**
   ```java
   // Inclusion-exclusion principle
   // Don't forget to add back overlapping region
   ```

---

## 🎯 When to Use Prefix Sum

### ✅ Use When:
- Multiple range sum queries
- Subarray sum problems
- Need O(1) query time
- Array doesn't change (static)
- Cumulative operations needed

### ❌ Consider Alternatives When:
- Array changes frequently (use Segment Tree/Fenwick Tree)
- Single query only
- Space is very limited
- Need other operations besides sum

---

## 🚀 Advanced Techniques

### 1. Difference Array (Inverse of Prefix Sum)
```java
// For range update problems
void rangeUpdate(int[] diff, int L, int R, int val) {
    diff[L] += val;
    if (R + 1 < diff.length) {
        diff[R + 1] -= val;
    }
}

int[] getFinalArray(int[] diff) {
    // Apply prefix sum to get result
    for (int i = 1; i < diff.length; i++) {
        diff[i] += diff[i - 1];
    }
    return diff;
}
```

### 2. Prefix XOR
```java
int[] prefixXOR = new int[n];
prefixXOR[0] = arr[0];
for (int i = 1; i < n; i++) {
    prefixXOR[i] = prefixXOR[i - 1] ^ arr[i];
}

// XOR of range [L, R]
int xor = (L == 0) ? prefixXOR[R] : prefixXOR[R] ^ prefixXOR[L - 1];
```

### 3. Prefix Product
```java
long[] prefixProduct = new long[n];
prefixProduct[0] = arr[0];
for (int i = 1; i < n; i++) {
    prefixProduct[i] = prefixProduct[i - 1] * arr[i];
}
```

---

## 📈 Problem Difficulty Levels

**Easy (10-15 problems):**
- Range sum queries
- Equilibrium index
- Pivot index

**Medium (15-20 problems):**
- Subarray sum equals K
- Continuous subarray sum
- Product except self
- 2D matrix sum

**Hard (5-8 problems):**
- Maximum sum rectangle
- Count submatrices with sum
- Complex constraints

---

## 🔗 Related Techniques

- [Sliding Window](../Sliding-Window/)
- [Two Pointer](../Two-Pointer/)
- [Subarrays](../Subarrays/)
- [Segment Trees](../../Advanced/) (dynamic updates)
- [Fenwick Trees](../../Advanced/) (point updates)

---

## 📊 Progress Tracker

- [ ] Build prefix sum array
- [ ] Range sum queries
- [ ] Equilibrium index
- [ ] Subarray sum equals K
- [ ] 2D prefix sum
- [ ] Prefix + HashMap
- [ ] Difference array
- [ ] Advanced applications

---

## 🏆 Must-Solve Problems

1. Range Sum Query - Immutable
2. Find Pivot Index
3. Subarray Sum Equals K
4. Continuous Subarray Sum
5. Product of Array Except Self
6. Range Sum Query 2D
7. Maximum Size Subarray Sum Equals K
8. Contiguous Array (0s and 1s)

---

## 💡 Interview Tips

1. **Recognize pattern**: "Range sum", "subarray sum"
2. **Mention trade-off**: Space for time
3. **Discuss alternatives**: Segment tree for updates
4. **Edge cases**: Empty array, single element, all negative
5. **HashMap variant**: For count-based problems

---

**Note:** This folder will contain 30-40 prefix sum problems with detailed solutions.

---

**Coming Soon!** 🚀

*Comprehensive problem set with explanations.*

---

**Happy Coding! ➕**
