package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import service.PetManager;
import dao.DashboardStatsDAO;

public class DashboardFrame extends JFrame {

    private PetManager petManager;
    private int userId;
    private DashboardStatsDAO statsDAO;

    // =========================
    // COLORS
    // =========================

    private final Color PRIMARY = new Color(124, 92, 166);
    private final Color PRIMARY_DARK = new Color(101, 70, 140);
    private final Color PRIMARY_LIGHT = new Color(239, 233, 247);

    private final Color BACKGROUND = new Color(248, 246, 251);
    private final Color WHITE = Color.WHITE;

    private final Color DARK_TEXT = new Color(45, 42, 50);
    private final Color LIGHT_TEXT = new Color(110, 105, 118);
    private final Color BORDER = new Color(230, 225, 235);

    // =========================
    // CONSTRUCTOR
    // =========================

    public DashboardFrame(String userName, int userId) {

        this.userId = userId;

        petManager = new PetManager();
        statsDAO = new DashboardStatsDAO();

        setTitle("PetConnect - Dashboard");
        setSize(1100, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // =========================
        // MAIN PANEL
        // =========================

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND);

        // =========================
        // SIDEBAR
        // =========================

        JPanel sidebar = new JPanel();

        sidebar.setLayout(
                new BoxLayout(
                        sidebar,
                        BoxLayout.Y_AXIS
                )
        );

        sidebar.setBackground(PRIMARY);

        sidebar.setPreferredSize(
                new Dimension(225, 720)
        );

        sidebar.setBorder(
                BorderFactory.createEmptyBorder(
                        30, 18, 25, 18
                )
        );

        // Logo
        JLabel logo = new JLabel("PETCONNECT");

        logo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        23
                )
        );

        logo.setForeground(Color.WHITE);
        logo.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        sidebar.add(logo);

        JLabel logoSub = new JLabel(
                "Pet Adoption Platform"
        );

        logoSub.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        11
                )
        );

        logoSub.setForeground(
                new Color(225, 215, 238)
        );

        logoSub.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        sidebar.add(
                Box.createVerticalStrut(6)
        );

        sidebar.add(logoSub);

        sidebar.add(
                Box.createVerticalStrut(40)
        );

        // Menu heading
        JLabel menuLabel =
                new JLabel("MAIN MENU");

        menuLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        10
                )
        );

        menuLabel.setForeground(
                new Color(218, 207, 232)
        );

        menuLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        sidebar.add(menuLabel);

        sidebar.add(
                Box.createVerticalStrut(12)
        );

        // =========================
        // SIDEBAR BUTTONS
        // =========================

        JButton dashboardButton =
                createSidebarButton(
                        "D   Dashboard",
                        true
                );

        JButton findPetsButton =
                createSidebarButton(
                        "P   Find Pets",
                        false
                );

        JButton addPetButton =
                createSidebarButton(
                        "+   Add a Pet",
                        false
                );

        JButton requestsButton =
                createSidebarButton(
                        "R   Requests",
                        false
                );

        JButton myRequestsButton =
                createSidebarButton(
                        "S   My Requests",
                        false
                );

        JButton historyButton =
                createSidebarButton(
                        "H   My History",
                        false
                );

        JButton wishlistButton =
                createSidebarButton(
                        "W   Wishlist",
                        false
                );

        sidebar.add(dashboardButton);

        sidebar.add(
                Box.createVerticalStrut(6)
        );

        sidebar.add(findPetsButton);

        sidebar.add(
                Box.createVerticalStrut(6)
        );

        sidebar.add(addPetButton);

        sidebar.add(
                Box.createVerticalStrut(6)
        );

        sidebar.add(requestsButton);

        sidebar.add(
                Box.createVerticalStrut(6)
        );

        sidebar.add(myRequestsButton);

        sidebar.add(
                Box.createVerticalStrut(6)
        );

        sidebar.add(historyButton);

        sidebar.add(
                Box.createVerticalStrut(6)
        );

        sidebar.add(wishlistButton);

        sidebar.add(
                Box.createVerticalGlue()
        );

        JButton logoutButton =
                createSidebarButton(
                        "X   Logout",
                        false
                );

        sidebar.add(logoutButton);

        // =========================
        // CONTENT
        // =========================

        JPanel content =
                new JPanel(
                        new BorderLayout()
                );

        content.setBackground(
                BACKGROUND
        );

        content.setBorder(
                BorderFactory.createEmptyBorder(
                        28, 35, 22, 35
                )
        );

        // =========================
        // TOP HEADER
        // =========================

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setOpaque(false);

        JPanel welcomePanel =
                new JPanel();

        welcomePanel.setLayout(
                new BoxLayout(
                        welcomePanel,
                        BoxLayout.Y_AXIS
                )
        );

        welcomePanel.setOpaque(false);

        JLabel welcome =
                new JLabel(
                        "Good to see you, "
                        + userName
                );

        welcome.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        27
                )
        );

        welcome.setForeground(
                DARK_TEXT
        );

        JLabel subtitle =
                new JLabel(
                        "Find a loving companion or help a pet find its forever home."
                );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        subtitle.setForeground(
                LIGHT_TEXT
        );

        welcomePanel.add(welcome);

        welcomePanel.add(
                Box.createVerticalStrut(6)
        );

        welcomePanel.add(subtitle);

        header.add(
                welcomePanel,
                BorderLayout.WEST
        );

        // =========================
        // PROFILE
        // =========================

        RoundedPanel profile =
                new RoundedPanel(
                        18,
                        WHITE
                );

        profile.setLayout(
                new FlowLayout(
                        FlowLayout.CENTER,
                        10,
                        8
                )
        );

        profile.setBorder(
                BorderFactory.createEmptyBorder(
                        2, 12, 2, 12
                )
        );

        JLabel profileCircle =
                new JLabel("U");

        profileCircle.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        profileCircle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        profileCircle.setForeground(
                Color.WHITE
        );

        profileCircle.setOpaque(true);

        profileCircle.setBackground(
                PRIMARY
        );

        profileCircle.setPreferredSize(
                new Dimension(30, 30)
        );

        JLabel profileName =
                new JLabel(userName);

        profileName.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        profileName.setForeground(
                DARK_TEXT
        );

        profile.add(profileCircle);
        profile.add(profileName);

        header.add(
                profile,
                BorderLayout.EAST
        );

        content.add(
                header,
                BorderLayout.NORTH
        );

        // =========================
        // CENTER
        // =========================

        JPanel center =
                new JPanel();

        center.setLayout(
                new BoxLayout(
                        center,
                        BoxLayout.Y_AXIS
                )
        );

        center.setOpaque(false);

        center.add(
                Box.createVerticalStrut(20)
        );

        // =========================
        // HERO BANNER
        // =========================

        RoundedPanel hero =
                new RoundedPanel(
                        22,
                        PRIMARY
                );

        hero.setLayout(
                new BorderLayout()
        );

        hero.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 25, 20, 25
                )
        );

        JPanel heroText =
                new JPanel();

        heroText.setLayout(
                new BoxLayout(
                        heroText,
                        BoxLayout.Y_AXIS
                )
        );

        heroText.setOpaque(false);

        JLabel heroTitle =
                new JLabel(
                        "Find your perfect companion"
                );

        heroTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        heroTitle.setForeground(
                Color.WHITE
        );

        JLabel heroDescription =
                new JLabel(
                        "Explore pets waiting for a loving forever home."
                );

        heroDescription.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        heroDescription.setForeground(
                new Color(
                        238,
                        230,
                        246
                )
        );

        heroText.add(heroTitle);

        heroText.add(
                Box.createVerticalStrut(5)
        );

        heroText.add(heroDescription);

        heroText.add(
                Box.createVerticalStrut(12)
        );

        JButton exploreButton =
                new JButton(
                        "Explore Pets  →"
                );

        exploreButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        exploreButton.setForeground(
                PRIMARY
        );

        exploreButton.setBackground(
                Color.WHITE
        );

        exploreButton.setFocusPainted(false);
        exploreButton.setBorderPainted(false);

        exploreButton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        exploreButton.setBorder(
                BorderFactory.createEmptyBorder(
                        8, 16, 8, 16
                )
        );

        exploreButton.addActionListener(e -> {
            new PetListFrame(
                    petManager,
                    userId
            ).setVisible(true);
        });

        heroText.add(exploreButton);

        hero.add(
                heroText,
                BorderLayout.WEST
        );

        // Decorative paw
        JPanel heroGraphic =
                new JPanel();

        heroGraphic.setOpaque(false);

        heroGraphic.setPreferredSize(
                new Dimension(
                        180,
                        100
                )
        );

        heroGraphic.setLayout(
                new GridBagLayout()
        );

        JLabel paw =
                new JLabel("PET");

        paw.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        40
                )
        );

        paw.setForeground(
                new Color(
                        210,
                        190,
                        230
                )
        );

        heroGraphic.add(paw);

        hero.add(
                heroGraphic,
                BorderLayout.EAST
        );

        center.add(hero);

        center.add(
                Box.createVerticalStrut(20)
        );

        // =========================
        // STATISTICS
        // =========================

        int availablePets =
                statsDAO.getAvailablePets();

        int wishlistCount =
                statsDAO.getWishlistCount(
                        userId
                );

        int requestsCount =
                statsDAO.getMyRequestsCount(
                        userId
                );

        int adoptedCount =
                statsDAO.getAdoptedPetsCount(
                        userId
                );

        JPanel statsPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                15,
                                0
                        )
                );

        statsPanel.setOpaque(false);

        statsPanel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        112
                )
        );

        statsPanel.add(
                createStatCard(
                        "P",
                        String.valueOf(
                                availablePets
                        ),
                        "Available Pets"
                )
        );

        statsPanel.add(
                createStatCard(
                        "W",
                        String.valueOf(
                                wishlistCount
                        ),
                        "Wishlist"
                )
        );

        statsPanel.add(
                createStatCard(
                        "R",
                        String.valueOf(
                                requestsCount
                        ),
                        "My Requests"
                )
        );

        statsPanel.add(
                createStatCard(
                        "A",
                        String.valueOf(
                                adoptedCount
                        ),
                        "Adopted Pets"
                )
        );

        center.add(statsPanel);

        center.add(
                Box.createVerticalStrut(22)
        );

        // =========================
        // QUICK ACTIONS
        // =========================

        JPanel actionsHeader =
                new JPanel(
                        new BorderLayout()
                );

        actionsHeader.setOpaque(false);

        JLabel actionsTitle =
                new JLabel(
                        "Quick Actions"
                );

        actionsTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        21
                )
        );

        actionsTitle.setForeground(
                DARK_TEXT
        );

        JLabel actionsSubtitle =
                new JLabel(
                        "Everything you need to manage your adoption journey."
                );

        actionsSubtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        actionsSubtitle.setForeground(
                LIGHT_TEXT
        );

        JPanel actionText =
                new JPanel();

        actionText.setLayout(
                new BoxLayout(
                        actionText,
                        BoxLayout.Y_AXIS
                )
        );

        actionText.setOpaque(false);

        actionText.add(actionsTitle);

        actionText.add(
                Box.createVerticalStrut(3)
        );

        actionText.add(actionsSubtitle);

        actionsHeader.add(
                actionText,
                BorderLayout.WEST
        );

        center.add(actionsHeader);

        center.add(
                Box.createVerticalStrut(13)
        );

        // =========================
        // FEATURE CARDS
        // =========================

        JPanel cards =
                new JPanel(
                        new GridLayout(
                                2,
                                3,
                                15,
                                15
                        )
                );

        cards.setOpaque(false);

        cards.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        225
                )
        );

        JPanel findPetsCard =
                createModernCard(
                        "P",
                        "Find Pets",
                        "Browse pets looking for a home."
                );

        JPanel addPetCard =
                createModernCard(
                        "+",
                        "Add a Pet",
                        "List a pet for adoption."
                );

        JPanel requestsCard =
                createModernCard(
                        "R",
                        "Adoption Requests",
                        "Manage requests for your pets."
                );

        JPanel myRequestsCard =
                createModernCard(
                        "S",
                        "My Requests",
                        "Track adoption requests you sent."
                );

        JPanel historyCard =
                createModernCard(
                        "H",
                        "My History",
                        "View your completed adoptions."
                );

        JPanel wishlistCard =
                createModernCard(
                        "W",
                        "My Wishlist",
                        "View your saved pets."
                );

        cards.add(findPetsCard);
        cards.add(addPetCard);
        cards.add(requestsCard);
        cards.add(myRequestsCard);
        cards.add(historyCard);
        cards.add(wishlistCard);

        center.add(cards);

        content.add(
                center,
                BorderLayout.CENTER
        );

        // =========================
        // FOOTER
        // =========================

        JLabel footer =
                new JLabel(
                        "PetConnect  |  Connecting pets with loving homes"
                );

        footer.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        footer.setForeground(
                LIGHT_TEXT
        );

        content.add(
                footer,
                BorderLayout.SOUTH
        );

        // =========================
        // SIDEBAR ACTIONS
        // =========================

        findPetsButton.addActionListener(e -> {
            new PetListFrame(
                    petManager,
                    userId
            ).setVisible(true);
        });

        addPetButton.addActionListener(e -> {
            new AddPetFrame(
                    petManager,
                    userId
            ).setVisible(true);
        });

        requestsButton.addActionListener(e -> {
            new AdoptionRequestsFrame(
                    userId
            ).setVisible(true);
        });

        myRequestsButton.addActionListener(e -> {
            new AdoptionRequestFrame(
                    userId
            ).setVisible(true);
        });

        historyButton.addActionListener(e -> {
            new AdoptionHistoryFrame(
                    userId
            ).setVisible(true);
        });

        wishlistButton.addActionListener(e -> {
            new WishlistFrame(
                    userId
            ).setVisible(true);
        });

        logoutButton.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });

        // =========================
        // CARD ACTIONS
        // =========================

        findPetsCard.addMouseListener(
                new MouseAdapter() {
                    public void mouseClicked(
                            MouseEvent e
                    ) {
                        new PetListFrame(
                                petManager,
                                userId
                        ).setVisible(true);
                    }
                }
        );

        addPetCard.addMouseListener(
                new MouseAdapter() {
                    public void mouseClicked(
                            MouseEvent e
                    ) {
                        new AddPetFrame(
                                petManager,
                                userId
                        ).setVisible(true);
                    }
                }
        );

        requestsCard.addMouseListener(
                new MouseAdapter() {
                    public void mouseClicked(
                            MouseEvent e
                    ) {
                        new AdoptionRequestsFrame(
                                userId
                        ).setVisible(true);
                    }
                }
        );

        myRequestsCard.addMouseListener(
                new MouseAdapter() {
                    public void mouseClicked(
                            MouseEvent e
                    ) {
                        new AdoptionRequestFrame(
                                userId
                        ).setVisible(true);
                    }
                }
        );

        historyCard.addMouseListener(
                new MouseAdapter() {
                    public void mouseClicked(
                            MouseEvent e
                    ) {
                        new AdoptionHistoryFrame(
                                userId
                        ).setVisible(true);
                    }
                }
        );

        wishlistCard.addMouseListener(
                new MouseAdapter() {
                    public void mouseClicked(
                            MouseEvent e
                    ) {
                        new WishlistFrame(
                                userId
                        ).setVisible(true);
                    }
                }
        );

        // =========================
        // ADD PANELS
        // =========================

        mainPanel.add(
                sidebar,
                BorderLayout.WEST
        );

        mainPanel.add(
                content,
                BorderLayout.CENTER
        );

        add(mainPanel);
    }

    // =====================================================
    // SIDEBAR BUTTON
    // =====================================================

    private JButton createSidebarButton(
            String text,
            boolean selected
    ) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        button.setForeground(
                Color.WHITE
        );

        button.setBackground(
                selected
                        ? PRIMARY_DARK
                        : PRIMARY
        );

        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        42
                )
        );

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        0, 14, 0, 10
                )
        );

        button.addMouseListener(
                new MouseAdapter() {

                    public void mouseEntered(
                            MouseEvent e
                    ) {

                        if (!selected) {
                            button.setBackground(
                                    PRIMARY_DARK
                            );
                        }
                    }

                    public void mouseExited(
                            MouseEvent e
                    ) {

                        if (!selected) {
                            button.setBackground(
                                    PRIMARY
                            );
                        }
                    }
                }
        );

        return button;
    }

    // =====================================================
    // STAT CARD
    // =====================================================

    private JPanel createStatCard(
            String icon,
            String value,
            String label
    ) {

        RoundedPanel panel =
                new RoundedPanel(
                        18,
                        WHITE
                );

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        11, 15, 11, 15
                )
        );

        JLabel iconLabel =
                new JLabel(icon);

        iconLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        iconLabel.setForeground(
                PRIMARY
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
                        25
                )
        );

        valueLabel.setForeground(
                PRIMARY
        );

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

        labelLabel.setForeground(
                LIGHT_TEXT
        );

        labelLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panel.add(iconLabel);

        panel.add(
                Box.createVerticalStrut(2)
        );

        panel.add(valueLabel);

        panel.add(
                Box.createVerticalStrut(2)
        );

        panel.add(labelLabel);

        return panel;
    }

    // =====================================================
    // MODERN CARD
    // =====================================================

    private JPanel createModernCard(
            String icon,
            String title,
            String description
    ) {

        RoundedPanel card =
                new RoundedPanel(
                        18,
                        WHITE
                );

        card.setLayout(
                new BorderLayout()
        );

        card.setBorder(
                BorderFactory.createEmptyBorder(
                        17, 17, 17, 17
                )
        );

        // Icon
        RoundedPanel iconPanel =
                new RoundedPanel(
                        14,
                        PRIMARY_LIGHT
                );

        iconPanel.setLayout(
                new GridBagLayout()
        );

        iconPanel.setPreferredSize(
                new Dimension(
                        56,
                        56
                )
        );

        JLabel iconLabel =
                new JLabel(icon);

        iconLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        iconLabel.setForeground(
                PRIMARY
        );

        iconPanel.add(iconLabel);

        // Text
        JPanel textPanel =
                new JPanel();

        textPanel.setLayout(
                new BoxLayout(
                        textPanel,
                        BoxLayout.Y_AXIS
                )
        );

        textPanel.setOpaque(false);

        textPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        2, 15, 0, 0
                )
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        titleLabel.setForeground(
                DARK_TEXT
        );

        JLabel descriptionLabel =
                new JLabel(
                        "<html>"
                        + description
                        + "</html>"
                );

        descriptionLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        descriptionLabel.setForeground(
                LIGHT_TEXT
        );

        textPanel.add(titleLabel);

        textPanel.add(
                Box.createVerticalStrut(6)
        );

        textPanel.add(descriptionLabel);

        card.add(
                iconPanel,
                BorderLayout.WEST
        );

        card.add(
                textPanel,
                BorderLayout.CENTER
        );

        card.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        card.addMouseListener(
                new MouseAdapter() {

                    public void mouseEntered(
                            MouseEvent e
                    ) {

                        card.setBackground(
                                new Color(
                                        252,
                                        250,
                                        254
                                )
                        );

                        card.repaint();
                    }

                    public void mouseExited(
                            MouseEvent e
                    ) {

                        card.setBackground(
                                WHITE
                        );

                        card.repaint();
                    }
                }
        );

        return card;
    }

    // =====================================================
    // ROUNDED PANEL
    // =====================================================

    private class RoundedPanel
            extends JPanel {

        private int radius;
        private Color backgroundColor;

        public RoundedPanel(
                int radius,
                Color backgroundColor
        ) {

            this.radius = radius;
            this.backgroundColor =
                    backgroundColor;

            setOpaque(false);
        }

        @Override
        protected void paintComponent(
                Graphics g
        ) {

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(
                    backgroundColor
            );

            g2.fillRoundRect(
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    radius,
                    radius
            );

            g2.dispose();

            super.paintComponent(g);
        }
    }

    // =====================================================
    // MAIN
    // =====================================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(() -> {

            new DashboardFrame(
                    "Divyansh Singh",
                    1
            ).setVisible(true);

        });
    }
}