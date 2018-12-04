package controller;

public class ProductBatchController {
    private int id;
    private int productId;
    private int batchId;

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

        ProductBatchController that = (ProductBatchController) o;

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
