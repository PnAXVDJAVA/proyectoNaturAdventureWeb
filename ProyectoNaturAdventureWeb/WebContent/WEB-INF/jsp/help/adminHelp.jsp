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
								<li onclick="muestraInfo( 'consultarActividadesAdmin' )" role="presentation"><a>Consultar actividades</a></li>
								<li onclick="muestraInfo( 'nuevaActividadAdmin' )" role="presentation"><a>Nueva actividad</a></li>
							</ul>
						</div>
					</li>
					
					<li role="presentation" class="active"><a>Sección Cliente</a></li>
					<li>
						<div class="info-help-subnav mouse">
							<ul class="nav nav-pills nav-stacked">
								<li onclick="muestraInfo( 'consultarClientesAdmin' )" role="presentation"><a>Consultar clientes</a></li>
								<li onclick="muestraInfo( 'nuevoClienteAdmin' )" role="presentation"><a>Nuevo cliente</a></li>
							</ul>
						</div>
					</li>
					
					<li role="presentation" class="active"><a>Sección Monitores</a></li>
					<li>
						<div class="info-help-subnav mouse">
							<ul class="nav nav-pills nav-stacked">
								<li onclick="muestraInfo( 'consultarMonitoresAdmin' )" role="presentation"><a>Consultar monitores</a></li>
								<li onclick="muestraInfo( 'nuevoMonitorAdmin' )" role="presentation"><a>Nuevo monitor</a></li>
								<li onclick="muestraInfo( 'consultarTitulosAdmin' )" role="presentation"><a>Consultar títulos de monitores</a></li>
								<li onclick="muestraInfo( 'nuevoTituloAdmin' )" role="presentation"><a>Nuevo título de monitor</a></li>
							</ul>
						</div>
					</li>
					
					<li role="presentation" class="active"><a>Sección Reservas</a></li>
					<li>
						<div class="info-help-subnav mouse">
							<ul class="nav nav-pills nav-stacked">
								<li onclick="muestraInfo( 'consultarReservasAdmin' )" role="presentation"><a>Consultar reservas</a></li>
							</ul>
						</div>
					</li>
					<li role="presentation" class="active"><a>Sección Sugerencias</a></li>
					<li>
						<div class="info-help-subnav mouse">
							<ul class="nav nav-pills nav-stacked">
								<li onclick="muestraInfo( 'sugerencias' )" role="presentation"><a>Segurencias, quejas, dudas</a></li>
							</ul>
						</div>
					</li>
				</ul>
			</nav>
		</div>
		<div class="info-help-text">
			<div id="consultarActividadesAdmin" class="hidden">
				<h3>Consultar actividades</h3>
				<p>Para poder consultar las actividades disponibles en el sistema, puede hacerlo pulsando en 
				Actividades que se encuentra en la barra superior de navegación y luego seleccionar Listar actividades 
				o mediante este enlace: <a href="${pageContext.request.contextPath}/activity/list.html">Listar actividades</a>.</p>
				<hr class="myHr">
				<p>Si tiene alguna duda puede contactar con nosotros a través del siguiente enlace: <a href="${pageContext.request.contextPath}/info/contact.html">Enlace de contacto</a></p>
			</div>
			<div id="nuevaActividadAdmin" class="hidden">
				<h3>Añadir actividad</h3>
				<p>Puede añadir una nueva actividad seleccionando Actividades disponible en la barra superior de navegación y luego elegir la opción Añadir actividad o puede hacerlo mediante este enlace: <a href="${pageContext.request.contextPath}/activity/add.html">Añadir actividad</a>.</p>
				<hr class="myHr">
				<p>Si tiene alguna duda puede contactar con nosotros a través del siguiente enlace: <a href="${pageContext.request.contextPath}/info/contact.html">Enlace de contacto</a></p>
			</div>
			<div id="consultarClientesAdmin" class="hidden">
				<h3>Consultar clientes</h3>
				<p>Para poder consultar toda la lista de clientes registrados en el sistema, puede hacerlo pulsando en Clientes que se encuentra en la barra superior de navegación y luego elegir la opción Listar clientes o puede hacerlo mediante el siguiente enlace: <a href="${pageContext.request.contextPath}/customer/list.html">Listar clientes</a>.</p>
				<hr class="myHr">
				<p>Si tiene alguna duda puede contactar con nosotros a través del siguiente enlace: <a href="${pageContext.request.contextPath}/info/contact.html">Enlace de contacto</a></p>
			</div>
			<div id="nuevoClienteAdmin" class="hidden">
				<h3>Añadir cliente</h3>
				<p>Puede registrar un nuevo cliente en el sistema seleccionando Clientes disponible en la barra superior de navegación y luego elegir la opción Añadir cliente o puede hacerlo mediante el siguiente enlace: <a href="${pageContext.request.contextPath}/customer/add.html">Añadir cliente</a>.</p>
				<hr class="myHr">
				<p>Si tiene alguna duda puede contactar con nosotros a través del siguiente enlace: <a href="${pageContext.request.contextPath}/info/contact.html">Enlace de contacto</a></p>
						
			</div>
			<div id="consultarMonitoresAdmin" class="hidden">
				<h3>Consultar monitores</h3>
				<p>Para poder consultar todos los monitores que hay registrados en el sistema, puede hacerlo mediante la barra superior de navegación pulsando Monitores y luego elegir la opción Listar Monitores o mediante el siguiente enlace: <a href="${pageContext.request.contextPath}/instructor/list.html">Listar monitores</a>.</p>
				<hr class="myHr">
				<p>Si tiene alguna duda puede contactar con nosotros a través del siguiente enlace: <a href="${pageContext.request.contextPath}/info/contact.html">Enlace de contacto</a></p>
			</div>
			<div id="nuevoMonitorAdmin" class="hidden">
				<h3>Añadir monitor</h3>
				<p>Puede registrar un nuevo monitor en el sistema pulsando Monitores de la barra superior de navegación y luego elegir la opción Añadir monitor o mediante el siguiente enlace: <a href="${pageContext.request.contextPath}/instructor/add.html">Añadir monitor</a>.</p>
				<hr class="myHr">
				<p>Si tiene alguna duda puede contactar con nosotros a través del siguiente enlace: <a href="${pageContext.request.contextPath}/info/contact.html">Enlace de contacto</a></p>
			</div>
			<div id="consultarTitulosAdmin" class="hidden">
				<h3>Consultar títulos</h3>
				<p>Para poder consultar todos los títulos de monitores que hay registrados en el sistema, puede hacerlo mediante la barra superior de navegación pulsando Monitores y luego elegir la opción Listar títulos o mediante el siguiente enlace: <a href="${pageContext.request.contextPath}/degree/list.html">Listar títulos</a>.</p>
				<hr class="myHr">
				<p>Si tiene alguna duda puede contactar con nosotros a través del siguiente enlace: <a href="${pageContext.request.contextPath}/info/contact.html">Enlace de contacto</a></p>
			</div>
			<div id="nuevoTituloAdmin" class="hidden">
				<h3>Añadir título</h3>
				<p>Puede registrar un nuevo título de monitor en el sistema pulsando Monitores situado en la barra superior de navegación y luego elegir la opción Añadir título o mediante el siguiente enlace: <a href="${pageContext.request.contextPath}/degree/add.html">Añadir título</a>.</p>
				<hr class="myHr">
				<p>Si tiene alguna duda puede contactar con nosotros a través del siguiente enlace: <a href="${pageContext.request.contextPath}/info/contact.html">Enlace de contacto</a></p>
			</div>
			<div id="consultarReservasAdmin" class="hidden">
				<h3>Consultar reservas</h3>
				<p>Puede consultar toda la lista de reservas realizadas y registradas en el sistema pulsando en Reservas de la barra superior de navegación y luego eligiendo la opción Listar reservas o mediante el siguiente enlace: <a href="${pageContext.request.contextPath}/booking/list.html">Listar reservas</a>.</p>
				<hr class="myHr">
				<p>Si tiene alguna duda puede contactar con nosotros a través del siguiente enlace: <a href="${pageContext.request.contextPath}/info/contact.html">Enlace de contacto</a></p>
			</div>
			<div id="sugerencias" class="hidden">
				<h3>Sugerencias</h3>
				<p>Puede consultar toda la lista de sugerencias realizadas por los clientes y monitores registradas en el sistema pulsando en Sugerencias de la barra superior de navegación y luego eligiendo la opción Listar sugerencias o mediante el siguiente enlace: <a href="${pageContext.request.contextPath}/suggestion/list.html">Listar sugerencias</a>.</p>
				<hr class="myHr">
				<p>Si tiene alguna duda puede contactar con nosotros a través del siguiente enlace: <a href="${pageContext.request.contextPath}/info/contact.html">Enlace de contacto</a></p>
			</div>
		</div>
	</div>

</jsp:body>
</t:paginabasica>