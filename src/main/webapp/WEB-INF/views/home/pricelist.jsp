<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="container-fluid">
		<div class="row-fluid">
		<div class="span8">
			<fieldset class="table_container">
			    <legend>
			        <spring:message code="label.pricelist.carwash.header" />
			    </legend>
				<table class="table table-bordered table-hover table-standard-formed">
					<tr>
						<th style="width:40%;"><a href="#"><spring:message code="label.pricelist.name" /></a></th>
						<th style="width:10%;"><a href="#"><spring:message code="label.pricelist.price" /></a></th>
						<th style="width:50%;"><a href="#"><spring:message code="label.pricelist.pts" /></a></th>
					</tr>
					<c:forEach var="data" items="${carWashPaginator.getCurrentItems()}">
					<tr>
						<td>${data.name}</td>
						<td>${data.cost}</td>
						<td>${data.loyalityPoints}</td>
					</tr>
					</c:forEach>
				</table>
			</fieldset>
			<fieldset class="table_container">
				<legend>
			        <spring:message code="label.pricelist.refueling.header" />
			    </legend>
				<table class="table table-bordered table-hover table-standard-formed">
					<tr>
						<th style="width:40%;"><a href="#"><spring:message code="label.pricelist.name" /></a></th>
						<th style="width:10%;"><a href="#"><spring:message code="label.pricelist.price" /></a></th>
						<th style="width:50%;"><a href="#"><spring:message code="label.pricelist.pts" /></a></th>
					</tr>
					<c:forEach var="data" items="${refuelingPaginator.getCurrentItems()}">
					<tr>
						<td>${data.name}</td>
						<td>${data.cost}</td>
						<td>${data.loyalityPoints}</td>
					</tr>
					</c:forEach>
				</table>
			</fieldset>
		</div>
		<div class="span4">
			<img src="<c:url value="/static/img/wr_cen.png" />" />
		</div>
	</div>
</div>
