<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form:form method="POST" modelAttribute="petrolContainerMeasurementEditForm" class="form-horizontal" id="form-add">
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
		<h3><spring:message code="title.petrol.containermeasurement.edit" /></h3>
	</div>
	<div id="form-add-data">
		<div class="control-group<spring:bind path="measurementDate"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="measurementDate"><spring:message code="label.petrol.containerlog.dateadded" />:</label>
			<div class="controls">
  			<div id="datetimepicker" class="input-append">
    			<input data-format="dd-MM-yyyy" type="text" name="measurementDate" value=<fmt:formatDate pattern="dd-MM-yyyy" value="${measurementDate}" /> />
    			<span class="add-on">
      				<i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
    			</span>
  			</div>
				<script type="text/javascript">
  					$(function() {
    					$('#datetimepicker').datetimepicker({
    					 	pickTime: false
    					 });
  					});
				</script>
				<span class="help-inline"><form:errors path="measurementDate" /></span>
			</div>
		</div>
		<div class="control-group<spring:bind path="type"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="type"><spring:message code="label.petrol.containerlog.type" />:</label>
			<div class="controls">
				<form:select path="type">
					<form:option value="PRESSURE" ><spring:message code="label.petrol.containerlog.type.pressure" /></form:option>
					<form:option value="PETROL_LEVEL"><spring:message code="label.petrol.containerlog.type.petrollevel" /></form:option>
				</form:select>
				<span class="help-inline"><form:errors path="type" /></span>
			</div>
		</div>
		<div class="control-group<spring:bind path="value"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="value"><spring:message code="label.petrol.containerlog.value" />:</label>
			<div class="controls">
				<form:input path="value" placeholder="..." />
				<span class="help-inline"><form:errors path="value" /></span>
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
		<div class="form-description">
			* <spring:message code="label.form.required-description" />
		</div>
	</div>
	<div class="form-actions">
		<input class="btn btn-primary" name="submit" type="submit" value="<spring:message code="button.send" />" />	
		<input class="btn" name="reset" type="reset" value="<spring:message code="button.reset" />" />	
	</div>
</form:form>