<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<form:form method="POST" modelAttribute="employeeAddForm" class="form-horizontal" id="form-add">
	<c:set var="formErrors"><form:errors /></c:set> 
    <c:if test="${not empty formErrors}"> 
    	<div class="alert alert-error"> 
        	<button class="close" data-dismiss="alert">×</button> 
        	<h4 class="alert-heading"><spring:message code="label.error" />!</h4> 
        	${formErrors} 
    	</div> 
	</c:if>
	<div class="well form-title">
		<div id="form-add-logo" class="icon-plus icon-3x"></div>
		<h3><spring:message code="label.employee.add" /></h3>
	</div>
	<div id="form-add-data">
		<div class="control-group<spring:bind path="name"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="name"><spring:message code="label.name" />:</label>
			<div class="controls">
				<form:input path="name" placeholder="..." />
				<span class="help-inline"><form:errors path="name" /></span>
			</div>
		</div>
		<div class="control-group<spring:bind path="surname"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="surname"><spring:message code="label.surname" />:</label>
			<div class="controls">
				<form:input path="surname" placeholder="..." />
				<span class="help-inline"><form:errors path="surname" /></span>
			</div>
		</div>
		<div class="control-group<spring:bind path="gender"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label" for="gender"><spring:message code="label.gender" />:</label>
			<div class="controls">
				<form:select path="gender">
					<form:option value="UNKNOWN" label=" - " />
					<form:option value="MALE" ><spring:message code="label.gender.MALE" /></form:option>
					<form:option value="FEMALE"><spring:message code="label.gender.FEMALE" /></form:option>
				</form:select>
				<span class="help-inline"><form:errors path="gender" /></span>
			</div>
		</div>
		<div class="control-group<spring:bind path="pesel"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="pesel"><spring:message code="label.pesel" />:</label>
			<div class="controls">
				<form:input path="pesel" placeholder="..." />
				<span class="help-inline"><form:errors path="pesel" /></span>
			</div>
		</div>
		<div class="control-group<spring:bind path="nip"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label" for="nip"><spring:message code="label.nip" />:</label>
			<div class="controls">
				<form:input path="nip" placeholder="..." />
				<span class="help-inline"><form:errors path="nip" /></span>
			</div>
		</div>
		<div class="control-group<spring:bind path="phone"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label" for="phone"><spring:message code="label.phone" />:</label>
			<div class="controls">
				<form:input path="phone" placeholder="..." />
				<span class="help-inline"><form:errors path="phone" /></span>
			</div>
		</div>
		<div class="control-group<spring:bind path="address"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label" for="address"><spring:message code="label.address" />:</label>
			<div class="controls">
				<form:input path="address" placeholder="..." />
				<span class="help-inline"><form:errors path="address" /></span>
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
		<div class="control-group<spring:bind path="password"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="password"><spring:message code="label.password" />:</label>
			<div class="controls">
				<form:password path="password" placeholder="..." />
				<a href="#" class="btn" id="passwordGenerator"><spring:message code="label.password.generator" /></a>
				<span class="help-inline"><form:errors path="password" /></span>
			</div>
		</div>
		<div class="control-group<spring:bind path="confirmPassword"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="confirmPassword"><spring:message code="label.confirmpassword" />:</label>
			<div class="controls">
				<form:password path="confirmPassword" placeholder="..." />
				<span class="help-inline"><form:errors path="confirmPassword" /></span>
			</div>
		</div>
		<div class="control-group<spring:bind path="roleId"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="roles"><spring:message code="label.roles" />:</label>
			<div class="controls">
				<form:select path="roleId">
					<form:option value="0" label=" - " />
					<form:options items="${roles}" itemValue="id" itemLabel="name" />
				</form:select>
				<span class="help-inline"><form:errors path="roleId" /></span>
			</div>
		</div>
		<div class="control-group<spring:bind path="status"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="status"><spring:message code="label.status" />:</label>
			<div class="controls">
				<form:select path="status">
					<form:option value="-" label=" - " />
					<form:option value="INACTIVE" ><spring:message code="label.status.inactive" /></form:option>
					<form:option value="ACTIVE"><spring:message code="label.status.active" /></form:option>
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