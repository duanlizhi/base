var content={};
content.debug={isAtive:false}; //是否开启debug模式。true 开启，false不开启


content.testsHttp={port:"http://content.xiaochejiang.com"};

content.tests={
	 version:"V 1.2",
	 name:"小车匠",
	 getTokenUrl:content.testsHttp.port+"/api/oper/tests/getToken",
	 getSessionIdUrl:content.testsHttp.port+"/api/oper/tests/getTestsSessionId",
	 getTestsPublicIdUrl:content.testsHttp.port+"/api/oper/tests/getTestsPublicId",
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
content.tests.commitTest=function(objData){
	if(!content.pubTests.isAvailable){return false;}
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
content.tests.commitPaperTest=function(objData){
	if(!content.pubTests.isAvailable){return false;}
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
		}
	});  
	return obj;
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



