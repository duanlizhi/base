var content={};
content.debug={isAtive:false}; //是否开启debug模式。true 开启，false不开启


content.testsHttp={port:"http://content.xiaochejiang.com"};

content.tests={
	 version:"V 1.0",
	 name:"小车匠",
	 getTokenUrl:content.testsHttp.port+"/api/oper/tests/getToken",
	 getSessionIdUrl:content.testsHttp.port+"/api/oper/tests/getTestsSessionId",
	 getTestsPublicIdUrl:content.testsHttp.port+"/api/oper/tests/getTestsPublicId",
	 strartTestUrl:content.testsHttp.port+"/api/oper/tests/strartTest",//(暂时没用到)
	 getHistoryDataUrl:content.testsHttp.port+"/api/oper/tests/getHistoryData",
	 commitTestUrl:content.testsHttp.port+"/api/oper/tests/commitTest",
	 commitPaperTestUrl:content.testsHttp.port+"/api/oper/tests/commitPaperTest",
	 backHomePage:content.testsHttp.port+"/api/tests/start"
}

//判断测评是否可以使用
content.pubTests={
	 isAvailable:true //判断是否与后台需要交互  如果需要交互则刚开始需要设置变量。。为true 
}

//打印
content.log = function(msg){
     if(content.debug.isAtive){
		if(window.console && window.console.firebug){
			console.log(msg);
		} else {
			console.log(msg);
		}
		
     }
};

//获取测评测评token
content.tests.getToken=function(domainAccount,domainPassword,userEmail){
	if(!content.pubTests.isAvailable){return false;}
	return $.ajax({
		type : "post",
		url : content.tests.getTokenUrl,
		async:"false",
		data : {
			"domainAccount":domainAccount,
			"domainPassword":domainPassword,
			"userEmail":userEmail
		},
		success : function(data) {
			return data;
		}
	});  
}

//获取测评sessionId
content.tests.getSessionId=function(domainUsername,userEmail,token){
	if(!content.pubTests.isAvailable){return false;}
	var objData = {
		domainUsername:domainUsername,
		userEmail:userEmail,
		token:token
	};
	var obj = "";
	$.ajax({
		type : "post",
		url : content.tests.getSessionIdUrl,
		async:false,
		dataType :"json",
		data : {
			"objData":JSON.stringify(objData)
		},
		success : function(data) {
			obj = data;
		}
	});  
	return obj;
}

//获取测评testsPublicId
content.tests.getTestsPublicId=function(domainUsername,userEmail,token){
	if(!content.pubTests.isAvailable){return false;}
	var objData = {
		domainUsername:domainUsername,
		userEmail:userEmail,
		token:token
	};
	var obj = "";
	$.ajax({
		type : "post",
		url : content.tests.getTestsPublicIdUrl,
		async:false,
		dataType :"json",
		data : {
			"objData":JSON.stringify(objData)
		},
		success : function(data) {
			obj = data;
		}
	});  
	return obj;
}

//测评开始(暂时没用到)
content.tests.strartTest=function(token,courseNumber,userEmail,domainUsername,sessionId,testsPublicId){
	if(!content.pub.isAvailable){return false;}
	var objData = {
		token:token,
		courseNumber:courseNumber,
		userEmail:userEmail,
		domainUsername:domainUsername,
		sessionId:sessionId,
		testsPublicId:testsPublicId
	};
	$.ajax({
		type : "post",
		url : content.tests.strartTestUrl,
		async:false,
		dataType :"json",
		data : {
			"objData":JSON.stringify(objData)
		},
		success : function(data) {
		}
	});  
}

//测评获取历史数据
content.tests.getHistoryData=function(token,courseNumber,userEmail,domainUsername,sessionId,testsPublicId,testPostedId){
	if(!content.pubTests.isAvailable){return false;}
	var objData = {
		token:token,
		courseNumber:courseNumber,
		userEmail:userEmail,
		domainUsername:domainUsername,
		sessionId:sessionId,
		testsPublicId:testsPublicId,
		testPostedId:testPostedId
	};
	var obj = "";
	$.ajax({
		type : "post",
		url : content.tests.getHistoryDataUrl,
		async:false,
		dataType :"json",
		data : {
			"objData":JSON.stringify(objData)
		},
		success : function(data) {
			obj = data;
		}
	});  
	return obj;
}

//测评提交数据的方法
content.tests.commitTest=function(token,courseNumber,userEmail,domainUsername,sessionId,receive,character,progress,score,isComplete,isPass,actionTime,testsPublicId,testPostedId){
	if(!content.pubTests.isAvailable){return false;}
	var objData = {
		token:token,
		courseNumber:courseNumber,
		userEmail:userEmail,
		domainUsername:domainUsername,
		sessionId:sessionId,
		receive:receive,
		character:character,
		progress:parseInt(progress),
		score:parseInt(score),
		iscomplete:isComplete,
		ispass:isPass,
		actionTime:actionTime,
		testsPublicId:testsPublicId,
		testPostedId:testPostedId
	};
	var obj = "";
	$.ajax({
		type : "post",
		url : content.tests.commitTestUrl,
		async:false,
		dataType :"json",
		data : {
			"objData":JSON.stringify(objData)
		},
		success : function(data) {
			obj = data;
		}
	});  
	return obj;
}

