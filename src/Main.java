import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ExpressionEvaluator evaluator = new ExpressionEvaluator();

        while (true) {

            System.out.println("\n=================================");
            System.out.println("            STACKX");
            System.out.println("    STACK BASED CALCULATOR");
            System.out.println("=================================");
            System.out.println("1. Evaluate Expression");
            System.out.println("2. View History");
            System.out.println("3. Clear History");
            System.out.println("4. Exit");
            System.out.println("=================================");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter expression: ");
                    String expression = sc.nextLine();

                    try {

                        int result = evaluator.evaluate(expression);

                        System.out.println("\n------------------------------");
                        System.out.println("Expression : " + expression);
                        System.out.println("Result     : " + result);
                        System.out.println("------------------------------");

                        DatabaseManager.saveExpression(
                                expression,
                                result
                        );

                    } catch (Exception e) {

                        System.out.println("Invalid expression!");
                    }

                    break;

                case 2:

                    DatabaseManager.showHistory();
                    break;

                case 3:

                    DatabaseManager.clearHistory();
                    break;

                case 4:

                    System.out.println("\nThank you for using STACKX!");
                    sc.close();
                    return;

                default:

                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}