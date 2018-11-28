package controller;

import model.ProductType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegisterProduct")
public class RegisterProductController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String name = request.getParameter("name");
        String productNumber = request.getParameter("productNumber");
        int batchSize = Integer.parseInt(request.getParameter("batchSize"));
        double batchCost = Double.parseDouble(request.getParameter("cost"));

        ProductType productType = new ProductType(name, productNumber, batchSize, batchCost);
    }
}
