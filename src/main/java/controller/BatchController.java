package controller;

import model.Batch;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/Batch")
public class BatchController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Taken from the input parameters
        int numTaken = Integer.parseInt(req.getParameter("takeFromBatch"));
        int batchChosenI = Integer.parseInt(req.getParameter("batchChosen"));


        HttpSession session = req.getSession();

        List<Batch> batchList = (List) session.getAttribute("batchList");

        Batch batch = batchList.get(batchChosenI);

        batch.takeFromBatch(numTaken);

        resp.sendRedirect("webpanel.jsp");

    }
}
