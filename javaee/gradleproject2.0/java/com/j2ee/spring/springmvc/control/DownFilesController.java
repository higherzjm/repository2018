package com.j2ee.spring.springmvc.control;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by zjm on 2017/5/7.
 */
@Controller
@RequestMapping(value ="/downFilesController")
public class DownFilesController {
    Logger logger = Logger.getLogger(DownFilesController.class);

    /**
     * 下载txt
     * @param request
     * @param response
     */
    //http://localhost:9002/gradleproject1.0/DownFilesController/downbasefile.do
    @RequestMapping(value = "downtxtfile")
    @ResponseBody
    public void downtxtfile(HttpServletRequest request, HttpServletResponse response){
        logger.info("执行下载txt文件");
        response.setContentType("multipart/form-data");
        //response.setContentType("application/octet-stream");
        //response.setContentType("application/x-msdownload");
        //response.setContentType("application/vnd.ms-excel");
        ServletOutputStream out;
        try {
            response.setHeader("Content-Disposition", "attachment;fileName="+new String("file.txt".getBytes("GBK"), "iso-8859-1"));
            out= response.getOutputStream();
            out.write("我的文件".getBytes("UTF-8"));//把服务器默认的编码转GBK
            out.close();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 用jxl下载excel(文件名无法自定义)
     * @param request
     * @param response
     */
    @RequestMapping(value = "downexcel_jxl")
    @ResponseBody
    public void downexcel_jxl(HttpServletRequest request, HttpServletResponse response){
        logger.info("执行excel(jxl)下载文件");
        response.setContentType("application/x-msdownload");
        ServletOutputStream out=null;
        try{
             out=response.getOutputStream();
             response.reset();//重置输出流
             WritableWorkbook book = Workbook.createWorkbook(out);
            for (int page=0;page<5;page++){
                WritableSheet sheet = book.createSheet( " 第"+page+"页 " , 0 );
                System.out.println("第 "+page+" 页");
                for (int i=0;i<10;i++){
                    for (int j=0;j<5;j++){
                        Label label =  new Label( j , i , "测试"+i );//列,行,值
                        sheet.addCell(label);
                    }
                }
            }
            book.write();
            book.close();
            response.setHeader("Content-disposition", "attachment;filename="+java.net.URLEncoder.encode("excel.xls", "UTF-8"));
            out.close();
            out.flush();
        }  catch (Exception e) {
            System.out.println(e);
        }
    }



    /**
     * 用jxl下载excel(使用文件中转站下载)
     * @param request
     * @param response
     */
    @RequestMapping(value = "downexcel_jxl2")
    @ResponseBody
    public void downexcel_jxl2(HttpServletRequest request, HttpServletResponse response) {
        try  {
            String randomPath= UUID.randomUUID().toString()+".xlsx";
            String contextpath=request.getSession().getServletContext().getRealPath("/temporaryfile");
            contextpath=contextpath.replace("\\", "/");
            logger.info("contextpath:"+contextpath);
            String savePath=contextpath+"/"+randomPath;
            logger.info("savePath:"+savePath);
            File directory=new File(contextpath);
            if (!directory.isDirectory()) {// 判断文件夹是否存在
                directory.mkdir();// 创建文件夹
            }
            File file = new File(savePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            WritableWorkbook book = Workbook.createWorkbook( new File( savePath ));

            for (int page=0;page<5;page++){
                WritableSheet sheet = book.createSheet( " 第"+page+"页 " , 0 );
                System.out.println("第 "+page+" 页");
                for (int i=0;i<60000;i++){
                    for (int j=0;j<55;j++){
                        Label label =  new Label( j , i , " test " );//列,行,值
                        sheet.addCell(label);
                        jxl.write.Number number =  new jxl.write.Number( 55 , i , 555.12541 );
                        sheet.addCell(number);
                    }
                }
            }
            System.out.println("更新结束");
            book.write();
            book.close();
            response.reset();
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Content-Disposition", "attachment;filename="+new String("excel.xls".getBytes("gbk"),"iso-8859-1"));  //转码之后下载的文件不会出现中文乱码
            try{
                //以流的形式下载文件
                InputStream fis = new BufferedInputStream(new FileInputStream(savePath));
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                fis.close();
                OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
                toClient.write(buffer);
                toClient.flush();
                toClient.close();
            }catch(Exception e){
                e.printStackTrace();
            }

            file.delete();
        }  catch (Exception e) {
            System.out.println(e);
        }
        logger.info("下载结束");
    }
    /**
     * 用jxl下载excel(使用文件中转站下载,同时下载多个文件-数据缓存)
     * @param request
     * @param response
     */
    @RequestMapping(value = "downmultifile_checkNums_jxl")
    @ResponseBody
    public String downmultifile_checkNums_jxl(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session=request.getSession(true);
        try  {
            int spilt=50;
            int row=233;
            int cell=5;
            List<Object[]> datas=new ArrayList<>();
            for (int i=0;i<row;i++){
                Object[] objects=new Object[cell];
                for (int j=0;j<cell;j++){
                    objects[j]=i+"行数据"+j;
                }
                datas.add(objects);
            }

            String msg="";
            int begin=0,end=0;
            for (int m=0;m< Math.ceil(datas.size() / spilt)+1;m++){
                begin=m*spilt;
                end=(m+1)*spilt-1;
                if (begin>datas.size()){
                    begin=datas.size()-1;
                }
                if (end>datas.size()){
                    end=datas.size()-1;
                }
                if ("".equals(msg)){
                    msg=begin+":"+end;
                }else {
                    msg=msg+";"+begin+":"+end;
                }
            }
            session.setAttribute("datas",datas);
            return msg;


        }  catch (Exception e) {
            System.out.println(e);
        }
        logger.info("下载结束");
        return "";
    }
    /**
     * 用jxl下载excel(使用文件中转站下载,同时下载多个文件---下载)
     * @param request
     * @param response
     */
    @RequestMapping(value = "downmultifile_downfiles_jxl")
    @ResponseBody
    public void downmultifile_downfiles_jxl(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session=request.getSession(true);
        try  {
            int cell=5;
            String  selction=request.getParameter("selction");
            List<Object[]> datas= (List<Object[]>) session.getAttribute("datas");
            int begin=Integer.parseInt(selction.split(":")[0].toString());
            int end=Integer.parseInt(selction.split(":")[1].toString());
            List<Object[]> subdatas=datas.subList(begin,end);

            String randomPath= UUID.randomUUID().toString()+".xlsx";
            String contextpath=request.getSession().getServletContext().getRealPath("/temporaryfile");
            contextpath=contextpath.replace("\\", "/");
            logger.info("contextpath:"+contextpath);
            String savePath=contextpath+"/"+randomPath;
            logger.info("savePath:"+savePath);
            File directory=new File(contextpath);
            if (!directory.isDirectory()) {// 判断文件夹是否存在
                directory.mkdir();// 创建文件夹
            }

            outputFile(subdatas,savePath,1,cell);

            response.reset();
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Content-Disposition", "attachment;filename="+new String(randomPath.getBytes("gbk"),"iso-8859-1"));  //转码之后下载的文件不会出现中文乱码
            InputStream fis = new BufferedInputStream(new FileInputStream(savePath));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        }  catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("下载结束");
    }
    private void outputFile(List<Object[]> datas,String savePath,int dpage,int cell) {
        WritableWorkbook book=null;
        try {
            File file = new File(savePath);
            if (!file.exists()) {
                file.createNewFile();
            }
             book = Workbook.createWorkbook( new File( savePath ));
            for (int page=0;page<dpage;page++){
                WritableSheet sheet = book.createSheet( " 第"+page+"页 " , 0 );
                System.out.println("第 "+page+" 页");
                for (int i=0;i<datas.size();i++){
                    for (int j=0;j<cell;j++){
                        Label label =  new Label( j , i , datas.get(i)[j]==null?"":datas.get(i)[j].toString());//列,行,值
                        sheet.addCell(label);
                    }
                }
            }
            System.out.println("更新结束");
            book.write();
            book.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 用poi下载excel(正常)
     * @param request
     * @param response
     */
    @RequestMapping(value = "downexcel_poi")
    @ResponseBody
    public void downexcel_poi(HttpServletRequest request, HttpServletResponse response)  {
        try {
            response.reset();//重置输出流
            response.setContentType("application/vnd.ms-excel");

            final String sheetname = "sheet";
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFCellStyle style = workbook.createCellStyle();
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            HSSFFont font = workbook.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            style.setFont(font);
            HSSFSheet sheet;
            sheet = workbook.createSheet(sheetname);

            HSSFRow row = sheet.createRow(0);
            HSSFCell cell = row.createCell(0);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new HSSFRichTextString("姓名"));
            cell = row.createCell(1);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new HSSFRichTextString("年龄"));
            for (int i=1;i<10;i++){
                row = sheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(new HSSFRichTextString("姓名"+i));
                cell = row.createCell(1);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(new HSSFRichTextString("年龄"+i));
            }

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                workbook.write(bos);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                throw new Exception("导出失败");
            }
            byte[] ba = bos.toByteArray();
            ByteArrayInputStream bis = new ByteArrayInputStream(ba);
            response.setHeader("Content-disposition", "attachment;filename="+java.net.URLEncoder.encode("excel.xls", "UTF-8"));
            HSSFWorkbook xwb = (HSSFWorkbook) WorkbookFactory.create(bis);
            OutputStream out=response.getOutputStream();
            xwb.write(out);
            out.flush();
            bos.close();
            out.close();
            bis.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 用poi下载excel(先从本地读取再从浏览器下载)
     * @param request
     * @param response
     */
    @RequestMapping(value = "/downexcel_poi2")
    public void downexcel_poi2(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String fileName="123.xls";
        // 清空response
        response.reset();
        // 设置response的Header
        response.addHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes("gbk"),"iso-8859-1"));  //转码之后下载的文件不会出现中文乱码
        try{
            //以流的形式下载文件
            InputStream fis = new BufferedInputStream(new FileInputStream("E:/工作记录/1213/5月份交易成功.xls"));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            fis.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 用poi下载excel(xlsx)
     * @param request
     * @param response
     */
    @RequestMapping(value = "downexcel_poi_xlsx")
    @ResponseBody
    public void downexcel_poi_xlsx(HttpServletRequest request, HttpServletResponse response)  {
        try {
            response.reset();//重置输出流
            response.setContentType("application/vnd.ms-excel");

            final String sheetname = "sheet";
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFCellStyle style = workbook.createCellStyle();
            style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            XSSFFont font = workbook.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            style.setFont(font);
            XSSFSheet sheet;
            sheet = workbook.createSheet(sheetname);

            XSSFRow row = sheet.createRow(0);
            XSSFCell cell = row.createCell(0);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new XSSFRichTextString("姓名"));
            cell = row.createCell(1);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new XSSFRichTextString("年龄"));
            for (int i=1;i<70000;i++){
                row = sheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(new XSSFRichTextString("xlsx姓名"+i));
                cell = row.createCell(1);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(new XSSFRichTextString("xlsx年龄"+i));
            }

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                workbook.write(bos);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                throw new Exception("导出失败");
            }
            byte[] ba = bos.toByteArray();
            bos.close();
            ByteArrayInputStream bis = new ByteArrayInputStream(ba);
            response.setHeader("Content-disposition", "attachment;filename="+java.net.URLEncoder.encode("excel.xlsx", "UTF-8"));
            XSSFWorkbook xwb = (XSSFWorkbook) WorkbookFactory.create(bis);
            OutputStream out=response.getOutputStream();
            xwb.write(out);
            out.flush();
            bos.close();
            out.close();
            bis.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        logger.info("下载完毕");
    }
    /**
     * 下载csv
     * @param request
     * @param response
     */
    @RequestMapping(value = "downcsv")
    @ResponseBody
    public void down(HttpServletRequest request, HttpServletResponse response)  {
        try {
            response.reset();//重置输出流
            String headerStr="姓名,年龄\n";
            ServletOutputStream out=response.getOutputStream();
            response.setContentType("multipart/form-data");
            out.write(headerStr.getBytes("UTF-8"));
            String content="";
            for (int i=0;i<10;i++){
                content="张三"+i+","+i+"\n";
                out.write(content.getBytes("UTF-8"));
            }
            response.setHeader("Content-disposition", "attachment;filename="+java.net.URLEncoder.encode("excel.csv", "UTF-8"));
            out.close();
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 下载图片
     * @param request
     * @param response
     */
    @RequestMapping(value = "downimage")
    @ResponseBody
    public void downimage(HttpServletRequest request, HttpServletResponse response) {
        String imagePath="F:/tuofu2017/learn/Python/files/iamges/img-0.jpg";
        try  {
            response.reset();
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment;filename="+new String("downimage.jpg".getBytes("gbk"),"iso-8859-1"));  //转码之后下载的文件不会出现中文乱码
            try{
                InputStream inputStream = new BufferedInputStream(new FileInputStream(imagePath));
                byte[] buffer = new byte[inputStream.available()];
                inputStream.read(buffer);
                inputStream.close();
                OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
                outputStream.write(buffer);
                outputStream.flush();
                outputStream.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }  catch (Exception e) {
            System.out.println(e);
        }
        logger.info("下载结束");
    }
    public DownFilesController() {
        logger.info("DownFilesController初始化");
    }



}
