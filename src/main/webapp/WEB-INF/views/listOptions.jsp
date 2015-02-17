<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:set var="options" value="${paginator.getOptions()}" />
<form class="form form-inline well" id="options-container-form" method="POST">
	<table id="options-container-main-table">
    	<tbody>
            <tr>
            	<td><spring:message code="label.listOptions.columnNames" /></td>
                <td>
                    <table id="options-container-left-table">
                        <tbody>
                            <tr>
                                <td>
                                    <label for="opt-orders-availables[]"><spring:message code="label.listOptions.availableColumns" /></label>
                                    <br />
                                    <select name="opt-orders-availables[]" multiple="multiple" id="options-container-orders-availables">
   										<c:forEach var="order" items="${options.getDiffOrders()}">
   											<c:set var="orderName" value="label.${order}" />
   											<option value="${order}"><spring:message code="${orderName}" /></option>
   										</c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <button class="btn btn-small" id="b-options-container-left"><i class="icon-caret-left"></i></button><br />
                                    <button class="btn btn-small" id="b-options-container-right"><i class="icon-caret-right"></i></button>
                                </td>
                                <td>
                                    <label for="opt-orders[]"><spring:message code="label.listOptions.chooseColumns" /></label>
                                    <br />
                                    <select name="opt-orders[]" multiple="multiple" id="options-container-orders">
   										<c:forEach var="order" items="${options.getOrders()}">
   											<c:set var="orderName" value="label.${order}" />
   											<option value="${order}"><spring:message code="${orderName}" /></option>
   										</c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <button class="btn btn-mini" id="b-options-container-up"><i class="icon-caret-up"></i></button><br />
                                    <button class="btn btn-mini" id="b-options-container-down"><i class="icon-caret-down"></i></button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </td>
                <td>
                    <table id="options-container-right-table">
                    	<tr>
                            <td>
                                <label for="opt-order"><spring:message code="label.listOptions.sorting" />:</label>
                            </td>
                            <td>
                                <select name="opt-order" class="input-medium">
  									<c:forEach var="order" items="${options.getOrders()}">
  										<c:set var="orderName" value="label.${order}" />
  										<option value="${order}"<c:if test="${options.getOrder() == order}"> selected="selected"</c:if>>
  											<spring:message code="${orderName}" />
  										</option>
  									</c:forEach>                                                                            
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label for="opt-ascing"><spring:message code="label.listOptions.sortType" />:</label>
                            </td>
                            <td>
                                <select name="opt-ascing" class="input-medium">
                                    <option value="1"<c:if test="${options.isAscing()}"> selected="selected"</c:if>><spring:message code="label.listOptions.growing" /></option>
                                    <option value="0"<c:if test="${!options.isAscing()}"> selected="selected"</c:if>><spring:message code="label.listOptions.decreasing" /></option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label for="opt-items"><spring:message code="label.listOptions.numberOfRecords" /></label>
                            </td>
                            <td>
                            	<select name="opt-items" class="input-mini">
									<c:forEach var="items" items="${options.getAvailableItems()}">
 										<option value="${items}"<c:if test="${options.getItems() == items}"> selected="selected"</c:if>>${items}</option>
 									</c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr id="b_options_container_left_table">
                            <td>
                                <button type="submit" class="btn btn-inverse btn-mini" name="submit" id="b-options-container-save"><i class="icon-save"></i> <spring:message code="button.save" /></button>
                            </td>
                            <td>
                                <button type="submit" class="btn btn-danger btn-mini" name="reset" id="b-options-container-reset"><i class="icon-off"></i> <spring:message code="button.reset" /></button>
                            </td>
                        </tr>
                    </table>
                </td>
          </tr>
        </tbody>
    </table>
</form>