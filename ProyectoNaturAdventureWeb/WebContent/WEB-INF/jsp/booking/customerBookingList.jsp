<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Lista de reservas | NaturAdventure">
<jsp:body>
	
		<h1>Lista de reservas</h1>
		
		<div class="form-group">
			<div class="col-xs-2">
               	<label class="control-label">Criterio de búsqueda: </label>
            </div>
            <div class="col-xs-3">
             	<div class="col-lg-9">
             		<select name="criterioBusqueda" id="criterioBusqueda" onChange="muestraCriterioBusqueda()" class="form-control">
             			<option value="todas">Todas</option>
             			<option value="anyo">Año</option>
             			<option value="mes">Mes</option>
             			<option value="dia">Día</option>
             		</select>
        		</div>
       		</div>
       		
       		<div class="col-xs-1">
            	<button onClick="buscar()" class="btn btn-custom">Buscar</button>
            </div>
			
       		<div class="clear"></div>
       	</div>
	    
	    
	    <div class="hidden" id="año">
			<div class="col-xs-2">
               	<label class="control-label">Año: </label>
            </div>
            <div class="col-xs-3">
             	<div class="col-lg-9">
             		<select name="año" class="form-control" id="añoValor">
             			<option value="2013">2013</option>
             			<option value="2014">2014</option>
             			<option value="2015">2015</option>
             			<option value="2016">2016</option>
             		</select>
        		</div>
       		</div>
       		<div class="clear"></div>
	    </div>
	    
	    <div class="hidden" id="mes">
			<div class="col-xs-2">
               	<label class="control-label">Mes: </label>
            </div>
            <div class="col-xs-3">
             	<div class="col-lg-9">
             		<select name="mes" class="form-control" id="mesValor">
             			<option value="1">Enero</option>
             			<option value="2">Febrero</option>
             			<option value="3">Marzo</option>
             			<option value="4">Abril</option>
             			<option value="5">Mayo</option>
             			<option value="6">Junio</option>
             			<option value="7">Julio</option>
             			<option value="8">Agosto</option>
             			<option value="9">Septiembre</option>
             			<option value="10">Octubre</option>
             			<option value="11">Noviembre</option>
             			<option value="12">Diciembre</option>
             		</select>
        		</div>
       		</div>
       		<div class="clear"></div>
	    </div>
   
   		<div class="hidden" id="dia">
				<div class="col-xs-2">
                	<label class="control-label">Día: </label>
                </div>
                <div class="col-xs-3">
	                <div class="col-lg-9">
	                	<input id="calendar-input" class="form-control" readonly/>
	           		</div>
	           		<div class="col-lg-3">
               			<span class="calendar-icon glyphicon glyphicon-calendar"></span>
               		</div>
           		</div>
           		<div class="clear"></div>
		</div>
		
		<div class="clear"></div>
		
		<div class="table-responsive">
			<table class="table table-striped">
		
				<tr>
					<th>Fecha propuesta</th>
					<th>Nombre de la actividad</th>
					<th>Estado</th>
				</tr>
				
				<c:forEach items="${customerBookings}" var="booking">
				<c:set var="status" scope="request" value='${booking.status}'/>
				
					<tr>
						<td>${booking.proposalPerformingDateString}</td>
	                	<td>${booking.activityName}</td>
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
	                	<td><a href="${pageContext.request.contextPath}/booking/customerBookingDetails.html?nif=${booking.customerNif}&codBooking=${booking.codBooking}"><span class="glyphicon glyphicon-info-sign"></span>  Más detalles</a></td>	                	
					</tr>
				
				</c:forEach>
		
			</table>
		</div>
		
		<script type="text/javascript">
		
	        datepickr('.calendar-icon', {
	            altInput: document.getElementById('calendar-input'),
	            dateFormat: 'd/m/Y'
	        });
		
			window.onload = setCriterio();
		
			function muestraCriterioBusqueda() {
				var criterio = document.getElementById( "criterioBusqueda" ).value;
				if( criterio == 'anyo' ) {
					document.getElementById( "año" ).className = "visible form-group";
					document.getElementById( "mes" ).className = "hidden";
					document.getElementById( "dia" ).className = "hidden";
				}
				else if( criterio == 'mes' ) {
					document.getElementById( "mes" ).className = "visible form-group";
					document.getElementById( "año" ).className = "hidden";
					document.getElementById( "dia" ).className = "hidden";
				}
				else if( criterio == 'dia' ) {
					document.getElementById( "dia" ).className = "visible form-group";
					document.getElementById( "año" ).className = "hidden";
					document.getElementById( "mes" ).className = "hidden";
				}
				else {
					document.getElementById( "año" ).className = "hidden";
					document.getElementById( "mes" ).className = "hidden";
					document.getElementById( "dia" ).className = "hidden";
				}
			}
			
			function buscar() {
				var criterio = document.getElementById( "criterioBusqueda" ).value;
				var valor;
				if( criterio == 'anyo' ) {
					valor = document.getElementById( "añoValor" ).value;
				}
				else if( criterio == 'mes' ) {
					valor = document.getElementById( "mesValor" ).value;
				}
				else if( criterio == 'dia' ) {
					valor = document.getElementById( "calendar-input" ).value;
				}
				location.href = '${pageContext.request.contextPath}/booking/customerBookingListSearch.html?nif=${profile.nif}&criterioBusqueda=' + criterio + '&valor=' + valor;
			}
			
			function setCriterio() {
				var criterio = "${criterioBusqueda}";
				var valor = "${valor}";
				if( criterio == "anyo" ) {
					document.getElementById( "criterioBusqueda" ).value = "anyo";
					document.getElementById( "año" ).className = "visible form-group";
					document.getElementById( "añoValor" ).value = valor;
				}
				else if( criterio == "mes" ) {
					document.getElementById( "criterioBusqueda" ).value = "mes";
					document.getElementById( "mes" ).className = "visible form-group";
					document.getElementById( "mesValor" ).value = valor;
				}
				else if( criterio == "dia" ) {
					document.getElementById( "criterioBusqueda" ).value = "dia";
					document.getElementById( "dia" ).className = "visible form-group";
					document.getElementById( "calendar-input" ).value = valor;
				}
			}
		
		</script>
			
</jsp:body>
</t:paginabasica>

