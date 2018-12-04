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

    /** Field */
    private int id;
    private String name;

    public Storage() {
    }

    public Storage(String name) {
        this.name = name;
    }

    /** Methods */
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

            Product product = new Product(name, batchSize, price);
            session.save(product);

            transaction.commit();

        } catch (HibernateException e) {
            System.out.println("Couldn't save");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<StorageProductController> collectProducts() {
        List<StorageProductController> storageProducts = new ArrayList<>();

        Session session = new SessionFactoryCfg().createSessionFactory().openSession();
        try {
            List<StorageProductController> storageProductList = session.createQuery("FROM StorageProductController ").list();
            for (StorageProductController storageProduct : storageProductList) {
                if (this.getId() == storageProduct.getStorageId()) {
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

    public List<Product> sortProducts() {
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();

        List<StorageProductController> storageProducts = collectProducts();
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
        return totalStorageProducts;
    }

    public BigDecimal calculateTotalPrice(Storage storage) {
        List<Product> storageProducts = sortProducts();
        BigDecimal totalPrice = new BigDecimal(0);

        for (int i = 0; i < storageProducts.size(); i++) {
            totalPrice = totalPrice.add(storageProducts.get(i).priceOfAllBatches(this));
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        return name;
    }
}
