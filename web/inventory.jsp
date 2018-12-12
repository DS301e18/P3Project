<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Storage" %><%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 06/12/2018
  Time: 18.09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inventar</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <% //Get current storage
        Storage storage = (Storage) session.getAttribute("storageChosen");%>

    <div id="invetory"><%
        //ProductList will be equal to the current product list
        List<Product> productList = (List) session.getAttribute("productList");

        //List of which products are shown
        List<Product> productShownList = new ArrayList<>();

        //If productList is null, then productList is equal to the full list of the current storage
        if(productList == null){
            productList = storage.sortProducts();
            session.setAttribute("productList", productList);
        }%>

        <!-- Assures that all products have the same functionality when initialized-->
        <form name="productChosenEvent" action="Product" method="post">

            <!-- Input used to know which product has been clicked and chosen-->
            <input type="hidden" name="productChosenButton"><%

            int i = 0;
            for(Product product : productList){
                productShownList.add(product);
                product.sortBatches();%>
            <button class="productButton" onclick="productChoice(id);" value="<%=i%>" id="<%=product.getId()%>">
                <label><%=product.getName()%></label>
                <label style="float: right; padding-right: 15px"><%=product.getTotalAmountOfBatches()%></label>
            </button><%
                i++;
            }
            session.setAttribute("productListForChoosing", productShownList);%>
        </form>

        <!-- Reset productList, so the other storages doesn't get the same product inventory -->
        <%session.setAttribute("productList", null);%>
    </div>

    <script>
        //Function so the productChosenButton gets the same value as the chosen product's value
        function productChoice(productID) {
            var button = document.getElementById(productID).value;
            document.productChosenEvent.productChosenButton.value = button;
        }
    </script>
</body>
</html>
