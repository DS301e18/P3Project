<%@ page import="model.Employee" %><%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 10/12/2018
  Time: 23.27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%Employee employee = (Employee) session.getAttribute("employeeChosen");%>
<div class="popUp">
    <header id="popUpHeader">
        <label>Rediger Produkt</label>
    </header>

    <div id="popUpBody">
        <form action="EditEmployee" accept-charset="ISO-8859-1" method="post">
            <input style="margin: 1px;" type="text" placeholder="Angiv brugernavn.." name="editUsername" value="<%=employee.getUsername()%>">
            <input style="margin: 1px;" type="text" placeholder="Angiv password..." name="editPassword" value="<%=employee.getPassword()%>">
            <input style="margin: 1px;" type="text" placeholder="Angiv fornavn..." name="editFirstName" value="<%=employee.getFirstName()%>">
            <input style="margin: 1px;" type="text" placeholder="Angiv efterNavn..." name="editLastName" value="<%=employee.getLastName()%>"><br><br>
            <input id="edit" type="submit" value="Rediger">
        </form>

        <form action="DeleteEmployee" method="get">
            <input id="delete" type="submit" value="Slet">
        </form>

        <form action="ClosePopUp" method="get">
            <input id="annul" type="submit" value="Annuller">
        </form>
    </div>
</div>
</body>
</html>
