var bl = false;
var oldView = '';
function renderPreview(study) {
  console.log(study)
  var arr = study.imgSeriesList;
  var study = study
  if(!bl){
    var str = ''
    for(var i = 0; i < arr.length; i ++){
      if(arr[i]){
        str += '<li><div class="img"><img src="http://112.74.41.176/' + arr[i][0] + '" alt="" /></div><span>' + (i + 1) + '[' + arr[i].length + ']</span></li>';
      }
    }
    document.getElementById('preview_main').innerHTML = str;

    $('#preview_main li').bind('click', function () {
      var ind = $(this).index();
      $('.preview_main li').removeClass('active');
      $(this).addClass('active');

      var series = study.series[ind];
      var curPanel = $('.viewportContainer.selected>.imageViewerViewport')[0];

      _study_ui.displayImages(study, series, curPanel,0);

      $('.rendered').hover(function() {
          cornerstoneTools.referenceLines.tool.disable(this);
        },
        function() {
          cornerstoneTools.referenceLines.tool.enable(this, synchronizer);
        });
    });
    $('.preview_main').css({left: 0})
    $('#preview_main li').eq(0).click();
    bl = true;
  }
}

function renderNow(n) {
  var imageElement = $('.imageViewerViewport').get(0);
  // var stackToolDataSource = cornerstoneTools.getToolState(imageElement, 'stack');
  // if (stackToolDataSource === undefined) {
  //   return;
  // }
  // var stackData = stackToolDataSource.data[0];
  // $('#loading').show();
  setTimeout(function() {
    cornerstone.loadAndCacheImage(imageIds[n]).then(function (image) {
      // $('#loading').hide();
      var viewport = cornerstone.getViewport(imageElement);
      cornerstone.displayImage(imageElement, image, viewport);



      // if (isPseudocolor) setPseudocolor();
    });
  },0)
}

