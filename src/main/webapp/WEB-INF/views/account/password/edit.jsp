<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<form:form method="POST" modelAttribute="accountPasswordEditForm" class="form-horizontal" id="form-add">
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
		<h3><spring:message code="label.account.editpassword" /></h3>
	</div>
	<div id="form-add-data">
		<div class="control-group<spring:bind path="oldPassword"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for=""><spring:message code="label.oldpassword" />:</label>
			<div class="controls">
				<form:password path="oldPassword" placeholder="..." />
				<span class="help-inline"><form:errors path="oldPassword" /></span>
			</div>
		</div>
		<div class="control-group<spring:bind path="password"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for=""><spring:message code="label.password" />:</label>
			<div class="controls">
				<form:password path="password" placeholder="..." />
				<a href="#" id="passwordGenerator" class="btn"><spring:message code="label.password.generator" /></a>
				<span class="help-inline"><form:errors path="password" /></span>
			</div>
		</div>
		<div class="control-group<spring:bind path="confirmPassword"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for=""><spring:message code="label.confirmpassword" />:</label>
			<div class="controls">
				<form:password path="confirmPassword" placeholder="..." />
				<span class="help-inline"><form:errors path="confirmPassword" /></span>
			</div>
		</div>		
	</div>
	<div class="form-actions">
		<input class="btn btn-primary" name="submit" type="submit" value="<spring:message code="button.send" />" />	
		<input class="btn" name="reset" type="reset" value="<spring:message code="button.reset" />" />	
	</div>
	
</form:form>