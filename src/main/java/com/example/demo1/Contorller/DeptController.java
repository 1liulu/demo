package com.example.demo1.Contorller;

import com.example.demo1.bean.Dept;
import com.example.demo1.service.DeptService;
import com.example.demo1.util.MsgBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/dept")
public class DeptController {
    @Autowired
    DeptService deptService;

    /**
     * 根据类型查找
     * @param type
     * @return
     */
    @RequestMapping(value = "/findbytype")
    public Map findbytype(String type){
        if (StringUtils.isBlank(type)) {
            return MsgBuilder.buildReturnErrorMessage("类型不能为空");
        }
        List<Dept> deptList=deptService.findbytype(type);
        return MsgBuilder.buildReturnMessage(deptList);
    }

}
