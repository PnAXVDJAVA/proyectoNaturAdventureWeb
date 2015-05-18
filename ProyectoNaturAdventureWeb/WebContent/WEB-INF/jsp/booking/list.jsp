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
					<th>Fecha de reserva</th>
					<th>Fecha propuesta</th>
					<th>Hora de comienzo</th>
					<th>Núm. participantes</th>
					<th>NIF del cliente</th>
					<th>Código de la actividad</th>
					<th>Estado</th>
				</tr>
				
				<c:forEach items="${bookings}" var="booking">
				<c:set var="status" scope="request" value='${booking.status}'/>
				
					<tr>
						<td>${booking.codBooking}</td>
	                	<td>${booking.bookingDate}</td>
						<td>${booking.proposalPerformingDate}</td>
	                	<td>${booking.startHour}</td>
	                	<td>${booking.numPartakers}</td>
	                	<td>${booking.customerNif}</td>
	                	<td>${booking.codActivity}</td>
	                	<td>${booking.status}</td>
	                	<td><a href="update/${booking.codBooking}.html">Edita</a>
	                	<td><a href="delete/${booking.codBooking}.html">Borra</a>
	                	<c:choose>
	                		<c:when test='${status == "pending"}'>	           
	                			<td><a href="accept/${booking.codBooking}.html"><span class="glyphicon glyphicon-ok"></span>  Aceptar</a>
	                			<td><a href="deny/${booking.codBooking}.html" onclick="return confirm('¿Estás seguro de que quieres rechazar la reserva? Se enviará en email de rechazo al cliente que la solicitó.');"><span class="glyphicon glyphicon-remove"></span>  Rechazar</a></td>
							</c:when>
						</c:choose>
					</tr>
				
				</c:forEach>
		
			</table>
		</div>
		
		<a href="add.html">Añadir reserva</a>
	
</jsp:body>
</t:paginabasica>

