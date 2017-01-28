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

.flow_left{float:left;}
.center{text-align:center;}

</style>
<style type="text/css">  
        .dropdown-submenu {  
            position: relative;  
        }  
        .dropdown-submenu > .dropdown-menu {  
            top: 0;  
            left: 100%;  
            margin-top: -6px;  
            margin-left: -1px;  
            -webkit-border-radius: 0 3px 3px 3px;  
            -moz-border-radius: 0 3px 3px;  
            border-radius: 0 3px 3px 3px;  
        }  
        .dropdown-submenu:hover > .dropdown-menu {  
            display: block;  
        }  
        .dropdown-submenu > a:after {  
            display: block;  
            content: " ";  
            float: right;  
            width: 0;  
            height: 0;  
            border-color: transparent;  
            border-style: solid;  
            border-width: 5px 0 5px 5px;  
            border-left-color: #ccc;  
            margin-top: 5px;  
            margin-right: -10px;  
        }  
        .dropdown-submenu:hover > a:after {  
            border-left-color: #fff;  
        }  
        .dropdown-submenu.pull-left {  
            float: none;  
        }  
        .dropdown-submenu.pull-left > .dropdown-menu {  
            left: -100%;  
            margin-left: 10px;  
            -webkit-border-radius: 3px 0 6px 3px;  
            -moz-border-radius: 3px 0 6px 3px;  
            border-radius: 3px 0 3px 3px;  
        }  
    </style>  
<script src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script	src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.5/js/bootstrap.min.js"></script>
<script type="text/javascript">
    function heartbeats(){
	    $.get("portal",function(s){
	        console.log("keep session alive.....");
	        setTimeout(heartbeats, 10000);
	    });
    }
    heartbeats();
</script>
</head>
<body>
	<div class="container">
        <h1>个人生活帮手</h1>
        <nav class="navbar navbar-inverse">
            <div class="navbar-collapse dropdown">
                <ul class="nav navbar-nav nav-tables">
                    <li class="active"><a href="default.jsp" target="sys_content">首页</a></li>
                    <li class="dropdown"><a href="#" data-toggle="dropdown"  target="sys_content">系统管理<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                          <li><a href="<c:url value="/sys/edit_password" />" target="sys_content">修改密码</a></li>
                          <li><a href="<c:url value="/otheraccounts/accounts" />" target="sys_content">用户管理</a></li>
                          <li><a href="<c:url value="/otheraccounts/addform" />" target="sys_content">角色管理</a></li>
                          <li><a href="<c:url value="/otheraccounts/addform" />" target="sys_content">角色组管理</a></li>
                          <li><a href="<c:url value="/otheraccounts/addform" />" target="sys_content">菜单设置</a></li>
                          <li><a href="<c:url value="/otheraccounts/addform" />" target="sys_content">定时任务设置</a></li>
                        </ul></li>
                    <li class="dropdown"><a href="#" data-toggle="dropdown"  target="sys_content">财务管理<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                          <li class="dropdown-submenu"><a href="#" data-toggle="dropdown"  target="sys_content">银行卡</a><ul class="dropdown-menu">
                                <li><a href="<c:url value="/bank/loan" />"  target="sys_content">贷记卡账户</a></li>
                                <li><a href="<c:url value="/bank/creditbill" />"  target="sys_content">贷记卡账单</a></li>
                                <li><a href="<c:url value="/bank/debitcards" />"  target="sys_content">借记卡账户</a></li>
                                <li><a href="<c:url value="/bank/borrow" />"  target="sys_content">借记卡账户明细</a></li>
                              </ul></li>
                          <li><a href="<c:url value="/otheraccounts/accounts" />" target="sys_content">购物/消费</a></li>
                          <li><a href="<c:url value="/otheraccounts/addform" />" target="sys_content">股票/基金</a></li>
                        </ul></li>
                    <li class="dropdown"><a href="#" data-toggle="dropdown"  target="sys_content">生活资讯<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                          <li><a href="<c:url value="/otheraccounts/accounts" />" target="sys_content">天气预报</a></li>
                          <li><a href="<c:url value="/otheraccounts/accounts" />" target="sys_content">购物/旅游</a></li>
                          <li><a href="<c:url value="/otheraccounts/addform" />" target="sys_content">社会活动</a></li>
                        </ul></li>
                    <li><a href="#">行事历</a></li>
                    <li><a href="#">书签</a></li>
                    <li><a href="#">记事簿</a></li>
                    <li class="dropdown"><a href="#" data-toggle="dropdown"  target="sys_content">账号管理<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                          <li><a href="<c:url value="/otheraccounts/accounts" />" target="sys_content">网络账号</a></li>
                        </ul></li>
                </ul>
            </div>
        </nav>
        <iframe name="sys_content" src="default.jsp" width="100%" height="550px" frameborder="0" framespacing="0" border="0"></iframe>
        <div class="center"><span class="flow_left">当前登录用户：<c:out value="${sessionScope.LOGIN_USER.userName}" />&nbsp;<a href="<c:url value="/signout" />">注销</a></span><p>&copy;Copyright:Tom,2017</p></div>
    </div>
</body>
</html>
