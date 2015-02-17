<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script type="text/javascript">
	//<![CDATA[
	$(function() {
	    $(".table-options a").tooltip(); 
	});    
	//]]>
</script>

<div id="modal-from-dom" class="modal hide fade">
    <div class="modal-header">
        <a href="#" class="close">&times;</a>
        <h3><spring:message code="label.confirm.head" /></h3>
    </div>
    <div class="modal-body"><spring:message code="label.confirm.body" /></div>
    <div class="modal-footer">
        <a href="#" class="btn btn-yes btn-danger"><spring:message code="label.confirm.accept" /></a>
        <a href="#" class="btn btn-no secondary"><spring:message code="label.confirm.cancel" /></a>
    </div>
</div>