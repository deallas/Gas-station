<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<c:set var="titleKey"><tiles:insertAttribute name="title" ignore="true" /></c:set>
		<title><tiles:insertAttribute name="appname" /> - <spring:message code="${titleKey}" /></title>
		<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
		<tiles:useAttribute id="styles" name="styles" classname="java.util.List" />
		<c:forEach var="style" items="${styles}">
			<c:set var="styleUrl"><c:url value="${style}" /></c:set>
			<link href="${styleUrl}" rel="stylesheet" type="text/css" />
  		</c:forEach>
  		
		<tiles:useAttribute id="scripts" name="scripts" classname="java.util.List" />
		<c:forEach var="script" items="${scripts}">
			<c:set var="scriptUrl"><c:url value="${script}" /></c:set>
			<script type="text/javascript" src="${scriptUrl}"></script>
  		</c:forEach>
	</head>
	<body>
	<div class="home-container" style="background-image:url('<c:url value="/static/img/stripe.png" />');">
			<div class="container container-fluid content-container" id="page">
	        	<div class="row-fluid" id="content_container">
	        		<div id="header">
	        		<div id="utils" class="box">
	        			<div>
	        				<tiles:insertAttribute name="language" />
	        			</div>
	        			<div id="activeuser">
	        					<sec:authorize access="isAuthenticated()">
	        						<p class="inline-block nomargin"><spring:message code="label.loggedas" /> </p>
	        						<p class="inline-block nomargin">
	        						<c:choose>
				      					<c:when test="${client == 'TRUE'}">
											<a href="<c:url value="/client/" />"><sec:authentication property="principal.username" /></a>
				      					</c:when>
				      					<c:otherwise>
											<a href="<c:url value="/admin/" />"><sec:authentication property="principal.username" /></a>
				      					</c:otherwise>
					  				</c:choose>
									| </p>
	        						<p class="inline-block nomargin"><a href="<c:url value="/logout" />"><spring:message code="label.logout" /></a></p>
	        					</sec:authorize>
	        					<sec:authorize access="isAnonymous()">
	        						<p class="nomargin"><spring:message code="label.hello" />, <a href="<c:url value="/login" />" id="login-ref"><spring:message code="label.pleaselogin" /></a></p>
	        					</sec:authorize>
	        			</div>
	        		</div>
	        			<div id="logo">
	        				<img src="<c:url value="/static/img/logo.png" />" />
	        			</div>
	        		</div>
	        	</div>
					<div class="navbar">
					  <div class="navbar-inner">
					    <div class="container">
					 
					      <!-- .btn-navbar is used as the toggle for collapsed navbar content -->
					      <a class="btn btn-navbar" data-toggle="collapse" data-target=".navbar-responsive-collapse">
					        <span class="icon-bar"></span>
					        <span class="icon-bar"></span>
					        <span class="icon-bar"></span>
					      </a>
					 
					      <!-- Be sure to leave the brand out there if you want it shown -->
					      <a class="brand" href="#"></a>
					 
					      <!-- Everything you want hidden at 940px or less, place within here -->
					      <div class="nav-collapse collapse navbar-responsive-collapse">
					      
					      	<ul class="nav">
					      	<c:choose>
					      		<c:when test="${not empty isClient}">
					      		<!-- Dodatkowe linki dla klienta -->
					      			<li <c:if test="${page == 'home'}">class="active"</c:if>><a href="<c:url value="/" />"><spring:message code="label.nav.home" /></a></li>
					      			<li <c:if test="${page == 'pricelist'}">class="active"</c:if>><a href="<c:url value="/pricelist" />"><spring:message code="label.nav.pricelist" /></a></li>
					      			<li <c:if test="${page == 'loyalty'}">class="active"</c:if>><a href="<c:url value="/loyalty" />"><spring:message code="label.loyalty" /></a></li>
					      			<li <c:if test="${page == 'contact'}">class="active"</c:if>><a href="<c:url value="/contact" />"><spring:message code="label.nav.contact" /></a></li>	
				      			</c:when>
				    			<c:otherwise>
					      			<li <c:if test="${page == 'home'}">class="active"</c:if>><a href="<c:url value="/" />"><spring:message code="label.nav.home" /></a></li>
					      			<li <c:if test="${page == 'pricelist'}">class="active"</c:if>><a href="<c:url value="/pricelist" />"><spring:message code="label.nav.pricelist" /></a></li>
					      			<li <c:if test="${page == 'loyalty'}">class="active"</c:if>><a href="<c:url value="/loyalty" />"><spring:message code="label.loyalty" /></a></li>
					      			<li <c:if test="${page == 'contact'}">class="active"</c:if>><a href="<c:url value="/contact" />"><spring:message code="label.nav.contact" /></a></li>
					      		</c:otherwise>
				  			</c:choose>
					      	</ul>
					        <!-- .nav, .navbar-search, .navbar-form, etc -->
					      </div>
					 
					    </div>
					  </div>
					</div> 	
	        		<div id="content-page">				
						<c:if test="${not empty INFO_MESSAGE}"> 
						   	<div class="alert alert-success"> 
						       	<button class="close" data-dismiss="alert">×</button> 
					       	<spring:message code="${INFO_MESSAGE}" /> 
						   	</div> 
						</c:if>
						<c:if test="${not empty WARNING_MESSAGE}"> 
						   	<div class="alert alert-block"> 
						       	<button class="close" data-dismiss="alert">×</button> 
						       	<h4><spring:message code="label.warning" /> !</h4>
						       	<p><spring:message code="${WARNING_MESSAGE}" />!</p> 
						   	</div> 
						</c:if>
						<c:if test="${not empty ERROR_MESSAGE}"> 
						   	<div class="alert alert-error"> 
						       	<button class="close" data-dismiss="alert">×</button> 
						       	<h4><spring:message code="label.error" /> !</h4>
						       	<p><spring:message code="${ERROR_MESSAGE}" />!</p> 
						   	</div> 
						</c:if>
						<tiles:insertAttribute name="body" />
					</div>
					<tiles:insertAttribute name="footer" />
				</div>
			</div>
		</div>
	</body>
</html>