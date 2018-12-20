// Disable all tools
function disableAllTools() {
    _study_ui.forEachElement(function (element) {
        cornerstoneTools.wwwc.disable(element);
        cornerstoneTools.pan.activate(element, 2); // 2 is middle mouse button
        cornerstoneTools.zoom.activate(element, 4); // 4 is right mouse button
        //cornerstoneTools.probe.deactivate(element, 1);
        cornerstoneTools.length.deactivate(element, 1);
        cornerstoneTools.angle.deactivate(element, 1);
        cornerstoneTools.ellipticalRoi.deactivate(element, 1);
        cornerstoneTools.rectangleRoi.deactivate(element, 1);
        cornerstoneTools.stackScroll.deactivate(element, 1);
        cornerstoneTools.wwwcTouchDrag.deactivate(element);
        cornerstoneTools.zoomTouchDrag.deactivate(element);
        cornerstoneTools.panTouchDrag.deactivate(element);
        cornerstoneTools.stackScrollTouchDrag.deactivate(element);
        cornerstoneTools.arrowAnnotate.deactivate(element, 1);
        cornerstoneTools.arrowAnnotateTouch.deactivate(element);
        cornerstoneTools.dragProbe.disable(element);
        cornerstoneTools.dragProbeTouch.disable(element);
    });
}

function setupButtons() {
    cornerstoneTools.dragProbe.strategy = cornerstoneTools.dragProbe.strategies.minimal;

    // WW/WL
    $('#btn-windowing').on('click touchstart', function () {
        action_windowing();
    });

    //预选窗
    $('#btn-preset').on('click touchstart', function () {
        action_presetWindowing();
    });

    //伪彩
    $('#btn-pseudocolor').on('click touchstart', function () {
        action_set_pseudocolor();
    });


    // Invert
    $('#btn-inverse').on('click touchstart', function () {
        action_inverse();
    });
    // Zoom
    $('#btn-zoom').on('click touchstart', function () {
        action_zoom();
    });

    //放大镜
    $('#btn-magnifier').on('click touchstart', function () {
        action_magnifier();
    });

    // Pan
    $('#btn-pan').on('click touchstart', function () {
        action_pan();
    });

    // rotation left
    $('#btn-rotation-left').on('click touchstart', function () {
        action_rotation_left();
    });

    // rotation right
    $('#btn-rotation-right').on('click touchstart', function () {
        action_rotation_right();
    });

    $('#btn-rotation-180H').on('click touchstart', function () {
      action_rotation_H();
    });

    $('#btn-rotation-180V').on('click touchstart', function () {
      action_rotation_V();
    });
    //reset
    $('#btn-reset').on('click touchstart', function () {
        action_reset();
    });

    // Length measurement
    $('#btn-line').on('click touchstart', function () {
        action_m_line();
    });

    // Angle measurement
    $('#btn-angle').on('click touchstart', function () {
        action_m_angle();
    });

    // Pixel probe
    $('#btn-probe').on('click touchstart', function () {
        action_m_probe();
    });

    // Elliptical ROI
    $('#btn-ellipse').on('click touchstart', function () {
        action_m_ellipse();
    });

    // Annotation
    $('#btn-annotation').on('click touchstart', function () {
        action_m_annotation();
    });

    // Rectangle ROI
    $('#btn-rectangle').on('click touchstart', function () {
        action_m_rectangle();
    });


    // Rectangle eraser
    $('#btn-eraser').on('click touchstart', function () {
        action_eraser();
    });

    // Play clip
    $('#btn-play').on('click touchstart', function () {
        action_play();
    });

    // Stop clip
    $('#btn-stop').on('click touchstart', function () {
        action_stop_play();
    });

    // mpr
    $('#btn-mpr').on('click touchstart', function () {
        action_mpr();
    });

    // save image
    $('#btn-save_image').on('click touchstart', function () {
        action_saveJpg();
    });

    // referenceLines
    $('#btn-reference-line').on('click touchstart', function () {
        action_switchReferenceLine();
    });

    // 挂片
    $('#btn-hang-settings').on('click touchstart', function () {
        action_hang_settings();
    });

    new insertTable($("#btn-layout"), {
        min: [2, 2], max: [6, 6],
        insert: function (rows, cols) {
            _study_ui.layout = [cols, rows];
            _study_ui.renderLayout();
        }
    });
};

function changeBtnState(el) {
    $('#measure-menu').hide();
    $('.tool').removeClass('selected');
    $(el).addClass('selected');
}

