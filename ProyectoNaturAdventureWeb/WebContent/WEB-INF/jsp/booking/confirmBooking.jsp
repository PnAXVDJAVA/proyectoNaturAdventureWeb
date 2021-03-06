<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:paginabasica title="Nueva reserva | NaturAdventure">
<jsp:body>
	    
	    <div class="left">
	    	<h3>Confirmación de la reserva</h3>
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
		    	<div class="col-sm-5"><span class="negrita">Precio total:</span></div>
		    	<div class="col-sm-5">${precio} €</div>
		    	<div class="clear"></div>
		    </div>
		</div>
		
		<div class="right">
	    	<h3>Datos de la actividad</h3>
	    	<div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Nombre: </span></div>
		    	<div class="col-sm-6">${activity.name}</div>
		    	<div class="clear"></div>
		    </div>
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Precio por persona: </span></div>
		    	<div class="col-sm-5">${activity.pricePerPerson} €</div>
		    	<div class="clear"></div>
		    </div>
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Duración: </span></div>
		    	<div class="col-sm-5">${activity.duration} minutos</div>
		    	<div class="clear"></div>
		    </div>
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Mín. núm. participantes: </span></div>
		    	<div class="col-sm-5">${activity.minPartakers}</div>
		    	<div class="clear"></div>
		    </div>
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Máx. núm. participantes: </span></div>
		    	<div class="col-sm-5">${activity.maxPartakers}</div>
		    	<div class="clear"></div>
		    </div>
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Nivel de dificultad: </span></div>
		    	<div class="col-sm-5">${activity.level}</div>
		    	<div class="clear"></div>
		    </div>
	    </div>
	    
	    <div class="left">
		    <form:form method="post" modelAttribute="booking" role="form" class="form form-horizontal" 
			    action="${pageContext.request.contextPath}/booking/acceptBooking/${activity.codActivity}.html">
		    
		    	<div class="form-group">
			            <div class="col-sm-5">
			    			¿Deseas confirmar la reserva?
			    		</div>
			    </div>
		    	
		    	<form:input type="hidden" path="proposalPerformingDateString"/>
		    	<form:input type="hidden" path="numPartakers"/>
		    	<form:input type="hidden" path="bookingDateString"/>
		    	<form:input type="hidden" path="startHour"/>	    	
		    	
		    	<div class="col-sm-4">    
	           		<button onClick="location.href='${pageContext.request.contextPath}/booking/acceptBooking/${activity.codActivity}.html'" type="submit" class="btn btn-custom btn-padding">Confirmar reserva</button>
		    	</div>   
		    </form:form>
           	<button onClick="history.back(-1)" class="btn btn-custom btn-padding">Cancelar reserva</button>
	    </div>
	    
</jsp:body>
</t:paginabasica>
