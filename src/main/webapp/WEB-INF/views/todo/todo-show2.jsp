<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Todo 목록</title>
</head>
<body>
<%@ include file="../header.jsp"%>
<h1>할 일 목록2</h1>
<ul>
  <li><b>할 일 리스트</b></li>
  <c:forEach var="todo" items="${todoList}">
    <li>${todo.todo}</li>
<%--    todoList각각의 list를 todo로 두고 todo.gettodo--%>
  </c:forEach>
</ul>
</body>
</html>