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
        function retMap() {
            $.ajax( {
                type : "POST",//(默认: "GET") 请求方式 ("POST" 或 "GET")，
                url : "MainController/retjacksonMap.do",
                data : "",
                async: false,
                dataType : "json",
                success : function(data) {
                    alert("success:"+data)
                    alert("size："+data.size+"；names:"+data.names)
                } ,
                error:function(data){
                    alert("error:"+data)
                    alert("size："+data.size+"；names:"+data.names)
                }
            });
        }
        function retList() {
            $.ajax( {
                type : "POST",//(默认: "GET") 请求方式 ("POST" 或 "GET")，
                url : "MainController/retjacksonList.do",
                data : "",
                async: false,
                dataType : "text",
                success : function(data) {
                    alert("success:"+data)
                } ,
                error:function(data){
                    alert("error:"+data)
                }
            });
        }

        function retText() {
            $.ajax( {
                type : "POST",//(默认: "GET") 请求方式 ("POST" 或 "GET")，
                url : "MainController/retText.do",
                data : "",
                async: false,
                dataType : "text",
                success : function(data) {
                    alert("success："+data)

                } ,
                error:function(data){
                    alert("error："+data)
                }
            });
        }
        function retcontenxtpath() {
            $.ajax( {
                type : "POST",//(默认: "GET") 请求方式 ("POST" 或 "GET")，
                url : "MainController/getcontextpath.do",
                data : "",
                async: false,
                dataType : "text",
                success : function(data) {
                    alert("success："+data)

                } ,
                error:function(data){
                    alert("error："+data)
                }
            });
        }

    </script>
  </head>
  <body style="text-align: left;margin-top: 20px">
  <input type="button" onclick="retMap()" value="返回map"/>&nbsp;
  <input type="button" onclick="retText()" value="返回text"/>&nbsp;
  <input type="button" onclick="retList()" value="返回list"/>&nbsp;
  <input type="button" onclick="retcontenxtpath()" value="获取上下文路径"/>&nbsp;
  </body>
</html>
