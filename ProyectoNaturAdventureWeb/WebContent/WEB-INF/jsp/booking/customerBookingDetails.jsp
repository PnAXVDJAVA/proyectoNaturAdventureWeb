<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="status" scope="request" value='${booking.status}'/> 
<t:paginabasica title="Información de la reserva | NaturAdventure">
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
		    	<div class="col-sm-5"><span class="negrita">Nombre de la actividad:</span></div>
		    	<div class="col-sm-5"><a href="${pageContext.request.contextPath}/activity/activityCustomerDetails/${booking.codActivity}.html">${booking.activityName}</a></div>
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
		    
		</div>
	    
</jsp:body>
</t:paginabasica>
