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
					<th>Username</th>
				</tr>
				
				<c:forEach items="${instructors}" var="instructor">
				
					<tr>
						<td>${instructor.nif}</td>
						<td>${instructor.name}</td>
	                	<td>${instructor.userID}</td>
	                	<td><a href="${pageContext.request.contextPath}/instructor/instructorDetails/${instructor.nif}.html"><span class="glyphicon glyphicon-info-sign"></span>  Más detalles</a></td>
					</tr>
				
				</c:forEach>
			
			</table>
		</div>
		
		<a href="add.html"><span class="glyphicon glyphicon-plus"></span>  Añadir monitor</a>
</jsp:body>
</t:paginabasica>


