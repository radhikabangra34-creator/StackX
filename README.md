# STACKX – Stack Based Expression Evaluation and History Management System

## 1. Project Overview

STACKX is a Java-based console application that demonstrates the practical implementation of the **Stack Data Structure**.

The project evaluates arithmetic expressions using stacks and stores the evaluated expressions along with their results in a **MySQL database** using **JDBC**.

The main purpose of this project is to understand how Stack data structures can be used to solve a real-world problem such as expression evaluation.

---

## 2. Objective

The main objectives of STACKX are:

- To implement a Stack data structure using an array.
- To understand Stack operations such as Push, Pop and Peek.
- To evaluate arithmetic expressions using stacks.
- To implement operator precedence.
- To handle parentheses in expressions.
- To connect a Java application with MySQL using JDBC.
- To store and retrieve expression history from a database.
- To demonstrate the practical application of Data Structures and Algorithms.

---

## 3. Technologies Used

| Technology | Purpose |
|------------|---------|
| Java | Application development |
| Stack | Expression evaluation |
| MySQL | Storing expression history |
| JDBC | Java-MySQL connectivity |
| IntelliJ IDEA | Development environment |

---

## 4. Features

STACKX provides the following features:

1. Custom Stack implementation using an array.
2. Push operation.
3. Pop operation.
4. Peek operation.
5. Stack Overflow handling.
6. Stack Underflow handling.
7. Arithmetic expression evaluation.
8. Support for:
    - Addition `+`
    - Subtraction `-`
    - Multiplication `*`
    - Division `/`
9. Operator precedence handling.
10. Parentheses support.
11. Multiple digit numbers support.
12. Division by zero handling.
13. Expression history storage.
14. View previous expression history.
15. Clear expression history.
16. Menu-driven console interface.

---

## 5. Project Structure

```text
STACKX
│
├── src
│   ├── Main.java
│   ├── Stack.java
│   ├── ExpressionEvaluator.java
│   └── DatabaseManager.java
│
└── README.md