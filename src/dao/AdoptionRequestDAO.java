package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DatabaseConnection;

public class AdoptionRequestDAO {

    // Send an adoption request
   public boolean sendRequest(int petId, int adopterId) {

    // Check whether the logged-in user owns this pet
    String ownerSQL =
        "SELECT owner_id FROM pets WHERE id = ?";

    try (Connection connection =
             DatabaseConnection.getConnection();
         PreparedStatement ownerStatement =
             connection.prepareStatement(ownerSQL)) {

        ownerStatement.setInt(1, petId);

        ResultSet result = ownerStatement.executeQuery();

        if (result.next()) {

            int ownerId = result.getInt("owner_id");

            // User cannot request their own pet
            if (ownerId == adopterId) {
                return false;
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }


    // Send the adoption request
    String requestSQL =
        "INSERT INTO adoption_requests "
      + "(pet_id, adopter_id, status) "
      + "VALUES (?, ?, 'PENDING')";

    try (Connection connection =
             DatabaseConnection.getConnection();
         PreparedStatement statement =
             connection.prepareStatement(requestSQL)) {

        statement.setInt(1, petId);
        statement.setInt(2, adopterId);

        int rowsInserted =
            statement.executeUpdate();

        return rowsInserted > 0;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
    // Get requests received by a pet owner
    public ArrayList<String> getRequestsForOwner(int ownerId) {

        ArrayList<String> requests = new ArrayList<>();

        String sql =
            "SELECT ar.id, p.name AS pet_name, "
          + "u.name AS adopter_name, u.email AS adopter_email, "
          + "ar.status "
          + "FROM adoption_requests ar "
          + "JOIN pets p ON ar.pet_id = p.id "
          + "JOIN users u ON ar.adopter_id = u.id "
          + "WHERE p.owner_id = ? "
          + "ORDER BY ar.request_date DESC";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                 connection.prepareStatement(sql)) {

            statement.setInt(1, ownerId);

            ResultSet result = statement.executeQuery();

            while (result.next()) {

                String request =
                    "Request ID: " + result.getInt("id")
                    + " | Pet: " + result.getString("pet_name")
                    + " | Adopter: " + result.getString("adopter_name")
                    + " | Email: " + result.getString("adopter_email")
                    + " | Status: " + result.getString("status");

                requests.add(request);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return requests;
    }
    // Accept an adoption request
public boolean acceptRequest(int requestId) {

    String getRequestSQL =
        "SELECT pet_id, adopter_id "
      + "FROM adoption_requests "
      + "WHERE id = ? AND status = 'PENDING'";

    String updateRequestSQL =
        "UPDATE adoption_requests "
      + "SET status = 'ACCEPTED' "
      + "WHERE id = ?";

    String updatePetSQL =
        "UPDATE pets "
      + "SET adopted = TRUE "
      + "WHERE id = ?";

    String addAdoptionSQL =
        "INSERT INTO adoptions (user_id, pet_id) "
      + "VALUES (?, ?)";

    try (Connection connection =
             DatabaseConnection.getConnection()) {

        // Get pet and adopter from the request
        int petId;
        int adopterId;

        try (PreparedStatement statement =
                 connection.prepareStatement(getRequestSQL)) {

            statement.setInt(1, requestId);

            ResultSet result = statement.executeQuery();

            if (!result.next()) {
                return false;
            }

            petId = result.getInt("pet_id");
            adopterId = result.getInt("adopter_id");
        }

        // Start transaction
        connection.setAutoCommit(false);

        try {

            // 1. Mark request as ACCEPTED
            try (PreparedStatement statement =
                     connection.prepareStatement(updateRequestSQL)) {

                statement.setInt(1, requestId);
                statement.executeUpdate();
            }

            // 2. Mark pet as adopted
            try (PreparedStatement statement =
                     connection.prepareStatement(updatePetSQL)) {

                statement.setInt(1, petId);
                statement.executeUpdate();
            }

            // 3. Add adoption record
            try (PreparedStatement statement =
                     connection.prepareStatement(addAdoptionSQL)) {

                statement.setInt(1, adopterId);
                statement.setInt(2, petId);
                statement.executeUpdate();
            }

            // Everything succeeded
            connection.commit();

            return true;

        } catch (Exception e) {

            // Undo everything if something fails
            connection.rollback();

            e.printStackTrace();

            return false;
        }

    } catch (Exception e) {

        e.printStackTrace();

        return false;
    }
}
// Reject an adoption request
public boolean rejectRequest(int requestId) {

    String sql =
        "UPDATE adoption_requests "
      + "SET status = 'REJECTED' "
      + "WHERE id = ?";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement =
             connection.prepareStatement(sql)) {

        statement.setInt(1, requestId);

        int rowsUpdated = statement.executeUpdate();

        return rowsUpdated > 0;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
// Get adoption requests sent by an adopter
public ArrayList<String> getRequestsByAdopter(int adopterId) {

    ArrayList<String> requests = new ArrayList<>();

    String sql =
        "SELECT ar.id, p.name AS pet_name, "
      + "u.name AS owner_name, "
      + "ar.status, ar.request_date "
      + "FROM adoption_requests ar "
      + "JOIN pets p ON ar.pet_id = p.id "
      + "JOIN users u ON p.owner_id = u.id "
      + "WHERE ar.adopter_id = ? "
      + "ORDER BY ar.request_date DESC";

    try (Connection connection =
             DatabaseConnection.getConnection();
         PreparedStatement statement =
             connection.prepareStatement(sql)) {

        statement.setInt(1, adopterId);

        ResultSet result = statement.executeQuery();

        while (result.next()) {

            String request =
                "Request ID: " + result.getInt("id")
                + " | Pet: " + result.getString("pet_name")
                + " | Owner: " + result.getString("owner_name")
                + " | Status: " + result.getString("status")
                + " | Date: " + result.getTimestamp("request_date");

            requests.add(request);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return requests;
}
public String getAdopterDetails(int requestId) {

    String sql =
        "SELECT u.name, u.email, u.address "
      + "FROM adoption_requests ar "
      + "JOIN users u ON ar.adopter_id = u.id "
      + "WHERE ar.id = ? "
      + "AND ar.status = 'ACCEPTED'";

    try (Connection connection =
             DatabaseConnection.getConnection();
         PreparedStatement statement =
             connection.prepareStatement(sql)) {

        statement.setInt(1, requestId);

        ResultSet result =
            statement.executeQuery();

        if (result.next()) {

            return "Name: "
                + result.getString("name")
                + "\nEmail: "
                + result.getString("email")
                + "\nAddress: "
                + result.getString("address");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}
public String getOwnerDetails(int requestId) {

    String sql =
        "SELECT u.name, u.email, u.address "
      + "FROM adoption_requests ar "
      + "JOIN pets p ON ar.pet_id = p.id "
      + "JOIN users u ON p.owner_id = u.id "
      + "WHERE ar.id = ? "
      + "AND ar.status = 'ACCEPTED'";

    try (Connection connection =
             DatabaseConnection.getConnection();
         PreparedStatement statement =
             connection.prepareStatement(sql)) {

        statement.setInt(1, requestId);

        ResultSet result =
            statement.executeQuery();

        if (result.next()) {

            return "Name: "
                + result.getString("name")
                + "\nEmail: "
                + result.getString("email")
                + "\nAddress: "
                + result.getString("address");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}
}