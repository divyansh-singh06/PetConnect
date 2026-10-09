import model.PetOwner;

public class TestInheritance {

    public static void main(String[] args) {

        PetOwner owner = new PetOwner(
            1,
            "Divyansh",
            "Divyansh@example.com",
            "1234",
            "Delhi"
        );

        System.out.println("Name: " + owner.getName());
        System.out.println("Email: " + owner.getEmail());
        System.out.println("Address: " + owner.getAddress());
    }
}
