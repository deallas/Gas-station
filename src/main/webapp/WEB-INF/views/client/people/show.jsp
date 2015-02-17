<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<div id="form-add">
		<div class="well form-title">
			<div id="form-add-logo" class="icon-user icon-3x"></div>
			<h3><spring:message code="label.client.show" /></h3>
		</div>
		<div id="form-add-data">
			<table class="table table-bordered table-striped table-hover">
			  <tbody>
		  		<tr>
		      		<td style="width:25%;"><spring:message code="label.name" /></td>
		      		<td>${cli.name}</td>
		    	</tr>
				<tr>
			      <td style="width:25%;"><spring:message code="label.surname" /></td>
			      <td>${cli.surname}</td>
			    </tr>
			    <tr>
			      <td style="width:25%;"><spring:message code="label.gender" /></td>
			      <td><c:if test="${not empty cli.gender}"> 
					<spring:message code="label.gender.${cli.gender}" />
				</c:if></td>
			    </tr>
			    <tr>
			      <td style="width:25%;"><spring:message code="label.pesel" /></td>
			      <td>${cli.pesel}</td>
			    </tr>
			    <tr>
			      <td style="width:25%;"><spring:message code="label.email" /></td>
			      <td>${cli.client.email}</td>
			    </tr>
			    <tr>
			      <td style="width:25%;"><spring:message code="label.phone" /></td>
			      <td>${cli.client.phoneNumber}</td>
			    </tr>
			    <tr>
			      <td style="width:25%;"><spring:message code="label.address" /></td>
			      <td>${cli.client.address}</td>
			    </tr>
			     <tr>
			      <td style="width:25%;"><spring:message code="label.status" /></td>
			      <td>
				      	<c:choose>
					<c:when test="${cli.client.status == 'BANNED'}">
						<div class="label label-inverse"><spring:message code="label.status.banned" /></div>
					</c:when>
				 	<c:when test="${cli.client.status == 'ACTIVE'}">
						<div class="label label-success"><spring:message code="label.status.active" /></div>
					</c:when>
					<c:when test="${cli.client.status == 'INACTIVE'}">
						<div class="label label-important"><spring:message code="label.status.inactive" /></div>
					</c:when>
				</c:choose>
					</td>
			    </tr>
			  </tbody>
			</table>
		</div>
	</div>