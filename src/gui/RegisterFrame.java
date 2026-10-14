
package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import dao.UserDAO;

public class RegisterFrame extends JFrame {

    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField addressField;

    private UserDAO userDAO;

    // PetConnect colors
    private final Color PRIMARY = new Color(124, 92, 166);
    private final Color BACKGROUND = new Color(250, 247, 252);
    private final Color DARK_TEXT = new Color(55, 50, 60);
    private final Color LIGHT_PURPLE = new Color(242, 235, 248);

    public RegisterFrame() {

        userDAO = new UserDAO();

        setTitle("PetConnect - Registration");
        setSize(700, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // =========================
        // MAIN PANEL
        // =========================

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND);

        // =========================
        // HEADER
        // =========================

        JPanel headerPanel = new JPanel();

        headerPanel.setLayout(
            new BoxLayout(headerPanel, BoxLayout.Y_AXIS)
        );

        headerPanel.setBackground(PRIMARY);

        headerPanel.setBorder(
            BorderFactory.createEmptyBorder(25, 20, 25, 20)
        );

        JLabel logoLabel = new JLabel("🐾");

        logoLabel.setFont(
            new Font("Segoe UI Emoji", Font.PLAIN, 42)
        );

        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel("PetConnect");

        titleLabel.setFont(
            new Font("Arial", Font.BOLD, 32)
        );

        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel(
            "Create your account and find a new friend"
        );

        subtitleLabel.setFont(
            new Font("Arial", Font.ITALIC, 15)
        );

        subtitleLabel.setForeground(Color.WHITE);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(logoLabel);
        headerPanel.add(Box.createVerticalStrut(4));
        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(4));
        headerPanel.add(subtitleLabel);

        // =========================
        // FORM PANEL
        // =========================

        JPanel formPanel = new JPanel();

        formPanel.setLayout(
            new BoxLayout(formPanel, BoxLayout.Y_AXIS)
        );

        formPanel.setBackground(BACKGROUND);

        formPanel.setBorder(
            new EmptyBorder(25, 100, 20, 100)
        );

        JLabel welcomeLabel = new JLabel(
            "Create Your Account"
        );

        welcomeLabel.setFont(
            new Font("Arial", Font.BOLD, 24)
        );

        welcomeLabel.setForeground(DARK_TEXT);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel instructionLabel = new JLabel(
            "Enter your details to join PetConnect"
        );

        instructionLabel.setFont(
            new Font("Arial", Font.PLAIN, 14)
        );

        instructionLabel.setForeground(DARK_TEXT);
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // =========================
        // NAME
        // =========================

        JLabel nameLabel = new JLabel("Full Name");

        nameLabel.setFont(
            new Font("Arial", Font.BOLD, 15)
        );

        nameLabel.setForeground(DARK_TEXT);
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        nameField = new JTextField();

        nameField.setFont(
            new Font("Arial", Font.PLAIN, 16)
        );

        nameField.setMaximumSize(
            new Dimension(Integer.MAX_VALUE, 42)
        );

        // =========================
        // EMAIL
        // =========================

        JLabel emailLabel = new JLabel("Email");

        emailLabel.setFont(
            new Font("Arial", Font.BOLD, 15)
        );

        emailLabel.setForeground(DARK_TEXT);
        emailLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        emailField = new JTextField();

        emailField.setFont(
            new Font("Arial", Font.PLAIN, 16)
        );

        emailField.setMaximumSize(
            new Dimension(Integer.MAX_VALUE, 42)
        );

        // =========================
        // PASSWORD
        // =========================

        JLabel passwordLabel = new JLabel("Password");

        passwordLabel.setFont(
            new Font("Arial", Font.BOLD, 15)
        );

        passwordLabel.setForeground(DARK_TEXT);
        passwordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        passwordField = new JPasswordField();

        passwordField.setFont(
            new Font("Arial", Font.PLAIN, 16)
        );

        passwordField.setMaximumSize(
            new Dimension(Integer.MAX_VALUE, 42)
        );

        // =========================
        // ADDRESS
        // =========================

        JLabel addressLabel = new JLabel("Address");

        addressLabel.setFont(
            new Font("Arial", Font.BOLD, 15)
        );

        addressLabel.setForeground(DARK_TEXT);
        addressLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        addressField = new JTextField();

