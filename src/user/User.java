package user;

import util.Hash;

public class User {

    private String username;
    private String passwordHash;

    private String name;
    private String lastName;
    public enum Type {
        USER,
        ADMINISTRATOR
    }
    private Type type;


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

    public String getUsername () { return username;}

    public void setUsername (String username) { this.username = username;}

    public Type getType() {
        return this.type;
    }

    public void setType(String type) throws UserIlegalTypeChangeException {
        if (type.equals("ADMINISTRATOR")) {

            throw new UserIlegalTypeChangeException("No se puede convertir un usuario a administrador");
        }
        this.type = Type.valueOf(type);
    }

    public boolean matchPassword(String enteredPassword) {
        return Hash.getStringHash(enteredPassword).equals(this.passwordHash);
    }

    public String changePassword (String oldPassword, String newPassword) {
        if (!Hash.getStringHash(oldPassword).equals(this.passwordHash)) {
            return "incorrect_old_password";
        }

        if (newPassword.length()<4) {
            return "password_too_short";
        }

        this.passwordHash = Hash.getStringHash(newPassword);

        return "success";
    }

    public User(String username, String name, String lastName, Type type, String password) {
        this.name = name;
        this.lastName = lastName;
        this.type = type;
        this.username = username;
        this.passwordHash = Hash.getStringHash(password);
    }

}
