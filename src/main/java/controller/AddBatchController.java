package controller;

import model.*;
import relationClasses.ProductBatch;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/AddBatch")
public class AddBatchController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Input parameters
        String batchNumber = req.getParameter("batchNumber");
        int numAdd = Integer.parseInt(req.getParameter("addBatch"));

        HttpSession session = req.getSession();

        //Initialise necessary variables
        List<Batch> batchList = (List) session.getAttribute("batchList");
        Product product = (Product) session.getAttribute("productChosen");
        Employee employee = (Employee) session.getAttribute("employee");
        Storage storage = (Storage) session.getAttribute("storageChosen");

        //Add to database
        Batch batch = new Batch(product, batchNumber, numAdd);
        new ProductBatch(product.getId(), batch.getId());

        //Transaction
        Transactions transactions = new Transactions();
        transactions.registerTransaction(storage, employee, batch, numAdd, "Tilføjet");

        //Update batch list for the product
        batchList.add(batch);
        session.setAttribute("batchList", batchList);

        //Redirect
        resp.sendRedirect("webpanel.jsp");

    }
}
