var conf = {
	base: "${path}"
	
};
var TConf = {};
TConf["domain"] = "xiaochejiang.com";
TConf["domainName"] = "苏建";
TConf["uploadImageExtension"] = "多个扩展名以(,)分隔，留空表示不允许上传";
TConf["uploadFlashExtension"] = "多个扩展名以(,)分隔，留空表示不允许上传";
TConf["uploadMediaExtension"] = "多个扩展名以(,)分隔，留空表示不允许上传";
TConf["uploadFileExtension"] = "多个扩展名以(,)分隔，留空表示不允许上传";

if(typeof(KindEditor) != "undefined") {
	KindEditor.ready(function(K) {
		editor = K.create("#editor", {
			height: "350px",
			items: [
		        'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
		        'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
		        'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
		        'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
		        'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
		        'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage',
		        'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
		        'anchor', 'link', 'unlink', '|'
			],
			syncType: "form",
			filterMode: false,
			pagebreakHtml: '<hr class="pageBreak" \/>',
			allowFileManager: true,
			filePostName: "file",
			fileManagerJson:TConf["domain"]+"/admin/common/browser",
			uploadJson:TConf["domain"]+"/admin/common/upload",
			uploadImageExtension: TConf["uploadImageExtension"],
			uploadFlashExtension: TConf["uploadFlashExtension"],
			uploadMediaExtension: TConf["uploadMediaExtension"],
			uploadFileExtension: TConf["uploadFileExtension"],
			afterChange: function() {
				this.sync();
			}
		});
	});
}

///admin/common/browser				
				
// 获取Cookie
function getCookie(name) {
	if (name != null) {
		var value = new RegExp("(?:^|; )" + encodeURIComponent(String(name)) + "=([^;]*)").exec(document.cookie);
		return value ? decodeURIComponent(value[1]) : null;
	}
}

// 移除Cookie
function removeCookie(name, options) {
	addCookie(name, null, options);
}

function delButton(str) {
	var osc = document.getElementById("sc");
	var ohight = document.documentElement.scrollHeight;
	var owidth = document.documentElement.scrollWidth;
	var whight = document.documentElement.clientHeight;
	var omask = document.createElement("div");
	omask.id = "mask";
	omask.style.height = ohight + "px";
	omask.style.width = owidth + "px";
	document.body.appendChild(omask);
	var omask1 = document.createElement("div");
	omask1.id = "mask-sc";
	omask1.style.height = "300px";
	omask1.style.width = "170px";
	document.body.appendChild(omask1);
	omask1.innerHTML = "<div class='sc'><p><span>\xe4\xba\xb2\xef\xbc\x9a</span>\xe5\x88\xa0\xe9\x99\xa4\xe5\x90\x8e\xe4\xb8\x8d\xe5\x8f\xaf\xe6\x81\xa2\xe5\xa4\x8d\xef\xbc\x8c\xe7\xa1\xae\xe5\xae\x9a\xe5\x88\xa0\xe9\x99\xa4\xe5\x90\x97\xef\xbc\x9f</p><div class='left'  id='c0' onClick='zhi=0;'><p>\xe7\xa1\xae\xe5\xae\x9a</p></div><div class='right' onClick='zhi=1;' id='cl' ><p>\xe5\x8f\x96\xe6\xb6\x88</p></div></div>";
	var oclose = document.getElementById("cl");
	oclose.onclick = function () {
		document.body.removeChild(omask);
		document.body.removeChild(omask1);
	};
	var okButton = document.getElementById("c0");
	okButton.onclick = function () {
		document.body.removeChild(omask);
		document.body.removeChild(omask1);
		document.forms[0].action = str;
		document.forms[0].submit();
	};
};
		   
 //自定义评语内容验证方法  手机号码
$.validator.addMethod("isMobile", function (value, element) {
	var length = value.length;
	var mobile = /^0?(13[0-9]|15[012356789]|18[0236789]|17[0123456789]|14[57])[0-9]{8}$/;
	return this.optional(element) || mobile.test(value);
}, "\u8bf7\u6b63\u786e\u586b\u5199\u60a8\u7684\u624b\u673a\u53f7\u7801");

