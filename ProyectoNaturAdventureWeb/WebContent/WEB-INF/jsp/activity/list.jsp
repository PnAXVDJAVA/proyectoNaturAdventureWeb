<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d"%>
<t:paginabasica title="Gestión de actividades">
<jsp:body>
		
		<h2>Lista de actividades</h2>
		
		<div class="table-responsive">
			<table class="table table-striped">
			
				<tr>
					<th>Nombre</th>
					<th>Precio por persona</th>
					<th>Nivel</th>
				</tr>
				
				<c:forEach items="${activities}" var="activity">
					<d:set var="picture" scope="request" value='${activity.pictureString}'/>
					<tr>
						<td>${activity.name}</td>
	                	<td>${activity.pricePerPerson} €</td>
	                	<td>${activity.level}</td>
	                	<td><a href="${pageContext.request.contextPath}/activity/activityDetails/${activity.codActivity}.html"><span class="glyphicon glyphicon-info-sign"></span>  Más detalles</a></td>	                	
					</tr>
				
				</c:forEach>
			
			</table>
		</div>
		
		<a href="add.html">Añadir actividad</a><br>
		
	
</jsp:body>
</t:paginabasica>


