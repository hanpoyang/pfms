<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

.form-panel {
	width: 650px;
	margin: 50px auto 0 auto;
	padding: 100px;
}

.form-panel ul {
	list-style: none;
	margin: 0;
	padding: 0;
	border-radius: 5px;
	border: 1px solid #BBB;
	box-shadow: 0 0 10px rgba(0, 0, 0, .3);
}

.form-panel ul li {
	list-style: none;
	margin: 0;
	padding: 0;
}

.form-panel ul li:first-child {
	background-color:#BBB;
}

.form-horizontal {
	padding-right: 30px;
}
.form-group{
    margin-left:10px;
}
.form-title {
	font-size: 25px;
	font-weight: bold;
	display: block;
	padding: 40px 0 40px 5px;
}

.btn-01 {
	background-color: #EEE;
}
.center{text-align:center;}
p{padding:5px 0;}
p.error-msg{color:red; text-indent:1em;}
</style>
<script src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script	src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.5/js/bootstrap.min.js"></script>
<script type="text/javascript">

  
    function setCookie(cookieName, cookieValue){
        $.cookie(cookieName, cookieValue, { expires: 7 }); 
    }
    
    function getCookie(cookieName){
        return $.cookie(cookieName); 
    }
    
   <c:if test="${KEY_RESULT != null}">setCookie("username","${KEY_RESULT.userName}");</c:if>
   <c:if test="${KEY_MESSAGE != null && KEY_RESULT == null}">setCookie("username",null);</c:if>
   function init(){
       var username = getCookie("username");
       if(username != null || username != "null"){
           $("#userName").val(username);
       }
   }
   $(function(){
       init();
   });
   
</script>
<!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
<!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
<!--[if lt IE 9]>
         <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
         <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
      <![endif]-->
</head>
<body>
	<div class="form-panel">
		<ul>
			<li><p class="form-title">家庭理财系统</p></li>
			<li>
				<form class="form-horizontal" role="form" method="post">
				    <c:if test="${KEY_MESSAGE != null}"><p class="error-msg"><c:out value="${KEY_MESSAGE}" /></p></c:if>
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">&nbsp;</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="userName" name="userName"
								placeholder="请输入用户名" value="<c:out value="${KEY_RESULT == null ? '' : KEY_RESULT.userName}" />">
						</div>
					</div>
					<div class="form-group">
						<label for="lastname" class="col-sm-2 control-label">&nbsp;</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="passWord" name="passWord"
								placeholder="请输入密码">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<div class="checkbox">
								<label> <input type="checkbox" name="remeberMe" value="1">请记住我
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn-01 form-control">登录</button>
						</div>
					</div>
				</form>
			</li>
		</ul>
        <p class="center">&copy;2016-12 TOM </p>
	</div>
</body>
</html>
