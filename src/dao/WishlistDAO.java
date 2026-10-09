package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Pet;
import util.DatabaseConnection;

public class WishlistDAO {

    // Add a pet to the user's wishlist
    public boolean addToWishlist(int userId, int petId) {

        String sql =
            "INSERT INTO wishlist (user_id, pet_id) "
          + "VALUES (?, ?)";

        try (Connection connection =
                 DatabaseConnection.getConnection();
             PreparedStatement statement =
                 connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            statement.setInt(2, petId);

            int rowsInserted =
                statement.executeUpdate();

            return rowsInserted > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Remove a pet from the user's wishlist
    public boolean removeFromWishlist(int userId, int petId) {

        String sql =
            "DELETE FROM wishlist "
          + "WHERE user_id = ? AND pet_id = ?";

        try (Connection connection =
                 DatabaseConnection.getConnection();
             PreparedStatement statement =
                 connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            statement.setInt(2, petId);

            int rowsDeleted =
                statement.executeUpdate();

            return rowsDeleted > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all pets saved by a user
    public ArrayList<Pet> getWishlist(int userId) {

        ArrayList<Pet> wishlist =
            new ArrayList<>();

        String sql =
            "SELECT p.id, p.name, p.type, p.breed, "
          + "p.age, p.adopted "
          + "FROM wishlist w "
          + "JOIN pets p ON w.pet_id = p.id "
          + "WHERE w.user_id = ? "
          + "ORDER BY w.added_date DESC";

        try (Connection connection =
                 DatabaseConnection.getConnection();
             PreparedStatement statement =
                 connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            ResultSet result =
                statement.executeQuery();

            while (result.next()) {

                Pet pet =
                    new Pet(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("type"),
                        result.getString("breed"),
                        result.getInt("age")
                    );

                pet.setAdopted(
                    result.getBoolean("adopted")
                );

                wishlist.add(pet);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return wishlist;
    }

    // Check whether a pet is already in the wishlist
    public boolean isInWishlist(int userId, int petId) {

        String sql =
            "SELECT id FROM wishlist "
          + "WHERE user_id = ? AND pet_id = ?";

        try (Connection connection =
                 DatabaseConnection.getConnection();
             PreparedStatement statement =
                 connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            statement.setInt(2, petId);

            ResultSet result =
                statement.executeQuery();

            return result.next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}