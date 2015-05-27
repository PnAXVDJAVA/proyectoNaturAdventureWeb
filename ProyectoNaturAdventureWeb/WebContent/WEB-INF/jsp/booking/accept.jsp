<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Añadir monitor especializado">
<jsp:body>

	<h2>Asignar monitor a reserva</h2>
	
		<div class="left">
			<p>Lista de <span class="negrita">monitores asignados</span> a la reserva con código <span class="negrita">${codBooking}</span>:</p>
			
			<div class="table-responsive adapter">
					<table id="asignados" class="table table-striped">
						<tr>
							<th>NIF</th>
							<th>Nombre</th>
						</tr>
						<c:forEach items="${assignedInstructors}" var="assignedInstructor">
							<tr>
								<td>${assignedInstructor.nif}</td>
								<td>${assignedInstructor.name}</td>
								<td><a href="${pageContext.request.contextPath}/booking/removeInstructor.html?nif=${assignedInstructor.nif}&codBooking=${codBooking}"><span class="glyphicon glyphicon-remove"></span>  Borrar monitor</a>
							</tr>
						</c:forEach>
					</table>
			</div>
		</div>
	
		<div class="right">
				
			<p>Lista de <span class="negrita">monitores disponibles</span>: </p>
			<div class="table-responsive adapter">
				<table class="table table-striped">
				
					<tr>
						<th>NIF</th>
						<th>Nombre</th>
					</tr>
					
					<c:forEach items="${availableInstructors}" var="availableInstructor">
						<tr>
							<td>${availableInstructor.nif}</td>
							<td>${availableInstructor.name}</td>
							<td><a href="${pageContext.request.contextPath}/booking/assignInstructor.html?nif=${availableInstructor.nif}&codBooking=${codBooking}"><span class="glyphicon glyphicon-plus"></span>  Añadir monitor</a></td>
						</tr>
					</c:forEach>
			
				</table>
			</div>
		</div>
		
		<div class="clear"></div>
		
       <div class="form-group">
       		<div class="col-xs-2">
       			<div class="col-xs-8">
					<button onClick="checkAssignedInstructors( ${codBooking} )" class="btn btn-custom">Aceptar reserva</button>
             	</div>
             </div>
             <div class="col-xs-4">
             		<div class="hidden" id="error">Es necesario asignar algún monitor a la reserva</div>
             </div>
        	<div class="clear"></div>
       </div> 
	
	<div class="clear">
	</div>
	
	<script type="text/javascript">
		function checkAssignedInstructors( codBooking ) {
			var n = document.getElementById( "asignados" ).rows.length;
			if( n <= 1 ) {
				document.getElementById( "error" ).className = "error";
			}
			else {
				document.location.href = '${pageContext.request.contextPath}/booking/confirmBooking/' + codBooking + '.html';
			}
		}
	</script>
	

</jsp:body>
</t:paginabasica>