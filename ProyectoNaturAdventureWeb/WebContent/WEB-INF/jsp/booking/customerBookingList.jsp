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
					<th>Fecha de reserva</th>
					<th>Fecha propuesta</th>
					<th>Hora de comienzo</th>
					<th>Núm. participantes</th>
					<th>Estado</th>
					<th>Código de la actividad</th>
				</tr>
				
				<c:forEach items="${bookings}" var="booking">
				
					<tr>
	                	<td>${booking.bookingDate}</td>
						<td>${booking.proposalPerformingDate}</td>
	                	<td>${booking.startHour}</td>
	                	<td>${booking.numPartakers}</td>
	                	<td>${booking.status}</td>
	                	<td>${booking.codActivity}</td>
					</tr>
				
				</c:forEach>
		
			</table>
		</div>
	
</jsp:body>
</t:paginabasica>

