var _study_ui = {

    //布局
    layout: [1, 1],

    //事件绑定
    event: {
        onViewportUpdated: function (layerInfoEle, e) {
            addToMetadataArray(cornerstone.getEnabledElement(e.target).image);
            var viewport = cornerstone.getViewport(e.target)
            $(layerInfoEle).find('.wlww').text("WW/WC: " + Math.round(viewport.voi.windowWidth) + "/" + Math.round(viewport.voi.windowCenter));
            $(layerInfoEle).find('.zoom').text("Zoom: " + viewport.scale.toFixed(2));
        },
        onNewImage: function (e, data) {
            var stackToolDataSource = cornerstoneTools.getToolState(e.target, 'stack');
            if (stackToolDataSource === undefined || stackToolDataSource === null) {
                return;
            }
            var stackData = stackToolDataSource.data[0];
            var newImageIdIndex = stackData.currentImageIdIndex;

            var viewerWrapper = $(e.target).parent();

            // Populate the current slice span
            viewerWrapper.find('.sliceText').html("Im: " + (newImageIdIndex + 1) + "/" + stackData.imageIds.length);

            // if we are currently playing a clip then update the FPS
            var playClipToolData = cornerstoneTools.getToolState(e.target, 'playClip');
            if (playClipToolData !== undefined && playClipToolData !== null && !$.isEmptyObject(playClipToolData.data)) {
                //$("#frameRate").text("FPS: " + Math.round(data.frameRate));
                viewerWrapper.find('.fts').text("FPS: " + Math.round(data.frameRate));
            } else {
                if (viewerWrapper.find('.fts').text().length > 0) {
                    viewerWrapper.find('.fts').text("");
                }
            }
        }
    },

    //根据影像编号从缓存中获取相关影像
    getStudyFromHistories: function (study_instance_uid) {
        var study = null;
        for (var i = 0; i < _studies.length; i++) {
            if (_studies[i].study_instance_uid == study_instance_uid) {
                study = _studies[i];
                break;
            }
        }
        return study;
    },

    //根据序列编号从缓存中获取相关序列对象
    getSeriesFromStudy: function (study, seriesNumber, seriesNumberUid) {
        var series = null;
        for (var i = 0; i < study.series.length; i++) {
            if (study.series[i].series_number == seriesNumber && study.series[i].series_instance_uid == seriesNumberUid) {
                series = study.series[i];
                break;
            }
        }
        return series;
    },

    //遍历所有viewport
    forEachElement: function (callback) {
      if(!blLoad){
        blLoad = true;
        $($('.preview_main li')[0]).click();
        return;
      }
      $('.rendered').each(function (i, n) {

        callback(n);
      });


    },

    //渲染历史影像
    renderHistoryStudies: function (studies) {
      console.log(studies)
        var html_nav = [];
        var html_series = [];
      // var tpl_nav = '<div data-id="{0}" class="study_nav_bar {3}">{1}-{2}</div>';
        /*var tpl_nav = '<div data-id="{0}" class="study_nav_bar {3}">{1}</div>';
        var tpl_series_thumb = '<div class="series_thumb" data-study="{4}" data-series="{3}" data-series-uid="{5}"><div class="series_thumb_desc">{0}</div><img class="series_thumb_img" style="width: 100px;" src="{1}"><div class="series_thumb_count">{2}</div></div>';

        $.each(studies, function (i, study) {
            var noDotStudyInstanceUid = study.study_instance_uid.replace(/\./g, "");
            var selectedCss = study.study_instance_uid == _currentStudyInstanceUID ? 'selected' : '';
            html_nav.push(tpl_nav.format(study.study_instance_uid, 'DICOM', _formatStudyDate(study.study_date), selectedCss));

            html_series.push('<div id="series_list_{0}" class="series_list {1}">'.format(noDotStudyInstanceUid, selectedCss));
            $.each(study.series, function (j, series) {
                if(study.modality == 'PR' && series.series_number == '0') return true;
                var series_thumb_img = '{0}{1}'.format(study.storage.replace('dicomweb://', 'http://'), study.series[j].imageBackUrls[0], series.series_number);
                console.log(series_thumb_img)
                html_series.push(tpl_series_thumb.format(series.series_description, series_thumb_img, series.instance_ids.length, series.series_number, study.study_instance_uid, series.series_instance_uid))
            });

            html_series.push('</div>');
        });*/
       /* $('#studyNavBar').html(html_nav.join(''));
        $('#studySeriesWrapper').html(html_series.join(''));
        $('#studySeriesWrapper .series_thumb').eq(0).click();*/
        //绑定事件 - 切换不同的检查
       /* $('#studyNavBar .study_nav_bar ').bind('click', function () {
            var suid = $(this).attr('data-id').replace(/\./g, "");
            $('.series_list,.study_nav_bar').removeClass('selected');
            $(this).addClass('selected');
            $('#series_list_' + suid).addClass('selected');
        });
      $('#studyNavBar .study_nav_bar ').click();

        //绑定事件 - 点击序列
      $('#studySeriesWrapper .series_thumb').bind('click', function () {
        $('.series_thumb').removeClass('selected');
        $(this).addClass('selected');

        var ind = $(this).index();
        newList = ind;
        renderPreview(studies[0].imgSeriesList[ind])
      })*/
    },

    //调整canvas的高度 宽度
    adjustAllCanvasLayout: function () {
        //adjust canvas width&height
        var firstViewerWrapper = $('#viewerWrapper .viewportContainer:eq(0)');
        var canvasWidth = firstViewerWrapper.width();
        var canvasHeight = firstViewerWrapper.height() - 4;
        $('#viewerWrapper .viewportContainer').find('canvas,iframe').css({
            width: canvasWidth,
            height: canvasHeight
        }).attr({
            width: canvasWidth,
            height: canvasHeight
        });
        _study_ui.forEachElement(function (el) {
            cornerstone.resize(el, true);
        });
    },

    //选择一个viewport
    selectViewportContainer: function (el) {
        $('.viewportContainer').removeClass('selected');
        $(el).addClass('selected');
        var viewport = $(el).find('.imageViewerViewport')
        var study_instance_uid = viewport.attr('data-study');
        var series_number = viewport.attr('data-series');
        if (typeof study_instance_uid == 'undefined') return false;
        $('.study_nav_bar[data-id="' + study_instance_uid + '"]').click();
        $('.series_thumb').removeClass('selected');
        $('.series_thumb[data-series="' + series_number + '"]').addClass('selected');

    },

    //渲染布局
    renderLayout: function () {
        var html = [];
        var tpl = ['<div oncontextmenu="return false;" unselectable="on" onselectstart="return false;" class="viewportContainer" style="width: {0}%;height: {1}%;">',
            '        <div class="infoLayer topleft"><div class="patient_info"></div><div class="pid"></div><div class="ano"></div></div>',
            '        <div class="infoLayer topright"><div class="modality"></div><div class="series_desc"></div><div class="study_info"></div></div>',
            '        <div class="infoLayer bottomleft"><div class="position"></div><div class="wlww"></div><div class="series_parms"></div></div>',
            '        <div class="infoLayer bottomright"><div class="zoom"></div><div class="sliceText"></div><div class="fts"></div><div class="index"></div></div>',
            '        <div class="topmode"><button class="mode_2d">2D</button><button class="mode_mpr">3D MPR</button></div>',
            '        <div class="imageViewerViewport_MPR" style="display: none;"><iframe class="mpr_frame" frameborder="0"></iframe></div>',
            '        <div class="imageViewerViewport" oncontextmenu="return false;" unselectable="on" onselectstart="return false;" tabindex="0">',
            '        </div>',
            '    </div>'].join("");

        var viewportCount = this.layout[0] * this.layout[1];
        var currentViewportCount = $('#viewerWrapper .viewportContainer').length;

        if (viewportCount < currentViewportCount) {
            $('.viewportContainer:gt(' + (viewportCount-1) + ')').remove();
        }
        //else {
            var needToAddCount = viewportCount - currentViewportCount;

            var wrapperWidth = 100 / this.layout[0];
            var wrapperHeight = 100 / this.layout[1];

            for (var i = 0; i < needToAddCount; i++) {
                html.push(tpl.format(wrapperWidth, wrapperHeight));
            }
            $('#viewerWrapper .viewportContainer').css({width: wrapperWidth + '%', height: wrapperHeight + '%'});
            $('#viewerWrapper canvas').css({width: 0, height: 0}).attr({width: 0, height: 0});
            $('#viewerWrapper').append(html.join(''));

            //
            $('.viewportContainer').unbind('click').bind('click', function () {
                _study_ui.selectViewportContainer(this);
            });

            //如果没有选择的viewport，则选择第一个
            var selectedViewport = $('.viewportContainer.selected');
            if (selectedViewport.length == 0) {
                $('.viewportContainer:eq(0)').addClass('selected');
            }

      //}

        _study_ui.adjustAllCanvasLayout();
    },

    //渲染专业影像
    displayImages: function (study, series, imageElement, index) {
        $(imageElement).attr({
            'data-study': study.study_instance_uid,
            'data-series': series.series_number,
            'data-modality': study.modality,
            'data-instance-count': series.instance_ids.length
        }).addClass('rendered');

        console.log(series)

        //为infolayer渲染信息
        var layerInfoEle = $(imageElement).prevAll();
        layerInfoEle.find('.patient_info').html('{0}/{1}/{2}'.format(study.name, study.sex, study.age));
        layerInfoEle.find('.pid').html('PID:{0}'.format(study.patientNum));
        layerInfoEle.find('.ano').html('ANO:{0}'.format(''));

        layerInfoEle.find('.modality').html('{0}/{1}'.format(study.checkItem, study.reportTime));
        layerInfoEle.find('.series_desc').html('Se:{0} - {1}'.format(series.series_number, series.series_description));
        layerInfoEle.find('.study_info').html('{0}'.format(''));

        try {
            cornerstone.disable(imageElement);
        } catch (ex) {
        }
        try {
            cornerstone.enable(imageElement);
        } catch (ex) {
        }

        $('#mask,#loading').show();
        $('#header_title').html(series.series_description);
        imageIds = [];

        var stack = {
            currentImageIdIndex: 0,
            imageIds: [],
            seriesDescription: series.series_desc,
            stackId: series.id,
            seriesIndex: 0,
            frameRate: parseInt(series.frame_rate)
        };

        if (series.frame_numbers != "0" && series.frame_numbers != "1") {
            var numberOfFrames = parseInt(series.frame_numbers);
            for (var i = 0; i < numberOfFrames; i++) {
                var imageId = '{0}{1}/{2}_{3}.png'.format(study.storage.replace('dicomweb://', 'http://'), '', '', i+1);
                stack.imageIds.push(imageId);
            }
        } else {
            for (var i = 0; i < series.urls.length; i++) {
                if (series.urls[i].indexOf('png') > 0 || series.urls[i].indexOf('jpg') > 0 || series.urls[i].indexOf('jpge') > 0) {
                  stack.imageIds.push(study.storage + series.urls[i]);
                } else {
                  stack.imageIds.push(study.storage.replace('http', 'dicomweb') + series.urls[i]);
                }
            }
        }

        imageIds = stack.imageIds;
        // console.log(imageIds)
        index = index ? index : 0;
        cornerstone.loadAndCacheImage(imageIds[index]).then(function (image) {
            // alert(1)
            cornerstone.displayImage(imageElement, image);
            cornerstone.fitToWindow(imageElement);

            //cornerstoneTools.addStackStateManager(imageElement, ['stack', 'referenceLines']);

            // Activate mouse clicks, mouse wheel and touch
            cornerstoneTools.mouseInput.enable(imageElement);
            cornerstoneTools.mouseWheelInput.enable(imageElement);

            // Set the stack as tool state
            cornerstoneTools.addStackStateManager(imageElement, ['stack', 'referenceLines']);
            cornerstoneTools.addToolState(imageElement, 'stack', stack);

            // Enable all tools we want to use with this element
            cornerstoneTools.wwwc.activate(imageElement, 1); // ww/wc is the default tool for left mouse button
            cornerstoneTools.pan.activate(imageElement, 2); // pan is the default tool for middle mouse button
            cornerstoneTools.zoom.activate(imageElement, 4); // zoom is the default tool for right mouse button
            cornerstoneTools.probe.enable(imageElement);
            cornerstoneTools.length.enable(imageElement);
            cornerstoneTools.ellipticalRoi.enable(imageElement);
            cornerstoneTools.rectangleRoi.enable(imageElement);

            // Stack tools
            cornerstoneTools.stackScrollWheel.activate(imageElement);

            cornerstoneTools.orientationMarkers.enable(imageElement);

            // Add the enabled elements to the synchronization context
            synchronizer.add(imageElement);
            // enable reference Lines tool
			//if(imageIds.length==1)
            //cornerstoneTools.referenceLines.tool.enable(imageElement, synchronizer);

            cornerstoneTools.stackPrefetch.enable(imageElement);
        });
      // if(index){
      //   cornerstone.loadAndCacheImage(imageIds[index]).then(function (image) {
      //     // $('#loading').hide();
      //     var viewport = cornerstone.getViewport(_study_ui);
      //     cornerstone.displayImage(_study_ui, image, viewport);
      //     // if (isPseudocolor) setPseudocolor();
      //   });
      // }

        //附加事件
        $(imageElement).on("CornerstoneImageRendered", function (e) {
            _study_ui.event.onViewportUpdated(layerInfoEle, e)
        });
		$(imageElement).on("CornerstoneNewImage", _study_ui.event.onNewImage);

        _study_ui.adjustAllCanvasLayout();


    }


};
