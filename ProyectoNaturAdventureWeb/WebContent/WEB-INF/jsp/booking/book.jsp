<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Gestión de reservas">
<jsp:body>
	
		<h2>Nueva reserva</h2>
		<div class="book-left">
			<h3>Datos de la reserva</h3>
		    <form:form method="post" modelAttribute="booking" role="form" class="form form-horizontal" 
		    action="${pageContext.request.contextPath}/booking/confirmBooking/${activity.codActivity}.html">
			    <div class="form-group">
						<div class="col-xs-3">
		                	<form:label path="proposalPerformingDateString" class="control-label">Fecha propuesta: </form:label>
		                </div>
		                <div class="col-xs-4">
			                <div class="col-lg-8">
			                	<form:input path="proposalPerformingDateString" id="calendar-input" class="form-control" readonly="true"/>
			           		</div>
			           		<div class="col-lg-3">
		               			<span class="calendar-icon glyphicon glyphicon-calendar"></span>
		               		</div>
		           		</div>
		           		<div class="col-xs-4">
		                	<form:errors path="proposalPerformingDateString" cssClass="error" />
		           		</div>
		           		<div class="clear"></div>
		        </div>
		        <div class="form-group">
					<div class="col-xs-3">
	                	<form:label path="numPartakers" class="control-label">Núm. participantes: </form:label>
	                </div>
	                <div class="col-xs-4">
		                <div class="col-lg-8">
		                	<form:input path="numPartakers" class="form-control"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-4">
	                	<form:errors path="numPartakers" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	           	</div> 
	           	<div class="form-group">
					<div class="col-xs-3">
	                	<form:label path="startHour" class="control-label">Hora de comienzo: </form:label>
	                </div>
	                <div class="col-xs-4">
		                <div class="col-lg-8">
		                	<form:select path="startHour" class="form-control">
		                		<form:options items="${hours}" />
		                	</form:select>
		           		</div>
	           		</div>
	           		<div class="col-xs-4">
	                	<form:errors path="startHour" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	           	</div>
	           	<div class="form-group">
	            	<div class="col-xs-3"></div>
		            <div class="col-xs-8">	            
		            	<button type="submit" class="btn btn-custom btn-padding">Siguiente</button>
		            	<button type="reset" class="btn btn-custom btn-padding">Limpiar</button>
		            	<input type="button" class="btn btn-custom btn-padding" value="Cancelar" onclick="history.back(-1)"/>
		    		</div>
	    		</div>

		    </form:form>
	    </div>
	    
	    <div class="book-right">
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
	    
	    <script>
	    
		 // Min and max date
	        datepickr('.calendar-icon', {
	            // few days ago
	            minDate: new Date().getTime(),
	            altInput: document.getElementById('calendar-input'),
	            dateFormat: 'd/m/Y'
	        });

            // datepickr on an icon, using altInput to store the value
            // altInput must be a direct reference to an input element (for now)
            //datepickr('.calendar-icon', { altInput: document.getElementById('calendar-input') });
   
        </script>
	    
</jsp:body>
</t:paginabasica>
