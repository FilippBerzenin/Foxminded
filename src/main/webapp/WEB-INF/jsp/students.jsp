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
<title>Students page</title>
</head>
<body>
	<div class="container">
		<a href="${prefix}/groups/show/all">Back</a> <br />
		<div>
			<h2>Add new student:</h2>
			<div class="form-group">
				<form action="/students/create/${group_id}">
					<input type="text" name="studentsName" placeholder="Students name">
					<input type="text" name="studentsSurename" placeholder="Students surename"
						placeholder="Students name">
					<button type="submit">Add new student</button>
				</form>
			</div>
		</div>
		<br />
		<h1>Students list</h1>
		<table class="table  table-sm">
			<thead class="table-info">
				<tr>
					<th>#</th>
					<th>ID</th>
					<th>Students name</th>
					<th>Students surename</th>
					<th>Group</th>
					<th>Delete</th>
					<th>Update</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="student" items="${studentsList}" varStatus="counter">
					<tr>
						<td>${counter.count}</td>
						<td>${student.id}</td>
						<td>${student.name}</td>
						<td>${student.surename}</td>
						<td>${student.group.name}</td>
						<td><a href="${prefix}/students/delete/${student.id}"
							onclick="return confirm('Are you sure?')">Delete</a></td>
						<td>
							<button type="button" class="btn btn-primary dropdown-toggle"
								data-toggle="dropdown">Update</button>
							<div class="dropdown-menu container form-group">
								<form class="form-inline" method="post"
									action="/students/update/${student.id}">
									<div class="form-group">
										<label for="newStudentName">Enter new student name:</label> <input
											class="form-control" name="newStudentName"
											id="newStudentName" value="${student.name}"> <label
											for="newStudentSurename">Enter new student surename:</label>
										<input class="form-control" name="newStudentSurename"
											id="newStudentSurename" value="${student.surename}">
										<label for="newStudentGroup">Enter new student group:</label>
										<input class="form-control" name="newStudentGroup"
											id="newStudentGroup" value="${student.group.name}">
										<button type="submit" class="btn btn-primary">Submit</button>
									</div>
								</form>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>