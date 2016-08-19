$(function () {
    $('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'Collapse this branch');
    $('.tree li.parent_li > span').on('click', function (e) {
        var children = $(this).parent('li.parent_li').find(' > ul > li');
        if (children.is(":visible")) {
            children.hide('fast');
            $(this).attr('title', 'Expand this branch').find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');
        } else {
            children.show('fast');
            $(this).attr('title', 'Collapse this branch').find(' > i').addClass('icon-minus-sign').removeClass('icon-plus-sign');
        }
        e.stopPropagation();
    });
	//选中事件
	$('.tree li> span').on('click', function (e) {
		var value=$(this).attr("value");
		$('.tree li> span').css("background","#eee")
		$(this).css("background","#FBEC88")
		//动态给编辑、删除、添加按钮赋值
		$("#add_child").attr("value",value);
		$("#delete_tree").attr("value",value);
		$("#edit_tree").attr("value",value);
	});
});