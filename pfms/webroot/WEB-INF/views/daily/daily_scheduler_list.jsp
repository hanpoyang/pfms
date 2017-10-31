<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>个人帮手</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="content-type" content="text/html;charset=GBK" />
<!-- 引入 Bootstrap -->
<link
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<style type="text/css">
body {
	font-family: arial, microsoft yahei;
	font-size: 11px;
}

table {
	margin: 0 auto;
}

table tr td {
	width: 20px;
	height: 20px;
	margin: 0;
	padding: 0;
	text-align: center;
}

div#scheduler-div{
    display:none;
}
div#scheduler-ctrl-div{
    padding:0;
    margin:0 5px 5px 30px;
}
div#scheduler-ctrl-div .btn-grp{
    padding:0;
    width:66px;
	height:25px;
	text-align:center;
}
div#scheduler-ctrl-div .select-grp{
    padding:0;
    width:90px;
	height:22px;
	text-align:center;
}
div#scheduler-ctrl-div .select-grp select{
    padding:0;
    width:90px;
	height:26px;
	padding:2px;
} 

div#scheduler-ctrl-div button{
    padding:3px;
    width:30px;
}

table thead tr td{
    background-color : #222;
	color:#9d9d9d;
	font-weight:bold;
}

table tbody tr td.sc-date {
	width: 100px;
	font-weight:normal;
	background-color : #D8D8D8;
	color:#000;
}
div#btn-add-div{
    float:right;
    margin:0;
    width:10%;
}

