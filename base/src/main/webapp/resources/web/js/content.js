//获取操作系统
function detectOS() {
	var sUserAgent = navigator.userAgent;
	var isWin = (navigator.platform == "Win32") || (navigator.platform == "Windows");
	var isMac = (navigator.platform == "Mac68K") || (navigator.platform == "MacPPC") || (navigator.platform == "Macintosh") || (navigator.platform == "MacIntel");
	if (isMac) {
		return "mac";
	}
	var isUnix = (navigator.platform == "X11") && !isWin && !isMac;
	if (isUnix) {
		return "unix";
	}
	if (isWin) {
		var isWin2K = sUserAgent.indexOf("Windows NT 5.0") > -1 || sUserAgent.indexOf("Windows 2000") > -1;
		if (isWin2K) {
			return "win2000";
		}
		var isWinXP = sUserAgent.indexOf("Windows NT 5.1") > -1 || sUserAgent.indexOf("Windows XP") > -1;
		if (isWinXP) {
			return "winXP";
		}
		var isWin2003 = sUserAgent.indexOf("Windows NT 5.2") > -1 || sUserAgent.indexOf("Windows 2003") > -1;
		if (isWin2003) {
			return "win2003";
		}
		var isWinVista = sUserAgent.indexOf("Windows NT 6.0") > -1 || sUserAgent.indexOf("Windows Vista") > -1;
		if (isWinVista) {
			return "winVista";
		}
		var isWin7 = sUserAgent.indexOf("Windows NT 6.1") > -1 || sUserAgent.indexOf("Windows 7") > -1;
		if (isWin7) {
			return "win7";
		}
		var isWin8 = sUserAgent.indexOf("Windows NT 6.2") > -1 || sUserAgent.indexOf("Windows 8") > -1;
		if (isWin8) {
			return "win8";
		}
		
		var isWin81 = sUserAgent.indexOf("Windows NT 6.3") > -1 || sUserAgent.indexOf("Windows 8.1") > -1;
		if (isWin81) {
			return "win8.1";
		}
		
	}
	
	var isLinux = (String(navigator.platform).indexOf("Linux") > -1);
	var bIsAndroid = sUserAgent.toLowerCase().match(/android/i) == "android";
	var isAndroidWeixin =((sUserAgent.indexOf('MQQBrowser')>=0)&&(sUserAgent.indexOf('MicroMessenger')>=0)&&(sUserAgent.indexOf('NetType')>=0));
	var isAndroidQQIn = ((sUserAgent.indexOf('MQQBrowser')>=0)&&(sUserAgent.indexOf('QQ')>=0)&&(sUserAgent.indexOf('NetType')>=0));
	var isAndroidQQQone= ((sUserAgent.indexOf('MQQBrowser')>=0)&&(sUserAgent.indexOf('Qzone')>=0));
	var isAndroidQQBrowser = ((sUserAgent.indexOf('MQQBrowser')>=0)&&!(sUserAgent.indexOf('MicroMessenger')>=0)&&!(sUserAgent.indexOf('NetType')>=0));
	var isAndroidOupeng = (sUserAgent.indexOf("Oupeng")>=0);
	var isAndroidLieBaoFast = (sUserAgent.indexOf("LieBaoFast")>=0);
	var isAndroidYdb = (sUserAgent.indexOf("Ydb")>=0);
	var isAndroidMbBrowser =((sUserAgent.indexOf('Mb')>=0)&&(sUserAgent.indexOf('Browser')>=0));
	var isAndroidFirefox = (sUserAgent.indexOf("Firefox")>=0);
	var isAndroidBaidu = (sUserAgent.indexOf("Baidu")>=0);
	var isAndroidSogouMSE =(sUserAgent.indexOf("SogouMSE")>=0);
	var isAndroidUCBrowser = (sUserAgent.indexOf("UCBrowser")>=0);
	var isAndroidUCAphone = (sUserAgent.indexOf("Aphone")>=0);
	if (isLinux) {
		if (bIsAndroid){
			 if(isAndroidWeixin){
			   return "android_WeiXin";
			 }
			 if(isAndroidQQIn){
			   return "android_QQ";
			 }
			  if(isAndroidQQQone){
			   return "android_QQ_Qzone";
			 }
			 if(isAndroidQQBrowser){
			   return "android_QQ_browser";
			 }
			 
			  if(isAndroidOupeng){
			   return "android_Oupeng";
			 }
			   if(isAndroidLieBaoFast){
			   return "android_lieBao";
			 }
			   if(isAndroidYdb){
			   return "android_Ydb";
			 }
			  if(isAndroidMbBrowser){
			   return "android_MBbrowser";
			 }
			  if(isAndroidFirefox){
			   return "android_FireFox";
			 }
			 if(isAndroidBaidu){
			   return "android_Baidu";
			 }
			  if(isAndroidSogouMSE){
			   return "android_SogouMSE";
			 }
			 if(isAndroidUCBrowser){
			   return "android_UC_browser";
			 }
			 if(isAndroidUCAphone){
			   return "android_360_Aphone_browser";
			 }
			return "android";
		} else {
			return "linux";
		}
	}
	
	var isIphone = (sUserAgent.indexOf("iPhone") != -1);
	var isIPad = (sUserAgent.indexOf("iPad") != -1);
	var iPod = (sUserAgent.indexOf("iPod") != -1);
	var isSymbian = (sUserAgent.indexOf("SymbianOS") != -1);
	var isWphone = (sUserAgent.indexOf("Windows Phone") != -1);
	
	var isWeixinIpad = sUserAgent.toLowerCase().match(/MicroMessenger/i) == "micromessenger";
	var isWeixinIphone = sUserAgent.toLowerCase().match(/MicroMessenger/i) == "micromessenger";
	var isQQIpad =(sUserAgent.indexOf("IPadQQ")>=0);
	var isQQMQQBrowser =(sUserAgent.indexOf("MQQBrowser")>=0);
	var isBaiduHD =(sUserAgent.indexOf("BaiduHD")>=0);
	var isCriOS =(sUserAgent.indexOf("CriOS")>=0);
	var isSafari =(sUserAgent.indexOf("Safari")>=0);
	var isQQIphone=(sUserAgent.indexOf("QQ")>=0);
	var isQzoneIphone = (sUserAgent.indexOf("Qzone")>=0);
	//IpadQQ空间不能使用。取不到特殊标识
	if (isIphone) {
		if(isQQIphone){
			return "iphone_qq";
		}
		if(isWeixinIphone){
			return "iphone_weixin";
		}
		if(isSafari){
			return "iphone_Safari";
		}
		if(isQzoneIphone){
			return "iphone_Qzone";
		}
		return "iphone";
	}
	if(isIPad) {
		if(isWeixinIpad){
			return "ipad_WeiXin";
		}
		if(isQQIpad){
			return "IPadQQ";
		}
		if(isQQMQQBrowser){
			return "Ipad_MQQBrowser";
		}
		if(isBaiduHD){
			return "Ipad_BaiDuHD";
		}
		if(isCriOS){
			return "Ipad_Chrome";
		}
		if(isSafari){
			return "Ipad_Safari";
		}
		
		return "ipad"
	}
	
	if (iPod) {
		return "iPod";
	}
	if (isSymbian) {
		return "symbian";
	}
	if (isWphone) {
		return "windows phone";
	}
	return "other";
}
		
	//获取浏览器版本		
