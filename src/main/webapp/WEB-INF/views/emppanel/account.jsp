<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


	<div id="form-add">
		<div class="well form-title">
			<div id="form-add-logo" class="icon-user icon-3x"></div>
			<h3><spring:message code="label.employee.account" /></h3>
		</div>
		<div id="form-add-data">
			<table class="table table-bordered table-striped table-hover">
	  		
			  <tbody>
			    <tr>
			      <td style="width:25%;"><spring:message code="label.name" /></td>
			      <td>${employee.name}</td>
			    </tr>
			    <tr>
			      <td style="width:25%;"><spring:message code="label.surname" /></td>
			      <td>${employee.surname}</td>
			    </tr>
			    <tr>
			      <td style="width:25%;"><spring:message code="label.gender" /></td>
			      <td><c:if test="${not empty employee.gender}"> 
					<spring:message code="label.gender.${employee.gender}" />
				</c:if></td>
			    </tr>
			    <tr>
			      <td style="width:25%;"><spring:message code="label.pesel" /></td>
			      <td>${employee.pesel}</td>
			    </tr>
			    <tr>
			      <td style="width:25%;"><spring:message code="label.email" /></td>
			      <td>${employee.email}</td>
			    </tr>
			    <tr>
			      <td style="width:25%;"><spring:message code="label.phone" /></td>
			      <td>${employee.phoneNumber}</td>
			    </tr>
			    <tr>
			      <td style="width:25%;"><spring:message code="label.address" /></td>
			      <td>${employee.address}</td>
			    </tr>
			    <tr>
			      <td style="width:25%;"><spring:message code="label.roles" /></td>
			      <td>
				      	<c:choose>
							<c:when test="${employee.role.name == 'GOD'}">
								<spring:message code="label.role.god" />
							</c:when>
							<c:when test="${employee.role.name == 'SEMP'}">
								<spring:message code="label.role.semp" />
							</c:when>
							<c:when test="${employee.role.name == 'EMP'}">
								<spring:message code="label.role.emp" />
							</c:when>
							<c:otherwise>
								${employee.role.name}
							</c:otherwise>
						</c:choose>
					</td>
			    </tr>
			     <tr>
			      <td style="width:25%;"><spring:message code="label.status" /></td>
			      <td>
				      	<c:choose>
					<c:when test="${employee.status == 'BANNED'}">
						<div class="label label-inverse"><spring:message code="label.status.banned" /></div>
					</c:when>
				 	<c:when test="${employee.status == 'ACTIVE'}">
						<div class="label label-success"><spring:message code="label.status.active" /></div>
					</c:when>
					<c:when test="${employee.status == 'INACTIVE'}">
						<div class="label label-important"><spring:message code="label.status.inactive" /></div>
					</c:when>
				</c:choose>
					</td>
			    </tr>
			  </tbody>
			</table>
		</div>
	</div>