//挂片协议
function action_hang_settings() {
    layer.open({
        type: 1,
        title: '挂片协议设置',
        skin: 'layui-layer-rim', //加上边框
        area: ['612px', '400px'], //宽高
        content: $('#hangSettingsWrapper').html()
    });
}

//switch ReferenceLine
function action_switchReferenceLine() {
    var ele = document.getElementById('btn-reference-line');
    changeBtnState(ele);
    _study_ui.forEachElement(function (element) {
        if(ReferenceLineOn == true) {
            cornerstoneTools.referenceLines.tool.disable(element);
        } else {
            cornerstoneTools.referenceLines.tool.enable(element);
        }

        ReferenceLineOn = !ReferenceLineOn;
    });
}

//切换调窗
function action_windowing() {
    disableAllTools();
    var ele = document.getElementById('btn-windowing');
    changeBtnState(ele);
    _study_ui.forEachElement(function (element) {
        cornerstoneTools.wwwc.activate(element, 1);
        cornerstoneTools.wwwcTouchDrag.activate(element);
    });
}

function action_mpr() {
  alert(1)
    var attrObj = $('.viewportContainer.selected .rendered');
    var study_instance_uid = attrObj.attr('data-study');
    var series_number = attrObj.attr('data-series');
    var modality = attrObj.attr('data-modality');
    var instance_count = parseInt(attrObj.attr('data-instance-count'));

	$('.viewportContainer.selected').find('.infoLayer').hide();
    console.log(modality)
    if ((modality == 'CT' || modality == 'MR') && instance_count > 5) {
        $('.viewportContainer.selected .topmode').show();
        //mpr url
        var mpr_url = 'mpr/index.html?oid=' + _orgId + '&study_instance_uid=' + study_instance_uid + '&series_number=' + series_number + '&t=' + new Date().getTime();
        attrObj.hide();
        console.log(mpr_url)
        $('.viewportContainer.selected .imageViewerViewport_MPR').show();
        $('.viewportContainer.selected .topmode').show();
        $('.viewportContainer.selected iframe').attr('src', mpr_url);
    }

    $('.mode_2d').unbind('click').bind('click', function () {
        $(this).parent().parent().find('.imageViewerViewport_MPR').hide();
        $(this).parent().parent().find('.imageViewerViewport').show();
		$(this).parent().parent().find('.infoLayer').show();
    });
    $('.mode_mpr').unbind('click').bind('click', function () {
        $(this).parent().parent().find('.imageViewerViewport_MPR').show();
        $(this).parent().parent().find('.imageViewerViewport').hide();
		$(this).parent().parent().find('.infoLayer').hide();
    })
}
//保存图像
function action_saveJpg() {
    /*var element = $('.viewportContainer.selected .rendered')[0];
    var filename = $(element).parent().find('.sliceText').text().replace('Im: ', '').replace("/", "");
    if (filename == '') {
        filename = new Date().getTime();
    }
    cornerstoneTools.saveAs(element, filename + '.png');*/
    // console.log(imageIds[nowDICOM]);
    var url = imageIds[nowDICOM];
    url = url.replace('dicomweb','http')
  // console.log(url)
    window.open(url)
    return false;
}

//设置窗宽窗位值
function action_setWindowing(ww, wc) {
    var element = $('.viewportContainer.selected .rendered')[0];
    var viewport = cornerstone.getViewport(element);
    viewport.voi.windowWidth = parseFloat(ww);
    viewport.voi.windowCenter = parseFloat(wc);
    cornerstone.setViewport(element, viewport);
}

