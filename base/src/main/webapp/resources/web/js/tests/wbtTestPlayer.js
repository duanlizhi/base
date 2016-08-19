/**
 * wbt测评js
 * 
 */
	var wbtTest = {};
	wbtTest.debug = {inAtive:false};
	wbtTest.wbt={
		version:"",
		name:"xcj",
		courseWareUrl:"/course/wbt/",
		star:"off"
	}
 	 
 	//判断是否可以使用
	wbtTest.pub={
		 isAvailable:true //判断是否与后台需要交互  如果需要交互则刚开始需要设置变量。。为true 
	}
	
 	wbtTest.openSwitch = function (){
 		wbtTest.wbt.star = "no";
 	}
 	
	//选择页跳页
	wbtTest.updateIframe = function(id, name) {
		//1.获取到课件地址和课件id 把值赋给iframe
		if(wbtTest.wbt.star=="no"){
			var courseNumber = $("#courseNumber").val();
			var url = wbtTest.wbt.courseWareUrl+courseNumber+"/" + name;
			$("#testIframe").attr("src", url);
			var pageNumber = name.split('.')[0];
			$("#pageNumber").attr("value",pageNumber);
	 		wbtTest.xmlCommit(id);	
	 		wbtTest.repairTools();
		}
	}
	
	//浏览过的页面页号是红色
	wbtTest.sheetBellRad = function(id){
		$("#"+id+"").attr("class","testFunction-sheetBell sheetBellRad");
	}
	
	//TODO目前没用
	wbtTest.toolToPages = function(id, name) {
		if(wbtTest.wbt.star=="no"){ 
			//1.获取到课件地址和课件id 把值赋给iframe
			$("#rating").attr("class","rating");
			var courseNumber = $("#courseNumber").val();
			var url = wbtTest.wbt.courseWareUrl+courseNumber+"/" + name;
			$("#testIframe").attr("src", url);
			var pageNumber = name.split('.')[0];
			$("#pageNumber").attr("value",pageNumber);
	 		wbtTest.xmlCommit(id);	
	 		if(id=="troubles"){
	 			wbtTest.repairTool();
	 			wbtTest.ration();
	 		}else{
	 			wbtTest.repairTools(); 
	 			wbtTest.ration();
	 		}
 		}
	}
	
	wbtTest.ration = function(){
		$("#rating").attr("class","rating rating-0");
	}
	
	//如果点击的故障现象调用此方法
	wbtTest.repairTool = function (){
		$("#maintenance").removeClass("menuBellFont");
	    $("#maintenance").find("img").attr("src","/resources/tests/images/tools.png");
	}
	
	//如果点击的故障现象调用此方法
	wbtTest.repairTools = function (){
		$("#maintenance").addClass("menuBellFont");
	    $("#maintenance").find("img").attr("src","/resources/web/image/wbtTestPlayer/tools_disabled.png");
	}
	
	wbtTest.toolToPage = function(id, name) {
		//1.获取到课件地址和课件id 把值赋给iframe
		var courseNumber = $("#courseNumber").val();
		var url = wbtTest.wbt.courseWareUrl+courseNumber+"/" + name;
		$("#testIframe").attr("src", url);
		var pageNumber = name.split('.')[0];
		$("#pageNumber").attr("value",pageNumber);
 		wbtTest.xmlCommit(id);	
	}
	
	//选择页跳页
	wbtTest.returnPage = function(id, name,courseNumber) {
		//1.获取到课件地址和课件id 把值赋给iframe
		var url = wbtTest.wbt.courseWareUrl+courseNumber+"/" + name;
		$("#testIframe").attr("src", url);
		var pageNumber = name.split('.')[0];
		$("#pageNumber").attr("value",pageNumber);
	 	wbtTest.pieceString(pageNumber);
	 	wbtTest.xmlCommit(id);	
	}
	
	//音频播放
	wbtTest.audio = function(){
		var audioEle  = $("#testIframe").contents().find("#audio")[0];
		var plays = $("#plays").val();
		if(plays==0){
			audioEle.pause();
			$("#plays").attr("value","1");
		}
		if(plays==1){
			audioEle.play();	
			$("#plays").attr("value","0");
		}
	}
	
	//拼接字符串
	wbtTest.pieceString = function(id){
		var page = $("#page").val();
		var ids = "";
		if(page.indexOf(","+id+",")==-1){
			ids = page+","+id;
			$("#page").attr("value",ids);
		}
	}
				
	//交卷
	wbtTest.ifCommit = function() {
	 
		var dataJson = $("#historyData").text();
		var jsonObj = eval('('+dataJson+')');
		var token = jsonObj.token;
		var testsPublicId = jsonObj.data.testsPublicId
		var courseNumber = jsonObj.data.courseName;
		var userEmail = jsonObj.data.userEmail;
		var domainUsername = jsonObj.data.domainName;
		var sessionId = jsonObj.data.sessionId;
		var receive = "123";//jsonObj.data.receive
		var character = $("#answers").text();
		var progress = $("#progress").attr("value");
		var score = $("#score").attr("value");
		var isComplete = jsonObj.data.isComplete;
		var isPass = jsonObj.data.isPass;
		var Time = $("#SECOND").html();
		var actionTimes = Time.split(":");
		var hh = Number(actionTimes[0])*60;
		var mm = Number(actionTimes[1]);
		var actionTime = hh+mm;
		content.setAutoCommitTestsData(token,testsPublicId,courseNumber,userEmail,domainUsername,sessionId,receive,character,progress,score,isComplete,isPass,actionTime);
 
	}
	
	//工具菜单显示整体显示
	wbtTest.toolDisplay = function(){
		$(".tool-big").show(500);
	}
	
	//工具菜单显示整体隐藏
	wbtTest.toolHidden = function (){
		$(".tool-big").hide(500);
	}
	 
	jQuery(document).ready(function () {
		var dataJson = $("#dataJson").text();
		var analyzeJson = eval('('+dataJson+')');
		var courseNumber = analyzeJson.wbtTests.courseWare.courseNumber;
		var entry = analyzeJson.wbtTests.courseWare.entry;
		var iframUrl =wbtTest.wbt.courseWareUrl+courseNumber+"/"+entry+"";
		$("#ifm_1").attr("src",iframUrl);
		$("#courseNumber").attr("value",courseNumber);
		var chapter = analyzeJson.wbtTests.chapter.length;
//		for(var i=0;i<chapter;i++){
//			var id = analyzeJson.wbtCourseWare.chapter[i].id;
//			var name = analyzeJson.wbtCourseWare.chapter[i].name;
//			var url = analyzeJson.wbtCourseWare.chapter[i].url;
//			$("#navtionlj").append("<button id='"+id+"' class='btnns' onclick='updateIframe(id,'"+url+"','"+courseNumber+"')' style='display: none;' disabled='disabled'>"+name+"</button>");
//		}
		
		var menu = analyzeJson.wbtTests.menu.page.length;
		for(var m=0;m<menu;m++){
			var id = analyzeJson.wbtTests.menu.page[m].id;
			var name = analyzeJson.wbtTests.menu.page[m].name;
			var url = analyzeJson.wbtTests.menu.page[m].url;
			var style = analyzeJson.wbtTests.menu.page[m].style;
		}
		
		for(var c=0;c<chapter;c++){
			var pages = analyzeJson.wbtTests.chapter[c].page.length;
			for(p=0;p<pages;p++){
				var id = analyzeJson.wbtTests.chapter[c].page[p].id;
				var url = analyzeJson.wbtTests.chapter[c].page[p].url;
				var isCommit = analyzeJson.wbtTests.chapter[c].page[p].isCommit;
				var onclik = "wbtTest.updateIframe('"+id+"','"+url+"')";
				var testFunction = "<div class='testFunction-sheetBell sheetBellDisabled' id='"+id+"' onclick="+onclik+">"+parseInt(p+1)+"</div>";
				$("#navtionlj").append(testFunction+"<input type='hidden' id='pageId' value='"+id+"' />" +
									"<input type='hidden' id='"+id+"c' value='"+isCommit+"' />" +
									"<input type='hidden' id='"+id+"p' value='"+url+"' />");
			}
			
			$("#pageLast").attr("value",pages);
		}
		var id = $("#pageId").val();
		$("#failures").removeAttr("onclick");
		$("#tools").removeAttr("onclick");
	});
	
	//下一页
	wbtTest.goNext = function(){
		var pageNumber = $("#pageNumber").val();
		var pageLast = $("#pageLast").val();
		wbtTest.repairTools(); 
		if(wbtTest.wbt.star=="no"){
			if(parseInt(pageLast)==parseInt(pageNumber)){
				alert("当前是最后一页");
			}else{
				wbtTest.goPage(parseInt(pageNumber)+1);
			    $("#pageNumber").attr("value",parseInt(pageNumber)+1);
			 }
		}
	}
	
	//页面提交
	wbtTest.commitGoNext = function(){
		var pageNumber = $("#pageNumber").val();
		var pageLast = $("#pageLast").val();
		wbtTest.repairTools(); 
		if(wbtTest.wbt.star=="no"){
			if(parseInt(pageLast)==parseInt(pageNumber)){
				alert("当前是最后一页");
			}else{
				wbtTest.commitGoPage(parseInt(pageNumber)+1);
			    $("#pageNumber").attr("value",parseInt(pageNumber)+1);
			 }
		}
	}
	
	//上一页
	wbtTest.goPrevious = function (){
		var pageNumber = $("#pageNumber").val();
		wbtTest.repairTools();
		if(wbtTest.wbt.star=="no"){
			if(parseInt(pageNumber)==1){
				alert("当前是第一页");
			}else{
				wbtTest.goPage(parseInt(pageNumber)-1);
				$("#pageNumber").attr("value",parseInt(pageNumber)-1);
			}
		}
	}
	
	//跳页
	wbtTest.goPage = function (pageNumber){
		var size = pageNumber.toString().length==2;
		var wbtUrl = $("#courseNumber").val();
		if(size){
		 	var id ="P0"+pageNumber;
		 	var ids = "0"+pageNumber;
		 	var idp = id + "p";
		 	var page = $("#" + idp + "").val();
	 		wbtTest.xmlCommit(id);
			var url =  wbtTest.wbt.courseWareUrl+wbtUrl+"/" + page;
			$("#testIframe").attr("src", url);
			
		}else{
			var id ="P00"+pageNumber;
			var idp = id + "p";
		 	var page  = "";
		 	page = $("#" + idp + "").val();
		 	if(page==null){
		 		page="001.html"
		 	}
	 		wbtTest.xmlCommit(id);
			var url =  wbtTest.wbt.courseWareUrl+wbtUrl+"/" + page;
			$("#testIframe").attr("src", url);
		}
	}
	
	//跳页
	wbtTest.commitGoPage = function (pageNumber){
		var size = pageNumber.toString().length==2;
		var wbtUrl = $("#courseNumber").val();
		if(size){
		 	var id ="P0"+pageNumber;
		 	var ids = "0"+pageNumber;
		 	var idp = id + "p";
		 	var page = $("#" + idp + "").val();
	 		wbtTest.pieceString("0"+pageNumber);
	 		wbtTest.xmlCommit(id);
			var url =  wbtTest.wbt.courseWareUrl+wbtUrl+"/" + page;
			$("#testIframe").attr("src", url);
			
		}else{
			var id ="P00"+pageNumber;
			var idp = id + "p";
		 	var page  = "";
		 	page = $("#" + idp + "").val();
		 	if(page==null){
		 		page="001.html"
		 	}
	 		wbtTest.pieceString("00"+pageNumber);
	 		wbtTest.xmlCommit(id);
			var url =  wbtTest.wbt.courseWareUrl+wbtUrl+"/" + page;
			$("#testIframe").attr("src", url);
		}
	}
	
	//工具栏转页
	wbtTest.toolPage = function (){
		$("#testIframe").contents().find('.mask-tools').show(500);
	}
	
	//根据xml提交数据
	wbtTest.xmlCommit = function (id){
		var idc = id + "c";
		var isCommit = $("#" + idc + "").val();
		 
		if(isCommit=="true"){
			var dataJson = $("#historyData").text();
			var jsonObj = eval('('+dataJson+')');
			var token = jsonObj.token;
			var testsPublicId = jsonObj.data.testsPublicId
			var courseNumber = jsonObj.data.courseName;
			var userEmail = jsonObj.data.userEmail;
			var domainUsername = jsonObj.data.domainName;
			var sessionId = jsonObj.data.sessionId;
			var receive = "123";//jsonObj.data.receive
			var character = $("#answers").text();
			var progress = $("#progress").attr("value");
			var score = $("#score").attr("value");
			var isComplete = jsonObj.data.isComplete;
			var isPass = jsonObj.data.isPass;
			var Time = $("#SECOND").html();
			var actionTimes = Time.split(":");
			var hh = Number(actionTimes[0])*60;
			var mm = Number(actionTimes[1]);
			var actionTime = hh+mm;
			 
			content.setAutoCommitTestsData(token,testsPublicId,courseNumber,userEmail,domainUsername,sessionId,receive,character,progress,score,isComplete,isPass,actionTime);
		}
	}
	
	//setInterval("autoCommintTest()",12000);
	//自动提交
	function autoCommintTest(){
		
		var dataJson = $("#historyData").text();
		var jsonObj = eval('('+dataJson+')');
		var token = jsonObj.token;
		var testsPublicId = jsonObj.data.testsPublicId
		var courseNumber = jsonObj.data.courseNumber;
		var userEmail = jsonObj.data.userEmail;
		var domainUsername = jsonObj.data.domainUsername;
		var sessionId = jsonObj.data.sessionId;
		var receive = "123";//jsonObj.data.receive
		var character = $("#answers").text();
		var progress = $("#progress").attr("value");
		var score = $("#scoreTest").attr("value");
		var isComplete = jsonObj.data.isComplete;
		var isPass = jsonObj.data.isPass;
		var Time = $("#SECOND").html();
		var actionTimes = Time.split(":");
		var hh = Number(actionTimes[0])*60;
		var mm = Number(actionTimes[1]);
		var actionTime = hh+mm;
		 
		content.setAutoCommitTestsData(token,testsPublicId,courseNumber,userEmail,domainUsername,sessionId,receive,character,progress,score,isComplete,isPass,actionTime);
	}
	
	
	//ipad
	function OnLoad(){
	   document.documentElement.style.webkitTouchCallout = "none"; //禁止弹出菜单
	   document.documentElement.style.webkitUserSelect = "none";//禁止选中
	
	}