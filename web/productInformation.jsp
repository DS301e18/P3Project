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
        //Product information header
        Product product = (Product) session.getAttribute("productChosen");%>
        <div class="productHeader">
            <label style="font-size: 40px"><%=product.getName()%></label>

            <!-- Should only be seen by a manager -->
            <%if(session.getAttribute("role").equals("Chef")){%>
                <button><span style="font-size: 30px"><i class="fas fa-hammer"></i></span></button>
            <%}%>
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
                        <td><%=batch.getRemainingInBox()%>
                            <form action="TakeBatch" method="post">
                                <input type="hidden" name="batchChosen" value="<%=i%>">
                                <input type="text" name="takeFromBatch" value="1">
                                <input type="submit" value="Tag Vare">
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
                <td><input type="text" name="batchNumber"></td>
                <td>
                    <input type="text" name="addBatch" value="<%=product.getBatchSize()%>">
                    <input type="submit" value="Tilføj Batch">
                </td>
                </form>
            </tr>
        </table>

        <div class="priceBox" style="width: 100%"><a>Total pris: <%=totalPrice%> kr.</a></div>
    </aside>

    <script>
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

        var today = new Date();
        document.getElementById('date').innerHTML = today.getFullYear() + "-" + today.getMonth() + "-" + today.getDate();
    </script>
</body>
</html>
