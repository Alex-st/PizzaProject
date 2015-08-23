<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 8/10/15
  Time: 5:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error page</title>
</head>
<br>
<h1>Error. Something happened</h1>
<c:if test="${url != null}"> URL: ${url} </c:if> </br>
Exception:${ex}<br>
</body>
</html>
