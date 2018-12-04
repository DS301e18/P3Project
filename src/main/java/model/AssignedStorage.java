package model;

import Util.AddRemove;

public class AssignedStorage extends AddRemove {
    private int id;
    private int restaurantId;
    private int storageId;

    public AssignedStorage(int restaurantId, int storageId) {
        this.restaurantId = restaurantId;
        this.storageId = storageId;

        addObject(this);
    }

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

        AssignedStorage that = (AssignedStorage) o;

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
