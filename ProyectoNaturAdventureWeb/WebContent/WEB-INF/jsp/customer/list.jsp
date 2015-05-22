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
					<th>Username</th>
				</tr>
				
				<c:forEach items="${customers}" var="customer">
				
					<tr>
						<td>${customer.nif}</td>
						<td>${customer.name}</td>
	                	<td>${customer.username}</td>
						<td><a href="${pageContext.request.contextPath}/customer/customerDetails/${customer.nif}.html"><span class="glyphicon glyphicon-info-sign"></span>  Más detalles</a>
					</tr>
				
				</c:forEach>
			
			</table>
		</div>
		
		<a href="add.html">Añadir cliente</a>
	
</jsp:body>
</t:paginabasica>


