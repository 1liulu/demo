package com.example.demo1.Contorller;


import com.alibaba.fastjson.JSON;
import com.example.demo1.bean.Account;
import com.example.demo1.bean.Patient;
import com.example.demo1.bean.Report;
import com.example.demo1.bean.Userimage;
import com.example.demo1.service.PatientService;
import com.example.demo1.service.ReportService;
import com.example.demo1.service.UserService;
import com.example.demo1.util.Constant;
import com.example.demo1.util.MsgBuilder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/index")
@Api(tags = "首页")
public class IndexController {
    @Autowired
    ReportService reportService;
    @Autowired
    UserService userService;
    @Autowired
    PatientService patientService;

    /**
     * 查看检查报告列表
     *
     * @param page
     * @param size
     * @param name
     * @param request
     * @return
     */
    @RequestMapping(value = "/show", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value = "查看检查报告列表", notes = "返回提示")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页数", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "每页显示数据", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "姓名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "类型", required = true, dataType = "String", paramType = "query")
    })
    public Map show(String page, String size, String name, HttpServletRequest request, String type) {
        Integer pageNum = 1;
        Integer pageSize = 1;
        if (!StringUtils.isBlank(page)) {
            pageNum = Integer.parseInt(page);
        }
        if (!StringUtils.isBlank(size)) {
            pageSize = Integer.parseInt(size);
        }
        PageHelper.startPage(pageNum, pageSize);
        Report report = new Report();
        if (!StringUtils.isBlank(name)) {
            report.setUsername("%" + name + "%");
        }
        if (StringUtils.isBlank(type)) {
            Account account = (Account) request.getAttribute("account");
            report.setType(account.getType());
        }
        List<Report> reportList = reportService.findall(report);
        PageInfo<Report> pageInfo = new PageInfo<>(reportList);
        return MsgBuilder.buildReturnMessage(pageInfo);
    }

    /**
     * 按病人分类查看检查报告列表
     * @param phone
     * @param name
     * @param uid
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/userimage", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value = "按病人分类查看检查报告列表", notes = "返回提示")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phonr", value = "手机号", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "姓名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "uid", value = "uid", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "当前页数", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "每页显示数据", required = true, dataType = "String", paramType = "query")
    })
    public Map userimage(String phone,String name,String uid,String page, String size){
        Integer pageNum = 1;
        Integer pageSize = 10;
        if (!StringUtils.isBlank(page)) {
            pageNum = Integer.parseInt(page);
        }
        if (!StringUtils.isBlank(size)) {
            pageSize = Integer.parseInt(size);
        }
        PageHelper.startPage(pageNum, pageSize);
        Patient patient = new Patient();
        if (!StringUtils.isBlank(name)) {
            patient.setName("%" + name + "%");
        }
        if (!StringUtils.isBlank(phone)) {
            patient.setPhone("%" + phone + "%");
        }
        if (!StringUtils.isBlank(uid)) {
            patient.setUid(uid);
        }
        List<Userimage> patientList=patientService.findall(patient);
        PageInfo<Userimage> pageInfo = new PageInfo<>(patientList);
        if(patientList.size()==0){
            return MsgBuilder.buildReturnMessage(pageInfo);
            //没有病人检查报告
        }
        List<Report> reportList=reportService.fingbyuid(patientList);
        Map<String, Userimage> map = new HashMap<>();
        patientList.stream().forEach(list ->{
            map.put(list.getUid(),list);
        } );
        reportList.stream().forEach(list -> {
            Userimage userimage=map.get(list.getUid());
            if(userimage.getReportList()==null){
                List<Report> reportList1=new ArrayList<Report>();
                reportList1.add(list);
                map.get(list.getUid()).setReportList(reportList1);
            }else{
                map.get(list.getUid()).getReportList().add(list);
            }
        });
        return MsgBuilder.buildReturnMessage(pageInfo);
    }
    /**
     * 查看病例图片
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "查看病例图片", notes = "返回提示")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/image", method = {RequestMethod.POST, RequestMethod.GET})
    public Map image(int id) {
        String map = "{\n" +
                "  \"status\": 200,\n" +
                "  \"message\": \"\",\n" +
                "  \"result\": {\n" +
                "    \"is_positive\": \"2\",\n" +
                "    \"disease_classify\": \"0\",\n" +
                "    \"organisation_id\": \"103\",\n" +
                "    \"patient_name\": \"Cao^RanYue\",\n" +
                "    \"patient_sex\": \"M\",\n" +
                "    \"patient_age\": \"003M\",\n" +
                "    \"patient_id\": \"2971480\",\n" +
                "    \"protocol_name\": \"t2_tse_tra_320\",\n" +
                "    \"study_date\": \"20180626154401.25900\",\n" +
                "    \"accession_number\": \"1408216458\",\n" +
                "    \"modality\": \"MR\",\n" +
                "    \"device\": \"MRC40535\",\n" +
                "    \"description\": \"head^library\",\n" +
                "    \"bodypart\": \"HEAD\",\n" +
                "    \"storage\": \"dicomweb:\\/\\/ks3-cn-shanghai.ksyun.com\\/suchen\\/ceshi\\/\",\n" +
                "    \"study_instance_uid\": \"CT\",\n" +
                "    \"source\": \"Gateway\",\n" +
                "    \"series_count\": \"6\",\n" +
                "    \"instance_count\": \"163\",\n" +
                "    \"has_jpeg\": \"1\",\n" +
                "    \"patient_chinese_name\": \"曹然越\",\n" +
                "    \"outpatient_id\": \"000367169300\",\n" +
                "    \"inpatient_id\": \"1042951\",\n" +
                "    \"checkitems\": \"头颅;\",\n" +
                "    \"patient_source\": \"儿童重症监护病房(P\",\n" +
                "    \"apply_department\": \"儿童重症监护病房(PIC\",\n" +
                "    \"apply_doctor\": \"\",\n" +
                "    \"clinical_diagnosis\": null,\n" +
                "    \"upload_file\": \"\",\n" +
                "    \"series\": [\n" +
                "      {\n" +
                "        \"series_instance_uid\": \"1.3.12.2.1107.5.2.36.40535.2018062615475393147464051.0.0.0\",\n" +
                "        \"series_number\": \"2\",\n" +
                "        \"series_description\": \"t2_tse_tra_320\",\n" +
                "        \"frame_rate\": \"0\",\n" +
                "        \"frame_numbers\": \"1\",\n" +
                "        \"instance_ids\": \"1,2,3,4,5,6,7,8,9,10\",\n" +
                "        \"bak1\": null,\n" +
                "        \"slice_thickness\": \"4\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"series_instance_uid\": \"1.3.12.2.1107.5.2.36.40535.30000018061823375242200014080\",\n" +
                "        \"series_number\": \"100\",\n" +
                "        \"series_description\": \"\",\n" +
                "        \"frame_rate\": \"0\",\n" +
                "        \"frame_numbers\": \"1\",\n" +
                "        \"instance_ids\": \"1|png,2|png,3|png,4|png,5|png,6|png,7|png,8|png,9|png,10|png,11|png,12|png,13|png,14|png,15|png,16|png,17|png,18|png,19|png,20|png\",\n" +
                "        \"bak1\": null,\n" +
                "        \"slice_thickness\": \"\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"report\": [\n" +
                "      {\n" +
                "        \"reporter_id\": \"0\",\n" +
                "        \"report_time\": \"1530005535\",\n" +
                "        \"reporter\": \"36691482608474316\",\n" +
                "        \"approver_id\": \"0\",\n" +
                "        \"approve_time\": \"1530058168\",\n" +
                "        \"approver\": \"291\",\n" +
                "        \"structured_data\": \"{&quot;Findings&quot;:&quot;颅脑MR平扫\\n    左侧额颞叶见多发斑片状异常信号，T1WI呈低信号，T2WI呈高信号，T2flair序列呈高信号，DWI呈高信号。病变边界欠清楚。脑室、脑池、脑沟及脑裂形态信号未见异常。中线结构居中。&quot;,&quot;Impression&quot;:&quot;左侧额颞叶多发异常信号灶，考虑脑炎治疗后表现，请结合临床。&quot;}\",\n" +
                "        \"report_status\": \"3\",\n" +
                "        \"findings\": \"颅脑MR平扫<br>    左侧额颞叶见多发斑片状异常信号，T1WI呈低信号，T2WI呈高信号，T2flair序列呈高信号，DWI呈高信号。病变边界欠清楚。脑室、脑池、脑沟及脑裂形态信号未见异常。中线结构居中。\",\n" +
                "        \"impression\": \"左侧额颞叶多发异常信号灶，考虑脑炎治疗后表现，请结合临床。\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"qc\": []\n" +
                "  },\n" +
                "  \"task_points\": {\n" +
                "    \"points\": 0,\n" +
                "    \"task_id\": \"\"\n" +
                "  }\n" +
                "}";
        return MsgBuilder.buildReturnMessage((Map) JSON.parse(map));

    }

    /**
     * 首页统计1
     * @return
     */
    @ApiOperation(value = "首页统计1", notes = "返回提示")
    @RequestMapping(value = "/inspection1", method = {RequestMethod.POST, RequestMethod.GET})
    public Map inspection1() {
        Map<String, Object> map = new HashMap<>();
        //计算当天检查人数
        map.put("countnumber", reportService.countnumber());
        //查询今天新增用户
        map.put("todaycountuser", userService.todaycountuser());
        //查询总人数
        map.put("countuser", userService.countuser());

        return MsgBuilder.buildReturnMessage(map);
    }

    /**
     * 首页统计2
     * @return
     */
    @ApiOperation(value = "首页统计2", notes = "返回提示")
    @RequestMapping(value = "/inspection2", method = {RequestMethod.POST, RequestMethod.GET})
    public Map inspection2() {
        Map<String, Object> map = new HashMap<>();
        //今天男的检查人数
        int MAN = reportService.tadaycountsex(Constant.SEX_MAN);
        map.put("MAN", MAN);
        //今天女的检查人数
        int WOMAN = reportService.tadaycountsex(Constant.SEX_WOMAN);
        map.put("WOMAN", WOMAN);
        //昨天男的检查人数
        int MAN1 = reportService.yesterdaycountsex(Constant.SEX_MAN);
        map.put("MAN1", MAN1);
        //昨天女的检查人数
        int WOMAN1 = reportService.yesterdaycountsex(Constant.SEX_WOMAN);
        map.put("WOMAN1", WOMAN1);
        //今天内科检查人数
        int WITHIN = reportService.tadaycountdepartments(Constant.WITHIN);
        map.put("WITHIN", WITHIN);
        //今天外科检查人数
        int OUTER = reportService.tadaycountdepartments(Constant.OUTER);
        map.put("OUTER", OUTER);
        return MsgBuilder.buildReturnMessage(map);
    }

    /**
     * 首页统计3
     * @return
     */
    @ApiOperation(value = "首页统计3", notes = "返回提示")
    @RequestMapping(value = "/inspection3", method = {RequestMethod.POST, RequestMethod.GET})
    public Map inspection3() {
        List<Report> reportList = reportService.findalls();
        int dx[] = new int[12];
        int ct[] = new int[12];
        int mr[] = new int[12];
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH");
        SimpleDateFormat sdf2 = new SimpleDateFormat("mm");
        SimpleDateFormat sdf3 = new SimpleDateFormat("ss");
        List<Report> reportList1 = new ArrayList<>();
        List<Report> reportList2 = new ArrayList<>();
        List<Report> reportList3 = new ArrayList<>();
        List<Report> reportList4 = new ArrayList<>();
        List<Report> reportList5 = new ArrayList<>();
        List<Report> reportList6 = new ArrayList<>();
        List<Report> reportList7 = new ArrayList<>();
        List<Report> reportList8 = new ArrayList<>();
        List<Report> reportList9 = new ArrayList<>();
        List<Report> reportList10 = new ArrayList<>();
        List<Report> reportList11 = new ArrayList<>();
        List<Report> reportList12 = new ArrayList<>();
        for (Report report : reportList) {
            int startTime1 = Integer.parseInt(sdf1.format(report.getCreation_time()));
            int startTime2 = Integer.parseInt(sdf2.format(report.getCreation_time()));
            int startTime3 = Integer.parseInt(sdf3.format(report.getCreation_time()));
            if ((startTime1 == 0 && startTime2 != 0 && startTime3 != 0) || (0 < startTime1 && startTime1 < 2) || (startTime1 == 2 && startTime2 == 0 && startTime3 == 0)) {
                reportList1.add(report);
            }
            if ((startTime1 == 2 && startTime2 != 0 && startTime3 != 0) || (2 < startTime1 && startTime1 < 4) || (startTime1 == 4 && startTime2 == 0 && startTime3 == 0)) {
                reportList2.add(report);
            }
            if ((startTime1 == 4 && startTime2 != 0 && startTime3 != 0) || (4 < startTime1 && startTime1 < 6) || (startTime1 == 6 && startTime2 == 0 && startTime3 == 0)) {
                reportList3.add(report);
            }
            if ((startTime1 == 6 && startTime2 != 0 && startTime3 != 0) || (6 < startTime1 && startTime1 < 8) || (startTime1 == 8 && startTime2 == 0 && startTime3 == 0)) {
                reportList4.add(report);
            }
            if ((startTime1 == 8 && startTime2 != 0 && startTime3 != 0) || (8 < startTime1 && startTime1 < 10) || (startTime1 == 10 && startTime2 == 0 && startTime3 == 0)) {
                reportList5.add(report);
            }
            if ((startTime1 == 10 && startTime2 != 0 && startTime3 != 0) || (10 < startTime1 && startTime1 < 12) || (startTime1 == 12 && startTime2 == 0 && startTime3 == 0)) {
                reportList6.add(report);
            }
            if ((startTime1 == 12 && startTime2 != 0 && startTime3 != 0) || (12 < startTime1 && startTime1 < 14) || (startTime1 == 14 && startTime2 == 0 && startTime3 == 0)) {
                reportList7.add(report);
            }
            if ((startTime1 == 14 && startTime2 != 0 && startTime3 != 0) || (14 < startTime1 && startTime1 < 16) || (startTime1 == 16 && startTime2 == 0 && startTime3 == 0)) {
                reportList8.add(report);
            }
            if ((startTime1 == 16 && startTime2 != 0 && startTime3 != 0) || (16 < startTime1 && startTime1 < 18) || (startTime1 == 18 && startTime2 == 0 && startTime3 == 0)) {
                reportList9.add(report);
            }
            if ((startTime1 == 18 && startTime2 != 0 && startTime3 != 0) || (18 < startTime1 && startTime1 < 20) || (startTime1 == 20 && startTime2 == 0 && startTime3 == 0)) {
                reportList10.add(report);
            }
            if ((startTime1 == 20 && startTime2 != 0 && startTime3 != 0) || (20 < startTime1 && startTime1 < 22) || (startTime1 == 22 && startTime2 == 0 && startTime3 == 0)) {
                reportList11.add(report);
            }
            if ((startTime1 == 22 && startTime2 != 0 && startTime3 != 0) || (22 < startTime1 && startTime1 < 24) || (startTime1 == 24 && startTime2 == 0 && startTime3 == 0)) {
                reportList12.add(report);
            }

        }
        for (Report report : reportList1) {
            if (report.getItems().equals("DX")) {
                dx[0]++;
            }
            if (report.getItems().equals("CT")) {
                ct[0]++;
            }
            if (report.getItems().equals("MR")) {
                mr[0]++;
            }
        }
        for (Report report : reportList2) {
            if (report.getItems().equals("DX")) {
                dx[1]++;
            }
            if (report.getItems().equals("CT")) {
                ct[1]++;
            }
            if (report.getItems().equals("MR")) {
                mr[1]++;
            }
        }
        for (Report report : reportList3) {
            if (report.getItems().equals("DX")) {
                dx[2]++;
            }
            if (report.getItems().equals("CT")) {
                ct[2]++;
            }
            if (report.getItems().equals("MR")) {
                mr[2]++;
            }
        }
        for (Report report : reportList4) {
            if (report.getItems().equals("DX")) {
                dx[3]++;
            }
            if (report.getItems().equals("CT")) {
                ct[3]++;
            }
            if (report.getItems().equals("MR")) {
                mr[3]++;
            }
        }
        for (Report report : reportList5) {
            if (report.getItems().equals("DX")) {
                dx[4]++;
            }
            if (report.getItems().equals("CT")) {
                ct[4]++;
            }
            if (report.getItems().equals("MR")) {
                mr[4]++;
            }
        }
        for (Report report : reportList6) {
            if (report.getItems().equals("DX")) {
                dx[5]++;
            }
            if (report.getItems().equals("CT")) {
                ct[5]++;
            }
            if (report.getItems().equals("MR")) {
                mr[5]++;
            }
        }
        for (Report report : reportList7) {
            if (report.getItems().equals("DX")) {
                dx[6]++;
            }
            if (report.getItems().equals("CT")) {
                ct[6]++;
            }
            if (report.getItems().equals("MR")) {
                mr[6]++;
            }
        }
        for (Report report : reportList8) {
            if (report.getItems().equals("DX")) {
                dx[7]++;
            }
            if (report.getItems().equals("CT")) {
                ct[7]++;
            }
            if (report.getItems().equals("MR")) {
                mr[7]++;
            }
        }
        for (Report report : reportList9) {
            if (report.getItems().equals("DX")) {
                dx[8]++;
            }
            if (report.getItems().equals("CT")) {
                ct[8]++;
            }
            if (report.getItems().equals("MR")) {
                mr[8]++;
            }
        }
        for (Report report : reportList10) {
            if (report.getItems().equals("DX")) {
                dx[9]++;
            }
            if (report.getItems().equals("CT")) {
                ct[9]++;
            }
            if (report.getItems().equals("MR")) {
                mr[9]++;
            }
        }
        for (Report report : reportList11) {
            if (report.getItems().equals("DX")) {
                dx[10]++;
            }
            if (report.getItems().equals("CT")) {
                ct[10]++;
            }
            if (report.getItems().equals("MR")) {
                mr[10]++;
            }
        }
        for (Report report : reportList12) {
            if (report.getItems().equals("DX")) {
                dx[11]++;
            }
            if (report.getItems().equals("CT")) {
                ct[11]++;
            }
            if (report.getItems().equals("MR")) {
                mr[11]++;
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("DX", dx);
        map.put("CT", ct);
        map.put("MR", mr);

        return MsgBuilder.buildReturnMessage(map);
    }

    /**
     * 首页统计4
     * @return
     */
    @ApiOperation(value = "首页统计4", notes = "返回提示")
    @RequestMapping(value = "/inspection4", method = {RequestMethod.POST, RequestMethod.GET})
    public Map inspection4() {
        List<Report> reportList = reportService.findalls();
        List<Report> reportList1 = new ArrayList<Report>();
        List<Report> reportList2 = new ArrayList<Report>();
        List<Report> reportList3 = new ArrayList<Report>();
        List<Report> reportList4 = new ArrayList<Report>();
        List<Report> reportList5 = new ArrayList<Report>();
        for (Report report : reportList) {
            int age = report.getAge();
            if (age < 7) {
                reportList1.add(report);
            }
            if (6 < age && age < 18) {
                reportList2.add(report);
            }
            if (17 < age && age < 41) {
                reportList3.add(report);
            }
            if (40 < age && age < 66) {
                reportList4.add(report);
            }
            if (65 < age) {
                reportList5.add(report);
            }
        }
        int nan[] = new int[5];
        int nv[] = new int[5];
        int nei[] = new int[5];
        int wai[] = new int[5];
        for (Report report : reportList1) {
            if (report.getSex() == Constant.SEX_MAN) {
                nan[0]++;
            }
            if (report.getSex() == Constant.SEX_WOMAN) {
                nv[0]++;
            }
            if (report.getDepartments() == Constant.WITHIN) {
                nei[0]++;
            }
            if (report.getDepartments() == Constant.OUTER) {
                wai[0]++;
            }
        }
        for (Report report : reportList2) {
            if (report.getSex() == Constant.SEX_MAN) {
                nan[1]++;
            }
            if (report.getSex() == Constant.SEX_WOMAN) {
                nv[1]++;
            }
            if (report.getDepartments() == Constant.WITHIN) {
                nei[1]++;
            }
            if (report.getDepartments() == Constant.OUTER) {
                wai[1]++;
            }
        }
        for (Report report : reportList3) {
            if (report.getSex() == Constant.SEX_MAN) {
                nan[2]++;
            }
            if (report.getSex() == Constant.SEX_WOMAN) {
                nv[2]++;
            }
            if (report.getDepartments() == Constant.WITHIN) {
                nei[2]++;
            }
            if (report.getDepartments() == Constant.OUTER) {
                wai[2]++;
            }
        }
        for (Report report : reportList4) {
            if (report.getSex() == Constant.SEX_MAN) {
                nan[3]++;
            }
            if (report.getSex() == Constant.SEX_WOMAN) {
                nv[3]++;
            }
            if (report.getDepartments() == Constant.WITHIN) {
                nei[3]++;
            }
            if (report.getDepartments() == Constant.OUTER) {
                wai[3]++;
            }
        }
        for (Report report : reportList5) {
            if (report.getSex() == Constant.SEX_MAN) {
                nan[4]++;
            }
            if (report.getSex() == Constant.SEX_WOMAN) {
                nv[4]++;
            }
            if (report.getDepartments() == Constant.WITHIN) {
                nei[4]++;
            }
            if (report.getDepartments() == Constant.OUTER) {
                wai[4]++;
            }
        }

        Map<String, Object> map = new HashMap<>();
        //男
        map.put("MAN", nan);
        //女
        map.put("WOMAN", nv);
        //内科
        map.put("WITHIN", nei);
        //外科
        map.put("OUTER", wai);
        return MsgBuilder.buildReturnMessage(map);
    }
}
