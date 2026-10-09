package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.prefs.Preferences;

import dao.UserDAO;
import model.User;

public class LoginFrame extends JFrame {


private JTextField emailField;
private JPasswordField passwordField;
private JCheckBox rememberMe;
private JButton loginButton;
private JButton registerButton;

private UserDAO userDAO;

// Used to remember the user's email safely on this computer
private Preferences preferences;

// PetConnect colors
private final Color PRIMARY = new Color(124, 92, 166);
private final Color BACKGROUND = new Color(250, 247, 252);
private final Color DARK_TEXT = new Color(55, 50, 60);
private final Color LIGHT_PURPLE = new Color(242, 235, 248);

public LoginFrame() {

    userDAO = new UserDAO();

    // Preferences stores only the remembered email
    preferences = Preferences.userNodeForPackage(LoginFrame.class);

    setTitle("PetConnect - Login");
    setSize(700, 550);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    // Main background
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
        "Find a friend. Give a home."
    );
    subtitleLabel.setFont(
        new Font("Arial", Font.ITALIC, 16)
    );
    subtitleLabel.setForeground(Color.WHITE);
    subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    headerPanel.add(logoLabel);
    headerPanel.add(Box.createVerticalStrut(3));
    headerPanel.add(titleLabel);
    headerPanel.add(Box.createVerticalStrut(3));
    headerPanel.add(subtitleLabel);

    // =========================
    // FORM PANEL
    // =========================

    JPanel formPanel = new JPanel();
    formPanel.setLayout(
        new BoxLayout(formPanel, BoxLayout.Y_AXIS)
    );
    formPanel.setBackground(BACKGROUND);

    // Fixed width prevents the form from stretching
    // across the entire window.
    formPanel.setPreferredSize(new Dimension(500, 335));
    formPanel.setMaximumSize(new Dimension(500, 335));
    formPanel.setMinimumSize(new Dimension(500, 335));

    formPanel.setBorder(
        new EmptyBorder(20, 35, 15, 35)
    );

    JLabel welcomeLabel = new JLabel(
        "Welcome Back!"
    );
    welcomeLabel.setFont(
        new Font("Arial", Font.BOLD, 24)
    );
    welcomeLabel.setForeground(DARK_TEXT);
    welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel instructionLabel = new JLabel(
        "Login to continue to PetConnect"
    );
    instructionLabel.setFont(
        new Font("Arial", Font.PLAIN, 15)
    );
    instructionLabel.setForeground(DARK_TEXT);
    instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    // =========================
    // EMAIL
    // =========================

    JLabel emailLabel = new JLabel("Email");
    emailLabel.setFont(
        new Font("Arial", Font.BOLD, 16)
    );
    emailLabel.setForeground(DARK_TEXT);
    emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    emailField = new JTextField();
    emailField.setFont(
        new Font("Arial", Font.PLAIN, 17)
    );
    emailField.setPreferredSize(new Dimension(315, 42));
emailField.setMaximumSize(new Dimension(315, 42));
emailField.setAlignmentX(Component.CENTER_ALIGNMENT);emailField.setPreferredSize(new Dimension(315, 42));
emailField.setMaximumSize(new Dimension(315, 42));
emailField.setAlignmentX(Component.CENTER_ALIGNMENT);

    // Load previously remembered email
    String savedEmail =
        preferences.get("rememberedEmail", "");

    emailField.setText(savedEmail);

    // =========================
    // PASSWORD
    // =========================

    JLabel passwordLabel = new JLabel("Password");
    passwordLabel.setFont(
        new Font("Arial", Font.BOLD, 16)
    );
    passwordLabel.setForeground(DARK_TEXT);
    passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    passwordField = new JPasswordField();
    passwordField.setFont(
        new Font("Arial", Font.PLAIN, 17)
    );
    passwordField.setPreferredSize(new Dimension(315, 42));
passwordField.setMaximumSize(new Dimension(315, 42));
passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
    // =========================
    // REMEMBER ME
    // =========================

    rememberMe = new JCheckBox(
        "Remember my email"
    );
    rememberMe.setFont(
        new Font("Arial", Font.PLAIN, 14)
    );
    rememberMe.setForeground(DARK_TEXT);
    rememberMe.setBackground(BACKGROUND);
    rememberMe.setFocusPainted(false);
    rememberMe.setAlignmentX(Component.CENTER_ALIGNMENT);

