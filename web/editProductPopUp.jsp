<%@ page import="model.Product" %><%--
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
    <%Product product = (Product) session.getAttribute("productChosen");%>
    <div class="popUp">
        <header id="popUpHeader">
            <label>Rediger Produkt</label>
        </header>

        <div id="popUpBody">
            <form action="EditProductController" accept-charset="ISO-8859-1" method="post">
                <input style="margin: 1px;" type="text" placeholder="Angiv navnet på produktet..." name="editProductName" value="<%=product.getName()%>">
                <input style="margin: 1px;" type="text" placeholder="Angiv antal varer i én kasse..." name="editSize" value="<%=product.getBatchSize()%>">
                <input style="margin: 1px;" type="text" placeholder="Angiv pris for én kasse..." name="editPrice" value="<%=product.getPrice()%>"><br><br>
                <input id="edit" type="submit" value="Rediger">
            </form>

            <form action="Product" method="get">
                <input id="delete" type="submit" value="Slet">
            </form>

            <form action="ClosePopUp" method="get">
                <input id="annul" type="submit" value="Annuller">
            </form>
        </div>
    </div>
</body>
</html>
