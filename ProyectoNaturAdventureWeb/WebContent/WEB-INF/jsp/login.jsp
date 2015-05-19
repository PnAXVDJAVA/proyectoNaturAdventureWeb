<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Login">
<jsp:body>
<h2>Acceso</h2>
		    <form:form method="post" modelAttribute="user"
		        action="${pageContext.request.contextPath}/login.html" role="form" class="form form-horizontal">
		        
		        <div class="form-group">
			        <div class="col-md-5">
				        <form:label path="username">Nombre de usuario:</form:label>
				    </div>
				    <div class="clear"></div>
		        	<div class="col-md-3">	
			            	<form:input path="username" class="form-control"
			            			placeHolder="Introduce tu nombre de usuario" />	
			       	</div>
			       	<div class="col-md-5">
		           			<form:errors path="username" cssClass="error" />
		           	</div>
	           	</div>
		        <div class="clear"></div>
		        <div class="form-group">
		        	<div class="col-md-5">
		           		<form:label path="password" for="password">Contraseña:</form:label>
		            </div>
		            <div class="clear"></div>
		            <div class="col-md-3">
		            	<form:password path="password" class="form-control" id="password"
		            				placeHolder="Introduce tu contraseña"/>
		            </div>
		            <div class="col-md-5">
		            	<form:errors path="password" cssClass="error" /> 
		            </div>
		        </div>	
		        <div class="clear"></div>
		        <div class="col-md-offset-1">
		        	<button type="submit" class="btn btn-custom">Login</button>
		        </div>   
		    </form:form>
</jsp:body>
</t:paginabasica>