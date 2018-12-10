package relationClasses;

import util.AddRemove;

public class StorageProduct extends AddRemove {
    private int id;
    private int storageId;
    private int productId;

    public StorageProduct(int storageId, int productId) {
        this.storageId = storageId;
        this.productId = productId;

        addObject(this);
    }

    public StorageProduct() {
    }

    public void remove(){
        removeObject(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStorageId() {
        return storageId;
    }

    public void setStorageId(int storageId) {
        this.storageId = storageId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StorageProduct that = (StorageProduct) o;

        if (storageId != that.storageId) return false;
        return productId == that.productId;
    }

    @Override
    public int hashCode() {
        int result = storageId;
        result = 31 * result + productId;
        return result;
    }
}
