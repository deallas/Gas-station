<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="language-container">
	<p id="language-title"><spring:message code="label.chooseLanguage" />:</p>
	<ul id="language-list">
		<li>
			<c:choose>
				<c:when test="${pageContext.response.locale == 'pl'}">Polski</c:when>
			 	<c:otherwise><a href="?language=pl">Polski</a></c:otherwise>
			</c:choose>
		</li>
		<li>|</li>
		<li>
			<c:choose>
				<c:when test="${pageContext.response.locale == 'en'}">English</c:when>
			 	<c:otherwise><a href="?language=en">English</a></c:otherwise>
			</c:choose>
		</li>
	</ul>
</div>