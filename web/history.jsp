<%@ page import="model.Transactions" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 07/12/2018
  Time: 21.15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <%%>

    <div class="productHeader">
        <label style="font-size: 40px">Historik</label>
    </div>
    <form action="History" method="post">
        <input type="text" placeholder="Indtast Historik Størrelse..." name="historyInput">
        <input type="text" placeholder="Søg..." name="historySearch" value="">
        <input type="submit" value="Udfør">
    </form>

    <table class="productTable">
        <tr>
            <th>Dato</th>
            <th>Vare</th>
            <th>Batch Nr.</th>
            <th>Type</th>
            <th>Antal</th>
            <th>Medarbejder</th>
        </tr><%
        List<Transactions> history = (List) session.getAttribute("history");
        for (Transactions transaction : history){%>
        <tr>
            <td><%=transaction.getTimestamp()%></td>
            <td><%=transaction.getProduct()%></td>
            <td><%=transaction.getBatch()%></td>
            <td><%=transaction.getTranstype()%></td>
            <td><%=transaction.getAmount()%></td>
            <td><%=transaction.getName()%></td>
        </tr>
        <%}%>

    </table>
</body>
</html>
