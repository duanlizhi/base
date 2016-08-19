var content={};
content.debug={isAtive:false}; //是否开启debug模式。true 开启，false不开启

content.studyHttp={port:"http://content.xiaochejiang.com"};

//定义常量
content.study={
	 version:"V 1.2",
	 name:"小车匠",
	 getTspUrl:content.studyHttp.port+"/api/oper/base/getTsp",
	 getTokenUrl:content.studyHttp.port+"/api/oper/base/getToken",
	 initStudyUrl:content.studyHttp.port+"/api/oper/study/initStudy",
	 startStudyUrl:content.studyHttp.port+"/api/oper/study/startStudy",
	 commitStudyUrl:content.studyHttp.port+"/api/oper/study/commitStudy",
	 exitStudyUrl:content.studyHttp.port+"/api/oper/study/exitStudy"
}

//判断学习是否可以使用
content.pub={
	 isAvailable:true //判断是否与后台需要交互  如果需要交互则刚开始需要设置变量。。为true 
}

//把String 转成boolean
content.study.StringToBoolean = function(string){
     switch(string.toLowerCase()) {
          case "true": case "yes": case "1": return true;
          case "false": case "no": case "0":case "null": case null: return false; 
          default: return Boolean(string);
     }     
};
//获取学习token
content.study.getToken=function(domainAccount,domainPassword,userEmail){
	if(!content.pub.isAvailable){return false;}
	return $.ajax({
		type : "post",
		url : content.study.getTokenUrl,
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


//获取TOKEN以及SessionID
content.study.getTsp=function(domainAccount,domainPassword,userEmail){
	if(!content.pub.isAvailable){return false;}
	return $.ajax({
		type : "post",
		url : content.study.getTspUrl,
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


//学习初始化
content.study.initStudy=function (token,courseNumber,userEmail,domainUsername){
	if(!content.pub.isAvailable){return false;}
	var objData = {
				token:token,
				courseNumber:courseNumber,
				userEmail:userEmail,
				domainUsername:domainUsername
	};
	var obj = "";
	$.ajax({
		type : "post",
		url : content.study.initStudyUrl,
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

//学习开始
content.study.startStudy=function (token,courseNumber,userEmail,domainUsername,sessionId){
	if(!content.pub.isAvailable){return false;}
	var objData = {
			token:token,
			courseNumber:courseNumber,
			userEmail:userEmail,
			domainUsername:domainUsername,
			sessionId:sessionId
	};
	var obj = "";
	$.ajax({
		type : "post",
		url : content.study.startStudyUrl,
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

//频繁提交学习数据 连分钟提交一次
content.study.commitStudy=function (objData){
	if(!content.pub.isAvailable){return false;}
	var obj = "";
	$.ajax({
		type : "post",
		url : content.study.commitStudyUrl,
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

//学习退出
content.study.exitStudy=function (objData){
	if(!content.pub.isAvailable){return false;}
	var obj = "";
	$.ajax({
		type : "post",
		url : content.study.exitStudyUrl,
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

//获取学习历史数据
content.getHistoryData = function(){
	return $("#historyData",parent.document).text();
}
//获取课件的学习json
content.getCourseWareJson = function(){
	return $("#xmljson",parent.document).text();
}

//此变量为学习自动提交的数据(不为空就保存到数据库中)
var Htoken="";
var HcourseNumber="";
var HuserEmail="";
var HdomainUsername="";
var HsessionId="";
var Hreceive="";
var Hcharacter="";
var Hprogress="";
var Hscore="";
var HisComplete="";
var HisPass="";
var HactionTime="";

//给上面的学习全局变量赋值
content.setAutoCommitStudyData = function(token,courseNumber,userEmail,domainUsername,sessionId,receive,character,progress,score,isComplete,isPass,actionTime){
	if(!content.pub.isAvailable){return false;}
	Htoken=token;
	HcourseNumber=courseNumber;
	HuserEmail=userEmail;
	HdomainUsername=domainUsername;
	HsessionId=sessionId;
	Hreceive=receive;
	Hcharacter=character;
	Hprogress=progress;
	Hscore=score;
	HisComplete=isComplete;
	HisPass=isPass;
	HactionTime=actionTime;
} 

//定时将上面学习的全局变量保存到数据库
//setInterval("autoCommitStudy()",120000);
function autoCommitStudy(){
	if(!Boolean(Htoken) || !Boolean(HcourseNumber) || !Boolean(HuserEmail) || !Boolean(HdomainUsername) || !Boolean(HsessionId)){
		return false;
	}
	var objData = {
		token:Htoken,
		courseNumber:HcourseNumber,
		userEmail:HuserEmail,
		domainUsername:HdomainUsername,
		sessionId:HsessionId,
		receive:Hreceive,
		character:Hcharacter,
		progress:parseInt(Hprogress),
		score:parseInt(Hscore),
		iscomplete:HisComplete,
		ispass:HisPass,
		actionTime:HactionTime
	};
	$.ajax({
		type : "post",
		url : content.study.commitStudyUrl,
		async:false,
		dataType :"json",
		data : {
			"objData":JSON.stringify(objData)
		},
		success : function(data) {
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