//弹出窗宽窗位预设框
function action_presetWindowing() {
    layer.open({
        type: 1,
        title: '窗宽窗位预设',
        skin: 'layui-layer-rim', //加上边框
        area: ['612px', '400px'], //宽高
        content: ['<div>',
            '            <ul class="windowingShortcutsWrapper">',
            '                <li data-ww="90" data-wl="35">',
            '                    <span class="title">头颅平扫</span>',
            '                    <span class="value">90/35</span>',
            '                </li>',
            '                <li data-ww="85" data-wl="40">',
            '                    <span class="title">头颅增强</span>',
            '                    <span class="value">85/40</span>',
            '                </li>',
            '                <li data-ww="1600" data-wl="450">',
            '                    <span class="title">头颅骨窗</span>',
            '                    <span class="value">1600/450</span>',
            '                </li>',
            '                <li data-ww="350" data-wl="40">',
            '                    <span class="title">椎间盘软组织</span>',
            '                    <span class="value">350/40</span>',
            '                </li>',
            '                <li data-ww="2000" data-wl="450">',
            '                    <span class="title">椎间盘骨窗</span>',
            '                    <span class="value">2000/450</span>',
            '                </li>',
            '                <li data-ww="1600" data-wl="550">',
            '                    <span class="title">关节骨窗</span>',
            '                    <span class="value">1600/550</span>',
            '                </li>',
            '                <li data-ww="300" data-wl="40">',
            '                    <span class="title">关节软组织</span>',
            '                    <span class="value">300/40</span>',
            '                </li>',
            '            </ul>',
            '            <ul class="windowingShortcutsWrapper">',
            '                <li data-ww="350" data-wl="35">',
            '                    <span class="title">鼻窦软组织</span>',
            '                    <span class="value">350/35</span>',
            '                </li>',
            '                <li data-ww="2000" data-wl="450">',
            '                    <span class="title">鼻窦骨窗</span>',
            '                    <span class="value">2000/450</span>',
            '                </li>',
            '            </ul>',
            '            <ul class="windowingShortcutsWrapper">',
            '            </ul>',
            '            <ul class="windowingShortcutsWrapper">',
            '                <li data-ww="1000" data-wl="-650">',
            '                    <span class="title">肺窗</span>',
            '                    <span class="value">1000/-650</span>',
            '                </li>',
            '                <li data-ww="350" data-wl="40">',
            '                    <span class="title">纵膈窗</span>',
            '                    <span class="value">350/40</span>',
            '                </li>',
            '                <li data-ww="1500" data-wl="-500">',
            '                    <span class="title">肺高分辨率</span>',
            '                    <span class="value">1500/-500</span>',
            '                </li>',
            '            </ul>',
            '            <ul class="windowingShortcutsWrapper">',
            '                <li data-ww="200" data-wl="50">',
            '                    <span class="title">肝脏</span>',
            '                    <span class="value">200/50</span>',
            '                </li>',
            '                <li data-ww="350" data-wl="40">',
            '                    <span class="title">肾脏</span>',
            '                    <span class="value">350/40</span>',
            '                </li>',
            '            </ul>',
            '        </div>',
            '        <div style="padding-left: 5px;margin-top: 20px;">',
            '            <input id="preset_window_ww" placeholder="自定义窗宽" type="number" style="width: 140px;">',
            '            <input id="preset_window_wl" placeholder="自定义窗位" type="number" style="width: 140px;">',
            '            &nbsp;<button id="preset_window_ok">确定</button>',
            '        </div>'].join("")
    });

    $('.windowingShortcutsWrapper li').click(function () {
        var ww = $(this).attr('data-ww');
        var wc = $(this).attr('data-wl');
        if (ww != '' && wc != '') {
            action_setWindowing(ww, wc);
        }
        layer.closeAll();
    });

    $('#preset_window_ok').click(function () {
        var ww = $('#preset_window_ww').val();
        var wc = $('#preset_window_wl').val();
        if (ww != '' && wc != '') {
            action_setWindowing(ww, wc);
        }
        layer.closeAll();
    });
}

//切换反色
function action_inverse() {

    var ele = document.getElementById('btn-inverse');
    changeBtnState(ele);
	var element = $('.viewportContainer.selected .rendered')[0];

	var viewport = cornerstone.getViewport(element);
	// Toggle invert
	if (viewport.invert === true) {
		viewport.invert = false;
	} else {
		viewport.invert = true;
	}
	cornerstone.setViewport(element, viewport);
}

//切换缩放
function action_zoom() {
    disableAllTools();
    var ele = document.getElementById('btn-zoom');
    changeBtnState(ele);
    _study_ui.forEachElement(function (element) {
        cornerstoneTools.zoom.activate(element, 5); // 5 is right mouse button and left mouse button
        cornerstoneTools.zoomTouchDrag.activate(element);
    });
}

//放大镜
function action_magnifier() {

}

//切换平移
function action_pan() {
    disableAllTools();
    var ele = document.getElementById('btn-pan');
    changeBtnState(ele);
    _study_ui.forEachElement(function (element) {
        cornerstoneTools.pan.activate(element, 3); // 3 is middle mouse button and left mouse button
        cornerstoneTools.panTouchDrag.activate(element);
    });
}
//切换左旋
function action_rotation_left() {
    disableAllTools();
    var ele = document.getElementById('btn-rotation-left');
    changeBtnState(ele);

	var element = $('.viewportContainer.selected .rendered')[0];
	cornerstoneTools.zoom.activate(element, 5); // 5 is right mouse button and left mouse button
	cornerstoneTools.zoomTouchDrag.activate(element);
	var viewport = cornerstone.getViewport(element);
	viewport.rotation -= 90;
	cornerstone.setViewport(element, viewport);
}
//切换右旋
function action_rotation_right() {
    disableAllTools();
    var ele = document.getElementById('btn-rotation-right');
    changeBtnState(ele);

	var element = $('.viewportContainer.selected .rendered')[0];
	cornerstoneTools.zoom.activate(element, 5); // 5 is right mouse button and left mouse button
	cornerstoneTools.zoomTouchDrag.activate(element);
	var viewport = cornerstone.getViewport(element);
	viewport.rotation += 90;
	cornerstone.setViewport(element, viewport);
}

