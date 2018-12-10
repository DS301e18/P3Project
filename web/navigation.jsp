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
</head>
<body>

<%//Get current user
    Employee employee = (Employee) session.getAttribute("employee");%>

<div class="topnav">
    <div id="topNavID">
        <!-- Button only shown in mobile form in order to hide sidebar/storage list-->
        <button onclick="openNav()"><span style="font-size: 20px; color: white"><i class="fas fa-bars"></i></span></button>
        <a><%=session.getAttribute("restaurant")%></a>
    </div>
    <div class="topnav-right">
        <a id="account"><%=employee.getFirstName() + " " + employee.getLastName()%></a>
        <!-- Logout button -->
        <div class="formbutton">
            <form action="Login" method="get">
                <button type="submit" value="Logout"><a>Log ud</a></button>
            </form>
        </div>
    </div>
</div>

<script>
    function openNav() {
        if(document.getElementById("sideNavID").style.width === "100px"){
            document.getElementById("sideNavID").style.width = "0";
        }else{
            document.getElementById("sideNavID").style.width = "100px";
        }
    }
</script>
</body>
</html>
