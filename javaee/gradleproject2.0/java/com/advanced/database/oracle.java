package com.advanced.database;

import oracle.sql.BLOB;
import org.junit.Test;

import java.io.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zjm on 2018/1/5.
 */
public class oracle {
    String drivername="oracle.jdbc.driver.OracleDriver";
    String url="jdbc:oracle:thin:@192.168.1.12:1521:orcl";
    String username="CUBE";
    String password="CUBE";

    /**
     * 基本查询
     */
    @Test
    public  void test1_query(){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            Class.forName(drivername);
            conn = DriverManager.getConnection(url, username, password);
            System.out.println(conn);
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT  p.MAID,round(sum(r.REFUSAL_AMOUNT*p.dtxnamt/p.STXNAMT),2) refuseam  from  CUBE.CUBE_TAB_REFUSE  r ")
                    .append("INNER JOIN  CUBE.PG_TXN p on p.STAN=r.TRANS_NO and p.TXNTYPE='01' where r.STATUS='0' GROUP by p.MAID ");

            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery();
            Map<String,Double> refusefreezes=new HashMap<String, Double>();
            while (rs.next()){
                String maid=rs.getString("MAID");
                String refusefezamout=rs.getString("refuseam");
                refusefreezes.put(maid,Double.parseDouble(refusefezamout));
            }
            System.out.println("refusefreezes:"+refusefreezes);
        } catch (Exception e) {
            e.printStackTrace();
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
    }

