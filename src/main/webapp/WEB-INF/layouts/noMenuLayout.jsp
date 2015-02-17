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
        <c:set var="currentGlobalMenu"><tiles:insertAttribute name="currentGlobalMenu" ignore="true" /></c:set>
		<tiles:insertAttribute name="globalMenu">
			<tiles:putAttribute name="currentGlobalMenu" value="${currentGlobalMenu}" />
		</tiles:insertAttribute>
        <div class="panel-container">
			<div class="container container-fluid content-container" id="page">
	        	<div class="row-fluid" id="content_container">
      
					<tiles:insertAttribute name="language" />
						
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
			</div>
			<tiles:insertAttribute name="footer" />
		</div>
	</body>
</html>