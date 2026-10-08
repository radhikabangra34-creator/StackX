public class ExpressionEvaluator {

    public int evaluate(String expression) {

        if (!isValidExpression(expression)) {
            System.out.println("Invalid expression!");
            return 0;
        }

        Stack numbers = new Stack(100);
        Stack operators = new Stack(100);

        for (int i = 0; i < expression.length(); i++) {

            char ch = expression.charAt(i);

            // Ignore spaces
            if (ch == ' ') {
                continue;
            }

            // Number
            if (Character.isDigit(ch)) {

                int number = 0;

                while (i < expression.length()
                        && Character.isDigit(expression.charAt(i))) {

                    number = number * 10
                            + (expression.charAt(i) - '0');

                    i++;
                }

                i--;
                numbers.push(number);
            }

            // Opening bracket
            else if (ch == '(') {

                operators.push(ch);
            }

            // Closing bracket
            else if (ch == ')') {

                while (!operators.isEmpty()
                        && operators.peek() != '(') {

                    applyOperation(numbers, operators);
                }

                if (!operators.isEmpty()) {
                    operators.pop();
                }
            }

            // Operator
            else if (isOperator(ch)) {

                while (!operators.isEmpty()
                        && operators.peek() != '('
                        && precedence(operators.peek())
                        >= precedence(ch)) {

                    applyOperation(numbers, operators);
                }

                operators.push(ch);
            }
        }

        // Apply remaining operators
        while (!operators.isEmpty()) {

            applyOperation(numbers, operators);
        }

        return numbers.pop();
    }


    private boolean isValidExpression(String expression) {

        Stack brackets = new Stack(100);

        for (int i = 0; i < expression.length(); i++) {

            char ch = expression.charAt(i);

            if (ch == '(') {

                brackets.push(ch);
            }

            else if (ch == ')') {

                if (brackets.isEmpty()) {
                    return false;
                }

                brackets.pop();
            }
        }

        return brackets.isEmpty();
    }


    private boolean isOperator(int ch) {

        return ch == '+'
                || ch == '-'
                || ch == '*'
                || ch == '/';
    }


    private int precedence(int operator) {

        if (operator == '+' || operator == '-') {
            return 1;
        }

        if (operator == '*' || operator == '/') {
            return 2;
        }

        return 0;
    }


    private void applyOperation(Stack numbers, Stack operators) {

        int b = numbers.pop();
        int a = numbers.pop();

        int operator = operators.pop();

        int result = 0;

        switch (operator) {

            case '+':
                result = a + b;
                break;

            case '-':
                result = a - b;
                break;

            case '*':
                result = a * b;
                break;

            case '/':

                if (b == 0) {
                    throw new ArithmeticException(
                            "Cannot divide by zero"
                    );
                }

                result = a / b;
                break;
        }

        numbers.push(result);
    }
}