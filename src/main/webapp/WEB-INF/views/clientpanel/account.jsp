<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="form-add">
	<div class="well form-title">
		<div id="form-add-logo" class="icon-user icon-3x"></div>
			<h3><spring:message code="label.employee.account" /></h3>
	</div>
		<div id="form-add-data">
			<table class="table table-bordered table-striped table-hover">
				<c:choose>
					<c:when test="${not empty clientPerson}">
						<tbody>
							<tr>
								<td style="width:25%;"><spring:message code="label.name" /></td>
			      				<td>${clientPerson.name}</td>
			    			</tr>
			    			<tr>
			      				<td style="width:25%;"><spring:message code="label.surname" /></td>
			      				<td>${clientPerson.surname}</td>
			    			</tr>
			    			<tr>
			     				<td style="width:25%;"><spring:message code="label.gender" /></td>
			      				<td><c:if test="${not empty clientPerson.gender}"> 
								<spring:message code="label.gender.${clientPerson.gender}" />
								</c:if></td>
			   				</tr>
			   				<tr>
			      				<td style="width:25%;"><spring:message code="label.nip" /></td>
			      				<td>${clientPerson.client.nip}</td>
			    			</tr>
			    			<tr>
			      				<td style="width:25%;"><spring:message code="label.pesel" /></td>
			      				<td>${clientPerson.pesel}</td>
			   			 	</tr>
			   				<tr>
			      				<td style="width:25%;"><spring:message code="label.email" /></td>
			      				<td>${clientPerson.client.email}</td>
			    			</tr>
			    			<tr>
			      				<td style="width:25%;"><spring:message code="label.phone" /></td>
			      				<td>${clientPerson.client.phoneNumber}</td>
			    			</tr>
			    			<tr>
			      				<td style="width:25%;"><spring:message code="label.address" /></td>
			      				<td>${clientPerson.client.address}</td>
			    			</tr>
			     			<tr>
			     				<td style="width:25%;"><spring:message code="label.status" /></td>
			      				<td>
				   				<c:choose>
									<c:when test="${clientPerson.client.status == 'BANNED'}">
										<div class="label label-inverse"><spring:message code="label.status.banned" /></div>
									</c:when>
				 					<c:when test="${clientPerson.client.status == 'ACTIVE'}">
										<div class="label label-success"><spring:message code="label.status.active" /></div>
									</c:when>
									<c:when test="${clientPerson.client.status == 'INACTIVE'}">
										<div class="label label-important"><spring:message code="label.status.inactive" /></div>
									</c:when>
								</c:choose>
								</td>
			    			</tr>
			    			<tr>
			      				<td style="width:25%;"><spring:message code="label.points" /></td>
			      				<td>${clientPerson.client.points}</td>
			    			</tr>
			    			<tr>
			      				<td style="width:25%;"><spring:message code="label.dateAdded" /></td>
			      				<td><fmt:formatDate pattern="dd-MM-yyyy" value="${clientPerson.client.dateAdded}" /></td>
			    			</tr>
			  			</tbody>
			  		</c:when>
					<c:otherwise>
			  			<c:if test="${not empty clientCompany}">
		      				<tbody>
			    				<tr>
			      					<td style="width:25%;"><spring:message code="label.companyName" /></td>
			      					<td>${clientCompany.companyName}</td>
			    				</tr>
			    				<tr>
			      					<td style="width:25%;"><spring:message code="label.nip" /></td>
			      					<td>${clientCompany.client.nip}</td>
			    				</tr>
			    				<tr>
			      					<td style="width:25%;"><spring:message code="label.regon" /></td>
			      					<td>${clientCompany.regon}</td>
			    				</tr>
			    				<tr>
			      					<td style="width:25%;"><spring:message code="label.email" /></td>
			      					<td>${clientCompany.client.email}</td>
			    				</tr>
			    				<tr>
			      					<td style="width:25%;"><spring:message code="label.phone" /></td>
			      					<td>${clientCompany.client.phoneNumber}</td>
			    				</tr>
			    				<tr>
			      					<td style="width:25%;"><spring:message code="label.address" /></td>
			      					<td>${clientCompany.client.address}</td>
			    				</tr>
			     				<tr>
			      					<td style="width:25%;"><spring:message code="label.status" /></td>
			      				<td>
				   				<c:choose>
									<c:when test="${clientCompany.client.status == 'BANNED'}">
										<div class="label label-inverse"><spring:message code="label.status.banned" /></div>
									</c:when>
				 					<c:when test="${clientCompany.client.status == 'ACTIVE'}">
										<div class="label label-success"><spring:message code="label.status.active" /></div>
									</c:when>
									<c:when test="${clientCompany.client.status == 'INACTIVE'}">
										<div class="label label-important"><spring:message code="label.status.inactive" /></div>
									</c:when>
								</c:choose>
								</td>
			    				</tr>
			    				<tr>
			    	  				<td style="width:25%;"><spring:message code="label.points" /></td>
			      					<td>${clientCompany.client.points}</td>
			    				</tr>
			    				<tr>
			      					<td style="width:25%;"><spring:message code="label.dateAdded" /></td>
			      					<td><fmt:formatDate pattern="dd-MM-yyyy" value="${clientCompany.client.dateAdded}" /></td>
			    				</tr>
			  				</tbody>
			  			</c:if>
		      		</c:otherwise>
				</c:choose>
		</table>
	</div>
</div>