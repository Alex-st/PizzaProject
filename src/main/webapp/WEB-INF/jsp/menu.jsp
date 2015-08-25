<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 8/23/15
  Time: 11:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<ul>
  <li><a href="${pageContext.request.contextPath}/jsp/pizza/">Pizza Catalog</a>
  </li>
  <li><a href="${pageContext.request.contextPath}/jsp/order/">Make Order</a>
  </li>
  <li><a href="${pageContext.request.contextPath}/login.jsp">Sign in</a>
  </li>
  <sec:authorize access="hasRole('USER')">
    <li><a href="${pageContext.request.contextPath}/jsp/pizza/showUserOrders">Show user orders</a>
    </li>
  </sec:authorize>
  <sec:authorize access="hasRole('ADMIN')">
    <li><a href="${pageContext.request.contextPath}/jsp/pizza/createPizzaForm">Create new pizza</a>
    </li>
    <li><a href="${pageContext.request.contextPath}/jsp/order/showAllOrders">Show all orders</a>
    </li>
  </sec:authorize>
</ul>
