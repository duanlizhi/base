/**
 * wbt教学js
 * @param {Object} msg
 */
var wbtPlayer={};
wbtPlayer.debug = {isAtive:false};

//定义常量
wbtPlayer.wbt={
	version:"",
	name:"xcj",
	courseWareUrl:"/course/wbt/"
}

//判断是否可以使用
wbtPlayer.pub={
	 isAvailable:true //判断是否与后台需要交互  如果需要交互则刚开始需要设置变量。。为true 
}

//打印
wbtPlayer.log = function(msg){
     if(content.debug.isAtive){
		if(window.console && window.console.firebug){
			console.log(msg);
		} else {
			console.log(msg);
		}
		
     }
};

//选择页跳页
wbtPlayer.wbt.updateIframe = function(id, name,courseNumber){
	var url = wbtPlayer.wbt.courseWareUrl+""+courseNumber+"/" + name;
	$("#ifm_1").attr("src", url);
	var pageNumber = name.split('.')[0];
	$("#pageNumber").attr("value",pageNumber);
 	wbtPlayer.wbt.showTool(id);
 	wbtPlayer.wbt.pieceString(pageNumber);
 	wbtPlayer.wbt.schedule(id);
	
}

//选择页跳页
wbtPlayer.wbt.returnPage = function(id, name,courseNumber) {
	//1.获取到课件地址和课件id 把值赋给iframe
	var url = wbtPlayer.wbt.courseWareUrl+""+courseNumber+"/" + name;
	$("#ifm_1").attr("src", url);
	var pageNumber = name.split('.')[0];
	$("#pageNumber").attr("value",pageNumber);
 	wbtPlayer.wbt.showTool(id);
 	wbtPlayer.wbt.pieceString(pageNumber);
 	wbtPlayer.wbt.schedule(id);
}

//音频播放
wbtPlayer.wbt.audio = function(){
	var audioEle  = $("#ifm_1").contents().find("#audio")[0];
	var plays = $("#plays").val();
	if(plays==0){
		audioEle.pause();
		$("#plays").attr("value","1");
		$(".btn_voice").addClass("btn_voice_no");
	}
	if(plays==1){
		audioEle.play();	
		$("#plays").attr("value","0");
		$(".btn_voice").removeClass("btn_voice_no");
	}
}	

//拼接字符串
wbtPlayer.wbt.pieceString = function(id){
	var page = $("#page").val();
	var ids = "";
	if(page.indexOf(","+id+",")==-1){
		ids = page+","+id;
		$("#page").attr("value",ids);
	}
}

//工具菜单显示整体显示
wbtPlayer.wbt.toolDisplay = function(){
	$(".tool-big").show(500);
}	 

//工具菜单显示整体隐藏
wbtPlayer.wbt.toolDisplay = function(){
	$(".tool-big").hide(500);
}
 
