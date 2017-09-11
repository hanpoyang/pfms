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
   var daysOfMonth = [31,28,31,30,31,30,31,31,30,31,30,31]; 
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
 <form class="form-horizontal" role="form" action="savescheduler" method="POST">
  <div class="form-group">
    <label for="visitAddr" class="col-sm-2 control-label">主题</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="schedulerSubject" name="schedulerSubject" placeholder="主题">
    </div>
  </div>
  <div class="form-group">
    <label for="username" class="col-sm-2 control-label">描述</label>
    <div class="col-sm-10">
      <textarea class="form-control" id="schedulerDescrip" name="schedulerDescrip"></textarea>
    </div>
  </div>
  <div class="form-group">
    <label for="username" class="col-sm-2 control-label">年</label>
    <div class="col-sm-10">
      <select class="form-control" id="schedulerYear" name="schedulerYear">
      </select>
      <script>var curYear = new Date().getFullYear(); $("#schedulerYear").append("<option value=\""+curYear+"\" selected>"+curYear+"</option>");$("#schedulerYear").append("<option value=\""+(curYear+1)+"\">"+(curYear+1)+"</option>");</script>
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">月</label>
    <div class="col-sm-10">
      <select class="form-control" id="schedulerMonth" name="schedulerMonth">
      </select>
      <script>
          var curMonth = new Date().getMonth() + 1; 
          for(var i = 1; i < 13; i++) {
              if(i == curMonth)
                  $("#schedulerMonth").append("<option value=\""+i+"\" selected>"+i+"</option>");
              else
                  $("#schedulerMonth").append("<option value=\""+i+"\">"+i+"</option>");
          }
          </script>
    </div>
  </div>
  <div class="form-group">
    <label for="username" class="col-sm-2 control-label">日</label>
    <div class="col-sm-10">
      <select class="form-control" id="schedulerDay" name="schedulerDay">
      </select>
      <script>
          var curMonthIndex = new Date().getMonth(); 
          var dayOfMonth = new Date().getDate();
          for(var i = 1; i <= daysOfMonth[curMonthIndex]; i++) {
              if(i == dayOfMonth)
                  $("#schedulerDay").append("<option value=\""+i+"\" selected>"+i+"</option>");
              else
                  $("#schedulerDay").append("<option value=\""+i+"\">"+i+"</option>");
          }
      </script>
    </div>
  </div>
  <div class="form-group">
    <label for="username" class="col-sm-2 control-label">时</label>
    <div class="col-sm-10">
      <select class="form-control" id="schedulerHour" name="schedulerHour">
      </select>
      <script>
          var curHour = new Date().getHours();
          for(var i = 0; i < 24; i++) {
              if(i == curHour)
                  $("#schedulerHour").append("<option value=\""+i+"\" selected>"+i+"</option>");
              else
                  $("#schedulerHour").append("<option value=\""+i+"\">"+i+"</option>");
          }
      </script>
    </div>
  </div>
  <div class="form-group">
    <label for="username" class="col-sm-2 control-label">分</label>
    <div class="col-sm-10">
      <select class="form-control" id="schedulerMinute" name="schedulerMinute">
          <option value="00">00</option>
          <option value="30">30</option>
      </select>
    </div>
  </div>
  <div class="form-group">
    <label for="username" class="col-sm-2 control-label">持续时长</label>
    <div class="col-sm-10">
      <select class="form-control" id="schedulerDuration" name="schedulerDuration">
          <option value="0.5">0.5</option>
          <option value="1">1</option>
          <option value="1.5">1.5</option>
          <option value="2">2</option>
          <option value="2.5">2.5</option>
          <option value="3">3</option>
          <option value="3.5">3.5</option>
          <option value="4">4</option>
          <option value="4.5">4.5</option>
          <option value="5">5</option>
          <option value="5.5">5.5</option>
          <option value="6">6</option>
          <option value="6.5">6.5</option>
          <option value="7">7</option>
          <option value="7.5">7.5</option>
          <option value="8">8</option>
          <option value="8.5">8.5</option>
          <option value="9">9</option>
          <option value="9.5">9.5</option>
          <option value="10">10</option>
          <option value="10.5">10.5</option>
          <option value="11">11</option>
          <option value="11.5">11.5</option>
          <option value="12">12</option>
          <option value="12.5">12.5</option>
          <option value="13">13</option>
          <option value="13.5">13.5</option>
          <option value="14">14</option>
          <option value="14.5">14.5</option>
          <option value="15">15</option>
          <option value="15.5">15.5</option>
          <option value="16">16</option>
          <option value="16.5">16.5</option>
          <option value="17">17</option>
          <option value="17.5">17.5</option>
          <option value="18">18</option>
          <option value="18.5">18.5</option>
          <option value="19">19</option>
          <option value="19.5">19.5</option>
          <option value="20">20</option>
          <option value="20.5">20.5</option>
          <option value="21">21</option>
          <option value="21.5">21.5</option>
          <option value="22">22</option>
          <option value="22.5">22.5</option>
          <option value="23">23</option>
          <option value="23.5">23.5</option>
          <option value="24">24</option>
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
