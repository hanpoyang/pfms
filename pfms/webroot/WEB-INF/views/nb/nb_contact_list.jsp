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
   
   function showAbstract(text){
       document.write(text.split("|")[0]);
   }
   
   function invalidContact(contactId){
       $.getJSON("contact_invalid", {'contactId':contactId}, function(data, status){
           if(status === 'success'){
               if(data.flag === 1){
                   showTip('', '数据已提交');
                   setTimeout(()=>queryDefault(), 3000);
               } else {
                   showTip('', data.message);
               }
           }
       });
   }
   
   function showChars(s, n){
       if(s.length > n) {
           document.write(s.substr(0, n) + "...");
       } else {
           document.write(s);
       }
   }
   
   function showTip(title, msg) {
       $("#tip_title").html(title);
       $("#tip_msg").html(msg);
       $("#myModal").modal('toggle');
   }
</script>
</head>
<body>
	<form method="post" action="<c:url value='/nb/querycontacts' />">
	<input type="hidden" name="pageNo" value="<c:out value="${KEY_RESULT.pageNo}" />" />
	<div class="col-lg-6">
	  <div class="input-group">
		 <div class="col-md-10" style="padding:0"><input type="text" placeholder="联系人、英文名称..." title="请输入联系人、英文名称、公司名称、地址、描述" alt="请输入联系人、英文名称、公司名称、地址、描述" style="border-right:0;" class="form-control" name="contactName" value="<c:out value="${KEY_PARAM.requestObject.contactName == null ? '' : KEY_PARAM.requestObject.contactName}" />"></div>
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
	<span class="float-right"><button class="btn btn-default" type="button" onclick="window.location.href='<c:url value="/nb/contact_add_form" />';">
					新增
				</button></span>
	<table class="table table-hover">
	<thead>
	  <tr>
	    <th>姓名</th>
	    <th>英文姓名</th>
	    <th>手机</th>
	    <th>固话</th>
	    <th>地址</th>
	    <th>状态</th>
	    <th>操作</th>
	  </tr>
	</thead>
	<tbody>
	  <c:forEach var="row" items="${KEY_RESULT.dataList}"><tr>
			<td><a href="contact_text?contactId=<c:out value='${row.contactId}' />"><script>showAbstract('<c:out value="${row.contactName}" />');</script></a></td>
			<td><script>showAbstract('<c:out value="${row.contactEnName}" />');</script></td>
			<td><script>showAbstract('<c:out value="${row.contactCellphone}" />');</script></td>
			<td><script>showAbstract('<c:out value="${row.contactFixedphone}" />');</script></td>
			<td alt="<c:out value="${row.contactAddress}" />" title="<c:out value="${row.contactAddress}" />"><script>showChars('<c:out value="${row.contactAddress}" />', 20);</script></td>
			<td><c:out value="${row.contactStatus == 'Y' ? '生效' : '失效'}" /></td>
			<td><a href="contact_edit_form?contactId=<c:out value="${row.contactId}" />">修改</a>&nbsp;|&nbsp;<a href="javascript:void(0)" onclick="invalidContact('<c:out value="${row.contactId}" />')">失效</a></td>
	  </tr></c:forEach>
	</tbody>
	<tfoot>
	  <tr><td colspan="16" align="right" ><p>共查到数据<c:out value="${KEY_RESULT.recordCount}" />条数据，分<c:out value="${KEY_RESULT.pageCount}" />页, <a href="javascript:void(0)" onclick="turnToPage('1')">|&lt;&lt;</a>&nbsp; <a href="javascript:void(0)" onclick="turnToPage('<c:out value="${KEY_RESULT.pageNo-1}" />')">&lt;&lt;</a>&nbsp;<input type="text" id="txt-page-no" value="<c:out value="${KEY_RESULT.pageNo }" />" class="txt-page-no" /><button class="btn btn-primary btn-xs" onclick="turnToPage($('#txt-page-no').val())">Go</button>&nbsp;<a href="javascript:void(0)" onclick="turnToPage('<c:out value="${KEY_RESULT.pageNo+1}" />')">&gt;&gt;</a>&nbsp;<a href="javascript:void(0)" onclick="turnToPage('<c:out value="${KEY_RESULT.pageCount}" />')">&gt;&gt;|</a></p></td></tr>
	</tfoot>
	</table>
	<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">系统提示：<span id="tip_title"></span></h4>
            </div>
            <div class="modal-body"><span id="tip_msg"></span></div>
            <div class="modal-footer">
            <!--
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
             -->
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>
