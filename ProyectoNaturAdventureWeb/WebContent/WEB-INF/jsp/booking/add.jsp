<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>

<html>

	<head>
		<meta charset="UTF-8" />
		<!-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/natacio.css">  -->
		<title>Crear nueva reserva</title>
	</head>
	
	<body>
	
	<h2>Nueva reserva</h2>
	    <form:form method="post" modelAttribute="booking">
	        <table>
	        	 <tr>
	                <td><form:label path="codBooking">Código reserva: </form:label></td>
	                <td><form:input path="codBooking" /></td>
	                <td><form:errors path="codBooking" cssClass="error" /></td>
	            </tr>
	            <tr>
	                <td><form:label path="proposalPerformingDateString">Fecha propuesta: </form:label></td>
	                <td><form:input path="proposalPerformingDateString" /></td>
	                <td><form:errors path="proposalPerformingDateString" cssClass="error" /></td>
	            </tr>
	            <tr>
	                <td><form:label path="numPartakers">Núm. participantes: </form:label></td>
	                <td><form:input path="numPartakers" /></td>
	            </tr>
	            <tr>
	                <td><form:label path="bookingDateString">Fecha de reserva: </form:label></td>
	                <td><form:input path="bookingDateString" /></td>
	            </tr>
	            <tr>
	                <td><form:label path="customerNif">NIF del cliente: </form:label></td>
	                <td><form:input path="customerNif" /></td>
	                <td><form:errors path="customerNif" cssClass="error" /></td>
	            </tr>
	            <tr>
	                <td><form:label path="codActivity">Código de la actividad: </form:label></td>
	                <td><form:input path="codActivity" /></td>
	                <td><form:errors path="codActivity" cssClass="error" /></td>
	            </tr>
	            <tr>
	                <td><form:label path="startHour">Hora de comienzo:</form:label></td>
	                <td>
	                	<form:select path="startHour">
	                		<form:option value="NONE" label="Elige"/>
	                		<form:options items="${hours}" />
	                	</form:select>
	                </td>
	            </tr>
	            <tr>
	                <td><form:label path="status">Estado: </form:label></td>
	                <td>
	                	<form:select path="status">
	                		<form:option value="NONE" label="Elige"/>
	                		<form:options items="${statusS}" />
	                	</form:select>
	                	
	                </td>
	            </tr>
	            <tr>
	                <td colspan="2"><input type="submit" value="Añadir reserva" />
	                </td>
	            </tr>
	        </table>
	    </form:form>
	    
	</body>
	
</html>
