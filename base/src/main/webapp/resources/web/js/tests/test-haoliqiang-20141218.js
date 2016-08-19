//诊断仪
function zdy() {
	var omaskzdy = document.createElement("div");
	omaskzdy.id = "mask-zdy";
	document.body.appendChild(omaskzdy);
	omaskzdy.innerHTML = "<div class='zdy'><iframe class='iframezdy' src='diagnostic/dnc_1.html' scrolling='no' id='zdyPowerIframe'></iframe><div class='zdy-power'><P onclick='clickZdyPower()'>POWER</P></div><div class='zdy-return'><p onclick='zdyHtmlBack()'>\u8fd4\u56de</p></div><div class='coe' id='zdyy'></div></div>";
	var zdy = document.getElementById("zdyy");
	zdy.onclick = function () {
		zdySwitch = 0;
		document.body.removeChild(omaskzdy);
	};
	$(function () {
		$("#mask-zdy").draggable({containment:".container", scroll:false});
	});
}

//工具点击弹出层
function popup(id) {
	$("#" + id + "").attr("class", "testConcent-menuBellactive");
	var omask = document.createElement("div");
	omask.id = "mask-popup";
	document.body.appendChild(omask);
	omask.innerHTML = "<div class='popup' id='shanchu2'><iframe src='' class='popupIframe' id='popupIframe' name='popupIframe' scrolling='no'></iframe></div>";
	var oclose = document.getElementById("shanchu2");
	oclose.onclick = function () {
		document.body.removeChild(omask);
	};
}
//select
$(function () {
	$(".select").each(function () {
		var s = $(this);
		var z = parseInt(s.css("z-index"));
		var dt = $(this).children("dt");
		var dd = $(this).children("dd");
		var _show = function () {
			dd.slideDown(200);
			dt.addClass("cur");
			s.css("z-index", z + 1);
			dt.css("background", "#ff6c00 url(/resources/web/image/wbtTestPlayer/selecticon.png) no-repeat 74px -27px");
		};   //展开效果
		var _hide = function () {
			dd.slideUp(200);
			dt.removeClass("cur");
			s.css("z-index", z);
			dt.css("background", "#ff6c00 url(/resources/web/image/wbtTestPlayer/selecticon.png) no-repeat 74px 8px");
		};    //关闭效果
		dt.click(function () {
			dd.is(":hidden") ? _show() : _hide();
		});
		dd.find("a").click(function () {
			dt.html($(this).html());
			_hide();
		});     //选择效果（如需要传值，可自定义参数，在此处返回对应的“value”值 ）
		$("body").click(function (i) {
			!$(i.target).parents(".select").first().is(s) ? _hide() : "";
		});
	});
});
$(function () {
	$(".selectt").each(function () {
		var s = $(this);
		var z = parseInt(s.css("z-index"));
		var dt = $(this).children("dt");
		var dd = $(this).children("dd");
		var _show = function () {
			dd.slideDown(200);
			dt.addClass("cur");
			s.css("z-index", z + 1);
		};   //展开效果
		var _hide = function () {
			dd.slideUp(200);
			dt.removeClass("cur");
			s.css("z-index", z);
		};    //关闭效果
		dt.click(function () {
			dd.is(":hidden") ? _show() : _hide();
		});
		dd.find("a").click(function () {
			dt.html($(this).html());
			_hide();
		});     //选择效果（如需要传值，可自定义参数，在此处返回对应的“value”值 ）
		$("body").click(function (i) {
			!$(i.target).parents(".selectt").first().is(s) ? _hide() : "";
		});
	});
});