//区号的校验
$.validator.addMethod("isQh", function (value, element) {
	var length = value.length;
	var qh = /^0?([1-9]\d|[3-9][13579]\d|[3-9][24680]\d{2})$/;
	return this.optional(element) || qh.test(value);
}, "\u8bf7\u6b63\u786e\u586b\u5199\u60a8\u7684\u533a\u53f7"); 
			
            
//自定义评语内容验证方法  -电话号码
$.validator.addMethod("isPhone", function (value, element) {
	var length = value.length;
	var tel = /^[2-9][0-9]{6,7}$/;
	return this.optional(element) || tel.test(value);
}, "\u8bf7\u6b63\u786e\u586b\u5199\u60a8\u7684\u8054\u7cfb\u7535\u8bdd"); 

//自定义评语内容验证方法  -分机
$.validator.addMethod("isFenji", function (value, element) {
	var fenji =  /^(0|[1-9][0-9]{0,3})$/;
	return this.optional(element) || fenji.test(value);
}, "分机号码必须填写"); 
                 
           
// 下拉框验证 
$.validator.addMethod("selectNone", function (value, element) {
	if (value != "--\u8bf7\u9009\u62e9--") {
		return true;
	} else {
		return false;
	}
}, "\u5fc5\u987b\u9009\u62e9\u4e00\u9879");

// 编码校验
$.validator.addMethod("xcjCode", function (value, element) {
	 var codes = /^[a-zA-Z][\a-zA-Z0-9_]{2,10}$/;
	return this.optional(element) || codes.test(value);
}, "字符必须是字母，数字、下划线、横线，必须以字母开头，位数在2-10位");




//身份证号码的校验
$.validator.addMethod("isIdCardNo", function (value, element) {
	 return this.optional(element) || isIdCardNo(value);
}, "\u8bf7\u6b63\u786e\u586b\u5199\u60a8\u7684\u533a\u53f7"); 
    
	
//增加身份证验证
function isIdCardNo(num) {
    var factorArr = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
    var parityBit = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");
    var varArray = new Array();
    var intValue;
    var lngProduct = 0;
    var intCheckDigit;
    var intStrLen = num.length;
    var idNumber = num;
    // initialize
    if ((intStrLen != 15) && (intStrLen != 18)) {
        return false;
    }
    // check and set value
    for (i = 0; i < intStrLen; i++) {
        varArray[i] = idNumber.charAt(i);
        if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
            return false;
        } else if (i < 17) {
            varArray[i] = varArray[i] * factorArr[i];
        }
    }
    if (intStrLen == 18) {
        //check date
        var date8 = idNumber.substring(6, 14);
        if (isDate8(date8) == false) {
            return false;
        }
        // calculate the sum of the products
        for (i = 0; i < 17; i++) {
            lngProduct = lngProduct + varArray[i];
        }
        // calculate the check digit
        intCheckDigit = parityBit[lngProduct % 11];
        // check last digit
        if (varArray[17] != intCheckDigit) {
            return false;
        }
    }
    else {        //length is 15
        //check date
        var date6 = idNumber.substring(6, 12);
        if (isDate6(date6) == false) {
            return false;
        }
    }
    return true;
}
//增加身份证验证-用到的方法
function isDate6(sDate) {
    if (!/^[0-9]{6}$/.test(sDate)) {
        return false;
    }
    var year, month, day;
    year = sDate.substring(0, 4);
    month = sDate.substring(4, 6);
    if (year < 1700 || year > 2500) return false
    if (month < 1 || month > 12) return false
    return true
}
//增加身份证验证-用到的方法
function isDate8(sDate) {
    if (!/^[0-9]{8}$/.test(sDate)) {
        return false;
    }
    var year, month, day;
    year = sDate.substring(0, 4);
    month = sDate.substring(4, 6);
    day = sDate.substring(6, 8);
    var iaMonthDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
    if (year < 1700 || year > 2500) return false
    if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) iaMonthDays[1] = 29;
    if (month < 1 || month > 12) return false
    if (day < 1 || day > iaMonthDays[month - 1]) return false
    return true
}
