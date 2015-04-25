<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib  uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>

<html>

	<head>
		<meta charset="UTF-8" />
		<!-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/natacio.css">  -->
		<title>Crear nuevo monitor</title>
	</head>
	
	<body>
	
	<h2>Nuevo monitor</h2>
	    <form:form method="post" action="">
	        <table>
	        	 <tr>
	                <td><label>NIF: </label></td>
	                <td><spring:bind path="instructor.nif"><input type="text" name="${status.expression}" value="${status.value}"/></spring:bind></td>
	            </tr>
	            <tr>
	                <tr>
	                <td><label>Nombre: </label></td>
	                <td><spring:bind path="instructor.name"><input type="text" name="${status.expression}" value="${status.value}"/></spring:bind></td>
	            </tr>
	           <tr>
	                <tr>
	                <td><label>Primer apellido: </label></td>
	                <td><spring:bind path="instructor.firstSurname"><input type="text" name="${status.expression}" value="${status.value}"/></spring:bind></td>
	            </tr>
	             <tr>
	                <tr>
	                <td><label>Segundo apellido: </label></td>
	                <td><spring:bind path="instructor.secondSurname"><input type="text" name="${status.expression}" value="${status.value}"/></spring:bind></td>
	            </tr>
	            <tr>
	                <tr>
	                <td><label>Dirección: </label></td>
	                <td><spring:bind path="instructor.address"><input type="text" name="${status.expression}" value="${status.value}"/></spring:bind></td>
	            </tr>
	            <tr>
	                <tr>
	                <td><label>Teléfono: </label></td>
	                <td><spring:bind path="instructor.telephone"><input type="text" name="${status.expression}" value="${status.value}"/></spring:bind></td>
	            </tr>
	            <tr>
	                <tr>
	                <td><label>Fecha de cumpleaños: (DD/MM/AAAA)</label></td>
	                <td><spring:bind path="instructor.dateOfBirthString"><input type="text" name="${status.expression}" value="${status.value}"/></spring:bind></td>
	            </tr>
	            <tr>
	                <tr>
	                <td><label>E-mail: </label></td>
	                <td><spring:bind path="instructor.email"><input type="text" name="${status.expression}" value="${status.value}"/></spring:bind></td>
	            </tr>
	            <tr>
	                <tr>
	                <td><label>Cuenta bancaria: </label></td>
	                <td><spring:bind path="instructor.bankAccount"><input type="text" name="${status.expression}" value="${status.value}"/></spring:bind></td>
	            </tr>
	             <tr>
	                <tr>
	                <td><label>Username: </label></td>
	                <td><spring:bind path="instructor.userID user.username"><input type="text" name="${status.expression}" value="${status.value}"/></spring:bind></td>
	            </tr>
	            <tr>
	                <tr>
	                <td><label>Contraseña: </label></td>
	                <td><spring:bind path="user.password"><input type="password" name="${status.expression}" value="${status.value}"/></spring:bind></td>
	            </tr>	            <tr>
	                <td colspan="2"><input type="submit" value="Añadir monitor" />
	                </td>
	            </tr>
	        </table>
	    </form:form>
	    
	</body>
	
</html>
