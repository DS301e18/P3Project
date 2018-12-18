package model;

import util.AddRemove;
import org.hibernate.Session;
import relationClasses.ProductBatch;
import util.SessionFactoryCfg;

import java.math.BigDecimal;
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

    /**
     * Methods
     **/

    //Constructor with database connectivity included
    public Product(String name, int batchSize, BigDecimal price) {

        this.name = name;
        this.batchSize = batchSize;
        this.price = price;

        addObject(this);
    }

    //Empty constructor because of AddRemove.
    public Product() {
    }

    //Getters and setters
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

    //Remove method
    public void remove() {
        //If sortBatches list is not null
        if ((sortBatches() != null)) {
            //For the size of sortBatches
            for (int i = 0; i < sortBatches().size(); i++) {
                //remove all indexes of batch objects created with the batch constructor.
                removeObject(sortBatches().get(i));
            }
        }

        //If collectBatches list is not null
        if ((collectBatches() != null)) {
            //For the size of collectBatches
            for (int i = 0; i < collectBatches().size(); i++) {
                //remove all indexes of productBatch objects created with the productBatch constructor.
                removeObject(collectBatches().get(i));
            }
        }

        //Removes this object
        removeObject(this);
    }

    //Update method
    public void update() {
        updateObject(this);
    }

    //Returns a list of all batches of a product.
    private List<ProductBatch> collectBatches() {
        //New arraylist of productBatches
        List<ProductBatch> productBatches = new ArrayList<>();

        //Opens a session from sessionFactoryCfg
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        //Makes a list with the relation data in the database
        List<ProductBatch> ProductBatchList = session.createQuery("FROM ProductBatch").list();
        //For each productBatch in the above list
        for (ProductBatch productBatch : ProductBatchList) {
            //If this product ID is the same as the productBatch.getRestaurantId()
            if (this.getId() == productBatch.getProductId()) {
                //Adds this productBatch to the productBatches arraylist
                productBatches.add(productBatch);
            }
        }
        //Closes the session
        session.close();
        //returns the arraylist
        return productBatches;
    }

    //Returns a list of all batches of a product in sorted order by name.
    public List<Batch> sortBatches() {
        //Opens a session from sessionFactoryCfg
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        //Makes a list of ProductBatch with the data from the method call: collectBatches
        List<ProductBatch> productBatches = collectBatches();
        //Makes a list with the batch data in the database
        List<Batch> batchList = session.createQuery("FROM Batch").list();
        //New arraylist of Batch
        List<Batch> totalProductBatches = new ArrayList<>();

        //Start i in 0, until i < size of productBatches, increase with 1 each loop
        for (int i = 0; i < productBatches.size(); i++) {
            //For each batch in batchList
            for (Batch batch : batchList) {
                //If batch.getId is the same as the getBatchId found in the relation list
                if (batch.getId() == productBatches.get(i).getBatchId()) {
                    //Add batch to totalProductBatches
                    totalProductBatches.add(batch);
                    //TODO remove this totalAmountOfBatches.
                    //totalAmountOfBatches += batch.getRemainingInBox();
                }
            }
        }
        //Sort totalProductBatches list with a Comparator comparing Batch with their BatchNumber
        totalProductBatches.sort(Comparator.comparing(Batch::getBatchNumber));

        //Closes the session
        session.close();
        //Returning a sorted arraylist of bathces of this product
        return totalProductBatches;
    }

    //Calculates a total price of all batches of a product
    public BigDecimal priceOfAllBatches() {
        List<Batch> productBatches = sortBatches();

        BigDecimal totalPrice = new BigDecimal(0);

        for (int i = 0; i < productBatches.size(); i++) {
            totalPrice = totalPrice.add(productBatches.get(i).getValue());
        }
        return totalPrice;
    }

    //funktion til at tælle antal af vare op. negativt argument fjerner antal.
    public void setBatchSize(int factor) {
        this.batchSize = factor;
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

}

