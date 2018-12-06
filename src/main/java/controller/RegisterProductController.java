package controller;

import model.Product;
import model.SessionFactoryCfg;
import model.Storage;
import model.StorageProduct;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/RegisterProduct")
public class RegisterProductController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String name = request.getParameter("name");
        int batchSize = Integer.parseInt(request.getParameter("batchSize"));
        String cost = request.getParameter("cost");
        BigDecimal bigCost;

        if(cost.contains(".") || cost.contains(",")){

            if(cost.contains(",")){
                bigCost = BigDecimal.valueOf(Double.parseDouble(request.getParameter("cost").replace(",", ".")));
            }else {
                bigCost = BigDecimal.valueOf(Double.parseDouble(request.getParameter("cost")));
            }

        }else{
            bigCost = BigDecimal.valueOf(Double.parseDouble(request.getParameter("cost") + ".00"));
        }

        HttpSession session = request.getSession();

        Storage storage = (Storage) session.getAttribute("storageChosen");

        Product product = new Product(name, batchSize, bigCost);

        new StorageProduct(storage.getId(), product.getId());

        response.sendRedirect("webpanel.jsp");

    }
}
