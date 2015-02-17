<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
		<div class="container container-fluid content-container" id="page">
        	<div class="row-fluid" id="content_container">
            	<div class="span12" id="content">
            		<div class="span6" id="login-container">
                		<tiles:insertAttribute name="language" />
						<tiles:insertAttribute name="body" />
						<tiles:insertAttribute name="footer" />
					</div>
				</div>
			</div>
		</div>
	</body>
</html>