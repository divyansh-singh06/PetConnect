package model;
import interfaces.Adoptable;

public class Pet implements Adoptable  {

    private int id;
    private String name;
    private String type;
    private String breed;
    private int age;
    private boolean adopted;

    public Pet(int id, String name, String type, String breed, int age) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.breed = breed;
        this.age = age;
        this.adopted = false;
    }
    @Override
public void adopt() {
    adopted = true;
    System.out.println(name + " has been adopted!");
}
    public int getId() {
    return id;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public String getType() {
    return type;
}

public void setType(String type) {
    this.type = type;
}

public String getBreed() {
    return breed;
}

public void setBreed(String breed) {
    this.breed = breed;
}

public int getAge() {
    return age;
}

public void setAge(int age) {
    this.age = age;
}

public boolean isAdopted() {
    return adopted;
}

public void setAdopted(boolean adopted) {
    this.adopted = adopted;
}
}