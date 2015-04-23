<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>

	<head>
	<meta charset="utf-8">
	<!-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/natacio.css"> -->
	<title>Gestionar monitores</title>
	</head>
	
	<body>
	
		<h1>Lista de monitores</h1>
		
		<table>
		
			<tr>
				<th>NIF</th>
				<th>Nombre</th>
				<th>Primer apellido</th>
				<th>Segundo apellido</th>
				<th>Dirección</th>
				<th>Teléfono</th>
				<th>Fecha de cumpleaños</th>
				<th>E-mail</th>
				<th>Cuenta bancaria</th>
				<th>User ID</th>
				<th>Contraseña</th>
			</tr>
			
			<c:forEach items="${instructors}" var="instructor">
			
				<tr>
					<td>${instructor.nif}</td>
					<td>${instructor.name}</td>
                	<td>${instructor.firstSurname}</td>
                	<td>${instructor.secondSurname}</td>
                	<td>${instructor.address}</td>
                	<td>${instructor.telephone}</td>
                	<td>${instructor.dateOfBirthString}</td>
                	<td>${instructor.email}</td>
                	<td>${instructor.bankAccount}</td>
                	<td>${instructor.userID}</td>
                	<td>${instructor.password}</td>
                	<td><a href="update/${instructor.nif}.html">Edita</a>
                	<td><a href="delete/${instructor.nif}.html">Borra</a>
				</tr>
			
			</c:forEach>
		
		</table>
		
		<a href="add.html">Añadir monitor</a>
	
	</body>
	
</html>