    // If an email was previously saved, check the box
    if (!savedEmail.isEmpty()) {
        rememberMe.setSelected(true);
    }

    // =========================
    // LOGIN BUTTON
    // =========================

    loginButton = new JButton("Login");
    loginButton.setFont(
        new Font("Arial", Font.BOLD, 17)
    );
    loginButton.setForeground(Color.WHITE);
    loginButton.setBackground(PRIMARY);
    loginButton.setFocusPainted(false);
    loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    loginButton.setMaximumSize(
        new Dimension(315, 45)
    );

    // =========================
    // REGISTER BUTTON
    // =========================

    registerButton = new JButton(
        "Create New Account"
    );
    registerButton.setFont(
        new Font("Arial", Font.PLAIN, 15)
    );
    registerButton.setForeground(PRIMARY);
    registerButton.setBackground(LIGHT_PURPLE);
    registerButton.setFocusPainted(false);
    registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    registerButton.setMaximumSize(
        new Dimension(315, 40)
    );

    // =========================
    // ADD COMPONENTS
    // =========================

    formPanel.add(welcomeLabel);
    formPanel.add(Box.createVerticalStrut(4));
    formPanel.add(instructionLabel);

    formPanel.add(Box.createVerticalStrut(18));

    formPanel.add(emailLabel);
    formPanel.add(Box.createVerticalStrut(5));
    formPanel.add(emailField);

    formPanel.add(Box.createVerticalStrut(12));

    formPanel.add(passwordLabel);
    formPanel.add(Box.createVerticalStrut(5));
    formPanel.add(passwordField);

    formPanel.add(Box.createVerticalStrut(5));

    formPanel.add(rememberMe);

    formPanel.add(Box.createVerticalStrut(12));

    formPanel.add(loginButton);

    formPanel.add(Box.createVerticalStrut(8));

    formPanel.add(registerButton);

    // =========================
    // CENTER FORM
    // =========================

    JPanel centerPanel = new JPanel(
        new FlowLayout(
            FlowLayout.CENTER,
            0,
            10
        )
    );
    centerPanel.setBackground(BACKGROUND);
    centerPanel.add(formPanel);

    // =========================
    // FOOTER
    // =========================

    JLabel footerLabel = new JLabel(
        "Every pet deserves a loving home ",
        SwingConstants.CENTER
    );
    footerLabel.setFont(
        new Font("Arial", Font.ITALIC, 13)
    );
    footerLabel.setForeground(DARK_TEXT);

    footerLabel.setBorder(
        BorderFactory.createEmptyBorder(
            3, 5, 12, 5
        )
    );

    // =========================
    // ADD EVERYTHING
    // =========================

    mainPanel.add(
        headerPanel,
        BorderLayout.NORTH
    );

    mainPanel.add(
        centerPanel,
        BorderLayout.CENTER
    );

    mainPanel.add(
        footerLabel,
        BorderLayout.SOUTH
    );

    add(mainPanel);

    // =========================
    // LOGIN BUTTON
    // =========================

    loginButton.addActionListener(e -> {

        String email =
            emailField.getText().trim();

        String password =
            new String(
                passwordField.getPassword()
            );

        if (email.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                this,
                "Please enter both your email and password to continue.",
                "Missing Information",
                JOptionPane.WARNING_MESSAGE
            );

        } else {

            User user =
                userDAO.loginUser(
                    email,
                    password
                );

            if (user != null) {

                // Remember only the email
                if (rememberMe.isSelected()) {

                    preferences.put(
                        "rememberedEmail",
                        email
                    );

                } else {

                    preferences.remove(
                        "rememberedEmail"
                    );
                }

                new DashboardFrame(
                    user.getName(),
                    user.getId()
                ).setVisible(true);

                dispose();

            } else {

                JOptionPane.showMessageDialog(
                    this,
                    "The email or password you entered is incorrect.\n"
                    + "Please check your details and try again.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    });

    // =========================
    // REGISTER BUTTON
    // =========================

    registerButton.addActionListener(e -> {

        new RegisterFrame().setVisible(true);

        dispose();
    });
}

public static void main(String[] args) {

    SwingUtilities.invokeLater(() -> {

        LoginFrame frame =
            new LoginFrame();

        frame.setVisible(true);
    });
}


}
