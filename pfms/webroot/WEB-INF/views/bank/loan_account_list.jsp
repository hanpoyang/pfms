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
.center{text-align:center;}
.txt-page-no{width:30px; height:21px; margin:3px;}
.w220{width:380px}
.input-group-text{float:left}
.float-right{float:right;}
</style>
<script src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script src="//cdn.bootcss.com/tether/1.3.6/js/tether.min.js"></script>
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
   
  
</script>
</head>
<body>
	<form method="post" action="<c:url value='/bank/loan' />">
	<input type="hidden" name="pageNo" value="<c:out value="${KEY_RESULT.pageNo}" />" />
	<div class="col-lg-6">
		<div class="input-group w220">
			<input type="text" placeholder="请输入账户" style="width:50%; border-right:0;" class="form-control" name="accountName" value="<c:out value="${KEY_PARAM.requestObject.accountName == null ? '' : KEY_PARAM.requestObject.accountName}" />">
			<input type="text" placeholder="请输入银行关键字" style="width:50%" class="form-control" name="bankName" value="<c:out value="${KEY_PARAM.requestObject.bankName == null ? '' : KEY_PARAM.requestObject.bankName}" />">
			<span class="input-group-btn">
				<button class="btn btn-default" type="button" onclick="queryDefault()">
					查询
				</button>
			</span>
		</div><!-- /input-group -->
	</div>
	</form>
	<span class="float-right"><button class="btn btn-default" type="button" onclick="window.location.href='<c:url value="/bank/loan_add" />';">
					新增
				</button></span>
	</form>
	<table class="table table-hover">
	<thead>
	  <tr>
	    <th>ID</th>
	    <th>卡号</th>
	    <th>户名</th>
	    <th>银行名</th>
	    <th>CVV2</th>
	    <th>有效期</th>
	    <th>状态</th>
	    <th>客服电话</th>
	    <th>操作</th>
	  </tr>
	</thead>
	<tbody>
	  <c:forEach var="row" items="${KEY_RESULT.dataList}"><tr>
	  		<td><c:out value="${row.creditId}" /></td>
	        <td><c:out value="${row.cardNumber}" /></td>
	        <td><c:out value="${row.accountName}" /></a></td>
	        <td><c:out value="${row.bankName}" /></td>
	        <td><c:out value="${row.securityCode}" /></td>
	        <td><c:out value="${row.validDate}" /></td>
	        <td><c:out value="${row.status}" /></td>
	        <td><c:out value="${row.servicePhone}" /></td>
	        <td><c:choose><c:when test="${row.status=='生效'}"><a href="<c:url value='/bank/loan/delete?creditId=${row.creditId}' />" useToken="true" onclick="confirm('确认作废？');return false">作废</a></c:when><c:otherwise>已作废</c:otherwise></c:choose></td>
	  </tr></c:forEach>
	</tbody>
	<tfoot>
	  <tr><td colspan="9" align="right" ><p>共查到数据<c:out value="${KEY_RESULT.recordCount}" />条数据，分<c:out value="${KEY_RESULT.pageCount}" />页, <a href="javascript:void(0)" onclick="turnToPage('1')">|&lt;&lt;</a>&nbsp; <a href="javascript:void(0)" onclick="turnToPage('<c:out value="${KEY_RESULT.pageNo-1}" />')">&lt;&lt;</a>&nbsp;<input type="text" id="txt-page-no" value="<c:out value="${KEY_RESULT.pageNo }" />" class="txt-page-no" /><button class="btn btn-primary btn-xs" onclick="turnToPage($('#txt-page-no').val())">Go</button>&nbsp;<a href="javascript:void(0)" onclick="turnToPage('<c:out value="${KEY_RESULT.pageNo+1}" />')">&gt;&gt;</a>&nbsp;<a href="javascript:void(0)" onclick="turnToPage('<c:out value="${KEY_RESULT.pageCount}" />')">&gt;&gt;|</a></p></td></tr>
	</tfoot>
	</table>
</body>
</html>
