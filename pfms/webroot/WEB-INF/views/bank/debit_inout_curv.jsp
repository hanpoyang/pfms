<%@ page language="java" pageEncoding="utf-8"%><%@taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>  
    body{font-size:11px; font-family:microsoft yanhei; margin:0; padding:0;}
    .axis path,  
    .axis line{  
        fill:none;  
        stroke:black;  
        shape-rendering:crispEdges;  
    }  
    .curv-container{margin:0; border:1px solid  #9BC6F0; height:261px; margin-left:3px;}
   #curv-svg{padding-left:25px;}
</style>
<div class="curv-container">
 <p><h3>&nbsp;收支走势</h3></p>
	<svg id="curv-svg"></svg>
	<svg id="legend-div"></svg>
</div>
<script type="text/javascript">
  var incomes = [];
  var outcomes = [];
  var differences = [];
  $.getJSON("<c:url value="/bank/debit_in_out" />", function(json){
      if(json.flag == "1") {
      $.each(json.data[1],function(i, d){
          var arr0 = Math.floor(parseInt(d.ym)/201613)*12+parseInt(d.ym)%100;
          console.log("=========="+arr0);
	      incomes.push([arr0, parseFloat(d.income).toFixed(2)]);
      });
      $.each(json.data[0],function(i, d){
          outcomes.push([Math.floor(parseInt(d.ym)/201613)*12+parseInt(d.ym)%100, parseFloat(d.outcome).toFixed(2)]);
      });
	  $.each(json.data[0],function(i, d){
          differences.push([Math.floor(parseInt(d.ym)/201613)*12+parseInt(d.ym)%100, (incomes[i][1]) - d.outcome]);
      });
      drawCurve();
      } else {
          console.log(json.message);
          alert(json.message);
      }
  });
  
function drawCurve(){  
	var width=315;  
	var height=180;  
	  
	var dataset=[  
	    {  
	        name:"incomes",  
	        value: incomes
	    },  
	    {  
	        name:"outcomes",  
	        value: outcomes
	    },
		{
		    name:"diff",
			value:differences
		}
	];  
	var lineNames = ["收入", "支出", "盈亏"];    
	var padding={top:20, right:30, bottom: 20, left:30};  
	var valueMax=0;  
	var valueMin = 0;
	for(var i=0;i<dataset.length;i++){  
	    var currValue=d3.max(dataset[i].value,function(d){  
	        return parseFloat(d[1]);  
	    });  
		
	    if(currValue>valueMax)valueMax=currValue;  
		if(currValue<valueMin)valueMin=currValue;  
	} 
	
	var maxY = d3.max(dataset[0].value, function(d){
	    return d[0];
	}); 
	
	var minY = d3.min(dataset[0].value, function(d){
	    return d[0];
	}); 
	  
	var xScale=d3.scale.linear()  
	            .domain([minY,maxY])  
	            .range([0,width-padding.left-padding.right]);  
	  
	var yScale=d3.scale.linear()  
	            .domain([0,valueMax])  
	            .range([height-padding.bottom-padding.top,0]);  
	  
	var linePath=d3.svg.line()//创建一个直线生成器  
	                .x(function(d){  
	                    return xScale(d[0]);  
	                })  
	                .y(function(d){  
	                    return yScale(d[1]);  
	                })  
	                .interpolate("basis")//插值模式  
	                ;  
	  
	//定义两个颜色  
	var colors=[d3.rgb(0,0,255),d3.rgb(0,255,0),d3.rgb(255,100,100)];  
	  
	var svg=d3.select("#curv-svg")  
	                .attr("width",width)  
	                .attr("height",height);  
	  
	svg.selectAll("path")  
	    .data(dataset)  
	    .enter()  
	    .append("path")  
	    .attr("transform","translate("+padding.left+","+padding.top+")")  
	    .attr("d",function(d){  
	        return linePath(d.value);  
	        //返回线段生成器得到的路径  
	    })  
	    .attr("fill","none")  
	    .attr("stroke-width",1)  
	    .attr("stroke",function(d,i){  
		    console.log("i:"+i)
	        return colors[i];  
	    });  
	  
	var xAxis=d3.svg.axis()  
	            .scale(xScale)  
	            .ticks(13)  
	            .tickFormat(d3.format("d"))  
	            .orient("bottom");  
	  
	var yAxis=d3.svg.axis()  
	            .scale(yScale)
				.ticks(6)  
	            .orient("left");  
	  
	//添加一个g用于放x轴  
	svg.append("g")  
	    .attr("class","axis")  
	    .attr("transform","translate("+padding.left+","+(height-padding.top)+")")  
	    .call(xAxis);  
	  
	svg.append("g")  
	    .attr("class","axis")  
	    .attr("transform","translate("+padding.left+","+padding.top+")")  
	    .call(yAxis);  
	    //---------------------------------------------------------
    //添加图例		
	svg.append("g")
	.append("text")
	.text(lineNames[0])
	.attr("x", 140)
	.attr("y", 20);
	
	svg.append("g")
	.append("rect")
	.attr("x", 125)
	.attr("y", 10)
	.attr("width",12)
	.attr("height", 12)
	.attr("fill", colors[0]);
	
	svg.append("g")
	.append("rect")
	.attr("x", 175)
	.attr("y", 10)
	.attr("width",12)
	.attr("height", 12)
	.attr("fill", colors[1]);
	
	svg.append("g")
	.append("text")
	.text(lineNames[1])
	.attr("x", 190)
	.attr("y", 20);
	
	svg.append("g")
	.append("rect")
	.attr("x", 225)
	.attr("y", 10)
	.attr("width",12)
	.attr("height", 12)
	.attr("fill", colors[2]);
	
	svg.append("g")
	.append("text")
	.text(lineNames[2])
	.attr("x", 240)
	.attr("y", 20);
	    
    }
    
</script>