        addressField.setFont(
            new Font("Arial", Font.PLAIN, 16)
        );

        addressField.setMaximumSize(
            new Dimension(Integer.MAX_VALUE, 42)
        );

        // =========================
        // REGISTER BUTTON
        // =========================

        JButton registerButton = new JButton(
            "Create Account"
        );

        registerButton.setFont(
            new Font("Arial", Font.BOLD, 16)
        );

        registerButton.setForeground(Color.WHITE);
        registerButton.setBackground(PRIMARY);
        registerButton.setFocusPainted(false);

        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        registerButton.setMaximumSize(
            new Dimension(250, 45)
        );

        // =========================
        // BACK BUTTON
        // =========================

        JButton backButton = new JButton(
            "Back to Login"
        );

        backButton.setFont(
            new Font("Arial", Font.PLAIN, 14)
        );

        backButton.setForeground(PRIMARY);
        backButton.setBackground(LIGHT_PURPLE);
        backButton.setFocusPainted(false);

        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        backButton.setMaximumSize(
            new Dimension(250, 40)
        );

        // =========================
        // ADD COMPONENTS
        // =========================

        formPanel.add(welcomeLabel);

        formPanel.add(
            Box.createVerticalStrut(4)
        );

        formPanel.add(instructionLabel);

        formPanel.add(
            Box.createVerticalStrut(20)
        );

        formPanel.add(nameLabel);

        formPanel.add(
            Box.createVerticalStrut(6)
        );

        formPanel.add(nameField);

        formPanel.add(
            Box.createVerticalStrut(13)
        );

        formPanel.add(emailLabel);

        formPanel.add(
            Box.createVerticalStrut(6)
        );

        formPanel.add(emailField);

        formPanel.add(
            Box.createVerticalStrut(13)
        );

        formPanel.add(passwordLabel);

        formPanel.add(
            Box.createVerticalStrut(6)
        );

        formPanel.add(passwordField);

        formPanel.add(
            Box.createVerticalStrut(13)
        );

        formPanel.add(addressLabel);

        formPanel.add(
            Box.createVerticalStrut(6)
        );

        formPanel.add(addressField);

        formPanel.add(
            Box.createVerticalStrut(20)
        );

        formPanel.add(registerButton);

        formPanel.add(
            Box.createVerticalStrut(10)
        );

        formPanel.add(backButton);

        // =========================
        // FOOTER
        // =========================

        JLabel footerLabel = new JLabel(
            "Every pet deserves a loving home ❤️",
            SwingConstants.CENTER
        );

        footerLabel.setFont(
            new Font("Arial", Font.ITALIC, 13)
        );

        footerLabel.setForeground(DARK_TEXT);

        footerLabel.setBorder(
            BorderFactory.createEmptyBorder(
                5, 5, 15, 5
            )
        );

        // =========================
        // ADD TO MAIN PANEL
        // =========================

        mainPanel.add(
            headerPanel,
            BorderLayout.NORTH
        );

        mainPanel.add(
            formPanel,
            BorderLayout.CENTER
        );

        mainPanel.add(
            footerLabel,
            BorderLayout.SOUTH
        );

        add(mainPanel);

        // =========================
        // REGISTER ACTION
        // =========================

        registerButton.addActionListener(e -> {

            String name =
                nameField.getText().trim();

            String email =
                emailField.getText().trim();

            String password =
                new String(
                    passwordField.getPassword()
                );

            String address =
                addressField.getText().trim();

            if (name.isEmpty()
                    || email.isEmpty()
                    || password.isEmpty()
                    || address.isEmpty()) {

                JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            boolean success =
                userDAO.registerUser(
                    name,
                    email,
                    password,
                    address
                );

            if (success) {

                JOptionPane.showMessageDialog(
                    this,
                    "Registration successful!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
                );

                nameField.setText("");
                emailField.setText("");
                passwordField.setText("");
                addressField.setText("");

            } else {

                JOptionPane.showMessageDialog(
                    this,
                    "Registration failed. Email may already exist.",
                    "Registration Failed",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // =========================
        // BACK TO LOGIN
        // =========================

        backButton.addActionListener(e -> {

            new LoginFrame().setVisible(true);
            dispose();

        });
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new RegisterFrame().setVisible(true);

        });
    }
}
