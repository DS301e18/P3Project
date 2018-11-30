<%--
  Created by IntelliJ IDEA.
  User: Kristoffer
  Date: 30/11/2018
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Navigation</title>
    <!-- Adjust after the size of the device -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Import Stylesheet -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="style/storageMenu.css">
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
        response.sendRedirect("index.jsp");
    } else {
        //Check which role the user has
        if (session.getAttribute("role").equals("Employee")) {
            System.out.println("Employee");%>

<%} else if (session.getAttribute("role").equals("Manager")) {

    System.out.println("Manager");
}

}
%>
<!-- Top navigation (restaurant navigation/logout) -->
<div class="topNav">
    <a><form action="Login" method="get">
        <button type="submit" value="Logout"><span style="font-size: 35px; color: white;"><i class="fas fa-sign-out-alt"></i></span></button>
    </form></a>
    <!-- TODO: Make dynamic -->
    <a1><label style="float:right; color: white">Employee</label></a1>
</div>

<nav>

</nav>

</body>
</html>
