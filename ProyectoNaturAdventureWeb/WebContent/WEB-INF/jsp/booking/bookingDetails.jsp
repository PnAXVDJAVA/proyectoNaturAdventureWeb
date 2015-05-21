<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="status" scope="request" value='${booking.status}'/> 
<t:paginabasica title="Gestión de reservas">
<jsp:body>
	
	<h2>Detalles de la reserva</h2>
	    
	    <div class="left">
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Código de reserva: </span></div>
		    	<div class="col-sm-5">${booking.codBooking}</div>
		    	<div class="clear"></div>
		    </div>
		    
		   <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Fecha propuesta:</span></div>
		    	<div class="col-sm-5">${booking.proposalPerformingDateString}</div>
		    	<div class="clear"></div>
		    </div>
		    
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Hora de comienzo:</span></div>
		    	<div class="col-sm-5">${booking.startHour}</div>
		    	<div class="clear"></div>
		    </div>
		    
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Fecha de reserva:</span></div>
		    	<div class="col-sm-5">${booking.bookingDateString}</div>
		    	<div class="clear"></div>
		    </div>
		    
			<div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Número de participantes:</span></div>
		    	<div class="col-sm-5">${booking.numPartakers}</div>
		    	<div class="clear"></div>
		    </div>
		    
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">NIF del cliente:</span></div>
		    	<div class="col-sm-5"><a href="${pageContext.request.contextPath}/customer/customerDetails/${booking.customerNif}.html">${booking.customerNif}</a></div>
		    	<div class="clear"></div>
		    </div>
		    
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Código de la actividad:</span></div>
		    	<div class="col-sm-5">${booking.codActivity}</div>
		    	<div class="clear"></div>
		    </div>
		    
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Estado:</span></div>
		    	<div class="col-sm-5">
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
		    	</div>
		    	<div class="clear"></div>
		    </div>
		    
		    <c:choose>
	           <c:when test='${status == "pending"}'>
				    <div class="formgroup detail-row">
				    	<div class="col-sm-5">
				    		<a href="${pageContext.request.contextPath}/booking/accept/${booking.codBooking}.html"><span class="glyphicon glyphicon-ok"></span>  Aceptar reserva</a>
				    	</div>
				    	
				    	<div class="col-sm-5">
				    		<a href="${pageContext.request.contextPath}/booking/deny/${booking.codBooking}.html" onclick="return confirm('¿Estás seguro de que quieres rechazar la reserva? Se enviará en email de rechazo al cliente que la solicitó.');"><span class="glyphicon glyphicon-remove"></span>  Rechazar reserva</a>
				    	</div>
				    </div>
		    	</c:when>
		    </c:choose>
		    
		</div>
		
		<div class="right">
			
		</div>
	    
</jsp:body>
</t:paginabasica>
