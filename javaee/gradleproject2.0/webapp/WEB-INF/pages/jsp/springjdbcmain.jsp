<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://"+request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
  <head>
    <base href="<%=basePath%>">
    <script src="style/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <title>Title</title>
    <script type="text/javascript">
        function handle(url,type) {
            $.ajax( {
                type : "POST",//(默认: "GET") 请求方式 ("POST" 或 "GET")，
                url : url,
                data : "type="+type,
                async: false,
                dataType : "json",
                success : function(data) {
                    alert('success:'+data.ret)
                } ,
                error:function(data){
                    alert('error:'+data.ret)
                }
            });
        }
        function handle2(url,type) {
            window.location.href =url+'?type='+type;
        }

    </script>
  </head>
  <body style="text-align: left;margin-top: 20px">
  <input type="button" onclick="handle('springjdbccontrol/handleBaseInfo.do')" value="oracle更新基本数据"/>&nbsp;
  <input type="button" onclick="handle('springjdbccontrol/handleImageInfo.do','insert')" value="oracle_jdbcTemplate处理图片信息与blob大文笔数据-插入"/>&nbsp;
  <input type="button" onclick="handle2('springjdbccontrol/handleImageInfo.do','query')" value="oracle_jdbcTemplate处理图片信息与blob大文笔数据-读取"/>&nbsp;
  <input type="button" onclick="handle('springjdbccontrol/namingContextJNDI.do')" value="oracle_JNDI实例"/>&nbsp;
  </body>
</html>
