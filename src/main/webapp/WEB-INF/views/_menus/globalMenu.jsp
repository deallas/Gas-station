<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script type="text/javascript">
$(function(){
	$('.dropdown-toggle').dropdown();
});
</script>
<c:set var="currentGlobalMenu"><tiles:insertAttribute name="currentGlobalMenu" ignore="true" /></c:set>
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
 		<div class="container">
	        <a data-target=".nav-collapse" data-toggle="collapse" class="btn btn-navbar">
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	        </a>
	        <c:choose>
				<c:when test="${not empty client}">
		      		<c:set var="path">/client</c:set>
					<a class="brand" href="<c:url value="${path}/" />">SB Client</a>
		      	</c:when>
		      	<c:otherwise>
		      		<c:set var="path">/admin</c:set>
					<a class="brand" href="<c:url value="${path}/" />">SB Admin</a>
		      	</c:otherwise>
			</c:choose>
            <div class="nav-collapse">
            	<ul class="nav">
                	<li <c:if test="${currentGlobalMenu == 'account'}">class="active"</c:if>>
						<a href="<c:url value="${path}/account/" />"><spring:message code="label.menu.account" /></a>
					</li>
                    <c:choose>
				    	<c:when test="${not empty client}">
				      		<!-- Dodatkowe linki dla klienta -->
				      	</c:when>
				    	<c:otherwise>
				      		<!-- Dodatkowe linki dla pracownika -->
			 				<li<c:if test="${currentGlobalMenu == 'employee'}"> class="active"</c:if>>
				        		<a href="<c:url value="${path}/employee/" />"><spring:message code="label.menu.employees" /></a>
				    		</li>
				   			<c:set var="active"></c:set>
				    		<c:if test="${currentGlobalMenu == 'client'}">
				    			<c:set var="active">active</c:set>
				    		</c:if>
				    		<li class="dropdown ${active}">
  								<a class="dropdown-toggle" data-toggle="dropdown" href="#"><spring:message code="label.menu.client.header" /> <b class="caret"></b></a>
  								<ul class="dropdown-menu">
				    				<li>
				    					<a href="<c:url value="${path}/client/people/" />"><spring:message code="label.client.people" /></a>
				    				</li>
				    				<li>
				    					<a href="<c:url value="${path}/client/company/" />"><spring:message code="label.client.company" /></a>
				    				</li>
  								</ul>
				    		</li>
						    <c:set var="active"></c:set>
				    		<c:if test="${currentGlobalMenu == 'petrol'}">
				    			<c:set var="active">active</c:set>
				    		</c:if>
				    		<li class="dropdown ${active}">
  								<a class="dropdown-toggle" data-toggle="dropdown" href="#"><spring:message code="label.menu.petrol.header" /> <b class="caret"></b></a>
  								<ul class="dropdown-menu">
				    				<li>
				    					<a href="<c:url value="${path}/petrol/container/" />"><spring:message code="label.petrol.container" /></a>
				    				</li>
				    				<li>
				    					<a href="<c:url value="${path}/petrol/provider/" />"><spring:message code="label.petrol.provider" /></a>
				    				</li>
				    				<li>
				    					<a href="<c:url value="${path}/petrol/delivery/" />"><spring:message code="label.petrol.delivery" /></a>
				    				</li>
				    				<li>
				    					<a href="<c:url value="${path}/petrol/refueling/" />"><spring:message code="label.petrol.refueling" /></a>
				    				</li>
				    				<li>
				    					<a href="<c:url value="${path}/petrol/refuelingtype/" />"><spring:message code="label.petrol.refuelingtypes" /></a>
				    				</li>
  								</ul>
				    		</li>
				    		<c:set var="active"></c:set>
				    		<c:if test="${currentGlobalMenu == 'carwash'}">
				    			<c:set var="active">active</c:set>
				    		</c:if>
				    		<li class="dropdown ${active}">
  								<a class="dropdown-toggle" data-toggle="dropdown" href="#"><spring:message code="label.menu.carwash" /> <b class="caret"></b></a>
  								<ul class="dropdown-menu">
				    				<li>
				    					<a href="<c:url value="${path}/carwash/" />"><spring:message code="label.menu.carwash.list" /></a>
				    				</li>
				    				<li>
				    					<a href="<c:url value="${path}/carwashtype/" />"><spring:message code="label.menu.carwashtype.list" /></a>
				    				</li>
  								</ul>
				    		</li>
				    		<c:set var="active"></c:set>
				    		<c:if test="${currentGlobalMenu == 'monitoring'}">
				    			<c:set var="active">active</c:set>
				    		</c:if>
				    		<li class="dropdown ${active}">
  								<a class="dropdown-toggle" data-toggle="dropdown" href="#"><spring:message code="label.menu.monitoring.header" /> <b class="caret"></b></a>
  								<ul class="dropdown-menu">
				    				<li>
				    					<a href="<c:url value="${path}/monitoring/measurement/" />"><spring:message code="label.petrol.containermeasurement" /></a>
				    				</li>
				    				<li>
				    					<a href="<c:url value="${path}/monitoring/log/" />"><spring:message code="label.petrol.containerlog" /></a>
				    				</li>
  								</ul>
				    		</li>
				    		<c:set var="active"></c:set>
				    		<c:if test="${currentGlobalMenu == 'loyalty'}">
				    			<c:set var="active">active</c:set>
				    		</c:if>
				    		<li class="dropdown ${active}">
  								<a class="dropdown-toggle" data-toggle="dropdown" href="#"><spring:message code="label.loyalty" /> <b class="caret"></b></a>
  								<ul class="dropdown-menu">
				    				<li>
				    					<a href="<c:url value="${path}/loyalty/prize/" />"><spring:message code="label.prizes" /></a>
				    				</li>
				    				<li>
				    					<a href="<c:url value="${path}/loyalty/category/" />"><spring:message code="label.prize.categories" /></a>
				    				</li>
  								</ul>
				    		</li>
				    		<li<c:if test="${currentGlobalMenu == 'bill'}"> class="active"</c:if>>
				        		<a href="<c:url value="${path}/bill/" />"><spring:message code="label.bills" /></a>
				    		</li>
			      		</c:otherwise>
				  	</c:choose>
				</ul>                            
				<ul class="nav pull-right">
                	<li><a href="<c:url value="/logout" />"><spring:message code="label.menu.logout" /> [<sec:authentication property="principal.username" />]</a></li>
               	</ul>
           	</div>
    	</div>
	</div>
</div>