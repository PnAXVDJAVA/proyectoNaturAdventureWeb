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
					<li role="presentation" class="active"><a>Sección Actividades</a></li>
					<li>
						<div class="info-help-subnav mouse">
							<ul class="nav nav-pills nav-stacked">
								<li onclick="muestraInfo( 'consultarActividadesCustomer' )" role="presentation"><a>Consultar actividades</a></li>
							</ul>
						</div>
					</li>
					<li role="presentation" class="active"><a>Sección Cliente</a></li>
					<li>
						<div class="info-help-subnav mouse">
							<ul class="nav nav-pills nav-stacked">
								<li onclick="muestraInfo( 'editarPerfilCustomer' )" role="presentation"><a>Editar perfil</a></li>
								<li onclick="muestraInfo( 'cambiarContrasenyaCustomer' )" role="presentation"><a>Cambiar contraseña</a></li>
								<li onclick="muestraInfo( 'cerrarSesionCustomer' )" role="presentation"><a>Cerrar sesión</a></li>
							</ul>
						</div>
					</li>
					
					<li role="presentation" class="active"><a>Sección Reservas</a></li>
					<li>
						<div class="info-help-subnav mouse">
							<ul class="nav nav-pills nav-stacked">
								<li onclick="muestraInfo( 'nuevaReservaCustomer' )" role="presentation"><a>Nueva reserva</a></li>
								<li onclick="muestraInfo( 'consultarReservasCustomer' )" role="presentation"><a>Consultar reservas realizadas</a></li>
							</ul>
						</div>
					</li>
					<li role="presentation" class="active"><a>Sección Sugerencias</a></li>
					<li>
						<div class="info-help-subnav mouse">
							<ul class="nav nav-pills nav-stacked">
								<li onclick="muestraInfo( 'sugerenciasCustomer' )" role="presentation"><a>Segurencias, quejas, dudas</a></li>
							</ul>
						</div>
					</li>
				</ul>
			</nav>
		</div>
		<div class="info-help-text">
			<div id="consultarActividadesCustomer" class="hidden">
				<h3>Consultar actividades</h3>
				<p>Para consultar las actividades que están actualmente disponibles, puede hacerlo pulsando la opción Listar actividades que encontrara en la barra superior de navegación o pulsado el siguiente enlace: <a href="${pageContext.request.contextPath}/activity/customerList.html">Listar actividades</a>.
					En la ventana de listar actividades, podrá consultar las actividades disponibles. Para obtener más información de la actividad, pulse la flecha hacia abajo que encontrara al final recuadro de la actividad.</p>
				<hr class="myHr">
				<p>Si tiene alguna duda puede contactar con nosotros a través del siguiente enlace: <a href="${pageContext.request.contextPath}/info/contact.html">Enlace de contacto</a></p>
			</div>
			<div id="editarPerfilCustomer" class="hidden">
				<h3>Editar perfil</h3>
				<p>Puede editar su perfil pulsando la opción Editar perfil que se encuentra en barra superior de navegación o pulsado el siguiente enlace: <a href="${pageContext.request.contextPath}/customer/update/${profile.nif}.html">Editar perfil</a>.
En la ventana de editar perfil, modifique el campo de texto que desea. Para guardar los cambios basta con solo pulsar el botón Confirmar cambios que se encuentra al final del formulario.</p>
				<hr class="myHr">
				<p>Si tiene alguna duda puede contactar con nosotros a través del siguiente enlace: <a href="${pageContext.request.contextPath}/info/contact.html">Enlace de contacto</a></p>
			</div>
			<div id="cambiarContrasenyaCustomer" class="hidden">
				<h3>Cambiar contraseña</h3>
				<p>Puede cambiar su contraseña pulsando la opción Cambiar contraseña que se encuentra en barra superior de navegación o pulsado el siguiente enlace: <a href="${pageContext.request.contextPath}/customer/changePwd/${user.username}.html">Cambiar contraseña</a>.
