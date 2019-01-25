<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>
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
<c:set var="page" value="${page}" />
<title>Excercises page</title>
</head>
<body>
	<div class="container">
		<a href="${prefix}/">Back</a> <br />
		<div>
			<h2>Add new ${page}:</h2>
			<div class="form-group">
				<form:form method="post" action="/${page}/create/" modelAttribute="entityFor">
					<table>
						<tr>
							<td><font color="red"><form:errors path="name" /></font></td>
							<td><form:input path="name" placeholder="${page} name"/></td>
							<td><font color="red"><form:errors path="dayOfWeek" /></font></td>
							<td><form:input path="dayOfWeek" placeholder="${page} day of week"/></td>
							<td><font color="red"><form:errors path="timeBegin" /></font></td>
							<td><form:input path="timeBegin" placeholder="${page} time begin"/></td>
							<td><font color="red"><form:errors path="timeFinish" /></font></td>
							<td><form:input path="timeFinish" placeholder="${page} time finish"/></td>
							<td><button type="submit">Add new ${page}</button></td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
		<br />
		<h1>${page} list</h1>
				<c:if test="${not empty message}">
			<div class="alert alert-success">${message}</div>
		</c:if>
		<table class="table  table-sm">
			<thead class="table-info">
				<tr>
					<th>#</th>
					<th>ID</th>
					<th>${page} name</th>
					<th>Day of week</th>
					<th>Begin time</th>
					<th>Finish time</th>
					<th>Delete</th>
					<th>Update</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="entity" items="${listOfEntites}" varStatus="counter">
					<tr>
						<td>${counter.count}</td>
						<td>${entity.id}</td>
						<td>${entity.name}</td>
						<td>${entity.timeBegin}</td>
						<td>${entity.timeFinish}</td>
						<td>${entity.course}</td>
						<td><a href="${prefix}/${page}/delete/${entity.id}"
							onclick="return confirm('Are you sure?')">Delete</a></td>
						<td>
							<button type="button" class="btn btn-primary dropdown-toggle"
								data-toggle="dropdown">Update</button>
							<div class="dropdown-menu container form-group">
							
							<form:form class="form-inline" method="post"
									action="/${page}/update/" modelAttribute="entityFor">
							<div class="form-group">
							<form:input type="hidden" path="id" value="${entity.id}"/>
							<font color="red"><form:errors path="subject" /></font>
							<form:input class="form-control" path="subject" value="${entity.subject}"/>
<%-- 							<font color="red"><form:errors path="courses.name" /></font>
							<form:input class="form-control" path="courses.name" value="${entity.course.name}"/> --%>
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
</body>
</html>