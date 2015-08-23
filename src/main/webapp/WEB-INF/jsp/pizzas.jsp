<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 8/7/15
  Time: 6:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Pizzas List</title>
</head>
<body>


<table border="1">
    <thead><tr>
        <th>ID</th>
        <th>Name</th>
        <th>Type</th>
        <th>Price</th>
    </tr></thead>

  <c:forEach var="pizza" items="${pizzas}">
    <tr><td><p><c:out value="${pizza.id}" /></p></td>
        <td><p><c:out value="${pizza.name}" /></p></td>
        <td><p><c:out value="${pizza.type}" /></p></td>
        <td><p><c:out value="${pizza.price}" /></p></td>
      <td>
          <form method="get" action="edit" >
              <input type="hidden" name="pizzaId" value="${pizza.id}" />
              <input type="submit" value="Edit" />
          </form>
      </td>
    </tr>
  </c:forEach>

</table>
<sec:authorize access="hasRole('ADMIN')">
    <a href="createPizzaForm">Create new pizza</a><br>
</sec:authorize>

<%--<a href="createOrderForm">Create new order</a>--%>

<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}" method="post">
    <input type="submit" value="Log out"/>
    <%--в post  запрос будуть завжди добавлятись такі токени для захисту від csrf атаки--%>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

</body>
</html>
