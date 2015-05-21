<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Gestión de reservas">
<jsp:body>
	
	<h2>Nueva reserva</h2>
		<div class="left">
			<h3>Datos de la reserva</h3>
		    <form:form method="post" modelAttribute="booking" role="form" class="form form-horizontal">
		        <div class="form-group">
		                <form:label path="proposalPerformingDateString" class="control-label col-sm-2">Fecha propuesta: </form:label>
		                <div class="col-xs-6">
			                <form:input path="proposalPerformingDateString" id="calendar-input" class="form-control" readonly="true"/>
			               
		               	</div>
		               	<div class="col-xs-3">
		               		 <span class="calendar-icon glyphicon glyphicon-calendar"></span>
		               	</div>
		               	<form:errors path="proposalPerformingDateString" cssClass="error" />
		       	</div>           
				<div class="form-group">
		                <form:label path="numPartakers" class="control-label col-sm-2">Núm. participantes: </form:label>
		                <div class="col-xs-6">
		                	<form:input path="numPartakers" class="form-control" />
		                </div>
		        </div>	          
		        <div class="form-group">
		                <form:label path="startHour" class="control-label col-sm-2">Hora de comienzo:</form:label>
		                <div class="col-xs-6">
		                	<form:select path="startHour" class="form-control">
		                		<form:option value="NONE" label="Elige"/>
		                		<form:options items="${hours}" />
		                	</form:select>
		                </div>
		        </div>
				<div class="form-group">
		                <div class="col-sm-offset-2 col-sm-10">	            
			            	<button type="submit" class="btn btn-default">Realizar reserva</button>
			            	<button type="reset" class="btn btn-default">Limpiar</button>
			            	<input type="button" class="btn btn-default" value="Cancelar" onclick="history.back(-1)"/>
			    		</div>
				</div>
		    </form:form>
	    </div>
	    
	    <div class="right">
	    	<h3>Datos de la actividad</h3>
	    	<p><span class="negrita">Nombre:</span> ${activity.name}</p>
	    	<p><span class="negrita">Descripción:</span> ${activity.description}</p>
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
