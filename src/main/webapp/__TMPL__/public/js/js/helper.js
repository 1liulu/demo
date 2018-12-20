String.prototype.format = function (args) {
    var result = this;
    if (arguments.length > 0) {
        if (arguments.length == 1 && typeof (args) == "object") {
            for (var key in args) {
                if (args[key] != undefined) {
                    var reg = new RegExp("({" + key + "})", "g");
                    result = result.replace(reg, args[key]);
                }
            }
        }
        else {
            for (var i = 0; i < arguments.length; i++) {
                if (arguments[i] != undefined) {
                    var reg = new RegExp("({)" + i + "(})", "g");
                    result = result.replace(reg, arguments[i]);
                }
            }
        }
    }
    return result;
};

String.prototype.replaceAll = function (reallyDo, replaceWith, ignoreCase) {
    if (!RegExp.prototype.isPrototypeOf(reallyDo)) {
        return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi" : "g")), replaceWith);
    } else {
        return this.replace(reallyDo, replaceWith);
    }
}

//获取get参数
function getQueryStringByName(name) {
    var result = location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
    if (result == null || result.length < 1) {
        return "";
    }
    return result[1];
}

//调整windows布局
function _adjustWindowLayout() {
    var winHeight = $(window).height();
    var winWidth = $(window).width();
    var studiesWrapperHeight = $('#studiesWrapper').height();
    var imgActionsWrapperWidth = $('#imgActionsWrapper').width();

    $('#imgActionsWrapper').height(winHeight - studiesWrapperHeight + 2);
    $('#viewerWrapper')
        .height(winHeight - studiesWrapperHeight + 2)
        .width(winWidth - imgActionsWrapperWidth);
}

//格式化检查时间
function _formatStudyDate(studyDate) {
    if (studyDate == null || studyDate == '') {
        return '未知';
    }
    if (studyDate.length > 9) {
        return studyDate.substring(0, 8);
    }
    return studyDate;
}