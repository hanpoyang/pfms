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
                $("#bankCode option[value='"+$("#bankCode").attr("value")+"']").attr("selected", "selected");
           }
       });
       
   });
</script>
</head>
<body>
<div class="form-panel center">
<form class="form-horizontal" role="form" action="debit/update" method="POST">
  <input type="hidden" name="debitCardId" value="<c:out value="${KEY_RESULT.debitCardId == null ? '' : KEY_RESULT.debitCardId}" />" />
  <div class="form-group">
    <label for="password" class="col-sm-2 control-label">开户行</label>
    <div class="col-sm-10">
      <select class="form-control" id="bankCode" name="bankCode" value="<c:out value="${KEY_RESULT.bankCode == null ? '' : KEY_RESULT.bankCode}" />">
          <option value="">-请选择-</option>
      </select>
    </div>
  </div>
  <div class="form-group">
    <label for="visitAddr" class="col-sm-2 control-label">卡号</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="cardNumber" name="cardNumber" placeholder="卡号" value="<c:out value="${KEY_RESULT.cardNumber == null ? '' : KEY_RESULT.cardNumber}" />" />
    </div>
  </div>
  <div class="form-group">
    <label for="username" class="col-sm-2 control-label">账户名</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="accountName" name="accountName" placeholder="请输入账户名" value="<c:out value="${KEY_RESULT.accountName == null ? '' : KEY_RESULT.accountName}" />" />
    </div>
  </div>
  <div class="form-group">
    <label for="username" class="col-sm-2 control-label">发卡行</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="issueBankName" name="issueBankName" placeholder="请输入发卡行" value="<c:out value="${KEY_RESULT.issueBankName == null ? '' : KEY_RESULT.issueBankName}" />" />
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
      <script>$("$("#currencyType option[value='<c:out value="${KEY_RESULT.currencyType == null ? '' : KEY_RESULT.currencyType}" />']").attr("selected","selected");</script>
    </div>
  </div>
  <div class="form-group">
    <label for="accountDescription" class="col-sm-2 control-label">余额</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="cardBalance" name="cardBalance" placeholder="请输入有余额" value="<c:out value="${KEY_RESULT.cardBalance == null ? '' : KEY_RESULT.cardBalance}" />" />
    </div>
  </div>
  <div class="form-group">
    <label for="username" class="col-sm-2 control-label">自有卡</label>
    <div class="col-sm-10">
      <select class="form-control" id="belongToUser" name="belongToUser">
          <option value="Y">自有</option>
          <option value="N">其他</option>
      </select>
      <script>$("$("#belongToUser option[value='<c:out value="${KEY_RESULT.belongToUser == '自有' ? 'Y' : 'N'}" />']").attr("selected","selected");</script>
    </div>
  </div>
  <div class="form-group">
    <label for="accountDescription" class="col-sm-2 control-label">说明</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="cardDescription" name="cardDescription" placeholder="说明" value="<c:out value="${KEY_RESULT.cardDescription == null ? '' : KEY_RESULT.cardDescription}" />" />
    </div>
  </div>
  <div class="form-group">
    <label for="accountDescription" class="col-sm-2 control-label">客服电话</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="servicePhone" name="servicePhone" placeholder="请输入客服电话" value="<c:out value="${KEY_RESULT.servicePhone == null ? '' : KEY_RESULT.servicePhone}" />" />
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
