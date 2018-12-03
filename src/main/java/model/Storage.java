package model;

import controller.StorageProductController;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Storage {

    /**
     * Field
     */
    private int id;
    private String name;
    private Restaurant restaurant;

    public Storage() {
    }

    public Storage(String name, Restaurant restaurant) {
        this.name = name;
        this.restaurant = restaurant;
    }

    /**
     * Methods
     */
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void relateProductToStorage(Product product) {
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();
        Transaction transaction;

        try {
            transaction = session.beginTransaction();

            StorageProductController storageProduct = new StorageProductController();
            storageProduct.setStorageId(this.id);
            storageProduct.setProductId(product.getId());

            session.save(storageProduct);
            transaction.commit();

        } catch (HibernateException e) {
            System.out.println("Couldn't create a relation");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void unrelateProductFromStorage(Product product) {
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();
        Transaction transaction = null;

        try {
            List<StorageProductController> storageProductList = session.createQuery("FROM StorageProductController").list();
            for (StorageProductController storageProducts : storageProductList) {
                if (storageProducts.getProductId() == product.getId()) {
                    transaction = session.beginTransaction();
                    session.delete(storageProducts);
                    transaction.commit();
                }
            }
        } catch (HibernateException e) {
            System.out.println("Couldn't unrelate any products");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Kode til Jonas
    public void createProduct(String name, int batchSize, BigDecimal price) {
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();
        Transaction transaction;

        try {
            transaction = session.beginTransaction();

            Product product = new Product(name, batchSize, price, getId());
            session.save(product);
            transaction.commit();

        } catch (HibernateException e) {
            System.out.println("Couldn't save");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<StorageProductController> collectProducts(Storage storage) {
        final List<StorageProductController> storageProducts = new ArrayList<>();

        Session session = new SessionFactoryCfg().createSessionFactory().openSession();
        try {
            List<StorageProductController> storageProductList = session.createQuery("FROM StorageProductController ").list();
            for (StorageProductController storageProduct : storageProductList) {
                if (storage.getId() == storageProduct.getStorageId()) {
                    storageProducts.add(storageProduct);
                }
            }
        } catch (HibernateException e) {
            System.out.println("Couldn't find any products in this storage");
            e.printStackTrace();
        } finally {
            session.close();
            return storageProducts;
        }
    }

    //TODO Gør sådan at den tjekket hvilket lager produktet ligger i.
    public void sortProducts() {
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();

        List<StorageProductController> storageProducts = collectProducts(this);
        List<Product> productList = session.createQuery("FROM Product").list();
        List<Product> totalStorageProducts = new ArrayList<>();

        for (int i = 0; i < storageProducts.size(); i++) {
            for (Product product : productList) {
                if (product.getId() == storageProducts.get(i).getProductId()) {
                    totalStorageProducts.add(product);
                }
            }
        }
        totalStorageProducts.sort(Comparator.comparing(Product::getName));
    }

    //TODO Gør sådan at den tjekker hvilket lager en bestemt varetype/batch ligger. Ellers kommer denne kode til at tage alle  pris i alle lagre.
    public BigDecimal calculateTotalPrice(Storage storage) {
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();
        BigDecimal totalPrice = new BigDecimal(0);

        try {
            List<Storage> storageList = session.createQuery("FROM Storage").list();
            for (Storage storage1 : storageList) {
                if (storage.getId() == storage1.getId()) {
                    List<Product> productList = session.createQuery("FROM Product").list();
                    for (Product product : productList) {
                        totalPrice = totalPrice.add(product.priceOfAllBatches(this));
                    }
                }
            }

        } catch (HibernateException e) {
            System.out.println("Couldn't find any products");
            e.printStackTrace();
        } finally {
            session.close();
        }

        return totalPrice;
    }

    @Override
    public String toString() {
        return name;
    }
}
