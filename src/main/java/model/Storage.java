package model;

public class Storage {

    /**
     * Field
     */
    private int id;
    private String name;
    private Restaurant restaurant;

    public Storage(String name, Restaurant restaurant) {
        this.name = name;
        this.restaurant = restaurant;
    }

    /**
     * Methods
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void addProduct(ProductType productType) {

    }

    public void removeProduct(ProductType productType) {
    }

    public void addBatch(Batch batch) {
    }

    public void collectProducts() {

    }

    public void sortProducts() {

    }

    public void calculateTotalPrice() {

    }
    
}
