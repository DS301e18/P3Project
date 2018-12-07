package controller;

import model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/EditProductController")
public class EditProductController extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("editProductName");
        int batchSize = Integer.parseInt(req.getParameter("editSize"));
        String cost = req.getParameter("editPrice");
        BigDecimal price;

        if(cost.contains(".") || cost.contains(",")){

            if(cost.contains(",")){
                price = BigDecimal.valueOf(Double.parseDouble(cost.replace(",", ".")));
            }else {
                price = BigDecimal.valueOf(Double.parseDouble(cost));
            }

        }else{
            price = BigDecimal.valueOf(Double.parseDouble(cost + ".00"));
        }

        HttpSession session = req.getSession();

        Product product = (Product) session.getAttribute("productChosen");
        product.setName(name);
        product.setBatchSize(batchSize);
        product.setPrice(price);

        product.update();

        session.setAttribute("showEditProPopUp", false);
        resp.sendRedirect("webpanel.jsp");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        session.setAttribute("showEditProPopUp", true);
        resp.sendRedirect("webpanel.jsp");
    }
}
