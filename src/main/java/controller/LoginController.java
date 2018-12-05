package controller;

import model.LoginCheck;
import model.SessionFactoryCfg;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Login")
public class LoginController extends HttpServlet {

    SessionFactory sessionFactory;

    /** Login Method **/
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        LoginCheck loginCheck = new LoginCheck();
        sessionFactory = loginCheck.getSessionFactory();

        if(loginCheck.check(username, password)){
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("role", loginCheck.getEmployee().getRole());
            session.setAttribute("employeeID", loginCheck.getEmployee().getId());
            response.sendRedirect("webpanel.jsp");

        } else {
            response.sendRedirect("index.jsp");
            loginCheck.getSessionFactory().close();
        }

    }

    /** Logout Method **/
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{

        HttpSession session = request.getSession();
        session.removeAttribute("username");
        session.removeAttribute("role");
        session.invalidate();
        response.sendRedirect("index.jsp");
        sessionFactory.close();

    }

}
