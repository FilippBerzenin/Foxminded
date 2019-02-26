<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="/css/dopstyle.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<c:set var="prefix" value="${pageContext.request.contextPath}" />
<title>Groups page</title>
</head>
<body>
	<div class="container">
		<a href="${prefix}/">Back</a> <br />
		<div>
			<h2>Add new group:</h2>
			<div class="form-group">
				<form action="${prefix}/groups/create" method="post">
					<input type="text" name="newGroupsName" placeholder="Groups name">
					<button type="submit">Add new group</button>
				</form>
			</div>
		</div>
		<br />
		<div>Search groups by name</div>
		<form action="${prefix}/groups/search" method="post">
			<input type="text" name="filter" placeholder="Groups name for serch">
			<button type="submit">Search</button>
		</form>
		<h1>Groups list</h1>
		<c:if test="${not empty message}">
			<div class="alert alert-success">${message}</div>
		</c:if>
		<a href="${prefix}/groups/show/all">Get all groups</a>
		<table class="table  table-sm">
			<thead class="table-info">
				<tr>
					<th>#</th>
					<th>ID</th>
					<th>Groups name</th>
					<th>Delete</th>
					<th>Update</th>
					<th>Students page</th>
					<th>Courses list</th>
					<th>Add new course</th>
					<th>Remove course</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="group" items="${listOfEntites}" varStatus="counter">
					<tr>
						<td>${counter.count}</td>
						<td>${group.id}</td>
						<td>${group.name}</td>
						<td>
							<form action="${prefix}/${page}/delete/${group.id}" method="post">
								<button type="submit" name="delete" value="Delete">Delete</button>
							</form>
						<td>
							<button type="button" class="btn btn-primary dropdown-toggle"
								data-toggle="dropdown">Update</button>
							<div class="dropdown-menu container form-group">
								<form class="form-inline" method="post"
									action="${prefix}/groups/update/${group.id}">
									<div class="form-group">
										<label for="newGroupName">Enter new group name:</label> <input
											class="form-control" name="newGroupName" id="newGroupName"
											value="${group.name}">
										<button type="submit" class="btn btn-primary">Submit</button>
									</div>
								</form>
							</div>
						</td>
						<td><a href="${prefix}/students/${group.id}">Students
								page</a></td>
						<td><c:forEach var="courseFromList" items="${group.courses}">
								<c:out value="${courseFromList.subject}" />
							</c:forEach>
						<td>
							<button type="button" class="btn btn-primary dropdown-toggle"
								data-toggle="dropdown">Add new course</button>
							<div class="dropdown-menu container form-group">
								<form:form class="form-inline" method="POST"
									action="${prefix}/groups/addCourse/${group.id}"
									modelAttribute="course">
									<div class="form-group">
										<table>
											<tr>
												<td><form:label path="subject">Add course</form:label></td>
												<td><form:input path="subject" /></td>
											</tr>
											<tr>
												<td><input type="submit" value="Add course" /></td>
											</tr>
										</table>
									</div>
								</form:form>
							</div>
						</td>
						<td>
							<button type="button" class="btn btn-primary dropdown-toggle"
								data-toggle="dropdown">Remove course</button>
							<div class="dropdown-menu container form-group">
								<form:form class="form-inline" method="POST"
									action="${prefix}/groups/removeCourse/${group.id}"
									modelAttribute="course">
									<div class="form-group">
										<table>
											<tr>
												<td><form:label path="subject">Remove course</form:label></td>
												<td><form:input path="subject" /></td>
											</tr>
											<tr>
												<td><input type="submit" value="Remove course" /></td>
											</tr>
										</table>
									</div>
								</form:form>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>