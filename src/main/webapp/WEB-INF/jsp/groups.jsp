<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<title>Groups page</title>
</head>
<body>
	<div class="container">
		<a href="${prefix}/">Back</a> <br />
		<div>
			<h2>Add new group:</h2>
			<div class="form-group">
				<form action="/groups/create" method="post">
					<input type="text" name="newGroupsName" placeholder="Groups name">
					<button type="submit">Add new group</button>
				</form>
			</div>
		</div>
		<br />
		<div>Search groups by name</div>
		<form action="/groups/search" method="post">
			<input type="text" name="filter" placeholder="Groups name for serch">
			<button type="submit">Search</button>
		</form>
		<h1>Groups list</h1>
		<c:if test="${not empty message}">
			<div class="alert alert-success">${message}</div>
		</c:if>
		<a href="${pageContext.request.contextPath}/groups/show/all">Get
			all groups</a>
		<table class="table  table-sm">
			<thead class="table-info">
				<tr>
					<th>#</th>
					<th>ID</th>
					<th>Groups name</th>
					<th>Delete</th>
					<th>Update</th>
					<th>Students page</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="group" items="${listOfEntirs}" varStatus="counter">
					<tr>
						<td>${counter.count}</td>
						<td>${group.id}</td>
						<td>${group.name}</td>
						<td><a href="${prefix}/groups/delete/${group.id}"
							onclick="return confirm('Are you sure?')">Delete</a></td>
						<td>
							<button type="button" class="btn btn-primary dropdown-toggle"
								data-toggle="dropdown">Update</button>
							<div class="dropdown-menu container form-group">
								<form class="form-inline" method="post"
									action="/groups/update/${group.id}">
									<div class="form-group">
										<label for="newGroupName">Enter new group name:</label> <input
											class="form-control" name="newGroupName" id="newGroupName"
											value="${group.name}">
										<button type="submit" class="btn btn-primary">Submit</button>
									</div>
								</form>
							</div>
						</td>
						<td><a href="${prefix}/students/${group.id}">Students page</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>