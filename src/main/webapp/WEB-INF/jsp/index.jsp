<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/dopstyle.css">
<c:set var="prefix" value="${pageContext.request.contextPath}" />
<title>Welcome</title>
</head>
<body>
	<div class="container">
		<h1>Hello</h1>
		<form action="/logout" method="post">
			<input type="submit" value="Sign Out" />
		</form>
		<br>
		<div class="table-responsive">
			<a href="${prefix}/main" class="list-group-item">Main page</a> <br>
			<a href="${prefix}/login" class="list-group-item">Login page</a> <br>
			<a href="${prefix}/registration" class="list-group-item">Registration
				page</a>
		</div>
	</div>
</body>
</html>