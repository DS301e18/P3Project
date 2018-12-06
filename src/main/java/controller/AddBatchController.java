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

@WebServlet("/AddBatch")
public class AddBatchController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String batchNumber = req.getParameter("batchNumber");
        int numAdd = Integer.parseInt(req.getParameter("addBatch"));

        HttpSession session = req.getSession();

        Product product = (Product) session.getAttribute("productChosen");

        Employee employee = (Employee) session.getAttribute("employee");
        Storage storage = (Storage) session.getAttribute("storageChosen");

        Batch batch = new Batch(product, batchNumber, numAdd);
        ProductBatch productBatch = new ProductBatch(product.getId(), batch.getId());

        Transactions transactions = new Transactions();
        transactions.registerTransaction(storage, employee, batch, numAdd, "Tilføjet");

        resp.sendRedirect("webpanel.jsp");

    }
}
