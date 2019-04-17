<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="/css/bootstrap.min.css">
	<link rel="stylesheet" href="/css/dopstyle.css">
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<c:set var="prefix" value="${pageContext.request.contextPath}" />
</head>
<body>
	<c:if test="${not empty message}">
		<div class="alert alert-success">${message}</div>
	</c:if>
	<h3>Add new user</h3>
	<form action="${prefix}/registration" method="post">
		<div>
			<label> User Name : <input type="text" name="username" />
			</label>
		</div>
		<div>
			<label> Password: <input type="password" name="password" />
			</label>
		</div>
		<input type="hidden" name="_csrf" value="${_csrf.token}">
		<div>
			<input type="submit" value="Sign In" />
		</div>
	</form>
</body>
</html>