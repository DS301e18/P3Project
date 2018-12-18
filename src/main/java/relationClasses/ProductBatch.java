package relationClasses;

import util.AddRemove;

public class ProductBatch extends AddRemove {

    /**
     * Field
     */
    private int id;
    private int productId;
    private int batchId;

    /**
     * "Methods
     */

    //Constructor with database connectivity included
    public ProductBatch(int productId, int batchId) {
        this.productId = productId;
        this.batchId = batchId;

        addObject(this);
    }

    //Empty constructor because of AddRemove.
    public ProductBatch() {
    }

    //Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductBatch that = (ProductBatch) o;

        if (productId != that.productId) return false;
        return batchId == that.batchId;
    }

    @Override
    public int hashCode() {
        int result = productId;
        result = 31 * result + batchId;
        return result;
    }
}
