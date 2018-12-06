package controller;

import model.Product;
import model.Search;
import model.Storage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        String input = req.getParameter("search");
        Storage storage = (Storage) session.getAttribute("storageChosen");

        List<Product> productList = new Search().searchProduct(storage, input);

        session.setAttribute("productList", productList);

        resp.sendRedirect("webpanel.jsp");

    }
}