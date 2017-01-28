<%@ page language="java" pageEncoding="utf-8" %><%@taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<style>
body{margin:0; padding:0; font-family:microsoft yahei; font-size:11px;}
.container {
  width: 370px;
  height: 251px;
  border: 1px solid #9BC6F0;
  padding:5px;
  }
.list{float:right; width:50%;}  
.div{height:240px;}
table td{padding:2px; }
</style>
<script src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<script src="http://d3js.org/d3.v3.min.js"></script>
</head>
<body>
<div class="container"><div class="pie-div"><svg width="50%"></svg><div id="list_div" class="list"><table></table></div></div><div>---------------------------------------------</div><span id="SummarySpan"></span></div>
<script type="text/javascript">
  var debitSummary = [];
  var dataSet = [];
  var totalAmount = 0;
  $.getJSON("<c:url value="/bank/debit_summary_data" />", function(json){
      $.each(json,function(i, d){
          debitSummary.push(d);
          dataSet.push(d.totalBalance);
      });
      console.log(dataSet);
      drawPie(dataSet);
      showList();
      
  });
  function showList(){
      $.each(debitSummary, function(i, e) {
          try{
		  console.log(e['totalBalance'])
          totalAmount += parseFloat(e['totalBalance'])
          }catch(e){console.log(e);}
		  console.log("totalAmount:"+totalAmount);
          $("#list_div table").append("<tr><td align=\"left\">"+e['bankName']+"</th><td align=\"left\">￥"+formatNumber(parseFloat(e['totalBalance']).toFixed(2))+"</td></tr>");
      });
	  totalAmount = parseFloat(totalAmount).toFixed(2);
      $("#SummarySpan").append("<p>总计:￥"+formatNumber(totalAmount)+"</p></span>")
  }
  
  function formatNumber(number) {
      var decimal = number.substring(number.indexOf("."));
      console.log("decimal:"+decimal);
      var integer = number.substring(0, number.indexOf("."));
      console.log("integer:"+integer);
      var count = integer.length;
      var remain = count % 3;
      var loopCount = parseInt(count/3) - 1;
      var result = "";
      
      
  }
  function drawPie(dataset){
      var width = 150;
      var pie = d3.layout.pie();
      var piedata = pie(dataset);
      var outerRadius = 75;           // 外半径
	  var innerRadius = 0;             // 内半径
	  var arc = d3.svg.arc()           // 弧生成器
      .innerRadius(innerRadius)    // 设置内半径
      .outerRadius(outerRadius)    // 设置外半径
      var svg = d3.select("svg");
      var arcs = svg.selectAll("g")
	  .data(piedata)
	  .enter()
	  .append("g")
	  .attr("transform","translate("+ (width/2) +","+ (width/2) +")");
	  var color = d3.scale.category10();   //有十种颜色的颜色比例尺
	  arcs.append("path")
      .attr("fill",function(d,i){
          return color(i);
      })
      .attr("d",function(d){
          return arc(d);   //调用弧生成器，得到路径值
      });
      
	  arcs.append("text")
	    .attr("transform",function(d){
	        return "translate(" + arc.centroid(d) + ")";
	    })
	    .attr("text-anchor","middle")
	    .text(function(d){
	        return d.data;
	    });
  }
  
  function formatNumber(number) {
      var decimal = number.substring(number.indexOf("."));
	  var result = "";
      console.log("decimal:"+decimal);
      var integer = number.substring(0, number.indexOf("."));
      console.log("integer:"+integer);
      var digits = [];
	  var digitsTmp = [];
	  for(var i = 0; i < integer.length;i++) digits.push(integer.charAt(i));
	  digits.reverse();
      for(var i = 0; i < digits.length; i++) {
	      if(i%3 == 0 && i > 0) {
		      digitsTmp.push(",");
			  digitsTmp.push(digits[i])
		  } else {
		      digitsTmp.push(digits[i]);
		  }
	  }
	  digitsTmp.reverse();
	  result = digitsTmp.join("") + decimal;
      console.log("result:"+digitsTmp.join(""));
	  return result;
  }
 
</script>
</body>
</html>