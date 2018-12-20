var _host = 'http://m.yzhcloud.com/';//影像api服务host
var _url_get_study_details = _host + 'api/clientstudy.action.php/study/'; //影像api服务 获取影像详细信息
var _url_get_series_details = _host + 'api/clientstudy.action.php/series/'; //影像api服务 获取影像详细信息
var _url_get_patient_studies = _host + 'api/clientstudy.action.php/patient_studies/'; //影像api服务 获取患者的历史影像

function sortNumber(a, b)
{
    return a - b;
}


/*var url = window.location.href;
var query = url.split('/dicom/index.html?')[1];*/
var baseUrl = "http://www.7bhealth.com/health/front/";
var str = window.location.search;     //配置访问链接
var url = 'patient2v3/lookHospitalArchives' + str;
var appid = localStorage.getItem('appid');
if(url.indexOf('?') == -1){
  url = url + '?tokenId=' + appid
}else {
  url = url + '&tokenId=' + appid
}
// + '&ip=' + returnCitySN["cip"] + '&site=' + remote_ip_info["province"] + remote_ip_info["city"];

var tips = '';
var backUrls = [];



var _study_restful = {
    //获取影像的详情
    getStudyDetails: function (org_id, study_instance_uid, callback) {
      // alert(1)
      // var url = baseURL + '/archives/archivesInfo?' + query;
      // var url = baseURL + 'wxwjtz/archivesInfo?' + query;

        // $.get(_url_get_study_details + org_id + '/' + study_instance_uid, function (res) {
        $.get(baseUrl+url, function (res) {
          if(typeof res == 'string'){
            res = JSON.parse(res);
          }
          if(res.res == 301){
            history.go(-2);
            return;
          }
          // console.log(res)
            var study = res.obj ? res.obj : {};
          study.study_instance_uid = "2.3";
          study.storage = '';

          study.series = [];
          var arr = study.dcmSeriesList;
          var arr2 = study.imgSeriesList;

          if(arr.length == 0){
            arr[0] = study.imageUrls.split(',');
            arr2[0] = study.imageBackUrls.split(',');
          }


          for (var i = 0; i < arr.length; i++) {
            var instanceUrls = [];
            var instanceUrls2 = [];
            // console.log(instanceIds[i])
            for(var j = 0; j < arr[i].length; j ++){
              if(arr[i][j]){
                instanceUrls.push(arr[i][j]);
                instanceUrls2.push(arr2[i][j]);
              }
            }
            study.series[i] = {};
            study.series[i].series_description = 'N/A';
            study.series[i].series_number = i + 1;
            study.series[i].series_instance_uid = '';
            study.series[i].frame_rate = '0';
            study.series[i].frame_numbers = '1';

            // console.log(instanceUrls)
            study.series[i].urls = instanceUrls;
            study.series[i].imageBackUrls = instanceUrls2;
            study.series[i].instance_ids = [];
            for (var j = 0; j < instanceUrls.length; j++) {
              study.series[i].instance_ids.push(j)
            }
          }

          study.storage = "http://112.74.41.176/";

          // renderPreview()
          loadInfomation(study)
          renderPreview(study)

          callback(study);
        });
    },

    //获取序列的详情
    getSeriesDetails: function (org_id, study_instance_uid, series_number, callback) {
        $.get(_url_get_series_details + org_id + '/' + study_instance_uid + '/' + series_number, function (res) {
            var study = res.result;
            for (var i = 0; i < study.series.length; i++) {
                if (study.series[i].series_description == '' || study.series[i].series_description == null) study.series[i].series_description = 'N/A';
                var instanceUrls = [];
                var instanceIds = study.series[i].instance_ids.split(',');
                for (var j = 0; j < instanceIds.length; j++) {
                    //instanceUrls.push('{0}{1}/{2}.{3}.dcm'.format(study.storage.replace('dicomweb:', 'http:'), study.study_instance_uid, study.series[i].series_number, instanceIds[j]));
					if(instanceIds[j].indexOf('png')>0){
						instanceUrls.push('{0}{1}/{2}.{3}.dcm'.format(study.storage.replace('dicomweb:', 'index4420.html'), study.study_instance_uid, study.series[i].series_number, instanceIds[j].replace('|png','')));
					} else {
						instanceUrls.push('{0}{1}/{2}.{3}.dcm'.format(study.storage.replace('dicomweb:', 'index4420.html'), study.study_instance_uid, study.series[i].series_number, instanceIds[j]));
					}
                }
                study.series[i].instanceList = instanceUrls;
            }

            callback(study);
        });
    },


    //获取患者的历史检查
    getPatientStudies: function (org_id, patient_id, callback) {
        $.get(_url_get_patient_studies + org_id + '/' + patient_id, function (res) {
            var studies = res.result;

            $.each(studies, function (i, study) {
                for (var i = 0; i < study.series.length; i++) {
                    if (study.series[i].series_description == '' || study.series[i].series_description == null) study.series[i].series_description = 'N/A';
                    var instanceUrls = [];
                    var instanceIds = study.series[i].instance_ids.split(',');
                    for (var j = 0; j < instanceIds.length; j++) {
                        if (study.source == 'jpg' || study.modality == 'DR' || study.modality == 'CR' || study.modality == 'RF' || study.modality == 'DX') {
                            instanceUrls.push('{0}{1}/{2}.{3}.jpg'.format(study.storage, study.study_instance_uid, study.series[i].series_number, instanceIds[j]));
                        } else {
                            instanceUrls.push('{0}{1}/{2}.{3}.dcm'.format(study.storage, study.study_instance_uid, study.series[i].series_number, instanceIds[j]));
                        }
                    }
                    study.series[i].urls = instanceUrls;
                }
            });

            callback(studies);
        });
    },


    //获取我的挂片协议
    getMyHangSettings: function (callback) {

    }
};



// var $Navs = $('#top_nav div span');
// var $left = $('#top_nav .left span');
// var $right = $('#top_nav .right span');
// var $Info = $('#left_view');