function browserinfo() {
	var Browser_Name = navigator.appName;
	var Browser_Version = parseFloat(navigator.appVersion);
	var Browser_Agent = navigator.userAgent;
	var Actual_Version, Actual_Name;
	var is_IE = (Browser_Name == "Microsoft Internet Explorer");//判读是否为ie浏览器
	var is_NN = (Browser_Name == "Netscape");//判断是否为netscape浏览器
	var is_op = (Browser_Name == "Opera");//判断是否为Opera浏览器
	if (is_NN) {
            //upper 5.0 need to be process,lower 5.0 return directly
		if (Browser_Version >= 5) {
			if (Browser_Agent.indexOf("Netscape") != -1) {
				var Split_Sign = Browser_Agent.lastIndexOf("/");
				var Version = Browser_Agent.lastIndexOf(" ");
				var Bname = Browser_Agent.substring(0, Split_Sign);
				var Split_sign2 = Bname.lastIndexOf(" ");
				Actual_Version = Browser_Agent.substring(Split_Sign + 1, Browser_Agent.length);
				Actual_Name = Bname.substring(Split_sign2 + 1, Bname.length);
			}
			if (Browser_Agent.indexOf("Firefox") != -1) {
				var Split_Sign = Browser_Agent.lastIndexOf("/");
				var Version = Browser_Agent.lastIndexOf(" ");
				Actual_Version = Browser_Agent.substring(Split_Sign + 1, Browser_Agent.length);
				Actual_Name = Browser_Agent.substring(Version + 1, Split_Sign);
			}
			if (Browser_Agent.indexOf("Safari") != -1) {
				if (Browser_Agent.indexOf("Chrome") != -1) {
					var Split_Sign = Browser_Agent.lastIndexOf(" ");
					var Version = Browser_Agent.substring(0, Split_Sign);
					var Split_Sign2 = Version.lastIndexOf("/");
					var Bname = Version.lastIndexOf(" ");
					Actual_Version = Version.substring(Split_Sign2 + 1, Version.length);
					Actual_Name = Version.substring(Bname + 1, Split_Sign2);
				} else {
					var Split_Sign = Browser_Agent.lastIndexOf("/");
					var Version = Browser_Agent.substring(0, Split_Sign);
					var Split_Sign2 = Version.lastIndexOf("/");
					var Bname = Browser_Agent.lastIndexOf(" ");
					Actual_Version = Browser_Agent.substring(Split_Sign2 + 1, Bname);
					Actual_Name = Browser_Agent.substring(Bname + 1, Split_Sign);
				}
			}
		} else {
			Actual_Version = Browser_Version;
			Actual_Name = Browser_Name;
		}
	} else {
		if (is_IE) {
			var Version_Start = Browser_Agent.indexOf("MSIE");
			var Version_End = Browser_Agent.indexOf(";", Version_Start);
			Actual_Version = Browser_Agent.substring(Version_Start + 5, Version_End);
			Actual_Name = Browser_Name;
			if (Browser_Agent.indexOf("Maxthon") != -1 || Browser_Agent.indexOf("MAXTHON") != -1) {
				var mv = Browser_Agent.lastIndexOf(" ");
				var mv1 = Browser_Agent.substring(mv, Browser_Agent.length - 1);
				mv1 = "\u9068\u6e38\u7248\u672c:" + mv1;
				Actual_Name += "(Maxthon)";
				Actual_Version += mv1;
			}
		} else {
			if (Browser_Agent.indexOf("Opera") != -1) {
				Actual_Name = "Opera";
				var tempstart = Browser_Agent.indexOf("Opera");
				var tempend = Browser_Agent.length;
				Actual_Version = Browser_Version;
			} else {
				Actual_Name = "Unknown Navigator";
				Actual_Version = "Unknown Version";
			}
		}
	}
	/*------------------------------------------------------------------------------
         --Your Can Create new properties of navigator(Acutal_Name and Actual_Version) --
         --Userage:                                                                     --
         --1,Call This Function.                                                        --
         --2,use the property Like This:navigator.Actual_Name/navigator.Actual_Version;--
         ------------------------------------------------------------------------------*/
	navigator.Actual_Name = Actual_Name;
	navigator.Actual_Version = Actual_Version;
	/*---------------------------------------------------------------------------
         --Or Made this a Class.                                                     --
         --Userage:                                                                  --
         --1,Create a instance of this object like this:var browser=new browserinfo;--
         --2,user this instance:browser.Version/browser.Name;                        --
         ---------------------------------------------------------------------------*/
	this.Name = Actual_Name;
	this.Version = Actual_Version;
}
//获取所在的URL

var getParam = function(name){
        var search = document.location.search;
        var pattern = new RegExp("[?&]"+name+"\=([^&]+)", "g");
        var matcher = pattern.exec(search);
        var items = null;
        if(null != matcher){
                try{
                        items = decodeURIComponent(decodeURIComponent(matcher[1]));
                }catch(e){
                        try{
                                items = decodeURIComponent(matcher[1]);
                        }catch(e){
                                items = matcher[1];
                        }
                }
        }
        return items;
};
