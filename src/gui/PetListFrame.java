
package gui;

import service.PetManager;
import dao.PetDAO;
import dao.WishlistDAO;
import dao.AdoptionRequestDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import model.Pet;

public class PetListFrame extends JFrame {

    private PetDAO petDAO;
    private AdoptionRequestDAO requestDAO;
    private WishlistDAO wishlistDAO;
    private JPanel cardsPanel;
    private JTextField searchField;
    private ArrayList<Pet> availablePets;
    private int userId;
    

    // PetConnect colors
    private final Color PRIMARY = new Color(124, 92, 166);
    private final Color PRIMARY_DARK = new Color(100, 70, 140);
    private final Color BACKGROUND = new Color(250, 247, 252);
    private final Color CARD_WHITE = Color.WHITE;
    private final Color DARK_TEXT = new Color(55, 50, 60);
    private final Color LIGHT_TEXT = new Color(110, 105, 115);
    private final Color AVAILABLE = new Color(76, 175, 80);

    public PetListFrame(PetManager petManager, int userId) {

    this.userId = userId;
    petDAO = new PetDAO();
    requestDAO = new AdoptionRequestDAO();
    wishlistDAO = new WishlistDAO();

        setTitle("PetConnect - Available Pets");
        setSize(800, 650);
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

        JPanel headerPanel = new JPanel(
            new BorderLayout()
        );

        headerPanel.setBackground(PRIMARY);
        headerPanel.setBorder(
            new EmptyBorder(22, 30, 22, 30)
        );

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(
            new BoxLayout(
                titlePanel,
                BoxLayout.Y_AXIS
            )
        );
        titlePanel.setOpaque(false);

        JLabel titleLabel = new JLabel(
            "🐾  Find Your New Friend"
        );

        titleLabel.setFont(
            new Font("Arial", Font.BOLD, 28)
        );

        titleLabel.setForeground(Color.WHITE);

        JLabel subtitleLabel = new JLabel(
            "Pets looking for a loving home"
        );

        subtitleLabel.setFont(
            new Font("Arial", Font.PLAIN, 14)
        );

        subtitleLabel.setForeground(Color.WHITE);

        titlePanel.add(titleLabel);
        titlePanel.add(
            Box.createVerticalStrut(5)
        );
        titlePanel.add(subtitleLabel);

        headerPanel.add(
            titlePanel,
            BorderLayout.WEST
        );

        // =========================
        // SEARCH PANEL
        // =========================

        JPanel searchPanel = new JPanel(
            new BorderLayout(8, 0)
        );

        searchPanel.setBackground(BACKGROUND);
        searchPanel.setBorder(
            new EmptyBorder(18, 30, 5, 30)
        );

        JLabel searchLabel = new JLabel("🔎");

        searchLabel.setFont(
            new Font("Segoe UI Emoji", Font.PLAIN, 22)
        );

        searchField = new JTextField();

        searchField.setFont(
            new Font("Arial", Font.PLAIN, 15)
        );

        searchField.setToolTipText(
            "Search by pet name, type or breed"
        );

        searchField.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(220, 215, 225),
                    1
                ),
                new EmptyBorder(
                    10, 12, 10, 12
                )
            )
        );

        searchPanel.add(
            searchLabel,
            BorderLayout.WEST
        );

        searchPanel.add(
            searchField,
            BorderLayout.CENTER
        );

        // =========================
        // PET CARDS PANEL
        // =========================

        cardsPanel = new JPanel();

        cardsPanel.setLayout(
            new BoxLayout(
                cardsPanel,
                BoxLayout.Y_AXIS
            )
        );

        cardsPanel.setBackground(BACKGROUND);

        cardsPanel.setBorder(
            new EmptyBorder(
                15, 30, 20, 30
            )
        );

        // Get pets from database
        ArrayList<Pet> pets =
            petDAO.getAllPets();

        availablePets = new ArrayList<>();

        for (Pet pet : pets) {

            if (!pet.isAdopted()) {
                availablePets.add(pet);
            }
        }

        displayPets("");

        // Search whenever user types
        searchField.getDocument()
            .addDocumentListener(
                new javax.swing.event.DocumentListener() {

                    private void search() {
                        displayPets(
                            searchField.getText()
                                .trim()
                                .toLowerCase()
                        );
                    }

                    @Override
                    public void insertUpdate(
                        javax.swing.event.DocumentEvent e
                    ) {
                        search();
                    }

                    @Override
                    public void removeUpdate(
                        javax.swing.event.DocumentEvent e
                    ) {
                        search();
                    }

                    @Override
                    public void changedUpdate(
                        javax.swing.event.DocumentEvent e
                    ) {
                        search();
                    }
                }
            );

        JScrollPane scrollPane =
            new JScrollPane(cardsPanel);

        scrollPane.setBorder(null);

        scrollPane.setBackground(BACKGROUND);

        scrollPane.getVerticalScrollBar()
            .setUnitIncrement(16);

        // =========================
        // CLOSE BUTTON
        // =========================

        JButton closeButton =
            new JButton("Close");

        closeButton.setFont(
            new Font("Arial", Font.BOLD, 14)
        );

        closeButton.setForeground(Color.WHITE);
        closeButton.setBackground(PRIMARY);

        closeButton.setFocusPainted(false);

        closeButton.setBorder(
            new EmptyBorder(
                10, 28, 10, 28
            )
        );

        closeButton.addActionListener(
            e -> dispose()
        );

        JPanel bottomPanel =
            new JPanel(new BorderLayout());

        bottomPanel.setBackground(BACKGROUND);

        bottomPanel.setBorder(
            new EmptyBorder(
                5, 30, 18, 30
            )
        );

        JLabel footerLabel = new JLabel(
            "Every pet deserves a loving home ❤️"
        );

        footerLabel.setFont(
            new Font("Arial", Font.ITALIC, 13)
        );

        footerLabel.setForeground(LIGHT_TEXT);

        bottomPanel.add(
            footerLabel,
            BorderLayout.WEST
        );

        bottomPanel.add(
            closeButton,
            BorderLayout.EAST
        );

        // =========================
        // ADD TO MAIN PANEL
        // =========================

        mainPanel.add(
            headerPanel,
            BorderLayout.NORTH
        );

        JPanel centerPanel =
            new JPanel(new BorderLayout());

        centerPanel.setBackground(BACKGROUND);

        centerPanel.add(
            searchPanel,
            BorderLayout.NORTH
        );

        centerPanel.add(
            scrollPane,
            BorderLayout.CENTER
        );

        mainPanel.add(
            centerPanel,
            BorderLayout.CENTER
        );

        mainPanel.add(
            bottomPanel,
            BorderLayout.SOUTH
        );

        add(mainPanel);
    }

    // =========================
    // DISPLAY PETS
    // =========================

    private void displayPets(String searchText) {

        cardsPanel.removeAll();

        boolean found = false;

        for (Pet pet : availablePets) {

            String name =
                pet.getName().toLowerCase();

            String type =
                pet.getType().toLowerCase();

            String breed =
                pet.getBreed().toLowerCase();

            if (
                searchText.isEmpty()
                || name.contains(searchText)
                || type.contains(searchText)
                || breed.contains(searchText)
            ) {

                found = true;

                JPanel card =
                    createPetCard(pet);

                cardsPanel.add(card);

                cardsPanel.add(
                    Box.createVerticalStrut(15)
                );
            }
        }

        if (!found) {

            JPanel emptyPanel =
                new JPanel();

            emptyPanel.setLayout(
                new BoxLayout(
                    emptyPanel,
                    BoxLayout.Y_AXIS
                )
            );

            emptyPanel.setOpaque(false);

            emptyPanel.setAlignmentX(
                Component.CENTER_ALIGNMENT
            );

            JLabel iconLabel =
                new JLabel("🐾");

            iconLabel.setFont(
                new Font(
                    "Segoe UI Emoji",
                    Font.PLAIN,
                    45
                )
            );

            iconLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
            );

            JLabel emptyLabel =
                new JLabel(
                    searchText.isEmpty()
                    ? "No pets are currently available."
                    : "No pets match your search."
                );

            emptyLabel.setFont(
                new Font(
                    "Arial",
                    Font.BOLD,
                    16
                )
            );

            emptyLabel.setForeground(DARK_TEXT);

            emptyLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
            );

            emptyPanel.add(
                Box.createVerticalStrut(60)
            );

            emptyPanel.add(iconLabel);

            emptyPanel.add(
                Box.createVerticalStrut(10)
            );

            emptyPanel.add(emptyLabel);

            cardsPanel.add(emptyPanel);
        }

        cardsPanel.revalidate();
        cardsPanel.repaint();
    }

    // =========================
    // CREATE PET CARD
    // =========================

    private JPanel createPetCard(Pet pet) {

        JPanel card =
            new JPanel(new BorderLayout(18, 10));

        card.setBackground(CARD_WHITE);

        card.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(230, 225, 235),
                    1
                ),
                new EmptyBorder(
                    18, 20, 18, 20
                )
            )
        );

        card.setMaximumSize(
            new Dimension(
                Integer.MAX_VALUE,
                145
            )
        );

        // =========================
        // PET ICON
        // =========================

        JLabel petIcon =
            new JLabel(
                getPetIcon(pet.getType()),
                SwingConstants.CENTER
            );

        petIcon.setFont(
            new Font(
                "Segoe UI Emoji",
                Font.PLAIN,
                52
            )
        );

        petIcon.setPreferredSize(
            new Dimension(85, 85)
        );

        // =========================
        // PET INFORMATION
        // =========================

        JPanel infoPanel =
            new JPanel();

        infoPanel.setLayout(
            new BoxLayout(
                infoPanel,
                BoxLayout.Y_AXIS
            )
        );

        infoPanel.setOpaque(false);

        JLabel nameLabel =
            new JLabel(pet.getName());

        nameLabel.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                21
            )
        );

        nameLabel.setForeground(PRIMARY);

        JLabel typeLabel =
            new JLabel(
                pet.getType()
                + "  •  "
                + pet.getBreed()
            );

        typeLabel.setFont(
            new Font(
                "Arial",
                Font.PLAIN,
                15
            )
        );

        typeLabel.setForeground(DARK_TEXT);

        JLabel ageLabel =
            new JLabel(
                "Age: "
                + pet.getAge()
                + " years"
            );

        ageLabel.setFont(
            new Font(
                "Arial",
                Font.PLAIN,
                14
            )
        );

        ageLabel.setForeground(LIGHT_TEXT);

        JLabel statusLabel =
            new JLabel(
                "●  Available for adoption"
            );

        statusLabel.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                13
            )
        );

        statusLabel.setForeground(
            AVAILABLE
        );

        infoPanel.add(nameLabel);

        infoPanel.add(
            Box.createVerticalStrut(6)
        );

        infoPanel.add(typeLabel);

        infoPanel.add(
            Box.createVerticalStrut(5)
        );

        infoPanel.add(ageLabel);

        infoPanel.add(
            Box.createVerticalStrut(7)
        );

        infoPanel.add(statusLabel);

        // =========================
        // VIEW LABEL
        // =========================

        JLabel viewLabel =
            new JLabel(
                "Available ✓"
            );

        viewLabel.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                12
            )
        );

        viewLabel.setForeground(
            AVAILABLE
        );

        viewLabel.setHorizontalAlignment(
            SwingConstants.RIGHT
        );

        card.add(
            petIcon,
            BorderLayout.WEST
        );

        card.add(
            infoPanel,
            BorderLayout.CENTER
        );

        card.add(
    viewLabel,
    BorderLayout.EAST
);