div#scheduler-ctrl-div #btn-add-div button {
    width:50px;
    height:26px;
    padding:3px;
}
td.task-td{
    background-color:#00FF00;
    border-color:#00FF00;
    cursor:pointer;
}
td.task-td-expires{
    background-color:#9D9C9D;
    border-color:#9D9C9D;
    cursor:pointer;
}
</style>
<script src="http://cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<script src="//cdn.bootcss.com/tether/1.3.6/js/tether.min.js"></script>
<script	src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.5/js/bootstrap.min.js"></script>
<script type="text/javascript">
   var context = "<c:url value="/" />";
   var daysOfMonth = [31,28,31,30,31,30,31,31,30,31,30,31]; 
   var year = 0;
   var monthIndex = 0;
   var taskList = [];
   <c:forEach items="${KEY_RESULT.dataList}" var="row">taskList.push({taskId:'<c:out value="${row.schedulerId}" />', taskYear:'<c:out value="${row.schedulerYear}" />', taskMonth:'<c:out value="${row.schedulerMonth}" />', taskDay:'<c:out value="${row.schedulerDay}" />', taskHour:'<c:out value="${row.schedulerHour}" />', taskMinute:'<c:out value="${row.schedulerMinute}" />', taskDuration:'<c:out value="${row.schedulerDuration}" />', taskSubject:'<c:out value="${row.schedulerSubject}" />'});</c:forEach>
   
   $(function(){
       init();
   });
   
   function init(day){
       var today = new Date();
       year = <c:out value="${param.y == null || param.y == '' ? 'today.getFullYear()' : param.y}" />;
       if(year % 4 == 0) daysOfMonth[1] = 29;
       monthIndex = <c:out value="${param.m == null || param.m == '' ? 'today.getMonth()' : param.m - 1}" />;
       var msNumber1day = 1000*60*60*24;
       var firstDay = new Date(year,monthIndex, 1);
       $("#schedulerTable").append("<tbody></tbody>");
       for(var i = 0; i < daysOfMonth[monthIndex]; i++) { 
	       var task = [];
		   for(var j = 0; j < taskList.length; j++){
		       var taskDay = taskList[j].taskDay;
			   if(i+1 == taskDay) task.push(taskList[j]);
		   }
           var ms = firstDay.getTime() + i * msNumber1day;
           var day = new Date();
           day.setTime(ms);
           initTable(day, task);
       }
       $("#scheduler-div").show();
       for(var i = year - 2; i < year + 2; i++){
           if(i == year) {
               $("#ySel").append("<option value=\""+i+"\" selected>"+i+"</option>");
           } else {
               $("#ySel").append("<option value=\""+i+"\">"+i+"</option>");
           }
       }
       for(var i = 1; i < 13; i++){
           if(i == monthIndex + 1) {
               $("#mSel").append("<option value=\""+i+"\" selected>"+i+"</option>");
           } else {
               $("#mSel").append("<option value=\""+i+"\">"+i+"</option>");
           }
       }
   }
   
   function initTable(day, tasks) {
       var year = day.getFullYear();
	   var loopIndex = 0;
       year = year < 10 ? "0" + year : year;
       var month = day.getMonth()+1;
       month = month < 10 ? "0" + month : month;
       var date = day.getDate();
       date = date < 10 ? "0" + date : date;
       var todayStr = year + "/" + month + "/" + date;
	   $("#schedulerTable tbody").append("<tr></tr>");
       $("#schedulerTable tbody tr:last").append("<td class=\"sc-date\">"+todayStr+"</td>");
       for(var i = 0; i < 48; i++) {
	       loopIndex = getTask(day, tasks, i, loopIndex);
       }
   }
   
   function getTask(day, tasks, outerLoopIndex, loopIndex){
	   var taskHour = 0;
	   var taskDuration = 0;
	   var startTd = 0;
	   var taskMinute = 0;
	   var hasTask = false;
	   var isExpires = false;
	   var taskSubject = null;
	   var taskId = null;
	   
       for(var i = 0; i < tasks.length; i++){
	       var task = tasks[i];
		   if(task != null) {
			   taskHour = parseInt(task.taskHour);
			   taskDuration = task.taskDuration;
			   loopCount = taskDuration/30;
			   taskMinute = task.taskMinute;
			   startTd = parseInt(taskHour*2) + (taskMinute == '30' ? 1 : 0);
			   hasTask = true;
			   var taskYear = task.taskYear;
			   var taskMonth = task.taskMonth;
			   var taskDay = task.taskDay;
			   var dateObj = new Date(taskYear, taskMonth - 1, taskDay, taskHour, taskMinute, 0);
			   var milliSeconds = dateObj.getTime();
			   var now = new Date();
			   var nowMilliSeconds = now.getTime();
			   var dif = nowMilliSeconds - milliSeconds - taskDuration * 60 * 1000;
			   isExpires = dif > 0;
			   taskSubject = task.taskSubject;
			   taskId = task.taskId;
			   //------------------------
			   var tempObj = new Date();
			   tempObj.setTime(milliSeconds + taskDuration * 60 * 1000);
			   var tempHour = tempObj.getHours();
			   var tempMinute = tempObj.getMinutes();
			   taskSubject += "(" + (taskHour < 10 ? "0"+taskHour : taskHour) + ":" + (taskMinute < 10 ? "0"+taskMinute : taskMinute) + " 至 " + (tempHour < 10 ? "0"+tempHour : tempHour) + ":" + (tempMinute < 10 ? "0"+tempMinute : tempMinute)  + ")";
			   if(hasTask && outerLoopIndex == (startTd+loopIndex) && loopIndex < loopCount) {
			       break;
			   }
		   }
		   
	   }
	   if(hasTask && outerLoopIndex == (startTd+loopIndex) && loopIndex < loopCount){
		   if(isExpires) {
			   $("#schedulerTable tbody tr:last").append("<td class=\"task-td-expires\" title=\""+taskSubject+"\" onclick=\"showTask('"+taskId+"')\" data-toggle=\"modal\" data-target=\"#show-task-panel\"></td>");
		   } else {
			   $("#schedulerTable tbody tr:last").append("<td class=\"task-td\" title=\""+taskSubject+"\" onclick=\"showTask('"+taskId+"')\" data-toggle=\"modal\" data-target=\"#show-task-panel\"></td>");
		   }  
		   loopIndex ++;
	   } else {
		   $("#schedulerTable tbody tr:last").append("<td></td>");
	   }
	   return loopIndex;
   }
   
   function prevMonth(){
       monthIndex -= 1;
       if(monthIndex < 0){
           year -= 1;
           monthIndex = 11;
       }
       monthIndex += 1;
       window.location.href = context + "daily/queryschedulers?y="+year+"&m="+monthIndex;
   }
   
   function prevYear(){
       year -= 1;
       monthIndex += 1;
       window.location.href = context + "daily/queryschedulers?y="+year+"&m="+monthIndex;
   }
   
   function nextYear(){
       year += 1;
       monthIndex += 1;
       window.location.href = context + "daily/queryschedulers?y="+year+"&m="+monthIndex;
   }
   
   function nextMonth(){
       monthIndex += 1;
       if(monthIndex > 11){
           year += 1;
           monthIndex = 0;
       }
       monthIndex += 1;
       window.location.href = context + "daily/queryschedulers?y="+year+"&m="+monthIndex;
   }
   
   function changeY(){
       year = $("#ySel option:selected").val();
       monthIndex += 1;
       window.location.href = context + "daily/queryschedulers?y="+year+"&m="+monthIndex;
   }
   
   function changeM(){
       monthIndex = $("#mSel option:selected").val();
       window.location.href = context + "daily/queryschedulers?y="+year+"&m="+monthIndex;
   }
   
   function add(){
       window.location.href = context + "daily/showscheduleradd"
   }
   
   function showTask(taskId) {
   	   $("#task-detail-frame").bind("load",function(obj){
           var _h = $(this).contents().find("body").height();
           $(this).height(_h);
       });
       $("#task-detail-frame").attr("src",context+"daily/schedulerdetail?schedulerId="+taskId);
       
   }
