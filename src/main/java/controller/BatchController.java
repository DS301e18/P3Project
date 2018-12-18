package controller;

import model.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import relationClasses.ProductBatch;
import relationClasses.RestaurantEmployee;
import util.SessionFactoryCfg;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/Batch")
public class BatchController extends HttpServlet {

    /** Batch is added */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try{
            //Input parameters
            String batchNumber = req.getParameter("batchNumber");

            //Only execute this, when a batchNumber has been entered
            if (!batchNumber.equals("")) {
                int numAdd = Integer.parseInt(req.getParameter("addBatch"));

                HttpSession session = req.getSession();

                //Initialise necessary variables
                List<Batch> batchList = (List) session.getAttribute("batchList");
                Storage storage = (Storage) session.getAttribute("storageChosen");
                Product product = (Product) session.getAttribute("productChosen");
                Employee employee = (Employee) session.getAttribute("employee");

                //Add to database
                Batch batch = new Batch(product, batchNumber, numAdd);
                new ProductBatch(product.getId(), batch.getId());

                //Transaction
                Transactions transactions = new Transactions();
                transactions.registerTransaction(storage, employee, batch, batch.getOriginalBatchSize() * numAdd, "Tilføjet");

                //Update batch list for the product
                batchList.add(batch);
                session.setAttribute("batchList", batchList);
            }
        } catch (NumberFormatException e){

            System.out.println("Something else than a number was entered");
            e.printStackTrace();
        } finally {

            //Redirect
            resp.sendRedirect("webpanel.jsp");
        }

    }

    /** Batch is taken */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Input parameters
        int batchChosenI = Integer.parseInt(req.getParameter("batchChosen"));
        int numTaken;

        try {
            //If the button has been pressed without any input, default value is 1
            if (req.getParameter("takeFromBatch").equals("")) {
                numTaken = 1;
            } else {
                numTaken = Integer.parseInt(req.getParameter("takeFromBatch"));
            }

            HttpSession session = req.getSession();

            //Initialise necessary variables
            List<Batch> batchList = (List) session.getAttribute("batchList");
            Employee employee = (Employee) session.getAttribute("employee");
            Storage storage = (Storage) session.getAttribute("storageChosen");

            //Get chosen batch
            Batch batch = batchList.get(batchChosenI);

            //If a whole box is taken
            String oneBox = req.getParameter("oneBox");
            if (oneBox != null) {
                if (oneBox.equals("Tag en Kasse")) {
                    numTaken = batch.getOriginalBatchSize();
                }
            }

            //If a person tries to take more than there is left, the number taken is the remaining size
            if (numTaken > batch.getRemainingInBox()) {
                numTaken = batch.getRemainingInBox();
            }

            //Get relation, so relation can be removed if batch is empty
            Session hibSession = SessionFactoryCfg.getSessionFactory().openSession();
            Query batchQue = hibSession.createQuery("From ProductBatch where batchId = :i");
            batchQue.setParameter("i", batch.getId());
            ProductBatch relationPB = (ProductBatch) batchQue.uniqueResult();

            //Take from batch
            batch.takeFromBatch(relationPB, numTaken);

            hibSession.close();

            //Transaction
            Transactions transactions = new Transactions();
            transactions.registerTransaction(storage, employee, batch, numTaken, "Fjernet");

            //If box is empty, delete from current batch list
            if (batch.getRemainingInBox() < 1) {
                batchList.remove(batchChosenI);
                session.setAttribute("batchList", batchList);
            }
        } catch (NumberFormatException e){
            System.out.println("Something else than a number was entered");
            e.printStackTrace();
        } finally {
            //Redirect
            resp.sendRedirect("webpanel.jsp");
        }
    }
}
