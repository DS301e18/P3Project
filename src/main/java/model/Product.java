package model;

import util.AddRemove;
import org.hibernate.Session;
import relationClasses.ProductBatch;
import util.SessionFactoryCfg;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Product extends AddRemove {

    /**
     * Field
     **/

    private int id;
    private String name;
    private int batchSize;
    private BigDecimal price;
    private int totalAmountOfBatches;

    public Product(String name, int batchSize, BigDecimal price) {

        this.name = name;
        this.batchSize = batchSize;
        this.price = price;

        addObject(this);
    }

    public Product() {
    }

    /**
     * Methods
     **/

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

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

    public int getBatchSize() {
        return batchSize;
    }

    private List<ProductBatch> collectBatches() {
        List<ProductBatch> productBatches = new ArrayList<>();

        Session session = new SessionFactoryCfg().getSessionFactory().openSession();

        List<ProductBatch> ProductBatchList = session.createQuery("FROM ProductBatch").list();
        for (ProductBatch productBatch : ProductBatchList) {
            if (this.getId() == productBatch.getProductId()) {
                productBatches.add(productBatch);
            }
        }
        return productBatches;
    }

    public List<Batch> sortBatches() {
        Session session = new SessionFactoryCfg().getSessionFactory().openSession();

        List<ProductBatch> productBatches = collectBatches();
        List<Batch> batchList = session.createQuery("FROM Batch").list();
        List<Batch> totalProductBatches = new ArrayList<>();

        for (int i = 0; i < productBatches.size(); i++) {
            for (Batch batch : batchList) {
                if (batch.getId() == productBatches.get(i).getBatchId()) {
                    totalProductBatches.add(batch);
                    totalAmountOfBatches += batch.getRemainingInBox();
                }
            }
        }
        totalProductBatches.sort(Comparator.comparing(Batch::getBatchNumber));
        return totalProductBatches;
    }

    public BigDecimal priceOfAllBatches(Storage storage) {
        List<Batch> productBatches = sortBatches();

        BigDecimal totalPrice = new BigDecimal(0);

        for (int i = 0; i < productBatches.size(); i++) {
            totalPrice = totalPrice.add(productBatches.get(i).getValue());
        }
        return totalPrice;
    }

    //funktion til at tælle antal af vare op. negativt argument fjerner antal.
    public void setBatchSize(int factor) {
        this.batchSize += factor;
    }

    public int getTotalAmountOfBatches() {
        return totalAmountOfBatches;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (batchSize != product.batchSize) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        return price != null ? price.equals(product.price) : product.price == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + batchSize;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}

