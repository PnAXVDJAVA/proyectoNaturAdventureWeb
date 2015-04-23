<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>

	<head>
	<meta charset="utf-8">
	<!-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/natacio.css"> -->
	<title>Gestionar títulos</title>
	</head>
	
	<body>
	
		<h1>Lista de títulos</h1>
		
		<table>
		
			<tr>
				<th>Código</th>
				<th>Nombre</th>
				<th>Descripción</th>
			</tr>
			
			<c:forEach items="${degrees}" var="degree">
			
				<tr>
					<td>${degree.codDegree}</td>
					<td>${degree.name}</td>
                	<td>${degree.description}</td>
                	<td><a href="update/${degree.codDegree}.html">Edita</a>
                	<td><a href="delete/${degree.codDegree}.html">Borra</a>
				</tr>
			
			</c:forEach>
		
		</table>
		
		<a href="add.html">Añadir título</a>
	
	</body>
	
</html>


