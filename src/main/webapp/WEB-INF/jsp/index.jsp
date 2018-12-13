<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/dopstyle.css">
<title>Welcome</title>
</head>
<body>
	<div class="container"> 
	<h2>${message}</h2>
	
	<a href="${pageContext.request.contextPath}/group_">Groups List</a>
	</div>

</body>
</html>