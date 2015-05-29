<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Añadir monitor | NaturAdventure">
<jsp:body>

	<h2>Añadir monitor especializado</h2>
	<h3>Actividad: ${activityName}</h3>
	
		<div class="left">
		<p>Lista de <span class="negrita">monitores especializados</span> en la actividad ${activityName}:</p>
		
		<div class="table-responsive adapter">
				<table class="table table-striped">
					<tr>
						<th>NIF</th>
						<th>Nombre</th>
					</tr>
					<c:forEach items="${addedInstructors}" var="addedInstructor">
						<tr>
							<td>${addedInstructor.nif}</td>
							<td>${addedInstructor.name}</td>
							<td><a href="../rmsp.html?nif=${addedInstructor.nif}&codActivity=${codActivity}"><span class="glyphicon glyphicon-remove"></span>  Borrar monitor</a>
						</tr>
					</c:forEach>
				</table>
		</div>
	</div>
	
	<div class="right">
			
			<p>Lista de <span class="negrita">monitores disponibles</span>: </p>
			<div class="table-responsive adapter">
				<table class="table table-striped">
				
					<tr>
						<th>NIF</th>
						<th>Nombre</th>
					</tr>
					
					<c:forEach items="${availableInstructors}" var="availableInstructor">
						<tr>
							<td>${availableInstructor.nif}</td>
							<td>${availableInstructor.name}</td>
							<td><a href="../addsp.html?nif=${availableInstructor.nif}&codActivity=${codActivity}"><span class="glyphicon glyphicon-plus"></span>  Añadir monitor</a></td>
						</tr>
					</c:forEach>
			
				</table>
			</div>
	</div>
	

</jsp:body>
</t:paginabasica>