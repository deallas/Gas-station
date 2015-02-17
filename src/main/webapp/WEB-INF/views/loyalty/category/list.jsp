<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/WEB-INF/views/confirm.jsp" %>

<fieldset class="table_container">
		<legend>
        	<spring:message code="label.prize.category" />
    	</legend>
		<table class="table table-striped table-hover table-standard-formed">
			<tr>
				<th class="table-nr">#</th>
				<th><a href="#"><spring:message code="label.name" /></a></th>
				<th><a href="#"><spring:message code="label.prize.points" /></a></th>
				<th><a href="#"><spring:message code="label.status" /></a></th>
				<th></th>
			</tr>
			<c:set var="counter" value="1"/>
			<c:forEach var="data" items="${prizeCategoryPaginator.getCurrentItems()}">
			<tr>
				<td class="table-nr">${counter}</td>
				<td>${data.name}</td>
				<td>${data.minPoints}</td>
				<td>
				<c:choose>
				 	<c:when test="${data.status == 'ACTIVE'}">
						<div class="label label-success"><spring:message code="label.status.active" /></div>
					</c:when>
					<c:when test="${data.status == 'INACTIVE'}">
						<div class="label label-important"><spring:message code="label.status.inactive" /></div>
					</c:when>
				</c:choose>
				</td>
				<td>
				<div class="table-options">
					<a href="<c:url value="/admin/loyalty/category/edit/${data.id}" />" class="btn btn-mini" data-toggle="tooltip" data-original-title="<spring:message code="label.edit" />"><span class="icon-edit"></span></a>
					<a href="<c:url value="/admin/loyalty/category/delete/${data.id}" />" class="btn btn-danger btn-mini btn-confirm" data-toggle="tooltip" data-original-title="<spring:message code="label.delete" />"><span class="icon-white icon-trash"></span></a>
				</div>
				</td>
			</tr>
			<c:set var="counter" value="${counter+1}"/>
			</c:forEach>
		</table>
</fieldset>