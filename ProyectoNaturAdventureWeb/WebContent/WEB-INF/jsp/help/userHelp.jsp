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
					<li role="presentation" class="active"><a>Acceso al Sistema</a></li>
					<li>
						<div class="info-help-subnav mouse">
							<ul class="nav nav-pills nav-stacked">
								<li onclick="muestraInfo( 'registrarseUser' )" role="presentation"><a>Registrarse</a></li>
								<li onclick="muestraInfo( 'iniciarSesionUser' )" role="presentation"><a>Iniciar sesión</a></li>
								<li onclick="muestraInfo( 'contrasenyaOlvidadaUser' )" role="presentation"><a>Contraseña olvidada</a></li>
							</ul>
						</div>
					</li>

					<li role="presentation" class="active"><a>Sección Actividades</a></li>
					<li>
						<div class="info-help-subnav mouse">
							<ul class="nav nav-pills nav-stacked">
								<li onclick="muestraInfo( 'consultarActividadesUser' )" role="presentation"><a>Consultar actividades</a></li>
							</ul>
						</div>
					</li>
					<li role="presentation" class="active"><a>Sección Sugerencias</a></li>
					<li>
						<div class="info-help-subnav mouse">
							<ul class="nav nav-pills nav-stacked">
								<li onclick="muestraInfo( 'sugerenciasUser' )" role="presentation"><a>Segurencias, quejas, dudas</a></li>
							</ul>
						</div>
					</li>
				</ul>
			</nav>
		</div>
		<div class="info-help-text">
			<div id="registrarseUser" class="hidden">
				<h3>Registro de usuario</h3>
				<p>Para poder registrarse en el sistema, pulse la opción Registrarse que encontrara en la barra superior de navegación o pulsado el siguiente enlace: <a href="${pageContext.request.contextPath}/customer/add.html">Registrarse</a>.
En la ventana de registro, introduzca los datos solicitados y pulse Registrar para completar el registro.</p>
				<hr class="myHr">
				<p>Si tiene alguna duda puede contactar con nosotros a través del siguiente enlace: <a href="${pageContext.request.contextPath}/info/contact.html">Enlace de contacto</a></p>
			</div>
			<div id="iniciarSesionUser" class="hidden">
				<h3>Iniciar sesión</h3>
				<p>Puede iniciar sesión pulsando el botón Login que se encuentra al final de la barra superior de navegación.
También puede iniciar sesión pulsando el siguiente enlace: <a href="${pageContext.request.contextPath}/login.html">Iniciar Sesión</a></p>
				<hr class="myHr">
				<p>Si tiene alguna duda puede contactar con nosotros a través del siguiente enlace: <a href="${pageContext.request.contextPath}/info/contact.html">Enlace de contacto</a></p>
			</div>
			<div id="contrasenyaOlvidadaUser" class="hidden">
				<h3>Recuperar contraseña olvidada</h3>
				<p>Si ha olvidado la contraseña, puede obtener una nueva pulsado el siguiente enlace:  <a href="${pageContext.request.contextPath}/customer/pwdRecovery.html">Contraseña olvidada</a>.
En la ventana de cambiar contraseña, se le pedirá introducir su correo electrónico y nombre de usuario para validarlo en el sistema.
Si su nombre de usuario y correo electrónico existen en sistema, se le enviará un correo electrónico con su nueva contraseña con la que podrá acceder al sistema.
Tras acceder al sistema, le recomendamos que cambie su contraseña.</p>
				<hr class="myHr">
				<p>Si tiene alguna duda puede contactar con nosotros a través del siguiente enlace: <a href="${pageContext.request.contextPath}/info/contact.html">Enlace de contacto</a></p>
			</div>
			<div id="consultarActividadesUser" class="hidden">
				<h3>Consultar lista de actividades</h3>
				<p>Para consultar las actividades que están actualmente disponibles, puede hacerlo pulsando la opción Listar actividades que encontrara en la barra superior de navegación o pulsado el siguiente enlace: <a href="${pageContext.request.contextPath}/activity/customerList.html">Lista actividades.</a>
En la ventana de listar actividades, podrá consultar las actividades disponibles. Para obtener más información de la actividad, pulse la flecha hacia abajo que encontrara al final recuadro de la actividad.</p>
				<hr class="myHr">
				<p>Si tiene alguna duda puede contactar con nosotros a través del siguiente enlace: <a href="${pageContext.request.contextPath}/info/contact.html">Enlace de contacto</a></p>
			</div>
			<div id="sugerenciasUser" class="hidden">
				<h3>Enviar sugerencia, queja o duda</h3>
				<p>Puede realizar sugerencias, quejas, dudas u otro tipo de opinión pulsado el enlace que aparece al final de la página o pulsado el siguiente enlace: <a href="${pageContext.request.contextPath}/suggestion/add.html">Sugerencias, quejas, dudas</a></p>
				<hr class="myHr">
				<p>Si tiene alguna duda puede contactar con nosotros a través del siguiente enlace: <a href="${pageContext.request.contextPath}/info/contact.html">Enlace de contacto</a></p>
			</div>
		</div>
	</div>
</jsp:body>
</t:paginabasica>