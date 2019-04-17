<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<c:set var="prefix" value="${pageContext.request.contextPath}" />
<title>Login</title>
</head>
<body>
	<div class="container">
		<h1>Login page</h1>
		<div class="wrapper fadeInDown">
			<div id="formContent">
				<form action="/login" method="post">
					<input type="text" id="username" class="fadeIn second"
						name="username" placeholder="username"> <input
						type="password" id="password" class="fadeIn third" name="password"
						placeholder="password"> <input type="hidden" name="_csrf"
						value="${_csrf.token}"> <input type="submit"
						class="fadeIn fourth" value="Log In">
				</form>

				<div id="formFooter">
					<a class="underlineHover" href="${prefix}/registration">Add new
						user</a>
				</div>

			</div>
		</div>
	</div>
</body>
</html>