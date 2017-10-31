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
.form-panel{width:50%; padding:20px 0;}
.center{text-align:center;}

</style>
<script src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<script src="//cdn.bootcss.com/tether/1.3.6/js/tether.min.js"></script>
<script	src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.5/js/bootstrap.min.js"></script>
<script type="text/javascript">
   var context = "<c:url value="/" />";
   $(function(){
       $.getJSON(context+"bank/banks",function(responseObject){
           var flag = responseObject.status;
           var data = responseObject.data;
           if(flag == "1") {
               for(var i = 0; i < data.length; i++){
                   $("#bankCode").append("<option value='"+data[i]["key"]+"'>"+data[i]["value"]+"</option>");
               }
               $("#bankCode option[value='"+$("#bankCode").attr("value")+"']").attr("selected", "selected");
           }
       });
       $("#bankCode").bind("change", loadDebits);
   });
   
   function loadDebits(){
        $.getJSON(context+"bank/querydebits?bankcode="+$("#bankCode option:selected").val(),function(responseObject){
           var flag = responseObject.status;
           var data = responseObject.data;
           if(flag == "1") {
               for(var i = 0; i < data.length; i++){
                   $("#debitCardId").append("<option value='"+data[i]["key"]+"'>"+data[i]["value"]+"</option>");
               }
               $("#debitCardId option[value='"+$("#cardNumber").attr("value")+"']").attr("selected", "selected");
           }
       });
   }
   
   function uploadImport(){
      var file = document.getElementById("postForm");
      var bankCode = $("#bankCode option:selected").val();
      var formData = new FormData(file);
      $.ajax({  
		     url : context + "common/upload",  
		     type : "POST",  
		     processData: false,
    		 contentType: false,
    		 cache:false,
		     data :formData,
		     success : function(data) {  
		        console.log(data);
		        if(bankCode == 'CMB')parseCmbHtml(data);
		        if(bankCode == 'PINGAN')parsePingAnHtml(data);
		        if(bankCode == 'ABC')parseAbcHtml(data);
		     },  
		     error : function(data, textStatus, errorThrown) {  
		          console.log(data);
		          console.log("errorThrown:"+errorThrown);
		          $("#txt").text(data.responseText);
		          $("#error").text(errorThrown);
		           
		     }  
		});
	
   }
   
   function parseCmbHtml(str){
       var lines = [];
       lines = str.split(/\r\n/g);
       console.log(lines.length);
       var debitCardId = $("#debitCardId option:selected").val();
        $("#tableRow tr").remove();
       for(var i = 1; i < lines.length; i++) {
           $("#tableRow").append("<tr></tr>");
           var line = lines[i];
           var cells = line.split(",");
           $("#tableRow tr:last").append("<td>"+(i)+"</td>");
           $("#tableRow tr:last").append("<td>"+debitCardId+"</td>");
           $("#tableRow tr:last").append("<td>"+cells[0]+"</td>");
           $("#tableRow tr:last").append("<td>"+cells[1]+"</td>");
           $("#tableRow tr:last").append("<td>"+cells[5]+"</td>");
           $("#tableRow tr:last").append("<td>"+cells[6]+"</td>");
           $("#tableRow tr:last").append("<td>"+cells[2]+"</td>");
           $("#tableRow tr:last").append("<td>"+cells[3]+"</td>");
           $("#tableRow tr:last").append("<td>"+cells[4]+"</td>");
       }
       
   }
   
   function parsePingAnHtml(str){
       var lines = [];
       lines = str.split(/\r\n/g);
       console.log(lines.length);
       var debitCardId = $("#debitCardId option:selected").val();
        $("#tableRow tr").remove();
       for(var i = 1; i < lines.length; i++) {
           $("#tableRow").append("<tr></tr>");
           var line = lines[i];
           var cells = line.split(",");
           $("#tableRow tr:last").append("<td>"+(i)+"</td>");
           $("#tableRow tr:last").append("<td>"+debitCardId+"</td>");
           $("#tableRow tr:last").append("<td>"+cells[0]+"</td>");
           $("#tableRow tr:last").append("<td></td>");
           $("#tableRow tr:last").append("<td>"+cells[3]+"</td>");
           $("#tableRow tr:last").append("<td>"+cells[6]+"</td>");
           $("#tableRow tr:last").append("<td>"+(cells[3] == '转入' ? cells[4] : '')+"</td>");
           $("#tableRow tr:last").append("<td>"+(cells[3] == '转出' ? cells[4] : '')+"</td>");
           $("#tableRow tr:last").append("<td>"+cells[5]+"</td>");
       }
       
   }
   
   function parseAbcHtml(str){
       var lines = [];
       lines = str.split(/\r\n/g);
       console.log(lines.length);
       var debitCardId = $("#debitCardId option:selected").val();
        $("#tableRow tr").remove();
       for(var i = 1; i < lines.length; i++) {
           $("#tableRow").append("<tr></tr>");
           var line = lines[i];
           var cells = line.split(",");
           $("#tableRow tr:last").append("<td>"+(i)+"</td>");
           $("#tableRow tr:last").append("<td>"+debitCardId+"</td>");
           $("#tableRow tr:last").append("<td>"+cells[0]+"</td>");
           $("#tableRow tr:last").append("<td>"+cells[1]+"</td>");
           $("#tableRow tr:last").append("<td>"+cells[8]+"</td>");
           $("#tableRow tr:last").append("<td>"+cells[12]+"</td>");
           $("#tableRow tr:last").append("<td>"+cells[2]+"</td>");
           $("#tableRow tr:last").append("<td>"+cells[3]+"</td>");
           $("#tableRow tr:last").append("<td>"+cells[4]+"</td>");
       }
       
   }
</script>
</head>
<body>
<form method="post" action="<c:url value='/common/upload' />" id="postForm" enctype="multipart/form-data">
	<input type="hidden" name="pageNo" value="<c:out value="${KEY_RESULT.pageNo}" />" />
	<div class="col-lg-6">
	  <div class="input-group">
		 <div class="col-md-2" style="padding:0"><select class="form-control" id="bankCode" style="border-right:0; margin-left:-1px;" name="bankCode" value="<c:out value="${KEY_PARAM.requestObject.bankCode == null ? '' : KEY_PARAM.requestObject.bankCode}" />">
	            <option value="">-请选择开户行-</option>
	        </select>
	        </div>
         <div class="col-md-2" style="padding:0"><select class="form-control" id="debitCardId" name="debitCardId" >
	            <option value="">-请选择账号-</option>
	        </select></div>	    
	     <div class="col-md-2" style="padding:0"><input type="file" style="border-right:0;" class="form-control" name="importFile" value="" /></div>
	     <div class="col-md-2" style="padding:0">
			<span class="input-group-btn">
				<button class="btn btn-default" type="button" onclick="uploadImport()">
					上传
				</button>
				<button class="btn btn-default" type="button" onclick="window.location.href='<c:url value="/bank/dodebitimport" />'">
					导入
				</button>
			</span>
		  </div>  
	  </div><!-- /input-group -->
	</div>
	</form>
    <table class="table table-hover">
      <thead>
		  <tr>
		    <th>&nbsp;</th>
		    <th>借记卡ID</th>
		    <th>交易日期</th>
		    <th>交易时间</th>
		    <th>交易类型</th>
		    <th>交易说明</th>
		    <th>收入</th>
		    <th>支出</th>
		    <th>余额</th>
		  </tr>
	  </thead>
	  <tbody id="tableRow">
	  </tbody>
	</table>
</body>
</html>
