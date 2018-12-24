package com.example.demo1.Contorller;


import com.alibaba.fastjson.JSON;
import com.example.demo1.bean.Account;
import com.example.demo1.bean.Report;
import com.example.demo1.service.ReportService;
import com.example.demo1.service.TokenService;
import com.example.demo1.util.MsgBuilder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/index")
public class IndexController {
    @Autowired
    ReportService reportService;
    @Autowired
    TokenService tokenService;
    @RequestMapping(value = "/show")
    public Map show(String page, String size, String name, HttpServletRequest request) {
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
        Account account = (Account) request.getAttribute("account");
        report.setType(account.getType());
        List<Report> reportList = reportService.findall(report);
        PageInfo<Report> pageInfo = new PageInfo<>(reportList);
        return MsgBuilder.buildReturnMessage(pageInfo);
    }

    @RequestMapping(value = "/image")
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

}
