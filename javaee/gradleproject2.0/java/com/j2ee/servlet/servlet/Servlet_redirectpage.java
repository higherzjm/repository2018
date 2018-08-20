package com.j2ee.servlet.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *servlet 跳转页面
 */
public class Servlet_redirectpage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");//解决输出中文乱码问题
        RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/static/jsp/redirect_jsp.jsp");
        resp.reset();
        //dispatcher.include(req,resp);//后面的输出还有效
        dispatcher.forward(req,resp);//后面的输出无效
        PrintWriter pw = resp.getWriter();
        pw.println("<br/> servlet forward include 调整区别 ");
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
