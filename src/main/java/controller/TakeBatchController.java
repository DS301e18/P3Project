package controller;

import model.Batch;
import model.Employee;
import model.Storage;
import model.Transactions;
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

@WebServlet("/TakeBatch")
public class TakeBatchController extends HttpServlet {

    //Items are taken
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Input parameters
        int batchChosenI = Integer.parseInt(req.getParameter("batchChosen"));
        int numTaken;
        int takeBox = 0;

        if(req.getParameter("takeFromBatch").equals("")){
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
        if(oneBox != null){
            if(oneBox.equals("Tag én Kasse") ){
                numTaken = batch.getOriginalBatchSize();
            }
        }

        //If a person tries to take more than there is left
        if(numTaken > batch.getRemainingInBox()){
            numTaken = batch.getRemainingInBox();
        }

        //Get relation, so relation can be removed if batch is empty
        Session hibSession = new SessionFactoryCfg().createSessionFactory().openSession();
        Query batchQue = hibSession.createQuery("From ProductBatch where batchId = :i");
        batchQue.setParameter("i", batch.getId());
        List<ProductBatch> relationPB = batchQue.list();

        //Take from batch
        batch.takeFromBatch(relationPB.get(0), numTaken);

        hibSession.close();

        //Transaction
        Transactions transactions = new Transactions();
        transactions.registerTransaction(storage, employee, batch, numTaken, "Fjernet");

        //If box is empty, delete from current batch list
        if(batch.getRemainingInBox() < 1){
            batchList.remove(batchChosenI);
            session.setAttribute("batchList", batchList);
        }

        //Redirect
        resp.sendRedirect("webpanel.jsp");

    }
}
