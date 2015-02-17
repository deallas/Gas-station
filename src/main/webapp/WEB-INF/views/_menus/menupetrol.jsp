<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="menu-left" class="well">
	<ul class="nav nav-list">
	  <li class="nav-header"><spring:message code="label.menu.petrol.header" /></li>
	  <c:choose>
	  	<c:when test="${page == 'container'}">
	  		<li><a href="<c:url value='/admin/petrol/container/add' />" ><spring:message code="title.petrol.container.add" /> </a></li>
	  	</c:when>
	  	<c:when test="${page == 'provider'}">
	  		<li><a href="<c:url value='/admin/petrol/provider/add' />" ><spring:message code="title.petrol.provider.add" /> </a></li>
	  	</c:when>
	  	<c:when test="${page == 'delivery'}">
	  		<li><a href="<c:url value='/admin/petrol/delivery/add' />" ><spring:message code="title.petrol.delivery.add" /> </a></li>
	  	</c:when>
	  	<c:when test="${page == 'refueling'}">
	  		<li><a href="<c:url value='/admin/petrol/refueling/add' />" ><spring:message code="title.petrol.refueling.add" /> </a></li>
	  	</c:when>
	  	<c:when test="${page == 'refuelingtype'}">
	  		<li><a href="<c:url value='/admin/petrol/refuelingtype/add' />" ><spring:message code="title.petrol.refuelingtype.add" /> </a></li>
	  	</c:when>
	  </c:choose>
	  <li><a href="<c:url value='/admin/' />"><spring:message code="label.menu.account.back" /></a></li>
	</ul>
</div>