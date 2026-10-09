package service;

import java.util.ArrayList;
import model.Pet;

public class PetManager {

    private ArrayList<Pet> pets;

    public PetManager() {
        pets = new ArrayList<>();

        // Sample pets
        pets.add(new Pet(1, "Bruno", "Dog", "Labrador", 3));
        pets.add(new Pet(2, "Milo", "Cat", "Persian", 2));
        pets.add(new Pet(3, "Rocky", "Dog", "Beagle", 4));
    }

    public void addPet(Pet pet) {
        pets.add(pet);
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }
}