<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<form:form method="POST" modelAttribute="prizeCategoryAddForm" class="form-horizontal" id="form-add">
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
		<h3><spring:message code="title.petrol.container.add" /></h3>
	</div>
	<div id="form-add-data">
		<div class="control-group<spring:bind path="name"><c:if test="${name.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="name"><spring:message code="label.name" />:</label>
			<div class="controls">
				<form:input path="name" placeholder="..." />
				<span class="help-inline"><form:errors path="name" /></span>
			</div>
		</div>
		<div class="control-group<spring:bind path="minPoints"><c:if test="${minPoints.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="minPoints"><spring:message code="label.prize.points" />:</label>
			<div class="controls">
				<form:input path="minPoints" placeholder="..." />
				<span class="help-inline"><form:errors path="minPoints" /></span>
			</div>
		</div>
		<div class="control-group<spring:bind path="status"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="status"><spring:message code="label.status" />:</label>
			<div class="controls">
				<form:select path="status">
					<form:option value="ACTIVE" ><spring:message code="label.status.active" /></form:option>
					<form:option value="INACTIVE"><spring:message code="label.status.inactive" /></form:option>
				</form:select>
				<span class="help-inline"><form:errors path="status" /></span>
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