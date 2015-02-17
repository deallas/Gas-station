<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<form:form method="POST" modelAttribute="petrolProviderEditForm" class="form-horizontal" id="form-add">
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
		<h3><spring:message code="title.petrol.provider.edit" /></h3>
	</div>
	<div id="form-add-data">
		<div class="control-group<spring:bind path="companyName"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="companyName"><spring:message code="label.petrol.provider.companyname" />:</label>
			<div class="controls">
				<form:input path="companyName" placeholder="..." />
				<span class="help-inline"><form:errors path="companyName" /></span>
			</div>
		</div>
		<div class="control-group<spring:bind path="nip"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="nip"><spring:message code="label.petrol.provider.nip" />:</label>
			<div class="controls">
				<form:input path="nip" placeholder="..." />
				<span class="help-inline"><form:errors path="nip" /></span>
			</div>
		</div>
		<div class="control-group<spring:bind path="regon"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="regon"><spring:message code="label.petrol.provider.regon" />:</label>
			<div class="controls">
				<form:input path="regon" placeholder="..." />
				<span class="help-inline"><form:errors path="regon" /></span>
			</div>
		</div>
		<div class="control-group<spring:bind path="address"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="address"><spring:message code="label.petrol.provider.address" />:</label>
			<div class="controls">
				<form:input path="address" placeholder="..." />
				<span class="help-inline"><form:errors path="address" /></span>
			</div>
		</div>
		<div class="control-group<spring:bind path="phone"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label" for="phone"><spring:message code="label.petrol.provider.phonenumber" />:</label>
			<div class="controls">
				<form:input path="phone" placeholder="..." />
				<span class="help-inline"><form:errors path="phone" /></span>
			</div>
		</div>
		<div class="control-group<spring:bind path="status"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="status"><spring:message code="label.petrol.provider.status" />:</label>
			<div class="controls">
				<form:select path="status">
					<form:option value="INACTIVE" ><spring:message code="label.status.inactive" /></form:option>
					<form:option value="ACTIVE"><spring:message code="label.status.active" /></form:option>
				</form:select>
				<span class="help-inline"><form:errors path="status" /></span>
			</div>
		</div>
		<div class="control-group<spring:bind path="email"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="email"><spring:message code="label.email" />:</label>
			<div class="controls">
				<form:input path="email" placeholder="..." />
				<span class="help-inline"><form:errors path="email" /></span>
			</div>
		</div>
		<div class="control-group<spring:bind path="confirmEmail"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="confirmEmail"><spring:message code="label.confirmemail" />:</label>
			<div class="controls">
				<form:input path="confirmEmail" placeholder="..." />
				<span class="help-inline"><form:errors path="confirmEmail" /></span>
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