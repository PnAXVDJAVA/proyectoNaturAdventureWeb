<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>

	<head>
	<meta charset="utf-8">
	<!-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/natacio.css"> -->
	<title>Gestionar actividades</title>
	</head>
	
	<body>
	
		<h1>Lista de actividades</h1>
		
		<table>
		
			<tr>
				<th>Código</th>
				<th>Nombre</th>
				<th>Descripción</th>
				<th>Precio por persona</th>
				<th>Nivel</th>
				<th>Duración</th>
				<th>Min. número de participantes</th>
				<th>Max. número de participantes</th>
			</tr>
			
			<c:forEach items="${activities}" var="activity">
			
				<tr>
					<td>${activity.codActivity}</td>
					<td>${activity.name}</td>
                	<td>${activity.description}</td>
                	<td>${activity.pricePerPerson}</td>
                	<td>${activity.level}</td>
                	<td>${activity.duration}</td>
                	<td>${activity.minPartakers}</td>
                	<td>${activity.maxPartakers}</td>
                	<td><a href="update/${activity.codActivity}.html">Edita</a>
                	<td><a href="delete/${activity.codActivity}.html">Borra</a>
				</tr>
			
			</c:forEach>
		
		</table>
		
		<a href="add.html">Añadir actividad</a>
	
	</body>
	
</html>


