<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="captcha" uri="/WEB-INF/tlds/recaptcha.tld" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:if test="${not empty error}">
	<div class="alert alert-error">
		<button data-dismiss="alert" class="close" type="button">×</button>
		<h4><spring:message code="label.error" /> !</h4>
		<p>
			<spring:message code="${sessionScope[\"SPRING_SECURITY_LAST_EXCEPTION\"].message}" />
		</p>
	</div>
	<div class="alert alert-warning">
		<button data-dismiss="alert" class="close" type="button">×</button>
		<h4><spring:message code="label.warning" /> !</h4>
		<p>
		<spring:message code="warning.blocker" arguments="${attempts}, ${maxAttempts}, ${banSeconds}" />
		</p>
	</div>
</c:if>
<c:url value="/j_spring_security_check" var="secureUrl"/>
<form action="${secureUrl}" method="POST" class="form-horizontal" id="form-signin">
	<div id="form-signin-logo" class="icon-lock icon-3x"></div>
	<div class="well">
		<h3><spring:message code="label.login" /></h3>
	</div>
	<div id="form-signin-data">
		<div class="control-group">
			<label class="control-label required" for="username_or_email"><spring:message code="label.peselOrEmail" />:</label>
			<div class="controls">
				<div class="input-append">
					<input type="text" name="j_username" placeholder="..." autocomplete="off" />
					<span class="add-on"><i class="icon-envelope"></i></span>
				</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label required" for="username_or_email"><spring:message code="label.password" />:</label>
			<div class="controls">
				<div class="input-append">
					<input type="password" name="j_password" placeholder="..." />
					<span class="add-on"><i class="icon-key"></i></span>
				</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label required"  for="j_remember"><spring:message code="label.remember_me" />:</label>
			<div class="controls">
				<input id="j_remember" name="_spring_security_remember_me" type="checkbox" />
			</div>
		</div>
		<c:if test="${recaptcha==true}">
			<div id="recaptcha-container">
					<captcha:recaptcha />
			</div>
		</c:if>
	</div>
	<div class="form-actions" id="login-buttons">
		<input class="btn btn-primary" name="submit" type="submit" value="<spring:message code="button.login" />" />	
		<input class="btn" name="reset" type="reset" value="<spring:message code="button.reset" />" />	
	</div>
</form>
