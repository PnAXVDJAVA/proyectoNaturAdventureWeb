<%@tag pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d"%>
<c:set var="user" scope="request" value='${session.getAttribute("user")}'/>
<d:set var="role" scope="request" value='${user.role}' />

<c:choose>
	<c:when test="${user == null}"> 
		<div class="container">
			<div class="help">
				<p><a href="${pageContext.request.contextPath}/help/userHelp.html"><span class="glyphicon glyphicon-question-sign"></span>  Ayuda</a></p>
			</div>
			<div class="clear"></div>
		</div>
	</c:when>
	<c:when test="${role == 0}"> 
		<div class="container">
			<div class="help">
				<p><a href="${pageContext.request.contextPath}/help/customerHelp.html"><span class="glyphicon glyphicon-question-sign"></span>  Ayuda</a></p>
			</div>
			<div class="clear"></div>
		</div>
	</c:when>
	<c:when test="${role == 1}"> 
		<div class="container">
			<div class="help">
				<p><a href="${pageContext.request.contextPath}/help/instructorHelp.html"><span class="glyphicon glyphicon-question-sign"></span>  Ayuda</a></p>
			</div>
			<div class="clear"></div>
		</div>
	</c:when>
	<c:when test="${role == 2}"> 
		<div class="container">
			<div class="help">
				<p><a href="${pageContext.request.contextPath}/help/adminHelp.html"><span class="glyphicon glyphicon-question-sign"></span>  Ayuda</a></p>
			</div>
			<div class="clear"></div>
		</div>
	</c:when>
</c:choose>
