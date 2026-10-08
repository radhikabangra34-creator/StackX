public class Stack {

    private int[] arr;
    private int top;

    // Constructor: creates stack with given size
    public Stack(int size) {
        arr = new int[size];
        top = -1;
    }

    // Adds an element to the top of the stack
    public void push(int value) {

        if (isFull()) {
            System.out.println("Stack Overflow");
            return;
        }

        arr[++top] = value;
    }

    // Removes and returns the top element
    public int pop() {

        if (isEmpty()) {
            System.out.println("Stack Underflow");
            return -1;
        }

        return arr[top--];
    }

    // Returns the top element without removing it
    public int peek() {

        if (isEmpty()) {
            System.out.println("Stack is Empty");
            return -1;
        }

        return arr[top];
    }

    // Checks whether the stack is empty
    public boolean isEmpty() {
        return top == -1;
    }

    // Checks whether the stack is full
    public boolean isFull() {
        return top == arr.length - 1;
    }
}