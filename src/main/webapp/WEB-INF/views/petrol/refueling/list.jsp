<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/WEB-INF/views/confirm.jsp" %>

<fieldset class="table_container">
    	<legend>
        	<spring:message code="label.petrol.refueling" />
    	</legend>
		<table class="table table-striped table-hover table-standard-formed">
			<tr>
				<th class="table-nr">#</th>
				<th><a href="#"><spring:message code="label.petrol.refueling.cost" /></a></th>
				<th><a href="#"><spring:message code="label.petrol.refueling.fuel" /></a></th>
				<th><a href="#"><spring:message code="label.petrol.refueling.refuelingdate" /></a></th>
				<th><a href="#"><spring:message code="label.petrol.containerId" /></a></th>
				<th><a href="#"><spring:message code="label.petrol.container" /></a></th>
				<th><a href="#"><spring:message code="label.petrol.client" /></a></th>
				<th><a href="#"><spring:message code="label.petrol.refuelingtype" /></a></th>
				<th></th>
			</tr>
			<c:set var="counter" value="1"/>
			<c:forEach var="data" items="${petrolRefuelingPaginator.getCurrentItems()}">
			<tr>
				<td class="table-nr">${counter}</td>
				<td>${data.cost}</td>
				<td>${data.fuel}</td>
				<td><fmt:formatDate pattern="dd-MM-yyyy" value="${data.refuelingDate}" /></td>
				<td>${data.petrolContainer.id}</td>
				<td>${data.petrolContainer.type}</td>
				<td>${data.client.email}</td>
				<td>${data.refuelingType.name}</td>
				<td>
				<div class="table-options">
					<a href="<c:url value="/admin/petrol/refueling/edit/${data.id}" />" class="btn btn-mini" data-toggle="tooltip" data-original-title="<spring:message code="label.edit" />"><span class="icon-edit"></span></a>
					<a href="<c:url value="/admin/petrol/refueling/delete/${data.id}" />" class="btn btn-danger btn-mini btn-confirm" data-toggle="tooltip" data-original-title="<spring:message code="label.delete" />"><span class="icon-white icon-trash"></span></a>
				</div>
				</td>
			</tr>
			<c:set var="counter" value="${counter+1}"/>
			</c:forEach>
		</table>
</fieldset>