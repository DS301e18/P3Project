<%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 07/12/2018
  Time: 14.55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div class="popUp">
        <label>Rediger Lager</label>

        <form action="EditProductController" accept-charset="ISO-8859-1" method="post">
            <input type="text" placeholder="Angiv navnet på produktet..." name="editProductName" value="">
            <input type="text" placeholder="Angiv antal varer i én kasse..." name="editSize" value="">
            <input type="text" placeholder="Angiv pris for én kasse..." name="editPrice" value="">
            <input type="submit" value="Rediger">
        </form>

        <form action="Product" method="get">
            <input type="submit" value="Slet">
        </form>

        <form action="ClosePopUp" method="get">
            <input type="submit" value="Annuller">
        </form>
    </div>
</body>
</html>