//测评交卷
content.tests.commitPaperTest=function(token,courseNumber,userEmail,domainUsername,sessionId,receive,character,progress,score,isComplete,isPass,actionTime,testsPublicId,testPostedId){
	if(!content.pubTests.isAvailable){return false;}
	Ttoken="";
	TtestsPublicId="";
	TcourseNumber="";
	TuserEmail="";
	TdomainUsername="";
	TsessionId="";
	Treceive="";
	Tcharacter="";
	Tprogress="";
	Tscore="";
	TisComplete="";
	TisPass="";
	TactionTime="";
	TtestPostedId="";
	var objData = {
		token:token,
		courseNumber:courseNumber,
		userEmail:userEmail,
		domainUsername:domainUsername,
		sessionId:sessionId,
		receive:receive,
		character:character,
		progress:parseInt(progress),
		score:parseInt(score),
		iscomplete:isComplete,
		ispass:isPass,
		actionTime:actionTime,
		testsPublicId:testsPublicId,
		testPostedId:testPostedId
	};
	var obj = "";
	$.ajax({
		type : "post",
		async:false,
		url : content.tests.commitPaperTestUrl,
		dataType :"json",
		data : {
			"objData":JSON.stringify(objData)
		},
		success : function(data) {
			obj = data;
//			$("#domainUsername").val(domainUsername);
//			$("#userEmail").val(userEmail);
//			$("#domainFlag").val("xcjcontentcenter");
//			$("#backHomePageForm").attr("action",content.tests.backHomePage);
//			$("#backHomePageForm").submit();
		}
	});  
	return obj;
}

//获取测评的历史数据
content.getHistoryData = function(){
	return $("#historyData",parent.document).text();
}
//获取课件测评的json
content.getCourseWareJson = function(){
	return $("#xmljson",parent.document).text();
}

//此变量为 测评 自动提交的数据(不为空就保存到数据库中)
var Ttoken="";
var TtestsPublicId="";
var TcourseNumber="";
var TuserEmail="";
var TdomainUsername="";
var TsessionId="";
var Treceive="";
var Tcharacter="";
var Tprogress="";
var Tscore="";
var TisComplete="";
var TisPass="";
var TactionTime="";
var TtestPostedId="";
//给上面的全局变量赋值
content.setAutoCommitTestsData = function(token,testsPublicId,courseNumber,userEmail,domainUsername,sessionId,receive,character,progress,score,isComplete,isPass,actionTime,testPostedId){
	if(!content.pubTests.isAvailable){return false;}
	Ttoken=token;
	TtestsPublicId=testsPublicId;
	TcourseNumber=courseNumber;
	TuserEmail=userEmail;
	TdomainUsername=domainUsername;
	TsessionId=sessionId;
	Treceive=receive;
	Tcharacter=character;
	Tprogress=parseInt(progress);
	Tscore=parseInt(score);
	TisComplete=isComplete;
	TisPass=isPass;
	TactionTime=actionTime;
	TtestPostedId=testPostedId;
} 

//定时将上面的全局变量保存到数据库
setInterval("autoCommitTests()",120000);
function autoCommitTests(){
	if(!Boolean(Ttoken) || !Boolean(TcourseNumber) || !Boolean(TuserEmail) || 
			!Boolean(TdomainUsername) || !Boolean(TsessionId) ||  !Boolean(TtestsPublicId) || !Boolean(TtestPostedId)){
		return false;
	}
	var objData = {
		token:Ttoken,
		testsPublicId:TtestsPublicId,
		courseNumber:TcourseNumber,
		userEmail:TuserEmail,
		domainUsername:TdomainUsername,
		sessionId:TsessionId,
		receive:Treceive,
		character:Tcharacter,
		progress:parseInt(Tprogress),
		score:parseInt(Tscore),
		iscomplete:TisComplete,
		ispass:TisPass,
		actionTime:TactionTime,
		testPostedId:TtestPostedId
	};
	 
	//调用提交接口
	$.ajax({
		type : "post",
		url : content.tests.commitTestUrl,
		async:true,
		dataType :"json",
		data : {
			"objData":JSON.stringify(objData)
		}
	});  
	return true;
}

//格式化时间
Date.prototype.format = function(format){ 
	var o = { 
	"M+" : this.getMonth()+1, //month 
	"d+" : this.getDate(), //day 
	"h+" : this.getHours(), //hour 
	"m+" : this.getMinutes(), //minute 
	"s+" : this.getSeconds(), //second 
	"q+" : Math.floor((this.getMonth()+3)/3), //quarter 
	"S" : this.getMilliseconds() //millisecond 
	} 
	
	if(/(y+)/.test(format)) { 
	format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	} 
	
	for(var k in o) { 
	if(new RegExp("("+ k +")").test(format)) { 
	format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
	} 
	} 
	return format; 
} 


