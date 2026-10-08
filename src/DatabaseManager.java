import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseManager {

    private static final String URL =
            "jdbc:mysql://localhost:3306/stackx_db";

    private static final String USER = "root";

    private static final String PASSWORD =
            "Radhika*123";

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

        } catch (Exception e) {

            System.out.println("Database connection failed!");
            e.printStackTrace();

            return null;
        }
    }

    public static void saveExpression(String expression, int result) {

        String sql =
                "INSERT INTO expression_history (expression, result) VALUES (?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, expression);
            statement.setInt(2, result);

            statement.executeUpdate();

            System.out.println("Expression saved successfully!");

        } catch (Exception e) {

            System.out.println("Failed to save expression!");
            e.printStackTrace();
        }
    }

    public static void showHistory() {

        String sql = "SELECT * FROM expression_history";

        try (Connection connection = getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet =
                     statement.executeQuery()) {

            System.out.println("\n========== HISTORY ==========");

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String expression = resultSet.getString("expression");
                int result = resultSet.getInt("result");

                System.out.println(
                        id + " | " + expression + " = " + result
                );
            }

            System.out.println("=============================");

        } catch (Exception e) {

            System.out.println("Failed to load history!");
            e.printStackTrace();
        }
    }

    public static void clearHistory() {

        String sql = "DELETE FROM expression_history";

        try (Connection connection = getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            int rows = statement.executeUpdate();

            System.out.println(rows + " history records deleted.");

        } catch (Exception e) {

            System.out.println("Failed to clear history!");
            e.printStackTrace();
        }
    }
}