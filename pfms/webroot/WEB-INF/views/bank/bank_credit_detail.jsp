<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<div class="navbar navbr-inverse navbar-static-top"><a href="<c:url value="/bank/creditbill" />" class="button">返回</a></div>
<c:out value="${KEY_RESULT.billContent}" escapeXml="flase" />