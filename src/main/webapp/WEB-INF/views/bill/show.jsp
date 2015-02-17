<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/WEB-INF/views/confirm.jsp" %>

<fieldset class="table_container">
    <legend>
        <spring:message code="label.menu.bill.show.header" />
    </legend>
	<table class="table table-striped table-hover table-standard-formed">
		<tr>
			<th class="table-nr">#</th>
			<th><a href="#"><spring:message code="label.bill.product" /></a></th>
			<th><a href="#"><spring:message code="label.bill.petrol" /></a></th>
			<th><a href="#"><spring:message code="label.bill.carwash" /></a></th>
		</tr>
		<c:set var="counter" value="1"/>
		<c:forEach var="data" items="${be}">
		<tr>
			<td class="table-nr">${counter}</td>
			<td>${data.product.name}</td>
			<td>${data.refueling.petrolContainer.type}</td>
			<td>${data.carWash.carWashType.name}</td>
		</tr>
		<c:set var="counter" value="${counter+1}"/>
		</c:forEach>
	</table>
</fieldset>