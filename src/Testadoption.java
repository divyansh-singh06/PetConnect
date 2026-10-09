import model.Pet;

public class TestAdoption {

    public static void main(String[] args) {

        Pet bruno = new Pet(
            1,
            "Bruno",
            "Dog",
            "Labrador",
            3
        );

        System.out.println("Before adoption: " + bruno.isAdopted());

        bruno.adopt();

        System.out.println("After adoption: " + bruno.isAdopted());
    }
}