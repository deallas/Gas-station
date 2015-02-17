<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/WEB-INF/views/confirm.jsp" %>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span8">
			<fieldset class="table_container">
					<legend>
			        	<spring:message code="label.loyalty" />
			    	</legend>
					<table class="table table-bordered table-hover table-standard-formed">
						<tr>
							<th><a href="#"><spring:message code="label.name" /></a></th>
							<th><a href="#"><spring:message code="label.prize.points" /></a></th>
							<th></th>
						</tr>
						<c:forEach var="data" items="${prizeCategoryPaginator.getCurrentItems()}">
						<c:if test="${data.status == 'ACTIVE'}">
						<tr>
							<td>${data.name}</td>
							<td>${data.minPoints}</td>
							<td>
							<div class="table-options">
								<a href="<c:url value="/loyalty/choose/${data.id}" />" class="btn btn-danger btn-mini btn-confirm" data-toggle="tooltip" data-original-title="Wybierz nagrodę"><span class="icon-arrow-right"></span></a>
							</div>
							</td>
						</tr>
						</c:if>
						</c:forEach>
					</table>
			</fieldset>
		</div>
		<div class="span4">
			<img src="<c:url value="/static/img/wr_loy.png" />" />
		</div>
	</div>
</div>