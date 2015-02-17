<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<form:form method="POST" modelAttribute="petrolContainerEditForm" class="form-horizontal" id="form-add">
	<c:set var="formErrors"><form:errors /></c:set> 
    <c:if test="${not empty formErrors}"> 
    	<div class="alert alert-error"> 
        	<button class="close" data-dismiss="alert">×</button> 
        	<h4 class="alert-heading"><spring:message code="label.error" />!</h4> 
        	${formErrors} 
    	</div> 
	</c:if>
	<div class="well form-title">
		<div id="form-add-logo" class="icon-edit icon-3x"></div>
		<h3><spring:message code="label.employee.edit" /></h3>
	</div>
	<div id="form-add-data">
		<div class="control-group<spring:bind path="type"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="type"><spring:message code="label.petrol.container.type" />:</label>
			<div class="controls">
				<form:select path="type">
					<form:option value="E95" ><spring:message code="label.type.E95" /></form:option>
					<form:option value="E98"><spring:message code="label.type.E98" /></form:option>
					<form:option value="ON" ><spring:message code="label.type.ON" /></form:option>
					<form:option value="LPG"><spring:message code="label.type.LPG" /></form:option>
				</form:select>
				<span class="help-inline"><form:errors path="type" /></span>
			</div>
		</div>
		<div class="form-description">
			* <spring:message code="label.form.required-description" />
		</div>
	</div>
	<div class="form-actions">
		<input class="btn btn-primary" name="submit" type="submit" value="<spring:message code="button.send" />" />	
		<input class="btn" name="reset" type="reset" value="<spring:message code="button.reset" />" />	
	</div>
</form:form>