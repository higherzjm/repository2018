package com.j2ee.restful;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/restfulAction")
public class RestfulAction {  

    // 新增
    @RequestMapping(value = "post", method = RequestMethod.POST)
    public @ResponseBody User post(@RequestParam String name,String password,String email) {
        User uadd = new User();
        uadd.setName(name);
        uadd.setPassword(password);
        uadd.setEmail(email);
        uadd.setUsername(name+name);
        return uadd;
    }    

    // 查找
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public @ResponseBody User get(@RequestParam String param) {
        User u = new User();
        u.setName(param);
        u.setPassword(param);
        u.setEmail(param);
        u.setUsername(param);
        return u;
    }

}