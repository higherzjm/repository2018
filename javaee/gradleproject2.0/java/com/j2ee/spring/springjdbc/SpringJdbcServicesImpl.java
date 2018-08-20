package com.j2ee.spring.springjdbc;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.core.support.AbstractLobStreamingResultSetExtractor;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zjm on 2018/1/18.
 */
@Component
public class SpringJdbcServicesImpl implements  SpringJdbcServices  {
    Logger logger = Logger.getLogger(SpringJdbcServicesImpl.class);
    //@Autowired
    @Resource(name="jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Resource(name="jdbcTemplate2")
    private JdbcTemplate jdbcTemplate2;


    public SpringJdbcServicesImpl() {

        System.out.println("jdbcTemplate:"+jdbcTemplate);
    }


    @Override
    public void handleBaseInfo() throws  Exception{
        System.out.println("jdbcTemplate:"+jdbcTemplate);
        System.out.println("jdbcTemplate2:"+jdbcTemplate2);

        Map<String,Object> retMap=jdbcTemplate.queryForMap("select max(u.USER_ID) maxid from user_info  u");
        String maxid=retMap.get("maxid").toString();
        String sql= "insert into user_info values(?,?,null,empty_blob())";
        jdbcTemplate2.update(sql,Integer.parseInt(maxid)+1,"测试"+(Integer.parseInt(maxid)+1));
    }

    public  void  handleImageInfo_insert() throws  Exception{
        Map<String,Object> retMap=jdbcTemplate.queryForMap("select max(u.USER_ID) maxid from user_info  u");
        String maxid=retMap.get("maxid").toString();
        int newId=Integer.parseInt(maxid)+1;

        String blobStr="";
        for(int a=0;a<50000;a++){
            blobStr="blob:"+blobStr;
        }
        logger.info("blobStr length:"+blobStr.length());
        InputStream contentInputStream = new ByteArrayInputStream(blobStr.getBytes());

        InputStream imageInputStream = new FileInputStream("F:/tuofu2017/learn/Python/files/iamges/img-0.jpg");
        LobHandler lobHandler = new DefaultLobHandler();
        jdbcTemplate.execute(
                "INSERT INTO user_info VALUES (?,?,?,?)",
                new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
                    protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException {
                        ps.setInt(1, newId);
                        ps.setString(2, "jdbcTemplate 插入图片与blob文本信息:"+newId);
                        lobCreator.setBlobAsBinaryStream(ps, 3, imageInputStream, 1024*2000);
                        lobCreator.setBlobAsBinaryStream(ps, 4, contentInputStream, 1024*2000);
                    }
                }
        );
    }
    @SuppressWarnings("unchecked")
    public  Map<String,Object>  handleImageInfo_query() throws  Exception {
        //final LobHandler lobHandler = new OracleLobHandler();//过时,还能用
        final LobHandler lobHandler = new DefaultLobHandler();//可用
        String sql = "SELECT  *  from  CUBE.USER_INFO m  where m.USER_ID='20'";
        Map<String,Object> retdatas=new HashMap<>();


        jdbcTemplate.query(sql, new AbstractLobStreamingResultSetExtractor() {
            protected void streamData(ResultSet rs) throws SQLException,
                    IOException, DataAccessException {
                do{
                    Object USER_ID= rs.getObject(1);
                    Object NAME=rs.getObject(2);
                    InputStream inputStreamContent = lobHandler.getBlobAsBinaryStream(rs, "BLOBCONTENT");
                    InputStream inputStreamImage = lobHandler.getBlobAsBinaryStream(rs, "IMAGE");

                    //如下两种也行
                    InputStream inputStreamImage2 = rs.getBinaryStream(3);
                    InputStream inputStreamContent2 = rs.getBinaryStream(4);

                    StringBuffer out = new StringBuffer();
                    try {
                        byte[] b = new byte[1000000];
                        for (int n; (n = inputStreamContent.read(b)) != -1;) {
                            out.append(new String(b, 0, n));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String content=out.toString();
                    retdatas.put("USER_ID",USER_ID);
                    retdatas.put("NAME",NAME);
                    retdatas.put("content",content);
                    retdatas.put("inputStreamImage",inputStreamImage);
                }while(rs.next());
            }
        });
     /*   jdbcTemplate.query(sql,(ResultSetExtractor)(rs)->{
            System.out.println(123);
            return null;
        });*/
        return  retdatas;
    }




}
