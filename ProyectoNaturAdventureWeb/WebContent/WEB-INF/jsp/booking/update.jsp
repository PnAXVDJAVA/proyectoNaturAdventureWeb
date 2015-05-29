<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Editar reserva | NaturAdventure">
<jsp:body>
	
	<h2>Editar reserva</h2>
	
	<form:form method="post" modelAttribute="booking" role="form" class="form form-horizontal">
	
	<div class="book-left">
	    
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
	           	
	           	<form:input path="codActivity" type="hidden" id="codActivity"/>        
	    	
	        <div class="form-group">
	            	<div class="col-xs-3"></div>
		            <div class="col-xs-8">	            
	                	<button class="btn btn-custom btn-padding" type="submit">Confirmar cambios</button>
		            	<input type="button" class="btn btn-custom btn-padding" value="Cancelar" onclick="history.back(-1)"/>
		    		</div>
	    		</div>
	   	</div>
	   	
	   	<div class="book-right">
	   		
	   		<div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Actividad asignada:</span></div>
		    	<div class="col-sm-5" id="activityName">${activityName}</div>
		    	<div class="clear"></div>
		    </div>
	   	
	   		<hr class="myHr">
	   		
	   		<p>Lista de actividades disponibles: </p>
	   		
	   		<div class="table-responsive">
		   		<table class="table table-striped">
					<tr>
						<th>Nombre</th>
					</tr>
					<c:forEach items="${activityList}" var="activity">
						<tr>
							<td>${activity.name}</td>
							<td><a href="${pageContext.request.contextPath}/activity/activityDetails/${activity.codActivity}.html"><span class="glyphicon glyphicon-info-sign"></span>  Detalles actividad</a></td>
							<td><a id="${activity.codActivity}" onClick="elegirActividad( ${activity.codActivity}, '${activity.name}' )" class="mouse"><span class="glyphicon glyphicon-ok"></span>  Elegir actividad</a>
						</tr>
					</c:forEach>
				</table>
			</div>
	   	
	   	</div>
	   	
	   	 </form:form>
	   	 
	   	 <script type="text/javascript">
	   	 	
			window.onload = hideActivityLink();
	   	 	
			function hideActivityLink() {
				var actualCodActivity = document.getElementById( "codActivity" ).value;
	   	 		document.getElementById( actualCodActivity ).className = "hidden";
			}
			
	   	 	function elegirActividad( codActivity, activityName ) {
	   	 		var actualCodActivity = document.getElementById( "codActivity" ).value;
	   	 		document.getElementById( actualCodActivity ).className = "visible mouse";
	   	 		document.getElementById( codActivity ).className = "hidden";
	   	 		document.getElementById( "codActivity" ).value = codActivity;
	   	 		document.getElementById( "activityName" ).innerHTML = activityName;
	   	 	}
	   	 	
	   	 </script>
	    
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
