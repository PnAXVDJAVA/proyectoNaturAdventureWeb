<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Gestión de clientes">
<jsp:body>
		
		<h2>Lista de clientes</h2>
		
		<div class="table-responsive">
			<table class="table table-striped">
			
				<tr>
					<th>NIF</th>
					<th>Nombre</th>
					<th>Primer apellido</th>
					<th>Segundo apellido</th>
					<th>Email</th>
					<th>Teléfono</th>
					<th>Username</th>
				</tr>
				
				<c:forEach items="${customers}" var="customer">
				
					<tr>
						<td>${customer.nif}</td>
						<td>${customer.name}</td>
	                	<td>${customer.firstSurname}</td>
	                	<td>${customer.secondSurname}</td>
	                	<td>${customer.email}</td>
	                	<td>${customer.telephone}</td>
	                	<td>${customer.username}</td>
	                	<td><a href="update/${customer.nif}.html"><span class="glyphicon glyphicon-pencil"></span></a>
	                	<td><a href="delete/${customer.nif}.html" onclick="return confirm('¿Estás seguro de que quieres borrar el cliente?');"><span class="glyphicon glyphicon-trash"></span></a>
					</tr>
				
				</c:forEach>
			
			</table>
		</div>
		
		<a href="add.html">Añadir cliente</a>
	
</jsp:body>
</t:paginabasica>


