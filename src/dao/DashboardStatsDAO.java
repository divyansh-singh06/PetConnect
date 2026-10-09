package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DatabaseConnection;

public class DashboardStatsDAO {

    // Count all available pets
    public int getAvailablePets() {

        String sql =
            "SELECT COUNT(*) FROM pets "
          + "WHERE adopted = FALSE";

        try (Connection connection =
                 DatabaseConnection.getConnection();
             PreparedStatement statement =
                 connection.prepareStatement(sql);
             ResultSet result =
                 statement.executeQuery()) {

            if (result.next()) {
                return result.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    // Count pets in the user's wishlist
    public int getWishlistCount(int userId) {

        String sql =
            "SELECT COUNT(*) FROM wishlist "
          + "WHERE user_id = ?";

        try (Connection connection =
                 DatabaseConnection.getConnection();
             PreparedStatement statement =
                 connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            ResultSet result =
                statement.executeQuery();

            if (result.next()) {
                return result.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    // Count adoption requests sent by the user
    public int getMyRequestsCount(int userId) {

        String sql =
            "SELECT COUNT(*) FROM adoption_requests "
          + "WHERE adopter_id = ?";

        try (Connection connection =
                 DatabaseConnection.getConnection();
             PreparedStatement statement =
                 connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            ResultSet result =
                statement.executeQuery();

            if (result.next()) {
                return result.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    // Count pets adopted by the user
    public int getAdoptedPetsCount(int userId) {

        String sql =
            "SELECT COUNT(*) FROM adoptions "
          + "WHERE user_id = ?";

        try (Connection connection =
                 DatabaseConnection.getConnection();
             PreparedStatement statement =
                 connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            ResultSet result =
                statement.executeQuery();

            if (result.next()) {
                return result.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}
