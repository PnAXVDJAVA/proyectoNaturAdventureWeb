<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Lista de títulos | NaturAdventure">
<jsp:body>
		
		<h2>Lista de títulos</h2>
		
		<div class="table-responsive">
			<table class="table table-striped">
			
				<tr>
					<th>Código</th>
					<th>Nombre</th>
				</tr>
				
				<c:forEach items="${degrees}" var="degree">
					<tr>
						<td>${degree.codDegree}</td>
	                	<td>${degree.name}</td>
	                	<td><a href="${pageContext.request.contextPath}/degree/degreeDetails/${degree.codDegree}.html"><span class="glyphicon glyphicon-info-sign"></span>  Más detalles</a></td>	                	
					</tr>
				
				</c:forEach>
			
			</table>
		</div>
		
		<a href="${pageContext.request.contextPath}/degree/add.html"><span class="glyphicon glyphicon-plus"></span>  Añadir título</a><br>
		
	
</jsp:body>
</t:paginabasica>