//水平翻转
function action_rotation_H() {
  disableAllTools();
  var ele = document.getElementById('btn-rotation-180H');
  changeBtnState(ele);

  var element = $('.viewportContainer.selected .rendered')[0];
  cornerstoneTools.zoom.activate(element, 5); // 5 is right mouse button and left mouse button
  cornerstoneTools.zoomTouchDrag.activate(element);
  var viewport = cornerstone.getViewport(element)
  var rotation = Math.abs(viewport.rotation)
  if(rotation == 90 || rotation == 270){
    viewport.vflip = !viewport.vflip;
  }else{
    viewport.hflip = !viewport.hflip;
  }
  cornerstone.setViewport(element, viewport);
}

//垂直翻转
function action_rotation_V() {
  disableAllTools();
  var ele = document.getElementById('btn-rotation-180V');
  changeBtnState(ele);

  var element = $('.viewportContainer.selected .rendered')[0];
  cornerstoneTools.zoom.activate(element, 5); // 5 is right mouse button and left mouse button
  cornerstoneTools.zoomTouchDrag.activate(element);
  var viewport = cornerstone.getViewport(element);
  var rotation = Math.abs(viewport.rotation)
  if(rotation == 90 || rotation == 270){
    viewport.hflip = !viewport.hflip;
  }else{
    viewport.vflip = !viewport.vflip;
  }
  cornerstone.setViewport(element, viewport);
}

