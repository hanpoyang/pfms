<%@ page language="java" pageEncoding="utf-8"%><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>个人帮手</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 引入 Bootstrap -->
<link
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<style type="text/css">
body {
	font-family: arial, microsoft yahei;
}
table th{
    width:100px;
    background-color:#EEE;
}
th, td{
    padding:5px;
}
</style>
<script src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script src="//cdn.bootcss.com/tether/1.3.6/js/tether.min.js"></script>
<script	src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.5/js/bootstrap.min.js"></script>
</head>
<body>
<div class="form-panel center">
  <table>
    <tr><th>主题</th><td><c:out value="${KEY_RESULT.schedulerSubject}" /></td></tr>
    <tr><th valign="top" height="100">描述</th><td valign="top" height="100"><pre><c:out value="${KEY_RESULT.schedulerDescrip}" /></pre></td></tr>
    <tr><th>年</th><td><c:out value="${KEY_RESULT.schedulerYear}" /></td></tr>
    <tr><th>月</th><td><c:out value="${KEY_RESULT.schedulerMonth}" /></td></tr>
    <tr><th>日</th><td><c:out value="${KEY_RESULT.schedulerDay}" /></td></tr>
    <tr><th>时</th><td><c:out value="${KEY_RESULT.schedulerHour}" /></td></tr>
    <tr><th>分</th><td><c:out value="${KEY_RESULT.schedulerMinute}" /></td></tr>
    <tr><th>持续时长</th><td><c:out value="${KEY_RESULT.schedulerDuration}" /></td></tr>
  </table>
</div>
</body>
</html>