En esta ventana se le pedirá que introduzca su nueva contraseña. Tras escribir su contraseña, pulse el botón Confirmar contraseña para confirmar los cambios.</p>
				<hr class="myHr">
				<p>Si tiene alguna duda puede contactar con nosotros a través del siguiente enlace: <a href="${pageContext.request.contextPath}/info/contact.html">Enlace de contacto</a></p>
			</div>
			<div id="cerrarSesionCustomer" class="hidden">
				<h3>Cerrar sesión</h3>
				<p>Para cerrar sesión basta con pulsar el botón Salir que se encuentra al final de la barra superior de navegación.
También puede cerrar sesión pulsando sobre el siguiente enlace: <a href="${pageContext.request.contextPath}/logout.html">Cerrar sesión</a></p>
				<hr class="myHr">
				<p>Si tiene alguna duda puede contactar con nosotros a través del siguiente enlace: <a href="${pageContext.request.contextPath}/info/contact.html">Enlace de contacto</a></p>
			</div>
			<div id="nuevaReservaCustomer" class="hidden">
				<h3>Realizar reserva</h3>
				<p>Para realizar una reserva de una actividad, siga los siguientes pasos:
					<ul>
						<li>Seleccione la opción Listar actividades que encontrara en la barra superior de navegación. Puede acceder también a esta opción pulsado el siguiente enlace: <a href="${pageContext.request.contextPath}/activity/customerList.html">Listar actividades</a>.</li>
						<li>Seleccione la actividad que desee realizar y pulse en la flecha con dirección hacia abajo que encontrara al final recuadro de la actividad.</li>
						<li>Seleccione Reservar actividad.</li>
						<li>Introduzca los datos solicitados. Para introducir la fecha propuesta, pulse el calendario para seleccionar la fecha que desea realizar la actividad.</li>
						<li>Pulse Siguiente para confirmar los cambios.</li>
						<li>Aparecerá una ventana de confirmación de la reserva. Compruebe que los datos introducidos son los correctos.</li>
						<li>Pulse Confirmar reserva para aceptar su solicitud.</li>
						<li>Para finalizar, se le enviará un correo electrónico indicando los datos de solicitud de la reserva.</li>
					</ul>
					Si finalmente la reserva se confirma, recebirá un correo electrónico indicándole que su reserva ha sido confirmada con todos
					los datos de ésta, y si finalmente la reserva resulta ser rechazada, recibirá también un correo electrónico para indicárselo.
					</p>
				<hr class="myHr">
				<p>Si tiene alguna duda puede contactar con nosotros a través del siguiente enlace: <a href="${pageContext.request.contextPath}/info/contact.html">Enlace de contacto</a></p>
			</div>
			<div id="consultarReservasCustomer" class="hidden">
				<h3>Consultar reservas realizadas</h3>
				<p>Puede consultar las reservas realizadas pulsando la opción Mis reservas que encontrara en la barra superior de navegación o pulsado el siguiente enlace: <a href="${pageContext.request.contextPath}/booking/customerBookingList/${profile.nif}.html">Mis reservas</a>.
					En esta ventana aparecerá un listado con todas las reservas realizadas indicando la fecha propuesta, nombre de la actividad y el estado en el cual se encuentra la actividad.
					Puede consultar más detalles de alguna reserva pulsando la opción Más detalles que encontrara a la derecha de cada reserva.</p>
				<hr class="myHr">
				<p>Si tiene alguna duda puede contactar con nosotros a través del siguiente enlace: <a href="${pageContext.request.contextPath}/info/contact.html">Enlace de contacto</a></p>
			</div>
			<div id="sugerenciasCustomer" class="hidden">
				<h3>Enviar sugerencia, queja o duda</h3>
				<p>Puede realizar sugerencias, quejas, dudas u otro tipo de opinión pulsado el enlace que aparece al final de la página o pulsado el siguiente enlace:  <a href="${pageContext.request.contextPath}/suggestion/add.html">Sugerencias, quejas, dudas</a>.</p>
				<hr class="myHr">
				<p>Si tiene alguna duda puede contactar con nosotros a través del siguiente enlace: <a href="${pageContext.request.contextPath}/info/contact.html">Enlace de contacto</a></p>
			</div>
		</div>
	</div>
</jsp:body>
</t:paginabasica>