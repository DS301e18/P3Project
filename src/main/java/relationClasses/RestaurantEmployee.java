package relationClasses;

import util.AddRemove;

public class RestaurantEmployee extends AddRemove {

    /**
     * Field
     */
    private int id;
    private int restaurantId;
    private int employeeId;

    /**
     * "Methods
     */

    //Constructor with database connectivity included
    public RestaurantEmployee(int restaurantId, int employeeId) {
        this.restaurantId = restaurantId;
        this.employeeId = employeeId;

        addObject(this);
    }

    //Empty constructor because of AddRemove.
    public RestaurantEmployee() {
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

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RestaurantEmployee that = (RestaurantEmployee) o;

        if (restaurantId != that.restaurantId) return false;
        return employeeId == that.employeeId;
    }

    @Override
    public int hashCode() {
        int result = restaurantId;
        result = 31 * result + employeeId;
        return result;
    }
}
