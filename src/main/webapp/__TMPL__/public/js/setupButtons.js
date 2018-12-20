function setupButtons() {
    cornerstoneTools.dragProbe.strategy = cornerstoneTools.dragProbe.strategies.minimal;

    // 穿越
    $('#btn_cross').on('click touchstart', function () {
        showCrossWrapper();

    });


    $('#btn-measure').on('click touchstart', function () {
        var isSelected = $('#measure-menu').css('display') != 'none';
        if (isSelected) {
            $('#measure-menu').hide();
        } else {
            changeBtnState(this);
            $('#measure-menu').show();
        }
    });

    // WW/WL
    $('#btn-windowing').on('click touchstart', function () {
        action_windowing();
        emitMessage('WCWL|' + 'na' + '|na');
    });

    // Invert
    $('#btn-inverse').on('click touchstart', function () {
        action_inverse();
        emitMessage('Inverse|' + 'na' + '|na');
    });

    // Zoom
    $('#btn-zoom').on('click touchstart', function () {
        action_zoom();
        emitMessage('Zoom|' + 'na' + '|na');
    });

    // Pan
    $('#btn-pan').on('click touchstart', function () {
        action_pan();
        emitMessage('Pan|' + 'na' + '|na');
    });

    // rotation left
    $('#btn-rotation-left').on('click touchstart', function () {
        action_rotation_left();
        emitMessage('RotationLeft|' + 'na' + '|na');
    });

    // rotation right
    $('#btn-rotation-right').on('click touchstart', function () {
        action_rotation_right();
        emitMessage('RotationRight|' + 'na' + '|na');
    });

    //reset
    $('#btn-reset').on('click touchstart', function () {
        action_reset();
        emitMessage('Reset|' + 'na' + '|na');
    });

    // Length measurement
    $('#btn-line').on('click touchstart', function () {
        action_m_line();
        emitMessage('M-Line|' + 'na' + '|na');
    });

    // Angle measurement
    $('#btn-angle').on('click touchstart', function () {
        action_m_angle();
        emitMessage('M-Angle|' + 'na' + '|na');
    });

    // Pixel probe
    $('#btn-probe').on('click touchstart', function () {
        action_m_probe();
        emitMessage('M-Probe|' + 'na' + '|na');
    });

    // Elliptical ROI
    $('#btn-ellipse').on('click touchstart', function () {
        action_m_ellipse();
        emitMessage('M-Ellipse|' + 'na' + '|na');
    });

    // Annotation
    $('#btn-annotation').on('click touchstart', function () {
        action_m_annotation();
        emitMessage('M-Annotation|' + 'na' + '|na');
    });

    // Rectangle ROI
    $('#btn-rectangle').on('click touchstart', function () {
        action_m_rectangle();
        emitMessage('M-Rectangle|' + 'na' + '|na');
    });


    // Rectangle eraser
    $('#btn-eraser').on('click touchstart', function () {
        action_eraser();
        emitMessage('Eraser|' + 'na' + '|na');
    });

    // Play clip
    $('#btn-play').on('click touchstart', function () {
        action_play();
        emitMessage('Play|' + 'na' + '|na');
    });

    // Stop clip
    $('#btn-stop').on('click touchstart', function () {
        action_stop_play();
        emitMessage('StopPlay|' + 'na' + '|na');
    });

    //会议模式下layout不可用模式
    $('#btn-layout-disabled').on('click touchstart', function () {
        $('#topPromptInfo').html('会议模式下布局功能不可用').fadeIn('slow');
        window.setTimeout(function(){
            $('#topPromptInfo').fadeOut('slow');
        },3000)
    });

    //Meeting
    $('#btn-meeting').on('click touchstart', function () {
        if (!meetingEnabled) {
            if (window.confirm('开启会议模式后，你的操作将会与其他入会者的操作形成直播，请你再次确认')) {
                enableMeeting();
                $(this).find('span').text('会议中');
                $(this).addClass('red');
                $('#btn-layout').hide();
                $('#btn-layout-disabled').show();
            }
        } else {
            if (window.confirm('关闭会议模式后，你将不再接收到会议直播，请你再次确认')) {
                meetingEnabled = false;
                $(this).find('span').text('会议');
                $(this).removeClass('red');
                $('#btn-layout-disabled').hide();
                $('#btn-layout').show();
            }
        }
    });
};

