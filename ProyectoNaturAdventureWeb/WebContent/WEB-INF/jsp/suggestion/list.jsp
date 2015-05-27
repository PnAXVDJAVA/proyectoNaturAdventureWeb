<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d"%>
<t:paginabasica title="Gestión de sugerencias">
<jsp:body>
		
		<h2>Lista de sugerencias</h2>
		
		<div class="table-responsive">
			<table class="table table-striped">
			
				<tr>
					<th>Nombre</th>
					<th>E-mail</th>
					<th>Tipo</th>
				</tr>
				
				<c:forEach items="${suggestions}" var="suggestion">
					<tr>
						<td>${suggestion.name}</td>
	                	<td>${suggestion.email}</td>
	                	<td>${suggestion.messageType}</td>
	                	<td><a href="${pageContext.request.contextPath}/suggestion/suggestionDetails/${suggestion.suggestion_code}.html"><span class="glyphicon glyphicon-info-sign"></span>  Más detalles</a></td>	                	
					</tr>
				
				</c:forEach>
			
			</table>
		</div>
		
		<a href="${pageContext.request.contextPath}/suggestion/add.html"><span class="glyphicon glyphicon-plus"></span>  Añadir sugerencia</a><br>
		
	
</jsp:body>
</t:paginabasica>


