<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Storage" %>
<%@ page import="java.util.ArrayList" %><%--
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
<% Storage storage = (Storage) session.getAttribute("storageChosen");%>

<!-- TODO: Make storage inventory dynamic -->
<!-- Storage inventory-->
<div class="container" id="storage">
    <section>

        <!-- Inventory header -->
        <div class="contentBox" id="inventoryHeader">

            <!--Storage name-->
            <label><%=storage.getName()%></label>

            <!-- Edit button -->
            <button><span style="font-size: 20px"><i class="fas fa-hammer"></i></span></button>

            <!--Search Bar-->
            <form action="search" method="post">
                <input type="text" placeholder="Søg..." name="search">
            </form>
        </div>

        <!-- Tabs only available for a manager-->
        <%if(session.getAttribute("role").equals("Chef")){%>
            <button class="tab" id="registerProductButton" onclick="show('registerProductPage', 'historyPage')">Registrer Vare</button>
            <button class="tab" id="historyButton" onclick="show('historyPage', 'registerProductPage')">Historik</button>

            <!-- Breaklines TODO: should probably be done with css-->
            <br><br><br>
        <%}%>

        <div id="inventory"><%
            //When the program runs for the first time, productList will have the value null
            List<Product> productList = (List) session.getAttribute("productList");

            List<Product> productShownList = new ArrayList<>();

            if(productList == null){
                productList = storage.sortProducts();
                session.setAttribute("productList", productList);
            }%>
            <form name="productChosenEvent" action="Product" method="post">
                <input type="hidden" name="productChosenButton"><%
                int i = 0;
                for(Product product : productList){
                    productShownList.add(product);
                    product.sortBatches();%>
                    <button class="productButton" onclick="productChoice(id)" value="<%=i%>" id="<%=product.getId()%>">
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

        <!-- Price-box -->
        <div class="priceBox"><a>Total pris: <%=storage.calculateTotalPrice()%> kr.</a></div>
    </section>

    <!-- Register new product in the storage -->
    <!-- Will be hidden every time page is reloaded-->
    <aside id="registerProductPage" hidden>
        <form action="RegisterProduct" method="post">
            <input type="text" placeholder="Indtast navnet på produktet" name="name">
            <input type="text" placeholder="Indtast antal per batch" name="batchSize">
            <input type="text" placeholder="Indtast prisen for en batch" name="cost">
            <input type="submit" value="Registrer">
        </form>
    </aside>

    <!-- History page -->
    <!-- Will be hidden every time page is reloaded-->
    <aside id="historyPage" hidden>
        History
    </aside>

    <!-- Product information -->
    <!-- If a product has been chosen, attribute won't be null -->
    <%if(session.getAttribute("productChosen") != null){%>
        <jsp:include page="productInformation.jsp"/>
    <%}%>
</div>

<script>
    //Show history and register product, and close the other tab if open
    function show(ID1, ID2) {
        var show1 = document.getElementById(ID1);
        var show2 = document.getElementById(ID2);

        if(show1.style.display === "none"){
            show1.style.display = "block";
            if(show2 !== "none"){
                show2.style.display = "none";
            }
        } else{
            show1.style.display = "none";
        }
    }

    function productChoice(productID) {
        var button = document.getElementById(productID).value;
        document.productChosenEvent.productChosenButton.value = button;
    }
</script>

</body>
</html>
