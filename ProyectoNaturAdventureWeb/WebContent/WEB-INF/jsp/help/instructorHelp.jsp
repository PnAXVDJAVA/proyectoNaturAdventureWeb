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
								<li onclick="setInfoHelpConsultarReservasMonitor()" role="presentation"><a>Consultar reservas asignadas</a></li>
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