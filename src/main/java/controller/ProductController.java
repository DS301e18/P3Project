package controller;

import model.Batch;
import model.Product;
import org.hibernate.Session;
import org.hibernate.query.Query;
import relationClasses.RestaurantStorage;
import relationClasses.StorageProduct;
import util.SessionFactoryCfg;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/Product")
public class ProductController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int productChosen = Integer.parseInt(req.getParameter("productChosenButton"));

        HttpSession session = req.getSession();

        session.setAttribute("historyPage", false);

        List<Product> productList = (List) session.getAttribute("productListForChoosing");

        Product product = productList.get(productChosen);

        session.setAttribute("productChosen", product);

        List<Batch> batchList = product.sortBatches();

        session.setAttribute("batchList", batchList);

        resp.sendRedirect("webpanel.jsp");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Product product = (Product) session.getAttribute("productChosen");
        product.remove();

        Session hibSession = new SessionFactoryCfg().getSessionFactory().openSession();

        Query relation = hibSession.createQuery("From StorageProduct where productId = :i");
        relation.setParameter("i", product.getId());
        List<StorageProduct> relationElement = relation.list();

        relationElement.get(0).remove();

        hibSession.close();

        session.setAttribute("showEditProPopUp", false);
        session.setAttribute("productChosen", null);
        resp.sendRedirect("webpanel.jsp");
    }
}