// Make the pet card clickable
card.setCursor(
    new Cursor(Cursor.HAND_CURSOR)
);

card.addMouseListener(
    new java.awt.event.MouseAdapter() {

        @Override
        public void mouseClicked(
            java.awt.event.MouseEvent e
        ) {
            showPetDetails(pet);
        }
    }
);

return card;
    }

    // =========================
    // PET ICON
    // =========================

    private String getPetIcon(String type) {

        if (type == null) {
            return "🐾";
        }

        String lowerType =
            type.toLowerCase();

        if (lowerType.contains("dog")) {
            return "🐶";
        }

        if (lowerType.contains("cat")) {
            return "🐱";
        }

        if (lowerType.contains("rabbit")) {
            return "🐰";
        }

        if (lowerType.contains("bird")) {
            return "🐦";
        }

        return "🐾";
    }
    
// =========================
// PET DETAILS POPUP
// =========================

private void showPetDetails(Pet pet) {

    JDialog dialog = new JDialog(
        this,
        "Pet Details",
        true
    );

    dialog.setSize(420, 430);
    dialog.setLocationRelativeTo(this);
    dialog.setResizable(false);

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(
        new BoxLayout(mainPanel, BoxLayout.Y_AXIS)
    );

    mainPanel.setBackground(BACKGROUND);
    mainPanel.setBorder(
        new EmptyBorder(25, 30, 25, 30)
    );

    // Pet icon
    JLabel iconLabel = new JLabel(
        getPetIcon(pet.getType())
    );

    iconLabel.setFont(
        new Font(
            "Segoe UI Emoji",
            Font.PLAIN,
            55
        )
    );

    iconLabel.setAlignmentX(
        Component.CENTER_ALIGNMENT
    );

    // Pet name
    JLabel nameLabel = new JLabel(
        pet.getName()
    );

    nameLabel.setFont(
        new Font(
            "Arial",
            Font.BOLD,
            25
        )
    );

    nameLabel.setForeground(PRIMARY);

    nameLabel.setAlignmentX(
        Component.CENTER_ALIGNMENT
    );

    // Pet information
    JLabel typeLabel = new JLabel(
        "Type: " + pet.getType()
    );

    JLabel breedLabel = new JLabel(
        "Breed: " + pet.getBreed()
    );

    JLabel ageLabel = new JLabel(
        "Age: " + pet.getAge() + " years"
    );

    JLabel statusLabel = new JLabel(
        "Status: Available ❤️"
    );

    JLabel[] labels = {
        typeLabel,
        breedLabel,
        ageLabel,
        statusLabel
    };

    for (JLabel label : labels) {

        label.setFont(
            new Font(
                "Arial",
                Font.PLAIN,
                15
            )
        );

        label.setForeground(DARK_TEXT);

        label.setAlignmentX(
            Component.CENTER_ALIGNMENT
        );
    }

    statusLabel.setForeground(AVAILABLE);
    statusLabel.setFont(
        new Font(
            "Arial",
            Font.BOLD,
            15
        )
    );

    // Adopt button
     JButton adoptButton =
    new JButton("❤️  Request Adoption");

    adoptButton.setFont(
        new Font(
            "Arial",
            Font.BOLD,
            15
        )
    );

    adoptButton.setForeground(Color.WHITE);
    adoptButton.setBackground(PRIMARY);
    adoptButton.setFocusPainted(false);

    adoptButton.setAlignmentX(
        Component.CENTER_ALIGNMENT
    );

    adoptButton.setMaximumSize(
        new Dimension(220, 42)
    );
    // Wishlist button
JButton wishlistButton =
    new JButton("🤍  Add to Wishlist");

wishlistButton.setFont(
    new Font(
        "Arial",
        Font.BOLD,
        15
    )
);

wishlistButton.setForeground(PRIMARY);
wishlistButton.setBackground(Color.WHITE);
wishlistButton.setFocusPainted(false);

wishlistButton.setAlignmentX(
    Component.CENTER_ALIGNMENT
);

wishlistButton.setMaximumSize(
    new Dimension(220, 42)
);

    // Close button
    JButton closeButton =
        new JButton("Close");

    closeButton.setFont(
        new Font(
            "Arial",
            Font.PLAIN,
            14
        )
    );

    closeButton.setForeground(PRIMARY);
    closeButton.setBackground(Color.WHITE);
    closeButton.setFocusPainted(false);

    closeButton.setAlignmentX(
        Component.CENTER_ALIGNMENT
    );

    closeButton.setMaximumSize(
        new Dimension(220, 38)
    );

    // Add components
    mainPanel.add(iconLabel);

    mainPanel.add(
        Box.createVerticalStrut(5)
    );

    mainPanel.add(nameLabel);

    mainPanel.add(
        Box.createVerticalStrut(18)
    );

    mainPanel.add(typeLabel);

    mainPanel.add(
        Box.createVerticalStrut(6)
    );

    mainPanel.add(breedLabel);

    mainPanel.add(
        Box.createVerticalStrut(6)
    );

    mainPanel.add(ageLabel);

    mainPanel.add(
        Box.createVerticalStrut(6)
    );

    mainPanel.add(statusLabel);

    mainPanel.add(
        Box.createVerticalStrut(22)
    );

    mainPanel.add(adoptButton);
    mainPanel.add(wishlistButton);

    mainPanel.add(
        Box.createVerticalStrut(10)
    );

    mainPanel.add(closeButton);

    // Close button
    closeButton.addActionListener(
        e -> dialog.dispose()
    );

    // Adopt button
    adoptButton.addActionListener(e -> {

    int result =
        JOptionPane.showConfirmDialog(
            dialog,
            "Send an adoption request for "
            + pet.getName()
            + "?",
            "Request Adoption",
            JOptionPane.YES_NO_OPTION
        );

    if (result == JOptionPane.YES_OPTION) {

        boolean success =
            requestDAO.sendRequest(
                pet.getId(),
                userId
            );

        if (success) {

            JOptionPane.showMessageDialog(
                dialog,
                "Your adoption request has been sent!\n"
                + "The pet owner will review your request.",
                "Request Sent",
                JOptionPane.INFORMATION_MESSAGE
            );

            dialog.dispose();

        } else {

            

    JOptionPane.showMessageDialog(
        dialog,
        "You cannot request adoption of your own pet.",
        "Request Not Allowed",
        JOptionPane.WARNING_MESSAGE
    );
}
    }
});
// Wishlist button
wishlistButton.addActionListener(e -> {

    boolean alreadyAdded =
        wishlistDAO.isInWishlist(
            userId,
            pet.getId()
        );

    if (alreadyAdded) {

        JOptionPane.showMessageDialog(
            dialog,
            pet.getName()
            + " is already in your wishlist.",
            "Already Saved",
            JOptionPane.INFORMATION_MESSAGE
        );

        return;
    }

    boolean success =
        wishlistDAO.addToWishlist(
            userId,
            pet.getId()
        );

    if (success) {

        JOptionPane.showMessageDialog(
            dialog,
            pet.getName()
            + " has been added to your wishlist! ❤️",
            "Added to Wishlist",
            JOptionPane.INFORMATION_MESSAGE
        );

    } else {

        JOptionPane.showMessageDialog(
            dialog,
            "Unable to add the pet to your wishlist.",
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
});
    dialog.add(mainPanel);

    dialog.setVisible(true);
}


}