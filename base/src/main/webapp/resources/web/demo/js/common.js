$("#top-portrait").hover(
	function() {
	$("#top-portrait").addClass("top-portraitActive");
	$("#top-portraitDrop").fadeIn(100);
  },function() {
   $("#top-portrait").removeClass("top-portraitActive");
	$("#top-portraitDrop").fadeOut(100);
});
/*function hide (id) {
				$("#"+id.parentNode.id+"_1").hide();
				$(id).attr("onclick","show(this);");
				$("#"+id.parentNode.id+" > p > img").attr("src","../images/sectionTitle_close.png");
		}
		function show (id) {
				$("#"+id.parentNode.id+"_1").show();
				$(id).attr("onclick","hide(this);");
				$("#"+id.parentNode.id+" > p > img").attr("src","../images/sectionTitle_open.png");
		}*/
		$(".Tcellmenu").hover(
	  function () {
	    $(".menu").show();
	  },
	  function () {
	    $(".menu").hide();
	  }
	)


	function tab(Nav,Num){
      if(Nav.className=="CtabActive")return;
      var thisNav=Nav.parentNode.id;
      var thisNavParent=Nav.parentNode.parentNode.id;
      //alert(thisNavParent);
      var navName=Nav.nodeName.toLowerCase();
      var navList=document.getElementById(thisNav).getElementsByTagName(navName);
      for(var i=0;i<navList.length;i++){
              if(i==Num){
                      Nav.className="CtabActive";
                      document.getElementById("Ctab_"+thisNav+i).style.display="block";
              }else{
                      navList[i].className="Ctab";
  
                      document.getElementById("Ctab_"+thisNav+i).style.display="none";
              }
      }
}

function follow(ererer) {
	if ($(ererer).html()=="关注") {
	$(ererer).css("background"," #fff");
	$(ererer).html("已关注");
	}else{
		$(ererer).css("background","  #fff url('images/add.png') no-repeat 12px 10px");
	$(ererer).html("关注");
	}

};

function dialog(testsPostedId) {
	var omask = document.createElement("div");
	omask.id = "mask";
	document.body.appendChild(omask);
	var omaskybyb = document.createElement("div");
	omaskybyb.id = "mask-dialog";
	document.body.appendChild(omaskybyb);
	omaskybyb.innerHTML = "<div class='zdy'><div class='content'>是否进入测评页面</div><div id='zdy' class='cancel'>取消</div><div id='zdy2' class='confirm'>确定</div></div>";
	var wyb = document.getElementById("zdy");
	var wyb2 = document.getElementById("zdy2");
	wyb.onclick = function() {
		document.body.removeChild(omaskybyb);
		document.body.removeChild(omask);
	}
	wyb2.onclick = function() {
		document.body.removeChild(omaskybyb);
		document.body.removeChild(omask);
		subTestForm(testsPostedId)
	}
};
