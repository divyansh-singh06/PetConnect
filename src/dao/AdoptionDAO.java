package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DatabaseConnection;

public class AdoptionDAO {

    // Record a new adoption
    public boolean addAdoption(int userId, int petId) {

        String sql = "INSERT INTO adoptions (user_id, pet_id) "
                   + "VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            statement.setInt(2, petId);

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get adoption history
    public ArrayList<String> getAdoptionHistory(int userId) {

        ArrayList<String> history = new ArrayList<>();

        String sql =
            "SELECT p.name, p.type, p.breed, a.adoption_date "
          + "FROM adoptions a "
          + "JOIN pets p ON a.pet_id = p.id "
          + "WHERE a.user_id = ? "
          + "ORDER BY a.adoption_date DESC";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            ResultSet result = statement.executeQuery();

            while (result.next()) {

                String record =
                    "Pet: " + result.getString("name")
                    + " | Type: " + result.getString("type")
                    + " | Breed: " + result.getString("breed")
                    + " | Date: " + result.getTimestamp("adoption_date");

                history.add(record);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return history;
    }
}
