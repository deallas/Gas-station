<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="menu-left" class="well">
	<ul class="nav nav-list">
	  <li class="nav-header"><spring:message code="label.menu.client.header" /></li>
	  <c:choose>
	  	<c:when test="${page == 'people'}">
	  		<li><a href="<c:url value='/admin/client/people/add' />" ><spring:message code="label.menu.clientPeople.add" /> </a></li>
	  	</c:when>
	  	<c:when test="${page == 'company'}">
	  		<li><a href="<c:url value='/admin/client/company/add' />" ><spring:message code="label.menu.clientCompany.add" /> </a></li>
	  	</c:when>
	  </c:choose>
	  <li><a href="<c:url value='/admin/' />"><spring:message code="label.menu.account.back" /></a></li>
	</ul>
</div>