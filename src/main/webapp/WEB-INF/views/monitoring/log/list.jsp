<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

<%@ include file="/WEB-INF/views/confirm.jsp" %>

<fieldset class="table_container">
    	<legend>
        	<spring:message code="label.petrol.containerlog" />
    	</legend>
		<table class="table table-striped table-hover table-standard-formed">
			<tr>
				<th class="table-nr">#</th>
				<th><a href="#"><spring:message code="label.petrol.containerlog.dateadded" /></a></th>
				<th><a href="#"><spring:message code="label.petrol.containerlog.message" /></a></th>
				<th><a href="#"><spring:message code="label.petrol.containerId" /></a></th>
				<th><a href="#"><spring:message code="label.petrol.container" /></a></th>
			</tr>
			<c:set var="counter" value="1"/>
			<c:forEach var="data" items="${petrolContainerLogPaginator.getCurrentItems()}">
			<tr>
				<td class="table-nr">${counter}</td>
				<td><fmt:formatDate pattern="dd-MM-yyyy" value="${data.dateAdded}" /></td>
				<td>${data.message}</td>
				<td>${data.petrolContainer.id}</td>
				<td>${data.petrolContainer.type}</td>
			</tr>
			<c:set var="counter" value="${counter+1}"/>
			</c:forEach>
		</table>
</fieldset>