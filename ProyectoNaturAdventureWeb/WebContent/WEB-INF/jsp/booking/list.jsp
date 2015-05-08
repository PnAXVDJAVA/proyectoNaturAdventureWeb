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
					<th>Código</th>
					<th>Fecha propuesta</th>
					<th>Núm. participantes</th>
					<th>Fecha de reserva</th>
					<th>NIF del cliente</th>
					<th>Código de la actividad</th>
					<th>Hora de comienzo</th>
					<th>Estado</th>
				</tr>
				
				<c:forEach items="${bookings}" var="booking">
				
					<tr>
						<td>${booking.codBooking}</td>
						<td>${booking.proposalPerformingDate}</td>
	                	<td>${booking.numPartakers}</td>
	                	<td>${booking.bookingDate}</td>
	                	<td>${booking.customerNif}</td>
	                	<td>${booking.codActivity}</td>
	                	<td>${booking.startHour}</td>
	                	<td>${booking.status}</td>
	                	<td><a href="update/${booking.codBooking}.html">Edita</a>
	                	<td><a href="delete/${booking.codBooking}.html">Borra</a>
					</tr>
				
				</c:forEach>
		
			</table>
		</div>
		
		<a href="add.html">Añadir reserva</a>
	
</jsp:body>
</t:paginabasica>

