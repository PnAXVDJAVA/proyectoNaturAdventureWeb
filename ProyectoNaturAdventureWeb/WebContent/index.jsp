<%@ page contentType="text/html; charset=utf-8" %>
<html> 
<head>
	<!-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/natacio.css"> -->
  	<title>Bienvenido a NaturAdventure</title> 
</head>
<body> 
	<ul>
		<li><a href="activity/list.html">Gestión de actividades</a></li>
		<li><a href="instructor/list.html">Gestión de monitores</a></li>
		<li><a href="degree/list.html">Gestión de títulos</a></li>
		<li><a href="booking/list.html">Gestión de reservas</a></li>
		<li><a href="login.html">Login</a></li>
		<li><a href="logout.html">Logout</a></li>
	</ul>
	<p>Has entrado como: ${user.username}</p>
</body> 
</html>