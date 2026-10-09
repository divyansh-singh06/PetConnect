 import model.Pet;
import interfaces.Adoptable;

public class TestPolymorphism {

    public static void main(String[] args) {

        Adoptable pet = new Pet(
            1,
            "Bruno",
            "Dog",
            "Labrador",
            3
        );

        pet.adopt();
    }

    
}
