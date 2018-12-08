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
    <aside><%
        Product product = (Product) session.getAttribute("productChosen");%>
        <!-- Product information header -->
        <div class="productHeader">
            <form action="EditProductController" method="get">
                <label style="font-size: 40px"><%=product.getName()%></label>
                <!-- Should only be seen by a manager -->
                <%if(session.getAttribute("role").equals("Chef")){%>
                    <button><span style="font-size: 30px"><i class="fas fa-hammer"></i></span></button>
                <%}%>
            </form>
        </div>

        <div>
            <!-- TODO: Hide style -->
            <button class="tab" style="border-radius: 8px 8px 0 0;" onclick="show('takeBatch', 'addBatch')">Tag Vare</button>
            <button class="tab" style="border-radius: 8px 8px 0 0;" onclick="show('addBatch', 'takeBatch')">Tilføj Vare</button>
            <br>
            <br>
            <br>
        </div>

        <table class="productTable" id="takeBatch">
            <tr>
                <th>Dato</th>
                <th>Batch Nummer</th>
                <th>Antal</th>
            </tr>
                <% List<Batch> batchList = (List) session.getAttribute("batchList");
                int i = 0;
                BigDecimal totalPrice = BigDecimal.valueOf(0);
                for(Batch batch : batchList){%>
                    <tr>
                        <td><%=batch.getDate()%></td>
                        <td><%=batch.getBatchNumber()%></td>
                        <td><b><%=batch.getRemainingInBox()%></b>
                            <form action="TakeBatch" method="post">
                                <input type="hidden" name="batchChosen" value="<%=i%>">
                                <input style="margin: 5px 5px 15px;" type="text" name="takeFromBatch" placeholder="Antal fra Kasse...">
                                <input style="width: 30%; min-width: 70px;" type="submit" value="Tag Vare" onclick="hide(id)" id="tagProductID">
                            </form>
                            <form action="TakeBatch" method="get">
                                <input type="hidden" name="batchChosen" value="<%=i%>">
                                <input style="width: 50%; min-width: 100px;" type="submit" value="Tag én Kasse" onclick="hide(id)" id="tagBoxID">
                            </form>
                        </td>
                    </tr>
                <%i++;
                totalPrice = totalPrice.add(batch.getValue());
                }%>
        </table>

        <table class="productTable" id="addBatch" hidden>
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

        <%if(session.getAttribute("role").equals("Chef")){%>
            <div class="priceBox" style="width: 100%" id="priceBox"><a>Totale produkt pris: <%=totalPrice%> kr.</a></div>
        <%}%>
    </aside>

    <script>
        function show(ID1, ID2) {
            var show = document.getElementById(ID1);
            var hide = document.getElementById(ID2);

            document.getElementById("priceBox").style.display = "none";

            if(show.style.display === "none"){
                show.style.display = "block";
                if(hide !== "none"){
                    hide.style.display = "none";
                }
            }
        }

        function hide(ID){
            document.getElementById(ID).style.display = "none";
        }

        var today = new Date();
        document.getElementById('date').innerHTML = today.getFullYear() + "-" + today.getMonth() + "-" + today.getDate();
    </script>
</body>
</html>
