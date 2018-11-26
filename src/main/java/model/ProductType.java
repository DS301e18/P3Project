package model;

public class ProductType {

    /** Field **/
    private int id;
    private String name;
    private String productNumber;
    private String batchSize;
    private int batchCost;


    /** Methods **/
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

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(String batchSize) {
        this.batchSize = batchSize;
    }

    public int getBatchCost() {
        return batchCost;
    }

    public void setBatchCost(int batchCost) {
        this.batchCost = batchCost;
    }
}
