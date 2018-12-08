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
    <meta name="viewport" content="width=device-width, initial-scale=1.0"
          charset="UTF-8">


    <!-- Import Stylesheet -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="style/storageMenu.css">
</head>
<body>

<%Employee employee = (Employee) session.getAttribute("employee");%>
<div class="topnav">
    <div id="topNavID">
        <button onclick="openNav()"><span style="font-size: 20px; color: white"><i class="fas fa-bars"></i></span></button>
        <a><%=session.getAttribute("restaurant")%></a>
    </div>
    <div class="topnav-right">
        <a id="account"><%=employee.getFirstName() + " " + employee.getLastName()%></a>
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
