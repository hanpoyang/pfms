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
.w220{width:500px}
.input-group-text{float:left}
.float-right{float:right;}
.w500{width:40%}
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
   
  $(function(){
       $.getJSON(context+"bank/banks",function(responseObject){
           var flag = responseObject.status;
           var data = responseObject.data;
           if(flag == "1") {
               for(var i = 0; i < data.length; i++){
                   $("#bankCode").append("<option value='"+data[i]["key"]+"'>"+data[i]["value"]+"</option>");
               }
               console.log($("#bankCode").attr("value"));
               $("#bankCode option[value='"+$("#bankCode").attr("value")+"']").attr("selected", "selected");
           }
       });
       
   });
</script>
</head>
<body>
	<form method="post" action="<c:url value='/bank/debitdetails' />">
	<input type="hidden" name="pageNo" value="<c:out value="${KEY_RESULT.pageNo}" />" />
	<div class="col-lg-6">
	  <div class="input-group">
		 <div class="col-md-2" style="padding:0"><input type="text" placeholder="请输入账户" style="border-right:0;" class="form-control" name="accountName" value="<c:out value="${KEY_PARAM.requestObject.accountName == null ? '' : KEY_PARAM.requestObject.accountName}" />"></div>
		 <div class="col-md-2" style="padding:0"><input type="text" placeholder="请输入卡号" style="border-right:0;" class="form-control" name="cardNumber" value="<c:out value="${KEY_PARAM.requestObject.cardNumber == null ? '' : KEY_PARAM.requestObject.cardNumber}" />"></div>
		 <div class="col-md-2" style="padding:0"><select class="form-control" id="bankCode" style="border-right:0; margin-left:-1px;" name="bankCode" value="<c:out value="${KEY_PARAM.requestObject.bankCode == null ? '' : KEY_PARAM.requestObject.bankCode}" />">
	            <option value="">-请选择-</option>
	        </select></div>
		 <div class="col-md-2" style="padding:0">
			<span class="input-group-btn">
				<button class="btn btn-default" type="button" onclick="queryDefault()">
					查询
				</button>
			</span>
		  </div>
	  </div><!-- /input-group -->
	</div>
	</form>
	<span class="float-right"><button class="btn btn-default" type="button" onclick="window.location.href='<c:url value="/bank/debitimport" />';">
					导入
				</button></span>
	</form>
	<table class="table table-hover">
	<thead>
	  <tr>
	    <th>银行名</th>
	    <th>卡号</th>
	    <th>账户名</th>
	    <th>交易日期</th>
	    <th>交易时间</th>
	    <th>交易类型</th>
	    <th>收入</th>
	    <th>支出</th>
	    <th>余额</th>
	    <th>交易说明</th>
	  </tr>
	</thead>
	<tbody>
	  <c:forEach var="row" items="${KEY_RESULT.dataList}"><tr>
	        <td><c:out value="${row.bankName}" /></td>
	        <td><c:out value="${row.cardNumber}" /></td>
	        <td title="<c:out value="${row.accountName}" />"><script>var accountName = "<c:out value="${row.accountName}" />";document.write(accountName.substring(0, 5));</script></td>
	        <td title="<c:out value="${row.purchaseDate}" />"><c:out value="${row.purchaseDate}" /></td>
	        <td title="<c:out value="${row.purchaseTime}" />"><c:out value="${row.purchaseTime}" /></td>
	        <td title="<c:out value="${row.purchaseType}" />"><c:out value="${row.purchaseType}" /></td>
	        <td><c:out value="${row.income}" /></td>
	        <td><c:out value="${row.outcome}" /></td>
	        <td><fmt:formatNumber type="currency" value="${row.balance}" pattern="0.00" /></td>
	        <td title="<c:out value="${row.purchaseRemark}" />"><script>var purchaseRemark = "<c:out value="${row.purchaseRemark}" />";document.write(purchaseRemark.substring(0, 20));</script></td>
	  </tr></c:forEach>
	</tbody>
	<tfoot>
	  <tr><td colspan="16" align="right" ><p>共查到数据<c:out value="${KEY_RESULT.recordCount}" />条数据，分<c:out value="${KEY_RESULT.pageCount}" />页, <a href="javascript:void(0)" onclick="turnToPage('1')">|&lt;&lt;</a>&nbsp; <a href="javascript:void(0)" onclick="turnToPage('<c:out value="${KEY_RESULT.pageNo-1}" />')">&lt;&lt;</a>&nbsp;<input type="text" id="txt-page-no" value="<c:out value="${KEY_RESULT.pageNo }" />" class="txt-page-no" /><button class="btn btn-primary btn-xs" onclick="turnToPage($('#txt-page-no').val())">Go</button>&nbsp;<a href="javascript:void(0)" onclick="turnToPage('<c:out value="${KEY_RESULT.pageNo+1}" />')">&gt;&gt;</a>&nbsp;<a href="javascript:void(0)" onclick="turnToPage('<c:out value="${KEY_RESULT.pageCount}" />')">&gt;&gt;|</a></p></td></tr>
	</tfoot>
	</table>
</body>
</html>
