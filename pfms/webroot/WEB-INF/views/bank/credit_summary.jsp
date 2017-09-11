<%@ page language="java" pageEncoding="utf-8" %><%@taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
div{margin:0; padding:0; font-family:microsoft yahei; font-size:11px;}
.credit-container {
  width: 386px;
  height: 251px;
  border: 1px solid #9BC6F0;
  padding:5px;
  }
.list{float:right; width:50%;}  
.div{height:240px;}
table td{padding:3px; }
</style>
<div class="credit-container">
<p><h3>贷记卡近期账单</h3></p>
<div class="credit-pie-div"><svg id="credit-svg" width="50%"></svg><div id="credit_list_div" class="list"><table></table></div></div><div>---------------------------------------------</div><span id="CreditSummarySpan"></span></div>
<script type="text/javascript">
  var creditSummary = [];
  var creditDataSet = [];
  var totalCreditAmount = 0;
  $.getJSON("<c:url value="/bank/credit_summary_data" />", function(json){
      $.each(json,function(i, d){
          creditSummary.push(d);
          creditDataSet.push(d.cnyImburseAmount);
      });
      drawCreditPie(creditDataSet);
      showCreditList();
      
  });
  function showCreditList(){
      $.each(creditSummary, function(i, e) {
          try{
		  console.log(e['totalBalance'])
          totalCreditAmount += parseFloat(e['cnyImburseAmount'])
          }catch(e){console.log(e);}
		  console.log("totalAmount:"+totalCreditAmount);
          $("#credit_list_div table").append("<tr><td align=\"left\">"+e['bankName']+"</th><td align=\"left\">￥"+formatNumber(parseFloat(e['cnyImburseAmount']).toFixed(2))+"</td><td>"+(e['isClear'] == 'Y' ? '结清': '未结')+"</td></tr>");
      });
	  totalCreditAmount = parseFloat(totalCreditAmount).toFixed(2);
      $("#CreditSummarySpan").append("<p>总计:￥"+formatNumber(totalCreditAmount)+"</p></span>")
  }
  
  function drawCreditPie(dataset){
      var width = 150;
      var pie = d3.layout.pie();
      var piedata = pie(dataset);
      var outerRadius = 75;           // 外半径
	  var innerRadius = 0;             // 内半径
	  var arc = d3.svg.arc()           // 弧生成器
      .innerRadius(innerRadius)    // 设置内半径
      .outerRadius(outerRadius)    // 设置外半径
      var svg = d3.select("#credit-svg");
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
  
</script>