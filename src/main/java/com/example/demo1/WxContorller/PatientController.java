package com.example.demo1.WxContorller;

import com.example.demo1.bean.Patient;
import com.example.demo1.bean.User;
import com.example.demo1.service.PatientService;
import com.example.demo1.service.UserService;
import com.example.demo1.util.MsgBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/Patient")
public class PatientController {
    @Autowired
    PatientService patientService;
    @Autowired
    UserService userService;
    @RequestMapping("/list")
    public Map list(HttpServletRequest httpServletRequest){
        Map<String, Object> result = new HashMap<>();
        User user = (User) httpServletRequest.getAttribute("user");
        List<User> userLists=new ArrayList<User>();
        userLists.add(user);
        List<Patient> patientList=patientService.findinuid(userLists);
        result.put("patient",patientList.get(0));
        List<User> userList=userService.findbypid(user.getId());
        String[] a=new String[1];
        a[0]="";
        if (userList.size()==0){
            result.put("list",null);
        }else {
            List<Patient> patientLists=patientService.findinuid(userList);
            result.put("list",patientLists);
        }
        return MsgBuilder.buildReturnMessage(result);
    }

}
