package bean;

public class User {
    private String name;
    private String lastName;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User(String name, String lastName, String type){
        this.name = name;
        this.lastName = lastName;
        this.type = type;
    }

}
