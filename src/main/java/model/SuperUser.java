package model;

public class SuperUser extends Employee {

    /**
     * Methods
     **/

    //Constructor with database connectivity included
    public SuperUser(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName, "Superbruger");
    }

    //Empty constructor because of AddRemove.
    public SuperUser() {
    }
}
