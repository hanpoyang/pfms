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
<script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script src="//cdn.bootcss.com/tether/1.3.6/js/tether.min.js"></script>
<script	src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.5/js/bootstrap.min.js"></script>
<script	src="../js/common.js"></script>
<script type="text/javascript">
   var context = "<c:url value="/" />";
   $(function(){
       $.getJSON(context+"bank/banks",function(responseObject){
           var flag = responseObject.status;
           var data = responseObject.data;
           console.log(flag);
           console.log(data);
           if(flag == "1") {
               for(var i = 0; i < data.length; i++){
                   $("#bankCode").append("<option value='"+data[i]["key"]+"'>"+data[i]["value"]+"</option>");
               }
           }
       });
       
   });
</script>
</head>
<body>
<div class="form-panel center">
 <form class="form-horizontal" role="form" action="debit/add" method="POST">
  <div class="form-group">
    <label for="password" class="col-sm-2 control-label">开户行</label>
    <div class="col-sm-10">
      <select class="form-control" id="bankCode" name="bankCode">
          <option value="">-请选择-</option>
      </select>
    </div>
  </div>
  <div class="form-group">
    <label for="visitAddr" class="col-sm-2 control-label">卡号</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="cardNumber" name="cardNumber" placeholder="卡号">
    </div>
  </div>
  <div class="form-group">
    <label for="username" class="col-sm-2 control-label">账户名</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="accountName" name="accountName" placeholder="请输入账户名">
    </div>
  </div>
  <div class="form-group">
    <label for="username" class="col-sm-2 control-label">发卡行</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="issueBankName" name="issueBankName" placeholder="请输入发卡行">
    </div>
  </div>
  <div class="form-group">
    <label for="username" class="col-sm-2 control-label">币种</label>
    <div class="col-sm-10">
      <select class="form-control" id="currencyType" name="currencyType">
          <option value="CNY">人民币</option>
          <option value="HKD">港元</option>
          <option value="USD">美元</option>
      </select>
    </div>
  </div>
  <div class="form-group">
    <label for="accountDescription" class="col-sm-2 control-label">余额</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="cardBalance" name="cardBalance" placeholder="请输入有余额">
    </div>
  </div>
  <div class="form-group">
    <label for="username" class="col-sm-2 control-label">自有卡</label>
    <div class="col-sm-10">
      <select class="form-control" id="belongToUser" name="belongToUser">
          <option value="Y">自有</option>
          <option value="N">其他</option>
      </select>
    </div>
  </div>
  <div class="form-group">
    <label for="accountDescription" class="col-sm-2 control-label">说明</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="cardDescription" name="cardDescription" placeholder="说明">
    </div>
  </div>
  <div class="form-group">
    <label for="accountDescription" class="col-sm-2 control-label">客服电话</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="servicePhone" name="servicePhone" placeholder="请输入客服电话">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="button" class="btn btn-default" onclick="submitForm()">保存</button>&nbsp;<button type="button" class="btn btn-default" onclick="goBack()">取消</button>
    </div>
  </div>
</form></div>
</body>
</html>
