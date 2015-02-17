<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/confirm.jsp" %>
<fieldset class="table_container">
    <legend>
        <spring:message code="title.carwashtype.list" />
        <span id="options-container">
            <a href="#" id="b-options-container"><i class="icon-caret-right"></i> <spring:message code="label.options" /></a>
        </span>
    </legend>
    
	<tiles:insertTemplate template="/WEB-INF/views/listOptions.jsp">
		<tiles:putAttribute name="paginator" value="${paginator}" />
	</tiles:insertTemplate>
	
	<c:set var="options" value="${paginator.getOptions()}" />
	<c:set var="orders" value="${options.getOrders()}" />
	<c:set var="cOrder" value="${options.getOrder()}" />
	<c:set var="isAscing" value="${options.isAscing()}" />
	
	<table class="table table-striped table-hover table-standard-formed">
		<tr>
			<th class="table-nr">#</th>
			<c:forEach var="order" items="${orders}">
				<c:set var="orderName" value="label.${order}" />
				<th>
					<a href="?order=${order}<c:if test="${order == cOrder}">&ascing=<c:choose><c:when test="${isAscing}">0</c:when><c:otherwise>1</c:otherwise></c:choose></c:if>">
						<spring:message code="${orderName}" />
						<c:if test="${order == cOrder}">
							<c:choose>
								<c:when test="${isAscing}"><i class="icon-caret-up"></i></c:when>
								<c:otherwise><i class="icon-caret-down"></i></c:otherwise>
							</c:choose>
						</c:if>
					</a>
				</th>
			</c:forEach>
			<th></th>
		</tr>
		<c:set var="counter" value="${paginator.getCurrentCounter()}" />
		<c:forEach var="data" items="${paginator.getCurrentItems()}">
		<tr>
			<td class="table-nr">${counter}</td>
			<c:forEach var="order" items="${orders}">
				<c:choose>
					<c:when test="${order == 'carwashtype.name'}">
						<td>${data.name}</td>
					</c:when>
					<c:when test="${order == 'carwashtype.cost'}">
						<td>${data.cost}</td>
					</c:when>
					<c:when test="${order == 'carwashtype.loyalitypoints'}">
						<td>${data.loyalityPoints}</td>
					</c:when>
				</c:choose>
			</c:forEach>
			<td>
				<div class="table-options">
					<a href="<c:url value="/admin/carwashtype/edit/${data.id}" />" class="btn btn-mini" data-toggle="tooltip" data-original-title="<spring:message code="label.edit" />"><span class="icon-edit"></span></a>
					<a href="<c:url value="/admin/carwashtype/delete/${data.id}" />" class="btn btn-danger btn-mini btn-confirm" data-toggle="tooltip" data-original-title="<spring:message code="label.delete" />"><span class="icon-white icon-trash"></span></a>
				</div>
			</td>
		</tr>
		<c:set var="counter" value="${counter+1}"/>
		</c:forEach>
	</table>
	<tiles:insertTemplate template="/WEB-INF/views/paginator.jsp">
		<tiles:putAttribute name="paginator" value="${paginator}" />
	</tiles:insertTemplate>
</fieldset>