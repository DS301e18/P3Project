package model;

import Util.AddRemove;

import org.hibernate.SessionFactory;

public class Restaurant extends AddRemove {

    /**
     * Field
     */
    private int id;
    private String name;
    private SessionFactory factory;

    public Restaurant(String name) {
        this.name = name;

        addObject(this);
    }

    public Restaurant() {
    }

    /**
     * Methods
     */
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


}



