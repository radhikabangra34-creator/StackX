import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StackXGUI extends JFrame {

    private JTextField expressionField;
    private JLabel resultLabel;

    public StackXGUI() {

        setTitle("STACKX - Stack Based Calculator");
        setSize(700, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(
                new GridLayout(6, 1, 10, 10)
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        30, 50, 30, 50
                )
        );

        // Title
        JLabel title = new JLabel(
                "STACKX",
                SwingConstants.CENTER
        );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        32
                )
        );

        // Subtitle
        JLabel subtitle = new JLabel(
                "Stack Based Expression Evaluator",
                SwingConstants.CENTER
        );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        // Expression input
        expressionField = new JTextField();

        expressionField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        20
                )
        );

        // Calculate button
        JButton calculateButton =
                new JButton("Calculate");

        calculateButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        // View History button
        JButton historyButton =
                new JButton("View History");

        historyButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        // Result
        resultLabel = new JLabel(
                "Result: ",
                SwingConstants.CENTER
        );

        resultLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        // Add components
        mainPanel.add(title);
        mainPanel.add(subtitle);
        mainPanel.add(expressionField);
        mainPanel.add(calculateButton);
        mainPanel.add(resultLabel);
        mainPanel.add(historyButton);

        add(mainPanel);

        // =========================
        // Calculate Button
        // =========================

        calculateButton.addActionListener(e -> {

            String expression =
                    expressionField.getText();

            try {

                ExpressionEvaluator evaluator =
                        new ExpressionEvaluator();

                int result =
                        evaluator.evaluate(expression);

                // Show result
                resultLabel.setText(
                        "Result: " + result
                );

                // Save to database
                DatabaseManager.saveExpression(
                        expression,
                        result
                );

            } catch (Exception ex) {

                resultLabel.setText(
                        "Invalid expression!"
                );
            }
        });

        // =========================
        // History Button
        // =========================

        historyButton.addActionListener(e -> {

            showHistoryWindow();

        });
    }

    // =========================
    // History Window
    // =========================

    private void showHistoryWindow() {

        JFrame historyFrame =
                new JFrame("STACKX - History");

        historyFrame.setSize(600, 400);

        historyFrame.setLocationRelativeTo(this);

        JTextArea historyArea =
                new JTextArea();

        historyArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        16
                )
        );

        historyArea.setEditable(false);

        try {

            Connection connection =
                    DatabaseManager.getConnection();

            String sql =
                    "SELECT * FROM expression_history";

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            ResultSet resultSet =
                    statement.executeQuery();

            historyArea.append(
                    "ID\tExpression\tResult\n"
            );

            historyArea.append(
                    "----------------------------------------\n"
            );

            while (resultSet.next()) {

                int id =
                        resultSet.getInt("id");

                String expression =
                        resultSet.getString("expression");

                int result =
                        resultSet.getInt("result");

                historyArea.append(
                        id + "\t"
                                + expression + "\t"
                                + result + "\n"
                );
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception ex) {

            historyArea.setText(
                    "Unable to load history!"
            );
        }

        JScrollPane scrollPane =
                new JScrollPane(historyArea);

        historyFrame.add(scrollPane);

        historyFrame.setVisible(true);
    }

    // =========================
    // Main Method
    // =========================

    public static void main(String[] args) {

        StackXGUI gui =
                new StackXGUI();

        gui.setVisible(true);
    }
}