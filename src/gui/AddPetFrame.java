package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import model.Pet;
import service.PetManager;
import dao.PetDAO;

public class AddPetFrame extends JFrame {

    private JTextField nameField;
    private JTextField typeField;
    private JTextField breedField;
    private JTextField ageField;

    private PetManager petManager;
    private PetDAO petDAO;
    private int userId;

    // PetConnect colors
    private final Color PRIMARY = new Color(124, 92, 166);
    private final Color BACKGROUND = new Color(250, 247, 252);
    private final Color CARD_WHITE = Color.WHITE;
    private final Color DARK_TEXT = new Color(55, 50, 60);
    private final Color LIGHT_TEXT = new Color(110, 105, 115);

    public AddPetFrame(PetManager petManager, int userId) {

    this.petManager = petManager;
    this.userId = userId;
    this.petDAO = new PetDAO();

        setTitle("PetConnect - Add Pet");
        setSize(650, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
            new EmptyBorder(25, 30, 25, 30)
        );

        JLabel titleLabel = new JLabel(
            "🐾  Add a Pet for Adoption"
        );

        titleLabel.setFont(
            new Font("Arial", Font.BOLD, 28)
        );

        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(
            Component.CENTER_ALIGNMENT
        );

        JLabel subtitleLabel = new JLabel(
            "Help a pet find a loving new home"
        );

        subtitleLabel.setFont(
            new Font("Arial", Font.PLAIN, 14)
        );

        subtitleLabel.setForeground(Color.WHITE);
        subtitleLabel.setAlignmentX(
            Component.CENTER_ALIGNMENT
        );

        headerPanel.add(titleLabel);
        headerPanel.add(
            Box.createVerticalStrut(6)
        );
        headerPanel.add(subtitleLabel);

        // =========================
        // FORM CARD
        // =========================

        JPanel cardPanel = new JPanel();

        cardPanel.setLayout(
            new BoxLayout(
                cardPanel,
                BoxLayout.Y_AXIS
            )
        );

        cardPanel.setBackground(CARD_WHITE);

        cardPanel.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(230, 225, 235),
                    1
                ),
                new EmptyBorder(
                    25, 35, 25, 35
                )
            )
        );

        // =========================
        // INTRODUCTION
        // =========================

        JLabel formTitle = new JLabel(
            "Pet Information"
        );

        formTitle.setFont(
            new Font("Arial", Font.BOLD, 20)
        );

        formTitle.setForeground(DARK_TEXT);
        formTitle.setAlignmentX(
            Component.LEFT_ALIGNMENT
        );

        JLabel formDescription = new JLabel(
            "Enter the details of the pet you want to list."
        );

        formDescription.setFont(
            new Font("Arial", Font.PLAIN, 13)
        );

        formDescription.setForeground(LIGHT_TEXT);
        formDescription.setAlignmentX(
            Component.LEFT_ALIGNMENT
        );

        cardPanel.add(formTitle);

        cardPanel.add(
            Box.createVerticalStrut(5)
        );

        cardPanel.add(formDescription);

        cardPanel.add(
            Box.createVerticalStrut(20)
        );

        // =========================
        // FIELDS
        // =========================

        nameField = createTextField();
        typeField = createTextField();
        breedField = createTextField();
        ageField = createTextField();

        cardPanel.add(
            createField(
                "Pet Name",
                "e.g. Bruno",
                nameField
            )
        );

        cardPanel.add(
            Box.createVerticalStrut(15)
        );

        cardPanel.add(
            createField(
                "Pet Type",
                "e.g. Dog, Cat, Rabbit",
                typeField
            )
        );

        cardPanel.add(
            Box.createVerticalStrut(15)
        );

        cardPanel.add(
            createField(
                "Breed",
                "e.g. Labrador, Persian",
                breedField
            )
        );

        cardPanel.add(
            Box.createVerticalStrut(15)
        );

        cardPanel.add(
            createField(
                "Age",
                "Age in years",
                ageField
            )
        );

        cardPanel.add(
            Box.createVerticalStrut(25)
        );

        // =========================
        // BUTTONS
        // =========================

        JPanel buttonPanel =
            new JPanel(new FlowLayout(
                FlowLayout.RIGHT,
                10,
                0
            ));

        buttonPanel.setOpaque(false);
        buttonPanel.setAlignmentX(
            Component.LEFT_ALIGNMENT
        );

        JButton cancelButton =
            new JButton("Cancel");

        cancelButton.setFont(
            new Font("Arial", Font.BOLD, 14)
        );

        cancelButton.setForeground(PRIMARY);
        cancelButton.setBackground(Color.WHITE);
        cancelButton.setFocusPainted(false);

        cancelButton.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    PRIMARY,
                    1
                ),
                new EmptyBorder(
                    10, 20, 10, 20
                )
            )
        );

        JButton addButton =
            new JButton("Add Pet  🐾");

        addButton.setFont(
            new Font("Arial", Font.BOLD, 14)
        );

        addButton.setForeground(Color.WHITE);
        addButton.setBackground(PRIMARY);
        addButton.setFocusPainted(false);

        addButton.setBorder(
            new EmptyBorder(
                11, 22, 11, 22
            )
        );

        buttonPanel.add(cancelButton);
        buttonPanel.add(addButton);

        cardPanel.add(buttonPanel);

        // =========================
        // CENTER AREA
        // =========================

        JPanel centerPanel =
            new JPanel(new GridBagLayout());

        centerPanel.setBackground(BACKGROUND);
        centerPanel.setBorder(
            new EmptyBorder(
                25, 35, 25, 35
            )
        );

        centerPanel.add(cardPanel);

        // =========================
        // FOOTER
        // =========================

        JLabel footerLabel =
            new JLabel(
                "Every pet deserves a loving home ❤️",
                SwingConstants.CENTER
            );

        footerLabel.setFont(
            new Font(
                "Arial",
                Font.ITALIC,
                13
            )
        );

        footerLabel.setForeground(LIGHT_TEXT);

        footerLabel.setBorder(
            new EmptyBorder(
                5, 5, 15, 5
            )
        );

        // =========================
        // ADD TO FRAME
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
        // BUTTON ACTIONS
        // =========================

        cancelButton.addActionListener(
            e -> dispose()
        );

        addButton.addActionListener(e -> {

            String name =
                nameField.getText().trim();

            String type =
                typeField.getText().trim();

            String breed =
                breedField.getText().trim();

            String ageText =
                ageField.getText().trim();

            // Check empty fields
            if (
                name.isEmpty()
                || type.isEmpty()
                || breed.isEmpty()
                || ageText.isEmpty()
            ) {

                JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            try {

                int age =
                    Integer.parseInt(ageText);

                // Check valid age
                if (age < 0) {

                    JOptionPane.showMessageDialog(
                        this,
                        "Age cannot be negative.",
                        "Invalid Age",
                        JOptionPane.WARNING_MESSAGE
                    );

                    return;
                }

                // ID generated by MySQL
                Pet pet = new Pet(
                    0,
                    name,
                    type,
                    breed,
                    age
                );

                // Save pet to MySQL
                boolean success =
    petDAO.addPet(pet, userId);

                if (success) {

                    // Keep it in PetManager
                    petManager.addPet(pet);

                    JOptionPane.showMessageDialog(
                        this,
                        "🐾 " + pet.getName()
                        + " has been added successfully!",
                        "Pet Added",
                        JOptionPane.INFORMATION_MESSAGE
                    );

                    // Clear fields
                    nameField.setText("");
                    typeField.setText("");
                    breedField.setText("");
                    ageField.setText("");

                } else {

                    JOptionPane.showMessageDialog(
                        this,
                        "Failed to add pet to database.",
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                    this,
                    "Age must be a valid number.",
                    "Invalid Age",
                    JOptionPane.WARNING_MESSAGE
                );
            }
        });
    }

    // =========================
    // CREATE TEXT FIELD
    // =========================

    private JTextField createTextField() {

        JTextField field =
            new JTextField();

        field.setFont(
            new Font("Arial", Font.PLAIN, 15)
        );

        field.setForeground(DARK_TEXT);
        field.setBackground(Color.WHITE);

        field.setMaximumSize(
            new Dimension(
                Integer.MAX_VALUE,
                42
            )
        );

        field.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(220, 215, 225),
                    1
                ),
                new EmptyBorder(
                    8, 12, 8, 12
                )
            )
        );

        return field;
    }

    // =========================
    // CREATE FORM FIELD
    // =========================

    private JPanel createField(
        String labelText,
        String hint,
        JTextField field
    ) {

        JPanel panel =
            new JPanel();

        panel.setLayout(
            new BoxLayout(
                panel,
                BoxLayout.Y_AXIS
            )
        );

        panel.setOpaque(false);

        panel.setAlignmentX(
            Component.LEFT_ALIGNMENT
        );

        JLabel label =
            new JLabel(labelText);

        label.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                14
            )
        );

        label.setForeground(DARK_TEXT);

        JLabel hintLabel =
            new JLabel(hint);

        hintLabel.setFont(
            new Font(
                "Arial",
                Font.PLAIN,
                11
            )
        );

        hintLabel.setForeground(LIGHT_TEXT);

        label.setAlignmentX(
            Component.LEFT_ALIGNMENT
        );

        hintLabel.setAlignmentX(
            Component.LEFT_ALIGNMENT
        );

        field.setAlignmentX(
            Component.LEFT_ALIGNMENT
        );

        panel.add(label);

        panel.add(
            Box.createVerticalStrut(2)
        );

        panel.add(hintLabel);

        panel.add(
            Box.createVerticalStrut(6)
        );

        panel.add(field);

        return panel;
    }
}