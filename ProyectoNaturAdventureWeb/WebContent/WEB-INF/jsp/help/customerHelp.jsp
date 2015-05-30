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
					<li onclick="setInfoHelpSeccionUsuario()" role="presentation" class="active"><a>Sección Usuario</a></li>
					<li>
						<div class="info-help-subnav">
							<ul class="nav nav-pills nav-stacked">
								<li onclick="setInfoHelpIniciarSesion()" role="presentation"><a>Iniciar sesión</a></li>
								<li onclick="setInfoHelpCerrarSesion()" role="presentation"><a>Cerrar sesión</a></li>
								<li onclick="setInfoHelpEditarPerfil()" role="presentation"><a>Editar perfil</a></li>
								<li onclick="setInfoHelpCambiarContrasenya()" role="presentation"><a>Cambiar contraseña</a></li>
							</ul>
						</div>
					</li>
					
					<li onclick="setInfoHelpSeccionActividades()" role="presentation" class="active"><a>Sección Actividades</a></li>
					<li>
						<div class="info-help-subnav">
							<ul class="nav nav-pills nav-stacked">
								<li onclick="setInfoHelpListaDeActividades()" role="presentation"><a>Lista de actividades</a></li>
							</ul>
						</div>
					</li>
					<li onclick="setInfoHelpSeccionReservas()" role="presentation" class="active"><a>Sección Reservas</a></li>
					<li>
						<div class="info-help-subnav">
							<ul class="nav nav-pills nav-stacked">
								<li onclick="setInfoHelpNuevaReserva()" role="presentation"><a>Nueva reserva</a></li>
								<li onclick="setInfoHelpConsultarReservas()" role="presentation"><a>Consultar reservas</a></li>
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