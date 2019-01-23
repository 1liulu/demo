package com.example.demo1.Contorller;

import com.example.demo1.bean.Dept;
import com.example.demo1.bean.User;
import com.example.demo1.service.DeptService;
import com.example.demo1.service.UserService;
import com.example.demo1.util.Constant;
import com.example.demo1.util.Md5;
import com.example.demo1.util.MsgBuilder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/specialist")
public class SpecialistController {
    @Autowired
    UserService userService;
    @Autowired
    DeptService deptService;
    /**
     * 查询所有专家账号
     *
     * @param page
     * @param size
     * @param name
     * @return
     */
    @RequestMapping(value = "/specialistfinall")
    public Map specialistfinall(String page, String size, String name) {
        Integer pageNum = 1;
        Integer pageSize = 1;
        if (!StringUtils.isBlank(page)) {
            pageNum = Integer.parseInt(page);
        }
        if (!StringUtils.isBlank(size)) {
            pageSize = Integer.parseInt(size);
        }
        PageHelper.startPage(pageNum, pageSize);
        User user = new User();
        if (!StringUtils.isBlank(name)) {
            user.setName("%" + name + "%");
        }
        user.setType(Constant.TYPE_SPECIALIST);
        List<User> userList = userService.finall(user);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return MsgBuilder.buildReturnMessage(pageInfo);
    }

    /**
     * 查询专家账户详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/specialistParticulars")
    public Map specialistParticulars(String id,String type) {
        if (StringUtils.isBlank(id)) {
            return MsgBuilder.buildReturnErrorMessage("访问受限制");
        }
        if (StringUtils.isBlank(type)) {
            return MsgBuilder.buildReturnErrorMessage("类型不能为空");
        }
        User users = new User();
        users.setId(Integer.parseInt(id));
        users.setType(Constant.TYPE_SPECIALIST);
        User user = userService.finbyid(users);
        if (user == null) {
            return MsgBuilder.buildReturnErrorMessage("找不到相应信息");
        }
        user.setPassword("");
        List<Dept> deptList=deptService.findbytype(type);
        Map map=MsgBuilder.buildReturnMessage(user);
        map.put("deptList",deptList);
        return map;
    }

    /**
     * 添加专家账户
     *
     * @param phone
     * @param name
     * @return
     */
    @RequestMapping(value = "/addSpecialist")
    public Map addSpecialist(String phone, String name, String classify) {
        if (StringUtils.isBlank(phone)) {
            return MsgBuilder.buildReturnErrorMessage("请输入手机号");
        }
        if (StringUtils.isBlank(name)) {
            return MsgBuilder.buildReturnErrorMessage("请输入姓名");
        }
        if (StringUtils.isBlank(classify)) {
            return MsgBuilder.buildReturnErrorMessage("请选择");
        }
        //查看手机号是否存在
        User user = new User();
        user.setType(Constant.TYPE_SPECIALIST);
        user.setPhone(phone);
        User user1 = userService.finbyphone(user);
        if (user1 != null) {
            return MsgBuilder.buildReturnErrorMessage("手机号已被注册");
        }
        //手机号不存在  添加用户
        user.setStatus(Constant.STATUS_VALID);
        user.setName(name);
        user.setCreation_time(new Date());
        String password = Md5.MD5(phone);
        user.setPassword(password);
        user.setClassify(Integer.parseInt(classify));
        int i = userService.addSpecia(user);
        if (i > 0) {
            return MsgBuilder.buildReturnMessage("添加成功");
        }
        return MsgBuilder.buildReturnErrorMessage("系统错误，添加失败");
    }

    /**
     * 修改专家账户信息
     *
     * @param name
     * @param phone
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateSpecialist")
    public Map updateSpecialist(String name, String phone, String id, String classify) {
        if (StringUtils.isBlank(phone)) {
            return MsgBuilder.buildReturnErrorMessage("请输入手机号");
        }
        if (StringUtils.isBlank(name)) {
            return MsgBuilder.buildReturnErrorMessage("请输入姓名");
        }
        if (StringUtils.isBlank(id)) {
            return MsgBuilder.buildReturnErrorMessage("选择有误");
        }
        if (StringUtils.isBlank(classify)) {
            return MsgBuilder.buildReturnErrorMessage("请选择选择");
        }
        User user = new User();
        user.setType(Constant.TYPE_SPECIALIST);
        user.setId(Integer.parseInt(id));
        //账号是否存在
        User users = userService.finbyid(user);
        if (users == null) {
            return MsgBuilder.buildReturnErrorMessage("账户不存在");
        }
        //查看手机号是否存在
        user.setPhone(phone);
        User user1 = userService.finbyphone(user);
        if (user1 != null) {
            return MsgBuilder.buildReturnErrorMessage("手机号已被注册");
        }
        //手机号不存在  修改用户
        user.setName(name);
        user.setClassify(Integer.parseInt(classify));
        int i = userService.updateSpecia(user);
        if (i > 0) {
            return MsgBuilder.buildReturnMessage("修改成功");
        }
        return MsgBuilder.buildReturnErrorMessage("系统错误，修改失败");
    }

    /**
     * 删除专家账户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteSpecialist")
    public Map deleteSpecialist(String id) {
        if (StringUtils.isBlank(id)) {
            return MsgBuilder.buildReturnErrorMessage("选择账号有误");
        }
        User user1 = new User();
        user1.setId(Integer.parseInt(id));
        user1.setType(Constant.TYPE_SPECIALIST);
        //账号是否存在
        User users = userService.finbyid(user1);
        if (users == null) {
            return MsgBuilder.buildReturnErrorMessage("账号不存在");
        }
        int i = userService.deleteDoctor(Integer.parseInt(id));
        if (i > 0) {
            return MsgBuilder.buildReturnMessage("删除成功");
        }
        return MsgBuilder.buildReturnErrorMessage("系统错误，删除失败");
    }
}
