package relationClasses;

import util.AddRemove;

public class RestaurantStorage extends AddRemove {

    /**
     * Field
     */
    private int id;
    private int restaurantId;
    private int storageId;

    /**
     * "Methods
     */

    //Constructor with database connectivity included
    public RestaurantStorage(int restaurantId, int storageId) {
        this.restaurantId = restaurantId;
        this.storageId = storageId;

        addObject(this);
    }

    //Empty constructor because of AddRemove.
    public RestaurantStorage() {
    }

    //Remove
    public void remove() {
        removeObject(this);
    }

    //Getters and setters
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

        RestaurantStorage that = (RestaurantStorage) o;

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
