<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Gestión de monitores">
<jsp:body>
	
	<h2>Nuevo monitor</h2>
	    <form:form method="post" modelAttribute="instructorUser" role="form" class="form form-horizontal">
	        	<div class="form-group">
			         	<form:label path="instructor.nif" class="control-label col-sm-2" for="nif" >NIF: </form:label>
			         	<div class="col-xs-3">
			            	<form:input path="instructor.nif" class="form-control" id="nif" />
			            </div>
			            <form:errors path="instructor.nif" cssClass="error" />
			    </div>
	            <div class="form-group">
	                	<form:label path="instructor.name" class="control-label col-sm-2">Nombre: </form:label>
	                	<div class="col-xs-3">
	                		<form:input path="instructor.name" class="form-control"/>
	                	</div>
	                	<form:errors path="instructor.name" cssClass="error" />
	            </div>
	            <div class="form-group">
	                	<form:label path="instructor.firstSurname" class="control-label col-sm-2">Primer apellido: </form:label>
	                	<div class="col-xs-3">
	                		<form:input path="instructor.firstSurname" class="form-control" />
	            		</div>
	            </div>
	             <div class="form-group">
	                	<form:label path="instructor.secondSurname" class="control-label col-sm-2">Segundo apellido: </form:label>
	                	<div class="col-xs-3">
	                		<form:input path="instructor.secondSurname" class="form-control"/>
	            		</div>
	            </div>
	            <div class="form-group">
	                	<form:label path="instructor.address" class="control-label col-sm-2">Dirección: </form:label>
	                	<div class="col-xs-3">
	                		<form:input path="instructor.address" class="form-control"/>
	            		</div>
	            </div>
	            <div class="form-group">
	                	<form:label path="instructor.telephone" class="control-label col-sm-2">Teléfono: </form:label>
	                	<div class="col-xs-3">
	                		<form:input path="instructor.telephone" class="form-control"/>
	                	</div>
	                	<form:errors path="instructor.telephone" cssClass="error" />
	            </div>
	            <div class="form-group">
	            	<form:label path="instructor.dayOfBirth"  class="control-label col-sm-2">Dia de cumpleaños: </form:label>
	            	<div class="col-xs-2">
           				<form:input path="instructor.dayOfBirth" id="calendar-input" class="form-control"/>
	                </div>
	                <form:label path="instructor.monthOfBirth"  class="control-label col-sm-2">Mes: </form:label>
	                <div class="col-xs-2">
	                	<form:input path="instructor.monthOfBirth" id="calendar-input" class="form-control"/>
	                </div>
	                <form:label path="instructor.yearOfBirth"  class="control-label col-sm-2">Año: </form:label>
	                <div class="col-xs-2">
	                	<form:input path="instructor.yearOfBirth" id="calendar-input" class="form-control"/>
	                </div>		           
	            </div>
	            
	            <div class="form-group">	     
	                	<form:label path="instructor.email" class="control-label col-sm-2">E-mail: </form:label>
	                	<div class="col-xs-3">
	                		<form:input path="instructor.email" class="form-control"/>
	                	</div>
	                	<form:errors path="instructor.email" cssClass="error" />	            	
	            </div>
	            <div class="form-group">	       
	                	<form:label path="instructor.bankAccount" class="control-label col-sm-2">Cuenta bancaria: </form:label>
	                	<div class="col-xs-3">
	                		<form:input path="instructor.bankAccount" class="form-control"/>
	                	</div>
	                	<form:errors path="instructor.bankAccount" cssClass="error" />	            	
	             </div>
	             <div class="form-group">	       
	                	<form:label path="userDetails.username" class="control-label col-sm-2">Username: </form:label>
	                	<div class="col-xs-3">
	                		<form:input path="userDetails.username" class="form-control"/>
	                	</div>
	                	<form:errors path="userDetails.username" cssClass="error" />	            	
	            </div>
	            <div class="form-group">	      
	                	<form:label path="userDetails.password" class="control-label col-sm-2">Contraseña: </form:label>
	                	<div class="col-xs-3">	
	                		<form:input type="password" path="userDetails.password" class="form-control"/>
	                	</div>
	                	<form:errors path="userDetails.password" cssClass="error" />	            	
	            </div>
	            <div class="form-group">
		            <div class="col-sm-offset-2 col-sm-10">	            
		            	<button type="submit" class="btn btn-default">Añadir monitor</button>
		            	<button type="reset" class="btn btn-default">Limpiar</button>
		            	<input type="button" class="btn btn-default" value="Cancelar" onclick="history.back(-1)"/>
		    		</div>
	    		</div>
	    </form:form>
	    
	    <script>

            // datepickr on an icon, using altInput to store the value
            // altInput must be a direct reference to an input element (for now)
            datepickr('.calendar-icon', { altInput: document.getElementById('calendar-input') });
   
        </script>
	    
</jsp:body>
</t:paginabasica>
