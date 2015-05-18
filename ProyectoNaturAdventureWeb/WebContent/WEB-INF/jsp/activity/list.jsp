<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Gestión de actividades">
<jsp:body>
		
		<h2>Lista de actividades</h2>
		
		<div class="table-responsive">
			<table class="table table-striped">
			
				<tr>
					<th>Código</th>
					<th>Nombre</th>
					<th>Descripción</th>
					<th>Precio por persona</th>
					<th>Nivel</th>
					<th>Duración</th>
					<th>Min. número de participantes</th>
					<th>Max. número de participantes</th>
					<th>Foto</th>
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
						<td><img src="data:image/jpeg;base64,${activity.pictureString}" width="100" height="70"></td>
	                	<td><a href="update/${activity.codActivity}.html"><span class="glyphicon glyphicon-pencil"></span></a>
	                	<td><a href="delete/${activity.codActivity}.html" onclick="return confirm('¿Estás seguro de que quieres borrar la actividad?');"><span class="glyphicon glyphicon-trash"></span></a>
						<td><a href="addSpecializedInstructor/${activity.codActivity}.html">Añadir monitor especializado</a></td>
					</tr>
				
				</c:forEach>
			
			</table>
		</div>
		
		<a href="add.html">Añadir actividad</a><br>
		
	
</jsp:body>
</t:paginabasica>


