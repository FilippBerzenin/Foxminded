<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/dopstyle.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<title>Students list</title>
</head>
<body>
<c:set var="prefix" value="${pageContext.request.contextPath}" />
	<div class="container">
	<a href="${prefix}/">Back</a>
	<br />
	<h1>${group.id}</h1>
			<div>
			<h2>Add new student:</h2>
		<div class="form-group">
			<form action="/students/create/${group.id}" method="post">
				<input type="text" name="studentsName" placeholder="Students name">
				<input type="text" name="studentsSurename" placeholder="Students name">
				<button type="submit">Add new student</button>
			</form>
		</div>
		</div>
	<br />
	<h1>Students list</h1>
	<div class="form-group">
		<table border="1">
			<tr>
				<th>#</th>
				<th>ID</th>
				<th>Students name</th>
				<th>Students surename</th>
				<th>Group</th>
			</tr>
			<c:forEach var="student" items="${studentsList}" varStatus="counter">
				<tr>
					<td>${counter.count}</td>
					<td>${student.id}</td>
					<td>${student.name}</td>
					<td>${student.surename}</td>
					<td>${student.group.name}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<br />
	<br />
	</div>
</body>
</html>