import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    // Create the frame
    private JFrame frame;
    private JTextField textField;

    // Variables to hold operands and results
    private double num1, num2, result;
    private String operator;

    // Constructor to initialize the calculator
    public Calculator() {
        frame = new JFrame("Calculator");
        textField = new JTextField();
        num1 = num2 = result = 0;
        operator = "";

        // Set up the frame layout
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Set the text field at the top
        frame.add(textField, BorderLayout.NORTH);
        textField.setEditable(false);
        textField.setFont(new Font("Arial", Font.BOLD, 24));

        // Create the panel for the buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        // Buttons for digits and operators
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        // Add buttons to the panel
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 24));
            panel.add(button);
            button.addActionListener(new ButtonClickListener());
        }

        // Add the panel to the frame
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // Button click listener class
    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String text = source.getText();

            // Perform actions based on the button clicked
            if ("0123456789.".contains(text)) {
                textField.setText(textField.getText() + text);
            } else if (text.equals("=")) {
                num2 = Double.parseDouble(textField.getText());
                calculate();
                textField.setText(String.valueOf(result));
                num1 = result;
                operator = "";
            } else {
                if (!operator.isEmpty()) {
                    return; // Ignore if another operator is pressed before calculation
                }
                operator = text;
                num1 = Double.parseDouble(textField.getText());
                textField.setText("");
            }
        }

        private void calculate() {
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        textField.setText("Error");
                        result = 0;
                    }
                    break;
                default:
                    break;
            }
        }
    }

    // Main method to launch the calculator
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Calculator();
            }
        });
    }
}


