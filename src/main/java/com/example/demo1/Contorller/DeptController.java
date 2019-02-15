package com.example.demo1.Contorller;

import com.example.demo1.bean.Dept;
import com.example.demo1.service.DeptService;
import com.example.demo1.util.MsgBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/dept")
@Api(tags = "字典")
public class DeptController {
    @Autowired
    DeptService deptService;

    /**
     * 根据类型查找
     * @param type
     * @return
     */
    @RequestMapping(value = "/findbytype", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value = "根据类型查找字典", notes = "返回提示")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型", required = true, dataType = "String", paramType = "query")
    })
    public Map findbytype(String type){
        if (StringUtils.isBlank(type)) {
            return MsgBuilder.buildReturnErrorMessage("类型不能为空");
        }
        List<Dept> deptList=deptService.findbytype(type);
        return MsgBuilder.buildReturnMessage(deptList);
    }

}
