package model;

public class Manager extends Employee {

    /**
     * Methods
     **/

    //Constructor with database connectivity included
    public Manager(String username, String password, String firstName, String lastName) {

        super(username, password, firstName, lastName, "Chef");
    }

    //Empty constructor because of AddRemove.
    public Manager() {
    }

}
