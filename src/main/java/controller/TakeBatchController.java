package controller;

import model.Batch;
import model.Employee;
import model.Storage;
import model.Transactions;

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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Input parameters
        int numTaken = Integer.parseInt(req.getParameter("takeFromBatch"));
        int batchChosenI = Integer.parseInt(req.getParameter("batchChosen"));

        HttpSession session = req.getSession();

        //Initialise necessary variables
        List<Batch> batchList = (List) session.getAttribute("batchList");
        Employee employee = (Employee) session.getAttribute("employee");
        Storage storage = (Storage) session.getAttribute("storageChosen");

        //Get chosen batch
        Batch batch = batchList.get(batchChosenI);
        batch.takeFromBatch(numTaken);

        //Transaction
        Transactions transactions = new Transactions();
        transactions.registerTransaction(storage, employee, batch, numTaken, "Fjernet");

        //Redirect
        resp.sendRedirect("webpanel.jsp");

    }
}