function loadInfomation(obj) {
  var info = document.createElement('div');
  if(hospitalId == 1){
    info.innerHTML = `<div class="inspection-report">
                            <div class="main">
                              <div class="main_c">
                                <div class="check_hospital">${obj.checkHospital}</div>
                                <h3 class="title">${obj.reportName}</h3>
                                <div class="number">
                                  <div>
                                    <p>检查号：${obj.checkNum}</p>
                                    <p>病人ID：${obj.patientNum}</p>
                                  </div>
                                </div>
                                <div class="inf">
                                  <p>
                                    <span>姓名</span>
                                    <span>：${obj.name}</span>
                                  </p>
                                  <p>
                                    <span>性别</span>
                                    <span>：${obj.sex}</span>
                                  </p>
                                  <p>
                                    <span>年龄</span>
                                    <span>：${obj.age}</span>
                                  </p>
                                  <p>
                                    <span>申请科室</span>
                                    <span>：${obj.applicationDepartment}</span>
                                  </p>
                                  <p class="other">
                                    <span>检查项目</span>
                                    <span>：${obj.checkItem}</span>
                                  </p>
                                  <p>
                                    <span>申请日期</span>
                                    <span>：${obj.applicationTime.split(' ')[0]}</span>
                                  </p>
                                  <p>
                                    <span>申请医生</span>
                                    <span>：${obj.applicationDoctor}</span>
                                  </p>
                                </div>
                              </div>

                              <div class="main_c">
                                <div class="feel">
                                  <div>
                                    <p>检查所见：</p>
                                    <p>${obj.checkLook}</p>
                                  </div>
                                  <div>
                                    <p>印象：</p>
                                    <p>${obj.impression}</p>
                                  </div>
                                </div>
                                <div class="inf">
                                  <p>
                                    <span>检查时间</span>
                                    <span>：${obj.checkTime.split(' ')[0]}</span>
                                  </p>
                                  <p>
                                    <span>报告时间</span>
                                    <span>：${obj.reportTime.split(' ')[0]}</span>
                                  </p>
                                  <p>
                                    <span>报告医生</span>
                                    <span>：${obj.reportDoctor}</span>
                                  </p>
                                  <p>
                                    <span>审核医生</span>
                                    <span>：${obj.auditDoctor}</span>
                                  </p>
                                  <p>
                                    <span>签名</span>
                                    <span>：<img src="http://112.74.41.176/${obj.signReportDoctor}" /></span>
                                  </p>
                                  <p>
                                    <span>签名</span>
                                    <span>：<img src="http://112.74.41.176/${obj.signAuditDoctor}" /></span>
                                  </p>
                                </div>
                              </div>
                              <div class="tip">注：此报告仅作为临床参考,不作为最终诊断</div>
                            </div>
                          </div>`;
  }else if(hospitalId == 2){
    if(obj.imageType == 3){
      info.innerHTML = `<div class="inspection-report">
                            <div class="main">
                              <div class="main_c">
                                <div class="check_hospital">${obj.checkHospital}</div>
                                <h3 class="title">${obj.reportName}</h3>
                                <div class="number">
                                  <div style="width: 100%;">
                                    <p style="width: 100%; text-align: right;">检查号：${obj.checkNum}</p>
                                  </div>
                                </div>
                                <div class="inf">
                                  <p>
                                    <span>姓名</span>
                                    <span>：${obj.name}</span>
                                  </p>
                                  <p>
                                    <span>性别</span>
                                    <span>：${obj.sex}</span>
                                  </p>
                                  <p>
                                    <span>年龄</span>
                                    <span>：${obj.age}</span>
                                  </p>
                                  <p>
                                    <span>科室</span>
                                    <span>：${obj.applicationDepartment}</span>
                                  </p>
                                  <p class="other">
                                    <span>门诊号</span>
                                    <span>：${obj.doorNum}</span>
                                  </p>
                                  <p>
                                    <span>病区</span>
                                    <span>：${obj.illPosition}</span>
                                  </p>
                                  <p>
                                    <span>床号</span>
                                    <span>：${obj.bedNum}</span>
                                  </p>
                                  <p>
                                    <span>检查部位</span>
                                    <span>：${obj.checkItem}</span>
                                  </p>
                                  <p>
                                    <span>方法</span>
                                    <span>：${obj.method}</span>
                                  </p>
                                  <p>
                                    <span>层厚</span>
                                    <span>：${obj.depth}</span>
                                  </p>
                                </div>
                              </div>

                              <div class="main_c">
                                <div class="feel">
                                  <div>
                                    <p>检查所见：</p>
                                    <p>${obj.checkLook}</p>
                                  </div>
                                  <div>
                                    <p>印象：</p>
                                    <p>${obj.impression}</p>
                                  </div>
                                </div>
                                <div class="inf">
                                  <p>
                                    <span>检查时间</span>
                                    <span>：${obj.checkTime.split(' ')[0]}</span>
                                  </p>
                                  <p>
                                    <span>报告时间</span>
                                    <span>：${obj.reportTime.split(' ')[0]}</span>
                                  </p>
                                  <p>
                                    <span>报告医生</span>
                                    <span>：${obj.reportDoctor}</span>
                                  </p>
                                  <p>
                                    <span>审核医生</span>
                                    <span>：${obj.auditDoctor}</span>
                                  </p>
                                  <p>
                                    <span>签名</span>
                                    <span>：</span>
                                  </p>
                                  <p>
                                    <span>签名</span>
                                    <span>：<img src="http://112.74.41.176/${obj.signAuditDoctor}" /></span>
                                  </p>
                                </div>
                              </div>
                              <div class="tip">注：此报告仅作为临床参考,不作为最终诊断</div>
                            </div>
                          </div>`;
    }else{
      info.innerHTML = `<div class="inspection-report">
                            <div class="main">
                              <div class="main_c">
                                <div class="check_hospital">${obj.checkHospital}</div>
                                <h3 class="title">${obj.reportName}</h3>
                                <div class="number">
                                  <div style="width: 100%;">
                                    <p style="width: 100%; text-align: right;">检查号：${obj.checkNum}</p>
                                  </div>
                                </div>
                                <div class="inf">
                                  <p>
                                    <span>姓名</span>
                                    <span>：${obj.name}</span>
                                  </p>
                                  <p>
                                    <span>性别</span>
                                    <span>：${obj.sex}</span>
                                  </p>
                                  <p>
                                    <span>年龄</span>
                                    <span>：${obj.age}</span>
                                  </p>
                                  <p>
                                    <span>科室</span>
                                    <span>：${obj.applicationDepartment}</span>
                                  </p>
                                  <p class="other">
                                    <span>门诊号</span>
                                    <span>：${obj.doorNum}</span>
                                  </p>
                                  <p>
                                    <span>病区</span>
                                    <span>：${obj.illPosition}</span>
                                  </p>
                                  <p>
                                    <span>床号</span>
                                    <span>：${obj.bedNum}</span>
                                  </p>
                                  <p>
                                    <span>检查部位</span>
                                    <span>：${obj.checkItem}</span>
                                  </p>
                                </div>
                              </div>

                              <div class="main_c">
                                <div class="feel">
                                  <div>
                                    <p>检查所见：</p>
                                    <p>${obj.checkLook}</p>
                                  </div>
                                  <div>
                                    <p>印象：</p>
                                    <p>${obj.impression}</p>
                                  </div>
                                </div>
                                <div class="inf">
                                  <p>
                                    <span>检查时间</span>
                                    <span>：${obj.checkTime.split(' ')[0]}</span>
                                  </p>
                                  <p>
                                    <span>报告时间</span>
                                    <span>：${obj.reportTime.split(' ')[0]}</span>
                                  </p>
                                  <p>
                                    <span>报告医生</span>
                                    <span>：${obj.reportDoctor}</span>
                                  </p>
                                  <p>
                                    <span>审核医生</span>
                                    <span>：${obj.auditDoctor}</span>
                                  </p>
                                  <p>
                                    <span>签名</span>
                                    <span>：</span>
                                  </p>
                                  <p>
                                    <span>签名</span>
                                    <span>：<img src="http://112.74.41.176/${obj.signAuditDoctor}" /></span>
                                  </p>
                                </div>
                              </div>
                              <div class="tip">注：此报告仅作为临床参考,不作为最终诊断</div>
                            </div>
                          </div>`;
    }
  }else if(hospitalId == 3){
    info.innerHTML = `<div class="inspection-report">
                            <div class="main">
                              <div class="main_c">
                                <div class="check_hospital">${obj.checkHospital}</div>
                                <h3 class="title">${obj.reportName}</h3>
                                <div class="number">
                                  <div>
                                    <p>检查号：${obj.checkNum}</p>
                                  </div>
                                </div>
                                <div class="inf">
                                  <p>
                                    <span>姓名</span>
                                    <span>：${obj.name}</span>
                                  </p>
                                  <p>
                                    <span>性别</span>
                                    <span>：${obj.sex}</span>
                                  </p>
                                  <p>
                                    <span>年龄</span>
                                    <span>：${obj.age}</span>
                                  </p>
                                  <p class="other">
                                    <span>检查项目</span>
                                    <span>：${obj.checkItem}</span>
                                  </p>
                                  <p>
                                    <span>住院科室</span>
                                    <span>：${obj.applicationDepartment ? obj.applicationDepartment : obj.department}</span>
                                  </p>
                                  <p>
                                    <span>床号</span>
                                    <span>：</span>
                                  </p>
                                  <p>
                                    <span>门诊级别</span>
                                    <span>：门诊室</span>
                                  </p>
                                </div>
                              </div>

                              <div class="main_c">
                                <div class="feel">
                                  <div>
                                    <p>影像所见：</p>
                                    <p>${obj.checkLook}</p>
                                  </div>
                                  <div>
                                    <p>影像诊断：</p>
                                    <p>${obj.impression}</p>
                                  </div>
                                </div>
                                <div class="inf">
                                  <p>
                                    <span>检查时间</span>
                                    <span>：${obj.checkTime}</span>
                                  </p>
                                  <p>
                                    <span>报告时间</span>
                                    <span>：${obj.reportTime ? obj.reportTime : obj.aduitTime}</span>
                                  </p>
                                  <p>
                                    <span>报告医生</span>
                                    <span>：${obj.reportDoctor}</span>
                                  </p>
                                  <p>
                                    <span>审核医生</span>
                                    <span>：${obj.auditDoctor}</span>
                                  </p>
                                  <p>
                                    <span>签名</span>
                                    <span>：</span>
                                  </p>
                                  <p>
                                    <span>签名</span>
                                    <span>：<img src="http://112.74.41.176/${obj.signAuditDoctor}" /></span>
                                  </p>
                                </div>
                              </div>
                              <div class="tip">注：此报告仅作为临床参考,不作为最终诊断</div>
                            </div>
                          </div>`;
  }else if(hospitalId == 4){
    info.innerHTML = `<div class="inspection-report">
                            <div class="main">
                              <div class="main_c">
                                <div class="check_hospital">${obj.checkHospital}</div>
                                <h3 class="title">${obj.reportName}</h3>
                                <div class="number">
                                  <div>
                                    <p>病人ID：${obj.patientNum}</p>
                                  </div>
                                </div>
                                <div class="inf">
                                  <p>
                                    <span>姓名</span>
                                    <span>：${obj.name}</span>
                                  </p>
                                  <p>
                                    <span>性别</span>
                                    <span>：${obj.sex}</span>
                                  </p>
                                  <p>
                                    <span>年龄</span>
                                    <span>：${obj.age}</span>
                                  </p>
                                  <p>
                                    <span>科室</span>
                                    <span>：${obj.applicationDepartment ? obj.applicationDepartment : obj.department}</span>
                                  </p>
                                  <p>
                                    <span>床号</span>
                                    <span>：</span>
                                  </p>
                                  <p>
                                    <span>门诊号</span>
                                    <span>：${obj.checkNum ? obj.checkNum : ''}</span>
                                  </p>
                                  <p class="other">
                                    <span>检查项目</span>
                                    <span>：${obj.checkItem ? obj.checkItem : ''}</span>
                                  </p>
                                </div>
                              </div>

                              <div class="main_c">
                                <div class="feel">
                                  <div>
                                    <p>影像所见：</p>
                                    <p>${obj.checkLook}</p>
                                  </div>
                                  <div>
                                    <p>诊断意见：</p>
                                    <p>${obj.impression}</p>
                                  </div>
                                </div>
                                <div class="inf">
                                  <p>
                                    <span>检查时间</span>
                                    <span>：${obj.checkTime}</span>
                                  </p>
                                  <p>
                                    <span>报告时间</span>
                                    <span>：${obj.reportTime ? obj.reportTime : obj.aduitTime}</span>
                                  </p>
                                  <p>
                                    <span>报告医生</span>
                                    <span>：${obj.reportDoctor}</span>
                                  </p>
                                  <p>
                                    <span>审核医生</span>
                                    <span>：${obj.auditDoctor}</span>
                                  </p>
                                  <p>
                                    <span>签名</span>
                                    <span>：</span>
                                  </p>
                                  <p>
                                    <span>签名</span>
                                    <span>：<img src="http://112.74.41.176/${obj.signAuditDoctor}" /></span>
                                  </p>
                                </div>
                              </div>
                              <div class="tip">注意：此报告仅作为临床参考,不作为最终诊断</div>
                            </div>
                          </div>`;
  }else if(hospitalId == 5){
    info.innerHTML = `<div class="inspection-report">
                            <div class="main">
                              <div class="main_c">
                                <div class="check_hospital">${obj.checkHospital}</div>
                                <h3 class="title">${obj.reportName}</h3>
                                <div class="inf">
                                  <p>
                                    <span>姓名</span>
                                    <span>：${obj.name}</span>
                                  </p>
                                  <p>
                                    <span>性别</span>
                                    <span>：${obj.sex}</span>
                                  </p>
                                  <p>
                                    <span>患者编号</span>
                                    <span>：${obj.sex}</span>
                                  </p>
                                  <p>
                                    <span>年龄</span>
                                    <span>：${obj.age}</span>
                                  </p>
                                  <p class="other">
                                    <span>检查部位</span>
                                    <span>：${obj.checkItem}</span>
                                  </p>
                                  <p>
                                    <span>科室</span>
                                    <span>：${obj.applicationDepartment ? obj.applicationDepartment : obj.department}</span>
                                  </p>
                                  <p>
                                    <span>检查日期</span>
                                    <span>：${obj.checkTime ? obj.checkTime : ''}</span>
                                  </p>
                                  <p>
                                    <span>床号</span>
                                    <span>：</span>
                                  </p>
                                  <p>
                                    <span>报告日期</span>
                                    <span>：${obj.reportTime ? obj.reportTime : ''}</span>
                                  </p>
                                  <p>
                                    <span>门诊号</span>
                                    <span>：${obj.checkNum ? obj.checkNum : ''}</span>
                                  </p>
                                  <p>
                                    <span>病历号</span>
                                    <span>：</span>
                                  </p>
                                  <p>
                                    <span>住院号</span>
                                    <span>：</span>
                                  </p>
                                </div>
                              </div>

                              <div class="main_c">
                                <div class="feel">
                                  <div>
                                    <p>检查所见：</p>
                                    <p>${obj.checkLook}</p>
                                  </div>
                                  <div>
                                    <p>诊断意见：</p>
                                    <p>${obj.impression}</p>
                                  </div>
                                </div>
                                <div class="inf">
                                  <p>
                                    <span>报告医生</span>
                                    <span>：${obj.reportDoctor}</span>
                                  </p>
                                  <p>
                                    <span>审核医生</span>
                                    <span>：${obj.auditDoctor}</span>
                                  </p>
                                  <p>
                                    <span>签名</span>
                                    <span>：</span>
                                  </p>
                                  <p>
                                    <span>签名</span>
                                    <span>：<img src="http://112.74.41.176/${obj.signAuditDoctor}" /></span>
                                  </p>
                                </div>
                              </div>
                              <div class="tip">注：此报告仅作为临床参考,不作为最终诊断</div>
                            </div>
                          </div>`;
  }else{
    info.innerHTML = `<div class="inspection-report">
                            <div class="main">
                              <div class="main_c">
                                <div class="check_hospital">${obj.checkHospital}</div>
                                <h3 class="title">${obj.reportName}</h3>
                                <div class="number">
                                  <div>
                                    <p>检查号：${obj.checkNum}</p>
                                    <p>病人ID：${obj.patientNum}</p>
                                  </div>
                                </div>
                                <div class="inf">
                                  <p>
                                    <span>姓名</span>
                                    <span>：${obj.name}</span>
                                  </p>
                                  <p>
                                    <span>性别</span>
                                    <span>：${obj.sex}</span>
                                  </p>
                                  <p>
                                    <span>年龄</span>
                                    <span>：${obj.age}</span>
                                  </p>
                                  <p>
                                    <span>申请科室</span>
                                    <span>：${obj.applicationDepartment}</span>
                                  </p>
                                  <p class="other">
                                    <span>检查项目</span>
                                    <span>：${obj.checkItem}</span>
                                  </p>
                                  <p>
                                    <span>申请日期</span>
                                    <span>：${obj.applicationTime.split(' ')[0]}</span>
                                  </p>
                                  <p>
                                    <span>申请医生</span>
                                    <span>：${obj.applicationDoctor}</span>
                                  </p>
                                </div>
                              </div>

                              <div class="main_c">
                                <div class="feel">
                                  <div>
                                    <p>检查所见：</p>
                                    <p>${obj.checkLook}</p>
                                  </div>
                                  <div>
                                    <p>印象：</p>
                                    <p>${obj.impression}</p>
                                  </div>
                                </div>
                                <div class="inf">
                                  <p>
                                    <span>检查时间</span>
                                    <span>：${obj.checkTime.split(' ')[0]}</span>
                                  </p>
                                  <p>
                                    <span>报告时间</span>
                                    <span>：${obj.reportTime.split(' ')[0]}</span>
                                  </p>
                                  <p>
                                    <span>报告医生</span>
                                    <span>：${obj.reportDoctor}</span>
                                  </p>
                                  <p>
                                    <span>审核医生</span>
                                    <span>：${obj.auditDoctor}</span>
                                  </p>
                                  <p>
                                    <span>签名</span>
                                    <span>：<img src="http://112.74.41.176/${obj.signReportDoctor}" /></span>
                                  </p>
                                  <p>
                                    <span>签名</span>
                                    <span>：<img src="http://112.74.41.176/${obj.signAuditDoctor}" /></span>
                                  </p>
                                </div>
                              </div>
                              <div class="tip">注：此报告仅作为临床参考,不作为最终诊断</div>
                            </div>
                          </div>`;
  }
  document.getElementById('left_view').appendChild(info);
  init(1);
}


function init(state) {
  $('#top_nav div span').removeClass('active');
  if(state == 1){
    // $('body').unbind('touchmove');
    $('#top_nav .left span').addClass('active');
    $('#left_view').css({zIndex: 99999,opacity: 1})
    $('#right_view').css({opacity: 0, zIndex: -1})
  }else{
    $('#left_view').bind('mousestart click touchstart',function (event) {
      event.preventDefault();
    })
    $('#top_nav .right span').addClass('active');
    $('#left_view').css({zIndex: -99999})
    $('#right_view').css({opacity: 1, zIndex: 1})
    $('#viewerWrapper').unbind('click mousewheel DOMMouseScroll');
    $('#viewerWrapper').bind('click mousewheel DOMMouseScroll',function (e) {
      if(e){
        e.preventDefault();
      }else{
        window.event.returnValue=fale;
        return false;
      }
    })
  }
}

