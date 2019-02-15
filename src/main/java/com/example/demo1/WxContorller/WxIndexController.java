package com.example.demo1.WxContorller;

import com.example.demo1.bean.Patient;
import com.example.demo1.bean.User;
import com.example.demo1.service.PatientService;
import com.example.demo1.util.Constant;
import com.example.demo1.util.MsgBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/wxindex")
public class WxIndexController {
    @Autowired
    PatientService patientService;

    /**
     * 个人中心
     * @param httpServletRequest
     * @return
     */
    @RequestMapping("/user")
    public Map user(HttpServletRequest httpServletRequest) {
        User user = (User) httpServletRequest.getAttribute("user");
        Patient patient = new Patient();
        patient.setUid(user.getCardnum());
        patient.setStatus(Constant.STATUS_VALID);
        List<Patient> patientList=patientService.findbyuid(patient);
        Map<String, Object> result = new HashMap<>();
        result.put("userphone",user.getPhone());
        result.put("patient",patientList.get(0));
        return MsgBuilder.buildReturnMessage(result);
    }
}
