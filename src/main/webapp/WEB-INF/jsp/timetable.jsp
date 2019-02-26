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
<c:set var="page" value="${page}" />
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<a href="${prefix}/">Back</a> <br />
		<div>
			<h2>Search student form:</h2>
			<div class="form-group">
				<form:form method="post" action="${prefix}/timetable/createRequest/student" modelAttribute="entityFor">
					<table>
						<tr>
							<td><font color="red"><form:errors path="name" /></font></td>
							<td><form:input class="form-control" type="text" path="name" value="Fil" placeholder="Enter name"/></td>
							<td><font color="red"><form:errors path="surename" /></font></td>
							<td><form:input class="form-control" type="text" path="surename" value="Fil" placeholder="Enter surename"/></td>
							<td><font color="red"><form:errors path="dateStartSearch" /></font></td>
							<td><form:input class="form-control" type="date" path="dateStartSearch" value="01-01-2019"/></td>
							<td><font color="red"><form:errors path="dateFinishSearch" /></font></td>
							<td><form:input class="form-control" type="date" path="dateFinishSearch" value="01-01-2020"/></td>
							<td><button type="submit">Search ${page}</button></td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
		<div>
			<h2>Search teacher form:</h2>
			<div class="form-group">
				<form:form method="post" action="${prefix}/timetable/createRequest/teacher" modelAttribute="entityFor">
					<table>
						<tr>
							<td><font color="red"><form:errors path="name" /></font></td>
							<td><form:input class="form-control" type="text" path="name" value="Vika" placeholder="Enter name"/></td>							
							<td><font color="red"><form:errors path="surename" /></font></td>
							<td><form:input class="form-control" type="text" path="surename" value="Berzenin" placeholder="Enter surename"/></td>							
							<td><font color="red"><form:errors path="dateStartSearch" /></font></td>
							<td><form:input class="form-control" type="date" path="dateStartSearch" value="01-01-2019" placeholder="Time start (HH-mm)"/></td>							
							<td><font color="red"><form:errors path="dateFinishSearch" /></font></td>
							<td><form:input class="form-control" type="date" path="dateFinishSearch" value="01-01-2020"  placeholder="Time start (HH-mm)"/></td>
							<td><button type="submit">Search ${page}</button></td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
		<h1>${page} list</h1>
				<c:if test="${not empty message}">
			<div class="alert alert-success">${message}</div>
		</c:if>
		<table class="table  table-sm">
			<thead class="table-info">
				<tr>
					<th>#</th>
					<th>ID</th>
					<th>Exercise name</th>
					<th>Date</th>
					<th>Begin time</th>
					<th>Finish time</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="entity" items="${listOfEntites}" varStatus="counter">
					<tr>
						<td>${counter.count}</td>
						<td>${entity.id}</td>
						<td>${entity.name}</td>
						<td>${entity.date}</td>
						<td>${entity.timeBegin}</td>
						<td>${entity.timeFinish}</td>
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