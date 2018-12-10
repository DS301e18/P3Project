package model;

public class SuperUser extends Employee {


    public SuperUser(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName, "Superbruger");
    }


    public SuperUser() {
    }
}
