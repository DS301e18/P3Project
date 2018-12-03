package model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Employee {

    private int id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String role = "Medarbejder";


    public void addEmployee(){
        Session session = new SessionFactoryCfg().getSessionFactory().openSession();
        Transaction transaction = null;

        try{
            //If any attribute equals Null, throw exception
            if(username != null && password != null && firstname != null && lastname != null){
                transaction = session.beginTransaction();
                session.save(this);
                transaction.commit();
            }
            else {
                throw new IllegalEmployeeException();
            }

        } catch (HibernateException e){
            System.out.println("Couldn't save employee");
            e.printStackTrace();

        } finally {
            session.close();
        }
    }

    public void removeEmployee(){
        Session session = new SessionFactoryCfg().getSessionFactory().openSession();
        Transaction transaction = null;

        try{
            transaction = session.beginTransaction();
            session.delete(this);
            transaction.commit();

        } catch (HibernateException e){
            System.out.println("Couldn't delete employee");
            e.printStackTrace();

        } finally {
            session.close();
        }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
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
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (id != employee.id) return false;
        if (!username.equals(employee.username)) return false;
        if (!password.equals(employee.password)) return false;
        if (!firstname.equals(employee.firstname)) return false;
        if (!lastname.equals(employee.lastname)) return false;
        return role.equals(employee.role);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + firstname.hashCode();
        result = 31 * result + lastname.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }
}
