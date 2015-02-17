<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="countPage" value="${paginator.getPageCount()}" />
<c:set var="currentPage" value="${paginator.getCurrentPageNumber()}" />
<c:if test="${countPage > 1}">
	<c:set var="lengthContextPath" value="${fn:length(pageContext.servletContext.contextPath)}" />
	<c:set var="lengthRequestURI" value="${fn:length(requestURI)}" />
	<c:set var="requestURI" value="${requestScope['javax.servlet.forward.request_uri']}" />
	<c:set var="appRequestURI" value="${fn:substring(requestURI, lengthContextPath, lengthRequestURI)}" />
    <div class="pagination pagination-centered">
        <ul>
        	<c:set var="previousPage" value="${paginator.getPreviousPageNumber()}" />
           	<c:choose>
           		<c:when test="${not empty previousPage}">
           			<c:url value="${appRequestURI}" var="previousPageLink">
           				<c:param name="pageNumber" value="${previousPage}" />
           				<c:param name="items" value="${param.items}" />
           			</c:url>
           			<li><a href="${previousPageLink}">«</a></li>
           		</c:when>
           		<c:otherwise>
           			<li class="disabled"><a href="#">«</a></li>         		
           		</c:otherwise>
           	</c:choose>
            <c:forEach var="page" items="${paginator.getPages()}">
            	<c:choose>
	                <c:when test="${page == currentPage}">
	                	<c:set var="pageClass" value=" class=\"active\""/>
	                </c:when>
	                <c:otherwise>
	                	<c:set var="pageClass" value=""/>
	                </c:otherwise>
                </c:choose>
                <c:url value="${appRequestURI}" var="pageLink">
         			<c:param name="pageNumber" value="${page}" />
         			<c:param name="items" value="${param.items}" />
         		</c:url>
                <li${pageClass}>
                    <a href="${pageLink}">${page}</a>
                </li>
            </c:forEach>
            <c:set var="nextPage" value="${paginator.getNextPageNumber()}" />
           	<c:choose>
           		<c:when test="${not empty nextPage}">
         		    <c:url value="${appRequestURI}" var="nextPageLink">
           				<c:param name="pageNumber" value="${nextPage}" />
           				<c:param name="items" value="${param.items}" />
           			</c:url>
           			<li><a href="${nextPageLink}">»</a></li>
           		</c:when>
           		<c:otherwise>
					<li class="disabled"><a href="#">»</a></li>            		
           		</c:otherwise>
           	</c:choose>
        </ul>
    </div>
</c:if>