<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Groups list</title>
</head>
<body>
	<br />
	<br />
	<div>
		<table border="1">
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
			</tr>
			<c:forEach items="${groups}" var="group">
				<tr>
					<td>${group.id}</td>
					<td>${group.name}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>