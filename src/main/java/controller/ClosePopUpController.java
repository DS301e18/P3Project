package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/ClosePopUp")
public class ClosePopUpController extends HttpServlet {

    /** Controller to close any popup*/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        session.setAttribute("showSPopUp", false);
        session.setAttribute("showEditSPopUp", false);
        session.setAttribute("showEditProPopUp", false);

        resp.sendRedirect("webpanel.jsp");
    }
}
