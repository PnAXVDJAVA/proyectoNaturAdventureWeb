<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Información">
<jsp:body>

	<h2>Sugerencias, quejas, dudas</h2>
	<br>
           		
	<form:form method="post" modelAttribute="suggestion" role="form" class="form form-horizontal"
   		accept-charset="utf-8">
   			
   			
    	<div class="col-xs-2">
			<form:label path="name">Nombre</form:label>
		</div>
		<div class="clear"></div>
		<div class="col-xs-3">
			<form:input path="name" class="form-control" />
		</div>
		<div class="col-xs-2"><form:errors path="name" cssClass="error" /></div>
		<div class="clear"></div>
		
		<br>
		
		<div class="col-xs-2">
			<form:label path="email">E-mail</form:label>
		</div>
		<div class="clear"></div>
		<div class="col-xs-3">
			<form:input path="email" class="form-control" />
		</div>
		<div class="col-xs-3"><form:errors path="email" cssClass="error" /></div>
		<div class="clear"></div>
		
		<br>
		
		<div class="col-xs-2">
			<form:label path="messageType">Tipo</form:label>
		</div>
		<div class="clear"></div>
		<div class="col-xs-3">
			<form:select path="messageType" class="form-control">
              		<form:options items="${messageTypes}" />
            </form:select>	
		</div>
		<div class="col-xs-3"><form:errors path="messageType" cssClass="error" /></div>
		<div class="clear"></div>
		
		<br>
		
		<div class="col-xs-2">
	        <form:label path="message">Mensaje</form:label>
        </div>
        <div class="clear"></div>
        <div class="col-xs-8">
        	<form:textarea path="message" class="form-control" rows="5"></form:textarea>     
   		</div>
   		<div class="col-xs-2"><form:errors path="message" cssClass="error" /></div>
   		<div class="clear"></div>
   		
		<div class="col-xs-6">
           	<button type="submit" class="btn btn-custom btn-padding-no-left">Enviar</button>
          	<button type="reset" class="btn btn-custom btn-padding">Restaurar valores</button>
          	<input type="button" class="btn btn-custom btn-padding" value="Cancelar" onclick="history.back(-1)"/>	                
		</div>
   
	</form:form>
        		
			

</jsp:body>
</t:paginabasica>