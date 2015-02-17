<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

<form:form method="POST" modelAttribute="petrolDeliveryEditForm" class="form-horizontal" id="form-add">
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
		<h3><spring:message code="title.petrol.delivery.edit" /></h3>
	</div>
	<div id="form-add-data">
		<div class="control-group<spring:bind path="deliveryDate"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="deliveryDate"><spring:message code="label.petrol.deliveries.deliveryDate" />:</label>
			<div class="controls">
  			<div id="datetimepicker" class="input-append">
    			<input data-format="dd-MM-yyyy" type="text" name="deliveryDate" value=<fmt:formatDate pattern="dd-MM-yyyy" value="${deliveryDate}" /> />
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
				<span class="help-inline"><form:errors path="deliveryDate" /></span>
			</div>
		</div>
		<div class="control-group<spring:bind path="quantity"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="quantity"><spring:message code="label.petrol.deliveries.quantity" />:</label>
			<div class="controls">
				<form:input path="quantity" placeholder="..." />
				<span class="help-inline"><form:errors path="quantity" /></span>
			</div>
		</div>
		<div class="control-group<spring:bind path="cost"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="cost"><spring:message code="label.petrol.deliveries.cost" />:</label>
			<div class="controls">
				<form:input path="cost" placeholder="..." />
				<span class="help-inline"><form:errors path="cost" /></span>
			</div>
		</div>
		<div class="control-group<spring:bind path="orderDate"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="orderDate"><spring:message code="label.petrol.deliveries.orderDate" />:</label>
			<div class="controls">
  			<div id="datetimepicker2" class="input-append">
    			<input data-format="dd-MM-yyyy" type="text" name="orderDate" value=<fmt:formatDate pattern="dd-MM-yyyy" value="${orderDate}" /> />
    			<span class="add-on">
      				<i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
    			</span>
  			</div>
				<script type="text/javascript">
  					$(function() {
    					$('#datetimepicker2').datetimepicker({
    					 	pickTime: false
    					 });
  					});
				</script>
			<span class="help-inline"><form:errors path="orderDate" /></span>
		</div>
		</div>
		<div class="control-group<spring:bind path="status"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="status"><spring:message code="label.petrol.deliveries.status" />:</label>
			<div class="controls">
				<form:select path="status">
					<form:option value="WAITING" ><spring:message code="label.status.waiting" /></form:option>
					<form:option value="COMPLETED"><spring:message code="label.status.completed" /></form:option>
				</form:select>
				<span class="help-inline"><form:errors path="status" /></span>
			</div>
		</div>
		<div class="control-group<spring:bind path="providerId"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="providers"><spring:message code="label.petrol.provider" />:</label>
			<div class="controls">
				<form:select path="providerId">
					<form:option value="0" label=" - " />
					<form:options items="${providers}" itemValue="id" itemLabel="companyName" />
				</form:select>
				<span class="help-inline"><form:errors path="providerId" /></span>
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