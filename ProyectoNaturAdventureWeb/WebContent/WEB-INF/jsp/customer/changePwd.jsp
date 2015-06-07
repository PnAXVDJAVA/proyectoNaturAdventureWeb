<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Cambiar contraseña | NaturAdventure">
<jsp:body>
	<body>
	
	<h2>Cambiar contraseña</h2>
	    <form:form method="post" modelAttribute="user" role="form" class="form form-horizontal" 
	    	onsubmit="return checkPasswords();">
	    	<div class="form-group">
					<div class="col-xs-2">
	                	<form:label path="username" class="control-label">Nombre de usuario: </form:label>
	                </div>
	                <div class="col-xs-4">
		                <div class="col-lg-10">
		                	<form:input path="username" class="form-control" readonly="true"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<form:errors path="username" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	         </div>
			<div class="form-group">
					<div class="col-xs-2">
	                	<form:label path="password" class="control-label">Nueva contraseña: </form:label>
	                </div>
	                <div class="col-xs-4">
		                <div class="col-lg-10">
		                	<form:input type="password" path="password" class="form-control"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<form:errors path="password" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	         </div>
	         <div class="form-group">
					<div class="col-xs-2">
	                	<label class="control-label">Repite la contraseña: </label>
	                </div>
	                <div class="col-xs-4">
		                <div class="col-lg-10">
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
				<div class="col-xs-5">
	        		<button type="submit" class="btn btn-custom btn-padding">Confirmar contraseña</button>
	       		    <button type="reset" class="btn btn-custom btn-padding">Restaurar valores</button>
	       			<input type="button" class="btn btn-custom btn-padding" value="Cancelar" onclick="history.back(-1)"/>
	       		</div>
	       	</div>
	    </form:form>
	    
</jsp:body>
</t:paginabasica>
