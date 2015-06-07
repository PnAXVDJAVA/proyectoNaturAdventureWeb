<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
		    
		    <br>
		    
		    <p>Elige el cliente:</p>
		    
		    <div class="table-responsive">
				<table class="table table-striped">
					
					<tr>
						<th>NIF</th>
		    		</tr>
		    		
				    <c:forEach items="${customerList}" var="customer">
				    
				    	<tr>
				    		<td>${customer.nif}</td>
				    		<td><a href="${pageContext.request.contextPath}/customer/customerDetails/${customer.nif}.html" target="_blank"><span class="glyphicon glyphicon-info-sign"></span>  Detalles cliente</a></td>
				    		<td><a id="${customer.nif}" class="mouse" onClick="elegirCliente( '${customer.nif}' )"><span class="glyphicon glyphicon-ok"></span>  Elegir cliente</a><td>			    	
				    	</tr>
				    
				    </c:forEach>
		    
		    	</table>
		    </div>
		    
		    <div class="left">
			  <form:form method="post" modelAttribute="booking" role="form" class="form form-horizontal" 
			    action="${pageContext.request.contextPath}/booking/acceptBooking/${activity.codActivity}.html" 
			    onsubmit="return confirmarReserva();">
		    
		   		<div class="form-group">
			            <div class="col-sm-5">
			    			¿Deseas confirmar la reserva?
			    		</div>
			    </div>
		    	
		    	<form:input type="hidden" path="proposalPerformingDateString"/>
		    	<form:input type="hidden" path="numPartakers"/>
		    	<form:input type="hidden" path="bookingDateString"/>
		    	<form:input type="hidden" path="startHour"/>
		    	<form:input type="hidden" path="customerNif" id="customerNif"/>	    	
		    	
			    <div class="col-sm-4">    
	           		<button onClick="location.href='${pageContext.request.contextPath}/booking/acceptBooking/${activity.codActivity}.html'" type="submit" class="btn btn-custom btn-padding">Confirmar reserva</button>
		    	</div>   
		    </form:form>
           	<button onClick="history.back(-1)" class="btn btn-custom btn-padding">Cancelar reserva</button>
	    </div>
	    
	    <div class="hidden alert alert-danger alert-dismissable" id="alert">
		  	Tienes que elegir un cliente.
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
	    
	    <script type="text/javascript">
	    
	    	function elegirCliente( nif ) {
	    		var actualCustomerNif = document.getElementById( "customerNif" ).value;
	    		if( actualCustomerNif != "" ) {	    			
	   	 			document.getElementById( '' + actualCustomerNif ).className = "visible mouse";
	    		}
	    		document.getElementById( '' + nif ).className = "hidden";
	    		document.getElementById( "customerNif" ).value = nif;
	    	}
	    	
	    	function confirmarReserva() {
	    		var actualCustomerNif = document.getElementById( "customerNif" ).value;
	    		if( actualCustomerNif == "" ) {
	    			document.getElementById( "alert" ).className = "visible alert alert-danger alert-dismissable";
	    			return false;
	    		}
	    		return true;
	    	}
	    
	    </script>
	    
</jsp:body>
</t:paginabasica>
