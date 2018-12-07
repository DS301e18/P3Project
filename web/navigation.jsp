<%@ page import="model.Employee" %><%--
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

<%Employee employee = (Employee) session.getAttribute("employee");%>
<div class="topnav">
    <a><%=session.getAttribute("restaurant")%></a>
    <div class="topnav-right">
        <a><%=employee.getFirstName() + " " + employee.getLastName()%></a>
        <div class="formbutton">
            <form action="Login" method="get">
                <button type="submit" value="Logout"><a>Log ud</a></button>
            </form>
        </div>
    </div>
</div>


</body>
</html>
