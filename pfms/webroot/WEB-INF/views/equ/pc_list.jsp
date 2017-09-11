<%@ page language="java" pageEncoding="utf-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<link
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<script src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<script>
$(function(){
    $.get("../rest/pclist",function(jsonData){
        if(jsonData.flag == "success"){
            $("#content-table tbody tr").remove();
            var html = []
            $(jsonData.data).each(function(index, item){
              html.push("<tr><td>"+item.equDesc+"</td><td>"+item.equIp+"</td><td>"+item.equMac+"</td><td>off</td><td><button onclick='powerOn(\""+item.equMac+"\",\""+item.equIp+"\")'>on</button><button onclick='powerOff(\""+item.equMac+"\",\""+item.equIp+"\")'>off</button></td></tr>");
            });
            $("#content-table tbody").append(html.join(''));
            initListen();
        }
    },"json");
});

function powerOn(mac,ip){
    $.post("../rest/pc/poweron",{'equMac':mac, 'equIp':ip},function(jsonData){
        if(jsonData.flag =='success') {
            alert('主机['+ip+']正在启动中...');
        }
    },'json');
}

function powerOff(mac,ip){
    $.post("../rest/pc/poweroff",{'equIp':ip},function(jsonData){
        if(jsonData.flag =='success') {
            alert('主机['+ip+']正在关闭中...');
        }
    },'json');
}

var websocket = null;
function initListen(){
    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://<%=request.getServerName() %>:<%=request.getServerPort() %>/websocket");
    }
    else {
        alert('当前浏览器 Not support websocket')
    }

    //连接发生错误的回调方法
    websocket.onerror = function () {
        alert("WebSocket连接发生错误");
    };

    //连接成功建立的回调方法
    websocket.onopen = function () {
        
    }

    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        setMessageInnerHTML(event.data);
    }

    //连接关闭的回调方法
    websocket.onclose = function () {
        console.log("WebSocket连接关闭");
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        console.log("..............................close websocket.............");
        closeWebSocket();
    }
}
    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
        console.log(innerHTML);
        $("#content-table tbody tr").each(function(k, v){
            var ip = v.childNodes[1].innerHTML;
            var jsonData = {};
            eval("jsonData = "+innerHTML);
            for(var i = 0; i < jsonData.length; i++) {
	            if(ip == jsonData[i].ip){
	                v.childNodes[3].innerHTML = jsonData[i].status;
	                v.childNodes[3].style.backgroundColor = jsonData[i].status == "on" ? "green" : "red";
	            }
            }
        });
    }

    //关闭WebSocket连接
    function closeWebSocket() {
        websocket.close();
    }

    //发送消息
    function send() {
        var message = document.getElementById('text').value;
        websocket.send(message);
    }
</script>
</head>
<body>
<table id="content-table" class="table table-hover">
<thead>
<tr>
<th>HOST</th>
<th>IP</th>
<th>MAC</th>
<th>STATUS</th>
<th>POWER ON</th>
</tr>
</thead>
<tbody>
</tbody>
</table>
</body>
</html>