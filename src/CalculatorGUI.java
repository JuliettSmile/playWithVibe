import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javazoom.jl.player.Player;
import java.io.InputStream;

public class CalculatorGUI extends JFrame implements ActionListener {
    private JTextField display;
    private StringBuilder currentInput;
    private double firstOperand = 0;
    private String operator = "";
    private boolean startNewInput = true;
    private Calculator calculator;

    public CalculatorGUI() {
        calculator = new Calculator();
        currentInput = new StringBuilder();
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 520);
        setResizable(false);

        // Main panel with vertical layout and background color
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(245, 245, 255)); // light pastel background
        mainPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        // Add duck image at the top
        try {
            ImageIcon duckIcon = new ImageIcon(getClass().getResource("/resources/duck.png"));
            Image duckImg = duckIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            JLabel duckLabel = new JLabel(new ImageIcon(duckImg));
            duckLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainPanel.add(duckLabel);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            setIconImage(duckImg);
        } catch (Exception e) {
            // If duck image not found, ignore
        }

        // Stylish display
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Consolas", Font.BOLD, 28));
        display.setBackground(new Color(255, 255, 230));
        display.setForeground(new Color(40, 40, 40));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 2),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        display.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        mainPanel.add(display);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Stylish button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));
        buttonPanel.setBackground(new Color(245, 245, 255));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Segoe UI", Font.BOLD, 22));
            button.setFocusPainted(false);
            button.setBackground(new Color(255, 255, 255));
            button.setForeground(new Color(60, 60, 60));
            button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
            // Rounded corners
            button.setContentAreaFilled(false);
            button.setOpaque(true);
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button.setBackground(new Color(220, 240, 255));
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button.setBackground(Color.WHITE);
                }
            });
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        mainPanel.add(buttonPanel);
        setContentPane(mainPanel);
        setVisible(true);
    }

    // Utility method to play MP3 sound
    private void playSound(String resourcePath) {
        new Thread(() -> {
            try (InputStream is = getClass().getResourceAsStream(resourcePath)) {
                if (is != null) {
                    Player player = new Player(is);
                    player.play();
                }
            } catch (Exception e) {
                // Ignore sound errors
            }
        }).start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        playSound("/resources/pig-grunt-100272.mp3");
        String cmd = e.getActionCommand();
        if (cmd.matches("[0-9]")) {
            if (startNewInput) {
                currentInput.setLength(0);
                startNewInput = false;
            }
            currentInput.append(cmd);
            display.setText(currentInput.toString());
        } else if (cmd.matches("[+\\-*/]")) {
            if (currentInput.length() > 0) {
                firstOperand = Double.parseDouble(currentInput.toString());
                operator = cmd;
                startNewInput = true;
            }
        } else if (cmd.equals("=")) {
            if (operator.length() > 0 && currentInput.length() > 0) {
                double secondOperand = Double.parseDouble(currentInput.toString());
                double result = 0;
                try {
                    switch (operator) {
                        case "+":
                            result = calculator.add(firstOperand, secondOperand);
                            break;
                        case "-":
                            result = calculator.subtract(firstOperand, secondOperand);
                            break;
                        case "*":
                            result = calculator.multiply(firstOperand, secondOperand);
                            break;
                        case "/":
                            result = calculator.divide(firstOperand, secondOperand);
                            break;
                    }
                    display.setText(Double.toString(result));
                    currentInput = new StringBuilder(Double.toString(result));
                } catch (Exception ex) {
                    display.setText("Error");
                    currentInput.setLength(0);
                }
                operator = "";
                startNewInput = true;
            }
        }
    }
}
