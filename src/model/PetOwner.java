package model;

public class PetOwner extends User {

    private String address;

    public PetOwner(int id, String name, String email, String password, String address) {
        super(id, name, email, password);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
