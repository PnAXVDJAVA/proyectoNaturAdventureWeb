<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Login">
<jsp:body>
<h2>Acceso</h2>
    <form:form method="post" modelAttribute="user"
        action="${pageContext.request.contextPath}/login.html" role="form" class="form form-horizontal">
        <div class="form-group">
        	<div class="col-md-3">
	            <form:label path="username" for="username">Nombre de usuario:</form:label>
	            <form:input path="username" class="form-control" id="username"
	            			placeHolder="Usuario" />
	           	<form:errors path="username" cssClass="error" />
           	</div>
        </div>
        <div class="form-group">
        	<div class="col-md-3">
	            <form:label path="password" for="password">Contraseña:</form:label>
	            <form:password path="password" class="form-control" id="password"
	            				placeHolder="Contraseña"/>
	            <form:errors path="password" cssClass="error" /> 
	        </div>
        </div>   
        <button type="submit" class="btn btn-default">Login</button>
    </form:form>
</jsp:body>
</t:paginabasica>