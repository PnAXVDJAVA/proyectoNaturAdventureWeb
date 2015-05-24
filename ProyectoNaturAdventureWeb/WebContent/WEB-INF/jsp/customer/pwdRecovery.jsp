<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Gestión de clientes">
<jsp:body>
	
	<h2>Recuperación de contraseña</h2>
		<p>Para la recuperación de una contraseña olvidada necesitamos saber tu nombre de usuario
			 y el correo electrónico correspondiente con el que te registraste.</p>
		<p>Si los datos son correctos, se procederá a enviarte un correo electrónico con la nueva contraseña.</p>
	    <form:form method="post" modelAttribute="pwdRecoveryDetails" role="form" class="form form-horizontal">
	    	<div class="form-group">
					<div class="col-xs-2">
	                	<form:label path="username" class="control-label">Nombre de usuario: </form:label>
	                </div>
	                <div class="col-xs-4">
		                <div class="col-lg-8">
		                	<form:input path="username" class="form-control"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<form:errors path="username" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	           	</div>
	           	
	           	<div class="form-group">
					<div class="col-xs-2">
	                	<form:label path="email" class="control-label">Email: </form:label>
	                </div>
	                <div class="col-xs-4">
		                <div class="col-lg-10">
		                	<form:input path="email" class="form-control"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<form:errors path="email" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	           	</div>
	           	
	           	<div class="form-group">
	            	<div class="col-xs-2"></div>
		            <div class="col-xs-6">	            
		            	<button type="submit" class="btn btn-custom btn-padding">Solicitar nueva contraseña</button>
		            	<button type="reset" class="btn btn-custom btn-padding">Limpiar</button>
		            	<input type="button" class="btn btn-custom btn-padding" value="Cancelar" onclick="history.back(-1)"/>
		    		</div>
	    		</div>
	           	
	    </form:form>
	    
</jsp:body>
</t:paginabasica>
