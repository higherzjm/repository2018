package com.javase;


import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.junit.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zjm on 2017/11/20.
 *
 */
public class a_temporary_test {
    @Test
    public  void test1(){
        System.out.println("123");
    }

    @Test
    public  void  test2(){
        try  {
            WritableWorkbook book = Workbook.createWorkbook( new File( "E:\\工作记录\\1212\\test.xlsx " ));

            for (int page=0;page<5;page++){
                WritableSheet sheet = book.createSheet( " 第"+page+"页 " , 0 );
                System.out.println("第 "+page+" 页");
                for (int i=0;i<20;i++){
                    for (int j=0;j<55;j++){
                        Label label =  new Label( j , i , " test " );//列,行,值
                        sheet.addCell(label);
                    /*jxl.write.Number number =  new jxl.write.Number( 1 , i , 555.12541 );
                    sheet.addCell(number);*/
                    }
                }
            }
            System.out.println("更新结束");
            book.write();
            book.close();

        }  catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public  void test3(){
        List<Integer>  list=new ArrayList<>();
        for (int i=0;i<100;i++){
            list.add(i);
        }
        System.out.println(list.subList(0,20));

    }

    @Test
    public void test4(){
        for (int i=0;i<10;i++){
            switch (i){
                case 0: System.out.println("-------------0"); break;
                case 1:
                case 2:
                case 9:
                    default:
                        System.out.println("switch case:"+i);
            }
        }
    }

    @Test
    public void test5(){
        int a=10;
        while (a-->0){
            System.out.println(a);
        }
    }
    @Test
    public void test6(){
        String a="aaa";
        System.out.println(a.intern());

    }

    @Test
    public void test7(){
        System.out.println(9>>2);//右移2为，相对于除以4
        System.out.println(11<<2);//左移2为，相对于乘以4

    }

    @Test
    public void test8(){
        int a=0x7fffffff;
        System.out.println(a);
    }

    @Test
    public  void test9(){
        List<Student> students=new ArrayList<>();
        for (int i=0;i<10;i++){
            students.add(new Student(String.valueOf(i),"张三"+i));
        }
        System.out.println("students:"+students);
        students.get(1).changeName();
        System.out.println("-------------------------------------");
        System.out.println("students2:"+students);
    }

    @Test
    public void test10(){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date=fmt.parse("2018-10-10");
            System.out.println(date.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

class  Student{
    public String id;
    public String name;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void changeName(){
        this.name="change:"+this.name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}