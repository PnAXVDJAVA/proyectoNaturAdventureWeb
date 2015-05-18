<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Gestión de monitores">
<jsp:body>
	
	<h2>Nuevo monitor</h2>
	    <form:form method="post" modelAttribute="instructorUser" role="form" class="form form-horizontal"
	     onsubmit="return checkPasswords();">
	           	<div class="form-group">
					<div class="col-xs-2">
	                	<form:label path="instructor.nif" class="control-label">NIF: </form:label>
	                </div>
	                <div class="col-xs-4">
		                <div class="col-lg-8">
		                	<form:input path="instructor.nif" class="form-control"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<form:errors path="instructor.nif" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	           	</div>
	           	<div class="form-group">
					<div class="col-xs-2">
	                	<form:label path="instructor.name" class="control-label">Nombre: </form:label>
	                </div>
	                <div class="col-xs-4">
		                <div class="col-lg-8">
		                	<form:input path="instructor.name" class="form-control"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<form:errors path="instructor.name" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	           	</div>
	           	<div class="form-group">
					<div class="col-xs-2">
	                	<form:label path="instructor.firstSurname" class="control-label">Primer apellido: </form:label>
	                </div>
	                <div class="col-xs-4">
		                <div class="col-lg-8">
		                	<form:input path="instructor.firstSurname" class="form-control"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<form:errors path="instructor.firstSurname" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	           	</div>
	           	<div class="form-group">
					<div class="col-xs-2">
	                	<form:label path="instructor.secondSurname" class="control-label">Segundo apellido: </form:label>
	                </div>
	                <div class="col-xs-4">
		                <div class="col-lg-8">
		                	<form:input path="instructor.secondSurname" class="form-control"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<form:errors path="instructor.secondSurname" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	           	</div>
	            <div class="form-group">
					<div class="col-xs-2">
	                	<form:label path="instructor.address" class="control-label">Dirección: </form:label>
	                </div>
	                <div class="col-xs-4">
		                <div class="col-lg-10">
		                	<form:input path="instructor.address" class="form-control"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<form:errors path="instructor.address" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	           	</div>
	            <div class="form-group">
					<div class="col-xs-2">
	                	<form:label path="instructor.telephone" class="control-label">Teléfono: </form:label>
	                </div>
	                <div class="col-xs-4">
		                <div class="col-lg-8">
		                	<form:input path="instructor.telephone" class="form-control"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<form:errors path="instructor.telephone" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	           	</div>
	           	<div class="form-group">
					<div class="col-xs-2">
	                	<label class="control-label">Fecha de cumpleaños: </label>
	                </div>
	                <div class="col-xs-4">
		                <div class="col-lg-3">
		                	<form:input path="instructor.dayOfBirth" class="form-control" placeHolder="Día"/>
		           		</div>
		           		<div class="col-lg-3">
		                	<form:input path="instructor.monthOfBirth" class="form-control" placeHolder="Mes"/>
		           		</div>
		           		<div class="col-lg-4">
		                	<form:input path="instructor.yearOfBirth" class="form-control" placeHolder="Año"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<form:errors path="instructor.dayOfBirth" cssClass="error" />
	             	    <form:errors path="instructor.monthOfBirth" cssClass="error" />	                	
	                	<form:errors path="instructor.yearOfBirth" cssClass="error" />	                		             
	           		</div>
	           		<div class="clear"></div>
	           	</div>
	           	<div class="form-group">
					<div class="col-xs-2">
	                	<form:label path="instructor.email" class="control-label">E-mail: </form:label>
	                </div>
	                <div class="col-xs-4">
		                <div class="col-lg-10">
		                	<form:input path="instructor.email" class="form-control"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<form:errors path="instructor.email" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	           	</div>
	           	<div class="form-group">
					<div class="col-xs-2">
	                	<form:label path="instructor.bankAccount" class="control-label">Cuenta bancaria: </form:label>
	                </div>
	                <div class="col-xs-4">
		                <div class="col-lg-10">
		                	<form:input path="instructor.bankAccount" class="form-control"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<form:errors path="instructor.bankAccount" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	           	</div>
	           	<div class="form-group">
					<div class="col-xs-2">
	                	<form:label path="userDetails.username" class="control-label">Nombre de usuario: </form:label>
	                </div>
	                <div class="col-xs-4">
		                <div class="col-lg-8">
		                	<form:input path="userDetails.username" class="form-control"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<form:errors path="userDetails.username" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	           	</div>
	           	<div class="form-group">
					<div class="col-xs-2">
	                	<form:label path="userDetails.password" class="control-label">Contraseña: </form:label>
	                </div>
	                <div class="col-xs-4">
		                <div class="col-lg-8">
		                	<form:input type="password" path="userDetails.password" class="form-control" id="password"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<form:errors path="userDetails.password" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	           	</div>
	           	<div class="form-group">
					<div class="col-xs-2">
	                	<label class="control-label">Repite la contraseña: </label>
	                </div>
	                <div class="col-xs-4">
		                <div class="col-lg-8">
		                	<input type="password" class="form-control" id="passwordRepeat"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<div id="checkPasswords" class="hidden"></div>
	           		</div>
	           		<div class="clear"></div>
	           	</div>
	            <div class="form-group">
	            	<div class="col-xs-2"></div>
		            <div class="col-xs-4">	            
		            	<button type="submit" class="btn btn-default btn-padding">Añadir monitor</button>
		            	<button type="reset" class="btn btn-default btn-padding">Limpiar</button>
		            	<input type="button" class="btn btn-default btn-padding" value="Cancelar" onclick="history.back(-1)"/>
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
