
package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

import service.AdoptionService;
import model.Pet;
import service.PetManager;
import dao.PetDAO;

public class AdoptPetFrame extends JFrame {

    private PetManager petManager;
    private PetDAO petDAO;
    private AdoptionService adoptionService;
    private JComboBox<String> petBox;

    // Keeps track of the actual pets shown in the combo box
    private ArrayList<Pet> availablePets;

    // PetConnect colors
    private final Color PRIMARY = new Color(124, 92, 166);
    private final Color BACKGROUND = new Color(250, 247, 252);
    private final Color CARD_WHITE = Color.WHITE;
    private final Color DARK_TEXT = new Color(55, 50, 60);
    private final Color LIGHT_TEXT = new Color(110, 105, 115);
    private final Color SUCCESS = new Color(76, 175, 80);

    public AdoptPetFrame(PetManager petManager, int userId) {

        this.petManager = petManager;
        this.petDAO = new PetDAO();
        this.adoptionService = new AdoptionService();

        setTitle("PetConnect - Adopt a Pet");
        setSize(650, 560);
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
            "❤️  Adopt a Pet"
        );

        titleLabel.setFont(
            new Font("Arial", Font.BOLD, 30)
        );

        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(
            Component.CENTER_ALIGNMENT
        );

        JLabel subtitleLabel = new JLabel(
            "Give a loving pet a forever home"
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
        // CONTENT CARD
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
                    30, 35, 30, 35
                )
            )
        );

        // =========================
        // ICON
        // =========================

        JLabel petIcon = new JLabel(
            "🐾",
            SwingConstants.CENTER
        );

        petIcon.setFont(
            new Font(
                "Segoe UI Emoji",
                Font.PLAIN,
                55
            )
        );

        petIcon.setAlignmentX(
            Component.CENTER_ALIGNMENT
        );

        // =========================
        // INFORMATION
        // =========================

        JLabel headingLabel = new JLabel(
            "Choose your new companion"
        );

        headingLabel.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                21
            )
        );

        headingLabel.setForeground(DARK_TEXT);
        headingLabel.setAlignmentX(
            Component.CENTER_ALIGNMENT
        );

        JLabel descriptionLabel = new JLabel(
            "Select one of the available pets below."
        );

        descriptionLabel.setFont(
            new Font(
                "Arial",
                Font.PLAIN,
                13
            )
        );

        descriptionLabel.setForeground(LIGHT_TEXT);
        descriptionLabel.setAlignmentX(
            Component.CENTER_ALIGNMENT
        );

        cardPanel.add(petIcon);

        cardPanel.add(
            Box.createVerticalStrut(10)
        );

        cardPanel.add(headingLabel);

        cardPanel.add(
            Box.createVerticalStrut(5)
        );

        cardPanel.add(descriptionLabel);

        cardPanel.add(
            Box.createVerticalStrut(25)
        );

        // =========================
        // PET SELECTION
        // =========================

        JLabel selectLabel = new JLabel(
            "Available Pets"
        );

        selectLabel.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                14
            )
        );

        selectLabel.setForeground(DARK_TEXT);
        selectLabel.setAlignmentX(
            Component.LEFT_ALIGNMENT
        );

        cardPanel.add(selectLabel);

        cardPanel.add(
            Box.createVerticalStrut(7)
        );

        petBox = new JComboBox<>();

        petBox.setFont(
            new Font(
                "Arial",
                Font.PLAIN,
                15
            )
        );

        petBox.setMaximumSize(
            new Dimension(
                Integer.MAX_VALUE,
                45
            )
        );

        petBox.setAlignmentX(
            Component.LEFT_ALIGNMENT
        );

        // Get pets from database
        ArrayList<Pet> pets =
            petDAO.getAllPets();

        availablePets = new ArrayList<>();

        for (Pet pet : pets) {

            if (!pet.isAdopted()) {

                availablePets.add(pet);

                petBox.addItem(
                    pet.getId()
                    + "  •  "
                    + pet.getName()
                    + "  •  "
                    + pet.getType()
                );
            }
        }

        cardPanel.add(petBox);

        cardPanel.add(
            Box.createVerticalStrut(12)
        );

        // Available count
        JLabel countLabel = new JLabel(
            availablePets.size()
            + " pet(s) currently available"
        );

        countLabel.setFont(
            new Font(
                "Arial",
                Font.PLAIN,
                12
            )
        );

        countLabel.setForeground(
            LIGHT_TEXT
        );

        countLabel.setAlignmentX(
            Component.LEFT_ALIGNMENT
        );

        cardPanel.add(countLabel);

        cardPanel.add(
            Box.createVerticalStrut(25)
        );

        // =========================
        // ADOPT BUTTON
        // =========================

        JButton adoptButton =
            new JButton(
                "❤️  Adopt Now"
            );

        adoptButton.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                16
            )
        );

        adoptButton.setForeground(Color.WHITE);
        adoptButton.setBackground(PRIMARY);
        adoptButton.setFocusPainted(false);

        adoptButton.setAlignmentX(
            Component.CENTER_ALIGNMENT
        );

        adoptButton.setBorder(
            new EmptyBorder(
                12, 35, 12, 35
            )
        );

        cardPanel.add(adoptButton);

        // =========================
        // CENTER AREA
        // =========================

        JPanel centerPanel =
            new JPanel(new GridBagLayout());

        centerPanel.setBackground(BACKGROUND);

        centerPanel.setBorder(
            new EmptyBorder(
                25, 35, 20, 35
            )
        );

        centerPanel.add(cardPanel);

        // =========================
        // FOOTER
        // =========================

        JLabel footerLabel = new JLabel(
            "Every adoption is a new beginning ❤️",
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
        // ADOPT ACTION
        // =========================

        adoptButton.addActionListener(e -> {

            int selectedIndex =
                petBox.getSelectedIndex();

            if (selectedIndex == -1) {

                JOptionPane.showMessageDialog(
                    this,
                    "No available pets.",
                    "No Pets Available",
                    JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            // Get actual selected pet
            Pet selectedPet =
                availablePets.get(selectedIndex);

            // Update adopted status in database
            boolean success =
                petDAO.adoptPet(
                    selectedPet.getId()
                );

            if (success) {

                // Record adoption in database
                boolean adoptionRecorded =
                    adoptionService.recordAdoption(
                        userId,
                        selectedPet.getId()
                    );

                if (!adoptionRecorded) {

                    JOptionPane.showMessageDialog(
                        this,
                        "Pet was marked as adopted, "
                        + "but adoption history could not be saved.",
                        "Database Warning",
                        JOptionPane.WARNING_MESSAGE
                    );

                    return;
                }

                // Update Java object
                selectedPet.adopt();

                JOptionPane.showMessageDialog(
                    this,
                    "❤️ " + selectedPet.getName()
                    + " has been adopted!\n\n"
                    + "Congratulations on finding "
                    + "your new companion!",
                    "Adoption Successful",
                    JOptionPane.INFORMATION_MESSAGE
                );

                // Remove adopted pet from current list
                petBox.removeItemAt(
                    selectedIndex
                );

                availablePets.remove(
                    selectedIndex
                );

                // Update count
                countLabel.setText(
                    availablePets.size()
                    + " pet(s) currently available"
                );

                // If no pets remain
                if (availablePets.isEmpty()) {

                    petBox.setEnabled(false);
                    adoptButton.setEnabled(false);

                    countLabel.setText(
                        "No pets currently available"
                    );
                }

            } else {

                JOptionPane.showMessageDialog(
                    this,
                    "Failed to adopt pet.",
                    "Adoption Failed",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
}