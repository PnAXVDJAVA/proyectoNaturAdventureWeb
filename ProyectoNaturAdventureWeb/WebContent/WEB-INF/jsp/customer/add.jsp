<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Gestión de clientes">
<jsp:body>
	
	<h2>Registro de usuario</h2>
	    <form:form method="post" modelAttribute="customerUser" role="form" class="form form-horizontal" 
	    	onsubmit="return checkPasswords();">
	    	<div class="form-group">
					<div class="col-xs-2">
	                	<form:label path="customer.nif" class="control-label">NIF: </form:label>
	                </div>
	                <div class="col-xs-4">
		                <div class="col-lg-8">
		                	<form:input path="customer.nif" class="form-control"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<form:errors path="customer.nif" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	           	</div>
				<div class="form-group">
					<div class="col-xs-2">
	                	<form:label path="customer.name" class="control-label">Nombre: </form:label>
	                </div>
	                <div class="col-xs-4">
		                <div class="col-lg-8">
		                	<form:input path="customer.name" class="form-control"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<form:errors path="customer.name" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	           	</div>
				<div class="form-group">
					<div class="col-xs-2">
	                	<form:label path="customer.firstSurname" class="control-label">Primer apellido: </form:label>
	                </div>
	                <div class="col-xs-4">
		                <div class="col-lg-8">
		                	<form:input path="customer.firstSurname" class="form-control"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<form:errors path="customer.firstSurname" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	           	</div>
	           	<div class="form-group">
					<div class="col-xs-2">
	                	<form:label path="customer.secondSurname" class="control-label">Segundo apellido: </form:label>
	                </div>
	                <div class="col-xs-4">
		                <div class="col-lg-8">
		                	<form:input path="customer.secondSurname" class="form-control"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<form:errors path="customer.secondSurname" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	           	</div>
	           	<div class="form-group">
					<div class="col-xs-2">
	                	<form:label path="customer.telephone" class="control-label">Teléfono: </form:label>
	                </div>
	                <div class="col-xs-4">
		                <div class="col-lg-10">
		                	<form:input path="customer.telephone" class="form-control"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-4">
	                	<form:errors path="customer.telephone" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	           	</div>
	           	<div class="form-group">
					<div class="col-xs-2">
	                	<form:label path="customer.email" class="control-label">E-mail: </form:label>
	                </div>
	                <div class="col-xs-4">
		                <div class="col-lg-11">
		                	<form:input path="customer.email" class="form-control"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<form:errors path="customer.email" cssClass="error" />
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
		            	<button type="submit" class="btn btn-custom btn-padding">Registrar</button>
		            	<button type="reset" class="btn btn-custom btn-padding">Limpiar</button>
		            	<input type="button" class="btn btn-custom btn-padding" value="Cancelar" onclick="history.back(-1)"/>
		    		</div>
	    		</div>
	    </form:form>
	    
</jsp:body>
</t:paginabasica>
