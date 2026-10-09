package gui;

import javax.swing.*;
import java.awt.*;
import dao.UserDAO;

public class RegisterFrame extends JFrame {

    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField addressField;

    private UserDAO userDAO;

    public RegisterFrame() {

        userDAO = new UserDAO();

        setTitle("PetConnect - Registration");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));

        JLabel titleLabel = new JLabel("Create Your PetConnect Account");
        JLabel nameLabel = new JLabel("Name:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel addressLabel = new JLabel("Address:");

        nameField = new JTextField();
        emailField = new JTextField();
        passwordField = new JPasswordField();
        addressField = new JTextField();

        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back to Login");

        panel.add(titleLabel);
        panel.add(new JLabel(""));

        panel.add(nameLabel);
        panel.add(nameField);

        panel.add(emailLabel);
        panel.add(emailField);

        panel.add(passwordLabel);
        panel.add(passwordField);

        panel.add(addressLabel);
        panel.add(addressField);

        panel.add(registerButton);
        panel.add(backButton);

        add(panel);

        registerButton.addActionListener(e -> {

            String name = nameField.getText();
            String email = emailField.getText();
            String password =
                new String(passwordField.getPassword());
            String address = addressField.getText();

            if (name.isEmpty() || email.isEmpty()
                    || password.isEmpty() || address.isEmpty()) {

                JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields."
                );

                return;
            }

            boolean success = userDAO.registerUser(
                name,
                email,
                password,
                address
            );

            if (success) {

                JOptionPane.showMessageDialog(
                    this,
                    "Registration successful!"
                );

                nameField.setText("");
                emailField.setText("");
                passwordField.setText("");
                addressField.setText("");

            } else {

                JOptionPane.showMessageDialog(
                    this,
                    "Registration failed. Email may already exist."
                );
            }
        });

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