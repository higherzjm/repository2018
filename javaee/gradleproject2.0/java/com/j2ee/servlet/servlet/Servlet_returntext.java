package com.j2ee.servlet.servlet;

import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class Servlet_returntext extends HttpServlet {
    Logger logger = Logger.getLogger(Servlet_returntext.class);
    private String name;
    private int age;

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

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String schoolClass=req.getParameter("schoolClass");
        logger.info("age:" + age);
        logger.info("name:" + name);
        logger.info("schoolClass:" + schoolClass);
        resp.setContentType("text/html;charset=UTF-8");//解决输出中文乱码问题
        resp.getWriter().write("servlet 返回信息 age:"+age+";name:"+name+";schoolClass:"+schoolClass);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        name = config.getInitParameter("name");
        age = Integer.parseInt(config.getInitParameter("age"));
        this.init();
        Enumeration<String> en = config.getInitParameterNames();
        while (en.hasMoreElements()) {
            String property = en.nextElement();
            Object value = config.getInitParameter(property);
            logger.info(property+"->"+value);
        }
    }

    public Servlet_returntext(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Servlet_returntext() {
        logger.info("servlet初始化(默认构造器)");//整个上下文只会启动一次，请求第一次时初始化
    }

}