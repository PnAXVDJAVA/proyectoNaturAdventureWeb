<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Lista de monitores | NaturAdventure">
<jsp:body>
<h2>Lista de monitores</h2>
		
		<div class="table-responsive">
			<table class="table table-striped">
			
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
					<th>Username</th>
				</tr>
				
				<c:forEach items="${instructors}" var="instructor">
				
					<tr>
						<td>${instructor.nif}</td>
						<td>${instructor.name}</td>
	                	<td>${instructor.firstSurname}</td>
	                	<td>${instructor.secondSurname}</td>
	                	<td>${instructor.address}</td>
	                	<td>${instructor.telephone}</td>
	                	<td>${instructor.dateOfBirth}</td>
	                	<td>${instructor.email}</td>
	                	<td>${instructor.bankAccount}</td>
	                	<td>${instructor.userID}</td>
	                	<td><a href="update/${instructor.nif}.html"><span class="glyphicon glyphicon-pencil"></span></a></td>
	                	<td><a href="delete/${instructor.nif}.html" onclick="return confirm('¿Estás seguro de que quieres borrar el monitor?');"><span class="glyphicon glyphicon-trash"></span></a></td>
	                	<td><a href="changePwd/${instructor.userID}.html">Cambiar contraseña</a></td>
					</tr>
				
				</c:forEach>
			
			</table>
		</div>
		
		<a href="add.html"><span class="glyphicon glyphicon-plus"></span>  Añadir monitor</a>
</jsp:body>
</t:paginabasica>


