package controller;

import model.LoginCheck;
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


    /** Login Method **/
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Input parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //check login
        LoginCheck loginCheck = new LoginCheck();

        //Check if login succeeded
        if(loginCheck.check(username, password)){
            HttpSession session = request.getSession();

            //Set global attributes
            session.setAttribute("username", username);
            session.setAttribute("role", loginCheck.getEmployee().getRole());
            session.setAttribute("employeeID", loginCheck.getEmployee().getId());
            session.setAttribute("employee", loginCheck.getEmployee());

            //Redirect
            if(loginCheck.getEmployee().getRole().equals("Superbruger")){
                response.sendRedirect("superuserWebpanel.jsp");
            }else{
                response.sendRedirect("webpanel.jsp");
            }

        } else {
            response.sendRedirect("index.jsp");
        }

    }

    /** Logout Method **/
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{

        //Invalidate session (removes sessionAttributes)
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("index.jsp");

    }

}
