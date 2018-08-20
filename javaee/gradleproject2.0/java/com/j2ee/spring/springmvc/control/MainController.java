package com.j2ee.spring.springmvc.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by zjm on 2017/5/7.
 */
@Controller
@RequestMapping(value ="/MainController")
public class MainController {
    private String name="test张三";
    Logger logger = Logger.getLogger(MainController.class);

    /**
     * 读取所有的bean
     * @return
     */
    //http://localhost:8080/gradleproject2.0/MainController/getallbans.do
    @RequestMapping("getallbans")
    @ResponseBody
    public String getAllBeans(){
        WebApplicationContext webApplicationContext= ContextLoader.getCurrentWebApplicationContext();
        String[]  beans=webApplicationContext.getBeanDefinitionNames();
        System.out.println("beans2"+ Arrays.toString(beans));
        return  Arrays.toString(beans);
    }

    //http://localhost:8080/gradleproject2.0/MainController/mainmethod.do
    @RequestMapping(value = "mainmethod")
    public String mainmethod(HttpServletRequest request, HttpServletResponse response){
        logger.info("登入");
        return "menu/index";
    }

    /**
     * http://localhost:8080/gradleproject2.0/MainController/springmvcmain.do
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "springmvcmain")
    public String springmvcmain(HttpServletRequest request, HttpServletResponse response){
        logger.info("登入");
        return "jsp/springmvcmain";
    }

    /**
     * http://localhost:8080/gradleproject2.0/MainController/httpSessionAttributeListener_addsession.do
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "httpSessionAttributeListener_addsession")
    @ResponseBody
    public String httpSessionAttributeListener_addsession(HttpServletRequest request, HttpServletResponse response){
        HttpSession session=request.getSession();
        session.setAttribute("username","张三");
        return "设置sesion用户名为:张三";
    }

    /**
     * 直接返回response输出map流对象
     * http://localhost:8080/gradleproject2.0/MainController/responseDirectReturnMap.do
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "responseDirectReturnMap")
    @ResponseBody
    public Map<String,Object> responseDirectReturnMap(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map=new HashMap<>();
        map.put("name","张三");
        map.put("age",100);
        map.put("birthday","20180626");
        return map;
    }

    /**
     * 直接返回response输出map字符串流对象
     * http://localhost:8080/gradleproject2.0/MainController/responseDirectReturnMapStr.do
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "responseDirectReturnMapStr")
    @ResponseBody
    public String responseDirectReturnMapStr(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map=new HashMap<>();
        map.put("name","张三");
        map.put("age",100);
        map.put("birthday","20180626");
        return map.toString();
    }
    /**
     * 间接返回Jackson输出流对象
     * http://localhost:8080/gradleproject2.0/MainController/jacksonIndirectReturn.do
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "jacksonIndirectReturn")
    public void jacksonIndirectReturn(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map=new HashMap<>();
        map.put("name","张三");
        map.put("age",100);
        map.put("birthday","20180626");
        toStrFromBean(map,response);
    }

    /**
     * 转json并output返回
     * @param obj
     * @param response
     */
    public  void toStrFromBean(Object obj,HttpServletResponse response)  {
        ObjectMapper objectMapper=null;
        response.setContentType("text/html;charset=UTF-8");
        if(objectMapper==null){
            objectMapper=new ObjectMapper();
        }
        try {
            objectMapper.writeValue(response.getOutputStream(), obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "httpSessionAttributeListener_newsession")
    @ResponseBody
    public String httpSessionAttributeListener_newsession(HttpServletRequest request, HttpServletResponse response){
        HttpSession session=request.getSession();
        String username= (String) session.getAttribute("username");
        return "更新sesion用户名为:"+username;
    }
    @RequestMapping(value = "servletrequestwrapper")
    @ResponseBody
    public String servletwrapper(HttpServletRequest request, HttpServletResponse response){
        HttpSession session=request.getSession();
        String studentname=request.getParameter("studentname");
        String age=request.getParameter("age");
        return "filter-Wrapper更新的参数为:"+studentname+";age:"+age;
    }
    @RequestMapping(value = "servletresponsewrapper")
    public void servletresponsewrapper(HttpServletRequest request, HttpServletResponse response){
        String ret="中华共和国万岁",ret2="-----中华共和国万岁2";
        try {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(ret);//跟@ResponseBody的效果一样
            response.getWriter().write(ret2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "retjacksonMap")
    @ResponseBody
    public Map<String,Object> retjacksonMap(HttpServletRequest request){
        try {
            String htppinfos=this.saveStream(request);
            System.out.println("htppinfos:"+htppinfos);
            Map map=new ObjectMapper().readValue(htppinfos,Map.class);
            System.out.println("map:"+map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String,Object> retmap=new HashMap<>();
        List<String>  listDatas=new ArrayList<>();
        listDatas.add("张三");listDatas.add("李四");listDatas.add("wangwu");
        retmap.put("names",listDatas);
        retmap.put("size",3);
       return retmap;
    }
    public String saveStream(HttpServletRequest request)
            throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        BufferedReader buff = null;
        StringBuffer str = null;
        try {
            str = new StringBuffer();
            String s = null;
            buff = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            while ((s = buff.readLine()) != null)
                str.append(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return str.toString();
    }
    @RequestMapping(value = "retjacksonList")
    @ResponseBody
    public List<String>  retjacksonList(){
        List<String>  listDatas=new ArrayList<>();
        listDatas.add("张三");listDatas.add("李四");listDatas.add("wangwu");

        return listDatas;
    }
    @RequestMapping(value = "retText")
    @ResponseBody
    public String retText(){
        String str="返回文本";
        return str;
    }
    @RequestMapping(value = "retOrigJackson")
    @ResponseBody
    public String retOrigJackson(){
        Theclass theclass=new Theclass();
        theclass.setId(001);
        theclass.setClassName("高三(6)班");
        Student student=new Student();
        student.setId("118532008052");
        return "";

    }

    @RequestMapping(value = "getcontextpath")
    @ResponseBody
    public String getcontextpath(HttpServletRequest request, HttpServletResponse response){
        String contextPath=request.getServletContext().getContextPath();
        String realPath=request.getServletContext().getRealPath("/uploadFile");
        logger.info("contextPath:"+contextPath+";realPath:"+realPath);
        return "contextPath:"+contextPath+";realPath:"+realPath;
    }


}

class  Student {
    private String id;
    private String name;
    private int age;
    private Theclass theclass;

    public Theclass getTheclass() {
        return theclass;
    }

    public void setTheclass(Theclass theclass) {
        this.theclass = theclass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

class Theclass{
    private int id;
    private String className;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}