</script>
</head>
<body>
    <div id="scheduler-ctrl-div" class="input-group">
        <div class="col-sm-1 btn-grp">
	        <span class="input-group-btn">
	    	    <button class="btn btn-default btn-xs" onclick="prevYear()">&nbsp;&lt;&lt;&nbsp;</button>
	    		<button class="btn btn-default btn-xs" onclick="prevMonth()">&nbsp;&lt;&nbsp;</button>
	    	</span>
        </div>
    	<div class="col-sm-1 select-grp">
	    	<select id="ySel" class="form-control" onchange="changeY()">
	    	</select>
    	</div>
    	<div class="col-sm-1 select-grp">
	    	<select id="mSel" class="form-control" onchange="changeM()">
	    	</select>
    	</div>
    	<div class="col-sm-1 btn-grp">
	    	<span class="input-group-btn">
	    		<button class="btn btn-default btn-xs" onclick="nextMonth()">&nbsp;&gt;&nbsp;</button>
	    		<button class="btn btn-default btn-xs" onclick="nextYear()">&nbsp;&gt;&gt;&nbsp;</button>
	    	</span>
    	</div>
    	<div id="btn-add-div">
    	    <span class="input-group-btn">
	    		<button class="btn btn-default" onclick="add()"> 新增 </button>
	    	</span>
    	</div>
    </div>
    <div id="scheduler-div">
	<table border="1" id="schedulerTable">
		<thead>
			<tr>
				<td class="sc-date">日期</td>
				<td colspan="2">00</td>
				<td colspan="2">01</td>
				<td colspan="2">02</td>
				<td colspan="2">03</td>
				<td colspan="2">04</td>
				<td colspan="2">05</td>
				<td colspan="2">06</td>
				<td colspan="2">07</td>
				<td colspan="2">08</td>
				<td colspan="2">09</td>
				<td colspan="2">10</td>
				<td colspan="2">11</td>
				<td colspan="2">12</td>
				<td colspan="2">13</td>
				<td colspan="2">14</td>
				<td colspan="2">15</td>
				<td colspan="2">16</td>
				<td colspan="2">17</td>
				<td colspan="2">18</td>
				<td colspan="2">19</td>
				<td colspan="2">20</td>
				<td colspan="2">21</td>
				<td colspan="2">22</td>
				<td colspan="2">23</td>
			</tr>
		</thead>
	</table>
	</div>
    <!-- showTask -->
    <div class="modal fade" id="show-task-panel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					任务详情
				</h4>
			</div>
			<div class="modal-body" id="modal-body">
			  <iframe id="task-detail-frame" src="about:blank" width="100%" frameborder="0" framespace="0" border="0"></iframe>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
	</div>	
</body>
</html>