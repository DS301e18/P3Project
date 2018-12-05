<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Storage" %><%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 05/12/2018
  Time: 18.23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lager Inventar</title>
</head>
<body>
<% Storage storage = (Storage) session.getAttribute("storageChosen"); %>
<section>
    <!-- Inventory header -->
    <div class="contentBox">
        <label><%=storage.getName()%></label>
        <button><span style="font-size: 20px"><i class="fas fa-hammer"></i></span></button>
    </div>


    <div class="contentBox">
        <input type="text" id="search" placeholder="Søg...">
    </div>

    <a><div class="tab">Registrer Vare</div></a>
    <button class="test"><div class="tab">Historik</div></button><br>

    <div id="productInventory">
        <%
            List<Product> productList = storage.sortProducts();

            for(Product product : productList){%>
        <button class="productTab"><%=product.getName()%></button>
        <!--<label style="float: right">4</label>-->
        <%}%>
    </div>

    <!-- Price-box -->
    <div class="priceBox"><a>Total pris: "Actual price" kr.</a></div>
</section>
</body>
</html>
