<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Gestión de reservas">
<jsp:body>
	
		<h1>Lista de reservas</h1>
		
		<div class="table-responsive">
			<table class="table table-striped">
		
				<tr>
					<th>Fecha propuesta</th>
					<th>Código de la actividad</th>
					<th>Estado</th>
				</tr>
				
				<c:forEach items="${bookings}" var="booking">
				<c:set var="status" scope="request" value='${booking.status}'/>
				
					<tr>
						<td>${booking.proposalPerformingDateString}</td>
	                	<td>${booking.codActivity}</td>
	                	<td>
	                		<c:choose>
	                			<c:when test='${status == "pending" }'>
	                				<span class="orange">${booking.status}</span>
	                			</c:when>
	                			<c:when test='${status == "accepted" }'>
	                				<span class="green">${booking.status}</span>
	                			</c:when>
	                			<c:when test='${status == "denied" }'>
	                				<span class="red">${booking.status}</span>
	                			</c:when>
	                		</c:choose>
	                	</td>
	                	<td><a href="${pageContext.request.contextPath}/booking/customerBookingDetails/${booking.codBooking}.html"><span class="glyphicon glyphicon-info-sign"></span>  Más detalles</a></td>	                	
					</tr>
				
				</c:forEach>
		
			</table>
		</div>
			
</jsp:body>
</t:paginabasica>

