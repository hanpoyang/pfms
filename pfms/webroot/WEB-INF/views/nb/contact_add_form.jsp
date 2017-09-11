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
</script>
</head>
<body>
<div class="form-panel center">
 <form class="form-horizontal" role="form" action="contact_add" method="POST">
	<div class="form-group">
	  <label for="contactName" class="col-sm-3 control-label">姓名</label>
	  <div class="col-sm-7">
	    <input type="text" class="form-control" id="contactName" name="contactName" placeholder="姓名">
	  </div>
	</div>
	<div class="form-group">
	  <label for="contactEnName" class="col-sm-3 control-label">Name</label>
	  <div class="col-sm-7">
	    <input type="text" class="form-control" id="contactEnName" name="contactEnName" placeholder="Name">
	  </div>
	</div>
	<div class="form-group">
	  <label for="contactCellphone" class="col-sm-3 control-label">手机号</label>
	  <div class="col-sm-7">
	    <input type="text" class="form-control" id="contactCellphone" name="contactCellphone" placeholder="手机号">
	  </div>
	</div>
	<div class="form-group">
	  <label for="contactFixedphone" class="col-sm-3 control-label">固话</label>
	  <div class="col-sm-7">
	    <input type="text" class="form-control" id="contactFixedphone" name="contactFixedphone" placeholder="固话">
	  </div>
	</div>
	<div class="form-group">
	  <label for="contactEmail" class="col-sm-3 control-label">电邮</label>
	  <div class="col-sm-7">
	    <input type="text" class="form-control" id="contactEmail" name="contactEmail" placeholder="电邮">
	  </div>
	</div>
	<div class="form-group">
	  <label for="contactQq" class="col-sm-3 control-label">QQ</label>
	  <div class="col-sm-7">
	    <input type="text" class="form-control" id="contactQq" name="contactQq" placeholder="QQ">
	  </div>
	</div>
	<div class="form-group">
	  <label for="contactNick" class="col-sm-3 control-label">昵称</label>
	  <div class="col-sm-7">
	    <input type="text" class="form-control" id="contactNick" name="contactNick" placeholder="昵称">
	  </div>
	</div>
	<div class="form-group">
	  <label for="contactWechat" class="col-sm-3 control-label">微信号</label>
	  <div class="col-sm-7">
	    <input type="text" class="form-control" id="contactWechat" name="contactWechat" placeholder="微信号">
	  </div>
	</div>
	<div class="form-group">
	  <label for="contactOther" class="col-sm-3 control-label">其他联系方式</label>
	  <div class="col-sm-7">
	    <input type="text" class="form-control" id="contactOther" name="contactOther" placeholder="其他联系人">
	  </div>
	</div>
	<div class="form-group">
	  <label for="contactAddress" class="col-sm-3 control-label">地址</label>
	  <div class="col-sm-7">
	    <input type="text" class="form-control" id="contactAddress" name="contactAddress" placeholder="地址">
	  </div>
	</div>
	<div class="form-group">
	  <label for="contactCompany" class="col-sm-3 control-label">公司</label>
	  <div class="col-sm-7">
	    <input type="text" class="form-control" id="contactCompany" name="contactCompany" placeholder="公司">
	  </div>
	</div>
	<div class="form-group">
	  <label for="contactDescrip" class="col-sm-3 control-label">说明</label>
	  <div class="col-sm-7">
	    <input type="text" class="form-control" id="contactDescrip" name="contactDescrip" placeholder="说明">
	  </div>
	</div>
	<div class="form-group">
      <label for="password" class="col-sm-3 control-label">状态</label>
      <div class="col-sm-7">
        <select class="form-control" id="contactStatus" name="contactStatus">
          <option value="">-请选择-</option>
          <option value="Y">生效</option>
          <option value="N">失效</option>
        </select>
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
