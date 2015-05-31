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
						<div class="info-help-subnav">
							<ul class="nav nav-pills nav-stacked">
								<li onclick="setInfoHelpConsultarActividades()" role="presentation"><a>Consultar actividades</a></li>
							</ul>
						</div>
					</li>
					<li role="presentation" class="active"><a>Sección Cliente</a></li>
					<li>
						<div class="info-help-subnav">
							<ul class="nav nav-pills nav-stacked">
								<li onclick="setInfoHelpEditarPerfil()" role="presentation"><a>Editar perfil</a></li>
								<li onclick="setInfoHelpCambiarContrasenya()" role="presentation"><a>Cambiar contraseña</a></li>
								<li onclick="setInfoHelpCerrarSesion()" role="presentation"><a>Cerrar sesión</a></li>
							</ul>
						</div>
					</li>
					
					<li role="presentation" class="active"><a>Sección Reservas</a></li>
					<li>
						<div class="info-help-subnav">
							<ul class="nav nav-pills nav-stacked">
								<li onclick="setInfoHelpNuevaReserva()" role="presentation"><a>Nueva reserva</a></li>
								<li onclick="setInfoHelpConsultarReservas()" role="presentation"><a>Consultar reservas realizadas</a></li>
							</ul>
						</div>
					</li>
					<li role="presentation" class="active"><a>Sección Sugerencias</a></li>
					<li>
						<div class="info-help-subnav">
							<ul class="nav nav-pills nav-stacked">
								<li onclick="setInfoHelpSugerencias()" role="presentation"><a>Segurencias, quejas, dudas</a></li>
							</ul>
						</div>
					</li>
				</ul>
			</nav>
		</div>
		<div id="infoHelpText" class="info-help-text"></div>
	</div>
</jsp:body>
</t:paginabasica>