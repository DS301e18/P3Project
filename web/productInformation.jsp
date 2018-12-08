<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Batch" %>
<%@ page import="java.math.BigDecimal" %><%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 06/12/2018
  Time: 11.36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Produkt Information</title>
</head>
<body>
    <aside id="productInformation"><%
        Product product = (Product) session.getAttribute("productChosen");%>
        <!-- Product information header -->
        <div class="productHeader">
            <!-- Only on mobile platform, in order to close product information -->
            <button style="float: right;" onclick="hide('productInformation')"><i class="fas fa-times"></i></button>

            <form action="EditProductController" method="get">
                <label style="font-size: 40px"><%=product.getName()%></label>
                <!-- Edit button -->
                <%if(session.getAttribute("role").equals("Chef")){%>
                    <button><span style="font-size: 30px"><i class="fas fa-hammer"></i></span></button>
                <%}%>
            </form>
        </div>

        <!-- Take and add buttons -->
        <div id="productInfoButton">
            <button class="tab" style="border-radius: 8px 8px 0 0;" onclick="show('takeBatch', 'addBatch')">Tag Vare</button>
            <button class="tab" style="border-radius: 8px 8px 0 0;" onclick="show('addBatch', 'takeBatch')">Tilføj Vare</button>
        </div>

        <!-- Take batch menu -->
        <table class="productTable" id="takeBatch">

            <!-- Batches table header-->
            <tr>
                <th>Dato</th>
                <th>Batch Nummer</th>
                <th>Antal</th>
            </tr>
        <% //Batches under the current product
            List<Batch> batchList = (List) session.getAttribute("batchList");
            int i = 0;

            //Calculate total price of the product
            BigDecimal totalPrice = BigDecimal.valueOf(0);
            for(Batch batch : batchList){%>
                <tr>
                    <td><%=batch.getDate()%></td>
                    <td><%=batch.getBatchNumber()%></td>
                    <td><b><%=batch.getRemainingInBox()%></b>
                        <form action="TakeBatch" accept-charset="ISO-8859-1" method="post">
                            <input type="hidden" name="batchChosen" value="<%=i%>">
                            <input style="margin: 5px 5px 15px;" type="text" name="takeFromBatch" placeholder="Antal fra Kasse...">
                            <input style="width: 30%; min-width: 70px;" type="submit" value="Tag Vare" onclick="hide(id)" id="<%=i%>">
                            <input style="width: 50%; min-width: 100px;" type="submit" value="Tag én Kasse" onclick="hide(id)" id="<%="t"+i%>" name="oneBox">
                        </form>
                    </td>
                </tr>
            <%i++;
            totalPrice = totalPrice.add(batch.getValue());
            }%>
        </table>

        <!-- Add batch menu -->
        <table class="productTable" id="addBatch" hidden>

            <!-- Batches table header-->
            <tr>
                <th>Dato</th>
                <th>Batch Nummer</th>
                <th>Antal</th>
            </tr>
            <tr>
                <form action="AddBatch" method="post">
                <td id="date"></td>
                <td><input style="width: 80%;" type="text" name="batchNumber" placeholder="Batch nr..."></td>
                <td>
                    <input type="text" name="addBatch" placeholder="Antal kasser...">
                    <input style="width: 30%; min-width: 90px;" type="submit" value="Tilføj Batch" onclick="hide(id)" id="addBatchID">
                </td>
                </form>
            </tr>
        </table>

        <!-- Total price of the product -->
        <%if(session.getAttribute("role").equals("Chef")){%>
            <div class="priceBox" style="width: 100%" id="priceBox"><a>Totale produkt pris: <%=totalPrice%> kr.</a></div>
        <%}%>
    </aside>

    <script>

        //Get current date
        var today = new Date();
        document.getElementById('date').innerHTML = today.getFullYear() + "-" + today.getMonth() + "-" + today.getDate();

        //Hide either take batch menu or add batch menu
        function show(ID1, ID2) {
            var show = document.getElementById(ID1);
            var hide = document.getElementById(ID2);

            if(show.style.display === "none"){
                show.style.display = "block";
                if(hide !== "none"){
                    hide.style.display = "none";
                }
            }
        }

        //Hide buttons to prevent double or multiple clicks
        function hide(ID) {
            document.getElementById(ID).style.display = "none";
        }
    </script>
</body>
</html>
