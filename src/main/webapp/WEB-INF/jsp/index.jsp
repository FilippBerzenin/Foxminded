<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/dopstyle.css">
<title>Welcome</title>
</head>
<body>
	<div class="container">
		<h2>${message}</h2>
			<div class="table-responsive">
		<a href="${pageContext.request.contextPath}/groups/show/all"
			class="list-group-item">Groups page</a> <br> <a
			href="${pageContext.request.contextPath}/teachers/show/all"
			class="list-group-item">Teachers page</a> <br> <a
			href="${pageContext.request.contextPath}/courses/show/all"
			class="list-group-item">Courses page</a> <br> <a
			href="${pageContext.request.contextPath}/excercises/show/all"
			class="list-group-item">Exercises page</a> <br>
	</div>
	</div>
</body>
</html>