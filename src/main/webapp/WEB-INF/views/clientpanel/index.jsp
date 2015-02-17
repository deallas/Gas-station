<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<h1>
	Hello world!  
</h1>

<a href=<c:url value="/login"/>>Login</a>

<P>  The time on the server is ${serverTime}. </P>
