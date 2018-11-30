package controller;

public class AssignedStorageController {
    private int id;
    private int restaurantId;
    private int storageId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getStorageId() {
        return storageId;
    }

    public void setStorageId(int storageId) {
        this.storageId = storageId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssignedStorageController that = (AssignedStorageController) o;

        if (restaurantId != that.restaurantId) return false;
        return storageId == that.storageId;
    }


    @Override
    public int hashCode() {
        int result = restaurantId;
        result = 31 * result + storageId;
        return result;
    }

}
