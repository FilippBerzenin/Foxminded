<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
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
<c:set var="page" value="${page}" />
<title>Courses page</title>
</head>
<body>
	<div class="container">
		<a href="${prefix}/">Back</a> <br />
		<div>
			<h2>Add new ${page}:</h2>
			<div class="form-group">
				<form:form method="post" action="${prefix}/${page}/create/"
					modelAttribute="entityFor">
					<table>
						<tr>
							<td><font color="red"><form:errors path="subject" /></font></td>
							<td><form:input path="subject" placeholder="${page} name" /></td>
							<td><button type="submit">Add new ${page}</button></td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
		<br />
		<h1>${page}list</h1>
		<c:if test="${not empty message}">
			<div class="alert alert-success">${message}</div>
		</c:if>
		<table class="table  table-sm">
			<thead class="table-info">
				<tr>
					<th>#</th>
					<th>ID</th>
					<th>${page}name</th>
					<th>Exercises list</th>
					<th>Teachers list</th>
					<th>Delete</th>
					<th>Update</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="entity" items="${listOfEntites}" varStatus="counter">
					<tr>
						<td>${counter.count}</td>
						<td>${entity.id}</td>
						<td>${entity.subject}</td>
						<td>
							<button type="button" class="btn btn-primary dropdown-toggle"
								data-toggle="dropdown">List of exercises</button>
							<div class="dropdown-menu container form-group">
								<table class="table  table-sm">
									<thead class="table-info">
										<tr>
											<th>#</th>
											<th>ID</th>
											<th>Name</th>
											<th>Date</th>
											<th>Time begin</th>
											<th>Time finish</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="exercise" items="${entity.exercises}"
											varStatus="counter">
											<tr>
												<td>${counter.count}</td>
												<td>${exercise.id}</td>
												<td>${exercise.name}</td>
												<td>${exercise.date}</td>
												<td>${exercise.timeBegin}</td>
												<td>${exercise.timeFinish}</td>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</td>
						<td>
							<button type="button" class="btn btn-primary dropdown-toggle"
								data-toggle="dropdown">List of teachers</button>
							<div class="dropdown-menu container form-group">
								<table class="table  table-sm">
									<thead class="table-info">
										<tr>
											<th>#</th>
											<th>ID</th>
											<th>Name</th>
											<th>Surename</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="teacher" items="${entity.teacher}"
											varStatus="counter">
											<tr>
												<td>${counter.count}</td>
												<td>${teacher.id}</td>
												<td>${teacher.name}</td>
												<td>${teacher.surename}</td>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</td>
						<td>
							<form action="${prefix}/${page}/delete/${entity.id}" method="post">
								<button type="submit" name="delete" value="Delete">Delete</button>
							</form>
						</td>
						<td>
							<button type="button" class="btn btn-primary dropdown-toggle"
								data-toggle="dropdown">Update</button>
							<div class="dropdown-menu container form-group">
								<form:form class="form-inline" method="post"
									action="${prefix}/${page}/update/" modelAttribute="entityFor">
									<div class="form-group">
										<form:input type="hidden" path="id" value="${entity.id}" />
										<font color="red"><form:errors path="subject" /></font>
										<form:input class="form-control" path="subject"
											value="${entity.subject}" />
										<button class="form-control" type="submit">Update</button>
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