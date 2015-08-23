<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 8/10/15
  Time: 4:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new pizza</title>
</head>
<body>
<h1>Create new pizza</h1>
<form action='addnew' method="post">
  <input type="hidden" name="id" value="${pizza.id}"/>
  Name
  <input type='text' name='name' value="${pizza.name}"/><br>
  Type
  <input type='text' name='type' value="${pizza.type}"/><br>
  Price
  <input type='text' name='price' value="${pizza.price}"/><br>

  <button type = "reset" name="Reset" value="reset">Reset</button>
  <button type="submit" name="send" value="Create">Create</button>
</form>

</body>
</html>