function changeBtnState(el) {
    $('#measure-menu').hide();
    $('.tool').removeClass('selected');
    $(el).addClass('selected');
}
//切换调窗
function action_windowing() {
    disableAllTools();
    var ele = document.getElementById('btn-windowing');
    changeBtnState(ele);
    forEachViewport(function (element) {
        cornerstoneTools.wwwc.activate(element, 1);
        cornerstoneTools.wwwcTouchDrag.activate(element);
    });
}
//切换反色
function action_inverse() {
    disableAllTools();
    var ele = document.getElementById('btn-inverse');
    changeBtnState(ele);
    forEachViewport(function (element) {
        var viewport = cornerstone.getViewport(element);
        // Toggle invert
        if (viewport.invert === true) {
            viewport.invert = false;
        } else {
            viewport.invert = true;
        }
        cornerstone.setViewport(element, viewport);
    });
}
//切换缩放
function action_zoom() {
    disableAllTools();
    var ele = document.getElementById('btn-zoom');
    changeBtnState(ele);
    forEachViewport(function (element) {
        cornerstoneTools.zoom.activate(element, 5); // 5 is right mouse button and left mouse button
        cornerstoneTools.zoomTouchDrag.activate(element);
    });
}
//切换平移
function action_pan() {
    disableAllTools();
    var ele = document.getElementById('btn-pan');
    changeBtnState(ele);
    forEachViewport(function (element) {
        cornerstoneTools.pan.activate(element, 3); // 3 is middle mouse button and left mouse button
        cornerstoneTools.panTouchDrag.activate(element);
    });
}
//切换左旋
function action_rotation_left() {
    disableAllTools();
    var ele = document.getElementById('btn-rotation-left');
    changeBtnState(ele);
    forEachViewport(function (element) {
        cornerstoneTools.zoom.activate(element, 5); // 5 is right mouse button and left mouse button
        cornerstoneTools.zoomTouchDrag.activate(element);
        var viewport = cornerstone.getViewport(element);
        viewport.rotation -= 90;
        cornerstone.setViewport(element, viewport);
    });
}
//切换右旋
function action_rotation_right() {
    disableAllTools();
    var ele = document.getElementById('btn-rotation-right');
    changeBtnState(ele);
    forEachViewport(function (element) {
        cornerstoneTools.zoom.activate(element, 5); // 5 is right mouse button and left mouse button
        cornerstoneTools.zoomTouchDrag.activate(element);
        var viewport = cornerstone.getViewport(element);
        viewport.rotation += 90;
        cornerstone.setViewport(element, viewport);

    });
}
//切换重置
function action_reset() {
    forEachViewport(function (element) {
        cornerstone.reset(element);
    });
}
//切换直线测量
function action_m_line() {
    disableAllTools();
    $('#measure-menu').hide();
    forEachViewport(function (element) {
        cornerstoneTools.length.activate(element, 1);
        cornerstoneTools.lengthTouch.activate(element);
    });
}
//切换角度测量
function action_m_angle() {
    disableAllTools();
    $('#measure-menu').hide();
    forEachViewport(function (element) {
        cornerstoneTools.angle.activate(element, 1);
        cornerstoneTools.angleTouch.activate(element);
    });
}
//切换探针
function action_m_probe() {
    $('#measure-menu').hide();
    disableAllTools();
    forEachViewport(function (element) {
        //cornerstoneTools.probe.activate(element, 1);
        cornerstoneTools.dragProbe.activate(element, 1);
        cornerstoneTools.dragProbeTouch.activate(element);
    });
}
//切换椭圆
function action_m_ellipse() {
    $('#measure-menu').hide();
    disableAllTools();
    forEachViewport(function (element) {
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

    forEachViewport(function (element) {
        cornerstoneTools.arrowAnnotate.activate(element, 1);
        cornerstoneTools.arrowAnnotateTouch.activate(element);
    });
}
//切换矩形
function action_m_rectangle() {
    $('#measure-menu').hide();
    disableAllTools();
    forEachViewport(function (element) {
        cornerstoneTools.rectangleRoi.activate(element, 1);
        cornerstoneTools.rectangleRoiTouch.activate(element);
    });
}

function action_eraser() {
    forEachViewport(function (element) {
        var toolStateManager = cornerstoneTools.globalImageIdSpecificToolStateManager;
        // Note that this only works on ImageId-specific tool state managers (for now)
        toolStateManager.clear(element)
        cornerstone.updateImage(element);
    });
}

function action_play() {
    $('#btn-play').hide();
    $('#btn-stop').show();
    forEachViewport(function (element) {
        var stackState = cornerstoneTools.getToolState(element, 'stack');
        var frameRate = stackState.data[0].frameRate;
        // Play at a default 10 FPS if the framerate is not specified
        if (frameRate === undefined) {
            frameRate = 10;
        }
        cornerstoneTools.playClip(element, frameRate);
    });
}
function action_stop_play(){
    $('#btn-stop').hide();
    $('#btn-play').show();
    forEachViewport(function (element) {
        cornerstoneTools.stopClip(element);
    });
}

