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
				<th><a href="#"><spring:message code="label.prize.date" /></a></th>
				<th><a href="#"><spring:message code="label.status" /></a></th>
				<th><a href="#"><spring:message code="label.client" /></a></th>
				<th><a href="#"><spring:message code="label.prize.category" /></a></th>
			</tr>
			<c:set var="counter" value="1"/>
			<c:forEach var="data" items="${prizePaginator.getCurrentItems()}">
			<tr>
				<td class="table-nr">${counter}</td>
				<td><fmt:formatDate pattern="dd-MM-yyyy" value="${data.dateOfSelection}" /></td>
				<td>
				<c:choose>
				 	<c:when test="${data.status == 'WAITING'}">
						<div class="label label-important"><spring:message code="label.status.waiting" /></div>
					</c:when>
					<c:when test="${data.status == 'COMPLETED'}">
						<div class="label label-success"><spring:message code="label.status.completed" /></div>
					</c:when>
				</c:choose>
				</td>
				<td>${data.client.email}</td>
				<td>${data.category.name}</td>
			</tr>
			<c:set var="counter" value="${counter+1}"/>
			</c:forEach>
		</table>
</fieldset>