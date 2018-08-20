<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="style/js/jquery.min.js"></script>
    <title>Title</title>
    <script type="text/javascript">
        function downmultifile() {
            console.info("进入下载判断")
            $.ajax( {
                type : "POST",//(默认: "GET") 请求方式 ("POST" 或 "GET")，
                url : "downFilesController/downmultifile_checkNums_jxl.do",
                data : "",
                async: false,
                dataType : "text",
                success : function(data) {
                    console.info('success:'+data+":"+typeof data);
                    //var section='';
                    for(var i=0;i<data.split(';').length;i++){
                        var section=data.split(';')[i];
                        url='downFilesController/downmultifile_downfiles_jxl.do?selction='+section;
                        console.info("url:"+url)
                        setTimeout(downthread(url),8000)
                       /* $.ajax( {
                            type : "POST",//(默认: "GET") 请求方式 ("POST" 或 "GET")，
                            url : url,
                            data : "",
                            async: true,
                            dataType : "",
                            success : function(data) {
                                //window.location.href =url;
                            } ,
                            error:function(data){
                                console.log('error:'+data);
                            }
                        });*/

                        //window.location.href =url;
                        /*var form = document.forms[0];
                        form.method = "POST";
                        form.action =url;
                        form.submit();*/
                    }
                } ,
                error:function(data){
                    console.log('error:'+data);
                }
            });
        }
        function downthread(url) {
            console.info("url2:"+url)
            window.location.href =url;
        }

    </script>
</head>
<body style="text-align: left;padding: 10px 0 0 10px">
<a href="downFilesController/downtxtfile.do" >下载普通文件(txt)</a>&nbsp;
<br/><br/>
<a href="downFilesController/downexcel_jxl.do" title="文件名无法自定义">下载excel文件1(jxl)</a>&nbsp;
<a href="downFilesController/downexcel_jxl2.do" title="使用文件中转站下载">下载excel文件2(jxl)</a>
<br/><br/>
<input type="button" value="下载excel文件3(jxl,下载多个文件)" onclick="downmultifile()"/>
<br/><br/>
<a href="downFilesController/downexcel_poi.do" title="xls" >下载excel文件1(poi)</a>&nbsp;
<a href="downFilesController/downexcel_poi2.do"  title="先从本地读取再从浏览器下载">下载excel文件2(poi)</a>&nbsp;
<a href="downFilesController/downexcel_poi_xlsx.do"  title="xlsx">下载excel文件(poi-xlsx)</a>
<br/><br/>
<a href="downFilesController/downcsv.do" >下载csv文件1</a>&nbsp;
<a href="downFilesController/downimage.do" >图片下载</a>&nbsp;
<form id="uplaodform" enctype="multipart/form-data" method="post">
</form>
</body>
</html>
