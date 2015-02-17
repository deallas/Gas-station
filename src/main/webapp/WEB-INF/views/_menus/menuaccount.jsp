<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="menu-left" class="well">   
	<ul class="nav nav-list">
	  <li class="nav-header"><spring:message code="label.menu.employee.account" /></li>
	  	<c:choose>
      		<c:when test="${not empty client}">
            	<c:set var="path">/client</c:set>
      		</c:when>
      		<c:otherwise>
      			<c:set var="path">/admin/</c:set>
      		</c:otherwise>
	  	</c:choose>
		<li><a href="<c:url value='${path}/account/editpassword' />" ><spring:message code="label.menu.account.editpassword" /></a></li>
	  	<li><a href="<c:url value='${path}/account/edit' />" ><spring:message code="label.menu.account.edit" /></a></li>
	  	<li><c:choose>
				<c:when test="${page == 'password'}">
						<a href="<c:url value='${path}/account/' />"><spring:message code="label.menu.account.back" /></a>
				</c:when>
				<c:when test="${page == 'profile'}">
						<a href="<c:url value='${path}/account/prize/' />"><spring:message code="label.prize.history" /></a>
						<a href="<c:url value='${path}' />"><spring:message code="label.menu.account.back" /></a>
				</c:when>
				</c:choose>
		</li>
	</ul>
</div>