package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import util.DatabaseConnection;
import model.User;

public class UserDAO {

    public boolean registerUser(
            String name,
            String email,
            String password,
            String address) {

        String sql = "INSERT INTO users (name, email, password, address) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setString(4, address);

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }
   public User loginUser(String email, String password) {

    String sql = "SELECT id, name FROM users "
               + "WHERE email = ? AND password = ?";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setString(1, email);
        statement.setString(2, password);

        var result = statement.executeQuery();

        if (result.next()) {

            int id = result.getInt("id");
            String name = result.getString("name");

            return new User(id, name, email, password);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}

}
