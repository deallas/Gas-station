<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

<%@ include file="/WEB-INF/views/confirm.jsp" %>

<fieldset class="table_container">
    	<legend>
        	<spring:message code="label.petrol.containermeasurement" />
    	</legend>
		<table class="table table-striped table-hover table-standard-formed">
			<tr>
				<th class="table-nr">#</th>
				<th><a href="#"><spring:message code="label.petrol.containerlog.measurementdate" /></a></th>
				<th><a href="#"><spring:message code="label.petrol.containerlog.type" /></a></th>
				<th><a href="#"><spring:message code="label.petrol.containerlog.value" /></a></th>
				<th><a href="#"><spring:message code="label.petrol.containerlog.status" /></a></th>
				<th><a href="#"><spring:message code="label.petrol.containerId" /></a></th>
				<th><a href="#"><spring:message code="label.petrol.container" /></a></th>
				<th></th>
			</tr>
			<c:set var="counter" value="1"/>
			<c:forEach var="data" items="${petrolContainerMeasurementPaginator.getCurrentItems()}">
			<tr>
				<td class="table-nr">${counter}</td>
				<td><fmt:formatDate pattern="dd-MM-yyyy" value="${data.measurementDate}" /></td>
				<td>
				<c:choose>
					<c:when test="${data.type == 'PRESSURE'}">
						<spring:message code="label.petrol.containerlog.type.pressure" />
					</c:when>
					<c:when test="${data.type == 'PETROL_LEVEL'}">
						<spring:message code="label.petrol.containerlog.type.petrollevel" />
					</c:when>
					<c:otherwise>
						${data.type}
					</c:otherwise>
				</c:choose>
				</td>
				<td>${data.value}</td>
				<td>
				<c:choose>
				 	<c:when test="${data.value < 10}">
						<div class="label label-important"><spring:message code="label.status.level.important" /></div>
					</c:when>
					<c:when test="${data.value >= 10 && data.value < 15}">
						<div class="label label-warning"><spring:message code="label.status.level.warning" /></div>
					</c:when>
					<c:when test="${data.value >= 15}">
						<div class="label label-success"><spring:message code="label.status.level.success" /></div>
					</c:when>
				</c:choose>
				</td>
				<td>${data.petrolContainer.id}</td>
				<td>${data.petrolContainer.type}</td>
				<td>
				<div class="table-options">
					<a href="<c:url value="/admin/monitoring/measurement/edit/${data.id}" />" class="btn btn-mini" data-toggle="tooltip" data-original-title="<spring:message code="label.edit" />"><span class="icon-edit"></span></a>
					<a href="<c:url value="/admin/monitoring/measurement/delete/${data.id}" />" class="btn btn-danger btn-mini btn-confirm" data-toggle="tooltip" data-original-title="<spring:message code="label.delete" />"><span class="icon-white icon-trash"></span></a>
				</div>
				</td>
			</tr>
			<c:set var="counter" value="${counter+1}"/>
			</c:forEach>
		</table>
</fieldset>