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

<!-- TODO: Make storage inventory dynamic -->
<!-- Storage inventory-->
<div class="container" id="storage">
    <section>
        <!-- Inventory header -->
        <div class="contentBox" id="inventoryHeader">
            <!--Storage name-->
            <label><%=storage.getName()%></label>
            <!--Search Bar-->
            <button><span style="font-size: 20px"><i class="fas fa-hammer"></i></span></button>
            <form action="search" method="post">
                <input type="text" placeholder="Søg..." name="search">
            </form>
        </div>

        <button class="tab">Registrer Vare</button>
        <button class="tab">Historik</button><br><br><br>

        <div id="inventory"><%
            List<Product> productList;
            if(session.getAttribute("productList")==null){
                productList = storage.sortProducts();
            } else {
                productList = (List<Product>) session.getAttribute("productList");
            }
            for(Product product : productList){
                //product.sortBatches();%>
                <div class="productTab">
                    <label><%=product.getName()%></label>
                    <label style="float: right; padding-right: 15px"><%//=product.getTotalAmountOfBatches()%></label>
                </div><%
            }%>
        </div>

        <%//TODO: This algorithm is very, very heavy. Maybe revise%>
        <!-- Price-box -->
        <div class="priceBox"><a>Total pris: <%//=storage.calculateTotalPrice()%> kr.</a></div>
        <!-- Product information -->
    </section>
    <aside>
        <div class="productHeader" style="font-size: 25px">Produkt Navn</div>
    </aside>
</div>
</body>
</html>
