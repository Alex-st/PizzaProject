<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 8/12/15
  Time: 2:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>New Order Form</title>
</head>
<body>
<H1>New order!!!</H1>
<%--User name: ${name} </br>--%>
<%--Roles: ${roles} </br></br>--%>

<c:if test="${not empty OrderPrice}">
  Your order for ${OrderPrice} was approved.</br>
</c:if>

${OrderPrice}<br>
<c:out value="${OrderPrice}"/><br>

<form method="post" action="placeOrder" >
  <table border="1">
    <thead><tr>
      <th>ID</th>
      <th>Name</th>
      <th>Type</th>
      <th>Price</th>
      <th>Input ammount</th>
    </tr></thead>

    <c:forEach var="order" items="${map}">
      <tr><td><p><c:out value="${order.key.id}" /></p></td>
        <td><p><c:out value="${order.key.name}" /></p></td>
        <td><p><c:out value="${order.key.type}" /></p></td>
        <td><p><c:out value="${order.key.price}" /></p></td>
        <td>
          <input type="text" name="${order.key.id}" value="${order.value}" />
        </td>
      </tr>
    </c:forEach>
  </table>
  <input type="submit" value="Create order"/>
</form>

<%--<form method="post" action="placeOrder" >--%>
<%--<table border="1">--%>
  <%--<thead><tr>--%>
    <%--<th>ID</th>--%>
    <%--<th>Name</th>--%>
    <%--<th>Type</th>--%>
    <%--<th>Price</th>--%>
    <%--<th>Input amount</th>>--%>
  <%--</tr></thead>--%>

  <%--<c:forEach var="pizza" items="${pizzas}">--%>
    <%--<tr><td><p><c:out value="${pizza.id}" /></p></td>--%>
      <%--<td><p><c:out value="${pizza.name}" /></p></td>--%>
      <%--<td><p><c:out value="${pizza.type}" /></p></td>--%>
      <%--<td><p><c:out value="${pizza.price}" /></p></td>--%>
      <%--<td>--%>
          <%--<input type="text" name="number" value="" />--%>
      <%--</td>--%>
    <%--</tr>--%>
  <%--</c:forEach>--%>
<%--</table>--%>
  <%--<input type="submit" value="Create order"/>--%>
<%--</form>--%>



</body>
</html>
