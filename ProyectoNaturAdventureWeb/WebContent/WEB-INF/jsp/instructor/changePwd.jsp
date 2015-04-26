<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Gestión de monitores">
<jsp:body>
	<body>
	
	<h2>Cambiar contraseña</h2>
	    <form:form method="post" modelAttribute="user" role="form" class="form-horizontal">
	        <div class="form-group">
					<form:label path="username" readonly="true" class="col-sm-2 control-label" for="username">Username: </form:label>
					<div class="col-sm-10">
	                	<form:input path="username" readonly="true" class="form-control" id="username"/>
	                </div>
	                <form:errors path="username" cssClass="error" />
	        </div>
			
			<div class="form-group">
	                <form:label path="password" class="col-sm-2 control-label" for="password">Nueva contraseña: </form:label>
	                <div class="col-sm-10">
	                	<form:input type="password" path="password" class="form-control" id="password"/>
	                	<form:errors path="password" cssClass="error" />
	                </div>
	        </div>

	        <button type="submit" class="btn btn-default">Confirmar contraseña</button>
	       	<input type="button" class="btn btn-default" value="Cancelar" onclick="history.back(-1)"/>
	    </form:form>
	    
</jsp:body>
</t:paginabasica>
