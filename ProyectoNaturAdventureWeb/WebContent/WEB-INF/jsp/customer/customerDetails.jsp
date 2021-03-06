<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Perfil de usuario | NaturAdventure">
<jsp:body>
	
	<h2>Detalles del cliente</h2>
	    
	    <div class="left">
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">NIF: </span></div>
		    	<div class="col-sm-5">${customer.nif}</div>
		    	<div class="clear"></div>
		    </div>
		    
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Nombre:</span></div>
		    	<div class="col-sm-5">${customer.name}</div>
		    	<div class="clear"></div>
		    </div>
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Apellidos:</span></div>
		    	<div class="col-sm-5">${customer.firstSurname} ${customer.secondSurname}</div>
		    	<div class="clear"></div>
		    </div>
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Email:</span></div>
		    	<div class="col-sm-5">${customer.email}</div>
		    	<div class="clear"></div>
		    </div>
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Teléfono:</span></div>
		    	<div class="col-sm-5">${customer.telephone}</div>
		    	<div class="clear"></div>
		    </div>
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Nombre de usuario:</span></div>
		    	<div class="col-sm-5">${customer.username}</div>
		    	<div class="clear"></div>
		    </div>
		    <div class="formgroup detail-row">
			    <div class="col-sm-5"><a href="${pageContext.request.contextPath}/customer/update/${customer.nif}.html"><span class="glyphicon glyphicon-pencil"></span>  Editar datos</a></div>
		    	<div class="clear"></div>
		    </div>
		     <div class="formgroup detail-row">
			    <div class="col-sm-5"><a href="${pageContext.request.contextPath}/customer/changePwd/${customer.username}.html"><span class="glyphicon glyphicon-cog"></span>  Cambiar contraseña</a></div>
		    	<div class="clear"></div>
		    </div>
		    <div class="formgroup detail-row">
			    <div class="col-sm-5"><a href="${pageContext.request.contextPath}/customer/delete/${customer.nif}.html" onclick="return confirm('¿Estás seguro de que quieres borrar el cliente?');"><span class="glyphicon glyphicon-trash"></span>  Eliminar cliente</a></div>
		    	<div class="clear"></div>
		    </div>
		</div>
		
		<div class="right">
			<p><span class="negrita">Lista de reservas:</span></p>
			<div class="table-responsive">
				<table class="table table-striped">
					
					<tr>
						<th>Fecha propuesta</th>
						<th>Fecha de reserva</th>
						<th>Estado</th>
					</tr>
					
					<c:forEach items="${bookings}" var="booking">
							<c:set var="status" scope="request" value='${booking.status}'/>
							
							<tr>
								<td>${booking.proposalPerformingDateString}</td>
								<td>${booking.bookingDateString}</td>
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
			                	<td><a href="${pageContext.request.contextPath}/booking/bookingDetails/${booking.codBooking}.html"><span class="glyphicon glyphicon-info-sign"></span>  Más detalles</a></td>
							</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	    
</jsp:body>
</t:paginabasica>
