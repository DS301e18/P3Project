package controller;

import model.HistoryMaker;
import model.Storage;

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

        String input = req.getParameter("historyInput");
        int numEntries = 10;
        if(input != null){
            if(!input.equals("")){
                numEntries = Integer.parseInt(req.getParameter("historyInput"));
            }
        }

        HttpSession session = req.getSession();
        Storage storage = (Storage) session.getAttribute("storageChosen");

        HistoryMaker historyMaker = new HistoryMaker();
        historyMaker.readHistory(numEntries, storage);

        session.setAttribute("history", historyMaker.getHistory());
        session.setAttribute("historyPage", true);

        resp.sendRedirect("webpanel.jsp");

    }
}
