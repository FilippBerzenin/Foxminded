<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/dopstyle.css">
<title>Welcome</title>
</head>
<body>
	<div class="container"> 
	<h2>${message}</h2>	
	<a href="${pageContext.request.contextPath}/groups/show/all">Groups List</a>
	</div>

</body>
</html>