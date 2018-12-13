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

<title>Groups list</title>
</head>
<body>
<c:set var="prefix" value="${pageContext.request.contextPath}" />
	<div class="container">
	<a href="${prefix}/">Back</a>
	<br />
		<div>
			<h2>Add new group:</h2>
		<div class="form-group">
			<form action="group_" method="post">
				<input type="text" name="newGroupsName" placeholder="Groups name">
				<button type="submit">Add new group</button>
			</form>
		</div>
		</div>
	<br />
	<h1>Groups list</h1>
	<div class="form-group">
		<table border="1">
			<tr>
				<th>#</th>
				<th>ID</th>
				<th>Groups name</th>
				<th>Options</th>
			</tr>
			<c:forEach var="group" items="${groupsList}" varStatus="counter">
				<tr>
					<td>${counter.count}</td>
					<td>${group.id}</td>
					<td>${group.name}</td>
					<td><a href="${pageContext.request.contextPath }/group_/${group.id}"
						onclick="return confirm('Are you sure?')">Delete</a></td>
					<td>
						<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">Update</button>
						<div class="dropdown-menu container form-group">
							<form class="form-inline" method="post"	action="/group_/${group.id}">
								<div class="form-group">
									<label for="newGroupName">Enter new group name:</label>
									<input class="form-control" name="newGroupName" id="newGroupName" value="${group.name}">
									<button type="submit" class="btn btn-primary">Submit</button>
								</div>
							</form>
						</div>
					</td>
					<td><a href="${pageContext.request.contextPath }/students/${group.id}">Students page</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<br />
	<br />
	</div>
</body>
</html>