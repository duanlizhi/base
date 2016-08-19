<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="format-detection" content="telephone=no">
		<title>
			  ${article.title} 
		</title>
		<link rel="stylesheet" type="text/css" href="${base}/resources/web/css/content.css">
		<script src="${base}/resources/web/js/jquery-1.8.3.min.js" type="text/javascript"></script>
		<script src="${base}/resources/web/js/content.js" type="text/javascript"></script>
		<style>
			ol,ul{list-style-position:inside;}
		</style>
		<script type="text/javascript">
		var system=detectOS();
		browserinfo();
		var userAgent = navigator.userAgent;
		var l_count=0;
		var b_count=0;
		$().ready(function() {
			//点击数量的获取
			 $.ajax({
			    type:"get",
  				url: "${base}/article/hits/${article.id}",  
  			    data:"system="+system+"&bappName="+navigator.Actual_Name+"&bversion="+navigator.Actual_Version+"&from="
  			    		+getParam('from')+"&isappinstalled="+getParam('isappinstalled')+"&userAgent="+userAgent,
			    success:function(msg){
			     	$("#click_article").append(msg);
			    }
			  });
			  //喜欢数量的获取
			  $.ajax({
					    type:"get",
		  				url: "${base}/article/loves/${article.id}",  
					    success:function(msg){
					     	 $("#love_article").append(msg);
					     	 l_count=msg;
					    }
			  });
			   //不喜欢数量的获取
			  $.ajax({
					    type:"get",
		  				url: "${base}/article/borings/${article.id}",  
					    success:function(msg){
					     	 $("#boring_article").append(msg);
					     	 b_count=msg;
					    }
			  });
			  
			  $( "#js_love_article" ).click( function (){ 
			  		 var newLove=(Number(l_count)+Number(1));
			         $("#love_article").empty();
					 $("#love_article").append(newLove);
						   $.ajax({
						    type:"get",
						     data:"system="+system+"&bappName="+navigator.Actual_Name+"&bversion="+navigator.Actual_Version+"&from="
  			    			+getParam('from')+"&isappinstalled="+getParam('isappinstalled')+"&userAgent="+userAgent,
			  				url: "${base}/article/cloves/${article.id}",  
						    success:function(msg){
						     }
				 		   });
				});  
				
				 $( "#js_boring_article" ).click( function (){ 
			  		 var newb=(Number(b_count)+Number(1));
			         $("#boring_article").empty();
					 $("#boring_article").append(newb);
						   $.ajax({
						    type:"get",
						     data:"system="+system+"&bappName="+navigator.Actual_Name+"&bversion="+navigator.Actual_Version+"&from="
  			    			+getParam('from')+"&isappinstalled="+getParam('isappinstalled')+"&userAgent="+userAgent,
			  				url: "${base}/article/cborings/${article.id}",  
						    success:function(msg){
						     }
				 		   });
				});  
				
		});
					
		</script>
		<body id="activity-detail" class="zh_CN ">
			<div class="rich_media">
				<div class="rich_media_inner">
					<h2 class="rich_media_title" id="activity-name">
						   ${article.title} 
					</h2>
					<div class="rich_media_meta_list">
						<em id="post-date" class="rich_media_meta text">
							${article.createDate?string("yyyy-MM-dd")}
						</em>
						<a class="rich_media_meta link nickname" href="javascript:void(0);" id="post-user">
							${article.author}
						</a>
						<a class="rich_media_meta link nickname" href="javascript:void(0);" id="post-user">
							更多精彩内容
						</a>
					</div>
					<div id="page-content">
						<div id="img-content">
							<div class="rich_media_content" id="js_content">
								   ${article.content} 
							</div>
					<div class="rich_media_tool" id="js_toobar">
						<a id="js_boring_article" class="media_tool_meta link_primary meta_primary" href="javascript:void(0);">踩<span  id="boring_article"><span></a>
						<a id="js_love_article" class="media_tool_meta link_primary meta_primary" href="javascript:void(0);"> 赞<span  id="love_article"><span>  </a>
					   <a id="js_click_article" class="media_tool_meta link_primary meta_primary" href="javascript:void(0);"> 阅读<span  id="click_article"><span> </a>
					   </div>
						</div>
					</div>
					<div style="display: block;" id="js_pc_qr_code" class="qr_code_pc_outer">
						<div class="qr_code_pc_inner">
							<div class="qr_code_pc">
								<img src="${base}${article.qrcodePath}" id="js_pc_qr_code_img" class="qr_code_pc_img">
								<p>
									扫一扫
									<br>
									查看本内容 
									</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</body>

</html>