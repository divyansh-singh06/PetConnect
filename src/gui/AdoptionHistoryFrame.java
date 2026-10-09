package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

import dao.AdoptionDAO;

public class AdoptionHistoryFrame extends JFrame {

    private AdoptionDAO adoptionDAO;
    private DefaultListModel<String> listModel;
    private JLabel statusLabel;

    // PetConnect colors
    private final Color PRIMARY = new Color(124, 92, 166);
    private final Color BACKGROUND = new Color(250, 247, 252);
    private final Color CARD_WHITE = Color.WHITE;
    private final Color DARK_TEXT = new Color(55, 50, 60);
    private final Color LIGHT_TEXT = new Color(110, 105, 115);
    private final Color SUCCESS = new Color(76, 175, 80);

    public AdoptionHistoryFrame(int userId) {

        adoptionDAO = new AdoptionDAO();

        setTitle("PetConnect - Adoption History");
        setSize(750, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // =========================
        // MAIN PANEL
        // =========================

        JPanel mainPanel = new JPanel(
            new BorderLayout()
        );

        mainPanel.setBackground(BACKGROUND);

        // =========================
        // HEADER
        // =========================

        JPanel headerPanel = new JPanel();

        headerPanel.setLayout(
            new BoxLayout(
                headerPanel,
                BoxLayout.Y_AXIS
            )
        );

        headerPanel.setBackground(PRIMARY);

        headerPanel.setBorder(
            new EmptyBorder(
                25, 30, 25, 30
            )
        );

        JLabel titleLabel = new JLabel(
            "📋  My Adoption History"
        );

        titleLabel.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                28
            )
        );

        titleLabel.setForeground(Color.WHITE);

        titleLabel.setAlignmentX(
            Component.CENTER_ALIGNMENT
        );

        JLabel subtitleLabel = new JLabel(
            "A record of the pets you've given a home"
        );

        subtitleLabel.setFont(
            new Font(
                "Arial",
                Font.PLAIN,
                14
            )
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

        JPanel contentCard = new JPanel(
            new BorderLayout()
        );

        contentCard.setBackground(
            CARD_WHITE
        );

        contentCard.setBorder(
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

        // =========================
        // SECTION TITLE
        // =========================

        JPanel topPanel = new JPanel(
            new BorderLayout()
        );

        topPanel.setOpaque(false);

        JLabel sectionLabel = new JLabel(
            "❤️  Your Adoptions"
        );

        sectionLabel.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                19
            )
        );

        sectionLabel.setForeground(
            DARK_TEXT
        );

        topPanel.add(
            sectionLabel,
            BorderLayout.WEST
        );

        // =========================
        // LIST
        // =========================

        listModel =
            new DefaultListModel<>();

        JList<String> historyList =
            new JList<>(listModel);

        historyList.setFont(
            new Font(
                "Arial",
                Font.PLAIN,
                14
            )
        );

        historyList.setForeground(
            DARK_TEXT
        );

        historyList.setBackground(
            new Color(253, 251, 254)
        );

        historyList.setSelectionBackground(
            new Color(235, 225, 245)
        );

        historyList.setSelectionForeground(
            DARK_TEXT
        );

        historyList.setFixedCellHeight(55);

        historyList.setBorder(
            BorderFactory.createEmptyBorder(
                5, 5, 5, 5
            )
        );

        JScrollPane scrollPane =
            new JScrollPane(historyList);

        scrollPane.setBorder(
            BorderFactory.createLineBorder(
                new Color(230, 225, 235)
            )
        );

        scrollPane.getVerticalScrollBar()
            .setUnitIncrement(16);

        // =========================
        // STATUS
        // =========================

        statusLabel = new JLabel(
            "  Loading adoption history..."
        );

        statusLabel.setFont(
            new Font(
                "Arial",
                Font.PLAIN,
                13
            )
        );

        statusLabel.setForeground(
            LIGHT_TEXT
        );

        statusLabel.setBorder(
            new EmptyBorder(
                12, 5, 5, 5
            )
        );

        // =========================
        // CLOSE BUTTON
        // =========================

        JButton closeButton =
            new JButton("Close");

        closeButton.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                14
            )
        );

        closeButton.setForeground(
            Color.WHITE
        );

        closeButton.setBackground(
            PRIMARY
        );

        closeButton.setFocusPainted(false);

        closeButton.setBorder(
            new EmptyBorder(
                10, 25, 10, 25
            )
        );

        closeButton.addActionListener(
            e -> dispose()
        );

        JPanel bottomPanel =
            new JPanel(
                new BorderLayout()
            );

        bottomPanel.setBackground(
            BACKGROUND
        );

        bottomPanel.setBorder(
            new EmptyBorder(
                10, 30, 18, 30
            )
        );

        JLabel footerLabel =
            new JLabel(
                "Every adoption is a new beginning ❤️"
            );

        footerLabel.setFont(
            new Font(
                "Arial",
                Font.ITALIC,
                13
            )
        );

        footerLabel.setForeground(
            LIGHT_TEXT
        );

        bottomPanel.add(
            footerLabel,
            BorderLayout.WEST
        );

        bottomPanel.add(
            closeButton,
            BorderLayout.EAST
        );

        // =========================
        // ADD CONTENT
        // =========================

        contentCard.add(
            topPanel,
            BorderLayout.NORTH
        );

        contentCard.add(
            scrollPane,
            BorderLayout.CENTER
        );

        contentCard.add(
            statusLabel,
            BorderLayout.SOUTH
        );

        JPanel centerPanel =
            new JPanel(
                new GridBagLayout()
            );

        centerPanel.setBackground(
            BACKGROUND
        );

        centerPanel.setBorder(
            new EmptyBorder(
                25, 30, 10, 30
            )
        );

        centerPanel.add(contentCard);

        mainPanel.add(
            headerPanel,
            BorderLayout.NORTH
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

        // =========================
        // SWINGWORKER
        // =========================

        SwingWorker<ArrayList<String>, Void> worker =
            new SwingWorker<ArrayList<String>, Void>() {

            @Override
            protected ArrayList<String> doInBackground()
                    throws Exception {

                // Database operation runs
                // in a background thread
                return adoptionDAO
                    .getAdoptionHistory(userId);
            }

            @Override
            protected void done() {

                try {

                    ArrayList<String> history =
                        get();

                    for (String record : history) {

                        listModel.addElement(
                            "🐾  " + record
                        );
                    }

                    if (history.isEmpty()) {

                        statusLabel.setText(
                            "  No adoption history found."
                        );

                    } else {

                        statusLabel.setText(
                            "  ✓ "
                            + history.size()
                            + " adoption(s) found."
                        );

                        statusLabel.setForeground(
                            SUCCESS
                        );
                    }

                } catch (Exception e) {

                    statusLabel.setText(
                        "  ✕ Failed to load adoption history."
                    );

                    statusLabel.setForeground(
                        new Color(190, 70, 70)
                    );

                    e.printStackTrace();
                }
            }
        };

        worker.execute();
    }
}