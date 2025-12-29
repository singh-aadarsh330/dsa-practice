# 🛡️ Exception Handling

[![Problems](https://img.shields.io/badge/Problems-1-blue?style=flat-square)](.)
[![Difficulty](https://img.shields.io/badge/Difficulty-Easy%20to%20Medium-orange?style=flat-square)](.)
[![Language](https://img.shields.io/badge/Language-Java-red?style=flat-square)](.)

> Master error handling and build robust applications. Learn try-catch, exception types, and recovery strategies.

---

## 📋 Overview

**Exception Handling** is a mechanism in Java to handle runtime errors gracefully, allowing programs to continue execution or fail elegantly instead of crashing abruptly. It provides a structured way to detect, handle, and recover from exceptional conditions.

**Key Features:**
- **Error Recovery** - Continue execution after errors
- **Separation of Concerns** - Error handling separate from business logic
- **Structured Approach** - Organized error management
- **Stack Trace** - Detailed error information
- **Resource Management** - Automatic cleanup with try-with-resources

**When to Use Exception Handling:**
- ✅ Dealing with I/O operations (files, network)
- ✅ User input validation
- ✅ Resource management (database, connections)
- ✅ Arithmetic operations (division by zero)
- ✅ Array/collection access
- ❌ Regular control flow (use if-else)
- ❌ Performance-critical code paths

---

## 🔑 Exception Handling Basics

### Exception Hierarchy

```
Throwable (root of exception hierarchy)
│
├── Error (System-level errors - usually don't catch)
│   ├── OutOfMemoryError
│   ├── StackOverflowError
│   └── VirtualMachineError
│
└── Exception (Application-level exceptions)
    │
    ├── RuntimeException (Unchecked Exceptions)
    │   ├── ArithmeticException (division by zero)
    │   ├── NullPointerException (null reference)
    │   ├── ArrayIndexOutOfBoundsException
    │   ├── IllegalArgumentException
    │   └── ClassCastException
    │
    └── Checked Exceptions (must be handled)
        ├── IOException
        │   ├── FileNotFoundException
        │   └── EOFException
        ├── SQLException
        └── ClassNotFoundException
```

### Basic Try-Catch Structure

```java
// Simple try-catch
try {
    // Code that might throw an exception
    int result = 10 / 0;
} catch (ArithmeticException e) {
    // Handle the exception
    System.out.println("Error: " + e.getMessage());
}

// Multiple catch blocks
try {
    // Risky code
} catch (ArithmeticException e) {
    // Handle arithmetic errors
} catch (NullPointerException e) {
    // Handle null errors
} catch (Exception e) {
    // Handle any other exceptions
}

// Try-catch-finally
try {
    // Risky code
} catch (Exception e) {
    // Handle exception
} finally {
    // Always executes (cleanup code)
}

// Try-with-resources (Java 7+)
try (FileReader fr = new FileReader("file.txt")) {
    // Use resource
} catch (IOException e) {
    // Handle exception
}
// Resource automatically closed
```

### Common Exception Methods

```java
try {
    // Code that throws exception
} catch (Exception e) {
    e.getMessage();           // Get error message
    e.printStackTrace();      // Print stack trace
    e.getCause();            // Get cause of exception
    e.toString();            // String representation
    e.getStackTrace();       // Get stack trace elements
}
```

---

## 📂 Repository Structure

```
ExceptionHandling/
│
├── TryCatchExample/         # Safe arithmetic operations with min finding
│   └── README.md
│
└── README.md                # This file
```

---

## 📝 Problems in This Folder

| # | Problem | Folder | Difficulty | Key Concepts | Status |
|---|---------|--------|------------|--------------|--------|
| 1 | Try-Catch Example | [TryCatchExample/](./TryCatchExample/) | Easy | Division safety, Exception recovery, Min finding | ✅ |

---

## 🎯 Problem: Try-Catch Example

### Problem Overview

Perform four arithmetic operations (division, addition, subtraction, multiplication) on two numbers and return the minimum value, while safely handling division by zero using try-catch.

**Key Concepts:**
- ArithmeticException handling
- Try-catch blocks
- Exception recovery
- Continuing execution after errors

**See detailed solution:** [TryCatchExample/README.md](./TryCatchExample/README.md)

---

## 🔑 Key Exception Handling Concepts

### 1. Checked vs Unchecked Exceptions

| Feature | Checked | Unchecked |
|---------|---------|-----------|
| **Extends** | Exception (not RuntimeException) | RuntimeException |
| **Compile-Time** | Must be declared or caught | Not required |
| **When to Use** | Recoverable conditions | Programming errors |
| **Examples** | IOException, SQLException | NullPointerException, ArithmeticException |
| **Handling** | Mandatory (try-catch or throws) | Optional |

**Checked Exception Example:**
```java
// Must handle or declare
public void readFile() throws IOException {
    FileReader fr = new FileReader("file.txt");
    // ...
}

// Or catch it
public void readFile() {
    try {
        FileReader fr = new FileReader("file.txt");
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

**Unchecked Exception Example:**
```java
// Optional to handle
public int divide(int a, int b) {
    return a / b;  // May throw ArithmeticException
}

// Can catch if needed
public int safeDivide(int a, int b) {
    try {
        return a / b;
    } catch (ArithmeticException e) {
        return 0;
    }
}
```

### 2. Exception Propagation

```java
// Exception propagates up the call stack
public void method1() {
    method2();
}

public void method2() {
    method3();
}

public void method3() {
    int x = 10 / 0;  // Throws ArithmeticException
    // Propagates: method3 → method2 → method1 → JVM
}
```

### 3. Throwing Exceptions

```java
// Throw built-in exception
public void setAge(int age) {
    if (age < 0) {
        throw new IllegalArgumentException("Age cannot be negative");
    }
}

// Throw checked exception
public void processFile(String filename) throws IOException {
    if (filename == null) {
        throw new IOException("Filename cannot be null");
    }
}

// Re-throw exception
public void method() {
    try {
        riskyOperation();
    } catch (Exception e) {
        System.out.println("Logging error");
        throw e;  // Re-throw to caller
    }
}
```

### 4. Custom Exceptions

```java
// Custom checked exception
public class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

// Custom unchecked exception
public class InvalidUserException extends RuntimeException {
    private String userId;
    
    public InvalidUserException(String message, String userId) {
        super(message);
        this.userId = userId;
    }
    
    public String getUserId() {
        return userId;
    }
}

// Usage
public void validateUser(String userId) throws InvalidAgeException {
    if (userId == null) {
        throw new InvalidUserException("User ID is null", userId);
    }
}
```

---

## 💡 Common Exception Handling Patterns

### Pattern 1: Catch and Log
```java
try {
    performOperation();
} catch (Exception e) {
    logger.error("Operation failed: " + e.getMessage(), e);
}
```

### Pattern 2: Catch and Recover
```java
try {
    return primaryMethod();
} catch (PrimaryException e) {
    logger.warn("Primary failed, using fallback");
    return fallbackMethod();
}
```

### Pattern 3: Catch and Wrap
```java
try {
    databaseOperation();
} catch (SQLException e) {
    throw new DataAccessException("Database error", e);
}
```

### Pattern 4: Try-With-Resources
```java
// Automatic resource management
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"));
     BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {
        bw.write(line);
    }
} catch (IOException e) {
    e.printStackTrace();
}
// Resources automatically closed
```

### Pattern 5: Multi-Catch (Java 7+)
```java
try {
    // Operations
} catch (IOException | SQLException e) {
    // Handle both exceptions the same way
    logger.error("Error: " + e.getMessage());
}
```

### Pattern 6: Exception Chaining
```java
try {
    lowLevelOperation();
} catch (LowLevelException e) {
    throw new HighLevelException("Business operation failed", e);
}
```

---

## ⚠️ Common Mistakes

### 1. Empty Catch Blocks
```java
❌ Incorrect:
try {
    riskyOperation();
} catch (Exception e) {
    // Empty - swallows exception silently
}

✅ Correct:
try {
    riskyOperation();
} catch (Exception e) {
    logger.error("Operation failed", e);
    // Or at minimum: e.printStackTrace();
}
```

### 2. Catching Generic Exception
```java
❌ Avoid:
try {
    operation();
} catch (Exception e) {
    // Too broad - catches everything
}

✅ Better:
try {
    operation();
} catch (IOException e) {
    // Specific exception
} catch (SQLException e) {
    // Another specific exception
}
```

### 3. Not Rethrowing Properly
```java
❌ Incorrect:
try {
    operation();
} catch (Exception e) {
    throw new Exception("Error occurred");  // Lost original exception!
}

✅ Correct:
try {
    operation();
} catch (Exception e) {
    throw new Exception("Error occurred", e);  // Chain exceptions
}
```

### 4. Using Exceptions for Flow Control
```java
❌ Incorrect:
try {
    int index = 0;
    while (true) {
        System.out.println(array[index++]);
    }
} catch (ArrayIndexOutOfBoundsException e) {
    // Using exception to exit loop
}

✅ Correct:
for (int i = 0; i < array.length; i++) {
    System.out.println(array[i]);
}
```

### 5. Catching Throwable or Error
```java
❌ Incorrect:
try {
    operation();
} catch (Throwable t) {
    // Catches everything including Errors
}

✅ Correct:
try {
    operation();
} catch (Exception e) {
    // Only catch Exceptions
}
```

---

## 🎓 Exception Handling Best Practices

### 1. Be Specific with Exceptions
```java
// Catch specific exceptions
try {
    readFile();
} catch (FileNotFoundException e) {
    // Handle missing file
} catch (IOException e) {
    // Handle other I/O errors
}
```

### 2. Clean Up in Finally
```java
Connection conn = null;
try {
    conn = getConnection();
    // Use connection
} catch (SQLException e) {
    e.printStackTrace();
} finally {
    if (conn != null) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

### 3. Provide Context in Messages
```java
throw new IllegalArgumentException(
    "Invalid age: " + age + ". Age must be between 0 and 150"
);
```

### 4. Don't Ignore Exceptions
```java
// Always handle or log
try {
    operation();
} catch (Exception e) {
    logger.error("Operation failed for user: " + userId, e);
}
```

### 5. Use Try-With-Resources
```java
// Preferred over manual cleanup
try (FileInputStream fis = new FileInputStream("file.txt")) {
    // Use resource
} catch (IOException e) {
    e.printStackTrace();
}
```

### 6. Document Exceptions
```java
/**
 * Processes the file
 * @param filename the file to process
 * @throws FileNotFoundException if file doesn't exist
 * @throws IOException if error reading file
 */
public void processFile(String filename) 
    throws FileNotFoundException, IOException {
    // Implementation
}
```

---

## 📚 Topics to be Added

As I continue learning, this folder will include:

### **Basic Exception Handling**
- Multiple catch blocks
- Finally block usage
- Try-with-resources
- Exception propagation

### **Custom Exceptions**
- Creating custom exception classes
- Checked vs unchecked custom exceptions
- Exception hierarchies
- Exception metadata

### **Advanced Patterns**
- Retry logic with exceptions
- Circuit breaker pattern
- Exception translation
- Fail-fast vs fail-safe

### **Real-World Scenarios**
- File I/O exception handling
- Database exception handling
- Network exception handling
- API exception handling

---

## 🎯 Learning Path

### Beginner Level
1. **Basic Try-Catch** - Simple exception handling
2. **Common Exceptions** - ArithmeticException, NullPointerException
3. **Finally Block** - Resource cleanup

### Intermediate Level
4. **Multiple Catch Blocks** - Handling different exceptions
5. **Try-With-Resources** - Automatic resource management
6. **Custom Exceptions** - Creating domain exceptions

### Advanced Level
7. **Exception Chaining** - Preserving exception context
8. **Best Practices** - When and how to use exceptions
9. **Design Patterns** - Exception handling strategies

---

## 💻 Essential Exception Handling Reference

### Try-Catch Syntax
```java
try {
    // Code that may throw exception
} catch (SpecificException e) {
    // Handle specific exception
} catch (AnotherException e) {
    // Handle another exception
} catch (Exception e) {
    // Handle any other exception
} finally {
    // Always executes (optional)
}
```

### Throwing Exceptions
```java
throw new ExceptionType("Message");
public void method() throws ExceptionType {
    // Method that may throw exception
}
```

### Try-With-Resources
```java
try (ResourceType resource = new ResourceType()) {
    // Use resource
} catch (ExceptionType e) {
    // Handle exception
}
```

### Common Exception Types

**Unchecked (RuntimeException):**
- `ArithmeticException` - Math errors (e.g., divide by zero)
- `NullPointerException` - Null reference access
- `ArrayIndexOutOfBoundsException` - Invalid array index
- `IllegalArgumentException` - Invalid method argument
- `ClassCastException` - Invalid type cast

**Checked (Exception):**
- `IOException` - I/O operation failed
- `FileNotFoundException` - File not found
- `SQLException` - Database error
- `ClassNotFoundException` - Class not found
- `InterruptedException` - Thread interrupted

---

## 🔄 Exception Handling vs Other Approaches

### When to Use What?

**Use Exception Handling when:**
- Dealing with external resources (files, network)
- Handling errors from libraries/APIs
- Exceptional conditions occur
- Error recovery is possible

**Use Return Codes when:**
- Expected failure conditions
- Performance-critical code
- Simple success/failure checks

**Use Optional when:**
- Value may or may not be present
- Absence of value is not an error
- Functional programming style

**Use Assertions when:**
- Testing invariants during development
- Conditions that should never occur
- Debug-time checks

---

## 📊 Progress Tracker

**Current Status:**
- Topics Covered: 1 (Try-Catch Example)
- Total Problems: 1
- Difficulty: Easy
- Concepts: Basic exception handling, ArithmeticException

**Next Milestones:**
- [ ] Add 3 basic exception handling problems
- [ ] Multiple catch blocks example
- [ ] Try-with-resources examples
- [ ] Custom exception creation
- [ ] File I/O with exception handling

---

## 🎓 Interview Tips

### Common Exception Handling Interview Questions

1. **"Explain checked vs unchecked exceptions"**
   - Checked: Must be handled at compile time (IOException)
   - Unchecked: Runtime exceptions (NullPointerException)
   - Checked for recoverable conditions
   - Unchecked for programming errors

2. **"What is the difference between throw and throws?"**
   - `throw`: Actually throws an exception
   - `throws`: Declares that method may throw exception
   - `throw` is followed by exception object
   - `throws` is followed by exception class name

3. **"When should you create custom exceptions?"**
   - Domain-specific error conditions
   - Need additional context/data
   - Clearer error semantics
   - Better exception handling organization

4. **"Explain try-with-resources"**
   - Automatic resource management (Java 7+)
   - Resources must implement AutoCloseable
   - Resources closed automatically
   - Cleaner than finally blocks

### What Interviewers Look For

- ✅ Understanding of exception hierarchy
- ✅ Appropriate exception type usage
- ✅ Proper resource cleanup
- ✅ Exception chaining knowledge
- ✅ Best practices awareness

---

## 📖 Additional Resources

### Documentation
- [Oracle Exception Handling Guide](https://docs.oracle.com/javase/tutorial/essential/exceptions/)
- [Java Exception Class](https://docs.oracle.com/javase/8/docs/api/java/lang/Exception.html)
- [Best Practices](https://www.oracle.com/technical-resources/articles/java/exceptions.html)

### Books
- "Effective Java" by Joshua Bloch (Chapter on Exceptions)
- "Java Concurrency in Practice" (Exception handling in concurrent code)

### Practice Platforms
- [GeeksforGeeks - Exception Handling](https://www.geeksforgeeks.org/exceptions-in-java/)
- [HackerRank - Java Exception Handling](https://www.hackerrank.com/domains/java)

---

## 🤝 Contributing

As I add more exception handling problems:
- Each problem will demonstrate specific exception types
- Multiple approaches for error handling
- Real-world scenarios
- Best practices and anti-patterns

---

## 💡 Remember

> "Exception handling is not about preventing errors—it's about handling them gracefully when they occur."

**Key Takeaways:**
- Exceptions for exceptional conditions, not control flow
- Always catch specific exceptions when possible
- Clean up resources in finally or try-with-resources
- Provide meaningful error messages
- Know when to catch, when to throw

---

<div align="center">

**Master Exception Handling, Build Robust Applications! 🛡️**

*Safe code is maintainable code*

*Last Updated: December 2024*

[⬆ Back to Java Concepts](../README.md)

</div>
