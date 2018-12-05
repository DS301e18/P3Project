package model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BatchTest {

    @Test
    void takeFromBatch() {
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();


        Batch batchTest = new Batch();
        batchTest.setBatchNumber("qw12");

        try {

            List<Batch> batchList = session.createQuery("FROM Batch").list();

            for(Batch batch : batchList){
                if (batch.getBatchNumber().equals("qw12")){ assert true;}
            }

        } catch (HibernateException e){
            System.out.println("PIS");
        }finally {
            session.close();
        }

    }

    @Test
    void TestAddObjectOnBatch(){
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();

        SessionFactory sessionFactory = new SessionFactoryCfg().createSessionFactory();
        Product product = new Product("TestProduct", 4, BigDecimal.valueOf(200));
        Batch batch = new Batch(product, "test1324");

        Batch sessionBatch = session.get(Batch.class, batch.getId());

        assertEquals(batch.getId(), sessionBatch.getId());

        session.close();
    }

    static class SystemAdministratorControllerTest {

     /*   @Test
        void addManager() {
            //Establishing a connection to the database
            Session session = new SessionFactoryCfg().createSessionFactory().openSession();

            SystemAdministrator admin = new SystemAdministrator();

            Manager manager = new Manager();

            manager.setUsername("Kajmanden");
            manager.setPassword("hejmeddig123");
            manager.setFirstName("Kaj");
            manager.setLastName("Kaj");

            admin.addManager(manager);

            Manager manager1 = session.get(Manager.class, manager.getId());

            assertEquals(manager.getId(), manager1.getId());

            session.close();
        }
    */
      /*  @Test
        void removeManager() {
            SystemAdministrator admin = new SystemAdministrator();

            Manager manager = new Manager();

            manager.setUsername("Kajmanden");
            manager.setPassword("hejmeddig123");
            manager.setFirstName("Kaj");
            manager.setLastName("Kaj");

            admin.addManager(manager);
            admin.removeManager(manager);

            Manager emptyManager = null;

            //Establishing a connection to the database
            Session session = new SessionFactoryCfg().createSessionFactory().openSession();

            List<Manager> managerList = session.createQuery("FROM Manager ").list();

           for (Manager managers : managerList){
                if (managers.getId() == manager.getId()){
                    emptyManager = managers;
                }
            }

            session.close();

            assertNull(emptyManager);
        }
    */

    /*    @Test
        void addRestaurant() {
            //Establishing a connection to the database
            Session session = new SessionFactoryCfg().createSessionFactory().openSession();

            SystemAdministrator admin = new SystemAdministrator();
            admin.addRestaurant("Mogens");

            Restaurant restaurant = session.get(Restaurant.class, admin.getRestaurantId());

            assertEquals("Mogens", restaurant.getName());

            //Assuring that the transaction also is stored in the database
            session.close();

            /*
            session = new SessionFactoryCfg().createSessionFactory().openSession();
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();
                //Deletes the restaurant form the database
                session.delete(restaurant);
                transaction.commit();

            } catch (HibernateException ex) {
                System.out.println("The restaurant couldn't be removed");
                ex.printStackTrace();
            } finally {
                session.close();
            }
        }
        */

    /*

        @Test
        void removeRestaurant() {
            //Establishing a connection to the database
            Session session = new SessionFactoryCfg().createSessionFactory().openSession();

            SystemAdministrator admin = new SystemAdministrator();
            admin.addRestaurant("Mogens");
            admin.removeRestaurant("Mogens");

            //Creating an empty restaurant
            Restaurant empty = null;

            List<Restaurant> restaurantList = session.createQuery("FROM Restaurant").list();

            for (Restaurant restaurant : restaurantList) {
                //If the restaurant that was mend to be deleted is found in the restaurant table, set the empty restaurant to be that
                if (restaurant.equals(admin.getRestaurant())) {
                    empty = restaurant;
                }
            }

            assertNull(empty);

        }
    */
    }
}