<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>小车匠内容中心</title>
    <link href="<%=path%>/resources/admin/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=path%>/resources/admin/css/dashboard.css" rel="stylesheet">
	<script src="<%=path%>/resources/admin/js/jquery-2.1.3.min.js"></script>
    <script src="<%=path%>/resources/admin/js/bootstrap.min.js"></script>
    <script src="<%=path%>/resources/admin/js/highcharts.js"></script>
	<script src="<%=path%>/resources/admin/js/exporting.js"></script>
		<style type="text/css">
			${demo.css}
		</style>
		<script type="text/javascript">
$(function () {
    var chart;
    
    $(document).ready(function () {
		//报表1
        $('#container').highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
				height:400,
				width:400
            },
            title: {
                text: '平台学习人数'
            },
            tooltip: {
        	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
			credits:{
				 enabled:false // 禁用版权信息
			},
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: false
                    },
                    showInLegend: true
                }
            },
            series: [{
                type: 'pie',
                name: 'Browser share',
                data: [
                    ['Firefox',   45.0],
                    ['IE',       26.8],
                    {
                        name: 'Chrome',
                        y: 12.8,
                        sliced: false,
                        selected: false
                    },
                    ['Safari',    8.5],
                    ['Opera',     6.2],
                    ['Others',   0.7]
                ]
            }]
        });
		//报表2
		$('#container2').highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
				height:400,
				width:400
            },
            title: {
                text: '平台学习人次'
            },
            tooltip: {
        	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
			credits:{
				 enabled:false // 禁用版权信息
			},
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: false
                    },
                    showInLegend: true
                }
            },
            series: [{
                type: 'pie',
                name: 'Browser share',
                data: [
                    ['Firefox',   45.0],
                    ['IE',       26.8],
                    {
                        name: 'Chrome',
                        y: 12.8,
                        sliced: false,
                        selected: false
                    },
                    ['Safari',    8.5],
                    ['Opera',     6.2],
                    ['Others',   0.7]
                ]
            }]
        });
		
    });//$(document).ready结束
    
});//$(function())结束
		</script>
 
  </head>
  <body>
    
    <div class="container-fluid">
    <jsp:include page="top.jsp">
    <jsp:param value="a" name="MeauTag"/>
    </jsp:include>
      <div class="row">
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">近30日统计</h1>

		   <div class="row placeholders">
			<!--报表1-->
			<div class="col-xs-6 placeholder">
			<div id="container" style="min-width: 400px; height: 400px; max-width: 600px; margin: 0 auto"></div>
			</div>
			<!--报表2-->
			<div class="col-xs-6 placeholder">
			<div id="container2" style="min-width: 400px; height: 400px; max-width: 600px; margin: 0 auto"></div>
			</div>
          </div>          
		  
          <h2 class="sub-header">数据
			<div  class="btn-group" style="padding-left:20px;">			
			<select class="form-control">
				  <option selected="selected">全部平台</option>
				  <option>培训平台</option>
				  <option>学习平台</option>
				  <option>相亲平台</option>
			</select>
			</div>
		  </h2>
		  
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>时间</th>
                  <th>学习人数</th>
                  <th>学习人次</th>
				  <th>学习时长</th>
                  <th>测评人数</th>
                  <th>测评人次</th>
                  <th>平均得分</th>
                </tr>
              </thead>
              <tbody>

			    <tr>
                  <td>12-17</td>
                  <td>32</td>
                  <td>86</td>
                  <td>300</td>
                  <td>15</td>
                  <td>16</td>
                  <td>88</td>
                </tr>
                <tr>
                  <td>12-17</td>
                  <td>32</td>
                  <td>86</td>
                  <td>300</td>
                  <td>15</td>
                  <td>16</td>
                  <td>88</td>
                </tr>
                <tr>
                  <td>12-17</td>
                  <td>32</td>
                  <td>86</td>
                  <td>300</td>
                  <td>15</td>
                  <td>16</td>
                  <td>88</td>
                </tr>
                <tr>
                  <td>12-17</td>
                  <td>32</td>
                  <td>86</td>
                  <td>300</td>
                  <td>15</td>
                  <td>16</td>
                  <td>88</td>
                </tr>
                <tr>
                  <td>12-17</td>
                  <td>32</td>
                  <td>86</td>
                  <td>300</td>
                  <td>15</td>
                  <td>16</td>
                  <td>88</td>
                </tr>
                <tr>
                  <td>12-17</td>
                  <td>32</td>
                  <td>86</td>
                  <td>300</td>
                  <td>15</td>
                  <td>16</td>
                  <td>88</td>
                </tr>
                <tr>
                  <td>12-17</td>
                  <td>32</td>
                  <td>86</td>
                  <td>300</td>
                  <td>15</td>
                  <td>16</td>
                  <td>88</td>
                </tr>
                <tr>
                  <td>12-17</td>
                  <td>32</td>
                  <td>86</td>
                  <td>300</td>
                  <td>15</td>
                  <td>16</td>
                  <td>88</td>
                </tr>
                <tr>
                  <td>12-17</td>
                  <td>32</td>
                  <td>86</td>
                  <td>300</td>
                  <td>15</td>
                  <td>16</td>
                  <td>88</td>
                </tr>
                <tr>
                  <td>12-17</td>
                  <td>32</td>
                  <td>86</td>
                  <td>300</td>
                  <td>15</td>
                  <td>16</td>
                  <td>88</td>
                </tr>
                <tr>
                  <td>12-17</td>
                  <td>32</td>
                  <td>86</td>
                  <td>300</td>
                  <td>15</td>
                  <td>16</td>
                  <td>88</td>
                </tr>
                <tr>
                  <td>12-17</td>
                  <td>32</td>
                  <td>86</td>
                  <td>300</td>
                  <td>15</td>
                  <td>16</td>
                  <td>88</td>
                </tr>
                <tr>
                  <td>12-17</td>
                  <td>32</td>
                  <td>86</td>
                  <td>300</td>
                  <td>15</td>
                  <td>16</td>
                  <td>88</td>
                </tr>
                <tr>
                  <td>12-17</td>
                  <td>32</td>
                  <td>86</td>
                  <td>300</td>
                  <td>15</td>
                  <td>16</td>
                  <td>88</td>
                </tr>
			  
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
