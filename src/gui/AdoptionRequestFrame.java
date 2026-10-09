
package gui;

import dao.AdoptionRequestDAO;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AdoptionRequestFrame extends JFrame {

    private int adopterId;
    private AdoptionRequestDAO requestDAO;

    private DefaultListModel<String> listModel;
    private JList<String> requestList;
    private JLabel statusLabel;

    public AdoptionRequestFrame(int adopterId) {

        this.adopterId = adopterId;
        this.requestDAO = new AdoptionRequestDAO();

        setTitle("My Adoption Requests");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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
            new JLabel("📤 My Adoption Requests");

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

        JScrollPane scrollPane =
            new JScrollPane(requestList);

        mainPanel.add(
            scrollPane,
            BorderLayout.CENTER
        );

        // =========================
        // BOTTOM PANEL
        // =========================

        JPanel bottomPanel =
            new JPanel(new BorderLayout(10, 10));

        bottomPanel.setBackground(
            new Color(250, 247, 252)
        );

        // Status
        statusLabel =
            new JLabel("Loading requests...");

        bottomPanel.add(
            statusLabel,
            BorderLayout.CENTER
        );

        // View owner details button
        JButton ownerDetailsButton =
            new JButton("View Owner Details");

        ownerDetailsButton.setFont(
            new Font("Arial", Font.BOLD, 14)
        );

        ownerDetailsButton.setForeground(
            Color.WHITE
        );

        ownerDetailsButton.setBackground(
            new Color(124, 92, 166)
        );

        ownerDetailsButton.setFocusPainted(false);

        ownerDetailsButton.addActionListener(e -> {

            int selectedIndex =
                requestList.getSelectedIndex();

            if (selectedIndex == -1) {

                JOptionPane.showMessageDialog(
                    this,
                    "Please select an adoption request first.",
                    "No Request Selected",
                    JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            String selectedRequest =
                listModel.getElementAt(selectedIndex);

            // Owner details are only available after acceptance
            if (!selectedRequest.contains(
                    "Status: ACCEPTED")) {

                JOptionPane.showMessageDialog(
                    this,
                    "Owner contact details are available "
                    + "only after the request is accepted.",
                    "Request Not Accepted",
                    JOptionPane.INFORMATION_MESSAGE
                );

                return;
            }

            int requestId =
                extractRequestId(selectedRequest);

            if (requestId == -1) {

                JOptionPane.showMessageDialog(
                    this,
                    "Unable to identify the selected request.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            String ownerDetails =
                requestDAO.getOwnerDetails(requestId);

            if (ownerDetails != null) {

                JOptionPane.showMessageDialog(
                    this,
                    "You can now contact the pet owner:\n\n"
                    + ownerDetails,
                    "Owner Contact Details",
                    JOptionPane.INFORMATION_MESSAGE
                );

            } else {

                JOptionPane.showMessageDialog(
                    this,
                    "Owner details could not be found.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        });

        bottomPanel.add(
            ownerDetailsButton,
            BorderLayout.EAST
        );

        mainPanel.add(
            bottomPanel,
            BorderLayout.SOUTH
        );

        // Add main panel to frame
        add(mainPanel);

        // Load requests
        loadRequests();
    }

    // =========================
    // LOAD REQUESTS
    // =========================

    private void loadRequests() {

        listModel.clear();

        ArrayList<String> requests =
            requestDAO.getRequestsByAdopter(
                adopterId
            );

        for (String request : requests) {

            listModel.addElement(request);
        }

        if (requests.isEmpty()) {

            statusLabel.setText(
                "You have not sent any adoption requests."
            );

        } else {

            statusLabel.setText(
                requests.size()
                + " request(s) found."
            );
        }
    }

    // =========================
    // EXTRACT REQUEST ID
    // =========================

    private int extractRequestId(String request) {

        try {

            String prefix =
                "Request ID: ";

            int start =
                request.indexOf(prefix)
                + prefix.length();

            int end =
                request.indexOf(
                    " |",
                    start
                );

            return Integer.parseInt(
                request.substring(
                    start,
                    end
                ).trim()
            );

        } catch (Exception e) {

            e.printStackTrace();

            return -1;
        }
    }
}

