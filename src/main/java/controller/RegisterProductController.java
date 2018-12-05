package controller;

import model.SessionFactoryCfg;
import model.Storage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/RegisterProduct")
public class RegisterProductController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        /*int storageID = Integer.parseInt(request.getParameter("storageID"));
        String name = request.getParameter("name");
        int batchSize = Integer.parseInt(request.getParameter("batchSize"));
        BigDecimal batchCost = BigDecimal.valueOf(Integer.parseInt(request.getParameter("cost")));

        Session session = new SessionFactoryCfg().createSessionFactory().openSession();
        Transaction transaction;
        Storage currentStorage = null;

        try{
            transaction = session.beginTransaction();
            currentStorage = session.get(Storage.class, storageID);
            transaction.commit();

        } catch (HibernateException e){
            System.out.println("Couldn't find the correct current storage!");
            e.printStackTrace();
        } finally {
            session.close();
        }

        currentStorage.createProduct(name, batchSize, batchCost);*/

        response.sendRedirect("webpanel.jsp");

    }
}