//初始化
jQuery(document).ready(function () {
	var dataJson = $("#dataJson").text();
	var analyzeJson = eval('('+dataJson+')');
	var courseNumber = analyzeJson.wbtCourseWare.courseWare.courseNumber;
	var entry = analyzeJson.wbtCourseWare.courseWare.entry;
	var iframUrl = wbtPlayer.wbt.courseWareUrl+courseNumber+"/"+entry+"";
	$("#ifm_1").attr("src",iframUrl);
	$("#courseNumber").attr("value",courseNumber);
	var chapter = analyzeJson.wbtCourseWare.chapter.length;
	for(var i=0;i<chapter;i++){
		var id = analyzeJson.wbtCourseWare.chapter[i].id;
		var name = analyzeJson.wbtCourseWare.chapter[i].name;
		var url = analyzeJson.wbtCourseWare.chapter[i].url;
		var updateIframe= "updateIframe('"+id+"','"+url+"','"+courseNumber+"')";
		$("#navtionlj").append("<button id='"+id+"' class='btnns' onclick="+updateIframe+" style='display: none;' disabled='disabled'>"+name+"</button>");
	}
	
	var menu = analyzeJson.wbtCourseWare.menu.page.length;
	for(var m=0;m<menu;m++){
		var id = analyzeJson.wbtCourseWare.menu.page[m].id;
		var name = analyzeJson.wbtCourseWare.menu.page[m].name;
		var url = analyzeJson.wbtCourseWare.menu.page[m].url;
		var style = analyzeJson.wbtCourseWare.menu.page[m].style;
		$("#gongju_box").append("<button id='"+url+"' class='"+style+"' ></button><span>"+name+"</span>");
	}
	
	for(var c=0;c<chapter;c++){
		var pages = analyzeJson.wbtCourseWare.chapter[c].page.length;
		for(p=0;p<pages;p++){
			var id = analyzeJson.wbtCourseWare.chapter[c].page[p].id;
			var url = analyzeJson.wbtCourseWare.chapter[c].page[p].url;
			var isCommit = analyzeJson.wbtCourseWare.chapter[c].page[p].isCommit;
			 
			$(".footer").append("<input type='hidden' id='pageId' value='"+id+"' />" +
								"<input type='hidden' id='"+id+"c' value='"+isCommit+"' />" +
								"<input type='hidden' id='"+id+"p' value='"+url+"' />");
		}
	}
	var id = $("#pageId").val();
	wbtPlayer.wbt.showTool(id);
	$("#failures").removeAttr("onclick");
	$("#tools").removeAttr("onclick");
});
	
//是否显示工具栏
wbtPlayer.wbt.showTool = function(id){
	var idb = id + "b";
	var idt = id + "t";
	var idf = id + "f";
	var idh = id + "h";
	var canBook = $("#" + idb + "").val();
	var canTool = $("#" + idt + "").val();
	var canFailure = $("#" + idf + "").val(); 
	var canHistory = $("#" + idh + "").val();
	if(canBook=="false"){
		$(".tool-big-a").css({display:"none"});
	}else{
		$(".tool-big-a").css({display:"block"});
	}
	if(canTool=="false"){
		$(".tool-big-b").css({display:"none"});
	}else{
		$(".tool-big-b").css({display:"block"});
	}
	if(canFailure=="false"){
		$(".tool-big-c").css({display:"none"});
	}else{
		$(".tool-big-c").css({display:"block"});
	}
	if(canHistory=="false"){
		$(".tool-big-d").css({display:"none"});
	}else{
		$(".tool-big-d").css({display:"block"});
	}
}

	
//下一页
wbtPlayer.wbt.goNext=function(){
	var pageNumber = $("#pageNumber").val();
	var pageLast = $("#pageLast").val();
	 
	if(parseInt(pageLast)==parseInt(pageNumber)){
	 
		alert("当前是最后一页");
	}else{
		wbtPlayer.wbt.goPage(parseInt(pageNumber)+1);
	    $("#pageNumber").attr("value",parseInt(pageNumber)+1);
	}
}
	
//上一页
wbtPlayer.wbt.goPrevious = function(){
	var pageNumber = $("#pageNumber").val();
	if(parseInt(pageNumber)==1){
		alert("当前是第一页");
	}else{
		wbtPlayer.wbt.goPage(parseInt(pageNumber)-1);
		$("#pageNumber").attr("value",parseInt(pageNumber)-1);
	}
}
	
//跳页
wbtPlayer.wbt.goPage = function (pageNumber){
	var size = pageNumber.toString().length==2;
	var wbtUrl = $("#courseNumber").val();
	if(size){
	 	var id ="P0"+pageNumber;
	 	var ids = "0"+pageNumber;
	 	var idp = id + "p";
	 	var page = $("#" + idp + "").val();
	 	wbtPlayer.wbt.schedule(id);
 		wbtPlayer.wbt.showTool(id);
 		wbtPlayer.wbt.pieceString("0"+pageNumber);
		var url = "/course/wbt/"+wbtUrl+"/" + page;
		$("#progress").attr("value",page);
		$("#ifm_1").attr("src", url);
		wbtPalyer.wbt.xmlCommit(id);
	}else{
		var id ="P00"+pageNumber;
		var ids = "00"+pageNumber;
		var idp = id + "p";
	 	var page  = "";
	 	page = $("#" + idp + "").val();
	 	wbtPlayer.wbt.schedule(id);
 		wbtPlayer.wbt.showTool(id);
 		wbtPlayer.wbt.pieceString("00"+pageNumber);
		var url = "/course/wbt/"+wbtUrl+"/" + page;
		$("#progress").attr("value",page);
		$("#ifm_1").attr("src", url);
		wbtPalyer.wbt.xmlCommit(id);
	}
}
	
