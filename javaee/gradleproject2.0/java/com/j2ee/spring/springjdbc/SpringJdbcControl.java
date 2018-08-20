package com.j2ee.spring.springjdbc;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zjm on 2018/1/18.
 */
@Controller
@RequestMapping(value ="/springjdbccontrol")
public class SpringJdbcControl {
    Logger logger = Logger.getLogger(SpringJdbcControl.class);
    @Autowired
    private SpringJdbcServices springJdbcServices;
    @Autowired
    private SpringJdbcServicesImpl springJdbcServices2;
    @RequestMapping(value = "springjdbcmain")
    public String springmvcmain(HttpServletRequest request, HttpServletResponse response){
        return "jsp/springjdbcmain";
    }
    @RequestMapping(value = "handleBaseInfo")
    @ResponseBody
    public Map<String,Object> handleBaseInfo(HttpServletRequest request, HttpServletResponse response){
       Map<String,Object> retmap=new HashMap<>();
        try {
            springJdbcServices.handleBaseInfo();
            retmap.put("ret","添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            retmap.put("ret","添加失败");
        }
        return retmap;
    }
    @RequestMapping(value = "handleImageInfo")
    @ResponseBody
    public Map<String,Object> handleImageInfo(HttpServletRequest request, HttpServletResponse response){
        String type=request.getParameter("type");
        Map<String, Object> retmap = new HashMap<>();
        if ("insert".equals(type)) {
            logger.info("insert");
            try {
                springJdbcServices2.handleImageInfo_insert();
                retmap.put("ret", "添加成功");
            } catch (Exception e) {
                e.printStackTrace();
                retmap.put("ret", "添加失败");
            }
        }else if ("query".equals(type)){
            logger.info("query");
            try {
                Map<String,Object> datas=springJdbcServices2.handleImageInfo_query();
                Object id=datas.get("USER_ID");
                Object name=datas.get("NAME");
                String content=datas.get("content").toString();
                InputStream inputStreamImage= (InputStream) datas.get("inputStreamImage");
                logger.info(content.length()+":"+content);
                logger.info("id:"+id+";name:"+name);
                retmap.put("ret", "读取成功:"+id+":"+name+":"+content);
                response.reset();

                response.setContentType("application/octet-stream");
                response.addHeader("Content-Disposition", "attachment;filename="+new String("downimage.jpg".getBytes("gbk"),"iso-8859-1"));  //转码之后下载的文件不会出现中文乱码
                try{
                    byte[] buffer = new byte[1000000];
                     /*
                       never correct to use the return value of this method to allocate
                        a buffer intended to hold all data in this stream.
                        byte[] buffer = new byte[inputStreamImage.available()];
                     */
                    inputStreamImage.read(buffer);
                    inputStreamImage.close();
                    OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
                    outputStream.write(buffer);
                    outputStream.flush();
                    outputStream.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
                retmap.put("ret", "读取失败");
            }
        }else {


        }
        return retmap;
    }
    @RequestMapping(value = "namingContextJNDI")
    @ResponseBody
    public Map<String,Object> namingContextJNDI(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> retmap=new HashMap<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        List<List<Object>> datas=new ArrayList<>();
        try {
            InitialContext initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/xx");

            //DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/xx”");//这一种不行
            conn=ds.getConnection();
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT  m.MAID,m.RNAME,m.TNAME  from  CUBE.MM_MERCHACC m   ");

            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery();
            while (rs.next()){
                List data=new ArrayList();
                String MAID=rs.getString("MAID");
                String RNAME=rs.getString("RNAME");
                String TNAME=rs.getString("TNAME");
                data.add(MAID);data.add(RNAME);data.add(TNAME);
                System.out.println("MAID:"+MAID+";RNAME:"+RNAME+";TNAME:"+TNAME);
                 datas.add(data);
            }
            retmap.put("ret",datas);
        } catch (Exception e) {
            e.printStackTrace();
            retmap.put("ret","读取失败");
        } finally {
            if (conn!=null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt!=null){
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return retmap;
    }
}
