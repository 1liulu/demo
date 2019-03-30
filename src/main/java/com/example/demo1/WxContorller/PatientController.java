package com.example.demo1.WxContorller;

import com.example.demo1.bean.Patient;
import com.example.demo1.bean.Report;
import com.example.demo1.bean.User;
import com.example.demo1.bean.Userimage;
import com.example.demo1.service.PatientService;
import com.example.demo1.service.ReportService;
import com.example.demo1.service.UserService;
import com.example.demo1.util.Constant;
import com.example.demo1.util.MsgBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@RestController
@RequestMapping("/Patient")
public class PatientController {
    @Autowired
    PatientService patientService;
    @Autowired
    UserService userService;
    @Autowired
    ReportService reportService;
    /**
     * 就诊人管理列表
     * @param httpServletRequest
     * @return
     */
    @RequestMapping("/list")
    public Map list(HttpServletRequest httpServletRequest){
        Map<String, Object> result = new HashMap<>();
        User user = (User) httpServletRequest.getAttribute("user");
        List<User> userLists=new ArrayList<User>();
        userLists.add(user);
        List<Patient> patientList=patientService.findinuid(userLists);
        result.put("patient",patientList.get(0));
        List<User> userList=userService.findbypid(user.getId());
        if (userList.size()==0){
            result.put("list",null);
        }else {
            List<Patient> patientLists=patientService.findinuid(userList);
            result.put("list",patientLists);
        }
        return MsgBuilder.buildReturnMessage(result);
    }

    /**
     * 查看某个用户所有检查报告
     * @param uid
     * @return
     */
    @RequestMapping("/particulars")
    public Map particulars(String uid){
        if (StringUtils.isBlank(uid)) {
            return MsgBuilder.buildReturnErrorMessage("请选择用户");
        }
        Patient patient=new Patient();
        patient.setUid(uid);
        List<Userimage> userimageList=patientService.findall(patient);
        if (userimageList.size()==0){
            return MsgBuilder.buildReturnErrorMessage("系统有误");
        }
        Userimage userimage=userimageList.get(0);
        List<Report> reportList=reportService.fingbyuid(userimageList);
        userimage.setReportList(reportList);
        return MsgBuilder.buildReturnMessage(userimage);
    }

    /**
     * 查看报告详情
     * @param id
     * @return
     */
    @RequestMapping("/reportDetails")
    public Map reportDetails(String id) {
        if (StringUtils.isBlank(id)) {
            return MsgBuilder.buildReturnErrorMessage("请选择检查报告");
        }
        Report report=new Report();

        report.setReport_id(id);
        List<Report> reportList=reportService.findall(report);
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
        //return MsgBuilder.buildReturnMessage((Map) JSON.parse(map));
        return MsgBuilder.buildReturnMessage(reportList.get(0));

    }

    /**
     * 远程专家诊断所有专家列表
     * @return
     */
    @RequestMapping("/specialistfinall")
    public Map specialistfinall(){
        User user=new User();
        user.setType(Constant.TYPE_SPECIALIST);
        List<User> userList = userService.finall(user);
        return MsgBuilder.buildReturnMessage(userList);
    }

    /**
     *添加家庭组
     * @param name
     * @param cardnum
     * @return
     */
    @RequestMapping("/addPatient")
    public Map addPatient(String name,String cardnum,HttpServletRequest httpServletRequest){
        if (StringUtils.isBlank(name)) {
            return MsgBuilder.buildReturnErrorMessage("请输入姓名");
        }
        if (StringUtils.isBlank(cardnum)) {
            return MsgBuilder.buildReturnErrorMessage("请输入卡号");
        }
        //判断卡号是否存在系统中
        Patient patient = new Patient();
        patient.setUid(cardnum);
        patient.setStatus(Constant.STATUS_VALID);
        List<Patient> patientList = patientService.findbyuid(patient);
        if(patientList.size()==0){
            //不在系统中
            return MsgBuilder.buildReturnErrorMessage("输入的卡号不存在");
        }
        //判断是否有用户已绑定卡号
        List<User> userList=userService.findbyuid(cardnum);
        if (userList.size()!=0){
            return MsgBuilder.buildReturnErrorMessage("该卡号已被绑定请解除后绑定");
        }
        User user1 = (User) httpServletRequest.getAttribute("user");
        User user=new User();
        user.setPid(user1.getId());
        user.setStatus(Constant.STATUS_VALID);
        user.setType(Constant.TYPE_USER);
        user.setCreation_time(new Date());
        user.setCardnum(cardnum);
        user.setName(name);
        userService.addUser(user);
        return MsgBuilder.buildReturnMessage("添加成功");
    }

    /**
     * 删除家庭组成员
     * @param uid
     * @return
     */
    @RequestMapping("/deluser")
    public Map deluser(String uid){
        if (StringUtils.isBlank(uid)) {
            return MsgBuilder.buildReturnErrorMessage("请选择家庭组成员");
        }

        int i=userService.deluser(uid);
        if (i==0){
            return MsgBuilder.buildReturnErrorMessage("删除失败");
        }
        return MsgBuilder.buildReturnMessage("删除成功");
    }
}