//进度 
wbtPlayer.wbt.schedule = function(id){
	//导航
	$("#"+id+"").css({"display":"block"});
	$("#"+id+"").parent().addClass("banner_active").siblings().removeClass("banner_active");
	$("#P065").parent().removeClass("banner_active");
}


//两分钟提交一次数据
//setInterval("timingSubmit()",120000);
function startInit(){
	var historyData = eval(JSON.parse(content.getHistoryData()).data);
	var returnCharacter = historyData.characterA;
	if(Boolean(returnCharacter)){
		if(Boolean(historyData.characterB)){
			returnCharacter += historyData.characterB;
		}
		if(Boolean(historyData.characterC)){
			returnCharacter += historyData.characterC;
		}
		var param = eval('('+returnCharacter+')').param;
		//调用赋值初始方法
		setInitParam(param);
	}
}

setInterval("timingSubmit()",120000);
//自动提交数据
function timingSubmit(){
 
	var dataJson = $("#historyData").text();
	var jsonObj = eval('('+dataJson+')');
	var token = jsonObj.token;
	var courseNumber = jsonObj.data.courseName;
	var userEmail = jsonObj.data.userEmail;
	var domainUsername = jsonObj.data.domainName;
	 
	var sessionId = jsonObj.data.sessionId;
	var receive = "123";//jsonObj.data.receive
	var character = jsonObj.data.character;
	var progress = $("#progress").attr("value");
	var score = $("#score").attr("value");
	var isComplete = jsonObj.data.isComplete;
	var isPass = jsonObj.data.isPass;
	content.setAutoCommitStudyData(token,courseNumber,userEmail,domainUsername,sessionId,receive,character,progress,score,isComplete,isPass); 
}

//根据xml提交数据
wbtPlayer.wbt.xmlCommit = function(id){
	var idc = id + "c";
	var isCommit = $("#" + idc + "").val();
	if(isCommit=="true"){
		var dataJson = $("#historyData").text();
		var jsonObj = eval('('+dataJson+')');
		var token = jsonObj.token;
		var courseNumber = jsonObj.data.courseName;
		var userEmail = jsonObj.data.userEmail;
		var domainUsername = jsonObj.data.domainName;
		var sessionId = jsonObj.data.sessionId;
		var receive = "123";//jsonObj.data.receive
		var character = jsonObj.data.character;
		var progress = $("#progress").attr("value");
		var score = $("#score").attr("value");
		var isComplete = jsonObj.data.isComplete;
		var isPass = jsonObj.data.isPass;
		content.setAutoCommitStudyData(token,courseNumber,userEmail,domainUsername,sessionId,receive,character,progress,score,isComplete,isPass); 

	}
}

//提交数据暂时没用
wbtPlayer.wbt.submitData = function(){
		var postdata={
			"courseNumber":$("#courseNumber").val(),
			"answer":$("#daan").val()
		};
		$.ajax({
			type:"get",
			contentType:"application/json; charset=utf-8",
			url:"/api/evaluation/aotomaticSubmit",
			data:postdata, 
			dataType:'json',
			success:function(data){
			
			},
	       	error : function() {
	       	}
		});
}
	
//ipad
function OnLoad(){
   document.documentElement.style.webkitTouchCallout = "none"; //禁止弹出菜单
   document.documentElement.style.webkitUserSelect = "none";//禁止选中
}
