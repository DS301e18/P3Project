package model;

public class Manager extends Employee {

    public Manager(String username, String password, String firstName, String lastName) {

        super(username, password, firstName, lastName, "Chef");
    }

    public Manager() {
    }


    public void removeManager() {
        if ((SystemAdministrator.collectManagers() != null)){
            for (int i = 0; i < SystemAdministrator.collectManagers().size(); i++) {
                removeObject(SystemAdministrator.collectManagers().get(i));
            }
        }

        removeObject(this);
    }

}
