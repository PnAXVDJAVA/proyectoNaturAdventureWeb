<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Ayuda | NaturAdventure">
<jsp:body>

	<h2>Sección de ayuda</h2>
	
	<div class="info-help-general">
		<div class="info-help-navigator">
			<nav>
				<ul class="nav nav-pills nav-stacked info-help-nav">
					<li role="presentation" class="active"><a>Sección Monitor</a></li>
					<li>
						<div class="info-help-subnav mouse">
							<ul class="nav nav-pills nav-stacked">
								<li onclick="muestraInfo( 'editarPerfilMonitor' )" role="presentation"><a>Editar perfil</a></li>
								<li onclick="muestraInfo( 'cambiarContrasenyaMonitor' )" role="presentation"><a>Cambiar contraseña</a></li>
								<li onclick="muestraInfo( 'cerrarSesionMonitor' )" role="presentation"><a>Cerrar sesión</a></li>
							</ul>
						</div>
					</li>
					
					<li role="presentation" class="active"><a>Sección Reservas</a></li>
					<li>
						<div class="info-help-subnav mouse">
							<ul class="nav nav-pills nav-stacked">
								<li onclick="muestraInfo( 'consultarReservasMonitor' )" role="presentation"><a>Consultar reservas asignadas</a></li>
							</ul>
						</div>
					</li>
					<li role="presentation" class="active"><a>Sección Sugerencias</a></li>
					<li>
						<div class="info-help-subnav mouse">
							<ul class="nav nav-pills nav-stacked">
								<li onclick="muestraInfo( 'sugerenciasMonitor' )" role="presentation"><a>Segurencias, quejas, dudas</a></li>
							</ul>
						</div>
					</li>
				</ul>
			</nav>
		</div>
		<div class="info-help-text">
			<div id="editarPerfilMonitor" class="hidden">
				<h3>Editar perfil</h3>
				<p>Puede editar su perfil pulsando la opción Editar perfil que se encuentra en barra superior de navegación o pulsado el siguiente enlace: <a href="${pageContext.request.contextPath}/instructor/update/${profile.nif}.html">Editar perfil</a>.
En la ventana de editar perfil, modifique el campo de texto que desea. Para guardar los cambios basta con solo pulsar el botón Confirmar cambios que se encuentra al final del formulario.</p>
				<hr class="myHr">
				<p>Si tiene alguna duda puede contactar con nosotros a través del siguiente enlace: <a href="${pageContext.request.contextPath}/info/contact.html">Enlace de contacto</a></p>
			</div>
			<div id="cambiarContrasenyaMonitor" class="hidden">
				<h3>Cambiar contraseña</h3>
				<p>Puede cambiar su contraseña pulsando la opción Cambiar contraseña que se encuentra en barra superior de navegación o pulsado el siguiente enlace: <a href="${pageContext.request.contextPath}/instructor/changePwd/${user.username}.html">Cambiar contraseña</a>.
En esta ventana se le pedirá que introduzca su nueva contraseña. Tras escribir su contraseña, pulse el botón Confirmar contraseña para confirmar los cambios.</p>
				<hr class="myHr">
				<p>Si tiene alguna duda puede contactar con nosotros a través del siguiente enlace: <a href="${pageContext.request.contextPath}/info/contact.html">Enlace de contacto</a></p>
			</div>
			<div id="cerrarSesionMonitor" class="hidden">
				<h3>Cerrar sesión</h3>
				<p>Para cerrar sesión basta con pulsar el botón Salir que se encuentra al final de la barra superior de navegación.
También puede cerrar sesión pulsando sobre el siguiente enlace: <a href="${pageContext.request.contextPath}/logout.html">Cerrar sesión</a></p>
				<hr class="myHr">
				<p>Si tiene alguna duda puede contactar con nosotros a través del siguiente enlace: <a href="${pageContext.request.contextPath}/info/contact.html">Enlace de contacto</a></p>
			</div>
			<div id="consultarReservasMonitor" class="hidden">
				<h3>Consultar reservas asignadas</h3>
				<p>Para poder consultar las reservas que le han sido asignadas, pulse la opción Reservas asignadas que se encuentra en barra superior de navegación.
También puede acceder pulsado el siguiente enlace: <a href="${pageContext.request.contextPath}/booking/instructorBookingList/${profile.nif}.html">Reservas asignadas</a></p>
				<hr class="myHr">
				<p>Si tiene alguna duda puede contactar con nosotros a través del siguiente enlace: <a href="${pageContext.request.contextPath}/info/contact.html">Enlace de contacto</a></p>
			</div>
			<div id="sugerenciasMonitor" class="hidden">
				<h3>Enviar sugerencia, queja o duda</h3>
				<p>Puede realizar sugerencias, quejas, dudas u otro tipo de opinión pulsado el enlace que aparece al final de la página o pulsado el siguiente enlace: <a href="${pageContext.request.contextPath}/suggestion/add.html">Sugerencias, quejas, dudas</a></p>
				<hr class="myHr">
				<p>Si tiene alguna duda puede contactar con nosotros a través del siguiente enlace: <a href="${pageContext.request.contextPath}/info/contact.html">Enlace de contacto</a></p>
			</div>
		</div>
	</div>
</jsp:body>
</t:paginabasica>