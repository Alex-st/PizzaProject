<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 8/25/15
  Time: 6:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>List of all user's orders</title>
</head>
<body>
<H1>Order list!!!</H1>
User name: ${name} </br>
Roles: ${roles} </br>
Balance:${balance} </br>

<table>
<tr>
  <td>
    <c:import url="/WEB-INF/jsp/menu.jsp"></c:import>
  </td>
  <td>
    <table border="1">
      <thead><tr>
        <th>ID</th>
        <th>Date</th>
        <th>Status</th>
        <th>Pizzas</th>
      </tr></thead>

      <c:forEach var="orders" items="${orders}" varStatus="outer">
        <tr><td><p><c:out value="${orders.orderId}" /></p></td>
          <td><p><c:out value="${orders.date}" /></p></td>
          <td><p><c:out value="${orders.orderStatus}" /></p></td>
          <td><p>
            <c:forEach var="pizzas" items="${orders.orderItems}" varStatus="inner">
              <c:out value="${pizzas.key.name}" /> - <c:out value="${pizzas.value}" /></br>
            </c:forEach>
          </p></td>
        </tr>
      </c:forEach>

    </table>

  </td>
</tr>
</table>

</body>
</html>