//切换重置
function action_reset() {
	var element = $('.viewportContainer.selected .rendered')[0];
	cornerstone.reset(element);
}
//切换直线测量
function action_m_line() {
    disableAllTools();
    $('#measure-menu').hide();
    _study_ui.forEachElement(function (element) {
        cornerstoneTools.length.activate(element, 1);
        cornerstoneTools.lengthTouch.activate(element);
    });
}
//切换角度测量
function action_m_angle() {
    disableAllTools();
    $('#measure-menu').hide();
    _study_ui.forEachElement(function (element) {
        cornerstoneTools.angle.activate(element, 1);
        cornerstoneTools.angleTouch.activate(element);
    });
}
//切换探针
function action_m_probe() {
    $('#measure-menu').hide();
    disableAllTools();
    _study_ui.forEachElement(function (element) {
        //cornerstoneTools.probe.activate(element, 1);
        cornerstoneTools.dragProbe.activate(element, 1);
        cornerstoneTools.dragProbeTouch.activate(element);
    });
}
//切换椭圆
function action_m_ellipse() {
    $('#measure-menu').hide();
    disableAllTools();
    _study_ui.forEachElement(function (element) {
        cornerstoneTools.ellipticalRoi.activate(element, 1);
        cornerstoneTools.ellipticalRoiTouch.activate(element);
    });
}
//切换标注
function action_m_annotation() {
    $('#measure-menu').hide();
    disableAllTools();
    // Register the dialogs using the HTML5 Dialog Polyfill
    var dialog = document.querySelector('dialog');
    dialogPolyfill.registerDialog(dialog);

    // Define a callback to get your text annotation
    // This could be used, e.g. to open a modal
    function getTextCallback(doneChangingTextCallback) {
        var dialog = $('.annotationDialog');
        var getTextInput = dialog.find('.annotationTextInput');
        var confirm = dialog.find('.annotationDialogConfirm');
        dialog.get(0).showModal();
        confirm.off('click');
        confirm.on('click', function () {
            closeHandler();
        });

        dialog.off("keypress");
        dialog.on('keypress', keyPressHandler);

        function keyPressHandler(e) {
            // If Enter is pressed, close the dialog
            if (e.which === 13) {
                closeHandler();
            }
        }

        function closeHandler() {
            dialog.get(0).close();
            doneChangingTextCallback(getTextInput.val());
            // Reset the text value
            getTextInput.val("");
        }
    }

    // Define a callback to edit your text annotation
    // This could be used, e.g. to open a modal
    function changeTextCallback(data, doneChangingTextCallback) {
        var dialog = $('.relabelDialog');
        var getTextInput = dialog.find('.annotationTextInput');
        var confirm = dialog.find('.relabelConfirm');
        var remove = dialog.find('.relabelRemove');

        getTextInput.val(data.annotationText);
        dialog.get(0).showModal();

        confirm.off('click');
        confirm.on('click', function () {
            dialog.get(0).close();
            doneChangingTextCallback(data, getTextInput.val());
        });

        // If the remove button is clicked, delete this marker
        remove.off('click');
        remove.on('click', function () {
            dialog.get(0).close();
            doneChangingTextCallback(data, undefined, true);
        });

        dialog.off("keypress");
        dialog.on('keypress', keyPressHandler);

        function keyPressHandler(e) {
            // If Enter is pressed, close the dialog
            if (e.which === 13) {
                closeHandler();
            }
        }

        function closeHandler() {
            dialog.get(0).close();
            doneChangingTextCallback(data, getTextInput.val());
            // Reset the text value
            getTextInput.val("");
        }

    }

    var config_a = {
        getTextCallback: getTextCallback,
        changeTextCallback: changeTextCallback,
        drawHandles: false,
        drawHandlesOnHover: true,
        arrowFirst: true
    }


    // Try commenting this out to see the default behaviour
    // By default, the tool uses Javascript's Prompt function
    // to ask the user for the annotation. This example uses a
    // slightly nicer HTML5 dialog element.
    cornerstoneTools.arrowAnnotate.setConfiguration(config_a);

    _study_ui.forEachElement(function (element) {
        cornerstoneTools.arrowAnnotate.activate(element, 1);
        cornerstoneTools.arrowAnnotateTouch.activate(element);
    });
}
//切换矩形
function action_m_rectangle() {
    $('#measure-menu').hide();
    disableAllTools();
    _study_ui.forEachElement(function (element) {
        cornerstoneTools.rectangleRoi.activate(element, 1);
        cornerstoneTools.rectangleRoiTouch.activate(element);
    });
}
//橡皮檫
function action_eraser() {
    _study_ui.forEachElement(function (element) {
        var toolStateManager = cornerstoneTools.globalImageIdSpecificToolStateManager;
        // Note that this only works on ImageId-specific tool state managers (for now)
        toolStateManager.clear(element)
        cornerstone.updateImage(element);
    });
}
//播放
function action_play() {
    $('#btn-play').hide();
    $('#btn-stop').show();
    _study_ui.forEachElement(function (element) {
        var stackState = cornerstoneTools.getToolState(element, 'stack');
        var frameRate = stackState.data[0].frameRate;
        // Play at a default 10 FPS if the framerate is not specified
        if (frameRate === undefined) {
            frameRate = 1;
        }
        cornerstoneTools.playClip(element, frameRate);
    });
}
//停止播放
function action_stop_play() {
    $('#btn-stop').hide();
    $('#btn-play').show();
    _study_ui.forEachElement(function (element) {
        cornerstoneTools.stopClip(element);
    });
}

function action_set_pseudocolor() {
    var ele = document.getElementById('btn-pseudocolor');
    changeBtnState(ele);
    var canvas = $('.viewportContainer.selected .rendered canvas')[0];
    var context = canvas.getContext("2d");
    var imageData = context.getImageData(0, 0, canvas.width, canvas.height);

    for (var i = 0; i < imageData.data.length; i += 4) {
        var gray = imageData.data[i];
        var R = gray, G = gray, B = gray, v = 0;
        if (gray < v && gray == v) {

        }
        else if (gray < 51 || gray == 51) {
            R = 0;
            G = 5 * (gray);
            B = 255;
        }
        else if (gray <= 102) {
            gray -= 51;
            R = 0;
            G = 255;
            B = 255 - 5 * gray;
        }
        else if (gray <= 153) {
            gray -= 102;
            R = 5 * gray;
            G = 255;
            B = 0;
        }
        else if (gray <= 204) {
            gray -= 153;
            R = 255;
            G = 255 - parseInt(128.0 * gray / 51.0 + 0.5);
            B = 0;
        } else if (gray < 255) {
            gray -= 204;
            R = 255;
            G = 127 - parseInt(127.0 * gray / 51.0 + 0.5);
            B = 0;
        }
        if (gray > v && gray < 255) {
            imageData.data[i] = R;
            imageData.data[i + 1] = G;
            imageData.data[i + 2] = B;
        }
    }

    context.putImageData(imageData, 0, 0);

}

