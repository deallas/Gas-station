<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="menu-left" class="well">   
	<ul class="nav nav-list">
	  	<li>
		  	<c:choose>
	      		<c:when test="${not empty client}">
	            	<c:set var="path">/client</c:set>
	      		</c:when>
	      		<c:otherwise>
	      			<c:set var="path">/admin</c:set>
	      		</c:otherwise>
		  	</c:choose>
	  		<c:choose>
	  			<c:when test="${page == 'backbill'}">
						<a href="<c:url value='${path}/bill/' />"><spring:message code="label.menu.account.back" /></a>
				</c:when>
				<c:when test="${page == 'backemployee'}">
						<a href="<c:url value='${path}/employee/' />"><spring:message code="label.menu.account.back" /></a>
				</c:when>
				<c:when test="${page == 'backclientpeople'}">
						<a href="<c:url value='${path}/client/people/' />"><spring:message code="label.menu.account.back" /></a>
				</c:when>
				<c:when test="${page == 'backclientcompany'}">
						<a href="<c:url value='${path}/client/company/' />"><spring:message code="label.menu.account.back" /></a>
				</c:when>
				<c:when test="${page == 'backaccount'}">
						<a href="<c:url value='${path}/employee/' />"><spring:message code="label.menu.account.back" /></a>
				</c:when>
				<c:when test="${page == 'password'}">
						<a href="<c:url value='${path}/account/' />"><spring:message code="label.menu.account.back" /></a>
				</c:when>
				<c:when test="${page == 'profile'}">
						<a href="<c:url value='${path}/' />"><spring:message code="label.menu.account.back" /></a>
				</c:when>
				<c:when test="${page == 'editaccount'}">
						<a href="<c:url value='${path}/account/' />"><spring:message code="label.menu.account.back" /></a>
				</c:when>
				<c:when test="${page == 'backcarwash'}">
						<a href="<c:url value='${path}/carwash/' />"><spring:message code="label.menu.account.back" /></a>
				</c:when>
				<c:when test="${page == 'backcarwashtype'}">
						<a href="<c:url value='${path}/carwashtype/' />"><spring:message code="label.menu.account.back" /></a>
				</c:when>	
				<c:when test="${page == 'backcontainer'}">
						<a href="<c:url value='${path}/petrol/container/' />"><spring:message code="label.menu.account.back" /></a>
				</c:when>
				<c:when test="${page == 'backprovider'}">
						<a href="<c:url value='${path}/petrol/provider/' />"><spring:message code="label.menu.account.back" /></a>
				</c:when>
				<c:when test="${page == 'backdelivery'}">
						<a href="<c:url value='${path}/petrol/delivery/' />"><spring:message code="label.menu.account.back" /></a>
				</c:when>
				<c:when test="${page == 'backrefueling'}">
						<a href="<c:url value='${path}/petrol/refueling/' />"><spring:message code="label.menu.account.back" /></a>
				</c:when>
				<c:when test="${page == 'backrefuelingtype'}">
						<a href="<c:url value='${path}/petrol/refuelingtype/' />"><spring:message code="label.menu.account.back" /></a>
				</c:when>
				<c:when test="${page == 'backmeasurement'}">
						<a href="<c:url value='${path}/monitoring/measurement/' />"><spring:message code="label.menu.account.back" /></a>
				</c:when>
				<c:when test="${page == 'backlog'}">
						<a href="<c:url value='${path}/monitoring/log/' />"><spring:message code="label.menu.account.back" /></a>
				</c:when>
				<c:when test="${page == 'backprize'}">
						<a href="<c:url value='${path}/loyalty/prize/' />"><spring:message code="label.menu.account.back" /></a>
				</c:when>
				<c:when test="${page == 'backcategory'}">
						<a href="<c:url value='${path}/loyalty/category/' />"><spring:message code="label.menu.account.back" /></a>
				</c:when>
			</c:choose>
		</li>
	</ul>
</div>