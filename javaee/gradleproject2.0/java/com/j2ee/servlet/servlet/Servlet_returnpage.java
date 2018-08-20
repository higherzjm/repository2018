package com.j2ee.servlet.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zjm on 2018/2/9.
 */
public class Servlet_returnpage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");//解决输出中文乱码问题
        PrintWriter out = resp.getWriter();
        out.println("<html>"
                + "<head><title>page2</title></head>"
                + "<body style=\"width:100%;height:100%\"><div style=\"text-align: left;color:red;background-color: #34A0CE\">" +
                "现在是北京时间:"+new SimpleDateFormat("YYYY-MM-DD HH:mm:ss").format(new Date())+"</div></body>"
                + "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
