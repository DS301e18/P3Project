package controller;

import model.HistoryMaker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/History")
public class HistoryController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //TODO: make dynamic
        int numEntries = 2;

        HistoryMaker historyMaker = new HistoryMaker();
        historyMaker.readHistory(numEntries);

        HttpSession session = req.getSession();
        session.setAttribute("history", historyMaker.getHistory());
        session.setAttribute("historyPage", true);

        resp.sendRedirect("webpanel.jsp");

    }
}
