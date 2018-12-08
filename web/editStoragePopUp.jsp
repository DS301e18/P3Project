<%@ page import="model.Storage" %><%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 07/12/2018
  Time: 13.56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%Storage storage = (Storage) session.getAttribute("storageChosen");%>
    <div class="popUp">
        <header id="popUpHeader">
            <label>Rediger Lager</label>
        </header>

        <div id="popUpBody">
            <form action="EditStorageController" accept-charset="ISO-8859-1" method="post">
                <input type="text" placeholder="Angiv navnet på lageret..." name="editStorageName" value="<%=storage.getName()%>"><br>
                <input id="edit" type="submit" value="Rediger">
            </form>

            <form action="Storage" method="get">
                <input id="delete" type="submit" value="Slet">
            </form>

            <form action="ClosePopUp" method="get">
                <input id="annul" type="submit" value="Annuller">
            </form>
        </div>

    </div>
</body>
</html>
