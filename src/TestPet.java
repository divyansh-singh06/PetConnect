import model.Pet;

public class TestPet {

    public static void main(String[] args) {

        Pet bruno = new Pet(1, "Bruno", "Dog", "Labrador", 3);

        System.out.println("Pet Name: " + bruno.getName());
        System.out.println("Type: " + bruno.getType());
        System.out.println("Breed: " + bruno.getBreed());
        System.out.println("Age: " + bruno.getAge());
        System.out.println("Adopted: " + bruno.isAdopted());
    }
}