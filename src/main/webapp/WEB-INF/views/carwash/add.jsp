<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %> 
    
<form:form method="POST" modelAttribute="carWashAddForm" class="form-horizontal" id="form-add">
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
		<h3><spring:message code="label.carwash.add" /></h3>
	</div>
	<div id="form-add-data">
		<div class="control-group<spring:bind path="carWashTypeId"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="carWashTypes"><spring:message code="label.carwashtype.name" />:</label>
			<div class="controls">
				<form:select path="carWashTypeId">
					<form:option value="0" label=" - " />
					<form:options items="${carWashTypes}" itemValue="id" itemLabel="name" />
				</form:select>
				<span class="help-inline"><form:errors path="carWashTypeId" /></span>
			</div>
		</div>
		<div class="control-group<spring:bind path="dateBeginWash"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="dateBeginWash"><spring:message code="label.carwash.datebeginwash" />:</label>
			<div class="controls">
  			<div id="datetimepicker" class="input-append">
    			<input data-format="dd-MM-yyyy" type="text" name="dateBeginWash" />
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
				<span class="help-inline"><form:errors path="dateBeginWash" /></span>
			</div>
		</div>
		<div class="control-group<spring:bind path="dateEndWash"><c:if test="${status.error}"> error</c:if></spring:bind>">
			<label class="control-label required" for="dateEndWash"><spring:message code="label.carwash.dateendwash" />:</label>
			<div class="controls">
  			<div id="datetimepicker2" class="input-append">
    			<input data-format="dd-MM-yyyy" type="text" name="dateEndWash" />
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
				<span class="help-inline"><form:errors path="dateEndWash" /></span>
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
