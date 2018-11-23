package model;

public class Employee {

    private int id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private int role;
    // private boolean state;


    public Employee(int id, String username, String password, String firstname, String lastname, int role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
    }

    public Employee(String firstname, String lastname, int role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
    }

    public Employee() {
    }

    public void withUsername(String username){
        setUsername(username);
    }
    public void withPassword(String password){
        setPassword(password);
    }
    public void withFirstName(String firstName){
        setFirstname(firstName);
    }
    public void withLastName(String lastName){
        setLastname(lastName);
    }
    public void withRole(int role){
        setRole(role);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", role=" + role +
                '}';
    }
}
