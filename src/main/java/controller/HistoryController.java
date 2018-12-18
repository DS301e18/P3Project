package controller;

import model.History;
import model.Storage;
import model.Transactions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/History")
public class HistoryController extends HttpServlet {

    /** Search/sort history*/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Input parameters
        String numInput = req.getParameter("historyInput");
        String searchInput = req.getParameter("historySearch");

        //Error handling, if search input is null
        if(searchInput == null){
            searchInput = "";
        }

        //Default amount of data shown
        int numEntries = 200;

        //Error handling if user hasn't written anything
        try {
            if(numInput != null){
                if(!numInput.equals("")){
                    numEntries = Integer.parseInt(req.getParameter("historyInput"));
                }
            }

            //Get current attributes
            HttpSession session = req.getSession();
            Storage storage = (Storage) session.getAttribute("storageChosen");

            //Sort and search history
            List<Transactions> history = new History().readHistory(numEntries, searchInput, storage);
            session.setAttribute("history", history);

            //Open history page
            session.setAttribute("historyPage", true);
        }catch (NumberFormatException e){
            System.out.println("Something else than a number was entered");
            e.printStackTrace();
        } finally {
            resp.sendRedirect("webpanel.jsp");
        }
    }

}
