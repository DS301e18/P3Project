<%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 23/11/2018
  Time: 13.02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Velkommen</title>
</head>
<body>

<%
    //Assures that the user can't go back after logout (removes cache)
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");// HTTP 1.1
    response.setHeader("Pragma", "no-cache");// HTTP 1.0
    response.setHeader("Expires", "0");// Proxies

    //Assures that the pages can't be accessed from the url alone
    if (session.getAttribute("username")==null)
    {
        response.sendRedirect("login.jsp");
    } else {
        //Check which role the user has
        if (session.getAttribute("role").equals("Employee")) { %>
            <p>Is employee</p>
        <%} else if (session.getAttribute("role").equals("Manager")) {
            %><p>Is manager</p><%
        }

    }
%>

<form action="Login" method="get">
    <input type="submit" value="Logout">
</form>

</body>
</html>
