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

    /** Show chosen product on the side page*/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try{
            //Input parameter
            int productChosen = Integer.parseInt(req.getParameter("productChosenButton"));

            HttpSession session = req.getSession();

            //If history page is shown, close it
            session.setAttribute("historyPage", false);

            //Current product list for storage
            List<Product> productList = (List) session.getAttribute("productListForChoosing");
            Product product = productList.get(productChosen);

            //Set productChosen to the chosen product
            session.setAttribute("productChosen", product);

            //Show the current batches under a product
            List<Batch> batchList = product.sortBatches();
            session.setAttribute("batchList", batchList);
        } catch (NumberFormatException e){
            System.out.println("Something else than a number was entered");
            e.printStackTrace();
        } finally {
            resp.sendRedirect("webpanel.jsp");
        }

    }

    /** Delete Product */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Get current product
        HttpSession session = req.getSession();
        Product product = (Product) session.getAttribute("productChosen");

        //Delete product from database
        product.remove();

        //Find relation from database
        Session hibSession = SessionFactoryCfg.getSessionFactory().openSession();
        Query relation = hibSession.createQuery("From StorageProduct where productId =:i");
        relation.setParameter("i", product.getId());
        StorageProduct relationElement = (StorageProduct) relation.uniqueResult();

        //Delete relation from database
        relationElement.remove();

        hibSession.close();

        //Close popup
        session.setAttribute("showEditProPopUp", false);

        //Close product information page
        session.setAttribute("productChosen", null);

        resp.sendRedirect("webpanel.jsp");
    }
}
