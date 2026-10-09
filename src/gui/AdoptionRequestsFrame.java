package gui;

import dao.AdoptionRequestDAO;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AdoptionRequestsFrame extends JFrame {

    private int ownerId;
    private AdoptionRequestDAO requestDAO;

    private DefaultListModel<String> listModel;
    private JList<String> requestList;
    private JLabel statusLabel;

    private JButton acceptButton;
    private JButton rejectButton;
    private JButton refreshButton;

    public AdoptionRequestsFrame(int ownerId) {

        this.ownerId = ownerId;
        this.requestDAO = new AdoptionRequestDAO();

        setTitle("Adoption Requests");
        setSize(850, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Main panel
        JPanel mainPanel =
            new JPanel(new BorderLayout(15, 15));

        mainPanel.setBackground(
            new Color(250, 247, 252)
        );

        mainPanel.setBorder(
            BorderFactory.createEmptyBorder(
                20, 20, 20, 20
            )
        );

        // =========================
        // TITLE
        // =========================

        JLabel titleLabel =
            new JLabel("📥 Adoption Requests");

        titleLabel.setFont(
            new Font("Arial", Font.BOLD, 24)
        );

        titleLabel.setForeground(
            new Color(124, 92, 166)
        );

        mainPanel.add(
            titleLabel,
            BorderLayout.NORTH
        );

        // =========================
        // REQUEST LIST
        // =========================

        listModel =
            new DefaultListModel<>();

        requestList =
            new JList<>(listModel);

        requestList.setFont(
            new Font("Arial", Font.PLAIN, 14)
        );

        requestList.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane =
            new JScrollPane(requestList);

        mainPanel.add(
            scrollPane,
            BorderLayout.CENTER
        );

        // =========================
        // BUTTON PANEL
        // =========================

        JPanel buttonPanel =
            new JPanel(
                new FlowLayout(
                    FlowLayout.CENTER,
                    12,
                    10
                )
            );

        buttonPanel.setBackground(
            new Color(250, 247, 252)
        );

        acceptButton =
            new JButton("✅ Accept");

        rejectButton =
            new JButton("❌ Reject");

        refreshButton =
            new JButton("🔄 Refresh");

        acceptButton.setFont(
            new Font("Arial", Font.BOLD, 14)
        );

        rejectButton.setFont(
            new Font("Arial", Font.BOLD, 14)
        );

        refreshButton.setFont(
            new Font("Arial", Font.BOLD, 14)
        );

        acceptButton.setBackground(
            new Color(76, 175, 80)
        );

        acceptButton.setForeground(Color.WHITE);
        acceptButton.setFocusPainted(false);

        rejectButton.setBackground(
            new Color(220, 80, 80)
        );

        rejectButton.setForeground(Color.WHITE);
        rejectButton.setFocusPainted(false);

        refreshButton.setBackground(Color.WHITE);
        refreshButton.setFocusPainted(false);

        buttonPanel.add(acceptButton);
        buttonPanel.add(rejectButton);
        buttonPanel.add(refreshButton);

        // =========================
        // STATUS
        // =========================

        statusLabel =
            new JLabel("Loading requests...");

        statusLabel.setFont(
            new Font("Arial", Font.PLAIN, 13)
        );

        statusLabel.setForeground(
            new Color(110, 105, 115)
        );

        JPanel bottomPanel =
            new JPanel(new BorderLayout());

        bottomPanel.setBackground(
            new Color(250, 247, 252)
        );

        bottomPanel.add(
            statusLabel,
            BorderLayout.WEST
        );

        bottomPanel.add(
            buttonPanel,
            BorderLayout.CENTER
        );

        mainPanel.add(
            bottomPanel,
            BorderLayout.SOUTH
        );

        add(mainPanel);

        // =========================
        // BUTTON ACTIONS
        // =========================

        acceptButton.addActionListener(e ->
            acceptRequest()
        );

        rejectButton.addActionListener(e ->
            rejectRequest()
        );

        refreshButton.addActionListener(e ->
            loadRequests()
        );

        // Load requests when screen opens
        loadRequests();
    }

    // =========================
    // LOAD REQUESTS
    // =========================

    private void loadRequests() {

        listModel.clear();

        ArrayList<String> requests =
            requestDAO.getRequestsForOwner(
                ownerId
            );

        for (String request : requests) {

            listModel.addElement(request);
        }

        if (requests.isEmpty()) {

            statusLabel.setText(
                "No adoption requests found."
            );

        } else {

            statusLabel.setText(
                requests.size()
                + " request(s) found."
            );
        }
    }

    // =========================
    // GET REQUEST ID
    // =========================

    private int getSelectedRequestId() {

        int selectedIndex =
            requestList.getSelectedIndex();

        if (selectedIndex == -1) {

            return -1;
        }

        String selectedRequest =
            listModel.getElementAt(
                selectedIndex
            );

        try {

            String[] parts =
                selectedRequest.split("\\|");

            String requestPart =
                parts[0].trim();

            return Integer.parseInt(
                requestPart
                    .replace("Request ID:", "")
                    .trim()
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "Unable to identify the selected request.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );

            return -1;
        }
    }

    // =========================
    // ACCEPT REQUEST
    // =========================

    private void acceptRequest() {

    int requestId =
        getSelectedRequestId();

    if (requestId == -1) {

        JOptionPane.showMessageDialog(
            this,
            "Please select an adoption request first.",
            "No Request Selected",
            JOptionPane.WARNING_MESSAGE
        );

        return;
    }

    int confirmation =
        JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to accept this adoption request?",
            "Accept Request",
            JOptionPane.YES_NO_OPTION
        );

    if (confirmation != JOptionPane.YES_OPTION) {

        return;
    }

    boolean success =
        requestDAO.acceptRequest(
            requestId
        );

    if (success) {

        String adopterDetails =
            requestDAO.getAdopterDetails(requestId);

        if (adopterDetails != null) {

            JOptionPane.showMessageDialog(
                this,
                "Adoption request accepted!\n\n"
                + "You can now contact the adopter:\n\n"
                + adopterDetails,
                "Adopter Contact Details",
                JOptionPane.INFORMATION_MESSAGE
            );

        } else {

            JOptionPane.showMessageDialog(
                this,
                "Adoption request accepted successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
            );
        }

        loadRequests();

    } else {

        JOptionPane.showMessageDialog(
            this,
            "Unable to accept the request.",
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
}
    // =========================
    // REJECT REQUEST
    // =========================

    private void rejectRequest() {

        int requestId =
            getSelectedRequestId();

        if (requestId == -1) {

            JOptionPane.showMessageDialog(
                this,
                "Please select an adoption request first.",
                "No Request Selected",
                JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int confirmation =
            JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to reject this adoption request?",
                "Reject Request",
                JOptionPane.YES_NO_OPTION
            );

        if (confirmation != JOptionPane.YES_OPTION) {

            return;
        }

        boolean success =
            requestDAO.rejectRequest(
                requestId
            );

        if (success) {

            JOptionPane.showMessageDialog(
                this,
                "Adoption request rejected.",
                "Request Rejected",
                JOptionPane.INFORMATION_MESSAGE
            );

            loadRequests();

        } else {

            JOptionPane.showMessageDialog(
                this,
                "Unable to reject the request.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}