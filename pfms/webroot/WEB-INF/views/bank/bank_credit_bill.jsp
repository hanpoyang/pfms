<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
.center{text-align:center;}
.txt-page-no{width:30px; height:21px; margin:3px;}
.w220{width:220px}
</style>
<script src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script	src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.5/js/bootstrap.min.js"></script>
<script	src="../js/common.js"></script>
<script type="text/javascript">
   var context = "<c:url value="/" />";
   var pageCount = <c:out value="${KEY_RESULT.pageCount}" />
   function queryDefault(){
       $("input[name='pageNo']").val("1");
       $("form").trigger("submit");
   }
   
   
  $(function(){
       $("a[bind-data-id]").bind("click",function(){
           $("#accountId").val($(this).attr("bind-data-id"));
       });
       $("button[data-dismiss='modal']").bind("click",function(){
           console.log("=========================");
           $("#modal-body").html('<input type="password" placeholder="请输入口令" id="password" class="form-control" name="password" value="" autocomplete="false">');
       });
   });
   
   function queryPassKey(){
      var password = $("#password").val();
      var accountId = $("#accountId").val();
      $.post("<c:url value="/otheraccounts/indeciate" />", 
      {"account_id":accountId, "password":password},
      function(responseText){$("#modal-body").html(responseText)});
   }
</script>
</head>
<body>
	<form method="post" action="creditbill">
	<input type="hidden" name="pageNo" value="<c:out value="${KEY_RESULT.pageNo}" />" />
	<div class="col-lg-6">
		<div class="input-group w220">
			<input type="text" placeholder="请输入描述关键字" class="form-control" name="creditCard" value="<c:out value="${KEY_PARAM.requestObject.creditCard == null ? '' : KEY_PARAM.requestObject.creditCard}" />">
			<span class="input-group-btn">
				<button class="btn btn-default" type="button" onclick="queryDefault()">
					查询
				</button>
			</span>
		</div><!-- /input-group -->
	</div>
	<table class="table table-hover">
	<thead>
	  <tr>
	    <th>信用卡号</th>
	    <th>卡户行</th>
	    <th>RMB</th>
	    <th>最低RMB</th>
	    <th>USD</th>
	    <th>最低USD</th>
	    <th>账单日</th>
	    <th>还款日</th>
	    <th>客服电话</th>
	    <th>是否已请</th>
	    <th>操作</th>
	  </tr>
	</thead>
	<tbody>
	  <c:forEach var="row" items="${KEY_RESULT.dataList}"><tr>
	        <td><c:out value="${row.creditCard}" /></td>
	        <td><c:out value="${row.bankName}" /></td>
	        <td><fmt:formatNumber pattern="￥.00" value="${row.cnyImburseAmount}" type="currency"/></td>
	        <td><fmt:formatNumber pattern="￥.00" value="${row.cnyleastAmount}" type="currency"/></td>
	        <td><fmt:formatNumber pattern="$.00" value="${row.usdImburseAmount}" type="currency"/></td>
	        <td><fmt:formatNumber pattern="$.00" value="${row.usdleastAmount}" type="currency"/></td>
	        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${row.billDate}" /></td>
	        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${row.imburseDate}" /></td>
	        <td><c:out value="${row.servicePhone}" /></td>
	        <td><c:out value="${row.isClear}" /></td>
	        <td><a href="creditdetail?creditBillId=<c:out value="${row.creditBillId}" />">详情</a>&nbsp;|&nbsp;<a href="clear?creditBillId=<c:out value="${row.creditBillId}" />">结清</a></td>
	  </tr></c:forEach>
	</tbody>
	<tfoot>
	  <tr><td colspan="11" align="right" ><p>共查到数据<c:out value="${KEY_RESULT.recordCount}" />条数据，分<c:out value="${KEY_RESULT.pageCount}" />页, <a href="javascript:void(0)" onclick="turnToPage('1')">|&lt;&lt;</a>&nbsp; <a href="javascript:void(0)" onclick="turnToPage('<c:out value="${KEY_RESULT.pageNo-1}" />')">&lt;&lt;</a>&nbsp;<input type="text" id="txt-page-no" value="<c:out value="${KEY_RESULT.pageNo }" />" class="txt-page-no" /><button class="btn btn-primary btn-xs" onclick="turnToPage($('#txt-page-no').val())">Go</button>&nbsp;<a href="javascript:void(0)" onclick="turnToPage('<c:out value="${KEY_RESULT.pageNo+1}" />')">&gt;&gt;</a>&nbsp;<a href="javascript:void(0)" onclick="turnToPage('<c:out value="${KEY_RESULT.pageCount}" />')">&gt;&gt;|</a></p></td></tr>
	</tfoot>
	</table>
</body>
</html>
