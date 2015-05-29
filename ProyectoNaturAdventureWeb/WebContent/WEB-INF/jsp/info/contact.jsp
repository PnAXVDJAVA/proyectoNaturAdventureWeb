<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Contacto | NaturAdventure">
<jsp:body>

	<h2>Contacto</h2>
	<p>Puedes contactar con nosotros a través de los siguientes métodos:</p>
	<br>
	<p><span class="negrita">Teléfono</span></p>
	<p>&nbsp;&nbsp;&nbsp;&nbsp;+34 964123456. Horarios: Lunes a viernes, de 9:00 a 20:00 h.</p>
	<br>
	<p><span class="negrita">E-mail</span></p>
	<p>&nbsp;&nbsp;&nbsp;&nbsp;naturadventure.xvd@gmail.com</p>
	<br>
	<p><span class="negrita">Dirección</span></p>
	<p>&nbsp;&nbsp;&nbsp;&nbsp;Escuela Superior de Tecnología y Ciencias Experimentales (ESTCE), Universitat Jaume I (UJI)</p>
	<p>&nbsp;&nbsp;&nbsp;&nbsp;Av. Vicent Sos Baynat, s/n, 12071, Castellón de la Plana, España</p>
	<br>
	<p>O, si lo prefieres, puedes contactar con nosotros a través de <a href="${pageContext.request.contextPath}/suggestion/add.html">
	 este formulario</a> para cualquier duda o sugerencia.</p>

</jsp:body>
</t:paginabasica>