<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<form:form method="POST" modelAttribute="checkoutForm" class="form-horizontal" id="form-add">
	<c:set var="formErrors"><form:errors /></c:set> 
    <c:if test="${not empty formErrors}"> 
    	<div class="alert alert-error"> 
        	<button class="close" data-dismiss="alert">×</button> 
        	<h4 class="alert-heading"><spring:message code="label.error" />!</h4> 
        	${formErrors} 
    	</div> 
	</c:if>
	<div class="well form-title">
		<div id="form-add-logo" class="icon-shopping-cart icon-3x"></div>
		<h3><spring:message code="label.checkout" /></h3>
	</div>
	<div id="form-add-data">
      	<div class="control-group<spring:bind path="id"><c:if test="${status.error}"> error</c:if></spring:bind>">
	      	<label class="control-label required" for="id"><spring:message code="label.clientId" />:</label>
			<div class="controls">
				<form:input path="id" placeholder="..." />
				<span class="help-inline"><form:errors path="id" /></span>
			</div>
		</div>
		<div class="control-group<spring:bind path="refuelingType"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="refuelingType"><spring:message code="label.refuelingType" />:</label>
			<div class="controls">
				<form:select path="refuelingType">
					<form:option value="0" label=" - " />
					<form:options items="${refuelingTypes}" itemValue="id" itemLabel="name" />
				</form:select>
				<span class="help-inline"><form:errors path="refuelingType" /></span>
			</div>
		</div>
		<div class="control-group<spring:bind path="container"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="container"><spring:message code="label.container" />:</label>
			<div class="controls">
				<form:select path="container">
					<form:option value="0" label=" - " />
					<form:options items="${containers}" itemValue="id" itemLabel="id" />
				</form:select>
				<span class="help-inline"><form:errors path="container" /></span>
			</div>
		</div>
	 	<div class="control-group<spring:bind path="fuel"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="fuel"><spring:message code="label.fuel" />:</label>
			<div class="controls">
				<form:input path="fuel" placeholder="..." />
				<span class="help-inline"><form:errors path="fuel" /></span>
			</div>
		</div>
		<div class="control-group<spring:bind path="washType"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="washType"><spring:message code="label.washType" />:</label>
			<div class="controls">
				<form:select path="washType">
					<form:option value="0" label=" - " />
					<form:options items="${washTypes}" itemValue="id" itemLabel="name" />
				</form:select>
				<span class="help-inline"><form:errors path="washType" /></span>
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