function loadStudy(studyViewer, viewportModel) {
    //data
    var imageViewer = new ImageViewer(studyViewer, viewportModel);
    currentImageViewer = imageViewer;
    imageViewer.setLayout('1x1'); // default layout

    function initViewports() {
        imageViewer.forEachElement(function (el) {
            cornerstone.enable(el);
            $(el).droppable({
                drop: function (evt, ui) {
                    var fromStack = $(ui.draggable.context).data('stack'), toItem = $(this).data('index');
                    useItemStack(toItem, fromStack);
                }
            });
        });
    }

    // setup the tool buttons todo
    setupButtons();
    //改变布局
    new insertTable($("#btn-layout"), {
        min: [4, 4],
        max: [20, 20],
        insert: function (rows, cols) {
            var previousUsed = [];
            imageViewer.forEachElement(function (el, vp, i) {
                if (!isNaN($(el).data('useStack'))) {
                    previousUsed.push($(el).data('useStack'));
                }
            });

            var type = rows + "x" + cols;
            imageViewer.setLayout(type);
            initViewports();
            resizeStudyViewer();
            if (previousUsed.length > 0) {
                previousUsed = previousUsed.slice(0, imageViewer.viewports.length);
                var item = 0;
                previousUsed.forEach(function (v) {
                    useItemStack(item++, v);
                });
            }
        }
    });
    //toggle 序列导航的显示
    $('#btn-toggle-series-view').on('click touchstart', function () {
        var width = $(window).width();
        var height = $(window).height();

        if (isShowingSeriesNav) {
            isShowingSeriesNav = false;
            $('#seriesPane').css({'left': '-170px'});
            $('#wrap').css({'left': '14px', 'width': width + 'px'});
        } else {
            isShowingSeriesNav = true;
            $('#seriesPane').css({'left': '0px'});
            $('#wrap').css({'left': '184px', 'width': (width - 170) + 'px'});
        }
        resizeStudyViewer();
    });


    // Load the first series into the viewport (?)
    //var stacks = [];
    //var currentStackIndex = 0;
    var seriesIndex = 0;

    // Create a stack object for each series
    study.series.forEach(function (series) {
        var stack = {
            seriesDescription: series.series_description,
            stackId: series.series_number,
            imageIds: [],
            seriesIndex: seriesIndex,
            currentImageIdIndex: 0,
            frameRate: series.frame_rate == "0" ? undefined : series.frame_rate
        };

        // Populate imageIds array with the imageIds from each series
        // For series with frame information, get the image url's by requesting each frame
        if (series.frame_numbers !== "0" && series.frame_numbers !== "1") {
            var numberOfFrames = series.frame_numbers;
            for (var i = 0; i < numberOfFrames; i++) {
                var imageId = '{0}{1}/{2}_{3}.png'.format(study.storage.replace('dicomweb://', 'http://'), study.study_instance_uid, series.series_number, i);
                stack.imageIds.push(imageId);
            }
        } else {
            series.instanceList.forEach(function (image) {
                if (image.indexOf('.png') > 0 || image.indexOf('.jpg') > 0) {
                    stack.imageIds.push(image.replace('dicomweb://', 'http://'));
                }
                else {
                    stack.imageIds.push(image);
                }
            });
        }
        // Move to next series
        seriesIndex++;

        // Add the series stack to the stacks array
        imageViewer.stacks.push(stack);
    });

    // Resize the parent div of the viewport to fit the screen
    var imageViewerElement = $(studyViewer).find('.imageViewer')[0];
    var viewportWrapper = $(imageViewerElement).find('.viewportWrapper')[0];
    var parentDiv = $(studyViewer).find('.viewer')[0];

    var studyRow = $(studyViewer).find('.studyRow')[0];
    var width = $(studyRow).width();

    // Get the viewport elements
    var element = $(studyViewer).find('.viewport')[0];

    // Image enable the dicomImage element
    initViewports();

    // Get series list from the series thumbnails (?)
    var seriesList = document.getElementById('seriesPane');
    imageViewer.stacks.forEach(function (stack, stackIndex) {

        var tpl = '<div' +
            ' class="seriesPart"><div class="seriesDesc">{2}</div><img class="series-img" src="{0}{1}/{3}_t.jpg"/><span class="count">{4}</span></div>';
        var seriesEntry = tpl.format(study.storage.replace('dicomweb://', 'http://'), study.study_instance_uid, stack.seriesDescription, stack.stackId, stack.imageIds.length);

        // Add to series list
        var seriesElement = $(seriesEntry).appendTo(seriesList);

        // Make the first thumbnail active
        if (stack.seriesIndex === 0) {
            $(seriesElement).addClass('active');
        }
        $(seriesElement).draggable({helper: "clone",addClasses: false,scroll: false});
		//$(seriesElement).draggable();

        // Handle thumbnail click
        $(seriesElement).on('click touchstart', function () {
            useItemStack(0, stackIndex);
            if (isEmitSwitchAction) {
                emitMessage('SwitchSeries|' + stackIndex + '|na');
            }
            isEmitSwitchAction = true;
        }).data('stack', stackIndex);
    });

    function useItemStack(item, stack) {
        var imageId = imageViewer.stacks[stack].imageIds[0], element = imageViewer.getElement(item);
        if ($(element).data('waiting')) {
            imageViewer.viewports[item].find('.overlay-text').remove();
            $(element).data('waiting', false);
        }
        $(element).data('useStack', stack);

        displayThumbnail(seriesList, $(seriesList).find('.seriesPart')[stack], element, imageViewer.stacks[stack], function (el, stack) {
            if (!$(el).data('setup')) {
                setupViewport(el, stack, this);
                setupViewportOverlays(el, study);
                $(el).data('setup', true);
            }
        });
    }

    // Resize study viewer
    function resizeStudyViewer() {
        var studyRow = $(studyViewer).find('.studyContainer')[0];
        var height = $(studyRow).height();
        var width = $(studyRow).width();
        console.log($(studyRow).innerWidth(), $(studyRow).outerWidth(), $(studyRow).width());
        $(seriesList).height("100%");
        $(parentDiv).width(width - $(studyViewer).find('.thumbnailSelector:eq(0)').width());
        $(parentDiv).css({height: '100%'});
        $(imageViewerElement).css({height: $(parentDiv).height() - $(parentDiv).find('.text-center:eq(0)').height()});

        imageViewer.forEachElement(function (el, vp) {
            cornerstone.resize(el, true);

            if ($(el).data('waiting')) {
                var ol = vp.find('.overlay-text');
                if (ol.length < 1) {
                    ol = $('<div class="overlay overlay-text">把序列拖动到这来调阅.</div>').appendTo(vp);
                }
                var ow = vp.width() / 2, oh = vp.height() / 2;
                ol.css({top: oh, left: ow - (ol.width() / 2)});
            }
        });
    }

    // Call resize viewer on window resize
    $(window).resize(function () {
        resizeStudyViewer();
    });
    resizeStudyViewer();
    if (imageViewer.isSingle())
        useItemStack(0, 0);
}
