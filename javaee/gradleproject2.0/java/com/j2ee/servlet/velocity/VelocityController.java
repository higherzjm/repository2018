package com.j2ee.servlet.velocity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "/velocitycontrol")
public class VelocityController {
    @RequestMapping(value = "/velocity_base", method = RequestMethod.GET)
    public String velocity_base(Model model) {
        model.addAttribute("hello", "test velocity");
        return "velocity_base";
    }

    @RequestMapping(value = "/velocity_table", method = RequestMethod.GET)
    public String velocity_table(Model model) {
        ArrayList<User> userList = new ArrayList<User>();
        userList.add(new User("张三", "男", "厦门市湖里区"));
        userList.add(new User("李四", "男", "长汀县濯田镇"));
        userList.add(new User("王五",  "女", "厦门市思明区"));
        userList.add(new User("林七",  "未知", "厦门市集美区"));
        model.addAttribute("userList", userList);
        model.addAttribute("title","学生名单");
        return "velocity_table";
    }
}