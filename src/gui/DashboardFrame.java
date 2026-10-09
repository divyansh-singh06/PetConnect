
package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import service.PetManager;
import dao.DashboardStatsDAO;

public class DashboardFrame extends JFrame {

    private PetManager petManager;
    private int userId;
    private DashboardStatsDAO statsDAO;

    // =========================
    // PETCONNECT COLORS
    // =========================

    private final Color PRIMARY = new Color(124, 92, 166);
    private final Color PRIMARY_DARK = new Color(100, 70, 140);
    private final Color BACKGROUND = new Color(250, 247, 252);
    private final Color CARD_BACKGROUND = Color.WHITE;
    private final Color DARK_TEXT = new Color(55, 50, 60);
    private final Color LIGHT_TEXT = new Color(110, 105, 115);

    public DashboardFrame(String userName, int userId) {

        this.userId = userId;
        petManager = new PetManager();
        statsDAO = new DashboardStatsDAO();

        setTitle("PetConnect - Dashboard");
        setSize(750, 650);
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

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(PRIMARY);
        headerPanel.setBorder(
            new EmptyBorder(25, 35, 25, 35)
        );

        // Left side of header
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setOpaque(false);

        JLabel titleLabel = new JLabel("🐾  PetConnect");
        titleLabel.setFont(
            new Font("Arial", Font.BOLD, 32)
        );
        titleLabel.setForeground(Color.WHITE);

        JLabel subtitleLabel = new JLabel(
            "Find a friend. Give a home."
        );
        subtitleLabel.setFont(
            new Font("Arial", Font.PLAIN, 15)
        );
        subtitleLabel.setForeground(Color.WHITE);

        titlePanel.add(titleLabel);
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(subtitleLabel);

        // Right side - welcome message
        JLabel welcomeLabel = new JLabel(
            "<html><div style='text-align:right;'>"
            + "Welcome,<br>"
            + "<b>" + userName + "!</b>"
            + "</div></html>"
        );

        welcomeLabel.setFont(
            new Font("Arial", Font.PLAIN, 16)
        );
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setHorizontalAlignment(
            SwingConstants.RIGHT
        );

        headerPanel.add(titlePanel, BorderLayout.WEST);
        headerPanel.add(welcomeLabel, BorderLayout.EAST);

        // =========================
        // CONTENT PANEL
        // =========================

        JPanel contentPanel = new JPanel(
            new BorderLayout()
        );

        contentPanel.setBackground(BACKGROUND);
        contentPanel.setBorder(
            new EmptyBorder(25, 35, 15, 35)
        );

        // Section heading
        JPanel headingPanel = new JPanel();
        headingPanel.setLayout(
            new BoxLayout(
                headingPanel,
                BoxLayout.Y_AXIS
            )
        );
        headingPanel.setOpaque(false);

        JLabel headingLabel = new JLabel(
            "What would you like to do?"
        );

        headingLabel.setFont(
            new Font("Arial", Font.BOLD, 22)
        );
        headingLabel.setForeground(DARK_TEXT);

        JLabel descriptionLabel = new JLabel(
            "Manage pets, adoptions and your adoption history."
        );

        descriptionLabel.setFont(
            new Font("Arial", Font.PLAIN, 14)
        );
        descriptionLabel.setForeground(LIGHT_TEXT);

        headingPanel.add(headingLabel);
        headingPanel.add(Box.createVerticalStrut(5));
        headingPanel.add(descriptionLabel);

        // =========================
        // FEATURE CARDS
        // =========================

        JPanel cardPanel = new JPanel(
    new GridLayout(3, 2, 18, 18)
);

        cardPanel.setBackground(BACKGROUND);
        cardPanel.setBorder(
            new EmptyBorder(20, 0, 10, 0)
        );
        // =========================
// DASHBOARD STATISTICS
// =========================

int availablePets =
    statsDAO.getAvailablePets();

int wishlistCount =
    statsDAO.getWishlistCount(userId);

int requestsCount =
    statsDAO.getMyRequestsCount(userId);

int adoptedCount =
    statsDAO.getAdoptedPetsCount(userId);

JPanel statsPanel =
    new JPanel(
        new GridLayout(1, 4, 12, 0)
    );

statsPanel.setBackground(BACKGROUND);

statsPanel.add(
    createStatCard(
        "🐾",
        String.valueOf(availablePets),
        "Available Pets"
    )
);

statsPanel.add(
    createStatCard(
        "❤️",
        String.valueOf(wishlistCount),
        "Wishlist"
    )
);

statsPanel.add(
    createStatCard(
        "📩",
        String.valueOf(requestsCount),
        "My Requests"
    )
);

statsPanel.add(
    createStatCard(
        "🏠",
        String.valueOf(adoptedCount),
        "Adopted Pets"
    )
);

        // Create cards
        JPanel viewPetsCard = createCard(
            "🐶",
            "Find Pets",
            "Browse available pets",
            PRIMARY
        );

        JPanel addPetCard = createCard(
            "🏠",
            "Add a Pet",
            "List a pet for adoption",
            PRIMARY
        );

        

        JPanel historyCard = createCard(
            "📋",
            "My History",
            "View your adoption history",
            PRIMARY
        );
        JPanel requestsCard = createCard(
    "📩",
    "Adoption Requests",
    "Manage requests for your pets",
    PRIMARY
);
JPanel myRequestsCard = createCard(
    "📤",
    "My Requests",
    "Track requests you have sent",
    PRIMARY
);
JPanel wishlistCard = createCard(
    "❤️",
    "My Wishlist",
    "View your saved pets",
    PRIMARY
);

        cardPanel.add(viewPetsCard);
        cardPanel.add(addPetCard);

       
        cardPanel.add(historyCard);
        cardPanel.add(requestsCard);
        cardPanel.add(myRequestsCard);
        cardPanel.add(wishlistCard);
        // DASHBOARD CONTENT
// =========================

JPanel centerPanel = new JPanel(
    new BorderLayout(0, 10)
);

centerPanel.setOpaque(false);

centerPanel.add(
    headingPanel,
    BorderLayout.NORTH
);

// Statistics + feature cards
JPanel mainContentPanel =
    new JPanel(
        new BorderLayout(0, 10)
    );

mainContentPanel.setOpaque(false);

mainContentPanel.add(
    statsPanel,
    BorderLayout.NORTH
);

mainContentPanel.add(
    cardPanel,
    BorderLayout.CENTER
);

centerPanel.add(
    mainContentPanel,
    BorderLayout.CENTER
);

contentPanel.add(
    centerPanel,
    BorderLayout.CENTER
);
        // =========================
        // LOGOUT BUTTON
        // =========================

        JButton logoutButton = new JButton(
            "↩  Logout"
        );

        logoutButton.setFont(
            new Font("Arial", Font.BOLD, 14)
        );

        logoutButton.setForeground(PRIMARY);
        logoutButton.setBackground(Color.WHITE);

        logoutButton.setFocusPainted(false);

        logoutButton.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    PRIMARY,
                    1
                ),
                new EmptyBorder(
                    8, 20, 8, 20
                )
            )
        );

        JPanel bottomPanel = new JPanel(
            new BorderLayout()
        );

        bottomPanel.setBackground(BACKGROUND);
        bottomPanel.setBorder(
            new EmptyBorder(
                5, 35, 20, 35
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
            logoutButton,
            BorderLayout.EAST
        );

        // =========================
        // ADD EVERYTHING
        // =========================

        mainPanel.add(
            headerPanel,
            BorderLayout.NORTH
        );

        mainPanel.add(
            contentPanel,
            BorderLayout.CENTER
        );

        mainPanel.add(
            bottomPanel,
            BorderLayout.SOUTH
        );

        add(mainPanel);

        // =========================
        // CARD ACTIONS
        // =========================

        viewPetsCard.addMouseListener(
            new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(
                    java.awt.event.MouseEvent e
                ) {
                    new PetListFrame(
    petManager,
    userId
).setVisible(true);
                }
            }
        );

        addPetCard.addMouseListener(
    new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(
            java.awt.event.MouseEvent e
        ) {
            new AddPetFrame(
                petManager,
                userId
            ).setVisible(true);
        }
    }
);

       

        historyCard.addMouseListener(
            new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(
                    java.awt.event.MouseEvent e
                ) {
                    new AdoptionHistoryFrame(
                        userId
                    ).setVisible(true);
                }
            }
        );
        requestsCard.addMouseListener(
    new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(
            java.awt.event.MouseEvent e
        ) {
            new AdoptionRequestsFrame(
                userId
            ).setVisible(true);
        }
    }
);
myRequestsCard.addMouseListener(
    new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(
            java.awt.event.MouseEvent e
        ) {
            new AdoptionRequestFrame(
                userId
            ).setVisible(true);
        }
    }
);
wishlistCard.addMouseListener(
    new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(
            java.awt.event.MouseEvent e
        ) {
            new WishlistFrame(
                userId
            ).setVisible(true);
        }
    }
);

        logoutButton.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });
    }

    // =========================
    // CREATE FEATURE CARD
    // =========================

    private JPanel createCard(
        String icon,
        String title,
        String description,
        Color color
    ) {

        JPanel card = new JPanel(
            new BorderLayout()
        );

        card.setBackground(CARD_BACKGROUND);

        card.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(230, 225, 235),
                    1
                ),
                new EmptyBorder(
                    20, 20, 20, 20
                )
            )
        );

        // Icon
        JLabel iconLabel = new JLabel(
            icon,
            SwingConstants.CENTER
        );

        iconLabel.setFont(
            new Font("Segoe UI Emoji", Font.PLAIN, 38)
        );

        // Text panel
        JPanel textPanel = new JPanel();
        textPanel.setLayout(
            new BoxLayout(
                textPanel,
                BoxLayout.Y_AXIS
            )
        );

        textPanel.setOpaque(false);

        JLabel titleLabel = new JLabel(title);

        titleLabel.setFont(
            new Font("Arial", Font.BOLD, 18)
        );

        titleLabel.setForeground(DARK_TEXT);

        JLabel descriptionLabel = new JLabel(
            description
        );

        descriptionLabel.setFont(
            new Font("Arial", Font.PLAIN, 13)
        );

        descriptionLabel.setForeground(LIGHT_TEXT);

        textPanel.add(titleLabel);
        textPanel.add(
            Box.createVerticalStrut(5)
        );
        textPanel.add(descriptionLabel);

        card.add(
            iconLabel,
            BorderLayout.WEST
        );

        card.add(
            textPanel,
            BorderLayout.CENTER
        );

        // Cursor
        card.setCursor(
            new Cursor(Cursor.HAND_CURSOR)
        );

        return card;
    }

    // =========================
    // MAIN METHOD
    // =========================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new DashboardFrame(
                "Divyansh",
                1
            ).setVisible(true);
        });
    }
    private JPanel createStatCard(
        String icon,
        String value,
        String label) {

    JPanel panel = new JPanel();

    panel.setLayout(
        new BoxLayout(
            panel,
            BoxLayout.Y_AXIS
        )
    );

    panel.setBackground(Color.WHITE);

    panel.setBorder(
        BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(
                new Color(230, 225, 235)
            ),
            BorderFactory.createEmptyBorder(
                12, 15, 12, 15
            )
        )
    );

    JLabel iconLabel =
        new JLabel(icon);

    iconLabel.setFont(
        new Font(
            "Segoe UI Emoji",
            Font.PLAIN,
            24
        )
    );

    iconLabel.setAlignmentX(
        Component.CENTER_ALIGNMENT
    );

    JLabel valueLabel =
        new JLabel(value);

    valueLabel.setFont(
        new Font(
            "Arial",
            Font.BOLD,
            24
        )
    );

    valueLabel.setForeground(PRIMARY);

    valueLabel.setAlignmentX(
        Component.CENTER_ALIGNMENT
    );

    JLabel labelLabel =
        new JLabel(label);

    labelLabel.setFont(
        new Font(
            "Arial",
            Font.PLAIN,
            12
        )
    );

    labelLabel.setForeground(LIGHT_TEXT);

    labelLabel.setAlignmentX(
        Component.CENTER_ALIGNMENT
    );

    panel.add(iconLabel);
    panel.add(Box.createVerticalStrut(3));
    panel.add(valueLabel);
    panel.add(Box.createVerticalStrut(2));
    panel.add(labelLabel);

    return panel;
}
}