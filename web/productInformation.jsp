<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Batch" %><%--
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

</body>
    <aside><%
        //Product information header
        Product product = (Product) session.getAttribute("productChosen");%>
        <div class="productHeader">
            <label style="font-size: 40px"><%=product.getName()%></label>
            <button><span style="font-size: 30px"><i class="fas fa-hammer"></i></span></button>
        </div>

        <div>
            <button class="tab" style="border-radius: 8px 8px 0 0;">Tag Vare</button>
            <button class="tab" style="border-radius: 8px 8px 0 0;">Tilføj Vare</button>
            <br>
            <br>
            <br>
        </div>

        <table class="productTable">
            <tr>
                <th>Dato</th>
                <th>Batch Nummer</th>
                <th>Antal</th>
            </tr>
                <% List<Batch> batchList = (List) session.getAttribute("batchList");
                int i = 0;
                for(Batch batch : batchList){%>
                    <tr>
                        <td><%=batch.getDate()%></td>
                        <td><%=batch.getBatchNumber()%></td>
                        <td><%=batch.getRemainingInBox()%>
                            <form action="Batch" method="post">
                                <input type="hidden" name="batchChosen" value="<%=i%>">
                                <input type="text" name="takeFromBatch">
                                <input type="submit" value="Tag Vare">
                            </form>
                        </td>
                    </tr>
                <%i++;
                }%>

        </table>
    </aside>
</html>
