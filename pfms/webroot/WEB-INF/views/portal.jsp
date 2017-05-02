<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>TOM监控平台</title>
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
        .dropdown-menu>li>a:focus, .dropdown-menu>li>a:hover{background-color:#DDD;}
    </style>  
<script src="http://cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script	src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.5/js/bootstrap.min.js"></script>
<script type="text/javascript">
    function heartbeats(){
	    $.get("watchsession",function(s){
	        console.log("keep session alive.....");
	        setTimeout(heartbeats, 10000);
	    });
    }
    heartbeats();  
    $(function(){
        $("#sys_content").bind("load",function(){
            var fb = document.getElementById("sys_content").contentWindow.document;
            console.log("_h::::"+fb);
        });
    });
</script>
</head>
<body>
	<div class="container">
        <h1>TOM监控平台</h1>
        <nav class="navbar navbar-inverse">
            <div class="navbar-collapse dropdown" id="navbar">
                <jsp:include page="common/menu.jsp" />
            </div>
        </nav>
        <iframe name="sys_content" src="default.jsp" width="100%" height="550" frameborder="0" framespacing="0" border="0"></iframe>
        <div class="center"><span class="flow_left">当前登录用户：<c:out value="${sessionScope.LOGIN_USER.userName}" />&nbsp;<a href="<c:url value="/signout" />">注销</a></span><p>&copy;Copyright:Tom,2017</p></div>
    </div>
</body>
</html>