    /**
     * 基本更新
     */
    @Test
    public  void test1_save(){

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            Class.forName(drivername);
            conn = DriverManager.getConnection(url, username, password);
            System.out.println(conn);
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO TABLE_NOTICE (ID, TITLE, CONTENT, CDATE, CUSER, LASTMDATE, LASTMUSER) ")
                    .append("VALUES (?, 'test', 'test', sysdate, 'test', sysdate, 'test') ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setObject(1,"9999999999999");
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 存图片
     */
    @Test
    public  void writeblobdatas_image(){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        int id=1;
        try {
            Class.forName(drivername);
            conn = DriverManager.getConnection(url, username, password);
            conn.setAutoCommit(false);

            //2. 首先向表中插入空的Blob
            //★注意: 对于empty_blob()应放在SQL语句中直接赋值, 使用预置语句的方式赋值无法实现.
            String sql = "insert into user_info values(?,?,empty_blob(),null)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, "Lucy");
            ps.executeUpdate();

            //3. 查询Blob, 获得Blob的Cursor
            sql = "select image from user_info where user_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            BLOB blob = null;
            if(rs.next())
            {
                blob = (BLOB)rs.getBlob(1);
            }

            //4. 使用字节流将待入库的文件写入到blob中
            File file = new File("F:/tuofu2017/learn/Python/files/iamges/img-0.jpg");
            FileInputStream fin = new FileInputStream(file);
            byte[] temp = new byte[fin.available()];
            fin.read(temp);
            OutputStream out = blob.getBinaryOutputStream();
            out.write(temp);
            fin.close();
            out.close();

            //5. 向数据库中写入数据
            sql = "update user_info set image = ? where user_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setBlob(1, blob);
            ps.setInt(2, id);
            ps.executeUpdate();
            conn.commit();
            System.out.println("update success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 存图片2
     */
    @Test
    public  void writeblobdatas_image2(){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        int id=3;
        try {
            Class.forName(drivername);
            conn = DriverManager.getConnection(url, username, password);
            conn.setAutoCommit(false);//关闭自动提交
            String sql="insert into user_info values(?,?,?,null)"; //插入语句
            PreparedStatement pres=conn.prepareStatement(sql);
            pres.setInt(1, id);
            pres.setString(2, "jerry");
            pres.setBlob(3,BLOB.getEmptyBLOB()); //先插入空的BLOB，获取游标

            pres.executeUpdate();

            sql="select image from user_info where user_id=?";
            pres=conn.prepareStatement(sql);
            pres.setInt(1, id);
            ResultSet res=pres.executeQuery();
            res.next();
            Blob imageBlob=res.getBlob(1); //得到该空的blob

            OutputStream os = imageBlob.setBinaryStream(0);
            // 读取想要存储的图片文件
            InputStream is = new FileInputStream("F:/tuofu2017/learn/Python/files/iamges/img-0.jpg");
            // 依次读取流字节,并输出到已定义好的数据库字段中.
            int i = 0;
            while ((i = is.read()) != -1) {
                os.write(i); //Blob的输入流，相当于输入到数据库中
            }
            os.flush();
            os.close();
            conn.commit();
            conn.setAutoCommit(true);// 恢复现场

            if(res!=null)
                res.close();
            if(pres!=null)
                pres.close();

            System.out.println("插入成功!!!");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * 读取图片
     */
    @Test
    public  void readblobdatas_image(){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        int id=1;
        try {
            Class.forName(drivername);
            conn = DriverManager.getConnection(url, username, password);
            conn.setAutoCommit(false);
            String sql = "select image from user_info where user_id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,id);
            rs = stmt.executeQuery();

            //3. 读取Blob类型数据
            Blob blob = null;
            if(rs.next())
            {
                blob = rs.getBlob(1);
            }
            byte[] temp = new byte[(int)blob.length()];
            InputStream in = blob.getBinaryStream();
            in.read(temp);
            File file = new File("E:\\工作记录\\0105\\aa.jpg");
            FileOutputStream fout = new FileOutputStream(file);
            fout.write(temp);
            in.close();
            fout.close();
            System.out.println("query success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 存blob字符串1
     */
    @Test
    public  void writeblobdatas_String() {
        int id=2;
        try {
            String blobStr="";
            for(int a=0;a<5000;a++){
                blobStr="blob:"+blobStr;
            }
            blobStr=blobStr+"-----------------end-----------------------";
            System.out.println("blobStr size:"+blobStr.length());
            byte[] bytes = null;
            try {
                bytes = blobStr.getBytes("utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Connection con = DriverManager.getConnection(url, username,password);
            con.setAutoCommit(false);
            String sql1 = "insert into user_info values(?,?,null,empty_blob())";

            PreparedStatement statement = con.prepareStatement(sql1);
            statement.setInt(1,id);
            statement.setObject(2,"李四");
            statement.executeUpdate();

            String sql2 = "select blobcontent from user_info where user_id=? for update";
            PreparedStatement stmt = con.prepareStatement(sql2);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            OutputStream outStream = null;
            if (rs.next()) {
                BLOB blob = (BLOB) rs.getBlob(1);
                outStream = blob.getBinaryOutputStream();
                outStream.write(bytes, 0, bytes.length);
            }
            outStream.flush();
            outStream.close();
            con.commit();
            con.close();
            System.out.println("update success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 存blob字符串2
     */
    @Test
    public  void writeblobdatas_String2(){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        String blobStr="";
        for(int a=0;a<5000;a++){
            blobStr="blob:"+blobStr;
        }
        blobStr=blobStr+"-----------------end-----------------------";
        System.out.println("blobStr size:"+blobStr.length());
        byte[] bytes = null;
        try {
            bytes = blobStr.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int id=4;
        try {
            Class.forName(drivername);
            conn = DriverManager.getConnection(url, username, password);
            conn.setAutoCommit(false);

            //2. 首先向表中插入空的Blob
            //★注意: 对于empty_blob()应放在SQL语句中直接赋值, 使用预置语句的方式赋值无法实现.
            String sql = "insert into user_info values(?,?,null,empty_blob())";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, "张三2");
            ps.executeUpdate();

            //3. 查询Blob, 获得Blob的Cursor
            sql = "select blobcontent from user_info where user_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs=ps.executeQuery();
            BLOB blob = null;
            if(rs.next())
            {
                blob = (BLOB)rs.getBlob(1);
            }
            OutputStream  outStream = blob.getBinaryOutputStream();
            //InputStream inputStream=blob.getBinaryStream();
            outStream.write(bytes);
            outStream.close();
            //5. 向数据库中写入数据
            sql = "update user_info set blobcontent = ? where user_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setBlob(1, blob);
            ps.setInt(2, id);
            ps.executeUpdate();
            conn.commit();
            System.out.println("update success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 存blob字符串3，不用游标，直接插入BinaryStream也可以
     */
    @Test
    public  void writeblobdatas_String3(){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        String blobStr="";
        for(int a=0;a<50000;a++){
            blobStr="国"+blobStr;
        }
        InputStream contentInputStream = new ByteArrayInputStream(blobStr.getBytes());
        int id=25;
        try {
            Class.forName(drivername);
            conn = DriverManager.getConnection(url, username, password);
            conn.setAutoCommit(false);

            //2. 首先向表中插入空的Blob
            //★注意: 对于empty_blob()应放在SQL语句中直接赋值, 使用预置语句的方式赋值无法实现.
            String sql = "insert into user_info values(?,?,null,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, "中国人");
            ps.setBinaryStream(3,contentInputStream);
            ps.executeUpdate();
            conn.commit();
            System.out.println("update success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 读blob字符串
     */
    @Test
    public  void readblobdatas_string(){
        ResultSet rs = null;
        Connection conn = null;
        int id=25;
        try {
            Class.forName(drivername);
            conn = DriverManager.getConnection(url, username, password);
            conn.setAutoCommit(false);
            String sql = "select BLOBCONTENT from user_info where user_id =?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,id);
            rs = stmt.executeQuery();
            //3. 读取Blob类型数据
            Blob blob = null;
            if(rs.next())
            {
                blob = rs.getBlob(1);
            }
            InputStream fiS = blob.getBinaryStream();
            byte bytes[] = new byte[10241];
            int n=0;
            String str="";
            while (-1 != (n = fiS.read(bytes))) {// 循环读取，
                str = str+new String(bytes, 0, n);
            }
            System.out.println("str:"+str);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("query success");
    }

}
