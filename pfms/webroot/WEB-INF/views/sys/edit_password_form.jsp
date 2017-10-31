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
.form-panel{width:750px; padding:20px 0;}
.center{text-align:center;}

</style>
<script src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script src="//cdn.bootcss.com/tether/1.3.6/js/tether.min.js"></script>
<script	src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.5/js/bootstrap.min.js"></script>
<script	src="../js/common.js"></script>
<script type="text/javascript">
   var context = "<c:url value="/" />";
</script>
</head>
<body>
<div class="form-panel center">
 <c:if test="${KEY_MESSAGE != null}"><p><c:out value="${KEY_MESSAGE}" /></p></c:if>
 <form class="form-horizontal" role="form" action="update_password" method="POST">
  <div class="form-group">
    <label for="accountId" class="col-sm-2 control-label">ID</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="userId" name="userId" readonly="readonly" value="<c:out value="${KEY_RESULT.userId}" />">
    </div>
  </div>
  <div class="form-group">
    <label for="username" class="col-sm-2 control-label">用户名</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名" value="<c:out value="${KEY_RESULT.userName}"/>" >
    </div>
  </div>
  <div class="form-group">
    <label for="password" class="col-sm-2 control-label">登录密码</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" id="passWord" name="passWord" placeholder="请输入密码" value="" />
    </div>
  </div>
  <div class="form-group">
    <label for="accountDescription" class="col-sm-2 control-label">新密码</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" id="newPassword" name="newPassWord" placeholder="请输入新密码"  value="" />
    </div>
  </div>
  <div class="form-group">
    <label for="accountDescription" class="col-sm-2 control-label">再次输入新密码</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" id="repeatNewPassWord" name="repeatNewPassWord" placeholder="请再次输入新密码"  value="">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="button" class="btn btn-default" onclick="submitForm()">保存</button>&nbsp;<button type="button" class="btn btn-default" onclick="goBack()">取消</button>
    </div>
  </div>
</form></div>
</body>
</html>