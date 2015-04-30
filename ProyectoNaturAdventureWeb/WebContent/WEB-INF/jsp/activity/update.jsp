<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>

<html>

	<head>
		<meta charset="UTF-8" />
		<!-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/natacio.css"> -->
		<title>Editar actividad</title>
	</head>
	
	<body>
	
	<h2>Editar actividad</h2>
	    <form:form method="post" modelAttribute="activity">
	        <table>
	            <tr>
	                <td><form:label path="name">Nombre: </form:label></td>
	                <td><form:input path="name" /></td>
	                <td><form:errors path="name" cssClass="error" /></td>
	            </tr>
	            <tr>
	                <td><form:label path="description">Descripción: </form:label></td>
	                <td><form:input path="description" /></td>
	            </tr>
	            <tr>
	                <td><form:label path="pricePerPerson">Precio por persona: </form:label></td>
	                <td><form:input path="pricePerPerson" /></td>
	            </tr>
	            <tr>
	                <td><form:label path="duration">Duración: </form:label></td>
	                <td><form:input path="duration" /></td>
	                <td><form:errors path="duration" cssClass="error" /></td>
	            </tr>
	            <tr>
	                <td><form:label path="minPartakers">Mín. núm. participantes: </form:label></td>
	                <td><form:input path="minPartakers" /></td>
	                <td><form:errors path="minPartakers" cssClass="error" /></td>
	            </tr>
	            <tr>
	                <td><form:label path="maxPartakers">Máx. núm. participantes: </form:label></td>
	                <td><form:input path="maxPartakers" /></td>
	                <td><form:errors path="maxPartakers" cssClass="error" /></td>
	            </tr>
	            <tr>
	                <td><form:label path="level">Nivel: </form:label></td>
	                <td>
	                	<form:select path="level">
	                		<form:option value="NONE" label="Elige"/>
	                		<form:options items="${levels}" />
	                	</form:select>
	                	
	                </td>
	                <td><form:errors path="level" cssClass="error" /></td>
	            </tr>
	            <tr>
	                <td colspan="2"><input type="submit" value="Confirmar cambios" />
	                </td>
	            </tr>
	        </table>
	    </form:form>
	    
	</body>
	
</html>
