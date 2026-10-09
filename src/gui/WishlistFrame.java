package gui;

import dao.WishlistDAO;
import model.Pet;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WishlistFrame extends JFrame {

    private int userId;
    private WishlistDAO wishlistDAO;

    private DefaultListModel<String> listModel;
    private JList<String> wishlistList;
    private JLabel statusLabel;
    private JButton removeButton;
    private JButton refreshButton;

    private ArrayList<Pet> wishlistPets;

    public WishlistFrame(int userId) {

        this.userId = userId;
        this.wishlistDAO = new WishlistDAO();

        setTitle("My Wishlist");
        setSize(800, 550);
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

        // Title
        JLabel titleLabel =
            new JLabel("❤️ My Wishlist");

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

        // Wishlist list
        listModel =
            new DefaultListModel<>();

        wishlistList =
            new JList<>(listModel);

        wishlistList.setFont(
            new Font("Arial", Font.PLAIN, 14)
        );

        wishlistList.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane =
            new JScrollPane(wishlistList);

        mainPanel.add(
            scrollPane,
            BorderLayout.CENTER
        );

        // Buttons
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

        removeButton =
            new JButton("🗑 Remove");

        refreshButton =
            new JButton("🔄 Refresh");

        removeButton.setFont(
            new Font("Arial", Font.BOLD, 14)
        );

        refreshButton.setFont(
            new Font("Arial", Font.BOLD, 14)
        );

        removeButton.setBackground(
            new Color(220, 80, 80)
        );

        removeButton.setForeground(Color.WHITE);
        removeButton.setFocusPainted(false);

        refreshButton.setBackground(Color.WHITE);
        refreshButton.setFocusPainted(false);

        buttonPanel.add(removeButton);
        buttonPanel.add(refreshButton);

        // Status
        statusLabel =
            new JLabel("Loading wishlist...");

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

        // Button actions
        removeButton.addActionListener(e ->
            removeSelectedPet()
        );

        refreshButton.addActionListener(e ->
            loadWishlist()
        );

        // Load wishlist when screen opens
        loadWishlist();
    }

    private void loadWishlist() {

        listModel.clear();

        wishlistPets =
            wishlistDAO.getWishlist(userId);

        for (Pet pet : wishlistPets) {

            String petInfo =
                "🐾 " + pet.getName()
                + " | Type: " + pet.getType()
                + " | Breed: " + pet.getBreed()
                + " | Age: " + pet.getAge()
                + (pet.isAdopted()
                    ? " | ADOPTED"
                    : " | Available");

            listModel.addElement(petInfo);
        }

        if (wishlistPets.isEmpty()) {

            statusLabel.setText(
                "Your wishlist is empty."
            );

        } else {

            statusLabel.setText(
                wishlistPets.size()
                + " pet(s) in your wishlist."
            );
        }
    }

    private void removeSelectedPet() {

        int selectedIndex =
            wishlistList.getSelectedIndex();

        if (selectedIndex == -1) {

            JOptionPane.showMessageDialog(
                this,
                "Please select a pet first.",
                "No Pet Selected",
                JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        Pet selectedPet =
            wishlistPets.get(selectedIndex);

        int confirmation =
            JOptionPane.showConfirmDialog(
                this,
                "Remove "
                + selectedPet.getName()
                + " from your wishlist?",
                "Remove from Wishlist",
                JOptionPane.YES_NO_OPTION
            );

        if (confirmation != JOptionPane.YES_OPTION) {
            return;
        }

        boolean success =
            wishlistDAO.removeFromWishlist(
                userId,
                selectedPet.getId()
            );

        if (success) {

            JOptionPane.showMessageDialog(
                this,
                selectedPet.getName()
                + " has been removed from your wishlist.",
                "Removed",
                JOptionPane.INFORMATION_MESSAGE
            );

            loadWishlist();

        } else {

            JOptionPane.showMessageDialog(
                this,
                "Unable to remove the pet.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}