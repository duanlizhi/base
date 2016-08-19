
function temp(window){
    //*** 屏蔽右键 ***
    function click(e) {
        if (window.document.all) {
            if (window.event.button == 1 || window.event.button == 2 || window.event.button == 3) {
                try {
                    for (var i = 0; i < imgs.size(); i++) {
                            window.document.imgs[i].onmousedown = function () {
                            return;
                        };
                    }
                } catch (e) {
                }
                oncontextmenu = 'return';
            }
        }
        if (window.document.layers) {
            if (e.which == 3) {
                oncontextmenu = 'return';
            }
        }
    }

    if (window.document.layers) {
        window.document.captureEvents(Event.MOUSEDOWN);
    }
    window.document.onmousedown = click;
    window.document.oncontextmenu = new Function("return false;");
//*******************************************
    window.document.onkeydown = function (evt) {

        try {
            if (window.document.selection.createRange().parentElement().type == "file") {
                return;
            }
        } catch (e) {

        }
        if ((window.event.keyCode == 116) || //屏蔽 F5 刷新键
            (window.event.ctrlKey && window.event.keyCode == 82)) { //Ctrl + R
            window.event.keyCode = 0;
            window.event.returnValue = false;
            return;
        }
        if ((window.event.altKey) && (window.event.keyCode == 115)) { //屏蔽Alt+F4
            return;
        }

    }
}


function temp1(window){
    var frams = window.document.frames;
    var imgs = window.document.images;
    temp(window);
    try{
        for (var i = 0; i < frams.size(); i++) {
            temp(frams[i].contentWindow);
        }
    }catch(e){}
}

