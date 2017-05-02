<%@ page language="java" pageEncoding="UTF-8" import="java.io.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
  response.setHeader("expires","0");
  response.setHeader("cache-control", "no-cache");
  response.setHeader("pragma", "no-cache");
%>
<!doctype html>
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<style>
table tr td{margin:0; padding:0;}
.split-line{height:2px;}
</style>
<script src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
<table border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="400" height="263" valign="top"><c:import url="http://i.tianqi.com/index.php?c=code&id=27" var="weatherHTML" /><%
    String html = (String)pageContext.getAttribute("weatherHTML");
    int pos = html.indexOf("http://www.tianqi.com/static/js/new_rili_ncode27.js");
    //System.out.println("#################################################################pos:"+pos);
    html = html.replaceAll("<script.*?new_rili_code27.js.*?</script>", "#{script_lib_weather}");
    html = html.replaceAll("#\\{script_lib_weather\\}", "<script src=\"js/Calendar.js\" charset=\"utf-8\"></script>");
    out.println(html);
    //new BufferedWriter(new OutputStreamWriter(new FileOutputStream("d:/weather.txt"))).write(html);
%></td>
    <td width="366" height="253" valign="top"><c:import url="bank/debitsummary" /></td>
    <td width="345" height="253" valign="top"><c:import url="bank/debit_detail_curves" /></td>
  </tr>
  <tr>
    <td colspan="3" class="split-line"></td>
  </tr>
  <tr>  
    <td width="400" height="263" valign="top"><c:import url="bank/creditsummary" /></td>
  </tr>
</table>
</body>  
</html>