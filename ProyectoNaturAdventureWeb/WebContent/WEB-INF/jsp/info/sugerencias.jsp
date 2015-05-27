<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Información">
<jsp:body>

	<h2>Sugerencias, quejas, dudas</h2>
	
				<div class="col-xs-1">
					<label>Nombre</label>
				</div>
				<div class="clear"></div>
				<div class="col-xs-3">
					<input type="text" class="form-control">
				</div>
				<div class="clear"></div>
				
				<div class="col-xs-1">
					<label>E-mail</label>
				</div>
				<div class="clear"></div>
				<div class="col-xs-3">
					<input type="text" class="form-control">
				</div>
				<div class="clear"></div>
				
				<div class="col-xs-1">
					<label>Tipo</label>
				</div>
				<div class="clear"></div>
				<div class="col-xs-3">
					<input type="text" class="form-control">
				</div>
				<div class="clear"></div>
				
				<div class="col-xs-1">
                	<label>Mensaje</label>
                </div>
                <div class="clear"></div>
                <div class="col-xs-8">
	                <textarea class="form-control" rows="5"></textarea>
           		</div>
           		
			

</jsp:body>
</t:paginabasica>