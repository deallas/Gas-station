<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

<form:form method="POST" modelAttribute="refuelingEditForm" class="form-horizontal" id="form-add">
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
		<h3><spring:message code="title.petrol.refueling.edit" /></h3>
	</div>
	<div id="form-add-data">
		<div class="control-group<spring:bind path="cost"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="cost"><spring:message code="label.petrol.refueling.cost" />:</label>
			<div class="controls">
				<form:input path="cost" id="disabledInput" placeholder="..." />
			<span class="help-inline"><form:errors path="cost" /></span>
			</div>
		</div>
		<div class="control-group<spring:bind path="fuel"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="fuel"><spring:message code="label.petrol.refueling.fuel" />:</label>
			<div class="controls">
				<form:input path="fuel" placeholder="..." />
			<span class="help-inline"><form:errors path="fuel" /></span>
			</div>
		</div>
		<div class="control-group<spring:bind path="refuelingDate"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="refuelingDate"><spring:message code="label.petrol.refueling.refuelingdate" />:</label>
			<div class="controls">
  			<div id="datetimepicker" class="input-append">
    			<input data-format="dd-MM-yyyy" type="text" name="refuelingDate" value=<fmt:formatDate pattern="dd-MM-yyyy" value="${refuelingDate}" /> />
    			<span class="add-on">
      				<i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
    			</span>
  			</div>
  				<c:set var="date" value="${refuelingDate}"/>
				<script type="text/javascript">
  					$(function() {
    					$('#datetimepicker').datetimepicker({
    					 	pickTime: false
    					 });
  					});
				</script>
				<span class="help-inline"><form:errors path="refuelingDate" /></span>
			</div>
		</div>
		<div class="control-group<spring:bind path="containerId"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="containers"><spring:message code="label.petrol.container" />:</label>
			<div class="controls">
				<form:select path="containerId">
					<form:option value="0" label=" - " />
					<form:options items="${containers}" itemValue="id" itemLabel="type" />
				</form:select>
				<span class="help-inline"><form:errors path="containerId" /></span>
			</div>
		</div>
		<div class="control-group<spring:bind path="clientId"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="clients"><spring:message code="label.petrol.client" />:</label>
			<div class="controls">
				<form:select path="clientId">
					<form:option value="0" label=" - " />
					<form:options items="${clients}" itemValue="id" itemLabel="email" />
				</form:select>
				<span class="help-inline"><form:errors path="clientId" /></span>
			</div>
		</div>
		<div class="control-group<spring:bind path="refuelingTypeId"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="refuelingTypes"><spring:message code="label.petrol.refuelingType" />:</label>
			<div class="controls">
				<form:select path="refuelingTypeId">
					<form:option value="0" label=" - " />
					<form:options items="${refuelingTypes}" itemValue="id" itemLabel="name" />
				</form:select>
				<span class="help-inline"><form:errors path="refuelingTypeId" /></span>
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