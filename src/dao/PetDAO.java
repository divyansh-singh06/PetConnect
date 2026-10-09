package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Pet;
import util.DatabaseConnection;

public class PetDAO {

    
    
// Add a new pet to the database
public boolean addPet(Pet pet, int ownerId) {

    String sql =
        "INSERT INTO pets "
        + "(name, type, breed, age, adopted, owner_id) "
        + "VALUES (?, ?, ?, ?, ?, ?)";

    try (Connection connection =
             DatabaseConnection.getConnection();
         PreparedStatement statement =
             connection.prepareStatement(sql)) {

        statement.setString(1, pet.getName());
        statement.setString(2, pet.getType());
        statement.setString(3, pet.getBreed());
        statement.setInt(4, pet.getAge());
        statement.setBoolean(5, pet.isAdopted());
        statement.setInt(6, ownerId);

        int rowsInserted =
            statement.executeUpdate();

        return rowsInserted > 0;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}



    // Get all pets from the database
    public ArrayList<Pet> getAllPets() {

        ArrayList<Pet> pets = new ArrayList<>();

        String sql = "SELECT * FROM pets";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {

                Pet pet = new Pet(
    result.getInt("id"),
    result.getString("name"),
    result.getString("type"),
    result.getString("breed"),
    result.getInt("age")
);

pet.setAdopted(result.getBoolean("adopted"));

pets.add(pet);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pets;
    }
    public boolean adoptPet(int petId) {

    String sql = "UPDATE pets SET adopted = TRUE WHERE id = ?";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setInt(1, petId);

        int rowsUpdated = statement.executeUpdate();

        return rowsUpdated > 0;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
}