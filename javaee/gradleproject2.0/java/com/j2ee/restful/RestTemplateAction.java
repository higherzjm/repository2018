package com.j2ee.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

/**
 * 使用restful 调用的服务
 */
@Controller
@RequestMapping("restTemplateAction")
public class RestTemplateAction {

    @Autowired
    private RestTemplate template;

    /**
     * http://localhost:8080/gradleproject2.0/restTemplateAction/RestTem.do?method=get
     * http://localhost:8080/gradleproject2.0/restTemplateAction/RestTem.do?method=post
     * @param method
     * @return
     */
    @RequestMapping("RestTem")
    public @ResponseBody User RestTem(String method) {
        User user = null;
        try {
            //查找
            if ("get".equals(method)) {
                user = template.getForObject(
                        "http://localhost:8080/gradleproject2.0/restfulAction/get.do?param=abcd",
                        User.class, "呜呜呜呜");

                //getForEntity与getForObject的区别是可以获取返回值和状态、头等信息
                ResponseEntity<User> re = template.
                        getForEntity("http://localhost:8080/gradleproject2.0/restfulAction/get.do?param=张三",
                                User.class, "呜呜呜呜");
                System.out.println(re.getStatusCode());
                System.out.println(re.getBody().getUsername());

                //新增
            } else if ("post".equals(method)) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("X-Auth-Token", UUID.randomUUID().toString());
                MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<String, String>();
                postParameters.add("password", "123456");
                postParameters.add("name", "allen");
                postParameters.add("email", "353263668@qq.com");
                HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(
                        postParameters, headers);
                user = template.postForObject(
                        "http://localhost:8080/gradleproject2.0/restfulAction/post.do", requestEntity,
                        User.class);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return user;

    }
}